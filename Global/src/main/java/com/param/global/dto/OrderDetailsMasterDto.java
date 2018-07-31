package com.param.global.dto;

import java.math.BigDecimal;
import java.util.List;

public class OrderDetailsMasterDto {
	private Integer orderDetailsId;
	private Integer orgId;
	private Integer orgUnitId;
	private Integer orderId;
	private Integer orderQty;
	private Integer serviceId;
	private Integer priorityId;
	private Character isOutsourced;
	private Long orderDate;
	private Long orderSchDate;
	private String orderDateString;
	private String orderSchDateString;
	private BigDecimal serviceAmt;
	private BigDecimal ordConcession;
	private BigDecimal ordDiscount;
	private BigDecimal netAmount;
	private BigDecimal oldNetPay;
	private Integer tariffId;
	private Integer billingClassId;
	private Integer bedId;
	private Integer roomId;
	private Integer wardId;
	private Character orderEmergencyFlag;
	private Integer packageId;
	private Integer ordDocId;
	private Integer drugId;
	private Integer batchId;
	private Integer oldOrdDtlId;
	private Integer serviceRendered;
	private Integer serviceChargeable;
	private Integer serviceRenderingDeptId;
	private Long ordRenderDatetime;
	private Integer serviceIsBilled;
	private Integer serviceBillId;
	private String ordRemarks;
	private Long createdDate;
	private Integer createdBy;
	private Character ordCancelled;
	private Integer ordCancelReasonId;
	private Long ordCancelDatetime;
	private Character status;
	private Integer updatedBy;
	private Long updatedDate;
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
	private Integer bedCategoryId;
	private BigDecimal coPayPer;
	private Integer docSharePer;
	private BigDecimal ordTotalAmt;
	private Integer ordCancelBy;
	private String ordCancelRemark;
	private Character isDrug;
	private Integer encounterId;
	private Character isRateEditable;
	private BigDecimal rateEditbleMinValue;
	private BigDecimal rateEditableMaxValue;
	private Character isTaxApplicable;
	private Character isRefDoctorShare;
	private Character isDiscount;
	private BigDecimal discountValue;
	private Integer gstTypeId;
	private Integer otcGstTypeId;
	private Character isQuantityEditable;
	private List<OrderDetailsDiscountDto> discountDetailsList;
	private boolean isPackage;//yogesh
	private Integer packageTypeId;//yogesh
	public Integer getPackageTypeId() {
		return packageTypeId;
	}
	public void setPackageTypeId(Integer packageTypeId) {
		this.packageTypeId = packageTypeId;
	}
	public BigDecimal getOldNetPay() {
		return oldNetPay;
	}
	public void setOldNetPay(BigDecimal oldNetPay) {
		this.oldNetPay = oldNetPay;
	}
	public List<OrderDetailsDiscountDto> getDiscountDetailsList() {
		return discountDetailsList;
	}
	public void setDiscountDetailsList(List<OrderDetailsDiscountDto> discountDetailsList) {
		this.discountDetailsList = discountDetailsList;
	}
	public Integer getOrderDetailsId() {
		return orderDetailsId;
	}
	public void setOrderDetailsId(Integer orderDetailsId) {
		this.orderDetailsId = orderDetailsId;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public Integer getOrgUnitId() {
		return orgUnitId;
	}
	public void setOrgUnitId(Integer orgUnitId) {
		this.orgUnitId = orgUnitId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getOrderQty() {
		return orderQty;
	}
	public void setOrderQty(Integer orderQty) {
		this.orderQty = orderQty;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public Integer getPriorityId() {
		return priorityId;
	}
	public void setPriorityId(Integer priorityId) {
		this.priorityId = priorityId;
	}
	public Character getIsOutsourced() {
		return isOutsourced;
	}
	public void setIsOutsourced(Character isOutsourced) {
		this.isOutsourced = isOutsourced;
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
	public String getOrderDateString() {
		return orderDateString;
	}
	public void setOrderDateString(String orderDateString) {
		this.orderDateString = orderDateString;
	}
	public String getOrderSchDateString() {
		return orderSchDateString;
	}
	public void setOrderSchDateString(String orderSchDateString) {
		this.orderSchDateString = orderSchDateString;
	}
	public BigDecimal getServiceAmt() {
		return serviceAmt;
	}
	public void setServiceAmt(BigDecimal serviceAmt) {
		this.serviceAmt = serviceAmt;
	}
	public BigDecimal getOrdConcession() {
		return ordConcession;
	}
	public void setOrdConcession(BigDecimal ordConcession) {
		this.ordConcession = ordConcession;
	}
	public BigDecimal getOrdDiscount() {
		return ordDiscount;
	}
	public void setOrdDiscount(BigDecimal ordDiscount) {
		this.ordDiscount = ordDiscount;
	}
	public BigDecimal getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
	}
	public Integer getTariffId() {
		return tariffId;
	}
	public void setTariffId(Integer tariffId) {
		this.tariffId = tariffId;
	}
	public Integer getBillingClassId() {
		return billingClassId;
	}
	public void setBillingClassId(Integer billingClassId) {
		this.billingClassId = billingClassId;
	}
	public Integer getBedId() {
		return bedId;
	}
	public void setBedId(Integer bedId) {
		this.bedId = bedId;
	}
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public Integer getWardId() {
		return wardId;
	}
	public void setWardId(Integer wardId) {
		this.wardId = wardId;
	}
	public Character getOrderEmergencyFlag() {
		return orderEmergencyFlag;
	}
	public void setOrderEmergencyFlag(Character orderEmergencyFlag) {
		this.orderEmergencyFlag = orderEmergencyFlag;
	}
	public Integer getPackageId() {
		return packageId;
	}
	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
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
	public Integer getOldOrdDtlId() {
		return oldOrdDtlId;
	}
	public void setOldOrdDtlId(Integer oldOrdDtlId) {
		this.oldOrdDtlId = oldOrdDtlId;
	}
	public Integer getServiceRendered() {
		return serviceRendered;
	}
	public void setServiceRendered(Integer serviceRendered) {
		this.serviceRendered = serviceRendered;
	}
	public Integer getServiceChargeable() {
		return serviceChargeable;
	}
	public void setServiceChargeable(Integer serviceChargeable) {
		this.serviceChargeable = serviceChargeable;
	}
	public Integer getServiceRenderingDeptId() {
		return serviceRenderingDeptId;
	}
	public void setServiceRenderingDeptId(Integer serviceRenderingDeptId) {
		this.serviceRenderingDeptId = serviceRenderingDeptId;
	}
	public Long getOrdRenderDatetime() {
		return ordRenderDatetime;
	}
	public void setOrdRenderDatetime(Long ordRenderDatetime) {
		this.ordRenderDatetime = ordRenderDatetime;
	}
	public Integer getServiceIsBilled() {
		return serviceIsBilled;
	}
	public void setServiceIsBilled(Integer serviceIsBilled) {
		this.serviceIsBilled = serviceIsBilled;
	}
	public Integer getServiceBillId() {
		return serviceBillId;
	}
	public void setServiceBillId(Integer serviceBillId) {
		this.serviceBillId = serviceBillId;
	}
	public String getOrdRemarks() {
		return ordRemarks;
	}
	public void setOrdRemarks(String ordRemarks) {
		this.ordRemarks = ordRemarks;
	}
	public Long getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Character getOrdCancelled() {
		return ordCancelled;
	}
	public void setOrdCancelled(Character ordCancelled) {
		this.ordCancelled = ordCancelled;
	}
	public Integer getOrdCancelReasonId() {
		return ordCancelReasonId;
	}
	public void setOrdCancelReasonId(Integer ordCancelReasonId) {
		this.ordCancelReasonId = ordCancelReasonId;
	}
	public Long getOrdCancelDatetime() {
		return ordCancelDatetime;
	}
	public void setOrdCancelDatetime(Long ordCancelDatetime) {
		this.ordCancelDatetime = ordCancelDatetime;
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
	public Long getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Long updatedDate) {
		this.updatedDate = updatedDate;
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
	public Integer getBedCategoryId() {
		return bedCategoryId;
	}
	public void setBedCategoryId(Integer bedCategoryId) {
		this.bedCategoryId = bedCategoryId;
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
	public Character getIsDrug() {
		return isDrug;
	}
	public void setIsDrug(Character isDrug) {
		this.isDrug = isDrug;
	}
	public Integer getEncounterId() {
		return encounterId;
	}
	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}
	public Character getIsRateEditable() {
		return isRateEditable;
	}
	public void setIsRateEditable(Character isRateEditable) {
		this.isRateEditable = isRateEditable;
	}
	public BigDecimal getRateEditbleMinValue() {
		return rateEditbleMinValue;
	}
	public void setRateEditbleMinValue(BigDecimal rateEditbleMinValue) {
		this.rateEditbleMinValue = rateEditbleMinValue;
	}
	public BigDecimal getRateEditableMaxValue() {
		return rateEditableMaxValue;
	}
	public void setRateEditableMaxValue(BigDecimal rateEditableMaxValue) {
		this.rateEditableMaxValue = rateEditableMaxValue;
	}
	public Character getIsTaxApplicable() {
		return isTaxApplicable;
	}
	public void setIsTaxApplicable(Character isTaxApplicable) {
		this.isTaxApplicable = isTaxApplicable;
	}
	public Character getIsRefDoctorShare() {
		return isRefDoctorShare;
	}
	public void setIsRefDoctorShare(Character isRefDoctorShare) {
		this.isRefDoctorShare = isRefDoctorShare;
	}
	public Character getIsDiscount() {
		return isDiscount;
	}
	public void setIsDiscount(Character isDiscount) {
		this.isDiscount = isDiscount;
	}
	public BigDecimal getDiscountValue() {
		return discountValue;
	}
	public void setDiscountValue(BigDecimal discountValue) {
		this.discountValue = discountValue;
	}
	public Integer getGstTypeId() {
		return gstTypeId;
	}
	public void setGstTypeId(Integer gstTypeId) {
		this.gstTypeId = gstTypeId;
	}
	public Integer getOtcGstTypeId() {
		return otcGstTypeId;
	}
	public void setOtcGstTypeId(Integer otcGstTypeId) {
		this.otcGstTypeId = otcGstTypeId;
	}
	public Character getIsQuantityEditable() {
		return isQuantityEditable;
	}
	public void setIsQuantityEditable(Character isQuantityEditable) {
		this.isQuantityEditable = isQuantityEditable;
	}
	public boolean isPackage() {
		return isPackage;
	}
	public void setIsPackage(boolean isPackage) {
		this.isPackage = isPackage;
	}	
	
	
}
