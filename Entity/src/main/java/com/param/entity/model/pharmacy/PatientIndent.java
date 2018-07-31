package com.param.entity.model.pharmacy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.param.entity.model.adt.Admission;
import com.param.entity.model.adt.BedMaster;
import com.param.entity.model.adt.WardMasters;
import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.doctor.Doctor;
import com.param.entity.model.master.CancelReason;
import com.param.entity.model.master.Status;
import com.param.entity.model.master.Store;
import com.param.entity.model.master.VisitType;
import com.param.entity.model.opd.Encounter;
import com.param.entity.model.patient.PatientRegistration;

@Entity(name="PatientIndent")
@Table(name="t_patient_indent",schema="pharmacy")
public class PatientIndent extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="indent_no")
	private String indentNo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="store_id")
	private Store store;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="patient_id")
	private PatientRegistration patient;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="doctor_id")
	private Doctor doctor;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="admission_id")
	private Admission admission;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="encounter_id")
	private Encounter encounter;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ward_id")
	private WardMasters ward;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bed_id")
	private BedMaster bed;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="status_id")
	private Status status;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="approval_status_id")
	private Status approvalStatus;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="visit_type_id")
	private VisitType visitType;
	
	@Column(name="is_consignment")
	private boolean isConsignment;
	
	@Column(name="remark")
	private String remark;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cancel_reason_id")
	private CancelReason cancelReason;

	@Column(name="cancel_note")
	private String cancelNote;
	
	@OneToMany(mappedBy="patientIndent",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<PatientIndentDetail> patientIndentDetail;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIndentNo() {
		return indentNo;
	}

	public void setIndentNo(String indentNo) {
		this.indentNo = indentNo;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public PatientRegistration getPatient() {
		return patient;
	}

	public void setPatient(PatientRegistration patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Admission getAdmission() {
		return admission;
	}

	public void setAdmission(Admission admission) {
		this.admission = admission;
	}

	public VisitType getVisitType() {
		return visitType;
	}

	public void setVisitType(VisitType visitType) {
		this.visitType = visitType;
	}

	public WardMasters getWard() {
		return ward;
	}

	public void setWard(WardMasters ward) {
		this.ward = ward;
	}

	public BedMaster getBed() {
		return bed;
	}

	public void setBed(BedMaster bed) {
		this.bed = bed;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Status getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(Status approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public boolean isConsignment() {
		return isConsignment;
	}

	public void setConsignment(boolean isConsignment) {
		this.isConsignment = isConsignment;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public List<PatientIndentDetail> getPatientIndentDetail() {
		return patientIndentDetail;
	}

	public void setPatientIndentDetail(List<PatientIndentDetail> patientIndentDetail) {
		this.patientIndentDetail = patientIndentDetail;
	}
	
	public CancelReason getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(CancelReason cancelReason) {
		this.cancelReason = cancelReason;
	}

	public String getCancelNote() {
		return cancelNote;
	}

	public void setCancelNote(String cancelNote) {
		this.cancelNote = cancelNote;
	}

	public PatientIndentDetail addPatientIndentDetail(PatientIndentDetail patientIndentDetail) {
		if (getPatientIndentDetail() == null)
			this.patientIndentDetail = new ArrayList<PatientIndentDetail>();
		
		getPatientIndentDetail().add(patientIndentDetail);
		patientIndentDetail.setPatientIndent(this);

		return patientIndentDetail;
	}
	
	public void updatePatientIndent(Boolean isConsignment, String remark) {
		this.isConsignment = isConsignment;
		this.remark = remark;
	}
	
	public Encounter getEncounter() {
		return encounter;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}

	public PatientIndent()
	{
		super();
	}

	public PatientIndent(Integer id, String indentNo, Store store, PatientRegistration patient, Doctor doctor,
			Admission admission, WardMasters ward, BedMaster bed, Status status, Status approvalStatus, boolean isConsignment,
			String remark, VisitType visitType) {
		super();
		this.id = id;
		this.indentNo = indentNo;
		this.store = store;
		this.patient = patient;
		this.doctor = doctor;
		this.admission = admission;
		this.ward = ward;
		this.bed = bed;
		this.status = status;
		this.approvalStatus = approvalStatus;
		this.isConsignment = isConsignment;
		this.remark = remark;
		this.visitType = visitType;
	}

	public PatientIndent(String indentNo, VisitType visitType, PatientRegistration patient, WardMasters ward,
			Store store, boolean isConsignment, String remark, Status status, Status approvalStatus, BedMaster bed,
			Doctor doctor, Admission admission) {
		super();
		this.indentNo = indentNo;
		this.visitType = visitType;
		this.patient = patient;
		this.ward = ward;
		this.store = store;
		this.isConsignment = isConsignment;
		this.remark = remark;
		this.status = status;
		this.approvalStatus = approvalStatus;
		this.bed = bed;
		this.doctor = doctor;
		this.admission = admission;
	}

	public PatientIndent(String indentNo, VisitType visitType, PatientRegistration patient,
			Store store, boolean isConsignment, String remark, Status status, Status approvalStatus,
			Doctor doctor, Encounter encounter) {
		super();
		this.indentNo = indentNo;
		this.visitType = visitType;
		this.patient = patient;
		this.store = store;
		this.isConsignment = isConsignment;
		this.remark = remark;
		this.status = status;
		this.approvalStatus = approvalStatus;
		this.doctor = doctor;
		this.encounter = encounter;
	}

	public PatientIndent(Integer id) {
		super();
		this.id = id;
	}
}