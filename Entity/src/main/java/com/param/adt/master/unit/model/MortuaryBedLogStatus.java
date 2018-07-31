package com.param.adt.master.unit.model;

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

import com.param.adt.master.global.model.BedStatusMaster;

@Entity
@Table(name="t_mortuary_bed_log_status",schema="adt")
@SequenceGenerator(name="mortuary_bed_log_status_seq",sequenceName="adt.mortuary_bed_log_status_seq",allocationSize=1)
public class MortuaryBedLogStatus {

	@Id
	@Column(name="mortuary_bed_log_status_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="mortuary_bed_log_status_seq")
	private int mortuaryBedLogStatusId;
	
	@Column(name = "mortuary_bed_id")
	private int mortuaryBedId;
	
	@Column(name="bed_status_id")
	private Integer bedStatusId;
	
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "created_by")
	private int createdBy;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="status")
	private char status;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mortuary_bed_id", insertable = false, nullable = false, updatable = false)
	private MortuaryBedMaster mortuaryBedMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bed_status_id", insertable = false, nullable = false, updatable = false)
	private  BedStatusMaster bedStatusMaster;

	public int getMortuaryBedLogStatusId() {
		return mortuaryBedLogStatusId;
	}

	public void setMortuaryBedLogStatusId(int mortuaryBedLogStatusId) {
		this.mortuaryBedLogStatusId = mortuaryBedLogStatusId;
	}

	public int getMortuaryBedId() {
		return mortuaryBedId;
	}

	public void setMortuaryBedId(int mortuaryBedId) {
		this.mortuaryBedId = mortuaryBedId;
	}

	public Integer getBedStatusId() {
		return bedStatusId;
	}

	public void setBedStatusId(Integer bedStatusId) {
		this.bedStatusId = bedStatusId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = (status == '\u0000' ? 'A' : status);
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
	
	
	
}
/*REATE TABLE adt.m_mortuary_bed_master
(
  mortuary_bed_log_status_id integer NOT NULL DEFAULT nextval('adt.mortuary_bed_log_status_seq'::regclass),
  organization_id integer,
  unit_id integer,
  mortuary_bed_id integer,
  bed_status_id integer,
  created_by integer,
  created_date timestamp without time zone DEFAULT now(),
  status character(1) DEFAULT 'A'::bpchar,
  updated_by integer,
  updated_date timestamp without time zone DEFAULT now(),
  CONSTRAINT t_mortuary_bed_log_status_pkey PRIMARY KEY (mortuary_bed_log_status_id)
)*/