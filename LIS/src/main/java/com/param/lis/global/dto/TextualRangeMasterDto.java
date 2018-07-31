package com.param.lis.global.dto;

public class TextualRangeMasterDto {
  
  private Integer textualRangeId;
  
  private String textualRangeCode;
  
  private String textualRangeName;
  
  private Integer createdBy;
  
  private Long createdDate;

  private Integer updatedBy;
  
  private Long updatedDate;
  
  private char status;
  
  private Integer orgId;
  
  private Integer orgUnitId;

  public Integer getTextualRangeId() {
    return textualRangeId;
  }

  public void setTextualRangeId(Integer textualRangeId) {
    this.textualRangeId = textualRangeId;
  }

  public String getTextualRangeCode() {
    return textualRangeCode;
  }

  public void setTextualRangeCode(String textualRangeCode) {
    this.textualRangeCode = textualRangeCode;
  }

  public String getTextualRangeName() {
    return textualRangeName;
  }

  public void setTextualRangeName(String textualRangeName) {
    this.textualRangeName = textualRangeName;
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
