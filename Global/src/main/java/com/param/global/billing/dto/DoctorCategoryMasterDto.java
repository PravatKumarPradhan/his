package com.param.global.billing.dto;



public class DoctorCategoryMasterDto {

	private int doctorCategoryId;

	private Integer organizationId;

	private String doctorCategoryCode;

	private String doctorCategoryDescription;

	private Character status;

	private String createdDate;

	private Integer createdBy;

	private Integer updatedBy;

	private String updatedDate;
	
	private Integer offset;

	private Integer noOfRecordsPerPage;

	public int getDoctorCategoryId() {
		return doctorCategoryId;
	}

	public void setDoctorCategoryId(int doctorCategoryId) {
		this.doctorCategoryId = doctorCategoryId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getDoctorCategoryCode() {
		return doctorCategoryCode;
	}

	public void setDoctorCategoryCode(String doctorCategoryCode) {
		this.doctorCategoryCode = doctorCategoryCode;
	}

	public String getDoctorCategoryDescription() {
		return doctorCategoryDescription;
	}

	public void setDoctorCategoryDescription(String doctorCategoryDescription) {
		this.doctorCategoryDescription = doctorCategoryDescription;
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
