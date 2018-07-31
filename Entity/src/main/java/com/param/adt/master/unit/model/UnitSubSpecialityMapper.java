package com.param.adt.master.unit.model;

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

import com.param.adt.master.global.model.OrganizationMaster;
import com.param.adt.master.global.model.UnitMaster;
import com.param.global.model.SpecialityMaster;
import com.param.global.model.SubSpecialityMaster;

@NamedQueries({
	
	@NamedQuery(name="GET_UNIT_SUB_SPECIALITY_LIST", query="SELECT usm.unitSubSpecialityId as unitSubSpecialityId, "
			+ "usm.specialityId as specialityId, "
			+ "usm.subSpecialityId as subSpecialityId, "
			+ "usm.status as status, "
			+ "spl.specialityCode as specialityCode, "
			+ "spl.specialityName as specialityName, "
			+ "sspl.subSpecialityMasterCode as subSpecialityMasterCode, "
			+ "sspl.subSpecialityMasterName as subSpecialityName "
			+ "FROM UnitSubSpecialityMapper usm "
			+ "INNER JOIN usm.specialityMaster spl "
			+ "INNER JOIN usm.subSpecialityMaster sspl "
			+ "WHERE usm.status='A' OR usm.status='I' "
			+ "AND usm.organizationId=:orgId "
			+ "AND usm.unitId=:unitId "
			+ "ORDER BY usm.unitSubSpecialityId DESC"),
	
	@NamedQuery(name="GET_UNIT_SUB_SPECIALITY_LIST_BY_ID", query="SELECT usm.unitSubSpecialityId as unitSubSpecialityId "
			+ "FROM UnitSubSpecialityMapper usm "
			+ "WHERE usm.unitSubSpecialityId=:unitSubSpecialityId"),
	
	@NamedQuery(name="GET_UNIT_SUB_SPECIALITY_LIST_BY_SPECIALITY_ID",query="SELECT usm.specialityId as specialityId, "
			+ "usm.subSpecialityId as subSpecialityId, "
			+ "spl.specialityName as specialityName, "
			+ "sspl.subSpecialityMasterName as subSpecialityName "
			+ "FROM UnitSubSpecialityMapper usm "
			+ "INNER JOIN usm.specialityMaster spl "
			+ "INNER JOIN usm.subSpecialityMaster sspl "
			+ "WHERE usm.status='A' "
			+ "AND usm.organizationId=:orgId "
			+ "AND usm.unitId=:unitId "
			+ "AND usm.specialityId=:specialityId "
			+ "AND sspl.isModality='Y' "),
	
	@NamedQuery(name="GET_UNIT_SUB_SPECIALITY_LIST_BY_SPECIALITY_ARRAY",query="SELECT usm.specialityId as specialityId, "
			+ "usm.subSpecialityId as subSpecialityId, "
			+ "spl.specialityName as specialityName, "
			+ "sspl.subSpecialityMasterName as subSpecialityName "
			+ "FROM UnitSubSpecialityMapper usm "
			+ "INNER JOIN usm.specialityMaster spl "
			+ "INNER JOIN usm.subSpecialityMaster sspl "
			+ "WHERE usm.status='A' "
			+ "AND usm.organizationId=:orgId "
			+ "AND usm.unitId=:unitId "
			+ "AND usm.specialityId IN (:specialityList) "
			+ "AND sspl.isModality='Y' "),
	
	@NamedQuery(name="GET_ACTIVE_UNIT_SUB_SPECIALITY_LIST",query="SELECT "
			+ "usm.subSpecialityId as subSpecialityId, "
			+ "sspl.subSpecialityMasterName as subSpecialityName "
			+ "FROM UnitSubSpecialityMapper usm "
			+ "INNER JOIN usm.subSpecialityMaster sspl "
			+ "WHERE usm.status='A' "
			+ "AND usm.organizationId=:orgId "
			+ "AND usm.unitId=:unitId "
			+ "AND usm.status='A' "
			+ "AND sspl.status='A'"),
	
	@NamedQuery(name="GET_UNIT_SUB_SPECIALITY_LIST_BY_SPECIALITY_ARRAY_FOR_TARIFF",query="SELECT usm.specialityId as specialityId, "
			+ "usm.subSpecialityId as subSpecialityId, "
			+ "spl.specialityName as specialityName, "
			+ "sspl.subSpecialityMasterName as subSpecialityName "
			+ "FROM UnitSubSpecialityMapper usm "
			+ "INNER JOIN usm.specialityMaster spl "
			+ "INNER JOIN usm.subSpecialityMaster sspl "
			+ "WHERE usm.status='A' "
			+ "AND usm.organizationId=:orgId "
			+ "AND usm.unitId=:unitId "
			+ "AND usm.specialityId IN (:specialityList) ")
	
})

@Entity
@Table(name="t_unit_sub_speciality_mapper",schema="public")
@SequenceGenerator(name="unit_sub_speciality_mapper_seq", sequenceName="public.unit_sub_speciality_mapper_seq",allocationSize=1)
public class UnitSubSpecialityMapper 
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="unit_sub_speciality_mapper_seq")
	@Column(name="unit_sub_speciality_id")
	private int unitSubSpecialityId;
	
	@Column(name = "unit_id")
	private Integer unitId;
	
	@Column(name="organization_id")
	private Integer organizationId;

	@Column(name="speciality_id")
	private Integer specialityId;
	
	@Column(name="sub_speciality_id")
	private Integer subSpecialityId;

	@Column(name="status")
	private char status;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "unit_id", insertable = false, updatable = false)
	private UnitMaster unitMaster;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private OrganizationMaster organizationMaster;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "speciality_id", insertable = false, updatable = false)
	private SpecialityMaster specialityMaster;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "sub_speciality_id", insertable = false, updatable = false)
	private SubSpecialityMaster subSpecialityMaster;

	public int getUnitSubSpecialityId() {
		return unitSubSpecialityId;
	}

	public void setUnitSubSpecialityId(int unitSubSpecialityId) {
		this.unitSubSpecialityId = unitSubSpecialityId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(Integer specialityId) {
		this.specialityId = specialityId;
	}

	public Integer getSubSpecialityId() {
		return subSpecialityId;
	}

	public void setSubSpecialityId(Integer subSpecialityId) {
		this.subSpecialityId = subSpecialityId;
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

	public UnitMaster getUnitMaster() {
		return unitMaster;
	}

	public void setUnitMaster(UnitMaster unitMaster) {
		this.unitMaster = unitMaster;
	}

	public OrganizationMaster getOrganizationMaster() {
		return organizationMaster;
	}

	public void setOrganizationMaster(OrganizationMaster organizationMaster) {
		this.organizationMaster = organizationMaster;
	}


	public SubSpecialityMaster getSubSpecialityMaster() {
		return subSpecialityMaster;
	}

	public void setSubSpecialityMaster(SubSpecialityMaster subSpecialityMaster) {
		this.subSpecialityMaster = subSpecialityMaster;
	}
	
	
	

}
