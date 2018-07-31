package com.param.adt.master.unit.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.adt.master.global.model.OrganizationMaster;
import com.param.adt.master.global.model.UnitMaster;
import com.param.adt.mortuary.model.MortuaryRequest;


@NamedQueries({

	@NamedQuery(name = "GET_MORTURY_BED_LIST", query = "SELECT mortuaryBedMaster.mortuaryBedId as mortuaryBedId, "
			+ "mortuaryBedMaster.mortuaryBedCode as mortuaryBedCode, " 
			+ "mortuaryBedMaster.mortuaryBedDesc as mortuaryBedDesc, "
			+ "mortuaryBedMaster.mortuaryBedNumber as mortuaryBedNumber, "
			+ "mortuaryBedMaster.status as status " 
			+ "FROM MortuaryBedMaster mortuaryBedMaster "
			+ "INNER JOIN mortuaryBedMaster.mortuaryBedLogStatusList mbls "
			+ "WHERE mortuaryBedMaster.organizationId=:orgId " 
			+ "AND mortuaryBedMaster.unitId=:unitId "
			+ "AND mbls.status='A' "
			+ "ORDER BY mortuaryBedMaster.mortuaryBedId DESC"),

	@NamedQuery(name = "GET_MORTURY_BED_LIST_BY_NAME", query = "SELECT mortuaryBedMaster.mortuaryBedId as mortuaryBedId, "
			+ "mortuaryBedMaster.mortuaryBedDesc as mortuaryBedDesc "
			+ "FROM MortuaryBedMaster mortuaryBedMaster "
			+ "INNER JOIN mortuaryBedMaster.mortuaryBedLogStatusList mbls "
			+ "WHERE LOWER(mortuaryBedMaster.mortuaryBedDesc)=:mortuaryBedDesc "
			+ "OR mortuaryBedMaster.mortuaryBedDesc=:mortuaryBedDesc "
			+ "AND mortuaryBedMaster.organizationId=:orgId " 
			+ "AND mortuaryBedMaster.unitId=:unitId "),

	@NamedQuery(name = "GET_MORTURY_BED_LIST_BY_ID", query = "SELECT mortuaryBedMaster.mortuaryBedId as mortuaryBedId, "
			+ "mortuaryBedMaster.mortuaryBedCode as mortuaryBedCode, " 
			+ "mortuaryBedMaster.mortuaryBedDesc as mortuaryBedDesc, "
			+ "mortuaryBedMaster.status as status " 
			+ "FROM MortuaryBedMaster mortuaryBedMaster "
			+ "WHERE mortuaryBedMaster.mortuaryBedId=:mortuaryBedId "),

	@NamedQuery(name = "GET_MORTURY_BED_BY_NAME_NOT_ID", query = "SELECT mortuaryBedMaster.mortuaryBedId as mortuaryBedId, "
			+ "mortuaryBedMaster.mortuaryBedDesc as mortuaryBedDesc "
			+ "FROM MortuaryBedMaster mortuaryBedMaster "
			+ "WHERE (LOWER(mortuaryBedMaster.mortuaryBedDesc)=:mortuaryBedDesc OR mortuaryBedMaster.mortuaryBedDesc=:mortuaryBedDesc) "
			+ "AND mortuaryBedMaster.mortuaryBedId!=:mortuaryBedId "
			+ "AND mortuaryBedMaster.organizationId=:orgId " 
			+ "AND mortuaryBedMaster.unitId=:unitId "),

	@NamedQuery(name = "GET_ACTIVE_MORTURY_BED_LIST", query = "SELECT mortuaryBedMaster.mortuaryBedId as mortuaryBedId, "
			+ "mortuaryBedMaster.mortuaryBedNumber as mortuaryBedNumber, "
			+ "mortuaryBedMaster.mortuaryBedDesc as mortuaryBedDesc "
			+ "FROM MortuaryBedMaster mortuaryBedMaster " 
			+ "INNER JOIN mortuaryBedMaster.mortuaryBedLogStatusList mbls "
			+ "WHERE mortuaryBedMaster.organizationId=:orgId " 
			+ "AND mortuaryBedMaster.unitId=:unitId " 
			+ "AND mortuaryBedMaster.status='A' "
			+ "AND mbls.status='A' ")
	
})

@NamedNativeQueries({
	@NamedNativeQuery(name = "GET_ACTIVE_MORTURY_BED_LIST_BY_STATUS_ID", query = "SELECT mortuaryBedMaster.mortuary_bed_id as \"mortuaryBedId\", "
			+ "mortuaryBedMaster.mortuary_bed_number as \"mortuaryBedNumber\","
			+ "mbls.bed_status_id as \"bedStatusId\" "
			+ "FROM adt.m_mortuary_bed_master mortuaryBedMaster "
			+ "INNER JOIN adt.t_mortuary_bed_log_status mbls on mbls.mortuary_bed_id=mortuaryBedMaster.mortuary_bed_id " 
			+ "WHERE mortuaryBedMaster.organization_id=:orgId " 
			+ "AND mortuaryBedMaster.unit_id=:unitId " 
			+ "AND mortuaryBedMaster.status='A' "
			+ "AND mbls.status='A' ")
	
	
})
@Entity
@Table(name = "m_mortuary_bed_master", schema = "adt")
@SequenceGenerator(name = "mortuary_bed_master_seq", sequenceName = "adt.mortuary_bed_master_seq", allocationSize = 1)
public class MortuaryBedMaster 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mortuary_bed_master_seq")
	@Column(name = "mortuary_bed_id")
	private int mortuaryBedId;
	
	@Column(name="mortuary_bed_code")
	private String mortuaryBedCode;
	
	@Column(name="mortuary_bed_desc")
	private String mortuaryBedDesc;
	
	@Column(name="mortuary_bed_number")
	private String mortuaryBedNumber;
	
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "created_by")
	private int createdBy;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="status")
	private char status;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, nullable = false, updatable = false)
	private OrganizationMaster organizationMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id", insertable = false, nullable = false, updatable = false)
	private UnitMaster unitMaster;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "mortuaryBedMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MortuaryBedLogStatus> mortuaryBedLogStatusList;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "mortuaryBedMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MortuaryRequest> mortuaryRequestsList;
	
	
	public int getMortuaryBedId() {
		return mortuaryBedId;
	}

	public void setMortuaryBedId(int mortuaryBedId) {
		this.mortuaryBedId = mortuaryBedId;
	}

	public String getMortuaryBedCode() {
		return mortuaryBedCode;
	}

	public void setMortuaryBedCode(String mortuaryBedCode) {
		this.mortuaryBedCode = mortuaryBedCode;
	}

	public String getMortuaryBedDesc() {
		return mortuaryBedDesc;
	}

	public void setMortuaryBedDesc(String mortuaryBedDesc) {
		this.mortuaryBedDesc = mortuaryBedDesc;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
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

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = (status == '\u0000') ? 'A' : status;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
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

	public String getMortuaryBedNumber() {
		return mortuaryBedNumber;
	}

	public void setMortuaryBedNumber(String mortuaryBedNumber) {
		this.mortuaryBedNumber = mortuaryBedNumber;
	}
	
}
