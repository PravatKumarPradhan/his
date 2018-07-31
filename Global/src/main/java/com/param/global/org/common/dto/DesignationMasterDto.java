package com.param.global.org.common.dto;



public class DesignationMasterDto {
	
 private int employeeDesignationId;
	 
	 private Integer organizationId;
	 
	 private String employeeDesignationCode;

	 private String employeeDesignationDescription;
	  
	 private Character status;
	 
	 private String createdDate;

	 private Integer createdBy;
	 
	 private Integer updatedBy;
	 
	 private String updatedDate;
	
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

	public int getEmployeeDesignationId() {
		return employeeDesignationId;
	}

	public void setEmployeeDesignationId(int employeeDesignationId) {
		this.employeeDesignationId = employeeDesignationId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getEmployeeDesignationCode() {
		return employeeDesignationCode;
	}

	public void setEmployeeDesignationCode(String employeeDesignationCode) {
		this.employeeDesignationCode = employeeDesignationCode;
	}

	public String getEmployeeDesignationDescription() {
		return employeeDesignationDescription;
	}

	public void setEmployeeDesignationDescription(String employeeDesignationDescription) {
		this.employeeDesignationDescription = employeeDesignationDescription;
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

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
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

}
