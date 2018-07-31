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

@Entity(name = "AssetType")
@Table(name = "m_asset_type", schema = "public")
public class AssetType extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false, length=10)
	private String code;

	@Column(nullable=false, length=100)
	private String type;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="assetType")
	private List<AssetCategory> assetCategoriesList;

	public AssetType() {
	}
	
	public AssetType(Integer id) {
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

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<AssetCategory> getAssetCategoriesList() {
		return this.assetCategoriesList;
	}

	public void setAssetCategoriesList(List<AssetCategory> assetCategoriesList) {
		this.assetCategoriesList = assetCategoriesList;
	}

	public AssetCategory addAssetCategoriesList(AssetCategory assetCategoriesList) {
		getAssetCategoriesList().add(assetCategoriesList);
		assetCategoriesList.setAssetType(this);

		return assetCategoriesList;
	}

	public AssetCategory removeAssetCategoriesList(AssetCategory assetCategoriesList) {
		getAssetCategoriesList().remove(assetCategoriesList);
		assetCategoriesList.setAssetType(null);

		return assetCategoriesList;
	}

}
