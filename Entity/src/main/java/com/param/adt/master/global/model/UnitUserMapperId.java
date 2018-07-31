package com.param.adt.master.global.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UnitUserMapperId implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "unit_id")
	private Integer unitId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
