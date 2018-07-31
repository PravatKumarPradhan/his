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
@NamedQuery(name="GET_UNK_PATIENT_LIST_LIKE" , query= "SELECT patient.unknownPatientId as unknownPatientId, "
		+ "patient.patientName as patientName "
		/*+ "patient.UHIDNumber as UHIDNumber, "
		+ "patient.lastName as lastName "*/
		+ "FROM UnknownPatientRegistration patient "
		+ "WHERE LOWER(patient.patientName) LIKE :name "
		+ "AND patient.status='A' "
		+ "AND patient.unitId=:unitId "
		+ "AND patient.organizationId=:organizationId ")

/*@NamedQuery(name="GET_UNKNOWN_PATIENT_LIST",query="SELECT patient.unknownPatientId as unknownPatientId, "
		+ "patient.tUhid as tUhid, "
		+ "patient.patientName as patientName "
		+ "FROM UnknownPatientRegistration patient "
		+ "INNER JOIN patient.admissionsList adm "
		+ "INNER JOIN adm.admissionDetailsList admd "
		+ "WHERE patient.status='A' "
		+ "AND patient.unitId=:unitId "
		+ "AND patient.organizationId=:orgId ")*/
})

@NamedNativeQueries({
	
	@NamedNativeQuery(name="GET_UNKNOWN_PATIENT_LIST",query=
			"SELECT patient.unknown_patient_id as \"patientId\", "
			+ "patient.t_uhid as \"tUhid\", "
			+ "patient.patient_name as \"patientName\","
			+ "trim(to_char(patient.age,'999')) as \"ageString\","
			+ "patient.age_format as \"ageFormat\",  "
			+ "patient.gender_id as \"genderId\", "
			+ "gender.gender_code as \"genderCode\", "
			+ "patient.mobile as \"mobile\" "
			+ "FROM patient.m_unknown_patient_registration patient "
			+ "LEFT JOIN adt.t_admission adm on patient.unknown_patient_id = adm.t_patient_id "
			+ "LEFT JOIN adt.t_admission_details admd on adm.admission_id = admd.admission_id "
			+ "INNER JOIN public.m_gender_master as gender on gender.gender_id= patient.gender_id " 
			+ "WHERE patient.status='A' "
			+ "AND patient.unit_id=:unitId "
			+ "AND patient.organization_id=:orgId ")
})
@Entity
@Table(name="m_unknown_patient_registration",schema="patient")
@SequenceGenerator(name="unknown_patient_registration_seq",sequenceName="patient.unknown_patient_registration_seq",allocationSize=1)
public class UnknownPatientRegistration 
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="unknown_patient_registration_seq")
	@Column(name="unknown_patient_id")
	private int unknownPatientId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="age")
	private Double age;
	
	@Column(name="age_format")
	private char ageFormat;
	
	@Column(name="patient_name")
	private String patientName;
	
	@Column(name = "status")
	private char status;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name="t_uhid")
	private String tUhid;
	
	@Column(name="gender_id")
	private Integer genderId;
	
	@Column(name="p_patient_id ")
	private Integer pPatientId; 
	
	@Column(name="mobile")
	private String mobileNo;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gender_id", insertable = false, nullable = false, updatable = false)
	private GenderMaster genderMaster;
	
	public Integer getpPatientId() {
		return pPatientId;
	}

	public String getMobile() {
		return mobileNo;
	}

	public void setMobile(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public void setpPatientId(Integer pPatientId) {
		this.pPatientId = pPatientId;
	}


	public Integer getGenderId() {
		return genderId;
	}

	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}

	public String gettUhid() {
		return tUhid;
	}

	public void settUhid(String tUhid) {
		this.tUhid = tUhid;
	}


	public GenderMaster getGenderMaster() {
		return genderMaster;
	}

	public void setGenderMaster(GenderMaster genderMaster) {
		this.genderMaster = genderMaster;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public int getUnknownPatientId() {
		return unknownPatientId;
	}

	public void setUnknownPatientId(int unknownPatientId) {
		this.unknownPatientId = unknownPatientId;
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

	public Double getAge() {
		return age;
	}

	public void setAge(Double age) {
		this.age = age;
	}

	public char getAgeFormat() {
		return ageFormat;
	}

	public void setAgeFormat(char ageFormat) {
		this.ageFormat = ageFormat;
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

	
	
	
	
	
}
