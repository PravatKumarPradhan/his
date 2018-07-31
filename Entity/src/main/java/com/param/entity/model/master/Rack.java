package com.param.entity.model.master;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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

import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.inventory.StoreIndentDetail;

@Entity(name = "Rack")
@Table(name = "m_rack",schema="public")
public class Rack extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@Column(nullable = false, length = 10)
	private String code;

	@Column(nullable = false, length = 500)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
	private Store store;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rack", cascade = CascadeType.ALL)
	private List<Shelf> shelfList;

	
	public Rack() {
		super();
	}

	public Rack(String code, String name, Store store) {
		super();
		this.code = code;
		this.name = name;
		this.store = store;
	}
	public void updateRack(String code, String name, Store store, Boolean isActive) {
		this.code = code;
		this.name = name;
		this.store = store;
		this.isActive = isActive;
	}
	
	public Rack(Integer id) {
		super();
		this.id = id;
	}


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
	
	public List<Shelf> getShelfList() {
		return shelfList;
	}

	public void setShelfList(List<Shelf> shelfList) {
		this.shelfList = shelfList;
	}


	public Shelf addShelfList(Shelf shelf) {
		if (getShelfList() == null)
			this.shelfList = new ArrayList<Shelf>();

		getShelfList().add(shelf);
		shelf.setRack(this);

		return shelf;
	}

	public Shelf removeShelfList(Shelf shelf) {
		getShelfList().remove(shelf);
		shelf.setRack(null);

		return shelf;
	}
	
}
