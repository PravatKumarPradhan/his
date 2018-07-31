package com.param.service.dto;

import java.util.List;

public class TPackageEitherorGroupDetailsDto {
	private Integer packageGroupDetailsId;
	private Integer packageId;
	private Integer groupId;
	private Double maxPrice;
	private Double minPrice;
	private Double avgPrice;
	private Double groupPrice;
	private Integer orgId;
	private Integer orgUnitId;
	private Integer createdBy;
	private String createdDate;
	private Integer updatedBy;
	private String updatedDate;
	private String packageGroupName;
	private Integer numberServiceUsable;
	private List<TPackageServicesDetailsDto> listTPackageServicesDetailsDto;
	public Integer getNumberServiceUsable() {
		return numberServiceUsable;
	}
	public void setNumberServiceUsable(Integer numberServiceUsable) {
		this.numberServiceUsable = numberServiceUsable;
	}
	public String getPackageGroupName() {
		return packageGroupName;
	}
	public void setPackageGroupName(String packageGroupName) {
		this.packageGroupName = packageGroupName;
	}
	public List<TPackageServicesDetailsDto> getListTPackageServicesDetailsDto() {
		return listTPackageServicesDetailsDto;
	}
	public void setListTPackageServicesDetailsDto(List<TPackageServicesDetailsDto> listTPackageServicesDetailsDto) {
		this.listTPackageServicesDetailsDto = listTPackageServicesDetailsDto;
	}
	public Integer getPackageGroupDetailsId() {
		return packageGroupDetailsId;
	}
	public void setPackageGroupDetailsId(Integer packageGroupDetailsId) {
		this.packageGroupDetailsId = packageGroupDetailsId;
	}
	public Integer getPackageId() {
		return packageId;
	}
	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Double getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}
	public Double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}
	public Double getAvgPrice() {
		return avgPrice;
	}
	public void setAvgPrice(Double avgPrice) {
		this.avgPrice = avgPrice;
	}
	public Double getGroupPrice() {
		return groupPrice;
	}
	public void setGroupPrice(Double groupPrice) {
		this.groupPrice = groupPrice;
	}
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
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
}
