package com.param.billing.dto;

public class TPackageConsumptionServiceDetailDto {
	private Integer pacakgeConsumptionServiceDetailId;
	private Integer packageConsumptionMasterId;
	private Integer serviceId;
	private Integer packageQuantity;
	private Integer balancePackageQuantity;
	private Double apportionedPrice;
	private Integer isServiceItem;
	private Integer itemId;
	private Integer packageEoDetailId;
	private Integer organisationId;
	private Integer unitId;
	private String createdDate;
	private Integer createdBy;
	private String updatedDate;
	private Integer updatedBy;
	private char status;
	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = (status != '\u0000' ? status : 'I');
	}
	public Integer getPacakgeConsumptionServiceDetailId() {
		return pacakgeConsumptionServiceDetailId;
	}
	public void setPacakgeConsumptionServiceDetailId(Integer pacakgeConsumptionServiceDetailId) {
		this.pacakgeConsumptionServiceDetailId = pacakgeConsumptionServiceDetailId;
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
	public Integer getBalancePackageQuantity() {
		return balancePackageQuantity;
	}
	public void setBalancePackageQuantity(Integer balancePackageQuantity) {
		this.balancePackageQuantity = balancePackageQuantity;
	}
	public Double getApportionedPrice() {
		return apportionedPrice;
	}
	public void setApportionedPrice(Double apportionedPrice) {
		this.apportionedPrice = apportionedPrice;
	}
	public Integer getIsServiceItem() {
		return isServiceItem;
	}
	public void setIsServiceItem(Integer isServiceItem) {
		this.isServiceItem = isServiceItem;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getPackageEoDetailId() {
		return packageEoDetailId;
	}
	public void setPackageEoDetailId(Integer packageEoDetailId) {
		this.packageEoDetailId = packageEoDetailId;
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
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
}
