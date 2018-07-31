package com.param.global.dto.items;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ItemsDetailsResponse extends ItemsDetail {

	protected List<BatchResponse> batch = new ArrayList<BatchResponse>();

	protected Long allocationId;

	public ItemsDetailsResponse() {
		super();
	}

	public ItemsDetailsResponse(Integer itemId, String itemCode, String itemName, Integer genericId, String genericName,
			Integer manufacturerId, String manufacturerName, Integer taxId, String taxName, Double taxPercent,
			Boolean isOtc, List<BatchResponse> batch) {
		super(itemId, itemCode, itemName, genericId, genericName, manufacturerId, manufacturerName, taxId, taxName,
				taxPercent, isOtc);
		this.batch = batch;
	}

	public Long getAllocationId() {
		return allocationId;
	}

	public void setAllocationId(Long allocationId) {
		this.allocationId = allocationId;
	}

	public List<BatchResponse> getBatch() {
		return batch;
	}

	public void setBatch(List<BatchResponse> batch) {
		this.batch = batch;
	}

	public void addBatch(BatchResponse batch) {
		this.batch.add(batch);
	}

	public void removeBatch(BatchResponse batch) {
		this.batch.remove(batch);
	}
}
