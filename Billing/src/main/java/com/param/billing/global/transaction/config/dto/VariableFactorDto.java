package com.param.billing.global.transaction.config.dto;

public class VariableFactorDto {

	private Integer serviceTarrifMasterId;
	
	private Integer visitTypeId;
	
	private Integer unitId;
	
	private Integer organisationId;
	
	private Integer bedTypeId; 
	
	private double multiplicationFactor;
	
	private Integer typeDiff;

	public Integer getServiceTarrifMasterId() {
		return serviceTarrifMasterId;
	}

	public void setServiceTarrifMasterId(Integer serviceTarrifMasterId) {
		this.serviceTarrifMasterId = serviceTarrifMasterId;
	}

	public Integer getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
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

	public Integer getTypeDiff() {
		return typeDiff;
	}

	public void setTypeDiff(Integer typeDiff) {
		this.typeDiff = typeDiff;
	}
	
	
}
