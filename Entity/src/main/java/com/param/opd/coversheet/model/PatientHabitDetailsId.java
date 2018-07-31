package com.param.opd.coversheet.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PatientHabitDetailsId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "patient_id")
	private Integer patientId;
	
	@Column(name = "habit_id")
	private Integer habitId;
	
	@Column(name="status")
	private char status;

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getHabitId() {
		return habitId;
	}

	public void setHabitId(Integer habitId) {
		this.habitId = habitId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}


}
