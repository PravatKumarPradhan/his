package com.param.global.dto.sync;

import java.util.List;

public class PatientMasterDto {

	private Integer globalUhid;
	
	private String aadharCardNumber;
	
	private String prefix;
	
	private String firstName;
	
	private String middleName;
	
	private String lastName;
	
	private String gender;
	
	private String dateOfBirth;
	
	private String patientPhoto;
	
	private String bloodGroup;
	
	private String mobileNumber;
	
	private String emailId;
	
	private String addressDesc;
	
	private String country;
	
	private String state;
	
	private String city;
	
	private String pincode;
	
	private String registrationDate;
	
	private String nationality;
	
	private String ssn;
	
	private String villageName;
	
	private String area;
	
	private char status;
	
	private char isAllergyKnown;
	
	private char addressDisclaimer;
	
	private String facebookId;
	
	private String gmailId;
	
	private String twitterId;
	
	private String linkedinId;
	
	private Integer patientId;
	
	private char isSmsSent;
	
	private String localUhid;
	
	private String hospitalName;
	
	private Integer hospitalId;
	
	private String createdDate;
	
	private String userName;
	private String password;
	private char isPasswordChanged;
	private String uuid;
	private List<Integer> globalUhids;
	private Integer applicationId;

	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public char getIsPasswordChanged() {
		return isPasswordChanged;
	}

	public void setIsPasswordChanged(char isPasswordChanged) {
		this.isPasswordChanged = (isPasswordChanged != '\u0000' ? isPasswordChanged : 'I');
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private List<PatientMasterDto> listPatientMasterDto;

	public Integer getGlobalUhid() {
		return globalUhid;
	}

	public void setGlobalUhid(Integer globalUhid) {
		this.globalUhid = globalUhid;
	}

	public String getAadharCardNumber() {
		return aadharCardNumber;
	}

	public void setAadharCardNumber(String aadharCardNumber) {
		this.aadharCardNumber = aadharCardNumber;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPatientPhoto() {
		return patientPhoto;
	}

	public void setPatientPhoto(String patientPhoto) {
		this.patientPhoto = patientPhoto;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAddressDesc() {
		return addressDesc;
	}

	public void setAddressDesc(String addressDesc) {
		this.addressDesc = addressDesc;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public char getIsAllergyKnown() {
		return isAllergyKnown;
	}

	public void setIsAllergyKnown(char isAllergyKnown) {
		this.isAllergyKnown = isAllergyKnown;
	}

	public char getAddressDisclaimer() {
		return addressDisclaimer;
	}

	public void setAddressDisclaimer(char addressDisclaimer) {
		this.addressDisclaimer = addressDisclaimer;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getGmailId() {
		return gmailId;
	}

	public void setGmailId(String gmailId) {
		this.gmailId = gmailId;
	}

	public String getTwitterId() {
		return twitterId;
	}

	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}

	public String getLinkedinId() {
		return linkedinId;
	}

	public void setLinkedinId(String linkedinId) {
		this.linkedinId = linkedinId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public char getIsSmsSent() {
		return isSmsSent;
	}

	public void setIsSmsSent(char isSmsSent) {
		this.isSmsSent = isSmsSent;
	}

	public List<PatientMasterDto> getListPatientMasterDto() {
		return listPatientMasterDto;
	}

	public void setListPatientMasterDto(List<PatientMasterDto> listPatientMasterDto) {
		this.listPatientMasterDto = listPatientMasterDto;
	}

	public String getLocalUhid() {
		return localUhid;
	}

	public void setLocalUhid(String localUhid) {
		this.localUhid = localUhid;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public Integer getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public List<Integer> getGlobalUhids() {
		return globalUhids;
	}

	public void setGlobalUhids(List<Integer> globalUhids) {
		this.globalUhids = globalUhids;
	}

	
}
