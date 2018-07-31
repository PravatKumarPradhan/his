package com.param.global.dto.global;

import java.util.List;

import com.param.global.dto.items.ItemsDetailsListResponse;

public class DirectPurchaseRequestResponse {
	
	private List<ItemsDetailsListResponse> itemDetailsList;
	
	private List<OtherChargesResponse> otherChargesList;
	

	public List<ItemsDetailsListResponse> getItemDetailsList() {
		return itemDetailsList;
	}

	public void setItemDetailsList(List<ItemsDetailsListResponse> itemDetailsList) {
		this.itemDetailsList = itemDetailsList;
	}

	public List<OtherChargesResponse> getOtherChargesList() {
		return otherChargesList;
	}

	public void setOtherChargesList(List<OtherChargesResponse> otherChargesList) {
		this.otherChargesList = otherChargesList;
	}
	
	

}
