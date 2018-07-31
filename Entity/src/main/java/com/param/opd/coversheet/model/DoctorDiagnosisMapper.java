package com.param.opd.coversheet.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.param.global.model.DoctorMaster;

@Entity
@Table(name="t_doctor_diagnosis_mapper", schema="doctor")
public class DoctorDiagnosisMapper {

	@EmbeddedId
	private DoctorDiagnosisId doctorDiagnosisId;
	
	@Column(name="status")
	private char status;
	
	@Column(name="created_by")
	private Integer created_by;
	
	@Column(name="cerated_date")
	private Date cerated_date;
	
	@Column(name="updated_by")
	private Integer updated_by;
	
	@Column(name="updated_date")
	private Date updated_date;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="organization_id")
	private Integer organizationId;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diagnosis_id", insertable = false, nullable = false, updatable = false)
	private DiagnosisMaster diagnosisMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctor_id", insertable = false, nullable = false, updatable = false)
	private DoctorMaster doctorMaster;
	
	public DoctorDiagnosisId getDoctorDiagnosisId() {
		return doctorDiagnosisId;
	}

	public void setDoctorDiagnosisId(DoctorDiagnosisId doctorDiagnosisId) {
		this.doctorDiagnosisId = doctorDiagnosisId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public Integer getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Integer created_by) {
		this.created_by = created_by;
	}

	public Date getCerated_date() {
		return cerated_date;
	}

	public void setCerated_date(Date cerated_date) {
		this.cerated_date = cerated_date;
	}

	public Integer getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(Integer updated_by) {
		this.updated_by = updated_by;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public DiagnosisMaster getDiagnosisMaster() {
		return diagnosisMaster;
	}

	public void setDiagnosisMaster(DiagnosisMaster diagnosisMaster) {
		this.diagnosisMaster = diagnosisMaster;
	}

	public DoctorMaster getDoctorMaster() {
		return doctorMaster;
	}

	public void setDoctorMaster(DoctorMaster doctorMaster) {
		this.doctorMaster = doctorMaster;
	}
	
	
}
