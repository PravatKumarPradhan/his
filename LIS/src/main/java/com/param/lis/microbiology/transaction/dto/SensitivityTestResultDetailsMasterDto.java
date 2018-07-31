package com.param.lis.microbiology.transaction.dto;

import java.util.List;


public class SensitivityTestResultDetailsMasterDto {
	
	private Integer sesitivityResultDetailsId;
	private Integer sensitivityResultId;
	private Integer orgId;
	private Integer orgUnitId;
	private Integer organismId;
	private String organismName;
	private Integer organismGroupId;
	private String organismGroupName;
	private String selectedAll;
	private Integer createdBy;
	private Long createdDate;
	private Integer updatedBy;
	private Long updatedDate;
	private Character isDeleted;
	

   private List<SensitivityTestResultSubDetailsMasterDto> listSensitivityTestResultSubDetailsMaster;

	public List<SensitivityTestResultSubDetailsMasterDto> getListSensitivityTestResultSubDetailsMaster() {
		return listSensitivityTestResultSubDetailsMaster;
	}

	public void setListSensitivityTestResultSubDetailsMaster(
			List<SensitivityTestResultSubDetailsMasterDto> listSensitivityTestResultSubDetailsMaster) {
		this.listSensitivityTestResultSubDetailsMaster = listSensitivityTestResultSubDetailsMaster;
	}
	

	public Integer getSesitivityResultDetailsId() {
		return sesitivityResultDetailsId;
	}

	public void setSesitivityResultDetailsId(Integer sesitivityResultDetailsId) {
		this.sesitivityResultDetailsId = sesitivityResultDetailsId;
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

	public Integer getSensitivityResultId() {
		return sensitivityResultId;
	}

	public void setSensitivityResultId(Integer sensitivityResultId) {
		this.sensitivityResultId = sensitivityResultId;
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

	public Integer getOrganismGroupId() {
		return organismGroupId;
	}

	public void setOrganismGroupId(Integer organismGroupId) {
		this.organismGroupId = organismGroupId;
	}

	public String getOrganismGroupName() {
		return organismGroupName;
	}

	public void setOrganismGroupName(String organismGroupName) {
		this.organismGroupName = organismGroupName;
	}

	public String getOrganismName() {
		return organismName;
	}

	public void setOrganismName(String organismName) {
		this.organismName = organismName;
	}

	public String getSelectedAll() {
		return selectedAll;
	}

	public void setSelectedAll(String selectedAll) {
		this.selectedAll = selectedAll;
	}
	
}
