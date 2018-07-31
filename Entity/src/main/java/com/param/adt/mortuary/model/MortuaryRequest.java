package com.param.adt.mortuary.model;

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
import com.param.adt.master.unit.model.MortuaryBedMaster;
import com.param.global.common.LocalTimeConverter;
import com.param.global.model.DeathRegistration;
import com.param.global.model.KinDetails;
import com.param.global.model.PatientRegistration;
import com.param.global.model.UnknownPatientRegistration;

@NamedNativeQueries({
	
	@NamedNativeQuery(name="GET_RESERVED_MORTUARY_REQUEST_LIST",query=
			"SELECT  mor.mortuary_request_id as \"mortuaryRequestId\", "
			+ "mor.patient_id as \"patientId\", "
			+ "mor.t_patient_id as \"tPatientId\", "
			+ "mor.d_patient_id as \"dPatientId\", "
			+ "pr.uhid_number as \"uhidNumber\", " 
			+ "concat(pr.first_name,' ',pr.middle_name,' ',pr.last_name,' ',EXTRACT(year from AGE(CURRENT_TIMESTAMP, pr.dob)),'/',gm.gender_code) as \"patientName\" , " 
			+ "CONCAT(to_char(mor.expected_arrival_date,'dd/MM/yyyy'),' ',to_char(mor.expected_arrival_date,'HH:MI')) as \"expectedArrivalDate\", "
			+ "mor.duration_format as \"durationFormat\", "
			+ "mor.duration_value as \"durationValue\", "
			+ "mor.mortuary_bed_id as \"mortuaryBedId\", "
			+ "mbm.mortuary_bed_number as \"mortuaryBedNumber\" "
			+ "FROM adt.t_mortuary_request mor "
			+ "INNER JOIN patient.t_patient_registration pr on pr.patient_id=mor.patient_id "
			+ "INNER JOIN public.m_gender_master gm on gm.gender_id=pr.gender_id "
			+ "INNER JOIN adt.m_mortuary_bed_master mbm on mbm.mortuary_bed_id=mor.mortuary_bed_id "
			+ "WHERE mor.organization_id=:orgId "
			+ "AND mor.unit_id=:unitId "
			+ "AND mor.mortuary_status_id=2 "
			+ "UNION "
			+ "SELECT mor.mortuary_request_id as \"mortuaryRequestId\", "
			+ "mor.patient_id as \"patientId\", "
			+ "mor.t_patient_id as \"tPatientId\", "
			+ "mor.d_patient_id as \"dPatientId\", "
			+ "pr.t_uhid as \"uhidNumber\", " 
			+ "CONCAT(pr.patient_name,' ',to_char(pr.age,'999'),' ', pr.age_format,'/',gm.gender_code) as \"patientName\" , " 
			+ "CONCAT(to_char(mor.expected_arrival_date,'dd/MM/yyyy'),' ',to_char(mor.expected_arrival_date,'HH:MI')) as \"expectedArrivalDate\", "
			+ "mor.duration_format as \"durationFormat\", "
			+ "mor.duration_value as \"durationValue\", "
			+ "mor.mortuary_bed_id as \"mortuaryBedId\", "
			+ "mbm.mortuary_bed_number as \"mortuaryBedNumber\" "
			+ "FROM adt.t_mortuary_request mor "
			+ "INNER JOIN patient.m_unknown_patient_registration pr on pr.unknown_patient_id=mor.t_patient_id "
			+ "INNER JOIN public.m_gender_master gm on gm.gender_id=pr.gender_id "
			+ "INNER JOIN adt.m_mortuary_bed_master mbm on mbm.mortuary_bed_id=mor.mortuary_bed_id "
			+ "WHERE mor.organization_id=:orgId "
			+ "AND mor.unit_id=:unitId "
			+ "AND mor.mortuary_status_id=2 "
			+ "UNION "
			+ "SELECT mor.mortuary_request_id as \"mortuaryRequestId\", "
			+ "mor.patient_id as \"patientId\", "
			+ "mor.t_patient_id as \"tPatientId\", "
			+ "mor.d_patient_id as \"dPatientId\", "
			+ "pr.uhid_number as \"uhidNumber\", " 
			+ "CONCAT(pr.first_name,' ',pr.last_name,' ',gm.gender_code) as \"patientName\" , " 
			+ "CONCAT(to_char(mor.expected_arrival_date,'dd/MM/yyyy'),' ',to_char(mor.expected_arrival_date,'HH:MI')) as \"expectedArrivalDate\", "
			+ "mor.duration_format as \"durationFormat\", "
			+ "mor.duration_value as \"durationValue\", "
			+ "mor.mortuary_bed_id as \"mortuaryBedId\", "
			+ "mbm.mortuary_bed_number as \"mortuaryBedNumber\" "
			+ "FROM adt.t_mortuary_request mor "
			+ "INNER JOIN patient.t_death_registration pr on pr.death_registration_id=mor.d_patient_id "
			+ "INNER JOIN public.m_gender_master gm on gm.gender_id=pr.gender_id "
			+ "INNER JOIN adt.m_mortuary_bed_master mbm on mbm.mortuary_bed_id=mor.mortuary_bed_id "
			+ "WHERE mor.organization_id=:orgId "
			+ "AND mor.unit_id=:unitId "
			+ "AND mor.mortuary_status_id=2 "
			
		 ),
	
	@NamedNativeQuery(name="GET_PENDING_MORTUARY_REQUEST_LIST",query=
			"SELECT mor.patient_id as \"patientId\", "
			+"mor.t_patient_id as \"tPatientId\", "
			+"mor.d_patient_id as \"dPatientId\", "
			+"mor.admission_id as \"admissionId\","
			+ "mor.mortuary_request_id as \"mortuaryRequestId\", "
			+ "mor.duration_format as \"durationFormat\", "
			+ "mor.duration_value as \"durationValue\", "
			+"CONCAT(pr.uhid_number,pr1.t_uhid,dpr.uhid_number) as \"uhidNumber\", "
			+"CONCAT(concat(pr.first_name,' ',pr.middle_name,' ',pr.last_name),pr1.patient_name,CONCAT(dpr.first_name,' ',dpr.last_name)) as \"patientName\" ," 
			//+"CONCAT(pr.gender_id,pr1.gender_id,dpr.gender_id) as \"genderId\"," 
			+"CONCAT(gm.gender_code,gm1.gender_code,gm2.gender_code) as \"genderCode\", " 
			+"CONCAT(CONCAT(EXTRACT(year from AGE(CURRENT_TIMESTAMP, pr.dob))),CONCAT(to_char(pr1.age,'999'),' ', pr1.age_format)) as \"age\", " 
			+"mor.mortuary_status_id as \"mortuaryStatusId\" " 
			+"FROM adt.t_mortuary_request mor "
			+"LEFT JOIN patient.t_patient_registration pr on pr.patient_id=mor.patient_id " 
			+"LEFT JOIN public.m_gender_master gm on gm.gender_id=pr.gender_id "
			+"LEFT JOIN patient.m_unknown_patient_registration pr1 on pr1.unknown_patient_id=mor.t_patient_id "
			+"LEFT JOIN public.m_gender_master gm1 on gm1.gender_id=pr1.gender_id "
			+"LEFT JOIN patient.t_death_registration dpr on dpr.death_registration_id=mor.d_patient_id "
			+"LEFT JOIN public.m_gender_master gm2 on gm2.gender_id=dpr.gender_id "
			+"WHERE mor.organization_id=:orgId "
			+"AND mor.unit_id=:unitId "
			+"AND mor.mortuary_status_id=1 "),
	
	@NamedNativeQuery(name="GET_ADMITTED_MORTUARY_LIST",query=
			"SELECT  mor.mortuary_request_id as \"mortuaryRequestId\", "
			+ "mor.patient_id as \"patientId\", "
			+ "mor.t_patient_id as \"tPatientId\", "
			+ "mor.d_patient_id as \"dPatientId\", "
			+ "pr.uhid_number as \"uhidNumber\", " 
			+ "concat(pr.first_name,' ',pr.middle_name,' ',pr.last_name,' ',EXTRACT(year from AGE(CURRENT_TIMESTAMP, pr.dob)),'/',gm.gender_code) as \"patientName\" , " 
			+ "CONCAT(to_char(mor.expected_arrival_date,'dd/MM/yyyy'),' ',to_char(mor.expected_arrival_date,'HH:MI')) as \"expectedArrivalDate\", "
			+ "mor.duration_format as \"durationFormat\", "
			+ "mor.duration_value as \"durationValue\", "
			+ "mor.mortuary_bed_id as \"mortuaryBedId\", "
			+ "to_char(mor.updated_date,'dd/MM/yyyy HH:mm:ss') as \"updatedDate\" "
			+ "FROM adt.t_mortuary_request mor "
			+ "INNER JOIN patient.t_patient_registration pr on pr.patient_id=mor.patient_id "
			+ "INNER JOIN public.m_gender_master gm on gm.gender_id=pr.gender_id "
			+ "WHERE mor.organization_id=:orgId "
			+ "AND mor.unit_id=:unitId "
			+ "AND mor.mortuary_status_id=3 "
			+ "UNION "
			+ "SELECT mor.mortuary_request_id as \"mortuaryRequestId\", "
			+ "mor.patient_id as \"patientId\", "
			+ "mor.t_patient_id as \"tPatientId\", "
			+ "mor.d_patient_id as \"dPatientId\", "
			+ "pr.t_uhid as \"uhidNumber\", " 
			+ "CONCAT(pr.patient_name,' ',to_char(pr.age,'999'),' ', pr.age_format,'/',gm.gender_code) as \"patientName\" , " 
			+ "CONCAT(to_char(mor.expected_arrival_date,'dd/MM/yyyy'),' ',to_char(mor.expected_arrival_date,'HH:MI')) as \"expectedArrivalDate\", "
			+ "mor.duration_format as \"durationFormat\", "
			+ "mor.duration_value as \"durationValue\", "
			+ "mor.mortuary_bed_id as \"mortuaryBedId\", "
			+ "to_char(mor.updated_date,'dd/MM/yyyy HH:mm:ss') as \"updatedDate\" "
			+ "FROM adt.t_mortuary_request mor "
			+ "INNER JOIN patient.m_unknown_patient_registration pr on pr.unknown_patient_id=mor.t_patient_id "
			+ "INNER JOIN public.m_gender_master gm on gm.gender_id=pr.gender_id "
			+ "WHERE mor.organization_id=:orgId "
			+ "AND mor.unit_id=:unitId "
			+ "AND mor.mortuary_status_id=3 "
			+ "UNION "
			+ "SELECT mor.mortuary_request_id as \"mortuaryRequestId\", "
			+ "mor.patient_id as \"patientId\", "
			+ "mor.t_patient_id as \"tPatientId\", "
			+ "mor.d_patient_id as \"dPatientId\", "
			+ "pr.uhid_number as \"uhidNumber\", " 
			+ "CONCAT(pr.first_name,' ',pr.last_name,' / ',gm.gender_code) as \"patientName\" , " 
			+ "CONCAT(to_char(mor.expected_arrival_date,'dd/MM/yyyy'),' ',to_char(mor.expected_arrival_date,'HH:MI')) as \"expectedArrivalDate\", "
			+ "mor.duration_format as \"durationFormat\", "
			+ "mor.duration_value as \"durationValue\", "
			+ "mor.mortuary_bed_id as \"mortuaryBedId\", "
			+ "to_char(mor.updated_date,'dd/MM/yyyy HH:mm:ss') as \"updatedDate\" "
			+ "FROM adt.t_mortuary_request mor "
			+ "INNER JOIN patient.t_death_registration pr on pr.death_registration_id=mor.d_patient_id "
			+ "INNER JOIN public.m_gender_master gm on gm.gender_id=pr.gender_id "
			+ "WHERE mor.organization_id=:orgId "
			+ "AND mor.unit_id=:unitId "
			+ "AND mor.mortuary_status_id=3 "
	
 ),
	
	@NamedNativeQuery(name="GET_DATA_FOR_MORTURY_MAP_OF_BED",
	    query="select mbm.mortuary_bed_id as \"mortuaryBedId\","
			+ "mr.mortuary_request_id as \"mortuaryRequestId\","
			+ "CONCAT(CONCAT(pr.first_name,' ',pr.last_name),CONCAT(dpr.first_name,' ',dpr.last_name),upr.patient_name) as \"patientName\", "
			+"CONCAT(gm1.gender_code,gm2.gender_code,gm3.gender_code) as \"genderCode\", " 
			+"CONCAT(CONCAT(EXTRACT(year from AGE(CURRENT_TIMESTAMP, pr.dob))),CONCAT(to_char(upr.age,'999'),' ', upr.age_format)) as \"age\", " 
			+ "CONCAT(pr.uhid_number,dpr.uhid_number,upr.t_uhid) as \"uhidNumber\","
			+ "mr.toe_band_id as \"toeBandId\","
			+ "kd.kin_name as \"kinName\", "
			+ "mr.duration_format as \"durationFormat\", "
			+ "mr.duration_value as \"durationValue\", "
			+ "to_char(mr.updated_date,'dd/MM/yyyy HH:mm:ss') as \"updatedDate\", "
			+ "kd.mobile_no as \"mobileNo\" "
			+ "FROM adt.m_mortuary_bed_master mbm "
			+ "INNER join adt.t_mortuary_bed_log_status mbls on mbls.mortuary_bed_id = mbm.mortuary_bed_id AND mbls.status = 'A' "
			+ "LEFT JOIN adt.t_mortuary_request mr on mr.mortuary_bed_id  = mbm.mortuary_bed_id AND mr.status='A' "
			+ "LEFT JOIN patient.t_patient_registration pr on mr.patient_id = pr.patient_id "
			+ "LEFT JOIN patient.t_death_registration dpr on mr.d_patient_id = dpr.death_registration_id "
			+ "LEFT JOIN patient.m_unknown_patient_registration upr on mr.t_patient_id = upr.unknown_patient_id " 
			+ "LEFT JOIN public.m_kin_details kd on mr.admission_kin_id = kd.kin_id "
			+ "LEFT JOIN public.m_gender_master gm1 on gm1.gender_id=pr.gender_id "
			+ "LEFT JOIN public.m_gender_master gm2 on gm2.gender_id=dpr.gender_id "
			+ "LEFT JOIN public.m_gender_master gm3 on gm3.gender_id=upr.gender_id "
			+ "WHERE mbm.organization_id=:orgId "
			+ "AND mbm.unit_id=:unitId "
			+ "AND mbm.status='A' "
			+ "AND mbls.bed_status_id!=4 "
			+ "ORDER BY mbm.mortuary_bed_id "
			)
})

@Entity
@Table(name="t_mortuary_request",schema="adt")
@SequenceGenerator(name="mortuary_request_seq",sequenceName="adt.mortuary_request_seq",allocationSize=1)
public class MortuaryRequest {

	@Id
	@Column(name="mortuary_request_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="mortuary_request_seq")
	private int mortuaryRequestId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="mortuary_bed_id")
	private Integer mortuaryBedId;
	
	@Column(name="admission_id")
	private Integer admissionId;
	
	@Column(name="patient_id")
	private Integer patientId;
	
	@Column(name="t_patient_id")
	private Integer tPatientId;
	
	@Column(name="d_patient_id")
	private Integer dPatientId;
	
	@Column(name="visit_type_id")
	private Integer visitTypeId;
	
	@Column(name="duration_format")
	private Integer durationFormat;
	
	@Column(name="duration_value")
	private Integer durationValue;
	
	@Column(name="mortuary_status_id")
	private Integer mortuaryStatusId;
	
	@Column(name="expected_arrival_date")
	private Date expectedArrivalDate;
	
	@Column(name="expected_arrival_time")
	@Convert(converter = LocalTimeConverter.class)
	private LocalTime expectedArrivalTime;
	
	@Column(name="out_time")
	private Date outTime;
	
	@Column(name = "status")
	private char status;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name="release_kin_id")
	private Integer releaseKinId;
	
	@Column(name="release_note")
	private String releaseNote;
	
	@Column(name="toe_band_id")
	private String toeBandId;
	
	@Column(name="admission_kin_id")
	private Integer admissionKinId;
	
	@Column(name="reject_reason_id")
	private Integer rejectReasonId;
	
	@Column(name="rejection_note")
	private String rejectionNote;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id", insertable = false, nullable = false, updatable = false)
	private PatientRegistration patientRegistration;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_patient_id", insertable = false, nullable = false, updatable = false)
	private UnknownPatientRegistration unknownPatientRegistration;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "d_patient_id", insertable = false, nullable = false, updatable = false)
	private DeathRegistration deathRegistration;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admission_id", insertable = false, nullable = false, updatable = false)
	private Admission admission;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mortuary_bed_id", insertable = false, nullable = false, updatable = false)
	private MortuaryBedMaster mortuaryBedMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "release_kin_id", insertable = false, nullable = false, updatable = false)
	private KinDetails kinDetails;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admission_kin_id", insertable = false, nullable = false, updatable = false)
	private KinDetails kinDetails2;
	
	
	public Integer getRejectReasonId() {
		return rejectReasonId;
	}

	public void setRejectReasonId(Integer rejectReasonId) {
		this.rejectReasonId = rejectReasonId;
	}

	public String getRejectionNote() {
		return rejectionNote;
	}

	public void setRejectionNote(String rejectionNote) {
		this.rejectionNote = rejectionNote;
	}

	public Integer getAdmissionKinId() {
		return admissionKinId;
	}

	public void setAdmissionKinId(Integer admissionKinId) {
		this.admissionKinId = admissionKinId;
	}

	public String getToeBandId() {
		return toeBandId;
	}

	public void setToeBandId(String toeBandId) {
		this.toeBandId = toeBandId;
	}

	public String getReleaseNote() {
		return releaseNote;
	}

	public void setReleaseNote(String releaseNote) {
		this.releaseNote = releaseNote;
	}

	public Integer getReleaseKinId() {
		return releaseKinId;
	}

	public void setReleaseKinId(Integer releaseKinId) {
		this.releaseKinId = releaseKinId;
	}

	public Integer getMortuaryBedId() {
		return mortuaryBedId;
	}

	public void setMortuaryBedId(Integer mortuaryBedId) {
		this.mortuaryBedId = mortuaryBedId;
	}

	public Integer getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(Integer admissionId) {
		this.admissionId = admissionId;
	}

	public int getMortuaryRequestId() {
		return mortuaryRequestId;
	}

	public void setMortuaryRequestId(int mortuaryRequestId) {
		this.mortuaryRequestId = mortuaryRequestId;
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

	public Integer getdPatientId() {
		return dPatientId;
	}

	public void setdPatientId(Integer dPatientId) {
		this.dPatientId = dPatientId;
	}

	public Integer getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public Integer getDurationFormat() {
		return durationFormat;
	}

	public void setDurationFormat(Integer durationFormat) {
		this.durationFormat = durationFormat;
	}

	public Integer getDurationValue() {
		return durationValue;
	}

	public void setDurationValue(Integer durationValue) {
		this.durationValue = durationValue;
	}

	public Integer getMortuaryStatusId() {
		return mortuaryStatusId;
	}

	public void setMortuaryStatusId(Integer mortuaryStatusId) {
		this.mortuaryStatusId = mortuaryStatusId;
	}

	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = (status == '\u0000' ? 'A' : status);
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Date getExpectedArrivalDate() {
		return expectedArrivalDate;
	}

	public void setExpectedArrivalDate(Date expectedArrivalDate) {
		this.expectedArrivalDate = expectedArrivalDate;
	}

	public LocalTime getExpectedArrivalTime() {
		return expectedArrivalTime;
	}

	public void setExpectedArrivalTime(LocalTime expectedArrivalTime) {
		this.expectedArrivalTime = expectedArrivalTime;
	}
	
}
