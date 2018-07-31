package com.param.adt.master.global.dto;

import java.util.List;

import com.param.adt.master.global.model.UserRoleMapperId;

public class UserRoleMapperDto 
{
	private UserRoleMapperId userRoleMapperId;
	private Integer  createdBy;
	private String createdDate;
	private List<Integer> listUserId;
	private Integer roleId;
	private Integer userId;
	public UserRoleMapperId getUserRoleMapperId() {
		return userRoleMapperId;
	}
	public void setUserRoleMapperId(UserRoleMapperId userRoleMapperId) {
		this.userRoleMapperId = userRoleMapperId;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public List<Integer> getListUserId() {
		return listUserId;
	}
	public void setListUserId(List<Integer> listUserId) {
		this.listUserId = listUserId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
}
