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
@Table(name="t_payment_reciept_master", schema="billing")
@SequenceGenerator(name="payment_reciept_master_seq", sequenceName="billing.payment_reciept_master_seq", allocationSize=1)
public class PaymentRecieptMaster {
	@Id
	@Column(name = "payment_reciept_id")
 	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="payment_reciept_master_seq")
	private int paymentRecieptId;
	  
	@Column(name = "reciept_number")
	private String recieptNumber;
	  
	@Column(name = "billing_master_id")
	private Integer billingMasterId;
	  
	@Column(name = "date_of_reciept")
	@Convert(converter=DateConverter.class)
	private Long dateOfReciept;
	  
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

	@Column(name="payemet_against_id")
	private Integer payemetAgainstId;
	
	@Column(name="receipt_type")
	private Integer receiptType;
	
	@Column(name="counter_id")
	private Integer counterId;
	
	@Column(name="settlement_id")
	private Integer settlementId;
	
	@Column(name="payee_id")
	private Integer payeeId;
	
	@Column(name="received_from")
	private String receivedFrom;
	
	@Column(name="identification_id")
	private Integer identificationId;
	
	@Column(name="remark")
	private String remark;
	
	@Column(name="is_printed")
	private Character isPrinted;
	
	@Column(name="amount")
	private double amount;
	
	public int getPaymentRecieptId() {
		return paymentRecieptId;
	}

	public void setPaymentRecieptId(int paymentRecieptId) {
		this.paymentRecieptId = paymentRecieptId;
	}

	public String getRecieptNumber() {
		return recieptNumber;
	}

	public void setRecieptNumber(String recieptNumber) {
		this.recieptNumber = recieptNumber;
	}

	public Integer getBillingMasterId() {
		return billingMasterId;
	}

	public void setBillingMasterId(Integer billingMasterId) {
		this.billingMasterId = billingMasterId;
	}

	public Long getDateOfReciept() {
		return dateOfReciept;
	}

	public void setDateOfReciept(Long dateOfReciept) {
		this.dateOfReciept = dateOfReciept;
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

	public Integer getPayemetAgainstId() {
		return payemetAgainstId;
	}

	public void setPayemetAgainstId(Integer payemetAgainstId) {
		this.payemetAgainstId = payemetAgainstId;
	}

	public Integer getReceiptType() {
		return receiptType;
	}

	public void setReceiptType(Integer receiptType) {
		this.receiptType = receiptType;
	}

	public Integer getCounterId() {
		return counterId;
	}

	public void setCounterId(Integer counterId) {
		this.counterId = counterId;
	}

	public Integer getSettlementId() {
		return settlementId;
	}

	public void setSettlementId(Integer settlementId) {
		this.settlementId = settlementId;
	}

	public Integer getPayeeId() {
		return payeeId;
	}

	public void setPayeeId(Integer payeeId) {
		this.payeeId = payeeId;
	}

	public String getReceivedFrom() {
		return receivedFrom;
	}

	public void setReceivedFrom(String receivedFrom) {
		this.receivedFrom = receivedFrom;
	}

	public Integer getIdentificationId() {
		return identificationId;
	}

	public void setIdentificationId(Integer identificationId) {
		this.identificationId = identificationId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Character getIsPrinted() {
		return isPrinted;
	}

	public void setIsPrinted(Character isPrinted) {
		this.isPrinted = isPrinted;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
