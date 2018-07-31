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

@Entity(name = "District")
@Table(name = "m_district_master", schema = "public")
public class District implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "district_id", unique = true, nullable = false)
	private Integer districtId;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "district_code", length = 50)
	private String districtCode;

	@Column(name = "district_name", length = 255)
	private String districtName;

	@Column(length = 1)
	private String status;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	private Timestamp updatedDate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "district")
	private List<Area> areasList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "district")
	private List<City> citiesList;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id")
	private Country country;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id")
	private Organization organization;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id")
	private State state;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "district")
	private List<KinDetail> kinDetailsList;

	public District() {
	}

	public Integer getDistrictId() {
		return this.districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
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

	public String getDistrictCode() {
		return this.districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictName() {
		return this.districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
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
		areasList.setDistrict(this);

		return areasList;
	}

	public Area removeAreasList(Area areasList) {
		getAreasList().remove(areasList);
		areasList.setDistrict(null);

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
		citiesList.setDistrict(this);

		return citiesList;
	}

	public City removeCitiesList(City citiesList) {
		getCitiesList().remove(citiesList);
		citiesList.setDistrict(null);

		return citiesList;
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
		kinDetailsList.setDistrict(this);

		return kinDetailsList;
	}

	public KinDetail removeKinDetailsList(KinDetail kinDetailsList) {
		getKinDetailsList().remove(kinDetailsList);
		kinDetailsList.setDistrict(null);

		return kinDetailsList;
	}

}
