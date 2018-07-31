package com.param.billing.global.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name="m_consultant_type_master", schema="public")
@SequenceGenerator(name = "consultant_type_master_seq", sequenceName = "public.consultant_type_master_seq", allocationSize = 1)
public class ConsultantTypeMaster {
	@Id
	@Column(name = "consultant_type_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "consultant_type_master_seq")
	private int consultantTypeId;
	  
	@Column(name = "consultant_type")
	private String consultantType;
	  
	@Column(name = "consultant_type_code")
	private String consultantTypeCode;

	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "orgnisation_id")
	private Integer orgnisationId;

	@Column(name = "status")
	private char status;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "updated_by")
	private Integer updatedBy;

	public int getConsultantTypeId() {
		return consultantTypeId;
	}

	public void setConsultantTypeId(int consultantTypeId) {
		this.consultantTypeId = consultantTypeId;
	}

	public String getConsultantType() {
		return consultantType;
	}

	public void setConsultantType(String consultantType) {
		this.consultantType = consultantType;
	}

	public String getConsultantTypeCode() {
		return consultantTypeCode;
	}

	public void setConsultantTypeCode(String consultantTypeCode) {
		this.consultantTypeCode = consultantTypeCode;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getOrgnisationId() {
		return orgnisationId;
	}

	public void setOrgnisationId(Integer orgnisationId) {
		this.orgnisationId = orgnisationId;
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

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	

}
