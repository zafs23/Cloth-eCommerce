package com.filtering_service.filter_and_sort.dto;

public class FilteredProducts {

	private String barcode;
	private String price;

	public FilteredProducts() {

	}

	public FilteredProducts(String barcode, String price) {
		super();
		this.barcode = barcode;
		this.setPrice(price);
	}

	public String getbarcode() {
		return barcode;
	}

	public void setbarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
