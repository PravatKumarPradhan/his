package com.param.service.dto;

import java.util.List;


public class CompanyContractReqDto {

	private Integer orgId;
	private Integer unitId;
	
	MCompanyContractMasterDto companyContractMasterDto;
	
	private List<TContractGroupPharmacyExclusionDetailsDto> listTContractGroupPharmacyExclusionDetailsDto;
	
	private List<TContractBedCategoryDetailDto> listTContractBedCategoryDetailDto;
	
	private List<TContractCapDetailsDto> listTContractCapDetailsDto;
	
	private List<TContractGroupDetailsDto> listTContractGroupDetailsDto;
	
	private List<TContractServiceDetailsDto> listTContractServiceDetailsDto;
	
	
	
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

	public MCompanyContractMasterDto getCompanyContractMasterDto() {
		return companyContractMasterDto;
	}

	public void setCompanyContractMasterDto(MCompanyContractMasterDto companyContractMasterDto) {
		this.companyContractMasterDto = companyContractMasterDto;
	}

	public List<TContractGroupPharmacyExclusionDetailsDto> getListTContractGroupPharmacyExclusionDetailsDto() {
		return listTContractGroupPharmacyExclusionDetailsDto;
	}

	public void setListTContractGroupPharmacyExclusionDetailsDto(
			List<TContractGroupPharmacyExclusionDetailsDto> listTContractGroupPharmacyExclusionDetailsDto) {
		this.listTContractGroupPharmacyExclusionDetailsDto = listTContractGroupPharmacyExclusionDetailsDto;
	}

	public List<TContractCapDetailsDto> getListTContractCapDetailsDto() {
		return listTContractCapDetailsDto;
	}

	public void setListTContractCapDetailsDto(List<TContractCapDetailsDto> listTContractCapDetailsDto) {
		this.listTContractCapDetailsDto = listTContractCapDetailsDto;
	}

	public List<TContractGroupDetailsDto> getListTContractGroupDetailsDto() {
		return listTContractGroupDetailsDto;
	}

	public void setListTContractGroupDetailsDto(List<TContractGroupDetailsDto> listTContractGroupDetailsDto) {
		this.listTContractGroupDetailsDto = listTContractGroupDetailsDto;
	}

	public List<TContractServiceDetailsDto> getListTContractServiceDetailsDto() {
		return listTContractServiceDetailsDto;
	}

	public void setListTContractServiceDetailsDto(List<TContractServiceDetailsDto> listTContractServiceDetailsDto) {
		this.listTContractServiceDetailsDto = listTContractServiceDetailsDto;
	}

	public List<TContractBedCategoryDetailDto> getListTContractBedCategoryDetailDto() {
		return listTContractBedCategoryDetailDto;
	}

	public void setListTContractBedCategoryDetailDto(
			List<TContractBedCategoryDetailDto> listTContractBedCategoryDetailDto) {
		this.listTContractBedCategoryDetailDto = listTContractBedCategoryDetailDto;
	}
	
	
}
