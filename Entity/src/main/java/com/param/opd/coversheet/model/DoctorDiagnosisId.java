package com.param.opd.coversheet.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DoctorDiagnosisId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "doctor_id")
	private Integer doctorId;
	
	@Column(name = "diagnosis_id")
	private Integer diagnosisId;

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getDiagnosisId() {
		return diagnosisId;
	}

	public void setDiagnosisId(Integer diagnosisId) {
		this.diagnosisId = diagnosisId;
	}
	
	
}
