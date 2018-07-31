package com.param.entity.model.master;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "OccupancyUnit")
@Table(name = "m_occupancy_unit_master", schema = "public")
public class OccupancyUnit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "occupancy_unit_id", unique = true, nullable = false)
	private Integer occupancyUnitId;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "number_of_hours", length = 2147483647)
	private String numberOfHours;

	@Column(name = "occupancy_unit_code", length = 50)
	private String occupancyUnitCode;

	@Column(name = "occupancy_unit_desc", length = 2147483647)
	private String occupancyUnitDesc;

	@Column(length = 1)
	private String status;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	private Timestamp updatedDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id")
	private Organization organization;

	public OccupancyUnit() {
	}

	public Integer getOccupancyUnitId() {
		return this.occupancyUnitId;
	}

	public void setOccupancyUnitId(Integer occupancyUnitId) {
		this.occupancyUnitId = occupancyUnitId;
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

	public String getNumberOfHours() {
		return this.numberOfHours;
	}

	public void setNumberOfHours(String numberOfHours) {
		this.numberOfHours = numberOfHours;
	}

	public String getOccupancyUnitCode() {
		return this.occupancyUnitCode;
	}

	public void setOccupancyUnitCode(String occupancyUnitCode) {
		this.occupancyUnitCode = occupancyUnitCode;
	}

	public String getOccupancyUnitDesc() {
		return this.occupancyUnitDesc;
	}

	public void setOccupancyUnitDesc(String occupancyUnitDesc) {
		this.occupancyUnitDesc = occupancyUnitDesc;
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

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
}
