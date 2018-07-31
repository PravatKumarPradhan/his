package com.param.global.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.entity.model.master.DiscountType;
import com.param.global.common.DateConverter;

@NamedQueries({
	
	@NamedQuery(name="GET_ORDERDETAILS_DISCOUNT_BY_ORDER_DETAILS_ID",query=" SELECT ordDetailDis.orderDetailsDiscountId as orderDetailsDiscountId, "
			+ "ordDetailDis.orderDetailsId as orderDetailsId, "
			+ "ordDetailDis.discountCategoryId as discountCategoryId, "
			+ "discCat.type as discountCategory, "
			+ "ordDetailDis.discountPercentage as discountPercentage, "
			+ "ordDetailDis.discountAmount as discountAmount, "
			+ "ordDetailDis.discountGivenBy as discountGivenBy,"
			+ "ordDetailDis.createdDate as createdDate, "
			+ "ordDetailDis.createdBy as createdBy "
			+ "FROM OrderDetailsDiscount ordDetailDis "
			+ "LEFT JOIN ordDetailDis.discountType discCat "
			+ "WHERE ordDetailDis.orderDetailsId=:orderDetailsId "
			+ "AND ordDetailDis.status='A' ")
})
	
	
	


@Entity
@Table(name="t_order_details_discount", schema="billing")
@SequenceGenerator(name="order_details_discount_seq", sequenceName="billing.order_details_discount_seq", allocationSize=1)
public class OrderDetailsDiscount {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="order_details_discount_seq")
	@Column(name="order_details_discount_id")
	private Integer orderDetailsDiscountId;
	
	@Column(name="order_details_id")
	private Integer orderDetailsId;
	
	@Column(name="discount_category_id")
	private Integer discountCategoryId;

	@Column(name="discount_percentage")
	private BigDecimal discountPercentage;

	@Column(name="discount_amount")
	private BigDecimal discountAmount;
	
	@Column(name="discount_given_by")
	private Integer discountGivenBy;
	
	@Column(name="discount_date")
	@Convert(converter=DateConverter.class)
	private Long discountDate;
	
	@Column(name="is_cancel")
	private Character isCancel;
	
	@Column(name="cancel_by")
	private Integer cancelBy;
	
	@Column(name="created_by")
	private Integer createdBy;
	
	@Column(name="created_date")
	@Convert(converter=DateConverter.class)
	private Long createdDate;
	
	@Column(name="updated_by")
	private Integer updatedBy;
	
	@Column(name="updated_date")
	@Convert(converter=DateConverter.class)
	private Long updatedDate;
	
	@Column(name="status")
	private Character status;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="org_id")
	private Integer orgId;
	
	@Column(name="remark")
	private String remark;
	
	@Column(name="cancelled_date") 
	@Convert(converter=DateConverter.class)
	private Long cancelledDate;
	 
	@Column(name="is_consession_discount") 
	private Character isConsessionDiscount;
	
	@Column(name="old_details_discount_id")
	private Integer oldDetailsDiscountId;
	
	@Column(name="cancel_reason_id")
	private Integer cancelReasonId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "discount_category_id", insertable = false, nullable = false, updatable = false)
	private DiscountType discountType;

	
	
	public Integer getCancelReasonId() {
		return cancelReasonId;
	}

	public void setCancelReasonId(Integer cancelReasonId) {
		this.cancelReasonId = cancelReasonId;
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

