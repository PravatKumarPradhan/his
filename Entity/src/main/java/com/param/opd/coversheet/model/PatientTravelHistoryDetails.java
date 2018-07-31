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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.global.model.PatientRegistration;
import com.param.opd.appointment.model.AppointmentMaster;


@NamedQueries({
	@NamedQuery(name="GET_PATIENT_TRAVEL_HISTORY_DEATILS",
				query=" SELECT 		patientTravelHist.travelHistoryDetailsId as travelHistoryDetailsId, "
					+ " 			patientTravelHist.description as description,"
					+ " 			patientTravelHist.encounterId as encounterId, patientTravelHist.patientId as patientId, "
					+ "             to_char(patientTravelHist.whenTravel,'MM/DD/YYYY') as whenTravel, "
					+ " 			patientTravelHist.organizationId as organizationId,"
					+ " 			patientTravelHist.unitId as unitId, patientTravelHist.createdBy as createdBy, patientTravelHist.updatedBy as updatedBy,"
					+ " 			to_char(patientTravelHist.createdDate,'DD-MM-YYYY') as createdDate,  to_char(patientTravelHist.updatedDate,'DD-MM-YYYY') as updatedDate, "
					+ " 			patientTravelHist.status as status,"
					+ " 			patientTravelHist.roleId as roleId, patientTravelHist.isEnterInErrorStatus as isEnterInErrorStatus, "
					+ " 			patientTravelHist.isReviewedFlag as isReviewedFlag  "
					+ " FROM 		PatientTravelHistoryDetails patientTravelHist "
					+ " INNER JOIN 	patientTravelHist.patientRegistration patientReg "
					+ " WHERE 		patientTravelHist.patientId =:patientId AND patientTravelHist.status = 'A'"
					+ " AND 		patientTravelHist.unitId=:unitId "
					+ " AND 		patientTravelHist.organizationId=:organizationId "
					+ " AND 		patientTravelHist.encounterId!=:encounterId "
					+ " ORDER BY 	patientTravelHist.travelHistoryDetailsId desc"
			),
			@NamedQuery(name="GET_PATIENT_TRAVEL_HISTORY_DEATILS_BY_ROLE_ID",
			query=" SELECT 		patientTravelHist.travelHistoryDetailsId as travelHistoryDetailsId, "
				+ " 			patientTravelHist.description as description,"
				+ " 			patientTravelHist.encounterId as encounterId, patientTravelHist.patientId as patientId, "
				+ "             to_char(patientTravelHist.whenTravel,'MM/DD/YYYY') as whenTravel, "
				+ " 			patientTravelHist.organizationId as organizationId,"
				+ " 			patientTravelHist.unitId as unitId, patientTravelHist.createdBy as createdBy, patientTravelHist.updatedBy as updatedBy,"
				+ " 			to_char(patientTravelHist.createdDate,'DD-MM-YYYY') as createdDate,  to_char(patientTravelHist.updatedDate,'DD-MM-YYYY') as updatedDate, "
				+ " 			patientTravelHist.status as status,"
				+ " 			patientTravelHist.roleId as roleId, patientTravelHist.isEnterInErrorStatus as isEnterInErrorStatus, "
				+ " 			patientTravelHist.isReviewedFlag as isReviewedFlag  "
				+ " FROM 		PatientTravelHistoryDetails patientTravelHist "
				+ " INNER JOIN 	patientTravelHist.patientRegistration patientReg "
				+ " WHERE 		patientTravelHist.patientId =:patientId AND patientTravelHist.status = 'A'"
				+ " AND 		patientTravelHist.unitId=:unitId "
				+ " AND 		patientTravelHist.organizationId=:organizationId "
				+ " AND 		patientTravelHist.encounterId!=:encounterId "
				+ " AND 		patientTravelHist.roleId=:roleId "
				+ " ORDER BY 	patientTravelHist.travelHistoryDetailsId desc"
		),
		
		@NamedQuery(name="GET_CURRENT_PATIENT_TRAVEL_HISTORY_DEATILS",
		query=" SELECT 		patientTravelHist.travelHistoryDetailsId as travelHistoryDetailsId, "
			+ " 			patientTravelHist.description as description,"
			+ " 			patientTravelHist.encounterId as encounterId, patientTravelHist.patientId as patientId, "
			+ "             to_char(patientTravelHist.whenTravel,'MM/DD/YYYY') as whenTravel, "
			+ " 			patientTravelHist.organizationId as organizationId,"
			+ " 			patientTravelHist.unitId as unitId, patientTravelHist.createdBy as createdBy, patientTravelHist.updatedBy as updatedBy,"
			+ " 			to_char(patientTravelHist.createdDate,'DD-MM-YYYY') as createdDate,  to_char(patientTravelHist.updatedDate,'DD-MM-YYYY') as updatedDate, "
			+ " 			patientTravelHist.status as status,"
			+ " 			patientTravelHist.roleId as roleId, patientTravelHist.isEnterInErrorStatus as isEnterInErrorStatus, "
			+ " 			patientTravelHist.isReviewedFlag as isReviewedFlag  "
			+ " FROM 		PatientTravelHistoryDetails patientTravelHist "
			+ " INNER JOIN 	patientTravelHist.patientRegistration patientReg "
			+ " WHERE 		patientTravelHist.patientId =:patientId AND patientTravelHist.status = 'A'"
			+ " AND 		patientTravelHist.unitId=:unitId "
			+ " AND 		patientTravelHist.organizationId=:organizationId "
			+ " AND 		patientTravelHist.encounterId=:encounterId "
			+ " ORDER BY 	patientTravelHist.travelHistoryDetailsId desc"
	),
	@NamedQuery(name="GET_CURRENT_PATIENT_TRAVEL_HISTORY_DEATILS_BY_ROLE_ID",
	query=" SELECT 		patientTravelHist.travelHistoryDetailsId as travelHistoryDetailsId, "
		+ " 			patientTravelHist.description as description,"
		+ " 			patientTravelHist.encounterId as encounterId, patientTravelHist.patientId as patientId, "
		+ "             to_char(patientTravelHist.whenTravel,'MM/DD/YYYY') as whenTravel, "
		+ " 			patientTravelHist.organizationId as organizationId,"
		+ " 			patientTravelHist.unitId as unitId, patientTravelHist.createdBy as createdBy, patientTravelHist.updatedBy as updatedBy,"
		+ " 			to_char(patientTravelHist.createdDate,'DD-MM-YYYY') as createdDate,  to_char(patientTravelHist.updatedDate,'DD-MM-YYYY') as updatedDate, "
		+ " 			patientTravelHist.status as status,"
		+ " 			patientTravelHist.roleId as roleId, patientTravelHist.isEnterInErrorStatus as isEnterInErrorStatus, "
		+ " 			patientTravelHist.isReviewedFlag as isReviewedFlag  "
		+ " FROM 		PatientTravelHistoryDetails patientTravelHist "
		+ " INNER JOIN 	patientTravelHist.patientRegistration patientReg "
		+ " WHERE 		patientTravelHist.patientId =:patientId AND patientTravelHist.status = 'A'"
		+ " AND 		patientTravelHist.unitId=:unitId "
		+ " AND 		patientTravelHist.organizationId=:organizationId "
		+ " AND 		patientTravelHist.encounterId=:encounterId "
		+ " AND 		patientTravelHist.roleId=:roleId "
		+ " ORDER BY 	patientTravelHist.travelHistoryDetailsId desc"
)
})


@Entity
@Table(name = "t_patient_travel_history_details", schema = "patient")
@SequenceGenerator(name="patient_travel_history_details_seq", sequenceName="patient.patient_travel_history_details_seq", allocationSize=1)
public class PatientTravelHistoryDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_travel_history_details_seq")
	@Column(name="travel_history_details_id")
	private Integer travelHistoryDetailsId;
	
	@Column(name="description")
	private String description;
	
	@Column(name="when_travel")
	private Date whenTravel;
	
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
	
	@Column(name = "status")
	private char status;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updatedDate;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id", insertable = false, updatable = false)
	private PatientRegistration patientRegistration;
	
	
	
	
	public Integer getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
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

	public char getIsEnterInErrorStatus() {
		return isEnterInErrorStatus;
	}

	public void setIsEnterInErrorStatus(char isEnterInErrorStatus) {
		this.isEnterInErrorStatus = isEnterInErrorStatus;
	}

	public Integer getTravelHistoryDetailsId() {
		return travelHistoryDetailsId;
	}

	public void setTravelHistoryDetailsId(Integer travelHistoryDetailsId) {
		this.travelHistoryDetailsId = travelHistoryDetailsId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getWhenTravel() {
		return whenTravel;
	}

	public void setWhenTravel(Date whenTravel) {
		this.whenTravel = whenTravel;
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

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
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

	public PatientRegistration getPatientRegistration() {
		return patientRegistration;
	}

	public void setPatientRegistration(PatientRegistration patientRegistration) {
		this.patientRegistration = patientRegistration;
	}

	
	
}
