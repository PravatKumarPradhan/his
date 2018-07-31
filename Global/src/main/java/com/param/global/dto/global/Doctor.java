package com.param.global.dto.global;

public class Doctor {

	private Integer id;
	
	private String doctorName;
	
	private String speciality;
	
	private String details;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public Doctor(Integer id, String doctorName, String speciality, String details) {
		super();
		this.id = id;
		this.doctorName = doctorName;
		this.speciality = speciality;
		this.details = details;
	}
	
}
