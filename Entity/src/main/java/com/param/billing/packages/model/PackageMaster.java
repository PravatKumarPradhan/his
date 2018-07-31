package com.param.billing.packages.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="m_package_master", schema="service")
@SequenceGenerator(name = "package_master_seq", sequenceName = "service.package_master_seq", allocationSize = 1)
public class PackageMaster {
	@Id
	@Column(name = "package_master_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "package_master_seq")
	private int packageMasterId;
	  
	@Column(name = "package_code")
	private String packageCode;
	  
	@Column(name = "package_name")
	private String packageName;
	  
	@Column(name = "package_gl_code_id")
	private Integer packageGlCodeId;
	  
	@Column(name = "visit_type_id")
	private Integer visitTypeId;
	  
	@Column(name = "patient_type_id")
	private Integer patientTypeId;
	  
	@Column(name = "package_type_id")
	private Integer packageTypeId;
	 
	@Column(name = "bed_category_id")
	private Integer bedCategoryId;
	  
	@Column(name = "payment_entitlement_type_id")
	private Integer paymentEntitlementTypeId;
	  
	@Column(name = "sex_id")
	private Integer sexId;
	  
	@Column(name = "validity_from_date")
	private Date validityFromDate;
	  
	@Column(name = "validity_to_date")
	private Date validityToDate;
	  
	@Column(name = "package_cost")
	private double packageCost;
	  
	@Column(name = "markup_down_in_percentage")
	private double markupDownInPercentage;
	  
	@Column(name = "package_price")
	private double packagePrice;
	  
	@Column(name = "is_manual_rounding_is_Allow")
	private char isManualRoundingIsAllow;
	  
	@Column(name = "manual_roundoff_Amount")
	private double manualRoundoffAmount;

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

	public int getPackageMasterId() {
		return packageMasterId;
	}

	public void setPackageMasterId(int packageMasterId) {
		this.packageMasterId = packageMasterId;
	}

	public String getPackageCode() {
		return packageCode;
	}

	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Integer getPackageGlCodeId() {
		return packageGlCodeId;
	}

	public void setPackageGlCodeId(Integer packageGlCodeId) {
		this.packageGlCodeId = packageGlCodeId;
	}

	public Integer getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public Integer getPatientTypeId() {
		return patientTypeId;
	}

	public void setPatientTypeId(Integer patientTypeId) {
		this.patientTypeId = patientTypeId;
	}

	public Integer getPackageTypeId() {
		return packageTypeId;
	}

	public void setPackageTypeId(Integer packageTypeId) {
		this.packageTypeId = packageTypeId;
	}

	public Integer getBedCategoryId() {
		return bedCategoryId;
	}

	public void setBedCategoryId(Integer bedCategoryId) {
		this.bedCategoryId = bedCategoryId;
	}

	public Integer getPaymentEntitlementTypeId() {
		return paymentEntitlementTypeId;
	}

	public void setPaymentEntitlementTypeId(Integer paymentEntitlementTypeId) {
		this.paymentEntitlementTypeId = paymentEntitlementTypeId;
	}

	public Integer getSexId() {
		return sexId;
	}

	public void setSexId(Integer sexId) {
		this.sexId = sexId;
	}

	public Date getValidityFromDate() {
		return validityFromDate;
	}

	public void setValidityFromDate(Date validityFromDate) {
		this.validityFromDate = validityFromDate;
	}

	public Date getValidityToDate() {
		return validityToDate;
	}

	public void setValidityToDate(Date validityToDate) {
		this.validityToDate = validityToDate;
	}

	public double getPackageCost() {
		return packageCost;
	}

	public void setPackageCost(double packageCost) {
		this.packageCost = packageCost;
	}

	public double getMarkupDownInPercentage() {
		return markupDownInPercentage;
	}

	public void setMarkupDownInPercentage(double markupDownInPercentage) {
		this.markupDownInPercentage = markupDownInPercentage;
	}

	public double getPackagePrice() {
		return packagePrice;
	}

	public void setPackagePrice(double packagePrice) {
		this.packagePrice = packagePrice;
	}

	public char getIsManualRoundingIsAllow() {
		return isManualRoundingIsAllow;
	}

	public void setIsManualRoundingIsAllow(char isManualRoundingIsAllow) {
		this.isManualRoundingIsAllow = isManualRoundingIsAllow;
	}

	public double getManualRoundoffAmount() {
		return manualRoundoffAmount;
	}

	public void setManualRoundoffAmount(double manualRoundoffAmount) {
		this.manualRoundoffAmount = manualRoundoffAmount;
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
