package com.param.entity.lis.transaction;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.param.entity.model.master.OrderDetail;
import com.param.entity.model.master.User;
import com.param.entity.lis.common.LocalTimeConverter;
import com.param.entity.lis.unit.TestMaster;

@Entity
@Table(name = "t_sample_audit_trail", schema = "lab")
public class SampleAuditTrailMaster {
  
  @Id
  @Column(name = "t_sample_audit_trail_pkey")
  private int tSampleAuditTrailPkey;
  
  @Column(name = "org_id")
  private Integer orgId;
  
  @Column(name = "org_unit_id")
  private Integer orgUnitId;
  
  @Column(name = "order_details_id")
  private Integer orderDetailsId;

  @Column(name = "test_id")
  private Integer testId;
  
  @Column(name = "sample_dtls_id")
  private Integer labSampleDtlsId;
  
  @Column(name = "test_status_id")
  private Integer testStatusId;
 
  @Column(name = "created_by")
  private Integer createdBy ;
  
  @Column(name = "created_date")
  @Convert(converter = LocalTimeConverter.class)
  private Long createdDate ;
  
  @ManyToOne
  @JoinColumn(name = "order_details_id", insertable = false, nullable = false, updatable = false)
  private OrderDetail orderDetail;
  
  @ManyToOne
  @JoinColumn(name = "test_id", insertable = false, nullable = false, updatable = false)
  private TestMaster testMaster;
  
  @ManyToOne
  @JoinColumn(name = "test_status_id", insertable = false, nullable = false, updatable = false)
  private SampleStatusMaster sampleStatusMaster;
  
  @ManyToOne
  @JoinColumn(name = "created_by", insertable = false, nullable = false, updatable = false)
  private User user;

  public int gettSampleAuditTrailPkey() {
    return tSampleAuditTrailPkey;
  }

  public void settSampleAuditTrailPkey(int tSampleAuditTrailPkey) {
    this.tSampleAuditTrailPkey = tSampleAuditTrailPkey;
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

  public Integer getOrderDetailsId() {
    return orderDetailsId;
  }

  public void setOrderDetailsId(Integer orderDetailsId) {
    this.orderDetailsId = orderDetailsId;
  }

  public Integer getTestId() {
    return testId;
  }

  public void setTestId(Integer testId) {
    this.testId = testId;
  }

  public Integer getLabSampleDtlsId() {
    return labSampleDtlsId;
  }

  public void setLabSampleDtlsId(Integer labSampleDtlsId) {
    this.labSampleDtlsId = labSampleDtlsId;
  }

  public Integer getTestStatusId() {
    return testStatusId;
  }

  public void setTestStatusId(Integer testStatusId) {
    this.testStatusId = testStatusId;
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

  public OrderDetail getOrderDetail() {
    return orderDetail;
  }

  public void setOrderDetail(OrderDetail orderDetail) {
    this.orderDetail = orderDetail;
  }

  public TestMaster getTestMaster() {
    return testMaster;
  }

  public void setTestMaster(TestMaster testMaster) {
    this.testMaster = testMaster;
  }

  public SampleStatusMaster getSampleStatusMaster() {
    return sampleStatusMaster;
  }

  public void setSampleStatusMaster(SampleStatusMaster sampleStatusMaster) {
    this.sampleStatusMaster = sampleStatusMaster;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
  
}
