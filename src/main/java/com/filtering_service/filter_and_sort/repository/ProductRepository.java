package com.filtering_service.filter_and_sort.repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.filtering_service.filter_and_sort.model.Products;



public interface ProductRepository extends JpaRepository<Products, String> {
	public List<Products> findAll();

}
