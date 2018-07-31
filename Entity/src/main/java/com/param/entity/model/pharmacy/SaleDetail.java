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
import com.param.entity.model.inventory.Batch;
import com.param.entity.model.master.Tax;
import com.param.entity.model.master.UomType;
import com.param.entity.model.master.UomUnit;

@Entity(name = "SaleDetail")
@Table(name = "t_sale_detail", schema = "pharmacy")
public class SaleDetail extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "batch_id")
	private Batch batch;

	@Column(name = "discount_amount")
	private Double discountAmount;

	@Column(name = "lease_quantity")
	private Integer leaseQuantity;

	@Column(name = "net_amount")
	private Double netAmount;

	@Column(name = "order_detail_id")
	private Integer orderDetailId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prescription_detail_id")
	private PrescriptionDetail prescriptionDetail;

	private Integer quantity;

	private Double rate;

	@Column(name = "tax_amount", nullable = false)
	private Double taxAmount = 0.0;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tax_id")
	private Tax tax;

	@Column(name = "tax_percentage")
	private Double taxPercentage;

	@Column(name = "total_amount")
	private Double totalAmount;

	@Column(name = "unit_discount_amount")
	private Double unitDiscountAmount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uom_unit_id")
	private UomUnit uomUnit;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uom_type_id")
	private UomType uomType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sale_id")
	private Sale sale;

	public SaleDetail() {
		super();
	}

	public SaleDetail(Long id) {
		super();
		this.id = id;
	}

	public SaleDetail(Batch batch, Double discountAmount, Integer leaseQuantity, Double netAmount,
			Integer orderDetailId, PrescriptionDetail prescriptionDetail, Integer quantity, Double rate,
			Double taxAmount, Tax tax, Double taxPercentage, Double totalAmount, Double unitDiscountAmount,
			UomType uomType, UomUnit uomUnit) {
		super();
		this.batch = batch;
		this.discountAmount = discountAmount;
		this.leaseQuantity = leaseQuantity;
		this.netAmount = netAmount;
		this.orderDetailId = orderDetailId;
		this.prescriptionDetail = prescriptionDetail;
		this.quantity = quantity;
		this.rate = rate;
		this.taxAmount = taxAmount;
		this.tax = tax;
		this.taxPercentage = taxPercentage;
		this.totalAmount = totalAmount;
		this.unitDiscountAmount = unitDiscountAmount;
		this.uomType = uomType;
		this.uomUnit = uomUnit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Integer getLeaseQuantity() {
		return leaseQuantity;
	}

	public void setLeaseQuantity(Integer leaseQuantity) {
		this.leaseQuantity = leaseQuantity;
	}

	public Double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}

	public Integer getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public PrescriptionDetail getPrescriptionDetail() {
		return prescriptionDetail;
	}

	public void setPrescriptionDetail(PrescriptionDetail prescriptionDetail) {
		this.prescriptionDetail = prescriptionDetail;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getTaxAmount() {
		return this.taxAmount;
	}

	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public Tax getTax() {
		return tax;
	}

	public void setTax(Tax tax) {
		this.tax = tax;
	}

	public Double getTaxPercentage() {
		return taxPercentage;
	}

	public void setTaxPercentage(Double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getUnitDiscountAmount() {
		return unitDiscountAmount;
	}

	public void setUnitDiscountAmount(Double unitDiscountAmount) {
		this.unitDiscountAmount = unitDiscountAmount;
	}

	public UomUnit getUomUnit() {
		return uomUnit;
	}

	public void setUomUnit(UomUnit uomUnit) {
		this.uomUnit = uomUnit;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public UomType getUomType() {
		return uomType;
	}

	public void setUomType(UomType uomType) {
		this.uomType = uomType;
	}
}