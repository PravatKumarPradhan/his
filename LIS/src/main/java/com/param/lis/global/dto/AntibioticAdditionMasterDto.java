package com.param.lis.global.dto;

import java.util.List;


public class AntibioticAdditionMasterDto {
	
	private Integer antiboiticMpprId;
	private Integer createdBy;
	private Long createdDate;
	private Long updatedDate;
	private Integer updatedBy;
	private Character status;
	private Integer antiboiticClassId;
	private String antiboiticClassCode;
	private Integer antiboiticId;
	private Integer orgId;
	private String antibioticClassDesc;
	private String antibioticName;
	private Integer antibioticId;
	private Character isDeleted;
	private List<Integer> selectAntibioticClassList;
	
	
	public String getAntibioticClassDesc() {
		return antibioticClassDesc;
	}
	public void setAntibioticClassDesc(String antibioticClassDesc) {
		this.antibioticClassDesc = antibioticClassDesc;
	}
	public String getAntibioticName() {
		return antibioticName;
	}
	public void setAntibioticName(String antibioticName) {
		this.antibioticName = antibioticName;
	}
	public Integer getAntibioticId() {
		return antibioticId;
	}
	public void setAntibioticId(Integer antibioticId) {
		this.antibioticId = antibioticId;
	}
	public Integer getAntiboiticMpprId() {
		return antiboiticMpprId;
	}
	public void setAntiboiticMpprId(Integer antiboiticMpprId) {
		this.antiboiticMpprId = antiboiticMpprId;
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
	public Integer getAntiboiticClassId() {
		return antiboiticClassId;
	}
	public void setAntiboiticClassId(Integer antiboiticClassId) {
		this.antiboiticClassId = antiboiticClassId;
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
	public List<Integer> getSelectAntibioticClassList() {
		return selectAntibioticClassList;
	}
	public void setSelectAntibioticClassList(List<Integer> selectAntibioticClassList) {
		this.selectAntibioticClassList = selectAntibioticClassList;
	}
	public Character getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Character isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getAntiboiticClassCode() {
		return antiboiticClassCode;
	}
	public void setAntiboiticClassCode(String antiboiticClassCode) {
		this.antiboiticClassCode = antiboiticClassCode;
	}
	
}
