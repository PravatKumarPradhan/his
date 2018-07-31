package com.param.service.dto;

public class TPackageCapDetailsDto {
	private Integer packageCapDetailsId;
	private Integer packageId;
	private Integer departmentId;
	private Integer subDepartmentId;
	private Double departmentCapAmount;
	private Double subDepartmentCapAmount;
	private Integer organisationId;
	private Integer unitId;
	private Integer createdBy;
	private String createdDate;
	private Integer isServiceItem;
	private Integer productCateroyId;
	private char status;
	private Double productCateroyCapAmount;
	private String groupCode;
	private String groupName;
	private String subGroupName;
	private String productCategory;
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getSubGroupName() {
		return subGroupName;
	}
	public void setSubGroupName(String subGroupName) {
		this.subGroupName = subGroupName;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Integer getPackageCapDetailsId() {
		return packageCapDetailsId;
	}
	public void setPackageCapDetailsId(Integer packageCapDetailsId) {
		this.packageCapDetailsId = packageCapDetailsId;
	}
	public Integer getPackageId() {
		return packageId;
	}
	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public Integer getSubDepartmentId() {
		return subDepartmentId;
	}
	public void setSubDepartmentId(Integer subDepartmentId) {
		this.subDepartmentId = subDepartmentId;
	}
	public Double getDepartmentCapAmount() {
		return departmentCapAmount;
	}
	public void setDepartmentCapAmount(Double departmentCapAmount) {
		this.departmentCapAmount = departmentCapAmount;
	}
	public Double getSubDepartmentCapAmount() {
		return subDepartmentCapAmount;
	}
	public void setSubDepartmentCapAmount(Double subDepartmentCapAmount) {
		this.subDepartmentCapAmount = subDepartmentCapAmount;
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
	public Integer getIsServiceItem() {
		return isServiceItem;
	}
	public void setIsServiceItem(Integer isServiceItem) {
		this.isServiceItem = isServiceItem;
	}
	public Integer getProductCateroyId() {
		return productCateroyId;
	}
	public void setProductCateroyId(Integer productCateroyId) {
		this.productCateroyId = productCateroyId;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public Double getProductCateroyCapAmount() {
		return productCateroyCapAmount;
	}
	public void setProductCateroyCapAmount(Double productCateroyCapAmount) {
		this.productCateroyCapAmount = productCateroyCapAmount;
	}
}
