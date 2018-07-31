package com.param.adt.master.global.dto;

import java.util.Date;

public class OrganizationUnitUserMapperDto 
{
	private OrganizationUnitUserMapperId organizationUnitUserMapperId;
	private Character status;
	private Integer createdBy;
	private Date createdDate;
	private Integer organizationlId;
	private Integer unitId;
	private Integer userId;
	private int updatedBy;

	private String updatedDate;
	
	
	
	
	public int getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public OrganizationUnitUserMapperId getOrganizationUnitUserMapperId() {
		return organizationUnitUserMapperId;
	}
	public void setOrganizationUnitUserMapperId(OrganizationUnitUserMapperId organizationUnitUserMapperId) {
		this.organizationUnitUserMapperId = organizationUnitUserMapperId;
	}
	public Integer getOrganizationlId() {
		return organizationlId;
	}
	public void setOrganizationlId(Integer organizationlId) {
		this.organizationlId = organizationlId;
	}
	public Character getStatus() {
		return status;
	}
	public void setStatus(Character status) {
		this.status = status;
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

	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
