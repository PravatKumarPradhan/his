package com.param.global.org.dto;

import java.util.Date;

import javax.persistence.Column;

public class OrganizationUnitLicenceDetailsDto {

	private Integer licenceDetailsId;
	
	private Integer licenceTypeId;
	
	private Integer organizationId;
	
	private Integer unitId;
	
	private String licenceNumber;
	
	private String expiryDate;
	
	private Integer createdBy;
	
	private Date createdDate;
	
	private Character isUnit;
	
	public String licenceType;

	public Integer getLicenceDetailsId() {
		return licenceDetailsId;
	}

	public void setLicenceDetailsId(Integer licenceDetailsId) {
		this.licenceDetailsId = licenceDetailsId;
	}

	public Integer getLicenceTypeId() {
		return licenceTypeId;
	}

	public void setLicenceTypeId(Integer licenceTypeId) {
		this.licenceTypeId = licenceTypeId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public String getLicenceNumber() {
		return licenceNumber;
	}

	public void setLicenceNumber(String licenceNumber) {
		this.licenceNumber = licenceNumber;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Character getIsUnit() {
		return isUnit;
	}

	public void setIsUnit(Character isUnit) {
		this.isUnit = isUnit;
	}

	public String getLicenceType() {
		return licenceType;
	}

	public void setLicenceType(String licenceType) {
		this.licenceType = licenceType;
	}
	
}
