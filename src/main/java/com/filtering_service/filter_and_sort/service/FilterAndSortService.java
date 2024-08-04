package com.filtering_service.filter_and_sort.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.expression.ParseException;

import com.filtering_service.filter_and_sort.dto.FilteredProducts;
import com.filtering_service.filter_and_sort.dto.SortedItems;
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
	
	public ArrayList<Products> createProducts (List<Products> products) throws Exception{
		
		try {
			return (ArrayList<Products>)productRepository.saveAll(products);
		}catch (Exception e) {
			throw new BadRequestException();
		}
	}
	
	
	//accepts an optional query string parameter item, and when this parameter is present, 
	//only the records with the matching barcodes are returned. The value of this parameter is 
	//case insensitive, so "Pants" and "pants" are equivalent. Moreover, it might contain 
	//several values, separated by commas (e.g. city=pants,jacket), meaning that records 
	//with the item matching any of these values must be returned
	
	// test by http://localhost:9090/sort/items?item=pants,jacket&sort=desc
	public ArrayList<SortedItems> getItems (String items, String sort) throws Exception {
		
			List<SortedItems>itemList = new ArrayList<>();
			

			// parse 
			try {
				List<String> cityList = new ArrayList<String>(Arrays.asList(items.split(",")));
				if (sort == null || sort.equalsIgnoreCase("asc")) {
				
				itemList = productRepository.findByItemIgnoreCaseInOrderByPriceAsc(cityList)
						.stream()
						.map(product -> 
						modelMapper.map(product, SortedItems.class))
						.collect(Collectors.toList());
				}else {
					itemList = productRepository.findByItemIgnoreCaseInOrderByPriceDesc(cityList)
							.stream()
							.map(product -> 
							modelMapper.map(product, SortedItems.class))
							.collect(Collectors.toList());
					
				}
				
			}catch (ParseException e) {
				throw new ParseException( 0, "String is not parsable", e);
			}catch (Exception e) {
				throw new NullPointerException();
			}
			
			return (ArrayList<SortedItems>)itemList;
	}
	
	
	public ArrayList<FilteredProducts> filtered_Prodcuts(double minPrice, double maxPrice) throws Exception{ // get the product from repository and map to the DTO
		try {
			List<FilteredProducts>filtered_productList = new ArrayList<>();
			
			// filter 
			filtered_productList = productRepository.findByPriceGreaterThanEqualAndPriceLessThanEqualOrderByPriceAsc(minPrice, maxPrice)
					.stream()
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
			
			sorted_productList = productRepository.findAllByOrderByPriceAsc()
					.stream()
					.map(product -> 
					modelMapper.map(product, SortedProducts.class))
					.collect(Collectors.toList());
			
				return sorted_productList.toArray(new SortedProducts[sorted_productList.size()]);
		}catch (Exception e) {
			throw new BadRequestException();
		}
		
	}
	 
	 public Iterable<Products> save(List<Products> users) {
	        return productRepository.saveAll(users);
	 }

}
