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
import com.param.global.model.SurgeryMaster;
import com.param.opd.appointment.model.AppointmentMaster;

@NamedQueries({
	
	@NamedQuery(name="GET_PATIENT_SURGICAL_HISTORY",
				query=" SELECT 		patientSurgicalHist.surgicalHistoryDetailsId as surgicalHistoryDetailsId, patientSurgicalHist.remark as remark,"
					+ "				patientSurgicalHist.isNoSignificantStatus as isNoSignificantStatus, patientSurgicalHist.month as month, "
					+ " 			patientSurgicalHist.year as year, patientSurgicalHist.surgeryId as surgeryId, patientSurgicalHist.surgenName as surgenName,"
					+ "				patientSurgicalHist.status as status, patientSurgicalHist.encounterId as encounterId, patientSurgicalHist.patientId as patientId,"
					+ "				patientSurgicalHist.roleId as roleId, patientSurgicalHist.isReviewedFlag as isReviewedFlag, "
					+ "				patientSurgicalHist.isEnterInErrorStatus as isEnterInErrorStatus, "
					+ "				patientSurgicalHist.organizationId as organizationId, patientSurgicalHist.unitId as unitId, patientSurgicalHist.createdBy as createdBy,"
					+ "             patientSurgicalHist.updatedBy as updatedBy,"
					+ "				to_char(patientSurgicalHist.createdDate,'DD-MM-YYYY') as createdDate, to_char(patientSurgicalHist.updatedDate,'DD-MM-YYYY') as updatedDate,"
					+ "		  		surgeryMst.surgeryName as surgeryName "
					+ " FROM 		PatientSurgicalHistory patientSurgicalHist "
					+ " INNER JOIN 	patientSurgicalHist.surgeryMaster surgeryMst"
					+ " WHERE 		patientSurgicalHist.patientId=:patientId "
					+ " AND			patientSurgicalHist.status = 'A'"
					+ " AND			patientSurgicalHist.unitId=:unitId"
					+ " AND 		patientSurgicalHist.encounterId!=:encounterId "
					+ " AND 		patientSurgicalHist.organizationId=:organizationId"
			),
			
	@NamedQuery(name="GET_PATIENT_SURGICAL_HISTORY_BY_ROLE_ID",
			query=" SELECT 		patientSurgicalHist.surgicalHistoryDetailsId as surgicalHistoryDetailsId, patientSurgicalHist.remark as remark,"
				+ "				patientSurgicalHist.isNoSignificantStatus as isNoSignificantStatus, patientSurgicalHist.month as month, "
				+ " 			patientSurgicalHist.year as year, patientSurgicalHist.surgeryId as surgeryId, patientSurgicalHist.surgenName as surgenName,"
				+ "				patientSurgicalHist.status as status, patientSurgicalHist.encounterId as encounterId, patientSurgicalHist.patientId as patientId,"
				+ "				patientSurgicalHist.roleId as roleId, patientSurgicalHist.isReviewedFlag as isReviewedFlag, "
				+ "				patientSurgicalHist.isEnterInErrorStatus as isEnterInErrorStatus, "
				+ "				patientSurgicalHist.organizationId as organizationId, patientSurgicalHist.unitId as unitId, patientSurgicalHist.createdBy as createdBy,"
				+ "             patientSurgicalHist.updatedBy as updatedBy,"
				+ "				to_char(patientSurgicalHist.createdDate,'DD-MM-YYYY') as createdDate, to_char(patientSurgicalHist.updatedDate,'DD-MM-YYYY') as updatedDate,"
				+ "		  		surgeryMst.surgeryName as surgeryName "
				+ " FROM 		PatientSurgicalHistory patientSurgicalHist "
				+ " INNER JOIN 	patientSurgicalHist.surgeryMaster surgeryMst"
				+ " WHERE 		patientSurgicalHist.patientId=:patientId "
				+ " AND			patientSurgicalHist.status = 'A'"
				+ " AND			patientSurgicalHist.unitId=:unitId"
				+ " AND 		patientSurgicalHist.roleId =:roleId "
				+ " AND 		patientSurgicalHist.encounterId!=:encounterId "
				+ " AND 		patientSurgicalHist.organizationId=:organizationId"
		),
	
	@NamedQuery(name="GET_CURRENT_PATIENT_SURGICAL_HISTORY",
	query=" SELECT 		patientSurgicalHist.surgicalHistoryDetailsId as surgicalHistoryDetailsId, patientSurgicalHist.remark as remark,"
		+ "				patientSurgicalHist.isNoSignificantStatus as isNoSignificantStatus, patientSurgicalHist.month as month, "
		+ " 			patientSurgicalHist.year as year, patientSurgicalHist.surgeryId as surgeryId, patientSurgicalHist.surgenName as surgenName,"
		+ "				patientSurgicalHist.status as status, patientSurgicalHist.encounterId as encounterId, patientSurgicalHist.patientId as patientId,"
		+ "				patientSurgicalHist.roleId as roleId, patientSurgicalHist.isReviewedFlag as isReviewedFlag, "
		+ "				patientSurgicalHist.isEnterInErrorStatus as isEnterInErrorStatus, "
		+ "				patientSurgicalHist.organizationId as organizationId, patientSurgicalHist.unitId as unitId, patientSurgicalHist.createdBy as createdBy,"
		+ "             patientSurgicalHist.updatedBy as updatedBy,"
		+ "				to_char(patientSurgicalHist.createdDate,'DD-MM-YYYY') as createdDate, to_char(patientSurgicalHist.updatedDate,'DD-MM-YYYY') as updatedDate,"
		+ "		  		surgeryMst.surgeryName as surgeryName "
		+ " FROM 		PatientSurgicalHistory patientSurgicalHist "
		+ " INNER JOIN 	patientSurgicalHist.surgeryMaster surgeryMst"
		+ " WHERE 		patientSurgicalHist.patientId=:patientId "
		+ " AND			patientSurgicalHist.status = 'A'"
		+ " AND			patientSurgicalHist.unitId=:unitId"
		+ " AND 		patientSurgicalHist.encounterId=:encounterId "
		+ " AND 		patientSurgicalHist.organizationId=:organizationId"
		),
		
		@NamedQuery(name="GET_CURRENT_PATIENT_SURGICAL_HISTORY_BY_ROLE_ID",
		query=" SELECT 		patientSurgicalHist.surgicalHistoryDetailsId as surgicalHistoryDetailsId, patientSurgicalHist.remark as remark,"
			+ "				patientSurgicalHist.isNoSignificantStatus as isNoSignificantStatus, patientSurgicalHist.month as month, "
			+ " 			patientSurgicalHist.year as year, patientSurgicalHist.surgeryId as surgeryId, patientSurgicalHist.surgenName as surgenName,"
			+ "				patientSurgicalHist.status as status, patientSurgicalHist.encounterId as encounterId, patientSurgicalHist.patientId as patientId,"
			+ "				patientSurgicalHist.roleId as roleId, patientSurgicalHist.isReviewedFlag as isReviewedFlag, "
			+ "				patientSurgicalHist.isEnterInErrorStatus as isEnterInErrorStatus, "
			+ "				patientSurgicalHist.organizationId as organizationId, patientSurgicalHist.unitId as unitId, patientSurgicalHist.createdBy as createdBy,"
			+ "             patientSurgicalHist.updatedBy as updatedBy,"
			+ "				to_char(patientSurgicalHist.createdDate,'DD-MM-YYYY') as createdDate, to_char(patientSurgicalHist.updatedDate,'DD-MM-YYYY') as updatedDate,"
			+ "		  		surgeryMst.surgeryName as surgeryName "
			+ " FROM 		PatientSurgicalHistory patientSurgicalHist "
			+ " INNER JOIN 	patientSurgicalHist.surgeryMaster surgeryMst"
			+ " WHERE 		patientSurgicalHist.patientId=:patientId "
			+ " AND			patientSurgicalHist.status = 'A'"
			+ " AND			patientSurgicalHist.unitId=:unitId"
			+ " AND 		patientSurgicalHist.roleId =:roleId "
			+ " AND 		patientSurgicalHist.encounterId=:encounterId "
			+ " AND 		patientSurgicalHist.organizationId=:organizationId"
		)
})

@Entity
@Table(name="t_surgical_history_details",schema="patient")
@SequenceGenerator(name="t_surgical_history_details_seq" , sequenceName="patient.t_surgical_history_details_seq", allocationSize=1)
public class PatientSurgicalHistory {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "t_surgical_history_details_seq")
	@Column(name="surgical_history_details_id")
	private Integer surgicalHistoryDetailsId;

	@Column(name="remark")
	private String remark;
	
	@Column(name="is_no_significant_status")
	private char isNoSignificantStatus;
	
	@Column(name="month")
	private Integer month;
	
	@Column(name="year")
	private Integer year;
	
	@Column(name="surgery_id")
	private Integer surgeryId;
	
	@Column(name="surgen_name")
	private String surgenName;
	
	@Column(name="status")
	private char status;
	
	@Column(name="encounter_id")
	private Integer encounterId;
	
	@Column(name="role_id")
	private Integer roleId;
	
	@Column(name="is_reviewed_flag")
	private char isReviewedFlag;
	
	@Column(name="is_enter_in_error")
	private char isEnterInErrorStatus;
	
	@Column(name="patient_id")
	private Integer patientId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="unit_id")
	private Integer unitId;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updatedDate;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "surgery_id", insertable = false, nullable = false, updatable = false)
	private SurgeryMaster surgeryMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id", insertable = false, updatable = false)
	private PatientRegistration patientRegistration;
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "appointment_id", insertable = false, nullable = false, updatable = false)
	private AppointmentMaster appointmentMaster;*/

	
	
	
	public char getIsEnterInErrorStatus() {
		return isEnterInErrorStatus;
	}

	public void setIsEnterInErrorStatus(char isEnterInErrorStatus) {
		this.isEnterInErrorStatus = isEnterInErrorStatus;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public char getIsReviewedFlag() {
		return isReviewedFlag;
	}

	public void setIsReviewedFlag(char isReviewedFlag) {
		this.isReviewedFlag = isReviewedFlag;
	}

	public Integer getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}

	public Integer getSurgicalHistoryDetailsId() {
		return surgicalHistoryDetailsId;
	}

	public void setSurgicalHistoryDetailsId(Integer surgicalHistoryDetailsId) {
		this.surgicalHistoryDetailsId = surgicalHistoryDetailsId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public char getIsNoSignificantStatus() {
		return isNoSignificantStatus;
	}

	public void setIsNoSignificantStatus(char isNoSignificantStatus) {
		this.isNoSignificantStatus = isNoSignificantStatus;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getSurgeryId() {
		return surgeryId;
	}

	public void setSurgeryId(Integer surgeryId) {
		this.surgeryId = surgeryId;
	}

	public String getSurgenName() {
		return surgenName;
	}

	public void setSurgenName(String surgenName) {
		this.surgenName = surgenName;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public SurgeryMaster getSurgeryMaster() {
		return surgeryMaster;
	}

	public void setSurgeryMaster(SurgeryMaster surgeryMaster) {
		this.surgeryMaster = surgeryMaster;
	}

	public PatientRegistration getPatientRegistration() {
		return patientRegistration;
	}

	public void setPatientRegistration(PatientRegistration patientRegistration) {
		this.patientRegistration = patientRegistration;
	}

	/*public AppointmentMaster getAppointmentMaster() {
		return appointmentMaster;
	}

	public void setAppointmentMaster(AppointmentMaster appointmentMaster) {
		this.appointmentMaster = appointmentMaster;
	}
	
	*/
	
}
