package com.param.adt.master.global.model;

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

import com.param.entity.org.model.LicenceTypeMaster;
@Entity
@Table(name = "unit_licence_master", schema = "public")
@SequenceGenerator(name="unit_licence_master_seq", sequenceName="public.unit_licence_master_seq", allocationSize=1)
public class UnitLicenceMaster
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="unit_licence_master_seq")
	@Column(name="unit_licence_id")
	private int unitLicenceId;
	
	@Column(name="licence_type_id")
	private Integer licenceTypeId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="licence_number")
	private String licenceNumber;

	@Column(name="documentation_path")
	private String documentationPath;
	
	@Column(name="from_date")
	private Date fromDate;
	
	@Column(name="to_date")
	private Date toDate;
	
	@Column(name="created_by")
	private int createdBy;

	@Column(name="created_date")
	private String createdDate;

	@Column(name="status")
	private char status;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private OrganizationMaster organizationMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "licence_type_id" , insertable = false , updatable = false , nullable = false)
	private LicenceTypeMaster licenceTypeMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id" , insertable = false , updatable = false , nullable = false)
	private UnitMaster unitMaster;

	
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

	public int getUnitLicenceId() {
		return unitLicenceId;
	}

	public void setUnitLicenceId(int unitLicenceId) {
		this.unitLicenceId = unitLicenceId;
	}

	

	public Integer getLicenceTypeId() {
		return licenceTypeId;
	}

	public void setLicenceTypeId(Integer licenceTypeId) {
		this.licenceTypeId = licenceTypeId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public String getLicenceNumber() {
		return licenceNumber;
	}

	public void setLicenceNumber(String licenceNumber) {
		this.licenceNumber = licenceNumber;
	}

	public String getDocumentationPath() {
		return documentationPath;
	}

	public void setDocumentationPath(String documentationPath) {
		this.documentationPath = documentationPath;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public LicenceTypeMaster getLicenceTypeMaster() {
		return licenceTypeMaster;
	}

	public void setLicenceTypeMaster(LicenceTypeMaster licenceTypeMaster) {
		this.licenceTypeMaster = licenceTypeMaster;
	}

	public UnitMaster getUnitMaster() {
		return unitMaster;
	}

	public void setUnitMaster(UnitMaster unitMaster) {
		this.unitMaster = unitMaster;
	}
	
	


}
