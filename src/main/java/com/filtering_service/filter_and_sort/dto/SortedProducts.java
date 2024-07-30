package com.filtering_service.filter_and_sort.dto;

public class SortedProducts{
	
	private String barCode;
	
	public SortedProducts(String barCode) {
		this.barCode = barCode;
	}
	
	public SortedProducts(String barCode, String item, int price, int discount, boolean available) {
		super();
		this.barCode = barCode;
	}
	
	public String getBarCode() {
		return this.barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	
	/**
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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
	}*/

	



}
