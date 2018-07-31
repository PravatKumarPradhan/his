package com.param.adt.master.unit.dto;

import java.util.List;

public class UnitICUTypeMapperDto {
	private int unitICUTypeId;

	private Integer unitId;

	private Integer organizationId;

	private Integer icuTypeId;

	private char status;

	private int createdBy;

	private String createdDate;

	private int updatedBy;

	private String updatedDate;

	private String ICUType;

	private String ICUtypeCode;

	private List<UnitICUTypeMapperDto> icuTypeMapperDtosList;

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

	public String getICUType() {
		return ICUType;
	}

	public void setICUType(String iCUType) {
		ICUType = iCUType;
	}

	public String getICUtypeCode() {
		return ICUtypeCode;
	}

	public void setICUtypeCode(String iCUtypeCode) {
		ICUtypeCode = iCUtypeCode;
	}

	public List<UnitICUTypeMapperDto> getIcuTypeMapperDtosList() {
		return icuTypeMapperDtosList;
	}

	public void setIcuTypeMapperDtosList(List<UnitICUTypeMapperDto> icuTypeMapperDtosList) {
		this.icuTypeMapperDtosList = icuTypeMapperDtosList;
	}

	public int getUnitICUTypeId() {
		return unitICUTypeId;
	}

	public void setUnitICUTypeId(int unitICUTypeId) {
		this.unitICUTypeId = unitICUTypeId;
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

	public Integer getIcuTypeId() {
		return icuTypeId;
	}

	public void setIcuTypeId(Integer icuTypeId) {
		this.icuTypeId = icuTypeId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
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
