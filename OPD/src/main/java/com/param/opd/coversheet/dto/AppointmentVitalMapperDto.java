package com.param.opd.coversheet.dto;

import java.util.List;

import javax.persistence.Column;


public class AppointmentVitalMapperDto {

	private Integer appointmentVitalMapperId;
	private Integer vitalId;
	private Integer appointmentId;
	private Integer patientId;
	private String value;
	private char status;
	private char isSelfTriage;
	private Integer createdBy;
	private String createdDate;
	private Integer updatedBy;
	private String updatedDate;
	private Integer unitId;
	private Integer organizationId;
	private String vitalName;
	private String fromDate;
	private String toDate;
	private Integer encounterId;
	private Integer roleId;
	private char isReviewedFlag;
	
	private List<ListAppointmentVitalMapperDto> listAppointmentVitalMapperDtos;
	
	
	
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
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getVitalName() {
		return vitalName;
	}
	public void setVitalName(String vitalName) {
		this.vitalName = vitalName;
	}
	public List<ListAppointmentVitalMapperDto> getListAppointmentVitalMapperDtos() {
		return listAppointmentVitalMapperDtos;
	}
	public void setListAppointmentVitalMapperDtos(
			List<ListAppointmentVitalMapperDto> listAppointmentVitalMapperDtos) {
		this.listAppointmentVitalMapperDtos = listAppointmentVitalMapperDtos;
	}
	public Integer getAppointmentVitalMapperId() {
		return appointmentVitalMapperId;
	}
	public void setAppointmentVitalMapperId(Integer appointmentVitalMapperId) {
		this.appointmentVitalMapperId = appointmentVitalMapperId;
	}
	public Integer getVitalId() {
		return vitalId;
	}
	public void setVitalId(Integer vitalId) {
		this.vitalId = vitalId;
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public char getIsSelfTriage() {
		return isSelfTriage;
	}
	public void setIsSelfTriage(char isSelfTriage) {
		this.isSelfTriage = isSelfTriage;
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
	
	
	
}
