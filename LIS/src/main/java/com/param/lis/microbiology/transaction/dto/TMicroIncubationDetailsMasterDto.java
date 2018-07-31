package com.param.lis.microbiology.transaction.dto;

public class TMicroIncubationDetailsMasterDto {

  private Integer tIncubationDetailsId;

  private Integer tIncubationId;
  
  private Integer incubationPeriodId;

  private Integer orgId;

  private Integer orgUnitId;

  private Long incubationStartTime;

  private Long incubationDueTime;

  private Long incubationStopTime;

  private String remark;

  private Integer createdBy;

  private Long createdDate;

  private Integer updatedBy;

  private Long updatedDate;

  private Character isDeleted;
  
  private Character isIncubationStop;


  public Integer gettIncubationDetailsId() {
    return tIncubationDetailsId;
  }

  public void settIncubationDetailsId(Integer tIncubationDetailsId) {
    this.tIncubationDetailsId = tIncubationDetailsId;
  }

  public Integer gettIncubationId() {
    return tIncubationId;
  }

  public void settIncubationId(Integer tIncubationId) {
    this.tIncubationId = tIncubationId;
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

  public Long getIncubationStartTime() {
    return incubationStartTime;
  }

  public void setIncubationStartTime(Long incubationStartTime) {
    this.incubationStartTime = incubationStartTime;
  }

  public Long getIncubationDueTime() {
    return incubationDueTime;
  }

  public void setIncubationDueTime(Long incubationDueTime) {
    this.incubationDueTime = incubationDueTime;
  }

  public Long getIncubationStopTime() {
    return incubationStopTime;
  }

  public void setIncubationStopTime(Long incubationStopTime) {
    this.incubationStopTime = incubationStopTime;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
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
    this.isDeleted = (isDeleted == '\u0000') ? 'N' : isDeleted;
  }

  public Character getIsIncubationStop() {
    return isIncubationStop;
  }

  public void setIsIncubationStop(Character isIncubationStop) {
    
    this.isIncubationStop = (isIncubationStop == '\u0000') ? 'N' : isIncubationStop;
  }

  public Integer getIncubationPeriodId() {
    return incubationPeriodId;
  }

  public void setIncubationPeriodId(Integer incubationPeriodId) {
    this.incubationPeriodId = incubationPeriodId;
  }



}
