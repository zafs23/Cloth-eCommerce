package com.filtering_service.filter_and_sort;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.filtering_service.filter_and_sort.model.Products;
import com.filtering_service.filter_and_sort.service.FilterAndSortService;

@SpringBootApplication
public class FilterAndSortApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilterAndSortApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(FilterAndSortService filterAndSortService) {
		return args -> {
			// read json and write to db
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Products>> typeReference = new TypeReference<List<Products>>() {
			};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/product.json");
			try {
				List<Products> products = mapper.readValue(inputStream, typeReference);
				filterAndSortService.save(products);
				System.out.println("Products Saved!");
			} catch (IOException e) {
				System.out.println("Unable to save products: " + e.getMessage());
			}
		};
	}

}
