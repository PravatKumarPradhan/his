package com.param.global.dto.global;

public class Patient {

	private Integer patientId;
	
	private String patientName;
	
	private String uhid;
	
	private String details;
	
	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getUhid() {
		return uhid;
	}

	public void setUhid(String uhid) {
		this.uhid = uhid;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Patient(Integer patientId, String patientName, String uhid, String details) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
		this.uhid = uhid;
		this.details = details;
	}

}
