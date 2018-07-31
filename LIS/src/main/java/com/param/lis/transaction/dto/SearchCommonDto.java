package com.param.lis.transaction.dto;

import java.util.List;

class obj
{
	private Integer id;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
public class SearchCommonDto 
{
	private Integer orgId;
	private Integer orgUnitId;
	private Integer deptId;
	private Integer subDeptId;
	private String searchKeyword;
	private Integer visitTypeId;
	private Integer sampleStatusId;
	private List<Integer> visitTypes;
	private List<Integer> sampleStatusIds;
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
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public Integer getSubDeptId() {
		return subDeptId;
	}
	public void setSubDeptId(Integer subDeptId) {
		this.subDeptId = subDeptId;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	public Integer getVisitTypeId() {
		return visitTypeId;
	}
	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}
	public Integer getSampleStatusId() {
		return sampleStatusId;
	}
	public void setSampleStatusId(Integer sampleStatusId) {
		this.sampleStatusId = sampleStatusId;
	}
	public List<Integer> getVisitTypes() {
		return visitTypes;
	}
	public void setVisitTypes(List<Integer> visitTypes) {
		this.visitTypes = visitTypes;
	}
	public List<Integer> getSampleStatusIds() {
		return sampleStatusIds;
	}
	public void setSampleStatusIds(List<Integer> sampleStatusIds) {
		this.sampleStatusIds = sampleStatusIds;
	}
   
}
