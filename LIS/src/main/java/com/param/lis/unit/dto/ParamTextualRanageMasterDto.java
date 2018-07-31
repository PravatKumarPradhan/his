package com.param.lis.unit.dto;

public class ParamTextualRanageMasterDto {

  private Integer paramTextualRangeId;

  private Integer parameterId;

  private Integer textualRangeId;

  private String textualRangeName;

  private Integer createdBy;

  private Long createdDate;

  private Integer updatedBy;

  private Long updatedDate;

  private char status;

  private Integer orgId;

  private Integer orgUnitId;

  private char isDeleted;

  private Integer ageGroupId;

  private String remark;

  private Integer genderId;

  private String genderName;

  private String ageGroupName;

  private Integer ageFromDay;

  private Integer ageToDay;

  public Integer getParamTextualRangeId() {
    return paramTextualRangeId;
  }

  public void setParamTextualRangeId(Integer paramTextualRangeId) {
    this.paramTextualRangeId = paramTextualRangeId;
  }

  public Integer getParameterId() {
    return parameterId;
  }

  public void setParameterId(Integer parameterId) {
    this.parameterId = parameterId;
  }

  public Integer getTextualRangeId() {
    return textualRangeId;
  }

  public void setTextualRangeId(Integer textualRangeId) {
    this.textualRangeId = textualRangeId;
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
    this.status = (status == '\u0000') ? 'A' : status;
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

  public char getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(char isDeleted) {
    this.isDeleted = (isDeleted == '\u0000') ? 'N' : isDeleted;
  }

  public Integer getAgeGroupId() {
    return ageGroupId;
  }

  public void setAgeGroupId(Integer ageGroupId) {
    this.ageGroupId = ageGroupId;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Integer getGenderId() {
    return genderId;
  }

  public void setGenderId(Integer genderId) {
    this.genderId = genderId;
  }

  public String getGenderName() {
    return genderName;
  }

  public void setGenderName(String genderName) {
    this.genderName = genderName;
  }

  public String getAgeGroupName() {
    return ageGroupName;
  }

  public void setAgeGroupName(String ageGroupName) {
    this.ageGroupName = ageGroupName;
  }

  public Integer getAgeFromDay() {
    return ageFromDay;
  }

  public void setAgeFromDay(Integer ageFromDay) {
    this.ageFromDay = ageFromDay;
  }

  public Integer getAgeToDay() {
    return ageToDay;
  }

  public void setAgeToDay(Integer ageToDay) {
    this.ageToDay = ageToDay;
  }

  public String getTextualRangeName() {
    return textualRangeName;
  }

  public void setTextualRangeName(String textualRangeName) {
    this.textualRangeName = textualRangeName;
  }


}
