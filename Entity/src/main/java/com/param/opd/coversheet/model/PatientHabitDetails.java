package com.param.opd.coversheet.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
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

import com.param.global.model.HabitMaster;
import com.param.global.model.PatientRegistration;

@NamedQueries({
	
	@NamedQuery(name="GET_PATIENT_HABIT_DETAILS_LIST",
				query=" SELECT  patHab.patientHabitDetailsId as patientHabitDetailsId, "
					+ " 		patHab.patientId as patientId, patHab.habitId as habitId,"
					+ " 		patHab.remark as remark, patHab.cigarettesPerDay as cigarettesPerDay, patHab.yearsSmoked as yearsSmoked,"
					+ " 		patHab.gmsPerDay as gmsPerDay, patHab.yearsUsed as yearsUsed, patHab.mlsPerDay as mlsPerDay, "
					+ " 		patHab.habitTypeId as habitTypeId, patHab.frequency as frequency, "
					+ " 		patHab.leftWhen as leftWhen, patHab.packYear as packYear, patHab.typeOfExercise as typeOfExercise,"
					+ " 		patHab.durationOfLeftWhen as durationOfLeftWhen, patHab.drug as drug, "
					+ " 		patHab.createdBy as createdBy, patHab.updatedBy as updatedBy, patHab.status as status,"
					+ " 		patHab.unitId as unitId, patHab.organizationId as organizationId, "
					+ " 		to_char(patHab.createdDate,'DD-MM-YYYY') as createdDate, to_char(patHab.updatedDate,'DD-MM-YYYY') as updatedDate,"
					+ "			patHab.encounterId as encounterId, patHab.roleId as roleId,  patHab.isReviewedFlag as isReviewedFlag, "
					+ "         patHab.isEnterInErrorStatus as isEnterInErrorStatus "
					+ " FROM 	PatientHabitDetails patHab "
					+ " WHERE 	patHab.patientId=:patientId "
					+ " AND 	patHab.status = 'A'"
					+ " AND 	patHab.unitId=:unitId"
					+ " AND 	patHab.encounterId!=:encounterId"
					+ " AND 	patHab.organizationId=:organizationId"
			),
			
			@NamedQuery(name="GET_PATIENT_HABIT_DETAILS_LIST_BY_ROLE_ID",
					query=" SELECT  patHab.patientHabitDetailsId as patientHabitDetailsId, "
							+ " 		patHab.patientId as patientId, patHab.habitId as habitId,"
				+ " 		patHab.remark as remark, patHab.cigarettesPerDay as cigarettesPerDay, patHab.yearsSmoked as yearsSmoked,"
				+ " 		patHab.gmsPerDay as gmsPerDay, patHab.yearsUsed as yearsUsed, patHab.mlsPerDay as mlsPerDay, "
				+ " 		patHab.habitTypeId as habitTypeId, patHab.frequency as frequency, "
				+ " 		patHab.leftWhen as leftWhen, patHab.packYear as packYear, patHab.typeOfExercise as typeOfExercise,"
				+ " 		patHab.durationOfLeftWhen as durationOfLeftWhen, patHab.drug as drug, "
				+ " 		patHab.createdBy as createdBy, patHab.updatedBy as updatedBy, patHab.status as status,"
				+ " 		patHab.unitId as unitId, patHab.organizationId as organizationId, "
				+ " 		to_char(patHab.createdDate,'DD-MM-YYYY') as createdDate, to_char(patHab.updatedDate,'DD-MM-YYYY') as updatedDate,"
				+ "			patHab.encounterId as encounterId, patHab.roleId as roleId,  patHab.isReviewedFlag as isReviewedFlag, "
				+ "         patHab.isEnterInErrorStatus as isEnterInErrorStatus "
				+ " FROM 	PatientHabitDetails patHab "
				+ " WHERE 	patHab.patientId=:patientId "
				+ " AND 	patHab.status = 'A'"
				+ " AND 	patHab.unitId=:unitId"
				+ " AND 	patHab.encounterId!=:encounterId"
				+ " AND 	patHab.roleId=:roleId"
				+ " AND 	patHab.organizationId=:organizationId"
		),
		
		@NamedQuery(name="GET_CURRENT_PATIENT_HABIT_DETAILS_LIST",
				query=" SELECT  patHab.patientHabitDetailsId as patientHabitDetailsId, "
						+ " 		patHab.patientId as patientId, patHab.habitId as habitId,"
			+ " 		patHab.remark as remark, patHab.cigarettesPerDay as cigarettesPerDay, patHab.yearsSmoked as yearsSmoked,"
			+ " 		patHab.gmsPerDay as gmsPerDay, patHab.yearsUsed as yearsUsed, patHab.mlsPerDay as mlsPerDay, "
			+ " 		patHab.habitTypeId as habitTypeId, patHab.frequency as frequency, "
			+ " 		patHab.leftWhen as leftWhen, patHab.packYear as packYear, patHab.typeOfExercise as typeOfExercise,"
			+ " 		patHab.durationOfLeftWhen as durationOfLeftWhen, patHab.drug as drug, "
			+ " 		patHab.createdBy as createdBy, patHab.updatedBy as updatedBy, patHab.status as status,"
			+ " 		patHab.unitId as unitId, patHab.organizationId as organizationId, "
			+ " 		to_char(patHab.createdDate,'DD-MM-YYYY') as createdDate, to_char(patHab.updatedDate,'DD-MM-YYYY') as updatedDate,"
			+ "			patHab.encounterId as encounterId, patHab.roleId as roleId,  patHab.isReviewedFlag as isReviewedFlag, "
			+ "         patHab.isEnterInErrorStatus as isEnterInErrorStatus "
			+ " FROM 	PatientHabitDetails patHab "
			+ " WHERE 	patHab.patientId=:patientId "
			+ " AND 	patHab.status = 'A'"
			+ " AND 	patHab.unitId=:unitId"
			+ " AND 	patHab.encounterId=:encounterId"
			+ " AND 	patHab.organizationId=:organizationId"
	),
	
	@NamedQuery(name="GET_CURRENT_PATIENT_HABIT_DETAILS_LIST_BY_ROLE_ID",
			query=" SELECT  patHab.patientHabitDetailsId as patientHabitDetailsId, "
					+ " 		patHab.patientId as patientId, patHab.habitId as habitId,"
		+ " 		patHab.remark as remark, patHab.cigarettesPerDay as cigarettesPerDay, patHab.yearsSmoked as yearsSmoked,"
		+ " 		patHab.gmsPerDay as gmsPerDay, patHab.yearsUsed as yearsUsed, patHab.mlsPerDay as mlsPerDay, "
		+ " 		patHab.habitTypeId as habitTypeId, patHab.frequency as frequency, "
		+ " 		patHab.leftWhen as leftWhen, patHab.packYear as packYear, patHab.typeOfExercise as typeOfExercise,"
		+ " 		patHab.durationOfLeftWhen as durationOfLeftWhen, patHab.drug as drug, "
		+ " 		patHab.createdBy as createdBy, patHab.updatedBy as updatedBy, patHab.status as status,"
		+ " 		patHab.unitId as unitId, patHab.organizationId as organizationId, "
		+ " 		to_char(patHab.createdDate,'DD-MM-YYYY') as createdDate, to_char(patHab.updatedDate,'DD-MM-YYYY') as updatedDate,"
		+ "			patHab.encounterId as encounterId, patHab.roleId as roleId,  patHab.isReviewedFlag as isReviewedFlag, "
		+ "         patHab.isEnterInErrorStatus as isEnterInErrorStatus "
		+ " FROM 	PatientHabitDetails patHab "
		+ " WHERE 	patHab.patientId=:patientId "
		+ " AND 	patHab.status = 'A'"
		+ " AND 	patHab.unitId=:unitId"
		+ " AND 	patHab.encounterId=:encounterId"
		+ " AND 	patHab.roleId=:roleId"
		+ " AND 	patHab.organizationId=:organizationId"
),
			
	@NamedQuery(name="GET_LIST_PATIENT_HABIT",
			query=" SELECT  patHab.patientHabitDetailsId as patientHabitDetailsId, "
					+ " 		patHab.patientId as patientId, patHab.habitId as habitId,"
					+ " 		patHab.remark as remark, patHab.cigarettesPerDay as cigarettesPerDay, patHab.yearsSmoked as yearsSmoked,"
					+ " 		patHab.gmsPerDay as gmsPerDay, patHab.yearsUsed as yearsUsed, patHab.mlsPerDay as mlsPerDay, "
					+ " 		patHab.habitTypeId as habitTypeId, patHab.frequency as frequency, "
					+ " 		patHab.leftWhen as leftWhen, patHab.packYear as packYear, patHab.typeOfExercise as typeOfExercise,"
					+ " 		patHab.durationOfLeftWhen as durationOfLeftWhen, "
					+ " 		patHab.createdBy as createdBy, patHab.updatedBy as updatedBy,"
					+ " 		patHab.unitId as unitId, patHab.organizationId as organizationId, "
					+ " 		to_char(patHab.createdDate,'DD-MM-YYYY') as createdDate, to_char(patHab.updatedDate,'DD-MM-YYYY') as updatedDate,"
					+ "			patHab.encounterId as encounterId, patHab.roleId as roleId,  patHab.isReviewedFlag as isReviewedFlag, "
					+ "         patHab.isEnterInErrorStatus as isEnterInErrorStatus "
				+ " FROM 	PatientHabitDetails patHab "
				+ " WHERE 	patHab.patientId=:patientId "
				+ " AND 	patHab.habitId=:habitId "
				+ " AND 	patHab.status = 'A' "
				+ " AND 	patHab.encounterId=:encounterId "
				
		),
		
	@NamedQuery(name="UPDATE_OLD_HABITS_BYPATIENT",
			query="UPDATE PatientHabitDetails pHabitDetail set pHabitDetail.status='I' "
					+ "WHERE pHabitDetail.patientId=:patientId AND pHabitDetail.habitId=:habitId "
					+ " AND pHabitDetail.encounterId=:encounterId "
					+ " AND pHabitDetail.roleId=:roleId "),
					
					
	/*@NamedQuery(name="UPDATE_NURSE_REVIEWD_FLAG",
	query=" UPDATE PatientHabitDetails pHabitDetail set pHabitDetail.isReviewedFlag ='Y' "
			+ "  WHERE pHabitDetail.patientHabitDetailsId.patientId=:patientId AND pHabitDetail.patientHabitDetailsId.habitId=:habitId"
			+ " AND pHabitDetail.encounterId=:encounterId AND pHabitDetail.patientHabitDetailsId.status = 'A' AND pHabitDetail.roleId=:roleId "),*/
					
					
					
})



@Entity
@Table(name="t_patient_habit_details",schema="patient")
@SequenceGenerator(name="patient_habit_details_seq" , sequenceName="patient.patient_habit_details_seq", allocationSize=1)
public class PatientHabitDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "patient_habit_details_seq")
	@Column(name="patient_habit_details_id")
	private Integer patientHabitDetailsId;
	
	@Column(name="remark")
	private String remark;
	
	@Column(name="cigarettes_per_day")
	private Integer cigarettesPerDay;
	
	@Column(name="years_smoked")
	private double yearsSmoked;
	
	@Column(name="gms_per_day")
	private Integer gmsPerDay;
	
	@Column(name="years_used")
	private double yearsUsed;
	
	@Column(name="mls_per_day")
	private Integer mlsPerDay;
	
	@Column(name="habit_type_id")
	private Integer habitTypeId;
	
	@Column(name="frequency")
	private String frequency;
	
	@Column(name="left_when")
	private Integer leftWhen;
	
	@Column(name="duration_of_left_when")
	private String durationOfLeftWhen;
	
	@Column(name="pack_year")
	private double packYear;
	
	@Column(name="type_of_exercise")
	private String typeOfExercise;
	
	@Column(name="drug")
	private String drug;
	
	@Column(name="created_by")
	private Integer createdBy;
	
	@Column(name="updated_by")
	private Integer updatedBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="encounter_id")
	private Integer encounterId;
	
	@Column(name="role_id")
	private Integer roleId;
	
	@Column(name="is_reviewed_flag")
	private char isReviewedFlag;
	
	@Column(name="is_enter_in_error")
	private char isEnterInErrorStatus;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	/*@Column(name="exercise_when")
	private Integer exerciseWhen;*/
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "habit_id", insertable = false, nullable = false, updatable = false)
	private HabitMaster habitMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id", insertable = false, updatable = false)
	private PatientRegistration patientRegistration;

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
	
	/*public Integer getExerciseWhen() {
		return exerciseWhen;
	}

	public void setExerciseWhen(Integer exerciseWhen) {
		this.exerciseWhen = exerciseWhen;
	}*/

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

	public String getDrug() {
		return drug;
	}

	public void setDrug(String drug) {
		this.drug = drug;
	}

	public Integer getHabitTypeId() {
		return habitTypeId;
	}

	public void setHabitTypeId(Integer habitTypeId) {
		this.habitTypeId = habitTypeId;
	}

	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCigarettesPerDay() {
		return cigarettesPerDay;
	}

	public void setCigarettesPerDay(Integer cigarettesPerDay) {
		this.cigarettesPerDay = cigarettesPerDay;
	}

	public double getYearsSmoked() {
		return yearsSmoked;
	}

	public void setYearsSmoked(double yearsSmoked) {
		this.yearsSmoked = yearsSmoked;
	}

	public Integer getGmsPerDay() {
		return gmsPerDay;
	}

	public void setGmsPerDay(Integer gmsPerDay) {
		this.gmsPerDay = gmsPerDay;
	}

	public double getYearsUsed() {
		return yearsUsed;
	}

	public void setYearsUsed(double yearsUsed) {
		this.yearsUsed = yearsUsed;
	}

	public Integer getMlsPerDay() {
		return mlsPerDay;
	}

	public void setMlsPerDay(Integer mlsPerDay) {
		this.mlsPerDay = mlsPerDay;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	

	public double getPackYear() {
		return packYear;
	}

	public void setPackYear(double packYear) {
		this.packYear = packYear;
	}

	public String getTypeOfExercise() {
		return typeOfExercise;
	}

	public void setTypeOfExercise(String typeOfExercise) {
		this.typeOfExercise = typeOfExercise;
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

	public HabitMaster getHabitMaster() {
		return habitMaster;
	}

	public void setHabitMaster(HabitMaster habitMaster) {
		this.habitMaster = habitMaster;
	}


	public PatientRegistration getPatientRegistration() {
		return patientRegistration;
	}

	public void setPatientRegistration(PatientRegistration patientRegistration) {
		this.patientRegistration = patientRegistration;
	}

	public Integer getLeftWhen() {
		return leftWhen;
	}

	public void setLeftWhen(Integer leftWhen) {
		this.leftWhen = leftWhen;
	}

	public String getDurationOfLeftWhen() {
		return durationOfLeftWhen;
	}

	public void setDurationOfLeftWhen(String durationOfLeftWhen) {
		this.durationOfLeftWhen = durationOfLeftWhen;
	}

	public Integer getPatientHabitDetailsId() {
		return patientHabitDetailsId;
	}

	public void setPatientHabitDetailsId(Integer patientHabitDetailsId) {
		this.patientHabitDetailsId = patientHabitDetailsId;
	}
	
	
}
