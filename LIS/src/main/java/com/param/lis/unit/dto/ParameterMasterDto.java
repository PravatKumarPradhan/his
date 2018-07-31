package com.param.lis.unit.dto;

import java.util.List;

public class ParameterMasterDto {
  private Integer parameterId;
  private Integer refrangeTypeId;
  private String parameterName;
  private String aliesName;
  private String parameterCode;
  private Integer unitId;
  private String unitName;
  private Integer deltaDaysId;
  private String deltaDays;
  private Double deltaPer;
  private Integer testType;
  private Character isMultyparameter;
  private Integer createdBy;
  private Long createdDate;
  private Integer updatedBy;
  private Long updatedDate;
  private Integer orgId;
  private Integer orgUnitId;
  private Character status;
  private Character isFormula;
  private List<ParamRefrengMasterDto> listParamRefrengMaster;
  private List<HelpValueMasterDto> listHelpValueMaster;
  private List<ParamTextualRanageMasterDto> listParamTextualRanageMaster;
  private List<ParamMultiTextualRangeMasterDto> listParamMultiTextualRangeMaster;

  public String getDeltaDays() {
    return deltaDays;
  }

  public void setDeltaDays(String deltaDays) {
    this.deltaDays = deltaDays;
  }

  public Integer getParameterId() {
    return parameterId;
  }

  public void setParameterId(Integer parameterId) {
    this.parameterId = parameterId;
  }

  public String getParameterName() {
    return parameterName;
  }

  public void setParameterName(String parameterName) {
    this.parameterName = parameterName;
  }

  public String getAliesName() {
    return aliesName;
  }

  public void setAliesName(String aliesName) {
    this.aliesName = aliesName;
  }

  public String getParameterCode() {
    return parameterCode;
  }

  public void setParameterCode(String parameterCode) {
    this.parameterCode = parameterCode;
  }

  public Integer getUnitId() {
    return unitId;
  }

  public void setUnitId(Integer unitId) {
    this.unitId = unitId;
  }

  public Integer getDeltaDaysId() {
    return deltaDaysId;
  }

  public void setDeltaDaysId(Integer deltaDaysId) {
    this.deltaDaysId = deltaDaysId;
  }

  public Double getDeltaPer() {
    return deltaPer;
  }

  public void setDeltaPer(Double deltaPer) {
    this.deltaPer = deltaPer;
  }

  public Integer getTestType() {
    return testType;
  }

  public void setTestType(Integer testType) {
    this.testType = testType;
  }

  public Character getIsMultyparameter() {
    return isMultyparameter;
  }

  public void setIsMultyparameter(Character isMultyparameter) {
    this.isMultyparameter = (isMultyparameter == '\u0000') ? 'N' : isMultyparameter;
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

  public List<ParamRefrengMasterDto> getListParamRefrengMaster() {
    return listParamRefrengMaster;
  }

  public void setListParamRefrengMaster(List<ParamRefrengMasterDto> listParamRefrengMaster) {
    this.listParamRefrengMaster = listParamRefrengMaster;
  }

  public List<HelpValueMasterDto> getListHelpValueMaster() {
    return listHelpValueMaster;
  }

  public void setListHelpValueMaster(List<HelpValueMasterDto> listHelpValueMaster) {
    this.listHelpValueMaster = listHelpValueMaster;
  }

  public Character getStatus() {
    return status;
  }

  public void setStatus(Character status) {
    this.status = status;
  }

  public String getUnitName() {
    return unitName;
  }

  public void setUnitName(String unitName) {
    this.unitName = unitName;
  }

  public Integer getRefrangeTypeId() {
    return refrangeTypeId;
  }

  public void setRefrangeTypeId(Integer refrangeTypeId) {
    this.refrangeTypeId = refrangeTypeId;
  }

  public List<ParamTextualRanageMasterDto> getListParamTextualRanageMaster() {
    return listParamTextualRanageMaster;
  }

  public void setListParamTextualRanageMaster(
      List<ParamTextualRanageMasterDto> listParamTextualRanageMaster) {
    this.listParamTextualRanageMaster = listParamTextualRanageMaster;
  }

  public List<ParamMultiTextualRangeMasterDto> getListParamMultiTextualRangeMaster() {
    return listParamMultiTextualRangeMaster;
  }

  public void setListParamMultiTextualRangeMaster(
      List<ParamMultiTextualRangeMasterDto> listParamMultiTextualRangeMaster) {
    this.listParamMultiTextualRangeMaster = listParamMultiTextualRangeMaster;
  }

  public Character getIsFormula() {
    return isFormula;
  }

  public void setIsFormula(Character isFormula) {
    this.isFormula = (isFormula == '\u0000') ? 'N' : isFormula;
  }


}
