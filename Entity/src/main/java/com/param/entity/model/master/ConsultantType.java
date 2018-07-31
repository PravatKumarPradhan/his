package com.param.entity.model.master;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "ConsultantType")
@Table(name = "m_consultant_type_master", schema = "public")
public class ConsultantType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="consultant_type_id", unique=true, nullable=false)
	private Integer consultantTypeId;

	@Column(name="consultant_type", length=200)
	private String consultantType;

	@Column(name="consultant_type_code", length=100)
	private String consultantTypeCode;

	@Column(name="created_by")
	private Integer createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;

	@Column(length=1)
	private String status;

	@Column(name="updated_by")
	private Integer updatedBy;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	public ConsultantType() {
	}

	public Integer getConsultantTypeId() {
		return this.consultantTypeId;
	}

	public void setConsultantTypeId(Integer consultantTypeId) {
		this.consultantTypeId = consultantTypeId;
	}

	public String getConsultantType() {
		return this.consultantType;
	}

	public void setConsultantType(String consultantType) {
		this.consultantType = consultantType;
	}

	public String getConsultantTypeCode() {
		return this.consultantTypeCode;
	}

	public void setConsultantTypeCode(String consultantTypeCode) {
		this.consultantTypeCode = consultantTypeCode;
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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
}