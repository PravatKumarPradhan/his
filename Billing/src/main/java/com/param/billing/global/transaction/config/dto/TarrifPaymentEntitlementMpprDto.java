package com.param.billing.global.transaction.config.dto;

import java.util.Date;

public class TarrifPaymentEntitlementMpprDto {

	private Integer serviceTarrifPaymentEntitlementManagerId;
	
	private Integer serviceTarrifMasterId;
	  
	private Integer paymentEntitlementId;
	  
	private double multiplicationFactor;
	  
	private Integer unitId;

	private Integer orgnisationId;

	private char status;

	private Date createdDate;

	private Integer createdBy;

	private Date updatedDate;

	private Integer updatedBy;

	public Integer getServiceTarrifPaymentEntitlementManagerId() {
		return serviceTarrifPaymentEntitlementManagerId;
	}

	public void setServiceTarrifPaymentEntitlementManagerId(
			Integer serviceTarrifPaymentEntitlementManagerId) {
		this.serviceTarrifPaymentEntitlementManagerId = serviceTarrifPaymentEntitlementManagerId;
	}

	public Integer getServiceTarrifMasterId() {
		return serviceTarrifMasterId;
	}

	public void setServiceTarrifMasterId(Integer serviceTarrifMasterId) {
		this.serviceTarrifMasterId = serviceTarrifMasterId;
	}

	public Integer getPaymentEntitlementId() {
		return paymentEntitlementId;
	}

	public void setPaymentEntitlementId(Integer paymentEntitlementId) {
		this.paymentEntitlementId = paymentEntitlementId;
	}

	public double getMultiplicationFactor() {
		return multiplicationFactor;
	}

	public void setMultiplicationFactor(double multiplicationFactor) {
		this.multiplicationFactor = multiplicationFactor;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getOrgnisationId() {
		return orgnisationId;
	}

	public void setOrgnisationId(Integer orgnisationId) {
		this.orgnisationId = orgnisationId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
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

	
	
	
}
