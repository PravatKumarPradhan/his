package com.param.opd.coversheet.dto;

public class ListOfPatientPersonalHistoryDto {

	private String description;
	private String remark;
	private char isNoSignificantHistoryStatus;
	private char isReviewedFlag;
	private Integer personalHistoryDetailsId;
	private char isEnterInErrorStatus;
	
	
	public char getIsEnterInErrorStatus() {
		return isEnterInErrorStatus;
	}
	public void setIsEnterInErrorStatus(char isEnterInErrorStatus) {
		this.isEnterInErrorStatus = isEnterInErrorStatus;
	}
	public Integer getPersonalHistoryDetailsId() {
		return personalHistoryDetailsId;
	}
	public void setPersonalHistoryDetailsId(Integer personalHistoryDetailsId) {
		this.personalHistoryDetailsId = personalHistoryDetailsId;
	}
	public char getIsReviewedFlag() {
		return isReviewedFlag;
	}
	public void setIsReviewedFlag(char isReviewedFlag) {
		this.isReviewedFlag = isReviewedFlag;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public char getIsNoSignificantHistoryStatus() {
		return isNoSignificantHistoryStatus;
	}
	public void setIsNoSignificantHistoryStatus(char isNoSignificantHistoryStatus) {
		this.isNoSignificantHistoryStatus = isNoSignificantHistoryStatus;
	}
	

	
}
