package com.param.service.dto;

public class ServiceForPackageReqDto {
	private Integer orgId;
	private Integer unitId;
	private Integer groupId;
	private Integer subGroupId;
	private String serviceCode;
	private String serviceDescription;
	private Integer tariffId;
	private Integer paymentEntitlementId;
	private Integer patientTypeId;
	private Integer packageMasterId;
	private Integer serviceId;
	private Integer age;
	private Integer sexId;
	private Integer packagetypeId;
	private Character isDoctorRequired;
	
	
	
	public Integer getSexId() {
		return sexId;
	}
	public void setSexId(Integer sexId) {
		this.sexId = sexId;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getPackageMasterId() {
		return packageMasterId;
	}
	public void setPackageMasterId(Integer packageMasterId) {
		this.packageMasterId = packageMasterId;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	
	public Integer getPatientTypeId() {
		return patientTypeId;
	}
	public void setPatientTypeId(Integer patientTypeId) {
		this.patientTypeId = patientTypeId;
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
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getSubGroupId() {
		return subGroupId;
	}
	public void setSubGroupId(Integer subGroupId) {
		this.subGroupId = subGroupId;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getServiceDescription() {
		return serviceDescription;
	}
	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	public Integer getPackagetypeId() {
		return packagetypeId;
	}
	public void setPackagetypeId(Integer packagetypeId) {
		this.packagetypeId = packagetypeId;
	}
	public Character getIsDoctorRequired() {
		return isDoctorRequired;
	}
	public void setIsDoctorRequired(Character isDoctorRequired) {
		this.isDoctorRequired = isDoctorRequired;
	}
	
	
}
