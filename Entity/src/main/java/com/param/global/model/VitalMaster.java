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
	
	@NamedQuery(name="GET_VITAL_MASTER_LIST",
			query=" SELECT 	vitalMst.vitalId as vitalId, vitalMst.vitalName as vitalName, vitalMst.minValue as minValue, "
				+ "			vitalMst.maxValue as maxValue, vitalMst.status as status, vitalMst.unit as unit, vitalMst.remark as remark,"
				+ "			vitalMst.vitalIconPath as vitalIconPath, vitalMst.createdBy as createdBy ,"
				+ "			to_char(vitalMst.createdDate,'DD-MM-YYYY') as createdDate, to_char(vitalMst.updatedDate,'DD-MM-YYYY') as updatedDate,"
				+ "			vitalMst.updatedBy as updatedBy, vitalMst.unitId as unitId, vitalMst.organizationId as organizationId "
				+ " FROM 	VitalMaster vitalMst "
				+ " WHERE 	vitalMst.unitId=:unitId"
				+ " AND 	vitalMst.organizationId=:orgId"
				+ " AND 	vitalMst.status = 'A' "
			)
})

@Entity
@Table(name = "m_vital_master", schema = "emr")
@SequenceGenerator(name = "m_vital_master_seq", sequenceName = "emr.m_vital_master_seq", allocationSize = 1)
public class VitalMaster {

	@Id
	@Column(name = "vital_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_vital_master_seq")
	private int vitalId;
	
	@Column(name = "vital_name")
	private String vitalName;
	
	@Column(name = "min_value")
	private String minValue;

	@Column(name = "max_value")
	private String maxValue;

	@Column(name = "status")
	private char status;
	
	@Column(name = "unit")
	private String unit;
	
	@Column(name = "remark")
	private String remark;
	
	@Column(name="vital_icon_path")
	private String vitalIconPath;
	
	@Column(name = "created_by")
	private Integer createdBy;
	
	@Column(name = "createdDate")
	private Date createdDate;
	
	@Column(name = "updated_by")
	private Integer updatedBy;
	
	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name = "unit_id")
	private Integer unitId;
	
	@Column(name = "organization_id")
	private Integer organizationId;

	
	
	public String getVitalIconPath() {
		return vitalIconPath;
	}

	public void setVitalIconPath(String vitalIconPath) {
		this.vitalIconPath = vitalIconPath;
	}

	public int getVitalId() {
		return vitalId;
	}

	public void setVitalId(int vitalId) {
		this.vitalId = vitalId;
	}

	public String getVitalName() {
		return vitalName;
	}

	public void setVitalName(String vitalName) {
		this.vitalName = vitalName;
	}

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
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
	
	
}
