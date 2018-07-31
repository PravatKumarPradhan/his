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

		@NamedQuery(name = "GET_STATE_LIST", query = "SELECT stateMaster.stateId as stateId, "
				+ "stateMaster.stateCode as stateCode, " + "stateMaster.stateName as stateName, "
				+ "stateMaster.countryId as countryId, " + "stateMaster.status as status, "
				+ "ctr.countryName as countryName "
				+ "FROM StateMaster as stateMaster INNER JOIN stateMaster.countryMaster ctr "
				+ "WHERE stateMaster.organizationId=:orgId "
				+ "ORDER BY stateMaster.stateId DESC"),

		@NamedQuery(name = "GET_STATE_LIST_BY_NAME", query = "SELECT stateMaster.stateId as stateId, "
				+ "stateMaster.stateCode as stateCode, " + "stateMaster.stateName as stateName, "
				+ "stateMaster.status as status, " + "stateMaster.countryId as countryId "
				+ "FROM StateMaster as stateMaster WHERE LOWER(stateMaster.stateName)=:stateName OR stateMaster.stateName=:stateName"),

		@NamedQuery(name = "GET_STATE_LIST_BY_ID", query = "SELECT stateMaster.stateId as stateId, "
				+ "stateMaster.stateCode as stateCode, " + "stateMaster.stateName as stateName, "
				+ "stateMaster.status as status, " + "stateMaster.countryId as countryId "
				+ "FROM StateMaster as stateMaster WHERE stateMaster.stateId=:stateId"),

		@NamedQuery(name = "GET_STATE_LIST_BY_NAME_NOT_ID", query = "SELECT stateMaster.stateId as stateId, "
				+ "stateMaster.stateCode as stateCode, " + "stateMaster.stateName as stateName, "
				+ "stateMaster.status as status, " + "stateMaster.countryId as countryId "
				+ "FROM StateMaster as stateMaster WHERE (LOWER(stateMaster.stateName)=:stateName OR stateMaster.stateName=:stateName) AND stateMaster.stateId !=:stateId"),

		@NamedQuery(name = "GET_ACTIVE_STATE_LIST", query = "SELECT stateMaster.stateId as stateId, "
				+ "stateMaster.stateName as stateName "
				+ "FROM StateMaster as stateMaster WHERE stateMaster.status='A'"), 

		@NamedQuery(name = "GET_STATE_BY_COUNTRY_ID",
					query = "SELECT stateMaster.stateId as stateId, "
					+ "stateMaster.stateCode as stateCode, " 
					+ "stateMaster.stateName as stateName, "
					+ "stateMaster.countryId as countryId, " 
					+ "stateMaster.status as status "
					+ "FROM StateMaster as stateMaster "
					+ "WHERE stateMaster.countryId= :countryId "
					+ "ORDER BY stateMaster.stateId DESC")

})
				
				
				
@Entity
@Table(name = "m_state_master", schema = "public")
@SequenceGenerator(name = "state_master_seq", sequenceName = "public.state_master_seq", allocationSize = 1)
public class StateMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "state_master_seq")
	@Column(name = "state_id")
	private int stateId;

	@Column(name = "state_code")
	private String stateCode;

	@Column(name = "state_name")
	private String stateName;

	@Column(name = "status")
	private char status;

	@Column(name = "country_id")
	private Integer countryId;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id", insertable = false, updatable = false)
	private CountryMaster countryMaster;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "stateMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CityMaster> listCityMaster;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "stateMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DistrictMaster> listDistrictMaster;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "stateMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AreaMaster> listAreaMaster;

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public List<CityMaster> getListCityMaster() {
		return listCityMaster;
	}

	public void setListCityMaster(List<CityMaster> listCityMaster) {
		this.listCityMaster = listCityMaster;
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

	public List<AreaMaster> getListAreaMaster() {
		return listAreaMaster;
	}

	public void setListAreaMaster(List<AreaMaster> listAreaMaster) {
		this.listAreaMaster = listAreaMaster;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
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

	public CountryMaster getCountryMaster() {
		return countryMaster;
	}

	public void setCountryMaster(CountryMaster countryMaster) {
		this.countryMaster = countryMaster;
	}

	public List<DistrictMaster> getListDistrictMaster() {
		return listDistrictMaster;
	}

	public void setListDistrictMaster(List<DistrictMaster> listDistrictMaster) {
		this.listDistrictMaster = listDistrictMaster;
	}
}
