package com.param.adt.master.global.dto;

public class ICUTypeMasterDto 
{
	private int ICUTypeMasterId;
	
	private String ICUtypeCode;
	
	private String ICUType;
	
	private char isCloseICU;
	
	private char isOpenICU;
	
	private int createdBy;
	
	private String createdDate;
	
	private char status;
	
	private Integer organizationId;
	
	private int updatedBy;

	private String updatedDate;
	
	private Integer offset;

	private Integer noOfRecordsPerPage;
	
	private Integer unitICUTypeId;
	
	private Integer icuTypeId;
	
	private Integer wardId;
	
	private Integer floorId;
	
	private String wardName;
	
	
	public Integer getWardId() {
		return wardId;
	}

	public void setWardId(Integer wardId) {
		this.wardId = wardId;
	}

	public Integer getFloorId() {
		return floorId;
	}

	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public Integer getIcuTypeId() {
		return icuTypeId;
	}

	public void setIcuTypeId(Integer icuTypeId) {
		this.icuTypeId = icuTypeId;
	}

	public Integer getUnitICUTypeId() {
		return unitICUTypeId;
	}

	public void setUnitICUTypeId(Integer unitICUTypeId) {
		this.unitICUTypeId = unitICUTypeId;
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

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public int getICUTypeMasterId() {
		return ICUTypeMasterId;
	}

	public void setICUTypeMasterId(int iCUTypeMasterId) {
		ICUTypeMasterId = iCUTypeMasterId;
	}

	public String getICUtypeCode() {
		return ICUtypeCode;
	}

	public void setICUtypeCode(String iCUtypeCode) {
		ICUtypeCode = iCUtypeCode;
	}

	public String getICUType() {
		return ICUType;
	}

	public void setICUType(String iCUType) {
		ICUType = iCUType;
	}

	public char getIsCloseICU() {
		return isCloseICU;
	}

	public void setIsCloseICU(char isCloseICU) {
		this.isCloseICU = isCloseICU;
	}

	public char getIsOpenICU() {
		return isOpenICU;
	}

	public void setIsOpenICU(char isOpenICU) {
		this.isOpenICU = isOpenICU;
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
