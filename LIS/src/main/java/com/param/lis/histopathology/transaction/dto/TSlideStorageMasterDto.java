package com.param.lis.histopathology.transaction.dto;

import java.util.Calendar;
import java.util.Date;
import com.param.lis.global.common.CommonDateUtils;

public class TSlideStorageMasterDto {
  
  private Integer tSlideStorageId;

  private Integer rackId;
  
  private Integer shelfId;
  
  private Integer tSlideId;
  
  private String remark;

  private Character isSlideAccepted;
  
  private Integer orgId;

  private Integer orgUnitId;
  
  private Integer createdBy;
  
  private Long createdDate;

  private Integer updatedBy;

  private Long updatedDate;
  
  private Character isDeleted;
  
  
  /**Dummy fields*/
  
  private Integer specimanTypeId;
  private Integer specimanId;
  private String specimanName;
  private String specimanType;
  private String uhid;
  private String genderName;
  private String patientDetails;
  private String doctorDetails;
  private Date dob;
  private Integer age;
  private String testDesc;
  private Integer visitTypeId;
  private String histopathlogyNumber;
  private String subSpecimanBarcode;
  private String grossNo;
  private String blockNo;
  private String slideNo;
  private String rackCode;
  private String shelfCode;
  private String visitType;
  
  public Integer gettSlideStorageId() {
    return tSlideStorageId;
  }

  public void settSlideStorageId(Integer tSlideStorageId) {
    this.tSlideStorageId = tSlideStorageId;
  }

  public Integer getRackId() {
    return rackId;
  }

  public void setRackId(Integer rackId) {
    this.rackId = rackId;
  }

  public Integer getShelfId() {
    return shelfId;
  }

  public void setShelfId(Integer shelfId) {
    this.shelfId = shelfId;
  }

  public Integer gettSlideId() {
    return tSlideId;
  }

  public void settSlideId(Integer tSlideId) {
    this.tSlideId = tSlideId;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Character getIsSlideAccepted() {
    return isSlideAccepted;
  }

  public void setIsSlideAccepted(Character isSlideAccepted) {
    this.isSlideAccepted = isSlideAccepted;
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

  public Integer getSpecimanTypeId() {
    return specimanTypeId;
  }

  public void setSpecimanTypeId(Integer specimanTypeId) {
    this.specimanTypeId = specimanTypeId;
  }

  public Integer getSpecimanId() {
    return specimanId;
  }

  public void setSpecimanId(Integer specimanId) {
    this.specimanId = specimanId;
  }

  public String getSpecimanName() {
    return specimanName;
  }

  public void setSpecimanName(String specimanName) {
    this.specimanName = specimanName;
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

  public String getTestDesc() {
    return testDesc;
  }

  public void setTestDesc(String testDesc) {
    this.testDesc = testDesc;
  }

  public Integer getVisitTypeId() {
    return visitTypeId;
  }

  public void setVisitTypeId(Integer visitTypeId) {
    this.visitTypeId = visitTypeId;
  }

  public String getHistopathlogyNumber() {
    return histopathlogyNumber;
  }

  public void setHistopathlogyNumber(String histopathlogyNumber) {
    this.histopathlogyNumber = histopathlogyNumber;
  }

  public String getSubSpecimanBarcode() {
    return subSpecimanBarcode;
  }

  public void setSubSpecimanBarcode(String subSpecimanBarcode) {
    this.subSpecimanBarcode = subSpecimanBarcode;
  }

  public String getGrossNo() {
    return grossNo;
  }

  public void setGrossNo(String grossNo) {
    this.grossNo = grossNo;
  }

  public String getSlideNo() {
    return slideNo;
  }

  public void setSlideNo(String slideNo) {
    this.slideNo = slideNo;
  }

  public String getRackCode() {
    return rackCode;
  }

  public void setRackCode(String rackCode) {
    this.rackCode = rackCode;
  }

  public String getShelfCode() {
    return shelfCode;
  }

  public void setShelfCode(String shelfCode) {
    this.shelfCode = shelfCode;
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

  public String getBlockNo() {
    return blockNo;
  }

  public void setBlockNo(String blockNo) {
    this.blockNo = blockNo;
  }

}
