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

import com.param.entity.model.base.BaseEntity;

@Entity(name = "StoreType")
@Table(name = "m_store_type", schema = "public")
public class StoreType extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@Column(nullable = false, length = 10)
	private String code;

	@Column(nullable = false, length = 100)
	private String type;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "storeType")
	private List<Store> storeList;

	public StoreType() {
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

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Store> getStoreList() {
		return this.storeList;
	}

	public void setStoreList(List<Store> storeList) {
		this.storeList = storeList;
	}

	public Store addStoreList(Store storeList) {
		getStoreList().add(storeList);
		storeList.setStoreType(this);

		return storeList;
	}

	public Store removeStoreList(Store storeList) {
		getStoreList().remove(storeList);
		storeList.setStoreType(null);

		return storeList;
	}

}
