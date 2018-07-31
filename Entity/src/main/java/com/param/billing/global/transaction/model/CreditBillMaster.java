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
@Table(name="t_credit_bill_master", schema="billing")
@SequenceGenerator(name="credit_bill_master_seq", sequenceName="billing.credit_bill_master_seq", allocationSize=1)
public class CreditBillMaster {
	@Id
	@Column(name = "credit_bill_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="credit_bill_master_seq")
	private int creditBillId;
	  
	@Column(name = "appointment_id")
	private Integer appointmentId;
	  
	@Column(name = "authorization_date")
	private Date authorizationDate;
	  
	@Column(name = "authorization_amount")
	private double authorizationAmount;
	  
	@Column(name = "authorization_code")
	private String authorizationCode;
	  
	@Column(name = "bill_date")
	private Date billDate;
	  
	@Column(name = "bill_number")
	private String billNumber;
	  
	@Column(name = "total_bill_amount")
	private double totalBillAmount;
	  
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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "creditBillMaster")
	private List<CreditBillMasterPayments> listCreditBillMasterPayments;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "creditBillMaster")
	private List<CreditBillMasterRecieptMapper> listCreditBillMasterRecieptMapper;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "creditBillMaster")
	private List<CreditBillMasterStatusLog> listCreditBillMasterStatusLog;
	
	public int getCreditBillId() {
		return creditBillId;
	}

	public void setCreditBillId(int creditBillId) {
		this.creditBillId = creditBillId;
	}

	public Integer getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Date getAuthorizationDate() {
		return authorizationDate;
	}

	public void setAuthorizationDate(Date authorizationDate) {
		this.authorizationDate = authorizationDate;
	}

	public double getAuthorizationAmount() {
		return authorizationAmount;
	}

	public void setAuthorizationAmount(double authorizationAmount) {
		this.authorizationAmount = authorizationAmount;
	}

	public String getAuthorizationCode() {
		return authorizationCode;
	}

	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	public double getTotalBillAmount() {
		return totalBillAmount;
	}

	public void setTotalBillAmount(double totalBillAmount) {
		this.totalBillAmount = totalBillAmount;
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

	public List<CreditBillMasterPayments> getListCreditBillMasterPayments() {
		return listCreditBillMasterPayments;
	}

	public void setListCreditBillMasterPayments(List<CreditBillMasterPayments> listCreditBillMasterPayments) {
		this.listCreditBillMasterPayments = listCreditBillMasterPayments;
	}

	public List<CreditBillMasterRecieptMapper> getListCreditBillMasterRecieptMapper() {
		return listCreditBillMasterRecieptMapper;
	}

	public void setListCreditBillMasterRecieptMapper(
			List<CreditBillMasterRecieptMapper> listCreditBillMasterRecieptMapper) {
		this.listCreditBillMasterRecieptMapper = listCreditBillMasterRecieptMapper;
	}

	public List<CreditBillMasterStatusLog> getListCreditBillMasterStatusLog() {
		return listCreditBillMasterStatusLog;
	}

	public void setListCreditBillMasterStatusLog(List<CreditBillMasterStatusLog> listCreditBillMasterStatusLog) {
		this.listCreditBillMasterStatusLog = listCreditBillMasterStatusLog;
	}
	
}
