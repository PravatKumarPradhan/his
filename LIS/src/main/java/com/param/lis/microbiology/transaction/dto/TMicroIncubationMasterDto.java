package com.param.lis.microbiology.transaction.dto;

import java.util.List;

public class TMicroIncubationMasterDto {


  private Integer tIncubationId;

  private Integer orgId;

  private Integer orgUnitId;

  private Integer labSampleDtlsId;

  private Character isGrowthFound;

  private Character isCompleted;

  private Integer createdBy;

  private Long createdDate;

  private Integer updatedBy;

  private Long updatedDate;

  private Character isDeleted;

  private Long incubationDueTime;
  
  private List<TMicroIncubationDetailsMasterDto> listTMicroIncubationDetailsMaster;

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

  public Integer getLabSampleDtlsId() {
    return labSampleDtlsId;
  }

  public void setLabSampleDtlsId(Integer labSampleDtlsId) {
    this.labSampleDtlsId = labSampleDtlsId;
  }

  public Character getIsGrowthFound() {
    return isGrowthFound;
  }

  public void setIsGrowthFound(Character isGrowthFound) {
    this.isGrowthFound = (isGrowthFound == '\u0000') ? 'N' : isGrowthFound;

  }

  public Character getIsCompleted() {
    return isCompleted;
  }

  public void setIsCompleted(Character isCompleted) {
    this.isCompleted = (isCompleted == '\u0000') ? 'N' : isCompleted;
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

  public List<TMicroIncubationDetailsMasterDto> getListTMicroIncubationDetailsMaster() {
    return listTMicroIncubationDetailsMaster;
  }

  public void setListTMicroIncubationDetailsMaster(
      List<TMicroIncubationDetailsMasterDto> listTMicroIncubationDetailsMaster) {
    this.listTMicroIncubationDetailsMaster = listTMicroIncubationDetailsMaster;
  }

  public Long getIncubationDueTime() {
    return incubationDueTime;
  }

  public void setIncubationDueTime(Long incubationDueTime) {
    this.incubationDueTime = incubationDueTime;
  }



}
