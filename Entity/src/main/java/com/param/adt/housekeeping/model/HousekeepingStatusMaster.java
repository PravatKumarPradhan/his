package com.param.adt.housekeeping.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="m_housekeeping_status_master",schema="adt")
@SequenceGenerator(name="housekeeping_status_master_seq",sequenceName="adt.housekeeping_status_master_seq",allocationSize=1)
public class HousekeepingStatusMaster {

	@Id
	@Column(name="housekeeping_status_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="housekeeping_status_master_seq")
	private int housekeepingStatusId;
	
	@Column(name="organization_id")
	private Integer organizationId;

	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="housekeeping_status_code")
	private String housekeepingStatusCode;
	
	@Column(name="housekeeping_status_desc")
	private String housekeepingStatusDesc;
	
	@Column(name="status")
	private char status;

	public int getHousekeepingStatusId() {
		return housekeepingStatusId;
	}

	public void setHousekeepingStatusId(int housekeepingStatusId) {
		this.housekeepingStatusId = housekeepingStatusId;
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

	public String getHousekeepingStatusCode() {
		return housekeepingStatusCode;
	}

	public void setHousekeepingStatusCode(String housekeepingStatusCode) {
		this.housekeepingStatusCode = housekeepingStatusCode;
	}

	public String getHousekeepingStatusDesc() {
		return housekeepingStatusDesc;
	}

	public void setHousekeepingStatusDesc(String housekeepingStatusDesc) {
		this.housekeepingStatusDesc = housekeepingStatusDesc;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}
	
	
}
