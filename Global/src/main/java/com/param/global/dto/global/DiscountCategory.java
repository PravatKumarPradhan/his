package com.param.global.dto.global;

public class DiscountCategory {
	protected Integer id;

	protected String discountCategory;

	public DiscountCategory(Integer id, String discountCategory) {
		super();
		this.id = id;
		this.discountCategory = discountCategory;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDiscountCategory() {
		return discountCategory;
	}

	public void setDiscountCategory(String discountCategory) {
		this.discountCategory = discountCategory;
	}
}
