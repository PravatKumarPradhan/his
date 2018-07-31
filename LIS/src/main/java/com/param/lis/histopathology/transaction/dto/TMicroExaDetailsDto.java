package com.param.lis.histopathology.transaction.dto;

public class TMicroExaDetailsDto {

  private Integer tMicroExaId;

  private Integer orgId;

  private Integer orgUnitId;

  private Integer tSlideId;
  
  private String slideNo;

  private Integer labSampleDtlsId;

  private Integer stainingId;

  private Character isComplete;
  
  private String captureNote;
  
  private Character sendForStorage;

  private Integer createdBy;

  private Long createdDate;

  private Integer updatedBy;

  private Long updatedDate;

  private Character isDeleted;

  private String subSpecimanName;
  
  private String grossNo;
  
  private String blockNo;
  
  private String stainName;
  
  private String isExaComplete;
  
  private Character isRestained;

  public Integer gettMicroExaId() {
    return tMicroExaId;
  }

  public void settMicroExaId(Integer tMicroExaId) {
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
    return isComplete==null?'N':isComplete;
  }

  public void setIsComplete(Character isComplete) {
    this.isComplete = isComplete;
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
    return isDeleted==null?'N':isDeleted;
  }

  public void setIsDeleted(Character isDeleted) {
    this.isDeleted = isDeleted;
  }

  public String getSubSpecimanName() {
    return subSpecimanName;
  }

  public void setSubSpecimanName(String subSpecimanName) {
    this.subSpecimanName = subSpecimanName;
  }

  public String getGrossNo() {
    return grossNo;
  }

  public void setGrossNo(String grossNo) {
    this.grossNo = grossNo;
  }

  public String getBlockNo() {
    return blockNo;
  }

  public void setBlockNo(String blockNo) {
    this.blockNo = blockNo;
  }

  public String getStainName() {
    return stainName;
  }

  public void setStainName(String stainName) {
    this.stainName = stainName;
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

public String getIsExaComplete() {
	return isExaComplete;
}

public void setIsExaComplete(String isExaComplete) {
	this.isExaComplete = isExaComplete;
}

public Character getIsRestained() {
  return isRestained;
}

public void setIsRestained(Character isRestained) {
  this.isRestained = isRestained;
}

public String getSlideNo() {
  return slideNo;
}

public void setSlideNo(String slideNo) {
  this.slideNo = slideNo;
}
  
  
}
