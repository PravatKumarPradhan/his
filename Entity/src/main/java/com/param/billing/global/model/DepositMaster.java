package com.param.billing.global.model;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.adt.admission.model.AdmissionNote;
import com.param.billing.global.transaction.model.ActiveDepositeCreditNoteMapper;
import com.param.global.model.CompanyMaster;
import com.param.global.model.PatientRegistration;
import com.param.global.model.VisitTypeMaster;

@NamedNativeQueries({
	@NamedNativeQuery(name = "GET_CURRNET_DEPOSIT_ID",
					 query = "select setval('billing.deposit_master_seq',nextval('billing.deposit_master_seq')-1)"),
	
	@NamedNativeQuery(name = "DEPOSIT_DETAILS_SEARCH",
					 query = "select dept.deposit_no as \"depositNo\", to_char(dept.deposit_date,'DD-MM-YYYY HH24:MI:SS') as \"depositDate\", pat.uhid_number as \"uhidNumber\", "
							 + " concat(pat.first_name,' ',pat.middle_name,' ',pat.last_name) as \"patientName\", dept.deposit_type_id as \"depositTypeId\", dept.visit_type_id as \"visitTypeId\", "
							 + " dept.deposit_amount as \"depositAmount\", dept.consumed_deposit as \"consumedDeposit\", dept.available_deposit as \"availableDeposit\", "
							 + " coalesce( "
							 + " case " 
							 + " when dept.visit_type_id  = 1 "
							 + " then  (select enc.encounter_number from opd.t_encounter_master enc where enc.encounter_id = dept.visit_adm_id) "
							 + " end , "
							 + " case  "
							 + " when dept.visit_type_id  = 2 or dept.visit_type_id  = 3 "
							 + " then (select adm.admission_number from adt.t_admission adm where adm.admission_id = dept.visit_adm_id) "
							 + " end,'-') as \"visitNo\" " 
							 + " from billing.m_deposit_master dept  "
							 + " inner join patient.t_patient_registration pat  "
							 + " on pat.patient_id = dept.patient_id "
							 + " where dept.status = 'A' "
							 + " and dept.org_id=:orgId and dept.unit_id=:unitId "
							 + " and dept.available_deposit > 0 "),
	
	@NamedNativeQuery(name = "GET_AVAILABLE_DEPOSIT_BY_PATIENT",
					 query = "select dept.deposit_master_id as \"depositMasterId\", "
					 		+ "dept.deposit_no as \"depositNo\", to_char(dept.deposit_date,'DD-MM-YYYY HH24:MI:SS') as \"depositDate\", pat.uhid_number as \"uhidNumber\", "
							 + " concat(pat.first_name,' ',pat.middle_name,' ',pat.last_name) as \"patientName\", dept.deposit_type_id as \"depositTypeId\", dept.visit_type_id as \"visitTypeId\", "
							 + " dept.deposit_amount as \"depositAmount\", dept.consumed_deposit as \"consumedDeposit\", dept.available_deposit as \"availableDeposit\", "
							 + " coalesce( "
							 + " case " 
							 + " when dept.visit_type_id  = 1 "
							 + " then  (select enc.encounter_number from opd.t_encounter_master enc where enc.encounter_id = dept.visit_adm_id) "
							 + " end , "
							 + " case  "
							 + " when dept.visit_type_id  = 2 or dept.visit_type_id  = 3 "
							 + " then (select adm.admission_number from adt.t_admission adm where adm.admission_id = dept.visit_adm_id) "
							 + " end,'-') as \"visitNo\" " 
							 + " from billing.m_deposit_master dept  "
							 + " inner join patient.t_patient_registration pat on pat.patient_id = dept.patient_id "
							 + " where dept.status = 'A' "
							 + " and dept.org_id=:orgId and dept.unit_id=:unitId "
							 + " and dept.available_deposit > 0 and dept.patient_id=:patientId "
							 + " order by dept.deposit_date asc ")
})
@Entity
@Table(name = "m_deposit_master" , schema = "billing")
@SequenceGenerator(name = "deposit_master_seq" , sequenceName = "billing.deposit_master_seq" , allocationSize = 1)
public class DepositMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "deposit_master_seq")
	@Column(name = "deposit_master_id")
	private int depositMasterId;
	
	@Column(name = "org_id")
	private Integer orgId;
	
	@Column(name = "unit_id")
	private Integer unitId;
	
	@Column(name = "deposit_no")
	private String depositNo;
	
	@Column(name = "deposit_date")
	private Date depositDate;
	
	@Column(name = "payee_id")
	private Integer payeeId;
	
	@Column(name = "patient_id")
	private Integer patientId;
	
	@Column(name = "visit_type_id")
	private Integer visitTypeId;
	
	@Column(name = "visit_adm_id")
	private Integer visitAdmId;
	
	@Column(name = "adm_note_id")
	private Integer admNoteId;
	
	@Column(name = "deposit_type_id")
	private Integer depositTypeId;
	
	@Column(name = "deposit_amount")
	private double depositAmount;
	
	@Column(name = "available_deposit")
	private double availableDeposit;
	
	@Column(name = "consumed_deposit")
	private double consumedDeposit;
	
	@Column(name = "received_from")
	private String receivedFrom;
	
	@Column(name = "identification_no")
	private String identificationNo;
	
	@Column(name = "status")
	private char status;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "created_by")
	private Integer createdBy;
	
	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name = "updated_by")
	private Integer updatedBy;
	
	@Column(name = "remarks")
	private String remarks;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payee_id" , insertable = false , updatable = false , nullable = false)
	private CompanyMaster companyMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id" , insertable = false , updatable = false , nullable = false)
	private PatientRegistration patientRegistration;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "visit_type_id" , insertable = false , updatable = false , nullable = false)
	private VisitTypeMaster visitTypeMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "adm_note_id" , insertable = false , updatable = false , nullable = false)
	private AdmissionNote admissionNote;
	
	@OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "depositMaster")
	private List<PaymentDepositDetails> listPaymentDepositDetails;
	
	@OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "depositMaster")
	private List<ActiveDepositeCreditNoteMapper> listActiveDepositeCreditNoteMapper;

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public List<ActiveDepositeCreditNoteMapper> getListActiveDepositeCreditNoteMapper() {
		return listActiveDepositeCreditNoteMapper;
	}

	public void setListActiveDepositeCreditNoteMapper(List<ActiveDepositeCreditNoteMapper> listActiveDepositeCreditNoteMapper) {
		this.listActiveDepositeCreditNoteMapper = listActiveDepositeCreditNoteMapper;
	}

	public int getDepositMasterId() {
		return depositMasterId;
	}

	public void setDepositMasterId(int depositMasterId) {
		this.depositMasterId = depositMasterId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public String getDepositNo() {
		return depositNo;
	}

	public void setDepositNo(String depositNo) {
		this.depositNo = depositNo;
	}

	public Date getDepositDate() {
		return depositDate;
	}

	public void setDepositDate(Date depositDate) {
		this.depositDate = depositDate;
	}

	public Integer getPayeeId() {
		return payeeId;
	}

	public void setPayeeId(Integer payeeId) {
		this.payeeId = payeeId;
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

	public Integer getAdmNoteId() {
		return admNoteId;
	}

	public void setAdmNoteId(Integer admNoteId) {
		this.admNoteId = admNoteId;
	}

	public Integer getDepositTypeId() {
		return depositTypeId;
	}

	public void setDepositTypeId(Integer depositTypeId) {
		this.depositTypeId = depositTypeId;
	}

	public double getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(double depositAmount) {
		this.depositAmount = depositAmount;
	}

	public double getAvailableDeposit() {
		return availableDeposit;
	}

	public void setAvailableDeposit(double availableDeposit) {
		this.availableDeposit = availableDeposit;
	}

	public double getConsumedDeposit() {
		return consumedDeposit;
	}

	public void setConsumedDeposit(double consumedDeposit) {
		this.consumedDeposit = consumedDeposit;
	}

	public String getReceivedFrom() {
		return receivedFrom;
	}

	public void setReceivedFrom(String receivedFrom) {
		this.receivedFrom = receivedFrom;
	}

	public String getIdentificationNo() {
		return identificationNo;
	}

	public void setIdentificationNo(String identificationNo) {
		this.identificationNo = identificationNo;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public CompanyMaster getCompanyMaster() {
		return companyMaster;
	}

	public void setCompanyMaster(CompanyMaster companyMaster) {
		this.companyMaster = companyMaster;
	}

	public PatientRegistration getPatientRegistration() {
		return patientRegistration;
	}

	public void setPatientRegistration(PatientRegistration patientRegistration) {
		this.patientRegistration = patientRegistration;
	}

	public VisitTypeMaster getVisitTypeMaster() {
		return visitTypeMaster;
	}

	public void setVisitTypeMaster(VisitTypeMaster visitTypeMaster) {
		this.visitTypeMaster = visitTypeMaster;
	}

	public AdmissionNote getAdmissionNote() {
		return admissionNote;
	}

	public void setAdmissionNote(AdmissionNote admissionNote) {
		this.admissionNote = admissionNote;
	}

	public List<PaymentDepositDetails> getListPaymentDepositDetails() {
		return listPaymentDepositDetails;
	}

	public void setListPaymentDepositDetails(List<PaymentDepositDetails> listPaymentDepositDetails) {
		this.listPaymentDepositDetails = listPaymentDepositDetails;
	}
	
}