package com.param.billing.global.dto;

public class BillingGroupMasterDto {
	private int billingGroupId;
	
	private String billingGroup;
	
	private String billingCode;
	
	private String billingGroupDesc;
	
	private char status;
	
	private String createdDate;
	
	private Integer createdBy;
	
	private String updatedDate;
	
	private Integer updatedBy;
	
	private Integer unitId;
	
	private Integer orgnisationId;
	
	private Integer offset;
	
	private Integer recordPerPage;
	
	private Integer specialityId;
	
	private String specialityName;

	public int getBillingGroupId() {
		return billingGroupId;
	}

	public void setBillingGroupId(int billingGroupId) {
		this.billingGroupId = billingGroupId;
	}

	public String getBillingGroup() {
		return billingGroup;
	}

	public void setBillingGroup(String billingGroup) {
		this.billingGroup = billingGroup;
	}

	public String getBillingCode() {
		return billingCode;
	}

	public void setBillingCode(String billingCode) {
		this.billingCode = billingCode;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
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

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getRecordPerPage() {
		return recordPerPage;
	}

	public void setRecordPerPage(Integer recordPerPage) {
		this.recordPerPage = recordPerPage;
	}

	public Integer getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(Integer specialityId) {
		this.specialityId = specialityId;
	}

	public String getSpecialityName() {
		return specialityName;
	}

	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}

	public String getBillingGroupDesc() {
		return billingGroupDesc;
	}

	public void setBillingGroupDesc(String billingGroupDesc) {
		this.billingGroupDesc = billingGroupDesc;
	}
	
}
