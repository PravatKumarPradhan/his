package com.param.global.dto;



public class SurgeryMasterDto {

	private int surgery_id;
	private Integer organizationId;
	private Integer unitId;
	private String surgeryName;
	private String surgeryCode; 
	private int createdBy;
	private String createdDate;
	private int updatedBy;
	private String updatedDate;
	private char status;
	
	private Integer specialityId;
	private String surgeryCodeCpt;
	private Integer surgeryGradeId;
	private String specialityName;
	private String surgeryGrade;
	
	public int getSurgery_id() {
		return surgery_id;
	}
	public void setSurgery_id(int surgery_id) {
		this.surgery_id = surgery_id;
	}
	public Integer getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	public String getSurgeryName() {
		return surgeryName;
	}
	public void setSurgeryName(String surgeryName) {
		this.surgeryName = surgeryName;
	}
	public String getSurgeryCode() {
		return surgeryCode;
	}
	public void setSurgeryCode(String surgeryCode) {
		this.surgeryCode = surgeryCode;
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
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = (status == '\u0000') ? 'A' : status;
	}
	public Integer getSpecialityId() {
		return specialityId;
	}
	public void setSpecialityId(Integer specialityId) {
		this.specialityId = specialityId;
	}
	public String getSurgeryCodeCpt() {
		return surgeryCodeCpt;
	}
	public void setSurgeryCodeCpt(String surgeryCodeCpt) {
		this.surgeryCodeCpt = surgeryCodeCpt;
	}
	public Integer getSurgeryGradeId() {
		return surgeryGradeId;
	}
	public void setSurgeryGradeId(Integer surgeryGradeId) {
		this.surgeryGradeId = surgeryGradeId;
	}
	public String getSpecialityName() {
		return specialityName;
	}
	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}
	public String getSurgeryGrade() {
		return surgeryGrade;
	}
	public void setSurgeryGrade(String surgeryGrade) {
		this.surgeryGrade = surgeryGrade;
	}
	
	
}
