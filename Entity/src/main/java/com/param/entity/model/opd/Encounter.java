package com.param.entity.model.opd;

import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.param.entity.model.doctor.Doctor;
import com.param.entity.model.master.KinDetail;
import com.param.entity.model.master.VisitType;
import com.param.entity.model.patient.PatientRegistration;

@Entity(name = "Encounters")
@Table(name = "t_encounter_master", schema = "opd")
public class Encounter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "encounter_id", unique = true, nullable = false)
	private Integer encounterId;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "dept_id")
	private Integer deptId;

	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;

	@Column(name = "encounter_date")
	private Timestamp encounterDate;

	@Column(name = "encounter_number", length = 200)
	private String encounterNumber;

	@Column(name = "encounter_time")
	private Time encounterTime;

	@Column(name = "is_referal", length = 1)
	private String isReferal;

	@ManyToOne
	@JoinColumn(name = "kin_details_id")
	private KinDetail kinDetails;

	@Column(name = "organization_id")
	private Integer organizationId;

	@ManyToOne
	@JoinColumn(name = "patient_id")
	private PatientRegistration patient;

	@Column(name = "payment_entitlement_type_id")
	private Integer paymentEntitlementTypeId;

	@Column(length = 2147483647)
	private String remark;

	@Column(name = "source_id")
	private Integer sourceId;

	@Column(length = 1)
	private String status;

	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	private Timestamp updatedDate;

	@ManyToOne
	@JoinColumn(name = "visit_type_id")
	private VisitType visitType;

	public Encounter() {
		super();
	}

	public Encounter(Integer encounterId) {
		super();
		this.encounterId = encounterId;
	}

	public Integer getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Timestamp getEncounterDate() {
		return encounterDate;
	}

	public void setEncounterDate(Timestamp encounterDate) {
		this.encounterDate = encounterDate;
	}

	public String getEncounterNumber() {
		return encounterNumber;
	}

	public void setEncounterNumber(String encounterNumber) {
		this.encounterNumber = encounterNumber;
	}

	public Time getEncounterTime() {
		return encounterTime;
	}

	public void setEncounterTime(Time encounterTime) {
		this.encounterTime = encounterTime;
	}

	public String getIsReferal() {
		return isReferal;
	}

	public void setIsReferal(String isReferal) {
		this.isReferal = isReferal;
	}

	public KinDetail getKinDetails() {
		return kinDetails;
	}

	public void setKinDetails(KinDetail kinDetails) {
		this.kinDetails = kinDetails;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public PatientRegistration getPatient() {
		return patient;
	}

	public void setPatient(PatientRegistration patient) {
		this.patient = patient;
	}

	public Integer getPaymentEntitlementTypeId() {
		return paymentEntitlementTypeId;
	}

	public void setPaymentEntitlementTypeId(Integer paymentEntitlementTypeId) {
		this.paymentEntitlementTypeId = paymentEntitlementTypeId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public VisitType getVisitType() {
		return visitType;
	}

	public void setVisitType(VisitType visitType) {
		this.visitType = visitType;
	}
}