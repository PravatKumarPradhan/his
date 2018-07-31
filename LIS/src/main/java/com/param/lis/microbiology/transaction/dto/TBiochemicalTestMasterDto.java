package com.param.lis.microbiology.transaction.dto;

import java.util.List;
import com.param.entity.lis.micro.TBiochemicalTestDetailsMaster;

public class TBiochemicalTestMasterDto {

  private Integer tBiochemicalTestId;

  private Integer labSampleDtlsId;

  private Integer orgId;

  private Integer orgUnitId;

  private Integer createdBy;

  private Long createdDate;

  private Integer updatedBy;

  private Long updatedDate;

  private Character isDeleted;

  private Character isCompleted;

  private List<TBiochemicalTestDetailsMasterDto> listTBiochemicalTestDetailsMaster;

  public Integer gettBiochemicalTestId() {
    return tBiochemicalTestId;
  }

  public void settBiochemicalTestId(Integer tBiochemicalTestId) {
    this.tBiochemicalTestId = tBiochemicalTestId;
  }

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

  public List<TBiochemicalTestDetailsMasterDto> getListTBiochemicalTestDetailsMaster() {
    return listTBiochemicalTestDetailsMaster;
  }

  public void setListTBiochemicalTestDetailsMaster(
      List<TBiochemicalTestDetailsMasterDto> listTBiochemicalTestDetailsMaster) {
    this.listTBiochemicalTestDetailsMaster = listTBiochemicalTestDetailsMaster;
  }

  public Character getIsCompleted() {
    return isCompleted;
  }

  public void setIsCompleted(Character isCompleted) {
    this.isCompleted = (isCompleted == '\u0000') ? 'N' : isCompleted;
  }



}
