package com.filtering_service.filter_and_sort.dto;

public class SortedProducts{
	
	private String barcode;
	
	public SortedProducts() {
		
	}
	
	public SortedProducts(String barcode) {
		super();
		this.barcode = barcode;
	}
	
	public SortedProducts(String barcode, String item, int price, int discount, boolean available) {
		super();
		this.barcode = barcode;
	}
	
	public String getBarCode() {
		return this.barcode;
	}

	public void setBarCode(String barcode) {
		this.barcode = barcode;
	}

}
