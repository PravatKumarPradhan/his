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

@Entity
@Table(name="t_debit_note_service_details", schema="billing")
@SequenceGenerator(name = "debit_note_service_details_seq", sequenceName = "billing.debit_note_service_details_seq", allocationSize = 1)
public class DebitNoteServiceDetails {
	@Id
	@Column(name = "debit_note_service_details_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "debit_note_service_details_seq")
	private int debitNoteServiceDetailsId;
	  
	@Column(name = "debit_note_id")
	private Integer debitNoteId;
	  
	@Column(name = "service_id")
	private Integer serviceId;
	  
	@Column(name = "quantity")
	private int quantity;
	  
	@Column(name = "price")
	private double price;
	  
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
	
 	public int getDebitNoteServiceDetailsId() {
		return debitNoteServiceDetailsId;
	}

	public void setDebitNoteServiceDetailsId(int debitNoteServiceDetailsId) {
		this.debitNoteServiceDetailsId = debitNoteServiceDetailsId;
	}

	public Integer getDebitNoteId() {
		return debitNoteId;
	}

	public void setDebitNoteId(Integer debitNoteId) {
		this.debitNoteId = debitNoteId;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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
