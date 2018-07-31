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
import com.param.entity.model.master.Status;
import com.param.entity.model.master.UomType;
import com.param.entity.model.master.UomUnit;

@Entity(name="BillableConsumptionDetail")
@Table(name="t_billable_consumption_detail",schema="pharmacy")
public class BillableConsumptionDetail extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="billable_consumption_id")
	private BillableConsumption billableConsumption;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="batch_id")
	private Batch batch;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="uom_type_id")
	private UomType uomType;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="uom_unit_id")
	private UomUnit uomUnit;
	
	@Column(name="consumable_quantity")
	private Integer consumableQuantity;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="status_id")
	private Status status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BillableConsumption getBillableConsumption() {
		return billableConsumption;
	}

	public void setBillableConsumption(BillableConsumption billableConsumption) {
		this.billableConsumption = billableConsumption;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public UomType getUomType() {
		return uomType;
	}

	public void setUomType(UomType uomType) {
		this.uomType = uomType;
	}

	public UomUnit getUomUnit() {
		return uomUnit;
	}

	public void setUomUnit(UomUnit uomUnit) {
		this.uomUnit = uomUnit;
	}

	public Integer getConsumableQuantity() {
		return consumableQuantity;
	}

	public void setConsumableQuantity(Integer consumableQuantity) {
		this.consumableQuantity = consumableQuantity;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public BillableConsumptionDetail() {
		super();
	}

	public BillableConsumptionDetail(Integer id) {
		super();
		this.id = id;
	}

	public BillableConsumptionDetail(Batch batch, UomType uomType, UomUnit uomUnit, Integer consumableQuantity,
			Status status) {
		super();
		this.batch = batch;
		this.uomType = uomType;
		this.uomUnit = uomUnit;
		this.consumableQuantity = consumableQuantity;
		this.status = status;
	}
	
}