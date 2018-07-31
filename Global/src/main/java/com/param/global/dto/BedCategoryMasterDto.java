package com.param.global.dto;


public class BedCategoryMasterDto {
	private int bedCategoryId;

	private Integer organizationId;

	private String bedCategoryCode;

	private String bedCategoryDesc;

	private Integer hierarchyId;

	private String hierarchyDesc;

	private Integer occupancyUnitId;

	private String occupancyUnitDesc;

	private char isBedRetention;

	private int createdBy;

	private String createdDate;

	private Character status;

	private int updatedBy;

	private String updatedDate;

	private Integer unitId;
	
	private Integer offset;
	
	private Integer noOfRecordsPerPage;
	
	private Integer xOccupancyUnitId;
	
	private Integer xHierarchyId;

	
	
	public Integer getxOccupancyUnitId() {
		return xOccupancyUnitId;
	}

	public void setxOccupancyUnitId(Integer xOccupancyUnitId) {
		this.xOccupancyUnitId = xOccupancyUnitId;
	}

	public Integer getxHierarchyId() {
		return xHierarchyId;
	}

	public void setxHierarchyId(Integer xHierarchyId) {
		this.xHierarchyId = xHierarchyId;
	}

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

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public int getBedCategoryId() {
		return bedCategoryId;
	}

	public void setBedCategoryId(int bedCategoryId) {
		this.bedCategoryId = bedCategoryId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getBedCategoryCode() {
		return bedCategoryCode;
	}

	public void setBedCategoryCode(String bedCategoryCode) {
		this.bedCategoryCode = bedCategoryCode;
	}

	public String getBedCategoryDesc() {
		return bedCategoryDesc;
	}

	public void setBedCategoryDesc(String bedCategoryDesc) {
		this.bedCategoryDesc = bedCategoryDesc;
	}

	public Integer getHierarchyId() {
		return hierarchyId;
	}

	public void setHierarchyId(Integer hierarchyId) {
		this.hierarchyId = hierarchyId;
	}

	public String getHierarchyDesc() {
		return hierarchyDesc;
	}

	public void setHierarchyDesc(String hierarchyDesc) {
		this.hierarchyDesc = hierarchyDesc;
	}

	public Integer getOccupancyUnitId() {
		return occupancyUnitId;
	}

	public void setOccupancyUnitId(Integer occupancyUnitId) {
		this.occupancyUnitId = occupancyUnitId;
	}

	public String getOccupancyUnitDesc() {
		return occupancyUnitDesc;
	}

	public void setOccupancyUnitDesc(String occupancyUnitDesc) {
		this.occupancyUnitDesc = occupancyUnitDesc;
	}

	public char getIsBedRetention() {
		return isBedRetention;
	}

	public void setIsBedRetention(char isBedRetention) {
		this.isBedRetention = (isBedRetention == '\u0000') ? 'A' : isBedRetention;
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

	
	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
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

}
