package com.param.adt.master.unit.dto;

import java.util.List;

public class UnitSpecialtyMapperDto 
{
	private int unitSpecialityMapperId;
	
	private Integer unitId;
	
	private Integer specialityId;
	
	private char status;
	
	private int createdBy;
	
	private String createdDate;

	private int updatedBy;

	private String updatedDate;
	
	private Integer organizationId;
	
	private String specialityName;
	
	private String specialityCode;
	
	private Integer offset;
	
	private Integer noOfRecordsPerPage;
	
	private Integer doctorId;
	
	/*"organizationId": 1,
	"unitId":1,
	"specialityId":28,
	"createdBy":1,
	"updatedBy":1,
	"createdDate":"10-02-2017 10:04:31",
	"updatedDate":"10-02-2017 10:04:31",
	"status":"A"*/
	
	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	private List<UnitSpecialtyMapperDto> unitSpecialtyMapperDtosList;

	
	
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

	public int getUnitSpecialityMapperId() {
		return unitSpecialityMapperId;
	}

	public void setUnitSpecialityMapperId(int unitSpecialityMapperId) {
		this.unitSpecialityMapperId = unitSpecialityMapperId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(Integer specialityId) {
		this.specialityId = specialityId;
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

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getSpecialityName() {
		return specialityName;
	}

	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}

	public String getSpecialityCode() {
		return specialityCode;
	}

	public void setSpecialityCode(String specialityCode) {
		this.specialityCode = specialityCode;
	}

	public List<UnitSpecialtyMapperDto> getUnitSpecialtyMapperDtosList() {
		return unitSpecialtyMapperDtosList;
	}

	public void setUnitSpecialtyMapperDtosList(List<UnitSpecialtyMapperDto> unitSpecialtyMapperDtosList) {
		this.unitSpecialtyMapperDtosList = unitSpecialtyMapperDtosList;
	}
	
	
	
}
