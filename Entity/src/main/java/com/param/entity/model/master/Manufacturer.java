package com.param.entity.model.master;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.param.entity.model.base.BaseEntity;

@Entity(name = "Manufacturer")
@Table(name = "m_manufacturer", schema = "public")
public class Manufacturer extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@Column(length = 1000)
	private String address;

	@Column(length = 10)
	private String code;

	@Column(length = 100)
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "manufacturer")
	private List<Item> itemsList;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="manufacturer",  cascade = CascadeType.ALL)
	private List<Vendor> vendors;

	public Manufacturer() {
	}

	public Manufacturer(Integer id) {
		super();
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public List<Item> getItemsList() {
		return this.itemsList;
	}

	public void setItemsList(List<Item> itemsList) {
		this.itemsList = itemsList;
	}
	
	public List<Vendor> getVendors() {
		return vendors;
	}

	public void setVendors(List<Vendor> vendors) {
		this.vendors = vendors;
	}

	public Item addItemsList(Item itemsList) {
		getItemsList().add(itemsList);
		itemsList.setManufacturer(this);

		return itemsList;
	}

	public Item removeItemsList(Item itemsList) {
		getItemsList().remove(itemsList);
		itemsList.setManufacturer(null);

		return itemsList;
	}

}
