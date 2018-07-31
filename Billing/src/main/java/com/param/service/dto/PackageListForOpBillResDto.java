package com.param.service.dto;

import java.util.List;

public class PackageListForOpBillResDto {
	private MPackageMasterDto mPackageMasterDto;
	private List<TPackageServicesDetailsDto> listTPackageServicesDetailsDto;
	private List<TPackageCapDetailsDto> listTPackageGroupWiseCapDetailsDto;
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
	public List<TPackageCapDetailsDto> getListTPackageGroupWiseCapDetailsDto() {
		return listTPackageGroupWiseCapDetailsDto;
	}
	public void setListTPackageGroupWiseCapDetailsDto(List<TPackageCapDetailsDto> listTPackageGroupWiseCapDetailsDto) {
		this.listTPackageGroupWiseCapDetailsDto = listTPackageGroupWiseCapDetailsDto;
	}
}
