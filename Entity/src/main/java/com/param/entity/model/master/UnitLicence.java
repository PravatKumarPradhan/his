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

@Entity(name = "UnitLicence")
@Table(name = "unit_licence_master", schema = "public")
public class UnitLicence implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "unit_licence_id", unique = true, nullable = false)
	private Integer unitLicenceId;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "documentation_path", length = 255)
	private String documentationPath;

	@Column(name = "from_date")
	private Timestamp fromDate;

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

	@Column(name = "to_date")
	private Timestamp toDate;

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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id")
	private Unit unit;

	public UnitLicence() {
	}

	public Integer getUnitLicenceId() {
		return this.unitLicenceId;
	}

	public void setUnitLicenceId(Integer unitLicenceId) {
		this.unitLicenceId = unitLicenceId;
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

	public Timestamp getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Timestamp fromDate) {
		this.fromDate = fromDate;
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

	public Timestamp getToDate() {
		return this.toDate;
	}

	public void setToDate(Timestamp toDate) {
		this.toDate = toDate;
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

	public Unit getUnit() {
		return this.unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

}
