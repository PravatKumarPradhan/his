package com.param.adt.master.unit.dto;

public class RoomMasterDto {

	private int roomId;

	private int unitId;

	private int bedCategoryId;

	private int bedTypeId;

	private String roomCode;

	private String roomDesc;

	private char isIsolated;

	private int noOfMaxBeds;

	private int createdBy;

	private String createdDate;

	private char status;

	private int updatedBy;

	private String updatedDate;

	private Integer organizationId;

	private Integer offset;

	private Integer noOfRecordsPerPage;

	private Integer wardId;

	private String billingBedDesc;

	private char isIsolation;

	private Integer billingBedCategoryId;
	private String wardName;
	private String bedCategoryDesc;

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

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public int getBedCategoryId() {
		return bedCategoryId;
	}

	public void setBedCategoryId(int bedCategoryId) {
		this.bedCategoryId = bedCategoryId;
	}

	public int getBedTypeId() {
		return bedTypeId;
	}

	public void setBedTypeId(int bedTypeId) {
		this.bedTypeId = bedTypeId;
	}

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public String getRoomDesc() {
		return roomDesc;
	}

	public void setRoomDesc(String roomDesc) {
		this.roomDesc = roomDesc;
	}

	public char getIsIsolated() {
		return isIsolated;
	}

	public void setIsIsolated(char isIsolated) {
		this.isIsolated = isIsolated;
	}

	public int getNoOfMaxBeds() {
		return noOfMaxBeds;
	}

	public void setNoOfMaxBeds(int noOfMaxBeds) {
		this.noOfMaxBeds = noOfMaxBeds;
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

	public Integer getWardId() {
		return wardId;
	}

	public void setWardId(Integer wardId) {
		this.wardId = wardId;
	}

	public char getIsIsolation() {
		return isIsolation;
	}

	public void setIsIsolation(char isIsolation) {
		this.isIsolation = isIsolation;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public Integer getBillingBedCategoryId() {
		return billingBedCategoryId;
	}

	public void setBillingBedCategoryId(Integer billingBedCategoryId) {
		this.billingBedCategoryId = billingBedCategoryId;
	}

	public String getBillingBedDesc() {
		return billingBedDesc;
	}

	public void setBillingBedDesc(String billingBedDesc) {
		this.billingBedDesc = billingBedDesc;
	}

	public String getBedCategoryDesc() {
		return bedCategoryDesc;
	}

	public void setBedCategoryDesc(String bedCategoryDesc) {
		this.bedCategoryDesc = bedCategoryDesc;
	}

}
