package com.param.global.dto.global;

public class CancelReason {

	protected Integer id;

	protected String code;

	protected String reason;

	public CancelReason(Integer id, String code, String reason) {
		super();
		this.id = id;
		this.code = code;
		this.reason = reason;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
