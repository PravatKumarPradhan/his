package com.param.opd.coversheet.dto;

public class ListOfPatientFamilyHistoryDto {

	private String remark;
	private char isNoFamilyHistoryStatus;
	private Integer relationId;
	private Integer diagnosisId;
	private char isReviewedFlag;
	private Integer familyHistoryId;
	private char isEnterInErrorStatus;
	
	
	public char getIsEnterInErrorStatus() {
		return isEnterInErrorStatus;
	}
	public void setIsEnterInErrorStatus(char isEnterInErrorStatus) {
		this.isEnterInErrorStatus = isEnterInErrorStatus;
	}
	public Integer getFamilyHistoryId() {
		return familyHistoryId;
	}
	public void setFamilyHistoryId(Integer familyHistoryId) {
		this.familyHistoryId = familyHistoryId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public char getIsNoFamilyHistoryStatus() {
		return isNoFamilyHistoryStatus;
	}
	public void setIsNoFamilyHistoryStatus(char isNoFamilyHistoryStatus) {
		this.isNoFamilyHistoryStatus = isNoFamilyHistoryStatus;
	}
	public Integer getRelationId() {
		return relationId;
	}
	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}
	public Integer getDiagnosisId() {
		return diagnosisId;
	}
	public void setDiagnosisId(Integer diagnosisId) {
		this.diagnosisId = diagnosisId;
	}
	public char getIsReviewedFlag() {
		return isReviewedFlag;
	}
	public void setIsReviewedFlag(char isReviewedFlag) {
		this.isReviewedFlag = isReviewedFlag;
	}
	
	
}
