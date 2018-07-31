package com.param.entity.model.master;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity(name = "Organization")
@Table(name = "m_organization_master", schema = "public")
public class Organization implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "organization_id", unique = true, nullable = false)
	private Integer organizationId;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "organization_address", length = 2147483647)
	private String organizationAddress;

	@Column(name = "organization_code", length = 50)
	private String organizationCode;

	@Column(name = "organization_contact", length = 2147483647)
	private String organizationContact;

	@Column(name = "organization_desc", length = 255)
	private String organizationDesc;

	@Column(name = "organization_email_id", length = 100)
	private String organizationEmailId;

	@Column(name = "organization_logo", length = 200)
	private String organizationLogo;

	@Column(name = "organization_name", length = 200)
	private String organizationName;

	@Column(name = "post_code")
	private Integer postCode;

	@Column(length = 1)
	private String status;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	private Timestamp updatedDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_id")
	private Area area;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_area_id")
	private Area organization;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id")
	private City city;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_city_id")
	private City organizationCity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id")
	private Country country;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_country_id")
	private Country organizationCountry;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id")
	private District district;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_district_id")
	private District organizationDistrict;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id")
	private State state;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_state_id")
	private State organizationState;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
	private List<Unit> unitsList;

	public Organization() {
	}

	public Integer getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
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

	public String getOrganizationAddress() {
		return this.organizationAddress;
	}

	public void setOrganizationAddress(String organizationAddress) {
		this.organizationAddress = organizationAddress;
	}

	public String getOrganizationCode() {
		return this.organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public String getOrganizationContact() {
		return this.organizationContact;
	}

	public void setOrganizationContact(String organizationContact) {
		this.organizationContact = organizationContact;
	}

	public String getOrganizationDesc() {
		return this.organizationDesc;
	}

	public void setOrganizationDesc(String organizationDesc) {
		this.organizationDesc = organizationDesc;
	}

	public String getOrganizationEmailId() {
		return this.organizationEmailId;
	}

	public void setOrganizationEmailId(String organizationEmailId) {
		this.organizationEmailId = organizationEmailId;
	}

	public String getOrganizationLogo() {
		return this.organizationLogo;
	}

	public void setOrganizationLogo(String organizationLogo) {
		this.organizationLogo = organizationLogo;
	}

	public String getOrganizationName() {
		return this.organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public Integer getPostCode() {
		return this.postCode;
	}

	public void setPostCode(Integer postCode) {
		this.postCode = postCode;
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

	public Area getArea() {
		return this.area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Area getOrganization() {
		return this.organization;
	}

	public void setOrganization(Area organization) {
		this.organization = organization;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public City getOrganizationCity() {
		return this.organizationCity;
	}

	public void setOrganizationCity(City organizationCity) {
		this.organizationCity = organizationCity;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Country getOrganizationCountry() {
		return this.organizationCountry;
	}

	public void setOrganizationCountry(Country organizationCountry) {
		this.organizationCountry = organizationCountry;
	}

	public District getDistrict() {
		return this.district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public District getOrganizationDistrict() {
		return this.organizationDistrict;
	}

	public void setOrganizationDistrict(District organizationDistrict) {
		this.organizationDistrict = organizationDistrict;
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public State getOrganizationState() {
		return this.organizationState;
	}

	public void setOrganizationState(State organizationState) {
		this.organizationState = organizationState;
	}

	public List<Unit> getUnitsList() {
		return this.unitsList;
	}

	public void setUnitsList(List<Unit> unitsList) {
		this.unitsList = unitsList;
	}

	public Unit addUnitsList(Unit unitsList) {
		getUnitsList().add(unitsList);
		unitsList.setOrganization(this);

		return unitsList;
	}

	public Unit removeUnitsList(Unit unitsList) {
		getUnitsList().remove(unitsList);
		unitsList.setOrganization(null);

		return unitsList;
	}

}
