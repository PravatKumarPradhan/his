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
@Table(name="t_refund_voucher_payment_mode_details", schema="billing")
@SequenceGenerator(name = "refund_voucher_payment_mode_details_seq", sequenceName = "billing.refund_voucher_payment_mode_details_seq", allocationSize = 1)
public class RefundVoucherPaymentModeDetails {
	@Id
	@Column(name = "refund_voucher_payment_mode_details_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "refund_voucher_payment_mode_details_seq")
	private int refundVoucherPaymentModeDetailsId;
	  
	@Column(name="refund_voucher_id")
	private Integer refundVoucherId;
	
	@Column(name = "payment_entitlement_type_id")
	private Integer paymentEntitlementTypeId;
	  
	@Column(name = "amount")
	private double amount;
	  
	@Column(name = "bank_id")
	private double bankId;
	  
	@Column(name = "account_number")
	private String accountNumber;
	  
	@Column(name = "card_number")
	private String cardNumber;
	  
	@Column(name = "checkNumber")
	private String checkNumber;
	  
	@Column(name = "card_holder_name")
	private String cardHolderName;
	  
	@Column(name = "date_of_refund")
	private Date dateOfRefund;
	  
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

	public int getRefundVoucherPaymentModeDetailsId() {
		return refundVoucherPaymentModeDetailsId;
	}

	public void setRefundVoucherPaymentModeDetailsId(int refundVoucherPaymentModeDetailsId) {
		this.refundVoucherPaymentModeDetailsId = refundVoucherPaymentModeDetailsId;
	}

	public Integer getRefundVoucherId() {
		return refundVoucherId;
	}

	public void setRefundVoucherId(Integer refundVoucherId) {
		this.refundVoucherId = refundVoucherId;
	}

	public Integer getPaymentEntitlementTypeId() {
		return paymentEntitlementTypeId;
	}

	public void setPaymentEntitlementTypeId(Integer paymentEntitlementTypeId) {
		this.paymentEntitlementTypeId = paymentEntitlementTypeId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getBankId() {
		return bankId;
	}

	public void setBankId(double bankId) {
		this.bankId = bankId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public Date getDateOfRefund() {
		return dateOfRefund;
	}

	public void setDateOfRefund(Date dateOfRefund) {
		this.dateOfRefund = dateOfRefund;
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
