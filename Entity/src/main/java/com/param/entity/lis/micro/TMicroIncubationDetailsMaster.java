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

@Entity
@Table(name = "t_micro_incubation_details", schema = "lab")
@SequenceGenerator(name = "t_seq_micro_incubation_details",
    sequenceName = "lab.t_seq_micro_incubation_details", allocationSize = 1)
public class TMicroIncubationDetailsMaster {

  @Id
  @Column(name = "t_incubation_details_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_seq_micro_incubation_details")
  private int tIncubationDetailsId;

  @Column(name = "t_incubation_id")
  private Integer tIncubationId;

  @Column(name = "incubation_period_id")
  private Integer incubationPeriodId;

  @Column(name = "org_id")
  private Integer orgId;

  @Column(name = "org_unit_id")
  private Integer orgUnitId;

  @Column(name = "incubation_start_time")
  @Convert(converter = LocalTimeConverter.class)
  private Long incubationStartTime;

  @Column(name = "incubation_due_time")
  @Convert(converter = LocalTimeConverter.class)
  private Long incubationDueTime;

  @Column(name = "incubation_stop_time")
  @Convert(converter = LocalTimeConverter.class)
  private Long incubationStopTime;

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

  @Column(name = "is_incubation_stop")
  private Character isIncubationStop;


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "t_incubation_id", insertable = false, nullable = false, updatable = false)
  private TMicroIncubationMaster tMicroIncubationMaster;

  public int gettIncubationDetailsId() {
    return tIncubationDetailsId;
  }

  public void settIncubationDetailsId(int tIncubationDetailsId) {
    this.tIncubationDetailsId = tIncubationDetailsId;
  }

  public Integer gettIncubationId() {
    return tIncubationId;
  }

  public void settIncubationId(Integer tIncubationId) {
    this.tIncubationId = tIncubationId;
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

  public Long getIncubationStartTime() {
    return incubationStartTime;
  }

  public void setIncubationStartTime(Long incubationStartTime) {
    this.incubationStartTime = incubationStartTime;
  }

  public Long getIncubationDueTime() {
    return incubationDueTime;
  }

  public void setIncubationDueTime(Long incubationDueTime) {
    this.incubationDueTime = incubationDueTime;
  }

  public Long getIncubationStopTime() {
    return incubationStopTime;
  }

  public void setIncubationStopTime(Long incubationStopTime) {
    this.incubationStopTime = incubationStopTime;
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

  public TMicroIncubationMaster gettMicroIncubationMaster() {
    return tMicroIncubationMaster;
  }

  public void settMicroIncubationMaster(TMicroIncubationMaster tMicroIncubationMaster) {
    this.tMicroIncubationMaster = tMicroIncubationMaster;
  }

  public Character getIsIncubationStop() {
    return isIncubationStop;
  }

  public void setIsIncubationStop(Character isIncubationStop) {
    this.isIncubationStop = (isDeleted == '\u0000') ? 'N' : isIncubationStop;
  }

  public Integer getIncubationPeriodId() {
    return incubationPeriodId;
  }

  public void setIncubationPeriodId(Integer incubationPeriodId) {
    this.incubationPeriodId = incubationPeriodId;
  }

}
