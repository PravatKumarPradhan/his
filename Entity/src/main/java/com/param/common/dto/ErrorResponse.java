package com.param.common.dto;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorResponse {
	protected String code;

	protected String message;

	protected String summary;

	protected String stackTrace;

	public ErrorResponse() {
		super();
	}

	public ErrorResponse(String code, String message, Exception ex) {
		super();

		StringWriter writer = new StringWriter();
		ex.printStackTrace(new PrintWriter(writer));
		
		this.code = code;
		this.message = message;
		this.summary = ex.getMessage();
		this.stackTrace = writer.toString();		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}
}