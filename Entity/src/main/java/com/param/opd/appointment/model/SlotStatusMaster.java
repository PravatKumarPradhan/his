package com.param.opd.appointment.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="m_slot_status_master",schema="opd")
public class SlotStatusMaster {
	
	@Id
	@Column(name="slot_status_id")
	private Integer slotStatusId;
	
	@Column(name="slot_status_code")
	private String slotStatusCode;
	
	@Column(name="slot_status_desc")
	private String slotStatusDesc;
	
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

	public Integer getSlotStatusId() {
		return slotStatusId;
	}

	public void setSlotStatusId(Integer slotStatusId) {
		this.slotStatusId = slotStatusId;
	}

	public String getSlotStatusCode() {
		return slotStatusCode;
	}

	public void setSlotStatusCode(String slotStatusCode) {
		this.slotStatusCode = slotStatusCode;
	}

	public String getSlotStatusDesc() {
		return slotStatusDesc;
	}

	public void setSlotStatusDesc(String slotStatusDesc) {
		this.slotStatusDesc = slotStatusDesc;
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
