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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.common.LocalTimeConverter;

@NamedNativeQueries({

    @NamedNativeQuery(name = "GET_BIOCHEM_RESULT_ENTRY_LIST", query = "  SELECT  "
        + "  visit_type_master.visit_type_code AS \"visitType\",  "
        + "  panel_master.panel_code AS \"panelCode\",  "
        + "  panel_master.panel_id AS \"panelId\",  "
        + "  lbsampledtls.gender_id AS \"genderId\",  " 
        + "  lbsampledtls.org_id AS \"orgId\",  "
        + "  lbsampledtls.org_unit_id AS \"orgUnitId\",  "
        + "  lbsampledtls.lab_sample_dtls_id AS \"labSampleDtlsId\",  "
        + "  lbsampledtls.sample_recollect_flag AS \"sampleRecollectFlag\",  "
        + "  lbsampledtls.sample_recollect_against_id AS \"sampleRecollectAgainstId\",  "
        + "  pati_mst.uhid_number AS uhid,  "
        + "  lab.generate_sample_no(CAST(lbsampledtls.sample_no AS text), 'SM-', 7) AS \"sampleNo\",  "
        + "  test_mst.test_desc AS \"testDesc\",  "
        + "  order_master.admission_id AS \"visitAdmId\",  "
        + "  order_master.visit_type_id AS \"visitTypeId\",  "
        + "  lbsampledtls.dept_id AS \"deptId\",  " + "  lbsampledtls.test_id AS \"testId\",  "
        + "  test_mst.test_type AS \"testType\",  "
        + "  lbsampledtls.sub_dept_id AS \"subDeptId\",  "
        + "  lbsampledtls.patient_id AS \"patientId\",  "
        + "  lbsampledtls.order_details_id AS \"orderDetailsId\",  "
        + "  pre_mst.prefix_code || ' ' || pati_mst.first_name || ' ' || pati_mst.last_name || ' ' || EXTRACT(  "
        + "    YEAR  " + "    FROM  " + "      age(NOW(), pati_mst.dob)  "
        + "  ) || '/' || gen_mst.gender_code AS \"patientDetails\",  "
        + "  CAST(lbsampledtls.patient_visit_age AS DATE) - CAST(pati_mst.dob AS DATE) AS \"patientAgeDays\",  "
        + "  'Dr.' || doct_mst.first_name || ' ' || doct_mst.last_name || ' (' || speciality_master.speciality_name || ')' AS \"doctorDetails\",  "
        + "  pri_mst.priority_name AS \"priorityName\",  "
        + "  pri_mst.color_code AS \"colorCode\",  "
        + "  lbsampledtls.sample_barcode AS \"sampleBarcode\",  "
        + "  order_details.created_date AS \"orderDateTime\",  "
        + "  lbsampledtls.sample_collection_datetime AS \"sampleCollectionDatetime\" , " 
        + "  wardmast.ward_code AS \"wardCode\" , " 
        + "  bedmst.bed_code AS \"bedNumber\",  " 
        + "  test_mst.test_code AS \"testCode\" , " 
        + "  samp_mst.sample_desc AS \"sampleType\"  " 
        + "FROM  "
        + "  lab.t_lab_sample_dtls lbsampledtls  "
        + "  INNER JOIN public.t_order_details order_details ON order_details.order_details_id = lbsampledtls.order_details_id  "
        + "  INNER JOIN public.t_order_master order_master ON order_master.order_id = order_details.order_id  "
        + "  INNER JOIN public.m_visit_type_master visit_type_master ON visit_type_master.visit_type_id = order_master.visit_type_id  "
        + "  INNER JOIN lab.m_test_master test_mst ON lbsampledtls.test_id = test_mst.test_id  "
        + "  INNER JOIN patient.t_patient_registration pati_mst ON lbsampledtls.patient_id = pati_mst.patient_id  "
        + "  INNER JOIN m_prefix_master pre_mst ON pati_mst.prefix_id = pre_mst.prefix_id  "
        + "  INNER JOIN m_gender_master gen_mst ON pati_mst.gender_id = gen_mst.gender_id  "
        + "  INNER JOIN doctor.m_sub_speciality_master subspe_mst ON subspe_mst.sub_speciality_id = lbsampledtls.sub_dept_id  "
        + "  INNER JOIN doctor.m_doctor_master doct_mst ON order_master.ord_doctor_id = doct_mst.doctor_id  "
        + "  INNER JOIN doctor.t_doctor_speciality_mapper doct_speciality_mppr ON doct_speciality_mppr.doctor_id = order_master.ord_doctor_id  "
        + "  INNER JOIN doctor.m_speciality_master speciality_master ON speciality_master.speciality_id = doct_speciality_mppr.speciality_id  "
        + "  INNER JOIN lab.m_test_master test_master ON test_master.test_id = lbsampledtls.test_id  "
        + "  INNER JOIN lab.m_container_master contai_mst ON contai_mst.container_id = lbsampledtls.container_id  "
        + "  INNER JOIN lab.m_sample_master samp_mst ON samp_mst.sample_id = test_master.sample_id  "
        + "  INNER JOIN lab.m_priority_master pri_mst ON lbsampledtls.priority_id = pri_mst.priority_id  "
        + "  LEFT JOIN adt.m_ward_master wardmast ON order_details.ward_id = wardmast.ward_id  "
        + "  LEFT JOIN adt.m_bed_master bedmst ON order_details.bed_id = bedmst.bed_id  "
        + "  LEFT JOIN lab.t_panel_master panel_master ON panel_master.panel_id = lbsampledtls.profile_id  "
        + "WHERE  " 
        + "  lbsampledtls.sample_status_id = :sampleStatusId  "
        + "  AND lbsampledtls.org_id = :orgId  " 
        + "  AND lbsampledtls.org_unit_id = :orgUnitId  "
        + "  AND lbsampledtls.dept_id = :deptId  " 
        + "  AND lbsampledtls.sub_dept_id = :subDeptId  "
        + "ORDER BY  " 
        + "  lbsampledtls.priority_id,  "
        + "  lbsampledtls.sample_accept_datetime  "),


    @NamedNativeQuery(name = "GET_BIOCHEM_RESULT_ENTRY_LIST_COUNT",
        query = " SELECT  " + "  COUNT(*)  " + "FROM  " + "  lab.t_lab_sample_dtls lbsampledtls  "
            + "WHERE  " + "  lbsampledtls.sample_status_id = :sampleStatusId  "
            + "  AND lbsampledtls.org_id = :orgId  "
            + "  AND lbsampledtls.org_unit_id = :orgUnitId  "
            + "  AND lbsampledtls.dept_id = :deptId  "
            + "  AND lbsampledtls.sub_dept_id = :subDeptId  "),



    @NamedNativeQuery(name = "GET_BIOCHEM_VALIDATION_ENTRY", query = " SELECT  "
        + "  max(labresmst.lab_test_res_id) AS \"labTestResId\",  "
        + "  labresmst.test_id AS \"testId\",  "
        + "  labresmst.dept_id AS \"deptId\",  " 
        + "  labresmst.sub_dept_id AS \"subDeptId\",  " 
        + "  labresmst.patient_id AS \"patientId\",  "
        + "  labresmst.visit_type_id AS \"visitTypeId\",  "
        + "  labresmst.admission_id AS \"visitAdmId\",  "
        + "  labresmst.order_details_id AS \"orderDetailsId\",  "
        + "  labresmst.sample_no AS \"sampleNo\",  "
        + "  lbsampledtls.lab_sample_dtls_id AS \"labSampleDtlsId\",  "
        + "  labresmst.result_enter_datetime AS \"resultEnterDatetime\",  "
        + "  labresmst.result_enter_by AS \"resultEnterBy\",  "
        + "  labresmst.result_validated_datetime AS \"resultValidatedDatetime\",  "
        + "  labresmst.result_validated_by AS \"resultValidatedBy\",  "
        + "  labresmst.result_authorised_datetime AS \"resultAuthorisedDatetime\",  "
        + "  labresmst.result_authorised_by AS \"resultAuthorisedBy\",  "
        + "  labresmst.result_authorised_flag AS \"resultAuthorisedFlag\",  "
        + "  labresmst.result_handover_datetime AS \"resultHandoverDatetime\",  "
        + "  labresmst.result_handover_by AS \"resultHandoverBy\",  "
        //+ "  labresmst.created_date AS \"createdDate\",  "
        + "  labresmst.suggetion_notes AS \"suggetionNotes\",  "
        + "  labresmst.foot_notes AS \"footsNotes\",  "
        + "  labresmst.report_type AS \"reportType\",  "
        + "  panel_master.panel_code AS \"panelCode\",  "
        + "  panel_master.panel_id AS \"panelId\",  "
        + "  lbsampledtls.gender_id AS \"genderId\",  " 
        + "  lbsampledtls.sample_recollect_flag AS \"sampleRecollectFlag\",  "
        + "  lbsampledtls.sample_recollect_against_id AS \"sampleRecollectAgainstId\",  "
        + "  pati_mst.uhid_number AS uhid,  " 
        + "  lab.generate_sample_no(  "
        + "    CAST(lbsampledtls.sample_no AS TEXT),  " 
        + "    'SM-',  " 
        + "    7  "
        + "  ) AS \"labSampleNo\",  " 
        + "  test_mst.test_desc AS \"testDesc\",  "
        + "  test_mst.test_type AS \"testType\",  "
        + "  pre_mst.prefix_code || ' ' || pati_mst.first_name || ' ' || pati_mst.last_name || ' ' || EXTRACT(  "
        + "    YEAR  " 
        + "    FROM  " 
        + "      age(NOW(), pati_mst.dob)  "
        + "  ) || '/' || gen_mst.gender_code AS \"patientDetails\",  "
        + "  CAST(lbsampledtls.patient_visit_age AS DATE) - CAST(pati_mst.dob AS DATE) AS \"patientAgeDays\",  "
        + "  'Dr.' || doct_mst.first_name || ' ' || doct_mst.last_name || ' (' || speciality_master.speciality_name || ')' AS \"doctorDetails\",  "
        + "  pri_mst.priority_name AS \"priorityName\",  "
        + "  pri_mst.color_code AS \"colorCode\",  "
        + "  lbsampledtls.sample_barcode AS \"sampleBarcode\",  "
        + "  visit_type_master.visit_type_code AS \"visitType\",  "
        + "  order_details.created_date AS \"orderDateTime\",  "
        + "  lbsampledtls.sample_collection_datetime AS \"sampleCollectionDatetime\",  "
        + "  wardmast.ward_code AS \"wardCode\" , " 
        + "  bedmst.bed_code AS \"bedNumber\",  " 
        + "  test_mst.test_code AS \"testCode\" , " 
        + "  samp_mst.sample_desc AS \"sampleType\"  " 
        + "FROM  "
        + "  lab.t_lab_sample_dtls lbsampledtls  "
        + "  INNER JOIN public.t_order_details order_details ON order_details.order_details_id = lbsampledtls.order_details_id  "
        + "  INNER JOIN public.t_order_master order_master ON order_master.order_id = order_details.order_id  "
        + "  INNER JOIN public.m_visit_type_master visit_type_master ON visit_type_master.visit_type_id = order_master.visit_type_id  "
        + "  INNER JOIN lab.m_test_master test_mst ON lbsampledtls.test_id = test_mst.test_id  "
        + "  INNER JOIN patient.t_patient_registration pati_mst ON lbsampledtls.patient_id = pati_mst.patient_id  "
        + "  INNER JOIN m_prefix_master pre_mst ON pati_mst.prefix_id = pre_mst.prefix_id  "
        + "  INNER JOIN m_gender_master gen_mst ON pati_mst.gender_id = gen_mst.gender_id  "
        + "  INNER JOIN doctor.m_sub_speciality_master subspe_mst ON subspe_mst.sub_speciality_id = lbsampledtls.sub_dept_id  "
        + "  INNER JOIN doctor.m_doctor_master doct_mst ON order_master.ord_doctor_id = doct_mst.doctor_id  "
        + "  INNER JOIN doctor.t_doctor_speciality_mapper doct_speciality_mppr ON doct_speciality_mppr.doctor_id = order_master.ord_doctor_id  "
        + "  INNER JOIN doctor.m_speciality_master speciality_master ON speciality_master.speciality_id = doct_speciality_mppr.speciality_id  "
        + "  INNER JOIN lab.m_test_master test_master ON test_master.test_id = lbsampledtls.test_id  "
        + "  INNER JOIN lab.m_container_master contai_mst ON contai_mst.container_id = lbsampledtls.container_id  "
        + "  INNER JOIN lab.m_sample_master samp_mst ON samp_mst.sample_id = test_master.sample_id  "
        + "  INNER JOIN lab.m_priority_master pri_mst ON lbsampledtls.priority_id = pri_mst.priority_id  "
        + "  INNER JOIN lab.t_lab_test_result labresmst ON labresmst.lab_sample_dtls_id = lbsampledtls.lab_sample_dtls_id  "
        + "  LEFT JOIN adt.m_ward_master wardmast ON order_details.ward_id = wardmast.ward_id  "
        + "  LEFT JOIN adt.m_bed_master bedmst ON order_details.bed_id = bedmst.bed_id  "
        + "  LEFT JOIN lab.t_panel_master panel_master ON panel_master.panel_id = lbsampledtls.profile_id  "
        + "WHERE  " 
        + "  lbsampledtls.sample_status_id = :sampleStatusId  "
        + "  AND lbsampledtls.org_id = :orgId  " 
        + "  AND lbsampledtls.org_unit_id = :orgUnitId  "
        + "  AND lbsampledtls.dept_id = :deptId  " 
        + "  AND lbsampledtls.sub_dept_id = :subDeptId  "
        + "  AND labresmst.lab_test_res_id =(  " 
        + "    SELECT  "
        + "      MAX(labres.lab_test_res_id)  " 
        + "    FROM  "
        + "      lab.t_lab_test_result labres  " 
        + "    WHERE  "
        + "      lbsampledtls.lab_sample_dtls_id = labres.lab_sample_dtls_id  " 
        + "  )  "
        + "GROUP BY  " 
        + "  labresmst.lab_test_res_id,  " 
        + "  lbsampledtls.lab_sample_dtls_id,  "
        + "  visit_type_master.visit_type_id,  " 
        + "  pati_mst.patient_id,  "
        + "  test_mst.test_id,  " 
        + "  pre_mst.prefix_id,  " 
        + "  gen_mst.gender_id,  "
        + "  doct_mst.doctor_id,  " 
        + "  pri_mst.priority_id,  " 
        + "  wardmast.ward_id,  "
        + "  bedmst.bed_id,  " 
        + "  samp_mst.sample_id , " 
        + "  panel_master.panel_id,  " 
        + "  speciality_master.speciality_id,  "
        + "  order_details.order_details_id  " 
        + "ORDER BY  " 
        + "  lbsampledtls.priority_id,  "
        + "  lbsampledtls.created_date  "),


    @NamedNativeQuery(name = "GET_BIOCHEM_VALIDATION_ENTRY_LIST_COUNT",
        query = " SELECT " + "	COUNT(*) " + "FROM " + "	lab.t_lab_sample_dtls lbsampledtls "
            + "WHERE " + "	lbsampledtls.sample_status_id = :sampleStatusId "
            + "	AND lbsampledtls.org_id = :orgId " + "	AND lbsampledtls.org_unit_id = :orgUnitId "
            + "	AND lbsampledtls.dept_id = :deptId "
            + " AND lbsampledtls.sub_dept_id =:subDeptId "),

    /** REPORT HAND_OVER_ENTRY_LIST */
    @NamedNativeQuery(name = "GET_BIOCHEM_REPORT_HAND_OVER_ENTRY_LIST",
        query = " SELECT "
            +"  MAX(labresmst.lab_test_res_id) AS \"labTestResId\", "
            +"  labresmst.test_id AS \"testId\", "
            +"  labresmst.dept_id AS \"deptId\", "
            +"  labresmst.sub_dept_id AS \"subDeptId\", "
            +"  labresmst.patient_id AS \"patientId\", "
            +"  labresmst.visit_type_id AS \"visitTypeId\", "
            +"  labresmst.created_by AS \"createdBy\", "
            +"  labresmst.admission_id AS \"visitAdmId\", "
            +"  labresmst.order_details_id AS \"orderDetailsId\", "
            +"  subspe_mst.sub_speciality_name AS \"deptName\", "
            +"  labresmst.result_enter_datetime AS \"resultEnterDatetime\", "
            +"  labresmst.result_enter_by AS \"resultEnterBy\", "
            +"  vistmst.visit_type_code AS \"visitType\", "
            +"  lbsampledtls.lab_sample_dtls_id AS \"labSampleDtlsId\", "
            +"  pati_mst.uhid_number AS uhid, "
            +"  lab.generate_sample_no(CAST(lbsampledtls.sample_no AS TEXT), 'SM-', 7) AS \"labSampleNo\", "
            +"  test_mst.test_desc AS \"testDesc\", "
            +"  test_mst.test_type AS \"testType\", "
            +"  pre_mst.prefix_code || ' ' || pati_mst.first_name || ' ' || pati_mst.last_name AS \"patientDetails\", "
            +"  EXTRACT( "
            +"    YEAR "
            +"    FROM "
            +"      age(lbsampledtls.patient_visit_age, pati_mst.dob) "
            +"  ) AS \"patientAgeInYears\", "
            +"  gen_mst.gender_name \"patientGender\", "
            +"  CURRENT_DATE - CAST(pati_mst.dob AS DATE) AS \"patientAgeDays\", "
            +"  'Dr.' || doct_mst.first_name || ' ' || doct_mst.last_name AS \"doctorDetails\", "
            +"  pri_mst.priority_name AS \"priorityName\", "
            +"  pri_mst.color_code AS \"colorCode\", "
            +"  lbsampledtls.sample_barcode AS \"sampleBarcode\" "
            +"FROM "
            +"  lab.t_lab_sample_dtls lbsampledtls "
            +"  INNER JOIN lab.m_test_master test_mst ON lbsampledtls.test_id = test_mst.test_id "
            +"  LEFT JOIN lab.t_lab_test_result labresmst ON labresmst.lab_sample_dtls_id = lbsampledtls.lab_sample_dtls_id "
            +"  INNER JOIN patient.t_patient_registration pati_mst ON lbsampledtls.patient_id = pati_mst.patient_id "
            +"  INNER JOIN doctor.m_doctor_master doct_mst ON lbsampledtls.doctor_id = doct_mst.doctor_id "
            +"  INNER JOIN m_prefix_master pre_mst ON pati_mst.prefix_id = pre_mst.prefix_id "
            +"  INNER JOIN m_gender_master gen_mst ON pati_mst.gender_id = gen_mst.gender_id "
            +"  INNER JOIN lab.m_priority_master pri_mst ON lbsampledtls.priority_id = pri_mst.priority_id "
            +"  INNER JOIN doctor.m_sub_speciality_master subspe_mst ON subspe_mst.sub_speciality_id = lbsampledtls.sub_dept_id "
            +"  LEFT JOIN lab.m_container_master contai_mst ON contai_mst.container_id = lbsampledtls.container_id "
            +"  INNER JOIN lab.t_lab_sample_master labsammst ON labsammst.lab_sample_id = lbsampledtls.lab_sample_id "
            +"  INNER JOIN m_visit_type_master vistmst ON vistmst.visit_type_id = labsammst.visit_type_id "
            +"  INNER JOIN public.t_order_master ord_mst ON ord_mst.order_id = lbsampledtls.order_id "
            +"WHERE "
            +"  lbsampledtls.sample_status_id = :sampleStatusId "
            +"  AND lbsampledtls.org_id = :orgId "
            +"  AND lbsampledtls.org_unit_id = :orgUnitId "
            +"  AND lbsampledtls.dept_id = :deptId "
            +"  AND lbsampledtls.sub_dept_id IN(:subDeptIds) "
            +"  AND lbsampledtls.sample_status_id <> 4 "
            +"GROUP BY "
            +"  labresmst.lab_test_res_id, "
            +"  lbsampledtls.lab_sample_dtls_id, "
            +"  vistmst.visit_type_id, "
            +"  pati_mst.patient_id, "
            +"  test_mst.test_id, "
            +"  pre_mst.prefix_id, "
            +"  gen_mst.gender_id, "
            +"  doct_mst.doctor_id, "
            +"  pri_mst.priority_id, "
            +"  subspe_mst.sub_speciality_name "
            +"ORDER BY "
            +"  lbsampledtls.priority_id, "
            +"  lbsampledtls.created_date "),


    /** REPORT_HAND_OVER_COUNT */
    @NamedNativeQuery(name = "GET_BIOCHEM_REPORT_HAND_OVER_COUNT",
        query = "  SELECT "
            +"  COUNT(*) "
            +"FROM "
            +"  lab.t_lab_sample_dtls lbsampledtls "
            +"WHERE "
            +"  lbsampledtls.sample_status_id = :sampleStatusId "
            +"  AND lbsampledtls.org_id = :orgId "
            +"  AND lbsampledtls.sample_status_id <> 4 "
            +"  AND lbsampledtls.org_unit_id = :orgUnitId "
            +"  AND lbsampledtls.dept_id = :deptId "
            +"  AND lbsampledtls.sub_dept_id IN(:subDeptIds) "),


    /** @Query for Lab Parameter history */
    @NamedNativeQuery(name = "GET_PARAMETER_FOR_HISTORY",
        query = "  SELECT  " 
            + " parameter_master.parameter_id AS \"parameterId\",  "
            + " parameter_master.parameter_name AS \"parameterName\", " 
            + " parameter_master.refrange_type_id AS \"refrangeTypeId\"  " 
            + "FROM  "
            + " lab.m_test_master test_master  "
            + "INNER JOIN lab.t_test_parameter_mppr test_parameter_mppr ON  "
            + " test_parameter_mppr.test_id = test_master.test_id  "
            + "INNER JOIN lab.m_parameter_master parameter_master ON  "
            + " parameter_master.parameter_id = test_parameter_mppr.parameter_id  " + "WHERE  "
            + " test_master.test_id =:testId  "
            + " AND test_master.is_histo_cyto = 'N'  "
            + " AND parameter_master.status = 'A'  "
            + " AND test_parameter_mppr.is_deleted = 'N'  "
            + " AND test_parameter_mppr.org_id = :orgId  "
            + " AND test_parameter_mppr.org_unit_id =:orgUnitId   "),
    
    
    @NamedNativeQuery(name="GET_RETEST_RECOLLECT_PARAMETERS",query=" SELECT "
        +"  CAST( "
        +"      lab_res_dtls.final_result AS TEXT "
        +"  ) AS \"result\", "
        +"  lab_res_dtls.created_date AS \"createdDate\" "
        +"FROM "
        +"  lab.t_lab_res_dtls lab_res_dtls "
        +"WHERE "
        +"  lab_res_dtls.lab_result_id =:labResultId "
        +"  AND lab_res_dtls.parameter_id =:parameterId "
        +"ORDER BY "
        +"  lab_res_dtls.created_date DESC "),

    @NamedNativeQuery(name = "GET_PRIVIOUS_TEST_RELEASE_ID", query = " SELECT  "
        +" MAX( lab_test_result.lab_test_res_id )  "
        +"FROM  "
        +" lab.t_lab_test_result lab_test_result  "
        +"INNER JOIN lab.t_lab_res_dtls lab_res_dtls ON  "
        +" lab_res_dtls.lab_result_id = lab_test_result.lab_test_res_id  "
        +"WHERE  "
        +" lab_test_result.result_level = 3  "
        +" AND lab_test_result.sample_status_id = :sampleStatusId  "
        +" AND lab_test_result.org_id =:orgId  "
        +" AND lab_test_result.org_unit_id =:orgUnitId  "
        +" AND lab_test_result.dept_id =:deptId  "
        +" AND lab_test_result.sub_dept_id =:subDeptId  "
        +" AND lab_test_result.patient_id =:patientId  "
        +" AND lab_test_result.test_id =:testId  "),



    @NamedNativeQuery(name = "GET_RETEST_RECOLLECT_LAB_RESULT_ID",
        query =   " WITH RECURSIVE sample_dtls AS(  "
            +"  SELECT  "
            +"    labsamp_one.lab_sample_dtls_id,  "
            +"    labsamp_one.sample_recollect_against_id,  "
            +"    labsamp_one.test_id  "
            +"  FROM  "
            +"    lab.t_lab_sample_dtls labsamp_one  "
            +"  WHERE  "
            +"    labsamp_one.lab_sample_dtls_id = :labSampleDtlsId  "
            +"    AND labsamp_one.sample_recollect_against_id IS NOT NULL  "
            +"  UNION  "
            +"  SELECT  "
            +"    labsamp_two.lab_sample_dtls_id,  "
            +"    labsamp_two.sample_recollect_against_id,  "
            +"    labsamp_two.test_id  "
            +"  FROM  "
            +"    lab.t_lab_sample_dtls labsamp_two,  "
            +"    sample_dtls  "
            +"  WHERE  "
            +"    labsamp_two.lab_sample_dtls_id = sample_dtls.sample_recollect_against_id  "
            +"    AND labsamp_two.sample_recollect_against_id IS NOT NULL  "
            +")  "
            +"SELECT  "
            +"  lab_test_result.lab_test_res_id AS \"labTestResId\",  "
            +"  test_mst.test_type AS \"testType\",  "
            +"  test_mst.test_id AS \"testId\",  "
            +"  lab_test_result.result_enter_datetime AS \"resultEnterDatetime\",  "
            +"  result_enter.user_name AS \"resultEnterByUser\",  "
            +"  lab_test_result.result_validated_datetime AS \"resultValidatedDatetime\",  "
            +"  result_val.user_name AS \"resultValidatedByUser\",  "
            +"  lab_test_result.result_authorised_datetime AS \"resultAuthorisedDatetime\",  "
            +"  result_auth.user_name AS \"resultAuthorisedByUser\",  "
            +"  lab_test_result.foot_notes AS \"footsNotes\" , "
            +"  lab_test_result.sample_no AS \"labSampleNo\" , "
            +"  lab_test_result.created_date AS \"createdDate\"  "
            +"FROM  "
            +"  sample_dtls sampledtls  "
            +"  INNER JOIN lab.t_lab_test_result lab_test_result ON lab_test_result.lab_sample_dtls_id = sampledtls.sample_recollect_against_id  "
            +"  INNER JOIN lab.m_test_master test_mst ON test_mst.test_id = sampledtls.test_id  "
            +"  LEFT JOIN public.m_user_master result_enter ON result_enter.user_id = lab_test_result.result_enter_by  "
            +"  LEFT JOIN public.m_user_master result_val ON result_val.user_id = lab_test_result.result_validated_by  "
            +"  LEFT JOIN public.m_user_master result_auth ON result_auth.user_id = lab_test_result.result_authorised_by  "
            +"WHERE  "
          /*  +"  lab_test_result.result_level = :resultLevel  AND"*/
            +"   lab_test_result.sample_status_id = :recollect  "
            +"UNION  "
            +"SELECT  "
            +"  lab_test_result.lab_test_res_id AS \"labTestResId\",  "
            +"  test_mst.test_type AS \"testType\",  "
            +"  test_mst.test_id AS \"testId\",  "
            +"  lab_test_result.result_enter_datetime AS \"resultEnterDatetime\",  "
            +"  result_enter.user_name AS \"resultEnterByUser\",  "
            +"  lab_test_result.result_validated_datetime AS \"resultValidatedDatetime\",  "
            +"  result_val.user_name AS \"resultValidatedByUser\",  "
            +"  lab_test_result.result_authorised_datetime AS \"resultAuthorisedDatetime\",  "
            +"  result_auth.user_name AS \"resultAuthorisedByUser\",  "
            +"  lab_test_result.foot_notes AS \"footsNotes\" , "
            +"  lab_test_result.sample_no AS \"labSampleNo\" , "
            +"  lab_test_result.created_date AS \"createdDate\"  "
            +"FROM  "
            +"  lab.t_lab_test_result lab_test_result  "
            +"  INNER JOIN lab.m_test_master test_mst ON test_mst.test_id = lab_test_result.test_id  "
            +"  LEFT JOIN public.m_user_master result_enter ON result_enter.user_id = lab_test_result.result_enter_by  "
            +"  LEFT JOIN public.m_user_master result_val ON result_val.user_id = lab_test_result.result_validated_by  "
            +"  LEFT JOIN public.m_user_master result_auth ON result_auth.user_id = lab_test_result.result_authorised_by  "
            +"WHERE  "
            +"  lab_test_result.sample_status_id = :retest  "
            +"  AND lab_test_result.org_id = :orgId  "
            +"  AND lab_test_result.org_unit_id = :orgUnitId  "
            +"  AND lab_test_result.dept_id = :deptId  "
            +"  AND lab_test_result.sub_dept_id = :subDeptId  "
            +"  AND lab_test_result.patient_id = :patientId  "
            +"  AND lab_test_result.test_id = :testId  "
            +"  AND lab_test_result.lab_sample_dtls_id = :labSampleDtlsId  "
          /*  +"  AND lab_test_result.result_level = :resultLevel  "*/

    ),


    @NamedNativeQuery(name = "GET_RESULT_ENTERY_BY_DATE_TIME",
        query = " SELECT  " + " lab_test_result.result_enter_datetime AS \"resultEnterDatetime\",  "
            + " user_master.user_name AS \"resultEnterByUser\"  " + "FROM  "
            + " lab.t_lab_test_result lab_test_result  "
            + "LEFT JOIN public.m_user_master user_master ON  "
            + " user_master.user_id = lab_test_result.result_enter_by  " + "WHERE  "
            + " lab_test_result.lab_test_res_id =:labTestResId  "
            + " AND lab_test_result.org_id =:orgId  "
            + " AND lab_test_result.org_unit_id =:orgUnitId  " ),


    @NamedNativeQuery(name = "GET_RESULT_VALIDATED_BY_DATE_TIME",
        query = "SELECT  " + " lab_test_result.result_enter_datetime AS \"resultEnterDatetime\",  "
            + " result_enter.user_name AS \"resultEnterByUser\",  "
            + " lab_test_result.result_validated_datetime AS \"resultValidatedDatetime\",  "
            + " result_val.user_name AS \"resultValidatedByUser\"  " + "FROM  "
            + " lab.t_lab_test_result lab_test_result  "
            + "LEFT JOIN public.m_user_master result_enter ON  "
            + " result_enter.user_id = lab_test_result.result_enter_by  "
            + "LEFT JOIN public.m_user_master result_val ON  "
            + " result_val.user_id = lab_test_result.result_validated_by  "
            + "WHERE  "
            + " lab_test_result.lab_test_res_id =:labTestResId  "
            + " AND lab_test_result.org_id =:orgId  "
            + " AND lab_test_result.org_unit_id =:orgUnitId  "),

    @NamedNativeQuery(name = "GET_RESULT_RELEASE_BY_DATE_TIME",
        query = "SELECT  " + " lab_test_result.result_enter_datetime AS \"resultEnterDatetime\",  "
            + " result_enter.user_name AS \"resultEnterByUser\",  "
            + " lab_test_result.result_validated_datetime AS \"resultValidatedDatetime\",  "
            + " result_val.user_name AS \"resultValidatedByUser\",  "
            + " lab_test_result.result_authorised_datetime AS \"resultAuthorisedDatetime\",  "
            + " result_auth.user_name AS \"resultAuthorisedByUser\"  " + "FROM  "
            + " lab.t_lab_test_result lab_test_result  "
            + "LEFT JOIN public.m_user_master result_enter ON  "
            + " result_enter.user_id = lab_test_result.result_enter_by  "
            + "LEFT JOIN public.m_user_master result_val ON  "
            + " result_val.user_id = lab_test_result.result_validated_by  "
            + "LEFT JOIN public.m_user_master result_auth ON  "
            + " result_auth.user_id = lab_test_result.result_authorised_by    " + "WHERE  "
            + " lab_test_result.lab_test_res_id =:labTestResId  "
            + " AND lab_test_result.org_id =:orgId  "
            + " AND lab_test_result.org_unit_id =:orgUnitId   ")


})


@Entity
@Table(name = "t_lab_test_result", schema = "lab")
@SequenceGenerator(name = "m_seq_result_entry", sequenceName = "lab.m_seq_result_entry",
    allocationSize = 1)
public class LabResultMaster {
  @Id
  @Column(name = "lab_test_res_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_result_entry")
  private int labTestResId;


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

  @Column(name = "patient_id")
  private Integer patientId;

  @Column(name = "visit_type_id")
  private Integer visitTypeId;

  @Column(name = "admission_id")
  private Integer visitAdmId;

  @Column(name = "order_details_id")
  private Integer orderDetailsId;

  @Column(name = "sample_no")
  private String sampleNo;

  @Column(name = "lab_sample_dtls_id")
  private Integer labSampleDtlsId;

  @Column(name = "result_enter_datetime")
  @Convert(converter = LocalTimeConverter.class)
  private Long resultEnterDatetime;

  @Column(name = "result_enter_by")
  private Integer resultEnterBy;

  @Column(name = "result_validated_datetime")
  @Convert(converter = LocalTimeConverter.class)
  private Long resultValidatedDatetime;

  @Column(name = "result_validated_by")
  private Integer resultValidatedBy;

  @Column(name = "result_authorised_datetime")
  @Convert(converter = LocalTimeConverter.class)
  private Long resultAuthorisedDatetime;

  @Column(name = "result_authorised_by")
  private Integer resultAuthorisedBy;

  @Column(name = "result_authorised_flag")
  private Character resultAuthorisedFlag;

  @Column(name = "result_handover_datetime")
  @Convert(converter = LocalTimeConverter.class)
  private Long resultHandoverDatetime;

  @Column(name = "result_handover_by")
  private Integer resultHandoverBy;

  @Column(name = "suggetion_notes")
  private String suggetionNotes;

  @Column(name = "foot_notes")
  private String footsNotes;

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

  @Column(name = "report_type")
  private Integer reportType;

  @Column(name = "result_level")
  private Integer resultLevel;

  @Column(name = "sample_status_id")
  private Integer sampleStatusId;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "labSampleMaster")
  private List<LabResultDetailsMaster> listLabSampleResultDetailsMaster;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "lab_sample_dtls_id", insertable = false, nullable = false, updatable = false)
  private LabSampleDetailsMaster labSampleDetailsMaster;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sample_status_id", insertable = false, nullable = false, updatable = false)
  private SampleStatusMaster sampleStatusMaster;


  public int getLabTestResId() {
    return labTestResId;
  }

  public void setLabTestResId(int labTestResId) {
    this.labTestResId = labTestResId;
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

  public Integer getPatientId() {
    return patientId;
  }

  public void setPatientId(Integer patientId) {
    this.patientId = patientId;
  }

  public Integer getVisitTypeId() {
    return visitTypeId;
  }

  public void setVisitTypeId(Integer visitTypeId) {
    this.visitTypeId = visitTypeId;
  }

  public Integer getVisitAdmId() {
    return visitAdmId;
  }

  public void setVisitAdmId(Integer visitAdmId) {
    this.visitAdmId = visitAdmId;
  }

  public Integer getOrderDetailsId() {
    return orderDetailsId;
  }

  public void setOrderDetailsId(Integer orderDetailsId) {
    this.orderDetailsId = orderDetailsId;
  }

  public String getSampleNo() {
    return sampleNo;
  }

  public void setSampleNo(String sampleNo) {
    this.sampleNo = sampleNo;
  }

  public Integer getLabSampleDtlsId() {
    return labSampleDtlsId;
  }

  public void setLabSampleDtlsId(Integer labSampleDtlsId) {
    this.labSampleDtlsId = labSampleDtlsId;
  }

  public Long getResultEnterDatetime() {
    return resultEnterDatetime;
  }

  public void setResultEnterDatetime(Long resultEnterDatetime) {
    this.resultEnterDatetime = resultEnterDatetime;
  }

  public Integer getResultEnterBy() {
    return resultEnterBy;
  }

  public void setResultEnterBy(Integer resultEnterBy) {
    this.resultEnterBy = resultEnterBy;
  }

  public Long getResultValidatedDatetime() {
    return resultValidatedDatetime;
  }

  public void setResultValidatedDatetime(Long resultValidatedDatetime) {
    this.resultValidatedDatetime = resultValidatedDatetime;
  }

  public Integer getResultValidatedBy() {
    return resultValidatedBy;
  }

  public void setResultValidatedBy(Integer resultValidatedBy) {
    this.resultValidatedBy = resultValidatedBy;
  }

  public Long getResultAuthorisedDatetime() {
    return resultAuthorisedDatetime;
  }

  public void setResultAuthorisedDatetime(Long resultAuthorisedDatetime) {
    this.resultAuthorisedDatetime = resultAuthorisedDatetime;
  }

  public Integer getResultAuthorisedBy() {
    return resultAuthorisedBy;
  }

  public void setResultAuthorisedBy(Integer resultAuthorisedBy) {
    this.resultAuthorisedBy = resultAuthorisedBy;
  }

  public Character getResultAuthorisedFlag() {
    return resultAuthorisedFlag;
  }

  public void setResultAuthorisedFlag(Character resultAuthorisedFlag) {
    this.resultAuthorisedFlag = resultAuthorisedFlag;
  }

  public Long getResultHandoverDatetime() {
    return resultHandoverDatetime;
  }

  public void setResultHandoverDatetime(Long resultHandoverDatetime) {
    this.resultHandoverDatetime = resultHandoverDatetime;
  }

  public Integer getResultHandoverBy() {
    return resultHandoverBy;
  }

  public void setResultHandoverBy(Integer resultHandoverBy) {
    this.resultHandoverBy = resultHandoverBy;
  }

  public String getSuggetionNotes() {
    return suggetionNotes;
  }

  public void setSuggetionNotes(String suggetionNotes) {
    this.suggetionNotes = suggetionNotes;
  }

  public String getFootsNotes() {
    return footsNotes;
  }

  public void setFootsNotes(String footsNotes) {
    this.footsNotes = footsNotes;
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

  public List<LabResultDetailsMaster> getListLabSampleResultDetailsMaster() {
    return listLabSampleResultDetailsMaster;
  }

  public void setListLabSampleResultDetailsMaster(
      List<LabResultDetailsMaster> listLabSampleResultDetailsMaster) {
    this.listLabSampleResultDetailsMaster = listLabSampleResultDetailsMaster;
  }

  public LabSampleDetailsMaster getLabSampleDetailsMaster() {
    return labSampleDetailsMaster;
  }

  public void setLabSampleDetailsMaster(LabSampleDetailsMaster labSampleDetailsMaster) {
    this.labSampleDetailsMaster = labSampleDetailsMaster;
  }

  public Integer getReportType() {
    return reportType;
  }

  public void setReportType(Integer reportType) {
    this.reportType = reportType;
  }

  public Integer getSubDeptId() {
    return subDeptId;
  }

  public void setSubDeptId(Integer subDeptId) {
    this.subDeptId = subDeptId;
  }

  public Integer getResultLevel() {
    return resultLevel;
  }

  public void setResultLevel(Integer resultLevel) {
    this.resultLevel = resultLevel;
  }

  public Integer getSampleStatusId() {
    return sampleStatusId;
  }

  public void setSampleStatusId(Integer sampleStatusId) {
    this.sampleStatusId = sampleStatusId;
  }

  public SampleStatusMaster getSampleStatusMaster() {
    return sampleStatusMaster;
  }

  public void setSampleStatusMaster(SampleStatusMaster sampleStatusMaster) {
    this.sampleStatusMaster = sampleStatusMaster;
  }


}
