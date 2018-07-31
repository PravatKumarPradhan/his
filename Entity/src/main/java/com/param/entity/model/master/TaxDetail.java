package com.param.entity.model.master;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.param.entity.model.base.BaseEntity;

@Entity
@Table(name = "m_tax_detail")
@NamedQuery(name = "TaxDetail.findAll", query = "SELECT t FROM TaxDetail t")
public class TaxDetail extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "tax_component_id")
	private TaxComponent taxComponent;

	@ManyToOne
	@JoinColumn(name = "tax_id")
	private Tax tax;

	@Column(name = "tax_percentage")
	private Double taxPercentage;

	public TaxDetail() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TaxComponent getTaxComponent() {
		return taxComponent;
	}

	public void setTaxComponent(TaxComponent taxComponent) {
		this.taxComponent = taxComponent;
	}

	public Tax getTax() {
		return tax;
	}

	public void setTax(Tax tax) {
		this.tax = tax;
	}

	public Double getTaxPercentage() {
		return taxPercentage;
	}

	public void setTaxPercentage(Double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	public TaxDetail(TaxComponent taxComponent, Double taxPercentage) {
		super();
		this.taxComponent = taxComponent;
		this.taxPercentage = taxPercentage;
	}
	
	
	public void updateTaxDetail(TaxComponent taxComponent, Double taxPercentage) {
		this.taxComponent = taxComponent;
		this.taxPercentage = taxPercentage;
	}
}