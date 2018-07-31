package com.param.lis.histopathology.transaction.dto;

import java.math.BigInteger;
import java.util.List;

public class TBlockMasterDto 
{
	private Integer tBlockId;
	
	private Integer orgId;

	private Integer orgUnitId;

	private Integer tGrossId;

	private Integer labSampleDtlsId;
	
	private String blockBarcode;
	
	private String blockNo;
	
	private BigInteger blockNum;
	
	private String captureNote;
	
	private Integer createdBy;
	
	private Long createdDate;

	private Integer updatedBy;

	private Long updatedDate;
	
	private Character isDeleted;
	
	private List<TSlideMasterDto> listTSlideMaster;

	public Integer gettBlockId() {
		return tBlockId;
	}

	public void settBlockId(Integer tBlockId) {
		this.tBlockId = tBlockId;
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

	public Integer gettGrossId() {
		return tGrossId;
	}

	public void settGrossId(Integer tGrossId) {
		this.tGrossId = tGrossId;
	}

	public Integer getLabSampleDtlsId() {
		return labSampleDtlsId;
	}

	public void setLabSampleDtlsId(Integer labSampleDtlsId) {
		this.labSampleDtlsId = labSampleDtlsId;
	}

	public String getBlockBarcode() {
		return blockBarcode;
	}

	public void setBlockBarcode(String blockBarcode) {
		this.blockBarcode = blockBarcode;
	}

	public String getBlockNo() {
		return blockNo;
	}

	public void setBlockNo(String blockNo) {
		this.blockNo = blockNo;
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

	public List<TSlideMasterDto> getListTSlideMaster() {
		return listTSlideMaster;
	}

	public void setListTSlideMaster(List<TSlideMasterDto> listTSlideMaster) {
		this.listTSlideMaster = listTSlideMaster;
	}

  public BigInteger getBlockNum() {
    return blockNum;
  }

  public void setBlockNum(BigInteger blockNum) {
    this.blockNum = blockNum;
  }
}
