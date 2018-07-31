package com.param.adt.master.global.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="t_user_role_mapper", schema="adt")
public class UserRoleMapper {

	@EmbeddedId
	private UserRoleMapperId userRoleMapperId;
	
	@Column(name="created_by")
	private Integer  createdBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="status")
	private char status;
	
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private UserMaster userMaster;
	
	@ManyToOne
	@JoinColumn(name = "role_id", insertable = false, updatable = false)
	private RoleMaster roleMaster;

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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public UserMaster getUserMaster() {
		return userMaster;
	}

	public void setUserMaster(UserMaster userMaster) {
		this.userMaster = userMaster;
	}

	public RoleMaster getRoleMaster() {
		return roleMaster;
	}

	public void setRoleMaster(RoleMaster roleMaster) {
		this.roleMaster = roleMaster;
	}
	
	
}
