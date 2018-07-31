package com.param.entity.lis.transaction;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.common.LocalTimeConverter;
import com.param.entity.lis.unit.HeaderMaster;
import com.param.entity.lis.unit.ParameterMaster;
import com.param.entity.lis.unit.TestMaster;

@NamedQueries({

    @NamedQuery(name = "GET_LAB_RESULT_DETAILS",
        query = "SELECT labResultDetailsMaster.labResDtlsId AS labResDtlsId, "
            + " labResultDetailsMaster.labResultId AS labResultId,"
            + " labResultDetailsMaster.parameterId AS parameterId,"
            + " labResultDetailsMaster.headerId AS headerId,"
            + " labResultDetailsMaster.orgId AS orgId,"
            + " labResultDetailsMaster.orgUnitId AS orgUnitId,"
            + " labResultDetailsMaster.testId AS testId,"
            + " labResultDetailsMaster.paramName AS paramName,"
            + " labResultDetailsMaster.paramUnit AS paramUnit,"
            + " labResultDetailsMaster.paramUnit AS paramUnit,"
            + " labResultDetailsMaster.sampleNo AS sampleNo,"
            + " labResultDetailsMaster.firstLevelResult AS firstLevelResult,"
            + " labResultDetailsMaster.secondLevelResult AS secondLevelResult,"
            + " labResultDetailsMaster.thirdLevelResult AS thirdLevelResult,"
            + " labResultDetailsMaster.finalResult AS finalResult,"
            + " labResultDetailsMaster.resultTypeFlag AS resultTypeFlag,"
            + " labResultDetailsMaster.parameterMin AS parameterMin,"
            + " labResultDetailsMaster.paramAbnrmlMin AS paramAbnrmlMin,"
            + " labResultDetailsMaster.parameterMax AS parameterMax,"
            + " labResultDetailsMaster.paramAbnrmlMax AS paramAbnrmlMax,"
            + " labResultDetailsMaster.paramPrintOrder AS paramPrintOrder,"
            + " labResultDetailsMaster.refrangeTypeId AS refrangeTypeId,"
            + " labResultDetailsMaster.textualRangeId AS textualRangeId,"
            + " labResultDetailsMaster.multitextaulRange AS multitextaulRange,"
            + " labResultDetailsMaster.textualRangeName AS textualRangeName,"
            + " labResultDetailsMaster.machineId AS machineId,"
            + " labResultDetailsMaster.machineResult AS machineResult,"
            + " labResultDetailsMaster.remarks AS remarks,"
            + " labResultDetailsMaster.isDeltaFlag AS isDeltaFlag,"
            + " labResultDetailsMaster.deltaPer AS deltaPer,"
            + " labResultDetailsMaster.deltaDaysId AS deltaDaysId,"
            + " labResultDetailsMaster.deltaChange AS deltaChange,"
            + " labResultDetailsMaster.infromationDtls AS infromationDtls,"
            + " labResultDetailsMaster.informationType AS informationType,"
            + " labResultDetailsMaster.informedTo AS informedTo,"
            + " labResultDetailsMaster.informedDatetime AS informedDatetime,"
            + " labResultDetailsMaster.paramRecheckedBy AS paramRecheckedBy,"
            + " labResultDetailsMaster.parameterFormula AS parameterFormula,"
            + " labResultDetailsMaster.isFormula AS isFormula,"
            + " labResultDetailsMaster.createdBy AS createdBy,"
            + " labResultDetailsMaster.createdDate AS createdDate,"
            + " labResultDetailsMaster.updatedBy AS updatedBy,"
            + " labResultDetailsMaster.updatedDate AS updatedDate "
            + " FROM LabResultDetailsMaster labResultDetailsMaster "
            + " WHERE labResultDetailsMaster.orgId = :orgId "
            + " AND labResultDetailsMaster.orgUnitId= :orgUnitId"
            + " AND labResultDetailsMaster.labResultId= :labResultId")})

@Entity
@Table(name = "t_lab_res_dtls", schema = "lab")
@SequenceGenerator(name = "m_seq_res_dtls", sequenceName = "lab.m_seq_res_dtls", allocationSize = 1)
public class LabResultDetailsMaster {
  @Id
  @Column(name = "lab_res_dtls_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_res_dtls")
  private int labResDtlsId;

  @Column(name = "lab_result_id")
  private Integer labResultId;

  @Column(name = "parameter_id")
  private Integer parameterId;

  @Column(name = "header_id")
  private Integer headerId;

  @Column(name = "org_id")
  private Integer orgId;

  @Column(name = "org_unit_id")
  private Integer orgUnitId;

  @Column(name = "test_id")
  private Integer testId;

  @Column(name = "param_name")
  private String paramName;

  @Column(name = "param_unit")
  private String paramUnit;

  @Column(name = "sample_no")
  private String sampleNo;

  @Column(name = "final_result")
  private String finalResult;

  @Column(name = "first_level_result")
  private String firstLevelResult;

  @Column(name = "second_level_result")
  private String secondLevelResult;

  @Column(name = "third_level_result")
  private String thirdLevelResult;

  @Column(name = "result_type_flag")
  private Character resultTypeFlag;

  @Column(name = "parameter_min")
  private Double parameterMin;

  @Column(name = "param_abnrml_min")
  private Double paramAbnrmlMin;

  @Column(name = "parameter_max")
  private Double parameterMax;

  @Column(name = "param_abnrml_max")
  private Double paramAbnrmlMax;

  @Column(name = "param_print_order")
  private Integer paramPrintOrder;

  @Column(name = "refrange_type_id")
  private Integer refrangeTypeId;

  @Column(name = "textual_range_id")
  private Integer textualRangeId;

  @Column(name = "multitextaul_range")
  private String multitextaulRange;

  @Column(name = "textual_range_name")
  private String textualRangeName;

  @Column(name = "machine_id ")
  private Integer machineId;

  @Column(name = "machine_result")
  private Double machineResult;

  @Column(name = "remarks")
  private String remarks;

  @Column(name = "is_delta_flag")
  private Character isDeltaFlag;

  @Column(name = "delta_per")
  private Double deltaPer;

  @Column(name = "delta_change")
  private Double deltaChange;

  @Column(name = "delta_days_id")
  private Integer deltaDaysId;

  @Column(name = "infromation_dtls")
  private String infromationDtls;

  @Column(name = "information_type")
  private String informationType;

  @Column(name = "informed_to")
  private Integer informedTo;

  @Column(name = "informed_datetime")
  @Convert(converter = LocalTimeConverter.class)
  private Long informedDatetime;

  @Column(name = "param_rechecked_by")
  private Integer paramRecheckedBy;

  @Column(name = "parameter_formula")
  private String parameterFormula;

  @Column(name = "is_formula")
  private Character isFormula;

  @Column(name = "created_by")
  private Integer createdBy;

  @Column(name = "created_date")
  @Convert(converter = LocalTimeConverter.class)
  private Long createdDate;

  @Column(name = "updated_by")
  private Integer updatedBy;

  @Column(name = "updated_date")
  @Convert(converter = LocalTimeConverter.class)
  private Long updatedDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "lab_result_id", insertable = false, nullable = false, updatable = false)
  private LabSampleMaster labSampleMaster;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parameter_id", insertable = false, nullable = false, updatable = false)
  private ParameterMaster parameterMaster;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "header_id", insertable = false, nullable = false, updatable = false)
  private HeaderMaster headerMaster;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "test_id", insertable = false, nullable = false, updatable = false)
  private TestMaster testMaster;

  public int getLabResDtlsId() {
    return labResDtlsId;
  }

  public void setLabResDtlsId(int labResDtlsId) {
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

  public Double getMachineResult() {
    return machineResult;
  }

  public void setMachineResult(Double machineResult) {
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

  public LabSampleMaster getLabSampleMaster() {
    return labSampleMaster;
  }

  public void setLabSampleMaster(LabSampleMaster labSampleMaster) {
    this.labSampleMaster = labSampleMaster;
  }

  public ParameterMaster getParameterMaster() {
    return parameterMaster;
  }

  public void setParameterMaster(ParameterMaster parameterMaster) {
    this.parameterMaster = parameterMaster;
  }

  public HeaderMaster getHeaderMaster() {
    return headerMaster;
  }

  public void setHeaderMaster(HeaderMaster headerMaster) {
    this.headerMaster = headerMaster;
  }

  public TestMaster getTestMaster() {
    return testMaster;
  }

  public void setTestMaster(TestMaster testMaster) {
    this.testMaster = testMaster;
  }

  public Double getDeltaPer() {
    return deltaPer;
  }

  public void setDeltaPer(Double deltaPer) {
    this.deltaPer = deltaPer;
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

  public Character getIsFormula() {
    return isFormula;
  }

  public void setIsFormula(Character isFormula) {
    this.isFormula = (isFormula == '\u0000') ? 'N' : isFormula;
  }


}
