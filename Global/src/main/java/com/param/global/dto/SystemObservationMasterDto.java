package com.param.global.dto;

import java.util.List;

import com.param.global.common.GlobalCommonDateUtils;


public class SystemObservationMasterDto {

	private Integer observationId;
	private String observationName;
	private Integer systemId;
	private String systemName;
	private String observationCode;
	private char isPropertyRequired;
	
	private Integer propertyId;
	private String propertyName;
	
	private char status;
	private Integer unitId;
	private Integer organizationId;
	private Integer createdBy;
	private Integer updatedBy;
	private String createdDate;
	private String updatedDate;
	
	private Character propertyStatus;
	
	
	
	public Character getPropertyStatus() {
		return propertyStatus;
	}
	public void setPropertyStatus(Character propertyStatus) {
		this.propertyStatus = propertyStatus;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	private List<SystemPropertyDto> listSystemPropertyDto;
	
	
	
	
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public Integer getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public List<SystemPropertyDto> getListSystemPropertyDto() {
		return listSystemPropertyDto;
	}
	public void setListSystemPropertyDto(
			List<SystemPropertyDto> listSystemPropertyDto) {
		this.listSystemPropertyDto = listSystemPropertyDto;
	}
	public Integer getObservationId() {
		return observationId;
	}
	public void setObservationId(Integer observationId) {
		this.observationId = observationId;
	}
	public String getObservationName() {
		return observationName;
	}
	public void setObservationName(String observationName) {
		this.observationName = observationName;
	}
	public Integer getSystemId() {
		return systemId;
	}
	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}
	public String getObservationCode() {
		return observationCode;
	}
	public void setObservationCode(String observationCode) {
		this.observationCode = observationCode;
	}
	public char getIsPropertyRequired() {
		return isPropertyRequired;
	}
	public void setIsPropertyRequired(char isPropertyRequired) {
		this.isPropertyRequired = isPropertyRequired;
	}
	
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	public Integer getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
}
