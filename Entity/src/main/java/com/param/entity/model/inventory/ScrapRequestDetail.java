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
import com.param.entity.model.master.ScrapReason;
import com.param.entity.model.master.Status;
import com.param.entity.model.master.UomType;
import com.param.entity.model.master.UomUnit;

@Entity(name = "com.param.entity.model.inventory.scrapRequestDetail")
@Table(name = "t_scrap_request_detail", schema = "inventory")
public class ScrapRequestDetail extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@Column(name = "scrap_quantity")
	private Integer scrapQty;

	@Column(name = "accepted_quantity")
	private Integer acceptedQty;

	@Column(name = "amount_cop", nullable = false)
	private Double amountCop;

	@Column(name = "reject_note", length = 100)
	private String rejectedNote;

	@Column(name = "reject_quantity")
	private Integer rejectedQty;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reject_reason_id")
	private RejectReason rejectReason;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "scrap_reason_id")
	private ScrapReason scrapReason;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "batch_id")
	private Batch batch;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uom_type_id")
	private UomType uomType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uom_unit_id")
	private UomUnit uomUnit;

	@ManyToOne
	@JoinColumn(name = "scrap_request_id")
	private ScrapRequest scrapRequest;

	public ScrapRequestDetail() {
	}
	
	public ScrapRequestDetail(Integer id) {
		super();
		this.id = id;
	}

	public ScrapRequestDetail(Integer scrapQty, ScrapReason scrapReason, Batch batch, Status status, UomType uomType,
			UomUnit uomUnit,  Double amountCop) {
		super();
		this.scrapQty = scrapQty;
		this.scrapReason = scrapReason;
		this.batch = batch;
		this.status = status;
		this.uomType = uomType;
		this.uomUnit = uomUnit;
		this.amountCop = amountCop;
	}

	public ScrapRequestDetail(Integer scrapQty, ScrapReason scrapReason, Status status, UomType uomType,
			UomUnit uomUnit, Batch batch, Double amountCop) {
		super();
		this.scrapQty = scrapQty;
		this.scrapReason = scrapReason;
		this.status = status;
		this.uomType = uomType;
		this.uomUnit = uomUnit;
		this.batch = batch;
		this.amountCop = amountCop;
	}

	public void updateScrapRequestDetail(Integer scrapQty, ScrapReason scrapReason, Double amountCop) {
		this.scrapQty = scrapQty;
		this.scrapReason = scrapReason;
		this.amountCop = amountCop;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getScrapQty() {
		return scrapQty;
	}

	public void setScrapQty(Integer scrapQty) {
		this.scrapQty = scrapQty;
	}

	public Double getAmountCop() {
		return amountCop;
	}

	public void setAmountCop(Double amountCop) {
		this.amountCop = amountCop;
	}

	public ScrapReason getScrapReason() {
		return scrapReason;
	}

	public void setScrapReason(ScrapReason scrapReason) {
		this.scrapReason = scrapReason;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
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

	public ScrapRequest getScrapRequest() {
		return scrapRequest;
	}

	public void setScrapRequest(ScrapRequest scrapRequest) {
		this.scrapRequest = scrapRequest;
	}

	public Integer getAcceptedQty() {
		return acceptedQty;
	}

	public void setAcceptedQty(Integer acceptedQty) {
		this.acceptedQty = acceptedQty;
	}

	public String getRejectedNote() {
		return rejectedNote;
	}

	public void setRejectedNote(String rejectedNote) {
		this.rejectedNote = rejectedNote;
	}

	public Integer getRejectedQty() {
		return rejectedQty;
	}

	public void setRejectedQty(Integer rejectedQty) {
		this.rejectedQty = rejectedQty;
	}

	public RejectReason getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(RejectReason rejectReason) {
		this.rejectReason = rejectReason;
	}

}