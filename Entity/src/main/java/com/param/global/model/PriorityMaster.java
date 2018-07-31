package com.param.global.model;

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
	@NamedQuery(name="GET_PRIORITY_LIST",query="SELECT priority.priorityId as priorityId, "
			+ "priority.priorityCode as priorityCode "
			+ "FROM PriorityMaster priority "
			+ "WHERE priority.status='A'"),
	@NamedQuery(name = "GET_PRIORITY_MASTER_BY_ID",
	query = "SELECT pc.priorityId as id, "
			+ "pc.priorityDesc as desc, "
			+ "pc.priorityCode as code, "
			+ "pc.status as status  "
			+ "FROM PriorityMaster as pc "
			+ "WHERE pc.priorityId=:priorityId " 
			+ "AND pc.organizationId=:orgId "),
	@NamedQuery(name = "GET_PRIORITY_MASTER_LIST", 
	query = "SELECT pc.priorityId as id, "
			+ "pc.priorityDesc as desc, " 
			+ "pc.status as status, " 
			+ "pc.priorityCode as code "
			+ "FROM PriorityMaster as pc  "
			+ " WHERE pc.organizationId=:orgId "),
	@NamedQuery(name = "GET_PRIORITY_MASTER_BY_NAME", 
	query = "SELECT pc.priorityId as priorityId, "
			+ "pc.priorityDesc as priorityDesc, " 
			+ "pc.priorityCode as priorityCode, "
			+ "pc.status as status "
			+ "FROM PriorityMaster as pc  "
			+ " WHERE LOWER(pc.priorityDesc)=:priorityDesc " 
			+ "AND pc.organizationId=:orgId"),
	@NamedQuery(name = "GET_PRIORITY_MASTER_BY_NAME_NOT_BY_ID",
	query = "SELECT pc.priorityId as priorityId, "
			+ "pc.priorityDesc as priorityDesc, " 
			+ "pc.priorityCode as priorityCode "
			+ "FROM PriorityMaster as pc  "
			+ "WHERE LOWER(pc.priorityDesc)=:priorityDesc "
			+ "AND pc.priorityId !=:priorityId")
})


@Entity
@Table(name = "m_priority_master", schema = "adt")
@SequenceGenerator(name = "priority_master_seq", sequenceName = "adt.priority_master_seq", allocationSize = 1)
public class PriorityMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "priority_master_seq")
	@Column(name = "priority_id")
	private int priorityId;

	@Column(name = "priority_desc")
	private String priorityDesc;

	@Column(name = "priority_code")
	private String priorityCode;

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
	
//	@Column(name = "color_code")
//	private String colorCode;

	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private OrganizationMaster organizationMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id", insertable = false, updatable = false)
	private UnitMaster unitMaster;

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "priorityMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MedicoLeagal> medicoLeagalsList;*/
	
	public int getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(int priorityId) {
		this.priorityId = priorityId;
	}

	public String getPriorityDesc() {
		return priorityDesc;
	}

	public void setPriorityDesc(String priorityDesc) {
		this.priorityDesc = priorityDesc;
	}

	public String getPriorityCode() {
		return priorityCode;
	}

	public void setPriorityCode(String priorityCode) {
		this.priorityCode = priorityCode;
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

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	
	/*public OrganizationMaster getOrganizationMaster() {
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
	}*/

	/*public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;

	}*/
	
	
}
