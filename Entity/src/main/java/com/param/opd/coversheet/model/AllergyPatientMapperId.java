package com.param.opd.coversheet.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AllergyPatientMapperId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "allergy_id")
	private Integer allergyId;
	
	@Column(name = "patient_id")
	private Integer patientId;
	
	@Column(name = "allergy_type_id")
	private Integer allergyTypeId;

	public Integer getAllergyId() {
		return allergyId;
	}

	public void setAllergyId(Integer allergyId) {
		this.allergyId = allergyId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getAllergyTypeId() {
		return allergyTypeId;
	}

	public void setAllergyTypeId(Integer allergyTypeId) {
		this.allergyTypeId = allergyTypeId;
	}
	
	
}
