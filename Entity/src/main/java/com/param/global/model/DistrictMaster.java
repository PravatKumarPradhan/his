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

		@NamedQuery(name = "GET_DISTRICT_LIST", query = "SELECT districtMaster.districtId as districtId, "
				+ "districtMaster.districtCode as districtCode, " + "districtMaster.districtName as districtName, "
				+ "districtMaster.countryId as countryId, " + "districtMaster.stateId as stateId, "
				+ "districtMaster.status as status, " + "ctr.countryName as countryName, "
				+ "state.stateName as stateName " + "FROM DistrictMaster as districtMaster "
				+ "INNER JOIN districtMaster.countryMaster ctr " + "INNER JOIN districtMaster.stateMaster state "
						+ "WHERE districtMaster.organizationId=:orgId "
						+ "ORDER BY districtMaster.districtId DESC"),

		@NamedQuery(name = "GET_DISTRICT_LIST_BY_ID", query = "SELECT districtMaster.districtId as districtId, "
				+ "districtMaster.districtCode as districtCode, " + "districtMaster.districtName as districtName, "
				+ "districtMaster.countryId as countryId, " + "districtMaster.stateId as stateId, "
				+ "districtMaster.status as status "
				+ "FROM DistrictMaster as districtMaster WHERE districtMaster.districtId=:districtId"),

		@NamedQuery(name = "GET_DISTRICT_LIST_BY_NAME", query = "SELECT districtMaster.districtId as districtId, "
				+ "districtMaster.districtCode as districtCode, " + "districtMaster.districtName as districtName, "
				+ "districtMaster.countryId as countryId, " + "districtMaster.stateId as stateId, "
				+ "districtMaster.status as status "
				+ "FROM DistrictMaster as districtMaster WHERE LOWER(districtMaster.districtName)=:districtName OR districtMaster.districtName=:districtName"),

		@NamedQuery(name = "GET_DISTRICT_LIST_BY_NAME_NOT_ID", query = "SELECT districtMaster.districtId as districtId, "
				+ "districtMaster.districtCode as districtCode, " + "districtMaster.districtName as districtName, "
				+ "districtMaster.countryId as countryId, " + "districtMaster.stateId as stateId, "
				+ "districtMaster.status as status "
				+ "FROM DistrictMaster as districtMaster WHERE (LOWER(districtMaster.districtName)=:districtName OR districtMaster.districtName=:districtName) AND districtMaster.districtId!=:districtId"),

		@NamedQuery(name = "GET_STATE_LIST_BY_COUNTRY_ID", query = "SELECT stateMaster.stateId as stateId, "
				+ "stateMaster.stateName as stateName "
				+ "FROM StateMaster as stateMaster WHERE stateMaster.countryId=:countryId AND stateMaster.status='A'")

})

@Entity
@Table(name = "m_district_master", schema = "public")
@SequenceGenerator(name = "district_master_seq", sequenceName = "public.district_master_seq", allocationSize = 1)
public class DistrictMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "district_master_seq")
	@Column(name = "district_id")
	private int districtId;

	@Column(name = "country_id")
	private Integer countryId;

	@Column(name = "state_id")
	private Integer stateId;

	@Column(name = "district_code")
	private String districtCode;

	@Column(name = "district_name")
	private String districtName;

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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id", insertable = false, updatable = false)
	private StateMaster stateMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id", insertable = false, updatable = false)
	private CountryMaster countryMaster;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "districtMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CityMaster> listCityMaster;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "districtMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AreaMaster> listAreaMaster;

	@Column(name="organization_id")
	private Integer organizationId;
	
	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
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

	public CountryMaster getCountryMaster() {
		return countryMaster;
	}

	public void setCountryMaster(CountryMaster countryMaster) {
		this.countryMaster = countryMaster;
	}

	public List<AreaMaster> getListAreaMaster() {
		return listAreaMaster;
	}

	public void setListAreaMaster(List<AreaMaster> listAreaMaster) {
		this.listAreaMaster = listAreaMaster;
	}

	public int getDistrictId() {
		return districtId;
	}

	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
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

	public StateMaster getStateMaster() {
		return stateMaster;
	}

	public void setStateMaster(StateMaster stateMaster) {
		this.stateMaster = stateMaster;
	}

	public List<CityMaster> getListCityMaster() {
		return listCityMaster;
	}

	public void setListCityMaster(List<CityMaster> listCityMaster) {
		this.listCityMaster = listCityMaster;
	}

}
