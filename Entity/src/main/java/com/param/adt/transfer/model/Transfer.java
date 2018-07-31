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
import com.param.adt.master.global.model.BedMaster;
import com.param.adt.master.global.model.OrganizationMaster;
import com.param.adt.master.global.model.UnitMaster;
import com.param.adt.master.unit.model.BillingBedCategoryMaster;
import com.param.adt.master.unit.model.RoomMaster;
import com.param.adt.master.unit.model.WardMaster;
import com.param.global.model.BedCategoryMaster;

@NamedNativeQueries({
	
	@NamedNativeQuery(name = "GET_TRANSFER_REQ_LIST", 
			query = 
			"SELECT admission.admission_id as \"admissionId\", "
			+ "transfer.transfer_id as \"transferId\", "
			+ "traReq.transfer_request_id as \"transferRequestId\", "
			+ "adl.admission_details_id as \"admissionDetailsId\", "
			+ "admission.patient_id as \"patientId\", "
			+ "admission.t_patient_id as \"tPatientId\", "
			+ "admission.doctor_id as \"doctorId\", "
			+ "admission.visit_type_id as \"visitTypeId\", " 
			+ "pr.uhid_number as \"UHIDNumber\", " 
			+ "concat(pr.first_name,' ',pr.middle_name,' ',pr.last_name) as \"pFirstName\" , " 
			+ "pr.gender_id as \"genderId\", " 
			+ "gm.gender_code as \"genderCode\", "
			+ "to_char(pr.dob,'MM/dd/yyyy') as \"dob\", " 
			+ "bcm1.bed_category_desc as \"bedCategoryDesc\", "
			+ "bcm2.bed_category_desc as \"toBedCategoryDesc\", " 
			+ "bm.bed_number as \"bedNumber\", "
			+ "bm.bed_category_id as \"bedCategoryId\", "
			+ "adl.ward_id as \"wardId\", "
			+ "ward.ward_name as \"wardName\", "
			+ " transfer.transfer_status_id as \"transferStatusId\", "
			+ "tsm.transfer_status_desc as \"transferStatusDesc\", "
			+ "bcm.hierarchy_id as \"hierarchyId\", "
			+ " traReq.to_bed_category_id as \"toBedCategoryId\", "
			+ " traReq.reason_id as \"reasonId\", "
			+ "reason.reason_desc as \"reasonDesc\", "
			+ " bcm2.hierarchy_id as \"toHierarchyId\", "
			+ " traReq.note as \"note\","
			+ "transfer.to_bed_id as \"toBedId\","
			+ "transfer.from_bed_id as \"fromBedId\", "
			+ "transfer.to_room_id as \"toRoomId\", "
			+ "bm2.billing_bed_category_id as \"billingBedCategoryId\", "
			+ "bm2.floor_id as \"toFloorId\", "
			+ "transfer.to_ward_id as \"toWardId\","
			+ "bm2.bed_number as \"toBedNumber\","
			+ "ward2.ward_name as \"toWardname\", "
			+ "traReq.doctor_reason_id as \"doctorReasonId\", "
			+ "reason2.reason_desc as \"doctorReasonDesc\", "
			+ "traReq.adt_reason_id as \"adtReasonId\", "
			+ "reason3.reason_desc as \"adtReasonDesc\", "
			+ "traReq.doctor_note as \"doctorNote\", "
			+ "traReq.adt_note as \"adtNote\", "
			+ "traReq.doctor_reject_reason as \"doctorRejectReason\", "
			+ "traReq.adt_reject_reason as \"adtRejectReason\","
			+ "traReq.treating_doctor_id  as \"treatingDoctorId\", "
			+" concat(doc.first_name,' ',doc.middle_name,' ',doc.last_name) as \"doctorName\", "
			+ "traReq.transfer_type_id as \"transferTypeId\" "
			+ "FROM adt.t_admission admission " 
			+ "INNER JOIN patient.t_patient_registration pr on admission.patient_id=pr.patient_id "
			+ "INNER JOIN adt.t_transfer transfer on transfer.admission_id=admission.admission_id "
			+ "INNER JOIN adt.m_transfer_status_master tsm on tsm.transfer_status_id=transfer.transfer_status_id "
			+ "LEFT JOIN adt.t_transfer_request traReq on traReq.transfer_request_id=transfer.transfer_request_id "
			+ "INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id " 
			+ "INNER JOIN adt.t_admission_details adl on adl.admission_id=admission.admission_id "
			+ "LEFT JOIN adt.m_bed_master bm on transfer.from_bed_id=bm.bed_id "
			+ "INNER JOIN adt.m_ward_master ward on transfer.from_ward_id=ward.ward_id " 
			+ "INNER JOIN adt.m_bed_category_master bcm on adl.bed_category_id=bcm.bed_category_id "
			+ "INNER JOIN doctor.m_doctor_master doc on traReq.treating_doctor_id=doc.doctor_id "
			+ "LEFT JOIN adt.m_ward_master ward2 on transfer.to_ward_id=ward2.ward_id "
			+ "LEFT JOIN adt.m_bed_master bm2 on transfer.to_bed_id=bm2.bed_id "
			+" LEFT JOIN adt.m_bed_category_master bcm1 on transfer.from_bed_category_id=bcm1.bed_category_id  "
			+" LEFT JOIN adt.m_bed_category_master bcm2 on transfer.to_bed_category_id=bcm2.bed_category_id  "
			+ "LEFT JOIN adt.m_reason_master reason on traReq.reason_id =reason.reason_master_id "
			+ "LEFT JOIN adt.m_reason_master reason2 on traReq.doctor_reason_id =reason2.reason_master_id "
			+ "LEFT JOIN adt.m_reason_master reason3 on traReq.adt_reason_id =reason3.reason_master_id "
			+ " WHERE admission.status='A' "
			+ "AND adl.status='A' "
			+ " AND transfer.status='A' "
			+ "AND admission.organization_id=:organizationId " 
			+ "AND admission.unit_id=:unitId "
			+ " AND traReq.transfer_type_id IN (1,2) "
			//+ "AND traReq.transfer_type_id =:transferTypeId "
			+ "UNION "
			+ "SELECT admission.admission_id as \"admissionId\", "
			+ "transfer.transfer_id as \"transferId\", "
			+ "traReq.transfer_request_id as \"transferRequestId\", "
			+ "adl.admission_details_id as \"admissionDetailsId\", "
			+ "admission.patient_id as \"patientId\", "
			+ "admission.t_patient_id as \"tPatientId\", "
			+ "admission.doctor_id as \"doctorId\", "
			+ "admission.visit_type_id as \"visitTypeId\", " 
			+ "pr.t_uhid as \"UHIDNumber\", " 
			+ "pr.patient_name as \"pFirstName\" , " 
			+ "pr.gender_id as \"genderId\", " 
			+ "gm.gender_code as \"genderCode\", "
			+ "concat(to_char(pr.age,'999'),' ', pr.age_format) as \"dob\", "
			+ "bcm1.bed_category_desc as \"bedCategoryDesc\", "
			+ "bcm2.bed_category_desc as \"toBedCategoryDesc\", " 
			+ " bm.bed_number as \"bedNumber\", "
			+ " bm.bed_category_id as \"bedCategoryId\", "
			+ " adl.ward_id as \"wardId\", "
			+ "ward.ward_name as \"wardName\", "
			+ " transfer.transfer_status_id as \"transferStatusId\", "
			+ "tsm.transfer_status_desc as \"transferStatusDesc\", "
			+ " bcm.hierarchy_id as \"hierarchyId\", "
			+ " traReq.to_bed_category_id as \"toBedCategoryId\", "
			+ " traReq.reason_id as \"reasonId\", "
			+ " reason.reason_desc as \"reasonDesc\", "
			+ " bcm2.hierarchy_id as \"toHierarchyId\", "
			+ " traReq.note as \"note\", "
			+ "transfer.to_bed_id as \"toBedId\","
			+ "transfer.from_bed_id as \"fromBedId\", "
			+ "transfer.to_room_id as \"toRoomId\", "
			+ "bm2.billing_bed_category_id as \"billingBedCategoryId\", "
			+ "bm2.floor_id as \"toFloorId\", "
			+ "transfer.to_ward_id as \"toWardId\","
			+ "bm2.bed_number as \"toBedNumber\","
			+ "ward2.ward_name as \"toWardname\", "
			+ "traReq.doctor_reason_id as \"doctorReasonId\", "
			+ "reason2.reason_desc as \"doctorReasonDesc\", "
			+ "traReq.adt_reason_id as \"adtReasonId\", "
			+ "reason3.reason_desc as \"adtReasonDesc\", "
			+ "traReq.doctor_note as \"doctorNote\", "
			+ "traReq.adt_note as \"adtNote\", "
			+ "traReq.doctor_reject_reason as \"doctorRejectReason\", "
			+ "traReq.adt_reject_reason as \"adtRejectReason\", "
			+ "traReq.treating_doctor_id  as \"treatingDoctorId\", "
			+" concat(doc.first_name,' ',doc.middle_name,' ',doc.last_name) as \"doctorName\", "
			+ "traReq.transfer_type_id as \"transferTypeId\" "
			+ " FROM adt.t_admission admission " 
			+ " INNER JOIN patient.m_unknown_patient_registration pr on admission.t_patient_id=pr.unknown_patient_id "
			+ " INNER JOIN adt.t_transfer transfer on transfer.admission_id=admission.admission_id "
			+ "INNER JOIN adt.m_transfer_status_master tsm on tsm.transfer_status_id=transfer.transfer_status_id "
			+ " LEFT JOIN adt.t_transfer_request traReq on traReq.transfer_request_id=transfer.transfer_request_id "
			+ " INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id " 
			+ " INNER JOIN adt.t_admission_details adl on adl.admission_id=admission.admission_id "
			+ " LEFT JOIN adt.m_bed_master bm on transfer.from_bed_id=bm.bed_id " 
			+ " INNER JOIN adt.m_ward_master ward on transfer.from_ward_id=ward.ward_id " 
			+ " INNER JOIN adt.m_bed_category_master bcm on adl.bed_category_id=bcm.bed_category_id "
			+ "INNER JOIN doctor.m_doctor_master doc on traReq.treating_doctor_id=doc.doctor_id "
			+ "LEFT JOIN adt.m_ward_master ward2 on transfer.to_ward_id=ward2.ward_id "
			+ "LEFT JOIN adt.m_bed_master bm2 on transfer.to_bed_id=bm2.bed_id "
			+" LEFT JOIN adt.m_bed_category_master bcm1 on transfer.from_bed_category_id=bcm1.bed_category_id  "
			+" LEFT JOIN adt.m_bed_category_master bcm2 on transfer.to_bed_category_id=bcm2.bed_category_id  "
			+ "LEFT JOIN adt.m_reason_master reason on traReq.reason_id =reason.reason_master_id "
			+ "LEFT JOIN adt.m_reason_master reason2 on traReq.doctor_reason_id =reason2.reason_master_id "
			+ "LEFT JOIN adt.m_reason_master reason3 on traReq.adt_reason_id =reason3.reason_master_id "
			+ " WHERE admission.status='A' "
			+ " AND adl.status='A' "
			+ " AND transfer.status='A' "
			+ " AND admission.organization_id=:organizationId "
			+ " AND admission.unit_id=:unitId "
			+ " AND traReq.transfer_type_id IN (1,2) "
			//+ "AND traReq.transfer_type_id = :transferTypeId "
			),
	
	@NamedNativeQuery(name = "GET_TRANSFER_REQ_LIST_BY_ADMISSION_ID_FOR_B2B", 
	query = 
	"SELECT admission.admission_id as \"admissionId\", "
	+ "transfer.transfer_id as \"transferId\", "
	+ "traReq.transfer_request_id as \"transferRequestId\", "
	+ "adl.admission_details_id as \"admissionDetailsId\", "
	+ "admission.patient_id as \"patientId\", "
	+ "admission.t_patient_id as \"tPatientId\", "
	+ "admission.doctor_id as \"doctorId\", "
	+ "admission.visit_type_id as \"visitTypeId\", " 
	+ "pr.uhid_number as \"UHIDNumber\", " 
	+ "concat(pr.first_name,' ',pr.middle_name,' ',pr.last_name) as \"pFirstName\" , " 
	+ "pr.gender_id as \"genderId\", " 
	+ "gm.gender_code as \"genderCode\", "
	+ "to_char(pr.dob,'MM/dd/yyyy') as \"dob\", " 
	+ "bcm1.bed_category_desc as \"bedCategoryDesc\", "
	+ "bcm2.bed_category_desc as \"toBedCategoryDesc\", " 
	+ "bm.bed_number as \"bedNumber\", "
	+ "bm.bed_category_id as \"bedCategoryId\", "
	+ "adl.ward_id as \"wardId\", "
	+ "ward.ward_name as \"wardName\", "
	+ " transfer.transfer_status_id as \"transferStatusId\", "
	+ "tsm.transfer_status_desc as \"transferStatusDesc\", "
	+ "bcm.hierarchy_id as \"hierarchyId\", "
	+ " traReq.to_bed_category_id as \"toBedCategoryId\", "
	+ " traReq.reason_id as \"reasonId\", "
	+ "reason.reason_desc as \"reasonDesc\", "
	+ " bcm2.hierarchy_id as \"toHierarchyId\", "
	+ " traReq.note as \"note\","
	+ "transfer.to_bed_id as \"toBedId\","
	+ "transfer.from_bed_id as \"fromBedId\", "
	+ "transfer.to_room_id as \"toRoomId\", "
	+ "bm2.billing_bed_category_id as \"billingBedCategoryId\", "
	+ "bm2.floor_id as \"toFloorId\", "
	+ "transfer.to_ward_id as \"toWardId\","
	+ "bm2.bed_number as \"toBedNumber\","
	+ "ward2.ward_name as \"toWardname\", "
	+ "traReq.doctor_reason_id as \"doctorReasonId\", "
	+ "reason2.reason_desc as \"doctorReasonDesc\", "
	+ "traReq.adt_reason_id as \"adtReasonId\", "
	+ "reason3.reason_desc as \"adtReasonDesc\", "
	+ "traReq.doctor_note as \"doctorNote\", "
	+ "traReq.adt_note as \"adtNote\", "
	+ "traReq.doctor_reject_reason as \"doctorRejectReason\", "
	+ "traReq.adt_reject_reason as \"adtRejectReason\","
	+ "traReq.treating_doctor_id  as \"treatingDoctorId\", "
	+" concat(doc.first_name,' ',doc.middle_name,' ',doc.last_name) as \"doctorName\", "
	+ "traReq.transfer_type_id as \"transferTypeId\" "
	+ "FROM adt.t_admission admission " 
	+ "INNER JOIN patient.t_patient_registration pr on admission.patient_id=pr.patient_id "
	+ "INNER JOIN adt.t_transfer transfer on transfer.admission_id=admission.admission_id "
	+ "INNER JOIN adt.m_transfer_status_master tsm on tsm.transfer_status_id=transfer.transfer_status_id "
	+ "LEFT JOIN adt.t_transfer_request traReq on traReq.transfer_request_id=transfer.transfer_request_id "
	+ "INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id " 
	+ "INNER JOIN adt.t_admission_details adl on adl.admission_id=admission.admission_id "
	+ "LEFT JOIN adt.m_bed_master bm on transfer.from_bed_id=bm.bed_id "
	+ "INNER JOIN adt.m_ward_master ward on transfer.from_ward_id=ward.ward_id " 
	+ "INNER JOIN adt.m_bed_category_master bcm on adl.bed_category_id=bcm.bed_category_id "
	+ "INNER JOIN doctor.m_doctor_master doc on traReq.treating_doctor_id=doc.doctor_id "
	+ "LEFT JOIN adt.m_ward_master ward2 on transfer.to_ward_id=ward2.ward_id "
	+ "LEFT JOIN adt.m_bed_master bm2 on transfer.to_bed_id=bm2.bed_id "
	+" LEFT JOIN adt.m_bed_category_master bcm1 on transfer.from_bed_category_id=bcm1.bed_category_id  "
	+" LEFT JOIN adt.m_bed_category_master bcm2 on transfer.to_bed_category_id=bcm2.bed_category_id  "
	+ "LEFT JOIN adt.m_reason_master reason on traReq.reason_id =reason.reason_master_id "
	+ "LEFT JOIN adt.m_reason_master reason2 on traReq.doctor_reason_id =reason2.reason_master_id "
	+ "LEFT JOIN adt.m_reason_master reason3 on traReq.adt_reason_id =reason3.reason_master_id "
	+ " WHERE admission.status='A' "
	+ "AND adl.status='A' "
	+ " AND transfer.status='A' "
	+ "AND admission.organization_id=:organizationId " 
	+ "AND admission.unit_id=:unitId "
	+ " AND traReq.transfer_type_id IN (1,2) "
	+ "AND admission.admission_id =:admissionId "
	+ "UNION "
	+ "SELECT admission.admission_id as \"admissionId\", "
	+ "transfer.transfer_id as \"transferId\", "
	+ "traReq.transfer_request_id as \"transferRequestId\", "
	+ "adl.admission_details_id as \"admissionDetailsId\", "
	+ "admission.patient_id as \"patientId\", "
	+ "admission.t_patient_id as \"tPatientId\", "
	+ "admission.doctor_id as \"doctorId\", "
	+ "admission.visit_type_id as \"visitTypeId\", " 
	+ "pr.t_uhid as \"UHIDNumber\", " 
	+ "pr.patient_name as \"pFirstName\" , " 
	+ "pr.gender_id as \"genderId\", " 
	+ "gm.gender_code as \"genderCode\", "
	+ "concat(to_char(pr.age,'999'),' ', pr.age_format) as \"dob\", "
	+ "bcm1.bed_category_desc as \"bedCategoryDesc\", "
	+ "bcm2.bed_category_desc as \"toBedCategoryDesc\", " 
	+ " bm.bed_number as \"bedNumber\", "
	+ " bm.bed_category_id as \"bedCategoryId\", "
	+ " adl.ward_id as \"wardId\", "
	+ "ward.ward_name as \"wardName\", "
	+ " transfer.transfer_status_id as \"transferStatusId\", "
	+ "tsm.transfer_status_desc as \"transferStatusDesc\", "
	+ " bcm.hierarchy_id as \"hierarchyId\", "
	+ " traReq.to_bed_category_id as \"toBedCategoryId\", "
	+ " traReq.reason_id as \"reasonId\", "
	+ " reason.reason_desc as \"reasonDesc\", "
	+ " bcm2.hierarchy_id as \"toHierarchyId\", "
	+ " traReq.note as \"note\", "
	+ "transfer.to_bed_id as \"toBedId\","
	+ "transfer.from_bed_id as \"fromBedId\", "
	+ "transfer.to_room_id as \"toRoomId\", "
	+ "bm2.billing_bed_category_id as \"billingBedCategoryId\", "
	+ "bm2.floor_id as \"toFloorId\", "
	+ "transfer.to_ward_id as \"toWardId\","
	+ "bm2.bed_number as \"toBedNumber\","
	+ "ward2.ward_name as \"toWardname\", "
	+ "traReq.doctor_reason_id as \"doctorReasonId\", "
	+ "reason2.reason_desc as \"doctorReasonDesc\", "
	+ "traReq.adt_reason_id as \"adtReasonId\", "
	+ "reason3.reason_desc as \"adtReasonDesc\", "
	+ "traReq.doctor_note as \"doctorNote\", "
	+ "traReq.adt_note as \"adtNote\", "
	+ "traReq.doctor_reject_reason as \"doctorRejectReason\", "
	+ "traReq.adt_reject_reason as \"adtRejectReason\", "
	+ "traReq.treating_doctor_id  as \"treatingDoctorId\", "
	+" concat(doc.first_name,' ',doc.middle_name,' ',doc.last_name) as \"doctorName\", "
	+ "traReq.transfer_type_id as \"transferTypeId\" "
	+ " FROM adt.t_admission admission " 
	+ " INNER JOIN patient.m_unknown_patient_registration pr on admission.t_patient_id=pr.unknown_patient_id "
	+ " INNER JOIN adt.t_transfer transfer on transfer.admission_id=admission.admission_id "
	+ "INNER JOIN adt.m_transfer_status_master tsm on tsm.transfer_status_id=transfer.transfer_status_id "
	+ " LEFT JOIN adt.t_transfer_request traReq on traReq.transfer_request_id=transfer.transfer_request_id "
	+ " INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id " 
	+ " INNER JOIN adt.t_admission_details adl on adl.admission_id=admission.admission_id "
	+ " LEFT JOIN adt.m_bed_master bm on transfer.from_bed_id=bm.bed_id " 
	+ " INNER JOIN adt.m_ward_master ward on transfer.from_ward_id=ward.ward_id " 
	+ " INNER JOIN adt.m_bed_category_master bcm on adl.bed_category_id=bcm.bed_category_id "
	+ "INNER JOIN doctor.m_doctor_master doc on traReq.treating_doctor_id=doc.doctor_id "
	+ "LEFT JOIN adt.m_ward_master ward2 on transfer.to_ward_id=ward2.ward_id "
	+ "LEFT JOIN adt.m_bed_master bm2 on transfer.to_bed_id=bm2.bed_id "
	+" LEFT JOIN adt.m_bed_category_master bcm1 on transfer.from_bed_category_id=bcm1.bed_category_id  "
	+" LEFT JOIN adt.m_bed_category_master bcm2 on transfer.to_bed_category_id=bcm2.bed_category_id  "
	+ "LEFT JOIN adt.m_reason_master reason on traReq.reason_id =reason.reason_master_id "
	+ "LEFT JOIN adt.m_reason_master reason2 on traReq.doctor_reason_id =reason2.reason_master_id "
	+ "LEFT JOIN adt.m_reason_master reason3 on traReq.adt_reason_id =reason3.reason_master_id "
	+ " WHERE admission.status='A' "
	+ " AND adl.status='A' "
	+ " AND transfer.status='A' "
	+ " AND admission.organization_id=:organizationId "
	+ " AND admission.unit_id=:unitId "
	+ " AND traReq.transfer_type_id IN (1,2) "
	+ "AND admission.admission_id =:admissionId "
	//+ "AND traReq.transfer_type_id = :transferTypeId "
	),
	
	@NamedNativeQuery(name = "GET_INITIATE_TRANSFER_REQ_LIST", 
	query = 
	"SELECT admission.admission_id as \"admissionId\", "
	+ "transfer.transfer_id as \"transferId\", "
	+ "adl.admission_details_id as \"admissionDetailsId\", "
	+ "admission.patient_id as \"patientId\", "
	+ "admission.t_patient_id as \"tPatientId\", "
	+ "admission.doctor_id as \"doctorId\", "
	+ "admission.visit_type_id as \"visitTypeId\", " 
	+ "pr.uhid_number as \"UHIDNumber\", " 
	+ "concat(pr.first_name,' ',pr.middle_name,' ',pr.last_name) as \"pFirstName\" , " 
	+ "pr.gender_id as \"genderId\", " 
	+ "gm.gender_code as \"genderCode\", "
	+ "to_char(pr.dob,'MM/dd/yyyy') as \"dob\", " 
	+ "bcm1.bed_category_desc as \"bedCategoryDesc\", "
	+ "bm.bed_number as \"bedNumber\", "
	+ "bm.bed_category_id as \"bedCategoryId\", "
	+ "adl.ward_id as \"wardId\", "
	+ "ward.ward_name as \"wardName\", "
	+ " transfer.transfer_status_id as \"transferStatusId\", "
	+ "tsm.transfer_status_desc as \"transferStatusDesc\", "
	+ "bcm.hierarchy_id as \"hierarchyId\" "
	+ "FROM adt.t_admission admission " 
	+ "INNER JOIN patient.t_patient_registration pr on admission.patient_id=pr.patient_id "
	+ "INNER JOIN adt.t_transfer transfer on transfer.admission_id=admission.admission_id "
	+ "INNER JOIN adt.m_transfer_status_master tsm on tsm.transfer_status_id=transfer.transfer_status_id "
	+ "INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id " 
	+ "INNER JOIN adt.t_admission_details adl on adl.admission_id=admission.admission_id "
	+ "LEFT JOIN adt.m_bed_master bm on transfer.from_bed_id=bm.bed_id "
	+ "INNER JOIN adt.m_ward_master ward on transfer.from_ward_id=ward.ward_id " 
	+ "INNER JOIN adt.m_bed_category_master bcm on adl.bed_category_id=bcm.bed_category_id "
	+" LEFT JOIN adt.m_bed_category_master bcm1 on transfer.from_bed_category_id=bcm1.bed_category_id  "
	+ " WHERE admission.status='A' "
	+ " AND adl.status='A' "
	+ " AND transfer.status='A' "
	+ "AND admission.organization_id=:organizationId " 
	+ "AND admission.unit_id=:unitId "
	+ "AND transfer.from_ward_id IN (:wards) "
	+ "AND transfer.transfer_status_id=1 "
	+ "UNION "
	+ "SELECT admission.admission_id as \"admissionId\", "
	+ "transfer.transfer_id as \"transferId\", "
	+ "adl.admission_details_id as \"admissionDetailsId\", "
	+ "admission.patient_id as \"patientId\", "
	+ "admission.t_patient_id as \"tPatientId\", "
	+ "admission.doctor_id as \"doctorId\", "
	+ "admission.visit_type_id as \"visitTypeId\", " 
	+ "pr.t_uhid as \"UHIDNumber\", " 
	+ "pr.patient_name as \"pFirstName\" , " 
	+ "pr.gender_id as \"genderId\", " 
	+ "gm.gender_code as \"genderCode\", "
	+ "concat(to_char(pr.age,'999'),' ', pr.age_format) as \"dob\", "
	+ "bcm1.bed_category_desc as \"bedCategoryDesc\", "
	+ " bm.bed_number as \"bedNumber\", "
	+ " bm.bed_category_id as \"bedCategoryId\", "
	+ " adl.ward_id as \"wardId\", "
	+ "ward.ward_name as \"wardName\", "
	+ " transfer.transfer_status_id as \"transferStatusId\", "
	+ "tsm.transfer_status_desc as \"transferStatusDesc\", "
	+ " bcm.hierarchy_id as \"hierarchyId\" "
	+ " FROM adt.t_admission admission " 
	+ " INNER JOIN patient.m_unknown_patient_registration pr on admission.t_patient_id=pr.unknown_patient_id "
	+ " INNER JOIN adt.t_transfer transfer on transfer.admission_id=admission.admission_id "
	+ "INNER JOIN adt.m_transfer_status_master tsm on tsm.transfer_status_id=transfer.transfer_status_id "
	+ " INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id " 
	+ " INNER JOIN adt.t_admission_details adl on adl.admission_id=admission.admission_id "
	+ " LEFT JOIN adt.m_bed_master bm on transfer.from_bed_id=bm.bed_id " 
	+ " INNER JOIN adt.m_ward_master ward on transfer.from_ward_id=ward.ward_id " 
	+ " INNER JOIN adt.m_bed_category_master bcm on adl.bed_category_id=bcm.bed_category_id "
	+" LEFT JOIN adt.m_bed_category_master bcm1 on transfer.from_bed_category_id=bcm1.bed_category_id  "
	+ " WHERE admission.status='A' "
	+ " AND adl.status='A' "
	+ " AND transfer.status='A' "
	+ " AND admission.organization_id=:organizationId "
	+ " AND admission.unit_id=:unitId "
	+ " AND transfer.from_ward_id IN (:wards) "
	+ "AND transfer.transfer_status_id=1 "
	),
	
	@NamedNativeQuery(name = "GET_TRANSFER_REQ_LIST_BY_ADMISSION_ID", 
	query = 
	"SELECT admission.admission_id as \"admissionId\", "
	+ "transfer.transfer_id as \"transferId\", "
	+ "adl.admission_details_id as \"admissionDetailsId\", "
	+ "admission.patient_id as \"patientId\", "
	+ "admission.t_patient_id as \"tPatientId\", "
	+ "admission.doctor_id as \"doctorId\", "
	+ "admission.visit_type_id as \"visitTypeId\", " 
	+ "pr.uhid_number as \"UHIDNumber\", " 
	+ "concat(pr.first_name,' ',pr.middle_name,' ',pr.last_name) as \"pFirstName\" , " 
	+ "pr.gender_id as \"genderId\", " 
	+ "gm.gender_code as \"genderCode\", "
	+ "to_char(pr.dob,'MM/dd/yyyy') as \"dob\", " 
	+ "bcm1.bed_category_desc as \"bedCategoryDesc\", "
	+ "bm.bed_number as \"bedNumber\", "
	+ "bm.bed_category_id as \"bedCategoryId\", "
	+ "adl.ward_id as \"wardId\", "
	+ "ward.ward_name as \"wardName\", "
	+ " transfer.transfer_status_id as \"transferStatusId\", "
	+ "tsm.transfer_status_desc as \"transferStatusDesc\", "
	+ "bcm.hierarchy_id as \"hierarchyId\" "
	+ "FROM adt.t_admission admission " 
	+ "INNER JOIN patient.t_patient_registration pr on admission.patient_id=pr.patient_id "
	+ "INNER JOIN adt.t_transfer transfer on transfer.admission_id=admission.admission_id "
	+ "INNER JOIN adt.m_transfer_status_master tsm on tsm.transfer_status_id=transfer.transfer_status_id "
	+ "INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id " 
	+ "INNER JOIN adt.t_admission_details adl on adl.admission_id=admission.admission_id "
	+ "LEFT JOIN adt.m_bed_master bm on transfer.from_bed_id=bm.bed_id "
	+ "INNER JOIN adt.m_ward_master ward on transfer.from_ward_id=ward.ward_id " 
	+ "INNER JOIN adt.m_bed_category_master bcm on adl.bed_category_id=bcm.bed_category_id "
	+" LEFT JOIN adt.m_bed_category_master bcm1 on transfer.from_bed_category_id=bcm1.bed_category_id  "
	+ " WHERE admission.status='A' "
	+ " AND adl.status='A' "
	+ " AND transfer.status='A' "
	+ "AND admission.organization_id=:organizationId " 
	+ "AND admission.unit_id=:unitId "
	+ " AND admission.admission_id=:admissionId "
	+ "UNION "
	+ "SELECT admission.admission_id as \"admissionId\", "
	+ "transfer.transfer_id as \"transferId\", "
	+ "adl.admission_details_id as \"admissionDetailsId\", "
	+ "admission.patient_id as \"patientId\", "
	+ "admission.t_patient_id as \"tPatientId\", "
	+ "admission.doctor_id as \"doctorId\", "
	+ "admission.visit_type_id as \"visitTypeId\", " 
	+ "pr.t_uhid as \"UHIDNumber\", " 
	+ "pr.patient_name as \"pFirstName\" , " 
	+ "pr.gender_id as \"genderId\", " 
	+ "gm.gender_code as \"genderCode\", "
	+ "concat(to_char(pr.age,'999'),' ', pr.age_format) as \"dob\", "
	+ "bcm1.bed_category_desc as \"bedCategoryDesc\", "
	+ " bm.bed_number as \"bedNumber\", "
	+ " bm.bed_category_id as \"bedCategoryId\", "
	+ " adl.ward_id as \"wardId\", "
	+ "ward.ward_name as \"wardName\", "
	+ " transfer.transfer_status_id as \"transferStatusId\", "
	+ "tsm.transfer_status_desc as \"transferStatusDesc\", "
	+ " bcm.hierarchy_id as \"hierarchyId\" "
	+ " FROM adt.t_admission admission " 
	+ " INNER JOIN patient.m_unknown_patient_registration pr on admission.t_patient_id=pr.unknown_patient_id "
	+ " INNER JOIN adt.t_transfer transfer on transfer.admission_id=admission.admission_id "
	+ "INNER JOIN adt.m_transfer_status_master tsm on tsm.transfer_status_id=transfer.transfer_status_id "
	+ " INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id " 
	+ " INNER JOIN adt.t_admission_details adl on adl.admission_id=admission.admission_id "
	+ " LEFT JOIN adt.m_bed_master bm on transfer.from_bed_id=bm.bed_id " 
	+ " INNER JOIN adt.m_ward_master ward on transfer.from_ward_id=ward.ward_id " 
	+ " INNER JOIN adt.m_bed_category_master bcm on adl.bed_category_id=bcm.bed_category_id "
	+" LEFT JOIN adt.m_bed_category_master bcm1 on transfer.from_bed_category_id=bcm1.bed_category_id  "
	+ " WHERE admission.status='A' "
	+ " AND adl.status='A' "
	+ " AND transfer.status='A' "
	+ " AND admission.organization_id=:organizationId "
	+ " AND admission.unit_id=:unitId "
	+ " AND admission.admission_id=:admissionId "
	),
	
	
	@NamedNativeQuery(name="GET_PENDING_TRANSFER_REQ_LIST_FOR_DOCTOR",
	query="SELECT transfer.transfer_id as \"transferId\", "
			+" transfer.transfer_request_id as \"transferRequestId\", "
			+ "admission.patient_id as \"patientId\", "
			+ "admission.t_patient_id as \"tPatientId\", "
			+ "transfer.from_bed_category_id as \"fromBedCategoryId\", "
			+ "bcm1.bed_category_desc as \"fromBedCategoryDesc\", "
			+ "transfer.to_bed_category_id as \"toBedCategoryId\", "
			+ "bcm2.bed_category_desc as \"toBedCategoryDesc\", "
			+" pr.uhid_number as \"UHIDNumber\", "
			+" concat(pr.first_name,' ',pr.middle_name,' ',pr.last_name) as \"patientName\",  " 
			+" pr.gender_id as \"genderId\",   "
			+" gm.gender_code as \"genderCode\",  "
			+" to_char(pr.dob,'MM/dd/yyyy') as \"dob\",   "
			+" transfer.from_ward_id as \"fromWardId\", "
			+" ward.ward_name as \"wardName\", "
			+" bm.floor_id as \"floorId\", "
			+" floor.floor_name as \"floorName\", "
			+" transfer.from_bed_id as \"fromBedId\", " 
			+" bm.bed_number as \"bedNumber\", "
			+" traReq.treating_doctor_id as \"doctorId\", "
			+" concat(doc.first_name,' ',doc.middle_name,' ',doc.last_name) as \"doctorName\", "
			+" traReq.reason_id as \"reasonId\",  "
			+" reason.reason_desc as \"reasonDesc\", "
			+" traReq.note as \"note\", "
			+" traReq.doctor_note as \"doctorNote\","
			+ "traReq.transfer_type_id as \"transferTypeId\" "
			+" FROM adt.t_transfer transfer "
			+" INNER JOIN adt.t_admission admission on admission.admission_id=transfer.admission_id   "
			+" INNER JOIN patient.t_patient_registration pr on admission.patient_id=pr.patient_id  "
			+" INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id   "
			+" LEFT JOIN adt.m_bed_master bm on transfer.from_bed_id=bm.bed_id   "
			+" LEFT JOIN adt.m_floor_master floor on bm.floor_id=floor.floor_id  "
			+" INNER JOIN adt.m_bed_category_master bcm1 on transfer.from_bed_category_id=bcm1.bed_category_id  "
			+" LEFT JOIN adt.m_bed_category_master bcm2 on transfer.to_bed_category_id=bcm2.bed_category_id  "
			+" INNER JOIN adt.m_ward_master ward on transfer.from_ward_id=ward.ward_id "
			+" INNER JOIN adt.t_transfer_request traReq on transfer.transfer_request_id=traReq.transfer_request_id  "
			+" INNER JOIN doctor.m_doctor_master doc on traReq.treating_doctor_id=doc.doctor_id   "
			+" INNER JOIN adt.m_reason_master reason on traReq.reason_id=reason.reason_master_id "
			+" WHERE transfer.transfer_status_id=3 "
			+ "AND traReq.transfer_status_id=3 "
			+ "AND transfer.status='A' "
			+ " AND admission.status='A' "
			+ "AND transfer.organization_id=:organizationId "
			+ "AND transfer.unit_id=:unitId "
			+" union "
			+" SELECT  "
			+" transfer.transfer_id as \"transferId\", "
			+" transfer.transfer_request_id as \"transferRequestId\",  "
			+ "admission.patient_id as \"patientId\", "
			+ "admission.t_patient_id as \"tPatientId\", "
			+ "transfer.from_bed_category_id as \"fromBedCategoryId\", "
			+ "bcm1.bed_category_desc as \"fromBedCategoryDesc\", "
			+ "transfer.to_bed_category_id as \"toBedCategoryId\", "
			+ "bcm2.bed_category_desc as \"toBedCategoryDesc\", "
			+" pr.t_uhid as \"UHIDNumber\", "
			+" pr.patient_name as \"patientName\",  " 
			+" pr.gender_id as \"genderId\",   "
			+" gm.gender_code as \"genderCode\",  "
			+" concat(to_char(pr.age,'999'),' ', pr.age_format) as \"dob\",  " 
			+" transfer.from_ward_id as \"fromWardId\", "
			+" ward.ward_name as \"wardName\", "
			+" bm.floor_id as \"floorId\", "
			+" floor.floor_name as \"floorName\", "
			+" transfer.from_bed_id as \"fromBedId\",  "
			+" bm.bed_number as \"bedNumber\", "
			+" traReq.treating_doctor_id as \"doctorId\", "
			+" concat(doc.first_name,' ',doc.middle_name,' ',doc.last_name) as \"doctorName\", "
			+" traReq.reason_id as \"reasonId\",  "
			+" reason.reason_desc as \"reasonDesc\", "
			+" traReq.note as \"note\", "
			+" traReq.doctor_note as \"doctorNote\", "
			+ "traReq.transfer_type_id as \"transferTypeId\" "
			+" FROM adt.t_transfer transfer "
			+" INNER JOIN adt.t_admission admission on admission.admission_id=transfer.admission_id  " 
			+" INNER JOIN patient.m_unknown_patient_registration pr on admission.t_patient_id=pr.unknown_patient_id  "
			+" INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id   "
			+" LEFT JOIN adt.m_bed_master bm on transfer.from_bed_id=bm.bed_id   "
			+" LEFT JOIN adt.m_floor_master floor on bm.floor_id=floor.floor_id  "
			+" INNER JOIN adt.m_bed_category_master bcm1 on transfer.from_bed_category_id=bcm1.bed_category_id  "
			+" LEFT JOIN adt.m_bed_category_master bcm2 on transfer.to_bed_category_id=bcm2.bed_category_id  "
			+" INNER JOIN adt.m_ward_master ward on transfer.from_ward_id=ward.ward_id "
			+" INNER JOIN adt.t_transfer_request traReq on transfer.transfer_request_id=traReq.transfer_request_id " 
			+" INNER JOIN doctor.m_doctor_master doc on traReq.treating_doctor_id=doc.doctor_id "  
			+" INNER JOIN adt.m_reason_master reason on traReq.reason_id=reason.reason_master_id "
			+" WHERE transfer.transfer_status_id=3 "
			+ "AND traReq.transfer_status_id=3 "
			+ "AND transfer.status='A' "
			+ " AND admission.status='A' "
			+ "AND transfer.organization_id=:organizationId "
			+ "AND transfer.unit_id=:unitId "),
	
	@NamedNativeQuery(name="GET_PENDING_TRANSFER_REQ_LIST_FOR_ADT",
	query="SELECT transfer.transfer_id as \"transferId\", "
			+" transfer.transfer_request_id as \"transferRequestId\", "
			+ "admission.patient_id as \"patientId\", "
			+ "admission.t_patient_id as \"tPatientId\", "
			+ "transfer.from_bed_category_id as \"fromBedCategoryId\", "
			+ "bcm1.bed_category_desc as \"fromBedCategoryDesc\", "
			+ "transfer.to_bed_category_id as \"toBedCategoryId\", "
			+ "bcm2.bed_category_desc as \"toBedCategoryDesc\", "
			+" pr.uhid_number as \"UHIDNumber\", "
			+" concat(pr.first_name,' ',pr.middle_name,' ',pr.last_name) as \"patientName\",  " 
			+" pr.gender_id as \"genderId\",   "
			+" gm.gender_code as \"genderCode\",  "
			+" to_char(pr.dob,'MM/dd/yyyy') as \"dob\",   "
			+" transfer.from_ward_id as \"fromWardId\", "
			+" ward.ward_name as \"wardName\", "
			+" bm.floor_id as \"floorId\", "
			+" floor.floor_name as \"floorName\", "
			+" transfer.from_bed_id as \"fromBedId\", "
			+" transfer.to_bed_id as \"toBedId\", " 
			+" bm.bed_number as \"bedNumber\", "
			+" traReq.treating_doctor_id as \"doctorId\", "
			+" concat(doc.first_name,' ',doc.middle_name,' ',doc.last_name) as \"doctorName\", "
			+" traReq.reason_id as \"reasonId\",  "
			+" reason.reason_desc as \"reasonDesc\", "
			+" traReq.note as \"note\", "
			+" traReq.doctor_note as \"doctorNote\", "
			+ "traReq.transfer_type_id as \"transferTypeId\" "
			+" FROM adt.t_transfer transfer "
			+" INNER JOIN adt.t_admission admission on admission.admission_id=transfer.admission_id   "
			+" INNER JOIN patient.t_patient_registration pr on admission.patient_id=pr.patient_id  "
			+" INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id   "
			+" LEFT JOIN adt.m_bed_master bm on transfer.from_bed_id=bm.bed_id   "
			+" LEFT JOIN adt.m_floor_master floor on bm.floor_id=floor.floor_id  "
			+" INNER JOIN adt.m_bed_category_master bcm1 on transfer.from_bed_category_id=bcm1.bed_category_id  "
			+" INNER JOIN adt.m_bed_category_master bcm2 on transfer.to_bed_category_id=bcm2.bed_category_id  "
			+" INNER JOIN adt.m_ward_master ward on transfer.from_ward_id=ward.ward_id "
			+" INNER JOIN adt.t_transfer_request traReq on transfer.transfer_request_id=traReq.transfer_request_id  "
			+" INNER JOIN doctor.m_doctor_master doc on traReq.treating_doctor_id=doc.doctor_id   "
			+" INNER JOIN adt.m_reason_master reason on traReq.reason_id=reason.reason_master_id "
			+" WHERE transfer.transfer_status_id=4 "
			+ "AND traReq.transfer_status_id=4 "
			+ "AND transfer.status='A' "
			+ " AND admission.status='A' "
			+ "AND transfer.organization_id=:organizationId "
			+ "AND transfer.unit_id=:unitId "
			+" union "
			+" SELECT  "
			+" transfer.transfer_id as \"transferId\", "
			+" transfer.transfer_request_id as \"transferRequestId\",  "
			+ "admission.patient_id as \"patientId\", "
			+ "admission.t_patient_id as \"tPatientId\", "
			+ "transfer.from_bed_category_id as \"fromBedCategoryId\", "
			+ "bcm1.bed_category_desc as \"fromBedCategoryDesc\", "
			+ "transfer.to_bed_category_id as \"toBedCategoryId\", "
			+ "bcm2.bed_category_desc as \"toBedCategoryDesc\", "
			+" pr.t_uhid as \"UHIDNumber\", "
			+" pr.patient_name as \"patientName\",  " 
			+" pr.gender_id as \"genderId\",   "
			+" gm.gender_code as \"genderCode\",  "
			+" concat(to_char(pr.age,'999'),' ', pr.age_format) as \"dob\",  " 
			+" transfer.from_ward_id as \"fromWardId\", "
			+" ward.ward_name as \"wardName\", "
			+" bm.floor_id as \"floorId\", "
			+" floor.floor_name as \"floorName\", "
			+" transfer.from_bed_id as \"fromBedId\",  "
			+" transfer.to_bed_id as \"toBedId\", " 
			+" bm.bed_number as \"bedNumber\", "
			+" traReq.treating_doctor_id as \"doctorId\", "
			+" concat(doc.first_name,' ',doc.middle_name,' ',doc.last_name) as \"doctorName\", "
			+" traReq.reason_id as \"reasonId\",  "
			+" reason.reason_desc as \"reasonDesc\", "
			+" traReq.note as \"note\", "
			+" traReq.doctor_note as \"doctorNote\", "
			+ "traReq.transfer_type_id as \"transferTypeId\" "
			+" FROM adt.t_transfer transfer "
			+" INNER JOIN adt.t_admission admission on admission.admission_id=transfer.admission_id  " 
			+" INNER JOIN patient.m_unknown_patient_registration pr on admission.t_patient_id=pr.unknown_patient_id  "
			+" INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id   "
			+" LEFT JOIN adt.m_bed_master bm on transfer.from_bed_id=bm.bed_id   "
			+" LEFT JOIN adt.m_floor_master floor on bm.floor_id=floor.floor_id  "
			+" INNER JOIN adt.m_bed_category_master bcm1 on transfer.from_bed_category_id=bcm1.bed_category_id  "
			+" INNER JOIN adt.m_bed_category_master bcm2 on transfer.to_bed_category_id=bcm2.bed_category_id  "
			+" INNER JOIN adt.m_ward_master ward on transfer.from_ward_id=ward.ward_id "
			+" INNER JOIN adt.t_transfer_request traReq on transfer.transfer_request_id=traReq.transfer_request_id " 
			+" INNER JOIN doctor.m_doctor_master doc on traReq.treating_doctor_id=doc.doctor_id "  
			+" INNER JOIN adt.m_reason_master reason on traReq.reason_id=reason.reason_master_id "
			+" WHERE transfer.transfer_status_id=4 "
			+ "AND traReq.transfer_status_id=4 "
			+ "AND transfer.status='A' "
			+ " AND admission.status='A' "
			+ "AND transfer.organization_id=:organizationId "
			+ "AND transfer.unit_id=:unitId "),
	
	
	
	@NamedNativeQuery(name="GET_FINAL_TRANSFER_REQUEST_LIST",query=
			"SELECT transfer.transfer_id as \"transferId\", "
			+" transfer.transfer_request_id as \"transferRequestId\", "
			+ "admission.admission_id as \"admissionId\", "		
			+ "admission.patient_id as \"patientId\", "
			+ "admission.t_patient_id as \"tPatientId\", "
			+" pr.uhid_number as \"UHIDNumber\", "
			+" concat(pr.first_name,' ',pr.middle_name,' ',pr.last_name) as \"patientName\",  " 
			+" pr.gender_id as \"genderId\",   "
			+" gm.gender_code as \"genderCode\",  "
			+" to_char(pr.dob,'MM/dd/yyyy') as \"dob\",   "
			+" transfer.from_ward_id as \"fromWardId\", "
			+" ward.ward_name as \"fromWardName\", "
			+" transfer.from_bed_id as \"fromBedId\", " 
			+" bm.bed_number as \"fromBedNumber\", "
			+" transfer.from_bed_category_id as \"fromBedCategoryId\", "
			+" bcm1.bed_category_desc as \"fromBedCategoryDesc\", "
			+" bm.floor_id as \"fromFloorId\", "
			+" bm.room_id as \"fromRoomId\", "
			+" transfer.to_ward_id as \"toWardId\", "
			+" ward2.ward_name as \"toWardName\", "
			+" transfer.to_bed_id as \"toBedId\", " 
			+" bm2.bed_number as \"toBedNumber\", "
			+" transfer.to_bed_category_id as \"toBedCategoryId\", "
			+" bcm2.bed_category_desc as \"toBedCategoryDesc\", "
			+" bm2.floor_id as \"toFloorId\", "
			+" bm2.room_id as \"toRoomId\", "
			+" traReq.treating_doctor_id as \"doctorId\", "
			+" concat(doc.first_name,' ',doc.middle_name,' ',doc.last_name) as \"doctorName\", "
			+" traReq.reason_id as \"reasonId\",  "
			+" reason.reason_desc as \"reasonDesc\", "
			+" traReq.note as \"note\", "
			+" traReq.final_note as \"finalNote\","
			+ "bm.billing_bed_category_id as \"fromBillingBedCategoryId\", "
			+ "bm2.billing_bed_category_id as \"toBillingBedCategoryId\" "
			+" FROM adt.t_transfer transfer "
			+" INNER JOIN adt.t_admission admission on admission.admission_id=transfer.admission_id   "
			+" INNER JOIN patient.t_patient_registration pr on admission.patient_id=pr.patient_id  "
			+" INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id   "
			+" LEFT JOIN adt.m_bed_master bm on transfer.from_bed_id=bm.bed_id   "
			+" LEFT JOIN adt.m_bed_master bm2 on transfer.to_bed_id=bm2.bed_id   "
			+" LEFT JOIN adt.m_floor_master floor on bm.floor_id=floor.floor_id  "
			+" INNER JOIN adt.m_bed_category_master bcm1 on transfer.from_bed_category_id=bcm1.bed_category_id  "
			+" INNER JOIN adt.m_bed_category_master bcm2 on transfer.to_bed_category_id=bcm2.bed_category_id  "
			+" INNER JOIN adt.m_ward_master ward on transfer.from_ward_id=ward.ward_id "
			+" INNER JOIN adt.m_ward_master ward2 on transfer.to_ward_id=ward2.ward_id "
			+" INNER JOIN adt.t_transfer_request traReq on transfer.transfer_request_id=traReq.transfer_request_id  "
			+" INNER JOIN doctor.m_doctor_master doc on traReq.treating_doctor_id=doc.doctor_id   "
			+" INNER JOIN adt.m_reason_master reason on traReq.reason_id=reason.reason_master_id "
			+" WHERE transfer.transfer_status_id=7 "
			+" AND traReq.transfer_status_id=7 "
			+" AND transfer.status='A' "
			+" AND admission.status='A' "
			+" AND transfer.organization_id=:organizationId "
			+" AND transfer.unit_id=:unitId "
			+" union "
			+" SELECT transfer.transfer_id as \"transferId\", "
			+" transfer.transfer_request_id as \"transferRequestId\", "
			+ "admission.admission_id as \"admissionId\", "		
			+ "admission.patient_id as \"patientId\", "
			+ "admission.t_patient_id as \"tPatientId\", "
			+" pr.t_uhid as \"UHIDNumber\", "
			+" pr.patient_name as \"patientName\",  " 
			+" pr.gender_id as \"genderId\",   "
			+" gm.gender_code as \"genderCode\",  "
			+" concat(to_char(pr.age,'999'),' ', pr.age_format) as \"dob\",  " 
			+" transfer.from_ward_id as \"fromWardId\", "
			+" ward.ward_name as \"fromWardName\", "
			+" transfer.from_bed_id as \"fromBedId\", " 
			+" bm.bed_number as \"fromBedNumber\", "
			+" transfer.from_bed_category_id as \"fromBedCategoryId\", "
			+" bcm1.bed_category_desc as \"fromBedCategoryDesc\", "
			+" bm.floor_id as \"fromFloorId\", "
			+" bm.room_id as \"fromRoomId\", "
			+" transfer.to_ward_id as \"toWardId\", "
			+" ward2.ward_name as \"toWardName\", "
			+" transfer.to_bed_id as \"toBedId\", " 
			+" bm2.bed_number as \"toBedNumber\", "
			+" transfer.to_bed_category_id as \"toBedCategoryId\", "
			+" bcm2.bed_category_desc as \"toBedCategoryDesc\", "
			+" bm2.floor_id as \"toFloorId\", "
			+" bm2.room_id as \"toRoomId\", "
			+" traReq.treating_doctor_id as \"doctorId\", "
			+" concat(doc.first_name,' ',doc.middle_name,' ',doc.last_name) as \"doctorName\", "
			+" traReq.reason_id as \"reasonId\",  "
			+" reason.reason_desc as \"reasonDesc\", "
			+" traReq.note as \"note\", "
			+" traReq.final_note as \"finalNote\", "
			+ "bm.billing_bed_category_id as \"fromBillingBedCategoryId\", "
			+ "bm2.billing_bed_category_id as \"toBillingBedCategoryId\" "
			+" FROM adt.t_transfer transfer "
			+" INNER JOIN adt.t_admission admission on admission.admission_id=transfer.admission_id  " 
			+" INNER JOIN patient.m_unknown_patient_registration pr on admission.t_patient_id=pr.unknown_patient_id  "
			+" INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id   "
			+" LEFT JOIN adt.m_bed_master bm on transfer.from_bed_id=bm.bed_id   "
			+" LEFT JOIN adt.m_bed_master bm2 on transfer.to_bed_id=bm2.bed_id   "
			+" LEFT JOIN adt.m_floor_master floor on bm.floor_id=floor.floor_id  "
			+" INNER JOIN adt.m_bed_category_master bcm1 on transfer.from_bed_category_id=bcm1.bed_category_id  "
			+" INNER JOIN adt.m_bed_category_master bcm2 on transfer.to_bed_category_id=bcm2.bed_category_id  "
			+" INNER JOIN adt.m_ward_master ward on transfer.from_ward_id=ward.ward_id "
			+" INNER JOIN adt.m_ward_master ward2 on transfer.to_ward_id=ward2.ward_id "
			+" INNER JOIN adt.t_transfer_request traReq on transfer.transfer_request_id=traReq.transfer_request_id  "
			+" INNER JOIN doctor.m_doctor_master doc on traReq.treating_doctor_id=doc.doctor_id   "
			+" INNER JOIN adt.m_reason_master reason on traReq.reason_id=reason.reason_master_id "
			+" WHERE transfer.transfer_status_id=7 "
			+" AND traReq.transfer_status_id=7 "
			+" AND transfer.status='A' "
			+" AND admission.status='A' "
			+" AND transfer.organization_id=:organizationId "
			+" AND transfer.unit_id=:unitId "),
	
	@NamedNativeQuery(name="GET_FINAL_TRANSFER_REQUEST_LIST_BY_ADMISSION_ID",query=
	"SELECT transfer.transfer_id as \"transferId\", "
	+" transfer.transfer_request_id as \"transferRequestId\", "
	+ "admission.admission_id as \"admissionId\", "		
	+ "admission.patient_id as \"patientId\", "
	+ "admission.t_patient_id as \"tPatientId\", "
	+" pr.uhid_number as \"UHIDNumber\", "
	+" concat(pr.first_name,' ',pr.middle_name,' ',pr.last_name) as \"patientName\",  " 
	+" pr.gender_id as \"genderId\",   "
	+" gm.gender_code as \"genderCode\",  "
	+" to_char(pr.dob,'MM/dd/yyyy') as \"dob\",   "
	+" transfer.from_ward_id as \"fromWardId\", "
	+" ward.ward_name as \"fromWardName\", "
	+" transfer.from_bed_id as \"fromBedId\", " 
	+" bm.bed_number as \"fromBedNumber\", "
	+" transfer.from_bed_category_id as \"fromBedCategoryId\", "
	+" bcm1.bed_category_desc as \"fromBedCategoryDesc\", "
	+" bm.floor_id as \"fromFloorId\", "
	+" bm.room_id as \"fromRoomId\", "
	+" transfer.to_ward_id as \"toWardId\", "
	+" ward2.ward_name as \"toWardName\", "
	+" transfer.to_bed_id as \"toBedId\", " 
	+" bm2.bed_number as \"toBedNumber\", "
	+" transfer.to_bed_category_id as \"toBedCategoryId\", "
	+" bcm2.bed_category_desc as \"toBedCategoryDesc\", "
	+" bm2.floor_id as \"toFloorId\", "
	+" bm2.room_id as \"toRoomId\", "
	+" traReq.treating_doctor_id as \"doctorId\", "
	+" concat(doc.first_name,' ',doc.middle_name,' ',doc.last_name) as \"doctorName\", "
	+" traReq.reason_id as \"reasonId\",  "
	+" reason.reason_desc as \"reasonDesc\", "
	+" traReq.note as \"note\", "
	+" traReq.final_note as \"finalNote\","
	+ "bm.billing_bed_category_id as \"fromBillingBedCategoryId\", "
	+ "bm2.billing_bed_category_id as \"toBillingBedCategoryId\" "
	+" FROM adt.t_transfer transfer "
	+" INNER JOIN adt.t_admission admission on admission.admission_id=transfer.admission_id   "
	+" INNER JOIN patient.t_patient_registration pr on admission.patient_id=pr.patient_id  "
	+" INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id   "
	+" LEFT JOIN adt.m_bed_master bm on transfer.from_bed_id=bm.bed_id   "
	+" LEFT JOIN adt.m_bed_master bm2 on transfer.to_bed_id=bm2.bed_id   "
	+" LEFT JOIN adt.m_floor_master floor on bm.floor_id=floor.floor_id  "
	+" INNER JOIN adt.m_bed_category_master bcm1 on transfer.from_bed_category_id=bcm1.bed_category_id  "
	+" INNER JOIN adt.m_bed_category_master bcm2 on transfer.to_bed_category_id=bcm2.bed_category_id  "
	+" INNER JOIN adt.m_ward_master ward on transfer.from_ward_id=ward.ward_id "
	+" INNER JOIN adt.m_ward_master ward2 on transfer.to_ward_id=ward2.ward_id "
	+" INNER JOIN adt.t_transfer_request traReq on transfer.transfer_request_id=traReq.transfer_request_id  "
	+" INNER JOIN doctor.m_doctor_master doc on traReq.treating_doctor_id=doc.doctor_id   "
	+" INNER JOIN adt.m_reason_master reason on traReq.reason_id=reason.reason_master_id "
	+" WHERE transfer.transfer_status_id=7 "
	+" AND traReq.transfer_status_id=7 "
	+" AND transfer.status='A' "
	+" AND admission.status='A' "
	+" AND transfer.organization_id=:organizationId "
	+" AND transfer.unit_id=:unitId "
	+ " AND transfer.admission_id=:admissionId "
	+" union "
	+" SELECT transfer.transfer_id as \"transferId\", "
	+" transfer.transfer_request_id as \"transferRequestId\", "
	+ "admission.admission_id as \"admissionId\", "		
	+ "admission.patient_id as \"patientId\", "
	+ "admission.t_patient_id as \"tPatientId\", "
	+" pr.t_uhid as \"UHIDNumber\", "
	+" pr.patient_name as \"patientName\",  " 
	+" pr.gender_id as \"genderId\",   "
	+" gm.gender_code as \"genderCode\",  "
	+" concat(to_char(pr.age,'999'),' ', pr.age_format) as \"dob\",  " 
	+" transfer.from_ward_id as \"fromWardId\", "
	+" ward.ward_name as \"fromWardName\", "
	+" transfer.from_bed_id as \"fromBedId\", " 
	+" bm.bed_number as \"fromBedNumber\", "
	+" transfer.from_bed_category_id as \"fromBedCategoryId\", "
	+" bcm1.bed_category_desc as \"fromBedCategoryDesc\", "
	+" bm.floor_id as \"fromFloorId\", "
	+" bm.room_id as \"fromRoomId\", "
	+" transfer.to_ward_id as \"toWardId\", "
	+" ward2.ward_name as \"toWardName\", "
	+" transfer.to_bed_id as \"toBedId\", " 
	+" bm2.bed_number as \"toBedNumber\", "
	+" transfer.to_bed_category_id as \"toBedCategoryId\", "
	+" bcm2.bed_category_desc as \"toBedCategoryDesc\", "
	+" bm2.floor_id as \"toFloorId\", "
	+" bm2.room_id as \"toRoomId\", "
	+" traReq.treating_doctor_id as \"doctorId\", "
	+" concat(doc.first_name,' ',doc.middle_name,' ',doc.last_name) as \"doctorName\", "
	+" traReq.reason_id as \"reasonId\",  "
	+" reason.reason_desc as \"reasonDesc\", "
	+" traReq.note as \"note\", "
	+" traReq.final_note as \"finalNote\", "
	+ "bm.billing_bed_category_id as \"fromBillingBedCategoryId\", "
	+ "bm2.billing_bed_category_id as \"toBillingBedCategoryId\" "
	+" FROM adt.t_transfer transfer "
	+" INNER JOIN adt.t_admission admission on admission.admission_id=transfer.admission_id  " 
	+" INNER JOIN patient.m_unknown_patient_registration pr on admission.t_patient_id=pr.unknown_patient_id  "
	+" INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id   "
	+" LEFT JOIN adt.m_bed_master bm on transfer.from_bed_id=bm.bed_id   "
	+" LEFT JOIN adt.m_bed_master bm2 on transfer.to_bed_id=bm2.bed_id   "
	+" LEFT JOIN adt.m_floor_master floor on bm.floor_id=floor.floor_id  "
	+" INNER JOIN adt.m_bed_category_master bcm1 on transfer.from_bed_category_id=bcm1.bed_category_id  "
	+" INNER JOIN adt.m_bed_category_master bcm2 on transfer.to_bed_category_id=bcm2.bed_category_id  "
	+" INNER JOIN adt.m_ward_master ward on transfer.from_ward_id=ward.ward_id "
	+" INNER JOIN adt.m_ward_master ward2 on transfer.to_ward_id=ward2.ward_id "
	+" INNER JOIN adt.t_transfer_request traReq on transfer.transfer_request_id=traReq.transfer_request_id  "
	+" INNER JOIN doctor.m_doctor_master doc on traReq.treating_doctor_id=doc.doctor_id   "
	+" INNER JOIN adt.m_reason_master reason on traReq.reason_id=reason.reason_master_id "
	+" WHERE transfer.transfer_status_id=7 "
	+" AND traReq.transfer_status_id=7 "
	+" AND transfer.status='A' "
	+" AND admission.status='A' "
	+" AND transfer.organization_id=:organizationId "
	+" AND transfer.unit_id=:unitId "
	+ " AND transfer.admission_id=:admissionId "),
	
	
	@NamedNativeQuery(name="GET_ICU_TRANSFER_REQ_LIST",query=
	"SELECT transfer.transfer_id as \"transferId\", "
	+" transfer.transfer_request_id as \"transferRequestId\", "
	+ "admission.admission_id as \"admissionId\", "
	+ "adl.admission_details_id as \"admissionDetailsId\", "		
	+ "admission.patient_id as \"patientId\", "
	+ "admission.t_patient_id as \"tPatientId\", "
	+" pr.uhid_number as \"UHIDNumber\", "
	+" concat(pr.first_name,' ',pr.middle_name,' ',pr.last_name) as \"patientName\",  " 
	+" pr.gender_id as \"genderId\",   "
	+" gm.gender_code as \"genderCode\",  "
	+" to_char(pr.dob,'MM/dd/yyyy') as \"dob\",   "
	+" transfer.from_ward_id as \"fromWardId\", "
	+" ward.ward_name as \"fromWardName\", "
	+" transfer.from_bed_id as \"fromBedId\", " 
	+" bm.bed_number as \"fromBedNumber\", "
	+" transfer.from_bed_category_id as \"fromBedCategoryId\", "
	+" bcm1.bed_category_desc as \"fromBedCategoryDesc\", "
	+" bm.floor_id as \"fromFloorId\", "
	+" bm.room_id as \"fromRoomId\", "
	+" transfer.to_ward_id as \"toWardId\", "
	+" ward2.ward_name as \"toWardName\", "
	+" transfer.to_bed_id as \"toBedId\", " 
	+" bm2.bed_number as \"toBedNumber\", "
	+" transfer.to_bed_category_id as \"toBedCategoryId\", "
	+" bcm2.bed_category_desc as \"toBedCategoryDesc\", "
	+" bm2.floor_id as \"toFloorId\", "
	+" bm2.room_id as \"toRoomId\", "
	+" traReq.treating_doctor_id as \"doctorId\", "
	+" concat(doc.first_name,' ',doc.middle_name,' ',doc.last_name) as \"doctorName\", "
	+" traReq.reason_id as \"reasonId\",  "
	+" reason.reason_desc as \"reasonDesc\", "
	+" traReq.note as \"note\", "
	+ "traReq.icu_type_id as \"icuTypeId\", "
	+ "icu.icu_type as \"icuType\", "
	+ "traReq.transfer_status_id as \"transferStatusId\", "
	+" traReq.final_note as \"finalNote\", "
	+ "bm.billing_bed_category_id as \"fromBillingBedCategoryId\" "
	//+ "bm2.billing_bed_category_id as \"toBillingBedCategoryId\" "
	+" FROM adt.t_transfer transfer "
	+" INNER JOIN adt.t_admission admission on admission.admission_id=transfer.admission_id "
	+ "INNER JOIN adt.t_admission_details adl on admission.admission_id=adl.admission_id "
	+" INNER JOIN patient.t_patient_registration pr on admission.patient_id=pr.patient_id  "
	+" INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id   "
	+" INNER JOIN adt.m_bed_master bm on transfer.from_bed_id=bm.bed_id   "
	+" LEFT JOIN adt.m_bed_master bm2 on transfer.to_bed_id=bm2.bed_id   "
	+" INNER JOIN adt.m_floor_master floor on bm.floor_id=floor.floor_id  "
	+" INNER JOIN adt.m_bed_category_master bcm1 on transfer.from_bed_category_id=bcm1.bed_category_id  "
	+" LEFT JOIN adt.m_bed_category_master bcm2 on transfer.to_bed_category_id=bcm2.bed_category_id  "
	+" INNER JOIN adt.m_ward_master ward on transfer.from_ward_id=ward.ward_id "
	+" LEFT JOIN adt.m_ward_master ward2 on transfer.to_ward_id=ward2.ward_id "
	+" INNER JOIN adt.t_transfer_request traReq on transfer.transfer_request_id=traReq.transfer_request_id  "
	+" INNER JOIN doctor.m_doctor_master doc on traReq.treating_doctor_id=doc.doctor_id   "
	+" INNER JOIN adt.m_reason_master reason on traReq.reason_id=reason.reason_master_id "
	+ "INNER JOIN adt.m_icu_type_master icu on traReq.icu_type_id=icu.icu_type_id "
	+" WHERE transfer.transfer_status_id IN (10,3) "
	+" AND traReq.transfer_status_id IN (10,3) "
	+" AND transfer.status='A' "
	+" AND admission.status='A' "
	+ " AND adl.status='A' "
	+" AND transfer.organization_id=:organizationId "
	+" AND transfer.unit_id=:unitId "
	+ "AND traReq.transfer_type_id=3 "
	+" union "
	+" SELECT transfer.transfer_id as \"transferId\", "
	+" transfer.transfer_request_id as \"transferRequestId\", "
	+ "admission.admission_id as \"admissionId\", "	
	+ "adl.admission_details_id as \"admissionDetailsId\", "	
	+ "admission.patient_id as \"patientId\", "
	+ "admission.t_patient_id as \"tPatientId\", "
	+" pr.t_uhid as \"UHIDNumber\", "
	+" pr.patient_name as \"patientName\",  " 
	+" pr.gender_id as \"genderId\",   "
	+" gm.gender_code as \"genderCode\",  "
	+" concat(to_char(pr.age,'999'),' ', pr.age_format) as \"dob\",  " 
	+" transfer.from_ward_id as \"fromWardId\", "
	+" ward.ward_name as \"fromWardName\", "
	+" transfer.from_bed_id as \"fromBedId\", " 
	+" bm.bed_number as \"fromBedNumber\", "
	+" transfer.from_bed_category_id as \"fromBedCategoryId\", "
	+" bcm1.bed_category_desc as \"fromBedCategoryDesc\", "
	+" bm.floor_id as \"fromFloorId\", "
	+" bm.room_id as \"fromRoomId\", "
	+" transfer.to_ward_id as \"toWardId\", "
	+" ward2.ward_name as \"toWardName\", "
	+" transfer.to_bed_id as \"toBedId\", " 
	+" bm2.bed_number as \"toBedNumber\", "
	+" transfer.to_bed_category_id as \"toBedCategoryId\", "
	+" bcm2.bed_category_desc as \"toBedCategoryDesc\", "
	+" bm2.floor_id as \"toFloorId\", "
	+" bm2.room_id as \"toRoomId\", "
	+" traReq.treating_doctor_id as \"doctorId\", "
	+" concat(doc.first_name,' ',doc.middle_name,' ',doc.last_name) as \"doctorName\", "
	+" traReq.reason_id as \"reasonId\",  "
	+" reason.reason_desc as \"reasonDesc\", "
	+" traReq.note as \"note\", "
	+ "traReq.icu_type_id as \"icuTypeId\", "
	+ "icu.icu_type as \"icuType\", "
	+ "traReq.transfer_status_id as \"transferStatusId\", "
	+" traReq.final_note as \"finalNote\", "
	+ "bm.billing_bed_category_id as \"fromBillingBedCategoryId\" "
	//+ "bm2.billing_bed_category_id as \"toBillingBedCategoryId\" "
	+" FROM adt.t_transfer transfer "
	+" INNER JOIN adt.t_admission admission on admission.admission_id=transfer.admission_id  " 
	+ "INNER JOIN adt.t_admission_details adl on admission.admission_id=adl.admission_id "
	+" INNER JOIN patient.m_unknown_patient_registration pr on admission.t_patient_id=pr.unknown_patient_id  "
	+" INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id   "
	+" INNER JOIN adt.m_bed_master bm on transfer.from_bed_id=bm.bed_id   "
	+" LEFT JOIN adt.m_bed_master bm2 on transfer.to_bed_id=bm2.bed_id   "
	+" INNER JOIN adt.m_floor_master floor on bm.floor_id=floor.floor_id  "
	+" INNER JOIN adt.m_bed_category_master bcm1 on transfer.from_bed_category_id=bcm1.bed_category_id  "
	+" LEFT JOIN adt.m_bed_category_master bcm2 on transfer.to_bed_category_id=bcm2.bed_category_id  "
	+" INNER JOIN adt.m_ward_master ward on transfer.from_ward_id=ward.ward_id "
	+" LEFT JOIN adt.m_ward_master ward2 on transfer.to_ward_id=ward2.ward_id "
	+" INNER JOIN adt.t_transfer_request traReq on transfer.transfer_request_id=traReq.transfer_request_id  "
	+" INNER JOIN doctor.m_doctor_master doc on traReq.treating_doctor_id=doc.doctor_id   "
	+" INNER JOIN adt.m_reason_master reason on traReq.reason_id=reason.reason_master_id "
	+ "INNER JOIN adt.m_icu_type_master icu on traReq.icu_type_id=icu.icu_type_id "
	+" WHERE transfer.transfer_status_id IN (10,3) "
	+" AND traReq.transfer_status_id IN (10,3) "
	+" AND transfer.status='A' "
	+" AND admission.status='A' "
	+ " AND adl.status='A' "
	+" AND transfer.organization_id=:organizationId "
	+" AND transfer.unit_id=:unitId "
	+ "AND traReq.transfer_type_id=3 "),
	
@NamedNativeQuery(name="GET_ICU_TRANSFER_REQ_LIST_BY_ADMISSION_ID",query=
	"SELECT transfer.transfer_id as \"transferId\", "
	+" transfer.transfer_request_id as \"transferRequestId\", "
	+ "admission.admission_id as \"admissionId\", "
	+ "adl.admission_details_id as \"admissionDetailsId\", "		
	+ "admission.patient_id as \"patientId\", "
	+ "admission.t_patient_id as \"tPatientId\", "
	+" pr.uhid_number as \"UHIDNumber\", "
	+" concat(pr.first_name,' ',pr.middle_name,' ',pr.last_name) as \"patientName\",  " 
	+" pr.gender_id as \"genderId\",   "
	+" gm.gender_code as \"genderCode\",  "
	+" to_char(pr.dob,'MM/dd/yyyy') as \"dob\",   "
	+" transfer.from_ward_id as \"fromWardId\", "
	+" ward.ward_name as \"fromWardName\", "
	+" transfer.from_bed_id as \"fromBedId\", " 
	+" bm.bed_number as \"fromBedNumber\", "
	+" transfer.from_bed_category_id as \"fromBedCategoryId\", "
	+" bcm1.bed_category_desc as \"fromBedCategoryDesc\", "
	+" bm.floor_id as \"fromFloorId\", "
	+" bm.room_id as \"fromRoomId\", "
	+" transfer.to_ward_id as \"toWardId\", "
	+" ward2.ward_name as \"toWardName\", "
	+" transfer.to_bed_id as \"toBedId\", " 
	+" bm2.bed_number as \"toBedNumber\", "
	+" transfer.to_bed_category_id as \"toBedCategoryId\", "
	+" bcm2.bed_category_desc as \"toBedCategoryDesc\", "
	+" bm2.floor_id as \"toFloorId\", "
	+" bm2.room_id as \"toRoomId\", "
	+" traReq.treating_doctor_id as \"doctorId\", "
	+" concat(doc.first_name,' ',doc.middle_name,' ',doc.last_name) as \"doctorName\", "
	+" traReq.reason_id as \"reasonId\",  "
	+" reason.reason_desc as \"reasonDesc\", "
	+" traReq.note as \"note\", "
	+ "traReq.icu_type_id as \"icuTypeId\", "
	+ "icu.icu_type as \"icuType\", "
	+ "traReq.transfer_status_id as \"transferStatusId\", "
	+" traReq.final_note as \"finalNote\", "
	+ "bm.billing_bed_category_id as \"fromBillingBedCategoryId\" "
	//+ "bm2.billing_bed_category_id as \"toBillingBedCategoryId\" "
	+" FROM adt.t_transfer transfer "
	+" INNER JOIN adt.t_admission admission on admission.admission_id=transfer.admission_id "
	+ "INNER JOIN adt.t_admission_details adl on admission.admission_id=adl.admission_id "
	+" INNER JOIN patient.t_patient_registration pr on admission.patient_id=pr.patient_id  "
	+" INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id   "
	+" INNER JOIN adt.m_bed_master bm on transfer.from_bed_id=bm.bed_id   "
	+" LEFT JOIN adt.m_bed_master bm2 on transfer.to_bed_id=bm2.bed_id   "
	+" INNER JOIN adt.m_floor_master floor on bm.floor_id=floor.floor_id  "
	+" INNER JOIN adt.m_bed_category_master bcm1 on transfer.from_bed_category_id=bcm1.bed_category_id  "
	+" LEFT JOIN adt.m_bed_category_master bcm2 on transfer.to_bed_category_id=bcm2.bed_category_id  "
	+" INNER JOIN adt.m_ward_master ward on transfer.from_ward_id=ward.ward_id "
	+" LEFT JOIN adt.m_ward_master ward2 on transfer.to_ward_id=ward2.ward_id "
	+" INNER JOIN adt.t_transfer_request traReq on transfer.transfer_request_id=traReq.transfer_request_id  "
	+" INNER JOIN doctor.m_doctor_master doc on traReq.treating_doctor_id=doc.doctor_id   "
	+" INNER JOIN adt.m_reason_master reason on traReq.reason_id=reason.reason_master_id "
	+ "INNER JOIN adt.m_icu_type_master icu on traReq.icu_type_id=icu.icu_type_id "
	+" WHERE transfer.transfer_status_id IN (10,3) "
	+" AND traReq.transfer_status_id IN (10,3) "
	+" AND transfer.status='A' "
	+" AND admission.status='A' "
	+ " AND adl.status='A' "
	+" AND transfer.organization_id=:organizationId "
	+" AND transfer.unit_id=:unitId "
	+ "AND traReq.transfer_type_id=3 "
	+ " AND transfer.admission_id=:admissionId "
	+" union "
	+" SELECT transfer.transfer_id as \"transferId\", "
	+" transfer.transfer_request_id as \"transferRequestId\", "
	+ "admission.admission_id as \"admissionId\", "	
	+ "adl.admission_details_id as \"admissionDetailsId\", "	
	+ "admission.patient_id as \"patientId\", "
	+ "admission.t_patient_id as \"tPatientId\", "
	+" pr.t_uhid as \"UHIDNumber\", "
	+" pr.patient_name as \"patientName\",  " 
	+" pr.gender_id as \"genderId\",   "
	+" gm.gender_code as \"genderCode\",  "
	+" concat(to_char(pr.age,'999'),' ', pr.age_format) as \"dob\",  " 
	+" transfer.from_ward_id as \"fromWardId\", "
	+" ward.ward_name as \"fromWardName\", "
	+" transfer.from_bed_id as \"fromBedId\", " 
	+" bm.bed_number as \"fromBedNumber\", "
	+" transfer.from_bed_category_id as \"fromBedCategoryId\", "
	+" bcm1.bed_category_desc as \"fromBedCategoryDesc\", "
	+" bm.floor_id as \"fromFloorId\", "
	+" bm.room_id as \"fromRoomId\", "
	+" transfer.to_ward_id as \"toWardId\", "
	+" ward2.ward_name as \"toWardName\", "
	+" transfer.to_bed_id as \"toBedId\", " 
	+" bm2.bed_number as \"toBedNumber\", "
	+" transfer.to_bed_category_id as \"toBedCategoryId\", "
	+" bcm2.bed_category_desc as \"toBedCategoryDesc\", "
	+" bm2.floor_id as \"toFloorId\", "
	+" bm2.room_id as \"toRoomId\", "
	+" traReq.treating_doctor_id as \"doctorId\", "
	+" concat(doc.first_name,' ',doc.middle_name,' ',doc.last_name) as \"doctorName\", "
	+" traReq.reason_id as \"reasonId\",  "
	+" reason.reason_desc as \"reasonDesc\", "
	+" traReq.note as \"note\", "
	+ "traReq.icu_type_id as \"icuTypeId\", "
	+ "icu.icu_type as \"icuType\", "
	+ "traReq.transfer_status_id as \"transferStatusId\", "
	+" traReq.final_note as \"finalNote\", "
	+ "bm.billing_bed_category_id as \"fromBillingBedCategoryId\" "
	//+ "bm2.billing_bed_category_id as \"toBillingBedCategoryId\" "
	+" FROM adt.t_transfer transfer "
	+" INNER JOIN adt.t_admission admission on admission.admission_id=transfer.admission_id  " 
	+ "INNER JOIN adt.t_admission_details adl on admission.admission_id=adl.admission_id "
	+" INNER JOIN patient.m_unknown_patient_registration pr on admission.t_patient_id=pr.unknown_patient_id  "
	+" INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id   "
	+" INNER JOIN adt.m_bed_master bm on transfer.from_bed_id=bm.bed_id   "
	+" LEFT JOIN adt.m_bed_master bm2 on transfer.to_bed_id=bm2.bed_id   "
	+" INNER JOIN adt.m_floor_master floor on bm.floor_id=floor.floor_id  "
	+" INNER JOIN adt.m_bed_category_master bcm1 on transfer.from_bed_category_id=bcm1.bed_category_id  "
	+" LEFT JOIN adt.m_bed_category_master bcm2 on transfer.to_bed_category_id=bcm2.bed_category_id  "
	+" INNER JOIN adt.m_ward_master ward on transfer.from_ward_id=ward.ward_id "
	+" LEFT JOIN adt.m_ward_master ward2 on transfer.to_ward_id=ward2.ward_id "
	+" INNER JOIN adt.t_transfer_request traReq on transfer.transfer_request_id=traReq.transfer_request_id  "
	+" INNER JOIN doctor.m_doctor_master doc on traReq.treating_doctor_id=doc.doctor_id   "
	+" INNER JOIN adt.m_reason_master reason on traReq.reason_id=reason.reason_master_id "
	+ "INNER JOIN adt.m_icu_type_master icu on traReq.icu_type_id=icu.icu_type_id "
	+" WHERE transfer.transfer_status_id IN (10,3) "
	+" AND traReq.transfer_status_id IN (10,3) "
	+" AND transfer.status='A' "
	+" AND admission.status='A' "
	+ " AND adl.status='A' "
	+" AND transfer.organization_id=:organizationId "
	+" AND transfer.unit_id=:unitId "
	+ "AND traReq.transfer_type_id=3 "
	+ " AND transfer.admission_id=:admissionId ")

	
    
})

@Entity
@Table(name="t_transfer",schema="adt")
@SequenceGenerator(name="transfer_seq",sequenceName="adt.transfer_seq",allocationSize=1)
public class Transfer 
{
	@Id
	@Column(name="transfer_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="transfer_seq")
	private int transferId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="admission_id")
	private Integer admissionId;
	
	@Column(name="from_bed_category_id")
	private Integer fromBedCategoryId;
	
	@Column(name="from_billing_bed_category_id")
	private Integer fromBillingBedCategoryId;
	
	@Column(name="from_ward_id")
	private Integer fromWardId;
	
	@Column(name="from_room_id")
	private Integer fromRoomId;
	
	@Column(name="from_bed_id")
	private Integer fromBedId;
	
	@Column(name="from_date")
	private Date fromDate;
	
	@Column(name="from_time")
	private Date fromTime;
	
	@Column(name="to_bed_category_id")
	private Integer toBedCategoryId;
	
	@Column(name="to_billing_bed_category_id")
	private Integer toBillingBedCategoryId;
	
	@Column(name="to_ward_id")
	private Integer toWardId;
	
	@Column(name="to_room_id")
	private Integer toRoomId;
	
	@Column(name="to_bed_id")
	private Integer toBedId;
	
	@Column(name="to_date")
	private Date toDate;
	
	@Column(name="to_time")
	private Date toTime;
	
	@Column(name="is_primary")
	private char isPrimary;
	
	@Column(name="transfer_request_id")
	private Integer transferRequestId;
	
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
	@JoinColumn(name = "from_bed_category_id", insertable = false, nullable = false, updatable = false)
	private BedCategoryMaster bedCategoryMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "from_billing_bed_category_id", insertable = false, nullable = false, updatable = false)
	private BillingBedCategoryMaster billingBedCategoryMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "from_ward_id", insertable = false, nullable = false, updatable = false)
	private WardMaster wardMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "from_room_id", insertable = false, nullable = false, updatable = false)
	private RoomMaster roomMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "from_bed_id", insertable = false, nullable = false, updatable = false)
	private BedMaster bedMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "to_bed_category_id", insertable = false, nullable = false, updatable = false)
	private BedCategoryMaster bedCategoryMaster2;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "to_billing_bed_category_id", insertable = false, nullable = false, updatable = false)
	private BillingBedCategoryMaster billingBedCategoryMaster2;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "to_ward_id", insertable = false, nullable = false, updatable = false)
	private WardMaster wardMaster2;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "to_room_id", insertable = false, nullable = false, updatable = false)
	private RoomMaster roomMaster2;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "to_bed_id", insertable = false, nullable = false, updatable = false)
	private BedMaster bedMaster2;
	
	
	

	public Integer getTransferStatusId() {
		return transferStatusId;
	}

	public void setTransferStatusId(Integer transferStatusId) {
		this.transferStatusId = transferStatusId;
	}

	public int getTransferId() {
		return transferId;
	}

	public void setTransferId(int transferId) {
		this.transferId = transferId;
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

	public Integer getFromBedCategoryId() {
		return fromBedCategoryId;
	}

	public void setFromBedCategoryId(Integer fromBedCategoryId) {
		this.fromBedCategoryId = fromBedCategoryId;
	}

	public Integer getFromBillingBedCategoryId() {
		return fromBillingBedCategoryId;
	}

	public void setFromBillingBedCategoryId(Integer fromBillingBedCategoryId) {
		this.fromBillingBedCategoryId = fromBillingBedCategoryId;
	}

	public Integer getFromWardId() {
		return fromWardId;
	}

	public void setFromWardId(Integer fromWardId) {
		this.fromWardId = fromWardId;
	}

	public Integer getFromRoomId() {
		return fromRoomId;
	}

	public void setFromRoomId(Integer fromRoomId) {
		this.fromRoomId = fromRoomId;
	}

	public Integer getFromBedId() {
		return fromBedId;
	}

	public void setFromBedId(Integer fromBedId) {
		this.fromBedId = fromBedId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getFromTime() {
		return fromTime;
	}

	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}

	

	public Integer getToBedCategoryId() {
		return toBedCategoryId;
	}

	public void setToBedCategoryId(Integer toBedCategoryId) {
		this.toBedCategoryId = toBedCategoryId;
	}

	public Integer getToBillingBedCategoryId() {
		return toBillingBedCategoryId;
	}

	public void setToBillingBedCategoryId(Integer toBillingBedCategoryId) {
		this.toBillingBedCategoryId = toBillingBedCategoryId;
	}

	public Integer getToWardId() {
		return toWardId;
	}

	public void setToWardId(Integer toWardId) {
		this.toWardId = toWardId;
	}

	public Integer getToRoomId() {
		return toRoomId;
	}

	public void setToRoomId(Integer toRoomId) {
		this.toRoomId = toRoomId;
	}

	public Integer getToBedId() {
		return toBedId;
	}

	public void setToBedId(Integer toBedId) {
		this.toBedId = toBedId;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Date getToTime() {
		return toTime;
	}

	public void setToTime(Date toTime) {
		this.toTime = toTime;
	}

	public char getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(char isPrimary) {
		this.isPrimary = (isPrimary == '\u0000') ? 'N' : isPrimary;
	}

	public Integer getTransferRequestId() {
		return transferRequestId;
	}

	public void setTransferRequestId(Integer transferRequestId) {
		this.transferRequestId = transferRequestId;
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
		this.status = (status == '\u0000') ? 'A' : status;
	}
	

	
	
	
}


