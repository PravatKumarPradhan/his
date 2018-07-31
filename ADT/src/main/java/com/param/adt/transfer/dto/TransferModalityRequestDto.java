package com.param.adt.transfer.dto;

import java.time.LocalTime;

public class TransferModalityRequestDto {

private int transferModalityId;
	
	private Integer organizationId;
	
	private Integer unitId;
	
	private Integer patientServiceOrderId;
	
	private Integer admissionId;
	
	private Integer schedularId;
	
	private LocalTime modalityTime;
	
	private LocalTime transferTime;
	
	private String modalityTimeString;
	
	private String transferTimeString;
	
	private char isRetained;
	
	private Integer destinationId;
	
	private String status;

	private int createdBy;

	private String createdDate;

	private int updatedBy;

	private String updatedDate;
	
	private String note;
	
	private Integer transferStatusId;
	
	private Integer patientSevicesOrderId;
	
	private Integer serviceId;
	
	private String serviceStandardName;
	
	private Integer scheduleId;
	
	private String transferStatusDesc;
	
	private Integer visitTypeId;
	
	private Integer transferTypeId;
	
	private Integer modalityId;
	
	private String modalityDesc;
	
	
	
	public String getModalityDesc() {
		return modalityDesc;
	}

	public void setModalityDesc(String modalityDesc) {
		this.modalityDesc = modalityDesc;
	}

	public Integer getModalityId() {
		return modalityId;
	}

	public void setModalityId(Integer modalityId) {
		this.modalityId = modalityId;
	}

	public String getModalityTimeString() {
		return modalityTimeString;
	}

	public void setModalityTimeString(String modalityTimeString) {
		this.modalityTimeString = modalityTimeString;
	}

	public String getTransferTimeString() {
		return transferTimeString;
	}

	public void setTransferTimeString(String transferTimeString) {
		this.transferTimeString = transferTimeString;
	}

	public Integer getTransferTypeId() {
		return transferTypeId;
	}

	public void setTransferTypeId(Integer transferTypeId) {
		this.transferTypeId = transferTypeId;
	}

	public Integer getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public Integer getTransferStatusId() {
		return transferStatusId;
	}

	public Integer getPatientSevicesOrderId() {
		return patientSevicesOrderId;
	}

	public void setPatientSevicesOrderId(Integer patientSevicesOrderId) {
		this.patientSevicesOrderId = patientSevicesOrderId;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceStandardName() {
		return serviceStandardName;
	}

	public void setServiceStandardName(String serviceStandardName) {
		this.serviceStandardName = serviceStandardName;
	}

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getTransferStatusDesc() {
		return transferStatusDesc;
	}

	public void setTransferStatusDesc(String transferStatusDesc) {
		this.transferStatusDesc = transferStatusDesc;
	}

	public void setTransferStatusId(Integer transferStatusId) {
		this.transferStatusId = transferStatusId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getTransferModalityId() {
		return transferModalityId;
	}

	public void setTransferModalityId(int transferModalityId) {
		this.transferModalityId = transferModalityId;
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

	public Integer getPatientServiceOrderId() {
		return patientServiceOrderId;
	}

	public void setPatientServiceOrderId(Integer patientServiceOrderId) {
		this.patientServiceOrderId = patientServiceOrderId;
	}

	public Integer getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(Integer admissionId) {
		this.admissionId = admissionId;
	}

	public Integer getSchedularId() {
		return schedularId;
	}

	public void setSchedularId(Integer schedularId) {
		this.schedularId = schedularId;
	}

	public LocalTime getModalityTime() {
		return modalityTime;
	}

	public void setModalityTime(String modalityTime) {
		this.modalityTime = LocalTime.parse(modalityTime);
	}

	public LocalTime getTransferTime() {
		return transferTime;
	}

	public void setTransferTime(String transferTime) {
		this.transferTime = LocalTime.parse(transferTime);
	}

	public char getIsRetained() {
		return isRetained;
	}

	public void setIsRetained(char isRetained) {
		this.isRetained = isRetained;
	}

	public Integer getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(Integer destinationId) {
		this.destinationId = destinationId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	
	
	
}
