package com.param.entity.model.master;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "PaymentEntitlementType")
@Table(name = "m_payment_entitlement_type_master", schema = "public")

public class PaymentEntitlementType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_entitlement_type_id", unique = true, nullable = false)
	private Integer paymentEntitlementTypeId;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "orgnisation_id")
	private Integer orgnisationId;

	@Column(name = "payment_entitlement_type", length = 200)
	private String paymentEntitlementType;

	@Column(length = 1)
	private String status;

	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "upadted_by")
	private Integer upadtedBy;

	@Column(name = "updated_date")
	private Timestamp updatedDate;

	public PaymentEntitlementType() {
	}

	public Integer getPaymentEntitlementTypeId() {
		return this.paymentEntitlementTypeId;
	}

	public void setPaymentEntitlementTypeId(Integer paymentEntitlementTypeId) {
		this.paymentEntitlementTypeId = paymentEntitlementTypeId;
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

	public Integer getOrgnisationId() {
		return this.orgnisationId;
	}

	public void setOrgnisationId(Integer orgnisationId) {
		this.orgnisationId = orgnisationId;
	}

	public String getPaymentEntitlementType() {
		return this.paymentEntitlementType;
	}

	public void setPaymentEntitlementType(String paymentEntitlementType) {
		this.paymentEntitlementType = paymentEntitlementType;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getUnitId() {
		return this.unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getUpadtedBy() {
		return this.upadtedBy;
	}

	public void setUpadtedBy(Integer upadtedBy) {
		this.upadtedBy = upadtedBy;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}
}
