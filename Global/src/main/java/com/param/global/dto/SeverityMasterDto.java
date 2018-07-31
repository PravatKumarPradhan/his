package com.param.global.dto;

import java.util.Date;

import javax.persistence.Column;

public class SeverityMasterDto {

	
	private Integer severityId;
	private String severityDesc;
	private char status;
	private Integer createdBy;
	private Integer updatedBy;
	private String createdDate;
	private String updatedDate;
	private Integer unitId;
	private Integer organizationId;
	private String marathiSeverityDesc;
	public Integer getSeverityId() {
		return severityId;
	}
	public void setSeverityId(Integer severityId) {
		this.severityId = severityId;
	}
	public String getSeverityDesc() {
		return severityDesc;
	}
	public void setSeverityDesc(String severityDesc) {
		this.severityDesc = severityDesc;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
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
	public String getMarathiSeverityDesc() {
		return marathiSeverityDesc;
	}
	public void setMarathiSeverityDesc(String marathiSeverityDesc) {
		this.marathiSeverityDesc = marathiSeverityDesc;
	}
	
	
}
