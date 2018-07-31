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
@Table(name="m_deposite_type_master", schema="billing")
@SequenceGenerator(name = "deposite_type_master_seq", sequenceName = "billing.deposite_type_master_seq", allocationSize = 1)
public class DepositeTypeMaster {
	@Id
	@Column(name = "deposite_type_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deposite_type_master_seq")
	private int depositeTypeId; 
	 
	@Column(name = "deposite_type_code") 
	private String depositeTypeCode;
	 
	@Column(name = "deposite_type_desc") 
	private String depositeTypeDesc;
	 
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

	public int getDepositeTypeId() {
		return depositeTypeId;
	}

	public void setDepositeTypeId(int depositeTypeId) {
		this.depositeTypeId = depositeTypeId;
	}

	public String getDepositeTypeCode() {
		return depositeTypeCode;
	}

	public void setDepositeTypeCode(String depositeTypeCode) {
		this.depositeTypeCode = depositeTypeCode;
	}

	public String getDepositeTypeDesc() {
		return depositeTypeDesc;
	}

	public void setDepositeTypeDesc(String depositeTypeDesc) {
		this.depositeTypeDesc = depositeTypeDesc;
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
