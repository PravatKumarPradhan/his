package com.param.adt.admission.dto;

public class PatientRegistrationDto 
{
	private int patientId;
	
	private Integer organizationId;
	
	private Integer unitId;
	
	private String UHIDNumber;
	
	private Integer prefixId;
	
	private String prefixDesc;
	
	private String firstName;
	
	private String middleName;
	
	private String lastName;
	
	private Integer id;
	
	private String desc;
	
	private String dob;
	
	private String mobile;
			
	private String barCode;
	
	private String isVIP;
	
	private char VIPRemarks;
	
	private char isBlacklist;
	
	/*private Integer patientTypeId;*/
	
	private Integer patientCategoryId;
	
	private String patientCategory;

	private char isUnknownReg;
	
	private char status;
	
	private int createdBy;
	
	private int updatedBy;
	
	private String createdDate;

	private String updatedDate;

	private String visitType;
	
	
	
	public String getVisitType() {
		return visitType;
	}

	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	
	public String getPrefixDesc() {
		return prefixDesc;
	}

	public void setPrefixDesc(String prefixDesc) {
		this.prefixDesc = prefixDesc;
	}

	public String getPatientCategory() {
		return patientCategory;
	}

	public void setPatientCategory(String patientCategory) {
		this.patientCategory = patientCategory;
	}

	public String getUHIDNumber() {
		return UHIDNumber;
	}

	public void setUHIDNumber(String uHIDNumber) {
		UHIDNumber = uHIDNumber;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGenderName() {
		return desc;
	}

	public void setGenderName(String desc) {
		this.desc = desc;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getIsVIP() {
		return isVIP;
	}

	public void setIsVIP(String isVIP) {
		this.isVIP = isVIP;
	}

	public char getVIPRemarks() {
		return VIPRemarks;
	}

	public void setVIPRemarks(char vIPRemarks) {
		VIPRemarks = vIPRemarks;
	}

	public char getIsBlacklist() {
		return isBlacklist;
	}

	public void setIsBlacklist(char isBlacklist) {
		this.isBlacklist = isBlacklist;
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

	
	
}
