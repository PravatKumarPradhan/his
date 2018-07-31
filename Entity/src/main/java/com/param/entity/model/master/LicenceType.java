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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "LicenceType")
@Table(name = "m_licence_type_master", schema = "public")
public class LicenceType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "licence_type_id", unique = true, nullable = false)
	private Integer licenceTypeId;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date", length = 255)
	private String createdDate;

	@Column(name = "liceence_type_code", length = 100)
	private String liceenceTypeCode;

	@Column(name = "licence_type", length = 255)
	private String licenceType;

	@Column(length = 1)
	private String status;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	private Timestamp updatedDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id")
	private Organization organization;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "licenceType")
	private List<OrganizationLicence> organizationLicenceList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "licenceType")
	private List<UnitLicence> unitLicenceList;

	public LicenceType() {
	}

	public Integer getLicenceTypeId() {
		return this.licenceTypeId;
	}

	public void setLicenceTypeId(Integer licenceTypeId) {
		this.licenceTypeId = licenceTypeId;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getLiceenceTypeCode() {
		return this.liceenceTypeCode;
	}

	public void setLiceenceTypeCode(String liceenceTypeCode) {
		this.liceenceTypeCode = liceenceTypeCode;
	}

	public String getLicenceType() {
		return this.licenceType;
	}

	public void setLicenceType(String licenceType) {
		this.licenceType = licenceType;
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

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public List<OrganizationLicence> getOrganizationLicenceList() {
		return this.organizationLicenceList;
	}

	public void setOrganizationLicenceList(List<OrganizationLicence> organizationLicenceList) {
		this.organizationLicenceList = organizationLicenceList;
	}

	public OrganizationLicence addOrganizationLicenceList(OrganizationLicence organizationLicenceList) {
		getOrganizationLicenceList().add(organizationLicenceList);
		organizationLicenceList.setLicenceType(this);

		return organizationLicenceList;
	}

	public OrganizationLicence removeOrganizationLicenceList(OrganizationLicence organizationLicenceList) {
		getOrganizationLicenceList().remove(organizationLicenceList);
		organizationLicenceList.setLicenceType(null);

		return organizationLicenceList;
	}

	public List<UnitLicence> getUnitLicenceList() {
		return this.unitLicenceList;
	}

	public void setUnitLicenceList(List<UnitLicence> unitLicenceList) {
		this.unitLicenceList = unitLicenceList;
	}

	public UnitLicence addUnitLicenceList(UnitLicence unitLicenceList) {
		getUnitLicenceList().add(unitLicenceList);
		unitLicenceList.setLicenceType(this);

		return unitLicenceList;
	}

	public UnitLicence removeUnitLicenceList(UnitLicence unitLicenceList) {
		getUnitLicenceList().remove(unitLicenceList);
		unitLicenceList.setLicenceType(null);

		return unitLicenceList;
	}

}
