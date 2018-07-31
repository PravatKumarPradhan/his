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
	
	@NamedQuery(name="GET_CONSENT_LIST",query="SELECT c.consentMasterId as consentMasterId, "
			+ "c.consentTypeMasterId as consentTypeMasterId, "
			+ "c.consentMasterName as consentMasterName, "
			+ "c.consentMasterCode as consentMasterCode,"
			+ "c.status as status, "
			+ "ct.consentTypeMasterName as consentTypeMasterName "
			+ "FROM ConsentMaster as c "
			+ "INNER JOIN c.consentTypeMaster as ct "
			+ "WHERE c.organizationId=:orgId "
			+ "ORDER BY c.consentMasterId DESC"),
	
	@NamedQuery(name="GET_CONSENT_LIST_BY_ID",query="SELECT c.consentMasterId as consentMasterId, "
			+ "c.consentTypeMasterId as consentTypeMasterId, "
			+ "c.consentMasterName as consentMasterName, "
			+ "c.consentMasterCode as consentMasterCode, "
			+ "c.status as status "
			+ "FROM ConsentMaster as c "
			+ "WHERE c.consentMasterId=:consentId"),
	
	@NamedQuery(name="GET_CONSENT_LIST_BY_NAME",query="SELECT c.consentMasterId as consentMasterId, "
			+ "c.consentMasterName as consentMasterName "
			+ "FROM ConsentMaster as c "
			+ "WHERE c.consentMasterName=:consentName OR LOWER(c.consentMasterName)=:consentName"),
	
	@NamedQuery(name="GET_CONSENT_LIST_BY_NAME_NOT_ID",query="SELECT c.consentMasterId as consentMasterId, "
			+ "c.consentMasterName as consentMasterName "
			+ "FROM ConsentMaster as c "
			+ "WHERE c.consentMasterName=:consentName OR LOWER(c.consentMasterName)=:consentName "
			+ "AND c.consentMasterId!=:consentId"),
	
	@NamedQuery(name="GET_ACTIVE_CONSENT_LIST",query="SELECT c.consentMasterId as consentMasterId, "
			+ "c.consentMasterName as consentMasterName "
			+ "FROM ConsentMaster as c "
			+ "WHERE c.status='A'"),
			
})

@Entity
@Table(name = "m_consent_master" , schema = "adt")
@SequenceGenerator(name = "consent_master_seq" , sequenceName = "adt.consent_master_seq" , allocationSize = 1)
public class ConsentMaster 
{
	@Id
	@Column(name = "consent_master_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "consent_master_seq")
	private int consentMasterId;
	
	@Column(name="consent_type_id")
	private Integer consentTypeMasterId;
	
	@Column(name="consent_master_name")
	private String consentMasterName;
	
	@Column(name="consent_master_code")
	private String consentMasterCode;
	
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "consent_type_id", insertable = false, updatable = false)
	private ConsentTypeMaster consentTypeMaster;

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

	public ConsentTypeMaster getConsentTypeMaster() {
		return consentTypeMaster;
	}

	public void setConsentTypeMaster(ConsentTypeMaster consentTypeMaster) {
		this.consentTypeMaster = consentTypeMaster;
	}

	public int getConsentMasterId() {
		return consentMasterId;
	}

	public void setConsentMasterId(int consentMasterId) {
		this.consentMasterId = consentMasterId;
	}

	public Integer getConsentTypeMasterId() {
		return consentTypeMasterId;
	}

	public void setConsentTypeMasterId(Integer consentTypeMasterId) {
		this.consentTypeMasterId = consentTypeMasterId;
	}

	public String getConsentMasterName() {
		return consentMasterName;
	}

	public void setConsentMasterName(String consentMasterName) {
		this.consentMasterName = consentMasterName;
	}

	public String getConsentMasterCode() {
		return consentMasterCode;
	}

	public void setConsentMasterCode(String consentMasterCode) {
		this.consentMasterCode = consentMasterCode;
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
