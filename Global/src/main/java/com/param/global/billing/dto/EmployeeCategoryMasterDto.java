package com.param.global.billing.dto;



public class EmployeeCategoryMasterDto {

	private Integer employeeCategoryId;

	private Integer organizationId;

	private String employeeCategoryCode;

	private String employeeCategoryDescription;

	private Character status;

	private String createdDate;

	private Integer createdBy;

	private Integer updatedBy;

	private String updatedDate;
	
	private Integer offset;

	private Integer noOfRecordsPerPage;


	public Integer getEmployeeCategoryId() {
		return employeeCategoryId;
	}

	public void setEmployeeCategoryId(Integer employeeCategoryId) {
		this.employeeCategoryId = employeeCategoryId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getEmployeeCategoryCode() {
		return employeeCategoryCode;
	}

	public void setEmployeeCategoryCode(String employeeCategoryCode) {
		this.employeeCategoryCode = employeeCategoryCode;
	}

	public String getEmployeeCategoryDescription() {
		return employeeCategoryDescription;
	}

	public void setEmployeeCategoryDescription(String employeeCategoryDescription) {
		this.employeeCategoryDescription = employeeCategoryDescription;
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
	
	
	
}
