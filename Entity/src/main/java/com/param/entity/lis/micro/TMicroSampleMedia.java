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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.common.LocalTimeConverter;
import com.param.entity.lis.global.MediaMaster;


@NamedQueries({
  @NamedQuery(name = "GET_INCUBATION_MEDIA", query = "SELECT tMicroSampleMedia.tSampleMediaId as tSampleMediaId,"
          + " tMicroSampleMedia.labSampleDtlsId as labSampleDtlsId,"
          + " tMicroSampleMedia.orgId as orgId,"
          + " tMicroSampleMedia.orgUnitId as orgUnitId,"
          + " tMicroSampleMedia.orgId as orgId,"
          + " tMicroSampleMedia.orgId as orgId,"
          + " tMicroSampleMedia.mediaId as mediaId,"
          + " tMicroSampleMedia.remark as remark,"
          + " mediamst.desc as desc,"
          + " tMicroSampleMedia.createdBy as createdBy,"
          + " tMicroSampleMedia.createdDate as createdDate,"
          + " tMicroSampleMedia.updatedBy as updatedBy,"
          + " tMicroSampleMedia.updatedDate as updatedDate"
          + " FROM TMicroSampleMedia tMicroSampleMedia "
          + " INNER JOIN tMicroSampleMedia.mediaMaster mediamst "
          + " WHERE tMicroSampleMedia.orgId = :orgId "
          + " AND tMicroSampleMedia.orgUnitId= :orgUnitId"
          + " AND tMicroSampleMedia.labSampleDtlsId= :labSampleDtlsId"
          + " AND tMicroSampleMedia.isDeleted= 'N'"
          + " AND mediamst.status= 'A'"),

})

@Entity
@Table(name = "t_micro_sample_media", schema = "lab")
@SequenceGenerator(name = "t_seq_micro_sample_media", sequenceName = "lab.t_seq_micro_sample_media",
    allocationSize = 1)
public class TMicroSampleMedia {


  @Id
  @Column(name = "t_sample_media_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_seq_micro_sample_media")
  private int tSampleMediaId;

  @Column(name = "lab_sample_dtls_id")
  private Integer labSampleDtlsId;

  @Column(name = "org_id")
  private Integer orgId;

  @Column(name = "org_unit_id")
  private Integer orgUnitId;

  @Column(name = "media_id")
  private Integer mediaId;
  
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
  @JoinColumn(name = "media_id", insertable = false, nullable = false, updatable = false)
  private MediaMaster mediaMaster;

  public int gettSampleMediaId() {
    return tSampleMediaId;
  }

  public void settSampleMediaId(int tSampleMediaId) {
    this.tSampleMediaId = tSampleMediaId;
  }

  public Integer getLabSampleDtlsId() {
    return labSampleDtlsId;
  }

  public void setLabSampleDtlsId(Integer labSampleDtlsId) {
    this.labSampleDtlsId = labSampleDtlsId;
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

  public Integer getMediaId() {
    return mediaId;
  }

  public void setMediaId(Integer mediaId) {
    this.mediaId = mediaId;
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

  public MediaMaster getMediaMaster() {
    return mediaMaster;
  }

  public void setMediaMaster(MediaMaster mediaMaster) {
    this.mediaMaster = mediaMaster;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

}
