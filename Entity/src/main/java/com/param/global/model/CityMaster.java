package com.param.global.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries({

		@NamedQuery(name = "GET_CITY_LIST", query = "SELECT cityMaster.cityId as cityId, "
				+ "cityMaster.cityCode as cityCode, " + "cityMaster.cityName as cityName, "
				+ "cityMaster.status as status, " + "cityMaster.stateId as stateId, " + "state.stateName as stateName, "
				+ "cityMaster.districtId as districtId, " + "district.districtName as districtName, "
				+ "cityMaster.countryId as countryId, " + "ctr.countryName as countryName "
				+ "FROM CityMaster as cityMaster INNER JOIN cityMaster.countryMaster ctr INNER JOIN cityMaster.stateMaster state INNER JOIN cityMaster.districtMaster district "
				+ "WHERE cityMaster.organizationId=:orgId ORDER BY cityMaster.cityId DESC"),

		@NamedQuery(name = "GET_CITY_LIST_BY_NAME", query = "SELECT cityMaster.cityId as cityId, "
				+ "cityMaster.cityName as cityName "
				+ "FROM CityMaster as cityMaster WHERE LOWER(cityMaster.cityName)=:cityName OR cityMaster.cityName=:cityName"),

		@NamedQuery(name = "GET_CITY_LIST_BY_ID", query = "SELECT cityMaster.cityId as cityId, "
				+ "cityMaster.cityName as cityName, " + "cityMaster.cityCode as cityCode, "
				+ "cityMaster.status as status, " + "cityMaster.stateId as stateId, "
				+ "cityMaster.countryId as countryId, " + "cityMaster.districtId as districtId "
				+ "FROM CityMaster as cityMaster WHERE cityMaster.cityId=:cityId"),

		@NamedQuery(name = "GET_CITY_LIST_BY_NAME_NOT_ID", query = "SELECT cityMaster.cityId as cityId, "
				+ "cityMaster.cityCode as cityCode, " + "cityMaster.cityName as cityName "
				+ "FROM CityMaster as cityMaster WHERE cityMaster.cityId!=:cityId AND (LOWER(cityMaster.cityName)=:cityName OR cityMaster.cityName=:cityName)"),

		@NamedQuery(name = "GET_ACTIVE_CITY_LIST", query = "SELECT cityMaster.cityId as cityId, "
				+ "cityMaster.cityName as cityName " + "FROM CityMaster as cityMaster WHERE cityMaster.status='A'"),

		@NamedQuery(name = "GET_ACTIVE_DISTRICT_LIST_BY_STATE_ID", query = "SELECT distMaster.districtId as districtId, "
				+ "distMaster.districtName as districtName "
				+ "FROM DistrictMaster as distMaster WHERE distMaster.status='A' AND distMaster.stateId=:stateId"),
				
		@NamedQuery(name = "GET_CITY_LIST_BY_STATE_ID",
					query = "SELECT cityMaster.cityId as cityId, "
							+ "cityMaster.cityName as cityName, "
							+ "cityMaster.cityCode as cityCode, "
							+ "cityMaster.status as status, " 
							+ "cityMaster.stateId as stateId, "
							+ "cityMaster.countryId as countryId, "
							+ "cityMaster.districtId as districtId "
							+ "FROM CityMaster as cityMaster "
							+ "WHERE cityMaster.stateId=:stateId ")

})

@Entity
@Table(name = "m_city_master", schema = "public")
@SequenceGenerator(name = "city_master_seq", sequenceName = "public.city_master_seq", allocationSize = 1)
public class CityMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_master_seq")
	@Column(name = "city_id")
	private int cityId;

	@Column(name = "country_id")
	private Integer countryId;

	@Column(name = "state_id")
	private Integer stateId;

	@Column(name = "district_id")
	private Integer districtId;

	@Column(name = "city_code")
	private String cityCode;

	@Column(name = "city_name")
	private String cityName;

	@Column(name = "status")
	private char status;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "organization_id")
	private Integer organizationId;

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id", insertable = false, updatable = false)
	private DistrictMaster districtMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id", insertable = false, updatable = false)
	private StateMaster stateMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id", insertable = false, updatable = false)
	private CountryMaster countryMaster;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cityMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AreaMaster> listAreaMaster;

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public StateMaster getStateMaster() {
		return stateMaster;
	}

	public void setStateMaster(StateMaster stateMaster) {
		this.stateMaster = stateMaster;
	}

	public CountryMaster getCountryMaster() {
		return countryMaster;
	}

	public void setCountryMaster(CountryMaster countryMaster) {
		this.countryMaster = countryMaster;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public List<AreaMaster> getListAreaMaster() {
		return listAreaMaster;
	}

	public void setListAreaMaster(List<AreaMaster> listAreaMaster) {
		this.listAreaMaster = listAreaMaster;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public DistrictMaster getDistrictMaster() {
		return districtMaster;
	}

	public void setDistrictMaster(DistrictMaster districtMaster) {
		this.districtMaster = districtMaster;
	}

}
