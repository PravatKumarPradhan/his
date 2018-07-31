package com.param.lis.histopathology.transaction.dto;



public class OutSourceDetailMasterDto {
	
	private Integer outSourceDetailId;
	private String resource;
	private Integer sampleSendThroughId;
	private String courierNumber;
	private String remarks;
	private Long expectedDate;
	private Integer createdBy;
	private Long createdDate;
	private Integer updatedBy;
	private Long updatedDate;
	private Character status;
	private Integer orgId;
	private Integer orgUnitId;
	private Integer outSourcedId;

	public Integer getOutSourceDetailId() {
		return outSourceDetailId;
	}

	public void setOutSourceDetailId(Integer outSourceDetailId) {
		this.outSourceDetailId = outSourceDetailId;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public Integer getSampleSendThroughId() {
		return sampleSendThroughId;
	}

	public void setSampleSendThroughId(Integer sampleSendThroughId) {
		this.sampleSendThroughId = sampleSendThroughId;
	}

	public String getCourierNumber() {
		return courierNumber;
	}

	public void setCourierNumber(String courierNumber) {
		this.courierNumber = courierNumber;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getExpectedDate() {
		return expectedDate;
	}

	public void setExpectedDate(Long expectedDate) {
		this.expectedDate = expectedDate;
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

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
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

	public Integer getOutSourcedId() {
		return outSourcedId;
	}

	public void setOutSourcedId(Integer outSourcedId) {
		this.outSourcedId = outSourcedId;
	}

	
}
