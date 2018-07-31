package com.param.opd.coversheet.dto;

public class ListOfPatientMedicalHistoryDto {

	private Integer diagnosisId;
	private Integer since;
	private String duration;
	private String remark;
	private char isNoSignificantStatus;
	private Integer encounterId;
	private char isEnterInError;
	private char isReviewedFlag;
	private Integer patientMedicalHistoryId;
	
	
	public Integer getPatientMedicalHistoryId() {
		return patientMedicalHistoryId;
	}
	public void setPatientMedicalHistoryId(Integer patientMedicalHistoryId) {
		this.patientMedicalHistoryId = patientMedicalHistoryId;
	}
	public Integer getEncounterId() {
		return encounterId;
	}
	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}
	public char getIsEnterInError() {
		return isEnterInError;
	}
	public void setIsEnterInError(char isEnterInError) {
		this.isEnterInError = isEnterInError;
	}
	public char getIsReviewedFlag() {
		return isReviewedFlag;
	}
	public void setIsReviewedFlag(char isReviewedFlag) {
		this.isReviewedFlag = isReviewedFlag;
	}
	public char getIsNoSignificantStatus() {
		return isNoSignificantStatus;
	}
	public void setIsNoSignificantStatus(char isNoSignificantStatus) {
		this.isNoSignificantStatus = isNoSignificantStatus;
	}
	public Integer getDiagnosisId() {
		return diagnosisId;
	}
	public void setDiagnosisId(Integer diagnosisId) {
		this.diagnosisId = diagnosisId;
	}
	public Integer getSince() {
		return since;
	}
	public void setSince(Integer since) {
		this.since = since;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
