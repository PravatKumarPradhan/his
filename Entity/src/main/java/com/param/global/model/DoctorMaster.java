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
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.adt.master.global.model.OccupationMaster;


@NamedQueries({
	
	
	@NamedQuery(name="GET_DOCTOR_LIST_BY_SPECIALITY",
			query="SELECT doctSpemppr.doctorSpecialityId as doctorSpecialityId, dctMst.firstName as firstName, "
					+ "dctMst.lastName as lastName, "
					+ "doctSpemppr.specialityId as specialityId, "
					+ "spl.specialityName as specialityName, "
					+ "dctMst.doctorId as doctorId "
					+ "FROM DoctorSpecialityMapper doctSpemppr "
					+ "INNER JOIN doctSpemppr.doctorMaster dctMst "
					+ "INNER JOIN doctSpemppr.specialityMaster spl "
					+ "WHERE doctSpemppr.specialityId=:specilaityId "
					+ "AND dctMst.status='A' "
					+ "AND doctSpemppr.status='A' "
					+ "AND dctMst.organizationId=:organizationId "
					+ "AND doctSpemppr.unitId=:unitId"),
			
			
			@NamedQuery(name="GET_DOCTORS_LIST_BY_SPECIALITIES",
			query="SELECT doctSpemppr.doctorSpecialityId as doctorSpecialityId, dctMst.firstName as firstName, "
					+ "dctMst.lastName as lastName, "
					+ "concat('Dr. ',dctMst.firstName,' ',dctMst.middleName,' ',dctMst.lastName) as doctorName, "
					+ "doctSpemppr.specialityId as specialityId, "
					+ "spl.specialityName as specialityName, "
					+ "dctMst.doctorId as doctorId,"
					+ "dctMst.allowedOverBookingSlots as allowedOverBookingSlots "
					+ "FROM DoctorSpecialityMapper doctSpemppr "
					+ "INNER JOIN doctSpemppr.doctorMaster dctMst "
					+ "INNER JOIN doctSpemppr.specialityMaster spl "
					+ "WHERE doctSpemppr.specialityId IN (:specilaityArray) "
					+ "AND dctMst.status='A' "
					+ "AND doctSpemppr.status='A' "
					+ "AND dctMst.organizationId=:organizationId "
					+ "AND doctSpemppr.unitId=:unitId"),
	
	@NamedQuery(name="GET_ACTIVE_DOCTOR_LIST",query="SELECT doc.doctorId as doctorId, "
			+ "concat('Dr. ',coalesce(doc.firstName),' ',coalesce(doc.middleName),' ',coalesce(doc.lastName)) as doctorName, "
			+ "coalesce(doc.firstName) as firstName, "
			+ "coalesce(doc.middleName) as middleName, "
			+ "coalesce(doc.lastName) as lastName, "
			+ "dsm.specialityId as specialityId, "
			+ "spl.specialityName as specialityName "
			+ "FROM DoctorMaster doc "
			+ "INNER JOIN doc.docotrSpecialityMapperslist dsm "
			+ "INNER JOIN dsm.specialityMaster spl "
			+ "WHERE doc.organizationId=:orgId "
			+ "AND doc.unitId=:unitId "),
			
	@NamedQuery(name="AUTO_FILL_SEARCH_FOR_DOCTOR_NAME",
			query="SELECT doc.doctorId as doctorId, "
			+ "concat('Dr. ',coalesce(doc.firstName),' ',coalesce(doc.middleName),' ',coalesce(doc.lastName)) as doctorName, "
			+ "coalesce(doc.firstName) as firstName, "
			+ "coalesce(doc.middleName) as middleName, "
			+ "coalesce(doc.lastName) as lastName, "
			+ "dsm.specialityId as specialityId, "
			+ "spl.specialityName as specialityName "
			+ "FROM DoctorMaster doc "
			+ "INNER JOIN doc.docotrSpecialityMapperslist dsm "
			+ "INNER JOIN dsm.specialityMaster spl "
			+ "WHERE LOWER(doc.firstName) LIKE :doctorName "
			+ "OR   LOWER(doc.middleName) LIKE :doctorName "
			+ "OR   LOWER(doc.lastName) LIKE :doctorName "
			+ "AND doc.organizationId=:orgId "
			+ "AND doc.unitId=:unitId "),
	
	

	@NamedQuery(name="GET_DOCTOR_DETAILS",query="SELECT doctor.doctorId as doctorId, "
			+ "doctor.accessCardNumber as accessCardNumber, "
			+ "doctor.prefixId as prefixId, "
			+ "doctor.firstName as firstName, "
			+ "doctor.middleName as middleName, "
			+ "doctor.lastName as lastName, "
			+ "doctor.genderId as genderId, "
			+ "to_char(doctor.dob,'MM/DD/YYYY') as dob, "
			+ "trim(to_char(EXTRACT(year from AGE(CURRENT_TIMESTAMP, doctor.dob)),'999')) as ageString, "
			+ "doctor.mobileNumber as mobileNumber, "
			+ "doctor.countryCallingCode as countryCallingCode, "
			+ "doctor.barCode as barCode, "
			+ "doctor.isVip as isVip, "
			+ "doctor.vipRemark as vipRemark, "
			+ "doctor.identificationTypeId as identificationTypeId, "
			+ "identificationMaster.identificationName as identificationName, "
			+ "doctor.identificationNumber as identificationNumber, "
			+ "doctor.email as email,"
			+ "doctor.docCategoryId as docCategoryId, "
			+ "doctorCategoryMaster.doctorCategoryDescription as docCategoryDescription, "
			+ "doctor.aliseName as aliseName, "
			+ "to_char(doctor.identificationExpiryDate,'MM/DD/YYYY') as identificationExpiryDate, "
			+ "doctor.occupationId as occupationId, "
			+ "doctor.specialityId as specialityId, "
			+ "specialityMaster.specialityName as specialityName, "
			+ "doctor.subSpecialityId as subSpecialityId, "
			+ "subSpecialityMaster.subSpecialityMasterName as subSpecialityName, "
			+ "doctor.docDesignationId as docDesignationId, "
			+ "employeeDesignationMaster.employeeDesignationDescription as docDesignationDescription, "
			+ "doctor.docCode as docCode, "
			+ "to_char(doctor.dateOfJoining,'MM/DD/YYYY') as dateOfJoining, "
			+ "to_char(doctor.createdDate,'DD-MM-YYYY HH:MM:SS') as createdDate, "
			+ "doctor.createdBy as createdBy, "
			+ "doctor.status as status, "
			+ "doctor.allowedOverBookingSlots as allowedOverBookingSlots, "
			+ "doctorDetails.doctorDetailsId as doctorDetailsId, "
			+ "doctorDetails.nationalityId as nationalityId, "
			+ "doctorDetails.raceId as raceId, "
			+ "doctorDetails.maritalStatusId as maritalStatusId, "
			+ "doctorDetails.address as address,"
			+ "doctorDetails.countryId as countryId, "
			+ "doctorDetails.stateId as stateId, "
			+ "doctorDetails.districtId as districtId, "
			+ "doctorDetails.cityId as cityId, "
			+ "doctorDetails.areaId as areaId, "
			+ "doctorDetails.zipCode as zipCode, "
			+ "doctorDetails.phoneCode as phoneCode, "
			+ "doctorDetails.phoneNumber as phoneNumber, "
			+ "doctorDetails.permanentAddress as permanentAddress, "
			+ "doctorDetails.permanentCountryId as permanentCountryId, "
			+ "doctorDetails.permanentStateId as permanentStateId, "
			+ "doctorDetails.permanentDistrictId as permanentDistrictId, "
			+ "doctorDetails.permanentCityId as permanentCityId, "
			+ "doctorDetails.permanentAreaId as permanentAreaId, "
			+ "doctorDetails.permanentZipCode as permanentZipCode, "
			+ "doctorDetails.permanentPhoneCode as permanentPhoneCode, "
			+ "doctorDetails.permanentPhoneNumber as permanentPhoneNumber, "
			+ "doctorDetails.bankName as bankName, "
			+ "doctorDetails.accountNumber as accountNumber, "
			+ "doctorDetails.bankBranchName as bankBranchName, "
			+ "identificationMaster.isExpiryRequired as isExpiryRequired, "
			+ "doctorDetails.ifscCode as ifscCode "
			+ "FROM DoctorMaster doctor "
			+ "INNER JOIN doctor.doctorDetailslist doctorDetails "
			+ "INNER JOIN doctor.specialityMaster specialityMaster "
			+ "INNER JOIN doctor.subSpecialityMaster subSpecialityMaster "
			+ "INNER JOIN doctor.doctorCategoryMaster doctorCategoryMaster "
			+ "INNER JOIN doctor.employeeDesignationMaster employeeDesignationMaster "
			+ "INNER JOIN doctor.identificationMaster identificationMaster "
			+ "WHERE doctor.organizationId=:organizationId "
			+ "AND doctor.unitId=:unitId ")
})
@NamedNativeQuery(
		name="GET_DOCTORDETAILS_FORSYNC_BY_ID",
		query="SELECT dm.doctor_id AS \"doctorLocalId\" , "
		//temp doc_code taken as doctorNumer
		+ "dm.doc_code AS \"doctorNumber\" , "
		+ "dm.first_name AS \"doctorName\","
		+ "to_char(dm.dob,'DD-MM-YYYY HH24:MI:SS.SSS' ) AS \"dateOfBirth\" ,"
//		+ "sbm.sub_speciality_id AS \"subGroup\" ,"
		+ "sm.speciality_name AS \"department\","
		+ "sm.speciality_id AS \"departmentId\","
//		+ "dcm.doc_category_id AS \"doctorType\","
		+ "gm.gender_name AS \"gender\","
		+ "dcm.doctor_category_code AS \"doctorType\","
		+ "msm.marital_status_name AS \"maritalStatus\" ,"
//		+ "dm.identification_number AS \"panNumber\" ,"
		+ "to_char(dm.date_of_joining,'DD-MM-YYYY HH24:MI:SS.SSS' ) AS \"dateOfJoining\" ,"
		+ "dm.access_card_number AS \"accessCardNumber\" ,"
		+ "dm.email as \"emailId\" ,"
		+ "dm.mobile_number AS \"mobileNumber\" ,"
		+ "dd.zip_code AS \"pincode\" ,"
		+ "stm.state_name AS \"state\" ,"
		+ "ctm.city_name AS \"city\" ,"
		+ "am.area_name AS \"area\" ,"
		+ "dd.phone_number AS \"phoneNumber\" ,"
		+ "dm.status AS \"status\" ,"
		+ "dm.unit_id AS \"unitId\" ,"
		+ "om.organization_name AS \"organisationName\" ,"
		+ "om.organization_id AS \"hospitalId\" ,"
		+ "2 AS \"applicationId\"  "
		+ "FROM doctor.m_doctor_master dm "
		+ "INNER JOIN doctor.m_doctor_details dd ON dm.doctor_id=dd.doctor_id "
		+ "INNER JOIN doctor.m_speciality_master sm ON dm.speciality_id=sm.speciality_id "
		+ "INNER JOIN doctor.m_sub_speciality_master sbm ON dm.sub_speciality_id=sbm.sub_speciality_id "
		+ "INNER JOIN public.m_doctor_category dcm ON dm.doc_category_id=dcm.doctor_category_id "
		+ "INNER JOIN public.m_marital_status_master msm ON dd.marital_status_id=msm.marital_status_id "
		+ "INNER JOIN public.m_gender_master gm ON dm.gender_id=gm.gender_id "
		+ "INNER JOIN public.m_state_master stm ON dd.state_id=stm.state_id "
		+ "INNER JOIN public.m_country_master cntm ON dd.country_id=cntm.country_id "
		+ "INNER JOIN public.m_district_master dstm ON dd.district_id=dstm.district_id "
		+ "INNER JOIN public.m_city_master ctm ON dd.city_id=ctm.city_id "
		+ "INNER JOIN public.m_area_master am ON dd.area_id=am.area_id "
		+ "INNER JOIN public.m_organization_master om ON dd.organization_id=om.organization_id "
		+ "WHERE dm.doctor_id=:doctorId")

@Entity
@Table(name="m_doctor_master",schema="doctor")
@SequenceGenerator(name="doctor_master_seq", sequenceName="doctor.doctor_master_seq", allocationSize=1)
public class DoctorMaster 
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="doctor_master_seq")
	@Column(name="doctor_id")
	private int doctorId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="doctor_number")
	private int doctorNumber;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="middle_name")
	private String middleName;
	
	@Column(name="last_name")
	private String  lastName;
	
	@Column(name="prefix_id")
	private Integer prefixId;
	
	@Column(name="dob")
	private Date dob;
	
	@Column(name="gender_id")
	private Integer genderId;

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
	
	@Column(name="identification_type_id")
	private Integer identificationTypeId;
	
	@Column(name="identification_number")
	private String identificationNumber;
	
	@Column(name="email")
	private String email;
	
	@Column(name="doc_category_id")
	private Integer docCategoryId;
	
	@Column(name="alise_name")
	private String aliseName;
	
	@Column(name="identification_expiry_date")
	private Date identificationExpiryDate;

	@Column(name="occupation_id")
	private Integer occupationId;
	
	@Column(name="speciality_id")
	private Integer specialityId;
	 
	@Column(name="sub_speciality_id")
	private Integer subSpecialityId;
	
	@Column(name="doc_designation_id")
	private Integer docDesignationId;
	
	@Column(name="doc_code")
	private String docCode;
	
	@Column(name="date_of_joining")
	private Date dateOfJoining;
	
	@Column(name="access_card_number")
	private String accessCardNumber;
	
	@Column(name="allowed_over_booking_slots")
	private Integer allowedOverBookingSlots;
	
	@Column(name="status")
	private char status; 

	@Column(name="created_by")
	private int createdBy;

	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "doctorMaster", cascade = CascadeType.ALL, orphanRemoval = true) 
	private List<DoctorSpecialityMapper> docotrSpecialityMapperslist;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "doctorMaster", cascade = CascadeType.ALL, orphanRemoval = true) 
	private List<DoctorDetails> doctorDetailslist;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prefix_id", insertable = false, updatable = false)
	private PrefixMaster prefixMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gender_id", insertable = false, updatable = false)
	private GenderMaster genderMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "speciality_id", insertable = false, updatable = false)
	private SpecialityMaster specialityMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sub_speciality_id", insertable = false, updatable = false)
	private SubSpecialityMaster subSpecialityMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doc_category_id", insertable = false, updatable = false)
	private DoctorCategoryMaster doctorCategoryMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doc_designation_id", insertable = false, updatable = false)
	private EmployeeDesignationMaster employeeDesignationMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "identification_type_id", insertable = false, updatable = false)
	private IdentificationMaster identificationMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "occupation_id", insertable = false, updatable = false)
	private OccupationMaster occupationMaster;

	public Integer getAllowedOverBookingSlots() {
		return allowedOverBookingSlots;
	}

	public void setAllowedOverBookingSlots(Integer allowedOverBookingSlots) {
		this.allowedOverBookingSlots = allowedOverBookingSlots;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public int getDoctorNumber() {
		return doctorNumber;
	}

	public void setDoctorNumber(int doctorNumber) {
		this.doctorNumber = doctorNumber;
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

	public Integer getPrefixId() {
		return prefixId;
	}

	public void setPrefixId(Integer prefixId) {
		this.prefixId = prefixId;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Integer getGenderId() {
		return genderId;
	}

	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
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

	public Integer getDocCategoryId() {
		return docCategoryId;
	}

	public void setDocCategoryId(Integer docCategoryId) {
		this.docCategoryId = docCategoryId;
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

	public Integer getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(Integer specialityId) {
		this.specialityId = specialityId;
	}

	public Integer getSubSpecialityId() {
		return subSpecialityId;
	}

	public void setSubSpecialityId(Integer subSpecialityId) {
		this.subSpecialityId = subSpecialityId;
	}

	public Integer getDocDesignationId() {
		return docDesignationId;
	}

	public void setDocDesignationId(Integer docDesignationId) {
		this.docDesignationId = docDesignationId;
	}

	public String getDocCode() {
		return docCode;
	}

	public void setDocCode(String docCode) {
		this.docCode = docCode;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getAccessCardNumber() {
		return accessCardNumber;
	}

	public void setAccessCardNumber(String accessCardNumber) {
		this.accessCardNumber = accessCardNumber;
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

	public List<DoctorSpecialityMapper> getDocotrSpecialityMapperslist() {
		return docotrSpecialityMapperslist;
	}

	public void setDocotrSpecialityMapperslist(List<DoctorSpecialityMapper> docotrSpecialityMapperslist) {
		this.docotrSpecialityMapperslist = docotrSpecialityMapperslist;
	}

	public List<DoctorDetails> getDoctorDetailslist() {
		return doctorDetailslist;
	}

	public void setDoctorDetailslist(List<DoctorDetails> doctorDetailslist) {
		this.doctorDetailslist = doctorDetailslist;
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

	public SpecialityMaster getSpecialityMaster() {
		return specialityMaster;
	}

	public void setSpecialityMaster(SpecialityMaster specialityMaster) {
		this.specialityMaster = specialityMaster;
	}

	public SubSpecialityMaster getSubSpecialityMaster() {
		return subSpecialityMaster;
	}

	public void setSubSpecialityMaster(SubSpecialityMaster subSpecialityMaster) {
		this.subSpecialityMaster = subSpecialityMaster;
	}

	public EmployeeDesignationMaster getEmployeeDesignationMaster() {
		return employeeDesignationMaster;
	}

	public void setEmployeeDesignationMaster(EmployeeDesignationMaster employeeDesignationMaster) {
		this.employeeDesignationMaster = employeeDesignationMaster;
	}

	public IdentificationMaster getIdentificationMaster() {
		return identificationMaster;
	}

	public void setIdentificationMaster(IdentificationMaster identificationMaster) {
		this.identificationMaster = identificationMaster;
	}

	public OccupationMaster getOccupationMaster() {
		return occupationMaster;
	}

	public void setOccupationMaster(OccupationMaster occupationMaster) {
		this.occupationMaster = occupationMaster;
	}
	
}
