package com.param.service.dto;

import java.util.List;

public class AllInclusivePackageReqDto extends MPackageMasterDto {

	/*private Integer orgId;
	private Integer unitId;*/
	private List<TPackageIncExcDetailsDto> listTPackageIncExcServiceDetailsDto;
	
	/*public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}*/
	public List<TPackageIncExcDetailsDto> getListTPackageIncExcServiceDetailsDto() {
		return listTPackageIncExcServiceDetailsDto;
	}
	public void setListTPackageIncExcServiceDetailsDto(
			List<TPackageIncExcDetailsDto> listTPackageIncExcServiceDetailsDto) {
		this.listTPackageIncExcServiceDetailsDto = listTPackageIncExcServiceDetailsDto;
	}
	
	
}
