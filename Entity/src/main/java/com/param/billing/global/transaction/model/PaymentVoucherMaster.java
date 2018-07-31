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
@Table(name="t_payment_voucher_master",schema="billing")
@SequenceGenerator(name = "payment_voucher_master_seq", sequenceName = "billing.payment_voucher_master_seq", allocationSize = 1)
public class PaymentVoucherMaster {
	@Id
	@Column(name = "payment_voucher_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_voucher_master_seq")
	private int paymentVoucherId;
	  
	@Column(name = "payment_voucher_number")
	private String paymentVoucherNumber;
	  
	@Column(name = "amount_collected")
	private double amountCollected;
	  
	@Column(name = "remark")
	private String remark;
	  
	@Column(name = "date_time")
	private Date dateTime;
	  
	@Column(name = "patient_id")
	private Integer patientId;
	  
	@Column(name = "nameOf_person_deposite")
	private String nameOfPersonDeposite;
	  
	@Column(name = "relation_id")
	private Integer relationId;
	  
	@Column(name = "contact_number")
	private String contactNumber;
	  
	@Column(name = "id_proof_type")
	private Integer idProofType;
	  
	@Column(name = "id_proof_number")
	private String idProofNumber;
	  
	@Column(name = "address_details")
	private String addressDetails;
	  
	@Column(name = "amount_to_be_return")
	private double amountToBeReturn;
	  
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

	public int getPaymentVoucherId() {
		return paymentVoucherId;
	}

	public void setPaymentVoucherId(int paymentVoucherId) {
		this.paymentVoucherId = paymentVoucherId;
	}

	public String getPaymentVoucherNumber() {
		return paymentVoucherNumber;
	}

	public void setPaymentVoucherNumber(String paymentVoucherNumber) {
		this.paymentVoucherNumber = paymentVoucherNumber;
	}

	public double getAmountCollected() {
		return amountCollected;
	}

	public void setAmountCollected(double amountCollected) {
		this.amountCollected = amountCollected;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getNameOfPersonDeposite() {
		return nameOfPersonDeposite;
	}

	public void setNameOfPersonDeposite(String nameOfPersonDeposite) {
		this.nameOfPersonDeposite = nameOfPersonDeposite;
	}

	public Integer getRelationId() {
		return relationId;
	}

	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Integer getIdProofType() {
		return idProofType;
	}

	public void setIdProofType(Integer idProofType) {
		this.idProofType = idProofType;
	}

	public String getIdProofNumber() {
		return idProofNumber;
	}

	public void setIdProofNumber(String idProofNumber) {
		this.idProofNumber = idProofNumber;
	}

	public String getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(String addressDetails) {
		this.addressDetails = addressDetails;
	}

	public double getAmountToBeReturn() {
		return amountToBeReturn;
	}

	public void setAmountToBeReturn(double amountToBeReturn) {
		this.amountToBeReturn = amountToBeReturn;
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
