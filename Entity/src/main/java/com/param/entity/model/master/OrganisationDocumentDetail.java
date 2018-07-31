package com.param.entity.model.master;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "OrganisationDocumentDetail")
@Table(name = "t_organisation_document_details", schema = "public")
public class OrganisationDocumentDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "organisation_document_details_id", unique = true, nullable = false)
	private Integer organisationDocumentDetailsId;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "documentation_path", length = 2147483647)
	private String documentationPath;

	@Column(name = "orgnisation_id")
	private Integer orgnisationId;

	@Column(length = 1)
	private String status;

	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	private Timestamp updatedDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "document_type_id")
	private OrganisationDocumentType organisationDocumentType;

	public OrganisationDocumentDetail() {
	}

	public Integer getOrganisationDocumentDetailsId() {
		return this.organisationDocumentDetailsId;
	}

	public void setOrganisationDocumentDetailsId(Integer organisationDocumentDetailsId) {
		this.organisationDocumentDetailsId = organisationDocumentDetailsId;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getDocumentationPath() {
		return this.documentationPath;
	}

	public void setDocumentationPath(String documentationPath) {
		this.documentationPath = documentationPath;
	}

	public Integer getOrgnisationId() {
		return this.orgnisationId;
	}

	public void setOrgnisationId(Integer orgnisationId) {
		this.orgnisationId = orgnisationId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getUnitId() {
		return this.unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public OrganisationDocumentType getOrganisationDocumentType() {
		return this.organisationDocumentType;
	}

	public void setOrganisationDocumentType(OrganisationDocumentType organisationDocumentType) {
		this.organisationDocumentType = organisationDocumentType;
	}
}
