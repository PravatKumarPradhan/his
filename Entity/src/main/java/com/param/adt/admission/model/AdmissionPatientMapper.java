package com.param.adt.admission.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.global.model.BedCategoryMaster;
import com.param.global.model.PatientCategoryMaster;
import com.param.global.model.PaymentEntitlementMaster;

@NamedQueries({
	@NamedQuery(name="GET_PREVIOUS_DATA", query="SELECT apm.admissionPatientId as admissionPatientId "
			+"FROM AdmissionPatientMapper as apm WHERE apm.admissionNoteId=:admissionNoteId AND apm.status='A'") 
	
})
@Entity
@Table(name = "t_admission_patient_mapping", schema = "adt")
@SequenceGenerator(name="admission_patient_mapping_seq", sequenceName="adt.admission_patient_mapping_seq", allocationSize=1)
public class AdmissionPatientMapper 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admission_patient_mapping_seq")
	@Column(name="admission_patient_id")
	private int admissionPatientId;
	
	@Column(name="admission_note_id")
	private Integer admissionNoteId;
	
	@Column(name="bed_category_id")
	private Integer bedCategoryId;
	
	@Column(name="payment_entitlement_id")
	private Integer paymentEntitlementId;
	
	@Column(name="patient_category_id")
	private Integer patientCategoryId;
	
	@Column(name="doa")
	private Date doa;
	
	@Column(name="pdd")
	private Date pdd;
	
	@Column(name="active_status")
	private Character activeStatus;
	
	@Column(name="status")
	private Character status;
	
	@Column(name = "created_by")
	private int createdBy;
	
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admission_note_id", insertable = false, updatable = false)
	private AdmissionNote admissionNote;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bed_category_id", insertable = false, updatable = false)
	private BedCategoryMaster bedCategoryMaster;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_category_id", insertable = false, updatable = false)
	private PatientCategoryMaster patientCategoryMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payment_entitlement_id", insertable = false, updatable = false)
	private PaymentEntitlementMaster paymentEntitlementMaster;

	public int getAdmissionPatientId() {
		return admissionPatientId;
	}

	public void setAdmissionPatientId(int admissionPatientId) {
		this.admissionPatientId = admissionPatientId;
	}

	public Integer getAdmissionNoteId() {
		return admissionNoteId;
	}

	public void setAdmissionNoteId(Integer admissionNoteId) {
		this.admissionNoteId = admissionNoteId;
	}

	public Integer getBedCategoryId() {
		return bedCategoryId;
	}

	public void setBedCategoryId(Integer bedCategoryId) {
		this.bedCategoryId = bedCategoryId;
	}

	public Integer getPaymentEntitlementId() {
		return paymentEntitlementId;
	}

	public void setPaymentEntitlementId(Integer paymentEntitlementId) {
		this.paymentEntitlementId = paymentEntitlementId;
	}

	public Integer getPatientCategoryId() {
		return patientCategoryId;
	}

	public void setPatientCategoryId(Integer patientCategoryId) {
		this.patientCategoryId = patientCategoryId;
	}

	public Date getDoa() {
		return doa;
	}

	public void setDoa(Date doa) {
		this.doa = doa;
	}

	public Date getPdd() {
		return pdd;
	}

	public void setPdd(Date pdd) {
		this.pdd = pdd;
	}

	public Character getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(Character activeStatus) {
		this.activeStatus = (activeStatus == '\u0000') ? 'I' : activeStatus;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = (status == '\u0000') ? 'A' : status;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public AdmissionNote getAdmissionNote() {
		return admissionNote;
	}

	public void setAdmissionNote(AdmissionNote admissionNote) {
		this.admissionNote = admissionNote;
	}

	

	
	

}
