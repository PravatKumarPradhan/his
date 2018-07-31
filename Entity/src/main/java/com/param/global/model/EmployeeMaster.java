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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.adt.master.global.model.OccupationMaster;

@NamedQueries({

	@NamedQuery(name="CHECK_UNIQUE_EMPLOYEE",
			query="SELECT et.employeeId as employeeId "
				+ "FROM EmployeeMaster et "
				+ "WHERE et.mobileNumber=:mobileNumber "
				+ "AND et.empCode=:empCode "
				+ "AND et.organizationId=:orgId "
				+ "AND et.unitId=:unitId "),
	
	@NamedQuery(name="GET_EMPLOYEE_DETAILS",query="SELECT employee.employeeId as employeeId, "
			+ "employee.accessCardNumber as accessCardNumber, "
			+ "employee.prefixId as prefixId, "
			+ "employee.firstName as firstName, "
			+ "employee.middleName as middleName, "
			+ "employee.lastName as lastName, "
			+ "employee.genderId as genderId, "
			+ "to_char(employee.dob,'MM/DD/YYYY') as dob, "
			+ "trim(to_char(EXTRACT(year from AGE(CURRENT_TIMESTAMP, employee.dob)),'999')) as ageString, "
			+ "employee.mobileNumber as mobileNumber, "
			+ "employee.countryCallingCode as countryCallingCode, "
			+ "employee.barCode as barCode, "
			+ "employee.isVip as isVip, "
			+ "employee.vipRemark as vipRemark, "
			+ "employee.identificationTypeId as identificationTypeId, "
			+ "identificationMaster.identificationName as identificationName, "
			+ "employee.identificationNumber as identificationNumber, "
			+ "employee.email as email,"
			+ "employee.empCategoryId as empCategoryId, "
			+ "employeeCategoryMaster.employeeCategoryDescription as employeeCategoryDescription, "
			+ "employee.aliseName as aliseName, "
			+ "to_char(employee.identificationExpiryDate,'MM/DD/YYYY') as identificationExpiryDate, "
			+ "employee.occupationId as occupationId, "
			+ "employee.specialityId as specialityId, "
			+ "specialityMaster.specialityName as specialityName, "
			+ "employee.subSpecialityId as subSpecialityId, "
			+ "subSpecialityMaster.subSpecialityMasterName as subSpecialityName, "
			+ "employee.empDesignationId as empDesignationId, "
			+ "employeeDesignationMaster.employeeDesignationDescription as employeeDesignationDescription, "
			+ "employee.empCode as empCode, "
			+ "to_char(employee.dateOfJoining,'MM/DD/YYYY') as dateOfJoining, "
			+ "to_char(employee.createdDate,'DD-MM-YYYY HH:MM:SS') as createdDate, "
			+ "employee.createdBy as createdBy, "
			+ "employee.status as status, "
			+ "employeeDeatils.employeeDetailsId as employeeDetailsId, "
			+ "employeeDeatils.nationalityId as nationalityId, "
			+ "employeeDeatils.raceId as raceId, "
			+ "employeeDeatils.maritalStatusId as maritalStatusId, "
			+ "employeeDeatils.address as address,"
			+ "employeeDeatils.countryId as countryId, "
			+ "employeeDeatils.stateId as stateId, "
			+ "employeeDeatils.districtId as districtId, "
			+ "employeeDeatils.cityId as cityId, "
			+ "employeeDeatils.areaId as areaId, "
			+ "employeeDeatils.zipCode as zipCode, "
			+ "employeeDeatils.phoneCode as phoneCode, "
			+ "employeeDeatils.phoneNumber as phoneNumber, "
			+ "employeeDeatils.permanentAddress as permanentAddress, "
			+ "employeeDeatils.permanentCountryId as permanentCountryId, "
			+ "employeeDeatils.permanentStateId as permanentStateId, "
			+ "employeeDeatils.permanentDistrictId as permanentDistrictId, "
			+ "employeeDeatils.permanentCityId as permanentCityId, "
			+ "employeeDeatils.permanentAreaId as permanentAreaId, "
			+ "employeeDeatils.permanentZipCode as permanentZipCode, "
			+ "employeeDeatils.permanentPhoneCode as permanentPhoneCode, "
			+ "employeeDeatils.permanentPhoneNumber as permanentPhoneNumber, "
			+ "employeeDeatils.bankName as bankName, "
			+ "employeeDeatils.accountNumber as accountNumber, "
			+ "employeeDeatils.bankBranchName as bankBranchName, "
			+ "identificationMaster.isExpiryRequired as isExpiryRequired, "
			+ "employeeDeatils.ifscCode as ifscCode "
			+ "FROM EmployeeMaster employee "
			+ "INNER JOIN employee.employeeDetailsList employeeDeatils "
			+ "INNER JOIN employee.specialityMaster specialityMaster "
			+ "INNER JOIN employee.subSpecialityMaster subSpecialityMaster "
			+ "INNER JOIN employee.employeeCategoryMaster employeeCategoryMaster "
			+ "INNER JOIN employee.employeeDesignationMaster employeeDesignationMaster "
			+ "INNER JOIN employee.identificationMaster identificationMaster "
			+ "WHERE employee.organizationId=:organizationId "
			+ "AND employee.unitId=:unitId ")
	
	
})

@Entity
@Table(name = "m_employee_master" , schema = "public")
@SequenceGenerator(name = "employee_master_seq" , sequenceName = "public.employee_master_seq" , allocationSize = 1)
public class EmployeeMaster {

	@Id
	@Column(name = "employee_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "employee_master_seq")
	private Integer employeeId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="access_card_number")
	private String accessCardNumber;
	
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
	
	@Column(name="identification_type_id")
	private Integer identificationTypeId;
	
	@Column(name="identification_number")
	private String identificationNumber;
	
	@Column(name="email")
	private String email;
	
	@Column(name="emp_category_id")
	private Integer empCategoryId;
	
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
	
	@Column(name="emp_designation_id")
	private Integer empDesignationId;
	
	@Column(name="emp_code")
	private String empCode;
	
	@Column(name="date_of_joining")
	private Date dateOfJoining;
	
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
	@JoinColumn(name = "speciality_id", insertable = false, updatable = false)
	private SpecialityMaster specialityMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sub_speciality_id", insertable = false, updatable = false)
	private SubSpecialityMaster subSpecialityMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "emp_category_id", insertable = false, updatable = false)
	private EmployeeCategoryMaster employeeCategoryMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "emp_designation_id", insertable = false, updatable = false)
	private EmployeeDesignationMaster employeeDesignationMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "identification_type_id", insertable = false, updatable = false)
	private IdentificationMaster identificationMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "occupation_id", insertable = false, updatable = false)
	private OccupationMaster occupationMaster;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "employeeMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EmployeeDetails> employeeDetailsList;
	

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
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

	public String getAccessCardNumber() {
		return accessCardNumber;
	}

	public void setAccessCardNumber(String accessCardNumber) {
		this.accessCardNumber = accessCardNumber;
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

	public Integer getEmpCategoryId() {
		return empCategoryId;
	}

	public void setEmpCategoryId(Integer empCategoryId) {
		this.empCategoryId = empCategoryId;
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

	public Integer getEmpDesignationId() {
		return empDesignationId;
	}

	public void setEmpDesignationId(Integer empDesignationId) {
		this.empDesignationId = empDesignationId;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
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
