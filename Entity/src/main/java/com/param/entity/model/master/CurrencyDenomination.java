package com.param.entity.model.master;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "CurrencyDenomination")
@Table(name = "t_currency_denomination_master", schema = "public")
public class CurrencyDenomination implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="currency_denomination_id", unique=true, nullable=false)
	private Integer currencyDenominationId;

	@Column(name="created_by")
	private Integer createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;

	private Timestamp date;

	@Column(name="orgnisation_id")
	private Integer orgnisationId;

	@Column(name="rate_per_doller")
	private double ratePerDoller;

	@Column(length=1)
	private String status;

	@Column(name="unit_id")
	private Integer unitId;

	@Column(name="updated_by")
	private Integer updatedBy;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="currency_id")
	private Currency currency;

	public CurrencyDenomination() {
	}

	public Integer getCurrencyDenominationId() {
		return this.currencyDenominationId;
	}

	public void setCurrencyDenominationId(Integer currencyDenominationId) {
		this.currencyDenominationId = currencyDenominationId;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Integer getOrgnisationId() {
		return this.orgnisationId;
	}

	public void setOrgnisationId(Integer orgnisationId) {
		this.orgnisationId = orgnisationId;
	}

	public double getRatePerDoller() {
		return this.ratePerDoller;
	}

	public void setRatePerDoller(double ratePerDoller) {
		this.ratePerDoller = ratePerDoller;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getUnitId() {
		return this.unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Currency getCurrency() {
		return this.currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

}