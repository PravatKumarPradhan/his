package com.param.opd.appointment.dto;

public class CountDto {

	private Long pending;
	
	private Long checkIn;
	
	private Long rescheduledAndCancelled;

	public Long getPending() {
		return pending;
	}

	public void setPending(Long pending) {
		this.pending = pending;
	}

	public Long getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Long checkIn) {
		this.checkIn = checkIn;
	}

	public Long getRescheduledAndCancelled() {
		return rescheduledAndCancelled;
	}

	public void setRescheduledAndCancelled(Long rescheduledAndCancelled) {
		this.rescheduledAndCancelled = rescheduledAndCancelled;
	}

	
}
