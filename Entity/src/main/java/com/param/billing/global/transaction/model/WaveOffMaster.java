package com.param.billing.global.transaction.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name="t_wave_off_master", schema="billing")
@SequenceGenerator(name = "wave_off_master_seq", sequenceName = "billing.wave_off_master_seq", allocationSize = 1)
public class WaveOffMaster {
	@Id
	@Column(name = "waive_off_master_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "refund_voucher_seq")
	private int waiveOffMasterId;
	  
	@Column(name = "debit_note_id")
	private Integer debitNoteId;
	  
	@Column(name = "waive_off_date")
	private Date waiveOffDate;
	  
	@Column(name = "waive_off_reason_id")
	private Integer waiveOffReasonId;
	  
	@Column(name = "credit_bill_debit_note_id")
	private Integer creditBillDebitNoteId;
	  
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

	public int getWaiveOffMasterId() {
		return waiveOffMasterId;
	}

	public void setWaiveOffMasterId(int waiveOffMasterId) {
		this.waiveOffMasterId = waiveOffMasterId;
	}

	public Integer getDebitNoteId() {
		return debitNoteId;
	}

	public void setDebitNoteId(Integer debitNoteId) {
		this.debitNoteId = debitNoteId;
	}

	public Date getWaiveOffDate() {
		return waiveOffDate;
	}

	public void setWaiveOffDate(Date waiveOffDate) {
		this.waiveOffDate = waiveOffDate;
	}

	public Integer getWaiveOffReasonId() {
		return waiveOffReasonId;
	}

	public void setWaiveOffReasonId(Integer waiveOffReasonId) {
		this.waiveOffReasonId = waiveOffReasonId;
	}

	public Integer getCreditBillDebitNoteId() {
		return creditBillDebitNoteId;
	}

	public void setCreditBillDebitNoteId(Integer creditBillDebitNoteId) {
		this.creditBillDebitNoteId = creditBillDebitNoteId;
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
