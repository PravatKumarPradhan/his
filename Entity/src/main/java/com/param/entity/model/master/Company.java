package com.param.entity.model.master;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "Company")
@Table(name = "t_company_master", schema = "public")
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="company_id", unique=true, nullable=false)
	private Integer companyId;

	@Column(length=100)
	private String area;

	@Column(name="city_id")
	private Integer cityId;

	@Column(name="company_code", length=20)
	private String companyCode;

	@Column(name="company_desc", length=100)
	private String companyDesc;

	@Column(name="contact_person_name", length=100)
	private String contactPersonName;

	@Column(name="country_id")
	private Integer countryId;

	@Column(name="created_by")
	private Integer createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;

	@Column(name="credit_limit_days")
	private Integer creditLimitDays;

	@Column(name="hospital_id")
	private Integer hospitalId;

	@Column(name="marathi_company_desc", length=2147483647)
	private String marathiCompanyDesc;

	@Column(name="mobile_no", length=11)
	private String mobileNo;

	@Column(name="organization_id")
	private Integer organizationId;

	@Column(name="person_address", length=2147483647)
	private String personAddress;

	@Column(name="report_path", length=200)
	private String reportPath;

	@Column(name="residence_phone_no", length=20)
	private String residencePhoneNo;

	@Column(name="state_id")
	private Integer stateId;

	@Column(length=1)
	private String status;

	@Column(name="unit_id")
	private Integer unitId;

	@Column(name="updated_by")
	private Integer updatedBy;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	@Column(length=10)
	private String zipcode;


	@OneToMany(fetch = FetchType.LAZY, mappedBy="company")
	private List<AssociatedCompany> associatedCompanyList;


	@OneToMany(fetch = FetchType.LAZY, mappedBy="company")
	private List<CompanyContractDetail> companyContractDetailsList;


	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="company_type_id")
	private CompanyType companyType;

	public Company() {
	}

	public Integer getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCompanyCode() {
		return this.companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyDesc() {
		return this.companyDesc;
	}

	public void setCompanyDesc(String companyDesc) {
		this.companyDesc = companyDesc;
	}

	public String getContactPersonName() {
		return this.contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public Integer getCountryId() {
		return this.countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCreditLimitDays() {
		return this.creditLimitDays;
	}

	public void setCreditLimitDays(Integer creditLimitDays) {
		this.creditLimitDays = creditLimitDays;
	}

	public Integer getHospitalId() {
		return this.hospitalId;
	}

	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getMarathiCompanyDesc() {
		return this.marathiCompanyDesc;
	}

	public void setMarathiCompanyDesc(String marathiCompanyDesc) {
		this.marathiCompanyDesc = marathiCompanyDesc;
	}

	public String getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Integer getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getPersonAddress() {
		return this.personAddress;
	}

	public void setPersonAddress(String personAddress) {
		this.personAddress = personAddress;
	}

	public String getReportPath() {
		return this.reportPath;
	}

	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}

	public String getResidencePhoneNo() {
		return this.residencePhoneNo;
	}

	public void setResidencePhoneNo(String residencePhoneNo) {
		this.residencePhoneNo = residencePhoneNo;
	}

	public Integer getStateId() {
		return this.stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getUnitId() {
		return this.unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public List<AssociatedCompany> getAssociatedCompanyList() {
		return this.associatedCompanyList;
	}

	public void setAssociatedCompanyList(List<AssociatedCompany> associatedCompanyList) {
		this.associatedCompanyList = associatedCompanyList;
	}

	public AssociatedCompany addAssociatedCompanyList(AssociatedCompany associatedCompanyList) {
		getAssociatedCompanyList().add(associatedCompanyList);
		associatedCompanyList.setCompany(this);

		return associatedCompanyList;
	}

	public AssociatedCompany removeAssociatedCompanyList(AssociatedCompany associatedCompanyList) {
		getAssociatedCompanyList().remove(associatedCompanyList);
		associatedCompanyList.setCompany(null);

		return associatedCompanyList;
	}

	public List<CompanyContractDetail> getCompanyContractDetailsList() {
		return this.companyContractDetailsList;
	}

	public void setCompanyContractDetailsList(List<CompanyContractDetail> companyContractDetailsList) {
		this.companyContractDetailsList = companyContractDetailsList;
	}

	public CompanyContractDetail addCompanyContractDetailsList(CompanyContractDetail companyContractDetailsList) {
		getCompanyContractDetailsList().add(companyContractDetailsList);
		companyContractDetailsList.setCompany(this);

		return companyContractDetailsList;
	}

	public CompanyContractDetail removeCompanyContractDetailsList(CompanyContractDetail companyContractDetailsList) {
		getCompanyContractDetailsList().remove(companyContractDetailsList);
		companyContractDetailsList.setCompany(null);

		return companyContractDetailsList;
	}

	public CompanyType getCompanyType() {
		return this.companyType;
	}

	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}

}