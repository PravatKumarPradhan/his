package com.param.global.dto.global;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.param.global.dto.items.BatchListResponse;

@JsonInclude(Include.NON_NULL)
public class AgainstGrnDetailResponse extends BatchListResponse {

	protected Integer grnId;
	
	protected Integer grnDetailId;

	protected Integer grnQuantity;
	
	protected Long pendingQuantity;

	protected Integer storeId;

	protected String Store;

	public Integer getGrnId() {
		return grnId;
	}

	public void setGrnId(Integer grnId) {
		this.grnId = grnId;
	}

	public Integer getGrnDetailId() {
		return grnDetailId;
	}

	public void setGrnDetailId(Integer grnDetailId) {
		this.grnDetailId = grnDetailId;
	}

	public Integer getGrnQuantity() {
		return grnQuantity;
	}

	public void setGrnQuantity(Integer grnQuantity) {
		this.grnQuantity = grnQuantity;
	}

	public Long getPendingQuantity() {
		return pendingQuantity;
	}

	public void setPendingQuantity(Long pendingQuantity) {
		this.pendingQuantity = pendingQuantity;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getStore() {
		return Store;
	}

	public void setStore(String store) {
		Store = store;
	}

}
