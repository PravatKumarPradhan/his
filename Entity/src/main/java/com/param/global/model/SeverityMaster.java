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
	
	@NamedQuery(name="GET_LIST_SEVERITY_MASTER",
			query="     SELECT 	servtyMst.severityId as severityId, servtyMst.severityDesc as severityDesc,"
					+ "			servtyMst.status as status"
					+ " FROM 	SeverityMaster servtyMst"
					+ " WHERE 	servtyMst.status = 'A'"
					+ " AND 	servtyMst.unitId=:unitId"
					+ " AND 	servtyMst.organizationId=:organizationId"
			)
})


@Entity
@Table(name="m_severity_master",schema="emr")
@SequenceGenerator(name = "severity_master_seq", sequenceName = "emr.severity_master_seq", allocationSize = 1)
public class SeverityMaster {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "severity_master_seq")
	@Column(name = "severity_id")
	private Integer severityId;

	@Column(name = "severity_desc")
	private String severityDesc;

	@Column(name="status")
	private char status;
	
	@Column(name="created_by")
	private Integer createdBy;
	
	@Column(name="updated_by")
	private Integer updatedBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name = "marathi_severity_desc")
	private String marathiSeverityDesc;

	public Integer getSeverityId() {
		return severityId;
	}

	public void setSeverityId(Integer severityId) {
		this.severityId = severityId;
	}

	public String getSeverityDesc() {
		return severityDesc;
	}

	public void setSeverityDesc(String severityDesc) {
		this.severityDesc = severityDesc;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
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

	public String getMarathiSeverityDesc() {
		return marathiSeverityDesc;
	}

	public void setMarathiSeverityDesc(String marathiSeverityDesc) {
		this.marathiSeverityDesc = marathiSeverityDesc;
	}

	
}
