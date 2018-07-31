package com.param.adt.admission.dto;

import java.util.List;

public class VisitorDto 
{
	private int visitorPatientId;
	
	private Integer organizationId;
	
	private Integer unitId;
	
	private Integer admissionId;
	
	private String passNumber;
	
	private Integer visitorPatientMapperId;
	
	private String expiryDate;
	
	private char visitorPassStatus;
	
	private Integer visitorPassTypeId;
	
	private char status;
	
	private int createdBy;
	
	private int updatedBy;
	
	private String createdDate;

	private String updatedDate;
	
	private String visitorPassTypeDesc;

	private String txtPassNo;
	
	private String txtNewDate;
	
	private String txtOldDate;
	
	private String txtReason;
	
	private List<VisitorDto> visitorDtosList;
	
/*	"visitorPatientMappersList": [{
		"admissionId": 101,
		"createdBy": 1,
		"organizationId": 1,
		"passNumber": "PassNO01",
		"txtNewDate": null,
		"txtReason": null,
		"unitId": 1,
		"updatedBy": 1,
		"visitorPassStatus": "A",
		"visitorPassTypeId": 1,
		"visitorPatientId": 5,
		"visitorPatientMapperId": 3*/
	
	
	public List<VisitorDto> getVisitorDtosList() {
		return visitorDtosList;
	}

	public void setVisitorDtosList(List<VisitorDto> visitorDtosList) {
		this.visitorDtosList = visitorDtosList;
	}


	public String getTxtPassNo() {
		return txtPassNo;
	}

	public void setTxtPassNo(String txtPassNo) {
		this.txtPassNo = txtPassNo;
	}

	public String getTxtNewDate() {
		return txtNewDate;
	}

	public void setTxtNewDate(String txtNewDate) {
		this.txtNewDate = txtNewDate;
	}

	public String getTxtOldDate() {
		return txtOldDate;
	}

	public void setTxtOldDate(String txtOldDate) {
		this.txtOldDate = txtOldDate;
	}

	public String getTxtReason() {
		return txtReason;
	}

	public void setTxtReason(String txtReason) {
		this.txtReason = txtReason;
	}

	public String getVisitorPassTypeDesc() {
		return visitorPassTypeDesc;
	}

	public void setVisitorPassTypeDesc(String visitorPassTypeDesc) {
		this.visitorPassTypeDesc = visitorPassTypeDesc;
	}

	public int getVisitorPatientId() {
		return visitorPatientId;
	}

	public void setVisitorPatientId(int visitorPatientId) {
		this.visitorPatientId = visitorPatientId;
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

	public String getPassNumber() {
		return passNumber;
	}

	public void setPassNumber(String passNumber) {
		this.passNumber = passNumber;
	}

	public Integer getVisitorPatientMapperId() {
		return visitorPatientMapperId;
	}

	public void setVisitorPatientMapperId(Integer visitorPatientMapperId) {
		this.visitorPatientMapperId = visitorPatientMapperId;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public char getVisitorPassStatus() {
		return visitorPassStatus;
	}

	public void setVisitorPassStatus(char visitorPassStatus) {
		this.visitorPassStatus = visitorPassStatus;
	}

	public Integer getVisitorPassTypeId() {
		return visitorPassTypeId;
	}

	public void setVisitorPassTypeId(Integer visitorPassTypeId) {
		this.visitorPassTypeId = visitorPassTypeId;
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
