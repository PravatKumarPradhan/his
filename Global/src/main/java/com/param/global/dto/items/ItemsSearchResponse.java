package com.param.global.dto.items;

public class ItemsSearchResponse {

	protected Integer id;

	protected String details;

	public ItemsSearchResponse() {
		super();
	}

	public ItemsSearchResponse(Integer id, String details) {
		this.id = id;
		this.details = details;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}