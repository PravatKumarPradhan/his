package com.param.global.dto;

public class AssociatedCompanyMasterDto {
	private int associatedCompanyMasterId;
	
	private String associatedCompanyName;
	
	private char status;

	private int createdBy;
	
	private Long createdDate;
	
	private Integer companyMasterId;
	
	private Integer updatedBy;
	
	private Long updatedDate;
	
	private Integer unitId;
	
	private Integer organizationId;

	public int getAssociatedCompanyMasterId() {
		return associatedCompanyMasterId;
	}

	public void setAssociatedCompanyMasterId(int associatedCompanyMasterId) {
		this.associatedCompanyMasterId = associatedCompanyMasterId;
	}

	public String getAssociatedCompanyName() {
		return associatedCompanyName;
	}

	public void setAssociatedCompanyName(String associatedCompanyName) {
		this.associatedCompanyName = associatedCompanyName;
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

	public Long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCompanyMasterId() {
		return companyMasterId;
	}

	public void setCompanyMasterId(Integer companyMasterId) {
		this.companyMasterId = companyMasterId;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Long updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	
}
