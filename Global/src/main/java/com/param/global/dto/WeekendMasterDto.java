package com.param.global.dto;

import java.time.LocalTime;

import com.param.global.common.GlobalCommonDateUtils;

public class WeekendMasterDto {
	
	private Integer weekendId;
	
	private Integer unitId;
	
	private Integer organizationId;
	
	private String weekendDate;
	
	private Integer dayId;
	
	private char isHalfDay;
	
	private LocalTime fromTime;
	
	private LocalTime toTime;
	
	private Integer createdBy;
	
	private String createdDate;
	
	private Integer updatedBy;
	
	private String updatedDate;
	
	private char status;
	
	private String year;
	
	private String day;
	
	private String fromTimeString;
	
	private String toTimeString;
	
	public String getFromTimeString() {
		return fromTimeString;
	}

	public void setFromTimeString(String fromTimeString) {
		this.fromTimeString = fromTimeString;
	}

	public String getToTimeString() {
		return toTimeString;
	}

	public void setToTimeString(String toTimeString) {
		this.toTimeString = toTimeString;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getWeekendId() {
		return weekendId;
	}

	public void setWeekendId(Integer weekendId) {
		this.weekendId = weekendId;
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

	public String getWeekendDate() {
		return weekendDate;
	}

	public void setWeekendDate(String weekendDate) {
		this.weekendDate = weekendDate;
	}

	public Integer getDayId() {
		return dayId;
	}

	public void setDayId(Integer dayId) {
		this.dayId = dayId;
	}

	public char getIsHalfDay() {
		return isHalfDay;
	}

	public void setIsHalfDay(char isHalfDay) {
		this.isHalfDay = isHalfDay;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
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

	public LocalTime getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		if(!fromTime.equals("")){
			this.fromTime = GlobalCommonDateUtils.getLocalTime(fromTime);
		}else{
			this.fromTime = GlobalCommonDateUtils.getLocalTime("00:00");
		}
	}

	public LocalTime getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		if(!toTime.equals("")){
			this.toTime = GlobalCommonDateUtils.getLocalTime(toTime);
		}else{
			this.toTime = GlobalCommonDateUtils.getLocalTime("00:00");
		}
	}


}
