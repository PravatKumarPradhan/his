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
@Table(name="m_wave_off_reason_master", schema="billing")
@SequenceGenerator(name = "wave_off_reason_master_seq", sequenceName = "billing.wave_off_reason_master_seq", allocationSize = 1)

public class WaveOffReasonMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wave_off_reason_master_seq")
	@Column(name = "waive_off_reason_id")
	private int waiveOffReasonId;
	  
	@Column(name = "waive_off_reason_desc")
	private String waiveOffReasonDesc;
	  
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

	public int getWaiveOffReasonId() {
		return waiveOffReasonId;
	}

	public void setWaiveOffReasonId(int waiveOffReasonId) {
		this.waiveOffReasonId = waiveOffReasonId;
	}

	public String getWaiveOffReasonDesc() {
		return waiveOffReasonDesc;
	}

	public void setWaiveOffReasonDesc(String waiveOffReasonDesc) {
		this.waiveOffReasonDesc = waiveOffReasonDesc;
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
