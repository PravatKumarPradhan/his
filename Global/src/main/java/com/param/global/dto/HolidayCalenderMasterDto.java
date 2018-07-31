package com.param.global.dto;

public class HolidayCalenderMasterDto {
	private int holidayCalenderId;
	
	private Integer unitHolidayCalenderId;

	private String holidayCalenderCode;

	private String holidayCalenderDesc;
	
	private Integer holidayId;

	private String holidayDate;

	private Integer dayId;

	private Integer organizationId;

	private int createdBy;

	private String createdDate;

	private char status;

	private int updatedBy;

	private String updatedDate;

	private String organizationName;

	private String day;
	
	private Integer offset;

	private Integer noOfRecordsPerPage;
	
	public Integer getUnitHolidayCalenderId() {
		return unitHolidayCalenderId;
	}

	public void setUnitHolidayCalenderId(Integer unitHolidayCalenderId) {
		this.unitHolidayCalenderId = unitHolidayCalenderId;
	}

	public Integer getHolidayId() {
		return holidayId;
	}

	public void setHolidayId(Integer holidayId) {
		this.holidayId = holidayId;
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

	public Integer getDayId() {
		return dayId;
	}

	public void setDayId(Integer dayId) {
		this.dayId = dayId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getHolidayDate() {
		return holidayDate;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
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

	public int getHolidayCalenderId() {
		return holidayCalenderId;
	}

	public void setHolidayCalenderId(int holidayCalenderId) {
		this.holidayCalenderId = holidayCalenderId;
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

	public void setHolidayDate(String holidayDate) {
		this.holidayDate = holidayDate;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

}
