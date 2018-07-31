package com.param.adt.admission.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.adt.master.global.model.DocumentTypeMaster;
import com.param.adt.master.global.model.OrganizationMaster;
import com.param.adt.master.global.model.UnitMaster;
@Entity
@Table(name="t_sponsor_details",schema="adt")
@SequenceGenerator(name="sponsor_details_seq",allocationSize=1,sequenceName="adt.sponsor_details_seq")
public class SponsorDetails 
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="sponsor_details_seq")
	@Column(name="sponsor_details_id")
	private int sponsorDetailsId;
	
	@Column(name="sponsor_id")
	private Integer sponsorId;
	
	@Column(name="preauthorized_number")
	private String preauthorizedNumber;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="amt")
	private float amt;
	
	@Column(name="document_type_id")
	private Integer documentTypeId;
	
	@Column(name="document_path")
	private String documentPath;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="status")
	private char status;
	
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "created_by")
	private int createdBy;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, nullable = false, updatable = false)
	private OrganizationMaster organizationMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id", insertable = false, nullable = false, updatable = false)
	private UnitMaster unitMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sponsor_id", insertable = false, nullable = false, updatable = false)
	private Sponsor sponser;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "document_type_id", insertable = false, nullable = false, updatable = false)
	private DocumentTypeMaster documentTypeMaster;
	

	public int getSponsorDetailsId() {
		return sponsorDetailsId;
	}

	public void setSponsorDetailsId(int sponsorDetailsId) {
		this.sponsorDetailsId = sponsorDetailsId;
	}

	public Integer getSponsorId() {
		return sponsorId;
	}

	public void setSponsorId(Integer sponsorId) {
		this.sponsorId = sponsorId;
	}

	public String getPreauthorizedNumber() {
		return preauthorizedNumber;
	}

	public void setPreauthorizedNumber(String preauthorizedNumber) {
		this.preauthorizedNumber = preauthorizedNumber;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getAmt() {
		return amt;
	}

	public void setAmt(float amt) {
		this.amt = amt;
	}

	public Integer getDocumentTypeId() {
		return documentTypeId;
	}

	public void setDocumentTypeId(Integer documentTypeId) {
		this.documentTypeId = documentTypeId;
	}

	public String getDocumentPath() {
		return documentPath;
	}

	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
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

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
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
