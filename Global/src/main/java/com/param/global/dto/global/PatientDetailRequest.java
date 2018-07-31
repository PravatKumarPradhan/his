package com.param.global.dto.global;

public class PatientDetailRequest
{
	private Integer visitType;
	
	private Integer patientId;
	
	public Integer getVisitType() {
		return visitType;
	}

	public void setVisitType(Integer visitType) {
		this.visitType = visitType;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	
}