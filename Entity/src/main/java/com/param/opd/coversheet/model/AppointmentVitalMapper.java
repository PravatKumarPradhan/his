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
import com.param.global.model.VitalMaster;
import com.param.opd.appointment.model.AppointmentMaster;

@NamedQueries({
	
	
	
	@NamedQuery(name="GET_LIST_APPOINTMENT_VITAL_MAPPER_BY_PATIENT_ID",
	query=" SELECT 		appVitalMapp.appointmentVitalMapperId as appointmentVitalMapperId, appVitalMapp.vitalId as vitalId,"
		+ "				appVitalMapp.encounterId as encounterId, appVitalMapp.patientId as patientId, "
		+ "				appVitalMapp.value as value, appVitalMapp.status as status, appVitalMapp.isSelfTriage as isSelfTriage,"
		+ "				appVitalMapp.createdBy as createdBy, appVitalMapp.updatedBy as updatedBy, "
		+ "				to_char(appVitalMapp.createdDate,'DD-MM-YYYY') as createdDate, to_char(appVitalMapp.updatedDate,'DD-MM-YYYY') as updatedDate,"
		+ "				appVitalMapp.unitId as unitId, appVitalMapp.organizationId as organizationId, "
		+ "				vitalMst.vitalName as vitalName,"
		+ " 			appVitalMapp.roleId as roleId, appVitalMapp.isReviewedFlag as isReviewedFlag "
		+ " FROM 		AppointmentVitalMapper appVitalMapp"
		+ " INNER JOIN 	appVitalMapp.vitalMaster vitalMst"
		+ " WHERE 		appVitalMapp.unitId=:unitId"
		+ " AND			appVitalMapp.organizationId=:organizationId"
		+ " AND 		appVitalMapp.patientId=:patientId"
		+ " ORDER BY 	appVitalMapp.appointmentVitalMapperId desc"
	),
	
	
	@NamedQuery(name="GET_LIST_APPOINTMENT_VITAL_MAPPER",
			query=" SELECT 		appVitalMapp.appointmentVitalMapperId as appointmentVitalMapperId, appVitalMapp.vitalId as vitalId,"
				+ "				appVitalMapp.encounterId as encounterId, appVitalMapp.patientId as patientId, "
				+ "				appVitalMapp.value as value, appVitalMapp.status as status, appVitalMapp.isSelfTriage as isSelfTriage,"
				+ "				appVitalMapp.createdBy as createdBy, appVitalMapp.updatedBy as updatedBy, "
				+ "				to_char(appVitalMapp.createdDate,'DD-MM-YYYY') as createdDate, to_char(appVitalMapp.updatedDate,'DD-MM-YYYY') as updatedDate,"
				+ "				appVitalMapp.unitId as unitId, appVitalMapp.organizationId as organizationId, "
				+ "				vitalMst.vitalName as vitalName,"
				+ " 			appVitalMapp.roleId as roleId, appVitalMapp.isReviewedFlag as isReviewedFlag "
				+ " FROM 		AppointmentVitalMapper appVitalMapp"
				+ " INNER JOIN 	appVitalMapp.vitalMaster vitalMst"
				+ " WHERE 		appVitalMapp.unitId=:unitId"
				+ " AND			appVitalMapp.organizationId=:organizationId"
				+ " AND 		appVitalMapp.vitalId=:vitalId"	
				+ " AND 		appVitalMapp.patientId=:patientId"
				+ " AND 		appVitalMapp.encounterId!=:encounterId"
				+ " AND 		appVitalMapp.createdDate BETWEEN :formDate AND :toDate "
				+ " ORDER BY 	appVitalMapp.appointmentVitalMapperId desc"
			),
	
	@NamedQuery(name="UPDATE_OLD_VITAL",
			query=" UPDATE 		AppointmentVitalMapper appointVitalMapp "
				+ " SET 		appointVitalMapp.status = 'I' "
				+ " WHERE 		appointVitalMapp.patientId=:patientId AND appointVitalMapp.encounterId=:encounterId "
				+ " AND 		appointVitalMapp.vitalId=:vitalId AND appointVitalMapp.roleId=:roleId "
			),
			
			@NamedQuery(name="GET_LIST_APPOINTMENT_VITAL",
			query=" SELECT 		appVitalMapp.appointmentVitalMapperId as appointmentVitalMapperId, appVitalMapp.vitalId as vitalId,"
				+ "				appVitalMapp.encounterId as encounterId, appVitalMapp.patientId as patientId, "
				+ "				appVitalMapp.value as value, appVitalMapp.status as status, appVitalMapp.isSelfTriage as isSelfTriage,"
				+ "				appVitalMapp.createdBy as createdBy, appVitalMapp.updatedBy as updatedBy, "
				+ "				to_char(appVitalMapp.createdDate,'DD-MM-YYYY') as createdDate, to_char(appVitalMapp.updatedDate,'DD-MM-YYYY') as updatedDate,"
				+ "				appVitalMapp.unitId as unitId, appVitalMapp.organizationId as organizationId, "
				+ "				vitalMst.vitalName as vitalName,"
				+ " 			appVitalMapp.roleId as roleId, appVitalMapp.isReviewedFlag as isReviewedFlag "
				+ " FROM 		AppointmentVitalMapper appVitalMapp"
				+ " INNER JOIN 	appVitalMapp.vitalMaster vitalMst"
				+ " WHERE 		appVitalMapp.unitId=:unitId"
				+ " AND			appVitalMapp.organizationId=:organizationId"
				+ " AND 		appVitalMapp.encounterId!=:encounterId"
				+ " AND 		appVitalMapp.patientId=:patientId"
				+ " AND 		appVitalMapp.status= 'A' "		
				
			),
			
			@NamedQuery(name="GET_LIST_APPOINTMENT_VITAL_MAPPER_BY_ROLE_ID",
			query=" SELECT 		appVitalMapp.appointmentVitalMapperId as appointmentVitalMapperId, appVitalMapp.vitalId as vitalId,"
				+ "				appVitalMapp.encounterId as encounterId, appVitalMapp.patientId as patientId, "
				+ "				appVitalMapp.value as value, appVitalMapp.status as status, appVitalMapp.isSelfTriage as isSelfTriage,"
				+ "				appVitalMapp.createdBy as createdBy, appVitalMapp.updatedBy as updatedBy, "
				+ "				to_char(appVitalMapp.createdDate,'DD-MM-YYYY') as createdDate, to_char(appVitalMapp.updatedDate,'DD-MM-YYYY') as updatedDate,"
				+ "				appVitalMapp.unitId as unitId, appVitalMapp.organizationId as organizationId, "
				+ "				vitalMst.vitalName as vitalName,"
				+ " 			appVitalMapp.roleId as roleId, appVitalMapp.isReviewedFlag as isReviewedFlag "
				+ " FROM 		AppointmentVitalMapper appVitalMapp"
				+ " INNER JOIN 	appVitalMapp.vitalMaster vitalMst"
				+ " WHERE 		appVitalMapp.unitId=:unitId"
				+ " AND			appVitalMapp.organizationId=:organizationId"
				+ " AND 		appVitalMapp.encounterId!=:encounterId"
				+ " AND 		appVitalMapp.roleId=:roleId"		
				+ " AND 		appVitalMapp.patientId=:patientId"
				+ " AND 		appVitalMapp.status= 'A' "		
				
			),
			
			@NamedQuery(name="GET_CURRENT_LIST_APPOINTMENT_VITAL",
			query=" SELECT 		appVitalMapp.appointmentVitalMapperId as appointmentVitalMapperId, appVitalMapp.vitalId as vitalId,"
				+ "				appVitalMapp.encounterId as encounterId, appVitalMapp.patientId as patientId, "
				+ "				appVitalMapp.value as value, appVitalMapp.status as status, appVitalMapp.isSelfTriage as isSelfTriage,"
				+ "				appVitalMapp.createdBy as createdBy, appVitalMapp.updatedBy as updatedBy, "
				+ "				to_char(appVitalMapp.createdDate,'DD-MM-YYYY') as createdDate, to_char(appVitalMapp.updatedDate,'DD-MM-YYYY') as updatedDate,"
				+ "				appVitalMapp.unitId as unitId, appVitalMapp.organizationId as organizationId, "
				+ "				vitalMst.vitalName as vitalName,"
				+ " 			appVitalMapp.roleId as roleId, appVitalMapp.isReviewedFlag as isReviewedFlag "
				+ " FROM 		AppointmentVitalMapper appVitalMapp"
				+ " INNER JOIN 	appVitalMapp.vitalMaster vitalMst"
				+ " WHERE 		appVitalMapp.unitId=:unitId"
				+ " AND			appVitalMapp.organizationId=:organizationId"
				+ " AND 		appVitalMapp.encounterId=:encounterId"
				+ " AND 		appVitalMapp.patientId=:patientId"
				+ " AND 		appVitalMapp.status= 'A' "		
				
			),
			
			@NamedQuery(name="GET_CURRENT_LIST_APPOINTMENT_VITAL_MAPPER_BY_ROLE_ID",
			query=" SELECT 		appVitalMapp.appointmentVitalMapperId as appointmentVitalMapperId, appVitalMapp.vitalId as vitalId,"
				+ "				appVitalMapp.encounterId as encounterId, appVitalMapp.patientId as patientId, "
				+ "				appVitalMapp.value as value, appVitalMapp.status as status, appVitalMapp.isSelfTriage as isSelfTriage,"
				+ "				appVitalMapp.createdBy as createdBy, appVitalMapp.updatedBy as updatedBy, "
				+ "				to_char(appVitalMapp.createdDate,'DD-MM-YYYY') as createdDate, to_char(appVitalMapp.updatedDate,'DD-MM-YYYY') as updatedDate,"
				+ "				appVitalMapp.unitId as unitId, appVitalMapp.organizationId as organizationId, "
				+ "				vitalMst.vitalName as vitalName,"
				+ " 			appVitalMapp.roleId as roleId, appVitalMapp.isReviewedFlag as isReviewedFlag "
				+ " FROM 		AppointmentVitalMapper appVitalMapp"
				+ " INNER JOIN 	appVitalMapp.vitalMaster vitalMst"
				+ " WHERE 		appVitalMapp.unitId=:unitId"
				+ " AND			appVitalMapp.organizationId=:organizationId"
				+ " AND 		appVitalMapp.encounterId=:encounterId"
				+ " AND 		appVitalMapp.roleId=:roleId"		
				+ " AND 		appVitalMapp.patientId=:patientId"
				+ " AND 		appVitalMapp.status= 'A' "		
				
			),
	
})

@Entity
@Table(name = "t_appointment_vital_mapper", schema = "patient")
@SequenceGenerator(name = "appointment_vital_mapper_seq", sequenceName = "patient.appointment_vital_mapper_seq", allocationSize = 1)
public class AppointmentVitalMapper {

	@Id
	@Column(name = "appointment_vital_mapper_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointment_vital_mapper_seq")
	private Integer appointmentVitalMapperId;
	
	@Column(name = "vital_id")
	private Integer vitalId;
	
	/*@Column(name = "appointment_id")
	private Integer appointmentId;*/
	
	@Column(name = "patient_id")
	private Integer patientId;

	@Column(name = "value")
	private String value;
	
	@Column(name = "status")
	private char status;
	
	@Column(name = "is_self_triage")
	private char isSelfTriage;
	
	@Column(name = "created_by")
	private Integer createdBy;
	
	@Column(name = "createdDate")
	private Date createdDate;
	
	@Column(name = "updated_by")
	private Integer updatedBy;
	
	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name = "unit_id")
	private Integer unitId;
	
	@Column(name = "organization_id")
	private Integer organizationId;
	
	@Column(name="encounter_id")
	private Integer encounterId;
	
	@Column(name="role_id")
	private Integer roleId;
	
	@Column(name="is_reviewed_flag")
	private char isReviewedFlag;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id", insertable = false, updatable = false)
	private PatientRegistration patientRegistration;
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "appointment_id", insertable = false, nullable = false, updatable = false)
	private AppointmentMaster appointmentMaster;*/
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vital_id", insertable = false, nullable = false, updatable = false)
	private VitalMaster vitalMaster;

	
	
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

	public Integer getAppointmentVitalMapperId() {
		return appointmentVitalMapperId;
	}

	public void setAppointmentVitalMapperId(Integer appointmentVitalMapperId) {
		this.appointmentVitalMapperId = appointmentVitalMapperId;
	}

	public Integer getVitalId() {
		return vitalId;
	}

	public void setVitalId(Integer vitalId) {
		this.vitalId = vitalId;
	}

	

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public char getIsSelfTriage() {
		return isSelfTriage;
	}

	public void setIsSelfTriage(char isSelfTriage) {
		this.isSelfTriage = isSelfTriage;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
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

	public PatientRegistration getPatientRegistration() {
		return patientRegistration;
	}

	public void setPatientRegistration(PatientRegistration patientRegistration) {
		this.patientRegistration = patientRegistration;
	}


	public VitalMaster getVitalMaster() {
		return vitalMaster;
	}

	public void setVitalMaster(VitalMaster vitalMaster) {
		this.vitalMaster = vitalMaster;
	}
	
	
}
