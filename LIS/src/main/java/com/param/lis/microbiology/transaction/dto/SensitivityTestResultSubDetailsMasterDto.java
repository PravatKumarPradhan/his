package com.param.lis.microbiology.transaction.dto;





public class SensitivityTestResultSubDetailsMasterDto {
	
	
	private Integer sesitivityResultSubDetailsId;
	private Integer orgId;
	private Integer orgUnitId;
	private Integer antibioticId;
	private String antibioticName;
	private Boolean isChecked;
	private Integer sensitivityId;
	private Integer microLabPriorityId;
	private Double mic;
	private String remark;
	private Integer createdBy;
	private Long createdDate;
	private Integer updatedBy;
	private Long updatedDate;
	private Character isDeleted;
	private Integer sesitivityResultDetailsId;


	public Integer getSesitivityResultSubDetailsId() {
		return sesitivityResultSubDetailsId;
	}

	public void setSesitivityResultSubDetailsId(Integer sesitivityResultSubDetailsId) {
		this.sesitivityResultSubDetailsId = sesitivityResultSubDetailsId;
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

	public Integer getAntibioticId() {
		return antibioticId;
	}

	public void setAntibioticId(Integer antibioticId) {
		this.antibioticId = antibioticId;
	}

	public Integer getSensitivityId() {
		return sensitivityId;
	}

	public void setSensitivityId(Integer sensitivityId) {
		this.sensitivityId = sensitivityId;
	}

	public Integer getMicroLabPriorityId() {
		return microLabPriorityId;
	}

	public void setMicroLabPriorityId(Integer microLabPriorityId) {
		this.microLabPriorityId = microLabPriorityId;
	}

	public Double getMic() {
		return mic;
	}

	public void setMic(Double mic) {
		this.mic = mic;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Integer getSesitivityResultDetailsId() {
		return sesitivityResultDetailsId;
	}

	public void setSesitivityResultDetailsId(Integer sesitivityResultDetailsId) {
		this.sesitivityResultDetailsId = sesitivityResultDetailsId;
	}

	public String getAntibioticName() {
		return antibioticName;
	}

	public void setAntibioticName(String antibioticName) {
		this.antibioticName = antibioticName;
	}

	public Boolean getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}
	
	
}
