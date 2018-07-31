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

@Entity(name = "PharmacologicalClassification")
@Table(name = "m_pharmacological_classification", schema = "public")
public class PharmacologicalClassification extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@Column(length = 100)
	private String classification;

	@Column(length = 10)
	private String code;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pharmacologicalClassification")
	private List<Item> itemsList;

	public PharmacologicalClassification() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClassification() {
		return this.classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Item> getItemsList() {
		return this.itemsList;
	}

	public void setItemsList(List<Item> itemsList) {
		this.itemsList = itemsList;
	}

	public Item addItemsList(Item itemsList) {
		getItemsList().add(itemsList);
		itemsList.setPharmacologicalClassification(this);

		return itemsList;
	}

	public Item removeItemsList(Item itemsList) {
		getItemsList().remove(itemsList);
		itemsList.setPharmacologicalClassification(null);

		return itemsList;
	}

}
