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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.global.StainigMaster;
import com.param.entity.lis.transaction.LabSampleDetailsMaster;
import com.param.entity.lis.common.LocalTimeConverter;


@NamedQueries({
  
  @NamedQuery(name="UPDATE_SLIDE_CREATED_STATUS",query= " UPDATE "
      +"    TRestainingReqSubDetailsMaster tRestainingReqSubDetailsMaster "
      +" SET tRestainingReqSubDetailsMaster.isSlideCreated = 'Y' "
      +" WHERE "
      +"  tRestainingReqSubDetailsMaster.tRestainingSubDetailId = :tRestainingSubDetailId "
      +"  AND tRestainingReqSubDetailsMaster.isDeleted = 'N' "
      +"  AND tRestainingReqSubDetailsMaster.orgId = :orgId "
      +"  AND tRestainingReqSubDetailsMaster.orgUnitId = :orgUnitId ")})

@Entity
@Table(name = "t_restaining_req_sub_details", schema = "lab")
@SequenceGenerator(name = "t_seq_restaining_req_sub_details",
    sequenceName = "lab.t_seq_restaining_req_sub_details", allocationSize = 1)
public class TRestainingReqSubDetailsMaster {

  @Id
  @Column(name = "t_restaining_sub_detail_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
      generator = "t_seq_restaining_req_sub_details")
  private int tRestainingSubDetailId;

  @Column(name = "org_id")
  private Integer orgId;

  @Column(name = "org_unit_id")
  private Integer orgUnitId;

  @Column(name = "t_restaining_detail_id")
  private Integer tRestainingDetailId;


  @Column(name = "t_slide_id")
  private Integer tSlideId;

  @Column(name = "lab_sample_dtls_id")
  private Integer labSampleDtlsId;

  @Column(name = "staining_id")
  private Integer stainingId;

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

  @Column(name = "is_new")
  private Character isNew;
  
  @Column(name = "is_slide_created")
  private Character isSlideCreated;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "t_restaining_detail_id", insertable = false, nullable = false,
      updatable = false)
  private TRestainingRequestDetailsMaster tRestainingRequestDetailsMaster;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "lab_sample_dtls_id", insertable = false, nullable = false, updatable = false)
  private LabSampleDetailsMaster labSampleDetailsMaster;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "staining_id", insertable = false, nullable = false, updatable = false)
  private StainigMaster stainigMaster;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "t_slide_id", insertable = false, nullable = false, updatable = false)
  private TSlideMaster tSlideMaster;

  public int gettRestainingSubDetailId() {
    return tRestainingSubDetailId;
  }

  public void settRestainingSubDetailId(int tRestainingSubDetailId) {
    this.tRestainingSubDetailId = tRestainingSubDetailId;
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

  public Integer gettRestainingDetailId() {
    return tRestainingDetailId;
  }

  public void settRestainingDetailId(Integer tRestainingDetailId) {
    this.tRestainingDetailId = tRestainingDetailId;
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

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
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

  public TRestainingRequestDetailsMaster gettRestainingRequestDetailsMaster() {
    return tRestainingRequestDetailsMaster;
  }

  public void settRestainingRequestDetailsMaster(
      TRestainingRequestDetailsMaster tRestainingRequestDetailsMaster) {
    this.tRestainingRequestDetailsMaster = tRestainingRequestDetailsMaster;
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

  public Character getIsNew() {
    return isNew;
  }

  public void setIsNew(Character isNew) {
    this.isNew = isNew;
  }

  public Character getIsSlideCreated() {
    return isSlideCreated;
  }

  public void setIsSlideCreated(Character isSlideCreated) {
    this.isSlideCreated = isSlideCreated;
  }



}
