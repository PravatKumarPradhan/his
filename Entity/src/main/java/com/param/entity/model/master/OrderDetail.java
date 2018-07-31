package com.param.entity.model.master;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "OrderDetail")
@Table(name = "t_order_details", schema = "public")
public class OrderDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_details_id", unique = true, nullable = false)
	private Integer orderDetailsId;

	@Column(name = "batch_id")
	private Integer batchId;

	@Column(name = "bed_id")
	private Integer bedId;

	@Column(name = "billing_class_id")
	private Integer billingClassId;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "drug_id")
	private Integer drugId;

	@Column(name = "is_drug", length = 1)
	private String isDrug;

	@Column(name = "is_outsourced", length = 1)
	private String isOutsourced;

	@Column(name = "net_amount", precision = 15, scale = 2)
	private BigDecimal netAmount;

	@Column(name = "old_ord_dtl_id")
	private Integer oldOrdDtlId;

	@Column(name = "ord_cancel_datetime")
	private Timestamp ordCancelDatetime;

	@Column(name = "ord_cancel_reason_id")
	private Integer ordCancelReasonId;

	@Column(name = "ord_cancelled", length = 1)
	private String ordCancelled;

	@Column(name = "ord_concession", precision = 15, scale = 2)
	private BigDecimal ordConcession;

	@Column(name = "ord_discount", precision = 15, scale = 2)
	private BigDecimal ordDiscount;

	@Column(name = "ord_doc_id")
	private Integer ordDocId;

	@Column(name = "ord_remarks", length = 2147483647)
	private String ordRemarks;

	@Column(name = "ord_render_datetime")
	private Timestamp ordRenderDatetime;

	@Column(name = "order_date")
	private Timestamp orderDate;

	@Column(name = "order_emergency_flag", length = 1)
	private String orderEmergencyFlag;

	@Column(name = "order_qty")
	private Integer orderQty;

	@Column(name = "order_sch_date")
	private Timestamp orderSchDate;

	@Column(name = "org_id")
	private Integer orgId;

	@Column(name = "org_unit_id")
	private Integer orgUnitId;

	@Column(name = "package_id")
	private Integer packageId;

	@Column(name = "priority_id")
	private Integer priorityId;

	@Column(name = "room_id")
	private Integer roomId;

	@Column(name = "service_amt", precision = 15, scale = 2)
	private BigDecimal serviceAmt;

	@Column(name = "service_bill_id")
	private Integer serviceBillId;

	@Column(name = "service_chargeable")
	private Integer serviceChargeable;

	@Column(name = "service_id")
	private Integer serviceId;

	@Column(name = "service_is_billed")
	private Integer serviceIsBilled;

	@Column(name = "service_rendered")
	private Integer serviceRendered;

	@Column(name = "service_rendering_deptid")
	private Integer serviceRenderingDeptid;

	@Column(length = 1)
	private String status;

	@Column(name = "tariff_id")
	private Integer tariffId;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	private Timestamp updatedDate;

	@Column(name = "ward_id")
	private Integer wardId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;
	
	
	

	public OrderDetail() {
	}

	public OrderDetail(Integer orderDetailsId) {
		super();
		this.orderDetailsId = orderDetailsId;
	}

	public Integer getOrderDetailsId() {
		return this.orderDetailsId;
	}

	public void setOrderDetailsId(Integer orderDetailsId) {
		this.orderDetailsId = orderDetailsId;
	}

	public Integer getBatchId() {
		return this.batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public Integer getBedId() {
		return this.bedId;
	}

	public void setBedId(Integer bedId) {
		this.bedId = bedId;
	}

	public Integer getBillingClassId() {
		return this.billingClassId;
	}

	public void setBillingClassId(Integer billingClassId) {
		this.billingClassId = billingClassId;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getDrugId() {
		return this.drugId;
	}

	public void setDrugId(Integer drugId) {
		this.drugId = drugId;
	}

	public String getIsDrug() {
		return this.isDrug;
	}

	public void setIsDrug(String isDrug) {
		this.isDrug = isDrug;
	}

	public String getIsOutsourced() {
		return this.isOutsourced;
	}

	public void setIsOutsourced(String isOutsourced) {
		this.isOutsourced = isOutsourced;
	}

	public BigDecimal getNetAmount() {
		return this.netAmount;
	}

	public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
	}

	public Integer getOldOrdDtlId() {
		return this.oldOrdDtlId;
	}

	public void setOldOrdDtlId(Integer oldOrdDtlId) {
		this.oldOrdDtlId = oldOrdDtlId;
	}

	public Timestamp getOrdCancelDatetime() {
		return this.ordCancelDatetime;
	}

	public void setOrdCancelDatetime(Timestamp ordCancelDatetime) {
		this.ordCancelDatetime = ordCancelDatetime;
	}

	public Integer getOrdCancelReasonId() {
		return this.ordCancelReasonId;
	}

	public void setOrdCancelReasonId(Integer ordCancelReasonId) {
		this.ordCancelReasonId = ordCancelReasonId;
	}

	public String getOrdCancelled() {
		return this.ordCancelled;
	}

	public void setOrdCancelled(String ordCancelled) {
		this.ordCancelled = ordCancelled;
	}

	public BigDecimal getOrdConcession() {
		return this.ordConcession;
	}

	public void setOrdConcession(BigDecimal ordConcession) {
		this.ordConcession = ordConcession;
	}

	public BigDecimal getOrdDiscount() {
		return this.ordDiscount;
	}

	public void setOrdDiscount(BigDecimal ordDiscount) {
		this.ordDiscount = ordDiscount;
	}

	public Integer getOrdDocId() {
		return this.ordDocId;
	}

	public void setOrdDocId(Integer ordDocId) {
		this.ordDocId = ordDocId;
	}

	public String getOrdRemarks() {
		return this.ordRemarks;
	}

	public void setOrdRemarks(String ordRemarks) {
		this.ordRemarks = ordRemarks;
	}

	public Timestamp getOrdRenderDatetime() {
		return this.ordRenderDatetime;
	}

	public void setOrdRenderDatetime(Timestamp ordRenderDatetime) {
		this.ordRenderDatetime = ordRenderDatetime;
	}

	public Timestamp getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderEmergencyFlag() {
		return this.orderEmergencyFlag;
	}

	public void setOrderEmergencyFlag(String orderEmergencyFlag) {
		this.orderEmergencyFlag = orderEmergencyFlag;
	}

	public Integer getOrderQty() {
		return this.orderQty;
	}

	public void setOrderQty(Integer orderQty) {
		this.orderQty = orderQty;
	}

	public Timestamp getOrderSchDate() {
		return this.orderSchDate;
	}

	public void setOrderSchDate(Timestamp orderSchDate) {
		this.orderSchDate = orderSchDate;
	}

	public Integer getOrgId() {
		return this.orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getOrgUnitId() {
		return this.orgUnitId;
	}

	public void setOrgUnitId(Integer orgUnitId) {
		this.orgUnitId = orgUnitId;
	}

	public Integer getPackageId() {
		return this.packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public Integer getPriorityId() {
		return this.priorityId;
	}

	public void setPriorityId(Integer priorityId) {
		this.priorityId = priorityId;
	}

	public Integer getRoomId() {
		return this.roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public BigDecimal getServiceAmt() {
		return this.serviceAmt;
	}

	public void setServiceAmt(BigDecimal serviceAmt) {
		this.serviceAmt = serviceAmt;
	}

	public Integer getServiceBillId() {
		return this.serviceBillId;
	}

	public void setServiceBillId(Integer serviceBillId) {
		this.serviceBillId = serviceBillId;
	}

	public Integer getServiceChargeable() {
		return this.serviceChargeable;
	}

	public void setServiceChargeable(Integer serviceChargeable) {
		this.serviceChargeable = serviceChargeable;
	}

	public Integer getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getServiceIsBilled() {
		return this.serviceIsBilled;
	}

	public void setServiceIsBilled(Integer serviceIsBilled) {
		this.serviceIsBilled = serviceIsBilled;
	}

	public Integer getServiceRendered() {
		return this.serviceRendered;
	}

	public void setServiceRendered(Integer serviceRendered) {
		this.serviceRendered = serviceRendered;
	}

	public Integer getServiceRenderingDeptid() {
		return this.serviceRenderingDeptid;
	}

	public void setServiceRenderingDeptid(Integer serviceRenderingDeptid) {
		this.serviceRenderingDeptid = serviceRenderingDeptid;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTariffId() {
		return this.tariffId;
	}

	public void setTariffId(Integer tariffId) {
		this.tariffId = tariffId;
	}

	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getWardId() {
		return this.wardId;
	}

	public void setWardId(Integer wardId) {
		this.wardId = wardId;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
