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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.adt.master.global.model.NationalityMaster;

@Entity
@Table(name="t_patient_details",schema="patient")
@SequenceGenerator(name="patient_details_seq",sequenceName="patient.patient_details_seq",allocationSize=1)
public class PatientDetails {

	@Id
	@Column(name = "patient_details_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_details_seq")
	private int patientDetailsId;

	@Column(name = "patient_id")
	private Integer patientId;

	@Column(name = "organization_id")
	private Integer organizationId;

	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "nationality_id")
	private Integer nationalityId;

	@Column(name = "race_id")
	private Integer raceId;

	@Column(name = "marital_status_id")
	private Integer maritalStatusId;

	@Column(name = "address")
	private String address;

	@Column(name = "country_id")
	private Integer countryId;

	@Column(name = "state_id")
	private Integer stateId;

	@Column(name = "district_id")
	private Integer districtId;

	@Column(name = "city_id")
	private Integer cityId;

	@Column(name = "area_id")
	private Integer areaId;

	@Column(name = "zip_code")
	private String zipCode;

	@Column(name = "phone_code")
	private String phoneCode;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "company_address")
	private String companyAddress;

	@Column(name = "company_country_id")
	private Integer companyCountryId;

	@Column(name = "company_state_id")
	private Integer companyStateId;

	@Column(name = "company_district_id")
	private Integer companyDistrictId;

	@Column(name = "company_city_id")
	private Integer companyCityId;

	@Column(name = "company_area_id")
	private Integer companyAreaId;

	@Column(name = "company_zip_code")
	private String companyZipCode;

	@Column(name = "company_mobile_number")
	private String companyMobileNumber;

	@Column(name = "permanent_address")
	private String permanentAddress;

	@Column(name = "permanent_country_id")
	private Integer permanentCountryId;

	@Column(name = "permanent_state_id")
	private Integer permanentStateId;

	@Column(name = "permanent_district_id")
	private Integer permanentDistrictId;

	@Column(name = "permanent_city_id")
	private Integer permanentCityId;

	@Column(name = "permanent_area_id")
	private Integer permanentAreaId;

	@Column(name = "permanent_zip_code")
	private String permanentZipCode;

	@Column(name = "permanent_phone_code")
	private String permanentPhoneCode;

	@Column(name = "permanent_phone_number")
	private String permanentPhoneNumber;

	@Column(name = "status")
	private char status;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updatedDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id", insertable = false, updatable = false)
	private PatientRegistration patientRegistration;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id", insertable = false, updatable = false)
	private CountryMaster countryMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id", insertable = false, updatable = false)
	private StateMaster stateMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id", insertable = false, updatable = false)
	private CityMaster cityMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_id", insertable = false, updatable = false)
	private AreaMaster areaMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id", insertable = false, updatable = false)
	private DistrictMaster districtMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nationality_id", insertable = false, updatable = false)
	private NationalityMaster nationalityMaster;

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public Integer getPermanentCountryId() {
		return permanentCountryId;
	}

	public void setPermanentCountryId(Integer permanentCountryId) {
		this.permanentCountryId = permanentCountryId;
	}

	public Integer getPermanentStateId() {
		return permanentStateId;
	}

	public void setPermanentStateId(Integer permanentStateId) {
		this.permanentStateId = permanentStateId;
	}

	public Integer getPermanentDistrictId() {
		return permanentDistrictId;
	}

	public void setPermanentDistrictId(Integer permanentDistrictId) {
		this.permanentDistrictId = permanentDistrictId;
	}

	public Integer getPermanentCityId() {
		return permanentCityId;
	}

	public void setPermanentCityId(Integer permanentCityId) {
		this.permanentCityId = permanentCityId;
	}

	public Integer getPermanentAreaId() {
		return permanentAreaId;
	}

	public void setPermanentAreaId(Integer permanentAreaId) {
		this.permanentAreaId = permanentAreaId;
	}

	public String getPermanentZipCode() {
		return permanentZipCode;
	}

	public void setPermanentZipCode(String permanentZipCode) {
		this.permanentZipCode = permanentZipCode;
	}

	public String getPermanentPhoneCode() {
		return permanentPhoneCode;
	}

	public void setPermanentPhoneCode(String permanentPhoneCode) {
		this.permanentPhoneCode = permanentPhoneCode;
	}

	public String getPermanentPhoneNumber() {
		return permanentPhoneNumber;
	}

	public void setPermanentPhoneNumber(String permanentPhoneNumber) {
		this.permanentPhoneNumber = permanentPhoneNumber;
	}

	public String getCompanyMobileNumber() {
		return companyMobileNumber;
	}

	public void setCompanyMobileNumber(String companyMobileNumber) {
		this.companyMobileNumber = companyMobileNumber;
	}

	public void setPatientDetailsId(int patientDetailsId) {
		this.patientDetailsId = patientDetailsId;
	}

	public Integer getPatientDetailsId() {
		return patientDetailsId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getNationalityId() {
		return nationalityId;
	}

	public void setNationalityId(Integer nationalityId) {
		this.nationalityId = nationalityId;
	}

	public Integer getRaceId() {
		return raceId;
	}

	public void setRaceId(Integer raceId) {
		this.raceId = raceId;
	}

	public Integer getMaritalStatusId() {
		return maritalStatusId;
	}

	public void setMaritalStatusId(Integer maritalStatusId) {
		this.maritalStatusId = maritalStatusId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public Integer getCompanyCountryId() {
		return companyCountryId;
	}

	public void setCompanyCountryId(Integer companyCountryId) {
		this.companyCountryId = companyCountryId;
	}

	public Integer getCompanyStateId() {
		return companyStateId;
	}

	public void setCompanyStateId(Integer companyStateId) {
		this.companyStateId = companyStateId;
	}

	public Integer getCompanyDistrictId() {
		return companyDistrictId;
	}

	public void setCompanyDistrictId(Integer companyDistrictId) {
		this.companyDistrictId = companyDistrictId;
	}

	public Integer getCompanyCityId() {
		return companyCityId;
	}

	public void setCompanyCityId(Integer companyCityId) {
		this.companyCityId = companyCityId;
	}

	public Integer getCompanyAreaId() {
		return companyAreaId;
	}

	public void setCompanyAreaId(Integer companyAreaId) {
		this.companyAreaId = companyAreaId;
	}

	public String getCompanyZipCode() {
		return companyZipCode;
	}

	public void setCompanyZipCode(String companyZipCode) {
		this.companyZipCode = companyZipCode;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = (status == '\u0000') ? 'A' : status;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}


}