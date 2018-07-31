package com.param.lis.microbiology.transaction.dto;

public class TBiochemicalTestDetailsMasterDto {

  private Integer tBiochemicalTestDetailsId;
  
  private Integer microTestId;

  private Integer orgId;

  private Integer orgUnitId;

  private Integer biochemTestId;

  private String result;

  private String remark;

  private Integer createdBy;

  private Long createdDate;

  private Integer updatedBy;

  private Long updatedDate;

  private Character isDeleted;


  public Integer gettBiochemicalTestDetailsId() {
    return tBiochemicalTestDetailsId;
  }

  public void settBiochemicalTestDetailsId(Integer tBiochemicalTestDetailsId) {
    this.tBiochemicalTestDetailsId = tBiochemicalTestDetailsId;
  }

  public Integer getMicroTestId() {
    return microTestId;
  }

  public void setMicroTestId(Integer microTestId) {
    this.microTestId = microTestId;
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

  public Integer getBiochemTestId() {
    return biochemTestId;
  }

  public void setBiochemTestId(Integer biochemTestId) {
    this.biochemTestId = biochemTestId;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
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



}
