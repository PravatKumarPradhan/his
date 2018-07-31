package com.param.lis.global.dto;

import java.util.List;


public class MediaColonyMapperMasterDto {
	


	private Integer mediaColonyMpprId;
	private Integer createdBy;
	private Long createdDate;
	private Long updatedDate;
	private Integer updatedBy;
	private Character status;
	private Integer mediaId;
	private Integer colonyId;
	private Integer orgId;
	private Character isDeleted;
	private String mediaCode;
	private String mediaDesc;
	private String colonyName;
	private List<Integer> selectediaColonyMpprList;
	


	public List<Integer> getSelectediaColonyMpprList() {
		return selectediaColonyMpprList;
	}

	public void setSelectediaColonyMpprList(List<Integer> selectediaColonyMpprList) {
		this.selectediaColonyMpprList = selectediaColonyMpprList;
	}

	public Integer getMediaColonyMpprId() {
		return mediaColonyMpprId;
	}

	public void setMediaColonyMpprId(Integer mediaColonyMpprId) {
		this.mediaColonyMpprId = mediaColonyMpprId;
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

	public Long getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Long updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public Integer getMediaId() {
		return mediaId;
	}

	public void setMediaId(Integer mediaId) {
		this.mediaId = mediaId;
	}

	public Integer getColonyId() {
		return colonyId;
	}

	public void setColonyId(Integer colonyId) {
		this.colonyId = colonyId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Character getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Character isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getMediaCode() {
		return mediaCode;
	}

	public void setMediaCode(String mediaCode) {
		this.mediaCode = mediaCode;
	}

	public String getMediaDesc() {
		return mediaDesc;
	}

	public void setMediaDesc(String mediaDesc) {
		this.mediaDesc = mediaDesc;
	}

	public String getColonyName() {
		return colonyName;
	}

	public void setColonyName(String colonyName) {
		this.colonyName = colonyName;
	}
	
	

	
}
