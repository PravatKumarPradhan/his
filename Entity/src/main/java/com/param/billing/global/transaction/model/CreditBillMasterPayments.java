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
@Entity
@Table(name="t_credit_bill_master_payments", schema="billing")
@SequenceGenerator(name="credit_bill_master_payments_seq", sequenceName="billing.credit_bill_master_payments_seq", allocationSize=1)
public class CreditBillMasterPayments {
	@Id
	@Column(name = "credit_bill_master_payments_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="credit_bill_master_payments_seq")
	private int creditBillMasterPaymentsId;
	
	@Column(name = "credit_bill_id")
	private Integer creditBillId;
	  
	@Column(name = "amount")
	private double amount;
	  
	@Column(name = "transaction_date")
	private Date transactionDate;
	
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
	@JoinColumn(name = "credit_bill_id", insertable = false, updatable = false)
	private CreditBillMaster creditBillMaster;
	
	public int getCreditBillMasterPaymentsId() {
		return creditBillMasterPaymentsId;
	}

	public void setCreditBillMasterPaymentsId(int creditBillMasterPaymentsId) {
		this.creditBillMasterPaymentsId = creditBillMasterPaymentsId;
	}

	public Integer getCreditBillId() {
		return creditBillId;
	}

	public void setCreditBillId(Integer creditBillId) {
		this.creditBillId = creditBillId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
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

	public CreditBillMaster getCreditBillMaster() {
		return creditBillMaster;
	}

	public void setCreditBillMaster(CreditBillMaster creditBillMaster) {
		this.creditBillMaster = creditBillMaster;
	}
	
}
