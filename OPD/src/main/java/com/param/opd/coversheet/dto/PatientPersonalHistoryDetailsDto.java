package com.param.opd.coversheet.dto;

import java.util.List;


public class PatientPersonalHistoryDetailsDto {

	private Integer personalHistoryDetailsId;
	private String description;
	private String remark;
	private Integer encounterId;
	private Integer patientId;
	private char isNoSignificantHistoryStatus;
	private Integer organizationId;
	private Integer unitId;
	private char status;
	private Integer createdBy;
	private Integer updatedBy;
	private String createdDate;
	private String updatedDate;
	private Integer roleId;
	private char isReviewedFlag;
	private char isEnterInErrorStatus;
	private List<ListOfPatientPersonalHistoryDto> listOfPatientPersonalHistoryDtos;
	
	
	
	public char getIsEnterInErrorStatus() {
		return isEnterInErrorStatus;
	}
	public void setIsEnterInErrorStatus(char isEnterInErrorStatus) {
		this.isEnterInErrorStatus = isEnterInErrorStatus;
	}
	public char getIsReviewedFlag() {
		return isReviewedFlag;
	}
	public void setIsReviewedFlag(char isReviewedFlag) {
		this.isReviewedFlag = isReviewedFlag;
	}
	public Integer getEncounterId() {
		return encounterId;
	}
	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public List<ListOfPatientPersonalHistoryDto> getListOfPatientPersonalHistoryDtos() {
		return listOfPatientPersonalHistoryDtos;
	}
	public void setListOfPatientPersonalHistoryDtos(
			List<ListOfPatientPersonalHistoryDto> listOfPatientPersonalHistoryDtos) {
		this.listOfPatientPersonalHistoryDtos = listOfPatientPersonalHistoryDtos;
	}
	public Integer getPersonalHistoryDetailsId() {
		return personalHistoryDetailsId;
	}
	public void setPersonalHistoryDetailsId(Integer personalHistoryDetailsId) {
		this.personalHistoryDetailsId = personalHistoryDetailsId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	
	public char getIsNoSignificantHistoryStatus() {
		return isNoSignificantHistoryStatus;
	}
	public void setIsNoSignificantHistoryStatus(char isNoSignificantHistoryStatus) {
		this.isNoSignificantHistoryStatus = (isNoSignificantHistoryStatus != '\u0000' ? isNoSignificantHistoryStatus : 'Y');
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
	
	
}
