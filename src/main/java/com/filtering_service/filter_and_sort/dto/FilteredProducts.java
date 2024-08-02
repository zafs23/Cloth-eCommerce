package com.filtering_service.filter_and_sort.dto;




public class FilteredProducts {
	
	
	private String barcode;
	
	public FilteredProducts() {
		
	}
	
	public FilteredProducts(String barcode) {
		super();
		this.barcode = barcode;
	}
	

	public String getbarcode() {
		return barcode;
	}

	public void setbarcode(String barcode) {
		this.barcode = barcode;
	}

	


}
