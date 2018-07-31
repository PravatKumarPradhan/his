package com.param.lis.transaction.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class LabSampleMasterDto {
	private Integer labSampleId;

	private Integer orgId;

	private Integer orgUnitId;

	private Date sampleGenDatetime;

	private Integer orderId;

	private Integer visitTypeId;

	private Integer visitAdmId;

	private Integer patientId;

	private Integer doctorId;

	private Integer createdBy;

	private Date createdDate;

	private Integer updatedBy;

	private Date updatedDate;

	private Date collectionTime;

	private String uhid;

	private String patientDetails;

	private String doctorDetails;

	private Integer priorityId;

	private String priorityName;

	private String colorCode;

	private Integer isOrderComplete;
	
	private Integer orderStatusId;
	
	private Integer genderId;
	
	private String visitType;

	private List<LabSampleDetailsMasterDto> listLabSampleDetailsMaster;

	public Integer getLabSampleId() {
		return labSampleId;
	}

	public void setLabSampleId(Integer labSampleId) {
		this.labSampleId = labSampleId;
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

	public Date getSampleGenDatetime() {
		return sampleGenDatetime;
	}

	public void setSampleGenDatetime(Date sampleGenDatetime) {
		this.sampleGenDatetime = sampleGenDatetime;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public Integer getVisitAdmId() {
		return visitAdmId;
	}

	public void setVisitAdmId(Integer visitAdmId) {
		this.visitAdmId = visitAdmId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUhid() {
		return uhid;
	}

	public void setUhid(String uhid) {
		this.uhid = uhid;
	}

	public String getPatientDetails() {
		return patientDetails;
	}

	public void setPatientDetails(String patientDetails) {
		this.patientDetails = patientDetails;
	}

	public String getDoctorDetails() {
		return doctorDetails;
	}

	public void setDoctorDetails(String doctorDetails) {
		this.doctorDetails = doctorDetails;
	}

	public Integer getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(Integer priorityId) {
		this.priorityId = priorityId;
	}

	public String getPriorityName() {
		return priorityName;
	}

	public void setPriorityName(String priorityName) {
		this.priorityName = priorityName;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public Integer getIsOrderComplete() {
		return isOrderComplete;
	}

	public void setIsOrderComplete(Integer isOrderComplete) {
		this.isOrderComplete = isOrderComplete;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public List<LabSampleDetailsMasterDto> getListLabSampleDetailsMaster() {
		return listLabSampleDetailsMaster;
	}

	public void setListLabSampleDetailsMaster(List<LabSampleDetailsMasterDto> listLabSampleDetailsMaster) {
		this.listLabSampleDetailsMaster = listLabSampleDetailsMaster;
	}

	public LocalTime getCollectionTime() 
	{
		//return collectionTime;
		LocalTime collectionLocalTime = collectionTime!=null?LocalDateTime.ofInstant(collectionTime.toInstant(), ZoneId.systemDefault()).toLocalTime():null;
		return collectionLocalTime;
	}

	public void setCollectionTime(Date collectionTime) {
		this.collectionTime = collectionTime;
	}

	public Integer getOrderStatusId() {
		return orderStatusId;
	}

	public void setOrderStatusId(Integer orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	public Integer getGenderId() {
		return genderId;
	}

	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}

  public String getVisitType() {
    return visitType;
  }

  public void setVisitType(String visitType) {
    this.visitType = visitType;
  }
	
}
