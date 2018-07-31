package com.param.global.org.common.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@NamedQueries({
	
	@NamedQuery(name = "GET_DEGREE_MASTER_BY_ID",
	query = "SELECT dm.degreeId as degreeId, "
			+ "dm.degreeDesc as degreeDesc, "
			+ "dm.degreeCode as degreeCode, "
			+ "dm.status as status  "
			+ "FROM DegreeMaster as dm "
			+ "WHERE dm.degreeId=:degreeId " 
			+ "AND dm.organizationId=:orgId "),
	@NamedQuery(name = "GET_DEGREE_MASTER_LIST", 
	query = "SELECT dm.degreeId as degreeId, "
			+ "dm.degreeDesc as degreeDesc, " 
			+ "dm.status as status, " 
			+ "dm.degreeCode as degreeCode "
			+ "FROM DegreeMaster as dm  "
			+ " WHERE dm.organizationId=:orgId "),
	@NamedQuery(name = "GET_DEGREE_MASTER_BY_NAME", 
	query = "SELECT dm.degreeId as degreeId, "
			+ "dm.degreeDesc as degreeDesc, " 
			+ "dm.degreeCode as degreeCode, "
			+ "dm.status as status "
			+ "FROM DegreeMaster as dm  "
			+ " WHERE LOWER(dm.degreeDesc)=:degreeDesc OR dm.degreeDesc=:degreeDesc " 
			+ "AND dm.organizationId=:orgId"),
	@NamedQuery(name = "GET_DEGREE_MASTER_BY_NAME_NOT_BY_ID",
	query = "SELECT dm.degreeId as degreeId, "
			+ "dm.degreeDesc as degreeDesc, " 
			+ "dm.degreeCode as degreeCode "
			+ "FROM DegreeMaster as dm  "
			+ "WHERE LOWER(dm.degreeDesc)=:degreeDesc OR dm.degreeDesc=:degreeDesc "
			+ "AND dm.degreeId !=:degreeId")
})
@Entity
@Table(name = "m_degree_master")
@SequenceGenerator(name = "degree_master_seq", sequenceName = "public.degree_master_seq", allocationSize = 1)
public class DegreeMaster {
	@Id
	@Column(name = "degree_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "degree_master_seq")

	private Integer degreeId;

	@Column(name = "degree_desc")
	private String degreeDesc;

	@Column(name = "degree_code")
	private String degreeCode;

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
	
	@Column(name = "unit_id")
	private Integer unitId;

	public Integer getDegreeId() {
		return degreeId;
	}

	public void setDegreeId(Integer degreeId) {
		this.degreeId = degreeId;
	}

	public String getDegreeDesc() {
		return degreeDesc;
	}

	public void setDegreeDesc(String degreeDesc) {
		this.degreeDesc = degreeDesc;
	}

	public String getDegreeCode() {
		return degreeCode;
	}

	public void setDegreeCode(String degreeCode) {
		this.degreeCode = degreeCode;
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

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	
	
}
