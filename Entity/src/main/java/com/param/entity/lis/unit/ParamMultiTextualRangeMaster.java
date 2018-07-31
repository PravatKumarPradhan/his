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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.common.LocalTimeConverter;
import com.param.entity.lis.global.AgeGroupMaster;
import com.param.global.model.GenderMaster;


@NamedQueries({

  @NamedQuery(name = "GET_MULTI_TEXTUAL_VALUE_BY_PARAMETER_ID", query = " SELECT  "
      +"  paramMultiTextualRangeMaster.paramMultiTextualRangeId AS paramMultiTextualRangeId,  "
      +"  paramMultiTextualRangeMaster.parameterId AS parameterId,  "
      +"  genderMst.desc AS genderName,  "
      +"  ageGroupMst.ageTypeGrpName AS ageGroupName,  "
      +"  ageGroupMst.ageFromDay AS ageFromDay,  "
      +"  ageGroupMst.ageToday AS ageToDay,  "
      +"  paramMultiTextualRangeMaster.multitextaulRange AS multitextaulRange,  "
      +"  paramMultiTextualRangeMaster.ageGroupId AS ageGroupId,  "
      +"  paramMultiTextualRangeMaster.genderId AS genderId,  "
      +"  paramMultiTextualRangeMaster.remark AS remark,  "
      +"  paramMultiTextualRangeMaster.status AS status,  "
      +"  paramMultiTextualRangeMaster.createdBy AS createdBy,  "
      +"  paramMultiTextualRangeMaster.createdDate AS createdDate,  "
      +"  paramMultiTextualRangeMaster.updatedBy AS updatedBy,  "
      +"  paramMultiTextualRangeMaster.updatedDate AS updatedDate,  "
      +"  paramMultiTextualRangeMaster.orgId AS orgId,  "
      +"  paramMultiTextualRangeMaster.orgUnitId AS orgUnitId,  "
      +"  paramMultiTextualRangeMaster.isDeleted AS isDeleted  "
      +"FROM  "
      +"  ParamMultiTextualRangeMaster paramMultiTextualRangeMaster  "
      +"  LEFT JOIN paramMultiTextualRangeMaster.genderMaster genderMst  "
      +"  LEFT JOIN paramMultiTextualRangeMaster.ageGroupMaster ageGroupMst  "
      +"WHERE  "
      +"  paramMultiTextualRangeMaster.parameterId = :parameterId  "
      +"  AND paramMultiTextualRangeMaster.orgId = :orgId  "
      +"  AND paramMultiTextualRangeMaster.orgUnitId = :orgUnitId  "
      +"  AND paramMultiTextualRangeMaster.isDeleted = 'N'  "),
  
  @NamedQuery(name = "UPDATE_MULTIVALUED_TEXTUAL_STATUS", query ="UPDATE ParamMultiTextualRangeMaster SET status =:status  "
      +"WHERE parameterId = :parameterId  " )

})


@Entity
@Table(name = "m_param_multitextual_range", schema = "lab")
@SequenceGenerator(name = "m_seq_param_multitextual_range",
    sequenceName = "lab.m_seq_param_multitextual_range", allocationSize = 1)
public class ParamMultiTextualRangeMaster {

  

  @Id
  @Column(name = "param_multitextual_range_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_param_multitextual_range")
  private int paramMultiTextualRangeId;

  @Column(name = "parameter_id")
  private int parameterId;

  @Column(name = "multitextaul_range")
  private String multitextaulRange;

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

  @Column(name = "status")
  private char status;

  @Column(name = "org_id")
  private Integer orgId;

  @Column(name = "org_unit_id")
  private Integer orgUnitId;

  @Column(name = "is_deleted")
  private char isDeleted;

  @Column(name = "age_group_id")
  private Integer ageGroupId;

  @Column(name = "remark")
  private String remark;
  
  @Column(name = "gender_id")
  private Integer genderId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "age_group_id", insertable = false, nullable = false, updatable = false)
  private AgeGroupMaster ageGroupMaster;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parameter_id", insertable = false, nullable = false, updatable = false)
  private ParameterMaster parameterMaster;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "gender_id", insertable = false, nullable = false, updatable = false)
  private GenderMaster genderMaster;

  public int getParamMultiTextualRangeId() {
    return paramMultiTextualRangeId;
  }

  public void setParamMultiTextualRangeId(int paramMultiTextualRangeId) {
    this.paramMultiTextualRangeId = paramMultiTextualRangeId;
  }

  public int getParameterId() {
    return parameterId;
  }

  public void setParameterId(int parameterId) {
    this.parameterId = parameterId;
  }

  public String getMultitextaulRange() {
    return multitextaulRange;
  }

  public void setMultitextaulRange(String multitextaulRange) {
    this.multitextaulRange = multitextaulRange;
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

  public Integer getGenderId() {
    return genderId;
  }

  public void setGenderId(Integer genderId) {
    this.genderId = genderId;
  }

  public GenderMaster getGenderMaster() {
    return genderMaster;
  }

  public void setGenderMaster(GenderMaster genderMaster) {
    this.genderMaster = genderMaster;
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
    this.isDeleted = (isDeleted == '\u0000') ? 'I' : isDeleted;
  }

  public ParameterMaster getParameterMaster() {
    return parameterMaster;
  }

  public void setParameterMaster(ParameterMaster parameterMaster) {
    this.parameterMaster = parameterMaster;
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

  public AgeGroupMaster getAgeGroupMaster() {
    return ageGroupMaster;
  }

  public void setAgeGroupMaster(AgeGroupMaster ageGroupMaster) {
    this.ageGroupMaster = ageGroupMaster;
  }



}
