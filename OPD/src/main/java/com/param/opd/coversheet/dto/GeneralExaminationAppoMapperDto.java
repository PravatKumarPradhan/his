package com.param.opd.coversheet.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;


public class GeneralExaminationAppoMapperDto {

	private Integer generalExamAppoMapperId;
	private Integer systemId;
	private Integer systemObervationId;
	private Integer systemObervationPropertyId;
	private char isNADValue;
	private String remark;
	private char status;
	private Integer appointmentId;
	private Integer patientId;
	private Integer unitId;
	private Integer organizationId;
	private Integer createdBy;
	private Integer updatedBy;
	private String createdDate;
	private String updatedDate;
	private Integer typeId;
	private Integer genderId;
	private Integer encounterId;
	
	private String systemName;
	private String observationName;
	private String propertyName;
	
	private List<ListOfGeneralExaminationAppoMapperDto> listOfGeneralExaminationAppoMapperDto;
	
	private List<ListOfGeneralExamSystemMaster> listOfListOfGeneralExamSystemMaster;
	
	
	
	
	
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getObservationName() {
		return observationName;
	}
	public void setObservationName(String observationName) {
		this.observationName = observationName;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public Integer getEncounterId() {
		return encounterId;
	}
	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}
	public Integer getGenderId() {
		return genderId;
	}
	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}
	public List<ListOfGeneralExamSystemMaster> getListOfListOfGeneralExamSystemMaster() {
		return listOfListOfGeneralExamSystemMaster;
	}
	public void setListOfListOfGeneralExamSystemMaster(
			List<ListOfGeneralExamSystemMaster> listOfListOfGeneralExamSystemMaster) {
		this.listOfListOfGeneralExamSystemMaster = listOfListOfGeneralExamSystemMaster;
	}
	public List<ListOfGeneralExaminationAppoMapperDto> getListOfGeneralExaminationAppoMapperDto() {
		return listOfGeneralExaminationAppoMapperDto;
	}
	public void setListOfGeneralExaminationAppoMapperDto(
			List<ListOfGeneralExaminationAppoMapperDto> listOfGeneralExaminationAppoMapperDto) {
		this.listOfGeneralExaminationAppoMapperDto = listOfGeneralExaminationAppoMapperDto;
	}
	
	
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Integer getGeneralExamAppoMapperId() {
		return generalExamAppoMapperId;
	}
	public void setGeneralExamAppoMapperId(Integer generalExamAppoMapperId) {
		this.generalExamAppoMapperId = generalExamAppoMapperId;
	}
	
	public char getIsNADValue() {
		return isNADValue;
	}
	public void setIsNADValue(char isNADValue) {
		this.isNADValue = isNADValue;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public Integer getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
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
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Integer getSystemId() {
		return systemId;
	}
	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}
	public Integer getSystemObervationId() {
		return systemObervationId;
	}
	public void setSystemObervationId(Integer systemObervationId) {
		this.systemObervationId = systemObervationId;
	}
	public Integer getSystemObervationPropertyId() {
		return systemObervationPropertyId;
	}
	public void setSystemObervationPropertyId(Integer systemObervationPropertyId) {
		this.systemObervationPropertyId = systemObervationPropertyId;
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	
	
}
