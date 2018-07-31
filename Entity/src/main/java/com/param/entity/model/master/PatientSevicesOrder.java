package com.param.entity.model.master;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "PatientSevicesOrder")
@Table(name = "t_patient_sevices_order", schema = "public")
public class PatientSevicesOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patient_sevices_order_id", unique = true, nullable = false)
	private Integer patientSevicesOrderId;

	@Column(name = "admission_id")
	private Integer admissionId;

	@Column(name = "bed_category_id")
	private Integer bedCategoryId;

	@Column(name = "billing_bed_category_id")
	private Integer billingBedCategoryId;

	@Column(name = "cancel_reason_id")
	private Integer cancelReasonId;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "is_modality_transfer", length = 1)
	private String isModalityTransfer;

	@Column(name = "order_status_id")
	private Integer orderStatusId;

	@Column(name = "ordered_by")
	private Integer orderedBy;

	@Column(name = "organization_id")
	private Integer organizationId;

	@Column(name = "patient_id")
	private Integer patientId;

	@Column(name = "payment_entitlement_id")
	private Integer paymentEntitlementId;

	@Column(name = "service_id")
	private Integer serviceId;

	@Column(length = 1)
	private String status;

	@Column(name = "t_patient_id")
	private Integer tPatientId;

	@Column(name = "time_of_service_order")
	private Timestamp timeOfServiceOrder;

	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	private Timestamp updatedDate;

	public PatientSevicesOrder() {
	}

	public Integer getPatientSevicesOrderId() {
		return this.patientSevicesOrderId;
	}

	public void setPatientSevicesOrderId(Integer patientSevicesOrderId) {
		this.patientSevicesOrderId = patientSevicesOrderId;
	}

	public Integer getAdmissionId() {
		return this.admissionId;
	}

	public void setAdmissionId(Integer admissionId) {
		this.admissionId = admissionId;
	}

	public Integer getBedCategoryId() {
		return this.bedCategoryId;
	}

	public void setBedCategoryId(Integer bedCategoryId) {
		this.bedCategoryId = bedCategoryId;
	}

	public Integer getBillingBedCategoryId() {
		return this.billingBedCategoryId;
	}

	public void setBillingBedCategoryId(Integer billingBedCategoryId) {
		this.billingBedCategoryId = billingBedCategoryId;
	}

	public Integer getCancelReasonId() {
		return this.cancelReasonId;
	}

	public void setCancelReasonId(Integer cancelReasonId) {
		this.cancelReasonId = cancelReasonId;
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

	public String getIsModalityTransfer() {
		return this.isModalityTransfer;
	}

	public void setIsModalityTransfer(String isModalityTransfer) {
		this.isModalityTransfer = isModalityTransfer;
	}

	public Integer getOrderStatusId() {
		return this.orderStatusId;
	}

	public void setOrderStatusId(Integer orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	public Integer getOrderedBy() {
		return this.orderedBy;
	}

	public void setOrderedBy(Integer orderedBy) {
		this.orderedBy = orderedBy;
	}

	public Integer getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getPatientId() {
		return this.patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getPaymentEntitlementId() {
		return this.paymentEntitlementId;
	}

	public void setPaymentEntitlementId(Integer paymentEntitlementId) {
		this.paymentEntitlementId = paymentEntitlementId;
	}

	public Integer getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTPatientId() {
		return this.tPatientId;
	}

	public void setTPatientId(Integer tPatientId) {
		this.tPatientId = tPatientId;
	}

	public Timestamp getTimeOfServiceOrder() {
		return this.timeOfServiceOrder;
	}

	public void setTimeOfServiceOrder(Timestamp timeOfServiceOrder) {
		this.timeOfServiceOrder = timeOfServiceOrder;
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
