package com.param.entity.lis.histo;



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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.transaction.LabSampleDetailsMaster;
import com.param.global.model.AccountPayableMaster;
import com.param.global.model.DoctorMaster;
import com.param.global.model.OrderMaster;
import com.param.global.model.PatientRegistration;
import com.param.global.model.VisitTypeMaster;
import com.param.entity.lis.common.LocalTimeConverter;
@NamedNativeQueries(
{ 

@NamedNativeQuery(name = "GET_OUT_SOURCE_PENDING_COUNT", query =   "SELECT count(*) "
		+ " FROM "
		+ " lab.t_lab_sample_dtls lbsampledtls "
		+ " WHERE "
		+ " lbsampledtls.org_id = :orgId "
		+ " AND lbsampledtls.org_unit_id =:orgUnitId "
		+ " AND lbsampledtls.is_outsourced = 'N' "
		+ " AND lbsampledtls.sample_recollect_flag = 'N' "
		+ " AND lbsampledtls.sample_status_id IN (:sampleStatusIds) "),//2

		@NamedNativeQuery(name = "GET_PAGINATED_OUT_SOURCE_PENDING_LIST", query = "SELECT lbsampledtls.lab_sample_dtls_id AS \"labSampleDtlsId\", "
				+ " pati_mst.uhid_number AS \"uhid\",  "
				+ " pati_mst.patient_id  AS \"patientId\",  "
				+ " test_master.test_id AS \"testId\",  "
				+ " doct_mst.doctor_id AS \"doctorId\",  "
				+ " lbsampledtls.order_id AS \"orderId\",  "	
				+ " t_lab_sample_master.visit_type_id as \"visitTypeId\", "
				+ " pre_mst.prefix_code || ' ' || pati_mst.first_name || ' ' || pati_mst.last_name || ' ' || EXTRACT(  "
				+ " 					YEAR "
				+ " 				FROM  "
				+ " 					age( "
				+ " 						NOW(), "
				+ " 						pati_mst.dob "
				+ " 					)  " 
				+ " 				)|| '/' ||  "
				+ " gen_mst.gender_code AS \"patientDetails\",  "
				+ " 'Dr.' || doct_mst.first_name || ' ' || doct_mst.last_name AS \"doctorDetails\" , "
				+ " subspe_mst.sub_speciality_name AS \"deptName\",  "
				+ " pri_mst.priority_name AS \"priorityName\",  "
				+ " pri_mst.color_code AS \"colorCode\",  " 
				+ " lbsampledtls.sample_no AS \"labSampleNo\", "
				+ " test_master.test_desc AS \"testDesc\", " 
				+ " contai_mst.container_name AS \"containerName\", "
				+ " visit_type_master.visit_type_code as \"visitTypeCode\" "
				+ " FROM  "
				+ " lab.t_lab_sample_dtls lbsampledtls "
				+ "  INNER JOIN patient.t_patient_registration pati_mst ON "
				+ " 	 lbsampledtls.patient_id = pati_mst.patient_id "
				+ "  INNER JOIN m_prefix_master pre_mst ON  "
				+ " 	pati_mst.prefix_id = pre_mst.prefix_id " 
				+ "  INNER JOIN m_gender_master gen_mst ON  "
				+ " 	pati_mst.gender_id = gen_mst.gender_id "
				+ "  INNER JOIN lab.m_priority_master pri_mst ON "
				+ " 	lbsampledtls.priority_id = pri_mst.priority_id "
				+ "  INNER JOIN doctor.m_sub_speciality_master subspe_mst ON "
				+ " 	subspe_mst.sub_speciality_id = lbsampledtls.sub_dept_id "
				+ "  INNER JOIN lab.m_container_master contai_mst ON  "
				+ " 	contai_mst.container_id = lbsampledtls.container_id "
				+ "  INNER JOIN lab.m_test_master test_master ON " 
				+ " 	test_master.test_id = lbsampledtls.test_id"
				+ "  INNER JOIN lab.t_lab_sample_master t_lab_sample_master ON "
				+ " 	t_lab_sample_master.lab_sample_id = lbsampledtls.lab_sample_id "
				+ "  INNER JOIN public.m_visit_type_master visit_type_master ON  "
				+ " 	visit_type_master.visit_type_id= t_lab_sample_master.visit_type_id "
				+ "  INNER JOIN doctor.m_doctor_master doct_mst ON "
				+ " 	lbsampledtls.doctor_id = doct_mst.doctor_id " 
				+ " WHERE  "
				+ "  lbsampledtls.org_id =:orgId "
				+ "  AND lbsampledtls.org_unit_id =:orgUnitId "
				+ "  AND test_master.is_outsourced = 'Y'  "
				+ "  AND lbsampledtls.sample_recollect_flag = 'N' "
				+ "  AND lbsampledtls.sample_status_id NOT IN (4) "
				+ "  AND lbsampledtls.sample_status_id IN (:sampleStatusIds) "),// 2

	@NamedNativeQuery(name = "GET_PENDING_OUT_SOURCE_SAMPLE_DISPATCH_COUNT", query = " SELECT COUNT(*) " 
		+ " FROM  "
				+ "  lab.t_out_source out_source  "
		+ " WHERE  " 
				+ "  out_source.org_id =:orgId "
				+ "  AND out_source.org_unit_id =:orgUnitId "
				+ "  AND out_source.sample_status IN (:sampleStatusIds )"), //1,2

		@NamedNativeQuery(name = "GET_PENDING_OUT_SOURCE_SAMPLE_DISPATCH_LIST", query = "SELECT out_source.out_sourced_id AS \"outSourcedId\", "
				+ " pati_mst.uhid_number AS \"uhid\",  "
				+ " out_source.lab_sample_dtls_id AS \"labSampleDtlsId\",  "
				+ " pati_mst.patient_id  AS \"patientId\",  "
				+ " test_master.test_id AS \"testId\",  "
				+ " doct_mst.doctor_id AS \"doctorId\",  "
				+ " t_lab_sample_master.order_id AS \"orderId\",  "	
				+ " visit_type_master.visit_type_id as \"visitTypeId\", "
				+ " pre_mst.prefix_code || ' ' || pati_mst.first_name || ' ' || pati_mst.last_name || ' ' || EXTRACT(  "
				+ " 				YEAR  " + " 				FROM  " 
				+ " 					age(  "
				+ " 						NOW(),  "
				+ " 						pati_mst.dob  "
				+ " 					)  "
				+ " 				)|| '/' || gen_mst.gender_code AS \"patientDetails\", "
				+ "  'Dr.' || doct_mst.first_name || ' ' || doct_mst.last_name AS \"doctorDetails\" , "
				+ "   visit_type_master.visit_type_code as \"visitTypeCode\", "
				+ " 	subspe_mst.sub_speciality_name AS \"deptName\",  "
				+ " 	test_master.test_desc AS \"testDesc\", "
				+ " 	out_source.lab_sample_no AS \"labSampleNo\", "
				+ " 	pri_mst.priority_name AS \"priorityName\",  " 
				+ " 	pri_mst.color_code AS \"colorCode\", 	 "
				+ "     account_payable_mst.acc_desc AS \"outSourceLabName\"  "	 
				+ " 	FROM  "
				+ " 	lab.t_out_source out_source  "
				+ " 			INNER JOIN patient.t_patient_registration pati_mst ON  "
				+ " 				out_source.patient_id = pati_mst.patient_id  "
				+ " 			INNER JOIN m_prefix_master pre_mst ON  "
				+ " 				pati_mst.prefix_id = pre_mst.prefix_id  "
				+ " 			INNER JOIN m_gender_master gen_mst ON  "
				+ " 				pati_mst.gender_id = gen_mst.gender_id  "
				+ " 			INNER JOIN doctor.m_doctor_master doct_mst ON  "
				+ " 				 	out_source.doctor_id = doct_mst.doctor_id "
				+ " 			INNER JOIN lab.m_test_master test_master ON  "
				+ " 				test_master.test_id = out_source.test_id  "
				+ " 			INNER JOIN lab.t_lab_sample_dtls lab_sample_dtls ON  "
				+ " 				lab_sample_dtls.lab_sample_dtls_id = out_source.lab_sample_dtls_id "
				+ " 			INNER JOIN lab.t_lab_sample_master t_lab_sample_master ON  "
				+ " 				t_lab_sample_master.lab_sample_id = lab_sample_dtls.lab_sample_id "
				+ " 			INNER JOIN public.m_visit_type_master visit_type_master ON  "
				+ " 				visit_type_master.visit_type_id= t_lab_sample_master.visit_type_id "
				+ " 			INNER JOIN doctor.m_sub_speciality_master  subspe_mst ON  "
				+ " 				subspe_mst.sub_speciality_id= lab_sample_dtls.sub_dept_id "
				+ " 			INNER JOIN lab.m_priority_master pri_mst ON  "
				+ " 				pri_mst.priority_id = lab_sample_dtls.priority_id  "
				+ "             INNER JOIN public.m_account_payable_master  account_payable_mst ON  "
				+ "		             out_source.acc_payable_mst_id = account_payable_mst.acc_payable_mst_id  "
				+ " 		WHERE  "
				+ " 			 out_source.org_id =:orgId "
				+ " 			 AND out_source.org_unit_id =:orgUnitId "
				+ " 			 AND out_source.org_unit_id =:orgUnitId "
				+ " 			 AND out_source.sample_status =:sampleStatusId "),//1,2
		
		@NamedNativeQuery(name = "OUT_SOURCE_PAITENT_SEARCH_LIST", query = "SELECT "
				+ "   DISTINCT lab_sample_dtls.patient_id AS\"id\", "
				+ "   pati_mst.first_name AS \"firstName\", "
				+ "   pati_mst.last_name AS \"lastName\", "
				+ "   pati_mst.uhid_number AS \"uhid\" "
				+ " FROM "
				+ "   lab.t_out_source out_source "
				+ "   INNER JOIN lab.t_lab_sample_dtls lab_sample_dtls ON lab_sample_dtls.lab_sample_dtls_id = out_source.lab_sample_dtls_id "
				+ "   INNER JOIN patient.t_patient_registration pati_mst ON lab_sample_dtls.patient_id = pati_mst.patient_id "
				+ " WHERE "
				+ "   out_source.sample_status In (:sampleStatus) "
				+ "   AND out_source.org_id =:orgId"
				+ "   AND out_source.org_unit_id =:orgUnitId"
				+ " AND (LOWER(pati_mst.first_name) LIKE (:searchKeyword)"
				+ "  OR lower(pati_mst.last_name) LIKE lower(:searchKeyword) "
				+ " OR lower(pati_mst.uhid_number) LIKE lower(:searchKeyword))")
})
@Entity
@Table(name = "t_out_source", schema = "lab")
@SequenceGenerator(name = "seq_t_out_sourced", sequenceName = "lab.seq_t_out_sourced", allocationSize = 1)
public class OutSourceMaster {
	
	@Id
	@Column(name = "out_sourced_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_t_out_sourced")
	private int outSourcedId;
	
	@Column(name = "patient_id")
	private Integer patientId;
	
	@Column(name = "doctor_id")
	private Integer doctorId;
	
	@Column(name = "test_id")
	private Integer testId;
	
	@Column(name = "visit_type_id")
	private Integer visitTypeId;
	
	@Column(name = "upload_doc")
	private String uploadDoc;
	
	@Column(name = "notes")
	private String notes;
	
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
	
	@Column(name = "lab_sample_dtls_id")
	private Integer labSampleDtlsId;
	
	
	@Column(name = "status")
	private Character status;
	
	@Column(name = "order_id")
	private Integer orderId;
	
	@Column(name = "org_id")
	private Integer orgId;
	
	@Column(name = "org_unit_id")
	private Integer orgUnitId;

	@Column(name = "lab_sample_no")
	private BigInteger labSampleNo;
	
	@Column(name = "sample_status")
	private Integer sampleStatus;
	
	@Column(name = "acc_payable_mst_id")
	private Integer accPayableMstId;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lab_sample_dtls_id", insertable = false, nullable = false, updatable = false)
	private LabSampleDetailsMaster labSampleDetailsMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "visit_type_id", insertable = false, nullable = false, updatable = false)
	private VisitTypeMaster visitTypeMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctor_id", insertable = false, nullable = false, updatable = false)
	private DoctorMaster doctorMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id", insertable = false, nullable = false, updatable = false)
	private PatientRegistration patientRegistration;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", insertable = false, nullable = false, updatable = false)
	private OrderMaster orderMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "acc_payable_mst_id", insertable = false, nullable = false, updatable = false)
	private AccountPayableMaster accountPayableMaster;
	
	
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "outSourceMaster")
	private List<OutSourceDetailMaster> listOutSourceDetailMaster;



	public int getOutSourcedId() {
		return outSourcedId;
	}

	public void setOutSourcedId(int outSourcedId) {
		this.outSourcedId = outSourcedId;
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

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public Integer getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}



	public String getUploadDoc() {
		return uploadDoc;
	}

	public void setUploadDoc(String uploadDoc) {
		this.uploadDoc = uploadDoc;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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

	public Integer getLabSampleDtlsId() {
		return labSampleDtlsId;
	}

	public void setLabSampleDtlsId(Integer labSampleDtlsId) {
		this.labSampleDtlsId = labSampleDtlsId;
	}


	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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

	public BigInteger getLabSampleNo() {
		return labSampleNo;
	}

	public void setLabSampleNo(BigInteger labSampleNo) {
		this.labSampleNo = labSampleNo;
	}

	public LabSampleDetailsMaster getLabSampleDetailsMaster() {
		return labSampleDetailsMaster;
	}

	public void setLabSampleDetailsMaster(LabSampleDetailsMaster labSampleDetailsMaster) {
		this.labSampleDetailsMaster = labSampleDetailsMaster;
	}

	public VisitTypeMaster getVisitTypeMaster() {
		return visitTypeMaster;
	}

	public void setVisitTypeMaster(VisitTypeMaster visitTypeMaster) {
		this.visitTypeMaster = visitTypeMaster;
	}

	public DoctorMaster getDoctorMaster() {
		return doctorMaster;
	}

	public void setDoctorMaster(DoctorMaster doctorMaster) {
		this.doctorMaster = doctorMaster;
	}

	public PatientRegistration getPatientRegistration() {
		return patientRegistration;
	}

	public void setPatientRegistration(PatientRegistration patientRegistration) {
		this.patientRegistration = patientRegistration;
	}

	public OrderMaster getOrderMaster() {
		return orderMaster;
	}

	public Integer getSampleStatus() {
		return sampleStatus;
	}

	public void setSampleStatus(Integer sampleStatus) {
		this.sampleStatus = sampleStatus;
	}

	public void setOrderMaster(OrderMaster orderMaster) {
		this.orderMaster = orderMaster;
	}

	public List<OutSourceDetailMaster> getListOutSourceDetailMaster() {
		return listOutSourceDetailMaster;
	}

	public void setListOutSourceDetailMaster(List<OutSourceDetailMaster> listOutSourceDetailMaster) {
		this.listOutSourceDetailMaster = listOutSourceDetailMaster;
	}

	public Integer getAccPayableMstId() {
		return accPayableMstId;
	}

	public void setAccPayableMstId(Integer accPayableMstId) {
		this.accPayableMstId = accPayableMstId;
	}

	public AccountPayableMaster getAccountPayableMaster() {
		return accountPayableMaster;
	}

	public void setAccountPayableMaster(AccountPayableMaster accountPayableMaster) {
		this.accountPayableMaster = accountPayableMaster;
	}


}
