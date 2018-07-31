package com.param.billing.global.transaction.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.param.billing.global.model.BillStatusMaster;

@Entity
@Table(name="t_billing_status_mapper", schema="billing")
public class BillingStatusMapper {

	@EmbeddedId
	private BillingStatusMapperId billingStatusMapperId;
	  
	@Column(name = "date_time")
	private Date dateTime;
	
	@Column(name = "unit_id")
	private Integer unitId;
	
	@Column(name = "organisation_id")
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
	@JoinColumn(name = "bill_id", insertable = false, updatable = false)
	private BillingMaster billingMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bill_status_id", insertable = false, updatable = false)
	private BillStatusMaster billStatusMaster;
	
	public BillingStatusMapperId getBillingStatusMapperId() {
		return billingStatusMapperId;
	}

	public void setBillingStatusMapperId(BillingStatusMapperId billingStatusMapperId) {
		this.billingStatusMapperId = billingStatusMapperId;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
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

	public BillingMaster getBillingMaster() {
		return billingMaster;
	}

	public void setBillingMaster(BillingMaster billingMaster) {
		this.billingMaster = billingMaster;
	}

	public BillStatusMaster getBillStatusMaster() {
		return billStatusMaster;
	}

	public void setBillStatusMaster(BillStatusMaster billStatusMaster) {
		this.billStatusMaster = billStatusMaster;
	}
	
}
