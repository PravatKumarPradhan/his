package com.param.lis.histopathology.transaction.dto;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.param.lis.global.common.CommonDateUtils;

public class TRestainingRequestMasterDto {

  private Integer tRestainingReqId;

  private Integer orgId;

  private Integer orgUnitId;

  private Integer labSampleDtlsId;

  private Integer tSubSpecimanId;

  private Integer createdBy;

  private Long createdDate;

  private Integer updatedBy;

  private Long updatedDate;

  private Character isDeleted;
  
  private String histopathlogyNumber;
  
  private String subSpcimanNo;
  
  private String subSpecimanBarcode;
  
  private Integer specimanId;

  private String specimanName;

  private String uhid;
  
  private String genderName;
  
  private String patientDetails;
  
  private String doctorDetails;
  
  private Date dob;
  
  private Integer age;
  
  private String visitType;
  
  private String specimanType;
  
  private Integer specimanTypeId;

  private List<TRestainingRequestDetailsMasterDto> listTRestainingRequestDetailsMaster;

  public Integer gettRestainingReqId() {
    return tRestainingReqId;
  }

  public void settRestainingReqId(Integer tRestainingReqId) {
    this.tRestainingReqId = tRestainingReqId;
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

  public Integer getLabSampleDtlsId() {
    return labSampleDtlsId;
  }

  public void setLabSampleDtlsId(Integer labSampleDtlsId) {
    this.labSampleDtlsId = labSampleDtlsId;
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

  public List<TRestainingRequestDetailsMasterDto> getListTRestainingRequestDetailsMaster() {
    return listTRestainingRequestDetailsMaster;
  }

  public void setListTRestainingRequestDetailsMaster(
      List<TRestainingRequestDetailsMasterDto> listTRestainingRequestDetailsMaster) {
    this.listTRestainingRequestDetailsMaster = listTRestainingRequestDetailsMaster;
  }

  public Integer gettSubSpecimanId() {
    return tSubSpecimanId;
  }

  public void settSubSpecimanId(Integer tSubSpecimanId) {
    this.tSubSpecimanId = tSubSpecimanId;
  }

  public String getHistopathlogyNumber() {
    return histopathlogyNumber;
  }

  public void setHistopathlogyNumber(String histopathlogyNumber) {
    this.histopathlogyNumber = histopathlogyNumber;
  }

  public String getSubSpcimanNo() {
    return subSpcimanNo;
  }

  public void setSubSpcimanNo(String subSpcimanNo) {
    this.subSpcimanNo = subSpcimanNo;
  }

  public String getSubSpecimanBarcode() {
    return subSpecimanBarcode;
  }

  public void setSubSpecimanBarcode(String subSpecimanBarcode) {
    this.subSpecimanBarcode = subSpecimanBarcode;
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

  public Integer getSpecimanTypeId() {
    return specimanTypeId;
  }

  public void setSpecimanTypeId(Integer specimanTypeId) {
    this.specimanTypeId = specimanTypeId;
  }

}
