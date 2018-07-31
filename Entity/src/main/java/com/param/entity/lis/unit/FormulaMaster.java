package com.param.entity.lis.unit;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.common.LocalTimeConverter;


@NamedQueries(
    {
      @NamedQuery(name="CHECK_PARAMETER_EXISTS",query= "SELECT " 
          + " COUNT(*)  "
          + "FROM  "
          + " FormulaMaster formulaMaster  "
          + "WHERE  " 
          + " formulaMaster.orgId =:orgId "
          + " AND formulaMaster.orgUnitId =:orgUnitId" 
          + " AND formulaMaster.status = 'A'  "
          + " AND formulaMaster.parameterId = :parameterId "),
      
      @NamedQuery(name="UPDATE_FORMULA_STATUS",query= "UPDATE " 
          + " FormulaDetails  "
          + " SET isDeleted ='Y'"
          + "  WHERE  " 
          + " orgId =:orgId "
          + " AND orgUnitId =:orgUnitId" 
          + " AND formulaId = :formulaId "),
      
      
      
      @NamedQuery(name="ACTIVE_INACTIVE_FORMULA",query= "UPDATE " 
          + " FormulaMaster  "
          + " SET status = :status  "
          + "  WHERE  " 
          + " orgId =:orgId "
          + " AND orgUnitId =:orgUnitId" 
          + " AND formulaId = :formulaId "),
      
      @NamedQuery(name="GET_FORMULA_MASTER",query= "SELECT " 
          + "formulaMaster.formulaId AS formulaId,  "
          + "formulaMaster.parameterId AS parameterId,  "
          + "formulaMaster.orgId AS orgId,  "
          + "formulaMaster.orgUnitId AS orgUnitId,  "
          + "formulaMaster.formula AS formula,  "
          + "formulaMaster.formulaLatex AS formulaLatex,  "
          + "formulaMaster.status AS status,  "
          + "formulaMaster.createdBy AS createdBy,  "
          + "formulaMaster.createdDate AS createdDate,  "
          + "formulaMaster.updatedBy AS updatedBy,  "
          + "formulaMaster.updatedDate AS updatedDate  "
          + "FROM  "
          + " FormulaMaster formulaMaster  "
          + "WHERE  " 
          + " formulaMaster.orgId =:orgId "
          + " AND formulaMaster.orgUnitId =:orgUnitId" 
          + " AND formulaMaster.formulaId = :formulaId "),
      
      @NamedQuery(name="GET_FORMULA_MASTER_DETAILS",query= "SELECT " 
          + "formulaDetails.formulaDetailsId AS formulaDetailsId,  "
          + "formulaDetails.formulaId AS formulaId,  "
          + "formulaDetails.parameterId AS parameterId,  "
          + "formulaDetails.orgId AS orgId,  "
          + "formulaDetails.orgUnitId AS orgUnitId,  "
          + "formulaDetails.paramVariable AS paramVariable,  "
          + "formulaDetails.isDeleted AS isDeleted,  "
          + "formulaDetails.createdBy AS createdBy,  "
          + "formulaDetails.createdDate AS createdDate,  "
          + "formulaDetails.updatedBy AS updatedBy,  "
          + "formulaDetails.updatedDate AS updatedDate  "
          + "FROM  "
          + " FormulaDetails formulaDetails  "
          + "WHERE  " 
          + " formulaDetails.orgId =:orgId "
          + " AND formulaDetails.orgUnitId =:orgUnitId" 
          + " AND formulaDetails.isDeleted = 'N'  "
          + " AND formulaDetails.formulaId = :formulaId "),
     
      
      @NamedQuery(name="GET_FORMULA_MASTER_LIST_COUNT",query= "SELECT " 
          + " COUNT(*)  "
          + "FROM  "
          + " FormulaMaster formulaMaster  "
          + "WHERE  " 
          + " formulaMaster.orgId =:orgId "
          + " AND formulaMaster.orgUnitId =:orgUnitId"),
      
      @NamedQuery(name="GET_FORMULA_MASTER_LIST",query= "SELECT " 
          + "formulaMaster.formulaId AS formulaId,  "
          + "formulaMaster.parameterId AS parameterId,  "
          + "parameterMst.parameterName AS paramDesc,  "
          + "formulaMaster.orgId AS orgId,  "
          + "formulaMaster.orgUnitId AS orgUnitId,  "
          + "formulaMaster.formula AS formula,  "
          + "formulaMaster.formulaLatex AS formulaLatex,  "
          + "formulaMaster.status AS status,  "
          + "formulaMaster.createdBy AS createdBy,  "
          + "formulaMaster.createdDate AS createdDate,  "
          + "formulaMaster.updatedBy AS updatedBy,  "
          + "formulaMaster.updatedDate AS updatedDate  "
          + "FROM  "
          + " FormulaMaster formulaMaster  "
          + " INNER JOIN formulaMaster.parameterMaster parameterMst"
          + " WHERE  " 
          + " formulaMaster.orgId =:orgId "
          + " AND formulaMaster.orgUnitId =:orgUnitId" 
              ),
      @NamedQuery(name="GET_FORMULA_DETAILS",query= "SELECT " 
          + "parameterMst.parameterName AS paramDesc,  "
          + "formulaDetails.paramVariable AS paramVariable  "
          + "FROM  "
          + " FormulaDetails formulaDetails  "
          + " INNER JOIN formulaDetails.parameterMaster parameterMst"
          + " WHERE  " 
          + " formulaDetails.orgId=:orgId"
          + " AND formulaDetails.orgUnitId =:orgUnitId"
          + " AND formulaDetails.formulaId =:formulaId"),
      
      @NamedQuery(name="GET_FORMULA_DETAILS_BY_PARAMETER",query= "SELECT " 
          + "formulaMaster.formula AS formula,  "
          + "lstFormulaDetails.parameterId AS parameterId,  "
          + "lstFormulaDetails.paramVariable AS paramVariable  "
          + "FROM  "
          + " FormulaMaster formulaMaster  "
          + " INNER JOIN formulaMaster.listFormulaDetails lstFormulaDetails"
          + " WHERE  " 
          + " formulaMaster.orgId=:orgId"
          + " AND formulaMaster.orgUnitId =:orgUnitId"
          + " AND formulaMaster.parameterId =:parameterId"
          + " AND lstFormulaDetails.isDeleted = 'N'"),
    })


@Entity
@Table(name = "m_formula_master", schema = "lab")
@SequenceGenerator(name = "m_seq_formula_master", sequenceName = "lab.m_seq_formula_master", allocationSize = 1)
public class FormulaMaster {

  @Id
  @Column(name = "formula_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_formula_master")
  private int formulaId;

  @Column(name = "parameter_id")
  private Integer parameterId;
  
  @Column(name = "org_id")
  private Integer orgId;
  
  @Column(name = "org_unit_id")
  private Integer orgUnitId;

  @Column(name = "formula")
  private String formula;

  @Column(name = "formula_latex")
  private String formulaLatex;

  @Column(name = "status")
  private char status;

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
  
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "formulaMaster")
  private List<FormulaDetails> listFormulaDetails;

  public int getFormulaId() {
    return formulaId;
  }

  public void setFormulaId(int formulaId) {
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

  public ParameterMaster getParameterMaster() {
    return parameterMaster;
  }

  public void setParameterMaster(ParameterMaster parameterMaster) {
    this.parameterMaster = parameterMaster;
  }

  public List<FormulaDetails> getListFormulaDetails() {
    return listFormulaDetails;
  }

  public void setListFormulaDetails(List<FormulaDetails> listFormulaDetails) {
    this.listFormulaDetails = listFormulaDetails;
  }
  
  
  
}
