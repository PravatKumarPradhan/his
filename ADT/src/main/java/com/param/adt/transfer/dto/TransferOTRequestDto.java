package com.param.adt.transfer.dto;

import java.time.LocalTime;

import com.param.adt.common.ADTCommonDateUtils;

public class TransferOTRequestDto {

	private int transferOTId;

	private Integer organizationId;

	private Integer unitId;

	private Integer admissionId;

	private Integer surgeryId;

	private LocalTime surgeryTime;

	private LocalTime transferTime;

	private String surgeryTimeString;

	private String transferTimeString;

	private char isRetained;

	private Integer destinationId;

	private char status;

	private int createdBy;

	private String createdDate;

	private int updatedBy;

	private String updatedDate;
	
	private Integer patientSurgeryOrderId;
	
	private Integer schedularId;
	
	private String surgeryName;
	
	private Integer scheduleId;
	
	private Integer transferStatusId;
	
	private String transferStatusDesc;

	private Integer transferTypeId;
	
	private Integer visitTypeId;
	
	
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

	public String getSurgeryName() {
		return surgeryName;
	}

	public void setSurgeryName(String surgeryName) {
		this.surgeryName = surgeryName;
	}

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Integer getTransferStatusId() {
		return transferStatusId;
	}

	public void setTransferStatusId(Integer transferStatusId) {
		this.transferStatusId = transferStatusId;
	}

	public String getTransferStatusDesc() {
		return transferStatusDesc;
	}

	public void setTransferStatusDesc(String transferStatusDesc) {
		this.transferStatusDesc = transferStatusDesc;
	}


	public Integer getSchedularId() {
		return schedularId;
	}

	public void setSchedularId(Integer schedularId) {
		this.schedularId = schedularId;
	}

	public Integer getPatientSurgeryOrderId() {
		return patientSurgeryOrderId;
	}

	public void setPatientSurgeryOrderId(Integer patientSurgeryOrderId) {
		this.patientSurgeryOrderId = patientSurgeryOrderId;
	}

	public int getTransferOTId() {
		return transferOTId;
	}

	public void setTransferOTId(int transferOTId) {
		this.transferOTId = transferOTId;
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

	public Integer getSurgeryId() {
		return surgeryId;
	}

	public void setSurgeryId(Integer surgeryId) {
		this.surgeryId = surgeryId;
	}

	public LocalTime getSurgeryTime() {
		return surgeryTime;
	}

	public void setSurgeryTime(String surgeryTime) {
		if(!surgeryTime.equals("")){
			   this.surgeryTime = ADTCommonDateUtils.getLocalTime(surgeryTime);
			  }else{
			   this.surgeryTime = ADTCommonDateUtils.getLocalTime("00:00");
			  }
	}

	public LocalTime getTransferTime() {
		return transferTime;
	}

	public void setTransferTime(String transferTime) {
		if(!transferTime.equals("")){
			   this.transferTime = ADTCommonDateUtils.getLocalTime(transferTime);
			  }else{
			   this.transferTime = ADTCommonDateUtils.getLocalTime("00:00");
			  }
	}

	public String getSurgeryTimeString() {
		return surgeryTimeString;
	}

	public void setSurgeryTimeString(String surgeryTimeString) {
		this.surgeryTimeString = surgeryTimeString;
	}

	public String getTransferTimeString() {
		return transferTimeString;
	}

	public void setTransferTimeString(String transferTimeString) {
		this.transferTimeString = transferTimeString;
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
