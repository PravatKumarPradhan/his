package com.param.global.dto;

import java.util.List;

import com.param.global.model.UnitServiceMapperId;

public class UnitServiceMapperDto {
	private UnitServiceMapperId unitServiceMapperId;
	
	private Integer serviceId;
	
	private Integer specialityId;
	
	private Integer subSpecialityId;
	
	private String serviceStandardName;
	
	private String serviceStandardCode;
	
	private Integer unitId;

	private Integer orgnisationId;

	private char status;

	private Long createdDate;

	private Integer createdBy;

	private Long updatedDate;

	private Integer updatedBy;
	
	private List<UnitServiceMapperDto> listUnitServiceMapperDto;
	
	private Integer offset;
	
	private Integer recordPerPage;
	
	private String specialityName;
	
	private String subSpecialityName;
	
	private List<Integer> specialityIdList;
	
	private List<Integer> subSpecialityIdList;
	
	private List<Integer> serviceIdList;
	
	private String searchKeyword;
	
	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getOrgnisationId() {
		return orgnisationId;
	}

	public void setOrgnisationId(Integer orgnisationId) {
		this.orgnisationId = orgnisationId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public Long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Long updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public List<UnitServiceMapperDto> getListUnitServiceMapperDto() {
		return listUnitServiceMapperDto;
	}

	public void setListUnitServiceMapperDto(List<UnitServiceMapperDto> listUnitServiceMapperDto) {
		this.listUnitServiceMapperDto = listUnitServiceMapperDto;
	}

	public UnitServiceMapperId getUnitServiceMapperId() {
		return unitServiceMapperId;
	}

	public void setUnitServiceMapperId(UnitServiceMapperId unitServiceMapperId) {
		this.unitServiceMapperId = unitServiceMapperId;
	}

	public String getServiceStandardName() {
		return serviceStandardName;
	}

	public void setServiceStandardName(String serviceStandardName) {
		this.serviceStandardName = serviceStandardName;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getRecordPerPage() {
		return recordPerPage;
	}

	public void setRecordPerPage(Integer recordPerPage) {
		this.recordPerPage = recordPerPage;
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

	public List<Integer> getSpecialityIdList() {
		return specialityIdList;
	}

	public void setSpecialityIdList(List<Integer> specialityIdList) {
		this.specialityIdList = specialityIdList;
	}

	public List<Integer> getSubSpecialityIdList() {
		return subSpecialityIdList;
	}

	public void setSubSpecialityIdList(List<Integer> subSpecialityIdList) {
		this.subSpecialityIdList = subSpecialityIdList;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public List<Integer> getServiceIdList() {
		return serviceIdList;
	}

	public void setServiceIdList(List<Integer> serviceIdList) {
		this.serviceIdList = serviceIdList;
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

	public String getServiceStandardCode() {
		return serviceStandardCode;
	}

	public void setServiceStandardCode(String serviceStandardCode) {
		this.serviceStandardCode = serviceStandardCode;
	}
	
	
}
