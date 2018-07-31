package com.param.adt.master.global.dto;

public class ConsultantTypeMasterDto {

	private int consultantTypeId;

	private String consultantTypeDesc;

	private String consultantTypeCode;

	private int createdBy;

	private String createdDate;

	private char status;

	private int updatedBy;

	private String updatedDate;

	private Integer organizationId;

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

	public int getConsultantTypeId() {
		return consultantTypeId;
	}

	public void setConsultantTypeId(int consultantTypeId) {
		this.consultantTypeId = consultantTypeId;
	}

	public String getConsultantTypeDesc() {
		return consultantTypeDesc;
	}

	public void setConsultantTypeDesc(String consultantTypeDesc) {
		this.consultantTypeDesc = consultantTypeDesc;
	}

	public String getConsultantTypeCode() {
		return consultantTypeCode;
	}

	public void setConsultantTypeCode(String consultantTypeCode) {
		this.consultantTypeCode = consultantTypeCode;
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

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

}
