package com.param.billing.global.transaction.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.global.common.DateConverter;
@Entity
@Table(name="t_payment_reciept_details", schema="billing")
@SequenceGenerator(name="payment_reciept_details_seq", sequenceName="billing.payment_reciept_details_seq", allocationSize=1)
public class PaymentReceiptDetails {
	@Id
	@Column(name = "payment_reciept_details_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_reciept_details_seq")
	private int paymentRecieptDetailsId;
	  
	@Column(name="payment_receipt_master_id")
	private Integer paymentReceiptMasterId;
	
	@Column(name = "payment_mode_id")
	private Integer paymentModeId;
	  
	@Column(name = "amount")
	private double amount;
	 
	@Column(name = "bank_id")
	private Integer bankId;
	  
	@Column(name = "account_number")
	private String accountNumber;
	  
	@Column(name = "check_number")
	private String  checkNumber;
	  
	@Column(name = "card_no")
	private String cardNo;
	  
	@Column(name = "card_holder_name")
	private String cardHolderName;
	  
	@Column(name = "date_of_transaction")
	@Convert(converter=DateConverter.class)
	private Long dateOfTransaction;
	  
	@Column(name = "expiry_date")
	@Convert(converter=DateConverter.class)
	private Long expiryDate;
	  
	@Column(name = "payment_voucher_no")
	private String paymentVoucherNo;
	  
	@Column(name = "cvv")
	private int cvv;
	  
	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "orgnisation_id")
	private Integer orgnisationId;

	@Column(name = "status")
	private char status;

	@Column(name = "created_date")
	@Convert(converter=DateConverter.class)
	private Long createdDate;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "updated_date")
	@Convert(converter=DateConverter.class)
	private Long updatedDate;

	@Column(name = "updated_by")
	private Integer updatedBy;
	
	@Column(name="cheque_date")
	@Convert(converter=DateConverter.class)
	private Long chequeDate;
	
	@Column(name="is_cancle")
	private Character isCancle;
	
	@Column(name="cardTypeId")
	private Integer cardTypeId;

	public int getPaymentRecieptDetailsId() {
		return paymentRecieptDetailsId;
	}

	public void setPaymentRecieptDetailsId(int paymentRecieptDetailsId) {
		this.paymentRecieptDetailsId = paymentRecieptDetailsId;
	}

	public Integer getPaymentModeId() {
		return paymentModeId;
	}

	public void setPaymentModeId(Integer paymentModeId) {
		this.paymentModeId = paymentModeId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public Long getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(Long dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}

	public Long getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Long expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getPaymentVoucherNo() {
		return paymentVoucherNo;
	}

	public void setPaymentVoucherNo(String paymentVoucherNo) {
		this.paymentVoucherNo = paymentVoucherNo;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
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

	public Long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Long updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getChequeDate() {
		return chequeDate;
	}

	public void setChequeDate(Long chequeDate) {
		this.chequeDate = chequeDate;
	}

	public Character getIsCancle() {
		return isCancle;
	}

	public void setIsCancle(Character isCancle) {
		this.isCancle = isCancle;
	}

	public Integer getCardTypeId() {
		return cardTypeId;
	}

	public void setCardTypeId(Integer cardTypeId) {
		this.cardTypeId = cardTypeId;
	}

	public Integer getPaymentReceiptMasterId() {
		return paymentReceiptMasterId;
	}

	public void setPaymentReceiptMasterId(Integer paymentReceiptMasterId) {
		this.paymentReceiptMasterId = paymentReceiptMasterId;
	}

}
