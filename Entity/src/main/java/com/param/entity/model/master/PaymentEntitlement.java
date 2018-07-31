package com.param.entity.model.master;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "PaymentEntitlement")
@Table(name = "m_payment_entitlement_master", schema = "public")
public class PaymentEntitlement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_entitlement_id", unique = true, nullable = false)
	private Integer paymentEntitlementId;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "payment_entitlement_code", length = 50)
	private String paymentEntitlementCode;

	@Column(name = "payment_entitlement_desc", length = 2147483647)
	private String paymentEntitlementDesc;

	@Column(length = 1)
	private String status;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	private Timestamp updatedDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id")
	private Organization organization;

	public PaymentEntitlement() {
	}

	public Integer getPaymentEntitlementId() {
		return this.paymentEntitlementId;
	}

	public void setPaymentEntitlementId(Integer paymentEntitlementId) {
		this.paymentEntitlementId = paymentEntitlementId;
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

	public String getPaymentEntitlementCode() {
		return this.paymentEntitlementCode;
	}

	public void setPaymentEntitlementCode(String paymentEntitlementCode) {
		this.paymentEntitlementCode = paymentEntitlementCode;
	}

	public String getPaymentEntitlementDesc() {
		return this.paymentEntitlementDesc;
	}

	public void setPaymentEntitlementDesc(String paymentEntitlementDesc) {
		this.paymentEntitlementDesc = paymentEntitlementDesc;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}
