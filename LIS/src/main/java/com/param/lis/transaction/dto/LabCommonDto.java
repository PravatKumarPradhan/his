package com.param.lis.transaction.dto;

public class LabCommonDto {
	private Integer orgId;
	private Integer orgUnitId;
	private Character currentStatus;
	private Character isOutSource;
	private Integer offset;
	private Integer noOfRecordsPerPage;
	private Integer sendToDept;
	private Integer statusId;
	private Character isArrival;
	private Integer deptId;

	
	
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public Integer getSendToDept() {
		return sendToDept;
	}
	public void setSendToDept(Integer sendToDept) {
		this.sendToDept = sendToDept;
	}
	public Character getIsArrival() {
		return isArrival;
	}
	public void setIsArrival(Character isArrival) {
		this.isArrival = isArrival;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public Integer getOrgUnitId() {
		return orgUnitId;
	}
	public void setOrgUnitId(Integer orgUnitId) {
		this.orgUnitId = orgUnitId;
	}
	public Character getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(Character currentStatus) {
		this.currentStatus = currentStatus;
	}
	public Character getIsOutSource() {
		return isOutSource;
	}
	public void setIsOutSource(Character isOutSource) {
		this.isOutSource = isOutSource;
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
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	


}
