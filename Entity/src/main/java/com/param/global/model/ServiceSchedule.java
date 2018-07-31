package com.param.global.model;

import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.global.common.LocalTimeConverter;

@Entity
@Table(name="t_service_schedule",schema="service")
@SequenceGenerator(name="service_schedule_seq",sequenceName="service.service_schedule_seq",allocationSize=1)
public class ServiceSchedule {
	
	@Id
	@Column(name="schedule_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="service_schedule_seq")
	private int scheduleId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="unit_id")
	private Integer unitId;

	@Column(name="group_id")
	private Integer groupId;
	
	@Column(name="from_time")
	@Convert(converter = LocalTimeConverter.class)
	private LocalTime fromTime;
	
	@Column(name="to_time")
	@Convert(converter = LocalTimeConverter.class)
	private LocalTime toTime;
	
	@Column(name="available_status")
	private char availableStatus;
	
	@Column(name="equipment_id")
	private Integer equipmentId;
	
	@Column(name="admission_id")
	private Integer admissionId;
	
	@Column(name="patient_service_order_id")
	private Integer patientServiceOrderId; 
	
	@Column(name="schedule_date")
	private Date scheduleDate;
	
	@Column(name = "status")
	private String status;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name="visit_type_id")
	private Integer visitTypeId;
	
	@Column(name="transfer_type_id")
	private Integer transferTypeId;
	
	public Integer getTransferTypeId() {
		return transferTypeId;
	}

	public void setTransferTypeId(Integer transferTypeId) {
		this.transferTypeId = transferTypeId;
	}

	public Integer getPatientServiceOrderId() {
		return patientServiceOrderId;
	}

	public void setPatientServiceOrderId(Integer patientServiceOrderId) {
		this.patientServiceOrderId = patientServiceOrderId;
	}

	public Integer getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
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

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
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

	public char getAvailableStatus() {
		return availableStatus;
	}

	public void setAvailableStatus(char availableStatus) {
		this.availableStatus = availableStatus;
	}

	public Integer getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public Integer getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(Integer admissionId) {
		this.admissionId = admissionId;
	}

	public Date getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
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
/*service.t_service_schedule
(
  created_by integer,
  created_date timestamp without time zone,
  unit_id integer,
  updated_by integer,
  updated_date timestamp with time zone DEFAULT now(),
  status "char",
  organization_id integer,
  */