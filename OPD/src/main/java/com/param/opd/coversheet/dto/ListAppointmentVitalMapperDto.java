package com.param.opd.coversheet.dto;

public class ListAppointmentVitalMapperDto {

	private Integer vitalId;
	private String value;
	private Integer appointmentVitalMapperId;
	private char isReviewedFlag;
	
	public Integer getAppointmentVitalMapperId() {
		return appointmentVitalMapperId;
	}
	public void setAppointmentVitalMapperId(Integer appointmentVitalMapperId) {
		this.appointmentVitalMapperId = appointmentVitalMapperId;
	}
	public char getIsReviewedFlag() {
		return isReviewedFlag;
	}
	public void setIsReviewedFlag(char isReviewedFlag) {
		this.isReviewedFlag = isReviewedFlag;
	}
	public Integer getVitalId() {
		return vitalId;
	}
	public void setVitalId(Integer vitalId) {
		this.vitalId = vitalId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
