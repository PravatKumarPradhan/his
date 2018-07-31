package com.param.billing.global.transaction.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name="t_debit_note_master", schema="billing")
@SequenceGenerator(name = "debit_note_master_seq", sequenceName = "billing.debit_note_master_seq", allocationSize = 1)
public class DebitNoteMaster {	
	@Id
	@Column(name = "debit_note_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "debit_note_master_seq")
	private int debitNoteId;
	  
	@Column(name = "debit_node_number")
	private String debitNodeNumber;
	 
	@Column(name = "date_of_note")
	private Date dateOfNote;
	  
	@Column(name = "amount")
	private double amount;
	  
	@Column(name = "encounter_type_id")
	private Integer encounterTypeId;
	  
	@Column(name = "bill_id")
	private Integer billId;
	  
	@Column(name = "bill_number")
	private String billNumber;
	  
	@Column(name = "patient_id")
	private Integer patientId;
	   
	@Column(name = "name_of_person")
	private String nameOfPerson;
	  
	@Column(name = "contact_number")
	private String contactNumber;
	  
	@Column(name = "relation_id")
	private Integer relationId;
	  
	@Column(name = "id_proof_type_id")
	private Integer idProofTypeId;
	  
	@Column(name = "id_proof_number")
	private String idProofNumber;
	  
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "debitNoteMaster")
	private List<DebitNoteDetails> listDebitNoteDetails;
	
	public int getDebitNoteId() {
		return debitNoteId;
	}

	public void setDebitNoteId(int debitNoteId) {
		this.debitNoteId = debitNoteId;
	}

	public String getDebitNodeNumber() {
		return debitNodeNumber;
	}

	public void setDebitNodeNumber(String debitNodeNumber) {
		this.debitNodeNumber = debitNodeNumber;
	}

	public Date getDateOfNote() {
		return dateOfNote;
	}

	public void setDateOfNote(Date dateOfNote) {
		this.dateOfNote = dateOfNote;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Integer getEncounterTypeId() {
		return encounterTypeId;
	}

	public void setEncounterTypeId(Integer encounterTypeId) {
		this.encounterTypeId = encounterTypeId;
	}

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
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

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Integer getRelationId() {
		return relationId;
	}

	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}

	public Integer getIdProofTypeId() {
		return idProofTypeId;
	}

	public void setIdProofTypeId(Integer idProofTypeId) {
		this.idProofTypeId = idProofTypeId;
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

	public List<DebitNoteDetails> getListDebitNoteDetails() {
		return listDebitNoteDetails;
	}

	public void setListDebitNoteDetails(List<DebitNoteDetails> listDebitNoteDetails) {
		this.listDebitNoteDetails = listDebitNoteDetails;
	}

}
