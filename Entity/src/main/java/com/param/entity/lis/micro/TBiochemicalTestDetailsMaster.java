package com.param.entity.lis.micro;

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
import com.param.entity.lis.transaction.LabSampleDetailsMaster;
import com.param.entity.lis.unit.MicrobiologyTestMaster;

@Entity
@Table(name = "t_biochemical_test_details", schema = "lab")
@SequenceGenerator(name = "t_seq_biochemical_test_details",
    sequenceName = "lab.t_seq_biochemical_test_details", allocationSize = 1)
public class TBiochemicalTestDetailsMaster {

  @Id
  @Column(name = "t_biochemical_test_details_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_seq_biochemical_test_details")
  private int tBiochemicalTestDetailsId;


  @Column(name = "micro_test_id")
  private Integer microTestId;

  @Column(name = "org_id")
  private Integer orgId;

  @Column(name = "org_unit_id")
  private Integer orgUnitId;

  @Column(name = "biochem_test_id")
  private Integer biochemTestId;

  @Column(name = "result")
  private String result;

  @Column(name = "remark")
  private String remark;

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

  @Column(name = "is_deleted")
  private Character isDeleted;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "biochem_test_id", insertable = false, nullable = false, updatable = false)
  private TBiochemicalTestMaster tBiochemicalTestMaster;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "biochem_test_id", insertable = false, nullable = false, updatable = false)
  private MicrobiologyTestMaster microbiologyTestMaster;


  public int gettBiochemicalTestDetailsId() {
    return tBiochemicalTestDetailsId;
  }

  public void settBiochemicalTestDetailsId(int tBiochemicalTestDetailsId) {
    this.tBiochemicalTestDetailsId = tBiochemicalTestDetailsId;
  }

  public Integer getMicroTestId() {
    return microTestId;
  }

  public void setMicroTestId(Integer microTestId) {
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

  public Integer getBiochemTestId() {
    return biochemTestId;
  }

  public void setBiochemTestId(Integer biochemTestId) {
    this.biochemTestId = biochemTestId;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
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

  public Character getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(Character isDeleted) {
    this.isDeleted = (isDeleted == '\u0000') ? 'N' : isDeleted;
  }

  public TBiochemicalTestMaster gettBiochemicalTestMaster() {
    return tBiochemicalTestMaster;
  }

  public void settBiochemicalTestMaster(TBiochemicalTestMaster tBiochemicalTestMaster) {
    this.tBiochemicalTestMaster = tBiochemicalTestMaster;
  }

  public MicrobiologyTestMaster getMicrobiologyTestMaster() {
    return microbiologyTestMaster;
  }

  public void setMicrobiologyTestMaster(MicrobiologyTestMaster microbiologyTestMaster) {
    this.microbiologyTestMaster = microbiologyTestMaster;
  }


}
