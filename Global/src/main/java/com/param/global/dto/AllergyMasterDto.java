package com.param.global.dto;

import java.util.Date;


public class AllergyMasterDto {

	private Integer allergyId;
	private String allergyName;
	private String allergyDesc;
	private Integer allergyTypeId;
	private char status;
	private Integer createdBy;
	private Integer updatedBy;
	private Date createdDate;
	private Date updatedDate;
	private String marathiAllergyName;
	private Integer unitId;
	private Integer organizationId;
	
	
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
	public Integer getAllergyId() {
		return allergyId;
	}
	public void setAllergyId(Integer allergyId) {
		this.allergyId = allergyId;
	}
	public String getAllergyName() {
		return allergyName;
	}
	public void setAllergyName(String allergyName) {
		this.allergyName = allergyName;
	}
	public String getAllergyDesc() {
		return allergyDesc;
	}
	public void setAllergyDesc(String allergyDesc) {
		this.allergyDesc = allergyDesc;
	}
	public Integer getAllergyTypeId() {
		return allergyTypeId;
	}
	public void setAllergyTypeId(Integer allergyTypeId) {
		this.allergyTypeId = allergyTypeId;
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
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getMarathiAllergyName() {
		return marathiAllergyName;
	}
	public void setMarathiAllergyName(String marathiAllergyName) {
		this.marathiAllergyName = marathiAllergyName;
	}
	
	
}
