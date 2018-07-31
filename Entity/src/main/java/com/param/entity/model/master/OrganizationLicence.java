package com.param.entity.model.master;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "OrganizationLicence")
@Table(name = "m_organization_licence_master", schema = "public")
public class OrganizationLicence implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "organization_licence_id", unique = true, nullable = false)
	private Integer organizationLicenceId;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "licence_documentation_path", length = 255)
	private String licenceDocumentationPath;

	@Temporal(TemporalType.DATE)
	@Column(name = "licence_from_date")
	private Date licenceFromDate;

	@Column(name = "licence_number", length = 2147483647)
	private String licenceNumber;

	@Temporal(TemporalType.DATE)
	@Column(name = "licence_to_date")
	private Date licenceToDate;

	@Column(name = "licenec_documentation_path", length = 2147483647)
	private String licenecDocumentationPath;

	@Column(length = 1)
	private String status;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	private Timestamp updatedDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "licence_type_id")
	private LicenceType licenceType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id")
	private Organization organization;

	public OrganizationLicence() {
	}

	public Integer getOrganizationLicenceId() {
		return this.organizationLicenceId;
	}

	public void setOrganizationLicenceId(Integer organizationLicenceId) {
		this.organizationLicenceId = organizationLicenceId;
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

	public String getLicenceDocumentationPath() {
		return this.licenceDocumentationPath;
	}

	public void setLicenceDocumentationPath(String licenceDocumentationPath) {
		this.licenceDocumentationPath = licenceDocumentationPath;
	}

	public Date getLicenceFromDate() {
		return this.licenceFromDate;
	}

	public void setLicenceFromDate(Date licenceFromDate) {
		this.licenceFromDate = licenceFromDate;
	}

	public String getLicenceNumber() {
		return this.licenceNumber;
	}

	public void setLicenceNumber(String licenceNumber) {
		this.licenceNumber = licenceNumber;
	}

	public Date getLicenceToDate() {
		return this.licenceToDate;
	}

	public void setLicenceToDate(Date licenceToDate) {
		this.licenceToDate = licenceToDate;
	}

	public String getLicenecDocumentationPath() {
		return this.licenecDocumentationPath;
	}

	public void setLicenecDocumentationPath(String licenecDocumentationPath) {
		this.licenecDocumentationPath = licenecDocumentationPath;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public LicenceType getLicenceType() {
		return this.licenceType;
	}

	public void setLicenceType(LicenceType licenceType) {
		this.licenceType = licenceType;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
}
