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

import com.param.billing.global.model.PaymentTypeMaster;
@Entity
@Table(name="t_credit_bill_payment_reciept_details", schema="billing")
@SequenceGenerator(name="credit_bill_payment_reciept_details_seq", sequenceName="billing.credit_bill_payment_reciept_details_seq", allocationSize=1)
public class CreditBillPaymentRecieptDetails {
	@Id
	@Column(name = "credit_bill_payment_reciept_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="credit_bill_payment_reciept_details_seq")
	private int creditBillPaymentRecieptId;
	  
	@Column(name = "credit_bill_reciept_id")
	private Integer creditBillRecieptId;
	  
	@Column(name = "reciept_no")
	private String recieptNo;
	  
	@Column(name = "reciept_date")
	private Date recieptDate;
	  
	@Column(name = "payment_type_id")
	private Integer paymentTypeId;
	  
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
	@JoinColumn(name = "credit_bill_reciept_id", insertable = false, updatable = false)
	private CreditBillReciept creditBillReciept;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payment_type_id", insertable = false, updatable = false)
	private PaymentTypeMaster paymentTypeMaster;
	
	public int getCreditBillPaymentRecieptId() {
		return creditBillPaymentRecieptId;
	}

	public void setCreditBillPaymentRecieptId(int creditBillPaymentRecieptId) {
		this.creditBillPaymentRecieptId = creditBillPaymentRecieptId;
	}

	public Integer getCreditBillRecieptId() {
		return creditBillRecieptId;
	}

	public void setCreditBillRecieptId(Integer creditBillRecieptId) {
		this.creditBillRecieptId = creditBillRecieptId;
	}

	public String getRecieptNo() {
		return recieptNo;
	}

	public void setRecieptNo(String recieptNo) {
		this.recieptNo = recieptNo;
	}

	public Date getRecieptDate() {
		return recieptDate;
	}

	public void setRecieptDate(Date recieptDate) {
		this.recieptDate = recieptDate;
	}

	public Integer getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(Integer paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
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
