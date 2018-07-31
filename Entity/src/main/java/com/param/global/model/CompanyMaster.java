package com.param.global.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.billing.global.model.DepositMaster;
import com.param.global.common.LocalTimeConverter;
import com.param.service.global.model.MCompanyContractMaster;

@NamedQueries({
	@NamedQuery(name="GET_ACTIVE_COMPANY_MASTER_LIST", 
			   query=" SELECT company.companyId as companyId, company.companyCode as companyCode, company.companyDesc as companyDesc,"
			   		+ " company.companyTypeId as companyTypeId"
			   		+ " FROM CompanyMaster company"
			   		+ " WHERE company.status='A' AND company.unitId=:unitId AND company.organizationId=:orgId")
})
@Entity
@Table(name="t_company_master", schema="public")
@SequenceGenerator(name = "company_master_seq", sequenceName = "public.company_master_seq", allocationSize = 1)
public class CompanyMaster {
	  
	@Id
	@Column(name = "company_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_master_seq")
	private int companyId;
	
	@Column(name = "company_code")
	private String companyCode;
	
	@Column(name = "company_desc")
	private String companyDesc;
	
	@Column(name = "company_type_id")
	private Integer companyTypeId;
	
	@Column(name = "credit_limit_days")
	private Integer creditLimitDays;
	
	
	@Column(name = "report_path")
	private String reportPath;
	
	@Column(name = "contact_person_name")
	private String contactPersonName;
	
	@Column(name = "person_address")
	private String personAddress;
	
	@Column(name = "country_id")
	private Integer countryId;
	
	@Column(name = "state_id")
	private Integer stateId;
	
	@Column(name = "city_id")
	private Integer cityId;
	
	@Column(name = "area")
	private String  area;
	
	@Column(name = "zipcode")
	private String zipcode;
	
	@Column(name = "mobile_no")
	private String mobileNo;
	
	@Column(name = "residence_phone_no")
	private String residencePhoneNo;
	
	@Column(name = "hospital_id")
	private Integer hospitalId;
	
	@Column(name = "updated_by")
	private Integer updatedBy;
	
	@Column(name = "updated_date")
	@Convert(converter = LocalTimeConverter.class)
	private Date updatedDate;
	
	@Column(name = "created_date")
	@Convert(converter = LocalTimeConverter.class)
	private Date createdDate;
	
	@Column(name = "created_by")
	private Integer createdBy;
	
	@Column(name = "unit_id")
	private Integer unitId;
	
	@Column(name = "organization_id")
	private Integer organizationId;
	
	@Column(name="status")
	private Character status;
	
	@OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "companyMaster")
	private List<DepositMaster> listDepositMaster;
	
	@OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "companyMaster")
	private List<MCompanyContractMaster> listMCompanyContractMaster;

	public List<MCompanyContractMaster> getListMCompanyContractMaster() {
		return listMCompanyContractMaster;
	}

	public void setListMCompanyContractMaster(List<MCompanyContractMaster> listMCompanyContractMaster) {
		this.listMCompanyContractMaster = listMCompanyContractMaster;
	}

	public List<DepositMaster> getListDepositMaster() {
		return listDepositMaster;
	}

	public void setListDepositMaster(List<DepositMaster> listDepositMaster) {
		this.listDepositMaster = listDepositMaster;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyDesc() {
		return companyDesc;
	}

	public void setCompanyDesc(String companyDesc) {
		this.companyDesc = companyDesc;
	}

	public Integer getCompanyTypeId() {
		return companyTypeId;
	}

	public void setCompanyTypeId(Integer companyTypeId) {
		this.companyTypeId = companyTypeId;
	}

	public Integer getCreditLimitDays() {
		return creditLimitDays;
	}

	public void setCreditLimitDays(Integer creditLimitDays) {
		this.creditLimitDays = creditLimitDays;
	}

	public String getReportPath() {
		return reportPath;
	}

	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}

	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public String getPersonAddress() {
		return personAddress;
	}

	public void setPersonAddress(String personAddress) {
		this.personAddress = personAddress;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getResidencePhoneNo() {
		return residencePhoneNo;
	}

	public void setResidencePhoneNo(String residencePhoneNo) {
		this.residencePhoneNo = residencePhoneNo;
	}

	public Integer getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}
	
}
