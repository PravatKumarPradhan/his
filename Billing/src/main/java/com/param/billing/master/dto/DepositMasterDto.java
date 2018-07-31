package com.param.billing.master.dto;

public class DepositMasterDto {
	private Integer depositMasterId;
	private Integer orgId;
	private Integer unitId;
	private String depositNo;
	private String depositDate;
	private Integer payeeId;
	private Integer patientId;
	private Integer visitTypeId;
	private Integer visitAdmId;
	private Integer admNoteId;
	private Integer depositTypeId;
	private double depositAmount;
	private double availableDeposit;
	private double consumedDeposit;
	private String receivedFrom;
	private String identificationNo;
	private char status;
	private String createdDate;
	private Integer createdBy;
	private String updatedDate;
	private Integer updatedBy;
	private String remarks;
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Integer getDepositMasterId() {
		return depositMasterId;
	}
	public void setDepositMasterId(Integer depositMasterId) {
		this.depositMasterId = depositMasterId;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	public String getDepositNo() {
		return depositNo;
	}
	public void setDepositNo(String depositNo) {
		this.depositNo = depositNo;
	}
	public String getDepositDate() {
		return depositDate;
	}
	public void setDepositDate(String depositDate) {
		this.depositDate = depositDate;
	}
	public Integer getPayeeId() {
		return payeeId;
	}
	public void setPayeeId(Integer payeeId) {
		this.payeeId = payeeId;
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public Integer getVisitTypeId() {
		return visitTypeId;
	}
	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}
	public Integer getVisitAdmId() {
		return visitAdmId;
	}
	public void setVisitAdmId(Integer visitAdmId) {
		this.visitAdmId = visitAdmId;
	}
	public Integer getAdmNoteId() {
		return admNoteId;
	}
	public void setAdmNoteId(Integer admNoteId) {
		this.admNoteId = admNoteId;
	}
	public Integer getDepositTypeId() {
		return depositTypeId;
	}
	public void setDepositTypeId(Integer depositTypeId) {
		this.depositTypeId = depositTypeId;
	}
	public double getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(double depositAmount) {
		this.depositAmount = depositAmount;
	}
	public double getAvailableDeposit() {
		return availableDeposit;
	}
	public void setAvailableDeposit(double availableDeposit) {
		this.availableDeposit = availableDeposit;
	}
	public double getConsumedDeposit() {
		return consumedDeposit;
	}
	public void setConsumedDeposit(double consumedDeposit) {
		this.consumedDeposit = consumedDeposit;
	}
	public String getReceivedFrom() {
		return receivedFrom;
	}
	public void setReceivedFrom(String receivedFrom) {
		this.receivedFrom = receivedFrom;
	}
	public String getIdentificationNo() {
		return identificationNo;
	}
	public void setIdentificationNo(String identificationNo) {
		this.identificationNo = identificationNo;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = (status != '\u0000' ? status : 'A');
	}
}
