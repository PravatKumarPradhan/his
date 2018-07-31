package com.param.entity.lis.transaction;

import java.math.BigInteger;
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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.common.LocalTimeConverter;
import com.param.entity.lis.histo.TTemplateResult;
import com.param.entity.lis.unit.TestMaster;
import com.param.global.model.DoctorMaster;
import com.param.global.model.GenderMaster;
import com.param.global.model.OrderDetailsMaster;
import com.param.global.model.PatientRegistration;


@NamedNativeQueries({

  /**@QUERY SEND TO CR LIST*/
    @NamedNativeQuery(name = "GET_COLLECTED_SAMPLE_LIST", query = " SELECT  "
        +"  panel_master.panel_code AS \"panelCode\",  "
        +"  lbsampledtls.lab_sample_dtls_id AS \"labSampleDtlsId\",  "
        +"  visit_type_master.visit_type_code AS \"visitType\",  "
        +"  test_master.test_desc AS \"testDesc\",  "
        +"  test_master.test_code  AS \"testCode\",  " 
        +" samp_mst.sample_desc  AS \"sampleType\",  " 
        +" lab.generate_sample_no(CAST(lbsampledtls.sample_no AS TEXT), 'SM-', 7) AS \"labSampleNo\",  " 
        +"  pati_mst.uhid_number AS uhid,  "
        +"  pre_mst.prefix_code || ' ' || pati_mst.first_name || ' ' || pati_mst.last_name || ' ' || EXTRACT(  "
        +"    YEAR  "
        +"    FROM  "
        +"      age(NOW(), pati_mst.dob)  "
        +"  ) || '/' || gen_mst.gender_code AS \"patientDetails\",  "
        +"  subspe_mst.sub_speciality_name AS \"deptName\",  "
        +"  pri_mst.priority_name AS \"priorityName\",  "
        +"  pri_mst.color_code AS \"colorCode\",  "
        +"  contai_mst.container_name AS \"containerName\",  "
        +"  order_details.created_date AS \"orderDateTime\",  "
        +"  lbsampledtls.sample_collection_datetime AS \"sampleCollectionDatetime\"  "
        +"FROM  "
        +"  lab.t_lab_sample_dtls lbsampledtls  "
        +"  INNER JOIN public.t_order_details order_details ON order_details.order_details_id = lbsampledtls.order_details_id  "
        +"  INNER JOIN public.t_order_master order_master ON order_master.order_id = order_details.order_id  "
        +"  INNER JOIN public.m_visit_type_master visit_type_master ON visit_type_master.visit_type_id = order_master.visit_type_id  "
        +"  INNER JOIN patient.t_patient_registration pati_mst ON lbsampledtls.patient_id = pati_mst.patient_id  "
        +"  INNER JOIN m_prefix_master pre_mst ON pati_mst.prefix_id = pre_mst.prefix_id  "
        +"  INNER JOIN m_gender_master gen_mst ON pati_mst.gender_id = gen_mst.gender_id  "
        +"  INNER JOIN doctor.m_sub_speciality_master subspe_mst ON subspe_mst.sub_speciality_id = lbsampledtls.sub_dept_id  "
        +"  INNER JOIN lab.m_test_master test_master ON test_master.test_id = lbsampledtls.test_id  "
        +"  INNER JOIN lab.m_container_master contai_mst ON contai_mst.container_id = lbsampledtls.container_id  "
        +"  INNER JOIN lab.m_sample_master samp_mst ON samp_mst.sample_id = test_master.sample_id "
        +"  INNER JOIN lab.m_priority_master pri_mst ON lbsampledtls.priority_id = pri_mst.priority_id  "
        +"  LEFT JOIN lab.t_panel_master panel_master ON panel_master.panel_id = lbsampledtls.profile_id  "
        +"WHERE  "
        +"  lbsampledtls.sample_status_id IN(:sampleStatusIds)  "
        +"  AND lbsampledtls.sub_dept_id IN(:biochemHematoDeptIds)  "
        +"  AND lbsampledtls.org_id = :orgId  "
        +"  AND lbsampledtls.org_unit_id = :orgUnitId  "
        +"  AND lbsampledtls.dept_id = :deptId  "
        +"  AND order_master.visit_type_id = :visitTypeId  "
        + " AND test_master.is_histo_cyto ='N' "
        +"ORDER BY  "
        +"  lbsampledtls.priority_id,  "
        +"  lbsampledtls.sample_accept_datetime  "),

    /**@QUERY SEND TO CR LIST COUNT*/
    @NamedNativeQuery(name = "GET_COUNT_FROM_SEND_TO_CR_LIST",
        query = "  SELECT  "
            +"  COUNT(*)  "
            +"FROM  "
            +"  lab.t_lab_sample_dtls lbsampledtls  "
            +"  INNER JOIN public.t_order_master order_master ON order_master.order_id = lbsampledtls.order_id  "
            +"  INNER JOIN lab.m_test_master test_master ON test_master.test_id = lbsampledtls.test_id  "
            +"WHERE  "
            +"  lbsampledtls.sample_status_id IN(:sampleStatusIds)  "
            +"  AND lbsampledtls.sub_dept_id IN(:biochemHematoDeptIds)  "
            +"  AND lbsampledtls.org_id = :orgId  "
            +"  AND lbsampledtls.dept_id = :deptId  "
            +"  AND order_master.visit_type_id = :visitTypeId  "
            + " AND test_master.is_histo_cyto ='N' "
            +"  AND lbsampledtls.org_unit_id = :orgUnitId  "),

    @NamedNativeQuery(name = "SEND_SAMPLE_TO_CENTRAL_RECEIVING", query = "	UPDATE "
        + "	lab.t_lab_sample_dtls  " + "SET " + "	sample_status_id =:sampleStatusId, "
        + "	sample_sendtocr_by =:sampleSendToCrBy ,sample_sendtocr_datetime =:sampleSendtocrDatetime "
        + "  	WHERE lab_sample_dtls_id = :labSampleDtlsId"),


    @NamedNativeQuery(name = "COLLECT_SAMPLE_OUT_PATIENT", query = " SELECT  "
        +"  visit_type_master.visit_type_code AS \"visitType\",  "
        +"  panel_master.panel_code AS \"panelCode\",  "
        +"  lbsampledtls.lab_sample_dtls_id AS \"labSampleDtlsId\",  "
        +"  lab.generate_sample_no(CAST(lbsampledtls.sample_no AS text), 'SM-', 7) AS \"labSampleNo\",  "
        +"  lbsampledtls.is_alliquote_req AS \"isAlliquoteReq\",  "
        +"  lbsampledtls.sample_pending_count AS \"samplePendingCount\",  "
        +"  test_master.test_code  AS \"testCode\",  " 
        +"  samp_mst.sample_desc  AS \"sampleType\",  " 
        +"  pati_mst.uhid_number AS uhid,  "
        +"  test_mst.test_desc AS \"testDesc\",  "
        +"  test_mst.is_centrifugation_requried AS \"isCentrifugationReq\",  "
        +"  pre_mst.prefix_code || ' ' || pati_mst.first_name || ' ' || pati_mst.last_name || ' ' || EXTRACT(  "
        +"    YEAR  "
        +"    FROM  "
        +"      age(NOW(), pati_mst.dob)  "
        +"  ) || '/' || gen_mst.gender_code AS \"patientDetails\",  "
        +"  'Dr.' || doct_mst.first_name || ' ' || doct_mst.last_name || ' (' || speciality_master.speciality_name || ')' AS \"doctorDetails\",  "
        +"  pri_mst.priority_name AS \"priorityName\",  "
        +"  pri_mst.color_code AS \"colorCode\",  "
        +"  order_details.created_date AS \"orderDateTime\",  "
        +"  lbsampledtls.sample_collection_datetime AS \"sampleSendtocrDatetime\"  "
        +"FROM  "
        +"  lab.t_lab_sample_dtls lbsampledtls  "
        +"  INNER JOIN public.t_order_details order_details ON order_details.order_details_id = lbsampledtls.order_details_id  "
        +"  INNER JOIN public.t_order_master order_master ON order_master.order_id = order_details.order_id  "
        +"  INNER JOIN public.m_visit_type_master visit_type_master ON visit_type_master.visit_type_id = order_master.visit_type_id  "
        +"  INNER JOIN lab.m_test_master test_mst ON lbsampledtls.test_id = test_mst.test_id  "
        +"  INNER JOIN patient.t_patient_registration pati_mst ON lbsampledtls.patient_id = pati_mst.patient_id  "
        +"  INNER JOIN m_prefix_master pre_mst ON pati_mst.prefix_id = pre_mst.prefix_id  "
        +"  INNER JOIN m_gender_master gen_mst ON pati_mst.gender_id = gen_mst.gender_id  "
        +"  INNER JOIN doctor.m_sub_speciality_master subspe_mst ON subspe_mst.sub_speciality_id = lbsampledtls.sub_dept_id  "
        +"  INNER JOIN doctor.m_doctor_master doct_mst ON order_master.ord_doctor_id = doct_mst.doctor_id  "
        +"  INNER JOIN doctor.t_doctor_speciality_mapper doct_speciality_mppr ON doct_speciality_mppr.doctor_id = order_master.ord_doctor_id  "
        +"  INNER JOIN doctor.m_speciality_master speciality_master ON speciality_master.speciality_id = doct_speciality_mppr.speciality_id  "
        +"  INNER JOIN lab.m_test_master test_master ON test_master.test_id = lbsampledtls.test_id  "
        +"  INNER JOIN lab.m_container_master contai_mst ON contai_mst.container_id = lbsampledtls.container_id  "
        +"  INNER JOIN lab.m_sample_master samp_mst ON samp_mst.sample_id = test_master.sample_id "
        +"  INNER JOIN lab.m_priority_master pri_mst ON lbsampledtls.priority_id = pri_mst.priority_id  "
        +"  LEFT JOIN lab.t_panel_master panel_master ON panel_master.panel_id = lbsampledtls.profile_id  "
        +"WHERE  "
        +"  lbsampledtls.sample_status_id = :sampleStatusId  "
        +"  AND lbsampledtls.sub_dept_id IN(:biochemHematoDeptIds)  "
        +"  AND lbsampledtls.org_id = :orgId  "
        +"  AND lbsampledtls.dept_id = :deptId  "
        +"  AND lbsampledtls.org_unit_id = :orgUnitId  "
        +"  AND order_master.visit_type_id = :visitTypeId  "
        +"ORDER BY  "
        +"  lbsampledtls.priority_id,  "
        +"  lbsampledtls.sample_sendtocr_datetime  "),

    @NamedNativeQuery(name = "GET_COUNT_FOR_OUT_PATIENT",
        query = " SELECT  "
            +"  COUNT(*)  "
            +"FROM  "
            +"  lab.t_lab_sample_dtls lbsampledtls  "
            +"  INNER JOIN lab.t_lab_sample_master labsammst ON labsammst.lab_sample_id = lbsampledtls.lab_sample_id  "
            +"WHERE  "
            +"  lbsampledtls.sample_status_id = :sampleStatusId  "
            +"  AND lbsampledtls.org_id = :orgId  "
            +"  AND lbsampledtls.dept_id = :deptId  "
            +"  AND lbsampledtls.sub_dept_id IN(:biochemHematoDeptIds)  "
            +"  AND lbsampledtls.org_unit_id = :orgUnitId  "
            +"  AND labsammst.visit_type_id = :visitTypeId  "),

    @NamedNativeQuery(name = "COLLECT_SAMPLE_IN_PATIENT", query = " SELECT  "
        +"  visit_type_master.visit_type_code AS \"visitType\",  "
        +"  panel_master.panel_code AS \"panelCode\",  "
        +"  lbsampledtls.lab_sample_dtls_id AS \"labSampleDtlsId\",  "
        +"  lab.generate_sample_no(CAST(lbsampledtls.sample_no AS TEXT), 'SM-', 7) AS \"labSampleNo\",  "
        +"  lbsampledtls.is_alliquote_req AS \"isAlliquoteReq\",  "
        +"  lbsampledtls.sample_pending_count AS \"samplePendingCount\",  "
        +"  pati_mst.uhid_number AS uhid,  "
        +"  test_mst.test_desc AS \"testDesc\",  "
        +"  pre_mst.prefix_code || ' ' || pati_mst.first_name || ' ' || pati_mst.last_name || ' ' || EXTRACT(  "
        +"    YEAR  "
        +"    FROM  "
        +"      age(NOW(), pati_mst.dob)  "
        +"  ) || '/' || gen_mst.gender_code AS \"patientDetails\",  "
        +"  'Dr.' || doct_mst.first_name || ' ' || doct_mst.last_name || ' (' || speciality_master.speciality_name || ')' AS \"doctorDetails\",  "
        +"  pri_mst.priority_name AS \"priorityName\",  "
        +"  pri_mst.color_code AS \"colorCode\",  "
        +"  wardmast.ward_code AS \"wardCode\",  "
        +"  bedmst.bed_number AS \"bedNumber\",  "
        +"  order_details.created_date AS \"orderDateTime\",  "
        +"  lbsampledtls.sample_collection_datetime AS \"sampleSendtocrDatetime\"  "
        +"FROM  "
        +"  lab.t_lab_sample_dtls lbsampledtls  "
        +"  INNER JOIN public.t_order_details order_details ON order_details.order_details_id = lbsampledtls.order_details_id  "
        +"  INNER JOIN public.t_order_master order_master ON order_master.order_id = order_details.order_id  "
        +"  INNER JOIN public.m_visit_type_master visit_type_master ON visit_type_master.visit_type_id = order_master.visit_type_id  "
        +"  INNER JOIN lab.m_test_master test_mst ON lbsampledtls.test_id = test_mst.test_id  "
        +"  INNER JOIN patient.t_patient_registration pati_mst ON lbsampledtls.patient_id = pati_mst.patient_id  "
        +"  INNER JOIN m_prefix_master pre_mst ON pati_mst.prefix_id = pre_mst.prefix_id  "
        +"  INNER JOIN m_gender_master gen_mst ON pati_mst.gender_id = gen_mst.gender_id  "
        +"  INNER JOIN doctor.m_sub_speciality_master subspe_mst ON subspe_mst.sub_speciality_id = lbsampledtls.sub_dept_id  "
        +"  INNER JOIN doctor.m_doctor_master doct_mst ON order_master.ord_doctor_id = doct_mst.doctor_id  "
        +"  INNER JOIN doctor.t_doctor_speciality_mapper doct_speciality_mppr ON doct_speciality_mppr.doctor_id = order_master.ord_doctor_id  "
        +"  INNER JOIN doctor.m_speciality_master speciality_master ON speciality_master.speciality_id = doct_speciality_mppr.speciality_id  "
        +"  INNER JOIN lab.m_test_master test_master ON test_master.test_id = lbsampledtls.test_id  "
        +"  INNER JOIN lab.m_container_master contai_mst ON contai_mst.container_id = lbsampledtls.container_id  "
        +"  INNER JOIN lab.m_priority_master pri_mst ON lbsampledtls.priority_id = pri_mst.priority_id  "
        +"  INNER JOIN adt.m_ward_master wardmast ON order_details.ward_id = wardmast.ward_id  "
        +"  INNER JOIN adt.m_bed_master bedmst ON order_details.bed_id = bedmst.bed_id  "
        +"  LEFT JOIN lab.t_panel_master panel_master ON panel_master.panel_id = lbsampledtls.profile_id  "
        +"WHERE  "
        +"  lbsampledtls.sample_status_id = :sampleStatusId  "
        +"  AND lbsampledtls.sub_dept_id IN(:biochemHematoDeptIds)  "
        +"  AND lbsampledtls.org_id = :orgId  "
        +"  AND lbsampledtls.org_unit_id = :orgUnitId  "
        +"  AND order_master.visit_type_id = :visitTypeId  "
        +"ORDER BY  "
        +"  lbsampledtls.priority_id,  "
        +"  lbsampledtls.sample_sendtocr_datetime  "),


    @NamedNativeQuery(name = "GET_COUNT_FOR_IN_PATIENT",
        query =   " SELECT  "
            +"  COUNT(*)  "
            +"FROM  "
            +"  lab.t_lab_sample_dtls lbsampledtls  "
            +"  INNER JOIN lab.t_lab_sample_master labsammst ON labsammst.lab_sample_id = lbsampledtls.lab_sample_id  "
            +"WHERE  "
            +"  lbsampledtls.sample_status_id = :sampleStatusId  "
            +"  AND lbsampledtls.sub_dept_id IN(:biochemHematoDeptIds)  "
            +"  AND lbsampledtls.org_id = :orgId  "
            +"  AND lbsampledtls.dept_id = :deptId  "
            +"  AND lbsampledtls.org_unit_id = :orgUnitId  "
            +"  AND labsammst.visit_type_id = :visitTypeId  "),


    @NamedNativeQuery(name = "CENTRIFUGATION_WORK_LIST", query =  " SELECT  "
        +"  visit_type_master.visit_type_code AS \"visitType\",  "
        +"  panel_master.panel_code AS \"panelCode\",  "
        +"  lbsampledtls.lab_sample_dtls_id AS \"labSampleDtlsId\",  "
        +"  lab.generate_sample_no(CAST(lbsampledtls.sample_no AS TEXT), 'SM-', 7) AS \"labSampleNo\",  "
        +"  lbsampledtls.is_alliquote_req AS \"isAlliquoteReq\",  "
        +"  pati_mst.uhid_number AS uhid,  "
        +"  test_mst.test_desc AS \"testDesc\",  " 
        +"  test_mst.test_code AS \"testCode\",  " 
        +" samp_mst.sample_desc  AS \"sampleType\",  " 
        +"  pre_mst.prefix_code || ' ' || pati_mst.first_name || ' ' || pati_mst.last_name || ' ' || EXTRACT(  "
        +"    YEAR  "
        +"    FROM  "
        +"      age(NOW(), pati_mst.dob)  "
        +"  ) || '/' || gen_mst.gender_code AS \"patientDetails\",  "
        +"  'Dr.' || doct_mst.first_name || ' ' || doct_mst.last_name || ' (' || speciality_master.speciality_name || ')' AS \"doctorDetails\",  "
        +"  pri_mst.priority_name AS \"priorityName\",  "
        +"  pri_mst.color_code AS \"colorCode\",  "
        +"  wardmast.ward_code AS \"wardCode\",  "
        +"  bedmst.bed_number AS \"bedNumber\",  "
        +"  lbsampledtls.sample_barcode AS \"sampleBarcode\",  "
        +"  order_details.created_date AS \"orderDateTime\",  "
        +"  lbsampledtls.sample_collection_datetime AS \"sampleCollectionDatetime\"  "
        +"FROM  "
        +"  lab.t_lab_sample_dtls lbsampledtls  "
        +"  INNER JOIN public.t_order_details order_details ON order_details.order_details_id = lbsampledtls.order_details_id  "
        +"  INNER JOIN public.t_order_master order_master ON order_master.order_id = order_details.order_id  "
        +"  INNER JOIN public.m_visit_type_master visit_type_master ON visit_type_master.visit_type_id = order_master.visit_type_id  "
        +"  INNER JOIN lab.m_test_master test_mst ON lbsampledtls.test_id = test_mst.test_id  "
        +"  INNER JOIN patient.t_patient_registration pati_mst ON lbsampledtls.patient_id = pati_mst.patient_id  "
        +"  INNER JOIN m_prefix_master pre_mst ON pati_mst.prefix_id = pre_mst.prefix_id  "
        +"  INNER JOIN m_gender_master gen_mst ON pati_mst.gender_id = gen_mst.gender_id  "
        +"  INNER JOIN doctor.m_sub_speciality_master subspe_mst ON subspe_mst.sub_speciality_id = lbsampledtls.sub_dept_id  "
        +"  INNER JOIN doctor.m_doctor_master doct_mst ON order_master.ord_doctor_id = doct_mst.doctor_id  "
        +"  INNER JOIN doctor.t_doctor_speciality_mapper doct_speciality_mppr ON doct_speciality_mppr.doctor_id = order_master.ord_doctor_id  "
        +"  INNER JOIN doctor.m_speciality_master speciality_master ON speciality_master.speciality_id = doct_speciality_mppr.speciality_id  "
        +"  INNER JOIN lab.m_test_master test_master ON test_master.test_id = lbsampledtls.test_id  "
        +"  INNER JOIN lab.m_container_master contai_mst ON contai_mst.container_id = lbsampledtls.container_id  "
        +"  INNER JOIN lab.m_sample_master samp_mst ON samp_mst.sample_id = test_master.sample_id "
        +"  INNER JOIN lab.m_priority_master pri_mst ON lbsampledtls.priority_id = pri_mst.priority_id  "
        +"  LEFT JOIN adt.m_ward_master wardmast ON order_details.ward_id = wardmast.ward_id  "
        +"  LEFT JOIN adt.m_bed_master bedmst ON order_details.bed_id = bedmst.bed_id  "
        +"  LEFT JOIN lab.t_panel_master panel_master ON panel_master.panel_id = lbsampledtls.profile_id  "
        +"WHERE  "
        +"  lbsampledtls.sample_status_id = :sampleStatusId  "
        +"  AND test_mst.is_centrifugation_requried = :isCentrifugationReq  "
        +"  AND lbsampledtls.sub_dept_id IN(:biochemHematoDeptIds)  "
        +"  AND lbsampledtls.org_id = :orgId  "
        +"  AND lbsampledtls.org_unit_id = :orgUnitId  "
        +"  AND lbsampledtls.dept_id = :deptId  "
        +"ORDER BY  "
        +"  lbsampledtls.priority_id,  "
        +"  lbsampledtls.sample_accept_datetime  "),

    @NamedNativeQuery(name = "GET_COUNT_CEONTRIFUGATION_WORKLIST",
        query = " SELECT  "
            +"  COUNT(*)  "
            +"FROM  "
            +"  lab.t_lab_sample_dtls lbsampledtls  "
            +"  INNER JOIN lab.m_test_master test_mst ON lbsampledtls.test_id = test_mst.test_id  "
            +"WHERE  "
            +"  lbsampledtls.sample_status_id = :sampleStatusId  "
            +"  AND lbsampledtls.org_id = :orgId  "
            +"  AND lbsampledtls.dept_id = :deptId  "
            +"  AND lbsampledtls.org_unit_id = :orgUnitId  "
            +"  AND lbsampledtls.sub_dept_id IN(:biochemHematoDeptIds)  "
            +"  AND test_mst.is_centrifugation_requried = :isCentrifugationReq  "),

    /** Sample Pending Acceptance */
    @NamedNativeQuery(name = "GET_ACCEPTANCE_PENDING_LIST", query =  " SELECT   "
        +"  visit_type_master.visit_type_code AS \"visitType\",   "
        +"  panel_master.panel_code AS \"panelCode\",   "
        +"  panel_master.panel_id AS \"panelId\",   "
        +"  lbsampledtls.gender_id AS \"genderId\",   "
        +"  lbsampledtls.org_id AS \"orgId\",   "
        +"  lbsampledtls.org_unit_id AS \"orgUnitId\",   "
        +"  lbsampledtls.lab_sample_dtls_id AS \"labSampleDtlsId\",   "
        + "  test_mst.sample_id AS \"sampleId\", "
        +"  lbsampledtls.sample_recollect_flag AS \"sampleRecollectFlag\",   "
        +"  lbsampledtls.sample_recollect_against_id AS \"sampleRecollectAgainstId\",   "
        +"  pati_mst.uhid_number AS uhid,   "
        +"  lab.generate_sample_no(CAST(lbsampledtls.sample_no AS text), 'SM-', 7) AS \"sampleNo\",   "
        +"  test_mst.test_desc AS \"testDesc\",   "
        +"  order_master.admission_id AS \"visitAdmId\",   "
        +"  order_master.visit_type_id AS \"visitTypeId\",   "
        +"  lbsampledtls.dept_id AS \"deptId\",    lbsampledtls.test_id AS \"testId\",   "
        +"  test_mst.test_type AS \"testType\",   "
        +"  lbsampledtls.sub_dept_id AS \"subDeptId\",   "
        +"  lbsampledtls.patient_id AS \"patientId\",   "
        +"  lbsampledtls.order_details_id AS \"orderDetailsId\",   "
        +"  pre_mst.prefix_code || ' ' || pati_mst.first_name || ' ' || pati_mst.last_name || ' ' || EXTRACT(   "
        +"    YEAR      FROM        age(NOW(), pati_mst.dob)   "
        +"  ) || '/' || gen_mst.gender_code AS \"patientDetails\",   "
        +"  CAST(lbsampledtls.patient_visit_age AS DATE) - CAST(pati_mst.dob AS DATE) AS \"patientAgeDays\",   "
        +"  'Dr.' || doct_mst.first_name || ' ' || doct_mst.last_name || ' (' || speciality_master.speciality_name || ')' AS \"doctorDetails\",   "
        +"  pri_mst.priority_name AS \"priorityName\",   "
        +"  pri_mst.color_code AS \"colorCode\",   "
        +"  lbsampledtls.sample_barcode AS \"sampleBarcode\",   "
        +"  order_details.created_date AS \"orderDateTime\",   "
        +"  lbsampledtls.sample_collection_datetime AS \"sampleCollectionDatetime\" ,  "
        +"  wardmast.ward_code AS \"wardCode\" ,  "
        +"  bedmst.bed_code AS \"bedNumber\",   "
        +"  test_mst.test_code AS \"testCode\" ,  "
        +"  samp_mst.sample_desc AS \"sampleType\"   "
        +"FROM   "
        +"  lab.t_lab_sample_dtls lbsampledtls   "
        +"  INNER JOIN public.t_order_details order_details ON order_details.order_details_id = lbsampledtls.order_details_id   "
        +"  INNER JOIN public.t_order_master order_master ON order_master.order_id = order_details.order_id   "
        +"  INNER JOIN public.m_visit_type_master visit_type_master ON visit_type_master.visit_type_id = order_master.visit_type_id   "
        +"  INNER JOIN lab.m_test_master test_mst ON lbsampledtls.test_id = test_mst.test_id   "
        +"  INNER JOIN patient.t_patient_registration pati_mst ON lbsampledtls.patient_id = pati_mst.patient_id   "
        +"  INNER JOIN m_prefix_master pre_mst ON pati_mst.prefix_id = pre_mst.prefix_id   "
        +"  INNER JOIN m_gender_master gen_mst ON pati_mst.gender_id = gen_mst.gender_id   "
        +"  INNER JOIN doctor.m_sub_speciality_master subspe_mst ON subspe_mst.sub_speciality_id = lbsampledtls.sub_dept_id   "
        +"  INNER JOIN doctor.m_doctor_master doct_mst ON order_master.ord_doctor_id = doct_mst.doctor_id   "
        +"  INNER JOIN doctor.t_doctor_speciality_mapper doct_speciality_mppr ON doct_speciality_mppr.doctor_id = order_master.ord_doctor_id   "
        +"  INNER JOIN doctor.m_speciality_master speciality_master ON speciality_master.speciality_id = doct_speciality_mppr.speciality_id   "
        +"  INNER JOIN lab.m_test_master test_master ON test_master.test_id = lbsampledtls.test_id   "
        +"  INNER JOIN lab.m_container_master contai_mst ON contai_mst.container_id = lbsampledtls.container_id   "
        +"  INNER JOIN lab.m_sample_master samp_mst ON samp_mst.sample_id = test_master.sample_id   "
        +"  INNER JOIN lab.m_priority_master pri_mst ON lbsampledtls.priority_id = pri_mst.priority_id   "
        +"  LEFT JOIN adt.t_admission_details admission_dtls ON order_master.admission_id = admission_dtls.admission_id "
        +"  LEFT JOIN adt.m_ward_master wardmast ON admission_dtls.ward_id = wardmast.ward_id   "
        +"  LEFT JOIN adt.m_bed_master bedmst ON admission_dtls.bed_id = bedmst.bed_id   "
        +"  LEFT JOIN lab.t_panel_master panel_master ON panel_master.panel_id = lbsampledtls.profile_id   "
        +"WHERE   "
        +"  lbsampledtls.sample_status_id =:sampleStatusId   "
        +"  AND lbsampledtls.org_id = :orgId   "
        +"  AND lbsampledtls.org_unit_id = :orgUnitId   "
        +"  AND lbsampledtls.dept_id = :deptId   "
        +"  AND lbsampledtls.sub_dept_id = :subDeptId   "
        +"ORDER BY   "
        +"  lbsampledtls.priority_id,   "
        +"  lbsampledtls.sample_accept_datetime   "),

    /** Sample Pending Acceptance Count */
    @NamedNativeQuery(name = "GET_ACCEPTANCE_PENDING_COUNT",
        query =   "  SELECT  "
            +"  COUNT(*)  "
            +"FROM  "
            +"  lab.t_lab_sample_dtls lbsampledtls  "
            +"WHERE  "
            +"  lbsampledtls.sample_status_id =:sampleStatusId  "
            +"  AND lbsampledtls.org_id = :orgId  "
            +"  AND lbsampledtls.org_unit_id = :orgUnitId  "
            +"  AND lbsampledtls.dept_id = :deptId  "
            +"  AND lbsampledtls.sub_dept_id = :subDeptId  "),

    /** List Rejected Sample */
    @NamedNativeQuery(name = "GET_REJECTED_SAMPLE_LIST",
        query =   " SELECT "
            +"  visit_mst.visit_type_code AS \"visitType\", "
            +"  lbsampledtls.lab_sample_dtls_id AS \"labSampleDtlsId\", "
            +"  panel_master.panel_code AS \"panelCode\", "
            +"  lbsampledtls.patient_id AS \"patientId\", "
            +"  lbsampledtls.doctor_id AS \"doctorId\", "
            +"  lbsampledtls.priority_id AS \"priorityId\", "
            +"  lbsampledtls.order_details_id AS \"orderDetailsId\", "
            +"  lbsampledtls.org_id AS \"orgId\", "
            +"  lbsampledtls.org_unit_id AS \"orgUnitId\", "
            +"  lbsampledtls.order_id AS \"orderId\", "
            +"  lbsampledtls.lab_sample_id AS \"labSampleId\", "
            +"  lbsampledtls.is_outsourced AS \"isOutsourced\", "
            +"  lbsampledtls.package_id AS \"packageId\", "
            +"  lbsampledtls.created_by AS \"createdBy\", "
            +"  lbsampledtls.created_date AS \"createdDate\", "
            +"  lbsampledtls.test_id AS \"testId\", "
            +"  lbsampledtls.gender_id AS \"genderId\", "
            +"  lbsampledtls.sample_req_count AS \"sampleReqCount\", "
            +"  lbsampledtls.sample_pending_count AS \"samplePendingCount\", "
            +"  lab.generate_sample_no(CAST(lbsampledtls.sample_no AS TEXT), 'SM-', 7) AS \"labSampleNo\", "
            +"  lbsampledtls.is_alliquote_req AS \"isAlliquoteReq\", "
            +"  pati_mst.uhid_number AS uhid, "
            +"  test_mst.test_desc AS \"testDesc\", "
            +"  test_mst.test_code AS \"testCode\", "
            +"  contai_mst.container_name AS \"containerName\", "
            +"  lbsampledtls.container_id AS \"containerId\", "
            +"  subspe_mst.sub_speciality_name AS \"deptName\", "
            +"  lbsampledtls.sample_type_id AS \"sampleTypeId\", "
            +"  subspe_mst.speciality_id AS \"deptId\", "
            +"  subspe_mst.sub_speciality_id AS \"subDeptId\", "
            +"  pre_mst.prefix_code || ' ' || pati_mst.first_name || ' ' || pati_mst.last_name || ' ' || EXTRACT( "
            +"    YEAR FROM age(NOW(), pati_mst.dob)) || '/' || gen_mst.gender_code AS \"patientDetails\", "
            +"  'Dr.' || doct_mst.first_name || ' ' || doct_mst.last_name || ' (' || speciality_master.speciality_name || ')' AS \"doctorDetails\", "
            +"  pri_mst.priority_name AS \"priorityName\", "
            +"  pri_mst.color_code AS \"colorCode\", "
            +"  lbsampledtls.sample_barcode AS \"sampleBarcode\", "
            +"  samp_mst.sample_desc AS \"sampleType\", "
            +"  rejection_master.rejection_name AS \"sampleRejectReason\", "
            +"  wardmast.ward_code AS \"wardCode\", "
            +"  bedmst.bed_code AS \"bedNumber\", "
            +"  test_mst.sample_volume AS \"sampleVolume\", "
            +"  unit_mst.unit_name AS \"unitName\" "
            +"FROM "
            +"  lab.t_lab_sample_dtls lbsampledtls "
            +"  INNER JOIN public.t_order_details order_details ON order_details.order_details_id = lbsampledtls.order_details_id "
            +"  INNER JOIN public.t_order_master order_mst ON order_mst.order_id = order_details.order_id "
            +"  INNER JOIN patient.t_patient_registration pati_mst ON pati_mst.patient_id = order_mst.patient_id "
            +"  INNER JOIN public.m_visit_type_master visit_mst ON visit_mst.visit_type_id = order_mst.visit_type_id "
            +"  INNER JOIN doctor.m_doctor_master doct_mst ON lbsampledtls.doctor_id = doct_mst.doctor_id "
            +"  INNER JOIN doctor.t_doctor_speciality_mapper doct_speciality_mppr ON doct_speciality_mppr.doctor_id = doct_mst.doctor_id "
            +"  INNER JOIN doctor.m_speciality_master speciality_master ON speciality_master.speciality_id = doct_speciality_mppr.speciality_id "
            +"  INNER JOIN doctor.m_sub_speciality_master subspe_mst ON subspe_mst.sub_speciality_id = lbsampledtls.sub_dept_id "
            +"  INNER JOIN m_prefix_master pre_mst ON pati_mst.prefix_id = pre_mst.prefix_id "
            +"  INNER JOIN m_gender_master gen_mst ON pati_mst.gender_id = gen_mst.gender_id "
            +"  INNER JOIN lab.m_priority_master pri_mst ON lbsampledtls.priority_id = pri_mst.priority_id "
            +"  INNER JOIN lab.m_test_master test_mst ON lbsampledtls.test_id = test_mst.test_id "
            +"  INNER JOIN lab.m_unit_master unit_mst ON unit_mst.unit_id = test_mst.unit_id "
            +"  INNER JOIN lab.m_sample_master samp_mst ON samp_mst.sample_id = test_mst.sample_id "
            +"  INNER JOIN lab.m_container_master contai_mst ON contai_mst.container_id = lbsampledtls.container_id "
            +"  LEFT JOIN lab.m_rejection_master rejection_master ON rejection_master.rejection_id = lbsampledtls.sample_reject_reason_id "
            +"  LEFT JOIN lab.t_panel_master panel_master ON panel_master.panel_id = lbsampledtls.profile_id "
            +"  LEFT JOIN adt.m_ward_master wardmast ON order_details.ward_id = wardmast.ward_id "
            +"  LEFT JOIN adt.m_bed_master bedmst ON order_details.bed_id = bedmst.bed_id "
            +"WHERE "
            +"  lbsampledtls.sample_status_id IN(:sampleStatusIds) "
            +"  AND lbsampledtls.org_id = :orgId "
            +"  AND lbsampledtls.dept_id = :deptId "
            +"  AND lbsampledtls.org_unit_id = :orgUnitId "
            +"ORDER BY "
            +"  lbsampledtls.priority_id, "
            +"  lbsampledtls.sample_sendtocr_datetime "
),


    /** GET COUNT FOR REJECTED SAMPLE */
    @NamedNativeQuery(name = "GET_COUNT_FOR_REJECTED_SAMPLE",
        query = " SELECT " + "  count(*) " + "FROM " + "	lab.t_lab_sample_dtls lbsampledtls "
            + "WHERE " + "	lbsampledtls.sample_status_id IN (:sampleStatusIds) "
            + "AND lbsampledtls.org_id = :orgId " + "AND lbsampledtls.dept_id = :deptId "
            + "AND lbsampledtls.org_unit_id = :orgUnitId "),

    @NamedNativeQuery(name = "UPDATE_SAMPLE_AFTER_RECOLLECTION",
        query = "UPDATE " + "	lab.t_lab_sample_dtls " + "SET "
            + "	sample_recollect_flag =:sampleRecollectFlag, "
            + "	sample_status_id =:sampleStatusId " + "WHERE "
            + "	lab_sample_dtls_id =:labSampleDtlsId "),



    @NamedNativeQuery(name = "GENERATE_SAMPLE_NO",
        query = "SELECT nextval('lab.sample_no_genarator');"),


    /** Biochemistry Worklist */
    @NamedNativeQuery(name = "GET_BIOCHEMISTRY_WORKLIST", query = "  SELECT  "
        +"  visit_type_master.visit_type_code AS \"visitType\",  "
        +"  panel_master.panel_code AS \"panelCode\",  "
        +"  lbsampledtls.lab_sample_dtls_id AS \"labSampleDtlsId\",  "
        +"  lbsampledtls.is_alliquote_req AS \"isAlliquoteReq\",  "
        +"  pati_mst.uhid_number AS uhid,  "
        +"  test_mst.test_desc AS \"testDesc\",  "
        +"  test_mst.test_code AS \"testCode\",  " 
        +" samp_mst.sample_desc  AS \"sampleType\",  " 
        +"  pre_mst.prefix_code || ' ' || pati_mst.first_name || ' ' || pati_mst.last_name || ' ' || EXTRACT(  "
        +"    YEAR  "
        +"    FROM  "
        +"      age(NOW(), pati_mst.dob)  "
        +"  ) || '/' || gen_mst.gender_code AS \"patientDetails\",  "
        +"  'Dr.' || doct_mst.first_name || ' ' || doct_mst.last_name || ' (' || speciality_master.speciality_name || ')' AS \"doctorDetails\",  "
        +"  pri_mst.priority_name AS \"priorityName\",  "
        +"  pri_mst.color_code AS \"colorCode\",  "
        +"  wardmast.ward_code AS \"wardCode\",  "
        +"  bedmst.bed_number AS \"bedNumber\",  "
        +"  lbsampledtls.sample_barcode AS \"sampleBarcode\",  "
        +"  lab.generate_sample_no(CAST(lbsampledtls.sample_no AS TEXT), 'SM-', 7) AS \"labSampleNo\",  "
        +"  order_details.created_date AS \"orderDateTime\",  "
        +"  lbsampledtls.sample_collection_datetime AS \"sampleCollectionDatetime\"  "
        +"FROM  "
        +"  lab.t_lab_sample_dtls lbsampledtls  "
        +"  INNER JOIN public.t_order_details order_details ON order_details.order_details_id = lbsampledtls.order_details_id  "
        +"  INNER JOIN public.t_order_master order_master ON order_master.order_id = order_details.order_id  "
        +"  INNER JOIN public.m_visit_type_master visit_type_master ON visit_type_master.visit_type_id = order_master.visit_type_id  "
        +"  INNER JOIN lab.m_test_master test_mst ON lbsampledtls.test_id = test_mst.test_id  "
        +"  INNER JOIN patient.t_patient_registration pati_mst ON lbsampledtls.patient_id = pati_mst.patient_id  "
        +"  INNER JOIN m_prefix_master pre_mst ON pati_mst.prefix_id = pre_mst.prefix_id  "
        +"  INNER JOIN m_gender_master gen_mst ON pati_mst.gender_id = gen_mst.gender_id  "
        +"  INNER JOIN doctor.m_sub_speciality_master subspe_mst ON subspe_mst.sub_speciality_id = lbsampledtls.sub_dept_id  "
        +"  INNER JOIN doctor.m_doctor_master doct_mst ON order_master.ord_doctor_id = doct_mst.doctor_id  "
        +"  INNER JOIN doctor.t_doctor_speciality_mapper doct_speciality_mppr ON doct_speciality_mppr.doctor_id = order_master.ord_doctor_id  "
        +"  INNER JOIN doctor.m_speciality_master speciality_master ON speciality_master.speciality_id = doct_speciality_mppr.speciality_id  "
        +"  INNER JOIN lab.m_test_master test_master ON test_master.test_id = lbsampledtls.test_id  "
        +"  INNER JOIN lab.m_container_master contai_mst ON contai_mst.container_id = lbsampledtls.container_id  "
        +"  INNER JOIN lab.m_sample_master samp_mst ON samp_mst.sample_id = test_master.sample_id "
        +"  INNER JOIN lab.m_priority_master pri_mst ON lbsampledtls.priority_id = pri_mst.priority_id  "
        +"  LEFT JOIN adt.m_ward_master wardmast ON order_details.ward_id = wardmast.ward_id  "
        +"  LEFT JOIN adt.m_bed_master bedmst ON order_details.bed_id = bedmst.bed_id  "
        +"  LEFT JOIN lab.t_panel_master panel_master ON panel_master.panel_id = lbsampledtls.profile_id  "
        +"WHERE  "
        +"  lbsampledtls.sample_status_id IN (:sampleStatusIds)  "
        +"  AND lbsampledtls.org_id = :orgId  "
        +"  AND lbsampledtls.org_unit_id = :orgUnitId  "
        +"  AND lbsampledtls.dept_id = :deptId  "
        +"  AND lbsampledtls.sub_dept_id = :subDeptId  "
        +"ORDER BY  "
        +"  lbsampledtls.priority_id,  "
        +"  lbsampledtls.sample_accept_datetime "),


    @NamedNativeQuery(name = "GET_BIOCHEMISTRY_WORKLIST_COUNT",
        query ="  SELECT  "
            +"  COUNT(*)  "
            +"FROM  "
            +"  lab.t_lab_sample_dtls lbsampledtls  "
            +"WHERE  "
            +"  lbsampledtls.sample_status_id IN (:sampleStatusIds)  "
            +"  AND lbsampledtls.org_id = :orgId  "
            +"  AND lbsampledtls.org_unit_id = :orgUnitId  "
            +"  AND lbsampledtls.dept_id = :deptId  "
            +"  AND lbsampledtls.sub_dept_id = :subDeptId  "),

    @NamedNativeQuery(name = "GET_FIRST_LEVEL_RESULT",
        query = "SELECT labresdtls.first_level_result FROM lab.t_lab_res_dtls labresdtls "
            + " WHERE labresdtls.lab_result_id = :labResultId " + " AND labresdtls.org_id = :orgId "
            + " AND labresdtls.org_unit_id = :orgUnitId "),
    
    @NamedNativeQuery(name = "SEARCH_BIOCHECMISTRY_PATIENT_LIST", query = 
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
			+ " AND lab_sample_dtls.sample_status_id IN (:sampleStatusIds)"
			+ " AND lab_sample_dtls.dept_id =:deptId"
			+ " AND lab_sample_dtls.sub_dept_id =:subDeptId"
			+ " AND lab_sample_mst.visit_type_id IN (:visitTypes)"
			+ " AND ( "
			+ "  LOWER(pati_mst.first_name) LIKE (:searchKeyword)"
			+ "  OR lower(pati_mst.last_name) LIKE lower(:searchKeyword) "
			+ " OR lower(pati_mst.uhid_number) LIKE lower(:searchKeyword))"),

    @NamedNativeQuery(name = "SEARCH_BIOCHECMISTRY_SAMPLE_NO_LIST", query = "SELECT  "
		+ " lab_sample_dtls.sample_no AS \"sampleNo\", "
		+ " lab_sample_dtls.lab_sample_dtls_id AS \"labSampleDtlsId\" "
		+ " FROM lab.t_lab_sample_dtls  lab_sample_dtls "
		+ " INNER JOIN lab.t_lab_sample_master lab_sample_mst ON lab_sample_dtls.patient_id = lab_sample_mst.patient_id    "
		+ " WHERE  "
		+ " lab_sample_dtls.org_id =:orgId "
		+ " AND lab_sample_dtls.org_unit_id =:orgUnitId "
		+ " AND lab_sample_dtls.sample_status_id IN (:sampleStatusIds)"
		+ " AND lab_sample_dtls.dept_id =:deptId "
		+ " AND lab_sample_mst.visit_type_id IN (:visitTypes)"
		+ " AND lab_sample_dtls.sub_dept_id =:subDeptId"
		+ " AND (CAST(lab_sample_dtls.sample_no AS text) LIKE (:searchKeyword))")

})
@Entity
@Table(name = "t_lab_sample_dtls", schema = "lab")
@SequenceGenerator(name = "m_seq_lab_sample_dtls", sequenceName = "lab.m_seq_lab_sample_dtls",
    allocationSize = 1)
public class LabSampleDetailsMaster {
  @Id
  @Column(name = "lab_sample_dtls_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_lab_sample_dtls")
  private int labSampleDtlsId;

  @Column(name = "org_id")
  private Integer orgId;

  @Column(name = "org_unit_id")
  private Integer orgUnitId;

  @Column(name = "test_id")
  private Integer testId;

  @Column(name = "dept_id")
  private Integer deptId;

  @Column(name = "sub_dept_id")
  private Integer subDeptId;

  @Column(name = "lab_sample_id")
  private Integer labSampleId;

  @Column(name = "sample_barcode")
  private Integer sampleBarcode;

  @Column(name = "sample_no")
  private BigInteger sampleNo;
  
  @Column(name = "order_details_id")
  private Integer orderDetailsId;

  @Column(name = "order_id")
  private Integer orderId;

  @Column(name = "profile_id")
  private Integer profileId;

  @Column(name = "package_id")
  private Integer packageId;

  @Column(name = "is_centrifugation_req")
  private Character isCentrifugationReq;

  @Column(name = "is_alliquote_req")
  private Character isAlliquoteReq;

  @Column(name = "sample_type_id")
  private Integer sampleTypeId;

  @Column(name = "container_id")
  private Integer containerId;

  @Column(name = "sample_req_count")
  private Integer sampleReqCount;

  @Column(name = "sample_pending_count")
  private Integer samplePendingCount;

  @Column(name = "sample_gen_datetime")
  @Convert(converter = LocalTimeConverter.class)
  private Long sampleGenDatetime;

  @Column(name = "sample_gen_by")
  private Integer sampleGenBy;

  @Column(name = "curr_status")
  private Character currStatus;

  @Column(name = "sample_status_id")
  private Integer sampleStatusId;

  @Column(name = "is_outsourced")
  private Character isOutsourced;

  @Column(name = "outsourced_lab_id")
  private Integer outsourcedLabId;

  @Column(name = "patient_id")
  private Integer patientId;

  @Column(name = "doctor_id")
  private Integer doctorId;

  @Column(name = "priority_id")
  private Integer priorityId;

  @Column(name = "samp_wrk_status_id")
  private Integer sampWrkStatusId;

  @Column(name = "sample_sendtocr_datetime")
  @Convert(converter = LocalTimeConverter.class)
  private Long sampleSendtocrDatetime;

  @Column(name = "sample_sendtocr_by")
  private Integer sampleSendtocrBy;

  @Column(name = "sample_collection_datetime")
  @Convert(converter = LocalTimeConverter.class)
  private Long sampleCollectionDatetime;

  @Column(name = "sample_collection_by")
  private Integer sampleCollectionBy;

  @Column(name = "sample_centrifugation_datetime")
  @Convert(converter = LocalTimeConverter.class)
  private Long sampleCentrifugationDatetime;

  @Column(name = "sample_centrifugation_by")
  private Integer sampleCentrifugationBy;

  @Column(name = "sample_accept_datetime")
  @Convert(converter = LocalTimeConverter.class)
  private Long sampleAcceptDatetime;

  @Column(name = "sample_accept_by")
  private Integer sampleAcceptBy;

  @Column(name = "sample_reject_datetime")
  @Convert(converter = LocalTimeConverter.class)
  private Long sampleRejectDatetime;

  @Column(name = "sample_reject_by")
  private Integer sampleRejectBy;

  @Column(name = "sample_reject_reason_id")
  private Integer sampleRejectReasonId;

  @Column(name = "sample_reject_reason")
  private String sampleRejectReason;

  @Column(name = "sample_recollect_flag")
  private Character sampleRecollectFlag;

  @Column(name = "sample_recollect_against_id")
  private Integer sampleRecollectAgainstId;

  @Column(name = "created_by")
  private Integer createdBy;

  @Column(name = "created_date")
  @Convert(converter = LocalTimeConverter.class)
  private Long createdDate;

  @Column(name = "updated_by")
  private Integer updatedBy;

  @Column(name = "updated_date")
  @Convert(converter = LocalTimeConverter.class)
  private Long updatedDate;

  @Column(name = "sample_test_time")
  @Convert(converter = LocalTimeConverter.class)
  private Long sampleTestTime;

  @Column(name = "patient_visit_age")
  @Convert(converter = LocalTimeConverter.class)
  private Long patientVisitAge;

  @Column(name = "gender_id")
  private Integer genderId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "lab_sample_id", insertable = false, nullable = false, updatable = false)
  private LabSampleMaster labSampleMaster;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sample_status_id", insertable = false, nullable = false, updatable = false)
  private SampleStatusMaster sampleStatusMaster;

/*  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id", insertable = false, nullable = false, updatable = false)
  private OrderMaster orderMaster;*/

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "gender_id", insertable = false, nullable = false, updatable = false)
  private GenderMaster genderMaster;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_details_id", insertable = false, nullable = false, updatable = false)
  private OrderDetailsMaster orderDetailsMaster;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "patient_id", insertable = false, nullable = false, updatable = false)
  private PatientRegistration patientRegistration;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "doctor_id", insertable = false, nullable = false, updatable = false)
  private DoctorMaster doctorMaster;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "test_id", insertable = false, nullable = false, updatable = false)
  private TestMaster testMaster;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "labSampleDetailsMaster")
  private List<TTemplateResult> listTTemplateResult;


  public int getLabSampleDtlsId() {
    return labSampleDtlsId;
  }

  public void setLabSampleDtlsId(int labSampleDtlsId) {
    this.labSampleDtlsId = labSampleDtlsId;
  }

  public Integer getOrgId() {
    return orgId;
  }

  public void setOrgId(Integer orgId) {
    this.orgId = orgId;
  }

  public Integer getOrgUnitId() {
    return orgUnitId;
  }

  public void setOrgUnitId(Integer orgUnitId) {
    this.orgUnitId = orgUnitId;
  }

  public Integer getTestId() {
    return testId;
  }

  public void setTestId(Integer testId) {
    this.testId = testId;
  }

  public Integer getDeptId() {
    return deptId;
  }

  public void setDeptId(Integer deptId) {
    this.deptId = deptId;
  }

  public Integer getSubDeptId() {
    return subDeptId;
  }

  public void setSubDeptId(Integer subDeptId) {
    this.subDeptId = subDeptId;
  }

  public Integer getLabSampleId() {
    return labSampleId;
  }

  public void setLabSampleId(Integer labSampleId) {
    this.labSampleId = labSampleId;
  }

  public Integer getSampleBarcode() {
    return sampleBarcode;
  }

  public void setSampleBarcode(Integer sampleBarcode) {
    this.sampleBarcode = sampleBarcode;
  }

  public BigInteger getSampleNo() {
    return sampleNo;
  }

  public void setSampleNo(BigInteger sampleNo) {
    this.sampleNo = sampleNo;
  }

  public Integer getOrderDetailsId() {
    return orderDetailsId;
  }

  public void setOrderDetailsId(Integer orderDetailsId) {
    this.orderDetailsId = orderDetailsId;
  }

  public Integer getOrderId() {
    return orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  public Integer getProfileId() {
    return profileId;
  }

  public void setProfileId(Integer profileId) {
    this.profileId = profileId;
  }

  public Integer getPackageId() {
    return packageId;
  }

  public void setPackageId(Integer packageId) {
    this.packageId = packageId;
  }

  public Character getIsCentrifugationReq() {
    return isCentrifugationReq;
  }

  public void setIsCentrifugationReq(Character isCentrifugationReq) {
    this.isCentrifugationReq = isCentrifugationReq;
  }

  public Character getIsAlliquoteReq() {
    return isAlliquoteReq;
  }

  public void setIsAlliquoteReq(Character isAlliquoteReq) {
    this.isAlliquoteReq = isAlliquoteReq;
  }

  public Integer getSampleTypeId() {
    return sampleTypeId;
  }

  public void setSampleTypeId(Integer sampleTypeId) {
    this.sampleTypeId = sampleTypeId;
  }

  public Integer getContainerId() {
    return containerId;
  }

  public void setContainerId(Integer containerId) {
    this.containerId = containerId;
  }

  public Integer getSampleReqCount() {
    return sampleReqCount;
  }

  public void setSampleReqCount(Integer sampleReqCount) {
    this.sampleReqCount = sampleReqCount;
  }

  public Integer getSamplePendingCount() {
    return samplePendingCount;
  }

  public void setSamplePendingCount(Integer samplePendingCount) {
    this.samplePendingCount = samplePendingCount;
  }

  public Long getSampleGenDatetime() {
    return sampleGenDatetime;
  }

  public void setSampleGenDatetime(Long sampleGenDatetime) {
    this.sampleGenDatetime = sampleGenDatetime;
  }

  public Integer getSampleGenBy() {
    return sampleGenBy;
  }

  public void setSampleGenBy(Integer sampleGenBy) {
    this.sampleGenBy = sampleGenBy;
  }

  public Character getCurrStatus() {
    return currStatus;
  }

  public void setCurrStatus(Character currStatus) {
    this.currStatus = currStatus;
  }

  public Character getIsOutsourced() {
    return isOutsourced;
  }

  public void setIsOutsourced(Character isOutsourced) {
    this.isOutsourced = isOutsourced;
  }

  public Integer getOutsourcedLabId() {
    return outsourcedLabId;
  }

  public void setOutsourcedLabId(Integer outsourcedLabId) {
    this.outsourcedLabId = outsourcedLabId;
  }

  public Long getSampleSendtocrDatetime() {
    return sampleSendtocrDatetime;
  }

  public void setSampleSendtocrDatetime(Long sampleSendtocrDatetime) {
    this.sampleSendtocrDatetime = sampleSendtocrDatetime;
  }

  public Integer getSampleSendtocrBy() {
    return sampleSendtocrBy;
  }

  public void setSampleSendtocrBy(Integer sampleSendtocrBy) {
    this.sampleSendtocrBy = sampleSendtocrBy;
  }

  public Long getSampleCollectionDatetime() {
    return sampleCollectionDatetime;
  }

  public void setSampleCollectionDatetime(Long sampleCollectionDatetime) {
    this.sampleCollectionDatetime = sampleCollectionDatetime;
  }

  public Integer getSampleCollectionBy() {
    return sampleCollectionBy;
  }

  public void setSampleCollectionBy(Integer sampleCollectionBy) {
    this.sampleCollectionBy = sampleCollectionBy;
  }

  public Long getSampleCentrifugationDatetime() {
    return sampleCentrifugationDatetime;
  }

  public void setSampleCentrifugationDatetime(Long sampleCentrifugationDatetime) {
    this.sampleCentrifugationDatetime = sampleCentrifugationDatetime;
  }

  public Integer getSampleCentrifugationBy() {
    return sampleCentrifugationBy;
  }

  public void setSampleCentrifugationBy(Integer sampleCentrifugationBy) {
    this.sampleCentrifugationBy = sampleCentrifugationBy;
  }

  public Long getSampleAcceptDatetime() {
    return sampleAcceptDatetime;
  }

  public void setSampleAcceptDatetime(Long sampleAcceptDatetime) {
    this.sampleAcceptDatetime = sampleAcceptDatetime;
  }

  public Integer getSampleAcceptBy() {
    return sampleAcceptBy;
  }

  public void setSampleAcceptBy(Integer sampleAcceptBy) {
    this.sampleAcceptBy = sampleAcceptBy;
  }

  public Long getSampleRejectDatetime() {
    return sampleRejectDatetime;
  }

  public void setSampleRejectDatetime(Long sampleRejectDatetime) {
    this.sampleRejectDatetime = sampleRejectDatetime;
  }

  public Integer getSampleRejectBy() {
    return sampleRejectBy;
  }

  public void setSampleRejectBy(Integer sampleRejectBy) {
    this.sampleRejectBy = sampleRejectBy;
  }

  public Integer getSampleRejectReasonId() {
    return sampleRejectReasonId;
  }

  public void setSampleRejectReasonId(Integer sampleRejectReasonId) {
    this.sampleRejectReasonId = sampleRejectReasonId;
  }

  public String getSampleRejectReason() {
    return sampleRejectReason;
  }

  public void setSampleRejectReason(String sampleRejectReason) {
    this.sampleRejectReason = sampleRejectReason;
  }

  public Character getSampleRecollectFlag() {
    return sampleRecollectFlag;
  }

  public void setSampleRecollectFlag(Character sampleRecollectFlag) {
    this.sampleRecollectFlag = sampleRecollectFlag;
  }

  public Integer getSampleRecollectAgainstId() {
    return sampleRecollectAgainstId;
  }

  public void setSampleRecollectAgainstId(Integer sampleRecollectAgainstId) {
    this.sampleRecollectAgainstId = sampleRecollectAgainstId;
  }

  public Integer getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Integer createdBy) {
    this.createdBy = createdBy;
  }

  public Long getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Long createdDate) {
    this.createdDate = createdDate;
  }


  public Integer getSampleStatusId() {
    return sampleStatusId;
  }

  public void setSampleStatusId(Integer sampleStatusId) {
    this.sampleStatusId = sampleStatusId;
  }

  public LabSampleMaster getLabSampleMaster() {
    return labSampleMaster;
  }

  public void setLabSampleMaster(LabSampleMaster labSampleMaster) {
    this.labSampleMaster = labSampleMaster;
  }

  public Integer getPatientId() {
    return patientId;
  }

  public void setPatientId(Integer patientId) {
    this.patientId = patientId;
  }

  public Integer getPriorityId() {
    return priorityId;
  }

  public void setPriorityId(Integer priorityId) {
    this.priorityId = priorityId;
  }

  public Integer getDoctorId() {
    return doctorId;
  }

  public void setDoctorId(Integer doctorId) {
    this.doctorId = doctorId;
  }

  public Integer getSampWrkStatusId() {
    return sampWrkStatusId;
  }

  public void setSampWrkStatusId(Integer sampWrkStatusId) {
    this.sampWrkStatusId = sampWrkStatusId;
  }

  public SampleStatusMaster getSampleStatusMaster() {
    return sampleStatusMaster;
  }

  public void setSampleStatusMaster(SampleStatusMaster sampleStatusMaster) {
    this.sampleStatusMaster = sampleStatusMaster;
  }

/*  public OrderMaster getOrderMaster() {
    return orderMaster;
  }

  public void setOrderMaster(OrderMaster orderMaster) {
    this.orderMaster = orderMaster;
  }*/

  public OrderDetailsMaster getOrderDetailsMaster() {
    return orderDetailsMaster;
  }

  public void setOrderDetailsMaster(OrderDetailsMaster orderDetailsMaster) {
    this.orderDetailsMaster = orderDetailsMaster;
  }

  public Integer getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(Integer updatedBy) {
    this.updatedBy = updatedBy;
  }

  public Long getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Long updatedDate) {
    this.updatedDate = updatedDate;
  }


  public Long getSampleTestTime() {
    return sampleTestTime;
  }

  public void setSampleTestTime(Long sampleTestTime) {
    this.sampleTestTime = sampleTestTime;
  }

  public Long getPatientVisitAge() {
    return patientVisitAge;
  }

  public void setPatientVisitAge(Long patientVisitAge) {
    this.patientVisitAge = patientVisitAge;
  }

  public Integer getGenderId() {
    return genderId;
  }

  public void setGenderId(Integer genderId) {
    this.genderId = genderId;
  }

  public GenderMaster getGenderMaster() {
    return genderMaster;
  }

  public void setGenderMaster(GenderMaster genderMaster) {
    this.genderMaster = genderMaster;
  }

  public PatientRegistration getPatientRegistration() {
    return patientRegistration;
  }

  public void setPatientRegistration(PatientRegistration patientRegistration) {
    this.patientRegistration = patientRegistration;
  }

  public DoctorMaster getDoctorMaster() {
    return doctorMaster;
  }

  public void setDoctorMaster(DoctorMaster doctorMaster) {
    this.doctorMaster = doctorMaster;
  }

  public TestMaster getTestMaster() {
    return testMaster;
  }

  public void setTestMaster(TestMaster testMaster) {
    this.testMaster = testMaster;
  }

  public List<TTemplateResult> getListTTemplateResult() {
    return listTTemplateResult;
  }

  public void setListTTemplateResult(List<TTemplateResult> listTTemplateResult) {
    this.listTTemplateResult = listTTemplateResult;
  }


}
