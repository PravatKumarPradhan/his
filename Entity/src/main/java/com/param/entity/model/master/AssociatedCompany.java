package com.param.entity.model.master;

import java.io.Serializable;
import java.sql.Time;
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

@Entity(name = "AssociatedCompany")
@Table(name = "t_associated_company_master", schema = "public")
public class AssociatedCompany implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="associated_company_master_id", unique=true, nullable=false)
	private Integer associatedCompanyMasterId;

	@Column(name="associated_company_name", length=200)
	private String associatedCompanyName;

	@Column(name="created_by")
	private Integer createdBy;

	@Column(name="created_date")
	private Time createdDate;

	@Column(name="organization_id")
	private Integer organizationId;

	@Column(length=1)
	private String status;

	@Column(name="unit_id")
	private Integer unitId;

	@Column(name="updated_by")
	private Integer updatedBy;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="company_master_id")
	private Company company;

	public AssociatedCompany() {
	}

	public Integer getAssociatedCompanyMasterId() {
		return this.associatedCompanyMasterId;
	}

	public void setAssociatedCompanyMasterId(Integer associatedCompanyMasterId) {
		this.associatedCompanyMasterId = associatedCompanyMasterId;
	}

	public String getAssociatedCompanyName() {
		return this.associatedCompanyName;
	}

	public void setAssociatedCompanyName(String associatedCompanyName) {
		this.associatedCompanyName = associatedCompanyName;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Time getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Time createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
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

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}