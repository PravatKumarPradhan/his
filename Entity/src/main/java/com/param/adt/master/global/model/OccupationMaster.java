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

	@NamedQuery(name = "GET_OCCUPATION_LIST", query = "SELECT occupationMaster.id as id, "
			+ "occupationMaster.code as code, " + "occupationMaster.desc as desc, "
			+ "occupationMaster.status as status "
			+ "FROM OccupationMaster as occupationMaster "
			+ "WHERE occupationMaster.organizationId=:orgId "
			+ "ORDER BY occupationMaster.id DESC"),

	@NamedQuery(name = "GET_OCCUPATION_BY_NAME", query = "SELECT occupationMaster.id as id, "
			+ "occupationMaster.code as code, "
			+ "occupationMaster.desc as desc "
			+ "FROM OccupationMaster as occupationMaster WHERE LOWER(occupationMaster.desc)=:desc OR occupationMaster.desc=:desc"),

	@NamedQuery(name = "GET_OCCUPATION_BY_ID", query = "SELECT occupationMaster.id as id, "
			+ "occupationMaster.code as code, " + "occupationMaster.desc as desc, "
			+ "occupationMaster.status as status "
			+ "FROM OccupationMaster as occupationMaster WHERE occupationMaster.id=:id"),

	@NamedQuery(name = "GET_OCCUPATION_BY_NAME_NOT_ID", query = "SELECT occupationMaster.id as id, "
			+ "occupationMaster.code as code, " + "occupationMaster.desc as desc, "
			+ "occupationMaster.status as status "
			+ "FROM OccupationMaster as occupationMaster WHERE (LOWER(occupationMaster.desc)=:desc OR occupationMaster.desc=:desc) AND occupationMaster.id!=:id"),
	
	@NamedQuery(name = "GET_ACTIVE_OCCUPATION_LIST", query = "SELECT occupationMaster.id as id, "
			+ "occupationMaster.desc as desc "
			+ "FROM OccupationMaster as occupationMaster WHERE occupationMaster.status='A'")

})

@Entity
@Table(name = "m_occupation_master", schema = "public")
@SequenceGenerator(name="occupation_master_seq", sequenceName="public.occupation_master_seq", allocationSize=1)
public class OccupationMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "occupation_master_seq")
	@Column(name = "occupation_id")
	private int id;
	
	@Column(name = "occupation_code")
	private String code;
	
	@Column(name = "occupation_name")
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
