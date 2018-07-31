package com.param.global.dto.items;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ItemsDetailsListResponse extends ItemsDetail {

	protected Long stockAvailable;

	protected Long stockInTransit;

	protected Long lastMonthSale;

	protected Long currentMonthSale;

	protected Long maxOrderQuantity;

	protected List<UomResponse> uom;

	protected List<BatchResponse> batch;

	public ItemsDetailsListResponse() {
		super();
	}

	public ItemsDetailsListResponse(Integer itemId, String itemCode, String itemName, Integer genericId,
			String genericName, Integer manufacturerId, String manufacturerName, Integer taxId, String taxName,
			Double taxPercent, Boolean isOtc, Long stockAvailable, Long stockInTransit, Long lastMonthSale,
			Long currentMonthSale, Long maxOrderQuantity, List<UomResponse> uom) {
		super(itemId, itemCode, itemName, genericId, genericName, manufacturerId, manufacturerName, taxId, taxName,
				taxPercent, isOtc);
		this.stockAvailable = stockAvailable;
		this.stockInTransit = stockInTransit;
		this.lastMonthSale = lastMonthSale;
		this.currentMonthSale = currentMonthSale;
		this.maxOrderQuantity = maxOrderQuantity;
		this.uom = uom;
	}

	public Long getStockAvailable() {
		return stockAvailable;
	}

	public void setStockAvailable(Long stockAvailable) {
		this.stockAvailable = stockAvailable;
	}

	public Long getStockInTransit() {
		return stockInTransit;
	}

	public void setStockInTransit(Long stockInTransit) {
		this.stockInTransit = stockInTransit;
	}

	public Long getLastMonthSale() {
		return lastMonthSale;
	}

	public void setLastMonthSale(Long lastMonthSale) {
		this.lastMonthSale = lastMonthSale;
	}

	public Long getCurrentMonthSale() {
		return currentMonthSale;
	}

	public void setCurrentMonthSale(Long currentMonthSale) {
		this.currentMonthSale = currentMonthSale;
	}

	public Long getMaxOrderQuantity() {
		return maxOrderQuantity;
	}

	public void setMaxOrderQuantity(Long maxOrderQuantity) {
		this.maxOrderQuantity = maxOrderQuantity;
	}

	public List<UomResponse> getUom() {
		return uom;
	}

	public void setUom(List<UomResponse> uom) {
		this.uom = uom;
	}

	public List<BatchResponse> getBatch() {
		return batch;
	}

	public void setBatch(List<BatchResponse> batch) {
		this.batch = batch;
	}

	public void addBatch(BatchResponse batch) {
		if (this.batch == null) {
			this.batch = new ArrayList<BatchResponse>();
		}
		
		this.batch.add(batch);
	}

	public void removeBatch(BatchResponse batch) {
		this.batch.remove(batch);
	}
}