package com.param.billing.global.dto;

import java.util.List;

public class BillPaymentCollectionReqDto {
	private Integer billingMasterId;
	private Integer payeeId;
	private Integer orgId;
	private Integer unitId;
	private List<BillingPaymentDetailsReqDto> listBillingPaymentDetailsReqDto;
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	public List<BillingPaymentDetailsReqDto> getListBillingPaymentDetailsReqDto() {
		return listBillingPaymentDetailsReqDto;
	}
	public void setListBillingPaymentDetailsReqDto(List<BillingPaymentDetailsReqDto> listBillingPaymentDetailsReqDto) {
		this.listBillingPaymentDetailsReqDto = listBillingPaymentDetailsReqDto;
	}
	public Integer getBillingMasterId() {
		return billingMasterId;
	}
	public void setBillingMasterId(Integer billingMasterId) {
		this.billingMasterId = billingMasterId;
	}
	public Integer getPayeeId() {
		return payeeId;
	}
	public void setPayeeId(Integer payeeId) {
		this.payeeId = payeeId;
	}
}
