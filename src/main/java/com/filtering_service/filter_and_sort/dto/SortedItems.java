package com.filtering_service.filter_and_sort.dto;

public class SortedItems {
	
	
	private String barcode;
	private String item;
	private String price;
	
	public SortedItems() {
		
	}
	
	public SortedItems(String barcode, String item, String price) {
		super();
		this.barcode = barcode;
		this.item = item;
	}
	

	public String getbarcode() {
		return barcode;
	}

	public void setbarcode(String barcode) {
		this.barcode = barcode;
	}
	
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
