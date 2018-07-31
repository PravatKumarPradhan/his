package com.param.billing.global.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.global.model.CountryMaster;

@NamedQueries({

	@NamedQuery(name = "GET_CURRENCY_MASTER_LIST_BY_NAME", 
			query = "SELECT currencyMaster.currencyId AS currencyId "
			+ " FROM CurrencyMaster currencyMaster "
			+ " WHERE LOWER(currencyMaster.currencyName)=:currencyName OR currencyMaster.currencyName=:currencyName "
			+ " AND currencyMaster.orgnisationId=:orgId "),
	
	@NamedQuery(name = "GET_CURRENCY_MASTER_BY_ID", 
			query = "SELECT currencyMaster.currencyId AS currencyId, "
			+ "currencyMaster.currencyName AS currencyName," 
			+ "currencyMaster.status AS status,"
			+ "currencyMaster.currencyCode AS currencyCode, "
			+ "cntryMster.countryName AS countryName, "
			+ "cntryMster.countryCode AS countryCode, "
			+ "currencyMaster.countryId AS countryId "
			+ " FROM CurrencyMaster currencyMaster "
			+ "INNER JOIN currencyMaster.countryMaster cntryMster  "
			+ " WHERE currencyMaster.currencyId=:currencyId"
			+ " AND currencyMaster.orgnisationId=:orgId") ,
	
	@NamedQuery(name = "GET_CURRENCY_MASTER_LIST", 
		query = "SELECT currencyMaster.currencyId AS currencyId, "
		+ "currencyMaster.currencyName AS currencyName," 
		+ "currencyMaster.status AS status,"
		+ "currencyMaster.currencyCode AS currencyCode, "
		+ "cntryMster.countryName AS countryName, "
		+ "cntryMster.countryCode AS countryCode "
		+ " FROM CurrencyMaster currencyMaster "
		+ " INNER JOIN currencyMaster.countryMaster cntryMster  "
		+ " WHERE currencyMaster.orgnisationId=:orgId"),
	
	@NamedQuery(name = "GET_CURRENCY_LIST_BY_NAME_NOT_ID",
		query = "SELECT currencyMaster.currencyId AS currencyId  "
		+ "FROM CurrencyMaster currencyMaster "
		+ "WHERE (LOWER(currencyMaster.currencyName)=:currencyName OR currencyMaster.currencyName=:currencyName) "
		+ "AND currencyMaster.currencyId !=:currencyId")

	})

@Entity
@Table(name="m_currency_master",schema="public")
@SequenceGenerator(name = "currency_master_seq", sequenceName = "public.currency_master_seq", allocationSize = 1)
public class CurrencyMaster {
	@Id
	@Column(name = "currency_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "currency_master_seq")
	private int currencyId; 
	  
	@Column(name = "currency_name")
	private String currencyName;
	  
	@Column(name = "country_id")
	private Integer countryId;
	  
	@Column(name = "currency_symbol")
	private String currencySymbol;

	@Column(name = "currency_code")
	private String currencyCode;

	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "orgnisation_id")
	private Integer orgnisationId;

	@Column(name = "status")
	private char status;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "updated_by")
	private Integer updatedBy;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id", insertable = false, updatable = false)
	CountryMaster countryMaster;

	public int getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getOrgnisationId() {
		return orgnisationId;
	}

	public void setOrgnisationId(Integer orgnisationId) {
		this.orgnisationId = orgnisationId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public CountryMaster getCountryMaster() {
		return countryMaster;
	}

	public void setCountryMaster(CountryMaster countryMaster) {
		this.countryMaster = countryMaster;
	}
	
	
}
