package com.param.adt.master.global.model;

import java.util.Date;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries({ 
	
	@NamedQuery(name = "GET_SHORT_LEAVE_LIST", query = "SELECT slm.shortLeaveReasonId as shortLeaveReasonId, "
			+ "slm.shortLeaveReasonName as shortLeaveReasonName, "
			+ "slm.shortLeaveReasonCode as shortLeaveReasonCode, "
			+ "slm.status as status "
			+ "FROM ShortLeaveReasonMaster slm "
			+ "WHERE slm.organizationId=:orgId "
			+ "ORDER BY slm.shortLeaveReasonId DESC"),

	@NamedQuery(name = "GET_SHORT_LEAVE_LIST_BY_ID", query = "SELECT slm.shortLeaveReasonId as shortLeaveReasonId, "
			+ "slm.shortLeaveReasonName as shortLeaveReasonName, "
			+ "slm.shortLeaveReasonCode as shortLeaveReasonCode, "
			+ "slm.status as status "
			+ "FROM ShortLeaveReasonMaster slm WHERE slm.shortLeaveReasonId=:shortLeaveReasonId"),
	
	@NamedQuery(name = "GET_SHORT_LEAVE_LIST_BY_NAME", query = "SELECT slm.shortLeaveReasonId as shortLeaveReasonId, "
			+ "slm.shortLeaveReasonName as shortLeaveReasonName "
			+ "FROM ShortLeaveReasonMaster slm "
			+ "WHERE slm.shortLeaveReasonName=:shortLeaveReasonName OR (slm.shortLeaveReasonName)=:shortLeaveReasonName"),
	
	@NamedQuery(name = "GET_SHORT_LEAVE_LIST_BY_NAME_NOT_ID", query = "SELECT slm.shortLeaveReasonId as shortLeaveReasonId, "
			+ "slm.shortLeaveReasonName as shortLeaveReasonName "
			+ "FROM ShortLeaveReasonMaster slm "
			+ "WHERE (slm.shortLeaveReasonName=:shortLeaveReasonName OR (slm.shortLeaveReasonName)=:shortLeaveReasonName) "
			+ "AND slm.shortLeaveReasonId!=:shortLeaveReasonId"),
	

	@NamedQuery(name = "GET_ACTIVE_SHORT_LEAVE_LIST", query = "SELECT slm.shortLeaveReasonId as shortLeaveReasonId, "
			+ "slm.shortLeaveReasonName as shortLeaveReasonName, "
			+ "slm.shortLeaveReasonCode as shortLeaveReasonCode , "
			+ "slm.status as status  "
			+ "FROM ShortLeaveReasonMaster slm "
			+ "WHERE slm.status='A' "
			+ "AND slm.organizationId=:orgId "),
	
	
	
})

@Entity
@Table(name = "m_short_leave_reason_master", schema = "adt")
@SequenceGenerator(name = "short_leave_reason_master_seq", sequenceName = "adt.short_leave_reason_master_seq", allocationSize = 1)
public class ShortLeaveReasonMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "short_leave_reason_master_seq")
	@Column(name = "short_leave_reason_id")
	private int shortLeaveReasonId;

	@Column(name = "short_leave_reason_name")
	private String shortLeaveReasonName;

	@Column(name = "short_leave_reason_code")
	private String shortLeaveReasonCode;

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

	@Column(name="organization_id")
	private Integer organizationId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private OrganizationMaster organizationMaster;
	
	
	
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

	public int getShortLeaveReasonId() {
		return shortLeaveReasonId;
	}

	public void setShortLeaveReasonId(int shortLeaveReasonId) {
		this.shortLeaveReasonId = shortLeaveReasonId;
	}

	public String getShortLeaveReasonName() {
		return shortLeaveReasonName;
	}

	public void setShortLeaveReasonName(String shortLeaveReasonName) {
		this.shortLeaveReasonName = shortLeaveReasonName;
	}

	public String getShortLeaveReasonCode() {
		return shortLeaveReasonCode;
	}

	public void setShortLeaveReasonCode(String shortLeaveReasonCode) {
		this.shortLeaveReasonCode = shortLeaveReasonCode;
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

}
