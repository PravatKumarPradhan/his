package com.param.global.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ServiceMasterDto {

	private Integer serviceMasterId;

	private Integer serviceCodeTypeId;

	private Integer organizationId;

	private String serviceAliasName;

	private String serviceStandardName;

	private String serviceStandardCode;

	private Integer specialityId;

	private Integer subSpecialityId;

	private String serviceShortDescription;

	private String serviceLongDescripton;

	private Character isAvailableForPackage;

	private Character isOutsourceService;

	private Character isOtProcedure;

	private Character isOrderSet;

	private Character isRateEditable;

	private Float minRateEditable;

	private Float maxRateEditable;

	private Character isDiscountApplicable;

	private Character isAllowMultipleQuantity;

	private Character isDoctorRequired;

	private Character isTaxApplicable;

	private Integer renderingDepartmentId;

	private Character isAutorendered;

	private Integer createdBy;

	private String createdDate;

	private Character status;

	private Integer updatedBy;

	private String updatedDate;

	private Integer unitId;

	private String specialityName;

	private String subSpecialityName;

	private Double basePrice;

	private String searchKeyword;

	private Double unitPrice;

	private Double quantity;

	private Integer patientId;

	private Integer orderId;

	private Integer orderDetailsId;

	private Date orderDate;

	private String orderDateString;

	private Double concession;

	private Double discount;

	private Double discountPercentage;

	private Integer visitTypeId;

	private Integer bedCategoryId;

	private Integer patientTypeId;

	private Integer paymentTypeId;

	private Integer doctorId;

	private Integer defaultSelfTariffId;

	private Integer paymentEntitlementTypeId;

	private Double taxPercentage;

	private String taxName;

	private Integer ordDocSplId;

	private Integer taxId;

	private BigDecimal taxPer;

	private BigDecimal taxAmount;

	private BigDecimal selfPayable;

	private BigDecimal creditPayable;

	private Integer payeeId;

	private BigDecimal packageConsumptionAmt;

	private Integer orderSetId;

	private Integer contractId;

	private BigDecimal coPayPer;

	private Integer docSharePer;

	private BigDecimal ordTotalAmt;

	private Integer ordCancelBy;

	private String ordCancelRemark;

	private String codificationServiceName;

	private String validFrom;

	private String validTill;

	private Integer generalLedgerId;
	private String generalLedgerName;
	

	private Integer encounterId;
	
	
	
	public Integer getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}

	public Integer getOrdDocSplId() {
		return ordDocSplId;
	}

	public void setOrdDocSplId(Integer ordDocSplId) {
		this.ordDocSplId = ordDocSplId;
	}

	public Integer getTaxId() {
		return taxId;
	}

	public void setTaxId(Integer taxId) {
		this.taxId = taxId;
	}

	public BigDecimal getTaxPer() {
		return taxPer;
	}

	public void setTaxPer(BigDecimal taxPer) {
		this.taxPer = taxPer;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public BigDecimal getSelfPayable() {
		return selfPayable;
	}

	public void setSelfPayable(BigDecimal selfPayable) {
		this.selfPayable = selfPayable;
	}

	public BigDecimal getCreditPayable() {
		return creditPayable;
	}

	public void setCreditPayable(BigDecimal creditPayable) {
		this.creditPayable = creditPayable;
	}

	public Integer getPayeeId() {
		return payeeId;
	}

	public void setPayeeId(Integer payeeId) {
		this.payeeId = payeeId;
	}

	public BigDecimal getPackageConsumptionAmt() {
		return packageConsumptionAmt;
	}

	public void setPackageConsumptionAmt(BigDecimal packageConsumptionAmt) {
		this.packageConsumptionAmt = packageConsumptionAmt;
	}

	public Integer getOrderSetId() {
		return orderSetId;
	}

	public void setOrderSetId(Integer orderSetId) {
		this.orderSetId = orderSetId;
	}

	public Integer getContractId() {
		return contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	public BigDecimal getCoPayPer() {
		return coPayPer;
	}

	public void setCoPayPer(BigDecimal coPayPer) {
		this.coPayPer = coPayPer;
	}

	public Integer getDocSharePer() {
		return docSharePer;
	}

	public void setDocSharePer(Integer docSharePer) {
		this.docSharePer = docSharePer;
	}

	public BigDecimal getOrdTotalAmt() {
		return ordTotalAmt;
	}

	public void setOrdTotalAmt(BigDecimal ordTotalAmt) {
		this.ordTotalAmt = ordTotalAmt;
	}

	public Integer getOrdCancelBy() {
		return ordCancelBy;
	}

	public void setOrdCancelBy(Integer ordCancelBy) {
		this.ordCancelBy = ordCancelBy;
	}

	public String getOrdCancelRemark() {
		return ordCancelRemark;
	}

	public void setOrdCancelRemark(String ordCancelRemark) {
		this.ordCancelRemark = ordCancelRemark;
	}

	public String getTaxName() {
		return taxName;
	}

	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}

	public Double getTaxPercentage() {
		return taxPercentage;
	}

	public void setTaxPercentage(Double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	public String getOrderDateString() {
		return orderDateString;
	}

	public void setOrderDateString(String orderDateString) {
		this.orderDateString = orderDateString;
	}

	public Integer getPaymentEntitlementTypeId() {
		return paymentEntitlementTypeId;
	}

	public void setPaymentEntitlementTypeId(Integer paymentEntitlementTypeId) {
		this.paymentEntitlementTypeId = paymentEntitlementTypeId;
	}

	public Integer getDefaultSelfTariffId() {
		return defaultSelfTariffId;
	}

	public void setDefaultSelfTariffId(Integer defaultSelfTariffId) {
		this.defaultSelfTariffId = defaultSelfTariffId;
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

	public Character getIsAvailableForPackage() {
		return isAvailableForPackage;
	}

	public void setIsAvailableForPackage(Character isAvailableForPackage) {
		this.isAvailableForPackage = isAvailableForPackage;
	}

	public Character getIsOutsourceService() {
		return isOutsourceService;
	}

	public void setIsOutsourceService(Character isOutsourceService) {
		this.isOutsourceService = isOutsourceService;
	}

	public Character getIsOtProcedure() {
		return isOtProcedure;
	}

	public void setIsOtProcedure(Character isOtProcedure) {
		this.isOtProcedure = isOtProcedure;
	}

	public Character getIsOrderSet() {
		return isOrderSet;
	}

	public void setIsOrderSet(Character isOrderSet) {
		this.isOrderSet = isOrderSet;
	}

	public Character getIsRateEditable() {
		return isRateEditable;
	}

	public void setIsRateEditable(Character isRateEditable) {
		this.isRateEditable = isRateEditable;
	}

	public Float getMinRateEditable() {
		return minRateEditable;
	}

	public void setMinRateEditable(Float minRateEditable) {
		this.minRateEditable = minRateEditable;
	}

	public Float getMaxRateEditable() {
		return maxRateEditable;
	}

	public void setMaxRateEditable(Float maxRateEditable) {
		this.maxRateEditable = maxRateEditable;
	}

	public Character getIsDiscountApplicable() {
		return isDiscountApplicable;
	}

	public void setIsDiscountApplicable(Character isDiscountApplicable) {
		this.isDiscountApplicable = isDiscountApplicable;
	}

	public Character getIsAllowMultipleQuantity() {
		return isAllowMultipleQuantity;
	}

	public void setIsAllowMultipleQuantity(Character isAllowMultipleQuantity) {
		this.isAllowMultipleQuantity = isAllowMultipleQuantity;
	}

	public Character getIsDoctorRequired() {
		return isDoctorRequired;
	}

	public void setIsDoctorRequired(Character isDoctorRequired) {
		this.isDoctorRequired = isDoctorRequired;
	}

	public Character getIsTaxApplicable() {
		return isTaxApplicable;
	}

	public void setIsTaxApplicable(Character isTaxApplicable) {
		this.isTaxApplicable = isTaxApplicable;
	}

	public Integer getRenderingDepartmentId() {
		return renderingDepartmentId;
	}

	public void setRenderingDepartmentId(Integer renderingDepartmentId) {
		this.renderingDepartmentId = renderingDepartmentId;
	}

	public Character getIsAutorendered() {
		return isAutorendered;
	}

	public void setIsAutorendered(Character isAutorendered) {
		this.isAutorendered = isAutorendered;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getServiceMasterId() {
		return serviceMasterId;
	}

	public void setServiceMasterId(Integer serviceMasterId) {
		this.serviceMasterId = serviceMasterId;
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

	public Double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}

	public String getServiceStandardCode() {
		return serviceStandardCode;
	}

	public void setServiceStandardCode(String serviceStandardCode) {
		this.serviceStandardCode = serviceStandardCode;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderDetailsId() {
		return orderDetailsId;
	}

	public void setOrderDetailsId(Integer orderDetailsId) {
		this.orderDetailsId = orderDetailsId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Double getConcession() {
		return concession;
	}

	public void setConcession(Double concession) {
		this.concession = concession;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(Double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public Integer getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public Integer getBedCategoryId() {
		return bedCategoryId;
	}

	public void setBedCategoryId(Integer bedCategoryId) {
		this.bedCategoryId = bedCategoryId;
	}

	public Integer getPatientTypeId() {
		return patientTypeId;
	}

	public void setPatientTypeId(Integer patientTypeId) {
		this.patientTypeId = patientTypeId;
	}

	public Integer getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(Integer paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public String getCodificationServiceName() {
		return codificationServiceName;
	}

	public void setCodificationServiceName(String codificationServiceName) {
		this.codificationServiceName = codificationServiceName;
	}

	public String getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}

	public String getValidTill() {
		return validTill;
	}

	public void setValidTill(String validTill) {
		this.validTill = validTill;
	}

	public Integer getGeneralLedgerId() {
		return generalLedgerId;
	}

	public void setGeneralLedgerId(Integer generalLedgerId) {
		this.generalLedgerId = generalLedgerId;
	}

	public String getGeneralLedgerName() {
		return generalLedgerName;
	}

	public void setGeneralLedgerName(String generalLedgerName) {
		this.generalLedgerName = generalLedgerName;
	}
	

}
