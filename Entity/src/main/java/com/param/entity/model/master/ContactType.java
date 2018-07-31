package com.param.entity.model.master;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "ContactType")
@Table(name = "m_contact_type_master", schema = "public")
public class ContactType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="contact_type_id", unique=true, nullable=false)
	private Integer contactTypeId;

	@Column(name="contact_type_code", length=100)
	private String contactTypeCode;

	@Column(name="contact_type_desc", length=200)
	private String contactTypeDesc;

	@Column(name="created_by")
	private Integer createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;

	@Column(name="orgnisation_id")
	private Integer orgnisationId;

	@Column(length=1)
	private String status;

	@Column(name="unit_id")
	private Integer unitId;

	@Column(name="updated_by")
	private Integer updatedBy;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="contactType")
	private List<OrganisationContactDetail> organisationContactDetailList;

	public ContactType() {
	}

	public Integer getContactTypeId() {
		return this.contactTypeId;
	}

	public void setContactTypeId(Integer contactTypeId) {
		this.contactTypeId = contactTypeId;
	}

	public String getContactTypeCode() {
		return this.contactTypeCode;
	}

	public void setContactTypeCode(String contactTypeCode) {
		this.contactTypeCode = contactTypeCode;
	}

	public String getContactTypeDesc() {
		return this.contactTypeDesc;
	}

	public void setContactTypeDesc(String contactTypeDesc) {
		this.contactTypeDesc = contactTypeDesc;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getOrgnisationId() {
		return this.orgnisationId;
	}

	public void setOrgnisationId(Integer orgnisationId) {
		this.orgnisationId = orgnisationId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getUnitId() {
		return this.unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<OrganisationContactDetail> getOrganisationContactDetailList() {
		return this.organisationContactDetailList;
	}

	public void setOrganisationContactDetailList(List<OrganisationContactDetail> organisationContactDetailList) {
		this.organisationContactDetailList = organisationContactDetailList;
	}

	public OrganisationContactDetail addOrganisationContactDetailList(OrganisationContactDetail organisationContactDetailList) {
		getOrganisationContactDetailList().add(organisationContactDetailList);
		organisationContactDetailList.setContactType(this);

		return organisationContactDetailList;
	}

	public OrganisationContactDetail removeOrganisationContactDetailList(OrganisationContactDetail organisationContactDetailList) {
		getOrganisationContactDetailList().remove(organisationContactDetailList);
		organisationContactDetailList.setContactType(null);

		return organisationContactDetailList;
	}

}