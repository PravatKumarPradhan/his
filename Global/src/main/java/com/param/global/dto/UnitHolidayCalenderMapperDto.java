package com.param.global.dto;

import java.util.List;

public class UnitHolidayCalenderMapperDto 
{
	private int unitHolidayCalenderId;

	private Integer unitId;

	private Integer organizationId;

	private Integer holidayId;

	private char status;

	private int createdBy;

	private String createdDate;
	
	private int updatedBy;

	private String updatedDate;
	
	private Integer offset;
	
	private Integer noOfRecordsPerPage;
	
	private String holidayCalenderCode;

	private String holidayCalenderDesc;

	private String holidayDate;

	private Integer dayId;
	
	private String day;
	
	private List<UnitHolidayCalenderMapperDto> unitHolidayCalenderMapperDtosList;

	public int getUnitHolidayCalenderId() {
		return unitHolidayCalenderId;
	}

	public void setUnitHolidayCalenderId(int unitHolidayCalenderId) {
		this.unitHolidayCalenderId = unitHolidayCalenderId;
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

	public Integer getHolidayId() {
		return holidayId;
	}

	public void setHolidayId(Integer holidayId) {
		this.holidayId = holidayId;
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

	public String getHolidayCalenderCode() {
		return holidayCalenderCode;
	}

	public void setHolidayCalenderCode(String holidayCalenderCode) {
		this.holidayCalenderCode = holidayCalenderCode;
	}

	public String getHolidayCalenderDesc() {
		return holidayCalenderDesc;
	}

	public void setHolidayCalenderDesc(String holidayCalenderDesc) {
		this.holidayCalenderDesc = holidayCalenderDesc;
	}

	public String getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(String holidayDate) {
		this.holidayDate = holidayDate;
	}

	public Integer getDayId() {
		return dayId;
	}

	public void setDayId(Integer dayId) {
		this.dayId = dayId;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public List<UnitHolidayCalenderMapperDto> getUnitHolidayCalenderMapperDtosList() {
		return unitHolidayCalenderMapperDtosList;
	}

	public void setUnitHolidayCalenderMapperDtosList(List<UnitHolidayCalenderMapperDto> unitHolidayCalenderMapperDtosList) {
		this.unitHolidayCalenderMapperDtosList = unitHolidayCalenderMapperDtosList;
	}
	
	
	
}
