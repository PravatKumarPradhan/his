package com.param.opd.coversheet.model;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.global.common.DateConverter;
import com.param.global.common.LocalTimeConverter;
import com.param.global.model.ClinicMaster;
import com.param.global.model.DoctorMaster;
import com.param.global.model.KinDetails;
import com.param.global.model.OrderMaster;
import com.param.global.model.PatientRegistration;
import com.param.global.model.PatientSponsorDetails;
import com.param.global.model.PaymentEntitlementMaster;
import com.param.global.model.ReferalDetails;
@NamedNativeQueries({
	@NamedNativeQuery(name="GET_ENCOUNTER_DETAILS_BY_ID", 
			query="SELECT encounter.encounter_id as \"encounterId\", "
					+ "encounter.patient_id as \"patientId\", "
					+ "encounter.user_defined_visit_type_id as \"userDefinedVisitTypeId\", "
					+ " encounter.doctor_id as \"doctorId\", "
					+ "encounter.encounter_date as \"encounterDateTime\", "
					+ "encounter.dept_id as \"deptId\","
					+ " encounter.encounter_number as \"encounterNumber\","
					+ "	concat(coalesce(doctor.first_name,''),' ',coalesce(doctor.middle_name,''),' ',coalesce(doctor.last_name,'')) as \"doctorName\", "
					+ " speciality.speciality_name as \"specialityName\""
					+ " FROM opd.t_encounter_master encounter "
					+ " LEFT JOIN doctor.m_doctor_master doctor "
					+ " 	ON doctor.doctor_id = encounter.doctor_id "
					+ " LEFT JOIN doctor.t_doctor_speciality_mapper mapper "
					+ " 	ON mapper.doctor_id = doctor.doctor_id "
					+ " LEFT JOIN doctor.m_speciality_master speciality"
					+ "		ON speciality.speciality_id = mapper.speciality_id"
					+ " WHERE encounter.encounter_id=:encounterId" ),
					
					
					@NamedNativeQuery(name="GET_SEARCH_ENCOUNTERED_LIST",query=""
							+ " SELECT  "
							+ "	enMst.encounter_id as \"encounterId\", enMst.encounter_number as \"encounterNumber\", "
							+ " enMst.encounter_date as \"encounterDate\", "
							+ " enMst.user_defined_visit_type_id as \"userDefinedVisitTypeId\" ,"
							+ " enMst.status as \"status\", "
							+ " enMst.status_id as \"statusId\", "
							+ " concat(prefix.prefix_code,' ',coalesce(patient.first_name) ,' ', coalesce(patient.middle_name) ,' ', coalesce(patient.last_name)) as \"patientName\", "
							+ "	to_char(sm.appointment_date,'dd/MM/yyyy') as \"appointmentDate\", "
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
							+ " concat(EXTRACT(year from AGE(CURRENT_TIMESTAMP, patient.dob)) ,'Y ', EXTRACT(month from AGE(CURRENT_TIMESTAMP, patient.dob)) , 'M ' , EXTRACT(day from AGE(CURRENT_TIMESTAMP, patient.dob)),'D ') as \"age\", "
							//+ "concat(to_char(EXTRACT(year from AGE(CURRENT_TIMESTAMP, patient.dob)),'999'), 'Y') as \"age\", "
							+ "patient.mobile_number as \"mobileNumber\", "
							+ "patient.email as \"email\", "
							+ "patient.patient_id as \"patientId\", "
							+ "patient.uhid_number as \"uhidNumber\", "
							+ "patient.prefix_id as \"prefixId\", "
							+ "prefix.prefix_code as \"prefixCode\", " 
							+ "patient.gender_id as \"genderId\", "
							+ "gender.gender_code as \"genderCode\", "
							+ "gender.gender_name as \"genderDec\", "
							+ "to_char(patient.dob,'DD/MM/YYYY') as \"dob\", "
							+ "sm.modality_id as \"modalityId\", "
							+ "modality.modality_desc as \"modalityDesc\", "
							+ " enDet.is_assignment_comp_status as \"isAssignmentCompStatus\","
							+ " enDet.is_consultation_status as \"isConsultationStatus\", "
							+ " to_char(enDet.consultation_stop_time,'HH:MM:SS') as \"consultationStopTime\", "
							+ " refrlTypeMst.referral_type_name as \"referralTypeName\", "
							+ " reflMst.referral_name as \"referralName\", "
							+ " refrDtls.referral_remark as \"referralRemark\", "
							//+ "sm.modality_type_id as \"modalityTypeId\", "
							//+ "modalityType.modality_type_desc as \"modalityTypeDesc\", "
							//+ "modalityType.speciality_id as \"modalitySpecialityId\", "
							+ "adm.admission_id as \"admissionId\" "
							+ " FROM opd.t_encounter_master enMst"
							+ " LEFT JOIN opd.t_encounter_details enDet on enDet.encounter_id=enMst.encounter_id "
							+ " LEFT JOIN opd.m_appointment_master am on am.appointment_id=enMst.appointment_id "
							+ " LEFT JOIN opd.m_slot_master sm on am.slot_id=sm.slot_id "
							
							+" LEFT JOIN public.t_referal_details refrDtls on refrDtls.encounter_id=enMst.encounter_id "
							+"  LEFT JOIN adt.m_referral_type_master refrlTypeMst on refrlTypeMst.referral_type_id=refrDtls.referral_type_id "
							+"  LEFT JOIN adt.m_referral_master reflMst on reflMst.referral_id=refrDtls.referral_id "
							
							+ " LEFT JOIN public.m_clinic_master clinMst on  clinMst.clinic_master_id=enMst.clinic_id "
							+ " LEFT JOIN opd.t_nursing_station_clinic_mapper nurStatClinMapp on nurStatClinMapp.clinic_id=clinMst.clinic_master_id "
							+ " LEFT OUTER JOIN doctor.m_speciality_master speciaityMaster on sm.speciality_id=speciaityMaster.speciality_id "
							+ " LEFT OUTER JOIN doctor.m_doctor_master doc on enMst.doctor_id=doc.doctor_id "
							//+ "LEFT OUTER JOIN public.m_modality_type_master modalityType on sm.modality_type_id=modalityType.modality_type_id "
							+ " LEFT OUTER JOIN public.m_modality_master modality on sm.modality_id=modality.modality_id "
							+ " LEFT JOIN patient.t_patient_registration patient on enMst.patient_id=patient.patient_id "
							+ " LEFT JOIN public.m_gender_master gender on patient.gender_id=gender.gender_id "
							+ " LEFT JOIN public.m_prefix_master prefix on patient.prefix_id=prefix.prefix_id "
							+ " LEFT JOIN opd.m_appointment_status_master asm on am.appointment_status_id=asm.appointment_status_id "
							+ " LEFT OUTER JOIN adt.t_admission adm on adm.patient_id = am.patient_id  AND adm.status='A' "
							+ " WHERE enMst.organization_id=:orgId "
							+ " AND enMst.unit_id=:unitId "),
							
							
							@NamedNativeQuery(name="GET_LIST_ENCOUNTER_MASTER",query=""
									+ " SELECT  "
									+ "	enMst.encounter_id as \"encounterId\", enMst.encounter_number as \"encounterNumber\", "
									+ " enMst.encounter_date as \"encounterDate\", "
									+ " enMst.user_defined_visit_type_id as \"userDefinedVisitTypeId\" ,"
									+ " enMst.status as \"status\", "
									+ " enMst.status_id as \"statusId\", "
									+ " concat(prefix.prefix_code,' ',coalesce(patient.first_name) ,' ', coalesce(patient.middle_name) ,' ', coalesce(patient.last_name)) as \"patientName\", "
									+ "	to_char(sm.appointment_date,'dd/MM/yyyy HH:MM:SS') as \"appointmentDate\", "
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
									+ " concat(EXTRACT(year from AGE(CURRENT_TIMESTAMP, patient.dob)) ,'Y ', EXTRACT(month from AGE(CURRENT_TIMESTAMP, patient.dob)) , 'M ' , EXTRACT(day from AGE(CURRENT_TIMESTAMP, patient.dob)),'D ') as \"age\", "
									//+ "concat(to_char(EXTRACT(year from AGE(CURRENT_TIMESTAMP, patient.dob)),'999'), 'Y') as \"age\", "
									+ "patient.mobile_number as \"mobileNumber\", "
									+ "patient.email as \"email\", "
									+ "patient.patient_id as \"patientId\", "
									+ "patient.uhid_number as \"uhidNumber\", "
									+ "patient.prefix_id as \"prefixId\", "
									+ "prefix.prefix_code as \"prefixCode\", " 
									+ "patient.gender_id as \"genderId\", "
									+ "gender.gender_code as \"genderCode\", "
									+ "gender.gender_name as \"genderDec\", "
									+ "to_char(patient.dob,'DD/MM/YYYY') as \"dob\", "
									+ "sm.modality_id as \"modalityId\", "
									+ "modality.modality_desc as \"modalityDesc\", "
									+ " enDet.is_assignment_comp_status as \"isAssignmentCompStatus\","
									+ " enDet.is_consultation_status as \"isConsultationStatus\", "
									+ " to_char(enDet.consultation_stop_time,'HH:MM:SS') as \"consultationStopTime\", "
									+ " refrlTypeMst.referral_type_name as \"referralTypeName\", "
									+ " reflMst.referral_name as \"referralName\", "
									+ " refrDtls.referral_remark as \"referralRemark\", "
									//+ "sm.modality_type_id as \"modalityTypeId\", "
									//+ "modalityType.modality_type_desc as \"modalityTypeDesc\", "
									//+ "modalityType.speciality_id as \"modalitySpecialityId\", "
									+ " adm.admission_id as \"admissionId\" "
									+ " FROM opd.t_encounter_master enMst "
									+ " LEFT JOIN opd.t_encounter_details enDet on enDet.encounter_id=enMst.encounter_id "
									+ " LEFT JOIN opd.m_appointment_master am on am.appointment_id=enMst.appointment_id "
									+ " LEFT JOIN opd.m_slot_master sm on am.slot_id=sm.slot_id "
									
									+" LEFT JOIN public.t_referal_details refrDtls on refrDtls.encounter_id=enMst.encounter_id "
					  	            +"  LEFT JOIN adt.m_referral_type_master refrlTypeMst on refrlTypeMst.referral_type_id=refrDtls.referral_type_id "
					  	            +"  LEFT JOIN adt.m_referral_master reflMst on reflMst.referral_id=refrDtls.referral_id "
									
									+ " LEFT JOIN public.m_clinic_master clinMst on  clinMst.clinic_master_id=enMst.clinic_id "
									+ " LEFT JOIN opd.t_nursing_station_clinic_mapper nurStatClinMapp on nurStatClinMapp.clinic_id=clinMst.clinic_master_id "
									+ " LEFT OUTER JOIN doctor.m_speciality_master speciaityMaster on sm.speciality_id=speciaityMaster.speciality_id "
									+ " LEFT OUTER JOIN doctor.m_doctor_master doc on enMst.doctor_id=doc.doctor_id "
									//+ "LEFT OUTER JOIN public.m_modality_type_master modalityType on sm.modality_type_id=modalityType.modality_type_id "
									+ " LEFT OUTER JOIN public.m_modality_master modality on sm.modality_id=modality.modality_id "
									+ " LEFT JOIN patient.t_patient_registration patient on enMst.patient_id=patient.patient_id "
									+ " LEFT JOIN public.m_gender_master gender on patient.gender_id=gender.gender_id "
									+ " LEFT JOIN public.m_prefix_master prefix on patient.prefix_id=prefix.prefix_id "
									+ " LEFT JOIN opd.m_appointment_status_master asm on am.appointment_status_id=asm.appointment_status_id "
									+ " LEFT OUTER JOIN adt.t_admission adm on adm.patient_id = am.patient_id  AND adm.status='A' "
									+ " WHERE enMst.organization_id=:orgId "
									+ " AND enMst.unit_id=:unitId "),
							
									
		@NamedNativeQuery(name="GET_ENCOUNTER_BY_ID_FOR_GLOBAL_SCHEDULING_SYNC",
							query="select encounter.doctor_id as \"doctorId\" , encounter.encounter_id as \"appointmentId\" ,"
									+ "encounter.organization_id  as \"hospitalId\" , encounter.unit_id as \"unitId\" ,"
									+ "encounter.patient_id as \"patientId\" , encounter.status_id as \"appointmentStatusId\" ,"
									+ "to_char(encounter.encounter_date,'DD-MM-YYYY') as \"appointmentDate\" ,"
									+ "to_char(slots.from_time , 'HH24:MI') as \"appointmentTime\" , 2 as \"applicationId\" ,"
									+ "encounter.dept_id as \"departmentId\" , speciality.speciality_name as \"departmentName\" ,"
									+ "concat(doctor.first_name,' ',doctor.last_name) as \"doctorName\",to_char(encounter.created_date,'DD-MM-YYYY') as \"createdDate\" ,"
									+ "encounter.created_by as \"createdBy\" "
									+ "from opd.t_encounter_master encounter "
									+ "inner join opd.m_appointment_master appointment "
									+ "on appointment.appointment_id = encounter.appointment_id "
									+ "inner join opd.m_slot_master slots "
									+ "on slots.slot_id = appointment.slot_id "
									+ "inner join doctor.m_speciality_master speciality "
									+ "on speciality.speciality_id = encounter.dept_id "
									+ "inner join doctor.m_doctor_master doctor "
									+ "on doctor.doctor_id = encounter.doctor_id "
									+ "where encounter.encounter_id = :encounterId")					
							
							
	
})
@NamedQueries({
	
	@NamedQuery(name="GET_ENCOUNTER_DETAILS",query="SELECT encounter.encounterId as encounterId,"
			+ "encounter.userDefinedVisitTypeId as userDefinedVisitTypeId, "
			+ "to_char(encounter.encounterDate,'yyyy-mm-dd') as encounterDateString, "
			+ "to_char(encounter.encounterDate,'dd/mm/yyyy') as encounterDateString2, "
			+ "encounter.encounterNumber as encounterNumber, "
			+ "encounter.doctorId as doctorId, "
			+ "concat('Dr. ',coalesce(doc.firstName),' ',coalesce(doc.middleName),' ',coalesce(doc.lastName)) as doctorName, "
			+ "docSpec.specialityId as specialityId, "
			+ "speciality.specialityName as specialityName "
			+ "FROM EncounterMaster encounter "
			+ "INNER JOIN encounter.doctorMaster doc "
			+ "INNER JOIN doc.docotrSpecialityMapperslist docSpec "
			+ "INNER JOIN docSpec.specialityMaster speciality "
			+ "WHERE encounter.organizationId=:orgId "
			+ "AND encounter.unitId=:unitId "
			+ "AND encounter.doctorId=:doctorId "
			+ "AND encounter.patientId=:patientId "
			+ "ORDER BY encounter.encounterDate "),
	
		@NamedQuery(name = "GET_ENCOUNTER_DETAILS_FOR_VISIT_TYPE_SERVICE_BY_DOCTOR_AND_PATIENT_ID", query = "SELECT encounter.encounterId as encounterId,"
			+ "encounter.userDefinedVisitTypeId as userDefinedVisitTypeId, "
			+ "to_char(encounter.encounterDate,'yyyy-mm-dd') as encounterDateString "
			+ "FROM EncounterMaster encounter " 
			+ "WHERE encounter.organizationId=:orgId "
			+ "AND encounter.unitId=:unitId " 
			+ "AND encounter.doctorId=:doctorId "
			+ "AND encounter.patientId=:patientId " 
			+ "ORDER BY encounter.encounterDate DESC "),
				
		@NamedQuery(name="GET_LIST_OF_ENCOUNTERS_BY_PATIENT_ID", query=" SELECT "
				+ "distinct(encounter.encounterId) as encounterId,"
				//+ "encounter.encounterId as encounterId,"
		   + " encounter.encounterNumber as encounterNumber, "
		   + " encounter.doctorId as doctorId, "
		   + " concat('Dr. ',coalesce(doc.firstName),' ',coalesce(doc.middleName),' ',coalesce(doc.lastName)) as doctorName, "
		   + " docSpec.specialityId as specialityId, "
		   + " speciality.specialityName as specialityName "
		   + " FROM EncounterMaster encounter "
		   + " INNER JOIN 	encounter.doctorMaster doc "
		   + " INNER JOIN 	doc.docotrSpecialityMapperslist docSpec "
		   + " INNER JOIN 	docSpec.specialityMaster speciality "
		   + " INNER JOIN 	encounter.orderMastersList ord "
		   + " INNER JOIN 	ord.listOrderDetailsMaster ordls "
		   + " WHERE encounter.organizationId=:orgId "
		   + " AND encounter.unitId=:unitId "
		   + " AND encounter.patientId=:patientId "
		   + " AND ordls.serviceIsBilled=0 "),

	
		@NamedQuery(name = "GET_ENC_VISIT_NOS_BY_PATIENT_ID_FOR_DEPOSIT",
			query= "SELECT		enc.encounterId as encounterId, enc.encounterNumber as visitNo "
				 + "FROM		EncounterMaster enc "
				 + "WHERE		enc.patientId =:patientId "
				 + "and			enc.statusId in (1,5,6) "
				 + "and			enc.organizationId =:orgId "
				 + "and 		enc.unitId =:unitId "),
		
		@NamedQuery(name="GET_ENCOUNTER_DETAILS_BY_ENCOUNTER_ID",
		
		query="SELECT encounter.encounterId as encounterId, "
				+ "encounter.patientId as patientId, "
				+ "encounter.modalityId as modalityId, "
				+ "encounter.doctorId as doctorId, "
				+ "to_char(encounter.encounterDate,'yyyy/mm/dd') as encounterDateString, "
				+ "encounter.encounterNumber as encounterNumber, "
				+ "speciality.specialityName as specialityName, "
				+ "concat('Dr. ',coalesce(doc.firstName),' ',coalesce(doc.middleName),' ',coalesce(doc.lastName)) as doctorName, "
				+ "encounter.paymentEntitlementTypeId as paymentEntitlementTypeId, "
				+ "referalDetsList.referralId as referralId, "
				+ "refMaster.desc as referralDesc, "
				+ "patSponDtl.payeeId as payeeId, "
				+ "company.companyDesc as companyDesc,"
				+ "paymentEntitlementMaster.paymentEntitlementDesc as paymentEntitlementDesc, "
				+ "patientCat.patientCategory as patientCategory "
				+ "FROM EncounterMaster encounter "
			    + "INNER JOIN encounter.doctorMaster doc "
			    + "INNER JOIN doc.docotrSpecialityMapperslist docSpec "
			    + "INNER JOIN docSpec.specialityMaster speciality "
				+ "LEFT JOIN encounter.referalDetailsList referalDetsList "
				+ "LEFT JOIN referalDetsList.referralMaster refMaster "
				+ "INNER JOIN encounter.patientRegistration patient "
				+ "INNER JOIN patient.patientCategoryMaster patientCat "
				+ "INNER JOIN encounter.patientSponsorDetailsList patSponDtl "
				+ "INNER JOIN encounter.paymentEntitlementMaster paymentEntitlementMaster "
				+ "INNER JOIN patSponDtl.companyMaster company "
				+ "WHERE encounter.encounterId=:encounterId ")
})

@Entity
@Table(name="t_encounter_master", schema="opd")
@SequenceGenerator(name = "encounter_master_seq", sequenceName = "opd.encounter_master_seq", allocationSize = 1)
public class EncounterMaster {
	@Id
	@Column(name="encounter_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "encounter_master_seq")
	private int encounterId;
	
	@Column(name="patient_id")
	private Integer patientId;
	
	@Column(name="modality_id")
	private Integer modalityId;
	
	@Column(name="system_generated_visit_type_id")
	private Integer systemGeneratedVisitTypeId;
	
	@Column(name="user_defined_visit_type_id")
	private Integer userDefinedVisitTypeId;
	
	@Column(name="reason_id_for_changing_visit_type")
	private Integer reasonIdForChangingVisitType;

	@Column(name="remark_for_changing_visit_type")
	private String remarkForChangingVisitType;
	
	@Column(name="doctor_id")
	private Integer doctorId;
	
	@Column(name="source_id")
	private Integer sourceId;
	
	@Column(name="encounter_date")
	@Convert(converter = DateConverter.class)
	private Long encounterDate;
	
	@Column(name="encounter_time")
	@Convert(converter = LocalTimeConverter.class)
	private LocalTime encounterTime;
	
	@Column(name="is_referal")
	private Character isReferal;
	
	@Column(name="dept_id")
	private Integer deptId;
	
	@Column(name="remark")
	private String remark;
	
	@Column(name="kin_details_id")
	private Integer kinDetailsId;
	
	@Column(name="payment_entitlement_type_id")
	private Integer paymentEntitlementTypeId;
	
	@Column(name="encounter_number")
	private String encounterNumber;
	
	@Column(name="created_date")
	@Convert(converter = DateConverter.class)
	private Long createdDate;
	
	@Column(name="created_by")
	private Integer createdBy;
	
	@Column(name="updated_date")
	@Convert(converter = DateConverter.class)
	private Long updatedDate;
	
	@Column(name="updated_by")
	private Integer updatedBy;
	
	@Column(name="status")
	private Character status;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="appointment_id")
	private Integer appointmentId;
	
	@Column(name="clinic_id")
	private Integer clinicId;
	
	@Column(name="status_id")
	private Integer statusId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctor_id", insertable = false, nullable = false, updatable = false)
	private DoctorMaster doctorMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id", insertable = false, nullable = false, updatable = false)
	private PatientRegistration patientRegistration;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kin_details_id", insertable = false, nullable = false, updatable = false)
	private KinDetails kinDetails;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clinic_id", insertable = false, nullable = false, updatable = false)
	private ClinicMaster clinicMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payment_entitlement_type_id", insertable = false, nullable = false, updatable = false)
	private PaymentEntitlementMaster paymentEntitlementMaster;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "encounterMaster", cascade = CascadeType.ALL, orphanRemoval = true) 
	private List<ComplaintAppointmentMapper> ComplaintAppointmentMapperList;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "encounterMaster", cascade = CascadeType.ALL, orphanRemoval = true) 
	private List<ReferalDetails> referalDetailsList;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "encounterMaster", cascade = CascadeType.ALL, orphanRemoval = true) 
	private List<PatientSponsorDetails> patientSponsorDetailsList;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "encounterMaster")
	private List<OrderMaster> orderMastersList;
	
	public int getClinicId() {
		return clinicId;
	}

	public void setClinicId(int clinicId) {
		this.clinicId = clinicId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Integer getUserDefinedVisitTypeId() {
		return userDefinedVisitTypeId;
	}

	public void setUserDefinedVisitTypeId(Integer userDefinedVisitTypeId) {
		this.userDefinedVisitTypeId = userDefinedVisitTypeId;
	}

	public Integer getReasonIdForChangingVisitType() {
		return reasonIdForChangingVisitType;
	}

	public void setReasonIdForChangingVisitType(Integer reasonIdForChangingVisitType) {
		this.reasonIdForChangingVisitType = reasonIdForChangingVisitType;
	}
	
	public Integer getSystemGeneratedVisitTypeId() {
		return systemGeneratedVisitTypeId;
	}

	public void setSystemGeneratedVisitTypeId(Integer systemGeneratedVisitTypeId) {
		this.systemGeneratedVisitTypeId = systemGeneratedVisitTypeId;
	}

	public String getRemarkForChangingVisitType() {
		return remarkForChangingVisitType;
	}

	public void setRemarkForChangingVisitType(String remarkForChangingVisitType) {
		this.remarkForChangingVisitType = remarkForChangingVisitType;
	}

	public Integer getModalityId() {
		return modalityId;
	}

	public void setModalityId(Integer modalityId) {
		this.modalityId = modalityId;
	}

	public int getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(int encounterId) {
		this.encounterId = encounterId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public Long getEncounterDate() {
		return encounterDate;
	}

	public void setEncounterDate(Long encounterDate) {
		this.encounterDate = encounterDate;
	}

	public LocalTime getEncounterTime() {
		return encounterTime;
	}

	public void setEncounterTime(LocalTime encounterTime) {
		this.encounterTime = encounterTime;
	}

	public Character getIsReferal() {
		return isReferal;
	}

	public void setIsReferal(Character isReferal) {
		this.isReferal = isReferal;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getKinDetailsId() {
		return kinDetailsId;
	}

	public void setKinDetailsId(Integer kinDetailsId) {
		this.kinDetailsId = kinDetailsId;
	}

	public Integer getPaymentEntitlementTypeId() {
		return paymentEntitlementTypeId;
	}

	public void setPaymentEntitlementTypeId(Integer paymentEntitlementTypeId) {
		this.paymentEntitlementTypeId = paymentEntitlementTypeId;
	}

	public String getEncounterNumber() {
		return encounterNumber;
	}

	public void setEncounterNumber(String encounterNumber) {
		this.encounterNumber = encounterNumber;
	}

	public Long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Long updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
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
