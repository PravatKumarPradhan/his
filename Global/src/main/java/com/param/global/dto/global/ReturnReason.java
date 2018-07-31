package com.param.global.dto.global;

public class ReturnReason {
	
	protected Integer id;
	
	protected String reason;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public ReturnReason(Integer id, String reason) {
		super();
		this.id = id;
		this.reason = reason;
	}
	
}