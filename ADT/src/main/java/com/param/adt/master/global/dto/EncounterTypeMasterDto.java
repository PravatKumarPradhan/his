package com.param.adt.master.global.dto;

public class EncounterTypeMasterDto {
	private int encounterTypeId;

	private String encounterTypeName;

	private String encounterTypeCode;

	private char status;

	private int createdBy;

	private String createdDate;

	private int updatedBy;

	private String updatedDate;

	private Integer organizationId;

	private Integer offset;

	private Integer noOfRecordsPerPage;
	
	

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getNoOfRecordsPerPage() {
		return noOfRecordsPerPage;
	}

	public void setNoOfRecordsPerPage(Integer noOfRecordsPerPage) {
		this.noOfRecordsPerPage = noOfRecordsPerPage;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public int getEncounterTypeId() {
		return encounterTypeId;
	}

	public void setEncounterTypeId(int encounterTypeId) {
		this.encounterTypeId = encounterTypeId;
	}

	public String getEncounterTypeName() {
		return encounterTypeName;
	}

	public void setEncounterTypeName(String encounterTypeName) {
		this.encounterTypeName = encounterTypeName;
	}

	public String getEncounterTypeCode() {
		return encounterTypeCode;
	}

	public void setEncounterTypeCode(String encounterTypeCode) {
		this.encounterTypeCode = encounterTypeCode;
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
