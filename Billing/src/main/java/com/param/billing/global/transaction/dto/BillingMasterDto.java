package com.param.billing.global.transaction.dto;

import java.util.List;

import com.param.global.dto.OrderDetailsDiscountDto;
import com.param.global.dto.OrderDetailsMasterDto;

public class BillingMasterDto {
	
	private Character billCancelled;

	private String billRemark; 
	
	private Integer billingMasterId;
	
	private Integer encounterId;
	
	private Integer visitTypeId;

	private Integer patientId;

	private String billNumber;

	private Long billDateTime;

	private Double netAmount;

	private Double discountAmount;

	private Double grossAmount;
	
	private Double concessionAmount;
	
	private Double gst;
	
	private Double totalBillAmount;
	
	private Double outstanding;
	
	private Double roundOff;

	private Character isCreditBill;

	private Integer unitId;

	private Integer organisationId;

	private Character status;

	private Long createdDate;

	private Integer createdBy;

	private Long updatedDate;

	private Integer updatedBy;
	
	private Double paidAmount;
	
	private Integer billStatusId;
	
	private Double basePrice;
	
	private Integer bedTypeId;
	
	private Integer patientTypeId;
	
	private Integer paymentTypeId;
	
	private char isDiscountInPercentage;
	
	private char isDiscountApplicable;
	
	private String orderDate;
	
	private Integer tariffId;
	
	private Character isFinalBill;
	
	private Double selfPayable;
	
	private Double creditPayable;
	
	private Integer orderId;
	
	private Integer admissionId;
	
	private Integer ordDoctorId;
	
	private Integer priorityId;
	
	private String patientType;
	
	private Integer wardId;
	
	private Integer drCrId;
	
	private String ordRemarks;
	
	private Character orderStatus;
	
	private Integer orderStatusId;
	
	private List<OrderDetailsMasterDto> orderDetailsMasterDtosList;
	
	private List<OrderDetailsMasterDto> cancelOrderDetailsList;
	
	private List<OrderDetailsDiscountDto> cancelledDiscountsList;
	
	
	public List<OrderDetailsMasterDto> getCancelOrderDetailsList() {
		return cancelOrderDetailsList;
	}

	public void setCancelOrderDetailsList(List<OrderDetailsMasterDto> cancelOrderDetailsList) {
		this.cancelOrderDetailsList = cancelOrderDetailsList;
	}

	public List<OrderDetailsDiscountDto> getCancelledDiscountsList() {
		return cancelledDiscountsList;
	}

	public void setCancelledDiscountsList(List<OrderDetailsDiscountDto> cancelledDiscountsList) {
		this.cancelledDiscountsList = cancelledDiscountsList;
	}

	public Character getBillCancelled() {
		return billCancelled;
	}

	public void setBillCancelled(Character billCancelled) {
		this.billCancelled = billCancelled;
	}

	public String getBillRemark() {
		return billRemark;
	}

	public void setBillRemark(String billRemark) {
		this.billRemark = billRemark;
	}

	public Integer getBillingMasterId() {
		return billingMasterId;
	}

	public void setBillingMasterId(Integer billingMasterId) {
		this.billingMasterId = billingMasterId;
	}

	public Integer getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}

	public Integer getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	public Long getBillDateTime() {
		return billDateTime;
	}

	public void setBillDateTime(Long billDateTime) {
		this.billDateTime = billDateTime;
	}

	public Double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Double getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount(Double grossAmount) {
		this.grossAmount = grossAmount;
	}

	public Double getConcessionAmount() {
		return concessionAmount;
	}

	public void setConcessionAmount(Double concessionAmount) {
		this.concessionAmount = concessionAmount;
	}

	public Double getGst() {
		return gst;
	}

	public void setGst(Double gst) {
		this.gst = gst;
	}

	public Double getTotalBillAmount() {
		return totalBillAmount;
	}

	public void setTotalBillAmount(Double totalBillAmount) {
		this.totalBillAmount = totalBillAmount;
	}

	public Double getOutstanding() {
		return outstanding;
	}

	public void setOutstanding(Double outstanding) {
		this.outstanding = outstanding;
	}

	public Double getRoundOff() {
		return roundOff;
	}

	public void setRoundOff(Double roundOff) {
		this.roundOff = roundOff;
	}

	public Character getIsCreditBill() {
		return isCreditBill;
	}

	public void setIsCreditBill(Character isCreditBill) {
		this.isCreditBill = isCreditBill;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
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

	public Long getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Long updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Integer getBillStatusId() {
		return billStatusId;
	}

	public void setBillStatusId(Integer billStatusId) {
		this.billStatusId = billStatusId;
	}

	public Double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}

	public Integer getBedTypeId() {
		return bedTypeId;
	}

	public void setBedTypeId(Integer bedTypeId) {
		this.bedTypeId = bedTypeId;
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

	public char getIsDiscountInPercentage() {
		return isDiscountInPercentage;
	}

	public void setIsDiscountInPercentage(char isDiscountInPercentage) {
		this.isDiscountInPercentage = isDiscountInPercentage;
	}

	public char getIsDiscountApplicable() {
		return isDiscountApplicable;
	}

	public void setIsDiscountApplicable(char isDiscountApplicable) {
		this.isDiscountApplicable = isDiscountApplicable;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public Integer getTariffId() {
		return tariffId;
	}

	public void setTariffId(Integer tariffId) {
		this.tariffId = tariffId;
	}

	public Character getIsFinalBill() {
		return isFinalBill;
	}

	public void setIsFinalBill(Character isFinalBill) {
		this.isFinalBill = isFinalBill;
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

	public List<OrderDetailsMasterDto> getOrderDetailsMasterDtosList() {
		return orderDetailsMasterDtosList;
	}

	public void setOrderDetailsMasterDtosList(List<OrderDetailsMasterDto> orderDetailsMasterDtosList) {
		this.orderDetailsMasterDtosList = orderDetailsMasterDtosList;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(Integer admissionId) {
		this.admissionId = admissionId;
	}

	public Integer getOrdDoctorId() {
		return ordDoctorId;
	}

	public void setOrdDoctorId(Integer ordDoctorId) {
		this.ordDoctorId = ordDoctorId;
	}

	public Integer getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(Integer priorityId) {
		this.priorityId = priorityId;
	}

	public String getPatientType() {
		return patientType;
	}

	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}

	public Integer getWardId() {
		return wardId;
	}

	public void setWardId(Integer wardId) {
		this.wardId = wardId;
	}

	public Integer getDrCrId() {
		return drCrId;
	}

	public void setDrCrId(Integer drCrId) {
		this.drCrId = drCrId;
	}

	public String getOrdRemarks() {
		return ordRemarks;
	}

	public void setOrdRemarks(String ordRemarks) {
		this.ordRemarks = ordRemarks;
	}

	public Character getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Character orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getOrderStatusId() {
		return orderStatusId;
	}

	public void setOrderStatusId(Integer orderStatusId) {
		this.orderStatusId = orderStatusId;
	}
	
	
}
