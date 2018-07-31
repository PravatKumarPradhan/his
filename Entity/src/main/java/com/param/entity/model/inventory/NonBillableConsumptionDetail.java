package com.param.entity.model.inventory;

import java.io.Serializable;
import java.util.Date;

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
import com.param.entity.model.master.Status;
import com.param.entity.model.master.UomType;
import com.param.entity.model.master.UomUnit;

@Entity(name = "NonBillableConsumptionDetail")
@Table(name = "t_non_billable_consumption_detail", schema="inventory")
public class NonBillableConsumptionDetail extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@Column(name = "accepted_qty", nullable = false)
	private Integer acceptedQty = 0;

	@Column(name = "consumed_qty", nullable = false)
	private Integer consumedQty = 0;

	@Column(name = "reject_note", length = 100)
	private String rejectNote;

	@Column(name = "reject_qty", nullable = false)
	private Integer rejectQty = 0;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reject_reason_id")
	private RejectReason rejectReason;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id", nullable = false)
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uom_type_id", nullable = false)
	protected UomType uomType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uom_unit_id", nullable = false)
	protected UomUnit uomUnit;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "batch_id", nullable = false)
	private Batch batch;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "non_billable_consumption_id", nullable = false)
	private NonBillableConsumption nonBillableConsumption;

	public NonBillableConsumptionDetail() {
		super();
	}

	public NonBillableConsumptionDetail(Batch batch, UomType uomType, UomUnit uomUnit, Integer consumedQty,
			Status status) {
		super();
		this.batch = batch;
		this.uomType = uomType;
		this.uomUnit = uomUnit;
		this.consumedQty = consumedQty;
		this.status = status;
	}

	public void approveConsumption(Integer acceptedQty, Integer rejectQty, String rejectNote, Status status, RejectReason rejectReason) {
		this.acceptedQty = acceptedQty;
		this.rejectNote = rejectNote;
		this.rejectQty = rejectQty;
		this.rejectReason = rejectReason;
		this.status = status;
		this.updatedDate = new Date();
		this.updatedBy = this.getUser();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAcceptedQty() {
		return acceptedQty;
	}

	public void setAcceptedQty(Integer acceptedQty) {
		this.acceptedQty = acceptedQty;
	}

	public Integer getConsumedQty() {
		return consumedQty;
	}

	public void setConsumedQty(Integer consumedQty) {
		this.consumedQty = consumedQty;
	}

	public String getRejectNote() {
		return rejectNote;
	}

	public void setRejectNote(String rejectNote) {
		this.rejectNote = rejectNote;
	}

	public Integer getRejectQty() {
		return rejectQty;
	}

	public void setRejectQty(Integer rejectQty) {
		this.rejectQty = rejectQty;
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

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public NonBillableConsumption getNonBillableConsumption() {
		return nonBillableConsumption;
	}

	public void setNonBillableConsumption(NonBillableConsumption nonBillableConsumption) {
		this.nonBillableConsumption = nonBillableConsumption;
	}
}