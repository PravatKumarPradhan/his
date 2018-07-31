package com.param.global.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries({
	
	@NamedQuery(name="GET_PATIENT_DATA_FOR_UPDATE",query=
			 "SELECT patient.patientId as patientId, "
			 + "patient.uhidNumber as uhidNumber, "
			 + "patient.prefixId as prefixId, "
			 + "prefix.prefixCode as prefixCode, "
			 + "patient.firstName as firstName, "
			 + "patient.middleName as middleName, "
			 + "patient.lastName as lastName, "
			 + "patient.genderId as genderId, "
			 + "gender.code as genderCode, "
			 + "to_char(patient.dob,'DD-MM-YYYY HH:mm:ss') as dob, "
			 + "trim(to_char(EXTRACT(year from AGE(CURRENT_TIMESTAMP, patient.dob)),'999')) as ageString, "
			 + "patient.mobileNumber as mobileNumber, "
			 + "patient.countryCallingCode as countryCallingCode, "
			 + "patient.isVip as isVip, "
			 + "patient.vipRemark as vipRemark, "
			 + "patient.isBlacklist as isBlacklist, "
			 + "patient.identificationTypeId as identificationTypeId, "
			 + "identification.identificationName as identificationName, "
			 + "identification.isExpiryRequired as isExpiryRequired, "
			 + "patient.identificationNumber as identificationNumber, " 
			 + "patient.email as email, "
			 + "patient.aliseName as aliseName, "
			 + "to_char(patient.identificationExpiryDate,'MM-DD-YYYY HH:mm:ss') as identificationExpiryDate, "
			 + "patient.occupationId as occupationId, "
			 + "patientDetails.patientDetailsId as patientDetailsId,"
			 + "patientDetails.nationalityId as nationalityId, "
			 + "patientDetails.raceId as raceId, "
			 + "patientDetails.maritalStatusId as maritalStatusId, "
			 + "patientDetails.address as address, "
			 + "patientDetails.countryId as countryId, " 
			 + "patientDetails.stateId as stateId, "
			 + "patientDetails.districtId as districtId, "
			 + "patientDetails.cityId as cityId, "
			 + "patientDetails.areaId as areaId, "
			 + "patientDetails.zipCode as zipCode, "
			 + "patientDetails.phoneCode as phoneCode, "
			 + "patientDetails.phoneNumber as phoneNumber, "
			 + "patientDetails.companyName as companyName, "
			 + "patientDetails.companyAddress as companyAddress, "
			 + "patientDetails.companyCountryId as companyCountryId, "
			 + "patientDetails.companyStateId as companyStateId, "
			 + "patientDetails.companyDistrictId as companyDistrictId, "
			 + "patientDetails.companyCityId as companyCityId, "
			 + "patientDetails.companyAreaId as companyAreaId, "
			 + "patientDetails.companyZipCode as companyZipCode, "
			 + "patientDetails.companyMobileNumber as companyMobileNumber, "
			 + "patientDetails.permanentAddress as permanentAddress, "
			 + "patientDetails.permanentCountryId as permanentCountryId, "
			 + "patientDetails.permanentStateId as permanentStateId, "
			 + "patientDetails.permanentDistrictId as permanentDistrictId, "
			 + "patientDetails.permanentCityId as permanentCityId, "
			 + "patientDetails.permanentAreaId as permanentAreaId, "
			 + "patientDetails.permanentZipCode as permanentZipCode, "
			 + "patientDetails.permanentPhoneCode as permanentPhoneCode, "
			 + "patientDetails.permanentPhoneNumber as permanentPhoneNumber "
			 + "FROM PatientRegistration patient "
			 + "LEFT JOIN patient.patientDetailslist patientDetails "
			 + "INNER JOIN patient.prefixMaster prefix "
			 + "INNER JOIN patient.genderMaster gender "
			 + "LEFT JOIN patient.identificationMaster identification "
			 + "WHERE patient.organizationId=:orgId "
			 + "AND patient.unitId=:unitId "
			 + "AND patient.status='A' "
			 + "AND patientDetails.status='A' ")
})

@Entity
@Table(name="t_patient_registration_log",schema="patient")
@SequenceGenerator(name="patient_registration_log_seq",sequenceName="patient.patient_registration_log_seq",allocationSize=1)
public class PatientRegistrationLog {
	
	@Id
	@Column(name="patient_registration_log_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="patient_registration_log_seq")
	private int patientRegistrationLogId;
	
	@Column(name="patient_id")
	private Integer patientId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="uhid_number")
	private String uhidNumber;
	
	@Column(name="is_conversion")
	private Character isConversion;
	
	@Column(name="prev_uhid_number")
	private String prevUhidNumber;
	
	@Column(name="prefix_id")
	private Integer prefixId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="middle_name")
	private String middleName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="gender_id")
	private Integer genderId;
	
	@Column(name="dob")
	private Date dob;
	
	@Column(name="mobile_number")
	private String mobileNumber;
	
	@Column(name="country_calling_code")
	private String countryCallingCode;
			
	@Column(name="bar_code")
	private String barCode;
	
	@Column(name="is_vip")
	private Character isVip;
	
	@Column(name="vip_remark")
	private String vipRemark;
	
	@Column(name="is_blacklist")
	private char isBlacklist;
	
	@Column(name="identification_type_id")
	private Integer identificationTypeId;
	
	@Column(name="identification_number")
	private String identificationNumber;
	
	@Column(name="email")
	private String email;
	
	@Column(name="patient_category_id")
	private Integer patientCategoryId;
	
	@Column(name="is_unknown_reg")
	private char isUnknownReg;
	
	@Column(name="is_otc_reg")
	private char isOtcReg;
	 
	@Column(name="is_pre_reg")
	private char isPreReg;
	
	@Column(name="alise_name")
	private String aliseName;
	
	@Column(name="identification_expiry_date")
	private Date identificationExpiryDate;

	@Column(name="occupation_id")
	private Integer occupationId;
	
	@Column(name = "registration_type_id")
	private Integer registrationTypeId;

	@Column(name = "previous_id")
	private Integer previousId;
	
	@Column(name="created_by")
	private Integer createdBy;
	
	@Column(name="updated_by")
	private Integer updatedBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
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

	public Character getIsConversion() {
		return isConversion;
	}

	public void setIsConversion(Character isConversion) {
		this.isConversion = isConversion;
	}

	public String getPrevUhidNumber() {
		return prevUhidNumber;
	}

	public void setPrevUhidNumber(String prevUhidNumber) {
		this.prevUhidNumber = prevUhidNumber;
	}
	
	public int getPatientRegistrationLogId() {
		return patientRegistrationLogId;
	}

	public void setPatientRegistrationLogId(int patientRegistrationLogId) {
		this.patientRegistrationLogId = patientRegistrationLogId;
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

	public String getUhidNumber() {
		return uhidNumber;
	}

	public void setUhidNumber(String uhidNumber) {
		this.uhidNumber = uhidNumber;
	}

	public Integer getPrefixId() {
		return prefixId;
	}

	public void setPrefixId(Integer prefixId) {
		this.prefixId = prefixId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getGenderId() {
		return genderId;
	}

	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getCountryCallingCode() {
		return countryCallingCode;
	}

	public void setCountryCallingCode(String countryCallingCode) {
		this.countryCallingCode = countryCallingCode;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public Character getIsVip() {
		return isVip;
	}

	public void setIsVip(Character isVip) {
		this.isVip = isVip;
	}

	public String getVipRemark() {
		return vipRemark;
	}

	public void setVipRemark(String vipRemark) {
		this.vipRemark = vipRemark;
	}

	public char getIsBlacklist() {
		return isBlacklist;
	}

	public void setIsBlacklist(char isBlacklist) {
		this.isBlacklist = (isBlacklist == '\u0000') ? 'N' : isBlacklist;
	}

	public Integer getIdentificationTypeId() {
		return identificationTypeId;
	}

	public void setIdentificationTypeId(Integer identificationTypeId) {
		this.identificationTypeId = identificationTypeId;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPatientCategoryId() {
		return patientCategoryId;
	}

	public void setPatientCategoryId(Integer patientCategoryId) {
		this.patientCategoryId = patientCategoryId;
	}

	public char getIsUnknownReg() {
		return isUnknownReg;
	}

	public void setIsUnknownReg(char isUnknownReg) {
		this.isUnknownReg = (isUnknownReg == '\u0000') ? 'N' : isUnknownReg;
	}

	public char getIsOtcReg() {
		return isOtcReg;
	}

	public void setIsOtcReg(char isOtcReg) {
		this.isOtcReg = (isOtcReg == '\u0000') ? 'N' : isOtcReg;
	}

	public char getIsPreReg() {
		return isPreReg;
	}

	public void setIsPreReg(char isPreReg) {
		this.isPreReg = (isPreReg == '\u0000') ? 'N' : isPreReg;
	}

	public String getAliseName() {
		return aliseName;
	}

	public void setAliseName(String aliseName) {
		this.aliseName = aliseName;
	}

	public Date getIdentificationExpiryDate() {
		return identificationExpiryDate;
	}

	public void setIdentificationExpiryDate(Date identificationExpiryDate) {
		this.identificationExpiryDate = identificationExpiryDate;
	}

	public Integer getOccupationId() {
		return occupationId;
	}

	public void setOccupationId(Integer occupationId) {
		this.occupationId = occupationId;
	}

	public Integer getRegistrationTypeId() {
		return registrationTypeId;
	}

	public void setRegistrationTypeId(Integer registrationTypeId) {
		this.registrationTypeId = registrationTypeId;
	}

	public Integer getPreviousId() {
		return previousId;
	}

	public void setPreviousId(Integer previousId) {
		this.previousId = previousId;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
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

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
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

	public String getCompanyMobileNumber() {
		return companyMobileNumber;
	}

	public void setCompanyMobileNumber(String companyMobileNumber) {
		this.companyMobileNumber = companyMobileNumber;
	}

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

}