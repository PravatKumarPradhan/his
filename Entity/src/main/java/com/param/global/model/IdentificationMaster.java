package com.param.global.model;

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
	
	@NamedQuery(name="GET_IDENTIFICATION_LIST", query="SELECT id.identificationId as identificationId, "
			+ "id.identificationCode as identificationCode, "
			+ "id.identificationName as identificationName, "
			+ "id.status as status, "
			+ "id.isExpiryRequired as isExpiryRequired "
			+ "FROM IdentificationMaster as id "
			+ "WHERE id.organizationId=:orgId "
			+ "ORDER BY id.identificationId DESC "),
	
	@NamedQuery(name="GET_IDENTIFICATION_LIST_BY_NAME", query="SELECT id.identificationId as identificationId, "
			+ "id.identificationCode as identificationCode, "
			+ "id.identificationName as identificationName, "
			+ "id.status as status "
			+ "FROM IdentificationMaster as id "
			+ "WHERE id.identificationName =:identificationName OR LOWER(id.identificationName)=:identificationName"),
	
	@NamedQuery(name="GET_IDENTIFICATION_LIST_BY_ID", query="SELECT id.identificationId as identificationId, "
			+ "id.identificationCode as identificationCode, "
			+ "id.identificationName as identificationName, "
			+ "id.status as status, "
			+ "id.isExpiryRequired as isExpiryRequired "
			+ "FROM IdentificationMaster as id "
			+ "WHERE id.identificationId =:identificationId"),
	
	@NamedQuery(name="GET_IDENTIFICATION_LIST_BY_NAME_NOT_ID", query="SELECT id.identificationId as identificationId, "
			+ "id.identificationCode as identificationCode, "
			+ "id.identificationName as identificationName, "
			+ "id.status as status "
			+ "FROM IdentificationMaster as id "
			+ "WHERE id.identificationId !=:identificationId AND (id.identificationName =:identificationName OR LOWER(id.identificationName)=:identificationName)"),
	
	@NamedQuery(name="GET_ACTIVE_IDENTIFICATION_LIST", query="SELECT id.identificationId as identificationId, "
			+ "id.identificationName as identificationName, "
			+ "id.isExpiryRequired as isExpiryRequired "
			+ "FROM IdentificationMaster as id WHERE id.status='A'")
})


@Entity
@Table(name = "m_identification_master", schema = "adt")
@SequenceGenerator(name="identification_master_seq", sequenceName="adt.identification_master_seq", allocationSize=1)
public class IdentificationMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "identification_master_seq")
	@Column(name = "identification_id")
	private int identificationId;
	
	@Column(name = "identification_code")
	private String identificationCode;
	
	@Column(name = "identification_name")
	private String identificationName;
	
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

	@Column(name="is_expiry_required")
	private char isExpiryRequired;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public char getIsExpiryRequired() {
		return isExpiryRequired;
	}

	public void setIsExpiryRequired(char isExpiryRequired) {
		this.isExpiryRequired = isExpiryRequired;
	}

	public int getIdentificationId() {
		return identificationId;
	}

	public void setIdentificationId(int identificationId) {
		this.identificationId = identificationId;
	}

	public String getIdentificationCode() {
		return identificationCode;
	}

	public void setIdentificationCode(String identificationCode) {
		this.identificationCode = identificationCode;
	}

	public String getIdentificationName() {
		return identificationName;
	}

	public void setIdentificationName(String identificationName) {
		this.identificationName = identificationName;
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
