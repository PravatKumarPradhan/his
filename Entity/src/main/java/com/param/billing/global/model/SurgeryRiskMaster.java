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
@Table(name="m_surgery_risk_master", schema="public")
@SequenceGenerator(name = "surgery_risk_master_seq", sequenceName = "public.surgery_risk_master_seq", allocationSize = 1)
public class SurgeryRiskMaster {
	@Id
	@Column(name = "surgery_risk_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "surgery_risk_master_seq")
	private int surgeryRiskId;
	  
	@Column(name = "surgery_risk")
	private String surgeryRisk;
	  
	@Column(name = "surgery_risk_code")
	private int privatesurgeryRiskCode;

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

	public int getSurgeryRiskId() {
		return surgeryRiskId;
	}

	public void setSurgeryRiskId(int surgeryRiskId) {
		this.surgeryRiskId = surgeryRiskId;
	}

	public String getSurgeryRisk() {
		return surgeryRisk;
	}

	public void setSurgeryRisk(String surgeryRisk) {
		this.surgeryRisk = surgeryRisk;
	}

	public int getPrivatesurgeryRiskCode() {
		return privatesurgeryRiskCode;
	}

	public void setPrivatesurgeryRiskCode(int privatesurgeryRiskCode) {
		this.privatesurgeryRiskCode = privatesurgeryRiskCode;
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
