package com.param.entity.model.inventory;

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
import com.param.entity.model.master.Store;

@Entity(name = "StoreStock")
@Table(name = "t_store_stock", schema = "inventory")
public class StoreStock extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@Column(name = "available_qty")
	private Integer availableQty;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id", nullable = false)
	private Store store;

	@Column(name = "t_qty")
	private Integer tQty;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "batch_id", nullable = false)
	private Batch batch;

	public StoreStock() {
	}

	public StoreStock(Integer availableQty, Store store, Integer tQty, Batch batch) {
		super();
		this.availableQty = availableQty;
		this.store = store;
		this.tQty = tQty;
		this.batch = batch;
	}

	public StoreStock(Integer tQty) {
		super();
		this.tQty = tQty;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAvailableQty() {
		return availableQty;
	}

	public void setAvailableQty(Integer availableQty) {
		this.availableQty = availableQty;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Integer gettQty() {
		return tQty;
	}

	public void settQty(Integer tQty) {
		this.tQty = tQty;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public StoreStock(Integer availableQty, Store store, Batch batch) {
		super();
		this.availableQty = availableQty;
		this.store = store;
		this.batch = batch;
	}
	
	public StoreStock(Integer id, Integer availableQty, Store store, Batch batch) {
		super();
		this.id = id;
		this.availableQty = availableQty;
		this.store = store;
		this.batch = batch;
	}
	
	public StoreStock(Integer id, Store store, Integer availableQty, Integer tQty, Batch batch) {
		super();
		this.id = id;
		this.store = store;
		this.availableQty = availableQty;
		this.tQty = tQty;
		this.batch = batch;
	}
	
}