package com.param.opd.coversheet.dto;

public class ComplaintAppointmentDetailsDto {

	private Integer diagnosisId;
	private Integer since;
	private String duration;
	private String remark;
	private char isReviewedFlag;
	private Integer complaintAppoId;
	
	
	
	public Integer getComplaintAppoId() {
		return complaintAppoId;
	}
	public void setComplaintAppoId(Integer complaintAppoId) {
		this.complaintAppoId = complaintAppoId;
	}
	public char getIsReviewedFlag() {
		return isReviewedFlag;
	}
	public void setIsReviewedFlag(char isReviewedFlag) {
		this.isReviewedFlag = isReviewedFlag;
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
