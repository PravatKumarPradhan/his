package com.param.global.dto;

import java.util.List;


public class SystemMasterDto {

	private Integer systemId;
	private String systemName;
	private String systemCode;
	private char status;
	private Integer organizationId;
	private Integer unitId;
	private Integer parentId;
	private Integer createdBy;
	private Integer updatedBy;
	private String createdDate;
	private String updatedDate;
	private Integer genderId;
	private Integer typeId;
	
	private List<ListSystemObservationMaster> listSystemObservationMaster;
	
	private List<ListSystemObervationPropertyMasterDto> listSystemObervationPropertyMasterDto;
	
	
	
	public List<ListSystemObservationMaster> getListSystemObservationMaster() {
		return listSystemObservationMaster;
	}
	public void setListSystemObservationMaster(
			List<ListSystemObservationMaster> listSystemObservationMaster) {
		this.listSystemObservationMaster = listSystemObservationMaster;
	}
	public List<ListSystemObervationPropertyMasterDto> getListSystemObervationPropertyMasterDto() {
		return listSystemObervationPropertyMasterDto;
	}
	public void setListSystemObervationPropertyMasterDto(
			List<ListSystemObervationPropertyMasterDto> listSystemObervationPropertyMasterDto) {
		this.listSystemObervationPropertyMasterDto = listSystemObervationPropertyMasterDto;
	}
	
	
	
	
	public Integer getGenderId() {
		return genderId;
	}
	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}
	public Integer getSystemId() {
		return systemId;
	}
	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getSystemCode() {
		return systemCode;
	}
	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
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
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	
	
	
}
