package com.param.global.dto;

import java.util.Date;

public class PaymentEntitlementMasterDto {

	private int paymentEntitlementId;

	private String paymentEntitlementDesc;

	private String paymentEntitlementCode;

	private int createdBy;
	
	private String createdDate;

	private char status;
	
	private int updatedBy;
	
	private Date updatedDate;
	
	private Integer organizationId;

	public int getPaymentEntitlementId() {
		return paymentEntitlementId;
	}

	public void setPaymentEntitlementId(int paymentEntitlementId) {
		this.paymentEntitlementId = paymentEntitlementId;
	}

	public String getPaymentEntitlementDesc() {
		return paymentEntitlementDesc;
	}

	public void setPaymentEntitlementDesc(String paymentEntitlementDesc) {
		this.paymentEntitlementDesc = paymentEntitlementDesc;
	}

	public String getPaymentEntitlementCode() {
		return paymentEntitlementCode;
	}

	public void setPaymentEntitlementCode(String paymentEntitlementCode) {
		this.paymentEntitlementCode = paymentEntitlementCode;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	
	
	
}
