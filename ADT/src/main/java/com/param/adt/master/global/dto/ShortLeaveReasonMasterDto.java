package com.param.adt.master.global.dto;

public class ShortLeaveReasonMasterDto {

	private int shortLeaveReasonId;

	private String shortLeaveReasonName;

	private String shortLeaveReasonCode;

	private char status;

	private int createdBy;

	private String createdDate;

	private int updatedBy;

	private String updatedDate;

	private Integer organizationId;

	private Integer offset;

	private Integer noOfRecordsPerPage;
	
	private Integer unitId;
	
	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

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

	public int getShortLeaveReasonId() {
		return shortLeaveReasonId;
	}

	public void setShortLeaveReasonId(int shortLeaveReasonId) {
		this.shortLeaveReasonId = shortLeaveReasonId;
	}

	public String getShortLeaveReasonName() {
		return shortLeaveReasonName;
	}

	public void setShortLeaveReasonName(String shortLeaveReasonName) {
		this.shortLeaveReasonName = shortLeaveReasonName;
	}

	public String getShortLeaveReasonCode() {
		return shortLeaveReasonCode;
	}

	public void setShortLeaveReasonCode(String shortLeaveReasonCode) {
		this.shortLeaveReasonCode = shortLeaveReasonCode;
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

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

}
