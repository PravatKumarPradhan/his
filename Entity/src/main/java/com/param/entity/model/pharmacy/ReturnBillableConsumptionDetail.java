package com.param.entity.model.pharmacy;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.param.entity.model.base.BaseEntity;

@Entity(name="ReturnBillableConsumptionDetail")
@Table(name="t_return_billable_consumption_detail",schema="pharmacy")
public class ReturnBillableConsumptionDetail extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="returnable_billable_consumption_id")
	private ReturnBillableConsumption returnBillableConsumption;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="billable_consumption_detail_id")
	private BillableConsumptionDetail billableConsumptionDetail;
	
	@Column(name="return_quantity")
	private Integer returnQuantity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ReturnBillableConsumption getReturnBillableConsumption() {
		return returnBillableConsumption;
	}

	public void setReturnBillableConsumption(ReturnBillableConsumption returnBillableConsumption) {
		this.returnBillableConsumption = returnBillableConsumption;
	}

	public BillableConsumptionDetail getBillableConsumptionDetail() {
		return billableConsumptionDetail;
	}

	public void setBillableConsumptionDetail(BillableConsumptionDetail billableConsumptionDetail) {
		this.billableConsumptionDetail = billableConsumptionDetail;
	}

	public Integer getReturnQuantity() {
		return returnQuantity;
	}

	public void setReturnQuantity(Integer returnQuantity) {
		this.returnQuantity = returnQuantity;
	}

	public ReturnBillableConsumptionDetail() {
		super();
	}

	public ReturnBillableConsumptionDetail(BillableConsumptionDetail billableConsumptionDetail,
			Integer returnQuantity) {
		super();
		this.billableConsumptionDetail = billableConsumptionDetail;
		this.returnQuantity = returnQuantity;
	}
	
}