package com.param.global.dto.global;

public class Ward {
	
	private Integer id;
	
	private String ward;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWardName() {
		return ward;
	}

	public void setWardName(String ward) {
		this.ward = ward;
	}

	public Ward(Integer id, String ward) {
		super();
		this.id = id;
		this.ward = ward;
	}
	
}