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

		@NamedQuery(name = "GET_HOLIDAY_LIST", query = "SELECT holidayMaster.holidayCalenderId as holidayCalenderId, "
				+ "holidayMaster.holidayCalenderCode as holidayCalenderCode, "
				+ "holidayMaster.holidayCalenderDesc as holidayCalenderDesc, "
				+ "to_char(holidayMaster.holidayDate,'dd/MM/yyyy') as holidayDate, "
				+ "holidayMaster.status as status, " 
				+ "day.day as day, "
				+" holidayMaster.dayId as dayId "
				+ "FROM HolidayCalenderMaster as holidayMaster " 
				+ "INNER JOIN holidayMaster.dayMaster day "
				+ "WHERE holidayMaster.organizationId=:orgId "
				+ "ORDER BY holidayMaster.holidayCalenderId DESC"),
		
		@NamedQuery(name = "GET_ACTIVE_DAY_LIST_BY_ID", query = "SELECT dayMaster.dayId as dayId, "
				+ "dayMaster.day as day "
				+ "FROM DayMaster as dayMaster WHERE dayMaster.status='A' AND dayMaster.dayId=:dayId"),

		@NamedQuery(name = "GET_HOLIDAY_LIST_BY_NAME", query = "SELECT holidayMaster.holidayCalenderId as holidayCalenderId, "
				+ "holidayMaster.holidayCalenderDesc as holidayCalenderDesc "
				+ "FROM HolidayCalenderMaster as holidayMaster "
				+ "WHERE LOWER(holidayMaster.holidayCalenderDesc) =:holidayName OR holidayMaster.holidayCalenderDesc=:holidayName"),

		@NamedQuery(name = "GET_HOLIDAY_LIST_BY_ID", query = "SELECT holidayMaster.holidayCalenderId as holidayCalenderId, "
				+ "holidayMaster.holidayCalenderCode as holidayCalenderCode, "
				+ "holidayMaster.holidayCalenderDesc as holidayCalenderDesc, "
				+ "to_char(holidayMaster.holidayDate,'dd-MM-yyyy') as holidayDate, "
				+ "holidayMaster.status as status, " 
				+ "holidayMaster.dayId as dayId "
				+ "FROM HolidayCalenderMaster as holidayMaster " 
				+ "WHERE holidayMaster.holidayCalenderId =:holidayId"),

		@NamedQuery(name = "GET_HOLIDAY_LIST_BY_NAME_NOT_ID", query = "SELECT holidayMaster.holidayCalenderId as holidayCalenderId, "
				+ "holidayMaster.holidayCalenderDesc as holidayCalenderDesc "
				+ "FROM HolidayCalenderMaster as holidayMaster "
				+ "WHERE (LOWER(holidayMaster.holidayCalenderDesc) =:holidayName OR holidayMaster.holidayCalenderDesc=:holidayName) AND holidayMaster.holidayCalenderId !=:holidayId"),
		
		@NamedQuery(name = "GET_ACTIVE_HOLIDAY_LIST", query = "SELECT holidayMaster.holidayCalenderId as holidayCalenderId, "
				+ "to_char(holidayMaster.holidayDate,'yyyy-MM-dd') as holidayDate, "
				+ "holidayMaster.holidayCalenderDesc as holidayCalenderDesc "
				+ "FROM HolidayCalenderMaster as holidayMaster "
				+ "WHERE holidayMaster.status='A' "
				+ "AND holidayMaster.organizationId=:orgId ")

})

@Entity
@Table(name = "m_holiday_calender_master", schema = "adt")
@SequenceGenerator(name = "holiday_calender_master_seq", sequenceName = "adt.holiday_calender_master_seq", allocationSize = 1)
public class HolidayCalenderMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "holiday_calender_master_seq")
	@Column(name = "holiday_calender_id")
	private int holidayCalenderId;

	@Column(name = "holiday_calender_code")
	private String holidayCalenderCode;

	@Column(name = "holiday_calender_desc")
	private String holidayCalenderDesc;

	@Column(name = "status")
	private char status;
	
	@Column(name = "holiday_calender_date")
	private Date holidayDate;

	@Column(name = "day_id")
	private Integer dayId;

	@Column(name = "organization_id")
	private Integer organizationId;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "day_id", insertable = false, updatable = false, nullable = false)
	private DayMaster dayMaster;

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


	public int getHolidayCalenderId() {
		return holidayCalenderId;
	}

	public void setHolidayCalenderId(int holidayCalenderId) {
		this.holidayCalenderId = holidayCalenderId;
	}

	public String getHolidayCalenderCode() {
		return holidayCalenderCode;
	}

	public void setHolidayCalenderCode(String holidayCalenderCode) {
		this.holidayCalenderCode = holidayCalenderCode;
	}

	public String getHolidayCalenderDesc() {
		return holidayCalenderDesc;
	}

	public void setHolidayCalenderDesc(String holidayCalenderDesc) {
		this.holidayCalenderDesc = holidayCalenderDesc;
	}

	

	public Date getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(Date holidayDate) {
		this.holidayDate = holidayDate;
	}

	public Integer getDayId() {
		return dayId;
	}

	public void setDayId(Integer dayId) {
		this.dayId = dayId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

}
