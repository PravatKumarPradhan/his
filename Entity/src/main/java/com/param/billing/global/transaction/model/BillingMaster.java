package com.param.billing.global.transaction.model;

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
import com.param.global.model.PatientRegistration;
import com.param.opd.coversheet.model.EncounterMaster;

/*@NamedNativeQueries({
	@NamedNativeQuery(name="GET_BILL_DETAILS_FOR_PAYMENT",
			query=" select billing.billing_master_id as \"billingMasterId\",coalesce(sum(paymentDtls.amount),0) as \"paidAmount\","
				+ "  billing.concession_amount as \"concessionAmount\",billing.net_amount AS \"netAmount\" "
				 + " from billing.m_billing_master billing "  
				 + "	left join billing.t_billing_payment_details paymentDtls" 
				 + "		on paymentDtls.billing_master_id=billing.billing_master_id" 
				 + "	inner join opd.t_encounter_master encounter"
				 + "		on encounter.encounter_id = billing.encounter_id"
			     + " where billing.billing_master_id=:billMasterId"
				 + " group by billing.billing_master_id "),
	
})*/

@NamedQueries({
	@NamedQuery(name="GET_BILL_MASTER_BY_ID", 
			query="SELECT bill.billingMasterId as billingMasterId, bill.encounterId as encounterId, "
					+ "bill.visitTypeId as visitTypeId, "
					+ " bill.patientId as patientId, "
					+ "bill.billNumber as billNumber, "
					+ "coalesce(bill.netAmount,0) as netAmount,"
					+ " coalesce(bill.discountAmount,0) as discountAmount, "
					//+ " coalesce(bill.grossAmount,0) as grossAmount, "
					+ "coalesce(bill.concessionAmount,0) as concessionAmount, "
					+ "coalesce(bill.totalBillAmount,0) as totalBillAmount, "
					+ " coalesce(bill.outstanding,0) as outstanding "
					+ " FROM BillingMaster bill "
					+ " WHERE bill.billingMasterId=:billMasterId"),
	
	@NamedQuery(name="GET_BILL_DETAILS_BY_BILL_ID",query="SELECT bill.billingMasterId as billingMasterId, "
			+ "billDtls.billingDetailsId as billingDetailsId, "
			+ "ordMast.orderNo as orderNo, "
			+ "billDtls.serviceId as serviceId, "
			+ "serviceMstr.serviceStandardName as serviceStandardName, "
			+ "billDtls.ordDocId as doctorId, "
			+ "concat('Dr. ',coalesce(doc.firstName),' ',coalesce(doc.middleName),' ',coalesce(doc.lastName)) as doctorName, "
			+ "billDtls.quantity as quantity, "
			+ "billDtls.rate as unitPrice, "
			+ "billDtls.grossAmount as grossAmount, "
			+ "billDtls.concession as concession, "
			+ "billDtls.discount as discount, "
			+ "billDtls.taxId as taxId, "
			+ "tax.name as taxName, "
			+ "tax.taxPercentage as taxPercentage, "
			+ "billDtls.taxAmount as taxAmount, "
			+ "billDtls.netAmt as netAmt, "
			+ "to_char(ordDtls.orderDate,'dd/mm/yyyy') as orderDateString "
			+ "FROM BillingMaster bill "
			+ "INNER JOIN bill.billingDetailsList billDtls "
			+ "INNER JOIN billDtls.serviceMaster serviceMstr "
			+ "INNER JOIN billDtls.orderDetailsMaster ordDtls "
			+ "INNER JOIN ordDtls.orderMaster ordMast "
			+ "INNER JOIN billDtls.doctorMaster doc "
			+ "INNER JOIN ordDtls.tax tax "
			+ "WHERE bill.billingMasterId=:billId "
			)
})

@NamedNativeQueries({
	@NamedNativeQuery(name = "GET_BILL_DETAILS_FOR_BILL_LIST_BY_FILTER",
					 query = " with billdtls as (select bill.billing_master_id as \"billingMasterId\", to_char(bill.bill_date_time,'DD-MM-YYYY HH24:MI:SS') as \"billDate\", bill.bill_number as \"billNumber\", "
							 + " pat.uhid_number as \"uhidNumber\", concat(pat.first_name,' ',pat.middle_name,' ',pat.last_name) as \"patientName\", bill.visit_type_id as \"visitTypeId\", "
							 + " pat.is_otc_reg as \"isOtcReg\", bill.organisation_id as \"orgId\", bill.unit_id as \"unitId\", bill.patient_id as \"patientId\", "
							 + " coalesce( "
							 + " case  "
							 + " when bill.visit_type_id = 1 "
							 + " then (select enc.encounter_number from opd.t_encounter_master enc where enc.encounter_id = bill.encounter_id) "
							 + " end , "
							 + " case  "
							 + " when bill.visit_type_id = 2 or bill.visit_type_id = 3 or bill.visit_type_id = 4 "
							 + " then (select adm.admission_number from adt.t_admission adm where adm.admission_id = bill.encounter_id) "
							 + " end  "
							 + " ) as \"visitNo\", "
							 + "  "
							 + " coalesce( "
							 + " case  "
							 + " when bill.visit_type_id = 1 "
							 + " then (select to_char(enc.encounter_date,'DD/MM/YYYY') from opd.t_encounter_master enc where enc.encounter_id = bill.encounter_id) "
							 + " end, "
							 + " case  "
							 + " when bill.visit_type_id = 2 or bill.visit_type_id = 3 or bill.visit_type_id = 4 "
							 + " then (select to_char(adm.doa,'DD/MM/YYYY') from adt.t_admission_details adm where adm.admission_id = bill.encounter_id) "
							 + " end) as \"visitDate\", "
							 //<---Added by kaustubh
							 + " coalesce( " 
							 + " case  "
							 + " when bill.visit_type_id = 1 "
							 + " then (select enc.encounter_id from opd.t_encounter_master enc where enc.encounter_id = bill.encounter_id) " 
							 + " end , "
							 + " case  "
							 + " when bill.visit_type_id = 2 or bill.visit_type_id = 3 or bill.visit_type_id = 4 " 
							 + " then (select adm.admission_id from adt.t_admission adm where adm.admission_id = bill.encounter_id) " 
							 + " end  "
							 + " ) as \"visitId\", "
							 + " coalesce( "
							 + " case  "
							 + " when bill.visit_type_id = 1 "
							 + " then (select enc.appointment_id from opd.t_encounter_master enc where enc.encounter_id = bill.encounter_id) " 
							 + " end  "
							 + " ) as \"appointmentId\", "
							 //--->
							 + "  "
							 + " bill.total_bill_amount as \"totalBillAmount\", bill.net_amount as \"netAmount\",  "
							 + " coalesce((bill.net_amount - bill.outstanding),0) as \"amountPaid\", bill.outstanding as \"outstanding\", bill.bill_status_id as \"billStatusId\" "
							 + "  "
							 + " from billing.m_billing_master bill  "
							 + " inner join patient.t_patient_registration pat "
							 + " on pat.patient_id = bill.patient_id "
							 + " left join billing.t_billing_payment_details billpymt "
							 + " on billpymt.billing_master_id = bill.billing_master_id "
							 + " group by bill.billing_master_id, pat.uhid_number, \"patientName\", pat.is_otc_reg) "
							 + " select * from billdtls bill "
							 + " where bill.\"orgId\" =:orgId and bill.\"unitId\" =:unitId ")
})


@Entity
@Table(name="m_billing_master", schema="billing")
@SequenceGenerator(name = "billing_master_seq", sequenceName = "billing.billing_master_seq", allocationSize = 1)
public class BillingMaster {
	@Id
	@Column(name="billing_master_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "billing_master_seq")
	private Integer billingMasterId;
	
	@Column(name = "encounter_id")
	private Integer encounterId;
	
	@Column(name = "visit_type_id")
	private Integer visitTypeId;

	@Column(name = "patient_id")
	private Integer patientId;

	@Column(name = "bill_number")
	private String billNumber;

	@Column(name = "bill_date_time")
	@Convert(converter=DateConverter.class)
	private Long billDateTime;

	@Column(name = "net_amount")
	private Double netAmount;

	@Column(name = "discount_amount")
	private Double discountAmount;

	@Column(name = "concession_amount")
	private Double concessionAmount;
	
	@Column(name = "gst")
	private Double gst;
	
	@Column(name = "total_bill_amount")
	private Double totalBillAmount;
	
	@Column(name = "outstanding")
	private Double outstanding;
	
	@Column(name = "round_off")
	private Double roundOff;

	@Column(name = "is_credit_bill")
	private Character isCreditBill;

	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "organisation_id")
	private Integer organisationId;

	@Column(name = "status")
	private Character status;

	@Column(name = "created_date")
	@Convert(converter=DateConverter.class)
	private Long createdDate;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "updated_date")
	@Convert(converter=DateConverter.class)
	private Long updatedDate;

	@Column(name = "updated_by")
	private Integer updatedBy;
	
	@Column(name="bill_status_id")
	private Integer billStatusId;
	
	@Column(name = "cr_amount")
	private Double crAmount;
	
	@Column(name = "settlement_date")
	@Convert(converter=DateConverter.class)
	private Long settlementDate;
	
	@Column(name="bill_cancelled")
	private Character billCancelled;
	
	@Column(name="bill_cancel_reason_id")
	private Integer billCancelReasonId;
	
	@Column(name = "bill_cancel_datetime")
	@Convert(converter=DateConverter.class)
	private Long billCancelDatetime;
	
	@Column(name="bill_cancel_by")
	private Integer billCancelBy;
	
	@Column(name="bill_cancel_remark")
	private String billCancelRemark;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "encounter_id", insertable = false, nullable = false, updatable = false)
	private EncounterMaster encounter;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id", insertable = false, nullable = false, updatable = false)
	private PatientRegistration patientRegistration;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "billingMaster", cascade = CascadeType.ALL, orphanRemoval = true) 
	private List<BillingDetails> billingDetailsList;
	
	public Character getBillCancelled() {
		return billCancelled;
	}

	public void setBillCancelled(Character billCancelled) {
		this.billCancelled = billCancelled;
	}

	public Integer getBillingMasterId() {
		return billingMasterId;
	}

	public void setBillingMasterId(Integer billingMasterId) {
		this.billingMasterId = billingMasterId;
	}

	public Integer getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}

	public Integer getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	public Long getBillDateTime() {
		return billDateTime;
	}

	public void setBillDateTime(Long billDateTime) {
		this.billDateTime = billDateTime;
	}

	public Double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Double getConcessionAmount() {
		return concessionAmount;
	}

	public void setConcessionAmount(Double concessionAmount) {
		this.concessionAmount = concessionAmount;
	}

	public Double getGst() {
		return gst;
	}

	public void setGst(Double gst) {
		this.gst = gst;
	}

	public Double getTotalBillAmount() {
		return totalBillAmount;
	}

	public void setTotalBillAmount(Double totalBillAmount) {
		this.totalBillAmount = totalBillAmount;
	}

	public Double getOutstanding() {
		return outstanding;
	}

	public void setOutstanding(Double outstanding) {
		this.outstanding = outstanding;
	}

	public Double getRoundOff() {
		return roundOff;
	}

	public void setRoundOff(Double roundOff) {
		this.roundOff = roundOff;
	}

	public Character getIsCreditBill() {
		return isCreditBill;
	}

	public void setIsCreditBill(Character isCreditBill) {
		this.isCreditBill = isCreditBill;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
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

	public Integer getBillStatusId() {
		return billStatusId;
	}

	public void setBillStatusId(Integer billStatusId) {
		this.billStatusId = billStatusId;
	}

	public Double getCrAmount() {
		return crAmount;
	}

	public void setCrAmount(Double crAmount) {
		this.crAmount = crAmount;
	}

	public Long getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(Long settlementDate) {
		this.settlementDate = settlementDate;
	}

	public Integer getBillCancelReasonId() {
		return billCancelReasonId;
	}

	public void setBillCancelReasonId(Integer billCancelReasonId) {
		this.billCancelReasonId = billCancelReasonId;
	}

	public Long getBillCancelDatetime() {
		return billCancelDatetime;
	}

	public void setBillCancelDatetime(Long billCancelDatetime) {
		this.billCancelDatetime = billCancelDatetime;
	}

	public Integer getBillCancelBy() {
		return billCancelBy;
	}

	public void setBillCancelBy(Integer billCancelBy) {
		this.billCancelBy = billCancelBy;
	}

	public String getBillCancelRemark() {
		return billCancelRemark;
	}

	public void setBillCancelRemark(String billCancelRemark) {
		this.billCancelRemark = billCancelRemark;
	}
	
}
