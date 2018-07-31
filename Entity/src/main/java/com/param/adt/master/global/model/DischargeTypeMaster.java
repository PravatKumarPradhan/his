package com.param.adt.master.global.model;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.adt.discharge.model.Discharge;

@NamedQueries({
	@NamedQuery(name="GET_DISCHARGE_TYPE_LIST", query="SELECT dt.dischargeTypeId as dischargeTypeId, "
			+ "dt.dischargeTypeName as dischargeTypeName, "
			+ "dt.dischargeTypeCode as dischargeTypeCode, "
			+ "dt.status as status "
			+ "FROM DischargeTypeMaster dt "
			+ "WHERE dt.organizationId=:orgId "
			+ "ORDER BY dt.dischargeTypeId DESC"),
	
	@NamedQuery(name="GET_DISCHARGE_TYPE_LIST_BY_ID", query="SELECT dt.dischargeTypeId as dischargeTypeId, "
			+ "dt.dischargeTypeName as dischargeTypeName, "
			+ "dt.dischargeTypeCode as dischargeTypeCode, "
			+ "dt.status as status "
			+ "FROM DischargeTypeMaster dt " 
			+ "WHERE dt.dischargeTypeId=:dischargeTypeId"),
	
	@NamedQuery(name="GET_DISCHARGE_TYPE_LIST_BY_NAME", query="SELECT dt.dischargeTypeId as dischargeTypeId, "
			+ "dt.dischargeTypeName as dischargeTypeName "
			+ "FROM DischargeTypeMaster dt " 
			+ "WHERE dt.dischargeTypeName=:dischargeTypeName OR LOWER(dt.dischargeTypeName)=:dischargeTypeName"),
	
	@NamedQuery(name="GET_DISCHARGE_TYPE_LIST_BY_NAME_NOT_ID", query="SELECT dt.dischargeTypeId as dischargeTypeId, "
			+ "dt.dischargeTypeName as dischargeTypeName "
			+ "FROM DischargeTypeMaster dt " 
			+ "WHERE dt.dischargeTypeId!=:dischargeTypeId "
			+ "AND (dt.dischargeTypeName=:dischargeTypeName OR LOWER(dt.dischargeTypeName)=:dischargeTypeName)"),
	
	@NamedQuery(name="GET_ACTIVE_DISCHARGE_TYPE_LIST", query="SELECT dt.dischargeTypeId as dischargeTypeId, "
			+ "dt.dischargeTypeName as dischargeTypeName "
			+ "FROM DischargeTypeMaster dt " 
			+ "WHERE dt.status='A' "
			+ "AND dt.organizationId=:orgId ")
	
	
})

@Entity
@Table(name = "m_reason_for_discharge_master", schema = "adt")
@SequenceGenerator(name="discharge_type_master_seq", sequenceName="adt.discharge_type_master_seq", allocationSize=1)
public class DischargeTypeMaster 
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="discharge_type_master_seq")
	@Column(name="discharge_type_id")
	private int dischargeTypeId;
	
	@Column(name="discharge_type_name")
	private String dischargeTypeName;
	
	@Column(name="discharge_type_code")
	private String dischargeTypeCode;
	
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

	@Column(name="organization_id")
	private Integer organizationId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private OrganizationMaster organizationMaster;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "dischargeTypeMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Discharge> dischargesList;
	

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

	public int getDischargeTypeId() {
		return dischargeTypeId;
	}

	public void setDischargeTypeId(int dischargeTypeId) {
		this.dischargeTypeId = dischargeTypeId;
	}

	public String getDischargeTypeName() {
		return dischargeTypeName;
	}

	public void setDischargeTypeName(String dischargeTypeName) {
		this.dischargeTypeName = dischargeTypeName;
	}

	public String getDischargeTypeCode() {
		return dischargeTypeCode;
	}

	public void setDischargeTypeCode(String dischargeTypeCode) {
		this.dischargeTypeCode = dischargeTypeCode;
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
