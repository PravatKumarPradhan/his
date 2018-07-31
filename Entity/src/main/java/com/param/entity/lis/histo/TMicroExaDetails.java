package com.param.entity.lis.histo;

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
import com.param.entity.lis.global.StainigMaster;
import com.param.entity.lis.transaction.LabSampleDetailsMaster;
import com.param.entity.lis.common.LocalTimeConverter;

@Entity
@Table(name = "t_micro_exa_details", schema = "lab")
@SequenceGenerator(name = "t_seq_micro_exa_details", sequenceName = "lab.t_seq_micro_exa_details",
    allocationSize = 1)
public class TMicroExaDetails {

  @Id
  @Column(name = "t_micro_exa_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_seq_micro_exa_details")
  private int tMicroExaId;

  @Column(name = "org_id")
  private Integer orgId;

  @Column(name = "org_unit_id")
  private Integer orgUnitId;

  @Column(name = "t_slide_id")
  private Integer tSlideId;

  @Column(name = "lab_sample_dtls_id")
  private Integer labSampleDtlsId;

  @Column(name = "staining_id")
  private Integer stainingId;

  @Column(name = "is_complete")
  private Character isComplete;

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

  @Column(name = "capture_note")
  private String captureNote;

  @Column(name = "send_for_storage")
  private Character sendForStorage;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "lab_sample_dtls_id", insertable = false, nullable = false, updatable = false)
  private LabSampleDetailsMaster labSampleDetailsMaster;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "staining_id", insertable = false, nullable = false, updatable = false)
  private StainigMaster stainigMaster;

  @ManyToOne
  @JoinColumn(name = "t_slide_id", insertable = false, nullable = false, updatable = false)
  private TSlideMaster tSlideMaster;

  public int gettMicroExaId() {
    return tMicroExaId;
  }

  public void settMicroExaId(int tMicroExaId) {
    this.tMicroExaId = tMicroExaId;
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

  public Integer gettSlideId() {
    return tSlideId;
  }

  public void settSlideId(Integer tSlideId) {
    this.tSlideId = tSlideId;
  }

  public Integer getLabSampleDtlsId() {
    return labSampleDtlsId;
  }

  public void setLabSampleDtlsId(Integer labSampleDtlsId) {
    this.labSampleDtlsId = labSampleDtlsId;
  }

  public Integer getStainingId() {
    return stainingId;
  }

  public void setStainingId(Integer stainingId) {
    this.stainingId = stainingId;
  }


  public Character getIsComplete() {
    return isComplete;
  }

  public void setIsComplete(Character isComplete) {
    this.isComplete = isComplete;
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
    this.isDeleted = isDeleted;
  }

  public LabSampleDetailsMaster getLabSampleDetailsMaster() {
    return labSampleDetailsMaster;
  }

  public void setLabSampleDetailsMaster(LabSampleDetailsMaster labSampleDetailsMaster) {
    this.labSampleDetailsMaster = labSampleDetailsMaster;
  }

  public StainigMaster getStainigMaster() {
    return stainigMaster;
  }

  public void setStainigMaster(StainigMaster stainigMaster) {
    this.stainigMaster = stainigMaster;
  }

  public TSlideMaster gettSlideMaster() {
    return tSlideMaster;
  }

  public void settSlideMaster(TSlideMaster tSlideMaster) {
    this.tSlideMaster = tSlideMaster;
  }

  public Integer getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Integer createdBy) {
    this.createdBy = createdBy;
  }

  public String getCaptureNote() {
    return captureNote;
  }

  public void setCaptureNote(String captureNote) {
    this.captureNote = captureNote;
  }

  public Character getSendForStorage() {
    return sendForStorage;
  }

  public void setSendForStorage(Character sendForStorage) {
    this.sendForStorage = sendForStorage;
  }


}
