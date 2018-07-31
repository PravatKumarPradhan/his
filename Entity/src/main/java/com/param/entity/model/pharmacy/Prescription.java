package com.param.entity.model.pharmacy;

import java.io.Serializable;
import java.sql.Timestamp;
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
import com.param.entity.model.adt.Priority;
import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.doctor.Doctor;
import com.param.entity.model.master.Status;
import com.param.entity.model.master.VisitType;
import com.param.entity.model.opd.Encounter;

@Entity(name="Prescription")
@Table(name="t_prescription",schema="pharmacy")
public class Prescription extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="admission_id")
	private Admission admission;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="encounter_id")
	private Encounter encounter;

	@Column(name="authorised_by", nullable=false)
	private Integer authorisedBy;

	@Column(name="authorised_date", nullable=false)
	private Timestamp authorisedDate;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="doctor_id", nullable=false)
	private Doctor doctor;

	@Column(name="is_intervention", nullable=false)
	private Boolean isIntervention;

	@Column(name="is_validate", nullable=false)
	private Boolean isValidate;

	@Column(name="prescription_date", nullable=false)
	private Timestamp prescriptionDate;

	@Column(name="prescription_no", nullable=false, length=50)
	private String prescriptionNo;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="priority_id", nullable=false)
	private Priority priority;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="status_id", nullable=false)
	private Status status;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="visit_type_id", nullable=false)
	private VisitType visitType;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="prescription",cascade = CascadeType.ALL)
	private List<PrescriptionDetail> prescriptionDetailsList;

	public Prescription() {
	}

	public Prescription(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAuthorisedBy() {
		return this.authorisedBy;
	}

	public void setAuthorisedBy(Integer authorisedBy) {
		this.authorisedBy = authorisedBy;
	}

	public Timestamp getAuthorisedDate() {
		return this.authorisedDate;
	}

	public void setAuthorisedDate(Timestamp authorisedDate) {
		this.authorisedDate = authorisedDate;
	}

	public Boolean getIsIntervention() {
		return this.isIntervention;
	}

	public void setIsIntervention(Boolean isIntervention) {
		this.isIntervention = isIntervention;
	}

	public Timestamp getPrescriptionDate() {
		return this.prescriptionDate;
	}

	public void setPrescriptionDate(Timestamp prescriptionDate) {
		this.prescriptionDate = prescriptionDate;
	}

	public String getPrescriptionNo() {
		return this.prescriptionNo;
	}

	public void setPrescriptionNo(String prescriptionNo) {
		this.prescriptionNo = prescriptionNo;
	}

	public List<PrescriptionDetail> getPrescriptionDetailsList() {
		return this.prescriptionDetailsList;
	}
	
	public void setPrescriptionDetailsList(List<PrescriptionDetail> prescriptionDetailsList) {
		this.prescriptionDetailsList = prescriptionDetailsList;
	}

	public Admission getAdmission() {
		return admission;
	}

	public void setAdmission(Admission admission) {
		this.admission = admission;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
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

	public VisitType getVisitType() {
		return visitType;
	}

	public void setVisitType(VisitType visitType) {
		this.visitType = visitType;
	}

	public Boolean getIsValidate() {
		return isValidate;
	}

	public void setIsValidate(Boolean isValidate) {
		this.isValidate = isValidate;
	}

	public PrescriptionDetail addPrescriptionDetailsList(PrescriptionDetail prescriptionDetailsList) {
		getPrescriptionDetailsList().add(prescriptionDetailsList);
		prescriptionDetailsList.setPrescription(this);

		return prescriptionDetailsList;
	}

	public PrescriptionDetail removePrescriptionDetailsList(PrescriptionDetail prescriptionDetailsList) {
		getPrescriptionDetailsList().remove(prescriptionDetailsList);
		prescriptionDetailsList.setPrescription(null);

		return prescriptionDetailsList;
	}

	public Encounter getEncounter() {
		return encounter;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}

}