package com.param.lis.microbiology.transaction.dto;

import java.util.List;
import com.param.lis.common.dto.CommonDto;

public class MicroscopicExaminationDetailsMasterDto {

  private Integer examinationDetailsId;

  private Integer orgId;

  private Integer orgUnitId;

  private Integer organismGroupId;

  private String microOrgGroupName;

  private Integer createdBy;

  private Long createdDate;

  private Integer updatedBy;

  private Long updatedDate;

  private Character isDeleted;

  private Integer microscopicExaId;

  private List<CommonDto> listCommonDto;

  private List<MicroscopicExaminationSubDetailsMasterDto> listMicroscopicExaminationSubDetailsMaster;

  public Integer getExaminationDetailsId() {
    return examinationDetailsId;
  }

  public void setExaminationDetailsId(Integer examinationDetailsId) {
    this.examinationDetailsId = examinationDetailsId;
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
    this.isDeleted = isDeleted;
  }

  public Integer getMicroscopicExaId() {
    return microscopicExaId;
  }

  public void setMicroscopicExaId(Integer microscopicExaId) {
    this.microscopicExaId = microscopicExaId;
  }

  public List<MicroscopicExaminationSubDetailsMasterDto> getListMicroscopicExaminationSubDetailsMaster() {
    return listMicroscopicExaminationSubDetailsMaster;
  }

  public void setListMicroscopicExaminationSubDetailsMaster(
      List<MicroscopicExaminationSubDetailsMasterDto> listMicroscopicExaminationSubDetailsMaster) {
    this.listMicroscopicExaminationSubDetailsMaster = listMicroscopicExaminationSubDetailsMaster;
  }

  public Integer getOrganismGroupId() {
    return organismGroupId;
  }

  public void setOrganismGroupId(Integer organismGroupId) {
    this.organismGroupId = organismGroupId;
  }

  public String getMicroOrgGroupName() {
    return microOrgGroupName;
  }

  public void setMicroOrgGroupName(String microOrgGroupName) {
    this.microOrgGroupName = microOrgGroupName;
  }

  public List<CommonDto> getListCommonDto() {
    return listCommonDto;
  }

  public void setListCommonDto(List<CommonDto> listCommonDto) {
    this.listCommonDto = listCommonDto;
  }


}
