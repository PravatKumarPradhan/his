package com.param.entity.model.inventory;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.master.RejectReason;
import com.param.entity.model.master.ReturnReason;
import com.param.entity.model.master.Status;
import com.param.entity.model.master.UomType;
import com.param.entity.model.master.UomUnit;

@Entity(name = "com.param.entity.model.inventory.returnDetail")
@Table(name = "t_return_detail", schema = "inventory")
public class ReturnDetail extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@Column(name = "accepted_qty")
	private Integer acceptedQty;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "batch_id")
	private Batch batch;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reason_id")
	private ReturnReason returnReason;

	@Column(name = "rejected_note", length = 100)
	private String rejectedNote;

	@Column(name = "rejected_qty")
	private Integer rejectedQty;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rejected_reason_id")
	private RejectReason rejectReason;

	@Column(name = "return_qty")
	private Integer returnQty;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uom_type_id")
	private UomType uomType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uom_unit_id")
	private UomUnit uomUnit;

	// bi-directional many-to-one association to TReturn
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "return_id")
	private Return mreturn;

	public ReturnDetail() {
	}

	public ReturnDetail(Integer id) {
		super();
		this.id = id;
	}

	public ReturnDetail(Batch batch, ReturnReason returnReason, Integer returnQty, Status status, UomType uomType,
			UomUnit uomUnit) {
		super();
		this.batch = batch;
		this.returnReason = returnReason;
		this.returnQty = returnQty;
		this.status = status;
		this.uomType = uomType;
		this.uomUnit = uomUnit;
	}

	public void updateMaterialReturn(ReturnReason returnReason, Integer returnQty) {
		this.returnReason = returnReason;
		this.returnQty = returnQty;
	}
	
	

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAcceptedQty() {
		return this.acceptedQty;
	}

	public void setAcceptedQty(Integer acceptedQty) {
		this.acceptedQty = acceptedQty;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getRejectedQty() {
		return this.rejectedQty;
	}

	public void setRejectedQty(Integer rejectedQty) {
		this.rejectedQty = rejectedQty;
	}

	public Integer getReturnQty() {
		return this.returnQty;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public ReturnReason getReturnReason() {
		return returnReason;
	}

	public void setReturnReason(ReturnReason returnReason) {
		this.returnReason = returnReason;
	}

	public String getRejectedNote() {
		return rejectedNote;
	}

	public void setRejectedNote(String rejectedNote) {
		this.rejectedNote = rejectedNote;
	}

	public RejectReason getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(RejectReason rejectReason) {
		this.rejectReason = rejectReason;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public UomType getUomType() {
		return uomType;
	}

	public void setUomType(UomType uomType) {
		this.uomType = uomType;
	}

	public UomUnit getUomUnit() {
		return uomUnit;
	}

	public void setUomUnit(UomUnit uomUnit) {
		this.uomUnit = uomUnit;
	}

	public Return getMreturn() {
		return mreturn;
	}

	public void setMreturn(Return mreturn) {
		this.mreturn = mreturn;
	}

	public void setReturnQty(Integer returnQty) {
		this.returnQty = returnQty;
	}

}