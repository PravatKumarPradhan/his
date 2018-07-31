package com.param.opd.coversheet.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DoctorClinicMapperId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name="clinic_id")
	private Integer clinicMasterId;
	
	@Column(name="doctor_id")
	private Integer doctorId;

	public Integer getClinicMasterId() {
		return clinicMasterId;
	}

	public void setClinicMasterId(Integer clinicMasterId) {
		this.clinicMasterId = clinicMasterId;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	
	
	
}
