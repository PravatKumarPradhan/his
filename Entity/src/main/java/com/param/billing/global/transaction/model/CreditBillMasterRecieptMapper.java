package com.param.billing.global.transaction.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="t_credit_bill_master_reciept_mapper", schema="billing")
public class CreditBillMasterRecieptMapper {
	@EmbeddedId
	private CreditBillMasterRecieptMapperId creditBillMasterRecieptMapperId;
	
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
	@JoinColumn(name = "credit_bill_reciept_id", insertable = false, updatable = false)
	private CreditBillReciept creditBillReciept;

	public CreditBillMasterRecieptMapperId getCreditBillMasterRecieptMapperId() {
		return creditBillMasterRecieptMapperId;
	}

	public void setCreditBillMasterRecieptMapperId(CreditBillMasterRecieptMapperId creditBillMasterRecieptMapperId) {
		this.creditBillMasterRecieptMapperId = creditBillMasterRecieptMapperId;
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

	public CreditBillReciept getCreditBillReciept() {
		return creditBillReciept;
	}

	public void setCreditBillReciept(CreditBillReciept creditBillReciept) {
		this.creditBillReciept = creditBillReciept;
	}

}
