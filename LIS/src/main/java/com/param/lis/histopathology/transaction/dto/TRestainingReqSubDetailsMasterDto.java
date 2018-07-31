package com.param.lis.histopathology.transaction.dto;


public class TRestainingReqSubDetailsMasterDto {

  private Integer tRestainingSubDetailId;

  private Integer orgId;

  private Integer orgUnitId;

  private Integer tRestainingDetailId;

  private Integer tSlideId;
  
  private String slideNo;

  private Integer labSampleDtlsId;

  private Integer stainingId;

  private String remark;

  private Integer createdBy;

  private Long createdDate;

  private Integer updatedBy;

  private Long updatedDate;

  private Character isDeleted;

  private Character isNew;
  
  private Character isSlideCreated;
  
 

  public Integer gettRestainingSubDetailId() {
    return tRestainingSubDetailId;
  }

  public void settRestainingSubDetailId(Integer tRestainingSubDetailId) {
    this.tRestainingSubDetailId = tRestainingSubDetailId;
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

  public Integer gettRestainingDetailId() {
    return tRestainingDetailId;
  }

  public void settRestainingDetailId(Integer tRestainingDetailId) {
    this.tRestainingDetailId = tRestainingDetailId;
  }


  public Integer gettSlideId() {
    return tSlideId;
  }

  public void settSlideId(Integer tSlideId) {
    this.tSlideId = tSlideId;
  }

  public Integer getLabSampleDtlsId() {
    return labSampleDtlsId;
  }

  public void setLabSampleDtlsId(Integer labSampleDtlsId) {
    this.labSampleDtlsId = labSampleDtlsId;
  }

  public Integer getStainingId() {
    return stainingId;
  }

  public void setStainingId(Integer stainingId) {
    this.stainingId = stainingId;
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
    this.isDeleted = isDeleted;
  }

  public Character getIsNew() {
    return isNew;
  }

  public void setIsNew(Character isNew) {
    this.isNew = isNew;
  }

  public String getSlideNo() {
    return slideNo;
  }

  public void setSlideNo(String slideNo) {
    this.slideNo = slideNo;
  }

  public Character getIsSlideCreated() {
    return isSlideCreated;
  }

  public void setIsSlideCreated(Character isSlideCreated) {
    this.isSlideCreated = isSlideCreated;
  }
  
}
