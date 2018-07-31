package com.param.opd.coversheet.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.global.model.PatientRegistration;
import com.param.opd.appointment.model.AppointmentMaster;

@NamedQueries({

		@NamedQuery(name = "GET_PATIENT_DIAGNOSIS_BY_PATIENT_ID",
				 query = "SELECT diagMapper.patientId AS patientId, "
				 		+ " diagMapper.diagnosisId AS diagnosisId, "
				 		+ " diagMapper.type AS type, "
				 		+ " diagMapper.remark AS remark, "
				 		+ " diagMaster.isNotifiable AS isNotifiable , "
				 		+ " diagMaster.isComorbidity AS isComorbidity,"
				 		+ " diagMaster.code AS code,"
				 		+ " diagMaster.diagnosisName AS diagnosisName "
				+ " FROM DiagnosisPatientAppointmentMapper diagMapper "
				+ " INNER JOIN diagMapper.diagnosisMaster diagMaster "
				+ " WHERE  diagMapper.patientId=:patientId AND diagMapper.unitId=:unitId AND diagMapper.organizationId=:orgId") })

@Entity 

@Table(name = "t_patient_diagnosis_appo_mapper", schema = "patient")
@SequenceGenerator(name = "patient_diagnosis_appo_mapper_seq", sequenceName = "patient.patient_diagnosis_appo_mapper_seq", allocationSize = 1)
public class DiagnosisPatientAppointmentMapper {

	@Id
	@Column(name = "patient_diagnosis_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_diagnosis_appo_mapper_seq")
	private int patientDiagnosisId;

	@Column(name = "encounter_id")
	private Integer encounterId;

	@Column(name = "diagnosis_id")
	private Integer diagnosisId;

	@Column(name = "patient_id")
	private Integer patientId;

	@Column(name = "type")
	private Integer type;

	@Column(name = "remark")
	private String remark;

	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "organization_id")
	private Integer organizationId;

	@Column(name = "status")
	private char status;

	@Column(name = "created_by")
	private Integer created_by;

	@Column(name = "created_date")
	private Date created_date;

	@Column(name = "updated_by")
	private Integer updated_by;

	@Column(name = "updated_date")
	private Date updated_date;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "encounter_id", insertable = false, nullable = false, updatable = false)
	private EncounterMaster encounterMaster;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diagnosis_id", insertable = false, nullable = false, updatable = false)
	private DiagnosisMaster diagnosisMaster;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id", insertable = false, nullable = false, updatable = false)
	private PatientRegistration patientRegistration;

	

	public Integer getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}

	public Integer getDiagnosisId() {
		return diagnosisId;
	}

	public void setDiagnosisId(Integer diagnosisId) {
		this.diagnosisId = diagnosisId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
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

	

	public EncounterMaster getEncounterMaster() {
		return encounterMaster;
	}

	public void setEncounterMaster(EncounterMaster encounterMaster) {
		this.encounterMaster = encounterMaster;
	}

	public DiagnosisMaster getDiagnosisMaster() {
		return diagnosisMaster;
	}

	public void setDiagnosisMaster(DiagnosisMaster diagnosisMaster) {
		this.diagnosisMaster = diagnosisMaster;
	}

	public PatientRegistration getPatientRegistration() {
		return patientRegistration;
	}

	public void setPatientRegistration(PatientRegistration patientRegistration) {
		this.patientRegistration = patientRegistration;
	}

	public int getPatientDiagnosisId() {
		return patientDiagnosisId;
	}

	public void setPatientDiagnosisId(int patientDiagnosisId) {
		this.patientDiagnosisId = patientDiagnosisId;
	}

}
