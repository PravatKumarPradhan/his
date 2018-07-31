package com.param.global.dto.global;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StoreIndentResponse {
	
	protected Integer id;
	
	protected Date indentDate;
	
	protected String indentNo;
	
	protected String storeName;
	
	protected String requestedBy;
	
	protected String authorizedBy;
	
	protected Date authorizedDate;
	
	protected List<ItemResponse> items;

	public StoreIndentResponse() {
		super();
	}

	public StoreIndentResponse(Integer id, Date indentDate, String indentNo, String storeName, String requestedBy,
			String authorizedBy, Date authorizedDate) {
		super();
		this.id = id;
		this.indentDate = indentDate;
		this.indentNo = indentNo;
		this.storeName = storeName;
		this.requestedBy = requestedBy;
		this.authorizedBy = authorizedBy;
		this.authorizedDate = authorizedDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getIndentDate() {
		return indentDate;
	}

	public void setIndentDate(Date indentDate) {
		this.indentDate = indentDate;
	}

	public String getIndentNo() {
		return indentNo;
	}

	public void setIndentNo(String indentNo) {
		this.indentNo = indentNo;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}

	public String getAuthorizedBy() {
		return authorizedBy;
	}

	public void setAuthorizedBy(String authorizedBy) {
		this.authorizedBy = authorizedBy;
	}

	public Date getAuthorizedDate() {
		return authorizedDate;
	}

	public void setAuthorizedDate(Date authorizedDate) {
		this.authorizedDate = authorizedDate;
	}

	public List<ItemResponse> getItems() {
		return items;
	}

	public void setItems(List<ItemResponse> items) {
		this.items = items;
	}	
	
	public void addItems(ItemResponse item) {
		if(this.items == null)
			this.items = new ArrayList<ItemResponse>();
		
		this.items.add(item);
	}	
}