package com.param.adt.discharge.model;

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
import com.param.adt.master.global.model.DischargeTypeMaster;
import com.param.adt.master.global.model.OrganizationMaster;
import com.param.adt.master.global.model.ReasonMaster;
import com.param.adt.master.global.model.UnitMaster;
import com.param.global.model.DoctorMaster;

@NamedNativeQueries({

		@NamedNativeQuery(name = "GET_ER_DISCHARGE_LIST", query = " SELECT  dis.discharge_id as \"dischargeId\",  "
				+ "dis.discharge_type_id as \"dischargeTypeId\",  " + "disStatus.discharge_desc as \"dischargeDesc\",  "
				+ "dis.discharge_status_id as \"dischargeStatusId\",  " + "admission.admission_id as \"admissionId\",  "
				+ "admission.patient_id as \"patientId\",  " + "admission.t_patient_id as \"tPatientId\",  "
				+ "admission.admission_number as \"admissionNumber\",  "
				+ "admission.visit_type_id as \"visitTypeId\",  " + "admission.doctor_id as \"doctorId\",   "
				+ "pr.uhid_number as \"UHIDNumber\",  "
				+ "concat(pr.first_name,' ',pr.middle_name,' ',pr.last_name) as \"pFirstName\",  "
				+ "pr.gender_id as \"genderId\",   " + "gm.gender_code as \"genderCode\",  "
				+ "to_char(pr.dob,'MM/dd/yyyy') as \"dob\",  "
				+ "concat(doc.first_name,' ', doc.middle_name,' ',doc.last_name) as \"dFirstName\",  "
				+ "to_char(adl.doa,'dd/MM/yyyy') as \"doa\",  " + "to_char(adl.pdd,'dd/MM/yyyy') as \"pdd\",  "
				+ "bedCatMst.bed_category_desc as \"bedCategoryDesc\", " + "bm.bed_number as \"bedNumber\",  "
				+ "bm.bed_id as \"bedId\",  " + "dis.remark_discharge as \"remarkDischarge\", "
				+ "dtm.discharge_type_name as \"dischargeTypeName\" " + "FROM adt.t_discharge dis  "
				+ "INNER JOIN adt.m_discharge_status_master disStatus on dis.discharge_status_id=disStatus.discharge_status_id  "
				+ "INNER JOIN adt.m_reason_for_discharge_master dtm on dis.discharge_type_id=dtm.discharge_type_id  "
				+ "INNER JOIN adt.t_admission admission on admission.admission_id = dis.admission_id  "
				+ "INNER JOIN patient.t_patient_registration pr on admission.patient_id=pr.patient_id  "
				+ "INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id  "
				+ "INNER JOIN doctor.m_doctor_master doc on admission.doctor_id=doc.doctor_id  "
				+ "INNER JOIN adt.t_admission_details adl on adl.admission_id=admission.admission_id   "
				+ "LEFT JOIN adt.m_bed_master bm on adl.bed_id=bm.bed_id  "
				+ "LEFT JOIN adt.m_bed_category_master bedCatMst on adl.bed_category_id = bedCatMst.bed_category_id "
				+ "WHERE admission.status='A'  " + "AND adl.status='A'  " + "AND dis.status='A' "
				+ "AND admission.organization_id=:organizationId " + "AND admission.unit_id=:unitId "
				+ "AND dis.visit_type_id=4  " + "UNION  " + "select dis.discharge_id as \"dischargeId\",  "
				+ "dis.discharge_type_id as \"dischargeTypeId\", " + "disStatus.discharge_desc as \"dischargeDesc\",  "
				+ "dis.discharge_status_id as \"dischargeStatusId\",  " + "admission.admission_id as \"admissionId\",  "
				+ "admission.patient_id as \"patientId\",  " + "admission.t_patient_id as \"tPatientId\",  "
				+ "admission.admission_number as \"admissionNumber\",  "
				+ "admission.visit_type_id as \"visitTypeId\",   " + "admission.doctor_id as \"doctorId\",  "
				+ "pr.t_uhid as \"UHIDNumber\",  " + "pr.patient_name as \"pFirstName\" ,  "
				+ "pr.gender_id as \"genderId\",   " + "gm.gender_code as \"genderCode\",  "
				+ "concat(to_char(pr.age,'999'),' ', pr.age_format) as \"dob\",  "
				+ "concat(doc.first_name,' ', doc.middle_name,' ',doc.last_name) as \"dFirstName\",  "
				+ "to_char(adl.doa,'dd/MM/yyyy') as \"doa\",    " + "to_char(adl.pdd,'dd/MM/yyyy') as \"pdd\",   "
				+ "bedCatMst.bed_category_desc as \"bedCategoryDesc\", " + "bm.bed_number as \"bedNumber\",  "
				+ "bm.bed_id as \"bedId\",  " + "dis.remark_discharge as \"remarkDischarge\", "
				+ "dtm.discharge_type_name as \"dischargeTypeName\" " + "FROM adt.t_discharge dis  "
				+ "INNER JOIN adt.m_discharge_status_master disStatus on dis.discharge_status_id=disStatus.discharge_status_id  "
				+ "INNER JOIN adt.m_reason_for_discharge_master dtm on dis.discharge_type_id=dtm.discharge_type_id  "
				+ "INNER JOIN adt.t_admission admission on admission.admission_id = dis.admission_id  "
				+ "INNER JOIN patient.m_unknown_patient_registration pr on admission.t_patient_id=pr.unknown_patient_id  "
				+ "INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id   "
				+ "INNER JOIN doctor.m_doctor_master doc on admission.doctor_id=doc.doctor_id   "
				+ "INNER JOIN adt.t_admission_details adl on adl.admission_id=admission.admission_id  "
				+ "LEFT JOIN adt.m_bed_master bm on adl.bed_id=bm.bed_id   "
				+ "LEFT JOIN adt.m_bed_category_master bedCatMst on adl.bed_category_id = bedCatMst.bed_category_id  "
				+ "WHERE admission.status='A'  " + "AND adl.status='A'  " + "AND dis.status='A' "
				+ "AND admission.organization_id=:organizationId " + "AND admission.unit_id=:unitId "
				+ "AND dis.visit_type_id=4 "),

		@NamedNativeQuery(name = "GET_DISCHARGE_PATIENT_LIST_FOR_NURSING", query = "SELECT  dis.discharge_id as \"dischargeId\", "
				+ "dis.discharge_type_id as \"dischargeTypeId\", " + "disStatus.discharge_desc as \"dischargeDesc\", "
				+ "dis.discharge_status_id as \"dischargeStatusId\", "
				+ "to_char(dis.discharge_date,'dd/MM/yyyy') as \"futureDischargeDate\", "
				+ "admission.admission_id as \"admissionId\", " + "admission.patient_id as \"patientId\", "
				+ "admission.t_patient_id as \"tPatientId\", " + "admission.admission_number as \"admissionNumber\", "
				+ "admission.visit_type_id as \"visitTypeId\", " + "admission.doctor_id as \"doctorId\", "
				+ "admission.speciality_id as \"specialityId\", " + "sp.speciality_name as \"specialityName\", "
				+ "pr.uhid_number as \"UHIDNumber\", "
				+ "concat(pr.first_name,' ',pr.middle_name,' ',pr.last_name) as \"pFirstName\" , "
				+ "pr.gender_id as \"genderId\", " + "gm.gender_code as \"genderCode\", "
				+ "to_char(pr.dob,'MM/dd/yyyy') as \"dob\", "
				+ " concat(doc.first_name,' ', doc.middle_name,' ',doc.last_name) as \"dFirstName\", "
				+ "to_char(adl.doa,'dd/MM/yyyy') as \"doa\", " + "to_char(adl.pdd,'dd/MM/yyyy') as \"pdd\", "
				+ "apml.payment_entitlement_id as \"paymentEntitlementId\", "
				+ "pe.payment_entitlement_desc as \"paymentEntitlementDesc\", "
				+ "bedCatMst.bed_category_desc as \"bedCategoryDesc\", " + "bm.bed_number as \"bedNumber\", "
				+ "bm.bed_id as \"bedId\", " + "dtm.discharge_type_name as \"dischargeTypeName\" "
				+ "FROM adt.t_discharge dis "
				+ "INNER JOIN adt.m_discharge_status_master disStatus on dis.discharge_status_id=disStatus.discharge_status_id "
				+ "INNER JOIN adt.m_reason_for_discharge_master dtm on dis.discharge_type_id=dtm.discharge_type_id "
				+ "INNER JOIN adt.t_admission admission on admission.admission_id = dis.admission_id "
				+ "INNER JOIN patient.t_patient_registration pr on admission.patient_id=pr.patient_id "
				+ "INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id "
				+ "INNER JOIN doctor.m_doctor_master doc on admission.doctor_id=doc.doctor_id "
				+ "INNER JOIN doctor.m_speciality_master sp on admission.speciality_id=sp.speciality_id  "
				+ "INNER JOIN adt.t_admission_details adl on adl.admission_id=admission.admission_id  "
				+ "INNER JOIN adt.m_bed_master bm on adl.bed_id=bm.bed_id "
				+ "INNER JOIN adt.t_admission_note anl on admission.admission_id=anl.admission_id  "
				+ "INNER JOIN adt.t_admission_patient_mapping apml on anl.admission_note_id = apml.admission_note_id "
				+ "INNER JOIN adt.m_bed_category_master bedCatMst on apml.bed_category_id = bedCatMst.bed_category_id  "
				+ "INNER JOIN public.m_payment_entitlement_master pe on apml.payment_entitlement_id = pe.payment_entitlement_id "
				+ " WHERE admission.status='A' " 
				+ "AND apml.active_status='B' " 
				+ "AND apml.status='A' "
				+ "AND anl.status='A' " 
				+ "AND admission.organization_id=:orgId  " 
				+ "AND admission.unit_id=:unitId "
				+ "AND dis.discharge_date =:date " 
				+ "AND dis.visit_type_id=1 "
				+ "AND adl.status='A' " 
				+ "UNION "
				+ "select dis.discharge_id as \"dischargeId\", " + "dis.discharge_type_id as \"dischargeTypeId\", "
				+ "disStatus.discharge_desc as \"dischargeDesc\", "
				+ "dis.discharge_status_id as \"dischargeStatusId\", "
				+ "to_char(dis.discharge_date,'dd/MM/yyyy') as \"futureDischargeDate\", "
				+ "admission.admission_id as \"admissionId\", " + " admission.patient_id as \"patientId\", "
				+ "admission.t_patient_id as \"tPatientId\", " + " admission.admission_number as \"admissionNumber\", "
				+ "admission.visit_type_id as \"visitTypeId\", " + "admission.doctor_id as \"doctorId\", "
				+ "admission.speciality_id as \"specialityId\", " + "sp.speciality_name as \"specialityName\", "
				+ "pr.t_uhid as \"UHIDNumber\", " + "pr.patient_name as \"pFirstName\" , "
				+ "pr.gender_id as \"genderId\", " + "gm.gender_code as \"genderCode\", "
				+ "concat(to_char(pr.age,'999'),' ', pr.age_format) as \"dob\", "
				+ "concat(doc.first_name,' ', doc.middle_name,' ',doc.last_name) as \"dFirstName\", "
				+ "to_char(adl.doa,'dd/MM/yyyy') as \"doa\",  " + "to_char(adl.pdd,'dd/MM/yyyy') as \"pdd\",  "
				+ "apml.payment_entitlement_id as \"paymentEntitlementId\", "
				+ "pe.payment_entitlement_desc as \"paymentEntitlementDesc\", "
				+ "bedCatMst.bed_category_desc as \"bedCategoryDesc\", " + "bm.bed_number as \"bedNumber\", "
				+ " bm.bed_id as \"bedId\", " + "dtm.discharge_type_name as \"dischargeTypeName\" "
				+ " FROM adt.t_discharge dis "
				+ "INNER JOIN adt.m_discharge_status_master disStatus on dis.discharge_status_id=disStatus.discharge_status_id "
				+ "INNER JOIN adt.m_reason_for_discharge_master dtm on dis.discharge_type_id=dtm.discharge_type_id "
				+ "INNER JOIN adt.t_admission admission on admission.admission_id = dis.admission_id "
				+ "INNER JOIN patient.m_unknown_patient_registration pr on admission.t_patient_id=pr.unknown_patient_id  "
				+ "INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id   "
				+ "INNER JOIN doctor.m_doctor_master doc on admission.doctor_id=doc.doctor_id  "
				+ "INNER JOIN doctor.m_speciality_master sp on admission.speciality_id=sp.speciality_id  "
				+ "INNER JOIN adt.t_admission_details adl on adl.admission_id=admission.admission_id "
				+ "INNER JOIN adt.m_bed_master bm on adl.bed_id=bm.bed_id  "
				+ "INNER JOIN adt.t_admission_note anl on admission.admission_id=anl.admission_id "
				+ "INNER JOIN adt.t_admission_patient_mapping apml on anl.admission_note_id = apml.admission_note_id "
				+ "INNER JOIN adt.m_bed_category_master bedCatMst on apml.bed_category_id = bedCatMst.bed_category_id  "
				+ "INNER JOIN public.m_payment_entitlement_master pe on apml.payment_entitlement_id = pe.payment_entitlement_id  "
				+ " WHERE admission.status='A'  " + "AND apml.active_status='B'  " + "AND apml.status='A'  "
				+ "AND anl.status='A' " + "AND admission.organization_id=:orgId  " + "AND admission.unit_id=:unitId "
				+ "AND dis.discharge_date =:date "
				+ "AND dis.visit_type_id=1 "
				+ "AND adl.status='A' "),

		@NamedNativeQuery(name = "GET_PATIENT_READY_FOR_BILLING", 
		query = "SELECT DISTINCT(discharge.admission_id) as \"admissionId\", "
				+ " admission.patient_id as \"patientId\", " 
				+ " admission.t_patient_id as \"tPatientId\", "
				+ " admission.admission_number as \"admissionNumber\",  "
				+ "  admission.visit_type_id as \"visitTypeId\", " 
				+ " admission.doctor_id as \"doctorId\",  "
				+ " admission.speciality_id as \"specialityId\", " 
				+ "  sm.speciality_name as \"specialityName\", "
				+ " pr.uhid_number as \"UHIDNumber\",  " 
				+ " gm.gender_code as \"genderCode\",  "
				+ "  concat(pr.first_name,' ',pr.middle_name,' ',pr.last_name) as \"pFirstName\" , "
				+ "  to_char(pr.dob,'MM/dd/yyyy') as \"dob\",  "
				+ "  concat(doc.first_name,' ', doc.middle_name,' ',doc.last_name) as \"dFirstName\", "
				+ "  bm.bed_number as \"bedNumber\", " 
				+ "  bm.floor_id as \"floorId\", "
				+ "  floor.floor_name as \"floorName\",  " 
				+ " ser.order_status_id as \"orderStatusId\", "
				+ " discharge.discharge_id as \"dischargeId\"," 
				+ "discharge.discharge_type_id as \"dischargeTypeId\", "
				+ "discharge.discharge_status_id as \"dischargeStatusId\", "
				+ "disStatus.discharge_desc as \"dischargeDesc\", "
				+ "dtm.discharge_type_name as \"dischargeTypeName\","
				+ "admissionDetails.er_bed_type_allocation as \"erBedTypeAllocation\" " 
				+ "  FROM adt.t_discharge discharge  "
				+ "INNER JOIN adt.m_discharge_status_master disStatus on discharge.discharge_status_id=disStatus.discharge_status_id "
				+ "INNER JOIN adt.m_reason_for_discharge_master dtm on discharge.discharge_type_id=dtm.discharge_type_id "
				+ "  INNER JOIN adt.t_admission as admission on discharge.admission_id=admission.admission_id "
				+ "  inner join patient.t_patient_registration pr on admission.patient_id=pr.patient_id "
				+ "  inner join public.m_gender_master gm on gm.gender_id=pr.gender_id  "
				+ " inner join public.m_gender_master gender on pr.gender_id=gender.gender_id  "
				+ " inner join adt.t_admission_details admissionDetails on admission.admission_id=admissionDetails.admission_id "
				+ " Inner join adt.m_bed_master bm on admissionDetails.bed_id=bm.bed_id "
				+ " INNER JOIN adt.m_floor_master floor on floor.floor_id=bm.floor_id "
				+ " INNER JOIN public.t_patient_sevices_order ser on discharge.admission_id=ser.admission_id "
				+ " INNER JOIN doctor.m_doctor_master doc on discharge.doctor_id =doc.doctor_id "
				+ " LEFT JOIN doctor.m_speciality_master sm on admission.speciality_id=sm.speciality_id  "
				+ " WHERE discharge.admission_id NOT IN(select sero.admission_id FROM adt.t_discharge dis "
				+ " INNER JOIN public.t_patient_sevices_order sero on dis.admission_id=sero.admission_id  "
				+ " where sero.order_status_id = 1  " 
				+ " AND sero.organization_id =:orgId  "
				+ " AND sero.unit_id = :unitId )  " 
				+ " AND ser.organization_id =:orgId "
				+ " AND ser.unit_id =:unitId  " 
				+ " AND discharge.organization_id =:orgId "
				+ " AND discharge.unit_id =:unitId "
				+ " AND discharge.discharge_status_id = 1 "
				+ " AND discharge.visit_type_Id=:visitTypeId "
				+ " OR discharge.discharge_status_id = 2 " 
				+ " union  "
				+ " SELECT DISTINCT(discharge.admission_id) as \"admissionId\", "
				+ " admission.patient_id as \"patientId\", " 
				+ " admission.t_patient_id as \"tPatientId\", "
				+ " admission.admission_number as \"admissionNumber\", "
				+ " admission.visit_type_id as \"visitTypeId\", " 
				+ " admission.doctor_id as \"doctorId\",     "
				+ " admission.speciality_id as \"specialityId\", " 
				+ " sm.speciality_name as \"specialityName\", "
				+ " pr.t_uhid as \"UHIDNumber\",   " 
				+ " gm.gender_code as \"genderCode\",  "
				+ " pr.patient_name as \"pFirstName\" ,  "
				+ " concat(to_char(pr.age,'999'),' ', pr.age_format) as \"dob\",  "
				+ " concat(doc.first_name,' ', doc.middle_name,' ',doc.last_name) as \"dFirstName\", "
				+ " bm.bed_number as \"bedNumber\", " 
				+ " bm.floor_id as \"floorId\", "
				+ " floor.floor_name as \"floorName\", " 
				+ " ser.order_status_id as \"orderStatusId\", "
				+ " discharge.discharge_id as \"dischargeId\",  "
				+ "discharge.discharge_type_id as \"dischargeTypeId\", "
				+ "discharge.discharge_status_id as \"dischargeStatusId\", "
				+ "disStatus.discharge_desc as \"dischargeDesc\", "
				+ "dtm.discharge_type_name as \"dischargeTypeName\", " 
				+ "admissionDetails.er_bed_type_allocation as \"erBedTypeAllocation\" " 
				+ " FROM adt.t_discharge discharge  "
				+ "INNER JOIN adt.m_discharge_status_master disStatus on discharge.discharge_status_id=disStatus.discharge_status_id "
				+ "INNER JOIN adt.m_reason_for_discharge_master dtm on discharge.discharge_type_id=dtm.discharge_type_id "
				+ " INNER JOIN adt.t_admission as admission on discharge.admission_id=admission.admission_id  "
				+ " inner join patient.m_unknown_patient_registration pr on admission.t_patient_id=pr.unknown_patient_id  "
				+ " inner join public.m_gender_master gm on gm.gender_id=pr.gender_id  "
				+ " inner join public.m_gender_master gender on pr.gender_id=gender.gender_id  "
				+ " inner join adt.t_admission_details admissionDetails on admission.admission_id=admissionDetails.admission_id  "
				+ " LEFT join adt.m_bed_master bm on admissionDetails.bed_id=bm.bed_id  "
				+ " LEFT JOIN adt.m_floor_master floor on floor.floor_id=bm.floor_id  "
				+ " INNER JOIN public.t_patient_sevices_order ser on discharge.admission_id=ser.admission_id  "
				+ " INNER JOIN doctor.m_doctor_master doc on discharge.doctor_id =doc.doctor_id  "
				+ " LEFT JOIN doctor.m_speciality_master sm on admission.speciality_id=sm.speciality_id  "
				+ " WHERE discharge.admission_id NOT IN(select sero.admission_id FROM adt.t_discharge dis  "
				+ " INNER JOIN public.t_patient_sevices_order sero on dis.admission_id=sero.admission_id  "
				+ " where sero.order_status_id = 1  " 
				+ " AND sero.organization_id =:orgId  "
				+ " AND sero.unit_id =:unitId )  " 
				+ " AND ser.organization_id =:orgId  " 
				+ " AND ser.unit_id =:unitId "
				+ " AND discharge.organization_id =:orgId " 
				+ " AND discharge.unit_id =:unitId "
				+ " AND discharge.discharge_status_id = 1 "
				+ " AND discharge.visit_type_Id=:visitTypeId "
				+ " OR discharge.discharge_status_id = 2"),

		@NamedNativeQuery(name = "GET_PATIENT_FINAL_DISCHARGE_LIST", query = "SELECT DISTINCT(discharge.admission_id) as \"admissionId\", "
				+ " admission.patient_id as \"patientId\", " 
				+ " admission.t_patient_id as \"tPatientId\", "
				+ " admission.admission_number as \"admissionNumber\",  "
				+ " admission.visit_type_id as \"visitTypeId\", " 
				+ " admission.doctor_id as \"doctorId\",  "
				+ " admission.speciality_id as \"specialityId\", " 
				+ " sm.speciality_name as \"specialityName\", "
				+ " pr.uhid_number as \"UHIDNumber\",  " 
				+ " gm.gender_code as \"genderCode\",  "
				+ " concat(pr.first_name,' ',pr.middle_name,' ',pr.last_name) as \"pFirstName\" , "
				+ " to_char(pr.dob,'MM/dd/yyyy') as \"dob\",  "
				+ " concat(doc.first_name,' ', doc.middle_name,' ',doc.last_name) as \"dFirstName\", "
				+ " bm.bed_id as \"bedId\", " 
				+ " bm.bed_number as \"bedNumber\", " 
				+ " bm.floor_id as \"floorId\", "
				+ " bm.room_id as \"roomId\", "
				+ " bm.ward_id as \"wardId\", "
				+ " ward.is_icu as \"isIcu\", "
				+ " bm.bed_category_id as \"bedCategoryId\", "
				+ " nur.nursing_station_id as \"nursingStationId\", "
				+ " floor.floor_name as \"floorName\",  " 
				+ " ser.order_status_id as \"orderStatusId\", "
				+ " discharge.discharge_id as \"dischargeId\"," 
				+ " discharge.discharge_type_id as \"dischargeTypeId\", "
				+ " discharge.discharge_status_id as \"dischargeStatusId\", "
				+ " disStatus.discharge_desc as \"dischargeDesc\", "
				+ " dtm.discharge_type_name as \"dischargeTypeName\", "
				+ " admissionDetails.er_bed_type_allocation as \"erBedTypeAllocation\" " 
				+ " FROM adt.t_discharge discharge  "
				+ " INNER JOIN adt.m_discharge_status_master disStatus on discharge.discharge_status_id=disStatus.discharge_status_id "
				+ " INNER JOIN adt.m_reason_for_discharge_master dtm on discharge.discharge_type_id=dtm.discharge_type_id "
				+ " INNER JOIN adt.t_admission as admission on discharge.admission_id=admission.admission_id "
				+ " inner join patient.t_patient_registration pr on admission.patient_id=pr.patient_id "
				+ " inner join public.m_gender_master gm on gm.gender_id=pr.gender_id  "
				+ " inner join adt.t_admission_details admissionDetails on admission.admission_id=admissionDetails.admission_id "
				+ " LEFT join adt.m_bed_master bm on admissionDetails.bed_id=bm.bed_id "
				+ " LEFT join adt.m_ward_master as ward on bm.ward_id=ward.ward_id "
				+ " LEFT JOIN adt.t_nursing_station_ward_mapper nur on nur.ward_id= ward.ward_id "
				+ " LEFT JOIN adt.m_floor_master floor on floor.floor_id=bm.floor_id "
				+ " INNER JOIN public.t_patient_sevices_order ser on discharge.admission_id=ser.admission_id "
				+ " INNER JOIN doctor.m_doctor_master doc on discharge.doctor_id =doc.doctor_id "
				+ " LEFT JOIN doctor.m_speciality_master sm on admission.speciality_id=sm.speciality_id  "
				+ " WHERE discharge.admission_id NOT IN(select sero.admission_id FROM adt.t_discharge dis "
				+ " INNER JOIN public.t_patient_sevices_order sero on dis.admission_id=sero.admission_id  "
				+ " where sero.order_status_id = 1  " 
				+ " AND sero.organization_id =:orgId  	"
				+ " AND sero.unit_id = :unitId )  " 
				+ " AND ser.organization_id =:orgId "
				+ " AND ser.unit_id =:unitId  " 
				+ " AND discharge.organization_id =:orgId "
				+ " AND discharge.unit_id =:unitId " 
				+ " AND discharge.discharge_status_id = 3 " 
				+ " AND discharge.visit_type_Id=:visitTypeId "
				+ " union  "
				+ " SELECT DISTINCT(discharge.admission_id) as \"admissionId\", "
				+ " admission.patient_id as \"patientId\", " 
				+ " admission.t_patient_id as \"tPatientId\", "
				+ " admission.admission_number as \"admissionNumber\", "
				+ " admission.visit_type_id as \"visitTypeId\", " 
				+ " admission.doctor_id as \"doctorId\",     "
				+ " admission.speciality_id as \"specialityId\", " 
				+ " sm.speciality_name as \"specialityName\", "
				+ " pr.t_uhid as \"UHIDNumber\",   " 
				+ " pr.patient_name as \"pFirstName\" ,  "
				+ " gm.gender_code as \"genderCode\",  "
				+ " concat(to_char(pr.age,'999'),' ', pr.age_format) as \"dob\",  "
				+ " concat(doc.first_name,' ', doc.middle_name,' ',doc.last_name) as \"dFirstName\", "
				+ "bm.bed_id as \"bedId\", " 
				+ " bm.bed_number as \"bedNumber\", " 
				+ " bm.floor_id as \"floorId\", "
				+ " bm.room_id as \"roomId\", "
				+ " bm.ward_id as \"wardId\", "
				+ " ward.is_icu as \"isIcu\", "
				+ " bm.bed_category_id as \"bedCategoryId\", "
				+ " nur.nursing_station_id as \"nursingStationId\", "
				+ " floor.floor_name as \"floorName\", "  
				+ " ser.order_status_id as \"orderStatusId\", "
				+ " discharge.discharge_id as \"dischargeId\",  "
				+ "discharge.discharge_type_id as \"dischargeTypeId\", "
				+ "discharge.discharge_status_id as \"dischargeStatusId\", "
				+ "disStatus.discharge_desc as \"dischargeDesc\", "
				+ "dtm.discharge_type_name as \"dischargeTypeName\", " 
				+ "admissionDetails.er_bed_type_allocation as \"erBedTypeAllocation\" " 
				+ " FROM adt.t_discharge discharge  "
				+ "INNER JOIN adt.m_discharge_status_master disStatus on discharge.discharge_status_id=disStatus.discharge_status_id "
				+ "INNER JOIN adt.m_reason_for_discharge_master dtm on discharge.discharge_type_id=dtm.discharge_type_id "
				+ " INNER JOIN adt.t_admission as admission on discharge.admission_id=admission.admission_id  "
				+ " inner join patient.m_unknown_patient_registration pr on admission.t_patient_id=pr.unknown_patient_id  "
				+ " inner join public.m_gender_master gm on gm.gender_id=pr.gender_id  "
				+ " inner join adt.t_admission_details admissionDetails on admission.admission_id=admissionDetails.admission_id  "
				+ " LEFT join adt.m_bed_master bm on admissionDetails.bed_id=bm.bed_id  "
				+ " LEFT join adt.m_ward_master as ward on bm.ward_id=ward.ward_id "
				+ " LEFT JOIN adt.t_nursing_station_ward_mapper nur on nur.ward_id= ward.ward_id "
				+ " LEFT JOIN adt.m_floor_master floor on floor.floor_id=bm.floor_id  "
				+ " INNER JOIN public.t_patient_sevices_order ser on discharge.admission_id=ser.admission_id  "
				+ " INNER JOIN doctor.m_doctor_master doc on discharge.doctor_id =doc.doctor_id  "
				+ " LEFT JOIN doctor.m_speciality_master sm on admission.speciality_id=sm.speciality_id  "
				+ " WHERE discharge.admission_id NOT IN(select sero.admission_id FROM adt.t_discharge dis  "
				+ " INNER JOIN public.t_patient_sevices_order sero on dis.admission_id=sero.admission_id  "
				+ " where sero.order_status_id = 1  " 
				+ " AND sero.organization_id =:orgId  "
				+ " AND sero.unit_id =:unitId )  " 
				+ " AND ser.organization_id =:orgId  " 
				+ " AND ser.unit_id =:unitId "
				+ " AND discharge.organization_id =:orgId " 
				+ " AND discharge.unit_id =:unitId "
				+ " AND discharge.discharge_status_id = 3 "
				+ " AND discharge.visit_type_Id=:visitTypeId ") })

@Entity
@Table(name = "t_discharge", schema = "adt")
@SequenceGenerator(name = "discharge_seq", sequenceName = "adt.discharge_seq", allocationSize = 1)
public class Discharge {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discharge_seq")
	@Column(name = "discharge_id")
	private int dischargeId;

	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "organization_id")
	private Integer organizationId;

	@Column(name = "admission_id")
	private Integer admissionId;

	@Column(name = "doctor_id")
	private Integer doctorId;

	@Column(name = "discharge_type_id")
	private Integer dischargeTypeId;

	@Column(name = "reason_id")
	private Integer reasonId;

	@Column(name = "destination_id")
	private Integer destinationId;

	@Column(name = "discharge_date")
	private Date dischargeDate;

	@Column(name = "discharge_time")
	private Date dischargeTime;

	@Column(name = "is_death")
	private char isDeath;

	@Column(name = "remark_discharge")
	private String remarkDischarge;

	@Column(name = "cancel_reason_id")
	private Integer cancelReasonId;

	@Column(name = "note_cancel")
	private String noteCancel;

	@Column(name = "status")
	private char status;

	@Column(name = "discharge_status_id")
	private Integer dischargeStatusId;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "visit_type_id")
	private Integer visitTypeId;

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
	@JoinColumn(name = "doctor_id", insertable = false, nullable = false, updatable = false)
	private DoctorMaster doctor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "discharge_type_id", insertable = false, nullable = false, updatable = false)
	private DischargeTypeMaster dischargeTypeMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reason_id", insertable = false, nullable = false, updatable = false)
	private ReasonMaster reason;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "destination_id", insertable = false, nullable = false, updatable = false)
	private BedMaster bedMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cancel_reason_id", insertable = false, nullable = false, updatable = false)
	private ReasonMaster reason2;

	public Integer getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public int getDischargeId() {
		return dischargeId;
	}

	public void setDischargeId(int dischargeId) {
		this.dischargeId = dischargeId;
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

	public Integer getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(Integer admissionId) {
		this.admissionId = admissionId;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getDischargeTypeId() {
		return dischargeTypeId;
	}

	public void setDischargeTypeId(Integer dischargeTypeId) {
		this.dischargeTypeId = dischargeTypeId;
	}

	public Integer getReasonId() {
		return reasonId;
	}

	public void setReasonId(Integer reasonId) {
		this.reasonId = reasonId;
	}

	public Integer getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(Integer destinationId) {
		this.destinationId = destinationId;
	}

	public Date getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public Date getDischargeTime() {
		return dischargeTime;
	}

	public void setDischargeTime(Date dischargeTime) {
		this.dischargeTime = dischargeTime;
	}

	public char getIsDeath() {
		return isDeath;
	}

	public void setIsDeath(char isDeath) {
		this.isDeath = (isDeath == '\u0000') ? 'N' : isDeath;
	}

	public Integer getDischargeStatusId() {
		return dischargeStatusId;
	}

	public void setDischargeStatusId(Integer dischargeStatusId) {
		this.dischargeStatusId = dischargeStatusId;
	}

	public String getRemarkDischarge() {
		return remarkDischarge;
	}

	public void setRemarkDischarge(String remarkDischarge) {
		this.remarkDischarge = remarkDischarge;
	}

	public Integer getCancelReasonId() {
		return cancelReasonId;
	}

	public void setCancelReasonId(Integer cancelReasonId) {
		this.cancelReasonId = cancelReasonId;
	}

	public String getNoteCancel() {
		return noteCancel;
	}

	public void setNoteCancel(String noteCancel) {
		this.noteCancel = noteCancel;
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

}
