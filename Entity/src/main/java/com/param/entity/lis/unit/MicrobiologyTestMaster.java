package com.param.entity.lis.unit;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.common.LocalTimeConverter;

@Entity
@Table(name = "m_microbiology_test_master", schema = "lab")
@SequenceGenerator(name = "m_seq_microbiology_test_master", sequenceName = "lab.m_seq_microbiology_test_master",
    allocationSize = 1)
public class MicrobiologyTestMaster {
  
  @Id
  @Column(name = "micro_test_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_microbiology_test_master")
  private int microTestId;

  @Column(name = "micro_test_code")
  private String microTestCode;
  
  @Column(name = "micro_test_desc")
  private String microTestDesc;
  
  @Column(name = "org_id")
  private Integer orgId;

  @Column(name = "org_unit_id")
  private Integer orgUnitId;

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
  private Character status;

  public int getMicroTestId() {
    return microTestId;
  }

  public void setMicroTestId(int microTestId) {
    this.microTestId = microTestId;
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


  public Character getStatus() {
    return status;
  }

  public void setStatus(Character status) {
    this.status = (status == '\u0000') ? 'N' : status;
  }

  public String getMicroTestCode() {
    return microTestCode;
  }

  public void setMicroTestCode(String microTestCode) {
    this.microTestCode = microTestCode;
  }

  public String getMicroTestDesc() {
    return microTestDesc;
  }

  public void setMicroTestDesc(String microTestDesc) {
    this.microTestDesc = microTestDesc;
  }
  
  

}
