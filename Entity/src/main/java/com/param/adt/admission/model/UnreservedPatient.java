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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.adt.master.global.model.OrganizationMaster;
import com.param.adt.master.global.model.ReasonMaster;
import com.param.adt.master.global.model.UnitMaster;
import com.param.global.model.BedCategoryMaster;
import com.param.global.model.PatientRegistration;
@Entity
@Table(name="t_unreserved_patient",schema="adt")
@SequenceGenerator(name="unreserved_patient_seq", sequenceName="adt.unreserved_patient_seq", allocationSize=1)
public class UnreservedPatient 
{

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="unreserved_patient_seq")
	@Column(name="unreservedt_id")
	private int unreservedtId;
	
	@Column(name="organizatoin_id")
	private Integer organizatoinId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="admission_note_id")
	private Integer admissionNoteId;
	
	@Column(name="patient_id")
	private Integer patientId;
	
	@Column(name="doa")
	private Date doa;
	
	@Column(name="newDoa")
	private Date newDoa;
	
	@Column(name="bed_category_id")
	private Integer bedCategoryId;
	
	@Column(name="resurve_bed_id")
	private Integer reserveBedId;
	
	@Column(name="reason_for_unreserve")
	private Integer reasonForUnreserve;
	
	@Column(name="reason_for_cancelation")
	private Integer reasonForCancelation;
	
	@Column(name="reason_for_admission")
	private Integer reasonForAdmission;
	
	@Column(name="is_cancel_reservation")
	private char isCancelReservation;
	
	@Column(name="is_flexible")
	private char isFlexible;
	
	@Column(name="is_reschedule_reservation")
	private char isRescheduleReservation;
	
	@Column(name="status")
	private char status;
	
	@Column(name="created_by")
	private int createdBy;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="updated_date")
	private Date updatedDate;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private OrganizationMaster organizationMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id", insertable = false, updatable = false)
	private UnitMaster unitMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admission_note_id", insertable = false, updatable = false)
	private AdmissionNote admissionNote;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id", insertable = false, updatable = false)
	private PatientRegistration patientRegistration;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bed_category_id", insertable = false, updatable = false)
	private BedCategoryMaster bedCategoryMaster;
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reserve_bed_id", insertable = false, updatable = false)
	private ReservedBed reservedBed;
	*/
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reason_for_unreserve", insertable = false, updatable = false)
	private ReasonMaster reasonMasterUnreserve;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reason_for_cancelation", insertable = false, updatable = false)
	private ReasonMaster reasonMasterCancelation;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reason_for_reservation", insertable = false, updatable = false)
	private ReasonMaster reasonMasterReservation;


	public int getUnreservedtId() {
		return unreservedtId;
	}


	public void setUnreservedtId(int unreservedtId) {
		this.unreservedtId = unreservedtId;
	}


	public Integer getOrganizatoinId() {
		return organizatoinId;
	}


	public void setOrganizatoinId(Integer organizatoinId) {
		this.organizatoinId = organizatoinId;
	}


	public Integer getUnitId() {
		return unitId;
	}


	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}


	public Integer getAdmissionNoteId() {
		return admissionNoteId;
	}


	public void setAdmissionNoteId(Integer admissionNoteId) {
		this.admissionNoteId = admissionNoteId;
	}


	public Integer getPatientId() {
		return patientId;
	}


	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}


	public Date getDoa() {
		return doa;
	}


	public void setDoa(Date doa) {
		this.doa = doa;
	}


	public Date getNewDoa() {
		return newDoa;
	}


	public void setNewDoa(Date newDoa) {
		this.newDoa = newDoa;
	}


	public Integer getBedCategoryId() {
		return bedCategoryId;
	}


	public void setBedCategoryId(Integer bedCategoryId) {
		this.bedCategoryId = bedCategoryId;
	}


	public Integer getReserveBedId() {
		return reserveBedId;
	}


	public void setReserveBedId(Integer reserveBedId) {
		this.reserveBedId = reserveBedId;
	}


	public Integer getReasonForUnreserve() {
		return reasonForUnreserve;
	}


	public void setReasonForUnreserve(Integer reasonForUnreserve) {
		this.reasonForUnreserve = reasonForUnreserve;
	}


	public Integer getReasonForCancelation() {
		return reasonForCancelation;
	}


	public void setReasonForCancelation(Integer reasonForCancelation) {
		this.reasonForCancelation = reasonForCancelation;
	}


	public Integer getReasonForAdmission() {
		return reasonForAdmission;
	}


	public void setReasonForAdmission(Integer reasonForAdmission) {
		this.reasonForAdmission = reasonForAdmission;
	}


	public char getIsCancelReservation() {
		return isCancelReservation;
	}


	public void setIsCancelReservation(char isCancelReservation) {
		this.isCancelReservation = (isCancelReservation == '\u0000') ? 'N' : isCancelReservation;
	}


	public char getIsFlexible() {
		return isFlexible;
	}


	public void setIsFlexible(char isFlexible) {
		this.isFlexible = (isFlexible == '\u0000') ? 'A' : isFlexible;
	}


	public char getIsRescheduleReservation() {
		return isRescheduleReservation;
	}


	public void setIsRescheduleReservation(char isRescheduleReservation) {
		this.isRescheduleReservation = (isRescheduleReservation == '\u0000') ? 'N' : isRescheduleReservation;
	}


	public char getStatus() {
		return status;
	}


	public void setStatus(char status) {
		this.status = (status == '\u0000') ? 'A' : status;
	}


	public int getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}


	public int getUpdatedBy() {
		return updatedBy;
	}


	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public Date getUpdatedDate() {
		return updatedDate;
	}


	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}


	public OrganizationMaster getOrganizationMaster() {
		return organizationMaster;
	}


	public void setOrganizationMaster(OrganizationMaster organizationMaster) {
		this.organizationMaster = organizationMaster;
	}


	public UnitMaster getUnitMaster() {
		return unitMaster;
	}


	public void setUnitMaster(UnitMaster unitMaster) {
		this.unitMaster = unitMaster;
	}


	public AdmissionNote getAdmissionNote() {
		return admissionNote;
	}


	public void setAdmissionNote(AdmissionNote admissionNote) {
		this.admissionNote = admissionNote;
	}


	public PatientRegistration getPatientRegistration() {
		return patientRegistration;
	}


	public void setPatientRegistration(PatientRegistration patientRegistration) {
		this.patientRegistration = patientRegistration;
	}


	public ReasonMaster getReasonMasterUnreserve() {
		return reasonMasterUnreserve;
	}


	public void setReasonMasterUnreserve(ReasonMaster reasonMasterUnreserve) {
		this.reasonMasterUnreserve = reasonMasterUnreserve;
	}


	public ReasonMaster getReasonMasterCancelation() {
		return reasonMasterCancelation;
	}


	public void setReasonMasterCancelation(ReasonMaster reasonMasterCancelation) {
		this.reasonMasterCancelation = reasonMasterCancelation;
	}


	public ReasonMaster getReasonMasterReservation() {
		return reasonMasterReservation;
	}


	public void setReasonMasterReservation(ReasonMaster reasonMasterReservation) {
		this.reasonMasterReservation = reasonMasterReservation;
	}
	
	
	
	
}
