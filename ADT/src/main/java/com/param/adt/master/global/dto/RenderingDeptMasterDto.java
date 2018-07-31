package com.param.adt.master.global.dto;

public class RenderingDeptMasterDto 
{
	private int rederingDeptId;
	
	private int specialityId;
	
	private int subSpecialityId;
	
	private int createdBy;
	
	private String createdDate;
	
	private char status;

	private int updatedBy;

	private String updatedDate;
	
private Integer organizationId;
	
	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	
	
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

	public int getRederingDeptId() {
		return rederingDeptId;
	}

	public void setRederingDeptId(int rederingDeptId) {
		this.rederingDeptId = rederingDeptId;
	}

	

	public int getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(int specialityId) {
		this.specialityId = specialityId;
	}

	public int getSubSpecialityId() {
		return subSpecialityId;
	}

	public void setSubSpecialityId(int subSpecialityId) {
		this.subSpecialityId = subSpecialityId;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}
	
	
}
