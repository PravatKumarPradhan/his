package com.param.lis.global.dto;

import java.util.List;


public class AntibioticGroupAdditionMasterDto {
	

	
	private Integer antiboiticGroupMpprId;
	private Integer createdBy;
	private Long createdDate;
	private Long updatedDate;
	private Integer updatedBy;
	private Character status;
	private Integer antiboiticGroupId;
	private Integer antiboiticId;
	private Integer orgId;
	private Character isDeleted;
	private String antibioticGorupCode;
	private String antibioticGroupDesc;
	private String  antibioticName;

	private List<Integer> selectAntiboiticGroupList;
	
	
	public Integer getAntiboiticGroupMpprId() {
		return antiboiticGroupMpprId;
	}

	public void setAntiboiticGroupMpprId(Integer antiboiticGroupMpprId) {
		this.antiboiticGroupMpprId = antiboiticGroupMpprId;
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

	public Integer getAntiboiticGroupId() {
		return antiboiticGroupId;
	}

	public void setAntiboiticGroupId(Integer antiboiticGroupId) {
		this.antiboiticGroupId = antiboiticGroupId;
	}

	public Integer getAntiboiticId() {
		return antiboiticId;
	}

	public void setAntiboiticId(Integer antiboiticId) {
		this.antiboiticId = antiboiticId;
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

	public List<Integer> getSelectAntiboiticGroupList() {
		return selectAntiboiticGroupList;
	}

	public void setSelectAntiboiticGroupList(List<Integer> selectAntiboiticGroupList) {
		this.selectAntiboiticGroupList = selectAntiboiticGroupList;
	}

	public String getAntibioticGorupCode() {
		return antibioticGorupCode;
	}

	public void setAntibioticGorupCode(String antibioticGorupCode) {
		this.antibioticGorupCode = antibioticGorupCode;
	}

	public String getAntibioticGroupDesc() {
		return antibioticGroupDesc;
	}

	public void setAntibioticGroupDesc(String antibioticGroupDesc) {
		this.antibioticGroupDesc = antibioticGroupDesc;
	}

	public String getAntibioticName() {
		return antibioticName;
	}

	public void setAntibioticName(String antibioticName) {
		this.antibioticName = antibioticName;
	}
	
	
	
}
