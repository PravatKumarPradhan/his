package com.param.global.dto;

public class TimeSlotsDto {
	
	private String toTime;
	
	private String fromTime;
	
	private String breakToTime;
	
	private String breakFromTime;
	
	public String getBreakToTime() {
		return breakToTime;
	}

	public void setBreakToTime(String breakToTime) {
		this.breakToTime = breakToTime;
	}

	public String getBreakFromTime() {
		return breakFromTime;
	}

	public void setBreakFromTime(String breakFromTime) {
		this.breakFromTime = breakFromTime;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public String getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	@Override
	public String toString() {
		return "TimeSlotsDto [fromTime=" + fromTime + ", toTime=" + toTime + "]";
	}
	

}
