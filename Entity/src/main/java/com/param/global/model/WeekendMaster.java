package com.param.global.model;

import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
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

import com.param.global.common.LocalTimeConverter;


@NamedQueries({
	@NamedQuery(name="GET_ACTIVE_WEEKENDS_LIST",query="SELECT weekendMaster.weekendId as weekendId, "
			+ "to_char(weekendMaster.weekendDate,'yyyy-dd-mm') as weekendDate, "
			+ "weekendMaster.dayId as dayId, "
			+ "dayM.day as day, "
			+ "weekendMaster.isHalfDay as isHalfDay, "
			+ "to_char(weekendMaster.fromTime,'HH:MI') as fromTimeString, "
			+ "to_char(weekendMaster.toTime,'HH:MI') as toTimeString "
			+ "FROM WeekendMaster weekendMaster "
			+ "INNER JOIN weekendMaster.dayMaster dayM "
			+ "WHERE weekendMaster.organizationId=:organizationId "
			+ "AND weekendMaster.unitId=:unitId "
			+ "AND weekendMaster.status='A'")
	})

@Entity
@Table(name="m_weekend_master",schema="public")
@SequenceGenerator(name="weekend_master_seq",sequenceName="public.weekend_master_seq",allocationSize=1)
public class WeekendMaster {

	@Id
	@Column(name="weekend_master_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="weekend_master_seq")
	private Integer weekendId;
	
	@Column(name = "unit_id")
	private Integer unitId;
	
	@Column(name = "organization_id")
	private Integer organizationId;
	
	@Column(name="weekend_date")
	private Date weekendDate;
	
	@Column(name="day_id")
	private Integer dayId;
	
	@Column(name="is_half_day")
	private char isHalfDay;
	
	@Column(name="from_time")
	@Convert(converter = LocalTimeConverter.class)
	private LocalTime fromTime;
	
	@Column(name="to_time")
	@Convert(converter = LocalTimeConverter.class)
	private LocalTime toTime;
	
	@Column(name="created_by")
	private Integer createdBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="updated_by")
	private Integer updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="status")
	private char status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "day_id", insertable = false, updatable = false, nullable = false)
	private DayMaster dayMaster;
	
	public Integer getWeekendId() {
		return weekendId;
	}

	public void setWeekendId(Integer weekendId) {
		this.weekendId = weekendId;
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

	public Date getWeekendDate() {
		return weekendDate;
	}

	public void setWeekendDate(Date weekendDate) {
		this.weekendDate = weekendDate;
	}

	public Integer getDayId() {
		return dayId;
	}

	public void setDayId(Integer dayId) {
		this.dayId = dayId;
	}

	public char getIsHalfDay() {
		return isHalfDay;
	}

	public void setIsHalfDay(char isHalfDay) {
		this.isHalfDay = (isHalfDay == '\u0000') ? 'N' : isHalfDay;
	}

	public LocalTime getFromTime() {
		return fromTime;
	}

	public void setFromTime(LocalTime fromTime) {
		this.fromTime = fromTime;
	}

	public LocalTime getToTime() {
		return toTime;
	}

	public void setToTime(LocalTime toTime) {
		this.toTime = toTime;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = (status == '\u0000') ? 'A' : status;
	}
	
	
	
	
}
/*CREATE TABLE public.m_weekend_master
(
  weekend_master_id integer NOT NULL DEFAULT nextval('adt.holiday_calender_master_seq'::regclass),
  organization_id integer,
  unit_id integer,	
  day_id integer,
  weekend_date timestamp without time zone,
  is_half_day character(1) DEFAULT 'N'::bpchar,
  from_time time without time zone,
  to_time time without time zone,
  created_by integer,
  created_date timestamp without time zone DEFAULT now(),
  status character(1) DEFAULT 'A'::bpchar,
  updated_by integer,
  updated_date timestamp without time zone DEFAULT now(),
  
  CONSTRAINT m_weekend_master_pkey PRIMARY KEY (weekend_master_id),
  CONSTRAINT m_day_master_fKey FOREIGN KEY (day_id)
      REFERENCES public.m_day_master (day_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)*/