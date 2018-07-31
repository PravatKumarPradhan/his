package com.param.billing.global.transaction.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.global.common.DateConverter;
import com.param.global.model.DoctorMaster;
import com.param.global.model.OrderDetailsMaster;
import com.param.global.model.ServiceMaster;
import com.param.global.model.SpecialityMaster;

@Entity
@Table(name = "t_billing_details", schema = "billing")
@SequenceGenerator(name = "billing_details_seq", sequenceName = "billing.billing_details_seq",
    allocationSize = 1)
public class BillingDetails {
  @Id
  @Column(name = "billing_details_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "billing_details_seq")
  private Integer billingDetailsId;

  @Column(name = "billing_master_id")
  private Integer billingMasterId;

  @Column(name = "service_id")
  private Integer serviceId;

  @Column(name = "rate")
  private Double rate;

  @Column(name = "quantity")
  private Double quantity;

  @Column(name = "concession")
  private Double concession;

  @Column(name = "gross_amount")
  private Double grossAmount;

  @Column(name = "co_pay_percentage")
  private Double coPayPercentage;

  @Column(name = "unit_id")
  private Integer unitId;

  @Column(name = "organization_id")
  private Integer organizationId;

  @Column(name = "status")
  private Character status;

  @Column(name = "created_by")
  private Integer createdBy;

  @Column(name = "updated_by")
  private Integer updatedBy;

  @Column(name = "created_date")
  @Convert(converter = DateConverter.class)
  private Long createdDate;

  @Column(name = "updated_date")
  @Convert(converter = DateConverter.class)
  private Long updatedDate;

  @Column(name = "discount")
  private Double discount;

  @Column(name = "tax_id")
  private Integer taxId;

  @Column(name = "tax_per")
  private Double taxPer;

  @Column(name = "tax_amount")
  private Double taxAmount;

  @Column(name = "net_amt")
  private Double netAmt;

  @Column(name = "ord_doc_spl_id")
  private Integer ordDocSplId;

  @Column(name = "ord_doc_id")
  private Integer ordDocId;

  @Column(name = "drug_id")
  private Integer drugId;

  @Column(name = "batch_id")
  private Integer batchId;

  @Column(name = "order_date")
  @Convert(converter = DateConverter.class)
  private Long orderDate;

  @Column(name = "order_sch_date")
  @Convert(converter = DateConverter.class)
  private Long orderSchDate;

  @Column(name = "self_payable")
  private Double selfPayable;

  @Column(name = "credit_payable")
  private Double creditPayable;

  @Column(name = "payee_id")
  private Integer payeeId;

  @Column(name = "co_pay_per")
  private Double coPayPer;

  @Column(name = "order_details_id")
  private Integer orderDetailsId;

  @Column(name = "package_id")
  private Integer packageId;

  @Column(name = "package_consumption_amt")
  private Double packageConsumptionAmt;
  
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "billing_master_id", insertable = false, nullable = false, updatable = false)
	private BillingMaster billingMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_details_id", insertable = false, nullable = false, updatable = false)
	private OrderDetailsMaster orderDetailsMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ord_doc_id", insertable = false, nullable = false, updatable = false)
	private DoctorMaster doctorMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ord_doc_spl_id", insertable = false, nullable = false, updatable = false)
	private SpecialityMaster specialityMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ord_doc_id", insertable = false, nullable = false, updatable = false)
	private ServiceMaster serviceMaster;

  public Integer getOrderDetailsId() {
    return orderDetailsId;
  }

  public void setOrderDetailsId(Integer orderDetailsId) {
    this.orderDetailsId = orderDetailsId;
  }

  public Integer getPackageId() {
    return packageId;
  }

  public void setPackageId(Integer packageId) {
    this.packageId = packageId;
  }

  public Double getPackageConsumptionAmt() {
    return packageConsumptionAmt;
  }

  public void setPackageConsumptionAmt(Double packageConsumptionAmt) {
    this.packageConsumptionAmt = packageConsumptionAmt;
  }

  public Integer getBillingDetailsId() {
    return billingDetailsId;
  }

  public void setBillingDetailsId(Integer billingDetailsId) {
    this.billingDetailsId = billingDetailsId;
  }

  public Integer getBillingMasterId() {
    return billingMasterId;
  }

  public void setBillingMasterId(Integer billingMasterId) {
    this.billingMasterId = billingMasterId;
  }

  public Integer getServiceId() {
    return serviceId;
  }

  public void setServiceId(Integer serviceId) {
    this.serviceId = serviceId;
  }

  public Double getRate() {
    return rate;
  }

  public void setRate(Double rate) {
    this.rate = rate;
  }

  public Double getQuantity() {
    return quantity;
  }

  public void setQuantity(Double quantity) {
    this.quantity = quantity;
  }

  public Double getConcession() {
    return concession;
  }

  public void setConcession(Double concession) {
    this.concession = concession;
  }

  public Double getGrossAmount() {
    return grossAmount;
  }

  public void setGrossAmount(Double grossAmount) {
    this.grossAmount = grossAmount;
  }

  public Double getCoPayPercentage() {
    return coPayPercentage;
  }

  public void setCoPayPercentage(Double coPayPercentage) {
    this.coPayPercentage = coPayPercentage;
  }

  public Integer getUnitId() {
    return unitId;
  }

  public void setUnitId(Integer unitId) {
    this.unitId = unitId;
  }

  public Integer getOrganizationId() {
    return organizationId;
  }

  public void setOrganizationId(Integer organizationId) {
    this.organizationId = organizationId;
  }

  public Character getStatus() {
    return status;
  }

  public void setStatus(Character status) {
    this.status = status;
  }

  public Integer getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Integer createdBy) {
    this.createdBy = createdBy;
  }

  public Integer getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(Integer updatedBy) {
    this.updatedBy = updatedBy;
  }

  public Long getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Long createdDate) {
    this.createdDate = createdDate;
  }

  public Long getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Long updatedDate) {
    this.updatedDate = updatedDate;
  }

  public Double getDiscount() {
    return discount;
  }

  public void setDiscount(Double discount) {
    this.discount = discount;
  }

  public Integer getTaxId() {
    return taxId;
  }

  public void setTaxId(Integer taxId) {
    this.taxId = taxId;
  }

  public Double getTaxPer() {
    return taxPer;
  }

  public void setTaxPer(Double taxPer) {
    this.taxPer = taxPer;
  }

  public Double getTaxAmount() {
    return taxAmount;
  }

  public void setTaxAmount(Double taxAmount) {
    this.taxAmount = taxAmount;
  }

  public Double getNetAmt() {
    return netAmt;
  }

  public void setNetAmt(Double netAmt) {
    this.netAmt = netAmt;
  }

  public Integer getOrdDocSplId() {
    return ordDocSplId;
  }

  public void setOrdDocSplId(Integer ordDocSplId) {
    this.ordDocSplId = ordDocSplId;
  }

  public Integer getOrdDocId() {
    return ordDocId;
  }

  public void setOrdDocId(Integer ordDocId) {
    this.ordDocId = ordDocId;
  }

  public Integer getDrugId() {
    return drugId;
  }

  public void setDrugId(Integer drugId) {
    this.drugId = drugId;
  }

  public Integer getBatchId() {
    return batchId;
  }

  public void setBatchId(Integer batchId) {
    this.batchId = batchId;
  }

  public Long getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(Long orderDate) {
    this.orderDate = orderDate;
  }

  public Long getOrderSchDate() {
    return orderSchDate;
  }

  public void setOrderSchDate(Long orderSchDate) {
    this.orderSchDate = orderSchDate;
  }

  public Double getSelfPayable() {
    return selfPayable;
  }

  public void setSelfPayable(Double selfPayable) {
    this.selfPayable = selfPayable;
  }

  public Double getCreditPayable() {
    return creditPayable;
  }

  public void setCreditPayable(Double creditPayable) {
    this.creditPayable = creditPayable;
  }

  public Integer getPayeeId() {
    return payeeId;
  }

  public void setPayeeId(Integer payeeId) {
    this.payeeId = payeeId;
  }

  public Double getCoPayPer() {
    return coPayPer;
  }

  public void setCoPayPer(Double coPayPer) {
    this.coPayPer = coPayPer;
  }

}
