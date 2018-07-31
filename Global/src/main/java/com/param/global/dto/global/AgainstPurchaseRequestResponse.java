package com.param.global.dto.global;

import java.util.List;

public class AgainstPurchaseRequestResponse {
	
	List<PurchaseRequestSearchDto> purchaseRequestList;
	
	List<OtherChargesResponse> otherChargesList;

	public List<PurchaseRequestSearchDto> getPurchaseRequestList() {
		return purchaseRequestList;
	}

	public void setPurchaseRequestList(List<PurchaseRequestSearchDto> purchaseRequestList) {
		this.purchaseRequestList = purchaseRequestList;
	}

	public List<OtherChargesResponse> getOtherChargesList() {
		return otherChargesList;
	}

	public void setOtherChargesList(List<OtherChargesResponse> otherChargesList) {
		this.otherChargesList = otherChargesList;
	}
	
	
	

}
