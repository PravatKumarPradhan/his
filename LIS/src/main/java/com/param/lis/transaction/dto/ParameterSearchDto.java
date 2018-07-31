package com.param.lis.transaction.dto;

import java.util.List;
import com.param.lis.global.common.obj;

public class ParameterSearchDto {
  
  
  private Integer orgUnitId;
  private Integer orgId;
  private Integer patientId;
  private Integer testId;
  private Integer limit;
  private List<obj> parameterIds;
  
  public Integer getOrgUnitId() {
    return orgUnitId;
  }
  public void setOrgUnitId(Integer orgUnitId) {
    this.orgUnitId = orgUnitId;
  }
  public Integer getOrgId() {
    return orgId;
  }
  public void setOrgId(Integer orgId) {
    this.orgId = orgId;
  }
  public Integer getPatientId() {
    return patientId;
  }
  public void setPatientId(Integer patientId) {
    this.patientId = patientId;
  }
  public Integer getTestId() {
    return testId;
  }
  public void setTestId(Integer testId) {
    this.testId = testId;
  }
  public Integer getLimit() {
    return limit;
  }
  public void setLimit(Integer limit) {
    this.limit = limit;
  }
  public List<obj> getParameterIds() {
    return parameterIds;
  }
  public void setParameterIds(List<obj> parameterIds) {
    this.parameterIds = parameterIds;
  }
  
  
  

}
