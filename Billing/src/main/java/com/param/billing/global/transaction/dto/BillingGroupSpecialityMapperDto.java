package com.param.billing.global.transaction.dto;

import com.param.billing.global.transaction.model.BillingGroupSpecialityMapperId;

public class BillingGroupSpecialityMapperDto {
	private BillingGroupSpecialityMapperId billingGroupSpecialityMapperId;
	
	private Integer billingGroupMasterId;
	
	private Integer specialityMasterId;
	
	private char status;
	
	private Integer createdBy;
	
	private String cratedDate;
	
	private Integer updatedBy;
	
	private String updatedDate;
	
	private Integer unitId;
	
	private Integer orgnizationId;

	public BillingGroupSpecialityMapperId getBillingGroupSpecialityMapperId() {
		return billingGroupSpecialityMapperId;
	}

	public void setBillingGroupSpecialityMapperId(BillingGroupSpecialityMapperId billingGroupSpecialityMapperId) {
		this.billingGroupSpecialityMapperId = billingGroupSpecialityMapperId;
	}

	public Integer getBillingGroupMasterId() {
		return billingGroupMasterId;
	}

	public void setBillingGroupMasterId(Integer billingGroupMasterId) {
		this.billingGroupMasterId = billingGroupMasterId;
	}

	public Integer getSpecialityMasterId() {
		return specialityMasterId;
	}

	public void setSpecialityMasterId(Integer specialityMasterId) {
		this.specialityMasterId = specialityMasterId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public String getCratedDate() {
		return cratedDate;
	}

	public void setCratedDate(String cratedDate) {
		this.cratedDate = cratedDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getOrgnizationId() {
		return orgnizationId;
	}

	public void setOrgnizationId(Integer orgnizationId) {
		this.orgnizationId = orgnizationId;
	}
	
}
