package com.param.global.dto.items;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ItemListResponse {
	protected Integer itemId;

	protected String assetCategory;

	protected String productCategory;

	protected String generic;

	protected String itemCode;

	protected String itemName;

	protected String itemDetails;

	public ItemListResponse() {
		super();
	}

	public ItemListResponse(Integer itemId, String itemCode, String itemName) {
		super();
		this.itemId = itemId;
		this.itemCode = itemCode;
		this.itemName = itemName;
	}
	
	public ItemListResponse(Integer itemId, String assetCategory, String productCategory, String itemCode,
			String itemName) {
		super();
		this.itemId = itemId;
		this.assetCategory = assetCategory;
		this.productCategory = productCategory;
		this.itemCode = itemCode;
		this.itemName = itemName;
	}


	public ItemListResponse(Integer itemId, String assetCategory, String productCategory, String itemCode,
			String itemName, String generic) {
		super();
		this.itemId = itemId;
		this.assetCategory = assetCategory;
		this.productCategory = productCategory;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.generic = generic;
	}

	public ItemListResponse(Integer itemId, String generic, String itemCode, String itemName) {
		super();
		this.itemId = itemId;
		this.generic = generic;
		this.itemCode = itemCode;
		this.itemName = itemName;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getAssetCategory() {
		return assetCategory;
	}

	public void setAssetCategory(String assetCategory) {
		this.assetCategory = assetCategory;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public String getGeneric() {
		return generic;
	}

	public void setGeneric(String generic) {
		this.generic = generic;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDetails() {
		return this.itemCode + " | " + this.itemName;
	}

	public void setItemDetails(String itemDetails) {
		this.itemDetails = itemDetails;
	}
}
