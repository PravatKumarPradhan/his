package com.param.global.dto.global;

public class SaleType {
	
	protected Integer id;
	
	protected String saleType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}

	public SaleType(Integer id, String saleType) {
		super();
		this.id = id;
		this.saleType = saleType;
	}
	
}