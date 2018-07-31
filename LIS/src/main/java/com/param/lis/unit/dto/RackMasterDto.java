package com.param.lis.unit.dto;

import java.util.List;

public class RackMasterDto {

  private Integer rackId;
  private Integer orgId;
  private Integer orgUnitId;
  private String rackCode;
  private String rackName;
  private Character status;
  private Integer createdBy;
  private Long createdDate;
  private Integer updatedBy;
  private Long updatedDate;
  
  private List<RackShelfMasterDto> listRackShelfMasterDto;


public Integer getRackId() {
	return rackId;
}

public void setRackId(Integer rackId) {
	this.rackId = rackId;
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

public String getRackCode() {
	return rackCode;
}

public void setRackCode(String rackCode) {
	this.rackCode = rackCode;
}

public String getRackName() {
	return rackName;
}

public void setRackName(String rackName) {
	this.rackName = rackName;
}

public Character getStatus() {
	return status;
}

public void setStatus(Character status) {
	this.status = status;
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

public List<RackShelfMasterDto> getListRackShelfMasterDto() {
	return listRackShelfMasterDto;
}

public void setListRackShelfMasterDto(List<RackShelfMasterDto> listRackShelfMasterDto) {
	this.listRackShelfMasterDto = listRackShelfMasterDto;
}

  
}
	



