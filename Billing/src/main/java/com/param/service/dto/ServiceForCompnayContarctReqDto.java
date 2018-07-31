package com.param.service.dto;

import java.util.Date;

public class ServiceForCompnayContarctReqDto {
	
	private Integer orgId;
	private Integer unitId;
	private Integer tariffId;
	private Integer paymentEntitlementId;
	private Integer companyId;
	private Integer associateCompanyId;

	private Integer contractId;
	private String assCompanyName;
	private String tariffName;
	private String paymentEntitlementName;
	private String contractName;
	private Date validityFrom;
	private Date validityTo;
	private String insuranceName;
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	public Integer getTariffId() {
		return tariffId;
	}
	public void setTariffId(Integer tariffId) {
		this.tariffId = tariffId;
	}
	public Integer getPaymentEntitlementId() {
		return paymentEntitlementId;
	}
	public void setPaymentEntitlementId(Integer paymentEntitlementId) {
		this.paymentEntitlementId = paymentEntitlementId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getAssociateCompanyId() {
		return associateCompanyId;
	}
	public void setAssociateCompanyId(Integer associateCompanyId) {
		this.associateCompanyId = associateCompanyId;
	}
	public Integer getContractId() {
		return contractId;
	}
	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}
	public String getAssCompanyName() {
		return assCompanyName;
	}
	public void setAssCompanyName(String assCompanyName) {
		this.assCompanyName = assCompanyName;
	}
	public String getTariffName() {
		return tariffName;
	}
	public void setTariffName(String tariffName) {
		this.tariffName = tariffName;
	}
	public String getPaymentEntitlementName() {
		return paymentEntitlementName;
	}
	public void setPaymentEntitlementName(String paymentEntitlementName) {
		this.paymentEntitlementName = paymentEntitlementName;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public Date getValidityFrom() {
		return validityFrom;
	}
	public void setValidityFrom(Date validityFrom) {
		this.validityFrom = validityFrom;
	}
	public Date getValidityTo() {
		return validityTo;
	}
	public void setValidityTo(Date validityTo) {
		this.validityTo = validityTo;
	}
	public String getInsuranceName() {
		return insuranceName;
	}
	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}
	
	
	
	
	
	
}
