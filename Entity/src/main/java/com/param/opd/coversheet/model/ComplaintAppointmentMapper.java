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

import com.param.opd.appointment.model.AppointmentMaster;

@NamedQueries({
	@NamedQuery(name="GET_COMPLAINT_APPOINTMENT_MAPPER",
				query=" SELECT comApp.complaintAppoId as complaintAppoId, comApp.encounterId as encounterId, comApp.diagnosisId as diagnosisId,"
					+ " digMast.diagnosisName as diagnosisName, comApp.since as since, comApp.duration as duration, comApp.remark as remark,"
					+ " comApp.status as status, comApp.roleId as roleId,"
					+ " comApp.isReviewedFlag as isReviewedFlag "
					+ " FROM ComplaintAppointmentMapper comApp "
					+ " INNER JOIN comApp.diagnosisMaster digMast "
					+ " WHERE comApp.encounterId =:encounterId AND comApp.status = 'A'"
					+ " AND comApp.unitId =:unitId "
					+ " AND comApp.organizationId =:organizationId "
					+ " ORDER BY comApp.complaintAppoId desc"
			),
			
			@NamedQuery(name="GET_COMPLAINT_APPOINTMENT_MAPPER_BY_ROLE_ID",
			query=" SELECT comApp.complaintAppoId as complaintAppoId,  comApp.encounterId as encounterId, comApp.diagnosisId as diagnosisId,"
				+ " digMast.diagnosisName as diagnosisName, comApp.since as since, comApp.duration as duration, comApp.remark as remark,"
				+ " comApp.status as status, comApp.roleId as roleId, "
				+ " comApp.isReviewedFlag as isReviewedFlag "
				+ " FROM ComplaintAppointmentMapper comApp "
				+ " INNER JOIN comApp.diagnosisMaster digMast "
				+ " WHERE comApp.encounterId =:encounterId AND comApp.status = 'A'"
				+ " AND comApp.unitId =:unitId "
				+ " AND comApp.organizationId =:organizationId "
				+ " AND comApp.roleId =:roleId "
				+ " ORDER BY comApp.complaintAppoId desc"
		),
		
		@NamedQuery(name="GET_COMPLAINT_APPOINTMENT_MAPPER_BY_COMPLAINT_ID",
		query=" SELECT comApp.complaintAppoId as complaintAppoId,  comApp.encounterId as encounterId, comApp.diagnosisId as diagnosisId,"
			+ " comApp.since as since, comApp.duration as duration, comApp.remark as remark,"
			+ " comApp.status as status, comApp.roleId as roleId, "
			+ " comApp.isReviewedFlag as isReviewedFlag "
			+ " FROM ComplaintAppointmentMapper comApp "
			+ " WHERE comApp.complaintAppoId =:complaintAppoId "
	)
})

@Entity
@Table(name="t_complaint_appo_mapper", schema="patient")
@SequenceGenerator(name = "t_complaint_appo_mapper_seq", sequenceName = "patient.t_complaint_appo_mapper_seq", allocationSize = 1)
public class ComplaintAppointmentMapper {

	
	@Id
	@Column(name="complaint_appo_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_complaint_appo_mapper_seq")
	private Integer complaintAppoId;
	
	/*@Column(name="complaint_id")
	private int complaintId;*/
	
	@Column(name="is_reviewed_flag")
	private char isReviewedFlag;
	
	@Column(name="encounter_id")
	private Integer encounterId;
	
	@Column(name="role_id")
	private Integer roleId;
	
	@Column(name="diagnosis_id")
	private Integer diagnosisId;
	
	@Column(name="since")
	private Integer since;
	
	@Column(name="duration")
	private String duration;
	
	@Column(name="remark")
	private String remark;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "encounter_id", insertable = false, nullable = false, updatable = false)
	private EncounterMaster encounterMaster;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diagnosis_id", insertable = false, nullable = false, updatable = false)
	private DiagnosisMaster diagnosisMaster;
	
	
	
	
	public char getIsReviewedFlag() {
		return isReviewedFlag;
	}

	public void setIsReviewedFlag(char isReviewedFlag) {
		this.isReviewedFlag = isReviewedFlag;
	}

	public void setComplaintAppoId(Integer complaintAppoId) {
		this.complaintAppoId = complaintAppoId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

/*	public int getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}
*/
	
	public EncounterMaster getEncounterMaster() {
		return encounterMaster;
	}

	public void setEncounterMaster(EncounterMaster encounterMaster) {
		this.encounterMaster = encounterMaster;
	}

	public Integer getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
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

	public Integer getDiagnosisId() {
		return diagnosisId;
	}

	public void setDiagnosisId(Integer diagnosisId) {
		this.diagnosisId = diagnosisId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
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

	public DiagnosisMaster getDiagnosisMaster() {
		return diagnosisMaster;
	}

	public void setDiagnosisMaster(DiagnosisMaster diagnosisMaster) {
		this.diagnosisMaster = diagnosisMaster;
	}

	public int getComplaintAppoId() {
		return complaintAppoId;
	}

	public void setComplaintAppoId(int complaintAppoId) {
		this.complaintAppoId = complaintAppoId;
	}
	
	
	
}
