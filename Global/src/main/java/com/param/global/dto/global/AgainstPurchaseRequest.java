package com.param.global.dto.global;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AgainstPurchaseRequest {
	
    private Date fromDate;
	
	private Date toDate;
	
	protected String prNo;
	
	protected Integer prId;	
	
	protected Integer storeId;
	
	private List<Integer> itemIds;
	
	
	public AgainstPurchaseRequest() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Date getFromDate() {
		return fromDate;
	}


	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}


	public Date getToDate() {
		return toDate;
	}


	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getPrNo() {
		return prNo;
	}


	public void setPrNo(String prNo) {
		this.prNo = prNo;
	}


	public Integer getStoreId() {
		return storeId;
	}


	public void setStoreId(Integer storeId) { 
		this.storeId = storeId;
	}


	public List<Integer> getItemIds() {
		return itemIds;
	}


	public void setItemIds(List<Integer> itemIds) {
		this.itemIds = itemIds;
	}


	public Integer getPrId() {
		return prId;
	}


	public void setPrId(Integer prId) {
		this.prId = prId;
	}
	
	

}
