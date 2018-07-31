package com.param.billing.exception;

public class BillingException extends RuntimeException{
	private static final long serialVersionUID = -4463339409632912953L;
	private String message;
	public BillingException(String message){
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
