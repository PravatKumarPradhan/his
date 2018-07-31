package com.param.adt.housekeeping.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.adt.master.global.model.OrganizationMaster;
import com.param.adt.master.global.model.ReasonMaster;
import com.param.adt.master.global.model.UnitMaster;
import com.param.global.model.PriorityMaster;

@Entity
@Table(name="t_housekeeping_log",schema="adt")
@SequenceGenerator(name="housekeeping_log_seq",sequenceName="adt.housekeeping_log_seq",allocationSize=1)
public class HousekeepingLog {

	@Id
	@Column(name="housekeeping_log_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="housekeeping_log_seq")
	private int housekeepingLogId;
	
	@Column(name = "organization_id")
	private Integer organizationId;

	@Column(name = "unit_id")
	private Integer unitId;
	
	@Column(name="priority_id")
	private Integer priorityId;
	
	@Column(name="assigned_person_id")
	private Integer assignedPersonId;
	
	@Column(name="housekeeping_id")
	private Integer housekeepingId;
	
	@Column(name="housekeeping_status_id")
	private Integer housekeepingStatusId;
	
	@Column(name = "status")
	private char status;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name="rejection_reason_id")
	private Integer rejectionReasonId;

	@Column(name="note")
	private String note;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private OrganizationMaster organizationMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id", insertable = false, updatable = false)
	private UnitMaster unitMaster;
	
/*	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "priority_id", insertable = false, updatable = false)
	private PriorityMaster priorityMaster;*/
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "housekeeping_id", insertable = false, updatable = false)
	private Housekeeping housekeeping;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "housekeeping_status_id", insertable = false, updatable = false)
	private HousekeepingStatusMaster housekeepingStatusMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rejection_reason_id", insertable = false, updatable = false)
	private ReasonMaster reasonMaster;
	
	public Integer getRejectionReasonId() {
		return rejectionReasonId;
	}

	public void setRejectionReasonId(Integer rejectionReasonId) {
		this.rejectionReasonId = rejectionReasonId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getHousekeepingStatusId() {
		return housekeepingStatusId;
	}

	public void setHousekeepingStatusId(Integer housekeepingStatusId) {
		this.housekeepingStatusId = housekeepingStatusId;
	}

	public int getHousekeepingLogId() {
		return housekeepingLogId;
	}

	public void setHousekeepingLogId(int housekeepingLogId) {
		this.housekeepingLogId = housekeepingLogId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(Integer priorityId) {
		this.priorityId = priorityId;
	}

	public Integer getAssignedPersonId() {
		return assignedPersonId;
	}

	public void setAssignedPersonId(Integer assignedPersonId) {
		this.assignedPersonId = assignedPersonId;
	}

	public Integer getHousekeepingId() {
		return housekeepingId;
	}

	public void setHousekeepingId(Integer housekeepingId) {
		this.housekeepingId = housekeepingId;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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
	
}


/*housekeeping_log_id integer NOT NULL DEFAULT nextval('adt.housekeeping_log_seq'::regclass),
priority_id integer,
assigned_person_id integer,
housekeeping_id integer,
created_by integer,
created_date timestamp without time zone DEFAULT now(),
updated_by integer,
updated_date timestamp without time zone DEFAULT now(),
status character(1) DEFAULT 'A'::bpchar,*/