package com.param.lis.global.dto;

public class RefRangeTypeMasterDto {
  
  private Integer refrangeTypeId;
  
  private String refrangeTypeName;
  
  private Integer createdBy;
  
  private Long createdDate;

  private Integer updatedBy;
  
  private Long updatedDate;
  
  private char status;
  
  private Integer orgId;
  
  private Integer orgUnitId;

  public int getRefrangeTypeId() {
    return refrangeTypeId;
  }

  public void setRefrangeTypeId(int refrangeTypeId) {
    this.refrangeTypeId = refrangeTypeId;
  }

  public String getRefrangeTypeName() {
    return refrangeTypeName;
  }

  public void setRefrangeTypeName(String refrangeTypeName) {
    this.refrangeTypeName = refrangeTypeName;
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

  public char getStatus() {
    return status;
  }

  public void setStatus(char status) {
    this.status = (status == '\u0000') ? 'I' : status;
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
  
  
  

}
