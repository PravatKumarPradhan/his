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

@Entity
@Table(name = "m_consultant_type_master", schema = "adt")
@SequenceGenerator(name="consultant_type_master_seq" , sequenceName ="adt.consultant_type_master_seq", allocationSize = 1)
public class ConsultantTypeMaster
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="consultant_type_master_seq")
	@Column(name="consultant_type_id")
	private int consultantTypeId;
	
	@Column(name="consultant_type_desc")
	private String consultantTypeDesc;
	
	@Column(name="consultant_type_code")
	private String consultantTypeCode;
	
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

	public int getConsultantTypeId() {
		return consultantTypeId;
	}

	public void setConsultantTypeId(int consultantTypeId) {
		this.consultantTypeId = consultantTypeId;
	}

	public String getConsultantTypeDesc() {
		return consultantTypeDesc;
	}

	public void setConsultantTypeDesc(String consultantTypeDesc) {
		this.consultantTypeDesc = consultantTypeDesc;
	}

	public String getConsultantTypeCode() {
		return consultantTypeCode;
	}

	public void setConsultantTypeCode(String consultantTypeCode) {
		this.consultantTypeCode = consultantTypeCode;
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
	
	
}
