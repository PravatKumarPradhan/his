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

import com.param.billing.global.transaction.model.BillingStatusMapper;
import com.param.billing.global.transaction.model.CreditBillMasterStatusLog;
import com.param.billing.global.transaction.model.CreditBillRecieptStatusLog;

@Entity
@Table(name="m_billing_status_master", schema="billing")
@SequenceGenerator(name = "billing_status_master_seq", sequenceName = "billing.billing_status_master_seq", allocationSize = 1)
public class BillStatusMaster {

	@Id
	@Column(name = "bill_status_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "billing_status_master_seq")
	private int billStatusId;
	
	@Column(name = "bill_status_desc")
	private String billStatusDesc;
	
	@Column(name = "bill_status_code")
	private String billStatusCode;
	
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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "billStatusMaster")
	private List<BillingStatusMapper> listBillingStatusMapper;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "billStatusMaster")
	private List<CreditBillMasterStatusLog> listCreditBillMasterStatusLog;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "billStatusMaster")
	private List<CreditBillRecieptStatusLog> listCreditBillRecieptStatusLog;
	
	public int getBillStatusId() {
		return billStatusId;
	}

	public void setBillStatusId(int billStatusId) {
		this.billStatusId = billStatusId;
	}

	public String getBillStatusDesc() {
		return billStatusDesc;
	}

	public void setBillStatusDesc(String billStatusDesc) {
		this.billStatusDesc = billStatusDesc;
	}

	public String getBillStatusCode() {
		return billStatusCode;
	}

	public void setBillStatusCode(String billStatusCode) {
		this.billStatusCode = billStatusCode;
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

	public List<BillingStatusMapper> getListBillingStatusMapper() {
		return listBillingStatusMapper;
	}

	public void setListBillingStatusMapper(List<BillingStatusMapper> listBillingStatusMapper) {
		this.listBillingStatusMapper = listBillingStatusMapper;
	}

	public List<CreditBillMasterStatusLog> getListCreditBillMasterStatusLog() {
		return listCreditBillMasterStatusLog;
	}

	public void setListCreditBillMasterStatusLog(List<CreditBillMasterStatusLog> listCreditBillMasterStatusLog) {
		this.listCreditBillMasterStatusLog = listCreditBillMasterStatusLog;
	}

	public List<CreditBillRecieptStatusLog> getListCreditBillRecieptStatusLog() {
		return listCreditBillRecieptStatusLog;
	}

	public void setListCreditBillRecieptStatusLog(List<CreditBillRecieptStatusLog> listCreditBillRecieptStatusLog) {
		this.listCreditBillRecieptStatusLog = listCreditBillRecieptStatusLog;
	}

}
