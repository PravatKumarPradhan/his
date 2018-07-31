package com.param.billing.global.transaction.dto;

public class PaymentReceiptDetailsDto {
	private int paymentRecieptDetailsId;
	private Integer paymentReceiptMasterId;
	private Integer paymentModeId;
	private double amount;
	private Integer bankId;
	private String accountNumber;
	private String checkNumber;
	private String cardNo;
	private String cardHolderName;
	private Long dateOfTransaction;
	private Long expiryDate;
	private String paymentVoucherNo;
	private int cvv;
	private Integer unitId;
	private Integer orgnisationId;
	private char status;
	private Long createdDate;
	private Integer createdBy;
	private Long updatedDate;
	private Integer updatedBy;
	private Long chequeDate;
	private Character isCancle;
	private Integer cardTypeId;
	
	private String paymentModeName;
	private String bankName;
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
	public String getPaymentModeName() {
		return paymentModeName;
	}
	public void setPaymentModeName(String paymentModeName) {
		this.paymentModeName = paymentModeName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public Integer getPaymentReceiptMasterId() {
		return paymentReceiptMasterId;
	}
	public void setPaymentReceiptMasterId(Integer paymentReceiptMasterId) {
		this.paymentReceiptMasterId = paymentReceiptMasterId;
	}
	
}
