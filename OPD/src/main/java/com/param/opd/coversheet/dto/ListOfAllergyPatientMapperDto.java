package com.param.opd.coversheet.dto;

public class ListOfAllergyPatientMapperDto {

	private Integer allergyPatientMapperId;
	private Integer allergyId;
	private Integer allergyTypeId;
	private String type;
	private String natureOfReaction;
	private Integer severityId;
	private String remark;
	private Integer month;
	private Integer year;
	private char isReviewedFlag;
	private char isEnterInErrorStatus;
	private char isNoKnownAllergies;
	
	public Integer getAllergyPatientMapperId() {
		return allergyPatientMapperId;
	}
	public void setAllergyPatientMapperId(Integer allergyPatientMapperId) {
		this.allergyPatientMapperId = allergyPatientMapperId;
	}
	
	public char getIsNoKnownAllergies() {
		return isNoKnownAllergies;
	}
	public void setIsNoKnownAllergies(char isNoKnownAllergies) {
		this.isNoKnownAllergies = isNoKnownAllergies;
	}
	
	public Integer getAllergyId() {
		return allergyId;
	}
	public void setAllergyId(Integer allergyId) {
		this.allergyId = allergyId;
	}
	public Integer getAllergyTypeId() {
		return allergyTypeId;
	}
	public void setAllergyTypeId(Integer allergyTypeId) {
		this.allergyTypeId = allergyTypeId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNatureOfReaction() {
		return natureOfReaction;
	}
	public void setNatureOfReaction(String natureOfReaction) {
		this.natureOfReaction = natureOfReaction;
	}
	public Integer getSeverityId() {
		return severityId;
	}
	public void setSeverityId(Integer severityId) {
		this.severityId = severityId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	
	
}
