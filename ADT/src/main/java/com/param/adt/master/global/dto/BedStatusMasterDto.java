package com.param.adt.master.global.dto;

public class BedStatusMasterDto {
	private int bedStatusId;

	private String bedStatusCode;

	private String bedStatusDesc;

	private char status;

	private int createdBy;

	private String createdDate;

	private int updatedBy;

	private String updatedDate;

	private Integer organizationId;
	
	private Integer unitId;
	
	

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

	public int getBedStatusId() {
		return bedStatusId;
	}

	public void setBedStatusId(int bedStatusId) {
		this.bedStatusId = bedStatusId;
	}

	public String getBedStatusCode() {
		return bedStatusCode;
	}

	public void setBedStatusCode(String bedStatusCode) {
		this.bedStatusCode = bedStatusCode;
	}

	public String getBedStatusDesc() {
		return bedStatusDesc;
	}

	public void setBedStatusDesc(String bedStatusDesc) {
		this.bedStatusDesc = bedStatusDesc;
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
