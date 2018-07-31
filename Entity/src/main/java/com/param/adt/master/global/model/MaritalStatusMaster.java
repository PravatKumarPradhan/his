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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@NamedQueries({

	@NamedQuery(name = "GET_MARITAL_STATUS_LIST", query = "SELECT maritalStatusMaster.id as id, "
			+ "maritalStatusMaster.code as code, " + "maritalStatusMaster.desc as desc, "
			+ "maritalStatusMaster.status as status "
			+ "FROM MaritalStatusMaster as maritalStatusMaster "
			+ "WHERE maritalStatusMaster.organizationId=:orgId "
			+ "ORDER BY maritalStatusMaster.id DESC"),

	@NamedQuery(name = "GET_MARITAL_STATUS_BY_NAME", query = "SELECT maritalStatusMaster.id as id, "
			+ "maritalStatusMaster.code as code, "
			+ "maritalStatusMaster.desc as desc "
			+ "FROM MaritalStatusMaster as maritalStatusMaster WHERE LOWER(maritalStatusMaster.desc)=:desc OR maritalStatusMaster.desc=:desc"),

	@NamedQuery(name = "GET_MARITAL_STATUS_BY_ID", query = "SELECT maritalStatusMaster.id as id, "
			+ "maritalStatusMaster.code as code, " + "maritalStatusMaster.desc as desc, "
			+ "maritalStatusMaster.status as status "
			+ "FROM MaritalStatusMaster as maritalStatusMaster WHERE maritalStatusMaster.id=:id"),

	@NamedQuery(name = "GET_MARITAL_STATUS_BY_NAME_NOT_ID", query = "SELECT maritalStatusMaster.id as id, "
			+ "maritalStatusMaster.code as code, " + "maritalStatusMaster.desc as desc, "
			+ "maritalStatusMaster.status as status "
			+ "FROM MaritalStatusMaster as maritalStatusMaster WHERE (LOWER(maritalStatusMaster.desc)=:desc OR maritalStatusMaster.desc=:desc) AND maritalStatusMaster.id!=:id"),
	
	@NamedQuery(name = "GET_ACTIVE_MARITAL_STATUS_LIST", query = "SELECT maritalStatusMaster.id as id, "
			+ "maritalStatusMaster.desc as desc "
			+ "FROM MaritalStatusMaster as maritalStatusMaster WHERE maritalStatusMaster.status='A'")

})

@Entity
@Table(name = "m_marital_status_master", schema = "public")
@SequenceGenerator(name="marital_status_master_seq", sequenceName="public.marital_status_master_seq", allocationSize=1)
public class MaritalStatusMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "marital_status_master_seq")
	@Column(name = "marital_status_id")
	private int id;
	
	@Column(name = "marital_status_code")
	private String code;
	
	@Column(name = "marital_status_name")
	private String desc;
	
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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
	
	

}
