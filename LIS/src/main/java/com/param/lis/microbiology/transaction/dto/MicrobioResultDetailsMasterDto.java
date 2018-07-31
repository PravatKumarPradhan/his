package com.param.lis.microbiology.transaction.dto;

public class MicrobioResultDetailsMasterDto {

	private Integer microbioResultDetailsId;

	private Integer orgId;

	private Integer orgUnitId;

	private String incuRemark;

	private Integer microbioResultEntryId;

	private Integer mediaId;

	private Integer incubationPeriodId;

	private Character isGrowthFound;

	private Integer createdBy;

	private Long createdDate;

	private Integer updatedBy;

	private Long updatedDate;

	private Character isDeleted;

	public Integer getMicrobioResultDetailsId() {
		return microbioResultDetailsId;
	}

	public void setMicrobioResultDetailsId(Integer microbioResultDetailsId) {
		this.microbioResultDetailsId = microbioResultDetailsId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getOrgUnitId() {
		return orgUnitId;
	}

	public void setOrgUnitId(Integer orgUnitId) {
		this.orgUnitId = orgUnitId;
	}



	public String getIncuRemark() {
		return incuRemark;
	}

	public void setIncuRemark(String incuRemark) {
		this.incuRemark = incuRemark;
	}

	public Integer getMicrobioResultEntryId() {
		return microbioResultEntryId;
	}

	public void setMicrobioResultEntryId(Integer microbioResultEntryId) {
		this.microbioResultEntryId = microbioResultEntryId;
	}

	public Integer getMediaId() {
		return mediaId;
	}

	public void setMediaId(Integer mediaId) {
		this.mediaId = mediaId;
	}

	public Integer getIncubationPeriodId() {
		return incubationPeriodId;
	}

	public void setIncubationPeriodId(Integer incubationPeriodId) {
		this.incubationPeriodId = incubationPeriodId;
	}

	public Character getIsGrowthFound() {
		return isGrowthFound;
	}

	public void setIsGrowthFound(Character isGrowthFound) {
		this.isGrowthFound = isGrowthFound;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Long updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Character getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Character isDeleted) {
		this.isDeleted = isDeleted;
	}



}
