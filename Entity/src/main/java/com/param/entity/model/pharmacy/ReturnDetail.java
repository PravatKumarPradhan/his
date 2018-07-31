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

@Entity(name="ReturnDetail")
@Table(name="t_return_detail",schema="pharmacy")
public class ReturnDetail extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=50)
	private String remark;

	@Column(name="return_amt", nullable=false)
	private Double returnAmt;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="return_id", nullable=false)
	private Return returnId;

	@Column(name="return_qty", nullable=false)
	private Integer returnQty;
	
	@Column(name="lease_quantity", nullable=false)
	private Integer leaseQty;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sale_detail_id", nullable=false)
	private SaleDetail saleDetail;

	public ReturnDetail() {
		super();
	}
	
	public ReturnDetail(SaleDetail saleDetail,Integer returnQty,Integer leaseQty,Double returnAmt,String remark) {
		super();
		this.remark = remark;
		this.returnAmt = returnAmt;
		this.leaseQty =leaseQty;
		this.returnQty = returnQty;
		this.saleDetail = saleDetail;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getReturnAmt() {
		return this.returnAmt;
	}

	public void setReturnAmt(Double returnAmt) {
		this.returnAmt = returnAmt;
	}

	public Return getReturnId() {
		return this.returnId;
	}

	public void setReturnId(Return returnId) {
		this.returnId = returnId;
	}

	public Integer getReturnQty() {
		return this.returnQty;
	}

	public void setReturnQty(Integer returnQty) {
		this.returnQty = returnQty;
	}

	public SaleDetail getSaleDetail() {
		return this.saleDetail;
	}

	public void setSaleDetail(SaleDetail saleDetail) {
		this.saleDetail = saleDetail;
	}

	public Integer getLeaseQty() {
		return leaseQty;
	}

	public void setLeaseQty(Integer leaseQty) {
		this.leaseQty = leaseQty;
	}

	
}