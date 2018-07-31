package com.param.billing.global.transaction.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.billing.global.model.DepositMaster;

@Entity
@Table(name="t_active_deposite_credit_note_mapper", schema="billing")
@SequenceGenerator(name = "active_deposite_credit_note_mapper_seq", sequenceName = "billing.active_deposite_credit_note_mapper_seq", allocationSize = 1)
public class ActiveDepositeCreditNoteMapper {
	@Id
	@Column(name = "active_deposit_credit_note_mapper_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "active_deposite_credit_note_mapper_seq")
	private int activeDepositCreditNoteMapperId;
	  
	@Column(name = "billing_payment_details_id")
	private Integer billingPaymentDetailsId;
	  
	@Column(name = "active_deposit_id")
	private Integer activeDepositId;
	  
	@Column(name = "credit_note_id")
	private Integer creditNoteId;
	  
	@Column(name = "amount")
	private double amount;
	  
	@Column(name = "date_of_transaction")
	private Date dateOfTransaction;
	  
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "billing_payment_details_id", insertable = false, updatable = false)
	private BillingPaymentDetails billingPaymentDetails;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "active_deposit_id", insertable = false, updatable = false)
	private DepositMaster depositMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "credit_note_id", insertable = false, updatable = false)
	private CreditNoteMaster creditNoteMaster;
	
	public int getActiveDepositCreditNoteMapperId() {
		return activeDepositCreditNoteMapperId;
	}

	public void setActiveDepositCreditNoteMapperId(int activeDepositCreditNoteMapperId) {
		this.activeDepositCreditNoteMapperId = activeDepositCreditNoteMapperId;
	}

	public Integer getBillingPaymentDetailsId() {
		return billingPaymentDetailsId;
	}

	public void setBillingPaymentDetailsId(Integer billingPaymentDetailsId) {
		this.billingPaymentDetailsId = billingPaymentDetailsId;
	}

	public Integer getActiveDepositId() {
		return activeDepositId;
	}

	public void setActiveDepositId(Integer activeDepositId) {
		this.activeDepositId = activeDepositId;
	}

	public Integer getCreditNoteId() {
		return creditNoteId;
	}

	public void setCreditNoteId(Integer creditNoteId) {
		this.creditNoteId = creditNoteId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(Date dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
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

	public BillingPaymentDetails getBillingPaymentDetails() {
		return billingPaymentDetails;
	}

	public void setBillingPaymentDetails(BillingPaymentDetails billingPaymentDetails) {
		this.billingPaymentDetails = billingPaymentDetails;
	}

	public DepositMaster getDepositMaster() {
		return depositMaster;
	}

	public void setDepositMaster(DepositMaster depositMaster) {
		this.depositMaster = depositMaster;
	}

	public CreditNoteMaster getCreditNoteMaster() {
		return creditNoteMaster;
	}

	public void setCreditNoteMaster(CreditNoteMaster creditNoteMaster) {
		this.creditNoteMaster = creditNoteMaster;
	}
	
}
