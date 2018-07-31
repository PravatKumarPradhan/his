package com.param.global.dto.generic;

public class StrengthUnit {

	protected Integer id;

	protected String unit;

	public StrengthUnit(Integer id, String unit) {
		super();
		this.id = id;
		this.unit = unit;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
