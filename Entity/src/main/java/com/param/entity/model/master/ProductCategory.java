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

@Entity(name = "ProductCategory")
@Table(name = "m_product_category", schema = "public")
public class ProductCategory extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@Column(nullable = false, length = 100)
	private String category;

	@Column(nullable = false, length = 10)
	private String code;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productCategory")
	private List<FormulationType> formulationTypesList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productCategory")
	private List<Generic> genericsList;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productCategory")
	private List<StoreProductCategoryMapper> storeMapperList;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "asset_category_id")
	private AssetCategory assetCategory;

	public ProductCategory() {
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

	public List<FormulationType> getFormulationTypesList() {
		return this.formulationTypesList;
	}

	public void setFormulationTypesList(List<FormulationType> formulationTypesList) {
		this.formulationTypesList = formulationTypesList;
	}

	public FormulationType addFormulationTypesList(FormulationType formulationTypesList) {
		getFormulationTypesList().add(formulationTypesList);
		formulationTypesList.setProductCategory(this);

		return formulationTypesList;
	}

	public FormulationType removeFormulationTypesList(FormulationType formulationTypesList) {
		getFormulationTypesList().remove(formulationTypesList);
		formulationTypesList.setProductCategory(null);

		return formulationTypesList;
	}

	public List<Generic> getGenericsList() {
		return this.genericsList;
	}

	public void setGenericsList(List<Generic> genericsList) {
		this.genericsList = genericsList;
	}

	public Generic addGenericsList(Generic genericsList) {
		getGenericsList().add(genericsList);
		genericsList.setProductCategory(this);

		return genericsList;
	}

	public Generic removeGenericsList(Generic genericsList) {
		getGenericsList().remove(genericsList);
		genericsList.setProductCategory(null);

		return genericsList;
	}

	public AssetCategory getAssetCategory() {
		return this.assetCategory;
	}

	public void setAssetCategory(AssetCategory assetCategory) {
		this.assetCategory = assetCategory;
	}

	public List<StoreProductCategoryMapper> getStoreMapperList() {
		return storeMapperList;
	}

	public void setStoreMapperList(List<StoreProductCategoryMapper> storeMapperList) {
		this.storeMapperList = storeMapperList;
	}

}
