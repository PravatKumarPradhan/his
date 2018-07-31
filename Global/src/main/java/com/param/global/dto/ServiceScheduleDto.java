package com.param.global.dto;

import java.time.LocalTime;

import com.param.global.common.GlobalCommonDateUtils;

public class ServiceScheduleDto {

	private int scheduleId;
	
	private Integer organizationId;
	
	private Integer unitId;

	private Integer groupId;
	
	private LocalTime fromTime;
	
	private LocalTime toTime;
	
	private char availableStatus;
	
	private Integer equipmentId;
	
	private Integer admissionId;
	
	private String scheduleDate;
	
	private String status;

	private int createdBy;

	private String createdDate;

	private int updatedBy;

	private String updatedDate;

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
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

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public LocalTime getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		if(!fromTime.equals("")){
			   this.fromTime = GlobalCommonDateUtils.getLocalTime(fromTime);
			   //System.out.println("timeslot="+fromTime);
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
			   //System.out.println("timeslot="+fromTime);
			  }else{
			   this.toTime = GlobalCommonDateUtils.getLocalTime("00:00");
			  }
	}

	public char getAvailableStatus() {
		return availableStatus;
	}

	public void setAvailableStatus(char availableStatus) {
		this.availableStatus = availableStatus;
	}

	public Integer getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public Integer getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(Integer admissionId) {
		this.admissionId = admissionId;
	}

	public String getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
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
