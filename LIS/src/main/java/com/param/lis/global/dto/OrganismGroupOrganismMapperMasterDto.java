package com.param.lis.global.dto;

import java.util.List;


public class OrganismGroupOrganismMapperMasterDto {
	

	private Integer orgGroupMpprId;
	private Integer createdBy;
	private Long createdDate;
	private Long updatedDate;
	private Integer updatedBy;
	private Character status;
	private Integer organismGroupId;
	private Integer organismId;
	private Integer orgId;
	private Character isDeleted;
	private String organismGroupCode;
	private String organismGroupName;
	private String  organismName;
	private String organismDesc;
	private String organismCode;
	
	private List<Integer> selecteOrgGroupMpprList;

	public Integer getOrgGroupMpprId() {
		return orgGroupMpprId;
	}

	public void setOrgGroupMpprId(Integer orgGroupMpprId) {
		this.orgGroupMpprId = orgGroupMpprId;
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

	public Integer getOrganismGroupId() {
		return organismGroupId;
	}

	public void setOrganismGroupId(Integer organismGroupId) {
		this.organismGroupId = organismGroupId;
	}

	public Integer getOrganismId() {
		return organismId;
	}

	public void setOrganismId(Integer organismId) {
		this.organismId = organismId;
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

	public List<Integer> getSelecteOrgGroupMpprList() {
		return selecteOrgGroupMpprList;
	}

	public void setSelecteOrgGroupMpprList(List<Integer> selecteOrgGroupMpprList) {
		this.selecteOrgGroupMpprList = selecteOrgGroupMpprList;
	}

	public String getOrganismGroupCode() {
		return organismGroupCode;
	}

	public void setOrganismGroupCode(String organismGroupCode) {
		this.organismGroupCode = organismGroupCode;
	}

	public String getOrganismGroupName() {
		return organismGroupName;
	}

	public void setOrganismGroupName(String organismGroupName) {
		this.organismGroupName = organismGroupName;
	}

	public String getOrganismName() {
		return organismName;
	}

	public void setOrganismName(String organismName) {
		this.organismName = organismName;
	}

	public String getOrganismDesc() {
		return organismDesc;
	}

	public void setOrganismDesc(String organismDesc) {
		this.organismDesc = organismDesc;
	}

	public String getOrganismCode() {
		return organismCode;
	}

	public void setOrganismCode(String organismCode) {
		this.organismCode = organismCode;
	}
	
	
	

	
}
