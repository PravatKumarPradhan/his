package com.param.billing.global.transaction.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="t_bill_payment_entitlement_details", schema="billing")
@SequenceGenerator(name = "bill_payment_entitlement_details_seq", sequenceName = "billing.bill_payment_entitlement_details_seq", allocationSize = 1)
public class BillPaymentEntitlementDetails {
	@Id
	@Column(name = "bill_payment_entitlement_details_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bill_payment_entitlement_details_seq")
	private int billPaymentEntitlementDetailsId;
	  
	@Column(name = "bill_master_id")
	private Integer billMasterId;
	  
	@Column(name = "payment_enttilement_type_id")
	 private Integer paymentEnttilementTypeId;
	  
	@Column(name = "patient_insurance_details_id")
	private Integer patientInsuranceDetailsId;
	  
	@Column(name = "patient_credit_company_details_id")
	private Integer patientCreditCompanyDetailsId;
	  
	@Column(name = "tarrif_id")
	private Integer tarrifId;
	
	@Column(name = "unit_id")
	private Integer unitId;
	
	@Column(name = "orgnisation_id")
	private Integer orgnisationId;
	
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

	public int getBillPaymentEntitlementDetailsId() {
		return billPaymentEntitlementDetailsId;
	}

	public void setBillPaymentEntitlementDetailsId(int billPaymentEntitlementDetailsId) {
		this.billPaymentEntitlementDetailsId = billPaymentEntitlementDetailsId;
	}

	public Integer getBillMasterId() {
		return billMasterId;
	}

	public void setBillMasterId(Integer billMasterId) {
		this.billMasterId = billMasterId;
	}

	public Integer getPaymentEnttilementTypeId() {
		return paymentEnttilementTypeId;
	}

	public void setPaymentEnttilementTypeId(Integer paymentEnttilementTypeId) {
		this.paymentEnttilementTypeId = paymentEnttilementTypeId;
	}

	public Integer getPatientInsuranceDetailsId() {
		return patientInsuranceDetailsId;
	}

	public void setPatientInsuranceDetailsId(Integer patientInsuranceDetailsId) {
		this.patientInsuranceDetailsId = patientInsuranceDetailsId;
	}

	public Integer getPatientCreditCompanyDetailsId() {
		return patientCreditCompanyDetailsId;
	}

	public void setPatientCreditCompanyDetailsId(Integer patientCreditCompanyDetailsId) {
		this.patientCreditCompanyDetailsId = patientCreditCompanyDetailsId;
	}

	public Integer getTarrifId() {
		return tarrifId;
	}

	public void setTarrifId(Integer tarrifId) {
		this.tarrifId = tarrifId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getOrgnisationId() {
		return orgnisationId;
	}

	public void setOrgnisationId(Integer orgnisationId) {
		this.orgnisationId = orgnisationId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
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

}
