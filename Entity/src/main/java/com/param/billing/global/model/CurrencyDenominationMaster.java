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

	@NamedQuery(name = "GET_CURRENCY_DENOMINATIONMASTER_BY_NAME", 
			query = "SELECT currencyMaster.currencyDenominationId AS currencyDenominationId "
			+ " FROM CurrencyDenominationMaster currencyMaster "
			+ " WHERE LOWER(currencyMaster.denominationDesc)=:denominationName OR currencyMaster.denominationDesc=:denominationName "
			+ " AND currencyMaster.orgnisationId=:orgId "),
	
	@NamedQuery(name = "GET_CURRENCY_DENOMINATIONMASTER_BY_ID", 
			query = "SELECT currencyMaster.currencyDenominationId AS currencyDenominationId, "
			+ "currencyMaster.status AS status,"
			+ "currencyMaster.denominatonUnit AS denominatonUnit, "
			+ "currencyMaster.denominationDesc AS denominationDesc, "
			+ "currencyMaster.denominationCode AS denominationCode, "
			+ "currencyMaster.lowestDenomination AS lowestDenomination, "
			+ "currencyMaster.currencyId AS currencyId "
			+ " FROM CurrencyDenominationMaster currencyMaster "			
			+ " WHERE currencyMaster.currencyDenominationId=:currencyId"
			+ " AND currencyMaster.orgnisationId=:orgId") ,
	
	@NamedQuery(name = "GET_CURRENCY_DENOMINATIONMASTER_LIST", 
			query = "SELECT currencyDenoMaster.currencyDenominationId AS currencyDenominationId, "
					+ "currencyDenoMaster.status AS status,"
					+ "currencyDenoMaster.denominatonUnit AS denominatonUnit, "
					+ "currencyDenoMaster.denominationDesc AS denominationDesc, "
					+ "currencyDenoMaster.denominationCode AS denominationCode, "
					+ "currencyDenoMaster.lowestDenomination AS lowestDenomination, "
					+ " currMaster.currencyName AS currencyName ,"
					+ "currMaster.currencyId AS currencyId "
					+ " FROM CurrencyDenominationMaster currencyDenoMaster "
					+ " INNER JOIN currencyDenoMaster.currencyMaster currMaster "
					+ " WHERE currencyDenoMaster.orgnisationId=:orgId"),
	
	@NamedQuery(name = "GET_CURRENCY_DENOMINATIONMASTER_BY_NAME_NOT_BY_ID",
		query = "SELECT currencyMaster.currencyDenominationId AS currencyDenominationId  "
		+ "FROM CurrencyDenominationMaster currencyMaster "
		+ "WHERE (LOWER(currencyMaster.denominationDesc)=:denominationName OR currencyMaster.denominationDesc=:denominationName) "
		+ "AND currencyMaster.currencyDenominationId !=:currencyId")

	})
@Entity
@Table(name="t_currency_denomination_master", schema="public")
@SequenceGenerator(name = "currency_denomination_master_seq", sequenceName = "public.currency_denomination_master_seq", allocationSize = 1)
public class CurrencyDenominationMaster {
	@Id
	@Column(name = "currency_denomination_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "currency_denomination_master_seq")
	private int currencyDenominationId;
	  
	@Column(name = "currency_id")
	private int currencyId;
	  
	@Column(name = "rate_per_doller")
	private int ratePerDoller;
	  
	@Column(name = "date")
	private Date date;

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
	
	@Column(name = "denominaton_unit")
	private Float denominatonUnit;
	
	@Column(name = "denomination_code")
	private String denominationCode;
	
	@Column(name = "denomination_desc")
	private String denominationDesc;
	
	@Column(name = "lowest_denomination")
	private Character lowestDenomination;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "currency_id", insertable = false, updatable = false)
	CurrencyMaster currencyMaster;

	public int getCurrencyDenominationId() {
		return currencyDenominationId;
	}

	public void setCurrencyDenominationId(int currencyDenominationId) {
		this.currencyDenominationId = currencyDenominationId;
	}

	public int getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}

	public int getRatePerDoller() {
		return ratePerDoller;
	}

	public void setRatePerDoller(int ratePerDoller) {
		this.ratePerDoller = ratePerDoller;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public Float getDenominatonUnit() {
		return denominatonUnit;
	}

	public void setDenominatonUnit(Float denominatonUnit) {
		this.denominatonUnit = denominatonUnit;
	}

	public String getDenominationCode() {
		return denominationCode;
	}

	public void setDenominationCode(String denominationCode) {
		this.denominationCode = denominationCode;
	}

	public String getDenominationDesc() {
		return denominationDesc;
	}

	public void setDenominationDesc(String denominationDesc) {
		this.denominationDesc = denominationDesc;
	}

	public Character getLowestDenomination() {
		return lowestDenomination;
	}

	public void setLowestDenomination(Character lowestDenomination) {
		this.lowestDenomination = lowestDenomination;
	}
	
	
	
}
