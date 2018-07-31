package com.param.global.dto;

public class ListSystemObservationMaster {

	private Integer observationId;
	private String observationName;
	private String observationCode;
	private char isPropertyRequired;
	
	
	public Integer getObservationId() {
		return observationId;
	}
	public void setObservationId(Integer observationId) {
		this.observationId = observationId;
	}
	public String getObservationName() {
		return observationName;
	}
	public void setObservationName(String observationName) {
		this.observationName = observationName;
	}
	public String getObservationCode() {
		return observationCode;
	}
	public void setObservationCode(String observationCode) {
		this.observationCode = observationCode;
	}
	public char getIsPropertyRequired() {
		return isPropertyRequired;
	}
	public void setIsPropertyRequired(char isPropertyRequired) {
		this.isPropertyRequired = isPropertyRequired;
	}
	
	
}
