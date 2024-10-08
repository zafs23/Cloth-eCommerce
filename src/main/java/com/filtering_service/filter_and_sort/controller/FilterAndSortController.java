package com.filtering_service.filter_and_sort.controller;

import java.util.ArrayList;


import java.util.List;

import org.apache.coyote.BadRequestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.filtering_service.filter_and_sort.dto.FilteredProducts;
import com.filtering_service.filter_and_sort.dto.SortedItems;
import com.filtering_service.filter_and_sort.dto.SortedProducts;
import com.filtering_service.filter_and_sort.exception.ResourceNotFoundException;
import com.filtering_service.filter_and_sort.model.Products;
import com.filtering_service.filter_and_sort.service.FilterAndSortService;

import jakarta.validation.Valid;

@RestController
public class FilterAndSortController {

	final String uri = "https://jsonmock.hackerrank.com/api/inventory";
	RestTemplate restTemplate = new RestTemplate();
	String result = restTemplate.getForObject(uri, String.class);
	JSONObject root = new JSONObject(result);

	JSONArray data = root.getJSONArray("data");

	@Autowired
	FilterAndSortService productService;

	@PostMapping("/products")
	public ResponseEntity<List<Products>> createUsers(@RequestBody @Valid List<Products> products) {
		List<Products> createdUsers = productService.createProducts(products);
		return new ResponseEntity<>(createdUsers, HttpStatus.CREATED);

	}

	// tested using curl
	// curl -X POST http://localhost:9090/products -H "Content-Type:
	// application/json" -d '[{"barcode": "74002300", "item": "Jacket","category":
	// "Full Body", "price": 690, "discount": 4, "available": 1}]'

	// test it by http://localhost:9090/sort/items?item=pants,jacket&sort=desc
	@GetMapping("/sort/items")
	public ResponseEntity<ArrayList<SortedItems>> getProducts(@RequestParam(required = false) String item,
			@RequestParam(required = false) String category, @RequestParam(required = false) String sort)
			throws Exception {
		try {
			ArrayList<SortedItems> itemList = productService.getItems(item, sort);

			return new ResponseEntity<ArrayList<SortedItems>>(itemList, HttpStatus.OK);
			// return new ResponseEntity<FilteredProducts[]>(books.toArray(new
			// FilteredProducts[books.size()]),HttpStatus.OK);

		} catch (ParseException E) {
			System.out.println("Error encountered : " + E.getMessage());
			return new ResponseEntity<ArrayList<SortedItems>>(HttpStatus.NOT_FOUND);
		} catch (ResourceNotFoundException E) {
			return new ResponseEntity<ArrayList<SortedItems>>(HttpStatus.NOT_FOUND);
	    }catch (Exception E) {
			System.out.println("Error encountered : " + E.getMessage());
			return new ResponseEntity<ArrayList<SortedItems>>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/hello-world")
	public String helloWorld() {
		return "Hello World";
	}

	// private ResponseEntity<FilteredProducts[]>
	// filtered_books(@PathVariable("initial_price") int init_price ,
	// @PathVariable("final_price") int final_price)

	@CrossOrigin
	@GetMapping("/filter/price/{initial_price}/{final_price}")
	private ResponseEntity<ArrayList<FilteredProducts>> filtered_books(
			@PathVariable("initial_price")int init_price, @PathVariable("final_price") int final_price)

	{

		try {

			ArrayList<FilteredProducts> books = new ArrayList<FilteredProducts>();
			books = productService.filtered_Prodcuts(init_price, final_price);

			return new ResponseEntity<ArrayList<FilteredProducts>>(books, HttpStatus.OK);
			// return new ResponseEntity<FilteredProducts[]>(books.toArray(new
			// FilteredProducts[books.size()]),HttpStatus.OK);

		} catch (ResourceNotFoundException E) {
			System.out.println("Error encountered : " + E.getMessage());
			return new ResponseEntity<ArrayList<FilteredProducts>>(HttpStatus.NOT_FOUND);
			// return new ResponseEntity<FilteredProducts[]>(HttpStatus.NOT_FOUND);
		} catch (Exception E) {
			System.out.println("Error encountered : " + E.getMessage());
			// return new ResponseEntity<FilteredProducts[]>(HttpStatus.BAD_REQUEST);
			return new ResponseEntity<ArrayList<FilteredProducts>>(HttpStatus.BAD_REQUEST);
		}

	}

	@CrossOrigin
	@GetMapping("/sort/price")
	private ResponseEntity<SortedProducts[]> sorted_books() {

		try {

			SortedProducts[] ans = new SortedProducts[data.length()];

			ans = productService.sorted_Products();
			return new ResponseEntity<SortedProducts[]>(ans, HttpStatus.OK);

		} catch (ResourceNotFoundException E) {
			System.out.println("Error encountered : " + E.getMessage());
			return new ResponseEntity<SortedProducts[]>(HttpStatus.NOT_FOUND);
		} catch (Exception E) {
			System.out.println("Error encountered : " + E.getMessage());
			return new ResponseEntity<SortedProducts[]>(HttpStatus.BAD_REQUEST);
		}

	}

}
