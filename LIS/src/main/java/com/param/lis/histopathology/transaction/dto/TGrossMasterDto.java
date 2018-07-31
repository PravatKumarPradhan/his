package com.param.lis.histopathology.transaction.dto;

import java.math.BigInteger;
import java.util.List;

public class TGrossMasterDto {
	
	private Integer tGrossId;
	
	private Integer orgId;

	private Integer orgUnitId;

	private Integer tSubSpecimanId;

	private Integer labSampleDtlsId;
	
	private String grossBarcode;
	
	private String grossNo;
	
	private String grossDesc;
	
	private String captureNote;
	
	private BigInteger grossNum;

	private Integer createdBy;
	
	private Long createdDate;

	private Integer updatedBy;

	private Long updatedDate;
	
	private Character isDeleted;
	
	private List<TBlockMasterDto> listTBlockMaster;
	
	private List<TRestainingRequestDetailsMasterDto> listTRestainingRequestDetailsMasterDto;

	public Integer gettGrossId() {
		return tGrossId;
	}

	public void settGrossId(Integer tGrossId) {
		this.tGrossId = tGrossId;
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

	public Integer getLabSampleDtlsId() {
		return labSampleDtlsId;
	}

	public void setLabSampleDtlsId(Integer labSampleDtlsId) {
		this.labSampleDtlsId = labSampleDtlsId;
	}

	public String getGrossBarcode() {
		return grossBarcode;
	}

	public void setGrossBarcode(String grossBarcode) {
		this.grossBarcode = grossBarcode;
	}

	public String getGrossNo() {
		return grossNo;
	}

	public void setGrossNo(String grossNo) {
		this.grossNo = grossNo;
	}

	public String getGrossDesc() {
		return grossDesc;
	}

	public void setGrossDesc(String grossDesc) {
		this.grossDesc = grossDesc;
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

	public List<TBlockMasterDto> getListTBlockMaster() {
		return listTBlockMaster;
	}

	public void setListTBlockMaster(List<TBlockMasterDto> listTBlockMaster) {
		this.listTBlockMaster = listTBlockMaster;
	}

  public List<TRestainingRequestDetailsMasterDto> getListTRestainingRequestDetailsMasterDto() {
    return listTRestainingRequestDetailsMasterDto;
  }

  public void setListTRestainingRequestDetailsMasterDto(
      List<TRestainingRequestDetailsMasterDto> listTRestainingRequestDetailsMasterDto) {
    this.listTRestainingRequestDetailsMasterDto = listTRestainingRequestDetailsMasterDto;
  }

public BigInteger getGrossNum() {
	return grossNum;
}

public void setGrossNum(BigInteger grossNum) {
	this.grossNum = grossNum;
}

  
}
