package com.filtering_service.filter_and_sort.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.filtering_service.filter_and_sort.model.Products;



public interface ProductRepository extends JpaRepository<Products, String> {
	public List<Products> findByItemIgnoreCaseInOrderByPriceAsc(@Param("item") List<String> item);
	public List<Products> findByItemIgnoreCaseInOrderByPriceDesc(@Param("item") List<String> item);
	public List<Products> findByPriceGreaterThanEqualAndPriceLessThanEqualOrderByPriceAsc(double greater, double less);
	public List<Products> findAllByOrderByPriceAsc();
}
