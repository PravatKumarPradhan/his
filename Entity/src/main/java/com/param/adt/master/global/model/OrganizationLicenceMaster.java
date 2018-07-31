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
@Table(name = "m_organization_licence_master", schema = "public")
@SequenceGenerator(name="organization_licence_master_seq", sequenceName="public.organization_licence_master_seq", allocationSize=1)
public class OrganizationLicenceMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organization_licence_master_seq")
	@Column(name = "organization_licence_id")
	private int organizationLicenceId;
	
	@Column(name = "organization_id")
	private Integer organizationId;
	
	@Column(name = "licence_type_id")
	private Integer licenceTypeId;
	
	@Column(name = "licence_number")
	private String licenceNumber;
	
	@Column(name = "licence_documentation_path")
	private String licenceDocumentationPath;
	

	@Column(name = "licence_from_date")
	private Date licenceFromDate;
	

	@Column(name = "licence_to_date")
	private Date licenceToDate;
		
	@Column(name = "status")
	private char status;
	
	@Column(name = "created_by")
	private int createdBy;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private OrganizationMaster organizationMaster;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "licence_type_id", insertable = false, updatable = false)
	private LicenceTypeMaster licenceTypeMaster;

	
	
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

	public int getOrganizationLicenceId() {
		return organizationLicenceId;
	}

	public void setOrganizationLicenceId(int organizationLicenceId) {
		this.organizationLicenceId = organizationLicenceId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getLicenceTypeId() {
		return licenceTypeId;
	}

	public void setLicenceTypeId(Integer licenceTypeId) {
		this.licenceTypeId = licenceTypeId;
	}

	public String getLicenceNumber() {
		return licenceNumber;
	}

	public void setLicenceNumber(String licenceNumber) {
		this.licenceNumber = licenceNumber;
	}

	public String getLicenceDocumentationPath() {
		return licenceDocumentationPath;
	}

	public void setLicenceDocumentationPath(String licenceDocumentationPath) {
		this.licenceDocumentationPath = licenceDocumentationPath;
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

	

	public Date getLicenceFromDate() {
		return licenceFromDate;
	}

	public void setLicenceFromDate(Date licenceFromDate) {
		this.licenceFromDate = licenceFromDate;
	}

	public Date getLicenceToDate() {
		return licenceToDate;
	}

	public void setLicenceToDate(Date licenceToDate) {
		this.licenceToDate = licenceToDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public OrganizationMaster getOrganizationMaster() {
		return organizationMaster;
	}

	public void setOrganizationMaster(OrganizationMaster organizationMaster) {
		this.organizationMaster = organizationMaster;
	}

	public LicenceTypeMaster getLicenceTypeMaster() {
		return licenceTypeMaster;
	}

	public void setLicenceTypeMaster(LicenceTypeMaster licenceTypeMaster) {
		this.licenceTypeMaster = licenceTypeMaster;
	}
	
	
}
