package com.filtering_service.filter_and_sort.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Products {
	@Id
	@Column(name="id")
	private String barcode;
	private String item;
	private double price;
	private int discount;
	private boolean available;
	
	public Products() {
		
	}
	public Products(String barcode, String item, int price, int discount, boolean available) {
		super();
		this.barcode = barcode;
		this.item = item;
		this.price = price;
		this.discount = discount;
		this.available = available;
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
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}

}
