package com.param.opd.coversheet.dto;

import java.util.List;

import javax.persistence.Column;


public class ImmunizationHistoryMapperDto {

	private Integer immunizationHistoryMapperId;
	private String immunizationDate;
	private Integer appointmentId;
	private Integer patientId;
	private Integer drugId;
	private Integer createdBy;
	private Integer updatedBy;
	private String createdDate;
	private String updatedDate;
	private char status;
	private char isAdministeredStatus;
	
	private String drugName;
	private Integer encounterId;
	private Integer roleId;
	private char isReviewedFlag;
	
	private List<ListImmunizationHistoryMapperDto> listListImmunizationHistoryMapperDto;
	
	private Integer unitId;
	private Integer organizationId;
	
	
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
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
	public List<ListImmunizationHistoryMapperDto> getListListImmunizationHistoryMapperDto() {
		return listListImmunizationHistoryMapperDto;
	}
	public void setListListImmunizationHistoryMapperDto(
			List<ListImmunizationHistoryMapperDto> listListImmunizationHistoryMapperDto) {
		this.listListImmunizationHistoryMapperDto = listListImmunizationHistoryMapperDto;
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
	public Integer getImmunizationHistoryMapperId() {
		return immunizationHistoryMapperId;
	}
	public void setImmunizationHistoryMapperId(Integer immunizationHistoryMapperId) {
		this.immunizationHistoryMapperId = immunizationHistoryMapperId;
	}
	public String getImmunizationDate() {
		return immunizationDate;
	}
	public void setImmunizationDate(String immunizationDate) {
		this.immunizationDate = immunizationDate;
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
	public Integer getDrugId() {
		return drugId;
	}
	public void setDrugId(Integer drugId) {
		this.drugId = drugId;
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
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public char getIsAdministeredStatus() {
		return isAdministeredStatus;
	}
	public void setIsAdministeredStatus(char isAdministeredStatus) {
		this.isAdministeredStatus = isAdministeredStatus;
	}
	
	
}
