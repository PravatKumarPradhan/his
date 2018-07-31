package com.param.global.dto;

public class ServicePriceDetailsDto {
	
	private Integer serviceMasterId;
	
	private Integer serviceCodeTypeId;
	
	private Integer organizationId;
	
	private String serviceAliasName;
	
	private String serviceStandardName;
	
	private Integer specialityId;
	
	private Integer subSpecialityId;
	
	private String serviceShortDescription;
	
	private String serviceLongDescripton;
	
	private char isAvailableForPackage;
	
	private char isOutsourceService;
	
	private char isOtProcedure;
	
	private char isOrderSet;
	
	private char isRateEditable;
	
	private float minRateEditable;
	
	private float maxRateEditable;
	
	private char isDiscountApplicable;
	
	private char isAllowMultipleQuantity;
	
	private char isDoctorRequired;
	
	private char isTaxApplicable;
	
	private Integer renderingDepartmentId;
	
	private char isAutorendered;
	
	private int createdBy;
	
	private String createdDate;
	
	private char status;
	
	private int updatedBy;
	
	private String updatedDate;
	
	private Integer unitId;
	
	private String specialityName;
	
	private String subSpecialityName;
	
	private Integer visitTypeId;
	
	private Double basePrice;
	
	private Double patientTypeMultiplicationFactor;
	private Double patientTypeFactor;
	
	private Double bedCategoryMultiplicationFactor;
	private Double bedCategoryFactor;
	
	private Double paymentEntitlementMultiplicationFactor;
	private Double paymentEntitlementFactor;
	
	private Double statChargesMultiplicationFactor;
	private Double statCharges;

	public Integer getServiceMasterId() {
		return serviceMasterId;
	}

	public void setServiceMasterId(Integer serviceMasterId) {
		this.serviceMasterId = serviceMasterId;
	}

	public Integer getServiceCodeTypeId() {
		return serviceCodeTypeId;
	}

	public void setServiceCodeTypeId(Integer serviceCodeTypeId) {
		this.serviceCodeTypeId = serviceCodeTypeId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getServiceAliasName() {
		return serviceAliasName;
	}

	public void setServiceAliasName(String serviceAliasName) {
		this.serviceAliasName = serviceAliasName;
	}

	public String getServiceStandardName() {
		return serviceStandardName;
	}

	public void setServiceStandardName(String serviceStandardName) {
		this.serviceStandardName = serviceStandardName;
	}

	public Integer getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(Integer specialityId) {
		this.specialityId = specialityId;
	}

	public Integer getSubSpecialityId() {
		return subSpecialityId;
	}

	public void setSubSpecialityId(Integer subSpecialityId) {
		this.subSpecialityId = subSpecialityId;
	}

	public String getServiceShortDescription() {
		return serviceShortDescription;
	}

	public void setServiceShortDescription(String serviceShortDescription) {
		this.serviceShortDescription = serviceShortDescription;
	}

	public String getServiceLongDescripton() {
		return serviceLongDescripton;
	}

	public void setServiceLongDescripton(String serviceLongDescripton) {
		this.serviceLongDescripton = serviceLongDescripton;
	}

	public char getIsAvailableForPackage() {
		return isAvailableForPackage;
	}

	public void setIsAvailableForPackage(char isAvailableForPackage) {
		this.isAvailableForPackage = isAvailableForPackage;
	}

	public char getIsOutsourceService() {
		return isOutsourceService;
	}

	public void setIsOutsourceService(char isOutsourceService) {
		this.isOutsourceService = isOutsourceService;
	}

	public char getIsOtProcedure() {
		return isOtProcedure;
	}

	public void setIsOtProcedure(char isOtProcedure) {
		this.isOtProcedure = isOtProcedure;
	}

	public char getIsOrderSet() {
		return isOrderSet;
	}

	public void setIsOrderSet(char isOrderSet) {
		this.isOrderSet = isOrderSet;
	}

	public char getIsRateEditable() {
		return isRateEditable;
	}

	public void setIsRateEditable(char isRateEditable) {
		this.isRateEditable = isRateEditable;
	}

	public float getMinRateEditable() {
		return minRateEditable;
	}

	public void setMinRateEditable(float minRateEditable) {
		this.minRateEditable = minRateEditable;
	}

	public float getMaxRateEditable() {
		return maxRateEditable;
	}

	public void setMaxRateEditable(float maxRateEditable) {
		this.maxRateEditable = maxRateEditable;
	}

	public char getIsDiscountApplicable() {
		return isDiscountApplicable;
	}

	public void setIsDiscountApplicable(char isDiscountApplicable) {
		this.isDiscountApplicable = isDiscountApplicable;
	}

	public char getIsAllowMultipleQuantity() {
		return isAllowMultipleQuantity;
	}

	public void setIsAllowMultipleQuantity(char isAllowMultipleQuantity) {
		this.isAllowMultipleQuantity = isAllowMultipleQuantity;
	}

	public char getIsDoctorRequired() {
		return isDoctorRequired;
	}

	public void setIsDoctorRequired(char isDoctorRequired) {
		this.isDoctorRequired = isDoctorRequired;
	}

	public char getIsTaxApplicable() {
		return isTaxApplicable;
	}

	public void setIsTaxApplicable(char isTaxApplicable) {
		this.isTaxApplicable = isTaxApplicable;
	}

	public Integer getRenderingDepartmentId() {
		return renderingDepartmentId;
	}

	public void setRenderingDepartmentId(Integer renderingDepartmentId) {
		this.renderingDepartmentId = renderingDepartmentId;
	}

	public char getIsAutorendered() {
		return isAutorendered;
	}

	public void setIsAutorendered(char isAutorendered) {
		this.isAutorendered = isAutorendered;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public String getSpecialityName() {
		return specialityName;
	}

	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}

	public String getSubSpecialityName() {
		return subSpecialityName;
	}

	public void setSubSpecialityName(String subSpecialityName) {
		this.subSpecialityName = subSpecialityName;
	}

	public Integer getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public Double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}

	public Double getPatientTypeFactor() {
		return patientTypeFactor;
	}

	public void setPatientTypeFactor(Double patientTypeFactor) {
		this.patientTypeFactor = patientTypeFactor;
	}

	public Double getBedCategoryFactor() {
		return bedCategoryFactor;
	}

	public void setBedCategoryFactor(Double bedCategoryFactor) {
		this.bedCategoryFactor = bedCategoryFactor;
	}

	public Double getPaymentEntitlementFactor() {
		return paymentEntitlementFactor;
	}

	public void setPaymentEntitlementFactor(Double paymentEntitlementFactor) {
		this.paymentEntitlementFactor = paymentEntitlementFactor;
	}

	public Double getStatCharges() {
		return statCharges;
	}

	public void setStatCharges(Double statCharges) {
		this.statCharges = statCharges;
	}

	public Double getPatientTypeMultiplicationFactor() {
		return patientTypeMultiplicationFactor;
	}

	public void setPatientTypeMultiplicationFactor(
			Double patientTypeMultiplicationFactor) {
		this.patientTypeMultiplicationFactor = patientTypeMultiplicationFactor;
	}

	public Double getBedCategoryMultiplicationFactor() {
		return bedCategoryMultiplicationFactor;
	}

	public void setBedCategoryMultiplicationFactor(
			Double bedCategoryMultiplicationFactor) {
		this.bedCategoryMultiplicationFactor = bedCategoryMultiplicationFactor;
	}

	public Double getPaymentEntitlementMultiplicationFactor() {
		return paymentEntitlementMultiplicationFactor;
	}

	public void setPaymentEntitlementMultiplicationFactor(
			Double paymentEntitlementMultiplicationFactor) {
		this.paymentEntitlementMultiplicationFactor = paymentEntitlementMultiplicationFactor;
	}

	public Double getStatChargesMultiplicationFactor() {
		return statChargesMultiplicationFactor;
	}

	public void setStatChargesMultiplicationFactor(
			Double statChargesMultiplicationFactor) {
		this.statChargesMultiplicationFactor = statChargesMultiplicationFactor;
	}
	
	
	
	
}
