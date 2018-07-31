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
@Table(name="m_credit_note_master", schema="billing")
@SequenceGenerator(name = "credit_note_master_seq", sequenceName = "billing.credit_note_master_seq", allocationSize = 1)
public class CreditNoteMaster {
	@Id
	@Column(name = "credit_note_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "credit_note_master_seq")
	private int creditNoteId;
	
	@Column(name = "patient_id")
	private Integer patientId;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "date_of_transaction")
	private Date dateOfTransaction;
	 
	@Column(name = "company_id")
	private Integer companyId ;

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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "creditNoteMaster")
	private List<ActiveDepositeCreditNoteMapper> listActiveDepositeCreditNoteMapper;

	public int getCreditNoteId() {
		return creditNoteId;
	}

	public void setCreditNoteId(int creditNoteId) {
		this.creditNoteId = creditNoteId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(Date dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
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

	public List<ActiveDepositeCreditNoteMapper> getListActiveDepositeCreditNoteMapper() {
		return listActiveDepositeCreditNoteMapper;
	}

	public void setListActiveDepositeCreditNoteMapper(
			List<ActiveDepositeCreditNoteMapper> listActiveDepositeCreditNoteMapper) {
		this.listActiveDepositeCreditNoteMapper = listActiveDepositeCreditNoteMapper;
	}

}
