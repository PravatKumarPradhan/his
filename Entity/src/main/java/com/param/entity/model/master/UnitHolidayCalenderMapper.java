package com.param.entity.model.master;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "UnitHolidayCalenderMappers")
@Table(name = "t_unit_holiday_calender_mapper", schema = "public")
public class UnitHolidayCalenderMapper implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="unit_holiday_calender_id", unique=true, nullable=false)
	private Integer unitHolidayCalenderId;

	@Column(name="created_by")
	private Integer createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;

	@Column(name="holiday_id")
	private Integer holidayId;

	@Column(length=1)
	private String status;

	@Column(name="updated_by")
	private Integer updatedBy;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="organization_id")
	private Organization organization;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="unit_id")
	private Unit unit;

	public UnitHolidayCalenderMapper() {
	}

	public Integer getUnitHolidayCalenderId() {
		return this.unitHolidayCalenderId;
	}

	public void setUnitHolidayCalenderId(Integer unitHolidayCalenderId) {
		this.unitHolidayCalenderId = unitHolidayCalenderId;
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

	public Integer getHolidayId() {
		return this.holidayId;
	}

	public void setHolidayId(Integer holidayId) {
		this.holidayId = holidayId;
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

	public Unit getUnit() {
		return this.unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

}
