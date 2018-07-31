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

@Entity(name="BillableConsumption")
@Table(name="t_billable_consumption",schema="pharmacy")
public class BillableConsumption extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;
	
	@Column(name="consumable_number")
	private String consumableNumber;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="store_id")
	private Store store;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="visit_type_id")
	private VisitType visitType;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="patient_id")
	private PatientRegistration patient;
	
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
	@JoinColumn(name="approved_status_id")
	private Status approvedStatus;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="admission_id")
	private Admission admission;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="encounter_id")
	private Encounter encounter;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="billableConsumption",cascade = CascadeType.ALL)
	private List<BillableConsumptionDetail> billableConsumptionDetail;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConsumableNumber() {
		return consumableNumber;
	}

	public void setConsumableNumber(String consumableNumber) {
		this.consumableNumber = consumableNumber;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public VisitType getVisitType() {
		return visitType;
	}

	public void setVisitType(VisitType visitType) {
		this.visitType = visitType;
	}

	public PatientRegistration getPatient() {
		return patient;
	}

	public void setPatient(PatientRegistration patient) {
		this.patient = patient;
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

	public Status getApprovedStatus() {
		return approvedStatus;
	}

	public void setApprovedStatus(Status approvedStatus) {
		this.approvedStatus = approvedStatus;
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

	public List<BillableConsumptionDetail> getBillableConsumptionDetail() {
		return billableConsumptionDetail;
	}

	public void setBillableConsumptionDetail(List<BillableConsumptionDetail> billableConsumptionDetail) {
		this.billableConsumptionDetail = billableConsumptionDetail;
	}

	public BillableConsumptionDetail addReturnDetailList(BillableConsumptionDetail billableConsumptionDetail) {
		if (getBillableConsumptionDetail() == null)
			this.billableConsumptionDetail = new ArrayList<BillableConsumptionDetail>();
		
		getBillableConsumptionDetail().add(billableConsumptionDetail);
		billableConsumptionDetail.setBillableConsumption(this);

		return billableConsumptionDetail;
	}
	
	public BillableConsumption() {
		super();
	}

	public BillableConsumption(Integer id) {
		super();
		this.id = id;
	}

	public BillableConsumption(String consumableNumber, Store store, VisitType visitType, PatientRegistration patient,
			Status status, Status approvedStatus) {
		super();
		this.consumableNumber = consumableNumber;
		this.store = store;
		this.visitType = visitType;
		this.patient = patient;
		this.status = status;
		this.approvedStatus = approvedStatus;
	}
	
}