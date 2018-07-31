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
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries({
	
	@NamedQuery(name="GET_DEPENDENT_DETAILS",query="SELECT depandents.dependentDetailsId as dependentDetailsId, "
			+ "depandents.empDocId as empDocId, "
			+ "depandents.typeId as typeId, "
			+ "depandents.prefixId as prefixId, "
			+ "depandents.firstName as firstName, "
			+ "depandents.middleName as middleName, "
			+ "depandents.lastName as lastName, "
			+ "depandents.relationId as relationId, "
			+ "depandents.mobileNo as mobileNo, "
			+ "depandents.genderId as genderId, "
			+ "depandents.identificationNumber as identificationNumber, "
			+ "depandents.identificationTypeId as identificationTypeId, "
			+ "depandents.occupationId as occupationId, "
			+ "to_char(depandents.dobKin,'MM/DD/YYYY') as dobKin, "
			+ "identificationMaster.isExpiryRequired as isExpiryRequired, "
			+ "depandents.isNok as isNok "
			+ "FROM DependentDetails depandents "
			+ "INNER JOIN depandents.identificationMaster identificationMaster "
			+ "WHERE depandents.empDocId=:empDocId "
			+ "AND depandents.status='A' "
			+ "AND depandents.typeId=:typeId ")
})

@NamedNativeQueries({
	
	@NamedNativeQuery(name="GET_DOCTORS_DEPENDENT_DETAILS",query="SELECT depandents.dependent_details_id as \"dependentDetailsId\", "
			+ "depandents.emp_doc_id as \"empDocId\", "
			+ "depandents.type_id as \"typeId\", "
			+ "depandents.prefix_id as \"prefixId\", "
			+ "depandents.first_name as \"firstName\", "
			+ "depandents.middle_name as \"middleName\", "
			+ "depandents.last_name as \"lastName\", "
			+ "depandents.relation_id as \"relationId\", "
			+ "relation.relation_name as \"relationName\", "
			+ "depandents.mobile_no as \"mobileNumber\", "
			+ "depandents.gender_id as \"genderId\", "
			+ "gender.gender_code as \"genderCode\", "
			+ "depandents.identification_number as \"identificationNumber\", "
			+ "depandents.identification_type_id as \"identificationTypeId\", "
			+ "identification.identification_name as \"identificationName\", "
			+ "depandents.occupation_id as \"occupationId\", "
			+ "to_char(depandents.dob_kin,'MM/DD/YYYY') as \"dob\", "
			+ "trim(to_char(EXTRACT(year from AGE(CURRENT_TIMESTAMP, depandents.dob_kin)),'999')) as \"ageString\", "
			+ "depandents.is_nok as \"isNok\", "
			+ "concat('Dr. ',coalesce(doctor.first_name),' ',coalesce(doctor.middle_name),' ',coalesce(doctor.last_name)) as \"employeeName\", "
			+ "doctor.doc_code as \"empCode\", "
			+ "identification.is_expiry_required as \"isExpiryRequired\", "
			+ "speciality.speciality_name as \"specialityName\" "
			+ "FROM public.m_dependent_details depandents "
			+ "INNER JOIN public.m_gender_master gender ON depandents.gender_id=gender.gender_id "
			+ "INNER JOIN doctor.m_doctor_master doctor ON depandents.emp_doc_id = doctor.doctor_id "
			+ "INNER JOIN public.m_relation_master relation ON relation.relation_id =depandents.relation_id "
			+ "INNER JOIN doctor.m_speciality_master speciality ON doctor.speciality_id=speciality.speciality_id "
			+ "INNER JOIN adt.m_identification_master identification ON identification.identification_id=depandents.identification_type_id "
			+ "WHERE depandents.status='A' "
			+ "AND depandents.organization_id=:organizationId "
			+ "AND depandents.unit_id=:unitId"),
	
	@NamedNativeQuery(name="GET_EMPLOYEES_DEPENDENT_DETAILS",query="SELECT depandents.dependent_details_id as \"dependentDetailsId\", "
			+ "depandents.emp_doc_id as \"empDocId\", "
			+ "depandents.type_id as \"typeId\", "
			+ "depandents.prefix_id as \"prefixId\", "
			+ "depandents.first_name as \"firstName\", "
			+ "depandents.middle_name as \"middleName\", "
			+ "depandents.last_name as \"lastName\", "
			+ "depandents.relation_id as \"relationId\", "
			+ "relation.relation_name as \"relationName\", "
			+ "depandents.mobile_no as \"mobileNumber\", "
			+ "depandents.gender_id as \"genderId\", "
			+ "gender.gender_code as \"genderCode\", "
			+ "depandents.identification_number as \"identificationNumber\", "
			+ "depandents.identification_type_id as \"identificationTypeId\", "
			+ "identification.identification_name as \"identificationName\", "
			+ "depandents.occupation_id as \"occupationId\", "
			+ "to_char(depandents.dob_kin,'MM/DD/YYYY') as \"dob\", "
			+ "trim(to_char(EXTRACT(year from AGE(CURRENT_TIMESTAMP, depandents.dob_kin)),'999')) as \"ageString\", "
			+ "depandents.is_nok as \"isNok\", "
			+ "concat(coalesce(employee.first_name),' ',coalesce(employee.middle_name),' ',coalesce(employee.last_name)) as \"employeeName\", "
			+ "employee.emp_code as \"empCode\", "
			+ "identification.is_expiry_required as \"isExpiryRequired\", "
			+ "speciality.speciality_name as \"specialityName\" "
			+ "FROM public.m_dependent_details depandents "
			+ "INNER JOIN public.m_gender_master gender ON depandents.gender_id=gender.gender_id "
			+ "INNER JOIN public.m_employee_master employee ON depandents.emp_doc_id = employee.employee_id "
			+ "INNER JOIN public.m_relation_master relation ON relation.relation_id =depandents.relation_id "
			+ "INNER JOIN doctor.m_speciality_master speciality ON employee.speciality_id=speciality.speciality_id "
			+ "INNER JOIN adt.m_identification_master identification ON identification.identification_id=depandents.identification_type_id "
			+ "WHERE depandents.status='A' "
			+ "AND depandents.organization_id=:organizationId "
			+ "AND depandents.unit_id=:unitId")
})




@Entity
@Table(name = "m_dependent_details", schema = "public")
@SequenceGenerator(name = "dependent_details_seq", sequenceName = "public.dependent_details_seq", allocationSize = 1)
public class DependentDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dependent_details_seq")
	@Column(name = "dependent_details_id")
	private Integer dependentDetailsId;

	@Column(name = "organization_id")
	private Integer organizationId;

	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "emp_doc_id")
	private Integer empDocId;

	@Column(name = "type_id")
	private Integer typeId;

	@Column(name = "prefix_id")
	private Integer prefixId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "relation_id")
	private Integer relationId;

	@Column(name = "mobile_no")
	private String mobileNo;

	@Column(name = "gender_id")
	private Integer genderId;

	@Column(name = "identification_number")
	private String identificationNumber;

	@Column(name = "identification_type_id")
	private Integer identificationTypeId;
	
	@Column(name = "occupation_id")
	private Integer occupationId;

	@Column(name = "dob_kin")
	private Date dobKin;

	@Column(name = "is_nok")
	private char isNok;

	@Column(name = "status")
	private char status;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gender_id", insertable = false, updatable = false)
	private GenderMaster genderMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "speciality_id", insertable = false, updatable = false)
	private SpecialityMaster specialityMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "identification_type_id", insertable = false, updatable = false)
	private IdentificationMaster identificationMaster;
	
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

	public Integer getDependentDetailsId() {
		return dependentDetailsId;
	}

	public void setDependentDetailsId(Integer dependentDetailsId) {
		this.dependentDetailsId = dependentDetailsId;
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

	public Integer getEmpDocId() {
		return empDocId;
	}

	public void setEmpDocId(Integer empDocId) {
		this.empDocId = empDocId;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
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

	
	public Integer getGenderId() {
		return genderId;
	}

	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}

	public Integer getOccupationId() {
		return occupationId;
	}

	public void setOccupationId(Integer occupationId) {
		this.occupationId = occupationId;
	}

	public Date getDobKin() {
		return dobKin;
	}

	public void setDobKin(Date dobKin) {
		this.dobKin = dobKin;
	}

	public char getIsNok() {
		return isNok;
	}

	public void setIsNok(char isNok) {
		this.isNok = isNok;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
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
	
	

}