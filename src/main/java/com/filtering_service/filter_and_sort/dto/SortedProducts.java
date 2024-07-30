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

}
