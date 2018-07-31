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
	@NamedQuery(name="GET_ENCOUNTER_LIST", query="SELECT encounter.encounterTypeId as encounterTypeId, "
			+ "encounter.encounterTypeName as encounterTypeName, "
			+ "encounter.encounterTypeCode as encounterTypeCode, "
			+ "encounter.status as status "
			+ "FROM EncounterTypeMaster encounter "
			+ "WHERE encounter.organizationId=:orgId "
			+ "ORDER BY encounter.encounterTypeId DESC "),
	
	@NamedQuery(name="GET_ENCOUNTER_LIST_BY_ID", query="SELECT encounter.encounterTypeId as encounterTypeId, "
			+ "encounter.encounterTypeName as encounterTypeName, "
			+ "encounter.encounterTypeCode as encounterTypeCode, "
			+ "encounter.status as status "
			+ "FROM EncounterTypeMaster encounter "
			+ "WHERE encounter.encounterTypeId=:encounterId "),
	
	@NamedQuery(name="GET_ENCOUNTER_LIST_BY_NAME", query="SELECT encounter.encounterTypeId as encounterTypeId, "
			+ "encounter.encounterTypeName as encounterTypeName "
			+ "FROM EncounterTypeMaster encounter "
			+ "WHERE LOWER(encounter.encounterTypeName)=:encounterName OR encounter.encounterTypeName=:encounterName"),
	
	@NamedQuery(name="GET_ENCOUNTER_LIST_BY_NAME_NOT_ID", query="SELECT encounter.encounterTypeId as encounterTypeId, "
			+ "encounter.encounterTypeName as encounterTypeName "
			+ "FROM EncounterTypeMaster encounter "
			+ "WHERE LOWER(encounter.encounterTypeName)=:encounterName OR encounter.encounterTypeName=:encounterName "
			+ "AND encounter.encounterTypeId=:encounterId"),
	
	@NamedQuery(name="GET_ACTIVE_ENCOUNTER_LIST", query="SELECT encounter.encounterTypeId as encounterTypeId, "
			+ "encounter.encounterTypeName as encounterTypeName "
			+ "FROM EncounterTypeMaster encounter "
			+ "WHERE encounter.status='A'")
})

@Entity
@Table(name = "m_encounter_type_master" , schema = "public")
@SequenceGenerator(name = "encounter_type_master_seq" , sequenceName = "public.encounter_type_master_seq" , allocationSize = 1)
public class EncounterTypeMaster {
	@Id
	@Column(name = "encounter_type_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "encounter_type_master_seq")
	private int encounterTypeId;
	
	@Column(name="encounter_type_name")
	private String encounterTypeName;
	
	@Column(name="encounter_type_code")
	private String encounterTypeCode;
	
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

	public int getEncounterTypeId() {
		return encounterTypeId;
	}

	public void setEncounterTypeId(int encounterTypeId) {
		this.encounterTypeId = encounterTypeId;
	}

	public String getEncounterTypeName() {
		return encounterTypeName;
	}

	public void setEncounterTypeName(String encounterTypeName) {
		this.encounterTypeName = encounterTypeName;
	}

	public String getEncounterTypeCode() {
		return encounterTypeCode;
	}

	public void setEncounterTypeCode(String encounterTypeCode) {
		this.encounterTypeCode = encounterTypeCode;
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
	
	
	
}
