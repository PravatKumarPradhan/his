package com.param.adt.admission.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.adt.discharge.model.Discharge;
import com.param.adt.master.global.model.OrganizationMaster;
import com.param.adt.master.global.model.UnitMaster;
import com.param.adt.transfer.model.Transfer;
import com.param.adt.transfer.model.TransferRequest;
import com.param.global.model.DoctorMaster;
import com.param.global.model.PatientRegistration;
import com.param.global.model.SpecialityMaster;
import com.param.global.model.UnknownPatientRegistration;
import com.param.global.model.VisitTypeMaster;

@NamedQueries({

		

		@NamedQuery(name = "GET_ER_OTHER_ADMISSION_LIST", query = "SELECT admission.admissionId as admissionId, "
				+ "admission.tPatientId as patientId, " 
				+ "admission.patientId as patientId, "
				+ "admission.admissionNumber as admissionNumber, " 
				+ "admission.visitTypeId as visitTypeId, "
				+ "admission.doctorId as doctorId, " 
				+ "pr.tUhid as UHIDNumber, " 
				+ "pr.patientName as pFirstName, "
				+ "pr.genderId as genderId, " 
				+ "pr.age as age, " 
				+ "pr.ageFormat as ageFormat, "
				+ "gm.code as genderCode, " 
				+ "doc.firstName as dFirstName, " 
				+ "doc.lastName as dLastName, "
				+ "doc.middleName as dMiddleName, " 
				+ "adl.admissionId as admissionId, "
				+ "to_char(adl.doa,'dd/MM/yyyy') as doa, " 
				+ "to_char(adl.pdd,'dd/MM/yyyy') as pdd, "
				+ "bm.bedNumber as bedNumber, " 
				+ "bm.floorId as floorId, " 
				+ "bm.bedId as bedId "
				+ "FROM Admission admission " 
				+ "INNER JOIN admission.unknownPatientRegistration pr "
				+ "INNER JOIN pr.genderMaster gm " 
				+ "INNER JOIN admission.doctorMaster doc "
				+ "INNER JOIN admission.admissionDetailsList adl " 
				+ "INNER JOIN adl.bedMaster bm "
				+ "WHERE admission.status='A' " 
				+ "AND admission.organizationId=:organizationId "
				+ "AND admission.unitId=:unitId " 
				+ "AND admission.visitTypeId=4 " 
				+ "AND adl.erBedTypeAllocation>0 "
				+ "AND adl.bedId>0 "),



		@NamedQuery(name = "GET_BED_RESERVATION_LIST_DETAILS", query = "SELECT admissionNote.admissionNoteId as admissionNoteId, "
				+ "admissionNote.visitTypeId as visitTypeId, " 
				+ "admissionNote.patientId as patientId, "
				+ "admissionNote.id as id, " 
				+ "admissionNote.reasonId as reasonId, "
				+ "admissionNote.requestToDoctorId as requestToDoctorId, "
				+ "admissionNote.doctorSpecialityId as doctorSpecialityId, "
				+ "admissionNote.isFlexiableDate as isFlexiableDate, " 
				+ "doctor.firstName as dFirstName, "
				+ "doctor.middleName as dMiddleName, " 
				+ "doctor.lastName as dLastName, "
				+ "speciality.specialityName as specialityName, " 
				+ "visitType.visitTypeName as visitTypeName, "
				+ "patient.uhidNumber as UHIDNumber, " 
				+ "patient.firstName as pFirstName, "
				+ "patient.middleName as pMiddleName, " 
				+ "patient.lastName as pLastName, "
				+ "to_char(patient.dob,'dd/MM/yyyy') as dob, " 
				+ "gender.code as code, "
				+ "reason.reasonDesc as reasonDesc, " 
				+ "mapper.bedCategoryId as bedCategoryId, "
				+ "mapper.paymentEntitlementId as paymentEntitlementId, "
				+ "mapper.patientCategoryId as patientCategoryId, " 
				+ "to_char(mapper.doa,'dd/MM/yyyy') as doa, "
				+ "to_char(mapper.pdd,'dd/MM/yyyy') as pdd, " 
				+ "bedCatMst.bedCategoryDesc as bedCategoryDesc, "
				+ "patCategoryMst.patientCategory as patientCategory, "
				+ "payEn.paymentEntitlementDesc as paymentEntitlementDesc " 
				+ "FROM AdmissionNote as admissionNote "
				+ "INNER JOIN admissionNote.doctorMaster as doctor "
				+ "INNER JOIN admissionNote.visitTypeMaster as visitType "
				+ "INNER JOIN admissionNote.patientRegistration as patient "
				+ "INNER JOIN admissionNote.genderMaster as gender "
				+ "INNER JOIN admissionNote.reasonMaster as reason "
				+ "INNER JOIN admissionNote.specialityMaster as speciality "
				+ "INNER JOIN admissionNote.admissionPatientMappersList as mapper "
				+ "INNER JOIN mapper.patientCategoryMaster patCategoryMst "
				+ "INNER JOIN mapper.bedCategoryMaster bedCatMst " 
				+ "INNER JOIN mapper.paymentEntitlementMaster payEn "
				+ "WHERE admissionNote.admissionStatus='R' " 
				+ "AND mapper.activeStatus='R' " 
				+ "AND mapper.status='A' "
				+ "AND admissionNote.organizationId=:organizationId " 
				+ "AND admissionNote.unitId=:unitId"),


})

@NamedNativeQueries({
	
	@NamedNativeQuery(name = "GET_ADMITTED_PATIENT_LIST", 
			query = 
				"SELECT admission.admission_id as \"admissionId\", "
				+ "admission.patient_id as \"patientId\", "
				+ "admission.t_patient_id as \"tPatientId\", "
				+ "admission.admission_number as \"admissionNumber\", "
				+ "admission.visit_type_id as \"visitTypeId\", " 
				+ "admission.doctor_id as \"doctorId\", "
				+ "admission.speciality_id as \"specialityId\", " 
				+ "sp.speciality_name as \"specialityName\", "
				+ "pr.uhid_number as \"UHIDNumber\", " 
				+ "concat(pr.first_name,' ',pr.middle_name,' ',pr.last_name) as \"pFirstName\" , " 
				+ "pr.gender_id as \"genderId\", " 
				+ "gm.gender_code as \"genderCode\", "
				+ "to_char(pr.dob,'MM/dd/yyyy') as \"dob\", " 
				+ "concat(doc.first_name,' ', doc.middle_name,' ',doc.last_name) as \"dFirstName\", "
				+ "to_char(adl.doa,'dd/MM/yyyy') as \"doa\", " 
				+ "to_char(adl.pdd,'dd/MM/yyyy') as \"pdd\", "
				+ "apml.payment_entitlement_id as \"paymentEntitlementId\", "
				+ "pe.payment_entitlement_desc as \"paymentEntitlementDesc\", "
				+ "bedCatMst.bed_category_desc as \"bedCategoryDesc\", " 
				+ "bm.floor_id as \"floorId\", "
				+ "bm.bed_number as \"bedNumber\", "
				+ "bm.bed_id as \"bedId\", "
				+ "bm.ward_id as \"wardId\","
				+ "ward.ward_name as \"wardName\", "
				+ "adl.is_short_leave as \"isShortLeave\" "
				/*+ "nurWard.nursing_station_id as \"nursingStationId\", "
				+ "nur.nursing_station_desc as \"nursingStationDesc\" "*/
				+ "FROM adt.t_admission admission " 
				+ "INNER JOIN patient.t_patient_registration pr on admission.patient_id=pr.patient_id "
				+ "INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id " 
				+ "INNER JOIN doctor.m_doctor_master doc on admission.doctor_id=doc.doctor_id "
				+ "INNER JOIN doctor.m_speciality_master sp on admission.speciality_id=sp.speciality_id " 
				+ "INNER JOIN adt.t_admission_details adl on adl.admission_id=admission.admission_id "
				+ "INNER JOIN adt.m_bed_master bm on adl.bed_id=bm.bed_id "
				+ "INNER JOIN adt.m_ward_master ward on bm.ward_id=ward.ward_id "
				/*	+ "LEFT JOIN adt.t_nursing_station_ward_mapper nurWard on ward.ward_id=nurWard.ward_id "
				+ "LEFT JOIN adt.m_nursing_station_master nur on nurWard.nursing_station_id=nur.nursing_station_id " */
				+ "INNER JOIN adt.t_admission_note anl on admission.admission_id=anl.admission_id "
				+ "INNER JOIN adt.t_admission_patient_mapping apml on anl.admission_note_id = apml.admission_note_id " 
				+ "INNER JOIN adt.m_bed_category_master bedCatMst on apml.bed_category_id = bedCatMst.bed_category_id "
				+ "INNER JOIN public.m_payment_entitlement_master pe on apml.payment_entitlement_id = pe.payment_entitlement_id " 
				+ "WHERE admission.status='A' "
				+ "AND apml.active_status='B' " 
				+ "AND apml.status='A' " 
				+ "AND anl.status='A' "
				+ "AND admission.organization_id=:organizationId " 
				+ "AND admission.unit_id=:unitId "
				+ "UNION "
				+ "SELECT admission.admission_id as \"admissionId\", "
				+ "admission.patient_id as \"patientId\", "
				+ "admission.t_patient_id as \"tPatientId\", "
				+ "admission.admission_number as \"admissionNumber\", "
				+ "admission.visit_type_id as \"visitTypeId\", " 
				+ "admission.doctor_id as \"doctorId\", "
				+ "admission.speciality_id as \"specialityId\", " 
				+ "sp.speciality_name as \"specialityName\", "
				+ "pr.t_uhid as \"UHIDNumber\", " 
				+ "pr.patient_name as \"pFirstName\" , " 
				+ "pr.gender_id as \"genderId\", " 
				+ "gm.gender_code as \"genderCode\", "
				+ "concat(to_char(pr.age,'999'),' ', pr.age_format) as \"dob\", "
				+ "concat(doc.first_name,' ', doc.middle_name,' ',doc.last_name) as \"dFirstName\", "
				+ "to_char(adl.doa,'dd/MM/yyyy') as \"doa\", " 
				+ "to_char(adl.pdd,'dd/MM/yyyy') as \"pdd\", "
				+ "apml.payment_entitlement_id as \"paymentEntitlementId\", "
				+ "pe.payment_entitlement_desc as \"paymentEntitlementDesc\", "
				+ "bedCatMst.bed_category_desc as \"bedCategoryDesc\", "
				+ "bm.floor_id as \"floorId\", "
				+ "bm.bed_number as \"bedNumber\", "
				+ "bm.bed_id as \"bedId\", "
				+ "bm.ward_id as \"wardId\","
				+ "ward.ward_name as \"wardName\", "
				+ "adl.is_short_leave as \"isShortLeave\" "
				/*+ "nurWard.nursing_station_id as \"nursingStationId\", "
				+ "nur.nursing_station_desc as \"nursingStationDesc\" "*/
				+ " FROM adt.t_admission admission " 
				+ " INNER JOIN patient.m_unknown_patient_registration pr on admission.t_patient_id=pr.unknown_patient_id " 
				+ " INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id " 
				+ " INNER JOIN doctor.m_doctor_master doc on admission.doctor_id=doc.doctor_id "
				+ " INNER JOIN doctor.m_speciality_master sp on admission.speciality_id=sp.speciality_id "
				+ " INNER JOIN adt.t_admission_details adl on adl.admission_id=admission.admission_id "
				+ " INNER JOIN adt.m_bed_master bm on adl.bed_id=bm.bed_id " 
				+ "INNER JOIN adt.m_ward_master ward on bm.ward_id=ward.ward_id "
				/*+ "LEFT JOIN adt.t_nursing_station_ward_mapper nurWard on ward.ward_id=nurWard.ward_id "
				+ "LEFT JOIN adt.m_nursing_station_master nur on nurWard.nursing_station_id=nur.nursing_station_id " */
				+ " INNER JOIN adt.t_admission_note anl on admission.admission_id=anl.admission_id "
				+ " INNER JOIN adt.t_admission_patient_mapping apml on anl.admission_note_id = apml.admission_note_id "  
				+ " INNER JOIN adt.m_bed_category_master bedCatMst on apml.bed_category_id = bedCatMst.bed_category_id "
				+ " INNER JOIN public.m_payment_entitlement_master pe on apml.payment_entitlement_id = pe.payment_entitlement_id " 
				+ " WHERE admission.status='A' "
				+ " AND apml.active_status='B' " 
				+ " AND apml.status='A' " 
				+ " AND anl.status='A' "
				+ " AND admission.organization_id=:organizationId "
				+ " AND admission.unit_id=:unitId"),
	
	
	@NamedNativeQuery(name = "GET_ADMITTED_PATIENT_LIST_FOR_DISCHARGE", 
	query = 
				"SELECT admission.admission_id as \"admissionId\", "
				+ "admission.patient_id as \"patientId\", "
				+ "admission.t_patient_id as \"tPatientId\", "
				+ "admission.admission_number as \"admissionNumber\", "
				+ "admission.visit_type_id as \"visitTypeId\", " 
				+ "admission.doctor_id as \"doctorId\", "
				+ "admission.speciality_id as \"specialityId\", " 
				+ "sp.speciality_name as \"specialityName\", "
				+ "pr.uhid_number as \"UHIDNumber\", " 
				+ "concat(pr.first_name,' ',pr.middle_name,' ',pr.last_name) as \"pFirstName\" , " 
				+ "pr.gender_id as \"genderId\", " 
				+ "gm.gender_code as \"genderCode\", "
				+ "to_char(pr.dob,'MM/dd/yyyy') as \"dob\", " 
				+ "  concat(doc.first_name,' ', doc.middle_name,' ',doc.last_name) as \"dFirstName\", "
				//+ "adl.admission_id as \"admissionId\", "
				+ "to_char(adl.doa,'dd/MM/yyyy') as \"doa\", " 
				+ "to_char(adl.pdd,'dd/MM/yyyy') as \"pdd\", "
				+ "apml.payment_entitlement_id as \"paymentEntitlementId\", "
				+ "pe.payment_entitlement_desc as \"paymentEntitlementDesc\", "
				+ "bedCatMst.bed_category_desc as \"bedCategoryDesc\", " 
				+ "bm.bed_number as \"bedNumber\", "
				+ "bm.bed_id as \"bedId\", "
				+ "bm.ward_id as \"wardId\","
				+ "ward.ward_name as \"wardName\", "
				+ "nurWard.nursing_station_id as \"nursingStationId\", "
				+ "nur.nursing_station_desc as \"nursingStationDesc\" "
				+ "FROM adt.t_admission admission " 
				+ "INNER JOIN patient.t_patient_registration pr on admission.patient_id=pr.patient_id "
				+ "INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id " 
				+ "INNER JOIN doctor.m_doctor_master doc on admission.doctor_id=doc.doctor_id "
				+ "INNER JOIN doctor.m_speciality_master sp on admission.speciality_id=sp.speciality_id " 
				+ "INNER JOIN adt.t_admission_details adl on adl.admission_id=admission.admission_id "
				+ "INNER JOIN adt.m_bed_master bm on adl.bed_id=bm.bed_id "
				+ "INNER JOIN adt.m_ward_master ward on bm.ward_id=ward.ward_id "
				+ "INNER JOIN adt.t_nursing_station_ward_mapper nurWard on ward.ward_id=nurWard.ward_id "
				+ "INNER JOIN adt.m_nursing_station_master nur on nurWard.nursing_station_id=nur.nursing_station_id " 
				+ "INNER JOIN adt.t_admission_note anl on admission.admission_id=anl.admission_id "
				+ "INNER JOIN adt.t_admission_patient_mapping apml on anl.admission_note_id = apml.admission_note_id " 
				+ "INNER JOIN adt.m_bed_category_master bedCatMst on apml.bed_category_id = bedCatMst.bed_category_id "
				+ "INNER JOIN public.m_payment_entitlement_master pe on apml.payment_entitlement_id = pe.payment_entitlement_id " 
				+ "WHERE admission.status='A' "
				+ "AND apml.active_status='B' " 
				+ "AND apml.status='A' " 
				+ "AND anl.status='A' "
				+ "AND adl.is_discharge='N' "
				+ "AND adl.pdd=:pdd "
				+ "AND admission.organization_id=:organizationId " 
				+ "AND admission.unit_id=:unitId "
				+ "UNION "
				+ "SELECT admission.admission_id as \"admissionId\", "
				+ "admission.patient_id as \"patientId\", "
				+ "admission.t_patient_id as \"tPatientId\", "
				+ "admission.admission_number as \"admissionNumber\", "
				+ "admission.visit_type_id as \"visitTypeId\", " 
				+ "admission.doctor_id as \"doctorId\", "
				+ "admission.speciality_id as \"specialityId\", " 
				+ "sp.speciality_name as \"specialityName\", "
				+ "pr.t_uhid as \"UHIDNumber\", " 
				+ "pr.patient_name as \"pFirstName\" , " 
				+ "pr.gender_id as \"genderId\", " 
				+ "gm.gender_code as \"genderCode\", "
				+ "concat(to_char(pr.age,'999'),' ', pr.age_format) as \"dob\", "
				+ "  concat(doc.first_name,' ', doc.middle_name,' ',doc.last_name) as \"dFirstName\", "
				//+ "adl.admission_id as \"admissionId\", "
				+ "to_char(adl.doa,'dd/MM/yyyy') as \"doa\", " 
				+ " to_char(adl.pdd,'dd/MM/yyyy') as \"pdd\", "
				+ " apml.payment_entitlement_id as \"paymentEntitlementId\", "
				+ " pe.payment_entitlement_desc as \"paymentEntitlementDesc\", "
				+ " bedCatMst.bed_category_desc as \"bedCategoryDesc\", "
				+ "bm.bed_number as \"bedNumber\", "
				+ "bm.bed_id as \"bedId\", "
				+ "bm.ward_id as \"wardId\","
				+ "ward.ward_name as \"wardName\", "
				+ "nurWard.nursing_station_id as \"nursingStationId\", "
				+ "nur.nursing_station_desc as \"nursingStationDesc\" "
				+ " FROM adt.t_admission admission " 
				+ " INNER JOIN patient.m_unknown_patient_registration pr on admission.t_patient_id=pr.unknown_patient_id " 
				+ " INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id " 
				+ " INNER JOIN doctor.m_doctor_master doc on admission.doctor_id=doc.doctor_id "
				+ " INNER JOIN doctor.m_speciality_master sp on admission.speciality_id=sp.speciality_id "
				+ " INNER JOIN adt.t_admission_details adl on adl.admission_id=admission.admission_id "
				+ " INNER JOIN adt.m_bed_master bm on adl.bed_id=bm.bed_id " 
				+ "INNER JOIN adt.m_ward_master ward on bm.ward_id=ward.ward_id "
				+ "INNER JOIN adt.t_nursing_station_ward_mapper nurWard on ward.ward_id=nurWard.ward_id "
				+ "INNER JOIN adt.m_nursing_station_master nur on nurWard.nursing_station_id=nur.nursing_station_id " 
				+ " INNER JOIN adt.t_admission_note anl on admission.admission_id=anl.admission_id "
				+ " INNER JOIN adt.t_admission_patient_mapping apml on anl.admission_note_id = apml.admission_note_id "  
				+ " INNER JOIN adt.m_bed_category_master bedCatMst on apml.bed_category_id = bedCatMst.bed_category_id "
				+ " INNER JOIN public.m_payment_entitlement_master pe on apml.payment_entitlement_id = pe.payment_entitlement_id " 
				+ " WHERE admission.status='A' "
				+ " AND apml.active_status='B' " 
				+ " AND apml.status='A' " 
				+ " AND anl.status='A' "
				+ "AND adl.is_discharge='N' "
				+ "AND adl.pdd=:pdd "
				+ " AND admission.organization_id=:organizationId "
				+ " AND admission.unit_id=:unitId"),

		@NamedNativeQuery(name = "GET_ER_BED_TYPE_ALLOCATION_LIST", query = "SELECT ad.admission_id as \"admissionId\", "
				+ "ad.patient_id as \"patientId\", " + "ad.t_patient_id as \"tPatientId\", "
				+ "ad.visit_type_id as \"visitTypeId\", " + "ad.doctor_id as \"doctorId\", "
				+ "gn.gender_code as \"genderName\", " + "uk.t_uhid as \"UHIDNumber\", "
				+ "uk.patient_name as \"patientName\", " + "uk.gender_id as \"genderId\", "
				+ "concat(to_char(uk.age,'999'),' ', uk.age_format) as \"dob\", "
				+ "adtails.admission_details_id as \"admissionDetailsId\", "
				+ "to_char(ad.created_date,'dd-MM-yyyy HH:mm:ss') as \"createdDate\", "
				+ "to_char(ad.created_date,'dd/MM/yyyy') as \"encounterDate\", "
				+ "ad.admission_number as \"admissionNumber\", " 
				+ "ad.created_by as \"createdBy\", "
				+ "pri.priority_desc as \"priorityDesc\" "
				+ "FROM adt.t_admission AS ad "
				+ "INNER JOIN patient.m_unknown_patient_registration AS uk ON ad.t_patient_id = uk.unknown_patient_id "
				+ "INNER JOIN public.m_gender_master gn on uk.gender_id = gn.gender_id "
				+ "INNER JOIN adt.t_admission_details adtails on ad.admission_id = adtails.admission_id "
				+ "LEFT JOIN adt.t_medico_legal medi on ad.admission_id=medi.admission_id "
				+ "LEFT JOIN adt.m_priority_master pri on medi.priority_id=pri.priority_id "
				+ "WHERE ad.visit_type_id=4 " 
				+ "AND ad.organization_id=:orgId " 
				+ "AND ad.unit_id=:unitId "
				+ "AND adtails.admission_type_id=4 "
				+ "AND coalesce(adtails.er_bed_type_allocation,0) = 0 " 
				+ "UNION "
				+ "SELECT ad.admission_id as \"admissionId\", " 
				+ "ad.patient_id as \"patientId\", "
				+ "ad.t_patient_id as \"tPatientId\", " 
				+ "ad.visit_type_id as \"visitTypeId\", "
				+ "ad.doctor_id as \"doctorId\", " 
				+ "gn.gender_code as \"genderName\", "
				+ "pt.uhid_number as \"UHIDNumber\", "
				+ "concat(pt.first_name,' ',pt.middle_name,' ',pt.last_name) as \"patientName\", "
				+ "pt.gender_id as \"genderId\", " 
				+ "to_char(pt.dob,'dd/MM/yyyy') as \"dob\", "
				+ "adtails.admission_details_id as \"admissionDetailsId\", "
				+ "to_char(ad.created_date,'dd-MM-yyyy HH:mm:ss') as \"createdDate\", "
				+ "to_char(ad.created_date,'dd/MM/yyyy') as \"encounterDate\", "
				+ "ad.admission_number as \"admissionNumber\", " 
				+ "ad.created_by as \"createdBy\", "
				+ "pri.priority_desc as \"priorityDesc\" "
				+ "FROM adt.t_admission AS ad "
				+ "INNER JOIN patient.t_patient_registration AS pt ON ad.patient_id = pt.patient_id "
				+ "INNER JOIN public.m_gender_master gn on pt.gender_id = gn.gender_id "
				+ "INNER JOIN adt.t_admission_details adtails on ad.admission_id = adtails.admission_id "
				+ "LEFT JOIN adt.t_medico_legal medi on ad.admission_id=medi.admission_id "
				+ "LEFT JOIN adt.m_priority_master pri on medi.priority_id=pri.priority_id "
				+ "WHERE ad.visit_type_id=4 " 
				+ "AND ad.organization_id=:orgId " 
				+ "AND ad.unit_id=:unitId "
				+ "AND adtails.admission_type_id=4 "
				+ "AND coalesce(adtails.er_bed_type_allocation,0) = 0 "),

		// ------------------------------------------------------------------
		@NamedNativeQuery(name = "GET_ER_ADMISSION_LIST", query = "select admission.admission_id as \"admissionId\", "
				+ "admissionDetails.admission_details_id as \"admissionDetailsId\", "
				+ "admission.patient_id as \"patientId\", " 
				+ "admission.t_patient_id as \"tPatientId\", "
				+ "admission.admission_number as \"admissionNumber\", " 
				+ "admission.doctor_id as \"doctorId\", "
				+ "concat(doctor.first_name,' ',doctor.middle_name,' ',doctor.last_name) as \"dFirstName\", "
				+ "to_char(admissionDetails.pdd,'dd/mm/yyyy HH:mm:ss')  as \"pdd\", "
				+ "bedMaster.bed_number as \"bedNumber\", " 
				+ "patient.uhid_number as \"UHIDNumber\", "
				+ "concat(patient.first_name,' ',patient.middle_name,' ',patient.last_name) as \"patientName\", "
				+ "to_char(patient.dob,'dd/MM/yyyy') as \"dob\", " 
				+ "gender.gender_code as \"genderName\", "
				+ "oum.number_of_hours as \"noOfHours\", "
				+ "bedMaster.bed_id as \"bedId\" "
				+ "FROM adt.t_admission admission "
				+ "inner join patient.t_patient_registration patient on admission.patient_id=patient.patient_id "
				+ "inner join public.m_gender_master gender on patient.gender_id=gender.gender_id "
				+ "inner join doctor.m_doctor_master doctor on admission.doctor_id=doctor.doctor_id "
				+ "inner join adt.t_admission_details admissionDetails on admission.admission_id=admissionDetails.admission_id "
				+ "left join adt.m_bed_master bedMaster on admissionDetails.bed_id=bedMaster.bed_id "
				+ "left join adt.m_bed_category_master bc on bedMaster.bed_category_id=bc.bed_category_id "
				+ "left join public.m_occupancy_unit_master oum on bc.occupancy_unit_id=oum.occupancy_unit_id "
				+ "where admission.status='A' "
				+ "and admission.visit_type_id=4 "
				+ "and admissionDetails.er_bed_type_allocation>0 " + "and admission.patient_id > 0 "
				+ "AND admission.organization_id=:orgId " 
				+ "AND admission.unit_id=:unitId " 
				+ "AND admissionDetails.is_discharge='N' "
				+ "union "
				+ "select admission.admission_id as \"admissionId\", "
				+ "admissionDetails.admission_details_id as \"admissionDetailsId\", "
				+ "admission.patient_id as \"patientId\", " 
				+ "admission.t_patient_id as \"tPatientId\", "
				+ "admission.admission_number as \"admissionNumber\", " 
				+ "admission.doctor_id as \"doctorId\", "
				+ "concat(doctor.first_name,' ',doctor.middle_name,' ',doctor.last_name) as \"dFirstName\", "
				+ "to_char(admissionDetails.pdd,'dd/mm/yyyy HH:mm:ss') as \"pdd\", "
				+ "bedMaster.bed_number as \"bedNumber\"," 
				+ "unknownPatient.t_uhid as \"UHIDNumber\", "
				+ "unknownPatient.patient_name as \"patientName\","
				+ "concat(to_char(unknownPatient.age,'999'),' ', unknownPatient.age_format) as \"dob\", "
				+ "gender.gender_code as \"genderName\", " 
				+ "oum.number_of_hours as \"noOfHours\", "
				+ "bedMaster.bed_id as \"bedId\" "
				+ "FROM adt.t_admission admission "
				+ "inner join patient.m_unknown_patient_registration unknownPatient on admission.t_patient_id=unknownPatient.unknown_patient_id "
				+ "inner join public.m_gender_master gender on unknownPatient.gender_id=gender.gender_id "
				+ "inner join doctor.m_doctor_master doctor on admission.doctor_id=doctor.doctor_id "
				+ "inner join adt.t_admission_details admissionDetails on admission.admission_id=admissionDetails.admission_id "
				+ "left join adt.m_bed_master bedMaster on admissionDetails.bed_id=bedMaster.bed_id "
				+ "left join adt.m_bed_category_master bc on bedMaster.bed_category_id=bc.bed_category_id "
				+ "left join public.m_occupancy_unit_master oum on bc.occupancy_unit_id=oum.occupancy_unit_id "
				+ "where admission.status='A' " 
				/*+ "and admission.organization_id=1 "
				+ "and admission.unit_id=1 "
				*/+ "and admission.visit_type_id=4 "
				+ "and admissionDetails.er_bed_type_allocation>0 " 
				+ "and admission.t_patient_id > 0 "
				+ "AND admission.organization_id=:orgId " 
				+ "AND admission.unit_id=:unitId "
				+ "AND admissionDetails.is_discharge='N' "),
		
		@NamedNativeQuery(name="GET_DATA_FOR_MAP_OF_BED",query="select admission.admission_id as \"admissionId\", "
				+ "admissionDetails.admission_details_id as \"admissionDetailsId\", "
				+ "admission.patient_id as \"patientId\", " 
				+ "admission.t_patient_id as \"tPatientId\", "
				+ "admission.admission_number as \"admissionNumber\", " 
				+ "admission.doctor_id as \"doctorId\", "
				+ "admission.visit_type_id as \"visitTypeId\", "
				+ "concat(doctor.first_name,' ',doctor.middle_name,' ',doctor.last_name) as \"dFirstName\", "
				+ "to_char(admissionDetails.pdd,'dd/MM/yyyy HH:mm:ss')  as \"pdd\", "
				+ "to_char(admissionDetails.doa,'dd/MM/yyyy HH:mm:ss')  as \"doa\", "
				+ "bedMaster.bed_number as \"bedNumber\", " 
				+ "patient.uhid_number as \"UHIDNumber\", "
				+ "patient.gender_id as \"genderId\", "
				+ "patient.prefix_id as \"prefixId\", "
				+ "patient.patient_category_id as \"patientCategoryId\", "
				+ "concat(patient.first_name,' ',patient.middle_name,' ',patient.last_name) as \"patientName\", "
				+ "to_char(patient.dob,'dd/MM/yyyy') as \"dob\", " 
				+ "gender.gender_code as \"genderName\", "
				+ "bedMaster.bed_id as \"bedId\", "
				+ "admissionDetails.floor_id as \"floorId\" " 
				+ "FROM adt.t_admission admission "
				+ "inner join patient.t_patient_registration patient on admission.patient_id=patient.patient_id "
				+ "inner join public.m_gender_master gender on patient.gender_id=gender.gender_id "
				+ "inner join doctor.m_doctor_master doctor on admission.doctor_id=doctor.doctor_id "
				+ "inner join adt.t_admission_details admissionDetails on admission.admission_id=admissionDetails.admission_id "
				+ "inner join adt.m_bed_master bedMaster on admissionDetails.bed_id=bedMaster.bed_id "
				+ "where admission.status='A' "
				+ "and admission.organization_id=1 "
				+ "and admission.unit_id=1 "
				+ "and admission.visit_type_id=4 "
				+ "and admissionDetails.er_bed_type_allocation=1 " 
				+ "and admission.patient_id > 0 "
				+ "AND admission.organization_id=:orgId " 
				+ "AND admission.unit_id=:unitId " 
				+ "union "
				+ "select admission.admission_id as \"admissionId\", "
				+ "admissionDetails.admission_details_id as \"admissionDetailsId\", "
				+ "admission.patient_id as \"patientId\", " 
				+ "admission.t_patient_id as \"tPatientId\", "
				+ "admission.admission_number as \"admissionNumber\", " 
				+ "admission.doctor_id as \"doctorId\", "
				+ "admission.visit_type_id as \"visitTypeId\", "
				+ "concat(doctor.first_name,' ',doctor.middle_name,' ',doctor.last_name) as \"dFirstName\", "
				+ "to_char(admissionDetails.pdd,'dd/MM/yyyy HH:mm:ss') as \"pdd\", "
				+ "to_char(admissionDetails.doa,'dd/MM/yyyy HH:mm:ss') as \"doa\", "
				+ "bedMaster.bed_number as \"bedNumber\", " 
				+ "unknownPatient.t_uhid as \"UHIDNumber\", "
				+ "unknownPatient.gender_id as \"genderId\", "
				+ "unknownPatient.prefix_id as \"prefixId\", "
				+ "unknownPatient.prefix_id as \"patientCategoryId\", "
				+ "unknownPatient.patient_name as \"patientName\","
				+ "concat(to_char(unknownPatient.age,'999'),' ', unknownPatient.age_format) as \"dob\", "
				+ "gender.gender_code as \"genderName\", " 
				+ "bedMaster.bed_id as \"bedId\", "
				+ "admissionDetails.floor_id as \"floorId\" "
				+ "FROM adt.t_admission admission "
				+ "inner join patient.m_unknown_patient_registration unknownPatient on admission.t_patient_id=unknownPatient.unknown_patient_id "
				+ "inner join public.m_gender_master gender on unknownPatient.gender_id=gender.gender_id "
				+ "inner join doctor.m_doctor_master doctor on admission.doctor_id=doctor.doctor_id "
				+ "inner join adt.t_admission_details admissionDetails on admission.admission_id=admissionDetails.admission_id "
				+ "inner join adt.m_bed_master bedMaster on admissionDetails.bed_id=bedMaster.bed_id "
				+ "where admission.status='A' " 
				+ "and admission.organization_id=1 "
				+ "and admission.unit_id=1 and admission.visit_type_id=4 "
				+ "and admissionDetails.er_bed_type_allocation=1 " 
				+ "and admission.t_patient_id > 0 "
				+ "AND admission.organization_id=:orgId " 
				+ "AND admission.unit_id=:unitId "),
		
		@NamedNativeQuery(name="GET_ER_PATIENT_LIST", query="select admission.admission_id as \"admissionId\", "
				+ "admission.patient_id as \"patientId\", "
				+ "admission.t_patient_id as \"tPatientId\", "
				+ "concat(pat.first_name,' ',pat.middle_name,' ',pat.last_name) as \"patientName\", "
				+ "pat.uhid_number as \"UHIDNumber\" "
				+" from adt.t_admission admission "
				+" inner join patient.t_patient_registration pat on admission.patient_id=pat.patient_id " 
				+" inner join adt.t_admission_details admdtls on admission.admission_id=admdtls.admission_id " 
				+" WHERE admdtls.er_bed_type_allocation>0 "
				+" AND (LOWER(concat(pat.first_name,' ',pat.middle_name,' ',pat.last_name)) ILIKE :firstName OR LOWER(pat.uhid_number) ILIKE :firstName) "
				+" AND admission.organization_id=:orgId "
				+" AND admission.unit_id=:unitId "
				+" union " 
				+"select admission.admission_id as \"admissionId\", "
				+ "admission.patient_id as \"patientId\", "
				+ "admission.t_patient_id as \"tPatientId\", "
				+" ukpat.patient_name as \"patientName\", "
				+" ukpat.t_uhid as \"UHIDNumber\" "
				+" from adt.t_admission admission "
				+" inner join patient.m_unknown_patient_registration ukpat on admission.t_patient_id=ukpat.unknown_patient_id " 
				+" inner join adt.t_admission_details admdtls on admission.admission_id=admdtls.admission_id " 
				+" WHERE admdtls.er_bed_type_allocation>0 "
				+" AND (LOWER(ukpat.patient_name) ILIKE :firstName OR LOWER(ukpat.t_uhid) ILIKE :firstName) "
				+" AND admission.organization_id=:orgId "
				+" AND admission.unit_id=:unitId "),
		
		@NamedNativeQuery(name = "GET_ER_ADMISSION_LIST_BY_MUL_CRI", query = "select admission.admission_id as \"admissionId\", "
				+ "admissionDetails.admission_details_id as \"admissionDetailsId\", "
				+ "admission.patient_id as \"patientId\", " 
				+ "admission.t_patient_id as \"tPatientId\", "
				+ "admission.admission_number as \"admissionNumber\", " 
				+ "admission.doctor_id as \"doctorId\", "
				+ "concat(doctor.first_name,' ',doctor.middle_name,' ',doctor.last_name) as \"dFirstName\", "
				+ "to_char(admissionDetails.pdd,'dd/mm/yyyy HH:mm:ss')  as \"pdd\", "
				+ "bedMaster.bed_number as \"bedNumber\", " 
				+ "patient.uhid_number as \"UHIDNumber\", "
				+ "concat(patient.first_name,' ',patient.middle_name,' ',patient.last_name) as \"patientName\", "
				+ "to_char(patient.dob,'dd/MM/yyyy') as \"dob\", " 
				+ "gender.gender_code as \"genderName\", "
				+ "bedMaster.bed_id as \"bedId\" " 
				+ "FROM adt.t_admission admission "
				+ "inner join patient.t_patient_registration patient on admission.patient_id=patient.patient_id "
				+ "inner join public.m_gender_master gender on patient.gender_id=gender.gender_id "
				+ "inner join doctor.m_doctor_master doctor on admission.doctor_id=doctor.doctor_id "
				+ "inner join adt.t_admission_details admissionDetails on admission.admission_id=admissionDetails.admission_id "
				+ "left join adt.m_bed_master bedMaster on admissionDetails.bed_id=bedMaster.bed_id "
				+ "where admission.status='A' "
				+ "and admission.visit_type_id=4 "
				+ "and admissionDetails.er_bed_type_allocation>0 " 
				+ "and admission.patient_id > 0 "
				+ "AND admission.organization_id=:orgId " 
				+ "AND admission.unit_id=:unitId " 
				+ "union "
				+ "select admission.admission_id as \"admissionId\", "
				+ "admissionDetails.admission_details_id as \"admissionDetailsId\", "
				+ "admission.patient_id as \"patientId\", " 
				+ "admission.t_patient_id as \"tPatientId\", "
				+ "admission.admission_number as \"admissionNumber\", " 
				+ "admission.doctor_id as \"doctorId\", "
				+ "concat(doctor.first_name,' ',doctor.middle_name,' ',doctor.last_name) as \"dFirstName\", "
				+ "to_char(admissionDetails.pdd,'dd/mm/yyyy HH:mm:ss') as \"pdd\", "
				+ "bedMaster.bed_number as \"bedNumber\"," 
				+ "unknownPatient.t_uhid as \"UHIDNumber\", "
				+ "unknownPatient.patient_name as \"patientName\","
				+ "concat(to_char(unknownPatient.age,'999'),' ', unknownPatient.age_format) as \"dob\", "
				+ "gender.gender_code as \"genderName\", " + "bedMaster.bed_id as \"bedId\" "
				+ "FROM adt.t_admission admission "
				+ "inner join patient.m_unknown_patient_registration unknownPatient on admission.t_patient_id=unknownPatient.unknown_patient_id "
				+ "inner join public.m_gender_master gender on unknownPatient.gender_id=gender.gender_id "
				+ "inner join doctor.m_doctor_master doctor on admission.doctor_id=doctor.doctor_id "
				+ "inner join adt.t_admission_details admissionDetails on admission.admission_id=admissionDetails.admission_id "
				+ "left join adt.m_bed_master bedMaster on admissionDetails.bed_id=bedMaster.bed_id "
				+ "where admission.status='A' " 
				+ "and admission.visit_type_id=4 "
				+ "and admissionDetails.er_bed_type_allocation>0 " + "and admission.t_patient_id > 0 "
				+ "AND admission.organization_id=:orgId " + "AND admission.unit_id=:unitId "),
		
		@NamedNativeQuery(name="GET_ADMITTED_PATIENT_LIST_LIKE" , query= 
				"SELECT admission.admission_id as \"admissionId\", "
				+ "admission.patient_id as \"patientId\", " 
				+ "admission.t_patient_id as \"tPatientId\", "
				+ "admission.doctor_id as \"doctorId\", "
				+ "concat(patient.first_name,' ',patient.last_name) as \"patientName\", "
				+ "to_char(patient.dob,'dd/MM/yyyy') as \"dob\", " 
				+ "gender.gender_code as \"genderName\", "
				+ "patient.uhid_number as \"UHIDNumber\", "
				+ "bm.bed_number as \"bedNumber\", " 
				+ "bm.bed_id as \"bedId\", "
				+ "bm.ward_id as \"wardId\", "
				+ "bm.floor_id as \"floorId\", "
				+ "bm.room_id as \"roomId\", "
				+ "admissionDetails.bed_category_id as \"bedCategoryId\", "
				+ "admissionDetails.billing_bed_category_id as \"billingBedCategoryId\", "
				+ "ward.ward_name as \"wardName\", "
				+ "concat(doctor.first_name,' ',doctor.middle_name,' ',doctor.last_name) as \"dFirstName\" "
				+ "FROM  adt.t_admission admission "
				+ "INNER join patient.t_patient_registration patient on admission.patient_id=patient.patient_id "
				+ "INNER join public.m_gender_master gender on patient.gender_id=gender.gender_id "
				+ "INNER join doctor.m_doctor_master doctor on admission.doctor_id=doctor.doctor_id "
				+ "INNER join adt.t_admission_details admissionDetails on admission.admission_id=admissionDetails.admission_id "
				+ "INNER join adt.m_bed_master bm on admissionDetails.bed_id=bm.bed_id "
				+ "INNER JOIN adt.m_ward_master ward on bm.ward_id=ward.ward_id "
				+ "WHERE (LOWER(patient.first_name) LIKE :firstName OR LOWER(patient.last_name) LIKE :firstName OR LOWER(patient.uhid_number) LIKE :firstName) "
				+ "AND admission.status='A' "
				+ "AND admissionDetails.status='A' "
				+ "AND admission.unit_id=:unitId "
				+ "AND admission.organization_id=:organizationId "
				+ "union "
				+ "SELECT admission.admission_id as \"admissionId\", "
				+ "admission.patient_id as \"patientId\", " 
				+ "admission.t_patient_id as \"tPatientId\", "
				+ "admission.doctor_id as \"doctorId\", "
				+ "unknownPatient.patient_name as \"patientName\","
				+ "concat(to_char(unknownPatient.age,'999'),' ', unknownPatient.age_format) as \"dob\", "
				+ "gender.gender_code as \"genderName\", "
				+ "unknownPatient.t_uhid as \"UHIDNumber\", "
				+ "bm.bed_number as \"bedNumber\", " 
				+ "bm.bed_id as \"bedId\", "
				+ "bm.ward_id as \"wardId\","
				+ "bm.floor_id as \"floorId\", "
				+ "bm.room_id as \"roomId\", "
				+ "admissionDetails.bed_category_id as \"bedCategoryId\", "
				+ "admissionDetails.billing_bed_category_id as \"billingBedCategoryId\", "
				+ "ward.ward_name as \"wardName\", "
				+ "concat(doctor.first_name,' ',doctor.middle_name,' ',doctor.last_name) as \"dFirstName\" "
				+ "FROM  adt.t_admission admission "
				+ "inner join patient.m_unknown_patient_registration unknownPatient on admission.t_patient_id=unknownPatient.unknown_patient_id "
				+ "INNER join public.m_gender_master gender on unknownPatient.gender_id=gender.gender_id "
				+ "INNER join doctor.m_doctor_master doctor on admission.doctor_id=doctor.doctor_id "
				+ "INNER join adt.t_admission_details admissionDetails on admission.admission_id=admissionDetails.admission_id "
				+ "INNER join adt.m_bed_master bm on admissionDetails.bed_id=bm.bed_id "
				+ "INNER JOIN adt.m_ward_master ward on bm.ward_id=ward.ward_id "
				+ "WHERE (LOWER(unknownPatient.patient_name) LIKE :firstName  OR LOWER(unknownPatient.t_uhid) LIKE :firstName) "
				+ "AND admission.status='A' "
				+ "AND admissionDetails.status='A' "
				+ "AND admission.unit_id=:unitId "
				+ "AND admission.organization_id=:organizationId "),

		@NamedNativeQuery(name = "GET_ADM_VISIT_NOS_BY_PATIENT_ID_FOR_DEPOSIT",
						  query= "select adm.admission_id as \"encounterId\", adm.admission_number as \"visitNo\" "
								  + " from adt.t_admission adm  "
								  + " inner join adt.t_admission_details admdtls "
								  + " on admdtls.admission_id = adm.admission_id "
								  + " where adm.visit_type_id =:visitTypeId "
								  + " and adm.patient_id = :patientId and admdtls.is_discharge <> 'D' "
								  + " and adm.organization_id =:orgId and adm.unit_id =:unitId ")
})

@Entity
@Table(name = "t_admission", schema = "adt")
@SequenceGenerator(name = "admission_seq", sequenceName = "adt.admission_seq", allocationSize = 1)
public class Admission {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admission_seq")
	@Column(name = "admission_id")
	private int admissionId;

	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "organization_id")
	private Integer organizationId;

	@Column(name = "patient_id")
	private Integer patientId;

	@Column(name = "t_patient_id")
	private Integer tPatientId;

	@Column(name = "admission_number")
	private String admissionNumber;

	@Column(name = "visit_type_id")
	private Integer visitTypeId;

	@Column(name = "against_visit_id")
	private Integer againstVisitId;

	@Column(name = "doctor_id")
	private Integer doctorId;

	@Column(name = "speciality_id")
	private Integer specialityId;

	@Column(name = "status")
	private char status;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, nullable = false, updatable = false)
	private OrganizationMaster organizationMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id", insertable = false, nullable = false, updatable = false)
	private UnitMaster unitMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id", insertable = false, nullable = false, updatable = false)
	private PatientRegistration patientRegistration;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_patient_id", insertable = false, nullable = false, updatable = false)
	private UnknownPatientRegistration unknownPatientRegistration;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "visit_type_id", insertable = false, nullable = false, updatable = false)
	private VisitTypeMaster visitTypeMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "against_visit_id", insertable = false, nullable = false, updatable = false)
	private VisitTypeMaster againstVisitTypeMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctor_id", insertable = false, nullable = false, updatable = false)
	private DoctorMaster doctorMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "speciality_id", insertable = false, nullable = false, updatable = false)
	private SpecialityMaster specialityMaster;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "admission", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AdmissionDetails> admissionDetailsList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "admission", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AdmissionNote> admissionNotesList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "admission", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<VisitorAgainstPatient> visitorAgainstPatientsList;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "admission", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Transfer> transferslist;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "admission", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TransferRequest> transferRequestslist;

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "admission", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Discharge> dischargesList;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "admission", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MedicoLeagal> medicoLeagalsList;
	
	public Integer gettPatientId() {
		return tPatientId;
	}

	public void settPatientId(Integer tPatientId) {
		this.tPatientId = tPatientId;
	}

	public int getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(int admissionId) {
		this.admissionId = admissionId;
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

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getAdmissionNumber() {
		return admissionNumber;
	}

	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}

	public Integer getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public Integer getAgainstVisitId() {
		return againstVisitId;
	}

	public void setAgainstVisitId(Integer againstVisitId) {
		this.againstVisitId = againstVisitId;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(Integer specialityId) {
		this.specialityId = specialityId;
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

	public DoctorMaster getDoctorMaster() {
		return doctorMaster;
	}

	public void setDoctorMaster(DoctorMaster doctorMaster) {
		this.doctorMaster = doctorMaster;
	}


}
