package com.param.adt.transfer.model;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.adt.admission.model.Admission;
import com.param.adt.master.global.model.OrganizationMaster;
import com.param.adt.master.global.model.ReasonMaster;
import com.param.adt.master.global.model.UnitMaster;
import com.param.global.model.DoctorMaster;
import com.param.global.model.SpecialityMaster;

@NamedNativeQueries({
	@NamedNativeQuery(name="GET_TRANSFER_OF_CARE_REQ_LIST",query="SELECT transfer_of_care_id as \"transferOfCareId\", "
			+ "admission.patient_id as \"patientId\", "
			+ "admission.t_patient_id as \"tPatientId\", "
			+ "tcr.request_to as \"requestTo\", "
			+ "tcr.speciality_id as \"specialityId\", " 
			+ "sp.speciality_name as \"specialityName\", "
			+ "pr.uhid_number as \"UHIDNumber\", " 
			+ "concat(pr.first_name,' ',pr.middle_name,' ',pr.last_name) as \"patientName\" , " 
			+ "pr.gender_id as \"genderId\", " 
			+ "gm.gender_code as \"genderCode\", "
			+ "to_char(pr.dob,'MM/dd/yyyy') as \"dob\", " 
			+ "concat(doc.first_name,' ', doc.middle_name,' ',doc.last_name) as \"doctorName\", "
			+ "bm.bed_number as \"bedNumber\", "
			+ "bm.bed_id as \"bedId\", "
			+ "tcr.reason_id as \"reasonId\", "
			+" reason.reason_desc as \"reasonDesc\", "
			+ "tcr.note as \"note\", "
			+ "tcr.transfer_status_id as \"transferStatusId\", "
			+ "trastat.transfer_status_desc as \"transferStatusDesc\", "
			+ "tcr.remark as \"remark\", "
			+ "tcr.reject_reason_id as \"rejectReasonId\", "
			+" reason2.reason_desc as \"rejectReasonDesc\" "
			+ "FROM adt.t_transfer_of_care_request tcr "
			+ "INNER JOIN adt.t_admission admission on tcr.admission_id=admission.admission_id "
			+ "INNER JOIN patient.t_patient_registration pr on admission.patient_id=pr.patient_id "
			+ "INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id " 
			+ "INNER JOIN doctor.m_doctor_master doc on tcr.request_to=doc.doctor_id "
			+ "INNER JOIN doctor.m_speciality_master sp on tcr.speciality_id=sp.speciality_id " 
			+ "INNER JOIN adt.t_admission_details adl on adl.admission_id=admission.admission_id "
			+ "INNER JOIN adt.m_bed_master bm on adl.bed_id=bm.bed_id "
			+" INNER JOIN adt.m_reason_master reason on tcr.reason_id=reason.reason_master_id "
			+ " LEFT JOIN adt.m_reason_master reason2 on tcr.reject_reason_id=reason2.reason_master_id "
			+" INNER JOIN adt.m_transfer_status_master trastat on tcr.transfer_status_id=trastat.transfer_status_id  "
			+ "WHERE tcr.transfer_type_id=:transferTypeId "
			//+ "AND tcr.transfer_status_id=1 "
			+ "AND tcr.organization_id=:organizationId "
			+ "AND tcr.unit_id=:unitId "
			+ " union "
			+ "SELECT transfer_of_care_id as \"transferOfCareId\", "
			+ "admission.patient_id as \"patientId\", "
			+ "admission.t_patient_id as \"tPatientId\", "
			+ "tcr.request_to as \"requestTo\", "
			+ "tcr.speciality_id as \"specialityId\", " 
			+ "sp.speciality_name as \"specialityName\", "
			+ "pr.t_uhid as \"UHIDNumber\", " 
			+ "pr.patient_name as \"patientName\" , " 
			+ "pr.gender_id as \"genderId\", " 
			+ "gm.gender_code as \"genderCode\", "
			+ "concat(to_char(pr.age,'999'),' ', pr.age_format) as \"dob\", "
			+ "concat(doc.first_name,' ', doc.middle_name,' ',doc.last_name) as \"doctorName\", "
			+ "bm.bed_number as \"bedNumber\", "
			+ "bm.bed_id as \"bedId\", "
			+ "tcr.reason_id as \"reasonId\", "
			+" reason.reason_desc as \"reasonDesc\", "
			+ "tcr.note as \"note\", "
			+ "tcr.transfer_status_id as \"transferStatusId\", "
			+ "trastat.transfer_status_desc as \"transferStatusDesc\", "
			+ "tcr.remark as \"remark\", "
			+ "tcr.reject_reason_id as \"rejectReasonId\", "
			+" reason2.reason_desc as \"rejectReasonDesc\" "
			+ "FROM adt.t_transfer_of_care_request tcr "
			+ "INNER JOIN adt.t_admission admission on tcr.admission_id=admission.admission_id "
			+ "INNER JOIN patient.m_unknown_patient_registration pr on admission.t_patient_id=pr.unknown_patient_id "
			+ "INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id " 
			+ "INNER JOIN doctor.m_doctor_master doc on tcr.request_to=doc.doctor_id "
			+ "LEFT JOIN doctor.m_speciality_master sp on tcr.speciality_id=sp.speciality_id " 
			+ "INNER JOIN adt.t_admission_details adl on adl.admission_id=admission.admission_id "
			+ "INNER JOIN adt.m_bed_master bm on adl.bed_id=bm.bed_id "
			+ " INNER JOIN adt.m_reason_master reason on tcr.reason_id=reason.reason_master_id "
			+ " LEFT JOIN adt.m_reason_master reason2 on tcr.reject_reason_id=reason2.reason_master_id "
			+ " INNER JOIN adt.m_transfer_status_master trastat on tcr.transfer_status_id=trastat.transfer_status_id  "
			+ "WHERE tcr.transfer_type_id=:transferTypeId "
			//+ "AND tcr.transfer_status_id=1 "
			+ "AND tcr.organization_id=:organizationId "
			+ "AND tcr.unit_id=:unitId "
			),
	
	@NamedNativeQuery(name="GET_TRANSFER_OF_CARE_REQ_LIST_FOR_DOC",query="SELECT transfer_of_care_id as \"transferOfCareId\", "
			+ "admission.patient_id as \"patientId\", "
			+ "admission.t_patient_id as \"tPatientId\", "
			+ "admission.admission_id as \"admissionId\", "
			+ "tcr.request_to as \"requestTo\", "
			+ "tcr.request_By as \"requestBy\", "
			+ "tcr.speciality_id as \"toSpecialityId\", "
			+ "admission.speciality_id as \"bySpecialityId\", "
			+ "sp.speciality_name as \"toSpecialityName\", "
			+ "sp2.speciality_name as \"bySpecialityName\", "
			+ "pr.uhid_number as \"UHIDNumber\", " 
			+ "concat(pr.first_name,' ',pr.middle_name,' ',pr.last_name) as \"patientName\" , " 
			+ "pr.gender_id as \"genderId\", " 
			+ "gm.gender_code as \"genderCode\", "
			+ "to_char(pr.dob,'MM/dd/yyyy') as \"dob\", " 
			+ "concat(doc.first_name,' ', doc.middle_name,' ',doc.last_name) as \"doctorName\", "
			+ "bm.bed_number as \"bedNumber\", "
			+ "bm.bed_id as \"bedId\", "
			+ "tcr.reason_id as \"reasonId\", "
			+" reason.reason_desc as \"reasonDesc\", "
			+ "tcr.note as \"note\", "
			+ "tcr.transfer_status_id as \"transferStatusId\", "
			+ "trastat.transfer_status_desc as \"transferStatusDesc\", "
			+ "nurWard.nursing_station_id as \"nursingStationId\", "
			+ "nur.nursing_station_desc as \"nursingStationDesc\", "
			+ "tcr.transfer_type_id as \"transferTypeId\", "
			+ "ward.ward_name as \"wardName\" "
			+ "FROM adt.t_transfer_of_care_request tcr "
			+ "INNER JOIN adt.t_admission admission on tcr.admission_id=admission.admission_id "
			+ "INNER JOIN patient.t_patient_registration pr on admission.patient_id=pr.patient_id "
			+ "INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id " 
			+ "INNER JOIN doctor.m_doctor_master doc on tcr.request_by=doc.doctor_id "
			+ "INNER JOIN doctor.m_speciality_master sp on tcr.speciality_id=sp.speciality_id " 
			+ "INNER JOIN doctor.m_speciality_master sp2 on admission.speciality_id=sp2.speciality_id " 
			+ "INNER JOIN adt.t_admission_details adl on adl.admission_id=admission.admission_id "
			+ "INNER JOIN adt.m_bed_master bm on adl.bed_id=bm.bed_id "
			+ "INNER JOIN adt.m_ward_master ward on bm.ward_id=ward.ward_id "
			+ "LEFT JOIN adt.t_nursing_station_ward_mapper nurWard on ward.ward_id=nurWard.ward_id "
			+ "LEFT JOIN adt.m_nursing_station_master nur on nurWard.nursing_station_id=nur.nursing_station_id " 
			+" INNER JOIN adt.m_reason_master reason on tcr.reason_id=reason.reason_master_id "
			+" INNER JOIN adt.m_transfer_status_master trastat on tcr.transfer_status_id=trastat.transfer_status_id  "
			+ "WHERE tcr.transfer_type_id=:transferTypeId "
			+ "AND tcr.transfer_status_id=1 "
			+ "AND tcr.organization_id=:organizationId "
			+ "AND tcr.unit_id=:unitId "
			+ "AND admission.status='A' "
			+ " union "
			+ "SELECT transfer_of_care_id as \"transferOfCareId\", "
			+ "admission.patient_id as \"patientId\", "
			+ "admission.t_patient_id as \"tPatientId\", "
			+ "admission.admission_id as \"admissionId\", "
			+ "tcr.request_to as \"requestTo\", "
			+ "tcr.request_By as \"requestBy\", "
			+ "tcr.speciality_id as \"toSpecialityId\", "
			+ "admission.speciality_id as \"bySpecialityId\", "
			+ "sp.speciality_name as \"toSpecialityName\", "
			+ "sp2.speciality_name as \"bySpecialityName\", "
			+ "pr.t_uhid as \"UHIDNumber\", " 
			+ "pr.patient_name as \"patientName\" , " 
			+ "pr.gender_id as \"genderId\", " 
			+ "gm.gender_code as \"genderCode\", "
			+ "concat(to_char(pr.age,'999'),' ', pr.age_format) as \"dob\", "
			+ "concat(doc.first_name,' ', doc.middle_name,' ',doc.last_name) as \"doctorName\", "
			+ "bm.bed_number as \"bedNumber\", "
			+ "bm.bed_id as \"bedId\", "
			+ "tcr.reason_id as \"reasonId\", "
			+" reason.reason_desc as \"reasonDesc\", "
			+ "tcr.note as \"note\", "
			+ "tcr.transfer_status_id as \"transferStatusId\", "
			+ "trastat.transfer_status_desc as \"transferStatusDesc\", "
			+ "nurWard.nursing_station_id as \"nursingStationId\", "
			+ "nur.nursing_station_desc as \"nursingStationDesc\","
			+ "tcr.transfer_type_id as \"transferTypeId\", "
			+ "ward.ward_name as \"wardName\" "
			+ "FROM adt.t_transfer_of_care_request tcr "
			+ "INNER JOIN adt.t_admission admission on tcr.admission_id=admission.admission_id "
			+ "INNER JOIN patient.m_unknown_patient_registration pr on admission.t_patient_id=pr.unknown_patient_id "
			+ "INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id " 
			+ "INNER JOIN doctor.m_doctor_master doc on tcr.request_by=doc.doctor_id "
			+ "LEFT JOIN doctor.m_speciality_master sp on tcr.speciality_id=sp.speciality_id " 
			+ "LEFT JOIN doctor.m_speciality_master sp2 on admission.speciality_id=sp2.speciality_id " 
			+ "INNER JOIN adt.t_admission_details adl on adl.admission_id=admission.admission_id "
			+ "INNER JOIN adt.m_bed_master bm on adl.bed_id=bm.bed_id "
			+ "INNER JOIN adt.m_ward_master ward on bm.ward_id=ward.ward_id "
			+ "LEFT JOIN adt.t_nursing_station_ward_mapper nurWard on ward.ward_id=nurWard.ward_id "
			+ "LEFT JOIN adt.m_nursing_station_master nur on nurWard.nursing_station_id=nur.nursing_station_id " 
			+ " INNER JOIN adt.m_reason_master reason on tcr.reason_id=reason.reason_master_id "
			+ " INNER JOIN adt.m_transfer_status_master trastat on tcr.transfer_status_id=trastat.transfer_status_id  "
			+ "WHERE tcr.transfer_type_id=:transferTypeId "
			+ "AND tcr.transfer_status_id=1 "
			+ "AND tcr.organization_id=:organizationId "
			+ "AND tcr.unit_id=:unitId "
			+ "AND admission.status='A' "
			),
	@NamedNativeQuery(name="SEARCH_CONSULT_REQ_ORD_BY_ADMISSION_ID",query="SELECT transfer_of_care_id as \"transferOfCareId\", "
			+ "admission.patient_id as \"patientId\", "
			+ "admission.t_patient_id as \"tPatientId\", "
			+ "admission.admission_id as \"admissionId\", "
			+ "tcr.request_to as \"requestTo\", "
			+ "tcr.request_By as \"requestBy\", "
			+ "tcr.speciality_id as \"toSpecialityId\", "
			+ "admission.speciality_id as \"bySpecialityId\", "
			+ "sp.speciality_name as \"toSpecialityName\", "
			+ "sp2.speciality_name as \"bySpecialityName\", "
			+ "pr.uhid_number as \"UHIDNumber\", " 
			+ "concat(pr.first_name,' ',pr.middle_name,' ',pr.last_name) as \"patientName\" , " 
			+ "pr.gender_id as \"genderId\", " 
			+ "gm.gender_code as \"genderCode\", "
			+ "to_char(pr.dob,'MM/dd/yyyy') as \"dob\", " 
			+ "concat(doc.first_name,' ', doc.middle_name,' ',doc.last_name) as \"doctorName\", "
			+ "bm.bed_number as \"bedNumber\", "
			+ "bm.bed_id as \"bedId\", "
			+ "tcr.reason_id as \"reasonId\", "
			+" reason.reason_desc as \"reasonDesc\", "
			+ "tcr.note as \"note\", "
			+ "tcr.transfer_status_id as \"transferStatusId\", "
			+ "trastat.transfer_status_desc as \"transferStatusDesc\", "
			+ "nurWard.nursing_station_id as \"nursingStationId\", "
			+ "nur.nursing_station_desc as \"nursingStationDesc\", "
			+ "tcr.transfer_type_id as \"transferTypeId\", "
			+ "ward.ward_name as \"wardName\" "
			+ "FROM adt.t_transfer_of_care_request tcr "
			+ "INNER JOIN adt.t_admission admission on tcr.admission_id=admission.admission_id "
			+ "INNER JOIN patient.t_patient_registration pr on admission.patient_id=pr.patient_id "
			+ "INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id " 
			+ "INNER JOIN doctor.m_doctor_master doc on tcr.request_by=doc.doctor_id "
			+ "INNER JOIN doctor.m_speciality_master sp on tcr.speciality_id=sp.speciality_id " 
			+ "INNER JOIN doctor.m_speciality_master sp2 on admission.speciality_id=sp2.speciality_id " 
			+ "INNER JOIN adt.t_admission_details adl on adl.admission_id=admission.admission_id "
			+ "INNER JOIN adt.m_bed_master bm on adl.bed_id=bm.bed_id "
			+ "INNER JOIN adt.m_ward_master ward on bm.ward_id=ward.ward_id "
			+ "LEFT JOIN adt.t_nursing_station_ward_mapper nurWard on ward.ward_id=nurWard.ward_id "
			+ "LEFT JOIN adt.m_nursing_station_master nur on nurWard.nursing_station_id=nur.nursing_station_id " 
			+" INNER JOIN adt.m_reason_master reason on tcr.reason_id=reason.reason_master_id "
			+" INNER JOIN adt.m_transfer_status_master trastat on tcr.transfer_status_id=trastat.transfer_status_id  "
			+ "WHERE tcr.transfer_type_id=:transferTypeId "
			+ "AND tcr.transfer_status_id=1 "
			+ "AND tcr.organization_id=:organizationId "
			+ "AND tcr.unit_id=:unitId "
			+ "AND admission.status='A' "
			+ "AND tcr.admission_id=:admissionId "
			+ " union "
			+ "SELECT transfer_of_care_id as \"transferOfCareId\", "
			+ "admission.patient_id as \"patientId\", "
			+ "admission.t_patient_id as \"tPatientId\", "
			+ "admission.admission_id as \"admissionId\", "
			+ "tcr.request_to as \"requestTo\", "
			+ "tcr.request_By as \"requestBy\", "
			+ "tcr.speciality_id as \"toSpecialityId\", "
			+ "admission.speciality_id as \"bySpecialityId\", "
			+ "sp.speciality_name as \"toSpecialityName\", "
			+ "sp2.speciality_name as \"bySpecialityName\", "
			+ "pr.t_uhid as \"UHIDNumber\", " 
			+ "pr.patient_name as \"patientName\" , " 
			+ "pr.gender_id as \"genderId\", " 
			+ "gm.gender_code as \"genderCode\", "
			+ "concat(to_char(pr.age,'999'),' ', pr.age_format) as \"dob\", "
			+ "concat(doc.first_name,' ', doc.middle_name,' ',doc.last_name) as \"doctorName\", "
			+ "bm.bed_number as \"bedNumber\", "
			+ "bm.bed_id as \"bedId\", "
			+ "tcr.reason_id as \"reasonId\", "
			+" reason.reason_desc as \"reasonDesc\", "
			+ "tcr.note as \"note\", "
			+ "tcr.transfer_status_id as \"transferStatusId\", "
			+ "trastat.transfer_status_desc as \"transferStatusDesc\", "
			+ "nurWard.nursing_station_id as \"nursingStationId\", "
			+ "nur.nursing_station_desc as \"nursingStationDesc\","
			+ "tcr.transfer_type_id as \"transferTypeId\", "
			+ "ward.ward_name as \"wardName\" "
			+ "FROM adt.t_transfer_of_care_request tcr "
			+ "INNER JOIN adt.t_admission admission on tcr.admission_id=admission.admission_id "
			+ "INNER JOIN patient.m_unknown_patient_registration pr on admission.t_patient_id=pr.unknown_patient_id "
			+ "INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id " 
			+ "INNER JOIN doctor.m_doctor_master doc on tcr.request_by=doc.doctor_id "
			+ "LEFT JOIN doctor.m_speciality_master sp on tcr.speciality_id=sp.speciality_id " 
			+ "LEFT JOIN doctor.m_speciality_master sp2 on admission.speciality_id=sp2.speciality_id " 
			+ "INNER JOIN adt.t_admission_details adl on adl.admission_id=admission.admission_id "
			+ "INNER JOIN adt.m_bed_master bm on adl.bed_id=bm.bed_id "
			+ "INNER JOIN adt.m_ward_master ward on bm.ward_id=ward.ward_id "
			+ "LEFT JOIN adt.t_nursing_station_ward_mapper nurWard on ward.ward_id=nurWard.ward_id "
			+ "LEFT JOIN adt.m_nursing_station_master nur on nurWard.nursing_station_id=nur.nursing_station_id " 
			+ " INNER JOIN adt.m_reason_master reason on tcr.reason_id=reason.reason_master_id "
			+ " INNER JOIN adt.m_transfer_status_master trastat on tcr.transfer_status_id=trastat.transfer_status_id  "
			+ "WHERE tcr.transfer_type_id=:transferTypeId "
			+ "AND tcr.transfer_status_id=1 "
			+ "AND tcr.organization_id=:organizationId "
			+ "AND tcr.unit_id=:unitId "
			+ "AND admission.status='A' "
			+ "AND tcr.admission_id=:admissionId ")
})


@Entity
@Table(name="t_transfer_of_care_request",schema="adt")
@SequenceGenerator(name="inter_consult_request_seq",sequenceName="adt.inter_consult_request_seq",allocationSize=1)
public class TransferOfCareRequest 
{
	@Id
	@Column(name="transfer_of_care_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="inter_consult_request_seq")
	private int transferOfCareId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="admission_id")
	private Integer admissionId;
	
	@Column(name="request_by")
	private Integer requestBy;
	
	@Column(name="request_to")
	private Integer requestTo;
	
	@Column(name="speciality_id")
	private Integer specialityId;
	
	@Column(name="reason_id")
	private Integer reasonId;
	
	@Column(name="note")
	private String note;
	
	@Column(name="authorized_by")
	private Integer authorizedBy;
	
	@Column(name="reject_reason_id")
	private Integer rejectReasonId;
	
	@Column(name="transfer_type_id")
	private Integer transferTypeId;
	
	@Column(name="created_by")
	private int createdBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="status")
	private char status;
	
	@Column(name="transfer_status_id")
	private Integer transferStatusId;
	
	@Column(name="remark")
	private String remark;
	
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
	@JoinColumn(name = "request_by", insertable = false, nullable = false, updatable = false)
	private DoctorMaster requestedByDoctorMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "request_to", insertable = false, nullable = false, updatable = false)
	private DoctorMaster requestedToDoctorMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "speciality_id", insertable = false, nullable = false, updatable = false)
	private SpecialityMaster specialityMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reason_id", insertable = false, nullable = false, updatable = false)
	private ReasonMaster reasonMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reject_reason_id", insertable = false, nullable = false, updatable = false)
	private ReasonMaster rejectReasonMaster;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getTransferOfCareId() {
		return transferOfCareId;
	}

	public void setTransferOfCareId(int transferOfCareId) {
		this.transferOfCareId = transferOfCareId;
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

	public Integer getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(Integer admissionId) {
		this.admissionId = admissionId;
	}

	public Integer getRequestBy() {
		return requestBy;
	}

	public void setRequestBy(Integer requestBy) {
		this.requestBy = requestBy;
	}

	public Integer getRequestTo() {
		return requestTo;
	}

	public void setRequestTo(Integer requestTo) {
		this.requestTo = requestTo;
	}

	public Integer getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(Integer specialityId) {
		this.specialityId = specialityId;
	}

	public Integer getReasonId() {
		return reasonId;
	}

	public void setReasonId(Integer reasonId) {
		this.reasonId = reasonId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getAuthorizedBy() {
		return authorizedBy;
	}

	public void setAuthorizedBy(Integer authorizedBy) {
		this.authorizedBy = authorizedBy;
	}

	public Integer getRejectReasonId() {
		return rejectReasonId;
	}

	public void setRejectReasonId(Integer rejectReasonId) {
		this.rejectReasonId = rejectReasonId;
	}

	public Integer getTransferTypeId() {
		return transferTypeId;
	}

	public void setTransferTypeId(Integer transferTypeId) {
		this.transferTypeId = transferTypeId;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public Integer getTransferStatusId() {
		return transferStatusId;
	}

	public void setTransferStatusId(Integer transferStatusId) {
		this.transferStatusId = transferStatusId;
	}

	public OrganizationMaster getOrganizationMaster() {
		return organizationMaster;
	}

	public void setOrganizationMaster(OrganizationMaster organizationMaster) {
		this.organizationMaster = organizationMaster;
	}

	public UnitMaster getUnitMaster() {
		return unitMaster;
	}

	public void setUnitMaster(UnitMaster unitMaster) {
		this.unitMaster = unitMaster;
	}

	public Admission getAdmission() {
		return admission;
	}

	public void setAdmission(Admission admission) {
		this.admission = admission;
	}

	public DoctorMaster getRequestedByDoctorMaster() {
		return requestedByDoctorMaster;
	}

	public void setRequestedByDoctorMaster(DoctorMaster requestedByDoctorMaster) {
		this.requestedByDoctorMaster = requestedByDoctorMaster;
	}

	public DoctorMaster getRequestedToDoctorMaster() {
		return requestedToDoctorMaster;
	}

	public void setRequestedToDoctorMaster(DoctorMaster requestedToDoctorMaster) {
		this.requestedToDoctorMaster = requestedToDoctorMaster;
	}

	public SpecialityMaster getSpecialityMaster() {
		return specialityMaster;
	}

	public void setSpecialityMaster(SpecialityMaster specialityMaster) {
		this.specialityMaster = specialityMaster;
	}

	public ReasonMaster getReasonMaster() {
		return reasonMaster;
	}

	public void setReasonMaster(ReasonMaster reasonMaster) {
		this.reasonMaster = reasonMaster;
	}

	public ReasonMaster getRejectReasonMaster() {
		return rejectReasonMaster;
	}

	public void setRejectReasonMaster(ReasonMaster rejectReasonMaster) {
		this.rejectReasonMaster = rejectReasonMaster;
	}
	
	
	
	
}
