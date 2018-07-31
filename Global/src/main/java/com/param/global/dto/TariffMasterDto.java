package com.param.global.dto;

import java.util.List;

public class TariffMasterDto {
	
	private Integer tariffId;
	
	private Integer organizationId;
	
	private Integer unitId;
	
	private String tariffCode;
	
	private String tariffDesc;
	
	private String validFrom;
	
	private String validTo;
	
	private Integer applicablePaymentEntitlementId;
	
	private String createdDate;
	
	private Integer createdBy;
	
	private String updatedDate;
	
	private Integer updatedBy;
	
	private Character status;
	
	private String paymentEntitlementDesc;
	
	private Integer tariffPaymentEntitlementMapperId;
	
	private List<Integer> applicablePaymentEntitlementIdList;
	
	private List<Integer> applicablePaymentEntitlementIdListOld;
	
	private List<TariffMasterDto> applicablePaymentEntitlementIdList2;
	
	private List<Integer> tariffIdList;
	
	public List<Integer> getTariffIdList() {
		return tariffIdList;
	}

	public void setTariffIdList(List<Integer> tariffIdList) {
		this.tariffIdList = tariffIdList;
	}

	public Integer getTariffPaymentEntitlementMapperId() {
		return tariffPaymentEntitlementMapperId;
	}

	public void setTariffPaymentEntitlementMapperId(Integer tariffPaymentEntitlementMapperId) {
		this.tariffPaymentEntitlementMapperId = tariffPaymentEntitlementMapperId;
	}

	public List<Integer> getApplicablePaymentEntitlementIdListOld() {
		return applicablePaymentEntitlementIdListOld;
	}

	public void setApplicablePaymentEntitlementIdListOld(List<Integer> applicablePaymentEntitlementIdListOld) {
		this.applicablePaymentEntitlementIdListOld = applicablePaymentEntitlementIdListOld;
	}

	public List<TariffMasterDto> getApplicablePaymentEntitlementIdList2() {
		return applicablePaymentEntitlementIdList2;
	}

	public void setApplicablePaymentEntitlementIdList2(List<TariffMasterDto> applicablePaymentEntitlementIdList2) {
		this.applicablePaymentEntitlementIdList2 = applicablePaymentEntitlementIdList2;
	}

	public String getPaymentEntitlementDesc() {
		return paymentEntitlementDesc;
	}

	public void setPaymentEntitlementDesc(String paymentEntitlementDesc) {
		this.paymentEntitlementDesc = paymentEntitlementDesc;
	}

	public List<Integer> getApplicablePaymentEntitlementIdList() {
		return applicablePaymentEntitlementIdList;
	}

	public void setApplicablePaymentEntitlementIdList(List<Integer> applicablePaymentEntitlementIdList) {
		this.applicablePaymentEntitlementIdList = applicablePaymentEntitlementIdList;
	}

	public Integer getTariffId() {
		return tariffId;
	}

	public void setTariffId(Integer tariffId) {
		this.tariffId = tariffId;
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

	public String getTariffCode() {
		return tariffCode;
	}

	public void setTariffCode(String tariffCode) {
		this.tariffCode = tariffCode;
	}

	public String getTariffDesc() {
		return tariffDesc;
	}

	public void setTariffDesc(String tariffDesc) {
		this.tariffDesc = tariffDesc;
	}

	public String getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}

	public String getValidTo() {
		return validTo;
	}

	public void setValidTo(String validTo) {
		this.validTo = validTo;
	}

	public Integer getApplicablePaymentEntitlementId() {
		return applicablePaymentEntitlementId;
	}

	public void setApplicablePaymentEntitlementId(Integer applicablePaymentEntitlementId) {
		this.applicablePaymentEntitlementId = applicablePaymentEntitlementId;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}
	
}
