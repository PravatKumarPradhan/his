package com.param.lis.unit.dto;

public class FormulaDetailsDto {

  private Integer formulaDetailsId;

  private Integer formulaId;

  private Integer parameterId;

  private String paramDesc;

  private String formula;

  private Character paramVariable;

  private Integer orgId;

  private Integer orgUnitId;

  private char isDeleted;

  private Integer createdBy;

  private Long createdDate;

  private Integer updatedBy;

  private Long updatedDate;

  public Integer getFormulaDetailsId() {
    return formulaDetailsId;
  }

  public Integer getFormulaId() {
    return formulaId;
  }

  public void setFormulaId(Integer formulaId) {
    this.formulaId = formulaId;
  }

  public Integer getParameterId() {
    return parameterId;
  }

  public void setParameterId(Integer parameterId) {
    this.parameterId = parameterId;
  }

  public Character getParamVariable() {
    return paramVariable;
  }

  public void setParamVariable(Character paramVariable) {
    this.paramVariable = paramVariable;
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

  public String getParamDesc() {
    return paramDesc;
  }

  public void setParamDesc(String paramDesc) {
    this.paramDesc = paramDesc;
  }

  public void setFormulaDetailsId(Integer formulaDetailsId) {
    this.formulaDetailsId = formulaDetailsId;
  }

  public String getFormula() {
    return formula;
  }

  public void setFormula(String formula) {
    this.formula = formula;
  }



}
