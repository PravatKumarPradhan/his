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

import com.param.adt.master.global.model.ICUTypeMaster;
import com.param.adt.master.global.model.OrganizationMaster;
import com.param.adt.master.global.model.UnitMaster;

@NamedQueries({
	
	@NamedQuery(name="GET_UNIT_ICU_TYPE_LIST",query="SELECT uicu.unitICUTypeId as unitICUTypeId, "
			+ "uicu.icuTypeId as icuTypeId, "
			+ "icu.ICUtypeCode as ICUtypeCode, "
			+ "icu.ICUType as ICUType, "
			+ "icu.isCloseICU as isCloseICU, "
			+ "icu.isOpenICU as isOpenICU, "
			+ "uicu.status as status "
			+ "FROM UnitICUTypeMapper uicu "
			+ "INNER JOIN uicu.icuTypeMaster icu "
			+ "WHERE uicu.status='A' "
			+ "OR uicu.status='I' "
			+ "AND uicu.unitId=:unitId "
			+ "AND uicu.organizationId=:orgId "
			+ "ORDER BY uicu.unitICUTypeId DESC"),
	
	@NamedQuery(name="GET_ACTIVE_UNIT_ICU_TYPE_LIST",query="SELECT uicu.unitICUTypeId as unitICUTypeId, "
			+ "uicu.icuTypeId as icuTypeId, "
			+ "icu.ICUtypeCode as ICUtypeCode, "
			+ "icu.ICUType as ICUType, "
			+ "icu.isCloseICU as isCloseICU, "
			+ "icu.isOpenICU as isOpenICU, "
			+ "ward.wardId as wardId, "
			+ "ward.floorId as floorId, "
			+ "ward.wardName as wardName, "
			+ "uicu.status as status "
			+ "FROM UnitICUTypeMapper uicu "
			+ "INNER JOIN uicu.icuTypeMaster icu "
			+ "INNER JOIN icu.wardMastersList ward "
			+ "WHERE uicu.status='A' "
			+ "AND uicu.unitId=:unitId "
			+ "AND uicu.organizationId=:orgId "),
	
	@NamedQuery(name="GET_UNIT_ICU_TYPE_LIST_BY_ID", query="SELECT uicu.unitICUTypeId as unitICUTypeId "
			+ "FROM UnitICUTypeMapper uicu "
			+ "WHERE uicu.unitICUTypeId=:unitICUTypeId")
	
})
@Entity
@Table(name="t_unit_icu_type_mapper",schema="public")
@SequenceGenerator(name="unit_icu_type_mapper_seq",sequenceName="public.unit_icu_type_mapper_seq",allocationSize=1)
public class UnitICUTypeMapper 
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="unit_icu_type_mapper_seq")
	@Column(name="unit_icu_type_id")
	private int unitICUTypeId;
	
	@Column(name = "unit_id")
	private Integer unitId;
	
	@Column(name="organization_id")
	private Integer organizationId;

	@Column(name="icu_type_id")
	private Integer icuTypeId;

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
	@JoinColumn(name = "icu_type_id", insertable = false, updatable = false)
	private ICUTypeMaster icuTypeMaster;

	public int getUnitICUTypeId() {
		return unitICUTypeId;
	}

	public void setUnitICUTypeId(int unitICUTypeId) {
		this.unitICUTypeId = unitICUTypeId;
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

	public Integer getIcuTypeId() {
		return icuTypeId;
	}

	public void setIcuTypeId(Integer icuTypeId) {
		this.icuTypeId = icuTypeId;
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

	public ICUTypeMaster getIcuTypeMaster() {
		return icuTypeMaster;
	}

	public void setIcuTypeMaster(ICUTypeMaster icuTypeMaster) {
		this.icuTypeMaster = icuTypeMaster;
	}
	
}
