package com.param.adt.master.unit.dto;

public class MortuaryBedMasterDto {

	
	private int mortuaryBedId;

	private String mortuaryBedCode;
	
	private String mortuaryBedNumber;

	private String mortuaryBedDesc;

	private String createdDate;

	private int createdBy;

	private String userName;

	private int updatedBy;

	private String updatedDate;

	private char status;

	private Integer organizationId;

	private Integer unitId;
	
	private Integer bedCount;
	
	private String basePattern;
	
	private Integer offset;
	
	private Integer noOfRecordsPerPage;
	
	private Integer bedStatusId;
	
	public Integer getBedStatusId() {
		return bedStatusId;
	}

	public void setBedStatusId(Integer bedStatusId) {
		this.bedStatusId = bedStatusId;
	}

	public String getMortuaryBedNumber() {
		return mortuaryBedNumber;
	}

	public void setMortuaryBedNumber(String mortuaryBedNumber) {
		this.mortuaryBedNumber = mortuaryBedNumber;
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

	public String getBasePattern() {
		return basePattern;
	}

	public void setBasePattern(String basePattern) {
		this.basePattern = basePattern;
	}

	public Integer getBedCount() {
		return bedCount;
	}

	public void setBedCount(Integer bedCount) {
		this.bedCount = bedCount;
	}

	public int getMortuaryBedId() {
		return mortuaryBedId;
	}

	public void setMortuaryBedId(int mortuaryBedId) {
		this.mortuaryBedId = mortuaryBedId;
	}

	public String getMortuaryBedCode() {
		return mortuaryBedCode;
	}

	public void setMortuaryBedCode(String mortuaryBedCode) {
		this.mortuaryBedCode = mortuaryBedCode;
	}

	public String getMortuaryBedDesc() {
		return mortuaryBedDesc;
	}

	public void setMortuaryBedDesc(String mortuaryBedDesc) {
		this.mortuaryBedDesc = mortuaryBedDesc;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
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

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
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
	
	
}
