package com.param.global.dto;

public class PatientRegistrationDto {
	private Integer patientId;

	private Integer organizationId;

	private Integer unitId;

	private String uhidNumber;

	private Integer prefixId;

	private String firstName;

	private String middleName;

	private String lastName;

	private Integer genderId;

	private String dob;
	
	private String birthDate;
	
	private String mobileNumber;

	private String countryCallingCode;

	private String barCode;

	private char isVip;

	private String vipRemark;

	private char isBlacklist;

	private Integer identificationTypeId;

	private String identificationNumber;

	private String email;

	private Integer patientCategoryId;

	private char isUnknownReg;

	private char status;

	private Integer createdBy;

	private Integer updatedBy;

	private String createdDate;

	private String updatedDate;

	private int patientDetailsId;

	private Integer nationalityId;

	private Integer raceId;

	private Integer maritalStatusId;

	private String address;

	private Integer countryId;

	private Integer stateId;

	private Integer districtId;

	private Integer cityId;

	private Integer areaId;

	private String zipCode;

	private String phoneCode;

	private String phoneNumber;

	private String companyName;

	private String companyAddress;

	private Integer companyCountryId;

	private Integer companyStateId;

	private Integer companyDistrictId;

	private Integer companyCityId;

	private Integer companyAreaId;

	private String companyZipCode;

	private String companyMobileNumber;

	private String patientName;

	private String genderCode;

	private String permanentAddress;

	private Integer permanentCountryId;

	private Integer permanentStateId;

	private Integer permanentDistrictId;

	private Integer permanentCityId;

	private Integer permanentAreaId;

	private String permanentZipCode;

	private String permanentPhoneCode;

	private String permanentPhoneNumber;

	private String aliseName;

	private String identificationExpiryDate;
	
	private Integer occupationId;
	
	private Integer registrationTypeId;

	private Integer previousId;
	
	private String prefixCode;
	
	private String identificationName;
	
	private char isOtcReg;
	
	private char isPreReg;
	
	private Character isExpiryRequired;
	
	private String ageString;
	
	private Integer empDocId;
	
	private Integer empDepTypeId;
	
	private Integer empDocDepId;
	
	private Integer dependentDetailsId;

	private Integer typeId;

	private Integer relationId;

	private String mobileNo;

	private String dobKin;

	private char isNok;
	
	private String employeeName;
	
	private String empCode;
	
	private String dependentName;
	
	private String relationName;

	private String specialityName;
	 
	private Integer reasonId;
	
	private Integer reasonDesc;
	
	private String remark;
	
	private String blockedRemark;
	
	private Character isConversion;
	
	private String prevUhidNumber;
	
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
	
	public String getBlockedRemark() {
		return blockedRemark;
	}

	public void setBlockedRemark(String blockedRemark) {
		this.blockedRemark = blockedRemark;
	}

	public Integer getReasonId() {
		return reasonId;
	}

	public void setReasonId(Integer reasonId) {
		this.reasonId = reasonId;
	}

	public Integer getReasonDesc() {
		return reasonDesc;
	}

	public void setReasonDesc(Integer reasonDesc) {
		this.reasonDesc = reasonDesc;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/*+ "slud.reason_id as \"reasonId\", "
	+ "reason.reason_desc as \"reasonDesc\", "
	+ "slud.remark as \"remark\" 
*/	
	public Integer getDependentDetailsId() {
		return dependentDetailsId;
	}

	public void setDependentDetailsId(Integer dependentDetailsId) {
		this.dependentDetailsId = dependentDetailsId;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getRelationId() {
		return relationId;
	}

	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getDobKin() {
		return dobKin;
	}

	public void setDobKin(String dobKin) {
		this.dobKin = dobKin;
	}

	public char getIsNok() {
		return isNok;
	}

	public void setIsNok(char isNok) {
		this.isNok = isNok;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getDependentName() {
		return dependentName;
	}

	public void setDependentName(String dependentName) {
		this.dependentName = dependentName;
	}

	public String getRelationName() {
		return relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

	public String getSpecialityName() {
		return specialityName;
	}

	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}

	public Integer getEmpDocId() {
		return empDocId;
	}

	public void setEmpDocId(Integer empDocId) {
		this.empDocId = empDocId;
	}

	public Integer getEmpDepTypeId() {
		return empDepTypeId;
	}

	public void setEmpDepTypeId(Integer empDepTypeId) {
		this.empDepTypeId = empDepTypeId;
	}

	public Integer getEmpDocDepId() {
		return empDocDepId;
	}

	public void setEmpDocDepId(Integer empDocDepId) {
		this.empDocDepId = empDocDepId;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
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

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
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

	public char getIsVip() {
		return isVip;
	}

	public void setIsVip(char isVip) {
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
		this.isBlacklist = isBlacklist;
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
		this.isUnknownReg = isUnknownReg;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
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

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getPatientDetailsId() {
		return patientDetailsId;
	}

	public void setPatientDetailsId(int patientDetailsId) {
		this.patientDetailsId = patientDetailsId;
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

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getGenderCode() {
		return genderCode;
	}

	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
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

	public String getAliseName() {
		return aliseName;
	}

	public void setAliseName(String aliseName) {
		this.aliseName = aliseName;
	}

	public String getIdentificationExpiryDate() {
		return identificationExpiryDate;
	}

	public void setIdentificationExpiryDate(String identificationExpiryDate) {
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

	public String getPrefixCode() {
		return prefixCode;
	}

	public void setPrefixCode(String prefixCode) {
		this.prefixCode = prefixCode;
	}

	public String getIdentificationName() {
		return identificationName;
	}

	public void setIdentificationName(String identificationName) {
		this.identificationName = identificationName;
	}

	public char getIsOtcReg() {
		return isOtcReg;
	}

	public void setIsOtcReg(char isOtcReg) {
		this.isOtcReg = isOtcReg;
	}

	public char getIsPreReg() {
		return isPreReg;
	}

	public void setIsPreReg(char isPreReg) {
		this.isPreReg = isPreReg;
	}

	public Character getIsExpiryRequired() {
		return isExpiryRequired;
	}

	public void setIsExpiryRequired(Character isExpiryRequired) {
		this.isExpiryRequired = isExpiryRequired;
	}

	public String getAgeString() {
		return ageString;
	}

	public void setAgeString(String ageString) {
		this.ageString = ageString;
	}
	


}	