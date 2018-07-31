package com.param.entity.model.master;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "OrderStatus")
@Table(name = "m_order_status_master", schema = "public")
public class OrderStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_status_id", unique = true, nullable = false)
	private Integer orderStatusId;

	@Column(name = "order_code", length = 50)
	private String orderCode;

	@Column(name = "order_desc", length = 100)
	private String orderDesc;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderStatus")
	private List<Order> orderList;

	public OrderStatus() {
	}

	public Integer getOrderStatusId() {
		return this.orderStatusId;
	}

	public void setOrderStatusId(Integer orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	public String getOrderCode() {
		return this.orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderDesc() {
		return this.orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public List<Order> getOrderList() {
		return this.orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public Order addOrderList(Order orderList) {
		getOrderList().add(orderList);
		orderList.setOrderStatuses(this);

		return orderList;
	}

	public Order removeOrderList(Order orderList) {
		getOrderList().remove(orderList);
		orderList.setOrderStatus(null);

		return orderList;
	}

}
