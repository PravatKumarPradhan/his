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

	@NamedQuery(name="GET_PATIENT_MEDICAL_HISTORY",
				query=" SELECT 		patientMedicalHist.patientMedicalHistoryId as patientMedicalHistoryId, patientMedicalHist.encounterId as encounterId,"
					+ "				patientMedicalHist.diagnosisId as diagnosisId, patientMedicalHist.patientId as patientId, patientMedicalHist.since as  since,"
					+ "				patientMedicalHist.duration as duration, patientMedicalHist.remark as remark,"
					+ "				patientMedicalHist.isNoSignificantStatus as isNoSignificantStatus, patientMedicalHist.unitId as unitId,"
					+ " 			patientMedicalHist.organizationId as organizationId, patientMedicalHist.status as status,"
					+ "				patientMedicalHist.created_by as created_by, patientMedicalHist.updated_by as updated_by,"
					+ "				to_char(patientMedicalHist.created_date,'DD-MM-YYYY') as created_date,"
					+ "				to_char(patientMedicalHist.updated_date,'DD-MM-YYYY') as updated_date,"
					+ "      		diagnoMst.code as code, diagnoMst.diagnosisName as diagnosisName,"
					+ "				patientMedicalHist.isEnterInError as isEnterInError, patientMedicalHist.isReviewedFlag as isReviewedFlag,"
					+ "				patientMedicalHist.roleId as roleId "
					+ " FROM 		PatientMedicalHistory patientMedicalHist "
					+ " INNER JOIN 	patientMedicalHist.diagnosisMaster diagnoMst "
					+ " WHERE 		patientMedicalHist.patientId=:patientId "
					+ " AND 		patientMedicalHist.status= 'A' "
					+ " AND 		patientMedicalHist.unitId=:unitId "
					+ " AND			patientMedicalHist.organizationId=:organizationId"
					+ " AND			patientMedicalHist.encounterId!=:encounterId"
					+ " ORDER BY 	patientMedicalHist.patientMedicalHistoryId desc"
			),
			
			@NamedQuery(name="GET_PATIENT_MEDICAL_HISTORY_BY_ROLE_ID",
			query=" SELECT 		patientMedicalHist.patientMedicalHistoryId as patientMedicalHistoryId, patientMedicalHist.encounterId as encounterId,"
				+ "				patientMedicalHist.diagnosisId as diagnosisId, patientMedicalHist.patientId as patientId, patientMedicalHist.since as  since,"
				+ "				patientMedicalHist.duration as duration, patientMedicalHist.remark as remark,"
				+ "				patientMedicalHist.isNoSignificantStatus as isNoSignificantStatus, patientMedicalHist.unitId as unitId,"
				+ " 			patientMedicalHist.organizationId as organizationId, patientMedicalHist.status as status,"
				+ "				patientMedicalHist.created_by as created_by, patientMedicalHist.updated_by as updated_by,"
				+ "				to_char(patientMedicalHist.created_date,'DD-MM-YYYY') as created_date,"
				+ "				to_char(patientMedicalHist.updated_date,'DD-MM-YYYY') as updated_date,"
				+ "      		diagnoMst.code as code, diagnoMst.diagnosisName as diagnosisName,"
				+ "				patientMedicalHist.isEnterInError as isEnterInError, patientMedicalHist.isReviewedFlag as isReviewedFlag,"
				+ "				patientMedicalHist.roleId as roleId "
				+ " FROM 		PatientMedicalHistory patientMedicalHist "
				+ " INNER JOIN 	patientMedicalHist.diagnosisMaster diagnoMst "
				+ " WHERE 		patientMedicalHist.patientId=:patientId "
				+ " AND 		patientMedicalHist.status= 'A' "
				+ " AND 		patientMedicalHist.unitId=:unitId "
				+ " AND			patientMedicalHist.organizationId=:organizationId"
				+ " AND			patientMedicalHist.encounterId!=:encounterId"
				+ " AND			patientMedicalHist.roleId=:roleId"
				+ " ORDER BY 	patientMedicalHist.patientMedicalHistoryId desc"
		),
		@NamedQuery(name="GET_CURRENT_PATIENT_MEDICAL_HISTORY",
		query=" SELECT 		patientMedicalHist.patientMedicalHistoryId as patientMedicalHistoryId, patientMedicalHist.encounterId as encounterId,"
			+ "				patientMedicalHist.diagnosisId as diagnosisId, patientMedicalHist.patientId as patientId, patientMedicalHist.since as  since,"
			+ "				patientMedicalHist.duration as duration, patientMedicalHist.remark as remark,"
			+ "				patientMedicalHist.isNoSignificantStatus as isNoSignificantStatus, patientMedicalHist.unitId as unitId,"
			+ " 			patientMedicalHist.organizationId as organizationId, patientMedicalHist.status as status,"
			+ "				patientMedicalHist.created_by as created_by, patientMedicalHist.updated_by as updated_by,"
			+ "				to_char(patientMedicalHist.created_date,'DD-MM-YYYY') as created_date,"
			+ "				to_char(patientMedicalHist.updated_date,'DD-MM-YYYY') as updated_date,"
			+ "      		diagnoMst.code as code, diagnoMst.diagnosisName as diagnosisName,"
			+ "				patientMedicalHist.isEnterInError as isEnterInError, patientMedicalHist.isReviewedFlag as isReviewedFlag,"
			+ "				patientMedicalHist.roleId as roleId "
			+ " FROM 		PatientMedicalHistory patientMedicalHist "
			+ " INNER JOIN 	patientMedicalHist.diagnosisMaster diagnoMst "
			+ " WHERE 		patientMedicalHist.patientId=:patientId "
			+ " AND 		patientMedicalHist.status= 'A' "
			+ " AND 		patientMedicalHist.unitId=:unitId "
			+ " AND			patientMedicalHist.organizationId=:organizationId"
			+ " AND			patientMedicalHist.encounterId=:encounterId"
			+ " ORDER BY 	patientMedicalHist.patientMedicalHistoryId desc"
	),
	
	@NamedQuery(name="GET_CURRENT_PATIENT_MEDICAL_HISTORY_BY_ROLE_ID",
	query=" SELECT 		patientMedicalHist.patientMedicalHistoryId as patientMedicalHistoryId, patientMedicalHist.encounterId as encounterId,"
		+ "				patientMedicalHist.diagnosisId as diagnosisId, patientMedicalHist.patientId as patientId, patientMedicalHist.since as  since,"
		+ "				patientMedicalHist.duration as duration, patientMedicalHist.remark as remark,"
		+ "				patientMedicalHist.isNoSignificantStatus as isNoSignificantStatus, patientMedicalHist.unitId as unitId,"
		+ " 			patientMedicalHist.organizationId as organizationId, patientMedicalHist.status as status,"
		+ "				patientMedicalHist.created_by as created_by, patientMedicalHist.updated_by as updated_by,"
		+ "				to_char(patientMedicalHist.created_date,'DD-MM-YYYY') as created_date,"
		+ "				to_char(patientMedicalHist.updated_date,'DD-MM-YYYY') as updated_date,"
		+ "      		diagnoMst.code as code, diagnoMst.diagnosisName as diagnosisName,"
		+ "				patientMedicalHist.isEnterInError as isEnterInError, patientMedicalHist.isReviewedFlag as isReviewedFlag,"
		+ "				patientMedicalHist.roleId as roleId "
		+ " FROM 		PatientMedicalHistory patientMedicalHist "
		+ " INNER JOIN 	patientMedicalHist.diagnosisMaster diagnoMst "
		+ " WHERE 		patientMedicalHist.patientId=:patientId "
		+ " AND 		patientMedicalHist.status= 'A' "
		+ " AND 		patientMedicalHist.unitId=:unitId "
		+ " AND			patientMedicalHist.organizationId=:organizationId"
		+ " AND			patientMedicalHist.encounterId=:encounterId"
		+ " AND			patientMedicalHist.roleId=:roleId"
		+ " ORDER BY 	patientMedicalHist.patientMedicalHistoryId desc"
)
})



@Entity
@Table(name = "t_patient_medical_history", schema = "patient")
@SequenceGenerator(name="t_patient_medical_history_seq", sequenceName="patient.t_patient_medical_history_seq", allocationSize=1)
public class PatientMedicalHistory {

	@Id
	@Column(name="patient_medical_history_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_patient_medical_history_seq")
	private Integer patientMedicalHistoryId;
	
	
	@Column(name="diagnosis_id")
	private Integer diagnosisId;
	
	@Column(name="encounter_id")
	private Integer encounterId;
	
	@Column(name="role_id")
	private Integer roleId;
	
	@Column(name="is_enter_in_error")
	private char isEnterInError;
	
	@Column(name="is_reviewed_flag")
	private char isReviewedFlag;
	
	@Column(name="patient_id")
	private Integer patientId;
	
	@Column(name="since")
	private Integer since;
	
	@Column(name="duration")
	private String duration;
	
	@Column(name="remark")
	private String remark;
	
	@Column(name="is_no_significant_status")
	private char isNoSignificantStatus;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="status")
	private char status;
	
	@Column(name="created_by")
	private Integer created_by;
	
	@Column(name="created_date")
	private Date created_date;
	
	@Column(name="updated_by")
	private Integer updated_by;
	
	@Column(name="updated_date")
	private Date updated_date;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id", insertable = false, updatable = false)
	private PatientRegistration patientRegistration;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diagnosis_id", insertable = false, nullable = false, updatable = false)
	private DiagnosisMaster diagnosisMaster;

	
	
	public Integer getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}

	public char getIsEnterInError() {
		return isEnterInError;
	}

	public void setIsEnterInError(char isEnterInError) {
		this.isEnterInError = isEnterInError;
	}

	public char getIsReviewedFlag() {
		return isReviewedFlag;
	}

	public void setIsReviewedFlag(char isReviewedFlag) {
		this.isReviewedFlag = isReviewedFlag;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public char getIsNoSignificantStatus() {
		return isNoSignificantStatus;
	}

	public void setIsNoSignificantStatus(char isNoSignificantStatus) {
		this.isNoSignificantStatus = isNoSignificantStatus;
	}

	public Integer getPatientMedicalHistoryId() {
		return patientMedicalHistoryId;
	}
	public void setPatientMedicalHistoryId(Integer patientMedicalHistoryId) {
		this.patientMedicalHistoryId = patientMedicalHistoryId;
	}


	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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

	public Integer getSince() {
		return since;
	}

	public void setSince(Integer since) {
		this.since = since;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
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

	public PatientRegistration getPatientRegistration() {
		return patientRegistration;
	}

	public void setPatientRegistration(PatientRegistration patientRegistration) {
		this.patientRegistration = patientRegistration;
	}

	
	public DiagnosisMaster getDiagnosisMaster() {
		return diagnosisMaster;
	}

	public void setDiagnosisMaster(DiagnosisMaster diagnosisMaster) {
		this.diagnosisMaster = diagnosisMaster;
	}
	
	
}
