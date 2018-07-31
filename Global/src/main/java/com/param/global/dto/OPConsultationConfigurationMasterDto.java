package com.param.global.dto;

public class OPConsultationConfigurationMasterDto {

	private Integer opVisitRuleId;
	
	private Integer organizationId;
	
	private Integer unitId;
	
	private Integer specialityId;
	
	private String specialityName;
	
	private Integer followupVisitDays;

	private Integer followupVisitCount;
	
	private Integer secondaryVisitDays;
	
	private Integer secondaryVisitCount;
	
	private char status;

	private Integer createdBy;

	private String createdDate;

	private Integer updatedBy;

	private String updatedDate;
	
	public String getSpecialityName() {
		return specialityName;
	}

	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}

	public Integer getOpVisitRuleId() {
		return opVisitRuleId;
	}

	public void setOpVisitRuleId(Integer opVisitRuleId) {
		this.opVisitRuleId = opVisitRuleId;
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

	public Integer getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(Integer specialityId) {
		this.specialityId = specialityId;
	}

	public Integer getFollowupVisitDays() {
		return followupVisitDays;
	}

	public void setFollowupVisitDays(Integer followupVisitDays) {
		this.followupVisitDays = followupVisitDays;
	}

	public Integer getFollowupVisitCount() {
		return followupVisitCount;
	}

	public void setFollowupVisitCount(Integer followupVisitCount) {
		this.followupVisitCount = followupVisitCount;
	}

	public Integer getSecondaryVisitDays() {
		return secondaryVisitDays;
	}

	public void setSecondaryVisitDays(Integer secondaryVisitDays) {
		this.secondaryVisitDays = secondaryVisitDays;
	}

	public Integer getSecondaryVisitCount() {
		return secondaryVisitCount;
	}

	public void setSecondaryVisitCount(Integer secondaryVisitCount) {
		this.secondaryVisitCount = secondaryVisitCount;
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

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
}
