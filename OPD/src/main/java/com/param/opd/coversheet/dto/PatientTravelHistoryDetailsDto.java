package com.param.opd.coversheet.dto;

import java.util.List;

public class PatientTravelHistoryDetailsDto {
	
	private Integer travelHistoryDetailsId;
	private String description;
	private String whenTravel;
	private Integer appointmentId;
	private Integer patientId;
	private Integer organizationId;
	private Integer unitId;
	private char status;
	private Integer createdBy;
	private Integer updatedBy;
	private String createdDate;
	private String updatedDate;
	
	private Integer encounterId;
	private Integer roleId;
	private char isReviewedFlag;
	private char isEnterInErrorStatus;
	
	private List<ListOfPatientTravelHistoryDto> listOfPatientTravelHistoryDtos;
	
	
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
	public char getIsEnterInErrorStatus() {
		return isEnterInErrorStatus;
	}
	public void setIsEnterInErrorStatus(char isEnterInErrorStatus) {
		this.isEnterInErrorStatus = isEnterInErrorStatus;
	}
	
	public Integer getTravelHistoryDetailsId() {
		return travelHistoryDetailsId;
	}
	public void setTravelHistoryDetailsId(Integer travelHistoryDetailsId) {
		this.travelHistoryDetailsId = travelHistoryDetailsId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getWhenTravel() {
		return whenTravel;
	}
	public void setWhenTravel(String whenTravel) {
		this.whenTravel = whenTravel;
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
	public List<ListOfPatientTravelHistoryDto> getListOfPatientTravelHistoryDtos() {
		return listOfPatientTravelHistoryDtos;
	}
	public void setListOfPatientTravelHistoryDtos(List<ListOfPatientTravelHistoryDto> listOfPatientTravelHistoryDtos) {
		this.listOfPatientTravelHistoryDtos = listOfPatientTravelHistoryDtos;
	}
	
	

}
