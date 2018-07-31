package com.param.global.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.billing.global.model.DepositMaster;

@NamedQueries({
	@NamedQuery(name="GET_PATIENT_DETAILS_BY_ID",
			query = "SELECT patient.patientId as patientId, "
					+ "patient.uhidNumber as UHIDNumber, "
					+ "patient.prefixId as prefixId, "
					+ "concat(coalesce(patient.firstName,'') ,' ', coalesce(patient.middleName,'') ,' ', coalesce(patient.lastName,'')) as patientName, "
					+ "patient.genderId as genderId, "
					+ "patient.mobileNumber as mobileNumber, "
					+ "to_char(patient.dob, 'DD-MM-YYYY') as dob,"
					+ " patient.barCode as barCode,"
					+ " patient.isVip as isVip,"
					+ " patient.vipRemark as vipRemark,"
					+ "patient.isBlacklist as isBlacklist, "
					+ " patient.patientCategoryId as patientCategoryId,"
					+ " patient.isUnknownReg as isUnknownReg,"
					+ " concat(EXTRACT(year from AGE(CURRENT_TIMESTAMP, patient.dob)) , 'Y ' , EXTRACT(month from AGE(CURRENT_TIMESTAMP, patient.dob)),'M ') as age, "
					+ " genderMaster.code as genderCode "
					+ " FROM PatientRegistration patient "
					+ " INNER JOIN patient.genderMaster genderMaster"
					+ " WHERE patient.patientId=:patientId"),
	
			@NamedQuery(name="GET_PATIENT_DETAILS",
			query = "SELECT patient.patientId as patientId, "
					+ "patient.uhidNumber as uhidNumber, "
					+ "patient.prefixId as prefixId, "
					+ "concat(coalesce(patient.firstName,'') ,' ', coalesce(patient.middleName,'') ,' ', coalesce(patient.lastName,'')) as patientName, "
					+ "patient.genderId as genderId, "
					+ "patient.mobileNumber as mobileNumber, "
					+ "to_char(patient.dob, 'dd/MM/yyyy') as dob,"
					+ " genderMaster.code as genderCode "
					+ " FROM PatientRegistration patient "
					+ " INNER JOIN patient.genderMaster genderMaster"
					+ " WHERE patient.unitId=:unitId "
					+ " AND patient.organizationId=:orgId"),
	
			@NamedQuery(name="CHECK_UNIQUE_PATIENT",
				query="SELECT pt.patientId as patientId "
					+ "FROM PatientRegistration pt "
					+ "WHERE pt.dob=:dob "
					+ "AND pt.genderId=:genderId "
					+ "AND pt.mobileNumber=:mobileNumber "
					+ "AND pt.organizationId=:orgId "
					+ "AND pt.unitId=:unitId "),
			
			@NamedQuery(name="GET_PATIENT_DATA",query=
					 "SELECT patient.patientId as patientId, "
					 + "patient.uhidNumber as uhidNumber, "
					 + "patient.prefixId as prefixId, "
					 + "prefix.prefixCode as prefixCode, "
					 + "patient.firstName as firstName, "
					 + "patient.middleName as middleName, "
					 + "patient.lastName as lastName, "
					 + "patient.genderId as genderId, "
					 + "gender.code as genderCode, "
					 + "to_char(patient.dob,'DD/MM/YYYY') as dob, "
					 + "to_char(patient.dob,'DD/MM/YYYY') as birthDate, "
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
					 + "to_char(patient.identificationExpiryDate,'MM/DD/YYYY') as identificationExpiryDate, "
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
					 + "INNER JOIN patient.patientDetailslist patientDetails "
					 + "INNER JOIN patient.prefixMaster prefix "
					 + "INNER JOIN patient.genderMaster gender "
					 + "LEFT JOIN patient.identificationMaster identification "
					 + "WHERE patient.organizationId=:orgId "
					 + "AND patient.unitId=:unitId "
					 + "AND patient.status='A' "
					 + "AND patientDetails.status='A' "),
				@NamedQuery(name="FORSYNC_GET_PATIENT_RECORD_BY_ID",
				query="SELECT prefix.prefixCode as prefix ,"
						+ " patient.firstName as firstName, "
						+ " patient.middleName as middleName, "
						+ " patient.lastName as lastName, "
						+ " genderMaster.desc as gender, "
						+ " to_char(patient.dob,'dd-MM-yyyy') as dateOfBirth, "
						 + "patient.mobileNumber as mobileNumber, "
						 + "patient.email as emailId, "
						 + "patientDetails.address as addressDesc, "
						 + "cntryMster.countryName as country, " 
						 + "stateMster.stateName as state, "						
						 + "cityMster.cityName as city, "
						 + "patientDetails.zipCode as pincode, "
						 + "to_char(patient.createdDate,'DD-MM-YYYY') as registrationDate, "
						 + "nationalityMster.nationalityName as nationality, "
						 + "areaMster.areaName as area, "
						 + "patient.status as status, "
						 + "patient.patientId as patientId, "
						 + "2 as applicationId ,"
						 + "patientDetails.organizationId as hospitalId "
						 
						+ " FROM  PatientRegistration patient "
						+ " INNER JOIN patient.patientDetailslist patientDetails "
						+ " INNER JOIN patient.prefixMaster prefix "
						+ " INNER JOIN patientDetails.countryMaster cntryMster "
						+ " INNER JOIN patientDetails.stateMaster stateMster "
						+ " INNER JOIN patientDetails.cityMaster cityMster "
						+ " INNER JOIN patientDetails.nationalityMaster nationalityMster "
						+ " INNER JOIN patientDetails.areaMaster areaMster "
						+ " INNER JOIN patient.genderMaster genderMaster "
						+ " WHERE patient.patientId=:PatientId "
						+ " AND patient.status='A'"
						+ " AND patientDetails.status='A' ")
	 
	
	
})
@NamedNativeQueries({
	@NamedNativeQuery(name = "GET_PATIENT_DETAILS_BY_ID_FOR_BILLING_SEARCH",
					 query = "select pat.patient_id as \"patientId\", pat.uhid_number as \"uhidNumber\", pat.first_name as \"firstName\", pat.middle_name as \"middleName\", pat.last_name as \"lastName\",  "
							 + " pat.mobile_number as \"mobileNumber\", to_char(pat.dob,'DD-MM-YYYY') as \"dob\",  "
							 + " trim(to_char(EXTRACT(year from AGE(CURRENT_TIMESTAMP, pat.dob)),'999')) as \"ageString\",  "
							 + " prfx.prefix_code as \"prefixCode\", gen.gender_name as \"genderName\", patdtls.address as \"address\", cnt.country_name as \"countryName\",  "
							 + " st.state_name as \"stateName\", cty.city_name as \"cityName\", ar.area_name as \"areaName\", patdtls.zip_code as \"zipCode\",  "
							 + " kin.kin_name as \"kinName\", kin.mobile_no as \"kinMobileNumber\" "
							 + " from patient.t_patient_registration pat  "
							 + " left join public.m_prefix_master prfx "
							 + " on prfx.prefix_id = pat.prefix_id "
							 + " left join public.m_gender_master gen  "
							 + " on gen.gender_id = pat.gender_id "
							 + " left join patient.t_patient_details patdtls "
							 + " on patdtls.patient_id = pat.patient_id "
							 + " left join public.m_country_master cnt  "
							 + " on cnt.country_id = patdtls.country_id "
							 + " left join public.m_state_master st  "
							 + " on st.state_id = patdtls.state_id "
							 + " left join public.m_city_master cty "
							 + " on cty.city_id = patdtls.city_id "
							 + " left join public.m_area_master ar "
							 + " on ar.area_id = patdtls.area_id "
							 + " left join public.m_kin_details kin "
							 + " on kin.patient_id = pat.patient_id  "
							 + " where pat.patient_id = :patientId ")
})

@Entity
@Table(name = "t_patient_registration", schema = "patient")
@SequenceGenerator(name="patient_registration_seq", sequenceName="patient.patient_registration_seq", allocationSize=1)
public class PatientRegistration{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_registration_seq")
	@Column(name="patient_id")
	private Integer patientId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="uhid_number")
	private String uhidNumber;
	
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
	
	@Column(name="emp_doc_id")
	private Integer empDocId;
	
	@Column(name="emp_dep_type_id")
	private Integer empDepTypeId;
	
	@Column(name="emp_doc_dep_id")
	private Integer empDocDepId;
	
	@Column(name="status")
	private char status;
	
	@Column(name="created_by")
	private Integer createdBy;
	
	@Column(name="updated_by")
	private Integer updatedBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prefix_id", insertable = false, updatable = false)
	private PrefixMaster prefixMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gender_id", insertable = false, updatable = false)
	private GenderMaster genderMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_category_id", insertable = false, updatable = false)
	private PatientCategoryMaster patientCategoryMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "identification_type_id", insertable = false, updatable = false)
	private IdentificationMaster identificationMaster;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "patientRegistration", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PatientDetails> patientDetailslist;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "patientRegistration", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DepositMaster> listDepositMaster;
	
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

	public IdentificationMaster getIdentificationMaster() {
		return identificationMaster;
	}

	public void setIdentificationMaster(IdentificationMaster identificationMaster) {
		this.identificationMaster = identificationMaster;
	}

	public List<PatientDetails> getPatientDetailslist() {
		return patientDetailslist;
	}

	public void setPatientDetailslist(List<PatientDetails> patientDetailslist) {
		this.patientDetailslist = patientDetailslist;
	}

	public List<DepositMaster> getListDepositMaster() {
		return listDepositMaster;
	}

	public void setListDepositMaster(List<DepositMaster> listDepositMaster) {
		this.listDepositMaster = listDepositMaster;
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
		this.isVip = (isVip == '\u0000') ? 'N' : isVip;
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

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = (status == '\u0000') ? 'A' : status;
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

	public PrefixMaster getPrefixMaster() {
		return prefixMaster;
	}

	public void setPrefixMaster(PrefixMaster prefixMaster) {
		this.prefixMaster = prefixMaster;
	}

	public GenderMaster getGenderMaster() {
		return genderMaster;
	}

	public void setGenderMaster(GenderMaster genderMaster) {
		this.genderMaster = genderMaster;
	}

	public PatientCategoryMaster getPatientCategoryMaster() {
		return patientCategoryMaster;
	}

	public void setPatientCategoryMaster(PatientCategoryMaster patientCategoryMaster) {
		this.patientCategoryMaster = patientCategoryMaster;
	}

	public String getVipRemark() {
		return vipRemark;
	}

	public void setVipRemark(String vipRemark) {
		this.vipRemark = vipRemark;
	}

}
