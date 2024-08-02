package com.filtering_service.filter_and_sort.service;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Sort;

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
		try {
			List<FilteredProducts>filtered_productList = new ArrayList<>();
			
			// filter 
			filtered_productList = getAllProducts()
					.stream()
					.filter(p -> p.getPrice()>= minPrice && p.getPrice()<=maxPrice)
					.map(product -> 
					modelMapper.map(product, FilteredProducts.class))
					.collect(Collectors.toList());
			return (ArrayList<FilteredProducts>) filtered_productList;
			
		}catch (Exception e) {
			throw new BadRequestException();
		}
		
		
	}
	
	public SortedProducts[] sorted_Products() throws Exception{
		try {
			List<SortedProducts>sorted_productList = new ArrayList<>();
			
			sorted_productList = getAllProducts()
					.stream()
					.map(product -> 
					modelMapper.map(product, SortedProducts.class))
					.collect(Collectors.toList());
			
				return sorted_productList.toArray(new SortedProducts[sorted_productList.size()]);
		}catch (Exception e) {
			throw new BadRequestException();
		}
		
	}
	
	 
	 public List<Products> getAllProducts () {
		 return productRepository.findAll(Sort.by(Sort.Direction.ASC, "price"));
	 }
	 
	 public Iterable<Products> save(List<Products> users) {
	        return productRepository.saveAll(users);
	 }

}
