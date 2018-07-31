package com.param.lis.global.dto;

import java.util.List;


public class AntibioticOrganismMpprMasterDto {
	

	private Integer antiboiticOrganismMpprId;
	private Integer createdBy;
	private Long createdDate;
	private Long updatedDate;
	private Integer updatedBy;
	private Character status;
	private Integer organismId;
	private Integer antiboiticId;
	private Integer antiboiticGroupId;
	private Integer orgId;
	private Character isDeleted;
	private String organismCode;
	private String organismDesc;
	
	private List<Integer> selectAntiboiticOrganismMppList;
	
	
	public Integer getAntiboiticOrganismMpprId() {
		return antiboiticOrganismMpprId;
	}

	public void setAntiboiticOrganismMpprId(Integer antiboiticOrganismMpprId) {
		this.antiboiticOrganismMpprId = antiboiticOrganismMpprId;
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

	public Integer getOrganismId() {
		return organismId;
	}

	public void setOrganismId(Integer organismId) {
		this.organismId = organismId;
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

	public List<Integer> getSelectAntiboiticOrganismMppList() {
		return selectAntiboiticOrganismMppList;
	}

	public void setSelectAntiboiticOrganismMppList(
			List<Integer> selectAntiboiticOrganismMppList) {
		this.selectAntiboiticOrganismMppList = selectAntiboiticOrganismMppList;
	}

	public Integer getAntiboiticGroupId() {
		return antiboiticGroupId;
	}

	public void setAntiboiticGroupId(Integer antiboiticGroupId) {
		this.antiboiticGroupId = antiboiticGroupId;
	}

	public String getOrganismCode() {
		return organismCode;
	}

	public void setOrganismCode(String organismCode) {
		this.organismCode = organismCode;
	}

	public String getOrganismDesc() {
		return organismDesc;
	}

	public void setOrganismDesc(String organismDesc) {
		this.organismDesc = organismDesc;
	}
	
	
	
}
