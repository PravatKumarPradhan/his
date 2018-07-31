package com.param.adt.master.unit.dto;

public class NursingStationMasterDto 
{
	
	private int nursingStationId;
	
	private String nursingStationCode;
	
	private String nursingStationDesc;
	
	private String createdDate;

	private int createdBy;

	private String userName;

	private int updatedBy;

	private String updatedDate;
	
	private char status;

	private Integer organizationId;
	
	private Integer unitId;

	public int getNursingStationId() {
		return nursingStationId;
	}

	public void setNursingStationId(int nursingStationId) {
		this.nursingStationId = nursingStationId;
	}

	public String getNursingStationCode() {
		return nursingStationCode;
	}

	public void setNursingStationCode(String nursingStationCode) {
		this.nursingStationCode = nursingStationCode;
	}

	public String getNursingStationDesc() {
		return nursingStationDesc;
	}

	public void setNursingStationDesc(String nursingStationDesc) {
		this.nursingStationDesc = nursingStationDesc;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
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

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
}
