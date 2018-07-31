package com.param.service.dto;

public class MPackageMasterDto {
	private Integer packageMasterId;
	private Integer visitTypeId;
	private Integer patientTypeId;
	private Integer packageTypeId;
	private Integer billingBedCategoryId;
	private Integer paymentEntitlementTypeId;
	private Integer sexId;
	private String validityFromDate;
	private String validityToDate;
	private char status;
	private double packageCost;
	private double markupDownInPercentage;
	private double packagePrice;
	private char isManualRoundingIsAllow;
	private double manualRoundoffAmount;
	private String createdDate;
	private Integer createdBy;
	private String updatedDate;
	private Integer updatedBy;
	private int validityPeriodIndays;
	private Integer minAge;
	private Integer maxAge;
	private Integer serviceId;
	private Integer orgId;
	private Integer unitId;
	private Integer tariffId;
	private Integer isMarkupDown;
	private Integer roundOffPositNeg;
	private Integer noOfEncounters;
	private String packageName;
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public Integer getNoOfEncounters() {
		return noOfEncounters;
	}
	public void setNoOfEncounters(Integer noOfEncounters) {
		this.noOfEncounters = noOfEncounters;
	}
	public Integer getTariffId() {
		return tariffId;
	}
	public void setTariffId(Integer tariffId) {
		this.tariffId = tariffId;
	}
	public Integer getIsMarkupDown() {
		return isMarkupDown;
	}
	public void setIsMarkupDown(Integer isMarkupDown) {
		this.isMarkupDown = isMarkupDown;
	}
	public Integer getRoundOffPositNeg() {
		return roundOffPositNeg;
	}
	public void setRoundOffPositNeg(Integer roundOffPositNeg) {
		this.roundOffPositNeg = roundOffPositNeg;
	}
	public Integer getPackageMasterId() {
		return packageMasterId;
	}
	public void setPackageMasterId(Integer packageMasterId) {
		this.packageMasterId = packageMasterId;
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
	public Integer getBillingBedCategoryId() {
		return billingBedCategoryId;
	}
	public void setBillingBedCategoryId(Integer billingBedCategoryId) {
		this.billingBedCategoryId = billingBedCategoryId;
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
	public String getValidityFromDate() {
		return validityFromDate;
	}
	public void setValidityFromDate(String validityFromDate) {
		this.validityFromDate = validityFromDate;
	}
	public String getValidityToDate() {
		return validityToDate;
	}
	public void setValidityToDate(String validityToDate) {
		this.validityToDate = validityToDate;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = (status != '\u0000' ? status : 'A');
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
		this.isManualRoundingIsAllow = (isManualRoundingIsAllow != '\u0000' ? isManualRoundingIsAllow : 'N');
	}
	public double getManualRoundoffAmount() {
		return manualRoundoffAmount;
	}
	public void setManualRoundoffAmount(double manualRoundoffAmount) {
		this.manualRoundoffAmount = manualRoundoffAmount;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	public int getValidityPeriodIndays() {
		return validityPeriodIndays;
	}
	public void setValidityPeriodIndays(int validityPeriodIndays) {
		this.validityPeriodIndays = validityPeriodIndays;
	}
	public Integer getMinAge() {
		return minAge;
	}
	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}
	public Integer getMaxAge() {
		return maxAge;
	}
	public void setMaxAge(Integer maxAge) {
		this.maxAge = maxAge;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
}
