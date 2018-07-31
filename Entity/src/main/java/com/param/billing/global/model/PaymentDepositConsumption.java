package com.param.billing.global.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.billing.global.transaction.model.BillingMaster;

@Entity
@Table(name = "t_payment_deposite_consumption" , schema = "billing")
@SequenceGenerator(name = "package_consumption_master_seq" , sequenceName = "billing.package_consumption_master_seq" , allocationSize = 1)
public class PaymentDepositConsumption {

	@Id
	@GeneratedValue(generator="package_consumption_master_seq",strategy=GenerationType.SEQUENCE)
	@Column(name="dep_consumption_id")
	private Integer depConsumptionId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="dep_consumption_date")
	private Date depConsumptionDate;
	
	@Column(name="deposit_id")
	private Integer depositId;
	
	@Column(name="bill_id")
	private Integer billId;
	
	@Column(name="consumed_amount")
	private Double consumedAmount;
	
	@Column(name="consumption_against")
	private Integer consumptionAgainst;
	
	@Column(name="deposit_details_id")
	private Integer depositDetailsId;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "created_by")
	private Integer createdBy;
	
	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name = "updated_by")
	private Integer updatedBy;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "deposit_id" , insertable = false , updatable = false , nullable = false)
	private DepositMaster depositMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bill_id" , insertable = false , updatable = false , nullable = false)
	private BillingMaster billingMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "deposit_details_id" , insertable = false , updatable = false , nullable = false)
	private PaymentDepositDetails paymentDepositDetails;

	public Integer getDepConsumptionId() {
		return depConsumptionId;
	}

	public void setDepConsumptionId(Integer depConsumptionId) {
		this.depConsumptionId = depConsumptionId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Date getDepConsumptionDate() {
		return depConsumptionDate;
	}

	public void setDepConsumptionDate(Date depConsumptionDate) {
		this.depConsumptionDate = depConsumptionDate;
	}

	public Integer getDepositId() {
		return depositId;
	}

	public void setDepositId(Integer depositId) {
		this.depositId = depositId;
	}

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public Double getConsumedAmount() {
		return consumedAmount;
	}

	public void setConsumedAmount(Double consumedAmount) {
		this.consumedAmount = consumedAmount;
	}

	public Integer getConsumptionAgainst() {
		return consumptionAgainst;
	}

	public void setConsumptionAgainst(Integer consumptionAgainst) {
		this.consumptionAgainst = consumptionAgainst;
	}

	public Integer getDepositDetailsId() {
		return depositDetailsId;
	}

	public void setDepositDetailsId(Integer depositDetailsId) {
		this.depositDetailsId = depositDetailsId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	
}