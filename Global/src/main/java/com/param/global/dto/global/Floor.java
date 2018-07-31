package com.param.global.dto.global;

public class Floor {
	
	private Integer id;
	
	private String floor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public Floor(Integer id, String floor) {
		super();
		this.id = id;
		this.floor = floor;
	}
	
}