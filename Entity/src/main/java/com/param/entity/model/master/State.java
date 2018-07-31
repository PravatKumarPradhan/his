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

@Entity(name = "State")
@Table(name = "m_state_master", schema = "public")
public class State implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "state_id", unique = true, nullable = false)
	private Integer stateId;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "state_code", length = 50)
	private String stateCode;

	@Column(name = "state_name", length = 50)
	private String stateName;

	@Column(length = 1)
	private String status;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	private Timestamp updatedDate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "state")
	private List<Area> areasList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "state")
	private List<City> citiesList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "state")
	private List<District> districtsList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "state")
	private List<KinDetail> kinDetailsList;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id")
	private Country country;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id")
	private Organization organization;

	public State() {
	}

	public Integer getStateId() {
		return this.stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
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

	public String getStateCode() {
		return this.stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
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
		areasList.setState(this);

		return areasList;
	}

	public Area removeAreasList(Area areasList) {
		getAreasList().remove(areasList);
		areasList.setState(null);

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
		citiesList.setState(this);

		return citiesList;
	}

	public City removeCitiesList(City citiesList) {
		getCitiesList().remove(citiesList);
		citiesList.setState(null);

		return citiesList;
	}

	public List<District> getDistrictsList() {
		return this.districtsList;
	}

	public void setDistrictsList(List<District> districtsList) {
		this.districtsList = districtsList;
	}

	public District addDistrictsList(District districtsList) {
		getDistrictsList().add(districtsList);
		districtsList.setState(this);

		return districtsList;
	}

	public District removeDistrictsList(District districtsList) {
		getDistrictsList().remove(districtsList);
		districtsList.setState(null);

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
		kinDetailsList.setState(this);

		return kinDetailsList;
	}

	public KinDetail removeKinDetailsList(KinDetail kinDetailsList) {
		getKinDetailsList().remove(kinDetailsList);
		kinDetailsList.setState(null);

		return kinDetailsList;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}
