package com.param.billing.global.transaction.config.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name="GET_VARIABLE_PRICING_SEQ_BY_ORG_ID",
				query = "SELECT seqMaster.variablePricingSequenceId as variablePricingSequenceId, seqMaster.visitType as visitType,"
					+ " seqMaster.patientTypeSeqNo as patientTypeSeqNo , seqMaster.billingBedCategorySeqNo as billingBedCategorySeqNo,"
					+ " seqMaster.statChargeSeqNo as statChargeSeqNo, seqMaster.paymentEntitlementSeqNo as paymentEntitlementSeqNo, "
					+ " seqMaster.unitId as unitId, seqMaster.orgId as orgId, seqMaster.status as status, seqMaster.createdBy as createdBy, "
					+ " seqMaster.createdDate as createdDate, seqMaster.updatedBy as updatedBy, seqMaster.updatedDate as updatedDate"
					+ " FROM VariablePricingSequenceMaster seqMaster"
					+ " WHERE seqMaster.orgId =:orgId AND seqMaster.unitId =:unitId AND seqMaster.visitType =:visitType AND seqMaster.status = 'A'")
	
})

@Entity
@Table(name="t_variable_pricing_sequence_master", schema="billing")
@SequenceGenerator(name = "variable_pricing_sequence_master_seq", sequenceName = "billing.variable_pricing_sequence_master_seq", allocationSize = 1)
public class VariablePricingSequenceMaster {
	
	@Id
	@Column(name="variable_pricing_sequence_master_id", nullable=true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "variable_pricing_sequence_master_seq")
	private int variablePricingSequenceId;
	
	@Column(name="visit_type_id")
	private int visitType;
	
	@Column(name="patient_type_seq_no")
	private int patientTypeSeqNo;
	
	@Column(name="billing_bed_category_seq_no")
	private int billingBedCategorySeqNo;
	
	@Column(name="stat_charge_seq_no")
	private int statChargeSeqNo;
	
	@Column(name="payment_entitlement_seq_bo")
	private int paymentEntitlementSeqNo;
	
	@Column(name="unit_id")
	private int unitId;
	
	@Column(name="orgnisation_id")
	private int orgId;
	
	@Column(name="status")
	private char status;
	
	@Column(name="created_by")
	private int createdBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;

	public int getVariablePricingSequenceId() {
		return variablePricingSequenceId;
	}

	public void setVariablePricingSequenceId(int variablePricingSequenceId) {
		this.variablePricingSequenceId = variablePricingSequenceId;
	}


	public int getVisitType() {
		return visitType;
	}

	public void setVisitType(int visitType) {
		this.visitType = visitType;
	}

	public int getPatientTypeSeqNo() {
		return patientTypeSeqNo;
	}

	public void setPatientTypeSeqNo(int patientTypeSeqNo) {
		this.patientTypeSeqNo = patientTypeSeqNo;
	}

	public int getBillingBedCategorySeqNo() {
		return billingBedCategorySeqNo;
	}

	public void setBillingBedCategorySeqNo(int billingBedCategorySeqNo) {
		this.billingBedCategorySeqNo = billingBedCategorySeqNo;
	}

	public int getStatChargeSeqNo() {
		return statChargeSeqNo;
	}

	public void setStatChargeSeqNo(int statChargeSeqNo) {
		this.statChargeSeqNo = statChargeSeqNo;
	}

	public int getPaymentEntitlementSeqNo() {
		return paymentEntitlementSeqNo;
	}

	public void setPaymentEntitlementSeqNo(int paymentEntitlementSeqNo) {
		this.paymentEntitlementSeqNo = paymentEntitlementSeqNo;
	}

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = (status == '\u0000') ? 'I' : status;
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

	
	
}
