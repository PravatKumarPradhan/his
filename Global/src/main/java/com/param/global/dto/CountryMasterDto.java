package com.param.global.dto;

import java.util.List;

public class CountryMasterDto {

	private int countryId;

	private String countryCode;

	private String countryName;

	private String countryInitial;

	private String countryCallingCode;

	private char status;

	private int createdBy;

	private String createdDate;

	private int updatedBy;

	private String updatedDate;

	private Integer organizationId;
	
	private Integer offset;

	private Integer noOfRecordsPerPage;
	
	

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getNoOfRecordsPerPage() {
		return noOfRecordsPerPage;
	}

	public void setNoOfRecordsPerPage(Integer noOfRecordsPerPage) {
		this.noOfRecordsPerPage = noOfRecordsPerPage;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	private List<StateMasterDto> listStateMaster;

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryInitial() {
		return countryInitial;
	}

	public void setCountryInitial(String countryInitial) {
		this.countryInitial = countryInitial;
	}

	public String getCountryCallingCode() {
		return countryCallingCode;
	}

	public void setCountryCallingCode(String countryCallingCode) {
		this.countryCallingCode = countryCallingCode;
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

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public List<StateMasterDto> getListStateMaster() {
		return listStateMaster;
	}

	public void setListStateMaster(List<StateMasterDto> listStateMaster) {
		this.listStateMaster = listStateMaster;
	}

	/*
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "countryMaster", cascade =
	 * CascadeType.ALL, orphanRemoval = true) private List<UnitMaster>
	 * listUnitMaster;
	 */

	/*
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "countryMaster", cascade =
	 * CascadeType.ALL, orphanRemoval = true) private List<PatientMaster>
	 * listPatientMaste;
	 * 
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "countryMaster", cascade =
	 * CascadeType.ALL, orphanRemoval = true) private List<TempPatientMaster>
	 * listTempPatientMaster;
	 * 
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "countryMaster", cascade =
	 * CascadeType.ALL, orphanRemoval = true) private List<DoctorMaster>
	 * listDoctorMaster;
	 * 
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "countryMaster", cascade =
	 * CascadeType.ALL, orphanRemoval = true) private List<CompanyMaster>
	 * listCompanyMaster;
	 * 
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "countryMaster", cascade =
	 * CascadeType.ALL, orphanRemoval = true) private List<EmployeeMaster>
	 * listEmployeeMaster;
	 * 
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "countryMaster", cascade =
	 * CascadeType.ALL, orphanRemoval = true) private List<HospitalMaster>
	 * listHospitalMaster;
	 */

}
