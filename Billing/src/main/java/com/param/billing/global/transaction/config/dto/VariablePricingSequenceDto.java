package com.param.billing.global.transaction.config.dto;

import java.util.Date;

public class VariablePricingSequenceDto {

private int variablePricingSequenceId;
	
	private int visitType;
	
	private int patientTypeSeqNo;
	
	private int billingBedCategorySeqNo;
	
	private int statChargeSeqNo;
	
	private int paymentEntitlementSeqNo;
	
	private int unitId;
	
	private int orgId;
	
	private char status;
	
	private int createdBy;
	
	private Date createdDate;
	
	private int updatedBy;
	
	private Date updatedDate;

	public int getVariablePricingSequenceId() {
		return variablePricingSequenceId;
	}

	public void setVariablePricingSequenceId(int variablePricingSequenceId) {
		this.variablePricingSequenceId = variablePricingSequenceId;
	}

	public int getVisitType() {
		return visitType;
	}

	public void setVisitType(int visitType) {
		this.visitType = visitType;
	}

	public int getPatientTypeSeqNo() {
		return patientTypeSeqNo;
	}

	public void setPatientTypeSeqNo(int patientTypeSeqNo) {
		this.patientTypeSeqNo = patientTypeSeqNo;
	}

	public int getBillingBedCategorySeqNo() {
		return billingBedCategorySeqNo;
	}

	public void setBillingBedCategorySeqNo(int billingBedCategorySeqNo) {
		this.billingBedCategorySeqNo = billingBedCategorySeqNo;
	}

	public int getStatChargeSeqNo() {
		return statChargeSeqNo;
	}

	public void setStatChargeSeqNo(int statChargeSeqNo) {
		this.statChargeSeqNo = statChargeSeqNo;
	}

	public int getPaymentEntitlementSeqNo() {
		return paymentEntitlementSeqNo;
	}

	public void setPaymentEntitlementSeqNo(int paymentEntitlementSeqNo) {
		this.paymentEntitlementSeqNo = paymentEntitlementSeqNo;
	}

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = (status == '\u0000') ? 'I' : status;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	
	
	
}
