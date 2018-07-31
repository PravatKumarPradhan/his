package com.param.global.org.common.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries({

		@NamedQuery(name = "GET_REJECTION_MASTER_BY_ID", 
				query = "SELECT rm.id AS id," 
						+ "rm.code AS code,"
						+ "rm.isActive as isActive, " 
						+ "rm.reasonDesc AS reasonDesc " 
						+ " FROM RejectionMaster rm " 
						+ "WHERE rm.organizationId=:orgId "
						+ "AND rm.id=:rejectId"),
		@NamedQuery(name = "GET_REJECTION_MASTER_LIST", 
		       query = "SELECT rm.id as id, "
		    		   + "rm.reasonDesc as reasonDesc, " 
		    		   + "rm.isActive as isActive, " 
		    		   + "rm.code as code "
		    		   + "FROM RejectionMaster as rm  "
		    		   + " WHERE rm.organizationId=:orgId "),
		@NamedQuery(name = "GET_REJECTION_MASTER_BY_NAME",
				query = "SELECT rm.id as id, "
						+ "rm.reasonDesc as reasonDesc, " 
						+ "rm.isActive as isActive " 
						+ "FROM RejectionMaster as rm  "
						+ " WHERE LOWER(rm.reasonDesc)=:rejectDesc "
						+ "AND rm.organizationId=:orgId"),
		@NamedQuery(name = "GET_REJECTION_MASTER_BY_NAME_NOT_BY_ID", 
		query = "SELECT rm.id as id, "
						+ "rm.reasonDesc as reasonDesc  " 
						+ "FROM RejectionMaster as rm  "
						+ "WHERE LOWER(rm.reasonDesc)=:rejectDesc "
						+ "AND rm.id !=:rejectId") })
@Entity
@Table(name = "m_reject_reason", schema = "public")
@SequenceGenerator(name = "m_reject_reason_id_seq", sequenceName = "public.m_reject_reason_id_seq", allocationSize = 1)
public class RejectionMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_reject_reason_id_seq")
	@Column(name = "id")
	private Integer id;

	@Column(name = "code")
	private String code;

	@Column(name = "reason")
	private String reasonDesc;

	@Column(name = "is_active")
	private Boolean isActive;
	
	@Column(name = "is_deleted")
	private Boolean isDeleted;

	@Column(name = "added_by")
	private Integer createdBy;

	@Column(name = "added_date")
	private Date createdDate;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "organization_id")
	private Integer organizationId;

	@Column(name = "unit_id")
	private Integer unitId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getReasonDesc() {
		return reasonDesc;
	}

	public void setReasonDesc(String reasonDesc) {
		this.reasonDesc = reasonDesc;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
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

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	
	

}
