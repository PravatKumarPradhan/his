package com.param.lis.transaction.dto;

import java.util.List;
import javax.persistence.Column;
import com.param.lis.unit.dto.FormulaDetailsDto;
import com.param.lis.unit.dto.HelpValueMasterDto;
import com.param.lis.unit.dto.ParamRefrengMasterDto;

public class LabResultDetailsViewDto {

  private Integer labResDtlsId;
  private Integer labResultId;
  private Integer parameterId;
  private Integer headerId;
  private Integer orgId;
  private Integer orgUnitId;
  private Integer testId;
  private String paramName;
  private String paramUnit;
  private String sampleNo;
  private String finalResult;
  private String firstLevelResult;
  private String secondLevelResult;
  private String thirdLevelResult;
  private Character resultTypeFlag;
  private Double parameterMin;
  private Double paramAbnrmlMin;
  private Double parameterMax;
  private Double paramAbnrmlMax;
  private Integer paramPrintOrder;
  private Integer refrangeTypeId;
  private Integer textualRangeId;
  private String multitextaulRange;
  private String textualRangeName;
  private Integer machineId;
  private String machineResult;
  private String remarks;
  private Character isDeltaFlag;
  private String infromationDtls;
  private String informationType;
  private Integer informedTo;
  private Long informedDatetime;
  private Integer paramRecheckedBy;
  private Character isFormula;
  private String parameterFormula;
  private Integer createdBy;
  private Long createdDate;
  private Integer updatedBy;
  private Integer reportType;
  private Long updatedDate;
  private Double deltaPer;
  private Double deltaChange;
  private Integer deltaDaysId;
  private String arrowClass;
  private List<ParamRefrengMasterDto> listParamRefrengMaster;
  private List<FormulaDetailsDto> listFormulaDetails;
  private List<HelpValueMasterDto> listHelpValueMaster;

  public Integer getLabResDtlsId() {
    return labResDtlsId;
  }

  public void setLabResDtlsId(Integer labResDtlsId) {
    this.labResDtlsId = labResDtlsId;
  }

  public Integer getLabResultId() {
    return labResultId;
  }

  public void setLabResultId(Integer labResultId) {
    this.labResultId = labResultId;
  }

  public Integer getParameterId() {
    return parameterId;
  }

  public void setParameterId(Integer parameterId) {
    this.parameterId = parameterId;
  }

  public Integer getHeaderId() {
    return headerId;
  }

  public void setHeaderId(Integer headerId) {
    this.headerId = headerId;
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

  public Integer getTestId() {
    return testId;
  }

  public void setTestId(Integer testId) {
    this.testId = testId;
  }

  public String getParamName() {
    return paramName;
  }

  public void setParamName(String paramName) {
    this.paramName = paramName;
  }

  public String getParamUnit() {
    return paramUnit;
  }

  public void setParamUnit(String paramUnit) {
    this.paramUnit = paramUnit;
  }

  public String getSampleNo() {
    return sampleNo;
  }

  public void setSampleNo(String sampleNo) {
    this.sampleNo = sampleNo;
  }

  public String getFinalResult() {
    return finalResult;
  }

  public void setFinalResult(String finalResult) {
    this.finalResult = finalResult;
  }

  public String getFirstLevelResult() {
    return firstLevelResult;
  }

  public void setFirstLevelResult(String firstLevelResult) {
    this.firstLevelResult = firstLevelResult;
  }

  public String getSecondLevelResult() {
    return secondLevelResult;
  }

  public void setSecondLevelResult(String secondLevelResult) {
    this.secondLevelResult = secondLevelResult;
  }

  public String getThirdLevelResult() {
    return thirdLevelResult;
  }

  public void setThirdLevelResult(String thirdLevelResult) {
    this.thirdLevelResult = thirdLevelResult;
  }

  public Character getResultTypeFlag() {
    return resultTypeFlag;
  }

  public void setResultTypeFlag(Character resultTypeFlag) {
    this.resultTypeFlag = resultTypeFlag;
  }

  public Double getParameterMin() {
    return parameterMin;
  }

  public void setParameterMin(Double parameterMin) {
    this.parameterMin = parameterMin;
  }

  public Double getParamAbnrmlMin() {
    return paramAbnrmlMin;
  }

  public void setParamAbnrmlMin(Double paramAbnrmlMin) {
    this.paramAbnrmlMin = paramAbnrmlMin;
  }

  public Double getParameterMax() {
    return parameterMax;
  }

  public void setParameterMax(Double parameterMax) {
    this.parameterMax = parameterMax;
  }

  public Double getParamAbnrmlMax() {
    return paramAbnrmlMax;
  }

  public void setParamAbnrmlMax(Double paramAbnrmlMax) {
    this.paramAbnrmlMax = paramAbnrmlMax;
  }

  public Integer getParamPrintOrder() {
    return paramPrintOrder;
  }

  public void setParamPrintOrder(Integer paramPrintOrder) {
    this.paramPrintOrder = paramPrintOrder;
  }

  public Integer getMachineId() {
    return machineId;
  }

  public void setMachineId(Integer machineId) {
    this.machineId = machineId;
  }

  public String getMachineResult() {
    return machineResult;
  }

  public void setMachineResult(String machineResult) {
    this.machineResult = machineResult;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public Character getIsDeltaFlag() {
    return isDeltaFlag;
  }

  public void setIsDeltaFlag(Character isDeltaFlag) {
    this.isDeltaFlag = isDeltaFlag;
  }

  public String getInfromationDtls() {
    return infromationDtls;
  }

  public void setInfromationDtls(String infromationDtls) {
    this.infromationDtls = infromationDtls;
  }

  public String getInformationType() {
    return informationType;
  }

  public void setInformationType(String informationType) {
    this.informationType = informationType;
  }

  public Integer getInformedTo() {
    return informedTo;
  }

  public void setInformedTo(Integer informedTo) {
    this.informedTo = informedTo;
  }

  public Long getInformedDatetime() {
    return informedDatetime;
  }

  public void setInformedDatetime(Long informedDatetime) {
    this.informedDatetime = informedDatetime;
  }

  public Integer getParamRecheckedBy() {
    return paramRecheckedBy;
  }

  public void setParamRecheckedBy(Integer paramRecheckedBy) {
    this.paramRecheckedBy = paramRecheckedBy;
  }

  public String getParameterFormula() {
    return parameterFormula;
  }

  public void setParameterFormula(String parameterFormula) {
    this.parameterFormula = parameterFormula;
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

  public Integer getReportType() {
    return reportType;
  }

  public void setReportType(Integer reportType) {
    this.reportType = reportType;
  }

  public Double getDeltaPer() {
    return deltaPer;
  }

  public void setDeltaPer(Double deltaPer) {
    this.deltaPer = deltaPer;
  }

  public String getArrowClass() {
    return arrowClass;
  }

  public void setArrowClass(String arrowClass) {
    this.arrowClass = arrowClass;
  }

  public Double getDeltaChange() {
    return deltaChange;
  }

  public void setDeltaChange(Double deltaChange) {
    this.deltaChange = deltaChange;
  }

  public Integer getDeltaDaysId() {
    return deltaDaysId;
  }

  public void setDeltaDaysId(Integer deltaDaysId) {
    this.deltaDaysId = deltaDaysId;
  }

  public Integer getRefrangeTypeId() {
    return refrangeTypeId;
  }

  public void setRefrangeTypeId(Integer refrangeTypeId) {
    this.refrangeTypeId = refrangeTypeId;
  }

  public Integer getTextualRangeId() {
    return textualRangeId;
  }

  public void setTextualRangeId(Integer textualRangeId) {
    this.textualRangeId = textualRangeId;
  }

  public String getMultitextaulRange() {
    return multitextaulRange;
  }

  public void setMultitextaulRange(String multitextaulRange) {
    this.multitextaulRange = multitextaulRange;
  }

  public String getTextualRangeName() {
    return textualRangeName;
  }

  public void setTextualRangeName(String textualRangeName) {
    this.textualRangeName = textualRangeName;
  }

  public List<FormulaDetailsDto> getListFormulaDetails() {
    return listFormulaDetails;
  }

  public void setListFormulaDetails(List<FormulaDetailsDto> listFormulaDetails) {
    this.listFormulaDetails = listFormulaDetails;
  }

  public Character getIsFormula() {
    return isFormula;
  }

  public void setIsFormula(Character isFormula) {
    this.isFormula = isFormula;
  }


}
