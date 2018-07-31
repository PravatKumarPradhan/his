package com.param.global.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.global.common.LocalTimeConverter;


@Entity
@Table(name = "m_account_payable_master", schema = "public")
@SequenceGenerator(name = "m_seq_account_payable_master", sequenceName = "public.m_seq_account_payable_master", allocationSize = 1)
public class AccountPayableMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_account_payable_master")
	@Column(name = "acc_payable_mst_id")
	private int accPayableMstId;

	@Column(name = "acc_code")
	private String accCode;

	@Column(name = "acc_desc")
	private String accDesc;
	
	@Column(name = "acc_payable_code")
	private String accPayableCode;
	
	@Column(name = "acc_email")
	private String accEmail;
	
	@Column(name = "acc_mobile ")
	private String accMobile;
	
	@Column(name = "acc_work_from_time")
	@Convert(converter = LocalTimeConverter.class)
	private Date accWorkFromTime;
	
	@Column(name = "acc_work_to_time")
	@Convert(converter = LocalTimeConverter.class)
	private Date accWorkToTime;
	
	@Column(name = "acc_validity_from_date")
	@Convert(converter = LocalTimeConverter.class)
	private Date accValidityFromDate;
	
	@Column(name = "acc_validity_to_date")
	@Convert(converter = LocalTimeConverter.class)
	private Date accValidityToDate;
	
	@Column(name = "acc_credit_limit")
	private double accCreditLimit;
	
	@Column(name = "acc_payment_terms ")
	private String accPaymentTerms;
	
	@Column(name = "acc_address ")
	private String accAddress;
	
	@Column(name = "acc_country ")
	private String accCountry;
	
	@Column(name = "acc_state ")
	private String accState;
	
	@Column(name = "acc_city ")
	private String accCity;
	
	@Column(name = "acc_area ")
	private String accArea;
	
	@Column(name = "acc_zipcode ")
	private String accZipcode;
	
	@Column(name = "acc_contact_number")
	private Integer accContactNumber;
	
	@Column(name = "status")
	private char status;

	@Column(name = "country_id")
	private Integer countryId;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "created_date")
	@Convert(converter = LocalTimeConverter.class)
	private Date createdDate;

	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "updated_date")
	@Convert(converter = LocalTimeConverter.class)
	private Date updatedDate;
	
	@Column(name="org_id")
	private Integer orgId;
	
	@Column(name="org_unit_id")
	private Integer orgUnitId;
	
	@Column(name="dept_id")
	private Integer deptId;
	
	@Column(name="sub_dept_id")
	private Integer subDeptId;
	
	@Column(name="order_id")
	private Integer orderId;

	public int getAccPayableMstId() {
		return accPayableMstId;
	}

	public void setAccPayableMstId(int accPayableMstId) {
		this.accPayableMstId = accPayableMstId;
	}

	public String getAccCode() {
		return accCode;
	}

	public void setAccCode(String accCode) {
		this.accCode = accCode;
	}

	public String getAccDesc() {
		return accDesc;
	}

	public void setAccDesc(String accDesc) {
		this.accDesc = accDesc;
	}

	public String getAccPayableCode() {
		return accPayableCode;
	}

	public void setAccPayableCode(String accPayableCode) {
		this.accPayableCode = accPayableCode;
	}

	public String getAccEmail() {
		return accEmail;
	}

	public void setAccEmail(String accEmail) {
		this.accEmail = accEmail;
	}

	public String getAccMobile() {
		return accMobile;
	}

	public void setAccMobile(String accMobile) {
		this.accMobile = accMobile;
	}

	public Date getAccWorkFromTime() {
		return accWorkFromTime;
	}

	public void setAccWorkFromTime(Date accWorkFromTime) {
		this.accWorkFromTime = accWorkFromTime;
	}

	public Date getAccWorkToTime() {
		return accWorkToTime;
	}

	public void setAccWorkToTime(Date accWorkToTime) {
		this.accWorkToTime = accWorkToTime;
	}

	public Date getAccValidityFromDate() {
		return accValidityFromDate;
	}

	public void setAccValidityFromDate(Date accValidityFromDate) {
		this.accValidityFromDate = accValidityFromDate;
	}

	public Date getAccValidityToDate() {
		return accValidityToDate;
	}

	public void setAccValidityToDate(Date accValidityToDate) {
		this.accValidityToDate = accValidityToDate;
	}

	public double getAccCreditLimit() {
		return accCreditLimit;
	}

	public void setAccCreditLimit(double accCreditLimit) {
		this.accCreditLimit = accCreditLimit;
	}

	public String getAccPaymentTerms() {
		return accPaymentTerms;
	}

	public void setAccPaymentTerms(String accPaymentTerms) {
		this.accPaymentTerms = accPaymentTerms;
	}

	public String getAccAddress() {
		return accAddress;
	}

	public void setAccAddress(String accAddress) {
		this.accAddress = accAddress;
	}

	public String getAccCountry() {
		return accCountry;
	}

	public void setAccCountry(String accCountry) {
		this.accCountry = accCountry;
	}

	public String getAccState() {
		return accState;
	}

	public void setAccState(String accState) {
		this.accState = accState;
	}

	public String getAccCity() {
		return accCity;
	}

	public void setAccCity(String accCity) {
		this.accCity = accCity;
	}

	public String getAccArea() {
		return accArea;
	}

	public void setAccArea(String accArea) {
		this.accArea = accArea;
	}

	public String getAccZipcode() {
		return accZipcode;
	}

	public void setAccZipcode(String accZipcode) {
		this.accZipcode = accZipcode;
	}

	public Integer getAccContactNumber() {
		return accContactNumber;
	}

	public void setAccContactNumber(Integer accContactNumber) {
		this.accContactNumber = accContactNumber;
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

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getOrgUnitId() {
		return orgUnitId;
	}

	public void setOrgUnitId(Integer orgUnitId) {
		this.orgUnitId = orgUnitId;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public Integer getSubDeptId() {
		return subDeptId;
	}

	public void setSubDeptId(Integer subDeptId) {
		this.subDeptId = subDeptId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	
	
	
	
}
