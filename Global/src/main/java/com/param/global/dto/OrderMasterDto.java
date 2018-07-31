package com.param.global.dto;

import java.util.List;

public class OrderMasterDto
{
	private Integer orderId;
	private Integer orgId;
	private Integer orgUnitId;
	private String orderNo;
	private Long orderDate;
	private Integer visitTypeId;
	private Integer encounterId;
	private Integer admissionId;
	private Integer ordDoctorId;
	private Integer patientId;
	private Integer priorityId;
	private String patientType;
	private Integer wardId;
	private Integer drCrId;
	private String ordRemarks;
	private Integer createdBy;
	private Long createdDate;
	private Integer updatedBy;
	private Long updatedDate;
	private Character orderStatus;
	private Integer orderStatusId;
	private double discountPercentage;
	private List<OrderDetailsMasterDto> listOrderDetailsMasterDto;
	private Integer doctorId;
	
	public Integer getEncounterId() {
		return encounterId;
	}
	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}
	public Integer getAdmissionId() {
		return admissionId;
	}
	public void setAdmissionId(Integer admissionId) {
		this.admissionId = admissionId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Long getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Long orderDate) {
		this.orderDate = orderDate;
	}
	public Integer getVisitTypeId() {
		return visitTypeId;
	}
	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
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
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Long getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
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
	public Character getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Character orderStatus) {
		this.orderStatus = orderStatus;
	}
	public List<OrderDetailsMasterDto> getListOrderDetailsMasterDto() {
		return listOrderDetailsMasterDto;
	}
	public void setListOrderDetailsMasterDto(List<OrderDetailsMasterDto> listOrderDetailsMasterDto) {
		this.listOrderDetailsMasterDto = listOrderDetailsMasterDto;
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public Integer getOrderStatusId() {
		return orderStatusId;
	}
	public void setOrderStatusId(Integer orderStatusId) {
		this.orderStatusId = orderStatusId;
	}
	public double getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	
}
