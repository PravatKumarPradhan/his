package com.param.billing.global.dto;

import com.param.billing.global.transaction.model.BillingStatusMapperId;

public class BillingStatusMapperDto {
	private BillingStatusMapperId billingStatusMapperId;
	private String dateTime;
	private Integer unitId;
	private Integer orgnisationId;
	private Character status;
	private String createdDate;
	private Integer createdBy;
	private String updatedDate;
	private Integer updatedBy;
	public BillingStatusMapperId getBillingStatusMapperId() {
		return billingStatusMapperId;
	}
	public void setBillingStatusMapperId(BillingStatusMapperId billingStatusMapperId) {
		this.billingStatusMapperId = billingStatusMapperId;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	public Integer getOrgnisationId() {
		return orgnisationId;
	}
	public void setOrgnisationId(Integer orgnisationId) {
		this.orgnisationId = orgnisationId;
	}
	public Character getStatus() {
		return status;
	}
	public void setStatus(Character status) {
		this.status = status;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
}
