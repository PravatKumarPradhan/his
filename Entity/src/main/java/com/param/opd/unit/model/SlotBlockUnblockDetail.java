package com.param.opd.unit.model;

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

import com.param.adt.master.global.model.ReasonMaster;

@Entity
@Table(name="t_slot_block_unblock_detail", schema="opd")
@SequenceGenerator(name="t_slot_block_unblock_detail_seq",sequenceName="opd.t_slot_block_unblock_detail_seq",allocationSize=1)
public class SlotBlockUnblockDetail {

	@Id
	@Column(name="slot_block_detail_id")
	@GeneratedValue(generator="t_slot_block_unblock_detail_seq", strategy=GenerationType.SEQUENCE)
	private Integer slotBlockDetailId;
	
	@Column(name="slot_id")
	private Integer slotId;
	
	@Column(name="reason_id")
	private Integer reasonId;
	
	@Column(name="remark")
	private String remark;
	
	@Column(name="created_by")
	private Integer createdBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="is_block_unblock")
	private Integer isBlockUnblock;
	
	@Column(name="organisation_id")
	private Integer organisationId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="status")
	private Character status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "slot_id", insertable = false, nullable = false, updatable = false)
	private SlotMaster slotMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reason_id", insertable = false, nullable = false, updatable = false)
	private ReasonMaster reasonMaster;

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public Integer getSlotBlockDetailId() {
		return slotBlockDetailId;
	}

	public void setSlotBlockDetailId(Integer slotBlockDetailId) {
		this.slotBlockDetailId = slotBlockDetailId;
	}

	public Integer getSlotId() {
		return slotId;
	}

	public void setSlotId(Integer slotId) {
		this.slotId = slotId;
	}

	public Integer getReasonId() {
		return reasonId;
	}

	public void setReasonId(Integer reasonId) {
		this.reasonId = reasonId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Integer getIsBlockUnblock() {
		return isBlockUnblock;
	}

	public void setIsBlockUnblock(Integer isBlockUnblock) {
		this.isBlockUnblock = isBlockUnblock;
	}

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	
	
}
