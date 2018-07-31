package com.param.entity.model.master;

import java.io.Serializable;
import java.sql.Timestamp;
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

@Entity(name = "Country")
@Table(name = "m_country_master", schema = "public")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="country_id", unique=true, nullable=false)
	private Integer countryId;

	@Column(name="country_calling_code", length=20)
	private String countryCallingCode;

	@Column(name="country_code", length=50)
	private String countryCode;

	@Column(name="country_initial", length=50)
	private String countryInitial;

	@Column(name="country_name", length=50)
	private String countryName;

	@Column(name="created_by")
	private Integer createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;

	@Column(length=1)
	private String status;

	@Column(name="updated_by")
	private Integer updatedBy;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	@OneToMany(mappedBy="country", fetch=FetchType.LAZY)
	private List<Area> areasList;

	@OneToMany(mappedBy="country", fetch=FetchType.LAZY)
	private List<City> citiesList;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="organization_id")
	private Organization organization;

	@OneToMany(mappedBy="country", fetch=FetchType.LAZY)
	private List<Currency> currenciesList;

	@OneToMany(mappedBy="country", fetch=FetchType.LAZY)
	private List<District> districtsList;

	@OneToMany(mappedBy="country", fetch=FetchType.LAZY)
	private List<KinDetail> kinDetailsList;

	@OneToMany(mappedBy="country", fetch=FetchType.LAZY)
	private List<State> stateList;

	public Country() {
	}

	public Integer getCountryId() {
		return this.countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getCountryCallingCode() {
		return this.countryCallingCode;
	}

	public void setCountryCallingCode(String countryCallingCode) {
		this.countryCallingCode = countryCallingCode;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryInitial() {
		return this.countryInitial;
	}

	public void setCountryInitial(String countryInitial) {
		this.countryInitial = countryInitial;
	}

	public String getCountryName() {
		return this.countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public List<Area> getAreasList() {
		return this.areasList;
	}

	public void setAreasList(List<Area> areasList) {
		this.areasList = areasList;
	}

	public Area addAreasList(Area areasList) {
		getAreasList().add(areasList);
		areasList.setCountry(this);

		return areasList;
	}

	public Area removeAreasList(Area areasList) {
		getAreasList().remove(areasList);
		areasList.setCountry(null);

		return areasList;
	}

	public List<City> getCitiesList() {
		return this.citiesList;
	}

	public void setCitiesList(List<City> citiesList) {
		this.citiesList = citiesList;
	}

	public City addCitiesList(City citiesList) {
		getCitiesList().add(citiesList);
		citiesList.setCountry(this);

		return citiesList;
	}

	public City removeCitiesList(City citiesList) {
		getCitiesList().remove(citiesList);
		citiesList.setCountry(null);

		return citiesList;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public List<Currency> getCurrenciesList() {
		return this.currenciesList;
	}

	public void setCurrenciesList(List<Currency> currenciesList) {
		this.currenciesList = currenciesList;
	}

	public Currency addCurrenciesList(Currency currenciesList) {
		getCurrenciesList().add(currenciesList);
		currenciesList.setCountry(this);

		return currenciesList;
	}

	public Currency removeCurrenciesList(Currency currenciesList) {
		getCurrenciesList().remove(currenciesList);
		currenciesList.setCountry(null);

		return currenciesList;
	}

	public List<District> getDistrictsList() {
		return this.districtsList;
	}

	public void setDistrictsList(List<District> districtsList) {
		this.districtsList = districtsList;
	}

	public District addDistrictsList(District districtsList) {
		getDistrictsList().add(districtsList);
		districtsList.setCountry(this);

		return districtsList;
	}

	public District removeDistrictsList(District districtsList) {
		getDistrictsList().remove(districtsList);
		districtsList.setCountry(null);

		return districtsList;
	}

	public List<KinDetail> getKinDetailsList() {
		return this.kinDetailsList;
	}

	public void setKinDetailsList(List<KinDetail> kinDetailsList) {
		this.kinDetailsList = kinDetailsList;
	}

	public KinDetail addKinDetailsList(KinDetail kinDetailsList) {
		getKinDetailsList().add(kinDetailsList);
		kinDetailsList.setCountry(this);

		return kinDetailsList;
	}

	public KinDetail removeKinDetailsList(KinDetail kinDetailsList) {
		getKinDetailsList().remove(kinDetailsList);
		kinDetailsList.setCountry(null);

		return kinDetailsList;
	}

	public List<State> getStateList() {
		return this.stateList;
	}

	public void setStateList(List<State> stateList) {
		this.stateList = stateList;
	}

	public State addStateList(State stateList) {
		getStateList().add(stateList);
		stateList.setCountry(this);

		return stateList;
	}

	public State removeStateList(State stateList) {
		getStateList().remove(stateList);
		stateList.setCountry(null);

		return stateList;
	}
}