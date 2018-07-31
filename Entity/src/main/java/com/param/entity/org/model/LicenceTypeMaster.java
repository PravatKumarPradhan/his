package com.param.entity.org.model;

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

import com.param.adt.master.global.model.OrganizationLicenceMaster;
import com.param.adt.master.global.model.OrganizationMaster;
import com.param.adt.master.global.model.UnitLicenceMaster;

@NamedQueries({

		@NamedQuery(name = "GET_LICENECE_TYPE_MASTER_LIST_BY_TYPE", 
				query = "SELECT licenceMster.licenceTypeId AS licenceTypeId "
				+ " FROM LicenceTypeMaster licenceMster "
				+ " WHERE LOWER(licenceMster.licenceType)=:liceneceType OR licenceMster.licenceType=:liceneceType "
				+ " AND licenceMster.organizationId=:orgId "),
		
		@NamedQuery(name = "GET_LICENECE_TYPE_MASTER_BY_ID", 
				query = "SELECT licenceMster.licenceTypeId AS licenceTypeId, "
				+ "licenceMster.licenceType AS licenceType," 
				+ "licenceMster.status AS status,"
				+ "licenceMster.liceenceTypeCode AS liceenceTypeCode "
				+ " FROM LicenceTypeMaster licenceMster "
				+ " WHERE licenceMster.licenceTypeId=:licenceId"
				+ " AND licenceMster.organizationId=:orgId") ,
		
		@NamedQuery(name = "GET_LICENECE_TYPE_MASTER", 
			query = "SELECT licenceMster.licenceTypeId AS licenceTypeId, "
			+ "licenceMster.licenceType AS licenceType," 
			+ "licenceMster.status AS status,"
			+ "licenceMster.liceenceTypeCode AS liceenceTypeCode "
			+ " FROM LicenceTypeMaster licenceMster "
			+ " WHERE licenceMster.organizationId=:orgId"),
		
		@NamedQuery(name = "GET_LICENECE_LIST_BY_NAME_NOT_ID",
			query = "SELECT licenceMster.licenceTypeId AS licenceTypeId  "
			+ "FROM LicenceTypeMaster licenceMster "
			+ "WHERE (LOWER(licenceMster.licenceType)=:licenceType OR licenceMster.licenceType=:licenceType) "
			+ "AND licenceMster.licenceTypeId !=:licenseId")

		})
@Entity
@Table(name = "m_licence_type_master", schema = "public")
@SequenceGenerator(name = "licence_type_master_seq", sequenceName = "public.licence_type_master_seq", allocationSize = 1)
public class LicenceTypeMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "licence_type_master_seq")
	@Column(name = "licence_type_id")
	private int licenceTypeId;

	@Column(name = "licence_type")
	private String licenceType;

	@Column(name = "status")
	private char status;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "organization_id")
	private Integer organizationId;

	@Column(name = "liceence_type_code")
	private String liceenceTypeCode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private OrganizationMaster organizationMaster;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "licenceTypeMaster")
	private List<UnitLicenceMaster> unitLicenceMaster;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "licenceTypeMaster")
	private List<OrganizationLicenceMaster> organizationLicenceMaster;

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

	public int getLicenceTypeId() {
		return licenceTypeId;
	}

	public void setLicenceTypeId(int licenceTypeId) {
		this.licenceTypeId = licenceTypeId;
	}

	public String getLicenceType() {
		return licenceType;
	}

	public void setLicenceType(String licenceType) {
		this.licenceType = licenceType;
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

	public List<UnitLicenceMaster> getUnitLicenceMaster() {
		return unitLicenceMaster;
	}

	public void setUnitLicenceMaster(List<UnitLicenceMaster> unitLicenceMaster) {
		this.unitLicenceMaster = unitLicenceMaster;
	}

	public List<OrganizationLicenceMaster> getOrganizationLicenceMaster() {
		return organizationLicenceMaster;
	}

	public void setOrganizationLicenceMaster(List<OrganizationLicenceMaster> organizationLicenceMaster) {
		this.organizationLicenceMaster = organizationLicenceMaster;
	}

	public String getLiceenceTypeCode() {
		return liceenceTypeCode;
	}

	public void setLiceenceTypeCode(String liceenceTypeCode) {
		this.liceenceTypeCode = liceenceTypeCode;
	}
}
