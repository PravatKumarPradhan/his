package com.param.global.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
	
	@NamedQuery(name="GET_LIST_SYSTEM_OBSERVATION_PROPERTY",
			query=" SELECT 		sysObMst.observationId as observationId, sysObMst.observationName as observationName, sysObMst.systemId as systemId,"
				+ "				sysObMst.observationCode as observationCode, sysObMst.isPropertyRequired as isPropertyRequired, sysObMst.status as status, "
				+ "				sysObMst.unitId as unitId, sysObMst.organizationId as organizationId, sysObMst.createdBy as createdBy, sysObMst.updatedBy as updatedBy,"
				+ "				to_char(sysObMst.createdDate,'DD-MM-YYYY') as createdDate, to_char(sysObMst.updatedDate,'DD-MM-YYYY') as updatedDate,"
				+ "  			sysProMst.propertyId as propertyId, sysProMst.propertyName as propertyName, sysProMst.status as propertyStatus, "
				+ "             sysMst.systemName as systemName"
				+ " FROM 		SystemObervationMaster sysObMst"
				+ " INNER JOIN	sysObMst.systemMaster sysMst "
				+ " LEFT OUTER JOIN	sysObMst.SystemObervationPropertyMasterList sysProMst "
				+ " WHERE 		sysObMst.unitId=:unitId "
				+ " AND 		sysObMst.organizationId=:organizationId"
			
			)
	
})


@Entity
@Table(name="m_system_observation_master", schema="emr")
@SequenceGenerator(name="system_observation_seq", sequenceName="emr.system_observation_seq", allocationSize=1)
public class SystemObervationMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system_observation_seq")
	@Column(name="observation_id")
	private Integer observationId;
	
	@Column(name="observation_name")
	private String observationName;
	
	@Column(name="system_id")
	private Integer systemId;
	
	@Column(name="observation_code")
	private String observationCode;
	
	@Column(name="is_property_required")
	private char isPropertyRequired;
	
	@Column(name="status")
	private char status;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="created_by")
	private Integer createdBy;
	
	@Column(name="updated_by")
	private Integer updatedBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Transient
	private boolean systemObservationFlag;
	
	@Transient
	private String systemObervationRemark;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "system_id", insertable = false, nullable = false, updatable = false)
	private SystemMaster systemMaster;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "systemObervationMaster", cascade = CascadeType.ALL, orphanRemoval = true) 
	private List<SystemObervationPropertyMaster> SystemObervationPropertyMasterList;
	
	
	
	

	public String getSystemObervationRemark() {
		return systemObervationRemark;
	}

	public void setSystemObervationRemark(String systemObervationRemark) {
		this.systemObervationRemark = systemObervationRemark;
	}

	public boolean isSystemObservationFlag() {
		return systemObservationFlag;
	}

	public void setSystemObservationFlag(boolean systemObservationFlag) {
		this.systemObservationFlag = systemObservationFlag;
	}

	public Integer getObservationId() {
		return observationId;
	}

	public void setObservationId(Integer observationId) {
		this.observationId = observationId;
	}

	public String getObservationName() {
		return observationName;
	}

	public void setObservationName(String observationName) {
		this.observationName = observationName;
	}

	public Integer getSystemId() {
		return systemId;
	}

	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}

	public String getObservationCode() {
		return observationCode;
	}

	public void setObservationCode(String observationCode) {
		this.observationCode = observationCode;
	}

	public char getIsPropertyRequired() {
		return isPropertyRequired;
	}

	public void setIsPropertyRequired(char isPropertyRequired) {
		this.isPropertyRequired = isPropertyRequired;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
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

	public SystemMaster getSystemMaster() {
		return systemMaster;
	}

	public void setSystemMaster(SystemMaster systemMaster) {
		this.systemMaster = systemMaster;
	}

	public List<SystemObervationPropertyMaster> getSystemObervationPropertyMasterList() {
		return SystemObervationPropertyMasterList;
	}

	public void setSystemObervationPropertyMasterList(
			List<SystemObervationPropertyMaster> systemObervationPropertyMasterList) {
		SystemObervationPropertyMasterList = systemObervationPropertyMasterList;
	}

	
	
	
}
