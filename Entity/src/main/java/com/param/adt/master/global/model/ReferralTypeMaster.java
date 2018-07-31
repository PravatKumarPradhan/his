package com.param.adt.master.global.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@NamedQueries({
	
	@NamedQuery(name="GET_REFERRAL_TYPE_MASTER_LIST", query="SELECT rtm.id as id, "
			+ "rtm.code as code,"
			+ "rtm.desc as desc, "
			+ "rtm.status as status "
			+ "FROM ReferralTypeMaster as rtm "
			+ "WHERE rtm.organizationId=:orgId "
			+ "ORDER BY rtm.id DESC"),
	
	@NamedQuery(name="GET_REFFERAL_TYPE_MASTER_LIST_BY_ID", query="SELECT rtm.id as id, "
			+ "rtm.code as code,"
			+ "rtm.desc as desc, "
			+ "rtm.status as status "
			+ "FROM ReferralTypeMaster as rtm WHERE rtm.id=:id"),
	
	@NamedQuery(name="GET_REFFERAL_TYPE_MASTER_LIST_BY_NAME", query="SELECT rtm.id as id, "
			+ "rtm.desc as desc "
			+ "FROM ReferralTypeMaster as rtm "
			+ "WHERE rtm.desc=:desc OR LOWER(rtm.desc)=:desc"),
	
	@NamedQuery(name="GET_REFFERAL_TYPE_MASTER_LIST_BY_NAME_NOT_ID", query="SELECT rtm.id as id, "
			+ "rtm.desc as desc "
			+ "FROM ReferralTypeMaster as rtm "
			+ "WHERE (rtm.desc=:desc OR LOWER(rtm.desc)=:desc) "
			+ "AND rtm.id!=:id"),
	
	@NamedQuery(name="GET_ACTIVE_REFFERAL_TYPE_MASTER_LIST", query="SELECT rtm.id as id, "
			+ "rtm.desc as desc "
			+ "FROM ReferralTypeMaster as rtm "
			+ "WHERE rtm.status='A' "
			+ "AND rtm.organizationId=:orgId ")
	
})

@Entity
@Table(name = "m_referral_type_master", schema = "adt")
@SequenceGenerator(name="referral_type_master_seq", sequenceName="adt.referral_type_master_seq", allocationSize=1)
public class ReferralTypeMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "referral_type_master_seq")
	@Column(name = "referral_type_id")
	private int id;
	
	@Column(name = "referral_type_code")
	private String code;
	
	@Column(name = "referral_type_name")
	private String desc;
	
	@Column(name = "status")
	private char status;
	
	@Column(name = "created_by")
	private int createdBy;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private OrganizationMaster organizationMaster;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "referralTypeMaster", cascade = CascadeType.ALL, orphanRemoval = true) 
	private List<ReferralMaster> listReferralMaster;

	
	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public OrganizationMaster getOrganizationMaster() {
		return organizationMaster;
	}

	public void setOrganizationMaster(OrganizationMaster organizationMaster) {
		this.organizationMaster = organizationMaster;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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

	public List<ReferralMaster> getListReferralMaster() {
		return listReferralMaster;
	}

	public void setListReferralMaster(List<ReferralMaster> listReferralMaster) {
		this.listReferralMaster = listReferralMaster;
	}
	
	
	
}
