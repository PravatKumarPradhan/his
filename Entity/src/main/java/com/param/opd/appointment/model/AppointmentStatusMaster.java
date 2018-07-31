package com.param.opd.appointment.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
	
	@NamedQuery(name="GET_LIST_APPOINTMENT_STATUS_MASTER",
				query="     SELECT appStatusMst.appointmentStatusId as appointmentStatusId, appStatusMst.appointmentStatusCode as appointmentStatusCode,"
						+ " 	   appStatusMst.appointmentStatusDesc as appointmentStatusDesc "
						+ " FROM   AppointmentStatusMaster appStatusMst "
						+ " WHERE  appStatusMst.unitId=:unitId "
						+ " AND    appStatusMst.organizationId=:organizationId "
						+ " AND    appStatusMst.status='A'" 
			)
})


@Entity
@Table(name="m_appointment_status_master",schema="opd")
public class AppointmentStatusMaster {

	@Id
	@Column(name="appointment_status_id")
	private Integer appointmentStatusId;
	
	@Column(name="appointment_status_code")
	private String appointmentStatusCode;
	
	@Column(name="appointment_status_desc")
	private String appointmentStatusDesc;
	
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
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="organization_id")
	private Integer organizationId;

	
	
	
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

	public Integer getAppointmentStatusId() {
		return appointmentStatusId;
	}

	public void setAppointmentStatusId(Integer appointmentStatusId) {
		this.appointmentStatusId = appointmentStatusId;
	}

	public String getAppointmentStatusCode() {
		return appointmentStatusCode;
	}

	public void setAppointmentStatusCode(String appointmentStatusCode) {
		this.appointmentStatusCode = appointmentStatusCode;
	}

	public String getAppointmentStatusDesc() {
		return appointmentStatusDesc;
	}

	public void setAppointmentStatusDesc(String appointmentStatusDesc) {
		this.appointmentStatusDesc = appointmentStatusDesc;
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
		this.status = status;
	}
}

