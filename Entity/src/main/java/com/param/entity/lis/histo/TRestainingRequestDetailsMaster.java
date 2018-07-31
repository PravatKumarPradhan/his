package com.param.entity.lis.histo;

import java.util.List;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.transaction.LabSampleDetailsMaster;
import com.param.entity.lis.common.LocalTimeConverter;


@NamedQueries({
  
  
  @NamedQuery(name="RESTAIN_REUEST_DETAILS_AGAINST_SLIDE",query= " SELECT "
      +"    tRestainingRequestDetailsMst.tRestainingDetailId AS tRestainingDetailId, "
      +" lstTRestainingReqSubDetailsMaster.tRestainingSubDetailId AS tRestainingSubDetailId,"
      +" tSubSpecimanMst.subSpecimanName AS subSpecimanName, "
      +" tGrossMst.tGrossId AS tGrossId, "
      +" tGrossMst.grossNo AS grossNo, "
      +" tBlockMst.tBlockId AS tBlockId, "
      +" tBlockMst.blockNo AS blockNo, "
      +" tSlideMst.slideNo AS againstSlide, "
      +" tSlideMst.tSlideId AS tSlideId, "
      +" stainigMst.desc AS stainingName, "
      +" 1L AS noOfSlides, "
      +" lstTRestainingReqSubDetailsMaster.remark AS remark, "
      +" lstTRestainingReqSubDetailsMaster.isNew AS isNew "
      +"FROM "
      +" TRestainingRequestDetailsMaster tRestainingRequestDetailsMst "
      +"INNER JOIN tRestainingRequestDetailsMst.listTRestainingReqSubDetailsMaster lstTRestainingReqSubDetailsMaster  "
      +"INNER JOIN tRestainingRequestDetailsMst.tGrossMaster tGrossMst "
      +"INNER JOIN tRestainingRequestDetailsMst.tBlockMaster tBlockMst   "
      +"INNER JOIN lstTRestainingReqSubDetailsMaster.tSlideMaster tSlideMst   "
      +"INNER JOIN lstTRestainingReqSubDetailsMaster.stainigMaster stainigMst   "
      +"INNER JOIN tGrossMst.tSubSpecimanMaster tSubSpecimanMst "
      +"WHERE "
      +" tRestainingRequestDetailsMst.tRestainingReqId = :tRestainingReqId "
      +" AND lstTRestainingReqSubDetailsMaster.isNew = 'N' "
      +" AND tRestainingRequestDetailsMst.isDeleted = 'N' "
      +" AND lstTRestainingReqSubDetailsMaster.isDeleted = 'N' "
      +" AND lstTRestainingReqSubDetailsMaster.isSlideCreated = 'N' "
      +" AND tGrossMst.isDeleted = 'N' "
      +" AND tBlockMst.isDeleted = 'N' "
      +" AND stainigMst.status = 'A' "
      +" AND tRestainingRequestDetailsMst.orgId = :orgId "
      +" AND tRestainingRequestDetailsMst.orgUnitId = :orgUnitId "),
  
  
  @NamedQuery(name="RESTAIN_REUEST_DETAILS_AGAINST_NEW_SLIDE",query=  " SELECT "
      +" tBlockMst.tBlockId AS tBlockId, "
      +" tRestainingRequestDetailsMst.tRestainingDetailId AS tRestainingDetailId, "
      +" lstTRestainingReqSubDetailsMaster.tRestainingSubDetailId AS tRestainingSubDetailId,"
      +" tSubSpecimanMst.subSpecimanName AS subSpecimanName, "
      +" tGrossMst.tGrossId AS tGrossId, "
      +" tGrossMst.grossNo AS grossNo, "
      +" tBlockMst.tBlockId AS tBlockId, "
      +" tBlockMst.blockNo AS blockNo, "
      +" stainigMst.desc AS stainingName, "
      +" 1L AS noOfSlides, "
      +" lstTRestainingReqSubDetailsMaster.remark AS remark ,"
      +" lstTRestainingReqSubDetailsMaster.isNew AS isNew "
      +"FROM "
      +" TRestainingRequestDetailsMaster tRestainingRequestDetailsMst "
      +"INNER JOIN tRestainingRequestDetailsMst.listTRestainingReqSubDetailsMaster lstTRestainingReqSubDetailsMaster "
      +"INNER JOIN tRestainingRequestDetailsMst.tGrossMaster tGrossMst "
      +"INNER JOIN tRestainingRequestDetailsMst.tBlockMaster tBlockMst "
      +"INNER JOIN lstTRestainingReqSubDetailsMaster.stainigMaster stainigMst "
      +"INNER JOIN tGrossMst.tSubSpecimanMaster tSubSpecimanMst "
      +"WHERE "
      +" tRestainingRequestDetailsMst.tRestainingReqId = :tRestainingReqId "
      +" AND lstTRestainingReqSubDetailsMaster.isNew = 'Y' "
      +" AND lstTRestainingReqSubDetailsMaster.isDeleted = 'N' "
      +" AND lstTRestainingReqSubDetailsMaster.isSlideCreated = 'N' "
      +" AND tGrossMst.isDeleted = 'N' "
      +" AND tBlockMst.isDeleted = 'N' "
      +" AND stainigMst.status = 'A' "
      +" AND tRestainingRequestDetailsMst.orgId = :orgId "
      +" AND tRestainingRequestDetailsMst.orgUnitId = :orgUnitId "
      +"GROUP BY "
      +" tBlockMst.tBlockId, "
      +" tGrossMst.tGrossId, "
      +" tRestainingRequestDetailsMst.tRestainingDetailId, "
      +" lstTRestainingReqSubDetailsMaster.tRestainingSubDetailId, "
      +" tSubSpecimanMst.tSubSpecimanId, "
      +" stainigMst.stainigId "),
  
})



@Entity
@Table(name = "t_restaining_req_details", schema = "lab")
@SequenceGenerator(name = "t_seq_restaining_req_details",
    sequenceName = "lab.t_seq_restaining_req_details", allocationSize = 1)
public class TRestainingRequestDetailsMaster {

  @Id
  @Column(name = "t_restaining_detail_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_seq_restaining_req_details")
  private int tRestainingDetailId;

  @Column(name = "org_id")
  private Integer orgId;

  @Column(name = "org_unit_id")
  private Integer orgUnitId;

  @Column(name = "t_restaining_req_id")
  private Integer tRestainingReqId;

  @Column(name = "lab_sample_dtls_id")
  private Integer labSampleDtlsId;

  @Column(name = "t_gross_id")
  private Integer tGrossId;

  @Column(name = "t_block_id")
  private Integer tBlockId;

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
  @JoinColumn(name = "lab_sample_dtls_id", insertable = false, nullable = false, updatable = false)
  private LabSampleDetailsMaster labSampleDetailsMaster;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "t_restaining_req_id", insertable = false, nullable = false, updatable = false)
  private TRestainingRequestMaster tRestainingRequestMaster;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "tRestainingRequestDetailsMaster")
  private List<TRestainingReqSubDetailsMaster> listTRestainingReqSubDetailsMaster;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "t_gross_id", insertable = false, nullable = false, updatable = false)
  private TGrossMaster tGrossMaster;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "t_block_id", insertable = false, nullable = false, updatable = false)
  private TBlockMaster tBlockMaster;


  public int gettRestainingDetailId() {
    return tRestainingDetailId;
  }

  public void settRestainingDetailId(int tRestainingDetailId) {
    this.tRestainingDetailId = tRestainingDetailId;
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

  public Integer gettRestainingReqId() {
    return tRestainingReqId;
  }

  public void settRestainingReqId(Integer tRestainingReqId) {
    this.tRestainingReqId = tRestainingReqId;
  }

  public Integer getLabSampleDtlsId() {
    return labSampleDtlsId;
  }

  public void setLabSampleDtlsId(Integer labSampleDtlsId) {
    this.labSampleDtlsId = labSampleDtlsId;
  }

  public Integer gettGrossId() {
    return tGrossId;
  }

  public void settGrossId(Integer tGrossId) {
    this.tGrossId = tGrossId;
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

  public TRestainingRequestMaster gettRestainingRequestMaster() {
    return tRestainingRequestMaster;
  }

  public void settRestainingRequestMaster(TRestainingRequestMaster tRestainingRequestMaster) {
    this.tRestainingRequestMaster = tRestainingRequestMaster;
  }

  public Integer getCreatedBy() {
    return createdBy;
  }
  
  

  public Integer gettBlockId() {
    return tBlockId;
  }

  public void settBlockId(Integer tBlockId) {
    this.tBlockId = tBlockId;
  }

  public TBlockMaster gettBlockMaster() {
    return tBlockMaster;
  }

  public void settBlockMaster(TBlockMaster tBlockMaster) {
    this.tBlockMaster = tBlockMaster;
  }

  public void setCreatedBy(Integer createdBy) {
    this.createdBy = createdBy;
  }

  public List<TRestainingReqSubDetailsMaster> getListTRestainingReqSubDetailsMaster() {
    return listTRestainingReqSubDetailsMaster;
  }

  public void setListTRestainingReqSubDetailsMaster(
      List<TRestainingReqSubDetailsMaster> listTRestainingReqSubDetailsMaster) {
    this.listTRestainingReqSubDetailsMaster = listTRestainingReqSubDetailsMaster;
  }

  public TGrossMaster gettGrossMaster() {
    return tGrossMaster;
  }

  public void settGrossMaster(TGrossMaster tGrossMaster) {
    this.tGrossMaster = tGrossMaster;
  }



}
