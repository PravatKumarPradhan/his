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
@Table(name="t_payment_refund_master", schema="Billing")
@SequenceGenerator(name = "payment_voucher_master_seq", sequenceName = "billing.payment_voucher_master_seq", allocationSize = 1)
public class PaymentRefundMaster {
	@Id
	@Column(name = "payment_refund_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_voucher_master_seq")
	private int paymentRefundVoucherId;
	  
	@Column(name = "payment_refund_voucher_no")
	private String paymentRefundVoucherNo;
	  
	@Column(name = "amount_refunded")
	private double amountRefunded;
	
	@Column(name = "date_of_refund")
	private Date dateOfRefund;
	  
	@Column(name = "patient_id")
	private Integer patientId;
	  
	@Column(name = "name_of_person")
	private String nameOfPerson;
	  
	@Column(name = "contact_no")
	private String contactNo;
	  
	@Column(name = "realtion_id")
	private Integer realtionId;
	  
	@Column(name = "id_proof_type_id")
	private Integer idProofTypeId;
	  
	@Column(name = "id_proof_no")
	private String idProofNo;
	  
	@Column(name = "address_details")
	private String addressDetails;
	  
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

	public int getPaymentRefundVoucherId() {
		return paymentRefundVoucherId;
	}

	public void setPaymentRefundVoucherId(int paymentRefundVoucherId) {
		this.paymentRefundVoucherId = paymentRefundVoucherId;
	}

	public String getPaymentRefundVoucherNo() {
		return paymentRefundVoucherNo;
	}

	public void setPaymentRefundVoucherNo(String paymentRefundVoucherNo) {
		this.paymentRefundVoucherNo = paymentRefundVoucherNo;
	}

	public double getAmountRefunded() {
		return amountRefunded;
	}

	public void setAmountRefunded(double amountRefunded) {
		this.amountRefunded = amountRefunded;
	}

	public Date getDateOfRefund() {
		return dateOfRefund;
	}

	public void setDateOfRefund(Date dateOfRefund) {
		this.dateOfRefund = dateOfRefund;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getNameOfPerson() {
		return nameOfPerson;
	}

	public void setNameOfPerson(String nameOfPerson) {
		this.nameOfPerson = nameOfPerson;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public Integer getRealtionId() {
		return realtionId;
	}

	public void setRealtionId(Integer realtionId) {
		this.realtionId = realtionId;
	}

	public Integer getIdProofTypeId() {
		return idProofTypeId;
	}

	public void setIdProofTypeId(Integer idProofTypeId) {
		this.idProofTypeId = idProofTypeId;
	}

	public String getIdProofNo() {
		return idProofNo;
	}

	public void setIdProofNo(String idProofNo) {
		this.idProofNo = idProofNo;
	}

	public String getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(String addressDetails) {
		this.addressDetails = addressDetails;
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
