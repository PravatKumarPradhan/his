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

@Entity(name="ReturnBillableConsumption")
@Table(name="t_return_billable_consumption",schema="pharmacy")
public class ReturnBillableConsumption extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="return_number")
	private String returnNumber;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="billable_consumption_id")
	private BillableConsumption billableConsumption;
	
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
	@JoinColumn(name="approved_status_id")
	private Status approvedStatus;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="returnBillableConsumption",cascade = CascadeType.ALL)
	private List<ReturnBillableConsumptionDetail> returnBillableConsumptionDetail;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReturnNumber() {
		return returnNumber;
	}

	public void setReturnNumber(String returnNumber) {
		this.returnNumber = returnNumber;
	}

	public BillableConsumption getBillableConsumption() {
		return billableConsumption;
	}

	public void setBillableConsumption(BillableConsumption billableConsumption) {
		this.billableConsumption = billableConsumption;
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

	public List<ReturnBillableConsumptionDetail> getReturnBillableConsumptionDetail() {
		return returnBillableConsumptionDetail;
	}

	public void setReturnBillableConsumptionDetail(List<ReturnBillableConsumptionDetail> returnBillableConsumptionDetail) {
		this.returnBillableConsumptionDetail = returnBillableConsumptionDetail;
	}
	
	public ReturnBillableConsumptionDetail addReturnBillableConsumptionDetailList(ReturnBillableConsumptionDetail returnBillableConsumptionDetail) {
		if (getReturnBillableConsumptionDetail() == null)
			this.returnBillableConsumptionDetail = new ArrayList<ReturnBillableConsumptionDetail>();
		
		getReturnBillableConsumptionDetail().add(returnBillableConsumptionDetail);
		returnBillableConsumptionDetail.setReturnBillableConsumption(this);

		return returnBillableConsumptionDetail;
	}
	
	public ReturnBillableConsumption() {
		super();
	}

	public ReturnBillableConsumption(String returnNumber, BillableConsumption billableConsumption, Store store,
			VisitType visitType, PatientRegistration patient, Status status, Status approvedStatus) {
		super();
		this.returnNumber = returnNumber;
		this.billableConsumption = billableConsumption;
		this.store = store;
		this.visitType = visitType;
		this.patient = patient;
		this.status = status;
		this.approvedStatus = approvedStatus;
	}
	
}
	