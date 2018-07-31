package com.param.lis.microbiology.transaction.dto;

import java.util.List;

public class SensitivityTestResultMasterDto {
	
	private Integer sensitivityResultId;
	private Integer orgId;
	private Integer orgUnitId;
	private Character checkTest;
	private Integer organismId;
	private Integer createdBy;
	private Long createdDate;
	private Integer updatedBy;
	private Long updatedDate;
	private Character isDeleted;
	private Integer labSampleDtlsId;
	
	private List<SensitivityTestResultDetailsMasterDto> listSensitivityTestResultDetailsMaster;

	public Integer getSensitivityResultId() {
		return sensitivityResultId;
	}

	public void setSensitivityResultId(Integer sensitivityResultId) {
		this.sensitivityResultId = sensitivityResultId;
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


	public Character getCheckTest() {
		return checkTest;
	}

	public void setCheckTest(Character checkTest) {
		this.checkTest = checkTest;
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


	public Integer getLabSampleDtlsId() {
		return labSampleDtlsId;
	}

	public void setLabSampleDtlsId(Integer labSampleDtlsId) {
		this.labSampleDtlsId = labSampleDtlsId;
	}

	public List<SensitivityTestResultDetailsMasterDto> getListSensitivityTestResultDetailsMaster() {
		return listSensitivityTestResultDetailsMaster;
	}

	public void setListSensitivityTestResultDetailsMaster(
			List<SensitivityTestResultDetailsMasterDto> listSensitivityTestResultDetailsMaster) {
		this.listSensitivityTestResultDetailsMaster = listSensitivityTestResultDetailsMaster;
	}

	

}
