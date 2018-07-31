package com.param.lis.unit.dto;

public class MicrobiologyTestMasterDto {

  private Integer microTestId;

  private String microTestCode;

  private String microTestDesc;

  private Integer orgId;

  private Integer orgUnitId;

  private Integer createdBy;

  private Long createdDate;

  private Integer updatedBy;

  private Long updatedDate;

  private Character status;

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


  public Character getStatus() {
    return status;
  }

  public void setStatus(Character status) {
    this.status = (status == '\u0000') ? 'N' : status;
  }

  public String getMicroTestCode() {
    return microTestCode;
  }

  public void setMicroTestCode(String microTestCode) {
    this.microTestCode = microTestCode;
  }

  public String getMicroTestDesc() {
    return microTestDesc;
  }

  public void setMicroTestDesc(String microTestDesc) {
    this.microTestDesc = microTestDesc;
  }


}
