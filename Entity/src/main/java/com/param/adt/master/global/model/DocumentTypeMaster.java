package com.param.adt.master.global.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.adt.admission.model.AdmissionPatientDocuments;
import com.param.adt.admission.model.SponsorDetails;

@NamedQueries({
	
	@NamedQuery(name="GET_DOCUMENT_TYPE_LIST",query="SELECT doc.documentTypeId as documentTypeId, "
			+ "doc.documentTypeName as documentTypeName, "
			+ "doc.documentTypeCode as documentTypeCode, "
			+ "doc.status as status "
			+ "FROM DocumentTypeMaster doc "
			+ "WHERE doc.organizationId=:orgId "
			+ "ORDER BY doc.documentTypeId DESC"),
	
	@NamedQuery(name="GET_DOCUMENT_TYPE_LIST_BY_ID",query="SELECT doc.documentTypeId as documentTypeId, "
			+ "doc.documentTypeName as documentTypeName, "
			+ "doc.documentTypeCode as documentTypeCode, "
			+ "doc.status as status "
			+ "FROM DocumentTypeMaster doc WHERE doc.documentTypeId=:documentId"),
	
	@NamedQuery(name="GET_DOCUMENT_TYPE_LIST_BY_NAME",query="SELECT doc.documentTypeId as documentTypeId, "
			+ "doc.documentTypeName as documentTypeName "
			+ "FROM DocumentTypeMaster doc "
			+ "WHERE doc.documentTypeName =:documentName OR LOWER(doc.documentTypeName)=:documentName"),
	
	@NamedQuery(name="GET_DOCUMENT_TYPE_LIST_BY_NAME_NOT_ID",query="SELECT doc.documentTypeId as documentTypeId, "
			+ "doc.documentTypeName as documentTypeName "
			+ "FROM DocumentTypeMaster doc "
			+ "WHERE (doc.documentTypeName =:documentName OR LOWER(doc.documentTypeName)=:documentName) AND doc.documentTypeId!=:documentId"),
	
	@NamedQuery(name="GET_ACTIVE_DOCUMENT_TYPE_LIST",query="SELECT doc.documentTypeId as documentTypeId, "
			+ "doc.documentTypeName as documentTypeName, "
			+ "doc.documentTypeCode as documentTypeCode, "
			+ "doc.status as status "
			+ "FROM DocumentTypeMaster doc WHERE doc.status='A'")
})

@Entity
@Table(name = "m_document_type_master", schema = "public")
@SequenceGenerator(name = "document_type_master_seq", sequenceName = "public.document_type_master_seq", allocationSize = 1)
public class DocumentTypeMaster 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "document_type_master_seq")
	@Column(name = "document_type_id")
	private int documentTypeId;
	
	@Column(name = "document_type_name")
	private String documentTypeName;
	
	@Column(name = "document_type_code")
	private String documentTypeCode;
	
	@Column(name="status")
	private char status;
	
	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name="organization_id")
	private Integer organizationId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private OrganizationMaster organizationMaster;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "documentTypeMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SponsorDetails> sponserDetailsList;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "documentTypeMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AdmissionPatientDocuments> admissionPatientDocumentsList;
	
	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public OrganizationMaster getOrganizationMaster() {
		return organizationMaster;
	}

	public void setOrganizationMaster(OrganizationMaster organizationMaster) {
		this.organizationMaster = organizationMaster;
	}

	public int getDocumentTypeId() {
		return documentTypeId;
	}

	public void setDocumentTypeId(int documentTypeId) {
		this.documentTypeId = documentTypeId;
	}

	public String getDocumentTypeName() {
		return documentTypeName;
	}

	public void setDocumentTypeName(String documentTypeName) {
		this.documentTypeName = documentTypeName;
	}

	public String getDocumentTypeCode() {
		return documentTypeCode;
	}

	public void setDocumentTypeCode(String documentTypeCode) {
		this.documentTypeCode = documentTypeCode;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	
}
