package com.param.lis.transaction.dto;

import java.util.Date;
import java.util.List;

public class RetestRecollectDto {

  private Integer testId;
  private Integer testType;
  private String labSampleNo;
  private Date createdDate;
  private List<ParameterHistoryDto> ListParameterHistoryDto;

  public Integer getTestId() {
    return testId;
  }

  public void setTestId(Integer testId) {
    this.testId = testId;
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

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public List<ParameterHistoryDto> getListParameterHistoryDto() {
    return ListParameterHistoryDto;
  }

  public void setListParameterHistoryDto(List<ParameterHistoryDto> listParameterHistoryDto) {
    ListParameterHistoryDto = listParameterHistoryDto;
  }



}
