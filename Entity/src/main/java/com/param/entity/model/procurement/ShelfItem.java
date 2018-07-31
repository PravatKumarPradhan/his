package com.param.entity.model.procurement;

import java.io.Serializable;
import java.util.Date;

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
import com.param.entity.model.master.Rack;
import com.param.entity.model.master.Shelf;
import com.param.entity.model.master.Store;

@Entity(name = "ShelfItem")
@Table(name = "t_shelf_item", schema = "procurement")
public class ShelfItem extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
	private Store store;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "batch_id")
	private Batch batch;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shelf_id")
	private Shelf shelf;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rack_id")
	private Rack rack;

	@Column(name = "remark")
	private String remark;

	public ShelfItem() {
		super();
	}

	public ShelfItem(Store store, Shelf shelf, Rack rack) {
		super();
		this.store = store;
		this.shelf = shelf;
		this.rack = rack;
	}

	public ShelfItem(Shelf shelf, Batch batch, Store store, Rack rack, String remark) {
		this(store, shelf, rack);
		this.batch = batch;
		this.remark = remark;
	}

	public void updateShelfItem(Shelf shelf, Rack rack, Boolean status) {
		this.shelf = shelf;
		this.rack = rack;
		this.isActive = status;
		this.updatedBy = this.getUser();
		this.updatedDate = new Date();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public Shelf getShelf() {
		return shelf;
	}

	public void setShelf(Shelf shelf) {
		this.shelf = shelf;
	}

	public Rack getRack() {
		return rack;
	}

	public void setRack(Rack rack) {
		this.rack = rack;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}