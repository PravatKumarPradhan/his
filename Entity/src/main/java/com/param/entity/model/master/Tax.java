package com.param.entity.model.master;

import java.io.Serializable;
import java.util.ArrayList;
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

import com.param.entity.model.adt.Priority;
import com.param.entity.model.base.BaseEntity;

@Entity(name = "Tax")
@Table(name = "m_tax", schema = "public")
public class Tax extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@Column(nullable = false, length = 10)
	private String code;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(name = "tax_percentage", nullable = false)
	private Double taxPercentage;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tax", cascade = CascadeType.ALL)
	private List<TaxDetail> taxDetailList;
	

	public Tax() {
	}

	public Tax(Integer id) {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getTaxPercentage() {
		return this.taxPercentage;
	}

	public void setTaxPercentage(Double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}
	
	public Tax(String code, String name, Double taxPercentage) {
		super();
		this.code = code;
		this.name = name;
		this.taxPercentage = taxPercentage;
	}
	
	public void updateTax(String code, String name, Double taxPercentage) {
		this.code = code;
		this.name = name;
		this.taxPercentage = taxPercentage;
	}


	public List<TaxDetail> getTaxDetailList() {
		return taxDetailList;
	}

	public void setTaxDetailsList(List<TaxDetail> taxDetailsList) {
		this.taxDetailList = taxDetailsList;
	}

	public TaxDetail addTaxDetailList(TaxDetail taxDetail) {
		if (getTaxDetailList() == null)
			this.taxDetailList = new ArrayList<TaxDetail>();
		
		getTaxDetailList().add(taxDetail);
		taxDetail.setTax(this);

		return taxDetail;
	}

	public TaxDetail removeTaxDetail(TaxDetail taxDetail) {
		getTaxDetailList().remove(taxDetail);
		taxDetail.setTax(this);

		return taxDetail;
	}

}
