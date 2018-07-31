package com.param.adt.master.global.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@NamedQueries({
	@NamedQuery(name = "GET_ORGANIZATION_UNIT_USER_MAPPER_FOR_LOGIN",
				query= "SELECT		huuMppr.organizationId as organizationId, huuMppr.userId as userId, "
					 + "			huuMppr.unitId as unitId "
					 + "FROM		OrganizationUnitUserMapper huuMppr "
					 + "WHERE		huuMppr.organizationUnitUserMapperId.organizationId =:organizationId "
					 + "AND			huuMppr.organizationUnitUserMapperId.userId =:userId "
					 + "AND			huuMppr.organizationUnitUserMapperId.unitId =:unitId ")
})
@Entity
@Table(name="t_organization_unit_user_mapper",schema="public")
@SequenceGenerator(sequenceName="organization_unit_user_mapper_seq", allocationSize=1,name="public.organization_unit_user_mapper")
public class OrganizationUnitUserMapper implements Serializable 
{
	public static final long serialVersionUID = 1l;
	@Id
	@Column(name="organization_unit_user_mapper_id")
	private int organizationUnitUserMapperId;
	
	@Column(name = "organization_id")
	private int organizationId;

	@Column(name = "unit_id")
	private int unitId;

	@Column(name = "user_id")
	private int userId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id" , insertable = false , updatable = false , nullable = false)
	private OrganizationMaster organizationMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id" , insertable = false , updatable = false , nullable = false)
	private UnitMaster unitMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id" , insertable = false , updatable = false , nullable = false)
	private UserMaster userMaster;

	
	public OrganizationMaster getOrganizationMaster() {
		return organizationMaster;
	}

	public void setOrganizationMaster(OrganizationMaster organizationMaster) {
		this.organizationMaster = organizationMaster;
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
