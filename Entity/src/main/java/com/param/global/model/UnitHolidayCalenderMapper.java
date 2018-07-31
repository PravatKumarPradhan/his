package com.param.global.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@NamedQueries({
	
		@NamedQuery(name = "GET_UNIT_HOLIDAY_CALENDER_LIST", query = "SELECT uhcm.unitHolidayCalenderId as unitHolidayCalenderId, "
				+ "uhcm.holidayId as holidayId, "
				+ "holiday.holidayCalenderCode as holidayCalenderCode ,"
				+ "holiday.holidayCalenderDesc as holidayCalenderDesc, "
				+ "to_char(holiday.holidayDate,'dd-MM-yyyy') as holidayDate, "
				+ "holiday.status as status, " 
				+ "day.day as day, "
				+" holiday.dayId as dayId "
				+ "FROM UnitHolidayCalenderMapper uhcm "
				+ "INNER JOIN uhcm.holidayCalenderMaster holiday "
				+ "INNER JOIN holiday.dayMaster as day "
				+ "WHERE uhcm.organizationId=:orgId "
				+ "AND uhcm.unitId=:unitId "
				),
		
		@NamedQuery(name = "GET_ACTIVE_UNIT_HOLIDAY_CALENDER_LIST", query = "SELECT uhcm.unitHolidayCalenderId as unitHolidayCalenderId, "
				+ "uhcm.holidayId as holidayId, "
				+ "holiday.holidayCalenderCode as holidayCalenderCode ,"
				+ "holiday.holidayCalenderDesc as holidayCalenderDesc, "
				+ "to_char(holiday.holidayDate,'yyyy-MM-dd') as holidayDate, "
				+ "holiday.status as status, " 
				+ "day.day as day, "
				+" holiday.dayId as dayId "
				+ "FROM UnitHolidayCalenderMapper uhcm "
				+ "INNER JOIN uhcm.holidayCalenderMaster holiday "
				+ "INNER JOIN holiday.dayMaster as day "
				+ "WHERE uhcm.organizationId=:orgId "
				+ "AND uhcm.unitId=:unitId "
				+ "AND uhcm.status='A' "
				+ "AND holiday.status='A' "
				)
})
@Entity
@Table(name="t_unit_holiday_calender_mapper",schema="public")
@SequenceGenerator(name="unit_holiday_calender_mapper_seq", sequenceName="public.unit_holiday_calender_mapper_seq",allocationSize=1)
public class UnitHolidayCalenderMapper 
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="unit_holiday_calender_mapper_seq")
	@Column(name="unit_holiday_calender_id")
	private int unitHolidayCalenderId;
	
	@Column(name = "unit_id")
	private Integer unitId;
	
	@Column(name="organization_id")
	private Integer organizationId;

	@Column(name="holiday_id")
	private Integer holidayId;

	@Column(name="status")
	private char status;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	/*@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "unit_id", insertable = false, updatable = false)
	private UnitMaster unitMaster;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private OrganizationMaster organizationMaster;*/
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "holiday_id", insertable = false, updatable = false)
	private HolidayCalenderMaster holidayCalenderMaster;

	
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getUnitHolidayCalenderId() {
		return unitHolidayCalenderId;
	}

	public void setUnitHolidayCalenderId(int unitHolidayCalenderId) {
		this.unitHolidayCalenderId = unitHolidayCalenderId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getHolidayId() {
		return holidayId;
	}

	public void setHolidayId(Integer holidayId) {
		this.holidayId = holidayId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
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

	/*public UnitMaster getUnitMaster() {
		return unitMaster;
	}

	public void setUnitMaster(UnitMaster unitMaster) {
		this.unitMaster = unitMaster;
	}

	public OrganizationMaster getOrganizationMaster() {
		return organizationMaster;
	}

	public void setOrganizationMaster(OrganizationMaster organizationMaster) {
		this.organizationMaster = organizationMaster;
	}*/

	public HolidayCalenderMaster getHolidayCalenderMaster() {
		return holidayCalenderMaster;
	}

	public void setHolidayCalenderMaster(HolidayCalenderMaster holidayCalenderMaster) {
		this.holidayCalenderMaster = holidayCalenderMaster;
	}
	
	
	
}
