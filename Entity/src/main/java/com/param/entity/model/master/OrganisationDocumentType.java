package com.param.entity.model.master;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "OrganisationDocumentType")
@Table(name = "m_organisation_document_type_master", schema = "public")
public class OrganisationDocumentType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "org_document_type_id", unique = true, nullable = false)
	private Integer orgDocumentTypeId;

	@Column(length = 50)
	private String contact;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "org_document_type")
	private Integer orgDocumentType;

	@Column(name = "org_document_type_code", length = 100)
	private String orgDocumentTypeCode;

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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organisationDocumentType")
	private List<OrganisationDocumentDetail> organisationDocumentDetailList;

	public OrganisationDocumentType() {
	}

	public Integer getOrgDocumentTypeId() {
		return this.orgDocumentTypeId;
	}

	public void setOrgDocumentTypeId(Integer orgDocumentTypeId) {
		this.orgDocumentTypeId = orgDocumentTypeId;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
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

	public Integer getOrgDocumentType() {
		return this.orgDocumentType;
	}

	public void setOrgDocumentType(Integer orgDocumentType) {
		this.orgDocumentType = orgDocumentType;
	}

	public String getOrgDocumentTypeCode() {
		return this.orgDocumentTypeCode;
	}

	public void setOrgDocumentTypeCode(String orgDocumentTypeCode) {
		this.orgDocumentTypeCode = orgDocumentTypeCode;
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

	public List<OrganisationDocumentDetail> getOrganisationDocumentDetailList() {
		return this.organisationDocumentDetailList;
	}

	public void setOrganisationDocumentDetailList(List<OrganisationDocumentDetail> organisationDocumentDetailList) {
		this.organisationDocumentDetailList = organisationDocumentDetailList;
	}

	public OrganisationDocumentDetail addOrganisationDocumentDetailList(
			OrganisationDocumentDetail organisationDocumentDetailList) {
		getOrganisationDocumentDetailList().add(organisationDocumentDetailList);
		organisationDocumentDetailList.setOrganisationDocumentType(this);

		return organisationDocumentDetailList;
	}

	public OrganisationDocumentDetail removeOrganisationDocumentDetailList(
			OrganisationDocumentDetail organisationDocumentDetailList) {
		getOrganisationDocumentDetailList().remove(organisationDocumentDetailList);
		organisationDocumentDetailList.setOrganisationDocumentType(null);

		return organisationDocumentDetailList;
	}

}
