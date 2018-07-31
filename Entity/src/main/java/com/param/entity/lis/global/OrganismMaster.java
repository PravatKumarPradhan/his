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

	@NamedQuery(name = "GET_ORGANISM_BY_ORGANISM_ID", query = "SELECT organismMaster.organismId as id,"
			+ " organismMaster.code as code,"
			+ " organismMaster.desc as desc,"
			+ " organismMaster.status as status,"
			+ " organismMaster.orgId as orgId,"
			+ " organismMaster.createdBy as createdBy,"
			+ " organismMaster.createdDate as createdDate,"
			+ " organismMaster.updatedBy as updatedBy,"
			+ " organismMaster.updatedDate as updatedDate"
			+ " FROM OrganismMaster organismMaster "
			+ " WHERE organismMaster.organismId = :organismId "
			+ " AND organismMaster.orgId= :orgId"),
			
		@NamedQuery(name = "GET_ORGANISM_BY_ORGANISM_CODE", query = "SELECT organismMaster.organismId as id,"
			+ " organismMaster.code as code,"
			+ " organismMaster.desc as desc,"
			+ " organismMaster.status as status,"
			+ " organismMaster.createdBy as createdBy,"
			+ " organismMaster.createdDate as createdDate,"
			+ " organismMaster.updatedBy as updatedBy,"
			+ " organismMaster.updatedDate as updatedDate"
			+ " FROM OrganismMaster organismMaster"
			+ " WHERE organismMaster.orgId=:orgId "
			+ " AND lower(organismMaster.code) = lower(:code)"),
			
			
			@NamedQuery(name = "UPDATE_GET_ORGANISM_TYPE_BY_ORGANISM_CODE", query = "SELECT organismMaster.organismId as id,"
					+ " organismMaster.code as code,"
					+ " organismMaster.desc as desc,"
					+ " organismMaster.status as status,"
					+ " organismMaster.createdBy as createdBy,"
					+ " organismMaster.createdDate as createdDate,"
					+ " organismMaster.updatedBy as updatedBy,"
					+ " organismMaster.updatedDate as updatedDate"
					+ " FROM OrganismMaster organismMaster"
					+ " WHERE organismMaster.orgId=:orgId "
					+ " AND lower(organismMaster.code) = lower(:code)"
					+ " AND organismMaster.organismId <> :organismId"),
	

	@NamedQuery(name = "GET_ORGANIsm_MASTER_LIST", query = "SELECT organismMaster.organismId as id,"
			+ " organismMaster.code as code,"
			+ " organismMaster.desc as desc,"
			+ " organismMaster.status as status,"
			+ " organismMaster.createdBy as createdBy,"
			+ " organismMaster.createdDate as createdDate,"
			+ " organismMaster.updatedBy as updatedBy,"
			+ " organismMaster.updatedDate as updatedDate"
			+ " FROM OrganismMaster organismMaster"
			+ " WHERE organismMaster.orgId = :orgId"
			+ " ORDER BY id DESC")

})

@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_ORGANISM_RECORD", query = "select count(*) from lab.m_organism_master where "
	+ "org_id = :orgId ")
})
@Entity
@Table(name = "m_organism_master", schema = "lab")
@SequenceGenerator(name = "m_seq_organism_master", sequenceName = "lab.m_seq_organism_master", allocationSize = 1)
public class OrganismMaster {
	
	
	@Id
	@Column(name = "organism_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_organism_master")
	private int organismId;
	
	@Column(name = "organism_code")
	private String code;
	
	@Column(name = "organism_name")
	private String desc;
	
	@Column(name = "organism_status")
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

	public int getOrganismId() {
		return organismId;
	}

	public void setOrganismId(int organismId) {
		this.organismId = organismId;
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
