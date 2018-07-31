package com.param.global.dto.global;

public class Gender {
	protected Integer id;

	protected String gender;

	public Gender(Integer id, String gender) {
		super();
		this.id = id;
		this.gender = gender;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
