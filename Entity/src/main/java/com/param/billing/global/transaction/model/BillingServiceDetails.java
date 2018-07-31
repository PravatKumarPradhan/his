package com.param.billing.global.transaction.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="t_billing_service_details",schema="billing")
@SequenceGenerator(name = "billing_service_details_seq", sequenceName = "billing.billing_service_details_seq", allocationSize = 1)
public class BillingServiceDetails {
	@Id
	@Column(name = "billing_service_details_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "billing_service_details_seq")
	private int billingServiceDetailsId;
	  
	@Column(name = "billing_master_id")
	private Integer billingMasterId;
	  
	@Column(name = "service_id")
	private Integer serviceId;
	  
	@Column(name = "rate")
	private double rate;
	  
	@Column(name = "quantity")
	private double  quantity;
	  
	@Column(name = "concession_amount")
	private double concessionAmount;
	  
	@Column(name = "concession")
	private  double concession;
	  
	@Column(name = "gross_amount")
	private double  grossAmount;
	  
	@Column(name = "remark")
	private String remark;
	  
	@Column(name = "gl_number_id")
	private int glNumberId;
	   
	@Column(name = "pc_center_id")
	private Integer pcCenterId;
	  
	@Column(name = "ordered_by_id")
	private Integer orderedById;
	  
	@Column(name = "is_from_package")
	private char isFromPackage;
	  
	@Column(name = "package_id")
	private Integer packageId;
	  
	@Column(name = "unit_id")
	private Integer unitId;
	
	@Column(name = "orgnisation_id")
	private Integer orgnisationId;
	
	@Column(name = "status")
	private char status;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "created_by")
	private Integer createdBy;
	
	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name = "updated_by")
	private Integer updatedBy;

	public int getBillingServiceDetailsId() {
		return billingServiceDetailsId;
	}

	public void setBillingServiceDetailsId(int billingServiceDetailsId) {
		this.billingServiceDetailsId = billingServiceDetailsId;
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

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getConcessionAmount() {
		return concessionAmount;
	}

	public void setConcessionAmount(double concessionAmount) {
		this.concessionAmount = concessionAmount;
	}

	public double getConcession() {
		return concession;
	}

	public void setConcession(double concession) {
		this.concession = concession;
	}

	public double getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount(double grossAmount) {
		this.grossAmount = grossAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getGlNumberId() {
		return glNumberId;
	}

	public void setGlNumberId(int glNumberId) {
		this.glNumberId = glNumberId;
	}

	public Integer getPcCenterId() {
		return pcCenterId;
	}

	public void setPcCenterId(Integer pcCenterId) {
		this.pcCenterId = pcCenterId;
	}

	public Integer getOrderedById() {
		return orderedById;
	}

	public void setOrderedById(Integer orderedById) {
		this.orderedById = orderedById;
	}

	public char getIsFromPackage() {
		return isFromPackage;
	}

	public void setIsFromPackage(char isFromPackage) {
		this.isFromPackage = isFromPackage;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getOrgnisationId() {
		return orgnisationId;
	}

	public void setOrgnisationId(Integer orgnisationId) {
		this.orgnisationId = orgnisationId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
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
