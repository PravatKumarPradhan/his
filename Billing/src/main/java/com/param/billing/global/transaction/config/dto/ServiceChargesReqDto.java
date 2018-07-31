package com.param.billing.global.transaction.config.dto;

import java.util.Date;
import java.util.List;

public class ServiceChargesReqDto {

	private Integer orgId;
	private Integer unitId;
	private Integer visitTypeId;
	private Integer serviceId;
	private Integer bedTypeId;
	private Integer patientTypeId;
	private Integer paymentTypeId;
	private Integer isStatTime;
	private Date serviceOrderTime;
	private List<Integer> listServiceId;
	
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
	public Integer getVisitTypeId() {
		return visitTypeId;
	}
	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public Integer getBedTypeId() {
		return bedTypeId;
	}
	public void setBedTypeId(Integer bedTypeId) {
		this.bedTypeId = bedTypeId;
	}
	public Integer getPatientTypeId() {
		return patientTypeId;
	}
	public void setPatientTypeId(Integer patientTypeId) {
		this.patientTypeId = patientTypeId;
	}
	public Integer getPaymentTypeId() {
		return paymentTypeId;
	}
	public void setPaymentTypeId(Integer paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}
	public Integer getIsStatTime() {
		return isStatTime;
	}
	public void setIsStatTime(Integer isStatTime) {
		this.isStatTime = isStatTime;
	}
	public Date getServiceOrderTime() {
		return serviceOrderTime;
	}
	public void setServiceOrderTime(Date serviceOrderTime) {
		this.serviceOrderTime = serviceOrderTime;
	}
	public List<Integer> getListServiceId() {
		return listServiceId;
	}
	public void setListServiceId(List<Integer> listServiceId) {
		this.listServiceId = listServiceId;
	}
	
	
	
}


