package com.param.global.model;

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
import javax.persistence.Transient;

@NamedQueries({

		@NamedQuery(name = "GET_SYSTEM_LIST", 
				query = " SELECT   	sysMst.systemId as systemId, "
				+ " sysMst.systemName as systemName, "
				+ " sysMst.systemCode as systemCode,"
				+ "	sysMst.status as status, "
				+ " sysMst.organizationId as organizationId, "
				+ " sysMst.unitId as unitId,"
				+ "	sysMst.createdBy as createdBy, "
				+ " sysMst.updatedBy as updatedBy, "
				+ " sysMst.genderId as genderId,"
				+ " genderMaster.desc as desc , "
				+ " sysMst.typeId as typeId , "
				+ "	to_char(sysMst.createdDate,'DD-MM-YYYY')as createdDate, "
				+ " to_char(sysMst.updatedDate, 'DD-MM-YYYY')as updatedDate "
				+ " FROM SystemMaster sysMst "
				+ " INNER JOIN sysMst.genderMaster genderMaster" 
				+ " WHERE sysMst.unitId=:unitId "
				+ " AND sysMst.organizationId=:organizationId "),
		@NamedQuery(name = "GET_LIST_OF_SYSTEM_MASTER_BY_ID", 
		 			query = " SELECT   	sysMst.systemId as systemId,"
				+ " sysMst.systemName as systemName, " 
				+ " sysMst.systemCode as systemCode, "
				+ " sysMst.typeId as typeId , "
				+ "	sysMst.status as status, "
				+ " sysMst.organizationId as organizationId, "
				+ " sysMst.unitId as unitId, "
			    + "	sysMst.genderId as genderId, "
			    + " genderMaster.desc as desc "
				+ " FROM SystemMaster sysMst "
				+ " INNER JOIN sysMst.genderMaster genderMaster "
				+ " WHERE sysMst.systemId=:systemId"),
		@NamedQuery(name = "GET_LIST_OF_SYSTEM_MASTER_BY_TYPE", 
			    query = " SELECT sysMst.systemName as systemName,"
			    + "	sysMst.systemId as systemId, " 
				+ " sysMst.systemCode as systemCode, "
				+ " sysMst.typeId as typeId , "
				+ "	sysMst.status as status, "
				+ " sysMst.organizationId as organizationId, "
				+ " sysMst.unitId as unitId, "
			    + "	sysMst.genderId as genderId "   
				+ " FROM SystemMaster sysMst "	
				+ " WHERE sysMst.organizationId=:organizationId "
				+ " AND sysMst.unitId=:unitId "
				+ " AND sysMst.typeId=:typeId "
				+ " OR sysMst.typeId= 3" )

})

@Entity
@Table(name = "m_system_master", schema = "emr")
@SequenceGenerator(name = "system_master_seq", sequenceName = "emr.system_master_seq", allocationSize = 1)
public class SystemMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system_master_seq")
	@Column(name = "system_id")
	private Integer systemId;

	@Column(name = "system_name")
	private String systemName;

	@Column(name = "system_code")
	private String systemCode;

	@Column(name = "status")
	private char status;

	@Column(name = "organization_id")
	private Integer organizationId;

	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "gender_id")
	private Integer genderId;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name = "type_id")
	private Integer typeId;
	
	@Transient
	private boolean systemFlag;
	
	@Transient
	private String systemRemark;
	
	@Transient
	private char systemNADFlag;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "systemMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SystemObervationMaster> SystemObervationMasterList;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gender_id", insertable = false, updatable = false)
	private GenderMaster genderMaster;

	
	

	public char getSystemNADFlag() {
		return systemNADFlag;
	}

	public void setSystemNADFlag(char systemNADFlag) {
		this.systemNADFlag = systemNADFlag;
	}

	public String getSystemRemark() {
		return systemRemark;
	}

	public void setSystemRemark(String systemRemark) {
		this.systemRemark = systemRemark;
	}

	public boolean isSystemFlag() {
		return systemFlag;
	}

	public void setSystemFlag(boolean systemFlag) {
		this.systemFlag = systemFlag;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
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

	public Integer getGenderId() {
		return genderId;
	}

	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getSystemId() {
		return systemId;
	}

	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public List<SystemObervationMaster> getSystemObervationMasterList() {
		return SystemObervationMasterList;
	}

	public void setSystemObervationMasterList(List<SystemObervationMaster> systemObervationMasterList) {
		SystemObervationMasterList = systemObervationMasterList;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

}
