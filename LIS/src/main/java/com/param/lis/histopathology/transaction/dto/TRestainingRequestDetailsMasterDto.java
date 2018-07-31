package com.param.lis.histopathology.transaction.dto;

import java.util.List;
import com.param.lis.common.dto.CommonDto;

public class TRestainingRequestDetailsMasterDto {

  private Integer tRestainingDetailId;

  private Integer orgId;

  private Integer orgUnitId;

  private Integer tRestainingReqId;
  
  private Integer labSampleDtlsId;

  private Integer tGrossId;
  
  private Integer tBlockId;
  
  private Integer tSlideId;

  private String blockNo;
  
  private String grossNo;
  
  private Integer createdBy;

  private Long createdDate;

  private Integer updatedBy;

  private Long updatedDate;

  private Character isDeleted;
  
  private String subSpecimanName;
  
  private String subSpcimanNo;
  
  private List<CommonDto> listSlidesNo;
  
  private String againstSlide;
  
  private Long noOfSlides;
  
  private String stainingName;
  
  private String remark;
  
  private Character isNew;
  
  private Integer tRestainingSubDetailId;
  
  private List<TRestainingReqSubDetailsMasterDto> listTRestainingReqSubDetailsMaster;

  public Integer gettRestainingDetailId() {
    return tRestainingDetailId;
  }

  public void settRestainingDetailId(Integer tRestainingDetailId) {
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
    this.isDeleted = isDeleted;
  }

  public List<TRestainingReqSubDetailsMasterDto> getListTRestainingReqSubDetailsMaster() {
    return listTRestainingReqSubDetailsMaster;
  }

  public void setListTRestainingReqSubDetailsMaster(
      List<TRestainingReqSubDetailsMasterDto> listTRestainingReqSubDetailsMaster) {
    this.listTRestainingReqSubDetailsMaster = listTRestainingReqSubDetailsMaster;
  }


  public String getSubSpecimanName() {
    return subSpecimanName;
  }

  public void setSubSpecimanName(String subSpecimanName) {
    this.subSpecimanName = subSpecimanName;
  }

  public String getSubSpcimanNo() {
    return subSpcimanNo;
  }

  public void setSubSpcimanNo(String subSpcimanNo) {
    this.subSpcimanNo = subSpcimanNo;
  }

  public Integer gettBlockId() {
    return tBlockId;
  }

  public void settBlockId(Integer tBlockId) {
    this.tBlockId = tBlockId;
  }

  public String getBlockNo() {
    return blockNo;
  }

  public void setBlockNo(String blockNo) {
    this.blockNo = blockNo;
  }

  public List<CommonDto> getListSlidesNo() {
    return listSlidesNo;
  }


  public void setListSlidesNo(List<CommonDto> listSlidesNo) {
    this.listSlidesNo = listSlidesNo;

  }

  public String getGrossNo() {
    return grossNo;
  }

  public void setGrossNo(String grossNo) {
    this.grossNo = grossNo;
  }

  public String getAgainstSlide() {
    return againstSlide;
  }

  public void setAgainstSlide(String againstSlide) {
    this.againstSlide = againstSlide;
  }

  public Long getNoOfSlides() {
    return noOfSlides;
  }

  public void setNoOfSlides(Long noOfSlides) {
    this.noOfSlides = noOfSlides;
  }

  public String getStainingName() {
    return stainingName;
  }

  public void setStainingName(String stainingName) {
    this.stainingName = stainingName;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Integer gettSlideId() {
    return tSlideId;
  }

  public void settSlideId(Integer tSlideId) {
    this.tSlideId = tSlideId;
  }

  public Character getIsNew() {
    return isNew;
  }

  public void setIsNew(Character isNew) {
    this.isNew = isNew;
  }

  public Integer gettRestainingSubDetailId() {
    return tRestainingSubDetailId;
  }

  public void settRestainingSubDetailId(Integer tRestainingSubDetailId) {
    this.tRestainingSubDetailId = tRestainingSubDetailId;
  }


  
}
