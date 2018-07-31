package com.param.adt.admission.dto;

import java.math.BigInteger;

public class DateDetailsDto 
{
	private BigInteger availableCout;

	private String availableStatus;
	
	private String date;

	public BigInteger getAvailableCout() {
		return availableCout;
	}

	public void setAvailableCout(BigInteger availableCout) {
		this.availableCout = availableCout;
	}

	public String getAvailableStatus() {
		return availableStatus;
	}

	public void setAvailableStatus(String availableStatus) {
		this.availableStatus = availableStatus;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
}
