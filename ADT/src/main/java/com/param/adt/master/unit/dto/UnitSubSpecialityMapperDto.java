package com.param.adt.master.unit.dto;

import java.util.List;

public class UnitSubSpecialityMapperDto 
{
	private int unitSubSpecialityId;
	
	private Integer unitId;
	
	private Integer organizationId;

	private Integer specialityId;
	
	private Integer subSpecialityId;

	private char status;
	
	private int createdBy;

	private String createdDate;

	private int updatedBy;
	
	private String updatedDate;
	
	private String specialityName;
	
	private String subSpecialityName;	
	
	private String subSpecialityMasterCode;
	
	private String specialityCode;
	
	private Integer offset;
	
	private Integer noOfRecordsPerPage;
	
	private List<UnitSubSpecialityMapperDto> unitSubSpecialityMapperDtosList;
	
	private Integer subSpecialityMasterId;
	
	private List<Integer> specialityIdList;

	public List<Integer> getSpecialityIdList() {
		return specialityIdList;
	}

	public void setSpecialityIdList(List<Integer> specialityIdList) {
		this.specialityIdList = specialityIdList;
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

	public String getSpecialityName() {
		return specialityName;
	}

	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}

	public String getSubSpecialityName() {
		return subSpecialityName;
	}

	public void setSubSpecialityName(String subSpecialityName) {
		this.subSpecialityName = subSpecialityName;
	}

	public List<UnitSubSpecialityMapperDto> getUnitSubSpecialityMapperDtosList() {
		return unitSubSpecialityMapperDtosList;
	}

	public void setUnitSubSpecialityMapperDtosList(List<UnitSubSpecialityMapperDto> unitSubSpecialityMapperDtosList) {
		this.unitSubSpecialityMapperDtosList = unitSubSpecialityMapperDtosList;
	}

	public int getUnitSubSpecialityId() {
		return unitSubSpecialityId;
	}

	public void setUnitSubSpecialityId(int unitSubSpecialityId) {
		this.unitSubSpecialityId = unitSubSpecialityId;
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

	public Integer getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(Integer specialityId) {
		this.specialityId = specialityId;
	}

	public Integer getSubSpecialityId() {
		return subSpecialityId;
	}

	public void setSubSpecialityId(Integer subSpecialityId) {
		this.subSpecialityId = subSpecialityId;
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

	public Integer getSubSpecialityMasterId() {
		return subSpecialityMasterId;
	}

	public void setSubSpecialityMasterId(Integer subSpecialityMasterId) {
		this.subSpecialityMasterId = subSpecialityMasterId;
	}

	public String getSubSpecialityMasterCode() {
		return subSpecialityMasterCode;
	}

	public void setSubSpecialityMasterCode(String subSpecialityMasterCode) {
		this.subSpecialityMasterCode = subSpecialityMasterCode;
	}

	public String getSpecialityCode() {
		return specialityCode;
	}

	public void setSpecialityCode(String specialityCode) {
		this.specialityCode = specialityCode;
	}
	
	
}
