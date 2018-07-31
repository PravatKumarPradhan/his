package com.param.entity.model.master;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "PatientSurgeryOrders")
@Table(name = "t_patient_surgery_order", schema = "public")
public class PatientSurgeryOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patient_surgery_order_id", unique = true, nullable = false)
	private Integer patientSurgeryOrderId;

	@Column(name = "admission_id")
	private Integer admissionId;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "is_ot_transfer", length = 1)
	private String isOtTransfer;

	@Column(name = "order_status_id")
	private Integer orderStatusId;

	@Column(name = "organization_id")
	private Integer organizationId;

	@Column(length = 1)
	private String status;

	@Column(name = "surgery_id")
	private Integer surgeryId;

	@Column(name = "time_of_surgery")
	private Time timeOfSurgery;

	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	private Timestamp updatedDate;

	public PatientSurgeryOrder() {
	}

	public Integer getPatientSurgeryOrderId() {
		return this.patientSurgeryOrderId;
	}

	public void setPatientSurgeryOrderId(Integer patientSurgeryOrderId) {
		this.patientSurgeryOrderId = patientSurgeryOrderId;
	}

	public Integer getAdmissionId() {
		return this.admissionId;
	}

	public void setAdmissionId(Integer admissionId) {
		this.admissionId = admissionId;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getIsOtTransfer() {
		return this.isOtTransfer;
	}

	public void setIsOtTransfer(String isOtTransfer) {
		this.isOtTransfer = isOtTransfer;
	}

	public Integer getOrderStatusId() {
		return this.orderStatusId;
	}

	public void setOrderStatusId(Integer orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	public Integer getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getSurgeryId() {
		return this.surgeryId;
	}

	public void setSurgeryId(Integer surgeryId) {
		this.surgeryId = surgeryId;
	}

	public Time getTimeOfSurgery() {
		return this.timeOfSurgery;
	}

	public void setTimeOfSurgery(Time timeOfSurgery) {
		this.timeOfSurgery = timeOfSurgery;
	}

	public Integer getUnitId() {
		return this.unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

}
