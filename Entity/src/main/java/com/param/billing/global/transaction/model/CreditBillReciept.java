package com.param.billing.global.transaction.model;

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

@Entity
@Table(name="t_credit_bill_reciept", schema="billing")
@SequenceGenerator(name="credit_bill_reciept_seq", sequenceName="billing.credit_bill_reciept_seq", allocationSize=1)
public class CreditBillReciept {
	@Id
	@Column(name = "credit_bill_reciept_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="credit_bill_reciept_seq")
	private int creditBillRecieptId;
	  
	@Column(name = "total_amount")
	private double totalAmount;
	  
	@Column(name = "bill_reciept_date")
	private Date billRecieptDate;
	  
	@Column(name = "bill_number")
	private String billNumber;
	  
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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "creditBillReciept")
	private List<CreditBillMasterRecieptMapper> listCreditBillMasterRecieptMapper;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "creditBillReciept")
	private List<CreditBillPaymentRecieptDetails> listCreditBillPaymentRecieptDetails;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "creditBillReciept")
	private List<CreditBillRecieptStatusLog> listCreditBillRecieptStatusLog;
	
	public int getCreditBillRecieptId() {
		return creditBillRecieptId;
	}

	public void setCreditBillRecieptId(int creditBillRecieptId) {
		this.creditBillRecieptId = creditBillRecieptId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getBillRecieptDate() {
		return billRecieptDate;
	}

	public void setBillRecieptDate(Date billRecieptDate) {
		this.billRecieptDate = billRecieptDate;
	}

	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
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

	public List<CreditBillMasterRecieptMapper> getListCreditBillMasterRecieptMapper() {
		return listCreditBillMasterRecieptMapper;
	}

	public void setListCreditBillMasterRecieptMapper(
			List<CreditBillMasterRecieptMapper> listCreditBillMasterRecieptMapper) {
		this.listCreditBillMasterRecieptMapper = listCreditBillMasterRecieptMapper;
	}

	public List<CreditBillPaymentRecieptDetails> getListCreditBillPaymentRecieptDetails() {
		return listCreditBillPaymentRecieptDetails;
	}

	public void setListCreditBillPaymentRecieptDetails(
			List<CreditBillPaymentRecieptDetails> listCreditBillPaymentRecieptDetails) {
		this.listCreditBillPaymentRecieptDetails = listCreditBillPaymentRecieptDetails;
	}

	public List<CreditBillRecieptStatusLog> getListCreditBillRecieptStatusLog() {
		return listCreditBillRecieptStatusLog;
	}

	public void setListCreditBillRecieptStatusLog(List<CreditBillRecieptStatusLog> listCreditBillRecieptStatusLog) {
		this.listCreditBillRecieptStatusLog = listCreditBillRecieptStatusLog;
	}
	
}
