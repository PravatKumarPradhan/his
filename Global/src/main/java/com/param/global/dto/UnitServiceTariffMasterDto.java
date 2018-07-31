package com.param.global.dto;

import java.util.Date;
import java.util.List;

public class UnitServiceTariffMasterDto 
{
	private Integer organizationId;
	
	private Integer unitId;
	
	private Integer tariffId;
	
	private String tariffDesc;
	
	private Integer paymentEntitlementId;
	
	private String paymentEntitlementDesc;
	
	private Integer patientTypeId;
	
	private String patientTypeDesc;
	
	private Integer visitTypeId;
	
	private String visitTypeDesc;
	
	private Integer billingBedCatId;
	
	private String billingBedCatDesc;
	
	private Integer specialityId;
	
	private String specialityDesc;
	
	private Integer subSpecialityId;
	
	private String subSpecialityDesc;
	
	private Integer serviceId;
	
	private String serviceDesc;
	
	private Double rate;
	
	private String statPer;
	
	private String effectiveDate;
	
	private Integer unitServiceTriffId;
	
	private Integer billingBedCategoryId;
	
	private Date createdDate;
	
	private Integer createdBy;
	
	private Date updatedDate;
	
	private Integer updatedBy;
	
	private Character status;
	
	private List<MultiSelectDto> tariffMasterList;
	
	private List<MultiSelectDto> paymentEntitlementList;
	
	private List<MultiSelectDto> visitTypeList;
	
	private List<MultiSelectDto> patientTypeList;
	
	private List<MultiSelectDto> bedCategoryList;
	
	private List<Integer> specialityList;
	
	private List<Integer> subSpecialityList;
	
	private List<Integer> serviceList;
	
	private Integer alreadyExist;
	
	private String orderDate;
	
	private List<UnitServiceTariffMasterDto> unitServiceTariffMasterDtosList;
	
	private List<Integer> listServiceId;
	
	public List<Integer> getListServiceId() {
		return listServiceId;
	}

	public void setListServiceId(List<Integer> listServiceId) {
		this.listServiceId = listServiceId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public Integer getAlreadyExist() {
		return alreadyExist;
	}

	public void setAlreadyExist(Integer alreadyExist) {
		this.alreadyExist = alreadyExist;
	}

	public Integer getUnitServiceTriffId() {
		return unitServiceTriffId;
	}

	public void setUnitServiceTriffId(Integer unitServiceTriffId) {
		this.unitServiceTriffId = unitServiceTriffId;
	}

	public Integer getBillingBedCategoryId() {
		return billingBedCategoryId;
	}

	public void setBillingBedCategoryId(Integer billingBedCategoryId) {
		this.billingBedCategoryId = billingBedCategoryId;
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

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
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

	public List<UnitServiceTariffMasterDto> getUnitServiceTariffMasterDtosList() {
		return unitServiceTariffMasterDtosList;
	}

	public void setUnitServiceTariffMasterDtosList(List<UnitServiceTariffMasterDto> unitServiceTariffMasterDtosList) {
		this.unitServiceTariffMasterDtosList = unitServiceTariffMasterDtosList;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public List<MultiSelectDto> getTariffMasterList() {
		return tariffMasterList;
	}

	public void setTariffMasterList(List<MultiSelectDto> tariffMasterList) {
		this.tariffMasterList = tariffMasterList;
	}

	public List<MultiSelectDto> getPaymentEntitlementList() {
		return paymentEntitlementList;
	}

	public void setPaymentEntitlementList(List<MultiSelectDto> paymentEntitlementList) {
		this.paymentEntitlementList = paymentEntitlementList;
	}

	public List<MultiSelectDto> getVisitTypeList() {
		return visitTypeList;
	}

	public void setVisitTypeList(List<MultiSelectDto> visitTypeList) {
		this.visitTypeList = visitTypeList;
	}

	public List<MultiSelectDto> getPatientTypeList() {
		return patientTypeList;
	}

	public void setPatientTypeList(List<MultiSelectDto> patientTypeList) {
		this.patientTypeList = patientTypeList;
	}

	public List<MultiSelectDto> getBedCategoryList() {
		return bedCategoryList;
	}

	public void setBedCategoryList(List<MultiSelectDto> bedCategoryList) {
		this.bedCategoryList = bedCategoryList;
	}

	public Integer getTariffId() {
		return tariffId;
	}

	public void setTariffId(Integer tariffId) {
		this.tariffId = tariffId;
	}

	public String getTariffDesc() {
		return tariffDesc;
	}

	public void setTariffDesc(String tariffDesc) {
		this.tariffDesc = tariffDesc;
	}

	public Integer getPaymentEntitlementId() {
		return paymentEntitlementId;
	}

	public void setPaymentEntitlementId(Integer paymentEntitlementId) {
		this.paymentEntitlementId = paymentEntitlementId;
	}

	public String getPaymentEntitlementDesc() {
		return paymentEntitlementDesc;
	}

	public void setPaymentEntitlementDesc(String paymentEntitlementDesc) {
		this.paymentEntitlementDesc = paymentEntitlementDesc;
	}

	public Integer getPatientTypeId() {
		return patientTypeId;
	}

	public void setPatientTypeId(Integer patientTypeId) {
		this.patientTypeId = patientTypeId;
	}

	public String getPatientTypeDesc() {
		return patientTypeDesc;
	}

	public void setPatientTypeDesc(String patientTypeDesc) {
		this.patientTypeDesc = patientTypeDesc;
	}

	public Integer getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public String getVisitTypeDesc() {
		return visitTypeDesc;
	}

	public void setVisitTypeDesc(String visitTypeDesc) {
		this.visitTypeDesc = visitTypeDesc;
	}

	public Integer getBillingBedCatId() {
		return billingBedCatId;
	}

	public void setBillingBedCatId(Integer billingBedCatId) {
		this.billingBedCatId = billingBedCatId;
	}

	public String getBillingBedCatDesc() {
		return billingBedCatDesc;
	}

	public void setBillingBedCatDesc(String billingBedCatDesc) {
		this.billingBedCatDesc = billingBedCatDesc;
	}

	public Integer getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(Integer specialityId) {
		this.specialityId = specialityId;
	}

	public String getSpecialityDesc() {
		return specialityDesc;
	}

	public void setSpecialityDesc(String specialityDesc) {
		this.specialityDesc = specialityDesc;
	}

	public Integer getSubSpecialityId() {
		return subSpecialityId;
	}

	public void setSubSpecialityId(Integer subSpecialityId) {
		this.subSpecialityId = subSpecialityId;
	}

	public String getSubSpecialityDesc() {
		return subSpecialityDesc;
	}

	public void setSubSpecialityDesc(String subSpecialityDesc) {
		this.subSpecialityDesc = subSpecialityDesc;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceDesc() {
		return serviceDesc;
	}

	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public String getStatPer() {
		return statPer;
	}

	public void setStatPer(String statPer) {
		this.statPer = statPer;
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

	public List<Integer> getSpecialityList() {
		return specialityList;
	}

	public void setSpecialityList(List<Integer> specialityList) {
		this.specialityList = specialityList;
	}

	public List<Integer> getSubSpecialityList() {
		return subSpecialityList;
	}

	public void setSubSpecialityList(List<Integer> subSpecialityList) {
		this.subSpecialityList = subSpecialityList;
	}

	public List<Integer> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<Integer> serviceList) {
		this.serviceList = serviceList;
	}
	
	
	
}
