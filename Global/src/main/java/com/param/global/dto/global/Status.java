package com.param.global.dto.global;

public class Status {
	protected Integer id;
	
	protected String status;

	public Status(Integer id) {
		super();
		this.id = id;
	}

	public Status(Integer id, String status) {
		super();
		this.id = id;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
