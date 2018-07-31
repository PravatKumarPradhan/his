package com.param.adt.master.global.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.param.global.model.EmployeeMaster;

@NamedQueries({
		@NamedQuery(name = "USER_LOGIN", query = "SELECT 	userMst.userId as userId, userMst.loginName as loginName,userMst.password as password,userMst.roleId as roleId, "
				+ "		to_char(userMst.createdDate,'DD-MM-YYYY') as createdDate,userMst.createdBy as createdBy,userMst.userName as userName "
				+ "FROM	UserMaster userMst " + "WHERE	userMst.userName=:userName "),
		
		@NamedQuery(name="GET_ACTIVE_USERS_LIST",query="SELECT um.userName as userName, "
				+ "um.userId as userId "
				+ "FROM UserMaster um "
				+ "WHERE um.organizationId=:organizationId "
				+ "AND um.unitId=:unitId ")
})
@Entity
@Table(name = "m_user_master", schema = "public")
@SequenceGenerator(name = "user_master_seq", sequenceName = "public.user_master_seq", allocationSize = 1)
public class UserMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_master_seq")
	@Column(name = "user_id")
	private int userId;

	@Column(name = "login_name")
	private String loginName;

	@Column(name = "password")
	private String password;

	@Column(name = "role_id")
	private Integer roleId;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "user_name")
	private String userName;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "organization_id")
	private Integer organizationId;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<RoleMaster> listRoleMaster;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserRoleMapper> listUserRoleMppr;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<UnitUserMapper> listUnitUserMapper;

	@ManyToOne
	@JoinColumn(name = "role_id", insertable = false, nullable = false, updatable = false)
	private RoleMaster roleMaster;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userMaster", orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<OrganizationUnitUserMapper> organizationUnitUserMappersList;

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

	public List<RoleMaster> getListRoleMaster() {
		return listRoleMaster;
	}

	public void setListRoleMaster(List<RoleMaster> listRoleMaster) {
		this.listRoleMaster = listRoleMaster;
	}

	public List<OrganizationUnitUserMapper> getOrganizationUnitUserMappersList() {
		return organizationUnitUserMappersList;
	}

	public void setOrganizationUnitUserMappersList(List<OrganizationUnitUserMapper> organizationUnitUserMappersList) {
		this.organizationUnitUserMappersList = organizationUnitUserMappersList;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<UserRoleMapper> getListUserRoleMppr() {
		return listUserRoleMppr;
	}

	public void setListUserRoleMppr(List<UserRoleMapper> listUserRoleMppr) {
		this.listUserRoleMppr = listUserRoleMppr;
	}

	public List<UnitUserMapper> getListUnitUserMapper() {
		return listUnitUserMapper;
	}

	public void setListUnitUserMapper(List<UnitUserMapper> listUnitUserMapper) {
		this.listUnitUserMapper = listUnitUserMapper;
	}

	public RoleMaster getRoleMaster() {
		return roleMaster;
	}

	public void setRoleMaster(RoleMaster roleMaster) {
		this.roleMaster = roleMaster;
	}

}
