package com.param.service.dto;

import java.util.List;

public class EitherOrPackageReqDto {
	private Integer orgId;
	private Integer unitId;
	private MPackageMasterDto mPackageMasterDto;
	private List<TPackageServicesDetailsDto> listTPackageServicesDetailsDto;
	private List<TPackageEitherorGroupDetailsDto> listTPackageEitherorGroupDetailsDto;
	public List<TPackageEitherorGroupDetailsDto> getListTPackageEitherorGroupDetailsDto() {
		return listTPackageEitherorGroupDetailsDto;
	}
	public void setListTPackageEitherorGroupDetailsDto(List<TPackageEitherorGroupDetailsDto> listTPackageEitherorGroupDetailsDto) {
		this.listTPackageEitherorGroupDetailsDto = listTPackageEitherorGroupDetailsDto;
	}
	public Integer getOrgId() {
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
	}
	public MPackageMasterDto getmPackageMasterDto() {
		return mPackageMasterDto;
	}
	public void setmPackageMasterDto(MPackageMasterDto mPackageMasterDto) {
		this.mPackageMasterDto = mPackageMasterDto;
	}
	public List<TPackageServicesDetailsDto> getListTPackageServicesDetailsDto() {
		return listTPackageServicesDetailsDto;
	}
	public void setListTPackageServicesDetailsDto(List<TPackageServicesDetailsDto> listTPackageServicesDetailsDto) {
		this.listTPackageServicesDetailsDto = listTPackageServicesDetailsDto;
	}
}
