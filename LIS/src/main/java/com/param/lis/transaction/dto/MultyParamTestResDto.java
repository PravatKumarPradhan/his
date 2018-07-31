package com.param.lis.transaction.dto;

import java.util.Date;
import java.util.List;

public class MultyParamTestResDto {
  private Integer testId;
  private Integer testType;
  private String testCode;
  private String testDesc;
  private String techniqueName;
  private String labSampleNo;
  private Date SampleCollectionTime;
  private Date SampleTestTime;
  private Date TestReleaseTime;

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

  public String getTestDesc() {
    return testDesc;
  }

  public void setTestDesc(String testDesc) {
    this.testDesc = testDesc;
  }

  public String getTechniqueName() {
    return techniqueName;
  }

  public void setTechniqueName(String techniqueName) {
    this.techniqueName = techniqueName;
  }

  public List<MultyParamTestHeaderDto> getListMultyParamTestHeaderDto() {
    return listMultyParamTestHeaderDto;
  }

  public void setListMultyParamTestHeaderDto(
      List<MultyParamTestHeaderDto> listMultyParamTestHeaderDto) {
    this.listMultyParamTestHeaderDto = listMultyParamTestHeaderDto;
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
  

}
