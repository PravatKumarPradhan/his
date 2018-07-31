package com.param.global.model;

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
import javax.persistence.Transient;


@Entity
@Table(name="m_system_observation_property_master", schema="emr")
@SequenceGenerator(name="system_observation_property_seq", sequenceName="emr.system_observation_property_seq", allocationSize=1)
public class SystemObervationPropertyMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system_observation_property_seq")
	@Column(name="property_id")
	private Integer propertyId;
	
	@Column(name="observation_id")
	private Integer observationId;
	
	@Column(name="property_name")
	private String propertyName;
	
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
	private boolean systemObservationPropertyFlag;
	
	@Transient
	private String systemObservationPropertyRemark;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "observation_id", insertable = false, nullable = false, updatable = false)
	private SystemObervationMaster systemObervationMaster;
	
	
	
	
	
	public String getSystemObservationPropertyRemark() {
		return systemObservationPropertyRemark;
	}

	public void setSystemObservationPropertyRemark(
			String systemObservationPropertyRemark) {
		this.systemObservationPropertyRemark = systemObservationPropertyRemark;
	}

	public boolean isSystemObservationPropertyFlag() {
		return systemObservationPropertyFlag;
	}

	public void setSystemObservationPropertyFlag(
			boolean systemObservationPropertyFlag) {
		this.systemObservationPropertyFlag = systemObservationPropertyFlag;
	}

	public Integer getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}

	public Integer getObservationId() {
		return observationId;
	}

	public void setObservationId(Integer observationId) {
		this.observationId = observationId;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
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

	/*public SystemObervationMaster getSystemObervationMaster() {
		return systemObervationMaster;
	}

	public void setSystemObervationMaster(
			SystemObervationMaster systemObervationMaster) {
		this.systemObervationMaster = systemObervationMaster;
	}*/

	
}
