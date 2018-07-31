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
	
	@NamedQuery(name="GET_RELIGION_LIST", query="SELECT religion.id as id, "
			+ "religion.code as code, "
			+ "religion.desc as desc, "
			+ "religion.status as status "
			+ "FROM ReligionMaster as religion "
			+ "WHERE religion.organizationId=:orgId "
			+ "ORDER BY religion.id DESC"),
	
	@NamedQuery(name="GET_RELIGION_LIST_BY_NAME", query="SELECT religion.id as id, "
			+ "religion.desc as desc "
			+ "FROM ReligionMaster as religion "
			+ "WHERE religion.desc=:desc OR LOWER(religion.desc)=:desc"),
	
	@NamedQuery(name="GET_RELIGION_LIST_BY_ID", query="SELECT religion.id as id, "
			+ "religion.code as code, "
			+ "religion.desc as desc, "
			+ "religion.status as status "
			+ "FROM ReligionMaster as religion "
			+ "WHERE religion.id=:id"),
	
	@NamedQuery(name="GET_RELIGION_LIST_BY_NAME_NOT_ID", query="SELECT religion.id as id, "
			+ "religion.desc as desc "
			+ "FROM ReligionMaster as religion "
			+ "WHERE religion.id!=:id AND (religion.desc=:desc OR LOWER(religion.desc)=:desc)"),
	
	@NamedQuery(name="GET_ACTIVE_RELIGION_LIST", query="SELECT religion.id as id, "
			+ "religion.desc as desc "
			+ "FROM ReligionMaster as religion "
			+ "WHERE religion.status='A'")
})
@Entity
@Table(name = "m_religion_master", schema = "public")
@SequenceGenerator(name="religion_master_seq", sequenceName="public.religion_master_seq", allocationSize=1)
public class ReligionMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "religion_master_seq")
	@Column(name = "religion_id")
	private int id;
	
	@Column(name = "religion_code")
	private String code;
	
	@Column(name = "religion_name")
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
