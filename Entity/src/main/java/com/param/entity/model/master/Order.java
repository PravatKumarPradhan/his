package com.param.entity.model.master;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "Order")
@Table(name = "t_order_master", schema = "public")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id", unique = true, nullable = false)
	private Integer orderId;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "dr_cr_id")
	private Integer drCrId;

	@Column(name = "ord_doctor_id")
	private Integer ordDoctorId;

	@Column(name = "ord_remarks", length = 2147483647)
	private String ordRemarks;

	@Column(name = "order_date")
	private Timestamp orderDate;

	@Column(name = "order_no", length = 200)
	private String orderNo;

	@Column(name = "order_status", length = 1)
	private String orderStatus;

	@Column(name = "org_id")
	private Integer orgId;

	@Column(name = "org_unit_id")
	private Integer orgUnitId;

	@Column(name = "patient_id")
	private Integer patientId;

	@Column(name = "priority_id")
	private Integer priorityId;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	private Timestamp updatedDate;

	@Column(name = "visit_adm_id")
	private Integer visitAdmId;

	@Column(name = "visit_type_id")
	private Integer visitTypeId;

	@Column(name = "ward_id")
	private Integer wardId;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	private List<OrderDetail> orderDetailsList;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_status_id")
	private OrderStatus orderStatuses;

	public Order() {
	}

	public Order(Integer orderId) {
		super();
		this.orderId = orderId;
	}

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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

	public Integer getDrCrId() {
		return this.drCrId;
	}

	public void setDrCrId(Integer drCrId) {
		this.drCrId = drCrId;
	}

	public Integer getOrdDoctorId() {
		return this.ordDoctorId;
	}

	public void setOrdDoctorId(Integer ordDoctorId) {
		this.ordDoctorId = ordDoctorId;
	}

	public String getOrdRemarks() {
		return this.ordRemarks;
	}

	public void setOrdRemarks(String ordRemarks) {
		this.ordRemarks = ordRemarks;
	}

	public Timestamp getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
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

	public Integer getPatientId() {
		return this.patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getPriorityId() {
		return this.priorityId;
	}

	public void setPriorityId(Integer priorityId) {
		this.priorityId = priorityId;
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

	public Integer getVisitAdmId() {
		return this.visitAdmId;
	}

	public void setVisitAdmId(Integer visitAdmId) {
		this.visitAdmId = visitAdmId;
	}

	public Integer getVisitTypeId() {
		return this.visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public Integer getWardId() {
		return this.wardId;
	}

	public void setWardId(Integer wardId) {
		this.wardId = wardId;
	}

	public List<OrderDetail> getOrderDetailsList() {
		return this.orderDetailsList;
	}

	public void setOrderDetailsList(List<OrderDetail> orderDetailsList) {
		this.orderDetailsList = orderDetailsList;
	}

	public OrderDetail addOrderDetailsList(OrderDetail orderDetailsList) {
		getOrderDetailsList().add(orderDetailsList);
		orderDetailsList.setOrder(this);

		return orderDetailsList;
	}

	public OrderDetail removeOrderDetailsList(OrderDetail orderDetailsList) {
		getOrderDetailsList().remove(orderDetailsList);
		orderDetailsList.setOrder(null);

		return orderDetailsList;
	}

	public OrderStatus getOrderStatuses() {
		return this.orderStatuses;
	}

	public void setOrderStatuses(OrderStatus orderStatuses) {
		this.orderStatuses = orderStatuses;
	}

}
