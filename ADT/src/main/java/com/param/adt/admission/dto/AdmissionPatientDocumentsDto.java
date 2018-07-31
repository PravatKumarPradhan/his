package com.param.adt.admission.dto;

import org.springframework.web.multipart.MultipartFile;

public class AdmissionPatientDocumentsDto 
{
	private int admissionPatientDocumentsId;

	private Integer organizationId;

	private Integer unitId;

	private Integer documentTypeId;

	private Integer admissionNoteId;

	private MultipartFile documentPath;

	private char status;

	private String createdDate;

	private int createdBy;

	private int updatedBy;

	private String updatedDate;

	public int getAdmissionPatientDocumentsId() {
		return admissionPatientDocumentsId;
	}

	public void setAdmissionPatientDocumentsId(int admissionPatientDocumentsId) {
		this.admissionPatientDocumentsId = admissionPatientDocumentsId;
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

	public Integer getDocumentTypeId() {
		return documentTypeId;
	}

	public void setDocumentTypeId(Integer documentTypeId) {
		this.documentTypeId = documentTypeId;
	}

	public Integer getAdmissionNoteId() {
		return admissionNoteId;
	}

	public void setAdmissionNoteId(Integer admissionNoteId) {
		this.admissionNoteId = admissionNoteId;
	}

	public MultipartFile getDocumentPath() {
		return documentPath;
	}

	public void setDocumentPath(MultipartFile documentPath) {
		this.documentPath = documentPath;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
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

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

}
