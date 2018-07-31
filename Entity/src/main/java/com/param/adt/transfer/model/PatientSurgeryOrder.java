package com.param.adt.transfer.model;

import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.LocalTimeConverter;

@NamedNativeQueries({

		@NamedNativeQuery(name = "GET_OT_REQUEST_BY_ADMISSION_ID", 
				query = "SELECT pso.patient_surgery_order_id as \"patientSurgeryOrderId\", "
				+ "pso.surgery_id as \"surgeryId\", " 
				+ "pso.admission_id as \"admissionId\", "
				+ "ser.surgery_name as \"surgeryName\", " 
				+ "to_char(pso.time_of_surgery,'HH:MI') as \"surgeryTime\" "
				//+ "ss.schedule_id as \"scheduleId\" " 
				+ "FROM public.t_patient_surgery_order pso "
				+ "INNER join public.m_surgery_master ser on ser.surgery_id=pso.surgery_id "
				//+ "left join service.t_service_schedule ss on pso.patient_surgery_order_id = ss.patient_service_order_id AND ss.transfer_type_id=:transferTypeId AND ss.visit_type_id = :visitTypeId "
				+ "where pso.admission_id =:admissionId " 
				+ "AND pso.organization_id=:orgId "
				+ "AND pso.unit_id=:unitId " 
				+ "AND pso.order_status_id=1 " 
				+ "AND pso.is_ot_transfer = 'N' "
				) ,
		
		@NamedNativeQuery(name="GET_OT_REQUEST_LIST",
			query=  " select adm.patient_id as \"patientId\", "
					+ " adm.t_patient_id as \"tPatientId\", "
					+ " pr.uhid_number as \"UHIDNumber\", "
					+ " concat(pr.first_name,' ',pr.middle_name,' ',pr.last_name) as \"pFirstName\" , " 
					+ " pr.gender_id as \"genderId\", " 
					+ " gm.gender_code as \"genderCode\", "
					+ " to_char(pr.dob,'MM/dd/yyyy') as \"dob\", " 
					+ " bm.bed_number as \"bedNumber\" ,"
					+ " bm.bed_id as \"bedId\", "
					+ " bm.ward_id as \"wardId\", "
					+ " ward.ward_name as \"wardName\", "
					+ " concat(doc.first_name,' ', doc.middle_name,' ',doc.last_name) as \"dFirstName\", "
					+ " adm.admission_id as \"admissionId\", "
					+ " (select to_char(pso.created_date,'dd/mm/yyyy') from t_patient_surgery_order pso where pso.admission_id = adm.admission_id order by pso.created_date asc limit 1) as \"surgeryDate\", "  
					+ " (select to_char(pso.time_of_surgery,'HH:MI') from t_patient_surgery_order pso where pso.admission_id = adm.admission_id order by pso.time_of_surgery asc limit 1) as \"surgeryTime\", "
					+ " (select count(*) from t_patient_surgery_order pso where pso.admission_id = adm.admission_id) as \"surgeryCount\" " 
					+ " from adt.t_admission adm "
					+ " INNER JOIN patient.t_patient_registration pr on adm.patient_id=pr.patient_id "
					+ " INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id " 
					+ " INNER JOIN adt.t_admission_details adl on adm.admission_id=adl.admission_id " 
					+ " INNER JOIN adt.m_bed_master bm on adl.bed_id=bm.bed_id "
					+ " INNER JOIN adt.m_ward_master ward on bm.ward_id=ward.ward_id "
					+ " INNER JOIN doctor.m_doctor_master doc on adm.doctor_id=doc.doctor_id "
					+ " INNER JOIN public.t_patient_surgery_order pso on adm.admission_id = pso.admission_id "
					+ " where adm.admission_id IN (select distinct(pso1.admission_id) from t_patient_surgery_order pso1 "
					+ " inner join adt.t_admission admission on admission.admission_id = pso1.admission_id "
					+ " where pso1.is_ot_transfer = 'N' AND admission.status='A' AND pso1.order_status_id=1 AND admission.organization_id=:orgId AND admission.unit_id=:unitId ) "
					+ " AND adm.organization_id=:orgId "
					+ " AND adm.unit_id=:unitId "
					+ " AND adm.status='A' "
					+ " AND adl.status='A' "
					+ " AND pso.order_status_id=1 "
					+ " union "
					+ " select adm.patient_id as \"patientId\", "
					+ " adm.t_patient_id as \"tPatientId\", "
					+ " pr.t_uhid as \"UHIDNumber\", " 
					+ " pr.patient_name as \"pFirstName\" , " 
					+ " pr.gender_id as \"genderId\", " 
					+ " gm.gender_code as \"genderCode\", "
					+ " concat(to_char(pr.age,'999'),' ', pr.age_format) as \"dob\", "
					+ " bm.bed_number as \"bedNumber\" ,"
					+ " bm.bed_id as \"bedId\", "
					+ " bm.ward_id as \"wardId\", "
					+ " ward.ward_name as \"wardName\", "
					+ " concat(doc.first_name,' ', doc.middle_name,' ',doc.last_name) as \"dFirstName\", "
					+ " adm.admission_id as \"admissionId\", "
					+ " (select to_char(pso.created_date,'dd/mm/yyyy') from t_patient_surgery_order pso where pso.order_status_id=1 AND pso.admission_id = adm.admission_id order by pso.created_date asc limit 1) as \"surgeryDate\", "  
					+ " (select to_char(pso.time_of_surgery,'HH:MI') from t_patient_surgery_order pso where pso.order_status_id=1 AND pso.admission_id = adm.admission_id order by pso.time_of_surgery asc limit 1) as \"surgeryTime\", " 
					+ " (select count(*) from t_patient_surgery_order pso where pso.order_status_id=1 AND pso.admission_id = adm.admission_id) as \"surgeryCount\" "
					+ " from adt.t_admission adm "
					+ " INNER JOIN patient.m_unknown_patient_registration pr on adm.t_patient_id=pr.unknown_patient_id  "
					+ " INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id " 
					+ " INNER JOIN adt.t_admission_details adl on adm.admission_id=adl.admission_id " 
					+ " INNER JOIN adt.m_bed_master bm on adl.bed_id=bm.bed_id "
					+ " INNER JOIN adt.m_ward_master ward on bm.ward_id=ward.ward_id "
					+ " INNER JOIN doctor.m_doctor_master doc on adm.doctor_id=doc.doctor_id "
					+ " INNER JOIN public.t_patient_surgery_order pso on adm.admission_id = pso.admission_id "
					+ " where adm.admission_id IN (select distinct(pso1.admission_id)  from t_patient_surgery_order pso1 "
					+ " inner join adt.t_admission admission on admission.admission_id = pso1.admission_id "
					+ " where pso1.is_ot_transfer = 'N' AND admission.status='A' AND pso1.order_status_id=1 AND admission.organization_id=:orgId AND admission.unit_id=:unitId ) "
					+ " AND adm.organization_id=:orgId "
					+ " AND adm.unit_id=:unitId "
					+ " AND adm.status='A' "
					+ " AND adl.status='A' "
					+ " AND pso.order_status_id=1 "
					),
		
		@NamedNativeQuery(name="GET_OT_REQUEST_LIST_BY_ADMISSION_ID",
		query=  " select adm.patient_id as \"patientId\", "
				+ " adm.t_patient_id as \"tPatientId\", "
				+ " pr.uhid_number as \"UHIDNumber\", "
				+ " concat(pr.first_name,' ',pr.middle_name,' ',pr.last_name) as \"pFirstName\" , " 
				+ " pr.gender_id as \"genderId\", " 
				+ " gm.gender_code as \"genderCode\", "
				+ " to_char(pr.dob,'MM/dd/yyyy') as \"dob\", " 
				+ " bm.bed_number as \"bedNumber\", "
				+ " bm.bed_id as \"bedId\", "
				+ " bm.ward_id as \"wardId\", "
				+ " ward.ward_name as \"wardName\", "
				+"  concat(doc.first_name,' ', doc.middle_name,' ',doc.last_name) as \"dFirstName\", "
				+ " adm.admission_id as \"admissionId\", "
				+ " (select to_char(pso.created_date,'dd/mm/yyyy') from t_patient_surgery_order pso where pso.order_status_id=1 AND pso.admission_id = :admissionId order by pso.created_date asc limit 1) as \"surgeryDate\", "  
				+ " (select to_char(pso.time_of_surgery,'HH:MI') from t_patient_surgery_order pso where pso.order_status_id=1 AND pso.admission_id = :admissionId order by pso.time_of_surgery asc limit 1) as \"surgeryTime\", "
				+ " (select count(*) from t_patient_surgery_order pso where pso.order_status_id=1 AND pso.admission_id = :admissionId) as \"surgeryCount\" " 
				+ " from adt.t_admission adm "
				+ " INNER JOIN patient.t_patient_registration pr on adm.patient_id=pr.patient_id "
				+ " INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id " 
				+ " INNER JOIN adt.t_admission_details adl on adm.admission_id=adl.admission_id " 
				+ " INNER JOIN adt.m_bed_master bm on adl.bed_id=bm.bed_id "
				+ " INNER JOIN adt.m_ward_master ward on bm.ward_id=ward.ward_id "
				+ " INNER JOIN doctor.m_doctor_master doc on adm.doctor_id=doc.doctor_id "
				+ " INNER JOIN public.t_patient_surgery_order pso on adm.admission_id = pso.admission_id "
				+ " where adm.admission_id =:admissionId "
				+ " AND adm.organization_id=:orgId "
				+ " AND adm.unit_id=:unitId "
				+ " AND adm.status='A' "
				+ " AND adl.status='A' "
				+ " AND pso.order_status_id=1 "
				+ " union "
				+ " select adm.patient_id as \"patientId\", "
				+ " adm.t_patient_id as \"tPatientId\", "
				+ " pr.t_uhid as \"UHIDNumber\", " 
				+ " pr.patient_name as \"pFirstName\" , " 
				+ " pr.gender_id as \"genderId\", " 
				+ " gm.gender_code as \"genderCode\", "
				+ " concat(to_char(pr.age,'999'),' ', pr.age_format) as \"dob\", "
				+ " bm.bed_number as \"bedNumber\", "
				+ " bm.bed_id as \"bedId\", "
				+ " bm.ward_id as \"wardId\", "
				+ " ward.ward_name as \"wardName\", "
				+ " concat(doc.first_name,' ', doc.middle_name,' ',doc.last_name) as \"dFirstName\", "
				+ " adm.admission_id as \"admissionId\", "
				+ " (select to_char(pso.created_date,'dd/mm/yyyy') from t_patient_surgery_order pso where pso.order_status_id=1 AND pso.admission_id = :admissionId order by pso.created_date asc limit 1) as \"surgeryDate\", "  
				+ " (select to_char(pso.time_of_surgery,'HH:MI') from t_patient_surgery_order pso where pso.order_status_id=1 AND pso.admission_id = :admissionId order by pso.time_of_surgery asc limit 1) as \"surgeryTime\", " 
				+ " (select count(*) from t_patient_surgery_order pso where pso.order_status_id=1 AND pso.admission_id = :admissionId) as \"surgeryCount\" "
				+ " from adt.t_admission adm "
				+ " INNER JOIN patient.m_unknown_patient_registration pr on adm.t_patient_id=pr.unknown_patient_id  "
				+ " INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id " 
				+ " INNER JOIN adt.t_admission_details adl on adm.admission_id=adl.admission_id " 
				+ " INNER JOIN adt.m_bed_master bm on adl.bed_id=bm.bed_id "
				+ " INNER JOIN adt.m_ward_master ward on bm.ward_id=ward.ward_id "
				+ " INNER JOIN doctor.m_doctor_master doc on adm.doctor_id=doc.doctor_id "
				+ " INNER JOIN public.t_patient_surgery_order pso on adm.admission_id = pso.admission_id "
				+ " where adm.admission_id =:admissionId "
				+ " AND adm.organization_id=:orgId "
				+ " AND adm.unit_id=:unitId "
				+ " AND adm.status='A' "
				+ " AND adl.status='A' "
				+ " AND pso.order_status_id=1 "
				)
})



@Entity
@Table(name = "t_patient_surgery_order", schema = "public")
@SequenceGenerator(name = "patient_surgery_order_seq", sequenceName = "public.patient_surgery_order_seq", allocationSize = 1)
public class PatientSurgeryOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_surgery_order_seq")
	@Column(name = "patient_surgery_order_id")
	private int patientSurgeryOrderId;

	@Column(name = "surgery_id")
	private Integer surgeryId;

	@Column(name = "organization_id")
	private Integer organizationId;

	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "admission_id")
	private Integer admissionId;

	@Column(name = "time_of_surgery")
	@Convert(converter = LocalTimeConverter.class)
	private LocalTime timeOfSurgery;

	@Column(name = "order_status_id")
	private Integer orderStatusId;

	@Column(name = "status")
	private char status;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "is_ot_transfer")
	private char isOtTransfer;

	public int getPatientSurgeryOrderId() {
		return patientSurgeryOrderId;
	}

	public void setPatientSurgeryOrderId(int patientSurgeryOrderId) {
		this.patientSurgeryOrderId = patientSurgeryOrderId;
	}

	public Integer getSurgeryId() {
		return surgeryId;
	}

	public void setSurgeryId(Integer surgeryId) {
		this.surgeryId = surgeryId;
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

	public LocalTime getTimeOfSurgery() {
		return timeOfSurgery;
	}

	public void setTimeOfSurgery(String timeOfSurgery) {
		if (!timeOfSurgery.equals("")) {
			this.timeOfSurgery = GlobalCommonDateUtils.getLocalTime(timeOfSurgery);
			System.out.println("timeslot=" + timeOfSurgery);
		} else {
			this.timeOfSurgery = GlobalCommonDateUtils.getLocalTime("00:00");
		}
	}

	public Integer getOrderStatusId() {
		return orderStatusId;
	}

	public void setOrderStatusId(Integer orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	public char getIsOtTransfer() {
		return isOtTransfer;
	}

	public void setIsOtTransfer(char isOtTransfer) {
		this.isOtTransfer = isOtTransfer;
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

}
/*
 * CREATE TABLE t_patient_surgery_order ( patient_surgery_order_id integer NOT
 * NULL DEFAULT nextval('patient_surgery_order_seq'::regclass), surgery_id
 * integer, organization_id integer, unit_id integer, admission_id integer,
 * time_of_surgery timestamp without time zone, order_status_id integer,
 * created_by integer, created_date timestamp without time zone DEFAULT now(),
 * updated_by integer, updated_date timestamp without time zone DEFAULT now(),
 * status character(1) DEFAULT 'A'::bpchar,
 */