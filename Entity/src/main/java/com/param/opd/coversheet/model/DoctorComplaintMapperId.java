package com.param.opd.coversheet.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DoctorComplaintMapperId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "doctor_id")
	private Integer doctorId;
	
	@Column(name = "complaint_id")
	private Integer complaintId;

	
	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(Integer complaintId) {
		this.complaintId = complaintId;
	}
	
	
}
