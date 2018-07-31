package com.param.lis.transaction.dto;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import com.param.lis.microbiology.transaction.dto.TMicroSampleMediaDto;


public class LabSampleDetailsMasterDto {
  private Integer labSampleDtlsId;

  private Integer orgId;

  private Integer orgUnitId;

  private Integer testId;

  private Integer deptId;

  private Integer subDeptId;

  private String deptName;

  private Integer labSampleId;

  private Integer sampleBarcode;

  private Integer sampleId;

  private BigInteger sampleNo;

  private Integer orderDetailsId;

  private Integer orderId;

  private Integer orderQty;

  private Integer serviceId;

  private String testCode;

  private String testDesc;

  private String sampleName;

  private Double sampleVolume;

  private String unitName;

  private Integer profileId;

  private String panelCode;

  private Integer packageId;

  private Character isCentrifugationReq;

  private Character isAlliquoteReq;

  private Integer sampleTypeId;

  private Integer containerId;

  private String containerName;

  private Integer sampleReqCount;

  private Integer samplePendingCount;

  private Date sampleGenDatetime;

  private Integer sampleGenBy;

  private Character currStatus;

  private Character isOutsourced;

  private Integer outsourcedLabId;

  private Date sampleSendtocrDatetime;

  private Integer sampleSendtocrBy;

  private Integer sampleStatusId;

  private Long sampleCollectionDatetime;

  private Integer sampleCollectionBy;

  private Long sampleCentrifugationDatetime;

  private Integer sampleCentrifugationBy;

  private Date sampleAcceptDatetime;

  private Integer sampleAcceptBy;

  private Date sampleRejectDatetime;

  private Integer sampleRejectBy;

  private Integer sampleRejectReasonId;

  private String sampleRejectReason;

  private Character sampleRecollectFlag;

  private Integer sampleRecollectAgainstId;

  private Integer createdBy;

  private Date createdDate;

  private Integer updatedBy;

  private Date updatedDate;

  private Integer patientId;

  private Integer priorityId;

  private Integer doctorId;

  private Integer sampWrkStatusId;

  private Date collectionTime;

  private Long sampleTestTime;

  private Long patientVisitAge;

  private Integer genderId;

  private String visitType;

  private List<TMicroSampleMediaDto> listTMicroSampleMediaDto;

  public Integer getLabSampleDtlsId() {
    return labSampleDtlsId;
  }

  public void setLabSampleDtlsId(Integer labSampleDtlsId) {
    this.labSampleDtlsId = labSampleDtlsId;
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

  public Integer getSubDeptId() {
    return subDeptId;
  }

  public void setSubDeptId(Integer subDeptId) {
    this.subDeptId = subDeptId;
  }

  public Integer getSampleBarcode() {
    return sampleBarcode;
  }

  public void setSampleBarcode(Integer sampleBarcode) {
    this.sampleBarcode = sampleBarcode;
  }

  public BigInteger getSampleNo() {
    return sampleNo;
  }

  public void setSampleNo(BigInteger sampleNo) {
    this.sampleNo = sampleNo;
  }

  public Integer getOrderDetailsId() {
    return orderDetailsId;
  }

  public void setOrderDetailsId(Integer orderDetailsId) {
    this.orderDetailsId = orderDetailsId;
  }

  public Integer getOrderId() {
    return orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  public Integer getProfileId() {
    return profileId;
  }

  public void setProfileId(Integer profileId) {
    this.profileId = profileId;
  }

  public Integer getPackageId() {
    return packageId;
  }

  public void setPackageId(Integer packageId) {
    this.packageId = packageId;
  }

  public Character getIsCentrifugationReq() {
    return isCentrifugationReq;
  }

  public void setIsCentrifugationReq(Character isCentrifugationReq) {
    this.isCentrifugationReq = isCentrifugationReq;
  }

  public Character getIsAlliquoteReq() {
    return isAlliquoteReq;
  }

  public void setIsAlliquoteReq(Character isAlliquoteReq) {
    this.isAlliquoteReq = isAlliquoteReq;
  }

  public Integer getSampleTypeId() {
    return sampleTypeId;
  }

  public void setSampleTypeId(Integer sampleTypeId) {
    this.sampleTypeId = sampleTypeId;
  }

  public Integer getContainerId() {
    return containerId;
  }

  public void setContainerId(Integer containerId) {
    this.containerId = containerId;
  }


  public Integer getSampleReqCount() {
    return sampleReqCount;
  }

  public void setSampleReqCount(Integer sampleReqCount) {
    this.sampleReqCount = sampleReqCount;
  }

  public Integer getSamplePendingCount() {
    return samplePendingCount;
  }

  public void setSamplePendingCount(Integer samplePendingCount) {
    this.samplePendingCount = samplePendingCount;
  }

  public Date getSampleGenDatetime() {
    return sampleGenDatetime;
  }

  public void setSampleGenDatetime(Date sampleGenDatetime) {
    this.sampleGenDatetime = sampleGenDatetime;
  }

  public Integer getSampleGenBy() {
    return sampleGenBy;
  }

  public void setSampleGenBy(Integer sampleGenBy) {
    this.sampleGenBy = sampleGenBy;
  }

  public Character getCurrStatus() {
    return currStatus;
  }

  public void setCurrStatus(Character currStatus) {
    this.currStatus = currStatus;
  }

  public Character getIsOutsourced() {
    return isOutsourced;
  }

  public void setIsOutsourced(Character isOutsourced) {
    this.isOutsourced = isOutsourced;
  }

  public Integer getOutsourcedLabId() {
    return outsourcedLabId;
  }

  public void setOutsourcedLabId(Integer outsourcedLabId) {
    this.outsourcedLabId = outsourcedLabId;
  }

  public Date getSampleSendtocrDatetime() {
    return sampleSendtocrDatetime;
  }

  public void setSampleSendtocrDatetime(Date sampleSendtocrDatetime) {
    this.sampleSendtocrDatetime = sampleSendtocrDatetime;
  }

  public Integer getSampleSendtocrBy() {
    return sampleSendtocrBy;
  }

  public void setSampleSendtocrBy(Integer sampleSendtocrBy) {
    this.sampleSendtocrBy = sampleSendtocrBy;
  }

  public Long getSampleCollectionDatetime() {
    return sampleCollectionDatetime;
  }

  public void setSampleCollectionDatetime(Long sampleCollectionDatetime) {
    this.sampleCollectionDatetime = sampleCollectionDatetime;
  }

  public Integer getSampleCollectionBy() {
    return sampleCollectionBy;
  }

  public void setSampleCollectionBy(Integer sampleCollectionBy) {
    this.sampleCollectionBy = sampleCollectionBy;
  }

  public Long getSampleCentrifugationDatetime() {
    return sampleCentrifugationDatetime;
  }

  public void setSampleCentrifugationDatetime(Long sampleCentrifugationDatetime) {
    this.sampleCentrifugationDatetime = sampleCentrifugationDatetime;
  }

  public Integer getSampleCentrifugationBy() {
    return sampleCentrifugationBy;
  }

  public void setSampleCentrifugationBy(Integer sampleCentrifugationBy) {
    this.sampleCentrifugationBy = sampleCentrifugationBy;
  }

  public Date getSampleAcceptDatetime() {
    return sampleAcceptDatetime;
  }

  public void setSampleAcceptDatetime(Date sampleAcceptDatetime) {
    this.sampleAcceptDatetime = sampleAcceptDatetime;
  }

  public Integer getSampleAcceptBy() {
    return sampleAcceptBy;
  }

  public void setSampleAcceptBy(Integer sampleAcceptBy) {
    this.sampleAcceptBy = sampleAcceptBy;
  }

  public Date getSampleRejectDatetime() {
    return sampleRejectDatetime;
  }

  public void setSampleRejectDatetime(Date sampleRejectDatetime) {
    this.sampleRejectDatetime = sampleRejectDatetime;
  }

  public Integer getSampleRejectBy() {
    return sampleRejectBy;
  }

  public void setSampleRejectBy(Integer sampleRejectBy) {
    this.sampleRejectBy = sampleRejectBy;
  }

  public Integer getSampleRejectReasonId() {
    return sampleRejectReasonId;
  }

  public void setSampleRejectReasonId(Integer sampleRejectReasonId) {
    this.sampleRejectReasonId = sampleRejectReasonId;
  }

  public String getSampleRejectReason() {
    return sampleRejectReason;
  }

  public void setSampleRejectReason(String sampleRejectReason) {
    this.sampleRejectReason = sampleRejectReason;
  }

  public Character getSampleRecollectFlag() {
    return sampleRecollectFlag;
  }

  public void setSampleRecollectFlag(Character sampleRecollectFlag) {
    this.sampleRecollectFlag = sampleRecollectFlag;
  }

  public Integer getSampleRecollectAgainstId() {
    return sampleRecollectAgainstId;
  }

  public void setSampleRecollectAgainstId(Integer sampleRecollectAgainstId) {
    this.sampleRecollectAgainstId = sampleRecollectAgainstId;
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

  public String getDeptName() {
    return deptName;
  }

  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }

  public Integer getLabSampleId() {
    return labSampleId;
  }

  public void setLabSampleId(Integer labSampleId) {
    this.labSampleId = labSampleId;
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

  public String getTestCode() {
    return testCode;
  }

  public void setTestCode(String testCode) {
    this.testCode = testCode;
  }

  public String getTestDesc() {
    return testDesc;
  }

  public void setTestDesc(String testDesc) {
    this.testDesc = testDesc;
  }

  public String getSampleName() {
    return sampleName;
  }

  public void setSampleName(String sampleName) {
    this.sampleName = sampleName;
  }

  public Double getSampleVolume() {
    return sampleVolume;
  }

  public void setSampleVolume(Double sampleVolume) {
    this.sampleVolume = sampleVolume;
  }

  public String getUnitName() {
    return unitName;
  }

  public void setUnitName(String unitName) {
    this.unitName = unitName;
  }

  public String getContainerName() {
    return containerName;
  }

  public void setContainerName(String containerName) {
    this.containerName = containerName;
  }

  public Integer getSampleId() {
    return sampleId;
  }

  public void setSampleId(Integer sampleId) {
    this.sampleId = sampleId;
  }

  public Integer getSampleStatusId() {
    return sampleStatusId;
  }

  public void setSampleStatusId(Integer sampleStatusId) {
    this.sampleStatusId = sampleStatusId;
  }

  public Integer getPatientId() {
    return patientId;
  }

  public void setPatientId(Integer patientId) {
    this.patientId = patientId;
  }

  public Integer getPriorityId() {
    return priorityId;
  }

  public void setPriorityId(Integer priorityId) {
    this.priorityId = priorityId;
  }

  public Integer getDoctorId() {
    return doctorId;
  }

  public void setDoctorId(Integer doctorId) {
    this.doctorId = doctorId;
  }

  public Integer getSampWrkStatusId() {
    return sampWrkStatusId;
  }

  public void setSampWrkStatusId(Integer sampWrkStatusId) {
    this.sampWrkStatusId = sampWrkStatusId;
  }

  public Date getCollectionTime() {
    return collectionTime;
  }

  public void setCollectionTime(Date collectionTime) {
    this.collectionTime = collectionTime;
  }

  public Long getSampleTestTime() {
    return sampleTestTime;
  }

  public void setSampleTestTime(Long sampleTestTime) {
    this.sampleTestTime = sampleTestTime;
  }

  public Long getPatientVisitAge() {
    return patientVisitAge;
  }

  public void setPatientVisitAge(Long patientVisitAge) {
    this.patientVisitAge = patientVisitAge;
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

  public String getPanelCode() {
    return panelCode;
  }

  public void setPanelCode(String panelCode) {
    this.panelCode = panelCode;
  }

  public List<TMicroSampleMediaDto> getListTMicroSampleMediaDto() {
    return listTMicroSampleMediaDto;
  }

  public void setListTMicroSampleMediaDto(List<TMicroSampleMediaDto> listTMicroSampleMediaDto) {
    this.listTMicroSampleMediaDto = listTMicroSampleMediaDto;
  }

}
