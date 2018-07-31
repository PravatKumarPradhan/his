package com.param.adt.admission.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.adt.master.global.model.DocumentTypeMaster;
import com.param.adt.master.global.model.OrganizationMaster;
import com.param.adt.master.global.model.UnitMaster;

@Entity
@Table(name="t_admission_patient_documents",schema="adt")
@SequenceGenerator(name="admission_patient_documents_seq",sequenceName="adt.admission_patient_documents_seq")
public class AdmissionPatientDocuments 
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="admission_patient_documents_seq")
	@Column(name="admission_patient_documents_id")
	private int admissionPatientDocumentsId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="document_type_id")
	private Integer documentTypeId;
	
	@Column(name="admission_note_id")
	private Integer admissionNoteId;
	
	@Column(name="document_path")
	private String documentPath;
	
	@Column(name="status")
	private char status;
	
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "created_by")
	private int createdBy;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private OrganizationMaster organizationMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id", insertable = false, updatable = false)
	private UnitMaster unitMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "document_type_id", insertable = false, updatable = false)
	private DocumentTypeMaster documentTypeMaster;	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admission_note_id", insertable = false, updatable = false)
	private AdmissionNote admissionNote;

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

	public String getDocumentPath() {
		return documentPath;
	}

	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
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

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public OrganizationMaster getOrganizationMaster() {
		return organizationMaster;
	}

	public void setOrganizationMaster(OrganizationMaster organizationMaster) {
		this.organizationMaster = organizationMaster;
	}

	public UnitMaster getUnitMaster() {
		return unitMaster;
	}

	public void setUnitMaster(UnitMaster unitMaster) {
		this.unitMaster = unitMaster;
	}

	public DocumentTypeMaster getDocumentTypeMaster() {
		return documentTypeMaster;
	}

	public void setDocumentTypeMaster(DocumentTypeMaster documentTypeMaster) {
		this.documentTypeMaster = documentTypeMaster;
	}

	public AdmissionNote getAdmissionNote() {
		return admissionNote;
	}

	public void setAdmissionNote(AdmissionNote admissionNote) {
		this.admissionNote = admissionNote;
	}
	
	
	
	
	
	
}
