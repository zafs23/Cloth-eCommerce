package com.filtering_service.filter_and_sort.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

import com.filtering_service.filter_and_sort.model.Products;



public interface ProductRepository extends JpaRepository<Products, String> {
	//public List<Products> findAll();

}
