package com.filtering_service.filter_and_sort.service;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filtering_service.filter_and_sort.dto.FilteredProducts;
import com.filtering_service.filter_and_sort.dto.SortedProducts;
import com.filtering_service.filter_and_sort.model.Products;
import com.filtering_service.filter_and_sort.repository.ProductRepository;


@Service
public class FilterAndSortService{

	@Autowired
	private ProductRepository productRepository;
	private static ModelMapper modelMapper = new ModelMapper();
	
	public FilterAndSortService (ProductRepository productRepository) {
        this.productRepository = productRepository;
	}
	
	public ArrayList<FilteredProducts> filtered_Prodcuts(double minPrice, double maxPrice) throws Exception{ // get the product from repository and map to the DTO
		List<Products>filtered_productList = new ArrayList<>();
		
		//List<Products>filtered = filtered_productList.stream().filter(p -> p.getPrice()>= minPrice && p.getPrice()<=maxPrice).collect(Collectors.toList());
	    for (Products p: productRepository.findAll()) {
	    	if (p.getPrice()>= minPrice && p.getPrice()<=maxPrice) {
	    		filtered_productList.add(p);
	    	}
	    }
				
	 Collections.sort(filtered_productList, (a,b)-> Double.compare(a.getPrice(), b.getPrice()));
                
		
		return (ArrayList<FilteredProducts>) getFilterProductDTO(filtered_productList);
		
	}
	
	public SortedProducts[] sorted_Products() throws Exception{
		List<Products>sorted_productList = new ArrayList<>();
		sorted_productList = productRepository.findAll();
		Collections.sort(sorted_productList, (a,b)-> Double.compare(a.getPrice(), b.getPrice()));
		
		return getListProductsDTO(sorted_productList).toArray(new SortedProducts[sorted_productList.size()]);
	}
	
	 private List<SortedProducts> getListProductsDTO(List<Products> products_entity_list){
	        List<SortedProducts> products_Dto_list = new ArrayList<SortedProducts>();
	        for (Products product_entity : products_entity_list) {
	            products_Dto_list.add(getSortedProductDTO(product_entity));
	        }
	        return products_Dto_list;
	 }
	 
	 private SortedProducts getSortedProductDTO(Products productEntity){
	       SortedProducts product = modelMapper.map(productEntity, SortedProducts.class);
	        return product;    
	 }
	 
	 private List<FilteredProducts> getFilterProductDTO(List<Products> products_entity_list){
	        List<FilteredProducts> products_Dto_list = new ArrayList<FilteredProducts>();
	        for (Products product_entity : products_entity_list) {
	            products_Dto_list.add(getFilteredProductDto(product_entity));
	        }
	        return products_Dto_list;
	 }
	 
	 private FilteredProducts getFilteredProductDto(Products productEntity){
	       FilteredProducts product = modelMapper.map(productEntity, FilteredProducts.class);
	        return product;    
	 }

}
