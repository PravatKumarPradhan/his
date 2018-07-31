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

@Entity(name = "FormulationType")
@Table(name = "m_formulation_type", schema = "public")

public class FormulationType extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@Column(nullable = false, length = 10)
	private String code;

	@Column(nullable = false, length = 100)
	private String type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_category_id")
	private ProductCategory productCategory;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "formulationType")
	private List<Item> itemsList;

	public FormulationType() {
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

	public ProductCategory getProductCategory() {
		return this.productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public List<Item> getItemsList() {
		return this.itemsList;
	}

	public void setItemsList(List<Item> itemsList) {
		this.itemsList = itemsList;
	}

	public Item addItemsList(Item itemsList) {
		getItemsList().add(itemsList);
		itemsList.setFormulationType(this);

		return itemsList;
	}

	public Item removeItemsList(Item itemsList) {
		getItemsList().remove(itemsList);
		itemsList.setFormulationType(null);

		return itemsList;
	}

}