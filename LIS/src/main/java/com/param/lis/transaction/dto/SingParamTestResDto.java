package com.param.lis.transaction.dto;

import java.util.Date;
import java.util.List;

public class SingParamTestResDto {

  private Integer labTestResId;
  private Integer testId;
  private Integer testType;
  private String testCode;
  private String testDesc;
  private String techniqueName;
  private String panelAlies;
  private String panelCode;
  private Date SampleCollectionTime;
  private Date SampleTestTime;
  private Date TestReleaseTime;
  private String labSampleNo;
  private String isSelected;
  private String footsNotes;
  private Date resultEnterDatetime;
  private Integer resultEnterBy;
  private String resultEnterByUser;
  private Date resultValidatedDatetime;
  private Integer resultValidatedBy;
  private String resultValidatedByUser;
  private Date resultAuthorisedDatetime;
  private Integer resultAuthorisedBy;
  private String resultAuthorisedByUser;
  private Character resultAuthorisedFlag;
  private Date resultHandoverDatetime;
  private Date createdDate;
  private LabResultDetailsViewDto labResultDetailsViewDto;
  private List<MultyParamTestHeaderDto> listMultyParamTestHeaderDto;

  public Integer getTestId() {
    return testId;
  }

  public void setTestId(Integer testId) {
    this.testId = testId;
  }

  public String getTestCode() {
    return testCode;
  }

  public void setTestCode(String testCode) {
    this.testCode = testCode;
  }

  public String getTechniqueName() {
    return techniqueName;
  }

  public void setTechniqueName(String techniqueName) {
    this.techniqueName = techniqueName;
  }

  public String getTestDesc() {
    return testDesc;
  }

  public void setTestDesc(String testDesc) {
    this.testDesc = testDesc;
  }

  public LabResultDetailsViewDto getLabResultDetailsViewDto() {
    return labResultDetailsViewDto;
  }

  public void setLabResultDetailsViewDto(LabResultDetailsViewDto labResultDetailsViewDto) {
    this.labResultDetailsViewDto = labResultDetailsViewDto;
  }

  public Date getSampleCollectionTime() {
    return SampleCollectionTime;
  }

  public void setSampleCollectionTime(Date sampleCollectionTime) {
    SampleCollectionTime = sampleCollectionTime;
  }

  public Date getSampleTestTime() {
    return SampleTestTime;
  }

  public void setSampleTestTime(Date sampleTestTime) {
    SampleTestTime = sampleTestTime;
  }

  public Date getTestReleaseTime() {
    return TestReleaseTime;
  }

  public void setTestReleaseTime(Date testReleaseTime) {
    TestReleaseTime = testReleaseTime;
  }

  public Integer getTestType() {
    return testType;
  }

  public void setTestType(Integer testType) {
    this.testType = testType;
  }

  public String getLabSampleNo() {
    return labSampleNo;
  }

  public void setLabSampleNo(String labSampleNo) {
    this.labSampleNo = labSampleNo;
  }

  public List<MultyParamTestHeaderDto> getListMultyParamTestHeaderDto() {
    return listMultyParamTestHeaderDto;
  }

  public void setListMultyParamTestHeaderDto(
      List<MultyParamTestHeaderDto> listMultyParamTestHeaderDto) {
    this.listMultyParamTestHeaderDto = listMultyParamTestHeaderDto;
  }

  public String getPanelAlies() {
    return panelAlies;
  }

  public void setPanelAlies(String panelAlies) {
    this.panelAlies = panelAlies;
  }

  public String getPanelCode() {
    return panelCode;
  }

  public void setPanelCode(String panelCode) {
    this.panelCode = panelCode;
  }

  public String getIsSelected() {
    return isSelected;
  }

  public void setIsSelected(String isSelected) {
    this.isSelected = isSelected;
  }

  public String getFootsNotes() {
    return footsNotes;
  }

  public void setFootsNotes(String footsNotes) {
    this.footsNotes = footsNotes;
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

  public String getResultEnterByUser() {
    return resultEnterByUser;
  }

  public void setResultEnterByUser(String resultEnterByUser) {
    this.resultEnterByUser = resultEnterByUser;
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

  public String getResultValidatedByUser() {
    return resultValidatedByUser;
  }

  public void setResultValidatedByUser(String resultValidatedByUser) {
    this.resultValidatedByUser = resultValidatedByUser;
  }

  public Date getResultAuthorisedDatetime() {
    return resultAuthorisedDatetime;
  }

  public void setResultAuthorisedDatetime(Date resultAuthorisedDatetime) {
    this.resultAuthorisedDatetime = resultAuthorisedDatetime;
  }

  public Integer getResultAuthorisedBy() {
    return resultAuthorisedBy;
  }

  public void setResultAuthorisedBy(Integer resultAuthorisedBy) {
    this.resultAuthorisedBy = resultAuthorisedBy;
  }

  public String getResultAuthorisedByUser() {
    return resultAuthorisedByUser;
  }

  public void setResultAuthorisedByUser(String resultAuthorisedByUser) {
    this.resultAuthorisedByUser = resultAuthorisedByUser;
  }

  public Character getResultAuthorisedFlag() {
    return resultAuthorisedFlag;
  }

  public void setResultAuthorisedFlag(Character resultAuthorisedFlag) {
    this.resultAuthorisedFlag = resultAuthorisedFlag;
  }

  public Date getResultHandoverDatetime() {
    return resultHandoverDatetime;
  }

  public void setResultHandoverDatetime(Date resultHandoverDatetime) {
    this.resultHandoverDatetime = resultHandoverDatetime;
  }

  public Integer getLabTestResId() {
    return labTestResId;
  }

  public void setLabTestResId(Integer labTestResId) {
    this.labTestResId = labTestResId;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }



}
