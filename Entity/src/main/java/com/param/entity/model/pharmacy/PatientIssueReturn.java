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
import com.param.entity.model.master.Status;
import com.param.entity.model.master.Store;
import com.param.entity.model.master.VisitType;
import com.param.entity.model.opd.Encounter;
import com.param.entity.model.patient.PatientRegistration;

@Entity(name="PatientIssueReturn")
@Table(name="t_patient_issue_return",schema="pharmacy")
public class PatientIssueReturn extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="return_no")
	private String returnNo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="patient_id")
	private PatientRegistration patient;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="visit_type_id")
	private VisitType visitType;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="admission_id")
	private Admission admission;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="encounter_id")
	private Encounter encounter;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="store_id")
	private Store store;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ward_id")
	private WardMasters wardMaster;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bed_id")
	private BedMaster bedMaster;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="status_id")
	private Status status;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patientIssueReturn", cascade = CascadeType.ALL)
	private List<PatientIssueReturnDetail> patientIssueReturnDetail;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReturnNo() {
		return returnNo;
	}

	public void setReturnNo(String returnNo) {
		this.returnNo = returnNo;
	}

	public PatientRegistration getPatient() {
		return patient;
	}

	public void setPatient(PatientRegistration patient) {
		this.patient = patient;
	}

	public VisitType getVisitType() {
		return visitType;
	}

	public void setVisitType(VisitType visitType) {
		this.visitType = visitType;
	}

	public Admission getAdmission() {
		return admission;
	}

	public void setAdmission(Admission admission) {
		this.admission = admission;
	}

	public Encounter getEncounter() {
		return encounter;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public WardMasters getWardMaster() {
		return wardMaster;
	}

	public void setWardMaster(WardMasters wardMaster) {
		this.wardMaster = wardMaster;
	}

	public BedMaster getBedMaster() {
		return bedMaster;
	}

	public void setBedMaster(BedMaster bedMaster) {
		this.bedMaster = bedMaster;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<PatientIssueReturnDetail> getPatientIssueReturnDetail() {
		return patientIssueReturnDetail;
	}

	public void setPatientIssueReturnDetail(List<PatientIssueReturnDetail> patientIssueReturnDetail) {
		this.patientIssueReturnDetail = patientIssueReturnDetail;
	}

	public PatientIssueReturnDetail addPatientIssueReturnDetail(PatientIssueReturnDetail patientIssueReturnDetail) {
		if (getPatientIssueReturnDetail() == null)
			this.patientIssueReturnDetail = new ArrayList<PatientIssueReturnDetail>();
		
		getPatientIssueReturnDetail().add(patientIssueReturnDetail);
		patientIssueReturnDetail.setPatientIssueReturn(this);

		return patientIssueReturnDetail;
	}
	
	public PatientIssueReturn() {
		super();
	}

	public PatientIssueReturn(Integer id) {
		super();
		this.id = id;
	}

	public PatientIssueReturn(Integer id, String returnNo, PatientRegistration patient, VisitType visitType,
			Admission admission, Encounter encounter, Store store, WardMasters wardMaster, BedMaster bedMaster,
			Status status) {
		super();
		this.id = id;
		this.returnNo = returnNo;
		this.patient = patient;
		this.visitType = visitType;
		this.admission = admission;
		this.encounter = encounter;
		this.store = store;
		this.wardMaster = wardMaster;
		this.bedMaster = bedMaster;
		this.status = status;
	}
	
	public PatientIssueReturn(String returnNo, PatientRegistration patient, VisitType visitType,
			Encounter encounter, Store store, Status status) {
		super();
		this.returnNo = returnNo;
		this.patient = patient;
		this.visitType = visitType;
		this.encounter = encounter;
		this.store = store;
		this.status = status;
	}
	
	public PatientIssueReturn(String returnNo, PatientRegistration patient, VisitType visitType,
			Admission admission, Store store, WardMasters wardMaster, BedMaster bedMaster, Status status) {
		super();
		this.returnNo = returnNo;
		this.patient = patient;
		this.visitType = visitType;
		this.admission = admission;
		this.store = store;
		this.wardMaster = wardMaster;
		this.bedMaster = bedMaster;
		this.status = status;
	}
	
}