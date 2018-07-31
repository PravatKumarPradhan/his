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
	
	@NamedQuery(name="GET_REASON_TYPE_MASTER_BY_ID",
			query="SELECT rtm.reasonTypeId AS reasonTypeId,"
					+ "rtm.reasonTypeCode AS reasonTypeCode,"
					+ "rtm.reasonTypeDesc AS reasonTypeDesc "
					+ " FROM ReasonTypeMaster rtm "
					+ "WHERE rtm.organizationId=:orgId "
					+ "AND rtm.reasonTypeId=:reasonTypeId"),
	@NamedQuery(name = "GET_REASON_TYPE_MASTER_LIST", 
	query = "SELECT rtm.reasonTypeId as reasonTypeId, "
			+ "rtm.reasonTypeDesc as reasonTypeDesc, " 
			+ "rtm.status as status, " 
			+ "rtm.reasonTypeCode as reasonTypeCode "
			+ "FROM ReasonTypeMaster as rtm  "
			+ " WHERE rtm.organizationId=:orgId "),
	@NamedQuery(name = "GET_REASON_TYPEMASTER_BY_NAME", 
	query = "SELECT rtm.reasonTypeId as reasonTypeId, "
			+ "rtm.reasonTypeDesc as reasonTypeDesc, " 
			+ "rtm.reasonTypeCode as reasonTypeCode, "
			+ "rtm.status as status "
			+ "FROM ReasonTypeMaster as rtm  "
			+ " WHERE LOWER(rtm.reasonTypeDesc)=:reasonTypeDesc OR rtm.reasonTypeDesc=:reasonTypeDesc " 
			+ "AND rtm.organizationId=:orgId"),
	@NamedQuery(name = "GET_REASONTYPEMASTER_BY_NAME_NOT_BY_ID",
	query = "SELECT rtm.reasonTypeId as reasonTypeId, "
			+ "rtm.reasonTypeDesc as reasonTypeDesc," 
			+ "rtm.reasonTypeCode as reasonTypeCode "
			+ "FROM ReasonTypeMaster as rtm  "
			+ "WHERE LOWER(rtm.reasonTypeDesc)=:reasonTypeDesc OR rtm.reasonTypeDesc=:reasonTypeDesc "
			+ "AND rtm.reasonTypeId !=:reasonTypeId")
})

@Table(name = "m_reason_type_master",schema = "adt")
@Entity
@SequenceGenerator(name = "reason_type_master_seq", sequenceName = "adt.reason_type_master_seq", allocationSize = 1)
public class ReasonTypeMaster {
	@Id
	@Column(name = "reason_type_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reason_type_master_seq")

	private Integer reasonTypeId;

	@Column(name = "reason_type_code")
	private String reasonTypeCode;

	@Column(name = "reason_type_desc")
	private String reasonTypeDesc;

	@Column(name = "status")
	private char status;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "organization_id")
	private Integer organizationId;

	@Column(name = "unit_id")
	private Integer unitId;

	public Integer getReasonTypeId() {
		return reasonTypeId;
	}

	public void setReasonTypeId(Integer reasonTypeId) {
		this.reasonTypeId = reasonTypeId;
	}

	public String getReasonTypeCode() {
		return reasonTypeCode;
	}

	public void setReasonTypeCode(String reasonTypeCode) {
		this.reasonTypeCode = reasonTypeCode;
	}

	public String getReasonTypeDesc() {
		return reasonTypeDesc;
	}

	public void setReasonTypeDesc(String reasonTypeDesc) {
		this.reasonTypeDesc = reasonTypeDesc;
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
