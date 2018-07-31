package com.param.lis.histopathology.transaction.dto;

import java.util.Calendar;
import java.util.Date;
import com.param.lis.global.common.CommonDateUtils;

public class TFrozenSectionReqMasterDto {

  private Integer requestId;
  
  private Integer orgId;

  private Integer orgUnitId;

  private Integer patientId;
  
  private Integer doctorId;
  
  private Integer surgeryId;
  
  private Integer visitTypeId;

  private Integer specimenTypeId;
  
  private Long surgeryStartTime;
  
  private String reqRemark;
  
  private String ackRemark;
  
  private Integer admissionId;
  
  private Integer createdBy;
  
  private Long createdDate;

  private Integer updatedBy;

  private Long updatedDate;
  
  private Character isAccepted;
  
  private Character isDeleted;
  
  private String visitType;
  private String specimanType;
  private String uhid;
  private String genderName;
  private Date dob;
  private Integer age;
  private String doctorDetails;
  private String patientDetails;

  public Integer getRequestId() {
    return requestId;
  }

  public void setRequestId(Integer requestId) {
    this.requestId = requestId;
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

  public Integer getPatientId() {
    return patientId;
  }

  public void setPatientId(Integer patientId) {
    this.patientId = patientId;
  }

  public Integer getDoctorId() {
    return doctorId;
  }

  public void setDoctorId(Integer doctorId) {
    this.doctorId = doctorId;
  }

  public Integer getSurgeryId() {
    return surgeryId;
  }

  public void setSurgeryId(Integer surgeryId) {
    this.surgeryId = surgeryId;
  }

  public Integer getVisitTypeId() {
    return visitTypeId;
  }

  public void setVisitTypeId(Integer visitTypeId) {
    this.visitTypeId = visitTypeId;
  }

  public Integer getSpecimenTypeId() {
    return specimenTypeId;
  }

  public void setSpecimenTypeId(Integer specimenTypeId) {
    this.specimenTypeId = specimenTypeId;
  }

  public Long getSurgeryStartTime() {
    return surgeryStartTime;
  }

  public void setSurgeryStartTime(Long surgeryStartTime) {
    this.surgeryStartTime = surgeryStartTime;
  }

  public String getReqRemark() {
    return reqRemark;
  }

  public void setReqRemark(String reqRemark) {
    this.reqRemark = reqRemark;
  }

  public String getAckRemark() {
    return ackRemark;
  }

  public void setAckRemark(String ackRemark) {
    this.ackRemark = ackRemark;
  }

  public Integer getAdmissionId() {
    return admissionId;
  }

  public void setAdmissionId(Integer admissionId) {
    this.admissionId = admissionId;
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

  public Character getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(Character isDeleted) {
    this.isDeleted = isDeleted;
  }

  public String getVisitType() {
    return visitType;
  }

  public void setVisitType(String visitType) {
    this.visitType = visitType;
  }

  public String getSpecimanType() {
    return specimanType;
  }

  public void setSpecimanType(String specimanType) {
    this.specimanType = specimanType;
  }

  public String getUhid() {
    return uhid;
  }

  public void setUhid(String uhid) {
    this.uhid = uhid;
  }

  public String getGenderName() {
    return genderName;
  }

  public void setGenderName(String genderName) {
    this.genderName = genderName;
  }

  public String getDoctorDetails() {
    return doctorDetails;
  }

  public void setDoctorDetails(String doctorDetails) {
    this.doctorDetails = doctorDetails;
  }

  public String getPatientDetails() {
    return patientDetails;
  }

  public void setPatientDetails(String patientDetails) {
    this.patientDetails = patientDetails;
  }

  public Date getDob() {
    return dob;
  }

  public void setDob(Date dob) {
    this.dob = dob;
  }

  public Integer getAge() {
    return CommonDateUtils.getAgeFromBirthDate(dob);
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Character getIsAccepted() {
    return isAccepted;
  }

  public void setIsAccepted(Character isAccepted) {
    this.isAccepted = isAccepted;
  }
  
  
}
