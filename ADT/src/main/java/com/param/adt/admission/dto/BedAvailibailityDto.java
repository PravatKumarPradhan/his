package com.param.adt.admission.dto;

public class BedAvailibailityDto 
{
	private String date;
	
	private String startDate;
	
	private String endDate;
	
	private Integer organizationId;
	
	private Integer unitId;
	
	private Integer bedCategoryId;
	
	private Integer waitListNumber;
	
	private String doa;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public Integer getBedCategoryId() {
		return bedCategoryId;
	}

	public void setBedCategoryId(Integer bedCategoryId) {
		this.bedCategoryId = bedCategoryId;
	}

	public Integer getWaitListNumber() {
		return waitListNumber;
	}

	public void setWaitListNumber(Integer waitListNumber) {
		this.waitListNumber = waitListNumber;
	}

	public String getDoa() {
		return doa;
	}

	public void setDoa(String doa) {
		this.doa = doa;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	

	

	
}
