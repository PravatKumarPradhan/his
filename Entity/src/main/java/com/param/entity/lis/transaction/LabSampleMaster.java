package com.param.entity.lis.transaction;

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

import com.param.global.model.OrderMaster;
import com.param.global.model.VisitTypeMaster;
import com.param.entity.lis.common.LocalTimeConverter;

@NamedNativeQueries({
	
	/*@NamedNativeQuery(name = "GET_LAB_ORDERS", query = " SELECT "
			+"	DISTINCT ord_mst.order_id AS \"orderId\", "
			+"  ord_mst.order_status_id AS \"orderStatusId\", "
			+"	ord_mst.org_id AS \"orgId\", "
			+"	ord_mst.org_unit_id AS \"orgUnitId\", "
			+"	ord_mst.visit_type_id AS \"visitTypeId\", "
			+"	ord_mst.ord_doctor_id AS \"doctorId\", "
			+"	ord_mst.patient_id AS \"patientId\", "
			+"	ord_mst.visit_adm_id AS \"visitAdmId\", "
			+"	ord_mst.created_by AS \"createdBy\", "
			+"	ord_mst.created_date AS \"createdDate\", "
			+"	pati_mst.uhid_number AS \"uhid\", "
			+"	pre_mst.prefix_code || ' ' || pati_mst.first_name || ' ' || pati_mst.last_name || ' ' || EXTRACT( "
			+"		YEAR "
			+"	FROM "
			+"		age( "
			+"			NOW(), "
			+"			pati_mst.dob "
			+"		) "
			+"	)|| '/' || gen_mst.gender_code AS \"patientDetails\", "
			+"	'Dr.' || doct_mst.first_name || ' ' || doct_mst.last_name AS \"doctorDetails\", "
			+"	pri_mst.priority_id AS \"priorityId\", "
			+"	pri_mst.priority_desc AS \"priorityName\", "
			+"	pri_mst.color_code AS \"colorCode\" "
			+"  FROM "
			+"	public.t_order_master ord_mst "
			+"  INNER JOIN public.t_order_details ord_details ON "
			+"	ord_details.order_id = ord_mst.order_id "
			+"  INNER JOIN patient.t_patient_registration pati_mst ON "
			+"	ord_mst.patient_id = pati_mst.patient_id "
			+"  INNER JOIN m_gender_master gen_mst ON "
			+"	gen_mst.gender_id = pati_mst.gender_id "
			+"  INNER JOIN m_prefix_master pre_mst ON "
			+"	pre_mst.prefix_id = pati_mst.prefix_id "
			+"  INNER JOIN doctor.m_doctor_master doct_mst ON "
			+"	ord_mst.ord_doctor_id = doct_mst.doctor_id "
			+"  INNER JOIN adt.m_priority_master pri_mst ON "
			+"	ord_mst.priority_id = pri_mst.priority_id "
			+"  WHERE "
			+"	ord_mst.org_id =:orgId "
			+"	AND ord_mst.org_unit_id =:orgUnitId "
			+"	AND ord_mst.visit_type_id =:visitTypeId "
			+"	AND ord_mst.order_status_id NOT IN (:orderStatusId) "
			+"	AND ord_details.service_rendered =:serviceRendered "
			+"	AND ord_details.service_isbilled =:serviceIsBilled "
			+"	AND ord_details.service_rendering_deptid =:serviceRenderingDeptId "
			+"  ORDER BY "
			+"	\"priorityId\" "),*/
	
	
	@NamedNativeQuery(name = "GET_LAB_ORDERS", query =   " SELECT   "
	    +" DISTINCT ord_mst.order_id AS \"orderId\",   "
	    +" ord_mst.org_id AS \"orgId\",   "
	    +" ord_mst.org_unit_id AS \"orgUnitId\",   "
	    +" ord_mst.order_status_id AS \"orderStatusId\",   "
	    +" ord_mst.visit_type_id AS \"visitTypeId\",   "
	    +" visit_type_mst.visit_type_code AS \"visitType\",   "
	    +" ord_mst.ord_doctor_id AS \"doctorId\",   "
	    +" ord_mst.patient_id AS \"patientId\",   "
	    +" ord_mst.admission_id AS \"visitAdmId\",   "
	    +" ord_mst.created_by AS \"createdBy\",   "
	    +" ord_mst.created_date AS \"createdDate\",   "
	    +" pati_mst.uhid_number AS uhid,   "
	    +" pre_mst.prefix_code || ' ' || pati_mst.first_name || ' ' || pati_mst.last_name || ' ' || EXTRACT(   "
	    +"     YEAR   "
	    +" FROM   "
	    +"     age(   "
	    +"         NOW(),   "
	    +"         pati_mst.dob   "
	    +"     )   "
	    +" )|| '/' || gen_mst.gender_code AS \"patientDetails\",   "
	    +" gen_mst.gender_id AS \"genderId\",   "
	    +" 'Dr.' || doct_mst.first_name || ' ' || doct_mst.last_name||' ('||speciality_master.speciality_name||')' AS \"doctorDetails\",   "
	    +" pri_mst.priority_id AS \"priorityId\",   "
	    +" pri_mst.priority_name AS \"priorityName\",   "
	    +" pri_mst.color_code AS \"colorCode\",   "
	    +" patient_arr_mppr.collection_time AS \"collectionTime\"   "
	    +"FROM   "
	    +" public.t_order_master ord_mst   "
	    +"INNER JOIN patient.t_patient_registration pati_mst ON   "
	    +" ord_mst.patient_id = pati_mst.patient_id   "
	    +"INNER JOIN public.m_gender_master gen_mst ON   "
	    +" gen_mst.gender_id = pati_mst.gender_id   "
	    +"INNER JOIN public.m_prefix_master pre_mst ON   "
	    +" pre_mst.prefix_id = pati_mst.prefix_id   "
	    +"INNER JOIN doctor.m_doctor_mASter doct_mst ON   "
	    +" ord_mst.ord_doctor_id = doct_mst.doctor_id   "
	    +"INNER JOIN lab.m_priority_master pri_mst ON   "
	    +" ord_mst.priority_id = pri_mst.priority_id   "
	    +"INNER JOIN doctor.t_doctor_speciality_mapper doct_speciality_mppr ON   "
	    +" doct_speciality_mppr.doctor_id=doct_mst.doctor_id  "
	    +"INNER JOIN doctor.m_speciality_master speciality_master ON   "
	    +" speciality_master.speciality_id=doct_speciality_mppr.speciality_id    "
	    +"INNER JOIN public.m_visit_type_master visit_type_mst ON   "
        +" visit_type_mst.visit_type_id = ord_mst.visit_type_id   "
	    +"LEFT JOIN lab.t_patient_arrival_mpper patient_arr_mppr ON   "
	    +" patient_arr_mppr.patient_id = ord_mst.patient_id   "
	    +"WHERE   "
	    +" ord_mst.org_id =:orgId   "
	    +" AND ord_mst.org_unit_id =:orgUnitId   "
	    +" AND ord_mst.order_status = 'A'   "
	    +" AND ord_mst.visit_type_id =:visitTypeId   "
	    +" AND patient_arr_mppr.created_date =(   "
	    +"     SELECT   "
	    +"         MAX(patiarrmppr.created_date)   "
	    +"     FROM   "
	    +"         lab.t_patient_arrival_mpper patiarrmppr   "
	    +"     WHERE   "
	    +"         patiarrmppr.order_id = ord_mst.order_id   "
	    +"         AND patiarrmppr.patient_id = ord_mst.patient_id   "
	    +"         AND patiarrmppr.status_id =:patientStatusId   "
	    +" )   "
	    +" AND patient_arr_mppr.status_id =:patientStatusId   "
	    +" AND doct_speciality_mppr.status = 'A'  "
	    +"UNION SELECT   "
	    +" DISTINCT(ord_mst.order_id) AS \"orderId\",   "
	    +" ord_mst.org_id AS \"orgId\",   "
	    +" ord_mst.org_unit_id AS \"orgUnitId\",   "
	    +" ord_mst.order_status_id AS \"orderStatusId\",   "
	    +" ord_mst.visit_type_id AS \"visitTypeId\",   "
	    +" visit_type_mst.visit_type_code AS \"visitType\",   "
	    +" ord_mst.ord_doctor_id AS \"doctorId\",   "
	    +" ord_mst.patient_id AS \"patientId\",   "
	    +" ord_mst.admission_id AS \"visitAdmId\",   "
	    +" ord_mst.created_by AS \"createdBy\",   "
	    +" ord_mst.created_date AS \"createdDate\",   "
	    +" pati_mst.uhid_number AS uhid,   "
	    +" pre_mst.prefix_code || ' ' || pati_mst.first_name || ' ' || pati_mst.last_name || ' ' || EXTRACT(   "
	    +"     YEAR   "
	    +" FROM   "
	    +"     age(   "
	    +"         NOW(),   "
	    +"         pati_mst.dob   "
	    +"     )   "
	    +" )|| '/' || gen_mst.gender_code AS \"patientDetails\",   "
	    +" gen_mst.gender_id AS \"genderId\",   "
	    +" 'Dr.' || doct_mst.first_name || ' ' || doct_mst.last_name||' ('||speciality_master.speciality_name||')' AS \"doctorDetails\",    "
	    +" pri_mst.priority_id AS \"priorityId\",   "
	    +" pri_mst.priority_name AS \"priorityName\",   "
	    +" pri_mst.color_code AS \"colorCode\",   "
	    +" CAST(NULL AS DATE) AS \"collectionTime\"   "
	    +"FROM   "
	    +" public.t_order_master ord_mst   "
	    +"INNER JOIN public.t_order_details ord_details ON   "
	    +" ord_details.order_id = ord_mst.order_id   "
	    +"INNER JOIN patient.t_patient_registration pati_mst ON   "
	    +" ord_mst.patient_id = pati_mst.patient_id   "
	    +"INNER JOIN public.m_gender_master gen_mst ON   "
	    +" gen_mst.gender_id = pati_mst.gender_id   "
	    +"INNER JOIN public.m_prefix_master pre_mst ON   "
	    +" pre_mst.prefix_id = pati_mst.prefix_id   "
	    +"INNER JOIN doctor.m_doctor_mASter doct_mst ON   "
	    +" ord_mst.ord_doctor_id = doct_mst.doctor_id   "
	    +"INNER JOIN lab.m_priority_mASter pri_mst ON   "
	    +" ord_mst.priority_id = pri_mst.priority_id  "
	    +"INNER JOIN doctor.t_doctor_speciality_mapper doct_speciality_mppr ON   "
	    +" doct_speciality_mppr.doctor_id=doct_mst.doctor_id  "
	    +"INNER JOIN doctor.m_speciality_master speciality_master ON   "
	    +" speciality_master.speciality_id=doct_speciality_mppr.speciality_id    "
	    +"INNER JOIN public.m_visit_type_master visit_type_mst ON   "
        +" visit_type_mst.visit_type_id = ord_mst.visit_type_id   "
	    +"LEFT JOIN lab.t_patient_arrival_mpper patient_arr_mppr ON   "
	    +" patient_arr_mppr.patient_id = ord_mst.patient_id   "
	    +"WHERE   "
	    +" ord_mst.org_id =:orgId   "
	    +" AND ord_mst.org_unit_id =:orgUnitId   "
	    +" AND ord_mst.order_status = 'A'   "
	    +" AND ord_mst.visit_type_id =:visitTypeId   "
	    +" AND ord_mst.order_status_id NOT IN(:orderStatusId)   "
	    +" AND ord_details.service_rendered =:serviceRendered   "
	    +" AND ord_details.service_is_billed =:serviceIsBilled   "
	    +" AND ord_details.ord_cancelled ='N'   "
	    +" AND ord_details.is_outsourced ='N'   "
	    +" AND ord_details.service_rendering_deptid IN (:serviceRenderingDeptIds)   "
	    +" AND doct_speciality_mppr.status = 'A'  "
	    +" AND ord_mst.order_id NOT IN(   "
	    +"     SELECT   "
	    +"         DISTINCT patient_arr_mppr.order_id   "
	    +"     FROM   "
	    +"         lab.t_patient_arrival_mpper patient_arr_mppr   "
	    +"     WHERE   "
	    +"         patient_arr_mppr.org_id =:orgId   "
	    +"         AND patient_arr_mppr.org_unit_id =:orgUnitId   "
	    +"         AND patient_arr_mppr.status_id =:patientStatusId   "
	    +"         AND patient_arr_mppr.pending_count > 0   "
	    +" )   "
	    +"GROUP BY   "
	    +" ord_mst.order_id,   "
	    +" pati_mst.patient_id,   "
	    +" pre_mst.prefix_id,   "
	    +" gen_mst.gender_id,   "  
	    +" doct_mst.doctor_id,   "
	    +" pri_mst.priority_id,   "
	    +" speciality_master.speciality_id,  "
	    +" visit_type_mst.visit_type_id,  "
	    +" patient_arr_mppr.collection_time   "
	    +"ORDER BY   "
	    +" \"priorityId\" ,\"createdDate\"  "),
	
	
	
	
	
	@NamedNativeQuery(name = "GET_LAB_DASHBOARD_LIST", query ="select"
			+ " 	visit_type_master.visit_type_code as \"visitType\",  "
			+ " 	lbsampledtls.sample_no AS \"sampleNo\",  "
			+ " 	panel_master.panel_code AS \"panelCode\",  "
			+ " 	lbsampledtls.lab_sample_dtls_id as  \"labSampleDtlsId\",  "
			+ " 	pati_mst.uhid_number as uhid,  "
			+ " 	("
			+ " 		pre_mst.prefix_code || ' ' ||pati_mst.first_name || ' ' ||pati_mst.last_name"
			+ " 	) as \"patientDetails\", "
			+ " 	("
			+ " 		'Dr.' || ' ' || doct_mst.first_name || ' ' || doct_mst.last_name"
			+ " 	) as \"doctorDetails\",  "
			+ " 	subspe_mst.sub_speciality_name as \"subSpecialityName\","
			+ " 	test_mst.test_desc as \"testDesc\",  "
			+ " 	speciman_mst.speciman_name as \"specimanName\","
			+ " 	test_status_master.status_name as \"status\","
			+ " 	test_mst.test_code as \"testCode\",  "
			+ " 	order_details.created_date as \"orderDateTime\",  "
			+ " 	lbsampledtls.sample_collection_datetime as \"sampleCollectionDatetime\","
			+ " 	samp_mst.sample_desc as \"sampleType\","
			+ " 	pri_mst.priority_name  as \"priority\""
			+ " from"
			+ " 	public.t_order_details order_details"
			+ " right outer join public.t_order_master order_master on"
			+ " 	order_details.order_id = order_master.order_id"
			+ " right outer join patient.t_patient_registration pati_mst  on"
			+ " 	order_master.patient_id =pati_mst.patient_id"
			+ " right outer join public.m_prefix_master pre_mst on"
			+ " 	pati_mst.prefix_id = pre_mst.prefix_id"
			+ " right outer join public.m_gender_master gen_mst on"
			+ " 	pati_mst.gender_id = gen_mst.gender_id"
			+ " right outer join doctor.m_doctor_master doct_mst on"
			+ " 	order_master.ord_doctor_id = doct_mst.doctor_id"
			+ " right outer join public.m_visit_type_master visit_type_master on"
			+ " 	order_master.visit_type_id = visit_type_master.visit_type_id"
			+ " right outer join lab.t_lab_sample_dtls lbsampledtls on"
			+ " 	order_details.order_details_id = lbsampledtls.order_details_id"
			+ " right outer join lab.m_test_status_master test_status_master on"
			+ " 	lbsampledtls.sample_status_id = test_status_master.test_status_id"
			+ " right outer join service.m_service_master service_mst on"
			+ " 	order_details.service_id = service_mst.service_master_id"
			+ " right outer join doctor.m_sub_speciality_master subspe_mst on"
			+ " 	service_mst.sub_speciality_id = subspe_mst.sub_speciality_id"
			+ " right outer join lab.m_test_master test_mst on"
			+ " 	service_mst.service_master_id = test_mst.service_id"
			+ " right outer join lab.m_sample_master samp_mst ON samp_mst.sample_id = test_mst.sample_id "
			+ " right outer join lab.m_speciman_master speciman_mst on"
			+ " 	test_mst.speciman_id = speciman_mst.speciman_id"
			+ " right outer join lab.m_priority_master pri_mst ON lbsampledtls.priority_id = pri_mst.priority_id  "
			+ "left outer join lab.t_panel_master panel_master ON panel_master.service_id = order_details.service_id "
			+ " where"
			+ " 	order_details.org_id =:orgId"
			+ " 	and order_details.org_unit_id =:orgUnitId"
			+ " 	and order_details.ord_cancelled = 'N'"
			+ " 	and order_details.status = 'A'"
			+ " 	and ("
			+ " 		order_details.service_is_billed in ("
			+ " 			0 ,"
			+ " 			1"
			+ " 		)"
			+ " 	)"
			+ " 	and order_details.service_rendering_deptid IN (:serviceRenderingDeptIds)"
),
		
	
	@NamedNativeQuery(name = "GET_LAB_DASHBOARD_LIST_COUNT", query ="SELECT"
			+ " COUNT (*)"
			+ " FROM"
			+ " 	public.t_order_details order_details"
			+ " right outer join public.t_order_master order_master on"
			+ " 	order_details.order_id = order_master.order_id"
			+ " right outer join patient.t_patient_registration pati_mst  on"
			+ " 	order_master.patient_id =pati_mst.patient_id"
			+ " right outer join public.m_prefix_master pre_mst on"
			+ " 	pati_mst.prefix_id = pre_mst.prefix_id"
			+ " right outer join public.m_gender_master gen_mst on"
			+ " 	pati_mst.gender_id = gen_mst.gender_id"
			+ " right outer join doctor.m_doctor_master doct_mst on"
			+ " 	order_master.ord_doctor_id = doct_mst.doctor_id"
			+ " right outer join public.m_visit_type_master visit_type_master on"
			+ " 	order_master.visit_type_id = visit_type_master.visit_type_id"
			+ " right outer join lab.t_lab_sample_dtls lbsampledtls on"
			+ " 	order_details.order_details_id = lbsampledtls.order_details_id"
			+ " right outer join lab.m_test_status_master test_status_master on"
			+ " 	lbsampledtls.sample_status_id = test_status_master.test_status_id"
			+ " right outer join service.m_service_master service_mst on"
			+ " 	order_details.service_id = service_mst.service_master_id"
			+ " right outer join doctor.m_sub_speciality_master subspe_mst on"
			+ " 	service_mst.sub_speciality_id = subspe_mst.sub_speciality_id"
			+ " right outer join lab.m_test_master test_mst on"
			+ " 	service_mst.service_master_id = test_mst.service_id"
			+ " right outer join lab.m_sample_master samp_mst ON samp_mst.sample_id = test_mst.sample_id "
			+ " right outer join lab.m_speciman_master speciman_mst on"
			+ " 	test_mst.speciman_id = speciman_mst.speciman_id"
			+ " right outer join lab.m_priority_master pri_mst ON lbsampledtls.priority_id = pri_mst.priority_id  "
			+ " "
			+ " where"
			+ " 	order_details.org_id =:orgId"
			+ " 	and order_details.org_unit_id =:orgUnitId"
			+ " 	and order_details.ord_cancelled = 'N'"
			+ " 	and order_details.status = 'A'"
			+ " 	and ("
			+ " 		order_details.service_is_billed in ("
			+ " 			0 ,"
			+ " 			1"
			+ " 		)"
			+ " 	)"
			+ " 	and order_details.service_rendering_deptid IN (:serviceRenderingDeptIds)"
),
	
	
	
	/*@NamedNativeQuery(name = "GET_LAB_ORDER_DETAILS", query = "   SELECT "
			+"	ord_dtls.order_details_id AS \"orderDetailsId\", "
			+"	ord_dtls.org_id AS \"orgId\", "
			+"	ord_dtls.org_unit_id AS \"orgUnitId\", "
			+"	ord_dtls.order_id AS \"orderId\", "
			+"	ord_dtls.order_qty AS \"orderQty\", "
			+"	ord_dtls.service_id AS \"serviceId\", "
			+"	ord_dtls.is_outsourced AS \"isOutsourced\", "
			+"	ord_dtls.package_id AS \"packageId\", "
			+"	ord_dtls.created_by AS \"createdBy\", "
			+"	ord_dtls.created_date AS \"createdDate\", "
			+"	test_mst.test_id AS \"testId\", "
			+"	test_mst.test_code AS \"testCode\", "
			+"	test_mst.test_desc AS \"testDesc\", "
			+"	test_mst.container_id AS \"containerId\", "
			+"	test_mst.no_of_sample_req AS \"sampleReqCount\", "
			+"	contain_mst.container_name AS \"containerName\", "
			+"	samp_mst.sample_desc AS \"sampleName\", "
			+"	samp_mst.sample_id AS \"sampleTypeId\", "
			+"	test_mst.sample_volume AS \"sampleVolume\", "
			+"	unit_mst.unit_name AS \"unitName\", "
			+"	subspe_mst.sub_speciality_name AS \"deptName\", "
			+"	subspe_mst.speciality_id AS \"deptId\", "
			+"	subspe_mst.sub_speciality_id AS \"subDeptId\", "
			+"	patient_arr_mppr.collection_time AS \"collectionTime\" "
			+"FROM "
			+"	public.t_order_details ord_dtls "
			+"INNER JOIN lab.m_test_master test_mst ON "
			+"	ord_dtls.service_id = test_mst.service_id "
			+"INNER JOIN lab.m_sample_master samp_mst ON "
			+"	samp_mst.sample_id = test_mst.sample_id "
			+"INNER JOIN lab.m_unit_master unit_mst ON "
			+"	unit_mst.unit_id = test_mst.unit_id "
			+"INNER JOIN doctor.m_sub_speciality_master subspe_mst ON "
			+"	test_mst.dept_id = subspe_mst.sub_speciality_id "
			+"INNER JOIN lab.m_container_master contain_mst ON "
			+"	contain_mst.container_id = test_mst.container_id "
			+"LEFT JOIN lab.t_patient_arrival_mpper patient_arr_mppr ON "
			+"	patient_arr_mppr.order_details_id = ord_dtls.order_details_id "
			+"WHERE "
			+"	ord_dtls.org_id =:orgId "
			+"	AND ord_dtls.org_unit_id =:orgUnitId "
			+"	AND ord_dtls.status = 'A' "
			+"	AND ord_dtls.order_id =:orderId "
			+"	AND ord_dtls.service_rendered =:serviceRendered "
			+"	AND ord_dtls.service_is_billed =:serviceIsBilled "
			+"	AND ord_dtls.service_rendering_deptid =:serviceRenderingDeptId "
			+"	AND ord_dtls.ord_cancelled =:ordCancelled "
			+"	AND ord_dtls.is_outsourced ='N' "
			+"	AND samp_mst.sample_status =:sampleStatus "
			+"	AND test_mst.is_outsourced =:isOutsourced "
			+"	AND unit_mst.unit_status =:unitStatus "
			+"	AND ord_dtls.order_details_id NOT IN( "
			+"		SELECT "
			+"			samp_dtls.order_details_id "
			+"		FROM "
			+"			lab.t_lab_sample_dtls samp_dtls "
			+"		WHERE "
			+"			samp_dtls.order_id =:orderId "
			+"			AND samp_dtls.sample_status_id <>:sampleStatusId "
			+"	) "
			+"UNION SELECT "
			+"	ord_dtls.order_details_id AS \"orderDetailsId\", "
			+"	ord_dtls.org_id AS \"orgId\", "
			+"	ord_dtls.org_unit_id AS \"orgUnitId\", "
			+"	ord_dtls.order_id AS \"orderId\", "
			+"	ord_dtls.order_qty AS \"orderQty\", "
			+"	ord_dtls.service_id AS \"serviceId\", "
			+"	ord_dtls.is_outsourced AS \"isOutsourced\", "
			+"	ord_dtls.package_id AS \"packageId\", "
			+"	ord_dtls.created_by AS \"createdBy\", "
			+"	ord_dtls.created_date AS \"createdDate\", "
			+"	test_mst.test_id AS \"testId\", "
			+"	test_mst.test_code AS \"testCode\", "
			+"	test_mst.test_desc AS \"testDesc\", "
			+"	test_mst.container_id AS \"containerId\", "
			+"	test_mst.no_of_sample_req AS \"sampleReqCount\", "
			+"	contain_mst.container_name AS \"containerName\", "
			+"	samp_mst.sample_desc AS \"sampleName\", "
			+"	samp_mst.sample_id AS \"sampleTypeId\", "
			+"	test_mst.sample_volume AS \"sampleVolume\", "
			+"	unit_mst.unit_name AS \"unitName\", "
			+"	subspe_mst.sub_speciality_name AS \"deptName\", "
			+"	subspe_mst.speciality_id AS \"deptId\", "
			+"	subspe_mst.sub_speciality_id AS \"subDeptId\", "
			+"	CAST(NULL AS DATE) AS \"collectionTime\" "
			+"FROM "
			+"	public.t_order_details ord_dtls "
			+"INNER JOIN lab.t_lab_sample_dtls smp_dtls ON "
			+"	smp_dtls.order_details_id = ord_dtls.order_details_id "
			+"INNER JOIN lab.m_test_master test_mst ON "
			+"	ord_dtls.service_id = test_mst.service_id "
			+"INNER JOIN lab.m_sample_master samp_mst ON "
			+"	samp_mst.sample_id = test_mst.sample_id "
			+"INNER JOIN lab.m_unit_master unit_mst ON "
			+"	unit_mst.unit_id = test_mst.unit_id "
			+"INNER JOIN doctor.m_sub_speciality_master subspe_mst ON "
			+"	test_mst.dept_id = subspe_mst.sub_speciality_id "
			+"INNER JOIN lab.m_container_master contain_mst ON "
			+"	contain_mst.container_id = test_mst.container_id "
			+"LEFT JOIN lab.t_patient_arrival_mpper patient_arr_mppr ON "
			+"	patient_arr_mppr.lab_smp_dtls_id = smp_dtls.lab_sample_dtls_id "
			+"WHERE "
			+"	ord_dtls.org_id =:orgId "
			+"	AND ord_dtls.org_unit_id =:orgUnitId "
			+"	AND ord_dtls.status = 'A' "
			+"	AND ord_dtls.order_id =:orderId "
			+"	AND ord_dtls.service_is_billed =:serviceIsBilled "
			+"	AND ord_dtls.service_rendering_deptid =:serviceRenderingDeptId "
			+"	AND ord_dtls.ord_cancelled =:ordCancelled "
			+"	AND ord_dtls.is_outsourced ='N' "
			+"	AND samp_mst.sample_status =:sampleStatus "
			+"	AND unit_mst.unit_status =:unitStatus "
			+"	AND test_mst.is_outsourced =:isOutsourced "
			+"	AND patient_arr_mppr.created_date =( "
			+"		SELECT "
			+"			MAX( patiarrmppr.created_date ) "
			+"		FROM "
			+"			lab.t_patient_arrival_mpper patiarrmppr "
			+"		WHERE "
			+"			patiarrmppr.order_id =:orderId "
			+"			AND patiarrmppr.patient_id = smp_dtls.patient_id "
			+"			AND patiarrmppr.status_id =:patientStatusId "
			+"			AND patiarrmppr.lab_smp_dtls_id = smp_dtls.lab_sample_dtls_id "
			+"			AND smp_dtls.sample_status_id <>:sampleStatusId "
			+"			AND smp_dtls.sample_pending_count > 0 "
			+"	) "),*/
	
	
	@NamedNativeQuery(name = "GET_LAB_ORDER_DETAILS", query =  " SELECT  "
	    +"  ord_dtls.order_details_id AS \"orderDetailsId\",  "
	    +"  CAST(NULL AS int) AS \"profileId\",  "
	    +"  CAST(NULL AS text) AS \"panelCode\",  "
	    +"  ord_dtls.org_id AS \"orgId\",  "
	    +"  ord_dtls.org_unit_id AS \"orgUnitId\",  "
	    +"  ord_dtls.order_id AS \"orderId\",  "
	    +"  ord_dtls.order_qty AS \"orderQty\",  "
	    +"  ord_dtls.service_id AS \"serviceId\",  "
	    +"  ord_dtls.is_outsourced AS \"isOutsourced\",  "
	    +"  ord_dtls.package_id AS \"packageId\",  "
	    +"  ord_dtls.created_by AS \"createdBy\",  "
	    +"  ord_dtls.created_date AS \"createdDate\",  "
	    +"  test_mst.test_id AS \"testId\",  "
	    +"  test_mst.test_code AS \"testCode\",  "
	    +"  test_mst.test_desc AS \"testDesc\",  "
	    +"  test_mst.container_id AS \"containerId\",  "
	    +"  test_mst.no_of_sample_req AS \"sampleReqCount\",  "
	    +"  contain_mst.container_name AS \"containerName\",  "
	    +"  samp_mst.sample_desc AS \"sampleName\",  "
	    +"  samp_mst.sample_id AS \"sampleTypeId\",  "
	    +"  test_mst.sample_volume AS \"sampleVolume\",  "
	    +"  unit_mst.unit_name AS \"unitName\",  "
	    +"  subspe_mst.sub_speciality_name AS \"deptName\",  "
	    +"  subspe_mst.speciality_id AS \"deptId\",  "
	    +"  subspe_mst.sub_speciality_id AS \"subDeptId\",  "
	    +"  CAST(NULL AS DATE) AS \"collectionTime\"  "
	    +"FROM  "
	    +"  public.t_order_details ord_dtls  "
	    +"  INNER JOIN lab.m_test_master test_mst ON ord_dtls.service_id = test_mst.service_id  "
	    +"  INNER JOIN lab.m_sample_master samp_mst ON samp_mst.sample_id = test_mst.sample_id  "
	    +"  INNER JOIN lab.m_unit_master unit_mst ON unit_mst.unit_id = test_mst.unit_id  "
	    +"  INNER JOIN doctor.m_sub_speciality_master subspe_mst ON test_mst.dept_id = subspe_mst.sub_speciality_id  "
	    +"  INNER JOIN lab.m_container_master contain_mst ON contain_mst.container_id = test_mst.container_id  "
	    +"  LEFT JOIN lab.t_patient_arrival_mpper patient_arr_mppr ON patient_arr_mppr.order_details_id = ord_dtls.order_details_id  "
	    +"WHERE  "
	    +"  ord_dtls.org_id = :orgId  "
	    +"  AND ord_dtls.org_unit_id = :orgUnitId  "
	    +"  AND ord_dtls.status = 'A'  "
	    +"  AND ord_dtls.order_id = :orderId  "
	    +"  AND ord_dtls.service_rendered = :serviceRendered  "
	    +"  AND ord_dtls.service_is_billed = :serviceIsBilled  "
	    +"  AND ord_dtls.service_rendering_deptid IN (:serviceRenderingDeptIds)  "
	    +"  AND ord_dtls.ord_cancelled = :ordCancelled  "
	    +"  AND samp_mst.sample_status = :sampleStatus  "
	    +"  AND unit_mst.unit_status = :unitStatus  "
	    +"  AND ord_dtls.order_details_id NOT IN(  "
	    +"    SELECT  "
	    +"      samp_dtls.order_details_id  "
	    +"    FROM  "
	    +"      lab.t_lab_sample_dtls samp_dtls  "
	    +"    WHERE  "
	    +"      samp_dtls.order_id = :orderId  "
	    +"      AND samp_dtls.sample_status_id <> 4  "
	    +"  )  "
	    +"UNION  "
	    +"SELECT  "
	    +"  ord_dtls.order_details_id AS \"orderDetailsId\",  "
	    +"  panel_master.panel_id AS \"profileId\",  "
	    +"  panel_master.panel_code AS \"panelCode\",  "
	    +"  ord_dtls.org_id AS \"orgId\",  "
	    +"  ord_dtls.org_unit_id AS \"orgUnitId\",  "
	    +"  ord_dtls.order_id AS \"orderId\",  "
	    +"  ord_dtls.order_qty AS \"orderQty\",  "
	    +"  ord_dtls.service_id AS \"serviceId\",  "
	    +"  ord_dtls.is_outsourced AS \"isOutsourced\",  "
	    +"  ord_dtls.package_id AS \"packageId\",  "
	    +"  ord_dtls.created_by AS \"createdBy\",  "
	    +"  ord_dtls.created_date AS \"createdDate\",  "
	    +"  test_mst.test_id AS \"testId\",  "
	    +"  test_mst.test_code AS \"testCode\",  "
	    +"  test_mst.test_desc AS \"testDesc\",  "
	    +"  test_mst.container_id AS \"containerId\",  "
	    +"  test_mst.no_of_sample_req AS \"sampleReqCount\",  "
	    +"  contain_mst.container_name AS \"containerName\",  "
	    +"  samp_mst.sample_desc AS \"sampleName\",  "
	    +"  samp_mst.sample_id AS \"sampleTypeId\",  "
	    +"  test_mst.sample_volume AS \"sampleVolume\",  "
	    +"  unit_mst.unit_name AS \"unitName\",  "
	    +"  subspe_mst.sub_speciality_name AS \"deptName\",  "
	    +"  subspe_mst.speciality_id AS \"deptId\",  "
	    +"  subspe_mst.sub_speciality_id AS \"subDeptId\",  "
	    +"  CAST(NULL AS DATE) AS \"collectionTime\"  "
	    +"FROM  "
	    +"  public.t_order_details ord_dtls  "
	    +"  LEFT JOIN lab.t_panel_master panel_master ON panel_master.service_id = ord_dtls.service_id  "
	    +"  INNER JOIN lab.t_panel_details panel_details ON panel_details.panel_id = panel_master.panel_id  "
	    +"  LEFT JOIN lab.m_test_master test_mst ON test_mst.test_id = panel_details.test_id  "
	    +"  INNER JOIN lab.m_sample_master samp_mst ON samp_mst.sample_id = test_mst.sample_id  "
	    +"  INNER JOIN lab.m_unit_master unit_mst ON unit_mst.unit_id = test_mst.unit_id  "
	    +"  INNER JOIN doctor.m_sub_speciality_master subspe_mst ON test_mst.dept_id = subspe_mst.sub_speciality_id  "
	    +"  INNER JOIN lab.m_container_master contain_mst ON contain_mst.container_id = test_mst.container_id  "
	    +"  LEFT JOIN lab.t_patient_arrival_mpper patient_arr_mppr ON patient_arr_mppr.order_details_id = ord_dtls.order_details_id  "
	    +"WHERE  "
	    +"  ord_dtls.org_id =:orgId  "
	    +"  AND ord_dtls.org_unit_id =:orgUnitId  "
	    +"  AND ord_dtls.status = 'A'  "
	    +"  AND ord_dtls.order_id =:orderId  "
	    +"  AND ord_dtls.service_rendered =:serviceRendered  "
	    +"  AND ord_dtls.service_is_billed =:serviceIsBilled  "
	    +"  AND ord_dtls.service_rendering_deptid IN (:serviceRenderingDeptIds)  "
	    +"  AND ord_dtls.ord_cancelled =:ordCancelled  "
	    +"  AND samp_mst.sample_status =:sampleStatus  "
	    +"  AND unit_mst.unit_status =:unitStatus  "
	    +"  AND panel_master.status = 'A'  "
	    +"  AND ord_dtls.order_details_id NOT IN(  "
	    +"    SELECT  "
	    +"      samp_dtls.order_details_id  "
	    +"    FROM  "
	    +"      lab.t_lab_sample_dtls samp_dtls  "
	    +"    WHERE  "
	    +"      samp_dtls.order_id =:orderId  "
	    +"      AND samp_dtls.sample_status_id <>:sampleStatusId  "
	    +"  )  "
	    +"UNION  "
	    +"SELECT  "
	    +"  ord_dtls.order_details_id AS \"orderDetailsId\",  "
	    +"  panel_master.panel_id AS \"profileId\",  "
	    +"  panel_master.panel_code AS \"panelCode\",  "
	    +"  ord_dtls.org_id AS \"orgId\",  "
	    +"  ord_dtls.org_unit_id AS \"orgUnitId\",  "
	    +"  ord_dtls.order_id AS \"orderId\",  "
	    +"  ord_dtls.order_qty AS \"orderQty\",  "
	    +"  ord_dtls.service_id AS \"serviceId\",  "
	    +"  ord_dtls.is_outsourced AS \"isOutsourced\",  "
	    +"  ord_dtls.package_id AS \"packageId\",  "
	    +"  ord_dtls.created_by AS \"createdBy\",  "
	    +"  ord_dtls.created_date AS \"createdDate\",  "
	    +"  test_mst.test_id AS \"testId\",  "
	    +"  test_mst.test_code AS \"testCode\",  "
	    +"  test_mst.test_desc AS \"testDesc\",  "
	    +"  test_mst.container_id AS \"containerId\",  "
	    +"  test_mst.no_of_sample_req AS \"sampleReqCount\",  "
	    +"  contain_mst.container_name AS \"containerName\",  "
	    +"  samp_mst.sample_desc AS \"sampleName\",  "
	    +"  samp_mst.sample_id AS \"sampleTypeId\",  "
	    +"  test_mst.sample_volume AS \"sampleVolume\",  "
	    +"  unit_mst.unit_name AS \"unitName\",  "
	    +"  subspe_mst.sub_speciality_name AS \"deptName\",  "
	    +"  subspe_mst.speciality_id AS \"deptId\",  "
	    +"  subspe_mst.sub_speciality_id AS \"subDeptId\",  "
	    +"  patient_arr_mppr.collection_time AS \"collectionTime\"  "
	    +"FROM  "
	    +"  public.t_order_details ord_dtls  "
	    +"  INNER JOIN lab.t_lab_sample_dtls smp_dtls ON smp_dtls.order_details_id = ord_dtls.order_details_id  "
	    +"  INNER JOIN lab.m_test_master test_mst ON ord_dtls.service_id = test_mst.service_id  "
	    +"  INNER JOIN lab.m_sample_master samp_mst ON samp_mst.sample_id = test_mst.sample_id  "
	    +"  INNER JOIN lab.m_unit_master unit_mst ON unit_mst.unit_id = test_mst.unit_id  "
	    +"  INNER JOIN doctor.m_sub_speciality_master subspe_mst ON test_mst.dept_id = subspe_mst.sub_speciality_id  "
	    +"  INNER JOIN lab.m_container_master contain_mst ON contain_mst.container_id = test_mst.container_id  "
	    +"  LEFT JOIN lab.t_panel_master panel_master ON panel_master.service_id = ord_dtls.service_id  "
	    +"  INNER JOIN lab.t_panel_details panel_details ON panel_details.test_id = test_mst.test_id  "
	    +"  LEFT JOIN lab.t_patient_arrival_mpper patient_arr_mppr ON patient_arr_mppr.lab_smp_dtls_id = smp_dtls.lab_sample_dtls_id  "
	    +"WHERE  "
	    +"  ord_dtls.org_id =:orgId  "
	    +"  AND ord_dtls.org_unit_id =:orgUnitId  "
	    +"  AND ord_dtls.status = 'A'  "
	    +"  AND ord_dtls.order_id =:orderId  "
	    +"  AND ord_dtls.service_is_billed =:serviceIsBilled  "
	    +"  AND ord_dtls.service_rendering_deptid IN (:serviceRenderingDeptIds)  "
	    +"  AND ord_dtls.ord_cancelled =:ordCancelled  "
	    +"  AND samp_mst.sample_status =:sampleStatus  "
	    +"  AND unit_mst.unit_status =:unitStatus  "
	    +"  AND patient_arr_mppr.created_date =(  "
	    +"    SELECT  "
	    +"      MAX(patiarrmppr.created_date)  "
	    +"    FROM  "
	    +"      lab.t_patient_arrival_mpper patiarrmppr  "
	    +"    WHERE  "
	    +"      patiarrmppr.order_id =:orderId  "
	    +"      AND patiarrmppr.patient_id = smp_dtls.patient_id  "
	    +"      AND patiarrmppr.status_id =:patientStatusId  "
	    +"      AND patiarrmppr.lab_smp_dtls_id =smp_dtls.lab_sample_dtls_id  "
	    +"      AND smp_dtls.sample_status_id <>:sampleStatusId  "
	    +"      AND smp_dtls.sample_pending_count > 0  "
	    +"  )  "
),
	
	
	
	
	
	
	
	@NamedNativeQuery(name = "GET_LAB_SAMPLE_ID", query ="SELECT labsamplemst.lab_sample_id FROM lab.t_lab_sample_master labsamplemst"
			+ " WHERE  labsamplemst.org_id = :orgId"
			+ " AND labsamplemst.org_unit_id =  :orgUnitId"
			+ " AND labsamplemst.order_id = :orderId"),
	
	
	

	@NamedNativeQuery(name = "REPEAT_SAMPLE_PATIENT_ARRIVAL_COUNT",
    query =  " SELECT "
    		+"	COUNT(*) "
    		+"FROM "
    		+"	public.t_order_master ord_mst "
    		+"LEFT JOIN lab.t_patient_arrival_mpper patient_arr_mppr ON "
    		+"	patient_arr_mppr.order_id = ord_mst.order_id "
    		+"WHERE "
    		+"	ord_mst.org_id =:orgId "
    		+"	AND ord_mst.org_unit_id =:orgUnitId "
    		+"	AND ord_mst.order_status_id <>:orderCancelStatus "
    		+"	AND ord_mst.visit_type_id =:visitTypeId "
    		+"	AND ord_mst.order_status = 'A' "
    		+"	AND ord_mst.order_status = 'A' "
    		+"	AND patient_arr_mppr.created_date =( "
    		+"		SELECT "
    		+"			MAX( patiarrmppr.created_date ) "
    		+"		FROM "
    		+"			lab.t_patient_arrival_mpper patiarrmppr "
    		+"		WHERE "
    		+"			patiarrmppr.order_id = patiarrmppr.order_id "
    		+"			AND patiarrmppr.patient_id = patiarrmppr.patient_id "
    		+"			AND patiarrmppr.status_id =:patientStatusId "
    		+"	) "
    		+"	AND patient_arr_mppr.status_id =:patientStatusId "
    		+"GROUP BY "
    		+"	ord_mst.order_id "
 ),
	
	
	
	@NamedNativeQuery(name = "REGULAR_PATIENT_ORDER_COUNT",
    query =  " WITH oder_count AS( "
    		+"	SELECT "
    		+"		DISTINCT ord_mst.order_id "
    		+"	FROM "
    		+"		public.t_order_master ord_mst "
    		+"	INNER JOIN public.t_order_details ord_details ON "
    		+"		ord_details.order_id = ord_mst.order_id "
    		+"	WHERE "
    		+"		ord_mst.org_id = :orgId "
    		+"		AND ord_mst.org_unit_id = :orgUnitId "
    		+"		AND ord_mst.visit_type_id = :visitTypeId "
    		+"		AND ord_mst.order_status_id NOT IN(:orderStatusId) "
    		+"		AND ord_details.service_rendering_deptid IN (:serviceRenderingDeptIds)"
    		+"		AND ord_details.service_rendered = 0 "
    		+"		AND ord_details.service_is_billed = 1 "
    		+"		AND ord_details.is_outsourced = 'N' "
    		+"		AND ord_details.ord_cancelled = 'N' "
    		+"		AND ord_mst.order_status = 'A' "
    		+"		AND ord_mst.order_id NOT IN( "
    		+"			SELECT "
    		+"				DISTINCT patient_arr_mppr.order_id "
    		+"			FROM "
    		+"				lab.t_patient_arrival_mpper patient_arr_mppr "
    		+"			WHERE "
    		+"				patient_arr_mppr.org_id = :orgId "
    		+"				AND patient_arr_mppr.org_unit_id = :orgUnitId "
    		+"				AND patient_arr_mppr.status_id = :patientStatusId "
    		+"				AND patient_arr_mppr.pending_count > 0 "
    		+"		) "
    		+"	GROUP BY "
    		+"		ord_mst.order_id "
    		+") SELECT "
    		+"	COUNT(*) "
    		+"FROM "
    		+"	oder_count " ),
	

	
	@NamedNativeQuery(name = "V1_GET_PHLEBOTOMY_WORKLIST_DETAILS", query =  " SELECT  "
			 +"	ord_dtls.order_details_id AS \"orderDetailsId\",  "
			 +"	ord_dtls.org_id AS \"orgId\",  "
			 +"	ord_dtls.org_unit_id AS \"orgUnitId\",  "
			 +"	ord_dtls.order_id AS \"orderId\",  "
			 +"	ord_dtls.order_qty AS \"orderQty\",  "
			 +"	ord_dtls.service_id AS \"serviceId\",  "
			 +"	ord_dtls.is_outsourced AS \"isOutsourced\",  "
			 +"	ord_dtls.package_id AS \"packageId\",  "
			 +"	ord_dtls.created_by AS \"createdBy\",  "
			 +"	ord_dtls.created_date AS \"createdDate\",  "
			 +"	test_mst.test_id AS \"testId\",  "
			 +"	test_mst.test_code AS \"testCode\",  "
			 +"	test_mst.test_desc AS \"testDesc\",  "
			 +"	test_mst.container_id AS \"containerId\",  "
			 +"	test_mst.no_of_sample_req AS \"sampleReqCount\", "
			 +"	contain_mst.container_name AS \"containerName\",  "
			 +"	samp_mst.sample_desc AS \"sampleName\",  "
			 +"	samp_mst.sample_id AS \"sampleTypeId\",  "
			 +"	test_mst.sample_volume AS \"sampleVolume\",  "
			 +"	unit_mst.unit_name AS \"unitName\",  "
			 +"	subspe_mst.sub_speciality_name AS \"deptName\",  "
			 +"	subspe_mst.speciality_id AS \"deptId\",  "
			 +"	subspe_mst.sub_speciality_id AS \"subDeptId\"  "
			 +"FROM  "
			 +"	public.t_order_details ord_dtls  "
			 +"INNER JOIN lab.m_test_master test_mst ON  "
			 +"	ord_dtls.service_id = test_mst.service_id  "
			 +"INNER JOIN lab.m_sample_master samp_mst ON  "
			 +"	samp_mst.sample_id = test_mst.sample_id  "
			 +"INNER JOIN lab.m_unit_master unit_mst ON  "
			 +"	unit_mst.unit_id = test_mst.unit_id  "
			 +"INNER JOIN doctor.m_sub_speciality_master subspe_mst ON  "
			 +"	test_mst.dept_id = subspe_mst.sub_speciality_id		  "
			 +"INNER JOIN lab.m_container_master contain_mst ON  "
			 +"	contain_mst.container_id = test_mst.container_id	  "
			 +"WHERE  "
			 +"	ord_dtls.org_id = :orgId  "
			 +"	AND ord_dtls.org_unit_id = :orgUnitId "
			 +"	AND ord_dtls.status = 'A'  "
			 +"	AND ord_dtls.order_id = :orderId  "
			 +"	AND ord_dtls.ord_cancelled = 'N'  "
			 +"	AND ord_dtls.service_is_billed = 1  "
			 +"    AND samp_mst.sample_status = 'A'  "
			 +"    AND unit_mst.unit_status = 'A'  "
			 +"    AND order_details_id NOT IN (SELECT samp_dtls.order_details_id  "
			 +"                                  FROM lab.t_lab_sample_dtls samp_dtls  "
			 +"                                  WHERE samp_dtls.order_id = :orderId  "
			 +"                                  AND samp_dtls.sample_recollect_flag = 'N')  "
			 +"UNION "
			 +"SELECT  "
			 +"	ord_dtls.order_details_id AS \"orderDetailsId\",  "
			 +"	ord_dtls.org_id AS \"orgId\",  "
			 +"	ord_dtls.org_unit_id AS \"orgUnitId\",  "
			 +"	ord_dtls.order_id AS \"orderId\",  "
			 +"	ord_dtls.order_qty AS \"orderQty\",  "
			 +"	ord_dtls.service_id AS \"serviceId\",  "
			 +"	ord_dtls.is_outsourced AS \"isOutsourced\",  "
			 +"	ord_dtls.package_id AS \"packageId\",  "
			 +"	ord_dtls.created_by AS \"createdBy\",  "
			 +"	ord_dtls.created_date AS \"createdDate\",  "
			 +"	test_mst.test_id AS \"testId\",  "
			 +"	test_mst.test_code AS \"testCode\",  "
			 +"	test_mst.test_desc AS \"testDesc\",  "
			 +"	test_mst.container_id AS \"containerId\",  "
			 +"	test_mst.no_of_sample_req AS \"sampleReqCount\", "
			 +"	contain_mst.container_name AS \"containerName\",  "
			 +"	samp_mst.sample_desc AS \"sampleName\",  "
			 +"	samp_mst.sample_id AS \"sampleTypeId\",  "
			 +"	test_mst.sample_volume AS \"sampleVolume\",  "
			 +"	unit_mst.unit_name AS \"unitName\",  "
			 +"	subspe_mst.sub_speciality_name AS \"deptName\",  "
			 +"	subspe_mst.speciality_id AS \"deptId\",  "
			 +"	subspe_mst.sub_speciality_id AS \"subDeptId\"  "
			 +"FROM  "
			 +"	public.t_order_details ord_dtls  "
			 +"INNER JOIN lab.m_test_master test_mst ON  "
			 +"	ord_dtls.service_id = test_mst.service_id  "
			 +"INNER JOIN lab.m_sample_master samp_mst ON  "
			 +"	samp_mst.sample_id = test_mst.sample_id  "
			 +"INNER JOIN lab.m_unit_master unit_mst ON  "
			 +"	unit_mst.unit_id = test_mst.unit_id  "
			 +"INNER JOIN doctor.m_sub_speciality_master subspe_mst ON  "
			 +"	test_mst.dept_id = subspe_mst.sub_speciality_id		  "
			 +"INNER JOIN lab.m_container_master contain_mst ON  "
			 +"	contain_mst.container_id = test_mst.container_id	  "
			 +"WHERE  "
			 +"	ord_dtls.org_id = :orgId  "
			 +"	AND ord_dtls.org_unit_id = :orgUnitId  "
			 +"	AND ord_dtls.status = 'A'  "
			 +"	AND ord_dtls.order_id = :orderId  "
			 +"	AND ord_dtls.ord_cancelled = 'N'  "
			 +"	AND ord_dtls.service_is_billed = 1  "
			 +"    AND samp_mst.sample_status = 'A'  "
			 +"    AND unit_mst.unit_status = 'A'  "
			 +"   AND order_details_id IN (SELECT samp_dtls.order_details_id  "
			 +"                           FROM lab.t_lab_sample_dtls samp_dtls  "
			 +"                           WHERE samp_dtls.order_id = :orderId "
			 +"                           AND samp_dtls.sample_recollect_flag = 'N' "
			 +"                            AND samp_dtls.sample_pending_count > 0)  "
			 +" "),
	@NamedNativeQuery(name = "SEARCH_PHLEBOTOMY_WORK_LIST_PATIENT", query = 
	" SELECT  "
			 +"  DISTINCT pati_mst.patient_id AS \"id\",  "
			/* +"  ord_mst.patient_id AS \"patientId\",  "*/
			 +"  pati_mst.first_name AS \"firstName\",  "
			 +"  pati_mst.last_name AS \"lastName\",  "
			 +"  pati_mst.uhid_number AS \"uhid\"  "
			 +"FROM  "
			 +"  public.t_order_master ord_mst  "
			 +"  INNER JOIN patient.t_patient_registration pati_mst ON ord_mst.patient_id = pati_mst.patient_id  "
			 +"WHERE  "
			 +"  ord_mst.org_id = :orgId  "
			 +"  AND ord_mst.org_unit_id = :orgUnitId  "
			 +"  AND ord_mst.order_status = 'A'  "
			 +"  AND ord_mst.visit_type_id = :visitTypeId  "
			 +"  AND (  "
			 +"    LOWER(pati_mst.first_name) LIKE (:searchKeyword)  "
			 +"    OR lower(pati_mst.last_name) LIKE lower(:searchKeyword)  "
			 +"    OR lower(pati_mst.uhid_number) LIKE lower(:searchKeyword)) "),

	@NamedNativeQuery(name = "SEARCH_SEND_TO_CR_LIST_PATIENT_GENRAL_LAB", query = 
			"SELECT "
					+ " DISTINCT lab_sample_dtls.patient_id AS \"id\",   "
					+ " pati_mst.first_name AS \"firstName\","
					+ " pati_mst.last_name AS \"lastName\", "
					+ " pati_mst.uhid_number AS \"uhid\" "
					+ " FROM "
					+ " lab.t_lab_sample_dtls  lab_sample_dtls    "
					+ " INNER JOIN patient.t_patient_registration pati_mst ON lab_sample_dtls.patient_id = pati_mst.patient_id   "
					+ " INNER JOIN lab.t_lab_sample_master lab_sample_mst ON lab_sample_dtls.patient_id = lab_sample_mst.patient_id   "
					+ " WHERE "
					+ " lab_sample_dtls.org_id =:orgId "
					+ " AND lab_sample_dtls.org_unit_id =:orgUnitId "
					+ " AND lab_sample_dtls.sample_status_id =:sampleStatusId"
					+ " AND lab_sample_mst.visit_type_id =:visitTypeId "
					+ " AND (   "
					+ "  LOWER(pati_mst.first_name) LIKE (:searchKeyword)"
					+ "  OR lower(pati_mst.last_name) LIKE lower(:searchKeyword) "
					+ " OR lower(pati_mst.uhid_number) LIKE lower(:searchKeyword))")
	
})

@NamedQueries({
@NamedQuery(name = "GET_LAB_ORDERS_COUNT", query =  "SELECT "
		 +"	COUNT(*) "
		 +"FROM "
		 +"	OrderMaster orderMaster "
		 +"WHERE "
		 +"	orderMaster.orderId IN( "
		 +"		SELECT "
		 +"			DISTINCT orderMaster.orderId "
		 +"		FROM "
		 +"			OrderMaster orderMaster "
		 +"		INNER JOIN orderMaster.listOrderDetailsMaster orderDtailsMaster "
		 +"		WHERE "
		 +"			orderMaster.orgUnitId = :orgUnitId "
		 +"			AND orderMaster.orgId = :orgId "
		 +"			AND orderMaster.visitTypeId = :visitTypeId "
		 +"			AND orderMaster.orderStatusId NOT IN(:orderStatusId) "
		 +"			AND orderDtailsMaster.serviceRendered = :serviceRendered "
		 +"			AND orderDtailsMaster.serviceIsBilled = :serviceIsBilled "
		 +"			AND orderDtailsMaster.serviceRenderingDeptId = :serviceRenderingDeptId "
		 +"	) "),
/*
@NamedQuery(name = "GET_LAB_DASHBOARD_LIST", query =  " SELECT  "
    +" visitMst.visitTypeCode AS visitType,  "
    +" labSampleDetailsMst.sampleNo AS sampleNo,  "
    +" labSampleDetailsMst.labSampleDtlsId AS labSampleDtlsId,  "
    +" patientReg.uhidNumber AS uhid,  "
    +" genderMst.code AS genderName,  "
    +" patientReg.dob AS dob,  "
    +" prefixMst.prefixCode || ' ' || patientReg.firstName || ' ' || patientReg.lastName AS patientDetails,  "
    +" 'Dr.' || ' ' || doctorMst.firstName || ' ' || doctorMst.lastName AS doctorDetails,  "
    +" subSpecialityMst.subSpecialityMasterName AS deptName,  "
    +" testMst.testDesc AS testDesc,  "
    +" speciMst.desc AS specimenType,  "
    +" sampleStatusMst.statusName AS labSampleStatus  "
    +"FROM  "
    +" OrderDetailsMaster orderDetailsMst  "
    +"LEFT JOIN orderDetailsMst.orderMaster orderMst  "
    +"LEFT JOIN orderDetailsMst.listLabSampleDetailsMaster labSampleDetailsMst  "
    +"LEFT JOIN orderDetailsMst.serviceMaster serviceMst  "
    +"LEFT JOIN orderMst.patientRegistration patientReg  "
    +"LEFT JOIN orderMst.doctorMaster doctorMst  "
    +"LEFT JOIN patientReg.prefixMaster prefixMst  "
    +"LEFT JOIN patientReg.genderMaster genderMst  "
    +"LEFT JOIN orderMst.visitTypeMaster visitMst  "
    +"LEFT JOIN serviceMst.subSpecialityMaster subSpecialityMst  "
    +"LEFT JOIN serviceMst.listTestMaster testMst  "
    +"LEFT JOIN labSampleDetailsMst.sampleStatusMaster sampleStatusMst  "
    +"LEFT JOIN testMst.specimanMaster speciMst  "
    +"WHERE  "
    +" orderDetailsMst.orgId = :orgId  "
    +" AND orderDetailsMst.orgUnitId = :orgUnitId  "
    +" AND orderDetailsMst.ordCancelled = 'N'  "
    +" AND orderDetailsMst.status = 'A'  "
    +" AND orderDetailsMst.serviceIsBilled IN(0,1)  "
    +" AND orderDetailsMst.serviceRenderingDeptId = :serviceRenderingDeptId  "
    +"ORDER BY  "
    +" orderDetailsMst.createdDate  "),*/


/*@NamedQuery(name = "GET_LAB_DASHBOARD_LIST_COUNT", query = " SELECT  "
    +" COUNT(*)  "
    +"FROM  "
    +" OrderDetailsMaster orderDetailsMst  "
    +"LEFT JOIN orderDetailsMst.orderMaster orderMst  "
    +"LEFT JOIN orderDetailsMst.listLabSampleDetailsMaster labSampleDetailsMst  "
    +"LEFT JOIN orderDetailsMst.serviceMaster serviceMst  "
    +"LEFT JOIN orderMst.patientRegistration patientReg  "
    +"LEFT JOIN orderMst.doctorMaster doctorMst  "
    +"LEFT JOIN patientReg.prefixMaster prefixMst  "
    +"LEFT JOIN patientReg.genderMaster genderMst  "
    +"LEFT JOIN orderMst.visitTypeMaster visitMst  "
    +"LEFT JOIN serviceMst.subSpecialityMaster subSpecialityMst  "
    +"LEFT JOIN serviceMst.listTestMaster testMst  "
    +"LEFT JOIN labSampleDetailsMst.sampleStatusMaster sampleStatusMst  "
    +"LEFT JOIN testMst.specimanMaster speciMst  "
    +"WHERE  "
    +" orderDetailsMst.orgId = :orgId  "
    +" AND orderDetailsMst.orgUnitId = :orgUnitId  "
    +" AND orderDetailsMst.ordCancelled = 'N'  "
    +" AND orderDetailsMst.status = 'A'  "
    +" AND orderDetailsMst.serviceIsBilled = 1  "
    +" AND orderDetailsMst.serviceRendered IN (0,1)  "
    +" AND orderDetailsMst.serviceRenderingDeptId = :serviceRenderingDeptId  " ),
*/

@NamedQuery(name = "SEARCH_DASHBORD_PATIENT", query = 
" SELECT  "
		 +"	DISTINCT(patientReg.patientId) AS id, "
		 +"	patientReg.firstName AS firstName , "
		 +" patientReg.lastName AS lastName , "
		 +"	patientReg.uhidNumber AS uhid "
		 +"FROM "
		 +" OrderDetailsMaster orderDetailsMst  "
		    +"LEFT JOIN orderDetailsMst.orderMaster orderMst  "
		    +"LEFT JOIN orderDetailsMst.listLabSampleDetailsMaster labSampleDetailsMst  "
		    +"LEFT JOIN orderDetailsMst.serviceMaster serviceMst  "
		    +"LEFT JOIN orderMst.patientRegistration patientReg  "
		    +"LEFT JOIN orderMst.doctorMaster doctorMst  "
		    +"LEFT JOIN patientReg.prefixMaster prefixMst  "
		    +"LEFT JOIN patientReg.genderMaster genderMst  "
		    +"LEFT JOIN orderMst.visitTypeMaster visitMst  "
		    +"LEFT JOIN serviceMst.subSpecialityMaster subSpecialityMst  "
		    +"LEFT JOIN serviceMst.listTestMaster testMst  "
		    +"LEFT JOIN labSampleDetailsMst.sampleStatusMaster sampleStatusMst  "
		    +"LEFT JOIN testMst.specimanMaster speciMst  "
		    +"WHERE  "
		    +" orderDetailsMst.orgId = :orgId  "
		    +" AND orderDetailsMst.orgUnitId = :orgUnitId  "
		    +" AND orderDetailsMst.ordCancelled = 'N'  "
		    +" AND orderDetailsMst.status = 'A'  "
		    +" AND orderDetailsMst.serviceIsBilled = 1  "
		    +" AND orderDetailsMst.serviceRendered IN (0,1)  "
		    +" AND orderDetailsMst.serviceRenderingDeptId = :serviceRenderingDeptId"
		    +" AND ( LOWER( patientReg.firstName ) LIKE (:searchKeyword) "
			+" OR lower( patientReg.lastName ) LIKE lower(:searchKeyword) "
			+" OR lower( patientReg.uhidNumber ) LIKE lower(:searchKeyword) ) "),


@NamedQuery(name = "GET_LAB_DASHBOARD_DETAILS", query =  " SELECT  "
    +" sampleStatusMst.statusName AS action,  "
    +" usr.userName AS userName,  "
    +" sampleAuditTrailMst.createdDate AS actionDateTime  "
    +"FROM  "
    +" SampleAuditTrailMaster sampleAuditTrailMst  "
    +"INNER JOIN sampleAuditTrailMst.sampleStatusMaster sampleStatusMst  "
    +"INNER JOIN sampleAuditTrailMst.user usr  "
    +"WHERE  "
    +" sampleAuditTrailMst.labSampleDtlsId =:labSampleDtlsId  "
    +" AND sampleAuditTrailMst.orgId =:orgId  "
    +" AND sampleAuditTrailMst.orgUnitId =:orgUnitId  "
    +"ORDER BY  "
    +" sampleAuditTrailMst.createdDate  " ),


@NamedQuery(name = "SEARCH_SAMPLE_NO_LAB_DASHBOARD_LIST", query =  " SELECT "
	    +" labSampleDetailsMst.sampleNo AS sampleNo,  "
	    +" labSampleDetailsMst.labSampleDtlsId AS labSampleDtlsId "
	    +"FROM  "
	    +" LabSampleDetailsMaster labSampleDetailsMst  "
	    +"WHERE  "
	    +" labSampleDetailsMst.orgId =:orgId  "
	    +" AND labSampleDetailsMst.orgUnitId =:orgUnitId "
	    +" AND  cast (labSampleDetailsMst.sampleNo as text ) LIKE (:searchKeyword) "),

@NamedQuery(name = "SEARCH_SAMPLE_NO_GENRAL_LAB_LIST", query =  " SELECT "
	    +" labSampleDetailsMst.sampleNo AS sampleNo,  "
	    +" labSampleDetailsMst.labSampleDtlsId AS labSampleDtlsId "
	    +"FROM  "
	    +" LabSampleDetailsMaster labSampleDetailsMst  "
	    +"WHERE  "
	    +" labSampleDetailsMst.orgId =:orgId  "
	    +" AND labSampleDetailsMst.sampleStatusId =:sampleStatusId "
	    +" AND labSampleDetailsMst.orgUnitId =:orgUnitId "
	    +" AND  cast (labSampleDetailsMst.sampleNo as text ) LIKE (:searchKeyword) ")

})

@Entity
@Table(name = "t_lab_sample_master", schema = "lab")
@SequenceGenerator(name = "m_seq_lab_sample_mst", sequenceName = "lab.m_seq_lab_sample_mst", allocationSize = 1)
public class LabSampleMaster
{
	@Id
	@Column(name = "lab_sample_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_lab_sample_mst")
	private int labSampleId;
	
	@Column(name = "org_id")
	private Integer orgId;
	
	@Column(name = "org_unit_id")
	private Integer orgUnitId;

	@Column(name = "sample_gen_datetime")
	@Convert(converter = LocalTimeConverter.class)
	private Long sampleGenDatetime;
	
	@Column(name = "order_id")
	private Integer orderId;
	
	@Column(name = "visit_type_id")
	private Integer visitTypeId;
	
	@Column(name = "admission_id")
	private Integer visitAdmId;
	
	@Column(name = "patient_id")
	private Integer patientId;
	
	@Column(name = "created_by")
	private Integer createdBy;
	
	@Column(name = "createdDate")
	@Convert(converter = LocalTimeConverter.class)
	private Long createdDate;
	
	@Column(name = "updated_by")
	private Integer updatedBy;
	
	@Column(name = "updated_date")
	@Convert(converter = LocalTimeConverter.class)
	private Long updatedDate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "labSampleMaster")
	private List<LabSampleDetailsMaster> listLabSampleDetailsMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", insertable = false, nullable = false, updatable = false)
	private OrderMaster orderMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "visit_type_id", insertable = false, nullable = false, updatable = false)
	private VisitTypeMaster visitTypeMaster;
	
	public int getLabSampleId()
	{
		return labSampleId;
	}
	
	public void setLabSampleId(int labSampleId)
	{
		this.labSampleId = labSampleId;
	}

	public Integer getOrgId()
	{
		return orgId;
	}

	public void setOrgId(Integer orgId)
	{
		this.orgId = orgId;
	}

	public Integer getOrgUnitId()
	{
		return orgUnitId;
	}

	public void setOrgUnitId(Integer orgUnitId)
	{
		this.orgUnitId = orgUnitId;
	}

	public Long getSampleGenDatetime()
	{
		return sampleGenDatetime;
	}

	public void setSampleGenDatetime(Long sampleGenDatetime)
	{
		this.sampleGenDatetime = sampleGenDatetime;
	}

	public Integer getOrderId()
	{
		return orderId;
	}

	public void setOrderId(Integer orderId)
	{
		this.orderId = orderId;
	}

	public Integer getVisitTypeId()
	{
		return visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId)
	{
		this.visitTypeId = visitTypeId;
	}

	public Integer getVisitAdmId()
	{
		return visitAdmId;
	}

	public void setVisitAdmId(Integer visitAdmId)
	{
		this.visitAdmId = visitAdmId;
	}

	public Integer getPatientId()
	{
		return patientId;
	}

	public void setPatientId(Integer patientId)
	{
		this.patientId = patientId;
	}

	public Integer getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy)
	{
		this.createdBy = createdBy;
	}

	public Long getCreatedDate()
	{
		return createdDate;
	}

	public void setCreatedDate(Long createdDate)
	{
		this.createdDate = createdDate;
	}

	public Integer getUpdatedBy()
	{
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy)
	{
		this.updatedBy = updatedBy;
	}

	public Long getUpdatedDate()
	{
		return updatedDate;
	}

	public void setUpdatedDate(Long updatedDate)
	{
		this.updatedDate = updatedDate;
	}

	public List<LabSampleDetailsMaster> getListLabSampleDetailsMaster()
	{
		return listLabSampleDetailsMaster;
	}

	public void setListLabSampleDetailsMaster(List<LabSampleDetailsMaster> listLabSampleDetailsMaster)
	{
		this.listLabSampleDetailsMaster = listLabSampleDetailsMaster;
	}

	public OrderMaster getOrderMaster() {
		return orderMaster;
	}

	public void setOrderMaster(OrderMaster orderMaster) {
		this.orderMaster = orderMaster;
	}

	public VisitTypeMaster getVisitTypeMaster() {
		return visitTypeMaster;
	}

	public void setVisitTypeMaster(VisitTypeMaster visitTypeMaster) {
		this.visitTypeMaster = visitTypeMaster;
	}
	
}
