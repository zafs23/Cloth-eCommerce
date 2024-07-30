package com.filtering_service.filter_and_sort.controller;

import java.util.ArrayList;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.filtering_service.filter_and_sort.dto.FilteredProducts;
import com.filtering_service.filter_and_sort.dto.SortedProducts;
import com.filtering_service.filter_and_sort.service.FilterAndSortService;


@RestController
public class SampleController {

	final String uri = "https://jsonmock.hackerrank.com/api/inventory";
	RestTemplate restTemplate = new RestTemplate();
	String result = restTemplate.getForObject(uri, String.class);
	JSONObject root = new JSONObject(result);

	JSONArray data = root.getJSONArray("data");
	
	
	@Autowired
    FilterAndSortService productService;

	@CrossOrigin
	@GetMapping("/filter/price/{initial_price}/{final_price}")
	private ResponseEntity< ArrayList<FilteredProducts>> filtered_books(@PathVariable("initial_price") int init_price , @PathVariable("final_price") int final_price)   
	{ 

		try {

			ArrayList<FilteredProducts> books = new ArrayList<FilteredProducts>();
			books = productService.filtered_Prodcuts(init_price, final_price);

			return new ResponseEntity<ArrayList<FilteredProducts>>(books,HttpStatus.OK);
			//return new ResponseEntity.ok().body(books);

		} catch (Exception E) {
			System.out.println("Error encountered : " + E.getMessage());
			return new ResponseEntity<ArrayList<FilteredProducts>>(HttpStatus.NOT_FOUND);
		}

	}

	@CrossOrigin
	@GetMapping("/sort/price")
	private ResponseEntity<SortedProducts[]> sorted_books() {

		try {

			SortedProducts[] ans = new SortedProducts[data.length()];
			ans = productService.sorted_Products();
			return new ResponseEntity<SortedProducts[]>(ans, HttpStatus.OK);

		} catch (Exception E) {
			System.out.println("Error encountered : " + E.getMessage());
			return new ResponseEntity<SortedProducts[]>(HttpStatus.NOT_FOUND);
		}

	}

}
