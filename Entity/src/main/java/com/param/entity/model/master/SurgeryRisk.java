package com.param.entity.model.master;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "SurgeryRisk")
@Table(name = "m_surgery_risk_master", schema = "public")
public class SurgeryRisk implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "surgery_risk_id", unique = true, nullable = false)
	private Integer surgeryRiskId;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "crerated_date")
	private Timestamp creratedDate;

	@Column(name = "organisation_id")
	private Integer organisationId;

	@Column(length = 1)
	private String status;

	@Column(name = "surgery_risk", length = 200)
	private String surgeryRisk;

	@Column(name = "surgery_risk_code", length = 200)
	private String surgeryRiskCode;

	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	private Timestamp updatedDate;

	public SurgeryRisk() {
	}

	public Integer getSurgeryRiskId() {
		return this.surgeryRiskId;
	}

	public void setSurgeryRiskId(Integer surgeryRiskId) {
		this.surgeryRiskId = surgeryRiskId;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreratedDate() {
		return this.creratedDate;
	}

	public void setCreratedDate(Timestamp creratedDate) {
		this.creratedDate = creratedDate;
	}

	public Integer getOrganisationId() {
		return this.organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSurgeryRisk() {
		return this.surgeryRisk;
	}

	public void setSurgeryRisk(String surgeryRisk) {
		this.surgeryRisk = surgeryRisk;
	}

	public String getSurgeryRiskCode() {
		return this.surgeryRiskCode;
	}

	public void setSurgeryRiskCode(String surgeryRiskCode) {
		this.surgeryRiskCode = surgeryRiskCode;
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

}
