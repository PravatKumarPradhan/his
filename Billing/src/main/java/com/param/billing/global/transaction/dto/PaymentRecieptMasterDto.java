package com.param.billing.global.transaction.dto;

import java.util.List;

public class PaymentRecieptMasterDto {

	private int paymentRecieptId;

	private String recieptNumber;

	private Integer billingMasterId;

	private Long dateOfReciept;

	private Integer unitId;

	private Integer orgnisationId;

	private char status;

	private Long createdDate;

	private Integer createdBy;

	private Long updatedDate;

	private Integer updatedBy;

	private Integer payemetAgainstId;

	private Integer receiptType;

	private Integer counterId;

	private Integer settlementId;

	private Integer payeeId;

	private String receivedFrom;

	private Integer identificationId;

	private String remark;

	private Character isPrinted;

	private Double amount;

	private Integer appointmentId;

	private List<PaymentReceiptDetailsDto> listPaymentReceiptDetailsDto;

	public Integer getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}

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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public List<PaymentReceiptDetailsDto> getListPaymentReceiptDetailsDto() {
		return listPaymentReceiptDetailsDto;
	}

	public void setListPaymentReceiptDetailsDto(List<PaymentReceiptDetailsDto> listPaymentReceiptDetailsDto) {
		this.listPaymentReceiptDetailsDto = listPaymentReceiptDetailsDto;
	}

}
