package com.param.billing.global.model;

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

import com.param.adt.master.global.model.PaymentModeMaster;

@Entity
@Table(name = "t_payment_deposit_details" , schema = "billing")
@SequenceGenerator(name = "deposit_details_seq" , sequenceName = "billing.deposit_details_seq" , allocationSize = 1)
public class PaymentDepositDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "deposit_details_seq")
	@Column(name = "deposit_details_id")
	private int depositDetailsId;
	
	@Column(name = "deposit_id")
	private Integer depositId;
	
	@Column(name = "payment_mode_id")
	private Integer paymentModeId;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "bank_id")
	private Integer bankId;
	
	@Column(name = "account_number")
	private String accountNumber;
	
	@Column(name = "cheque_number")
	private String chequeNumber;
	
	@Column(name = "card_no")
	private String cardNo;
	
	@Column(name = "card_holder_name")
	private String cardHolderName;
	
	@Column(name = "expiry_date")
	private Date expiryDate;
	
	@Column(name = "card_type_id")
	private Integer cardTypeId;
	
	@Column(name = "cheque_date")
	private Date chequeDate;
	
	@Column(name="consumed_amount")
	private Double consumedAmount;

	@Column(name="balance_amount")
	private Double balanceAmount;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "created_by")
	private Integer createdBy;
	
	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name = "updated_by")
	private Integer updatedBy;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "deposit_id" , insertable = false , updatable = false , nullable = false)
	private DepositMaster depositMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payment_mode_id" , insertable = false , updatable = false , nullable = false)
	private PaymentModeMaster paymentModeMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bank_id" , insertable = false , updatable = false , nullable = false)
	private BankMaster bankMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "card_type_id" , insertable = false , updatable = false , nullable = false)
	private CardTypeMaster cardTypeMaster;

	public Double getConsumedAmount() {
		return consumedAmount;
	}

	public void setConsumedAmount(Double consumedAmount) {
		this.consumedAmount = consumedAmount;
	}

	public Double getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(Double balanceAmount) {
		this.balanceAmount = balanceAmount;
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

	public int getDepositDetailsId() {
		return depositDetailsId;
	}

	public void setDepositDetailsId(int depositDetailsId) {
		this.depositDetailsId = depositDetailsId;
	}

	public Integer getDepositId() {
		return depositId;
	}

	public void setDepositId(Integer depositId) {
		this.depositId = depositId;
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

	public String getChequeNumber() {
		return chequeNumber;
	}

	public void setChequeNumber(String chequeNumber) {
		this.chequeNumber = chequeNumber;
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

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Integer getCardTypeId() {
		return cardTypeId;
	}

	public void setCardTypeId(Integer cardTypeId) {
		this.cardTypeId = cardTypeId;
	}

	public Date getChequeDate() {
		return chequeDate;
	}

	public void setChequeDate(Date chequeDate) {
		this.chequeDate = chequeDate;
	}

	public DepositMaster getDepositMaster() {
		return depositMaster;
	}

	public void setDepositMaster(DepositMaster depositMaster) {
		this.depositMaster = depositMaster;
	}

	public PaymentModeMaster getPaymentModeMaster() {
		return paymentModeMaster;
	}

	public void setPaymentModeMaster(PaymentModeMaster paymentModeMaster) {
		this.paymentModeMaster = paymentModeMaster;
	}

	public BankMaster getBankMaster() {
		return bankMaster;
	}

	public void setBankMaster(BankMaster bankMaster) {
		this.bankMaster = bankMaster;
	}

	public CardTypeMaster getCardTypeMaster() {
		return cardTypeMaster;
	}

	public void setCardTypeMaster(CardTypeMaster cardTypeMaster) {
		this.cardTypeMaster = cardTypeMaster;
	}
}
