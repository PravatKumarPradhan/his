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

		@NamedQuery(name = "GET_HIERARCHY_LIST", query = "SELECT hie.hierarchyId as hierarchyId, "
				+ "hie.hierarchyDesc as hierarchyDesc, " + "hie.hierarchyCode as hierarchyCode, "
				+ "hie.status as status " + "FROM HierarchyMaster hie " 
				+ "WHERE hie.organizationId=:orgId "
				+ "ORDER BY hie.hierarchyId DESC"),

		@NamedQuery(name = "GET_HIERARCHY_LIST_BY_ID", query = "SELECT hie.hierarchyId as hierarchyId, "
				+ "hie.hierarchyDesc as hierarchyDesc, " + "hie.hierarchyCode as hierarchyCode, "
				+ "hie.status as status " + "FROM HierarchyMaster hie " + "WHERE hie.hierarchyId=:hierarchyId "),

		@NamedQuery(name = "GET_HIERARCHY_LIST_BY_NAME", query = "SELECT hie.hierarchyId as hierarchyId, "
				+ "hie.hierarchyDesc as hierarchyDesc " + "FROM HierarchyMaster hie "
				+ "WHERE hie.hierarchyDesc=:hierarchyDesc OR LOWER(hie.hierarchyDesc)=:hierarchyDesc"),

		@NamedQuery(name = "GET_HIERARCHY_LIST_BY_NAME_NOT_ID", query = "SELECT hie.hierarchyId as hierarchyId, "
				+ "hie.hierarchyDesc as hierarchyDesc " + "FROM HierarchyMaster hie "
				+ "WHERE (hie.hierarchyDesc=:hierarchyDesc OR LOWER(hie.hierarchyDesc)=:hierarchyDesc) "
				+ "AND hie.hierarchyId!=:hierarchyId"),

		@NamedQuery(name = "GET_ACTIVE_HIERARCHY_LIST", query = "SELECT hie.hierarchyId as hierarchyId, "
				+ "hie.hierarchyDesc as hierarchyDesc " + "FROM HierarchyMaster hie " + "WHERE hie.status='A'")

})

@Entity
@Table(name = "m_hierarchy_master", schema = "adt")
@SequenceGenerator(name = "hierarchy_master_seq", sequenceName = "adt.hierarchy_master_seq", allocationSize = 1)
public class HierarchyMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hierarchy_master_seq")
	@Column(name = "hierarchy_id")
	private int hierarchyId;

	@Column(name = "hierarchy_desc")
	private String hierarchyDesc;

	@Column(name = "hierarchy_code")
	private String hierarchyCode;

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
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private OrganizationMaster organizationMaster;
	
	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "hierarchyMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BedCategoryMaster> listBedCategoryMaster;*/

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


	public int getHierarchyId() {
		return hierarchyId;
	}

	public String getHierarchyCode() {
		return hierarchyCode;
	}

	public void setHierarchyCode(String hierarchyCode) {
		this.hierarchyCode = hierarchyCode;
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

	public void setHierarchyId(int hierarchyId) {
		this.hierarchyId = hierarchyId;
	}

	public String getHierarchyDesc() {
		return hierarchyDesc;
	}

	public void setHierarchyDesc(String hierarchyDesc) {
		this.hierarchyDesc = hierarchyDesc;
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
