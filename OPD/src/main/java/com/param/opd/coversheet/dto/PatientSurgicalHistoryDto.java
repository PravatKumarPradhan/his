package com.param.opd.coversheet.dto;

import java.util.List;

public class PatientSurgicalHistoryDto {

	private Integer surgicalHistoryDetailsId;
	private String remark;
	private char isNoSignificantStatus;
	private Integer month;
	private Integer year;
	private Integer surgeryId;
	private String surgenName;
	private char status;
	private Integer appointmentId;
	private Integer patientId;
	private Integer organizationId;
	private Integer unitId;
	private Integer createdBy;
	private Integer updatedBy;
	private String createdDate;
	private String updatedDate;
	
	private Integer encounterId;
	private Integer roleId;
	private char isReviewedFlag;
	
	private String surgeryName;
	private char isEnterInErrorStatus;
	
	private List<ListOfPatientSurgicalHistoryDto> listOfPatientSurgicalHistoryDto;
	
	
	public char getIsEnterInErrorStatus() {
		return isEnterInErrorStatus;
	}
	public void setIsEnterInErrorStatus(char isEnterInErrorStatus) {
		this.isEnterInErrorStatus = isEnterInErrorStatus;
	}
	public Integer getEncounterId() {
		return encounterId;
	}
	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public char getIsReviewedFlag() {
		return isReviewedFlag;
	}
	public void setIsReviewedFlag(char isReviewedFlag) {
		this.isReviewedFlag = isReviewedFlag;
	}
	public String getSurgeryName() {
		return surgeryName;
	}
	public void setSurgeryName(String surgeryName) {
		this.surgeryName = surgeryName;
	}
	public List<ListOfPatientSurgicalHistoryDto> getListOfPatientSurgicalHistoryDto() {
		return listOfPatientSurgicalHistoryDto;
	}
	public void setListOfPatientSurgicalHistoryDto(
			List<ListOfPatientSurgicalHistoryDto> listOfPatientSurgicalHistoryDto) {
		this.listOfPatientSurgicalHistoryDto = listOfPatientSurgicalHistoryDto;
	}
	public Integer getSurgicalHistoryDetailsId() {
		return surgicalHistoryDetailsId;
	}
	public void setSurgicalHistoryDetailsId(Integer surgicalHistoryDetailsId) {
		this.surgicalHistoryDetailsId = surgicalHistoryDetailsId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public char getIsNoSignificantStatus() {
		return isNoSignificantStatus;
	}
	public void setIsNoSignificantStatus(char isNoSignificantStatus) {
		this.isNoSignificantStatus = isNoSignificantStatus;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getSurgeryId() {
		return surgeryId;
	}
	public void setSurgeryId(Integer surgeryId) {
		this.surgeryId = surgeryId;
	}
	
	public String getSurgenName() {
		return surgenName;
	}
	public void setSurgenName(String surgenName) {
		this.surgenName = surgenName;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public Integer getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
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
