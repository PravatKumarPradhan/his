package com.param.entity.model.pharmacy;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.inventory.Batch;
import com.param.entity.model.master.Store;

@Entity(name="BatchAllocation")
@Table(name="t_batch_allocation", schema="pharmacy")
public class BatchAllocation extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="batch_id")
	private Batch batch;

	private Integer quantity;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="store_id")
	private Store store;

	@Column(name="temp_bill_id")
	private Long tempBillId;

	public BatchAllocation() {
	}

	public BatchAllocation(Long tempBillId, Store store, Batch batch, Integer quantity) {
		super();
		this.batch = batch;
		this.quantity = quantity;
		this.store = store;
		this.tempBillId = tempBillId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Long getTempBillId() {
		return tempBillId;
	}

	public void setTempBillId(Long tempBillId) {
		this.tempBillId = tempBillId;
	}
}