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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.procurement.ShelfItem;
import com.param.global.model.VisitMaster;

@Entity(name = "Store")
@Table(name = "m_store", schema = "public")
public class Store extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "store_id", unique=true, nullable=false)
	private Integer storeId;

	@Column(name = "store_code", length = 100)
	private String storeCode;

	@Column(name = "store_name", length = 500)
	private String storeName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_type_id")
	private StoreType storeType;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
	private List<Rack> rack;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
	private List<StoreItemMapper> storeItemMapperList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
	private List<UserStoreMapper> userStoreMappersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="store", cascade = CascadeType.ALL)
	private List<ShelfItem> shelfItem;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "visit_type_id", nullable = false)
	private VisitMaster visitMaster;
	
	public Store() {
		
	}

	public Store(Integer id) {
		super();
		this.storeId = id;
	}

	public Integer getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getStoreCode() {
		return this.storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getStoreName() {
		return this.storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public StoreType getStoreType() {
		return this.storeType;
	}

	public void setStoreType(StoreType storeType) {
		this.storeType = storeType;
	}

	public List<StoreItemMapper> getStoreItemMapperList() {
		return this.storeItemMapperList;
	}

	public void setStoreItemMapperList(List<StoreItemMapper> storeItemMapperList) {
		this.storeItemMapperList = storeItemMapperList;
	}

	public List<Rack> getRack() {
		return rack;
	}

	public void setRack(List<Rack> rack) {
		this.rack = rack;
	}

	public List<ShelfItem> getShelfItem() {
		return shelfItem;
	}

	public void setShelfItem(List<ShelfItem> shelfItem) {
		this.shelfItem = shelfItem;
	}
	
	public VisitMaster getVisitMaster() {
		return visitMaster;
	}

	public void setVisitMaster(VisitMaster visitMaster) {
		this.visitMaster = visitMaster;
	}

	public StoreItemMapper addStoreItemMapperList(StoreItemMapper storeItemMapperList) {
		getStoreItemMapperList().add(storeItemMapperList);
		storeItemMapperList.setStore(this);

		return storeItemMapperList;
	}

	public StoreItemMapper removeStoreItemMapperList(StoreItemMapper storeItemMapperList) {
		getStoreItemMapperList().remove(storeItemMapperList);
		storeItemMapperList.setStore(null);

		return storeItemMapperList;
	}

	public List<UserStoreMapper> getUserStoreMappersList() {
		return this.userStoreMappersList;
	}

	public void setUserStoreMappersList(List<UserStoreMapper> userStoreMappersList) {
		this.userStoreMappersList = userStoreMappersList;
	}

	public UserStoreMapper addUserStoreMappersList(UserStoreMapper userStoreMappersList) {
		getUserStoreMappersList().add(userStoreMappersList);
		userStoreMappersList.setStore(this);

		return userStoreMappersList;
	}

	public UserStoreMapper removeUserStoreMappersList(UserStoreMapper userStoreMappersList) {
		getUserStoreMappersList().remove(userStoreMappersList);
		userStoreMappersList.setStore(null);

		return userStoreMappersList;
	}

	public void updateItemRestriction(Integer storeId) {
		this.storeId = storeId;
		
	}
	
	public void updateRack(Integer storeId) {
		this.storeId = storeId;
		
	}
}
