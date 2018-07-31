package com.param.opd.coversheet.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@NamedQueries({
	
	@NamedQuery(name="GET_OLD_ENCOUNTER_LIST",
			query="     SELECT "
					+ " eDeta.encounterDetailsMapperId as encounterDetailsMapperId, "
					+ " to_char(enMst.encounterDate,'DD-MM-YYYY') as encounterDate, "
					+ "	concat('Dr. ',coalesce(docMst.firstName,''),' ',coalesce(docMst.middleName,''),' ',coalesce(docMst.lastName,'')) as doctorName, "
					+ " spcMst.specialityName as specialityName "
					+ " FROM EncounterDetails eDeta "
					+ " INNER JOIN eDeta.encounterMaster enMst "
					+ " INNER JOIN enMst.doctorMaster docMst"
					+ " LEFT JOIN docMst.docotrSpecialityMapperslist docSp "
					+ " LEFT JOIN docSp.specialityMaster spcMst "
					+ " WHERE eDeta.isAssignmentCompStatus = 'Y' AND  eDeta.isConsultationStatus = 'Y' "
					+ " AND eDeta.unitId=:unitId"
					+ " AND eDeta.organizationId=:organizationId "
					+ " AND eDeta.patientId=:patientId"
			),
			
			
			
	@NamedQuery(name="GET_ENCOUNTER_LIST_BY_ENCOUNTER_ID",
			query="SELECT enDt.encounterDetailsMapperId as encounterDetailsMapperId, enDt.encounterId as encounterId "
					+ " FROM EncounterDetails enDt "
					+ " WHERE enDt.encounterId=:encounterId "
			
			)
	
})


@Entity
@Table(name="t_encounter_details",schema="opd")
@SequenceGenerator(name="t_encounter_assignment_mapper_seq" , sequenceName="opd.t_encounter_assignment_mapper_seq", allocationSize=1)
public class EncounterDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "t_encounter_assignment_mapper_seq")
	@Column(name="encounter_details_mapper_id ")
	private Integer encounterDetailsMapperId ;
	
	@Column(name="is_assignment_comp_status")
	private Character isAssignmentCompStatus;
	
	@Column(name="assignment_start_time")
	private Date assignmentStartTime;
	
	@Column(name="assignment_start_by")
	private Integer assignmentStartBy;
	
	@Column(name="assignment_stop_by")
	private Integer assignmentStopBy;
	
	@Column(name="assignment_start_role_id")
	private Integer assignmentStartRoleId;
	
	@Column(name="assignment_stop_role_id")
	private Integer assignmentStopRoleId;
	
	@Column(name="assignment_stop_time")
	private Date assignmentStopTime;
	
	@Column(name="is_consultation_status")
	private Character isConsultationStatus;
	
	@Column(name="consultation_start_time")
	private Date consultationStartTime;
	
	@Column(name="consultation_start_by")
	private Integer consultationStartBy;
	
	@Column(name="consultation_stop_by")
	private Integer consultationStopBy;
	 
	@Column(name="consultation_start_role_id")
	private Integer consultationStartRoleId;
	
	@Column(name="consultation_stop_role_id")
	private Integer consultationStopRoleId;
	
	@Column(name="consultation_stop_time")
	private Date consultationStopTime;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="encounter_id")
	private Integer encounterId;

	@Column(name="patient_id")
	private Integer patientId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "encounter_id", insertable = false, nullable = false, updatable = false)
	private EncounterMaster encounterMaster;
	
	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	
	
	public Integer getEncounterDetailsMapperId() {
		return encounterDetailsMapperId;
	}

	public void setEncounterDetailsMapperId(Integer encounterDetailsMapperId) {
		this.encounterDetailsMapperId = encounterDetailsMapperId;
	}

	public Character getIsAssignmentCompStatus() {
		return isAssignmentCompStatus;
	}

	public void setIsAssignmentCompStatus(Character isAssignmentCompStatus) {
		this.isAssignmentCompStatus = isAssignmentCompStatus;
	}

	public Date getAssignmentStartTime() {
		return assignmentStartTime;
	}

	public void setAssignmentStartTime(Date assignmentStartTime) {
		this.assignmentStartTime = assignmentStartTime;
	}

	public Integer getAssignmentStartBy() {
		return assignmentStartBy;
	}

	public void setAssignmentStartBy(Integer assignmentStartBy) {
		this.assignmentStartBy = assignmentStartBy;
	}

	public Integer getAssignmentStopBy() {
		return assignmentStopBy;
	}

	public void setAssignmentStopBy(Integer assignmentStopBy) {
		this.assignmentStopBy = assignmentStopBy;
	}

	public Integer getAssignmentStartRoleId() {
		return assignmentStartRoleId;
	}

	public void setAssignmentStartRoleId(Integer assignmentStartRoleId) {
		this.assignmentStartRoleId = assignmentStartRoleId;
	}

	public Integer getAssignmentStopRoleId() {
		return assignmentStopRoleId;
	}

	public void setAssignmentStopRoleId(Integer assignmentStopRoleId) {
		this.assignmentStopRoleId = assignmentStopRoleId;
	}

	
	public Date getAssignmentStopTime() {
		return assignmentStopTime;
	}

	public void setAssignmentStopTime(Date assignmentStopTime) {
		this.assignmentStopTime = assignmentStopTime;
	}

	public Character getIsConsultationStatus() {
		return isConsultationStatus;
	}

	public void setIsConsultationStatus(Character isConsultationStatus) {
		this.isConsultationStatus = isConsultationStatus;
	}

	public Date getConsultationStartTime() {
		return consultationStartTime;
	}

	public void setConsultationStartTime(Date consultationStartTime) {
		this.consultationStartTime = consultationStartTime;
	}

	public Integer getConsultationStartBy() {
		return consultationStartBy;
	}

	public void setConsultationStartBy(Integer consultationStartBy) {
		this.consultationStartBy = consultationStartBy;
	}

	public Integer getConsultationStopBy() {
		return consultationStopBy;
	}

	public void setConsultationStopBy(Integer consultationStopBy) {
		this.consultationStopBy = consultationStopBy;
	}

	public Integer getConsultationStartRoleId() {
		return consultationStartRoleId;
	}

	public void setConsultationStartRoleId(Integer consultationStartRoleId) {
		this.consultationStartRoleId = consultationStartRoleId;
	}

	public Integer getConsultationStopRoleId() {
		return consultationStopRoleId;
	}

	public void setConsultationStopRoleId(Integer consultationStopRoleId) {
		this.consultationStopRoleId = consultationStopRoleId;
	}

	public Date getConsultationStopTime() {
		return consultationStopTime;
	}

	public void setConsultationStopTime(Date consultationStopTime) {
		this.consultationStopTime = consultationStopTime;
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

	public Integer getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}
	
	
	
}
