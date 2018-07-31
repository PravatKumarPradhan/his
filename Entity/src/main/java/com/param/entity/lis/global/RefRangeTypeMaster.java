package com.param.entity.lis.global;

import javax.persistence.Column;


import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.common.LocalTimeConverter;

@NamedQueries({
  
  @NamedQuery(name="GET_REF_RENGE_TYPE_LIST",query= " SELECT  "
      +"  refRangeTypeMaster.refrangeTypeId AS id,  "
      +"  refRangeTypeMaster.refrangeTypeName AS name  "
      +"FROM  "
      +"  RefRangeTypeMaster refRangeTypeMaster  "
      +"WHERE  "
      +"  refRangeTypeMaster.orgUnitId = :orgUnitId  "
      +"  AND refRangeTypeMaster.orgId = :orgId  "
      +"  AND refRangeTypeMaster.status = 'A'  ")
})

@Entity
@Table(name = "m_refrange_type_master", schema = "lab")
@SequenceGenerator(name = "m_seq_refrange_type_master", sequenceName = "lab.m_seq_refrange_type_master", allocationSize = 1)
public class RefRangeTypeMaster {
  
  @Id
  @Column(name = "refrange_type_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_refrange_type_master")
  private int refrangeTypeId;
  
  @Column(name = "refrange_type_name")
  private String refrangeTypeName;
  
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

  public int getRefrangeTypeId() {
    return refrangeTypeId;
  }

  public void setRefrangeTypeId(int refrangeTypeId) {
    this.refrangeTypeId = refrangeTypeId;
  }

  public String getRefrangeTypeName() {
    return refrangeTypeName;
  }

  public void setRefrangeTypeName(String refrangeTypeName) {
    this.refrangeTypeName = refrangeTypeName;
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
