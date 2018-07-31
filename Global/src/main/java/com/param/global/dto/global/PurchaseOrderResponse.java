package com.param.global.dto.global;

import java.util.List;

public class PurchaseOrderResponse {
	
    List<AgainstPOSearchResponse> purchaseOrderList;
	
	List<OtherChargesResponse> otherChargesList;

	public List<AgainstPOSearchResponse> getPurchaseOrderList() {
		return purchaseOrderList;
	}

	public void setPurchaseOrderList(List<AgainstPOSearchResponse> purchaseOrderList) {
		this.purchaseOrderList = purchaseOrderList;
	}

	public List<OtherChargesResponse> getOtherChargesList() {
		return otherChargesList;
	}

	public void setOtherChargesList(List<OtherChargesResponse> otherChargesList) {
		this.otherChargesList = otherChargesList;
	}
	
	

}
