package com.param.lis.unit.dto;


import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.common.LocalTimeConverter;


public class RackShelfMasterDto {
  private Integer shelfId;

  private Integer orgId;

  private Integer rackId;

  private Integer orgUnitId;

  private String shelfCode;

  private String shelfName;

  private Character status;
  
  private Character isDeleted;
  
  private Integer createdBy;

  private Long createdDate;

  private Integer updatedBy;

  private Long updatedDate;

public Integer getShelfId() {
	return shelfId;
}

public void setShelfId(Integer shelfId) {
	this.shelfId = shelfId;
}

public Integer getOrgId() {
	return orgId;
}

public void setOrgId(Integer orgId) {
	this.orgId = orgId;
}

public Integer getRackId() {
	return rackId;
}

public void setRackId(Integer rackId) {
	this.rackId = rackId;
}

public Integer getOrgUnitId() {
	return orgUnitId;
}

public void setOrgUnitId(Integer orgUnitId) {
	this.orgUnitId = orgUnitId;
}

public String getShelfCode() {
	return shelfCode;
}

public void setShelfCode(String shelfCode) {
	this.shelfCode = shelfCode;
}

public String getShelfName() {
	return shelfName;
}

public void setShelfName(String shelfName) {
	this.shelfName = shelfName;
}

public Character getStatus() {
	return status;
}

public void setStatus(Character status) {
	this.status = status;
}

public Character getIsDeleted() {
	return isDeleted;
}

public void setIsDeleted(Character isDeleted) {
	this.isDeleted = isDeleted;
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


}
	



