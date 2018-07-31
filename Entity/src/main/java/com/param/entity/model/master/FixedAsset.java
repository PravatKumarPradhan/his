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
import com.param.entity.model.procurement.ItemRequestDetail;

@Entity
@Table(name = "m_fixed_asset_master")
// @NamedQuery(name = "FixedAssetMaster.findAll", query = "SELECT f FROM
// FixedAssetMaster f")
public class FixedAsset extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@Column(nullable = false, length = 50)
	private String name;

	@Column(name = "item_code", length = 50)
	private String itemCode;

	@Column(name = "product_code", length = 50)
	private String productCode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fixed_asset_type_id")
	private FixedAssetType fixedAssetType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_category_id", nullable = false)
	private ProductCategory productCategory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manufacturer_id", nullable = false)
	private Manufacturer manufacturer;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fixedAsset", cascade = CascadeType.ALL)
	private List<ItemRequestDetail> itemRequestDetailList;

	public FixedAsset() {
	}

	public FixedAsset(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public FixedAssetType getFixedAssetType() {
		return fixedAssetType;
	}

	public void setFixedAssetType(FixedAssetType fixedAssetType) {
		this.fixedAssetType = fixedAssetType;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public List<ItemRequestDetail> getItemRequestDetailList() {
		return itemRequestDetailList;
	}

	public void setItemRequestDetailList(List<ItemRequestDetail> itemRequestDetailList) {
		this.itemRequestDetailList = itemRequestDetailList;
	}

}