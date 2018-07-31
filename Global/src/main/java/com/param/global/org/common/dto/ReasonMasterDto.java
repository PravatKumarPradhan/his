package com.param.global.org.common.dto;

public class ReasonMasterDto {
	
	private int reasonMasterId;

	private String reasonDesc;
	
	private String reasonCode;

	private Character status;

	private Integer createdBy;

	private String createdDate;

	private Integer updatedBy;

	private String updatedDate;

	private Integer organizationId;

	private Integer reasonTypeId;
	
	private String reasonTypeDesc;
	
	private Integer offset;

	private Integer noOfRecordsPerPage;

	public int getReasonMasterId() {
		return reasonMasterId;
	}

	public void setReasonMasterId(int reasonMasterId) {
		this.reasonMasterId = reasonMasterId;
	}

	public String getReasonDesc() {
		return reasonDesc;
	}

	public void setReasonDesc(String reasonDesc) {
		this.reasonDesc = reasonDesc;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
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

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getReasonTypeId() {
		return reasonTypeId;
	}

	public void setReasonTypeId(Integer reasonTypeId) {
		this.reasonTypeId = reasonTypeId;
	}

	public String getReasonTypeDesc() {
		return reasonTypeDesc;
	}

	public void setReasonTypeDesc(String reasonTypeDesc) {
		this.reasonTypeDesc = reasonTypeDesc;
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

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
	
	

}
