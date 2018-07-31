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
  
  @NamedQuery(name="GET_TEXTUAL_RANGE_LIST",query= " SELECT  "
      +"  textualRangeMaster.textualRangeId AS id,  "
      +"  textualRangeMaster.textualRangeName AS name  "
      +"FROM  "
      +"  TextualRangeMaster textualRangeMaster  "
      +"WHERE  "
      +"  textualRangeMaster.orgUnitId = :orgUnitId  "
      +"  AND textualRangeMaster.orgId = :orgId  "
      +"  AND textualRangeMaster.status = 'A'  ")
})


@Entity
@Table(name = "m_textual_range_master", schema = "lab")
@SequenceGenerator(name = "m_seq_textual_range_master", sequenceName = "lab.m_seq_textual_range_master", allocationSize = 1)
public class TextualRangeMaster {
  
  @Id
  @Column(name = "textual_range_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_textual_range_master")
  private int textualRangeId;
  
  @Column(name = "textual_range_code")
  private String textualRangeCode;
  
  @Column(name = "textual_range_name")
  private String textualRangeName;
  
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

  public int getTextualRangeId() {
    return textualRangeId;
  }

  public void setTextualRangeId(int textualRangeId) {
    this.textualRangeId = textualRangeId;
  }

  public String getTextualRangeCode() {
    return textualRangeCode;
  }

  public void setTextualRangeCode(String textualRangeCode) {
    this.textualRangeCode = textualRangeCode;
  }

  public String getTextualRangeName() {
    return textualRangeName;
  }

  public void setTextualRangeName(String textualRangeName) {
    this.textualRangeName = textualRangeName;
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
