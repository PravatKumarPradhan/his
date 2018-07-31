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
import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.doctor.Doctor;
import com.param.entity.model.master.Store;
import com.param.entity.model.master.User;
import com.param.entity.model.master.VisitType;
import com.param.entity.model.opd.Encounter;
import com.param.entity.model.master.Status;

@Entity(name="Issue")
@Table(name="t_issue",schema="pharmacy")
public class Issue extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="issue_no")
	private String issueNo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sale_id")
	private Sale sale;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="prescription_id")
	private Prescription prescription;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="visit_type_id", nullable=false)
	private VisitType visitType;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="admission_id")
	private Admission admission;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="encounter_id")
	private Encounter encounter;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="doctor_id", nullable=false)
	private Doctor doctor;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="store_id", nullable=false)
	private Store store;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="patient_indent_id")
	private PatientIndent patientIndent;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="carrier_id")
	private User carrier;
	
	@Column(name="remark")
	private String remark;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="status_id")
	private Status status;
	
	@OneToMany(mappedBy="issue",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<IssueDetail> issueDetail;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIssueNo() {
		return issueNo;
	}

	public void setIssueNo(String issueNo) {
		this.issueNo = issueNo;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
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

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public PatientIndent getPatientIndent() {
		return patientIndent;
	}

	public void setPatientIndent(PatientIndent patientIndent) {
		this.patientIndent = patientIndent;
	}

	public User getCarrier() {
		return carrier;
	}

	public void setCarrier(User carrier) {
		this.carrier = carrier;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Issue()
	{
		super();
	}
	
	public Issue(Integer id) {
		super();
		this.id = id;
	}

	public List<IssueDetail> getIssueDetail() {
		return issueDetail;
	}

	public void setIssueDetail(List<IssueDetail> issueDetail) {
		this.issueDetail = issueDetail;
	}

	public IssueDetail addIssueDetail(IssueDetail issueDetail) {
		if (getIssueDetail() == null)
			this.issueDetail = new ArrayList<IssueDetail>();
		
		getIssueDetail().add(issueDetail);
		issueDetail.setIssue(this);

		return issueDetail;
	}
	
	public Issue(Integer id, String issueNo, Sale sale, Prescription prescription, VisitType visitType,
			Admission admission, Encounter encounter, Doctor doctor, Store store) {
		super();
		this.id = id;
		this.issueNo = issueNo;
		this.sale = sale;
		this.prescription = prescription;
		this.visitType = visitType;
		this.admission = admission;
		this.encounter = encounter;
		this.doctor = doctor;
		this.store = store;
	}

	public Issue(String issueNo, VisitType visitType, Doctor doctor, Store store, PatientIndent patientIndent,
			User carrier, String remark, Status status) {
		super();
		this.issueNo = issueNo;
		this.visitType = visitType;
		this.doctor = doctor;
		this.store = store;
		this.patientIndent = patientIndent;
		this.carrier = carrier;
		this.remark = remark;
		this.status = status;
	}

	public Issue(String issueNo, Sale sale, Prescription prescription, VisitType visitType, Doctor doctor, Store store,
			String remark, Status status) {
		super();
		this.issueNo = issueNo;
		this.sale = sale;
		this.prescription = prescription;
		this.visitType = visitType;
		this.doctor = doctor;
		this.store = store;
		this.remark = remark;
		this.status = status;
	}
	
}