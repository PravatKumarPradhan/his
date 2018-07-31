package com.param.adt.discharge.model;

import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.adt.admission.model.Admission;
import com.param.adt.master.global.model.OrganizationMaster;
import com.param.adt.master.global.model.ReasonMaster;
import com.param.adt.master.global.model.ShortLeaveReasonMaster;
import com.param.adt.master.global.model.UnitMaster;
import com.param.global.common.LocalTimeConverter;
import com.param.global.model.PatientRegistration;
import com.param.global.model.UnknownPatientRegistration;


@NamedNativeQueries({
	
	@NamedNativeQuery(name="GET_SHORT_LEAVE_REQUEST_LIST",query=
			"SELECT admission.admission_id as \"admissionId\", "
					+ "admission.patient_id as \"patientId\", "
					+ "admission.t_patient_id as \"tPatientId\", "
					+ "admission.admission_number as \"admissionNumber\", "
					+ "admission.doctor_id as \"doctorId\", "
					+ "admission.speciality_id as \"specialityId\", " 
					+ "to_char(adl.doa,'dd/MM/yyyy') as \"doa\", " 
					+ "to_char(adl.pdd,'dd/MM/yyyy') as \"pdd\", "
					+ "sp.speciality_name as \"specialityName\", "
					+ "pr.uhid_number as \"uhidNumber\", " 
					+ "concat(pr.first_name,' ',pr.middle_name,' ',pr.last_name) as \"patientName\" , " 
					+ "pr.gender_id as \"genderId\", " 
					+ "gm.gender_code as \"genderCode\", "
					+ "to_char(pr.dob,'MM/dd/yyyy') as \"dob\", " 
					+ "concat(doc.first_name,' ', doc.middle_name,' ',doc.last_name) as \"doctorName\", "
					+ "bm.floor_id as \"floorId\", "
					+ "floor.floor_name as \"floorName\", "
					+ "bm.bed_number as \"bedNumber\", "
					+ "bm.bed_id as \"bedId\", "
					+ "sl.short_leave_id as \"shortLeaveId\", "
					+ "sl.short_leave_reason_id as \"shortLeaveReasonId\", "
					+ "slr.short_leave_reason_name as \"shortLeaveReasonName\", "
					+ "to_char(sl.from_time,'HH:MI') as \"stringFromTime\", "
					+ "to_char(sl.to_time,'HH:MI') as \"stringToTime\", "
					+ "to_char(sl.from_date,'MM/dd/yyyy') as \"fromDate\", "
					+ "to_char(sl.to_date,'MM/dd/yyyy') as \"toDate\", "
					+ "to_char(sl.created_date,'MM/dd/yyyy') as \"createdDate\", "
					+ "sl.note as \"note\", "
					+ "sl.rejection_reason_id as \"rejectionReasonId\", "
					+ "reason.reason_desc as \"reasonDesc\", "
					+ "sl.doctors_note as \"doctorsNote\","
					+ "sl.billings_note as \"billingsNote\","
					+ "sl.short_leave_status_id as \"shortLeaveStatusId\", "
					+ "slrm.short_leave_desc as \"shortLeaveStatusName\", "
					+ "sl.is_handover_medication as \"isHandoverMedication\" "
					+ "FROM adt.t_short_leave sl "
					+ "INNER JOIN adt.t_admission admission on sl.admission_id=admission.admission_id " 
					+ "INNER JOIN patient.t_patient_registration pr on sl.patient_id=pr.patient_id "
					+ "INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id " 
					+ "INNER JOIN doctor.m_doctor_master doc on admission.doctor_id=doc.doctor_id "
					+ "INNER JOIN doctor.m_speciality_master sp on admission.speciality_id=sp.speciality_id " 
					+ "INNER JOIN adt.t_admission_details adl on adl.admission_id=admission.admission_id "
					+ "INNER JOIN adt.m_bed_master bm on adl.bed_id=bm.bed_id "
					+ "INNER JOIN adt.m_floor_master floor on bm.floor_id=floor.floor_id "
					+ "INNER JOIN adt.m_short_leave_reason_master slr on slr.short_leave_reason_id=sl.short_leave_reason_id "
					+ "LEFT JOIN adt.m_reason_master reason on sl.rejection_reason_id = reason.reason_master_id "
					+ " INNER JOIN adt.m_short_leave_status slrm on slrm.short_leave_status_id=sl.short_leave_status_id "
					+ "WHERE admission.status='A' "
					+ "AND adl.status='A' "
					+ "AND admission.organization_id=:organizationId " 
					+ "AND admission.unit_id=:unitId "
					+ "AND sl.status='A' "
					+ "AND sl.short_leave_status_id IN (:shortLeaveStatusIdList)"
					+ "UNION "
					+ "SELECT admission.admission_id as \"admissionId\", "
					+ "admission.patient_id as \"patientId\", "
					+ "admission.t_patient_id as \"tPatientId\", "
					+ "admission.admission_number as \"admissionNumber\", "
					+ "admission.doctor_id as \"doctorId\", "
					+ "admission.speciality_id as \"specialityId\", " 
					+ "to_char(adl.doa,'dd/MM/yyyy') as \"doa\", " 
					+ "to_char(adl.pdd,'dd/MM/yyyy') as \"pdd\", "
					+ "sp.speciality_name as \"specialityName\", "
					+ "pr.t_uhid as \"uhidNumber\", " 
					+ "pr.patient_name as \"patientName\" , " 
					+ "pr.gender_id as \"genderId\", " 
					+ "gm.gender_code as \"genderCode\", "
					+ "concat(to_char(pr.age,'999'),' ', pr.age_format) as \"dob\", "
					+ "concat(doc.first_name,' ', doc.middle_name,' ',doc.last_name) as \"doctorName\", "
					+ "bm.floor_id as \"floorId\", "
					+ "floor.floor_name as \"floorName\", "
					+ "bm.bed_number as \"bedNumber\", "
					+ "bm.bed_id as \"bedId\", "
					+ "sl.short_leave_id as \"shortLeaveId\", "
					+ "sl.short_leave_reason_id as \"shortLeaveReasonId\", "
					+ "slr.short_leave_reason_name as \"shortLeaveReasonName\", "
					+ "to_char(sl.from_time,'HH:MI') as \"stringFromTime\", "
					+ "to_char(sl.to_time,'HH:MI') as \"stringToTime\", "
					+ "to_char(sl.from_date,'MM/dd/yyyy') as \"fromDate\", "
					+ "to_char(sl.to_date,'MM/dd/yyyy') as \"toDate\", "
					+ "to_char(sl.created_date,'MM/dd/yyyy') as \"createdDate\", "
					+ "sl.note as \"note\", "
					+ "sl.rejection_reason_id as \"rejectionReasonId\", "
					+ "reason.reason_desc as \"reasonDesc\", "
					+ "sl.doctors_note as \"doctorsNote\","
					+ "sl.billings_note as \"billingsNote\","
					+ "sl.short_leave_status_id as \"shortLeaveStatusId\", "
					+ "slrm.short_leave_desc as \"shortLeaveStatusName\", "
					+ "sl.is_handover_medication as \"isHandoverMedication\" "
					+ " FROM adt.t_short_leave sl "
					+ " INNER JOIN adt.t_admission admission on sl.admission_id=admission.admission_id " 
					+ " INNER JOIN patient.m_unknown_patient_registration pr on sl.t_patient_id=pr.unknown_patient_id " 
					+ " INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id " 
					+ " INNER JOIN doctor.m_doctor_master doc on admission.doctor_id=doc.doctor_id "
					+ " INNER JOIN doctor.m_speciality_master sp on admission.speciality_id=sp.speciality_id "
					+ " INNER JOIN adt.t_admission_details adl on adl.admission_id=admission.admission_id "
					+ " INNER JOIN adt.m_bed_master bm on adl.bed_id=bm.bed_id " 
					+ " INNER JOIN adt.m_floor_master floor on bm.floor_id=floor.floor_id "
					+ " INNER JOIN adt.m_short_leave_reason_master slr on slr.short_leave_reason_id=sl.short_leave_reason_id "
					+ " LEFT JOIN adt.m_reason_master reason on sl.rejection_reason_id = reason.reason_master_id "
					+ " INNER JOIN adt.m_short_leave_status slrm on slrm.short_leave_status_id=sl.short_leave_status_id "
					+ " WHERE admission.status='A' "
					+ " AND adl.status='A' "
					+ " AND admission.organization_id=:organizationId "
					+ " AND admission.unit_id=:unitId "
					+ " AND sl.status='A' "
					+ " AND sl.short_leave_status_id IN (:shortLeaveStatusIdList)"
			),
	
	@NamedNativeQuery(name="GET_SHORT_LEAVE_REQUEST_LIST_FOR_DOCTOR",query=
			"SELECT admission.admission_id as \"admissionId\", "
					+ "admission.patient_id as \"patientId\", "
					+ "admission.t_patient_id as \"tPatientId\", "
					+ "admission.admission_number as \"admissionNumber\", "
					+ "admission.doctor_id as \"doctorId\", "
					+ "admission.speciality_id as \"specialityId\", " 
					+ "to_char(adl.doa,'dd/MM/yyyy') as \"doa\", " 
					+ "to_char(adl.pdd,'dd/MM/yyyy') as \"pdd\", "
					+ "sp.speciality_name as \"specialityName\", "
					+ "pr.uhid_number as \"uhidNumber\", " 
					+ "concat(pr.first_name,' ',pr.middle_name,' ',pr.last_name) as \"patientName\" , " 
					+ "pr.gender_id as \"genderId\", " 
					+ "gm.gender_code as \"genderCode\", "
					+ "to_char(pr.dob,'MM/dd/yyyy') as \"dob\", " 
					+ "concat(doc.first_name,' ', doc.middle_name,' ',doc.last_name) as \"doctorName\", "
					+ "bm.floor_id as \"floorId\", "
					+ "floor.floor_name as \"floorName\", "
					+ "bm.bed_number as \"bedNumber\", "
					+ "bm.bed_id as \"bedId\", "
					+ "sl.short_leave_id as \"shortLeaveId\", "
					+ "sl.short_leave_reason_id as \"shortLeaveReasonId\", "
					+ "slr.short_leave_reason_name as \"shortLeaveReasonName\", "
					+ "to_char(sl.from_time,'HH:MI') as \"stringFromTime\", "
					+ "to_char(sl.to_time,'HH:MI') as \"stringToTime\", "
					+ "to_char(sl.from_date,'MM/dd/yyyy') as \"fromDate\", "
					+ "to_char(sl.to_date,'MM/dd/yyyy') as \"toDate\", "
					+ "to_char(sl.created_date,'MM/dd/yyyy') as \"createdDate\", "
					+ "sl.note as \"note\", "
					+ "sl.rejection_reason_id as \"rejectionReasonId\", "
					+ "reason.reason_desc as \"reasonDesc\", "
					+ "sl.doctors_note as \"doctorsNote\","
					+ "sl.billings_note as \"billingsNote\","
					+ "sl.short_leave_status_id as \"shortLeaveStatusId\", "
					+ "slrm.short_leave_desc as \"shortLeaveStatusName\", "
					+ "sl.is_handover_medication as \"isHandoverMedication\" "
					+ "FROM adt.t_short_leave sl "
					+ "INNER JOIN adt.t_admission admission on sl.admission_id=admission.admission_id " 
					+ "INNER JOIN patient.t_patient_registration pr on sl.patient_id=pr.patient_id "
					+ "INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id " 
					+ "INNER JOIN doctor.m_doctor_master doc on admission.doctor_id=doc.doctor_id "
					+ "INNER JOIN doctor.m_speciality_master sp on admission.speciality_id=sp.speciality_id " 
					+ "INNER JOIN adt.t_admission_details adl on adl.admission_id=admission.admission_id "
					+ "INNER JOIN adt.m_bed_master bm on adl.bed_id=bm.bed_id "
					+ "INNER JOIN adt.m_floor_master floor on bm.floor_id=floor.floor_id "
					+ "INNER JOIN adt.m_short_leave_reason_master slr on slr.short_leave_reason_id=sl.short_leave_reason_id "
					+ "LEFT JOIN adt.m_reason_master reason on sl.rejection_reason_id = reason.reason_master_id "
					+ " INNER JOIN adt.m_short_leave_status slrm on slrm.short_leave_status_id=sl.short_leave_status_id "
					+ "WHERE admission.status='A' "
					+ "AND adl.status='A' "
					+ "AND admission.organization_id=:organizationId " 
					+ "AND admission.unit_id=:unitId "
					+ "AND sl.status='A' "
					+ "AND sl.short_leave_status_id = 1 "
					+ "AND sl.treating_doctor_id =:treatingDoctorId "
					+ "UNION "
					+ "SELECT admission.admission_id as \"admissionId\", "
					+ "admission.patient_id as \"patientId\", "
					+ "admission.t_patient_id as \"tPatientId\", "
					+ "admission.admission_number as \"admissionNumber\", "
					+ "admission.doctor_id as \"doctorId\", "
					+ "admission.speciality_id as \"specialityId\", " 
					+ "to_char(adl.doa,'dd/MM/yyyy') as \"doa\", " 
					+ "to_char(adl.pdd,'dd/MM/yyyy') as \"pdd\", "
					+ "sp.speciality_name as \"specialityName\", "
					+ "pr.t_uhid as \"uhidNumber\", " 
					+ "pr.patient_name as \"patientName\" , " 
					+ "pr.gender_id as \"genderId\", " 
					+ "gm.gender_code as \"genderCode\", "
					+ "concat(to_char(pr.age,'999'),' ', pr.age_format) as \"dob\", "
					+ "concat(doc.first_name,' ', doc.middle_name,' ',doc.last_name) as \"doctorName\", "
					+ "bm.floor_id as \"floorId\", "
					+ "floor.floor_name as \"floorName\", "
					+ "bm.bed_number as \"bedNumber\", "
					+ "bm.bed_id as \"bedId\", "
					+ "sl.short_leave_id as \"shortLeaveId\", "
					+ "sl.short_leave_reason_id as \"shortLeaveReasonId\", "
					+ "slr.short_leave_reason_name as \"shortLeaveReasonName\", "
					+ "to_char(sl.from_time,'HH:MI') as \"stringFromTime\", "
					+ "to_char(sl.to_time,'HH:MI') as \"stringToTime\", "
					+ "to_char(sl.from_date,'MM/dd/yyyy') as \"fromDate\", "
					+ "to_char(sl.to_date,'MM/dd/yyyy') as \"toDate\", "
					+ "to_char(sl.created_date,'MM/dd/yyyy') as \"createdDate\", "
					+ "sl.note as \"note\", "
					+ "sl.rejection_reason_id as \"rejectionReasonId\", "
					+ "reason.reason_desc as \"reasonDesc\", "
					+ "sl.doctors_note as \"doctorsNote\","
					+ "sl.billings_note as \"billingsNote\","
					+ "sl.short_leave_status_id as \"shortLeaveStatusId\", "
					+ "slrm.short_leave_desc as \"shortLeaveStatusName\", "
					+ "sl.is_handover_medication as \"isHandoverMedication\" "
					+ " FROM adt.t_short_leave sl "
					+ " INNER JOIN adt.t_admission admission on sl.admission_id=admission.admission_id " 
					+ " INNER JOIN patient.m_unknown_patient_registration pr on sl.t_patient_id=pr.unknown_patient_id " 
					+ " INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id " 
					+ " INNER JOIN doctor.m_doctor_master doc on admission.doctor_id=doc.doctor_id "
					+ " INNER JOIN doctor.m_speciality_master sp on admission.speciality_id=sp.speciality_id "
					+ " INNER JOIN adt.t_admission_details adl on adl.admission_id=admission.admission_id "
					+ " INNER JOIN adt.m_bed_master bm on adl.bed_id=bm.bed_id " 
					+ " INNER JOIN adt.m_floor_master floor on bm.floor_id=floor.floor_id "
					+ " INNER JOIN adt.m_short_leave_reason_master slr on slr.short_leave_reason_id=sl.short_leave_reason_id "
					+ " LEFT JOIN adt.m_reason_master reason on sl.rejection_reason_id = reason.reason_master_id "
					+ " INNER JOIN adt.m_short_leave_status slrm on slrm.short_leave_status_id=sl.short_leave_status_id "
					+ " WHERE admission.status='A' "
					+ " AND adl.status='A' "
					+ " AND admission.organization_id=:organizationId "
					+ " AND admission.unit_id=:unitId "
					+ " AND sl.status='A' "
					+ " AND sl.short_leave_status_id = 1 "
					+ " AND sl.treating_doctor_id =:treatingDoctorId "
	)
})


@Entity
@Table(name="t_short_leave",schema="adt")
@SequenceGenerator(name="short_leave_seq",sequenceName="adt.short_leave_seq",allocationSize=1)
public class ShortLeave {

	@Id
	@Column(name="short_leave_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="short_leave_seq")
	private int shortLeaveId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="patient_id")
	private Integer patientId;
	
	@Column(name="t_patient_id")
	private Integer tPatientId;
	
	@Column(name="admission_id")
	private Integer admissionId;
	
	@Column(name="short_leave_reason_id")
	private Integer shortLeaveReasonId;
	
	@Column(name="from_time")
	@Convert(converter = LocalTimeConverter.class)
	private LocalTime fromTime;
	
	@Column(name="to_time")
	@Convert(converter = LocalTimeConverter.class)
	private LocalTime toTime;
	
	@Column(name="from_date")
	private Date fromDate;
	
	@Column(name="to_date")
	private Date toDate;
	
	@Column(name="note")
	private String note;
	
	@Column(name="rejection_reason_id")
	private Integer rejectionReasonId;

	@Column(name="authorized_by")
	private Integer authorizedBy;
	
	@Column(name="status")
	private char status;

	@Column(name="created_date")
	private Date createdDate;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="updated_by")
	private int updatedBy;

	@Column(name="updated_date")
	private Date updatedDate;

	@Column(name="short_leave_status_id")
	private Integer shortLeaveStatusId;
	
	@Column(name="treating_doctor_id")
	private Integer treatingDoctorId;
	
	@Column(name="doctors_note")
	private String doctorsNote;
	
	@Column(name="billings_note")
	private String billingsNote;
	
	@Column(name="is_handover_medication")
	private char isHandoverMedication;
	
	public char getIsHandoverMedication() {
		return isHandoverMedication;
	}

	public void setIsHandoverMedication(char isHandoverMedication) {
		this.isHandoverMedication = (isHandoverMedication == '\u0000') ? 'N' : isHandoverMedication;
	}

	public String getDoctorsNote() {
		return doctorsNote;
	}

	public void setDoctorsNote(String doctorsNote) {
		this.doctorsNote = doctorsNote;
	}

	public String getBillingsNote() {
		return billingsNote;
	}

	public void setBillingsNote(String billingsNote) {
		this.billingsNote = billingsNote;
	}

	public Integer getTreatingDoctorId() {
		return treatingDoctorId;
	}

	public void setTreatingDoctorId(Integer treatingDoctorId) {
		this.treatingDoctorId = treatingDoctorId;
	}

	public Integer getShortLeaveStatusId() {
		return shortLeaveStatusId;
	}

	public void setShortLeaveStatusId(Integer shortLeaveStatusId) {
		this.shortLeaveStatusId = shortLeaveStatusId;
	}

	public int getShortLeaveId() {
		return shortLeaveId;
	}

	public void setShortLeaveId(int shortLeaveId) {
		this.shortLeaveId = shortLeaveId;
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

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer gettPatientId() {
		return tPatientId;
	}

	public void settPatientId(Integer tPatientId) {
		this.tPatientId = tPatientId;
	}

	public Integer getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(Integer admissionId) {
		this.admissionId = admissionId;
	}

	public Integer getShortLeaveReasonId() {
		return shortLeaveReasonId;
	}

	public void setShortLeaveReasonId(Integer shortLeaveReasonId) {
		this.shortLeaveReasonId = shortLeaveReasonId;
	}

	public LocalTime getFromTime() {
		return fromTime;
	}

	public LocalTime getToTime() {
		return toTime;
	}

	public void setToTime(LocalTime toTime) {
		this.toTime = toTime;
	}

	public void setFromTime(LocalTime fromTime) {
		this.fromTime = fromTime;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getRejectionReasonId() {
		return rejectionReasonId;
	}

	public void setRejectionReasonId(Integer rejectionReasonId) {
		this.rejectionReasonId = rejectionReasonId;
	}

	public Integer getAuthorizedBy() {
		return authorizedBy;
	}

	public void setAuthorizedBy(Integer authorizedBy) {
		this.authorizedBy = authorizedBy;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = (status == '\u0000') ? 'A' : status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, nullable = false, updatable = false)
	private OrganizationMaster organizationMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id", insertable = false, nullable = false, updatable = false)
	private UnitMaster unitMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admission_id", insertable = false, nullable = false, updatable = false)
	private Admission admission;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id", insertable = false, nullable = false, updatable = false)
	private PatientRegistration patientRegistration;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_patient_id", insertable = false, nullable = false, updatable = false)
	private UnknownPatientRegistration unknownPatientRegistration;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "short_leave_reason_id", insertable = false, nullable = false, updatable = false)
	private ShortLeaveReasonMaster shortLeaveReasonMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rejection_reason_id", insertable = false, nullable = false, updatable = false)
	private ReasonMaster reasonMaster;
	
	
	
	
	
}


