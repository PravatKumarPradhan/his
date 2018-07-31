package com.param.entity.lis.global;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.common.LocalTimeConverter;
@NamedQueries({

	@NamedQuery(name = "GET_ORGANISM_GROUP_BY_ORGANISM_ID", query = "SELECT organismGroupMaster.organismGroupId as id,"
			+ " organismGroupMaster.code as code,"
			+ " organismGroupMaster.desc as desc,"
			+ " organismGroupMaster.status as status,"
			+ " organismGroupMaster.orgId as orgId,"
			+ " organismGroupMaster.createdBy as createdBy,"
			+ " organismGroupMaster.createdDate as createdDate,"
			+ " organismGroupMaster.updatedBy as updatedBy,"
			+ " organismGroupMaster.updatedDate as updatedDate"
			+ " FROM OrganismGroupMaster organismGroupMaster "
			+ " WHERE organismGroupMaster.organismGroupId = :organismGroupId "
			+ " AND organismGroupMaster.orgId= :orgId"),
			
		@NamedQuery(name = "GET_ORGANISM_GROUP_BY_ORGANISM_CODE", query = "SELECT organismGroupMaster.organismGroupId as id,"
			+ " organismGroupMaster.code as code,"
			+ " organismGroupMaster.desc as desc,"
			+ " organismGroupMaster.status as status,"
			+ " organismGroupMaster.createdBy as createdBy,"
			+ " organismGroupMaster.createdDate as createdDate,"
			+ " organismGroupMaster.updatedBy as updatedBy,"
			+ " organismGroupMaster.updatedDate as updatedDate"
			+ " FROM OrganismGroupMaster organismGroupMaster"
			+ " WHERE organismGroupMaster.orgId=:orgId "
			+ " AND lower(organismGroupMaster.code) = lower(:code)"),
			
			
			@NamedQuery(name = "UPDATE_GET_ORGANISM_GROUP_TYPE_BY_ORGANISM_CODE", query = "SELECT organismGroupMaster.organismGroupId as id,"
					+ " organismGroupMaster.code as code,"
					+ " organismGroupMaster.desc as desc,"
					+ " organismGroupMaster.status as status,"
					+ " organismGroupMaster.createdBy as createdBy,"
					+ " organismGroupMaster.createdDate as createdDate,"
					+ " organismGroupMaster.updatedBy as updatedBy,"
					+ " organismGroupMaster.updatedDate as updatedDate"
					+ " FROM OrganismGroupMaster organismGroupMaster"
					+ " WHERE organismGroupMaster.orgId=:orgId "
					+ " AND lower(organismGroupMaster.code) = lower(:code)"
					+ " AND organismGroupMaster.organismGroupId <> :organismGroupId"),
	

	@NamedQuery(name = "GET_ORGANIsm_GROUP_MASTER_LIST", query = "SELECT organismGroupMaster.organismGroupId as id,"
			+ " organismGroupMaster.code as code,"
			+ " organismGroupMaster.desc as desc,"
			+ " organismGroupMaster.status as status,"
			+ " organismGroupMaster.createdBy as createdBy,"
			+ " organismGroupMaster.createdDate as createdDate,"
			+ " organismGroupMaster.updatedBy as updatedBy,"
			+ " organismGroupMaster.updatedDate as updatedDate"
			+ " FROM OrganismGroupMaster organismGroupMaster"
			+ " WHERE organismGroupMaster.orgId = :orgId"
			+ " ORDER BY id DESC")

})

@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_ORGANISM_GROUP_RECORD", query = "select count(*) from lab.m_organism_group_master where "
	+ "org_id = :orgId ")
})
@Entity
@Table(name = "m_organism_group_master", schema = "lab")
@SequenceGenerator(name = "m_seq_organism_group_master", sequenceName = "lab.m_seq_organism_group_master", allocationSize = 1)
public class OrganismGroupMaster {
	
	
	@Id
	@Column(name = "organism_group_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_organism_group_master")
	private int organismGroupId;
	
	@Column(name = "organism_group_code")
	private String code;
	
	@Column(name = "organism_group_name")
	private String desc;
	
	@Column(name = "organism_group_status")
	private Character status;
	
	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	@Convert(converter = LocalTimeConverter.class)
	private Long createdDate;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	@Convert(converter = LocalTimeConverter.class)
	private Long updatedDate;
	
	@Column(name = "org_id")
	private Integer orgId;

	public int getOrganismGroupId() {
		return organismGroupId;
	}

	public void setOrganismGroupId(int organismGroupId) {
		this.organismGroupId = organismGroupId;
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

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Long updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
	
	
	
	
}
