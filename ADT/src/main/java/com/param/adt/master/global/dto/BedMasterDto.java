package com.param.adt.master.global.dto;

import java.math.BigInteger;

public class BedMasterDto {
	private int bedId;

	private int unitId;

	private int floorId;

	private String floorName;

	private int wardId;

	private String wardName;

	private int roomId;

	private int bedCategoryId;

	private String bedCategoryDesc;

	private Integer billingBedCategoryId;

	private String bedCode;

	private String bedNumber;

	private Integer bedStatusId;
	
	private Integer nursingStationId;

	private char isVirtual;

	private int createdBy;

	private String createdDate;

	private char status;

	private int updatedBy;

	private String updatedDate;

	private Integer organizationId;
	
	private String billingBedDesc;
	
	private Integer transferTypeId;
	
	private Integer housekeepingId;

	private Integer priorityId;

	private Integer assignedPersonId;
	
	private int housekeepingLogId;

	private String nursingStationDesc;
	
	private String priorityDesc;
	
	private Integer housekeepingStatusId;
	
	private String housekeepingStatusDesc;
	
	private String userName;
	
	private Integer rejectionReasonId;

	private String note;

	private String reasonDesc;
	
	private Integer bedCategoryWaitingListId;
	
	private Integer admissionNoteId;

	private String doa;
	
	private BigInteger waitListNumber;
	
	
	public Integer getBedCategoryWaitingListId() {
		return bedCategoryWaitingListId;
	}

	public void setBedCategoryWaitingListId(Integer bedCategoryWaitingListId) {
		this.bedCategoryWaitingListId = bedCategoryWaitingListId;
	}

	public Integer getAdmissionNoteId() {
		return admissionNoteId;
	}

	public void setAdmissionNoteId(Integer admissionNoteId) {
		this.admissionNoteId = admissionNoteId;
	}

	public String getDoa() {
		return doa;
	}

	public void setDoa(String doa) {
		this.doa = doa;
	}

	public BigInteger getWaitListNumber() {
		return waitListNumber;
	}

	public void setWaitListNumber(BigInteger waitListNumber) {
		this.waitListNumber = waitListNumber;
	}

	public String getReasonDesc() {
		return reasonDesc;
	}

	public void setReasonDesc(String reasonDesc) {
		this.reasonDesc = reasonDesc;
	}

	public Integer getRejectionReasonId() {
		return rejectionReasonId;
	}

	public void setRejectionReasonId(Integer rejectionReasonId) {
		this.rejectionReasonId = rejectionReasonId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getHousekeepingId() {
		return housekeepingId;
	}

	public String getNursingStationDesc() {
		return nursingStationDesc;
	}

	public void setNursingStationDesc(String nursingStationDesc) {
		this.nursingStationDesc = nursingStationDesc;
	}

	public String getPriorityDesc() {
		return priorityDesc;
	}

	public void setPriorityDesc(String priorityDesc) {
		this.priorityDesc = priorityDesc;
	}

	public Integer getHousekeepingStatusId() {
		return housekeepingStatusId;
	}

	public void setHousekeepingStatusId(Integer housekeepingStatusId) {
		this.housekeepingStatusId = housekeepingStatusId;
	}

	public String getHousekeepingStatusDesc() {
		return housekeepingStatusDesc;
	}

	public void setHousekeepingStatusDesc(String housekeepingStatusDesc) {
		this.housekeepingStatusDesc = housekeepingStatusDesc;
	}

	public void setHousekeepingId(Integer housekeepingId) {
		this.housekeepingId = housekeepingId;
	}

	public Integer getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(Integer priorityId) {
		this.priorityId = priorityId;
	}

	public Integer getAssignedPersonId() {
		return assignedPersonId;
	}

	public void setAssignedPersonId(Integer assignedPersonId) {
		this.assignedPersonId = assignedPersonId;
	}

	public int getHousekeepingLogId() {
		return housekeepingLogId;
	}

	public void setHousekeepingLogId(int housekeepingLogId) {
		this.housekeepingLogId = housekeepingLogId;
	}

	public Integer getTransferTypeId() {
		return transferTypeId;
	}

	public void setTransferTypeId(Integer transferTypeId) {
		this.transferTypeId = transferTypeId;
	}

	public String getBillingBedDesc() {
		return billingBedDesc;
	}

	public void setBillingBedDesc(String billingBedDesc) {
		this.billingBedDesc = billingBedDesc;
	}

	public Integer getBedStatusId() {
		return bedStatusId;
	}

	public void setBedStatusId(Integer bedStatusId) {
		this.bedStatusId = bedStatusId;
	}

	public Integer getNursingStationId() {
		return nursingStationId;
	}

	public void setNursingStationId(Integer nursingStationId) {
		this.nursingStationId = nursingStationId;
	}

	public Integer getBillingBedCategoryId() {
		return billingBedCategoryId;
	}

	public void setBillingBedCategoryId(Integer billingBedCategoryId) {
		this.billingBedCategoryId = billingBedCategoryId;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public String getBedCategoryDesc() {
		return bedCategoryDesc;
	}

	public void setBedCategoryDesc(String bedCategoryDesc) {
		this.bedCategoryDesc = bedCategoryDesc;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
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

	public int getBedId() {
		return bedId;
	}

	public void setBedId(int bedId) {
		this.bedId = bedId;
	}

	public String getBedCode() {
		return bedCode;
	}

	public void setBedCode(String bedCode) {
		this.bedCode = bedCode;
	}

	public String getBedNumber() {
		return bedNumber;
	}

	public void setBedNumber(String bedNumber) {
		this.bedNumber = bedNumber;
	}

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public int getFloorId() {
		return floorId;
	}

	public void setFloorId(int floorId) {
		this.floorId = floorId;
	}

	public int getWardId() {
		return wardId;
	}

	public void setWardId(int wardId) {
		this.wardId = wardId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getBedCategoryId() {
		return bedCategoryId;
	}

	public void setBedCategoryId(int bedCategoryId) {
		this.bedCategoryId = bedCategoryId;
	}

	
	public char getIsVirtual() {
		return isVirtual;
	}

	public void setIsVirtual(char isVirtual) {
		this.isVirtual = isVirtual;
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

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

}
