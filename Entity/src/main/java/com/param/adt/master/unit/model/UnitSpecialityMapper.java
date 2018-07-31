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
@NamedQueries({
	
	@NamedQuery(name="GET_UNIT_SPECIALITY_LIST",query="SELECT usm.unitSpecialityMapperId as unitSpecialityMapperId, "
			+ "usm.specialityId as specialityId, "
			+ "spl.specialityName as specialityName, "
			+ "spl.specialityCode as specialityCode, "
			+ "usm.status as status "
			+ "FROM UnitSpecialityMapper as usm "
			+ "INNER JOIN usm.specialityMaster as spl "
			+ "WHERE usm.status='A' OR usm.status='I' "
			+ "AND usm.organizationId=:orgId "
			+ "AND usm.unitId=:unitId "
			+ "ORDER BY usm.unitSpecialityMapperId DESC"),
	
	@NamedQuery(name="GET_UNIT_SPECIALITY_BY_ID",query="SELECT usm.unitSpecialityMapperId as unitSpecialityMapperId,"
			+ "usm.specialityId as specialityId,"
			+ "spl.specialityName as specialityName, "
			+ "spl.specialityCode as specialityCode "
			+ "FROM UnitSpecialityMapper as usm "
			+ "INNER JOIN usm.specialityMaster as spl "
			+ "WHERE usm.unitSpecialityMapperId=:unitSpecialityMapperId "
			+ "AND usm.organizationId=:orgId "
			+ "AND usm.unitId=:unitId "),
	
	@NamedQuery(name="GET_SPECIALITY_LIST_BY_DOCTOR_ID",query="SELECT speciality.specialityId as specialityId, "
			+ "speciality.specialityName as specialityName "
			+ "FROM UnitSpecialityMapper usm "
			+ "INNER JOIN usm.specialityMaster speciality "
			+ "INNER JOIN speciality.doctorSpecialityMappersList dsml "
			+ "WHERE dsml.status='A' "
			+ "AND usm.status='A' "
			+ "AND speciality.status='A' "
			+ "AND dsml.doctorId=:doctorId "
			+ "AND usm.organizationId=:orgId "
			+ "AND usm.unitId=:unitId "),
	@NamedQuery(name="GET_ACTIVE_UNIT_SPECIALITY_LIST",
	query="SELECT usm.unitSpecialityMapperId as unitSpecialityMapperId, "
			+ "usm.specialityId as specialityId, "
			+ "spl.specialityName as specialityName, "
			+ "spl.specialityCode as specialityCode, "
			+ "usm.status as status "
			+ "FROM UnitSpecialityMapper as usm "
			+ "INNER JOIN usm.specialityMaster as spl "
			+ "WHERE usm.status='A' "
			+ "AND usm.organizationId=:orgId "
			+ "AND usm.unitId=:unitId "
			+ "ORDER BY usm.unitSpecialityMapperId DESC")
})

@Entity
@Table(name = "t_unit_speciality_mapper", schema = "public")
@SequenceGenerator(name="unit_speciality_mapper_seq", sequenceName="public.unit_speciality_mapper_seq", allocationSize=1)
public class UnitSpecialityMapper 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unit_speciality_mapper_seq")
	@Column(name="unit_speciality_mapper_id")
	private int unitSpecialityMapperId;
	
	@Column(name = "unit_id")
	private Integer unitId;
	
	@Column(name="organization_id")
	private Integer organizationId;

	@Column(name="speciality_id")
	private Integer specialityId;

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

	public int getUnitSpecialityMapperId() {
		return unitSpecialityMapperId;
	}

	public void setUnitSpecialityMapperId(int unitSpecialityMapperId) {
		this.unitSpecialityMapperId = unitSpecialityMapperId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(Integer specialityId) {
		this.specialityId = specialityId;
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

	
	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
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

	public UnitMaster getUnitMaster() {
		return unitMaster;
	}

	public void setUnitMaster(UnitMaster unitMaster) {
		this.unitMaster = unitMaster;
	}


	
	
	
}
