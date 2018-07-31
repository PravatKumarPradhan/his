package com.param.entity.model.master;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "ProfitAndCostCenter")
@Table(name = "m_profit_and_cost_center_master", schema = "public")
public class ProfitAndCostCenter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "profit_cost_center_master_id", unique = true, nullable = false)
	private Integer profitCostCenterMasterId;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "organisation_id")
	private Integer organisationId;

	@Column(name = "pc_center_code", length = 100)
	private String pcCenterCode;

	@Column(name = "pc_center_name", length = 200)
	private String pcCenterName;

	@Column(length = 1)
	private String status;

	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	private Timestamp updatedDate;

	public ProfitAndCostCenter() {
	}

	public Integer getProfitCostCenterMasterId() {
		return this.profitCostCenterMasterId;
	}

	public void setProfitCostCenterMasterId(Integer profitCostCenterMasterId) {
		this.profitCostCenterMasterId = profitCostCenterMasterId;
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

	public Integer getOrganisationId() {
		return this.organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

	public String getPcCenterCode() {
		return this.pcCenterCode;
	}

	public void setPcCenterCode(String pcCenterCode) {
		this.pcCenterCode = pcCenterCode;
	}

	public String getPcCenterName() {
		return this.pcCenterName;
	}

	public void setPcCenterName(String pcCenterName) {
		this.pcCenterName = pcCenterName;
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
}