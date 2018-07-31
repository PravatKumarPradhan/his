package com.param.adt.master.global.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "t_unit_user_mapper", schema = "public")
@SequenceGenerator(name="unit_user_seq",sequenceName="public.unit_user_seq",allocationSize=1)
public class UnitUserMapper {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="unit_user_seq")
	private UnitUserMapperId unitUserMapperId;

	@Column(name = "status")
	private char status;

	@Column(name = "created_by")
	private int createdBy;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@ManyToOne
	@JoinColumn(name = "unit_id", insertable = false, updatable = false)
	private UnitMaster unitMaster;
	
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private UserMaster userMaster;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;

	public UnitUserMapperId getUnitUserMapperId() {
		return unitUserMapperId;
	}

	public void setUnitUserMapperId(UnitUserMapperId unitUserMapperId) {
		this.unitUserMapperId = unitUserMapperId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public UnitMaster getUnitMaster() {
		return unitMaster;
	}

	public void setUnitMaster(UnitMaster unitMaster) {
		this.unitMaster = unitMaster;
	}

	public UserMaster getUserMaster() {
		return userMaster;
	}

	public void setUserMaster(UserMaster userMaster) {
		this.userMaster = userMaster;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	

}
