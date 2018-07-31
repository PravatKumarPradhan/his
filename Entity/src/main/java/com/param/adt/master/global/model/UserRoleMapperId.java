package com.param.adt.master.global.model;

import java.io.Serializable;

import javax.persistence.*;

@Embeddable
public class UserRoleMapperId implements Serializable{

	private static final long serialVersionUID = 1L;	
	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="role_id")
	private Integer roleId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}