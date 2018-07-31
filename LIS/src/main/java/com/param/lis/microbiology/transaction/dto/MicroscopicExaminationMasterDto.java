package com.param.lis.microbiology.transaction.dto;

import java.util.List;
import com.param.lis.common.dto.CommonDto;

public class MicroscopicExaminationMasterDto {

  private Integer microscopicExaId;

  private Integer orgId;

  private Integer orgUnitId;

  private Integer stainingId;

  private String stainingName;

  private Integer organismGroupId;

  private Integer labSampleDtlsId;

  private Integer createdBy;

  private Long createdDate;

  private Integer updatedBy;

  private Long updatedDate;

  private Character isDeleted;
  
  private Character isCompleted;

  private List<MicroscopicExaminationDetailsMasterDto> listMicroscopicExaminationDetailsMasters;

  public Integer getMicroscopicExaId() {
    return microscopicExaId;
  }

  public void setMicroscopicExaId(Integer microscopicExaId) {
    this.microscopicExaId = microscopicExaId;
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

  public Integer getStainingId() {
    return stainingId;
  }

  public void setStainingId(Integer stainingId) {
    this.stainingId = stainingId;
  }

  public Integer getLabSampleDtlsId() {
    return labSampleDtlsId;
  }

  public void setLabSampleDtlsId(Integer labSampleDtlsId) {
    this.labSampleDtlsId = labSampleDtlsId;
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

  public List<MicroscopicExaminationDetailsMasterDto> getListMicroscopicExaminationDetailsMasters() {
    return listMicroscopicExaminationDetailsMasters;
  }

  public void setListMicroscopicExaminationDetailsMasters(
      List<MicroscopicExaminationDetailsMasterDto> listMicroscopicExaminationDetailsMasters) {
    this.listMicroscopicExaminationDetailsMasters = listMicroscopicExaminationDetailsMasters;
  }

  public String getStainingName() {
    return stainingName;
  }

  public void setStainingName(String stainingName) {
    this.stainingName = stainingName;
  }

  public Integer getOrganismGroupId() {
    return organismGroupId;
  }

  public void setOrganismGroupId(Integer organismGroupId) {
    this.organismGroupId = organismGroupId;
  }

  public Character getIsCompleted() {
    return isCompleted;
  }

  public void setIsCompleted(Character isCompleted) {
    this.isCompleted = (isCompleted == '\u0000') ? 'N' : isCompleted;
  }



}
