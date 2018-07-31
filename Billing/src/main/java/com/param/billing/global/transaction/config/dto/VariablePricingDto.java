package com.param.billing.global.transaction.config.dto;

import java.util.Date;
import java.util.List;

public class VariablePricingDto {
	
	private int serviceTarrifMasterId;
	
	private Integer serviceMasterId;
	
	private Integer visitTypeId;
	
	private Date fromDate;
	  
	private Date toDate;
	
	private Integer unitId;
	
	private Integer organisationId;
	
	private char status;
	
	private Integer createdBy;
	
	private Date createdDate;
	
	private Integer updatedBy;
	
	private Date updatedDate;
	
	private List<TarrifBedCategoryMpprDto> listBedCategoryDto;
	private List<TarrifPatientCategoryMpprDto> listPatientCategoryDto;
	private List<TarrifPaymentEntitlementMpprDto> listPaymentEntitlementDto;
	
	public int getServiceTarrifMasterId() {
		return serviceTarrifMasterId;
	}
	public void setServiceTarrifMasterId(int serviceTarrifMasterId) {
		this.serviceTarrifMasterId = serviceTarrifMasterId;
	}
	public Integer getServiceMasterId() {
		return serviceMasterId;
	}
	public void setServiceMasterId(Integer serviceMasterId) {
		this.serviceMasterId = serviceMasterId;
	}
	public Integer getVisitTypeId() {
		return visitTypeId;
	}
	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	public Integer getOrganisationId() {
		return organisationId;
	}
	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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
	public List<TarrifBedCategoryMpprDto> getListBedCategoryDto() {
		return listBedCategoryDto;
	}
	public void setListBedCategoryDto(
			List<TarrifBedCategoryMpprDto> listBedCategoryDto) {
		this.listBedCategoryDto = listBedCategoryDto;
	}
	public List<TarrifPatientCategoryMpprDto> getListPatientCategoryDto() {
		return listPatientCategoryDto;
	}
	public void setListPatientCategoryDto(
			List<TarrifPatientCategoryMpprDto> listPatientCategoryDto) {
		this.listPatientCategoryDto = listPatientCategoryDto;
	}
	public List<TarrifPaymentEntitlementMpprDto> getListPaymentEntitlementDto() {
		return listPaymentEntitlementDto;
	}
	public void setListPaymentEntitlementDto(
			List<TarrifPaymentEntitlementMpprDto> listPaymentEntitlementDto) {
		this.listPaymentEntitlementDto = listPaymentEntitlementDto;
	}

	
	
	

}
