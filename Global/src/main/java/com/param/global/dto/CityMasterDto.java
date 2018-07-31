package com.param.global.dto;

public class CityMasterDto {
	
	private int cityId;
	
	private String cityCode;
	
	private String cityName;

	private char status;
	
	private int createdBy;
	
	private String createdDate;
	
	private int stateId;
	
	private String stateName;
	
	private int countryId;
	
	private String countryName;
	
	private Integer districtId;
	
	private String districtName;
	
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

	
	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
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

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
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
	

	
	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "cityMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PatientMaster> listPatientMaste;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cityMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TempPatientMaster> listTempPatientMaster;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cityMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DoctorMaster> listDoctorMaster;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cityMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CompanyMaster> listCompanyMaster;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cityMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EmployeeMaster> listEmployeeMaster;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cityMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<HospitalMaster> listHospitalMaster;
	*/
	
	
	
}

