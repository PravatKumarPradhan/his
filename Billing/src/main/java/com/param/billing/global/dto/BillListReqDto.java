package com.param.billing.global.dto;

public class BillListReqDto {
	private String fromDate;
	private String toDate;
	private Integer visitTypeId;
	private String billNo;
	private String visitNo;
	private Integer billStatusId;
	private Integer patientId;
	private Integer orgId;
	private Integer unitId;
	private Character isOtcReg;
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
	public Integer getVisitTypeId() {
		return visitTypeId;
	}
	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getVisitNo() {
		return visitNo;
	}
	public void setVisitNo(String visitNo) {
		this.visitNo = visitNo;
	}
	public Integer getBillStatusId() {
		return billStatusId;
	}
	public void setBillStatusId(Integer billStatusId) {
		this.billStatusId = billStatusId;
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
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
	public Character getIsOtcReg() {
		return isOtcReg;
	}
	public void setIsOtcReg(Character isOtcReg) {
		this.isOtcReg = isOtcReg;
	}
}
