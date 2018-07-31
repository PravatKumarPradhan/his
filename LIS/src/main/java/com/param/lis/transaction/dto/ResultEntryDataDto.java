package com.param.lis.transaction.dto;

public class ResultEntryDataDto {

	private Integer orgId;
	private Integer orgUnitId;
	private Integer sampleRecollectAgainstId;
	private String firstLevelResult;
	private String secondLevelResult;
	private String thiredLevelResult;
	private double firstlevelresult;

	public double getFirstlevelresult() {
		return firstlevelresult;
	}

	public void setFirstlevelresult(double firstlevelresult) {
		this.firstlevelresult = firstlevelresult;
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

	public String getFirstLevelResult() {
		return firstLevelResult;
	}

	public void setFirstLevelResult(String firstLevelResult) {
		this.firstLevelResult = firstLevelResult;
	}

	public String getSecondLevelResult() {
		return secondLevelResult;
	}

	public void setSecondLevelResult(String secondLevelResult) {
		this.secondLevelResult = secondLevelResult;
	}

	public String getThiredLevelResult() {
		return thiredLevelResult;
	}

	public void setThiredLevelResult(String thiredLevelResult) {
		this.thiredLevelResult = thiredLevelResult;
	}

	public Integer getSampleRecollectAgainstId() {
		return sampleRecollectAgainstId;
	}

	public void setSampleRecollectAgainstId(Integer sampleRecollectAgainstId) {
		this.sampleRecollectAgainstId = sampleRecollectAgainstId;
	}

	
}
