package com.param.global.dto;

import java.util.List;

public class StateMasterDto {

	private int stateId;

	private String stateCode;

	private String stateName;

	private char status;

	private Integer countryId;

	private int createdBy;

	private String createdDate;

	private int updatedBy;

	private String updatedDate;

	private String countryName;

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

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
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

	private List<DistrictMasterDto> listDistrictMaster;

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
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

	public List<DistrictMasterDto> getListDistrictMaster() {
		return listDistrictMaster;
	}

	public void setListDistrictMaster(List<DistrictMasterDto> listDistrictMaster) {
		this.listDistrictMaster = listDistrictMaster;
	}

	/*
	 * public List<UnitMaster> getListUnitMaster() { return listUnitMaster; }
	 * 
	 * public void setListUnitMaster(List<UnitMaster> listUnitMaster) {
	 * this.listUnitMaster = listUnitMaster; }
	 */

	/*
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "stateMaster", cascade =
	 * CascadeType.ALL, orphanRemoval = true) private List<PatientMaster>
	 * listPatientMaste;
	 * 
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "stateMaster", cascade =
	 * CascadeType.ALL, orphanRemoval = true) private List<TempPatientMaster>
	 * listTempPatientMaster;
	 * 
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "stateMaster", cascade =
	 * CascadeType.ALL, orphanRemoval = true) private List<DoctorMaster>
	 * listDoctorMaster;
	 * 
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "stateMaster", cascade =
	 * CascadeType.ALL, orphanRemoval = true) private List<CompanyMaster>
	 * listCompanyMaster;
	 * 
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "stateMaster", cascade =
	 * CascadeType.ALL, orphanRemoval = true) private List<EmployeeMaster>
	 * listEmployeeMaster;
	 * 
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "stateMaster", cascade =
	 * CascadeType.ALL, orphanRemoval = true) private List<HospitalMaster>
	 * listHospitalMaster;
	 */

}
