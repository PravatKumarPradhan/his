package com.param.lis.transaction.dto;

import java.time.LocalTime;

public class PatientArrivalMapperMasterDto
{

	private PatientArrivalMasterId patientArrivalMasterId;
	private Integer orgId;
	private Integer orgUnitId;
	private Integer testId;
	private Integer deptId;
	private Integer pendingCount;
	private Integer doctorId;
	private Long collectionTime ;
	private Integer createdBy;
	private Long createdDate;
	private Integer orderDetailsId;
	public PatientArrivalMasterId getPatientArrivalMasterId() {
		return patientArrivalMasterId;
	}
	public void setPatientArrivalMasterId(
			PatientArrivalMasterId patientArrivalMasterId) {
		this.patientArrivalMasterId = patientArrivalMasterId;
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
	public Integer getTestId() {
		return testId;
	}
	public void setTestId(Integer testId) {
		this.testId = testId;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public Integer getPendingCount() {
		return pendingCount;
	}
	public void setPendingCount(Integer pendingCount) {
		this.pendingCount = pendingCount;
	}
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	public Long getCollectionTime() {
		return collectionTime;
	}
	public void setCollectionTime(Long collectionTime) 
	{
		this.collectionTime =collectionTime;
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
	public Integer getOrderDetailsId() {
		return orderDetailsId;
	}
	public void setOrderDetailsId(Integer orderDetailsId) {
		this.orderDetailsId = orderDetailsId;
	}
	
	
}
