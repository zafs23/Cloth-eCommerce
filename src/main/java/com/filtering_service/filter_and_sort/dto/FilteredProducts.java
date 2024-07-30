package com.filtering_service.filter_and_sort.dto;



public class FilteredProducts {
	
	private String barCode;
	
	public FilteredProducts(String barCode) {
		this.barCode = barCode;
	}
	

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	


}
