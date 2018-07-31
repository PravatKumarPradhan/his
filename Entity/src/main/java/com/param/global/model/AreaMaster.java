package com.param.global.model;

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

@NamedQueries({

		@NamedQuery(name = "GET_AREA_LIST", query = "SELECT areaMaster.areaId as areaId, "
				+ "areaMaster.areaCode as areaCode, " + "areaMaster.areaName as areaName, "
				+ "areaMaster.status as status, " + "areaMaster.countryId as countryId, "
				+ "areaMaster.stateId as stateId, " + "areaMaster.districtId as districtId, "
				+ "areaMaster.cityId as cityId, " + "ctr.countryName as countryName, "
				+ "state.stateName as stateName, " + "dist.districtName as districtName, "
				+ "city.cityName as cityName, " + "areaMaster.postCode as postCode " + "FROM AreaMaster as areaMaster "
				+ "INNER JOIN areaMaster.countryMaster ctr " + "INNER JOIN areaMaster.stateMaster state "
				+ "INNER JOIN areaMaster.districtMaster dist " + "INNER JOIN areaMaster.cityMaster city "
				+ "WHERE areaMaster.organizationId=:orgId " + "ORDER BY areaMaster.areaId DESC"),

		@NamedQuery(name = "GET_AREA_LIST_BY_NAME", query = "SELECT areaMaster.areaId as areaId, "
				+ "areaMaster.areaCode as areaCode, " + "areaMaster.areaName as areaName, "
				+ "areaMaster.status as status, " + "areaMaster.countryId as countryId "
				+ "FROM AreaMaster as areaMaster WHERE LOWER(areaMaster.areaName)=:areaName OR areaMaster.areaName=:areaName"),

		@NamedQuery(name = "GET_AREA_LIST_BY_ID", query = "SELECT areaMaster.areaId as areaId, "
				+ "areaMaster.areaCode as areaCode, " + "areaMaster.areaName as areaName, "
				+ "areaMaster.status as status, " + "areaMaster.countryId as countryId, "
				+ "areaMaster.stateId as stateId, " + " areaMaster.districtId as districtId, "
				+ "areaMaster.cityId as cityId, " + "areaMaster.postCode as postCode "
				+ "FROM AreaMaster as areaMaster WHERE areaMaster.areaId=:areaId"),

		@NamedQuery(name = "GET_AREA_LIST_BY_NAME_NOT_ID", query = "SELECT areaMaster.areaId as areaId, "
				+ "areaMaster.areaCode as areaCode, " + "areaMaster.areaName as areaName, "
				+ "areaMaster.status as status, " + "areaMaster.countryId as countryId "
				+ "FROM AreaMaster as areaMaster WHERE (LOWER(areaMaster.areaName)=:areaName OR areaMaster.areaName=:areaName) AND areaMaster.areaId !=:areaId"),

		@NamedQuery(name = "GET_ACTIVE_AREA_LIST", query = "SELECT areaMaster.areaId as areaId, "
				+ "areaMaster.areaName as areaName " + "FROM AreaMaster as areaMaster WHERE areaMaster.status='A'"),

		@NamedQuery(name = "GET_CITY_LIST_BY_DISTRICT_ID", query = "SELECT cityMaster.cityId as cityId, "
				+ "cityMaster.cityName as cityName "
				+ "FROM CityMaster as cityMaster WHERE cityMaster.status='A' AND cityMaster.districtId=:districtId") })

@Entity
@Table(name = "m_area_master", schema = "public")
@SequenceGenerator(name = "area_master_seq", sequenceName = "public.area_master_seq", allocationSize = 1)
public class AreaMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "area_master_seq")
	@Column(name = "area_id")
	private int areaId;

	@Column(name = "area_name")
	private String areaName;

	@Column(name = "area_code")
	private String areaCode;

	@Column(name = "country_id")
	private Integer countryId;

	@Column(name = "state_id")
	private Integer stateId;

	@Column(name = "district_id")
	private Integer districtId;

	@Column(name = "city_id")
	private Integer cityId;

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

	@Column(name = "post_code")
	private String postCode;

	@Column(name = "organization_id")
	private Integer organizationId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id", insertable = false, updatable = false)
	private CityMaster cityMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id", insertable = false, updatable = false)
	private DistrictMaster districtMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id", insertable = false, updatable = false)
	private StateMaster stateMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id", insertable = false, updatable = false)
	private CountryMaster countryMaster;
	
/*	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private OrganizationMaster organizationMaster;*/

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}


	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
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

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

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

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public DistrictMaster getDistrictMaster() {
		return districtMaster;
	}

	public void setDistrictMaster(DistrictMaster districtMaster) {
		this.districtMaster = districtMaster;
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

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
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

	public CityMaster getCityMaster() {
		return cityMaster;
	}

	public void setCityMaster(CityMaster cityMaster) {
		this.cityMaster = cityMaster;
	}

}
