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

import com.param.entity.model.adt.BedMaster;
import com.param.entity.model.adt.Priority;
import com.param.entity.model.adt.WardMasters;
import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.doctor.Doctor;
import com.param.entity.model.master.Status;
import com.param.entity.model.patient.PatientRegistration;

@Entity(name="ReturnOfEmptyAmpoules")
@Table(name="t_return_of_empty_ampoules",schema="pharmacy")
public class ReturnOfEmptyAmpoules extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="issue_id")
	private Issue issue;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="patient_id")
	private PatientRegistration patient;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="doctor_id")
	private Doctor doctor;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ward_id")
	private WardMasters ward;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bed_id")
	private BedMaster bed;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="priority_id")
	private Priority priority;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="status_id")
	private Status status;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="returnOfEmptyAmpoules",cascade = CascadeType.ALL)
	private List<ReturnOfEmptyAmpoulesDetail> returnOfEmptyAmpoulesDetail;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
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

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<ReturnOfEmptyAmpoulesDetail> getReturnOfEmptyAmpoulesDetail() {
		return returnOfEmptyAmpoulesDetail;
	}

	public void setReturnOfEmptyAmpoulesDetail(List<ReturnOfEmptyAmpoulesDetail> returnOfEmptyAmpoulesDetail) {
		this.returnOfEmptyAmpoulesDetail = returnOfEmptyAmpoulesDetail;
	}

	public ReturnOfEmptyAmpoulesDetail addReturnDetailList(ReturnOfEmptyAmpoulesDetail returnOfEmptyAmpoulesDetail) {
		if (getReturnOfEmptyAmpoulesDetail() == null)
			this.returnOfEmptyAmpoulesDetail = new ArrayList<ReturnOfEmptyAmpoulesDetail>();
		
		getReturnOfEmptyAmpoulesDetail().add(returnOfEmptyAmpoulesDetail);
		returnOfEmptyAmpoulesDetail.setReturnOfEmptyAmpoules(this);

		return returnOfEmptyAmpoulesDetail;
	}
	
	public ReturnOfEmptyAmpoules() {
		super();
	}

	public ReturnOfEmptyAmpoules(Issue issue, PatientRegistration patient, Doctor doctor, WardMasters ward,
			BedMaster bedMaster, Priority priority, Status status) {
		super();
		this.issue = issue;
		this.patient = patient;
		this.doctor = doctor;
		this.ward = ward;
		this.bed = bedMaster;
		this.priority = priority;
		this.status = status;
	}
	
}