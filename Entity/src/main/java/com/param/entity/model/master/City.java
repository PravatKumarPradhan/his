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

@Entity(name = "City")
@Table(name = "m_city_master", schema = "public")

public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="city_id", unique=true, nullable=false)
	private Integer cityId;

	@Column(name="city_code", length=50)
	private String cityCode;

	@Column(name="city_name", length=50)
	private String cityName;

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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="city")
	private List<Area> areasList;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="country_id")
	private Country country;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="district_id")
	private District district;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="organization_id")
	private Organization organization;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="state_id")
	private State state;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="city")
	private List<KinDetail> kinDetailsList;

	public City() {
	}

	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityCode() {
		return this.cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
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
		areasList.setCity(this);

		return areasList;
	}

	public Area removeAreasList(Area areasList) {
		getAreasList().remove(areasList);
		areasList.setCity(null);

		return areasList;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public District getDistrict() {
		return this.district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public List<KinDetail> getKinDetailsList() {
		return this.kinDetailsList;
	}

	public void setKinDetailsList(List<KinDetail> kinDetailsList) {
		this.kinDetailsList = kinDetailsList;
	}

	public KinDetail addKinDetailsList(KinDetail kinDetailsList) {
		getKinDetailsList().add(kinDetailsList);
		kinDetailsList.setCity(this);

		return kinDetailsList;
	}

	public KinDetail removeKinDetailsList(KinDetail kinDetailsList) {
		getKinDetailsList().remove(kinDetailsList);
		kinDetailsList.setCity(null);

		return kinDetailsList;
	}

}