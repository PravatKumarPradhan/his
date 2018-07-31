package com.param.global.dto;

import java.util.List;

public class SlotBlockUnblockRequestDto {
	
	private List<Integer> listDoctorId;
	private List<Integer> listSpecialityId;
	private List<Integer> listModalityId;
	private List<Integer> listSubSpecialityId;
	private String fromDate;
	private String toDate;
	private String fromTime;
	private String toTime;
	private Integer organisationId;
	private Integer unitId;
	private Integer slotTypeId;
	private Integer isBlocked;
	private Integer offset;
	private Integer count;
	private Integer noOfRecordsPerPage;
	
	public List<Integer> getListModalityId() {
		return listModalityId;
	}
	public void setListModalityId(List<Integer> listModalityId) {
		this.listModalityId = listModalityId;
	}
	public List<Integer> getListSubSpecialityId() {
		return listSubSpecialityId;
	}
	public void setListSubSpecialityId(List<Integer> listSubSpecialityId) {
		this.listSubSpecialityId = listSubSpecialityId;
	}
	public List<Integer> getListDoctorId() {
		return listDoctorId;
	}
	public void setListDoctorId(List<Integer> listDoctorId) {
		this.listDoctorId = listDoctorId;
	}
	public List<Integer> getListSpecialityId() {
		return listSpecialityId;
	}
	public void setListSpecialityId(List<Integer> listSpecialityId) {
		this.listSpecialityId = listSpecialityId;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getFromTime() {
		return fromTime;
	}
	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}
	public String getToTime() {
		return toTime;
	}
	public void setToTime(String toTime) {
		this.toTime = toTime;
	}
	public Integer getOrganisationId() {
		return organisationId;
	}
	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	public Integer getSlotTypeId() {
		return slotTypeId;
	}
	public void setSlotTypeId(Integer slotTypeId) {
		this.slotTypeId = slotTypeId;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getNoOfRecordsPerPage() {
		return noOfRecordsPerPage;
	}
	public void setNoOfRecordsPerPage(Integer noOfRecordsPerPage) {
		this.noOfRecordsPerPage = noOfRecordsPerPage;
	}
	public Integer getIsBlocked() {
		return isBlocked;
	}
	public void setIsBlocked(Integer isBlocked) {
		this.isBlocked = isBlocked;
	}
	
}
