package com.param.global.dto.global;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class PurchaseRequestSearchDto extends AgainstPurchaseRequest{
	
	private Long Id;
	
	private Date prDate;
	
	private String storeName;
	
	private String requestBy;
	
	private String authorisedBy;
	
	List<PurchaseRequestDetailResponse> items;
	
	
	public PurchaseRequestSearchDto(Long id, String prNo, Date prDate, Integer storeId, String storeName,
			String requestBy, String authorisedBy) {
		super();
		Id = id;
		super.prNo = prNo;
		this.prDate = prDate;
		super.storeId = storeId;
		this.storeName = storeName;
		this.requestBy = requestBy;
		this.authorisedBy = authorisedBy;
	}
	
	public PurchaseRequestSearchDto(Long id, String prNo) {
		super();
		Id = id;
		super.prNo = prNo;
	}





	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Date getPrDate() {
		return prDate;
	}

	public void setPrDate(Date prDate) {
		this.prDate = prDate;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getRequestBy() {
		return requestBy;
	}

	public void setRequestBy(String requestBy) {
		this.requestBy = requestBy;
	}

	public String getAuthorisedBy() {
		return authorisedBy;
	}

	public void setAuthorisedBy(String authorisedBy) {
		this.authorisedBy = authorisedBy;
	}

	public List<PurchaseRequestDetailResponse> getItems() {
		return items;
	}

	public void setItems(List<PurchaseRequestDetailResponse> items) {
		this.items = items;
	}

}
