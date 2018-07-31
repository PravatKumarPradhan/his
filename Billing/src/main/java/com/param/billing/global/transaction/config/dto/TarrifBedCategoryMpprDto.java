package com.param.billing.global.transaction.config.dto;

import java.util.Date;

public class TarrifBedCategoryMpprDto {
	
	private Integer tarrifBedTypeManagerId;

	private int serviceTarrifId;
	
	private Integer bedTypeId;
	  
	private double multiplicationFactor;
	  
	private Integer unitId;

	private Integer orgnisationId;

	private char status;

	private Date createdDate;

	private Integer createdBy;

	private Date updatedDate;

	public Integer getTarrifBedTypeManagerId() {
		return tarrifBedTypeManagerId;
	}

	public void setTarrifBedTypeManagerId(Integer tarrifBedTypeManagerId) {
		this.tarrifBedTypeManagerId = tarrifBedTypeManagerId;
	}

	public int getServiceTarrifId() {
		return serviceTarrifId;
	}

	public void setServiceTarrifId(int serviceTarrifId) {
		this.serviceTarrifId = serviceTarrifId;
	}

	public Integer getBedTypeId() {
		return bedTypeId;
	}

	public void setBedTypeId(Integer bedTypeId) {
		this.bedTypeId = bedTypeId;
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
	
	
}
