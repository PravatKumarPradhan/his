package com.param.service.dto;

import java.util.List;

public class PackageWithCapReqDto {
	private Integer orgId;
	private Integer unitId;
	private MPackageMasterDto mPackageMasterDto;
	private List<TPackageServicesDetailsDto> listTPackageServicesDetailsDto;
	private List<TPackageBedCategoryDetailDto> listTPackageBedCategoryDetailDto;
	private List<TPackageCapDetailsDto> listTPackageGroupWiseCapDetailsDto;
	private List<TPackageCategoryWiseConsumableDetailsDto> listTPackageCategoryWiseConsumableDetailsDto;
	private List<TPackageIncExcDetailsDto> listGroupWiseIncExc;
	private List<TPackageIncExcDetailsDto> listCategoryWiseExc;
	private List<TPackageIncExcDetailsDto> listConsumableWiseIncExc;
	private List<TPackageCapDetailsDto> listTPackageConsumableWiseCapDetailsDto;
	private List<TPackageServicesDetailsDto> listTPackageConsumableDetailsDto;
	public List<TPackageServicesDetailsDto> getListTPackageConsumableDetailsDto() {
		return listTPackageConsumableDetailsDto;
	}
	public void setListTPackageConsumableDetailsDto(List<TPackageServicesDetailsDto> listTPackageConsumableDetailsDto) {
		this.listTPackageConsumableDetailsDto = listTPackageConsumableDetailsDto;
	}
	public List<TPackageCapDetailsDto> getListTPackageConsumableWiseCapDetailsDto() {
		return listTPackageConsumableWiseCapDetailsDto;
	}
	public void setListTPackageConsumableWiseCapDetailsDto(
			List<TPackageCapDetailsDto> listTPackageConsumableWiseCapDetailsDto) {
		this.listTPackageConsumableWiseCapDetailsDto = listTPackageConsumableWiseCapDetailsDto;
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
	public List<TPackageBedCategoryDetailDto> getListTPackageBedCategoryDetailDto() {
		return listTPackageBedCategoryDetailDto;
	}
	public void setListTPackageBedCategoryDetailDto(List<TPackageBedCategoryDetailDto> listTPackageBedCategoryDetailDto) {
		this.listTPackageBedCategoryDetailDto = listTPackageBedCategoryDetailDto;
	}
	public List<TPackageCapDetailsDto> getListTPackageGroupWiseCapDetailsDto() {
		return listTPackageGroupWiseCapDetailsDto;
	}
	public void setListTPackageGroupWiseCapDetailsDto(List<TPackageCapDetailsDto> listTPackageGroupWiseCapDetailsDto) {
		this.listTPackageGroupWiseCapDetailsDto = listTPackageGroupWiseCapDetailsDto;
	}
	public List<TPackageCategoryWiseConsumableDetailsDto> getListTPackageCategoryWiseConsumableDetailsDto() {
		return listTPackageCategoryWiseConsumableDetailsDto;
	}
	public void setListTPackageCategoryWiseConsumableDetailsDto(
			List<TPackageCategoryWiseConsumableDetailsDto> listTPackageCategoryWiseConsumableDetailsDto) {
		this.listTPackageCategoryWiseConsumableDetailsDto = listTPackageCategoryWiseConsumableDetailsDto;
	}
	public List<TPackageIncExcDetailsDto> getListGroupWiseIncExc() {
		return listGroupWiseIncExc;
	}
	public void setListGroupWiseIncExc(List<TPackageIncExcDetailsDto> listGroupWiseIncExc) {
		this.listGroupWiseIncExc = listGroupWiseIncExc;
	}
	public List<TPackageIncExcDetailsDto> getListCategoryWiseExc() {
		return listCategoryWiseExc;
	}
	public void setListCategoryWiseExc(List<TPackageIncExcDetailsDto> listCategoryWiseExc) {
		this.listCategoryWiseExc = listCategoryWiseExc;
	}
	public List<TPackageIncExcDetailsDto> getListConsumableWiseIncExc() {
		return listConsumableWiseIncExc;
	}
	public void setListConsumableWiseIncExc(List<TPackageIncExcDetailsDto> listConsumableWiseIncExc) {
		this.listConsumableWiseIncExc = listConsumableWiseIncExc;
	}
}
