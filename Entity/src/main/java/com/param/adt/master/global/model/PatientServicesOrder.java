package com.param.adt.master.global.model;

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
import com.param.adt.master.unit.model.BillingBedCategoryMaster;
import com.param.global.model.BedCategoryMaster;
import com.param.global.model.OrderStatusMaster;
import com.param.global.model.PaymentEntitlementMaster;
import com.param.global.model.ServiceMaster;

@NamedNativeQueries({

		@NamedNativeQuery(name = "GET_PATIENT_SERVICE_ORDER_LIST", query = " SELECT admission.admission_id as \"admissionId\", "
				+ " admission.patient_id as \"patientId\", " 
				+ "  admission.t_patient_id as \"tPatientId\","
				+ "  admission.admission_number as \"admissionNumber\", " 
				+ "  admission.visit_type_id as \"visitTypeId\",  "
				+ " admission.doctor_id as \"doctorId\", " 
				+ "  admission.speciality_id as \"specialityId\", "
				+ "  sp.speciality_name as \"specialityName\", " 
				+ "  pr.uhid_number as \"UHIDNumber\", "
				+ "  gm.gender_code as \"genderCode\", "
				+ "  concat(pr.first_name,' ',pr.middle_name,' ',pr.last_name) as \"pFirstName\" , "
				+ "  to_char(pr.dob,'MM/dd/yyyy') as \"dob\", "
				+ "  concat(doc.first_name,' ', doc.middle_name,' ',doc.last_name) as \"dFirstName\", "
				+ "  bm.bed_number as \"bedNumber\", " 
				+ " ser.patient_sevices_order_id as \"patientSevicesOrderId\","
				+ " ser.service_id as \"serviceId\", " 
				+ "  ser.order_status_id as \"orderStatusId\", "
				+ "  osi.order_desc as \"orderDesc\", " 
				+ " to_char(ser.created_date,'dd-MM-yyyy') as \"createdDate\", "
				+ " subs.sub_speciality_id as \"subSpecialityId\", " 
				+ " subs.sub_speciality_name as \"subSpecialityName\", "
				+ " sm.service_standard_name as \"serviceName\" "
				+ "  FROM public.t_patient_sevices_order ser "
				+ " INNER JOIN service.m_service_master sm on ser.service_id = sm.service_master_id "
				+ " INNER JOIN adt.t_admission admission on ser.admission_id=admission.admission_id "
				+ " INNER JOIN patient.t_patient_registration pr on admission.patient_id=pr.patient_id "
				+ " INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id "
				+ " INNER JOIN doctor.m_doctor_master doc on admission.doctor_id=doc.doctor_id "
				+ " LEFT JOIN doctor.m_speciality_master sp on admission.speciality_id=sp.speciality_id "
				+ " INNER JOIN adt.t_admission_details adl on adl.admission_id=admission.admission_id "
				+ " INNER JOIN adt.m_bed_master bm on adl.bed_id=bm.bed_id "
				+ " INNER JOIN doctor.m_sub_speciality_master subs on subs.sub_speciality_id=sm.service_sub_speciality_id "
				+ " INNER JOIN public.m_order_status_master osi on osi.order_status_id = ser.order_status_id "
				+ "  WHERE admission.status='A' " 
				+ "  AND ser.organization_id=:orgId " 
				+ " AND ser.unit_id=:unitId "
				+ "  AND ser.admission_id=:admissionId " 
				+ "AND ser.order_status_id=1 "//-----> order status Id 1 = Pending 
				+ "  UNION " 
				+ "  SELECT "
				+ " admission.admission_id as \"admissionId\", " 
				+ " admission.patient_id as \"patientId\", "
				+ " admission.t_patient_id as \"tPatientId\", " 
				+ "  admission.admission_number as \"admissionNumber\","
				+ "  admission.visit_type_id as \"visitTypeId\",  " 
				+ " admission.doctor_id as \"doctorId\", "
				+ " admission.speciality_id as \"specialityId\", " 
				+ "  sp.speciality_name as \"specialityName\", "
				+ " pr.t_uhid as \"UHIDNumber\"," 
				+ "  gm.gender_code as \"genderCode\",   "
				+ "  pr.patient_name as \"pFirstName\" , " 
				+ "  concat(to_char(pr.age,'999'),' ', pr.age_format) as \"dob\" ,"
				+ "  concat(doc.first_name,' ', doc.middle_name,' ',doc.last_name) as \"dFirstName\", "
				+ "  bm.bed_number as \"bedNumber\"," 
				+ " ser.patient_sevices_order_id as \"patientSevicesOrderId\", "
				+ "  ser.service_id as \"serviceId\", " 
				+ "  ser.order_status_id as \"orderStatusId\", "
				+ "  osi.order_desc as \"orderDesc\", " 
				+ "  to_char(ser.created_date,'dd-MM-yyyy') as \"createdDate\", "
				+ "  subs.sub_speciality_id as \"subSpecialityId\", " 
				+ " subs.sub_speciality_name as \"subSpecialityName\", "
				+ " sm.service_standard_name as \"serviceName\" "
				+ "   FROM public.t_patient_sevices_order ser "
				+ "  INNER JOIN service.m_service_master sm on ser.service_id = sm.service_master_id"
				+ "  INNER JOIN adt.t_admission admission on ser.admission_id=admission.admission_id "
				+ " INNER JOIN patient.m_unknown_patient_registration pr on admission.t_patient_id=pr.unknown_patient_id "
				+ "  INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id  "
				+ "  INNER JOIN doctor.m_doctor_master doc on admission.doctor_id=doc.doctor_id "
				+ " LEFT JOIN doctor.m_speciality_master sp on admission.speciality_id=sp.speciality_id  "
				+ "  INNER JOIN adt.t_admission_details adl on adl.admission_id=admission.admission_id "
				+ "  INNER JOIN adt.m_bed_master bm on adl.bed_id=bm.bed_id "
				+ "   INNER JOIN doctor.m_sub_speciality_master subs on subs.sub_speciality_id=sm.service_sub_speciality_id "
				+ " INNER JOIN public.m_order_status_master osi on osi.order_status_id = ser.order_status_id  "
				+ "  WHERE admission.status='A' " 
				+ "  AND ser.organization_id=:orgId " 
				+ " AND ser.unit_id=:unitId "
				+ "  AND ser.admission_id=:admissionId " 
				+ "AND ser.order_status_id=1 "//-----> order status Id 1 = Pending 
				),
		
				@NamedNativeQuery(name="GET_PATIENT_SERVICE_ORDER_LIST_BY_ADMISSION_ID",
				query="SELECT pso.patient_sevices_order_id as \"patientSevicesOrderId\", " 
				+ "pso.service_id as \"serviceId\" , "
				+ "mm.modality_id as \"modalityId\", "
				+ "pso.admission_id as \"admissionId\", "
				+ "ser.service_standard_name as \"serviceStandardName\", "
				+ "to_char(ss.from_time,'HH:MI') as \"fromTime2\", "
				+ "ss.schedule_id as \"scheduleId\", "
				+ "mm.modality_desc as \"modalityDesc\" " 
				+ "FROM public.t_patient_sevices_order pso "
				+ "INNER join service.m_service_master ser on ser.service_master_id=pso.service_id " 
				+ "INNER join service.t_service_schedule ss on pso.patient_sevices_order_id = ss.patient_service_order_id AND ss.transfer_type_id=:transferTypeId AND ss.visit_type_id =:visitTypeId "
				+ "INNER join service.m_modality_master mm on ss.modality_id = mm.modality_id "
				+ "where pso.admission_id =:admissionId "
				+ "AND pso.organization_id=:orgId "
				+ "AND pso.unit_id=:unitId "
				+ "AND pso.order_status_id=1 "
				+ "AND pso.is_modality_transfer = 'N' "
				+ "union "
				+ "SELECT pso.patient_sevices_order_id as \"patientSevicesOrderId\" ," 
				+ "pso.service_id as \"serviceId\", "
				+ "mm.modality_id as \"modalityId\", "
				+ "pso.admission_id as \"admissionId\", " 
				+ "ser.service_standard_name as \"serviceStandardName\", "
				+ "to_char(ss.from_time,'HH:MI') as \"fromTime2\", "
				+ "ss.schedule_id as \"scheduleId\" ,"
				+ "mm.modality_desc as \"modalityDesc\" "
				+ "FROM public.t_patient_sevices_order pso " 
				+ "INNER join service.m_service_master ser on ser.service_master_id=pso.service_id AND ser.is_required_schedule = 'N' AND ser.is_on_bed='Y' "
				+ "LEFT join service.t_service_schedule ss on pso.patient_sevices_order_id = ss.patient_service_order_id AND ss.transfer_type_id=:transferTypeId AND ss.visit_type_id =:visitTypeId " 
				+ "LEFT join service.m_modality_master mm on ss.modality_id = mm.modality_id "  
				+ "where pso.admission_id =:admissionId "
				+ "AND pso.organization_id=:orgId " 
				+ "AND pso.unit_id=:unitId "
				+ "AND pso.order_status_id=1 "
				+ "AND pso.is_modality_transfer = 'N' "			
					),
		
		@NamedNativeQuery(name="GET_ALL_PATIENT_SERVICE_ORDER_LIST",
		query="SELECT pso.patient_sevices_order_id as \"patientSevicesOrderId\", "
			+ "pso.service_id as \"serviceId\", "
			+ "pso.admission_id as \"admissionId\", "
			+ "adm.patient_id as \"patientId\", "
			+ "adm.t_patient_id as \"tPatientId\", "
			+ "adm.visit_type_id as \"visitTypeId\", " 
			+ "pr.uhid_number as \"UHIDNumber\", " 
			+ "concat(pr.first_name,' ',pr.middle_name,' ',pr.last_name) as \"pFirstName\" , " 
			+ "to_char(pr.dob,'MM/dd/yyyy') as \"dob\", "
			+ "pr.gender_id as \"genderId\", " 
			+ "gm.gender_code as \"genderCode\", "
			+ "bm.bed_number as \"bedNumber\", "
			+ "bm.bed_id as \"bedId\", "
			+ "bm.ward_id as \"wardId\","
			+ "ward.ward_name as \"wardName\", "
			+ "ser.service_standard_name as \"serviceStandardName\", "
			+ "to_char(ss.schedule_date,'dd/MM/yyyy') as \"scheduleDate\", "
			+ "to_char(ss.from_time,'HH:MI') as \"fromTime2\","
			+ "ss.schedule_id as \"scheduleId\" "
			+ "FROM public.t_patient_sevices_order pso "
			+ "INNER join adt.t_admission adm on pso.admission_id=adm.admission_id "
			+ "INNER join adt.t_admission_details adl on adm.admission_id=adl.admission_id "
			+ "INNER JOIN patient.t_patient_registration pr on adm.patient_id=pr.patient_id "
			+ "INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id " 
			+ "INNER JOIN adt.m_bed_master bm on adl.bed_id=bm.bed_id "
			+ "INNER JOIN adt.m_ward_master ward on bm.ward_id=ward.ward_id "
			+ "INNER join service.m_service_master ser on ser.service_master_id=pso.service_id AND ser.is_required_schedule = 'Y' "
			+ "LEFT join service.t_service_schedule ss on pso.patient_sevices_order_id = ss.patient_service_order_id AND ss.transfer_type_id=:transferTypeId AND ss.visit_type_id = :visitTypeId "
			+ "WHERE pso.organization_id=:orgId "
			+ "AND pso.unit_id=:unitId "
			+ "AND pso.order_status_id=1 "
			//+ "AND pso.is_modality_transfer = 'N' "
			+ "UNION "
			+"SELECT pso.patient_sevices_order_id as \"patientSevicesOrderId\", "
			+ "pso.service_id as \"serviceId\", "
			+ "pso.admission_id as \"admissionId\", "
			+ "adm.patient_id as \"patientId\", "
			+ "adm.t_patient_id as \"tPatientId\", "
			+ "adm.visit_type_id as \"visitTypeId\", " 
			+ " pr.t_uhid as \"UHIDNumber\"," 
			+ "  pr.patient_name as \"pFirstName\" , " 
			+ "  concat(to_char(pr.age,'999'),' ', pr.age_format) as \"dob\" ,"
			+ "pr.gender_id as \"genderId\", " 
			+ "gm.gender_code as \"genderCode\", "
			+ "bm.bed_number as \"bedNumber\", "
			+ "bm.bed_id as \"bedId\", "
			+ "bm.ward_id as \"wardId\","
			+ "ward.ward_name as \"wardName\", "
			+ "ser.service_standard_name as \"serviceStandardName\", "
			+ "to_char(ss.schedule_date,'dd/MM/yyyy') as \"scheduleDate\", "
			+ "to_char(ss.from_time,'HH:MI') as \"fromTime2\","
			+ "ss.schedule_id as \"scheduleId\" "
			+ "FROM public.t_patient_sevices_order pso "
			+ "INNER join adt.t_admission adm on pso.admission_id=adm.admission_id "
			+ "INNER join adt.t_admission_details adl on adm.admission_id=adl.admission_id "
			+ " INNER JOIN patient.m_unknown_patient_registration pr on adm.t_patient_id=pr.unknown_patient_id "
			+ "INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id " 
			+ "INNER JOIN adt.m_bed_master bm on adl.bed_id=bm.bed_id "
			+ "INNER JOIN adt.m_ward_master ward on bm.ward_id=ward.ward_id "
			+ "INNER join service.m_service_master ser on ser.service_master_id=pso.service_id AND ser.is_required_schedule = 'Y' "
			+ "LEFT join service.t_service_schedule ss on pso.patient_sevices_order_id = ss.patient_service_order_id AND ss.transfer_type_id=:transferTypeId AND ss.visit_type_id = :visitTypeId "
			+ "WHERE pso.organization_id=:orgId "
			+ "AND pso.unit_id=:unitId "
			+ "AND pso.order_status_id=1 "
			//+ "AND pso.is_modality_transfer = 'N' "
			
			)


	
})

@Entity
@Table(name = "t_patient_sevices_order", schema = "public")
@SequenceGenerator(name = "patient_sevices_order_seq", sequenceName = "public.patient_sevices_order_seq", allocationSize = 1)
public class PatientServicesOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_sevices_order_seq")
	@Column(name = "patient_sevices_order_id")
	private int patientSevicesOrderId;

	@Column(name = "organization_id")
	private Integer organizationId;

	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "admission_id")
	private Integer admissionId;

	@Column(name = "bed_category_id")
	private Integer bedCategoryId;

	@Column(name = "billing_bed_category_id")
	private Integer billingBedCategoryId;

	@Column(name = "payment_entitlement_id")
	private Integer paymentEntitlementId;

	@Column(name = "service_id")
	private Integer serviceId;

	@Column(name = "patient_id")
	private Integer patientId;

	@Column(name = "ordered_by")
	private Integer orderedBy;

	@Column(name = "time_of_service_order")
	private Date timeOfServiceOrder;

	@Column(name = "order_status_id")
	private Integer orderStatusId;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "status")
	private char status;
	
	@Column(name="cancel_reason_id")
	private Integer cancelReasonId;

	@Column(name="is_modality_transfer")
	private char isModalityTransfer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admission_id", insertable = false, nullable = false, updatable = false)
	private Admission admission;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bed_category_id", insertable = false, nullable = false, updatable = false)
	private BedCategoryMaster bedCategoryMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "billing_bed_category_id", insertable = false, nullable = false, updatable = false)
	private BillingBedCategoryMaster billingBedCategoryMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payment_entitlement_id", insertable = false, nullable = false, updatable = false)
	private PaymentEntitlementMaster paymentEntitlementMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_id", insertable = false, nullable = false, updatable = false)
	private ServiceMaster serviceMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_status_id", insertable = false, nullable = false, updatable = false)
	private OrderStatusMaster orderStatusMaster;
	
	
	
	public char getIsModalityTransfer() {
		return isModalityTransfer;
	}

	public void setIsModalityTransfer(char isModalityTransfer) {
		this.isModalityTransfer = (isModalityTransfer == '\u0000') ? 'N' : isModalityTransfer;
	}

	public Integer getCancelReasonId() {
		return cancelReasonId;
	}

	public void setCancelReasonId(Integer cancelReasonId) {
		this.cancelReasonId = cancelReasonId;
	}

	public int getPatientSevicesOrderId() {
		return patientSevicesOrderId;
	}

	public void setPatientSevicesOrderId(int patientSevicesOrderId) {
		this.patientSevicesOrderId = patientSevicesOrderId;
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

	public Integer getBedCategoryId() {
		return bedCategoryId;
	}

	public void setBedCategoryId(Integer bedCategoryId) {
		this.bedCategoryId = bedCategoryId;
	}

	public Integer getBillingBedCategoryId() {
		return billingBedCategoryId;
	}

	public void setBillingBedCategoryId(Integer billingBedCategoryId) {
		this.billingBedCategoryId = billingBedCategoryId;
	}

	public Integer getPaymentEntitlementId() {
		return paymentEntitlementId;
	}

	public void setPaymentEntitlementId(Integer paymentEntitlementId) {
		this.paymentEntitlementId = paymentEntitlementId;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer sericeId) {
		this.serviceId = sericeId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getOrderedBy() {
		return orderedBy;
	}

	public void setOrderedBy(Integer orderedBy) {
		this.orderedBy = orderedBy;
	}

	public Date getTimeOfServiceOrder() {
		return timeOfServiceOrder;
	}

	public void setTimeOfServiceOrder(Date timeOfServiceOrder) {
		this.timeOfServiceOrder = timeOfServiceOrder;
	}

	public Integer getOrderStatusId() {
		return orderStatusId;
	}

	public void setOrderStatusId(Integer orderStatusId) {
		this.orderStatusId = orderStatusId;
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

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = (status == '\u0000') ? 'A' : status;
	}

}
