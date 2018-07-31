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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.billing.global.transaction.model.CreditBillPaymentRecieptDetails;

@Entity
@Table(name="m_payment_type_master", schema="billing")
@SequenceGenerator(name = "payment_type_master_seq", sequenceName = "billing.payment_type_master_seq", allocationSize = 1)
public class PaymentTypeMaster {
	@Id
	@Column(name = "payment_type_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_type_master_seq")
	private int paymentTypeId;
	  
	@Column(name = "payment_type_code")
	private String paymentTypeCode;
	  
	@Column(name = "payment_type_desc")
	private String paymentTypeDesc;
	
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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "paymentTypeMaster")
	private List<CreditBillPaymentRecieptDetails> listCreditBillPaymentRecieptDetails;

	public int getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(int paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	public String getPaymentTypeCode() {
		return paymentTypeCode;
	}

	public void setPaymentTypeCode(String paymentTypeCode) {
		this.paymentTypeCode = paymentTypeCode;
	}

	public String getPaymentTypeDesc() {
		return paymentTypeDesc;
	}

	public void setPaymentTypeDesc(String paymentTypeDesc) {
		this.paymentTypeDesc = paymentTypeDesc;
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

	public List<CreditBillPaymentRecieptDetails> getListCreditBillPaymentRecieptDetails() {
		return listCreditBillPaymentRecieptDetails;
	}

	public void setListCreditBillPaymentRecieptDetails(
			List<CreditBillPaymentRecieptDetails> listCreditBillPaymentRecieptDetails) {
		this.listCreditBillPaymentRecieptDetails = listCreditBillPaymentRecieptDetails;
	}

}
