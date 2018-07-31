package com.param.global.dto;

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


public class AccountPayableMasterDto {
	private Integer accPayableMstId;
	private String accCode;
	private String accDesc;
	private String accPayableCode;
	private String accEmail;
	private String accMobile;
	private Date accWorkFromTime;
	private Date accWorkToTime;
	private Date accValidityFromDate;
	private Date accValidityToDate;
	private double accCreditLimit;
	private String accPaymentTerms;
	private String accAddress;
	private String accCountry;
	private String accState;
	private String accCity;
	private String accArea;
	private String accZipcode;
	private Integer accContactNumber;
	private Character status;
	private Integer countryId;
	private Integer createdBy;
	private Date createdDate;
	private Integer updatedBy;
	private Date updatedDate;
	private Integer orgId;
	private Integer orgUnitId;
	private Integer deptId;
	private Integer subDeptId;
	private Integer orderId;

	public Integer getAccPayableMstId() {
		return accPayableMstId;
	}

	public void setAccPayableMstId(Integer accPayableMstId) {
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
