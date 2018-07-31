package com.param.lis.unit.dto;

import java.util.List;

public class FormulaMasterDto {


  private Integer formulaId;

  private Integer parameterId;
  
  private String paramDesc;
  
  private Integer orgId;
  
  private Integer orgUnitId;

  private String formula;

  private String formulaLatex;

  private char status;

  private Integer createdBy;

  private Long createdDate;

  private Integer updatedBy;

  private Long updatedDate;
  
  private List<FormulaDetailsDto> listFormulaDetails;

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

  public String getFormula() {
    return formula;
  }

  public void setFormula(String formula) {
    this.formula = formula;
  }

  public String getFormulaLatex() {
    return formulaLatex;
  }

  public void setFormulaLatex(String formulaLatex) {
    this.formulaLatex = formulaLatex;
  }

  public char getStatus() {
    return status;
  }

  public void setStatus(char status) {
    this.status = (status == '\u0000') ? 'A' : status;
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
  

  public List<FormulaDetailsDto> getListFormulaDetails() {
    return listFormulaDetails;
  }

  public void setListFormulaDetails(List<FormulaDetailsDto> listFormulaDetails) {
    this.listFormulaDetails = listFormulaDetails;
  }

  public String getParamDesc() {
    return paramDesc;
  }

  public void setParamDesc(String paramDesc) {
    this.paramDesc = paramDesc;
  }
  
  
}
