package com.param.opd.coversheet.dto;

public class ListImmunizationHistoryMapperDto {

	private String immunizationDate;
	private Integer drugId;
	private char isAdministeredStatus;
	private char isReviewedFlag;
	private Integer immunizationHistoryMapperId;
	
	private String drugName;
	
	
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public char getIsReviewedFlag() {
		return isReviewedFlag;
	}
	public void setIsReviewedFlag(char isReviewedFlag) {
		this.isReviewedFlag = isReviewedFlag;
	}
	public Integer getImmunizationHistoryMapperId() {
		return immunizationHistoryMapperId;
	}
	public void setImmunizationHistoryMapperId(Integer immunizationHistoryMapperId) {
		this.immunizationHistoryMapperId = immunizationHistoryMapperId;
	}
	public String getImmunizationDate() {
		return immunizationDate;
	}
	public void setImmunizationDate(String immunizationDate) {
		this.immunizationDate = immunizationDate;
	}
	public Integer getDrugId() {
		return drugId;
	}
	public void setDrugId(Integer drugId) {
		this.drugId = drugId;
	}
	
	public char getIsAdministeredStatus() {
		return isAdministeredStatus;
	}
	public void setIsAdministeredStatus(char isAdministeredStatus) {
		this.isAdministeredStatus = isAdministeredStatus;
	}
	
	
}
