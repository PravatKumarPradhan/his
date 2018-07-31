package com.param.global.dto.global;

public class ProductCategory {

	protected Integer id;

	protected String category;

	public ProductCategory(Integer id, String category) {
		super();
		this.id = id;
		this.category = category;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
