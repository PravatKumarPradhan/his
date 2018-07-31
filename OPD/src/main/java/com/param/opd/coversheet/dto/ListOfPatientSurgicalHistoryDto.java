package com.param.opd.coversheet.dto;

public class ListOfPatientSurgicalHistoryDto {

	private String remark;
	private char isNoSignificantStatus;
	private Integer month;
	private Integer year;
	private Integer surgeryId;
	private String surgenName;
	private char isReviewedFlag;
	private char isEnterInErrorStatus;
	private Integer surgicalHistoryDetailsId;
	
	
	
	
	public char getIsReviewedFlag() {
		return isReviewedFlag;
	}
	public void setIsReviewedFlag(char isReviewedFlag) {
		this.isReviewedFlag = isReviewedFlag;
	}
	public char getIsEnterInErrorStatus() {
		return isEnterInErrorStatus;
	}
	public void setIsEnterInErrorStatus(char isEnterInErrorStatus) {
		this.isEnterInErrorStatus = isEnterInErrorStatus;
	}
	public Integer getSurgicalHistoryDetailsId() {
		return surgicalHistoryDetailsId;
	}
	public void setSurgicalHistoryDetailsId(Integer surgicalHistoryDetailsId) {
		this.surgicalHistoryDetailsId = surgicalHistoryDetailsId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public char getIsNoSignificantStatus() {
		return isNoSignificantStatus;
	}
	public void setIsNoSignificantStatus(char isNoSignificantStatus) {
		this.isNoSignificantStatus = isNoSignificantStatus;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getSurgeryId() {
		return surgeryId;
	}
	public void setSurgeryId(Integer surgeryId) {
		this.surgeryId = surgeryId;
	}
	public String getSurgenName() {
		return surgenName;
	}
	public void setSurgenName(String surgenName) {
		this.surgenName = surgenName;
	}
	
	
}
