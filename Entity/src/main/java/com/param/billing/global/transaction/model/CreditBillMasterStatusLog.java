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
@Table(name="t_credit_bill_master_status_log", schema="billing")
public class CreditBillMasterStatusLog {
	@EmbeddedId
	private CreditBillMasterStatusLogId creditBillMasterStatusLogId;
	
	@Column(name = "bill_status_date")
	private Date billStatusDate;
	  
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bill_status_id", insertable = false, updatable = false)
	private BillStatusMaster billStatusMaster;
	
	public CreditBillMasterStatusLogId getCreditBillMasterStatusLogId() {
		return creditBillMasterStatusLogId;
	}

	public void setCreditBillMasterStatusLogId(CreditBillMasterStatusLogId creditBillMasterStatusLogId) {
		this.creditBillMasterStatusLogId = creditBillMasterStatusLogId;
	}

	public Date getBillStatusDate() {
		return billStatusDate;
	}

	public void setBillStatusDate(Date billStatusDate) {
		this.billStatusDate = billStatusDate;
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
