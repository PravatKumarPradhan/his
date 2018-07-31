package com.param.lis.histopathology.transaction.dto;

import java.math.BigInteger;
import java.util.List;

public class TSlideMasterDto {
  private Integer tSlideId;

  private Integer orgId;

  private Integer orgUnitId;

  private Integer tSubSpecimanId;

  private Integer tGrossId;

  private Integer tBlockId;

  private Integer labSampleDtlsId;

  private Integer stainingId;
  
  private BigInteger  slideNum;
  
  private String slideBarcode;

  private String slideNo;

  private String captureNote;

  private Integer createdBy;

  private Long createdDate;

  private Integer updatedBy;

  private Long updatedDate;

  private Character isDeleted;

  private Character isRestained;
  
  private Integer restainAgainstTslideid;
  
  private Integer tRestainingSubDetailId;

  private List<TMicroExaDetailsDto> listTMicroExaDetails;

  private List<TRestainingReqSubDetailsMasterDto> listTRestainingReqSubDetailsMaster;

  public Integer gettSlideId() {
    return tSlideId;
  }

  public void settSlideId(Integer tSlideId) {
    this.tSlideId = tSlideId;
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


  public Integer gettSubSpecimanId() {
    return tSubSpecimanId;
  }

  public void settSubSpecimanId(Integer tSubSpecimanId) {
    this.tSubSpecimanId = tSubSpecimanId;
  }

  public Integer gettGrossId() {
    return tGrossId;
  }

  public void settGrossId(Integer tGrossId) {
    this.tGrossId = tGrossId;
  }

  public Integer gettBlockId() {
    return tBlockId;
  }

  public void settBlockId(Integer tBlockId) {
    this.tBlockId = tBlockId;
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

  public String getSlideBarcode() {
    return slideBarcode;
  }

  public void setSlideBarcode(String slideBarcode) {
    this.slideBarcode = slideBarcode;
  }

  public String getSlideNo() {
    return slideNo;
  }

  public void setSlideNo(String slideNo) {
    this.slideNo = slideNo;
  }

  public String getCaptureNote() {
    return captureNote;
  }

  public void setCaptureNote(String captureNote) {
    this.captureNote = captureNote;
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

  public List<TMicroExaDetailsDto> getListTMicroExaDetails() {
    return listTMicroExaDetails;
  }

  public void setListTMicroExaDetails(List<TMicroExaDetailsDto> listTMicroExaDetails) {
    this.listTMicroExaDetails = listTMicroExaDetails;
  }

  public List<TRestainingReqSubDetailsMasterDto> getListTRestainingReqSubDetailsMaster() {
    return listTRestainingReqSubDetailsMaster;
  }

  public void setListTRestainingReqSubDetailsMaster(
      List<TRestainingReqSubDetailsMasterDto> listTRestainingReqSubDetailsMaster) {
    this.listTRestainingReqSubDetailsMaster = listTRestainingReqSubDetailsMaster;
  }

  public Character getIsRestained() {
    return isRestained;
  }

  public void setIsRestained(Character isRestained) {
    this.isRestained = isRestained;
  }

  public Integer getRestainAgainstTslideid() {
    return restainAgainstTslideid;
  }

  public void setRestainAgainstTslideid(Integer restainAgainstTslideid) {
    this.restainAgainstTslideid = restainAgainstTslideid;
  }

  public Integer gettRestainingSubDetailId() {
    return tRestainingSubDetailId;
  }

  public void settRestainingSubDetailId(Integer tRestainingSubDetailId) {
    this.tRestainingSubDetailId = tRestainingSubDetailId;
  }

public BigInteger getSlideNum() {
	return slideNum;
}

public void setSlideNum(BigInteger slideNum) {
	this.slideNum = slideNum;
}



}
