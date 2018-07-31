package com.param.lis.microbiology.transaction.dto;

public class MicroscopicExaminationSubDetailsMasterDto 
{
	
	private Integer microexaSubDetailsId;
	
	private Integer orgId;

	private Integer orgUnitId;

	private String microRemark;

	private Integer examinationDetailsId;
	
	private Integer organismId;
	
	private Integer createdBy;

	private Long createdDate;

	private Integer updatedBy;

	private Long updatedDate;
	
	private Character isDeleted;

	public Integer getMicroexaSubDetailsId() {
		return microexaSubDetailsId;
	}

	public void setMicroexaSubDetailsId(Integer microexaSubDetailsId) {
		this.microexaSubDetailsId = microexaSubDetailsId;
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

	
	public String getMicroRemark() {
		return microRemark;
	}

	public void setMicroRemark(String microRemark) {
		this.microRemark = microRemark;
	}

	public Integer getExaminationDetailsId() {
		return examinationDetailsId;
	}

	public void setExaminationDetailsId(Integer examinationDetailsId) {
		this.examinationDetailsId = examinationDetailsId;
	}

	public Integer getOrganismId() {
		return organismId;
	}

	public void setOrganismId(Integer organismId) {
		this.organismId = organismId;
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

	public MicroscopicExaminationDetailsMasterDto getMicroscopicExaminationDetailsMaster() {
		return microscopicExaminationDetailsMaster;
	}

	public void setMicroscopicExaminationDetailsMaster(
			MicroscopicExaminationDetailsMasterDto microscopicExaminationDetailsMaster) {
		this.microscopicExaminationDetailsMaster = microscopicExaminationDetailsMaster;
	}

	private MicroscopicExaminationDetailsMasterDto microscopicExaminationDetailsMaster;

}
