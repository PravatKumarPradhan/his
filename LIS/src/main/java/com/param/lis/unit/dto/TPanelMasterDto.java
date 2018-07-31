package com.param.lis.unit.dto;

import java.util.List;

public class TPanelMasterDto {
	private Integer panelId;

	private Integer serviceId;
	private Integer orgId;
	private Integer orgUnitId;

	private String panelAlies;

	private String panelCode;

	private Character status;

	private Character isDeleted;

	private Integer createdBy;

	private Long createdDate;

	private Integer updatedBy;

	private Long updatedDate;


	private List<TPanelDetailsMasterDto> listTPanelDetailsMaster;


	public Integer getPanelId() {
		return panelId;
	}


	public void setPanelId(Integer panelId) {
		this.panelId = panelId;
	}


	public Integer getServiceId() {
		return serviceId;
	}


	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
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


	public String getPanelAlies() {
		return panelAlies;
	}


	public void setPanelAlies(String panelAlies) {
		this.panelAlies = panelAlies;
	}


	public String getPanelCode() {
		return panelCode;
	}


	public void setPanelCode(String panelCode) {
		this.panelCode = panelCode;
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


	public List<TPanelDetailsMasterDto> getListTPanelDetailsMaster() {
		return listTPanelDetailsMaster;
	}


	public void setListTPanelDetailsMaster(List<TPanelDetailsMasterDto> listTPanelDetailsMaster) {
		this.listTPanelDetailsMaster = listTPanelDetailsMaster;
	}

	

}
