package com.param.entity.lis.unit;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.common.LocalTimeConverter;

@Entity
@Table(name = "m_formula_details", schema = "lab")
@SequenceGenerator(name = "m_seq_formula_details", sequenceName = "lab.m_seq_formula_details", allocationSize = 1)
public class FormulaDetails {
  
  @Id
  @Column(name = "formula_details_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_formula_details")
  private int formulaDetailsId;

  @Column(name = "formula_id")
  private Integer formulaId;
  
  @Column(name = "parameter_id")
  private Integer parameterId;
  
  @Column(name = "param_variable")
  private Character paramVariable;

  @Column(name = "org_id")
  private Integer orgId;
  
  @Column(name = "org_unit_id")
  private Integer orgUnitId;

  @Column(name = "is_deleted")
  private char isDeleted;

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
  @JoinColumn(name = "parameter_id", insertable = false, nullable = false, updatable = false)
  private ParameterMaster parameterMaster;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "formula_id", insertable = false, nullable = false, updatable = false)
  private FormulaMaster formulaMaster;

  public int getFormulaDetailsId() {
    return formulaDetailsId;
  }

  public void setFormulaDetailsId(int formulaDetailsId) {
    this.formulaDetailsId = formulaDetailsId;
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

  public ParameterMaster getParameterMaster() {
    return parameterMaster;
  }

  public void setParameterMaster(ParameterMaster parameterMaster) {
    this.parameterMaster = parameterMaster;
  }

  public FormulaMaster getFormulaMaster() {
    return formulaMaster;
  }

  public void setFormulaMaster(FormulaMaster formulaMaster) {
    this.formulaMaster = formulaMaster;
  }
  

}
