package com.param.billing.global.transaction.dto;

import java.util.List;

public class BillingPaymentDetailsDto {
	private int billingPaymentDetailsId;
	private Integer billingMasterId;
	private Integer paymentModeId;
	private double amount;
	private Integer bankId;
	private String accountNumber;
	private String  checkNumber;
	private String cardNo;
	private String cardHolderName;
	private Long dateOfTransaction;
	private Long expiryDate;
	private String paymentVoucherNo;
	private int cvv;
	private Integer tariffId;
	private Integer unitId;
	private Integer orgnisationId;
	private char status;
	private Long createdDate;
	private Integer createdBy;
	private Long updatedDate;
	private Integer updatedBy;
	private String chequeNumber;
	private List<BillingPaymentDetailsDto> listBillingPaymentDetailsDto;
	
	public String getChequeNumber() {
		return chequeNumber;
	}
	public void setChequeNumber(String chequeNumber) {
		this.chequeNumber = chequeNumber;
	}
	public int getBillingPaymentDetailsId() {
		return billingPaymentDetailsId;
	}
	public void setBillingPaymentDetailsId(int billingPaymentDetailsId) {
		this.billingPaymentDetailsId = billingPaymentDetailsId;
	}
	public Integer getBillingMasterId() {
		return billingMasterId;
	}
	public void setBillingMasterId(Integer billingMasterId) {
		this.billingMasterId = billingMasterId;
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
		this.chequeNumber = checkNumber;
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
	public Integer getTariffId() {
		return tariffId;
	}
	public void setTariffId(Integer tariffId) {
		this.tariffId = tariffId;
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
	public List<BillingPaymentDetailsDto> getListBillingPaymentDetailsDto() {
		return listBillingPaymentDetailsDto;
	}
	public void setListBillingPaymentDetailsDto(List<BillingPaymentDetailsDto> listBillingPaymentDetailsDto) {
		this.listBillingPaymentDetailsDto = listBillingPaymentDetailsDto;
	}
	
}

