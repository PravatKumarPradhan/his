package com.param.global.dto;

import java.math.BigDecimal;

public class OrderDetailsDiscountDto {
	private Integer orderDetailsDiscountId;
	private Integer orderDetailsId;
	private Integer discountCategoryId;
	private String discountCategory;
	private BigDecimal discountPercentage;
	private BigDecimal discountAmount;
	private Integer discountGivenBy;
	private Long discountDate;
	private Character isCancel;
	private Integer cancelBy;
	private Integer createdBy;
	private Long createdDate;
	private Integer updatedBy;
	private Long updatedDate;
	private Character status;
	private Integer unitId;
	private Integer orgId;
	private String remark;
	private Long cancelledDate;
	private Character isConsessionDiscount;
	private Integer oldDetailsDiscountId;
	private Integer cancelReasonId;
	
	
	public Integer getCancelReasonId() {
		return cancelReasonId;
	}
	public void setCancelReasonId(Integer cancelReasonId) {
		this.cancelReasonId = cancelReasonId;
	}
	public String getDiscountCategory() {
		return discountCategory;
	}
	public void setDiscountCategory(String discountCategory) {
		this.discountCategory = discountCategory;
	}
	public Integer getOrderDetailsDiscountId() {
		return orderDetailsDiscountId;
	}
	public void setOrderDetailsDiscountId(Integer orderDetailsDiscountId) {
		this.orderDetailsDiscountId = orderDetailsDiscountId;
	}
	public Integer getOrderDetailsId() {
		return orderDetailsId;
	}
	public void setOrderDetailsId(Integer orderDetailsId) {
		this.orderDetailsId = orderDetailsId;
	}
	public Integer getDiscountCategoryId() {
		return discountCategoryId;
	}
	public void setDiscountCategoryId(Integer discountCategoryId) {
		this.discountCategoryId = discountCategoryId;
	}
	public BigDecimal getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(BigDecimal discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}
	public Integer getDiscountGivenBy() {
		return discountGivenBy;
	}
	public void setDiscountGivenBy(Integer discountGivenBy) {
		this.discountGivenBy = discountGivenBy;
	}
	public Long getDiscountDate() {
		return discountDate;
	}
	public void setDiscountDate(Long discountDate) {
		this.discountDate = discountDate;
	}
	public Character getIsCancel() {
		return isCancel;
	}
	public void setIsCancel(Character isCancel) {
		this.isCancel = isCancel;
	}
	public Integer getCancelBy() {
		return cancelBy;
	}
	public void setCancelBy(Integer cancelBy) {
		this.cancelBy = cancelBy;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Long getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Long getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Long updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Character getStatus() {
		return status;
	}
	public void setStatus(Character status) {
		this.status = status;
	}
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getCancelledDate() {
		return cancelledDate;
	}
	public void setCancelledDate(Long cancelledDate) {
		this.cancelledDate = cancelledDate;
	}
	public Character getIsConsessionDiscount() {
		return isConsessionDiscount;
	}
	public void setIsConsessionDiscount(Character isConsessionDiscount) {
		this.isConsessionDiscount = isConsessionDiscount;
	}
	public Integer getOldDetailsDiscountId() {
		return oldDetailsDiscountId;
	}
	public void setOldDetailsDiscountId(Integer oldDetailsDiscountId) {
		this.oldDetailsDiscountId = oldDetailsDiscountId;
	}
	
	
	
}
