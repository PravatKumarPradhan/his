package com.param.opd.appointment.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.global.model.PatientRegistration;
import com.param.opd.unit.model.SlotMaster;



@NamedQueries({
	
	@NamedQuery(name="PATIENT_AUTO_FILL_SEARCH" , query= "SELECT patient.patientId as patientId, "
			+ "concat(coalesce(patient.firstName) ,' ', coalesce(patient.middleName) ,' ', coalesce(patient.lastName)) as patientName, "
			+ "patient.uhidNumber as uhidNumber, "
			+ "patient.mobileNumber as mobileNumber "
			+ "FROM AppointmentMaster appointment "
			+ "INNER JOIN appointment.patientRegistration patient "
			+ "WHERE (LOWER(patient.firstName) LIKE :patientName "
			+ "OR LOWER(patient.lastName) LIKE :patientName "
			+ "OR LOWER(patient.uhidNumber) LIKE :patientName "
			+ "OR LOWER(patient.mobileNumber) LIKE :patientName ) "
			+ "AND appointment.status='A' "
			+ "AND patient.status='A' "
			+ "AND appointment.unitId=:unitId "
			+ "AND appointment.organizationId=:organizationId "),
	
})

@NamedNativeQueries({
	
	@NamedNativeQuery(name="GET_SCHEDULED_APPOINTMENTS_SLOTS_LIST",query=""
			+ "SELECT to_char(sm.appointment_date,'dd/MM/yyyy') as \"appointmentDate\", "
			+ "am.appointment_id as \"appointmentId\", "
			+ "sm.slot_id as \"slotId\", "
			+ "concat(to_char(sm.from_time,'HH24:MI')) as \"fromTimeString\", "
			+ "concat(to_char(sm.to_time,'HH24:MI')) as \"toTimeString\", "
			+ "am.remark as \"remark\", "
			+ "sm.doctor_id as \"doctorId\", "
			+ "am.appointment_type_id as \"appointmentTypeId\", "
			+ "am.appointment_status_id as \"appointmentStatusId\", "
			+ "asm.appointment_status_desc as \"appointmentStatusDesc\", "
			+ "concat('Dr. ',coalesce(doc.first_name),' ',coalesce(doc.middle_name),' ',coalesce(doc.last_name)) as \"doctorName\", "
			+ "sm.speciality_id as \"specialityId\", "
			+ "speciaityMaster.speciality_name as \"specialityName\", "
			+ "concat(coalesce(patient.first_name) ,' ', coalesce(patient.middle_name) ,' ', coalesce(patient.last_name)) as \"patientName\", "
			+ "concat(to_char(EXTRACT(year from AGE(CURRENT_TIMESTAMP, patient.dob)),'999'), 'Y') as \"age\", "
			+ "patient.mobile_number as \"mobileNumber\", "
			+ "patient.email as \"email\", "
			+ "patient.patient_id as \"patientId\", "
			+ "patient.uhid_number as \"uhidNumber\", "
			+ "patient.prefix_id as \"prefixId\", "
			+ "prefix.prefix_code as \"prefixCode\", " 
			+ "patient.first_name as \"firstName\", "
			+ "patient.middle_name as \"middleName\", "
			+ "patient.last_name as \"lastName\", "
			+ "patient.gender_id as \"genderId\", "
			+ "gender.gender_code as \"genderCode\", "
			+ "to_char(patient.dob,'DD/MM/YYYY') as \"dob\", "
			+ "sm.modality_id as \"modalityId\", "
			+ "modality.modality_desc as \"modalityDesc\", "
			//+ "sm.modality_type_id as \"modalityTypeId\", "
			//+ "modalityType.modality_type_desc as \"modalityTypeDesc\", "
			//+ "modalityType.speciality_id as \"modalitySpecialityId\", "
			+ "adm.admission_id as \"admissionId\" "
			+ "FROM opd.m_appointment_master am "
			+ "INNER JOIN opd.m_slot_master sm on am.slot_id=sm.slot_id "
			+ "LEFT OUTER JOIN doctor.m_speciality_master speciaityMaster on sm.speciality_id=speciaityMaster.speciality_id "
			+ "LEFT OUTER JOIN doctor.m_doctor_master doc on sm.doctor_id=doc.doctor_id "
			//+ "LEFT OUTER JOIN public.m_modality_type_master modalityType on sm.modality_type_id=modalityType.modality_type_id "
			+ "LEFT OUTER JOIN public.m_modality_master modality on sm.modality_id=modality.modality_id "
			+ "INNER JOIN patient.t_patient_registration patient on am.patient_id=patient.patient_id "
			+ "INNER JOIN public.m_gender_master gender on patient.gender_id=gender.gender_id "
			+ "INNER JOIN public.m_prefix_master prefix on patient.prefix_id=prefix.prefix_id "
			+ "INNER JOIN opd.m_appointment_status_master asm on am.appointment_status_id=asm.appointment_status_id "
			+ "LEFT OUTER JOIN adt.t_admission adm on adm.patient_id = am.patient_id AND am.appointment_status_id=1 AND adm.status='A' "
			+ "WHERE am.organization_id=:orgId "
			+ "AND am.unit_id=:unitId "
			+ "AND am.appointment_type_id=:appointmentTypeId "),
	
	@NamedNativeQuery(name="GET_BLOCKED_SCHEDULED_APPOINTMENTS_SLOTS_LIST",query=""
			+ "SELECT to_char(sm.appointment_date,'dd/MM/yyyy') as \"appointmentDate\", "
			+ "am.appointment_id as \"appointmentId\", "
			+ "sm.slot_id as \"slotId\", "
			+ "concat(to_char(sm.from_time,'HH24:MI')) as \"fromTimeString\", "
			+ "concat(to_char(sm.to_time,'HH24:MI')) as \"toTimeString\", "
			+ "am.remark as \"remark\", "
			+ "sm.doctor_id as \"doctorId\", "
			+ "am.appointment_type_id as \"appointmentTypeId\", "
			+ "am.appointment_status_id as \"appointmentStatusId\", "
			+ "asm.appointment_status_desc as \"appointmentStatusDesc\", "
			+ "concat('Dr. ',coalesce(doc.first_name),' ',coalesce(doc.middle_name),' ',coalesce(doc.last_name)) as \"doctorName\", "
			+ "sm.speciality_id as \"specialityId\", "
			+ "speciaityMaster.speciality_name as \"specialityName\", "
			+ "concat(coalesce(patient.first_name) ,' ', coalesce(patient.middle_name) ,' ', coalesce(patient.last_name)) as \"patientName\", "
			+ "concat(to_char(EXTRACT(year from AGE(CURRENT_TIMESTAMP, patient.dob)),'999'), 'Y') as \"age\", "
			+ "patient.mobile_number as \"mobileNumber\", "
			+ "patient.email as \"email\", "
			+ "patient.patient_id as \"patientId\", "
			+ "patient.uhid_number as \"uhidNumber\", "
			+ "patient.prefix_id as \"prefixId\", "
			+ "prefix.prefix_code as \"prefixCode\", " 
			+ "patient.first_name as \"firstName\", "
			+ "patient.middle_name as \"middleName\", "
			+ "patient.last_name as \"lastName\", "
			+ "patient.gender_id as \"genderId\", "
			+ "gender.gender_code as \"genderCode\", "
			+ "to_char(patient.dob,'DD/MM/YYYY') as \"dob\", "
			+ "sm.modality_id as \"modalityId\", "
			+ "modality.modality_desc as \"modalityDesc\", "
			+ "adm.admission_id as \"admissionId\", "
			+ "slud.reason_id as \"reasonId\", "
			+ "reason.reason_desc as \"reasonDesc\", "
			+ "slud.remark as \"blockedRemark\", "
			+ "to_char(slud.created_date,'dd/mm/yyyy HH:MI') as \"createdDate\" "
			+ "FROM opd.m_appointment_master am "
			+ "INNER JOIN opd.m_slot_master sm on am.slot_id=sm.slot_id "
			+ "LEFT OUTER JOIN doctor.m_speciality_master speciaityMaster on sm.speciality_id=speciaityMaster.speciality_id "
			+ "LEFT OUTER JOIN doctor.m_doctor_master doc on sm.doctor_id=doc.doctor_id "
			+ "LEFT OUTER JOIN opd.t_slot_block_unblock_detail slud on slud.slot_id=sm.slot_id "
			+ "LEFT OUTER JOIN adt.m_reason_master reason on slud.reason_id=reason.reason_master_id "
			+ "LEFT OUTER JOIN public.m_modality_master modality on sm.modality_id=modality.modality_id "
			+ "INNER JOIN patient.t_patient_registration patient on am.patient_id=patient.patient_id "
			+ "INNER JOIN public.m_gender_master gender on patient.gender_id=gender.gender_id "
			+ "INNER JOIN public.m_prefix_master prefix on patient.prefix_id=prefix.prefix_id "
			+ "INNER JOIN opd.m_appointment_status_master asm on am.appointment_status_id=asm.appointment_status_id "
			+ "LEFT OUTER JOIN adt.t_admission adm on adm.patient_id = am.patient_id AND am.appointment_status_id=1 AND adm.status='A' "
			+ "WHERE am.organization_id=:orgId "
			+ "AND am.unit_id=:unitId "
			+ "AND am.appointment_type_id=:appointmentTypeId "
			+ "AND sm.is_blocked=1 ")
})


@Entity
@Table(name="m_appointment_master", schema="opd")
@SequenceGenerator(name = "appointment_scheduling_master_seq", sequenceName = "opd.appointment_scheduling_master_seq", allocationSize = 1)
public class AppointmentMaster {
	@Id
	@Column(name="appointment_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointment_scheduling_master_seq")
	private int appointmentId;
	
	@Column(name = "unit_id")
	private Integer unitId;
	
	@Column(name = "organization_id")
	private Integer organizationId;
	
	@Column(name="slot_id")
	private Integer slotId;
	
	@Column(name="patient_id")
	private Integer patientId;
	
	@Column(name="remark")
	private String remark;
	
	@Column(name="appointment_status_id")
	private Integer appointmentStatusId;
	
	@Column(name="cancel_reason_id")
	private Integer cancelReasonId;
	
	@Column(name="cancelled_by")
	private char cancelledBy;
	
	@Column(name="reschedule_reason_id")
	private Integer rescheduleReasonId;
	
	@Column(name="rescheduled_by")
	private char rescheduledBy;
	
	@Column(name="appointment_type_id")
	private Integer appointmentTypeId;
	
	@Column(name="priority_id")
	private Integer priorityId;
	
	@Column(name="ehc_package_id")
	private Integer ehcPackageId;
	
	@Column(name = "status")
	private Character status;
	
	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "slot_id", insertable = false, nullable = false, updatable = false)
	private SlotMaster slotMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id", insertable = false, nullable = false, updatable = false)
	private PatientRegistration patientRegistration;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "appointment_status_id", insertable = false, nullable = false, updatable = false)
	private AppointmentStatusMaster appointmentStatusMaster;
	
	
	
	
	public Integer getEhcPackageId() {
		return ehcPackageId;
	}

	public void setEhcPackageId(Integer ehcPackageId) {
		this.ehcPackageId = ehcPackageId;
	}

	public Integer getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(Integer priorityId) {
		this.priorityId = priorityId;
	}

	public Integer getAppointmentTypeId() {
		return appointmentTypeId;
	}

	public void setAppointmentTypeId(Integer appointmentTypeId) {
		this.appointmentTypeId = appointmentTypeId;
	}

	public Integer getRescheduleReasonId() {
		return rescheduleReasonId;
	}

	public void setRescheduleReasonId(Integer rescheduleReasonId) {
		this.rescheduleReasonId = rescheduleReasonId;
	}

	public char getRescheduledBy() {
		return rescheduledBy;
	}

	public void setRescheduledBy(char rescheduledBy) {
		this.rescheduledBy = (rescheduledBy == '\u0000') ? 'P' : rescheduledBy;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
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

	public Integer getSlotId() {
		return slotId;
	}

	public void setSlotId(Integer slotId) {
		this.slotId = slotId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
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

	public Integer getAppointmentStatusId() {
		return appointmentStatusId;
	}

	public void setAppointmentStatusId(Integer appointmentStatusId) {
		this.appointmentStatusId = appointmentStatusId;
	}

	public Integer getCancelReasonId() {
		return cancelReasonId;
	}

	public void setCancelReasonId(Integer cancelReasonId) {
		this.cancelReasonId = cancelReasonId;
	}

	public char getCancelledBy() {
		return cancelledBy;
	}

	public void setCancelledBy(char cancelledBy) {
		this.cancelledBy = (cancelledBy == '\u0000') ? 'P' : cancelledBy;
	}

	/*public List<ComplaintAppointmentMapper> getComplaintAppointmentMapperList() {
		return ComplaintAppointmentMapperList;
	}

	public void setComplaintAppointmentMapperList(
			List<ComplaintAppointmentMapper> complaintAppointmentMapperList) {
		ComplaintAppointmentMapperList = complaintAppointmentMapperList;
	}*/

	/*public List<PatientFamilyHistory> getListPatientFamilyHistory() {
		return listPatientFamilyHistory;
	}

	public void setListPatientFamilyHistory(
			List<PatientFamilyHistory> listPatientFamilyHistory) {
		this.listPatientFamilyHistory = listPatientFamilyHistory;
	}
	*/
	
	
}
