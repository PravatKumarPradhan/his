package com.param.service.dto;

import java.util.List;

public class MultiencounterPackageReqDto {
	private Integer orgId;
	private Integer unitId;
	private MPackageMasterDto mPackageMasterDto;
	private List<TPackageServicesDetailsDto> serviceWiseDetails;
	private List<TPackageServicesDetailsDto> itemWiseDetails;
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
	public List<TPackageServicesDetailsDto> getServiceWiseDetails() {
		return serviceWiseDetails;
	}
	public void setServiceWiseDetails(List<TPackageServicesDetailsDto> serviceWiseDetails) {
		this.serviceWiseDetails = serviceWiseDetails;
	}
	public List<TPackageServicesDetailsDto> getItemWiseDetails() {
		return itemWiseDetails;
	}
	public void setItemWiseDetails(List<TPackageServicesDetailsDto> itemWiseDetails) {
		this.itemWiseDetails = itemWiseDetails;
	}
}
