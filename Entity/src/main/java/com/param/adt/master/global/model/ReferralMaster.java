package com.param.adt.master.global.model;

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

import com.param.global.model.AreaMaster;
import com.param.global.model.CityMaster;
import com.param.global.model.CountryMaster;
import com.param.global.model.DistrictMaster;
import com.param.global.model.StateMaster;
@NamedQueries({
	
	@NamedQuery(name="GET_REFERRAL_MASTER_LIST",
			query="SELECT rm.referralId as referralId, "
					+ "rm.desc as desc, "
					+ "rm.id as id, "
					+ "rm.status as status, "
					+ "rm.countryId as countryId, "
					+ "rm.stateId as stateId, "
					+ "rm.districtId as districtId, "
					+ "rm.areaId as areaId, "
					+ "rm.cityId as cityId, "
					+ "rm.code as code, "
					+ "rm.referralAddress as referralAddress, "
					+ "rm.referralEmail as referralEmail, "
					+ "rm.referralContact as referralContact, "
					+ "rm.referralContactPerson as referralContactPerson, "
					+ "rtm.desc as referralTypeDesc, "
					+ "ctr.countryName as countryName, "
					+ "state.stateName as stateName, "
					+ "dist.districtName as districtName, "
					+ "city.cityName as cityName, "
					+ "area.areaName as areaName, "
					+ "area.postCode as postCode "
					+ "FROM ReferralMaster as rm "
					+ "INNER JOIN rm.referralTypeMaster as rtm "
					+ "INNER JOIN rm.countryMaster as ctr "
					+ "INNER JOIN rm.stateMaster as state "
					+ "INNER JOIN rm.districtMaster as dist "
					+ "INNER JOIN rm.cityMaster as city "
					+ "INNER JOIN rm.areaMaster as area "
					+ "WHERE rm.organizationId=:orgId "
					+ "ORDER BY rm.referralId DESC"),
	
	@NamedQuery(name="GET_REFERRAL_MASTER_LIST_BY_NAME",query="SELECT rm.referralId as referralId, "
					+ "rm.desc as desc "
					+ "FROM ReferralMaster as rm "
					+ "WHERE LOWER(rm.desc)=:desc OR rm.desc=:desc"),
	
	@NamedQuery(name="GET_REFERRAL_MASTER_LIST_BY_NAME_NOT_ID",query="SELECT rm.referralId as referralId, "
			+ "rm.desc as desc "
			+ "FROM ReferralMaster as rm "
			+ "WHERE rm.referralId!=:referralId AND (LOWER(rm.desc)=:desc OR rm.desc=:desc)"),
	
	@NamedQuery(name="GET_REFERRAL_MASTER_LIST_BY_ID",
	query="SELECT rm.referralId as referralId, "
			+ "rm.desc as desc, "
			+ "rm.id as id, "
			+ "rm.status as status, "
			+ "rm.countryId as countryId, "
			+ "rm.stateId as stateId, "
			+ "rm.districtId as districtId, "
			+ "rm.areaId as areaId, "
			+ "rm.cityId as cityId, "
			+ "rm.code as code, "
			+ "rm.referralAddress as referralAddress, "
			+ "rm.referralEmail as referralEmail, "
			+ "rm.referralContact as referralContact, "
			+ "rm.referralContactPerson as referralContactPerson "
			+ "FROM ReferralMaster as rm WHERE rm.referralId=:referralId"),
	
	@NamedQuery(name="GET_ACTIVE_REFERRAL_MASTER_LIST",query="SELECT rm.referralId as referralId, "
			+ "rm.desc as desc "
			+ "FROM ReferralMaster as rm "
			+ "WHERE rm.status='A'"),
	
	@NamedQuery(name="GET_ACTIVE_AREA_BY_CITY_ID",query="SELECT area.areaId as areaId, "
			+ "area.areaName as areaName, "
			+ "area.postCode as postCode "
			+ "FROM AreaMaster as area "
			+ "WHERE area.cityId=:cityId AND area.status='A'"),
	
	@NamedQuery(name="GET_REFERRAL_LIST_BY_REFERRAL_TYPE_ID",query="SELECT rtm.referralId as referralId, "
			+ "rtm.desc as desc "
			+ "FROM ReferralMaster as rtm "
			+ "WHERE rtm.status='A'  "
			+ "AND rtm.organizationId=:organizationId "
			+ "AND rtm.id=:referralTypeId ")
	
})


@Entity
@Table(name = "m_referral_master", schema = "adt")
@SequenceGenerator(name="referral_master_seq", sequenceName="adt.referral_master_seq", allocationSize=1)
public class ReferralMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "referral_master_seq")
	@Column(name = "referral_id")
	private int referralId;
	
	@Column(name = "referral_type_id")
	private Integer id;
	
	@Column(name = "referral_code")
	private String code;
	
	@Column(name = "referral_name")
	private String desc;
	
	@Column(name = "referral_address")
	private String referralAddress;
	
	@Column(name = "referral_email")
	private String referralEmail;
	
	@Column(name = "referral_contact")
	private String referralContact;
	
	@Column(name = "referral_contact_person")
	private String referralContactPerson;
	
	@Column(name = "status")
	private char status;
	
	@Column(name = "created_by")
	private int createdBy;
	
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="country_id")
	private Integer countryId; 
	
	@Column(name="state_id")
	private Integer stateId; 
	
	@Column(name="district_id")
	private Integer districtId; 
	
	@Column(name="city_id")
	private Integer cityId; 
	
	@Column(name="area_id")
	private Integer areaId; 
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private OrganizationMaster organizationMaster;
	
	@ManyToOne
	@JoinColumn(name = "referral_type_id", insertable = false, updatable = false)
	private ReferralTypeMaster referralTypeMaster;
	
	@ManyToOne
	@JoinColumn(name = "country_id", insertable = false, updatable = false)
	private CountryMaster countryMaster;
	
	@ManyToOne
	@JoinColumn(name = "state_id", insertable = false, updatable = false)
	private StateMaster stateMaster;
	
	@ManyToOne
	@JoinColumn(name = "district_id", insertable = false, updatable = false)
	private DistrictMaster districtMaster;
	
	@ManyToOne
	@JoinColumn(name = "city_id", insertable = false, updatable = false)
	private CityMaster cityMaster;
	
	@ManyToOne
	@JoinColumn(name = "area_id", insertable = false, updatable = false)
	private AreaMaster areaMaster;
	
	
	
	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public OrganizationMaster getOrganizationMaster() {
		return organizationMaster;
	}

	public void setOrganizationMaster(OrganizationMaster organizationMaster) {
		this.organizationMaster = organizationMaster;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}



	public CityMaster getCityMaster() {
		return cityMaster;
	}

	public void setCityMaster(CityMaster cityMaster) {
		this.cityMaster = cityMaster;
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

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public CountryMaster getCountryMaster() {
		return countryMaster;
	}

	public void setCountryMaster(CountryMaster countryMaster) {
		this.countryMaster = countryMaster;
	}

	public StateMaster getStateMaster() {
		return stateMaster;
	}

	public void setStateMaster(StateMaster stateMaster) {
		this.stateMaster = stateMaster;
	}

	public DistrictMaster getDistrictMaster() {
		return districtMaster;
	}

	public void setDistrictMaster(DistrictMaster districtMaster) {
		this.districtMaster = districtMaster;
	}

	public AreaMaster getAreaMaster() {
		return areaMaster;
	}

	public void setAreaMaster(AreaMaster areaMaster) {
		this.areaMaster = areaMaster;
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

	public int getReferralId() {
		return referralId;
	}

	public void setReferralId(int referralId) {
		this.referralId = referralId;
	}

	
	public ReferralTypeMaster getReferralTypeMaster() {
		return referralTypeMaster;
	}

	public void setReferralTypeMaster(ReferralTypeMaster referralTypeMaster) {
		this.referralTypeMaster = referralTypeMaster;
	}

	public String getReferralCode() {
		return code;
	}

	public void setReferralCode(String code) {
		this.code = code;
	}

	public String getReferralName() {
		return desc;
	}

	public void setReferralName(String desc) {
		this.desc = desc;
	}

	public String getReferralAddress() {
		return referralAddress;
	}

	public void setReferralAddress(String referralAddress) {
		this.referralAddress = referralAddress;
	}

	public String getReferralEmail() {
		return referralEmail;
	}

	public void setReferralEmail(String referralEmail) {
		this.referralEmail = referralEmail;
	}

	public String getReferralContact() {
		return referralContact;
	}

	public void setReferralContact(String referralContact) {
		this.referralContact = referralContact;
	}

	public String getReferralContactPerson() {
		return referralContactPerson;
	}

	public void setReferralContactPerson(String referralContactPerson) {
		this.referralContactPerson = referralContactPerson;
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
	
	
	
}
