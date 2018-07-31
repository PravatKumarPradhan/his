package com.param.adt.master.global.dto;

public class ServiceCodeTypeDto 
{
	private int serviceCodeTypeId;
	
	private String ServiceCodeType;
	
	private char status;
	
	private int createdBy;
	
	private String createdDate;

	private int updatedBy;

	private String updatedDate;
	
private Integer organizationId;
	
	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getServiceCodeTypeId() {
		return serviceCodeTypeId;
	}

	public void setServiceCodeTypeId(int serviceCodeTypeId) {
		this.serviceCodeTypeId = serviceCodeTypeId;
	}

	public String getServiceCodeType() {
		return ServiceCodeType;
	}

	public void setServiceCodeType(String serviceCodeType) {
		ServiceCodeType = serviceCodeType;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
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
	
	
}
