package com.param.entity.model.master;

import java.io.Serializable;
import java.util.List;

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

@Entity(name = "AssetCategory")
@Table(name = "m_asset_category", schema = "public")
public class AssetCategory extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false, length=100)
	private String category;

	@Column(nullable=false, length=10)
	private String code;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="asset_type_id")
	private AssetType assetType;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="assetCategory")
	private List<ProductCategory> productCategoryList;

	public AssetCategory() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public AssetType getAssetType() {
		return this.assetType;
	}

	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}

	public List<ProductCategory> getProductCategoryList() {
		return this.productCategoryList;
	}

	public void setProductCategoryList(List<ProductCategory> productCategoryList) {
		this.productCategoryList = productCategoryList;
	}

	public ProductCategory addProductCategoryList(ProductCategory productCategoryList) {
		getProductCategoryList().add(productCategoryList);
		productCategoryList.setAssetCategory(this);

		return productCategoryList;
	}

	public ProductCategory removeProductCategoryList(ProductCategory productCategoryList) {
		getProductCategoryList().remove(productCategoryList);
		productCategoryList.setAssetCategory(null);

		return productCategoryList;
	}

}
