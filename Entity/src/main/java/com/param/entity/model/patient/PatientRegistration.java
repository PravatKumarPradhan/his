package com.param.entity.model.patient;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.param.entity.model.master.Gender;
import com.param.entity.model.master.Organization;
import com.param.entity.model.master.Prefix;

@Entity(name = "PatientRegistrations")
@Table(name = "t_patient_registration", schema = "patient")
public class PatientRegistration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patient_id", unique = true, nullable = false)
	private Integer patientId;

	@Column(name = "alise_name", length = 200)
	private String aliseName;

	@Column(name = "bar_code", length = 2147483647)
	private String barCode;

	@Column(name = "country_calling_code", length = 2147483647)
	private String countryCallingCode;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	private Timestamp dob;

	@Column(length = 200)
	private String email;

	@Column(name = "first_name", length = 2147483647)
	private String firstName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gender_id")
	private Gender gender;

	@Column(name = "identification_expiry_date")
	private Timestamp identificationExpiryDate;

	@Column(name = "identification_number", length = 2147483647)
	private String identificationNumber;

	@Column(name = "identification_type_id")
	private Integer identificationTypeId;

	@Column(name = "is_blacklist", length = 1)
	private String isBlacklist;

	@Column(name = "is_otc_reg", length = 1)
	private String isOtcReg;

	@Column(name = "is_pre_reg", length = 1)
	private String isPreReg;

	@Column(name = "is_unknown_reg", length = 1)
	private String isUnknownReg;

	@Column(name = "is_vip", length = 1)
	private String isVip;

	@Column(name = "last_name", length = 2147483647)
	private String lastName;

	@Column(name = "middle_name", length = 2147483647)
	private String middleName;

	@Column(name = "mobile_number", length = 2147483647)
	private String mobileNumber;

	@Column(name = "occupation_id")
	private Integer occupationId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id")
	private Organization organization;

	@Column(name = "patient_category_id")
	private Integer patientCategoryId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prefix_id")
	private Prefix prefix;

	@Column(name = "previous_id")
	private Integer previousId;

	@Column(name = "registration_type_id")
	private Integer registrationTypeId;

	@Column(length = 1)
	private String status;

	@Column(name = "uhid_number", length = 2147483647)
	private String uhidNumber;

	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	private Timestamp updatedDate;

	@Column(name = "vip_remark", length = 500)
	private String vipRemark;

	public PatientRegistration() {
	}

	public PatientRegistration(Integer patientId) {
		super();
		this.patientId = patientId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getAliseName() {
		return aliseName;
	}

	public void setAliseName(String aliseName) {
		this.aliseName = aliseName;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getCountryCallingCode() {
		return countryCallingCode;
	}

	public void setCountryCallingCode(String countryCallingCode) {
		this.countryCallingCode = countryCallingCode;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getDob() {
		return dob;
	}

	public void setDob(Timestamp dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Timestamp getIdentificationExpiryDate() {
		return identificationExpiryDate;
	}

	public void setIdentificationExpiryDate(Timestamp identificationExpiryDate) {
		this.identificationExpiryDate = identificationExpiryDate;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public Integer getIdentificationTypeId() {
		return identificationTypeId;
	}

	public void setIdentificationTypeId(Integer identificationTypeId) {
		this.identificationTypeId = identificationTypeId;
	}

	public String getIsBlacklist() {
		return isBlacklist;
	}

	public void setIsBlacklist(String isBlacklist) {
		this.isBlacklist = isBlacklist;
	}

	public String getIsOtcReg() {
		return isOtcReg;
	}

	public void setIsOtcReg(String isOtcReg) {
		this.isOtcReg = isOtcReg;
	}

	public String getIsPreReg() {
		return isPreReg;
	}

	public void setIsPreReg(String isPreReg) {
		this.isPreReg = isPreReg;
	}

	public String getIsUnknownReg() {
		return isUnknownReg;
	}

	public void setIsUnknownReg(String isUnknownReg) {
		this.isUnknownReg = isUnknownReg;
	}

	public String getIsVip() {
		return isVip;
	}

	public void setIsVip(String isVip) {
		this.isVip = isVip;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Integer getOccupationId() {
		return occupationId;
	}

	public void setOccupationId(Integer occupationId) {
		this.occupationId = occupationId;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Integer getPatientCategoryId() {
		return patientCategoryId;
	}

	public void setPatientCategoryId(Integer patientCategoryId) {
		this.patientCategoryId = patientCategoryId;
	}

	public Prefix getPrefix() {
		return prefix;
	}

	public void setPrefix(Prefix prefix) {
		this.prefix = prefix;
	}

	public Integer getPreviousId() {
		return previousId;
	}

	public void setPreviousId(Integer previousId) {
		this.previousId = previousId;
	}

	public Integer getRegistrationTypeId() {
		return registrationTypeId;
	}

	public void setRegistrationTypeId(Integer registrationTypeId) {
		this.registrationTypeId = registrationTypeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUhidNumber() {
		return uhidNumber;
	}

	public void setUhidNumber(String uhidNumber) {
		this.uhidNumber = uhidNumber;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getVipRemark() {
		return vipRemark;
	}

	public void setVipRemark(String vipRemark) {
		this.vipRemark = vipRemark;
	}
}