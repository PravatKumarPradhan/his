package com.param.billing.global.transaction.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.billing.global.model.BankMaster;
@Entity
@Table(name="t_debit_note_details", schema="billing")
@SequenceGenerator(name = "debit_note_details_seq", sequenceName = "billing.debit_note_details_seq", allocationSize = 1)
public class DebitNoteDetails {
	@Id
	@Column(name = "debit_note_detials_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "debit_note_details_seq")
	private int debitNoteDetialsId;
	  
	@Column(name = "debit_note_id")
	private Integer debitNoteId;
	  
	@Column(name = "amount")
	private double amount;
	  
	@Column(name = "payment_mode_id")
	private Integer paymentModeId;
	  
	@Column(name = "bank_id")
	private Integer bankId;
	  
	@Column(name = "account_number")
	private String accountNumber;
	  
	@Column(name = "check_number")
	private String checkNumber;
	  
	@Column(name = "card_number")
	private String cardNumber;
	  
	@Column(name = "card_holder_name")
	private String cardHolderName;
	  
	@Column(name = "date_of_transaction")
	private Date dateOfTransaction;
	  
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
	@JoinColumn(name = "debit_note_id", insertable = false, updatable = false)
	private DebitNoteMaster debitNoteMaster; 

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bank_id", insertable = false, updatable = false)
	private BankMaster bankMaster;
	
	public int getDebitNoteDetialsId() {
		return debitNoteDetialsId;
	}

	public void setDebitNoteDetialsId(int debitNoteDetialsId) {
		this.debitNoteDetialsId = debitNoteDetialsId;
	}

	public Integer getDebitNoteId() {
		return debitNoteId;
	}

	public void setDebitNoteId(Integer debitNoteId) {
		this.debitNoteId = debitNoteId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Integer getPaymentModeId() {
		return paymentModeId;
	}

	public void setPaymentModeId(Integer paymentModeId) {
		this.paymentModeId = paymentModeId;
	}

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public Date getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(Date dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
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

	public DebitNoteMaster getDebitNoteMaster() {
		return debitNoteMaster;
	}

	public void setDebitNoteMaster(DebitNoteMaster debitNoteMaster) {
		this.debitNoteMaster = debitNoteMaster;
	}

	public BankMaster getBankMaster() {
		return bankMaster;
	}

	public void setBankMaster(BankMaster bankMaster) {
		this.bankMaster = bankMaster;
	}
	
}
