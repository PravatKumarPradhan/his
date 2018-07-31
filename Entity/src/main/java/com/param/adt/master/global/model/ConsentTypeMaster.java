package com.param.adt.master.global.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;



@NamedQueries({
	
	@NamedQuery(name="GET_CONSENT_TYPE_LIST", query="SELECT ct.consentTypeMasterId as consentTypeMasterId, "
			+ "ct.consentTypeMasterName as consentTypeMasterName, "
			+ "ct.consentTypeMasterCode as consentTypeMasterCode, "
			+ "ct.status as status "
			+ "FROM ConsentTypeMaster ct "
			+ "WHERE ct.organizationId=:orgId "
			+ "ORDER BY ct.consentTypeMasterId DESC"),
	
	@NamedQuery(name="GET_CONSENT_TYPE_LIST_BY_ID", query="SELECT ct.consentTypeMasterId as consentTypeMasterId, "
			+ "ct.consentTypeMasterName as consentTypeMasterName, "
			+ "ct.consentTypeMasterCode as consentTypeMasterCode, "
			+ "ct.status as status "
			+ "FROM ConsentTypeMaster ct "
			+ "WHERE ct.consentTypeMasterId=:consentTypeId"),
	
	@NamedQuery(name="GET_CONSENT_TYPE_LIST_BY_NAME", query="SELECT ct.consentTypeMasterId as consentTypeMasterId, "
			+ "ct.consentTypeMasterName as consentTypeMasterName "
			+ "FROM ConsentTypeMaster ct "
			+ "WHERE ct.consentTypeMasterName=:consentTypeName OR LOWER(ct.consentTypeMasterName)=:consentTypeName"),
	
	@NamedQuery(name="GET_CONSENT_TYPE_LIST_BY_NAME_NOT_ID", query="SELECT ct.consentTypeMasterId as consentTypeMasterId, "
			+ "ct.consentTypeMasterName as consentTypeMasterName "
			+ "FROM ConsentTypeMaster ct "
			+ "WHERE ct.consentTypeMasterId!=:consentTypeId AND "
			+ "(ct.consentTypeMasterName=:consentTypeName OR LOWER(ct.consentTypeMasterName)=:consentTypeName)"),
	
	@NamedQuery(name="GET_ACTIVE_CONSENT_TYPE_LIST", query="SELECT ct.consentTypeMasterId as consentTypeMasterId, "
			+ "ct.consentTypeMasterName as consentTypeMasterName "
			+ "FROM ConsentTypeMaster ct "
			+ "WHERE ct.status='A'")
})


@Entity
@Table(name = "m_consent_type_master", schema = "adt")
@SequenceGenerator(name = "consent_type_master_seq2", sequenceName = "adt.consent_type_master_seq2", allocationSize = 1)
public class ConsentTypeMaster 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "consent_type_master_seq2")
	@Column(name = "consent_type_master_id")
	private int consentTypeMasterId;
	
	@Column(name="consent_type_master_name")
	private String consentTypeMasterName;
	
	@Column(name="consent_type_master_code")
	private String consentTypeMasterCode;
	
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "consentTypeMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ConsentMaster> listConsentMaster;
	
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

	public List<ConsentMaster> getListConsentMaster() {
		return listConsentMaster;
	}

	public void setListConsentMaster(List<ConsentMaster> listConsentMaster) {
		this.listConsentMaster = listConsentMaster;
	}

	public int getConsentTypeMasterId() {
		return consentTypeMasterId;
	}

	public void setConsentTypeMasterId(int consentTypeMasterId) {
		this.consentTypeMasterId = consentTypeMasterId;
	}

	public String getConsentTypeMasterName() {
		return consentTypeMasterName;
	}

	public void setConsentTypeMasterName(String consentTypeMasterName) {
		this.consentTypeMasterName = consentTypeMasterName;
	}

	
	public String getConsentTypeMasterCode() {
		return consentTypeMasterCode;
	}

	public void setConsentTypeMasterCode(String consentTypeMasterCode) {
		this.consentTypeMasterCode = consentTypeMasterCode;
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
