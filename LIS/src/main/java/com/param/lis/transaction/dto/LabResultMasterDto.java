package com.param.lis.transaction.dto;

import java.util.Date;
import java.util.List;

public class LabResultMasterDto {
  private Integer labTestResId;

  private Integer orgId;

  private Integer orgUnitId;

  private Integer testId;

  private Integer deptId;

  private Integer patientId;

  private Integer visitTypeId;

  private Integer visitAdmId;

  private Integer orderDetailsId;

  private String sampleNo;

  private Integer labSampleDtlsId;

  private Date resultEnterDatetime;

  private Integer resultEnterBy;

  private Date resultValidatedDatetime;

  private Integer resultValidatedBy;

  private Long resultAuthorisedDatetime;

  private Integer resultAuthorisedBy;

  private Character resultAuthorisedFlag;

  private Long resultHandoverDatetime;

  private Long resultHandoverBy;

  private String suggetionNotes;

  private String footsNotes;

  private Integer sampleStatusId;

  private Integer createdBy;

  private Long createdDate;

  private Integer updatedBy;

  private Long updatedDate;

  private Integer resultLevel;

  // Dummy Fields
  private Integer reportType;

  private String deptName;

  private String sampleBarcode;

  private String labSampleNo;

  private Integer subDeptId;

  private String visitType;

  private String panelCode;

  private String uhid;

  private String patientDetails;

  private String doctorDetails;

  private String testDesc;

  private String priorityName;

  private String colorCode;

  private Integer testType;

  private Integer patientAgeDays;

  private Integer offset;

  private Integer recordPerPage;

  private Double patientAgeInYears;

  private String patientGender;

  private String printReport;

  private Character sampleRecollectFlag;

  private Integer sampleRecollectAgainstId;

  private Date orderDateTime;

  private Date sampleCollectionDatetime;

  private Integer panelId;

  private Integer genderId;
  
  private String wardCode;
  
  private String bedNumber;
  
  private String sampleType;
  
  private String testCode;

  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  public Integer getRecordPerPage() {
    return recordPerPage;
  }

  public void setRecordPerPage(Integer recordPerPage) {
    this.recordPerPage = recordPerPage;
  }

  private List<LabResultDetailsViewDto> listLabSampleResultDetailsMaster;

  public Integer getLabTestResId() {
    return labTestResId;
  }

  public void setLabTestResId(Integer labTestResId) {
    this.labTestResId = labTestResId;
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

  public Integer getPatientId() {
    return patientId;
  }

  public void setPatientId(Integer patientId) {
    this.patientId = patientId;
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

  public Integer getOrderDetailsId() {
    return orderDetailsId;
  }

  public void setOrderDetailsId(Integer orderDetailsId) {
    this.orderDetailsId = orderDetailsId;
  }

  public String getSampleNo() {
    return sampleNo;
  }

  public void setSampleNo(String sampleNo) {
    this.sampleNo = sampleNo;
  }

  public Integer getLabSampleDtlsId() {
    return labSampleDtlsId;
  }

  public void setLabSampleDtlsId(Integer labSampleDtlsId) {
    this.labSampleDtlsId = labSampleDtlsId;
  }

  public Date getResultEnterDatetime() {
    return resultEnterDatetime;
  }

  public void setResultEnterDatetime(Date resultEnterDatetime) {
    this.resultEnterDatetime = resultEnterDatetime;
  }

  public Integer getResultEnterBy() {
    return resultEnterBy;
  }

  public void setResultEnterBy(Integer resultEnterBy) {
    this.resultEnterBy = resultEnterBy;
  }

  public Date getResultValidatedDatetime() {
    return resultValidatedDatetime;
  }

  public void setResultValidatedDatetime(Date resultValidatedDatetime) {
    this.resultValidatedDatetime = resultValidatedDatetime;
  }

  public Integer getResultValidatedBy() {
    return resultValidatedBy;
  }

  public void setResultValidatedBy(Integer resultValidatedBy) {
    this.resultValidatedBy = resultValidatedBy;
  }

  public Long getResultAuthorisedDatetime() {
    return resultAuthorisedDatetime;
  }

  public void setResultAuthorisedDatetime(Long resultAuthorisedDatetime) {
    this.resultAuthorisedDatetime = resultAuthorisedDatetime;
  }

  public Integer getResultAuthorisedBy() {
    return resultAuthorisedBy;
  }

  public void setResultAuthorisedBy(Integer resultAuthorisedBy) {
    this.resultAuthorisedBy = resultAuthorisedBy;
  }

  public Character getResultAuthorisedFlag() {
    return resultAuthorisedFlag;
  }

  public void setResultAuthorisedFlag(Character resultAuthorisedFlag) {
    this.resultAuthorisedFlag = resultAuthorisedFlag;
  }

  public Long getResultHandoverDatetime() {
    return resultHandoverDatetime;
  }

  public void setResultHandoverDatetime(Long resultHandoverDatetime) {
    this.resultHandoverDatetime = resultHandoverDatetime;
  }

  public Long getResultHandoverBy() {
    return resultHandoverBy;
  }

  public void setResultHandoverBy(Long resultHandoverBy) {
    this.resultHandoverBy = resultHandoverBy;
  }

  public String getSuggetionNotes() {
    return suggetionNotes;
  }

  public void setSuggetionNotes(String suggetionNotes) {
    this.suggetionNotes = suggetionNotes;
  }

  public String getFootsNotes() {
    return footsNotes;
  }

  public void setFootsNotes(String footsNotes) {
    this.footsNotes = footsNotes;
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

  public String getLabSampleNo() {
    return labSampleNo;
  }

  public void setLabSampleNo(String labSampleNo) {
    this.labSampleNo = labSampleNo;
  }

  public String getVisitType() {
    return visitType;
  }

  public void setVisitType(String visitType) {
    this.visitType = visitType;
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

  public String getTestDesc() {
    return testDesc;
  }

  public void setTestDesc(String testDesc) {
    this.testDesc = testDesc;
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

  public Integer getSubDeptId() {
    return subDeptId;
  }

  public void setSubDeptId(Integer subDeptId) {
    this.subDeptId = subDeptId;
  }

  public String getSampleBarcode() {
    return sampleBarcode;
  }

  public void setSampleBarcode(String sampleBarcode) {
    this.sampleBarcode = sampleBarcode;
  }

  public Integer getTestType() {
    return testType;
  }

  public void setTestType(Integer testType) {
    this.testType = testType;
  }

  public Integer getPatientAgeDays() {
    return patientAgeDays;
  }

  public void setPatientAgeDays(Integer patientAgeDays) {
    this.patientAgeDays = patientAgeDays;
  }

  public List<LabResultDetailsViewDto> getListLabSampleResultDetailsMaster() {
    return listLabSampleResultDetailsMaster;
  }

  public void setListLabSampleResultDetailsMaster(
      List<LabResultDetailsViewDto> listLabSampleResultDetailsMaster) {
    this.listLabSampleResultDetailsMaster = listLabSampleResultDetailsMaster;
  }

  public String getDeptName() {
    return deptName;
  }

  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }

  public Integer getReportType() {
    return reportType;
  }

  public void setReportType(Integer reportType) {
    this.reportType = reportType;
  }

  public Double getPatientAgeInYears() {
    return patientAgeInYears;
  }

  public void setPatientAgeInYears(Double patientAgeInYears) {
    this.patientAgeInYears = patientAgeInYears;
  }

  public String getPatientGender() {
    return patientGender;
  }

  public void setPatientGender(String patientGender) {
    this.patientGender = patientGender;
  }

  public String getPrintReport() {
    return printReport;
  }

  public void setPrintReport(String printReport) {
    this.printReport = printReport;
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

  public String getPanelCode() {
    return panelCode;
  }

  public void setPanelCode(String panelCode) {
    this.panelCode = panelCode;
  }

  public Date getOrderDateTime() {
    return orderDateTime;
  }

  public void setOrderDateTime(Date orderDateTime) {
    this.orderDateTime = orderDateTime;
  }

  public Date getSampleCollectionDatetime() {
    return sampleCollectionDatetime;
  }

  public void setSampleCollectionDatetime(Date sampleCollectionDatetime) {
    this.sampleCollectionDatetime = sampleCollectionDatetime;
  }

  public Integer getPanelId() {
    return panelId;
  }

  public void setPanelId(Integer panelId) {
    this.panelId = panelId;
  }

  public Integer getGenderId() {
    return genderId;
  }

  public void setGenderId(Integer genderId) {
    this.genderId = genderId;
  }

  public Integer getResultLevel() {
    return resultLevel;
  }

  public void setResultLevel(Integer resultLevel) {
    this.resultLevel = resultLevel;
  }

  public Integer getSampleStatusId() {
    return sampleStatusId;
  }

  public void setSampleStatusId(Integer sampleStatusId) {
    this.sampleStatusId = sampleStatusId;
  }

  public String getWardCode() {
    return wardCode;
  }

  public void setWardCode(String wardCode) {
    this.wardCode = wardCode;
  }

  public String getBedNumber() {
    return bedNumber;
  }

  public void setBedNumber(String bedNumber) {
    this.bedNumber = bedNumber;
  }

  public String getSampleType() {
    return sampleType;
  }

  public void setSampleType(String sampleType) {
    this.sampleType = sampleType;
  }

  public String getTestCode() {
    return testCode;
  }

  public void setTestCode(String testCode) {
    this.testCode = testCode;
  }
  
  

}
