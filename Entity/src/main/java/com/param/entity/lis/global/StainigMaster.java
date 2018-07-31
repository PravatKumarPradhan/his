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

	@NamedQuery(name = "GET_STAINIG_BY_STAINIG_ID", query = "SELECT stainigMaster.stainigId as id,"
			+ " stainigMaster.code as code,"
			+ " stainigMaster.desc as desc,"
			+ " stainigMaster.status as status,"
			+ " stainigMaster.orgId as orgId,"
			+ " stainigMaster.createdBy as createdBy,"
			+ " stainigMaster.createdDate as createdDate,"
			+ " stainigMaster.updatedBy as updatedBy,"
			+ " stainigMaster.updatedDate as updatedDate"
			+ " FROM StainigMaster stainigMaster "
			+ " WHERE stainigMaster.stainigId = :stainigId "
			+ " AND stainigMaster.orgId= :orgId"),
			
		@NamedQuery(name = "GET_STAINIG_BY_STAINIG_CODE", query = "SELECT stainigMaster.stainigId as id,"
			+ " stainigMaster.code as code,"
			+ " stainigMaster.desc as desc,"
			+ " stainigMaster.status as status,"
			+ " stainigMaster.createdBy as createdBy,"
			+ " stainigMaster.createdDate as createdDate,"
			+ " stainigMaster.updatedBy as updatedBy,"
			+ " stainigMaster.updatedDate as updatedDate"
			+ " FROM StainigMaster stainigMaster"
			+ " WHERE stainigMaster.orgId=:orgId "
			+ " AND lower(stainigMaster.code) = lower(:code)"),
			
			
			@NamedQuery(name = "UPDATE_GET_STAINIG_TYPE_BY_STAINIG_CODE", query = "SELECT stainigMaster.stainigId as id,"
					+ " stainigMaster.code as code,"
					+ " stainigMaster.desc as desc,"
					+ " stainigMaster.status as status,"
					+ " stainigMaster.createdBy as createdBy,"
					+ " stainigMaster.createdDate as createdDate,"
					+ " stainigMaster.updatedBy as updatedBy,"
					+ " stainigMaster.updatedDate as updatedDate"
					+ " FROM StainigMaster stainigMaster"
					+ " WHERE stainigMaster.orgId=:orgId "
					+ " AND lower(stainigMaster.code) = lower(:code)"
					+ " AND stainigMaster.stainigId <> :stainigId"),
	

	@NamedQuery(name = "GET_STAINIG_MASTER_LIST", query = "SELECT stainigMaster.stainigId as id,"
			+ " stainigMaster.code as code,"
			+ " stainigMaster.desc as desc,"
			+ " stainigMaster.status as status,"
			+ " stainigMaster.createdBy as createdBy,"
			+ " stainigMaster.createdDate as createdDate,"
			+ " stainigMaster.updatedBy as updatedBy,"
			+ " stainigMaster.updatedDate as updatedDate"
			+ " FROM StainigMaster stainigMaster"
			+ " WHERE stainigMaster.orgId = :orgId"
			+ " ORDER BY id DESC")

})




@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_STAINIG_RECORD", query = "select count(*) from lab.m_staining_master where "
	+ "org_id = :orgId "),
	
	
@NamedNativeQuery(name = "GET_STATINING_LIST_BY_ORGID", query =" SELECT "
		 +"	staining_master.staining_id AS id , "
		 +"	staining_master.staining_name AS name "
		 +"FROM "
		 +"	lab.m_staining_master staining_master "
		 +" "
		 +"WHERE "
		 +"	staining_master.staining_status = 'A' "
		 +"	AND staining_master.org_id =:orgId " )

})
@Entity
@Table(name = "m_staining_master", schema = "lab")
@SequenceGenerator(name = "m_seq_stainig_master", sequenceName = "lab.m_seq_stainig_master", allocationSize = 1)
public class StainigMaster {
	
	
	@Id
	@Column(name = "staining_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_stainig_master")
	private Integer stainigId;
	
	@Column(name = "staining_code")
	private String code;
	
	@Column(name = "staining_name")
	private String desc;
	
	@Column(name = "staining_status")
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

	public int getStainigId() {
		return stainigId;
	}

	public void setStainigId(int stainigId) {
		this.stainigId = stainigId;
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
