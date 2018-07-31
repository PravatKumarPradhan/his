package com.param.adt.master.global.dto;

public class OccupancyUnitMasterDto {
	private int occupancyUnitId;

	private String occupancyUnitDesc;

	private String occupancyUnitCode;

	private String numberOfHours;

	private int createdBy;

	private String createdDate;

	private int updatedBy;

	private String updatedDate;

	private char status;

	private Integer organizationId;

	private Integer offset;

	private Integer noOfRecordsPerPage;

	
	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getNoOfRecordsPerPage() {
		return noOfRecordsPerPage;
	}

	public void setNoOfRecordsPerPage(Integer noOfRecordsPerPage) {
		this.noOfRecordsPerPage = noOfRecordsPerPage;
	}

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

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public int getOccupancyUnitId() {
		return occupancyUnitId;
	}

	public void setOccupancyUnitId(int occupancyUnitId) {
		this.occupancyUnitId = occupancyUnitId;
	}

	public String getOccupancyUnitDesc() {
		return occupancyUnitDesc;
	}

	public void setOccupancyUnitDesc(String occupancyUnitDesc) {
		this.occupancyUnitDesc = occupancyUnitDesc;
	}

	public String getOccupancyUnitCode() {
		return occupancyUnitCode;
	}

	public void setOccupancyUnitCode(String occupancyUnitCode) {
		this.occupancyUnitCode = occupancyUnitCode;
	}

	public String getNumberOfHours() {
		return numberOfHours;
	}

	public void setNumberOfHours(String numberOfHours) {
		this.numberOfHours = numberOfHours;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

}
