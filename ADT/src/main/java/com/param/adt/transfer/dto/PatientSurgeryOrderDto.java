package com.param.adt.transfer.dto;

import java.time.LocalTime;

public class PatientSurgeryOrderDto 
{
	
	private int patientSurgeryOrderId;

	private Integer surgeryId;

	private Integer organizationId;

	private Integer unitId;

	private Integer admissionId;

	private LocalTime timeOfSurgery;

	private Integer orderStatusId;

	private char status;

	private int createdBy;

	private String createdDate;

	private int updatedBy;

	private String updatedDate;

	private char isOtTransfer;

	public int getPatientSurgeryOrderId() {
		return patientSurgeryOrderId;
	}

	public void setPatientSurgeryOrderId(int patientSurgeryOrderId) {
		this.patientSurgeryOrderId = patientSurgeryOrderId;
	}

	public Integer getSurgeryId() {
		return surgeryId;
	}

	public void setSurgeryId(Integer surgeryId) {
		this.surgeryId = surgeryId;
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

	public Integer getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(Integer admissionId) {
		this.admissionId = admissionId;
	}

	public LocalTime getTimeOfSurgery() {
		return timeOfSurgery;
	}

	public void setTimeOfSurgery(LocalTime timeOfSurgery) {
		this.timeOfSurgery = timeOfSurgery;
	}

	public Integer getOrderStatusId() {
		return orderStatusId;
	}

	public void setOrderStatusId(Integer orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}


	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}


	public char getIsOtTransfer() {
		return isOtTransfer;
	}

	public void setIsOtTransfer(char isOtTransfer) {
		this.isOtTransfer = isOtTransfer;
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
