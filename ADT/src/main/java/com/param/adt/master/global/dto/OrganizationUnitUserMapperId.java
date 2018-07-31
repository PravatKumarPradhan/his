package com.param.adt.master.global.dto;

import java.io.Serializable;

public class OrganizationUnitUserMapperId implements Serializable{

	public static final long serialVersionUID = 1l;


	private int organizationId;

	private int unitId;
	
	private int userId;

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
