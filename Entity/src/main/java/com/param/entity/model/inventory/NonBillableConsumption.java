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

@Entity(name = "NonBillableConsumption")
@Table(name = "t_non_billable_consumption", schema="inventory")
public class NonBillableConsumption extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approval_status_id", nullable = false)
	private Status approvalStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approved_by")
	private User approvedBy;

	@Column(name = "approved_on")
	private Date approvedOn;

	@Column(name = "cancel_note", length = 100)
	private String cancelNote;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cancel_reason_id")
	private CancelReason cancelReason;

	@Column(name = "consumption_no", nullable = false, length = 100)
	private String consumptionNo;

	@Column(length = 100)
	private String remark;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id", nullable = false)
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id", nullable = false)
	private Store store;

	@OneToMany(mappedBy = "nonBillableConsumption", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<NonBillableConsumptionDetail> nonBillableConsumptionDetails;

	public NonBillableConsumption() {
		super();
	}

	public NonBillableConsumption(String consumptionNo, Store store, Status status, Status approvalStatus,
			String remark) {
		super();
		this.consumptionNo = consumptionNo;
		this.store = store;
		this.status = status;
		this.approvalStatus = approvalStatus;
		this.remark = remark;
	}
	
	public void updateNonBillableConsumption(String remark) {
		this.remark = remark;
		this.updatedDate = new Date();
		this.updatedBy = this.getUser();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getConsumptionNo() {
		return consumptionNo;
	}

	public void setConsumptionNo(String consumptionNo) {
		this.consumptionNo = consumptionNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public List<NonBillableConsumptionDetail> getNonBillableConsumptionDetails() {
		return nonBillableConsumptionDetails;
	}

	public void setNonBillableConsumptionDetails(List<NonBillableConsumptionDetail> nonBillableConsumptionDetails) {
		this.nonBillableConsumptionDetails = nonBillableConsumptionDetails;
	}

	public NonBillableConsumptionDetail addNonBillableConsumptionDetail(
			NonBillableConsumptionDetail nonBillableConsumptionDetail) {
		
		if (this.nonBillableConsumptionDetails == null)
			this.nonBillableConsumptionDetails = new ArrayList<NonBillableConsumptionDetail>();
		
		this.nonBillableConsumptionDetails.add(nonBillableConsumptionDetail);
		nonBillableConsumptionDetail.setNonBillableConsumption(this);

		return nonBillableConsumptionDetail;
	}

	public NonBillableConsumptionDetail removeNonBillableConsumptionDetail(
			NonBillableConsumptionDetail nonBillableConsumptionDetail) {
		this.nonBillableConsumptionDetails.remove(nonBillableConsumptionDetail);
		nonBillableConsumptionDetail.setNonBillableConsumption(null);

		return nonBillableConsumptionDetail;
	}

}