package com.filtering_service.filter_and_sort.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Products {
	
	@Id
	@Column(name="id")
	@JsonProperty("barcode")
	private String barcode;
	
	 @JsonProperty("item")
	private String item;
	 
	 @JsonProperty("category")
	private String category;
	 
	 @JsonProperty("price")
	private double price;
	 
	 @JsonProperty("discount")
	private int discount;
	 
	 @JsonProperty("available")
	private int available;
	
	public Products() {
		
	}

	public Products(String barcode, String item, String category, int price, int discount, int available) {
		super();
		this.barcode = barcode;
		this.item = item;
		this.category = category;
		this.price = price;
		this.discount = discount;
		this.available = available;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(Double minPrice) {
		this.price = minPrice;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int isAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}

}
