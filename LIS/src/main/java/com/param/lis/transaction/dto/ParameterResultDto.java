package com.param.lis.transaction.dto;

import java.util.Date;

public class ParameterResultDto {

  private String result;
  private Date resultDateTime;
  private Date createdDate;

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public Date getResultDateTime() {
    return resultDateTime;
  }

  public void setResultDateTime(Date resultDateTime) {
    this.resultDateTime = resultDateTime;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }


}
