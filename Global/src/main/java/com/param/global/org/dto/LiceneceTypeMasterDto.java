package com.param.global.org.dto;



public class LiceneceTypeMasterDto {

	private int licenceTypeId;

	private String licenceType;

	private Integer organizationId;
	
	private String liceenceTypeCode;

	private char status;

	private int createdBy;

	private String createdDate;
	
	private int updatedBy;
	
	private String updatedDate;

	public int getLicenceTypeId() {
		return licenceTypeId;
	}

	public void setLicenceTypeId(int licenceTypeId) {
		this.licenceTypeId = licenceTypeId;
	}

	public String getLicenceType() {
		return licenceType;
	}

	public void setLicenceType(String licenceType) {
		this.licenceType = licenceType;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getLiceenceTypeCode() {
		return liceenceTypeCode;
	}

	public void setLiceenceTypeCode(String liceenceTypeCode) {
		this.liceenceTypeCode = liceenceTypeCode;
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
	
	
}
