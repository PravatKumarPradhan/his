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

import com.param.global.model.DrugMaster;
import com.param.global.model.PatientRegistration;
import com.param.opd.appointment.model.AppointmentMaster;


@NamedQueries({
	
	@NamedQuery(name="GET_LIST_IMMUNIZATION_MASTER_HISTORY",
				query=" SELECT 		imHisMapper.immunizationHistoryMapperId as immunizationHistoryMapperId, "
					+ "				to_char(imHisMapper.immunizationDate, 'MM/DD/YYYY') as immunizationDate, imHisMapper.encounterId as encounterId,"
					+ "				imHisMapper.patientId as patientId, imHisMapper.drugId as drugId, imHisMapper.createdBy as createdBy, imHisMapper.updatedBy as updatedBy,"
					+ "				to_char(imHisMapper.createdDate, 'DD-MM-YYYY') as createdDate, to_char(imHisMapper.updatedDate, 'DD-MM-YYYY') as updatedDate,"
					+ "				imHisMapper.status as status, imHisMapper.isAdministeredStatus as isAdministeredStatus, imHisMapper.unitId as unitId, "
					+ "				imHisMapper.organizationId as organizationId,"
					+ " 			imHisMapper.roleId as roleId,"
					+ "				imHisMapper.isReviewedFlag as isReviewedFlag,"
					+ " 			durgMst.medicationName as drugName "
					+ " FROM 		ImmunizationHistoryMapper imHisMapper "
					+ " INNER JOIN	imHisMapper.drugMaster durgMst "
					+ " WHERE 		imHisMapper.patientId=:patientId "
					+ " AND 		imHisMapper.unitId=:unitId "
					+ " AND 		imHisMapper.encounterId!=:encounterId "
					+ " AND 		imHisMapper.organizationId=:organizationId "
					+ " AND 		imHisMapper.status = 'A' "
			),
			
			@NamedQuery(name="GET_LIST_IMMUNIZATION_MASTER_HISTORY_BY_ROLE_ID",
			query=" SELECT 		imHisMapper.immunizationHistoryMapperId as immunizationHistoryMapperId, "
				+ "				to_char(imHisMapper.immunizationDate, 'MM/DD/YYYY') as immunizationDate, imHisMapper.encounterId as encounterId,"
				+ "				imHisMapper.patientId as patientId, imHisMapper.drugId as drugId, imHisMapper.createdBy as createdBy, imHisMapper.updatedBy as updatedBy,"
				+ "				to_char(imHisMapper.createdDate, 'DD-MM-YYYY') as createdDate, to_char(imHisMapper.updatedDate, 'DD-MM-YYYY') as updatedDate,"
				+ "				imHisMapper.status as status, imHisMapper.isAdministeredStatus as isAdministeredStatus, imHisMapper.unitId as unitId, "
				+ "				imHisMapper.organizationId as organizationId,"
				+ " 			imHisMapper.roleId as roleId,"
				+ "				imHisMapper.isReviewedFlag as isReviewedFlag, "
				+ " 			durgMst.medicationName as drugName "
				+ " FROM 		ImmunizationHistoryMapper imHisMapper "
				+ " INNER JOIN	imHisMapper.drugMaster durgMst "
				+ " WHERE 		imHisMapper.patientId=:patientId "
				+ " AND 		imHisMapper.unitId=:unitId "
				+ " AND 		imHisMapper.encounterId!=:encounterId "
				+ " AND 		imHisMapper.roleId=:roleId "
				+ " AND 		imHisMapper.organizationId=:organizationId "
				+ " AND 		imHisMapper.status = 'A' "
		),
		
		@NamedQuery(name="GET_CURRENT_LIST_IMMUNIZATION_MASTER_HISTORY",
		query=" SELECT 		imHisMapper.immunizationHistoryMapperId as immunizationHistoryMapperId, "
			+ "				to_char(imHisMapper.immunizationDate, 'MM/DD/YYYY') as immunizationDate, imHisMapper.encounterId as encounterId,"
			+ "				imHisMapper.patientId as patientId, imHisMapper.drugId as drugId, imHisMapper.createdBy as createdBy, imHisMapper.updatedBy as updatedBy,"
			+ "				to_char(imHisMapper.createdDate, 'DD-MM-YYYY') as createdDate, to_char(imHisMapper.updatedDate, 'DD-MM-YYYY') as updatedDate,"
			+ "				imHisMapper.status as status, imHisMapper.isAdministeredStatus as isAdministeredStatus, imHisMapper.unitId as unitId, "
			+ "				imHisMapper.organizationId as organizationId,"
			+ " 			imHisMapper.roleId as roleId,"
			+ "				imHisMapper.isReviewedFlag as isReviewedFlag, "
			+ " 			durgMst.medicationName as drugName "
			+ " FROM 		ImmunizationHistoryMapper imHisMapper "
			+ " INNER JOIN	imHisMapper.drugMaster durgMst "
			+ " WHERE 		imHisMapper.patientId=:patientId "
			+ " AND 		imHisMapper.unitId=:unitId "
			+ " AND 		imHisMapper.encounterId=:encounterId "
			+ " AND 		imHisMapper.organizationId=:organizationId "
			+ " AND 		imHisMapper.status = 'A' "
	),
	
	@NamedQuery(name="GET_CURRENT_LIST_IMMUNIZATION_MASTER_HISTORY_BY_ROLE_ID",
	query=" SELECT 		imHisMapper.immunizationHistoryMapperId as immunizationHistoryMapperId, "
		+ "				to_char(imHisMapper.immunizationDate, 'MM/DD/YYYY') as immunizationDate, imHisMapper.encounterId as encounterId,"
		+ "				imHisMapper.patientId as patientId, imHisMapper.drugId as drugId, imHisMapper.createdBy as createdBy, imHisMapper.updatedBy as updatedBy,"
		+ "				to_char(imHisMapper.createdDate, 'DD-MM-YYYY') as createdDate, to_char(imHisMapper.updatedDate, 'DD-MM-YYYY') as updatedDate,"
		+ "				imHisMapper.status as status, imHisMapper.isAdministeredStatus as isAdministeredStatus, imHisMapper.unitId as unitId, "
		+ "				imHisMapper.organizationId as organizationId,"
		+ " 			imHisMapper.roleId as roleId,"
		+ "				imHisMapper.isReviewedFlag as isReviewedFlag, "
		+ " 			durgMst.medicationName as drugName "
		+ " FROM 		ImmunizationHistoryMapper imHisMapper "
		+ " INNER JOIN	imHisMapper.drugMaster durgMst "
		+ " WHERE 		imHisMapper.patientId=:patientId "
		+ " AND 		imHisMapper.unitId=:unitId "
		+ " AND 		imHisMapper.encounterId=:encounterId "
		+ " AND 		imHisMapper.roleId=:roleId "
		+ " AND 		imHisMapper.organizationId=:organizationId "
		+ " AND 		imHisMapper.status = 'A' "
),
			
			@NamedQuery(name="GET_LIST_IMMUNIZATION",
			query=" SELECT 		imHisMapper.immunizationHistoryMapperId as immunizationHistoryMapperId "
				+ " FROM 		ImmunizationHistoryMapper imHisMapper "
				+ " WHERE 		imHisMapper.drugId=:drugId "
				+ " AND 		imHisMapper.patientId=:patientId"
				
		),
		@NamedQuery(name="UPDATE_OLD_IMMUNIZATION",
		query=" UPDATE ImmunizationHistoryMapper imMP SET  imMP.status = 'I' "
			+ " WHERE 		imMP.drugId=:drugId "
			+ " AND 		imMP.patientId=:patientId "
			+ " AND 		imMP.roleId=:roleId "
			+ " AND 		imMP.encounterId=:encounterId "
			
	)
	
	
})

@Entity
@Table(name="t_immunization_history_mapper",schema="patient")
@SequenceGenerator(name="t_immunization_history_mapper_seq", sequenceName="patient.t_immunization_history_mapper_seq", allocationSize=1)
public class ImmunizationHistoryMapper {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="t_immunization_history_mapper_seq")
	@Column(name="immunization_history_mapper_id")
	private Integer immunizationHistoryMapperId;
	
	@Column(name="immunization_date")
	private Date immunizationDate;
	
	@Column(name="encounter_id")
	private Integer encounterId;
	
	@Column(name="role_id")
	private Integer roleId;
	
	@Column(name="is_reviewed_flag")
	private char isReviewedFlag;
	
	@Column(name="patient_id")
	private Integer patientId;
	
	@Column(name="drug_id")
	private Integer drugId;
	
	@Column(name="created_by")
	private Integer createdBy;
	
	@Column(name="updated_by")
	private Integer updatedBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="status")
	private char status;
	
	@Column(name="is_administered_status")
	private char isAdministeredStatus;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "drug_id", insertable = false, nullable = false, updatable = false)
	private DrugMaster drugMaster;
	
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

	public Integer getImmunizationHistoryMapperId() {
		return immunizationHistoryMapperId;
	}

	public void setImmunizationHistoryMapperId(Integer immunizationHistoryMapperId) {
		this.immunizationHistoryMapperId = immunizationHistoryMapperId;
	}

	public Date getImmunizationDate() {
		return immunizationDate;
	}

	public void setImmunizationDate(Date immunizationDate) {
		this.immunizationDate = immunizationDate;
	}


	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getDrugId() {
		return drugId;
	}

	public void setDrugId(Integer drugId) {
		this.drugId = drugId;
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

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public char getIsAdministeredStatus() {
		return isAdministeredStatus;
	}

	public void setIsAdministeredStatus(char isAdministeredStatus) {
		this.isAdministeredStatus = isAdministeredStatus;
	}

	public DrugMaster getDrugMaster() {
		return drugMaster;
	}

	public void setDrugMaster(DrugMaster drugMaster) {
		this.drugMaster = drugMaster;
	}

	public PatientRegistration getPatientRegistration() {
		return patientRegistration;
	}

	public void setPatientRegistration(PatientRegistration patientRegistration) {
		this.patientRegistration = patientRegistration;
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
	
	
	
	
}
