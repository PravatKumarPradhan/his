package com.param.entity.model.inventory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.master.CancelReason;
import com.param.entity.model.master.Status;
import com.param.entity.model.master.Store;
import com.param.entity.model.master.User;

@Entity(name = "BatchExp")
@Table(name = "t_batch_exp", schema = "inventory")
public class BatchExp extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@Column(name = "adjustment_number", length = 100)
	private String adjustmentNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approval_status_id", nullable = false)
	private Status approvalStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approved_by")
	protected User approvedBy;

	@Column(name = "approved_on")
	private Date approvedOn;

	@Column(name = "cancel_note", length = 100)
	private String cancelNote;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cancel_reason_id")
	private CancelReason cancelReason;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id", nullable = false)
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id", nullable = false)
	private Store store;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "batchExp", cascade = CascadeType.ALL)
	private List<BatchExpDetail> batchExpDetails;

	public BatchExp() {
		super();
	}

	public BatchExp(String adjustmentNumber, Status status, Status approvalStatus, Store store) {
		super();
		this.adjustmentNumber = adjustmentNumber;
		this.status = status;
		this.store = store;
		this.approvalStatus = approvalStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdjustmentNumber() {
		return adjustmentNumber;
	}

	public void setAdjustmentNumber(String adjustmentNumber) {
		this.adjustmentNumber = adjustmentNumber;
	}

	public Status getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(Status approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public User getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(User approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getApprovedOn() {
		return approvedOn;
	}

	public void setApprovedOn(Date approvedOn) {
		this.approvedOn = approvedOn;
	}

	public String getCancelNote() {
		return cancelNote;
	}

	public void setCancelNote(String cancelNote) {
		this.cancelNote = cancelNote;
	}

	public CancelReason getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(CancelReason cancelReason) {
		this.cancelReason = cancelReason;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public List<BatchExpDetail> getBatchExpDetails() {
		return batchExpDetails;
	}

	public void setBatchExpDetails(List<BatchExpDetail> batchExpDetails) {
		this.batchExpDetails = batchExpDetails;
	}

	public BatchExpDetail addBatchExpDetail(BatchExpDetail batchExpDetails) {
		if (this.batchExpDetails == null)
			this.batchExpDetails = new ArrayList<BatchExpDetail>();

		this.batchExpDetails.add(batchExpDetails);
		batchExpDetails.setBatchExp(this);

		return batchExpDetails;
	}

	public BatchExpDetail removeBatchExpDetail(BatchExpDetail batchExpDetail) {
		this.batchExpDetails.remove(batchExpDetail);
		batchExpDetail.setBatchExp(null);

		return batchExpDetail;
	}

}