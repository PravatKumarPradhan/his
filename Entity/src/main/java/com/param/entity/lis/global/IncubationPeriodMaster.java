package com.param.entity.lis.global;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.common.LocalTimeConverter;

@NamedQueries({

    @NamedQuery(name = "GET_REPORT_TYPE_BY_INCUBATION_PERIOD_ID",
        query = "SELECT incubationPeriodMaster.incubationPeriodId as incubationPeriodId,"
            + " incubationPeriodMaster.incubationPeriodTime as incubationPeriodTime,"
            + " incubationPeriodMaster.incubationPeriodCode as incubationPeriodCode,"
            + " incubationPeriodMaster.incubationValue as incubationValue,"
            + " incubationPeriodMaster.incubationTimeId as incubationTimeId,"
            + " incubationPeriodMaster.incubationInHours as incubationInHours,"
            + " incubationPeriodMaster.status as status,"
            + " incubationPeriodMaster.orgId as orgId,"
            + " incubationPeriodMaster.createdBy as createdBy,"
            + " incubationPeriodMaster.createdDate as createdDate,"
            + " incubationPeriodMaster.updatedBy as updatedBy,"
            + " incubationPeriodMaster.updatedDate as updatedDate"
            + " FROM IncubationPeriodMaster incubationPeriodMaster "
            + " WHERE incubationPeriodMaster.incubationPeriodId = :incubationPeriodId "
            + " AND incubationPeriodMaster.orgId= :orgId"),

    @NamedQuery(name = "GET_REPORT_TYPE_BY_INCUBATION_PERIOD_CODE",
        query = "SELECT incubationPeriodMaster.incubationPeriodId as incubationPeriodId,"
            + " incubationPeriodMaster.incubationPeriodTime as incubationPeriodTime,"
            + " incubationPeriodMaster.incubationPeriodCode as incubationPeriodCode,"
            + " incubationPeriodMaster.incubationInHours as incubationInHours,"
            + " incubationPeriodMaster.status as status,"
            + " incubationPeriodMaster.createdBy as createdBy,"
            + " incubationPeriodMaster.createdDate as createdDate,"
            + " incubationPeriodMaster.updatedBy as updatedBy,"
            + " incubationPeriodMaster.updatedDate as updatedDate"
            + " FROM IncubationPeriodMaster incubationPeriodMaster"
            + " WHERE incubationPeriodMaster.orgId=:orgId "
            + " AND lower(incubationPeriodMaster.incubationPeriodCode) = lower(:incubationPeriodCode)"),


    @NamedQuery(name = "UPDATE_GET_REPORT_TYPE_BY_INCUBATION_PERIOD_CODE",
        query = "SELECT incubationPeriodMaster.incubationPeriodId AS incubationPeriodId,"
            + " incubationPeriodMaster.incubationPeriodTime AS incubationPeriodTime,"
            + " incubationPeriodMaster.incubationPeriodCode as incubationPeriodCode,"
            + " incubationPeriodMaster.incubationInHours as incubationInHours,"
            + " incubationPeriodMaster.createdBy as createdBy,"
            + " incubationPeriodMaster.createdDate as createdDate,"
            + " incubationPeriodMaster.updatedBy as updatedBy,"
            + " incubationPeriodMaster.updatedDate as updatedDate"
            + " FROM IncubationPeriodMaster incubationPeriodMaster"
            + " WHERE incubationPeriodMaster.orgId=:orgId "
            + " AND lower(incubationPeriodMaster.incubationPeriodCode) = lower(:incubationPeriodCode)"
            + " AND incubationPeriodMaster.incubationPeriodId <> :incubationPeriodId"),

    /*
     * @NamedQuery(name = "GET_PAGINATED_INCUBATION_PERIOD_MASTER_LIST", query =
     * "SELECT incubationPeriodMaster.incubationPeriodId as incubationPeriodId," +
     * " incubationPeriodMaster.incubationPeriodCode as incubationPeriodCode," +
     * " incubationPeriodMaster.hourId as hourId," +
     * " incubationPeriodMaster.dayMasteId as dayMasteId," +
     * " incubationPeriodMaster.incubationPeriodStatus as incubationPeriodStatus," +
     * " incubationPeriodMaster.createdBy as createdBy," +
     * " incubationPeriodMaster.createdDate as createdDate," +
     * " incubationPeriodMaster.updatedBy as updatedBy," +
     * " incubationPeriodMaster.updatedDate as updatedDate " +
     * " FROM IncubationPeriodMaster incubationPeriodMaster" +
     * " WHERE incubationPeriodMaster.orgId = :orgId" + " ORDER BY incubationPeriodId DESC")
     */



    @NamedQuery(name = "GET_INCUBATION_PERIOD_LIST",
        query = "SELECT incubationPeriodMaster.incubationPeriodId AS id,"
            + " incubationPeriodMaster.incubationPeriodTime AS name , "
            + " cast(incubationPeriodMaster.incubationInHours as string) AS label  "
            + " FROM IncubationPeriodMaster incubationPeriodMaster"
            + " WHERE incubationPeriodMaster.orgId=:orgId "
            + " AND incubationPeriodMaster.status ='A' ")

})

@NamedNativeQueries({
    @NamedNativeQuery(name = "GET_TOTAL_INCUBATION_PERIOD_RECORD",
        query = "select count(*) from lab.m_incubation_period_master where " + "org_id = :orgId "),


    @NamedNativeQuery(name = "GET_PAGINATED_INCUBATION_PERIOD_MASTER_LIST",
        query = " SELECT m_icubperiod.incubation_period_id as \"incubationPeriodId\","
            + " m_icubperiod.incubation_period_code as \"incubationPeriodCode\","
            + " m_icubperiod.incubation_period_time as \"incubationPeriodTime\","
            + " m_icubperiod.incubation_time_id as \"incubationTimeId\","
            + "  m_icubperiod.incubation_in_hours as \"incubationInHours\","
            + " m_icubperiod.status as status "
            + " FROM lab.m_incubation_period_master m_icubperiod "
            + " WHERE m_icubperiod.org_id = :orgId "
            + " ORDER BY m_icubperiod.incubation_period_id DESC ")})


@Entity
@Table(name = "m_incubation_period_master", schema = "lab")
@SequenceGenerator(name = "m_seq_incubation_period_master",
    sequenceName = "lab.m_seq_incubation_period_master", allocationSize = 1)
public class IncubationPeriodMaster {
  @Id
  @Column(name = "incubation_period_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_incubation_period_master")
  private int incubationPeriodId;

  @Column(name = "incubation_period_code")
  private String incubationPeriodCode;

  @Column(name = "incubation_period_time")
  private String incubationPeriodTime;

  @Column(name = "incubation_value")
  private Integer incubationValue;

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

  @Column(name = "org_id")
  private Integer orgId;

  @Column(name = "incubation_time_id")
  private Integer incubationTimeId;

  @Column(name = "status")
  private Character status;

  @Column(name = "incubation_in_hours")
  private Integer incubationInHours;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "incubation_time_id", insertable = false, nullable = false, updatable = false)
  private IncubationTimeMaster incubationTimeMaster;

  public int getIncubationPeriodId() {
    return incubationPeriodId;
  }

  public void setIncubationPeriodId(int incubationPeriodId) {
    this.incubationPeriodId = incubationPeriodId;
  }


  public String getIncubationPeriodTime() {
    return incubationPeriodTime;
  }

  public void setIncubationPeriodTime(String incubationPeriodTime) {
    this.incubationPeriodTime = incubationPeriodTime;
  }

  public Integer getIncubationValue() {
    return incubationValue;
  }

  public void setIncubationValue(Integer incubationValue) {
    this.incubationValue = incubationValue;
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

  public Integer getOrgId() {
    return orgId;
  }

  public void setOrgId(Integer orgId) {
    this.orgId = orgId;
  }

  public Integer getIncubationTimeId() {
    return incubationTimeId;
  }

  public void setIncubationTimeId(Integer incubationTimeId) {
    this.incubationTimeId = incubationTimeId;
  }

  public Character getStatus() {
    return status;
  }

  public void setStatus(Character status) {
    this.status = status;
  }

  public IncubationTimeMaster getIncubationTimeMaster() {
    return incubationTimeMaster;
  }

  public void setIncubationTimeMaster(IncubationTimeMaster incubationTimeMaster) {
    this.incubationTimeMaster = incubationTimeMaster;
  }

  public String getIncubationPeriodCode() {
    return incubationPeriodCode;
  }

  public void setIncubationPeriodCode(String incubationPeriodCode) {
    this.incubationPeriodCode = incubationPeriodCode;
  }

  public Integer getIncubationInHours() {
    return incubationInHours;
  }

  public void setIncubationInHours(Integer incubationInHours) {
    this.incubationInHours = incubationInHours;
  }



}
