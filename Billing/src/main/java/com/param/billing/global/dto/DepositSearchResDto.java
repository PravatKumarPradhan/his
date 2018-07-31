package com.param.billing.global.dto;

public class DepositSearchResDto {
	private String depositNo;
	private String depositDate;
	private String uhidNumber;
	private String patientName;
	private Integer depositTypeId;
	private Integer visitTypeId;
	private Double depositAmount;
	private Double consumedDeposit;
	private Double availableDeposit;
	private String visitNo;
	private Integer depositMasterId;
	
	
	public Integer getDepositMasterId() {
		return depositMasterId;
	}
	public void setDepositMasterId(Integer depositMasterId) {
		this.depositMasterId = depositMasterId;
	}
	public String getVisitNo() {
		return visitNo;
	}
	public void setVisitNo(String visitNo) {
		this.visitNo = visitNo;
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
	public String getUhidNumber() {
		return uhidNumber;
	}
	public void setUhidNumber(String uhidNumber) {
		this.uhidNumber = uhidNumber;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public Integer getDepositTypeId() {
		return depositTypeId;
	}
	public void setDepositTypeId(Integer depositTypeId) {
		this.depositTypeId = depositTypeId;
	}
	public Integer getVisitTypeId() {
		return visitTypeId;
	}
	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}
	public Double getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(Double depositAmount) {
		this.depositAmount = depositAmount;
	}
	public Double getConsumedDeposit() {
		return consumedDeposit;
	}
	public void setConsumedDeposit(Double consumedDeposit) {
		this.consumedDeposit = consumedDeposit;
	}
	public Double getAvailableDeposit() {
		return availableDeposit;
	}
	public void setAvailableDeposit(Double availableDeposit) {
		this.availableDeposit = availableDeposit;
	}
	
}
