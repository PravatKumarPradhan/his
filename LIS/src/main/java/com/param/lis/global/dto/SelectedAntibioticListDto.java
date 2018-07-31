package com.param.lis.global.dto;

import java.util.List;

public class SelectedAntibioticListDto {
	
	private List<Integer> selectAntibioticList;
	private Integer orgId;
	
	
	public List<Integer> getSelectAntibioticList() {
		return selectAntibioticList;
	}
	public void setSelectAntibioticList(List<Integer> selectAntibioticList) {
		this.selectAntibioticList = selectAntibioticList;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
	
	

}
