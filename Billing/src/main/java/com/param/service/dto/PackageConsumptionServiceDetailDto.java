package com.param.service.dto;

public class PackageConsumptionServiceDetailDto {

	private Integer packageConsumptionServiceDetailId;
	private Integer  packageConsumptionMasterId;
	private Integer serviceId;
	private String serviceCode;
	private String serviceStandardName;
	private Integer packageQuantity;
	private Integer balanceQuantity;
	private double apportinedPrice;
	private Integer isServiceItem;
	private Integer packageEODetailId;
	private Integer organisationId;
	private Integer unitId;
	private Integer specialityId;
	private String specialityName;
	private Integer subSpecialityId;
	private String subSpecialityName;
	
	public Integer getPackageConsumptionServiceDetailId() {
		return packageConsumptionServiceDetailId;
	}
	public void setPackageConsumptionServiceDetailId(
			Integer packageConsumptionServiceDetailId) {
		this.packageConsumptionServiceDetailId = packageConsumptionServiceDetailId;
	}
	public Integer getPackageConsumptionMasterId() {
		return packageConsumptionMasterId;
	}
	public void setPackageConsumptionMasterId(Integer packageConsumptionMasterId) {
		this.packageConsumptionMasterId = packageConsumptionMasterId;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public Integer getPackageQuantity() {
		return packageQuantity;
	}
	public void setPackageQuantity(Integer packageQuantity) {
		this.packageQuantity = packageQuantity;
	}
	public Integer getBalanceQuantity() {
		return balanceQuantity;
	}
	public void setBalanceQuantity(Integer balanceQuantity) {
		this.balanceQuantity = balanceQuantity;
	}
	public double getApportinedPrice() {
		return apportinedPrice;
	}
	public void setApportinedPrice(double apportinedPrice) {
		this.apportinedPrice = apportinedPrice;
	}
	public Integer getIsServiceItem() {
		return isServiceItem;
	}
	public void setIsServiceItem(Integer isServiceItem) {
		this.isServiceItem = isServiceItem;
	}
	public Integer getPackageEODetailId() {
		return packageEODetailId;
	}
	public void setPackageEODetailId(Integer packageEODetailId) {
		this.packageEODetailId = packageEODetailId;
	}
	public Integer getOrganisationId() {
		return organisationId;
	}
	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getServiceStandardName() {
		return serviceStandardName;
	}
	public void setServiceStandardName(String serviceStandardName) {
		this.serviceStandardName = serviceStandardName;
	}
	public Integer getSpecialityId() {
		return specialityId;
	}
	public void setSpecialityId(Integer specialityId) {
		this.specialityId = specialityId;
	}
	public String getSpecialityName() {
		return specialityName;
	}
	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}
	public Integer getSubSpecialityId() {
		return subSpecialityId;
	}
	public void setSubSpecialityId(Integer subSpecialityId) {
		this.subSpecialityId = subSpecialityId;
	}
	public String getSubSpecialityName() {
		return subSpecialityName;
	}
	public void setSubSpecialityName(String subSpecialityName) {
		this.subSpecialityName = subSpecialityName;
	}
	
	
}
