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

@Entity(name = "OpeningBalance")
@Table(name = "t_opening_balance", schema = "inventory")
public class OpeningBalance extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@Column(name = "cancel_note", length = 100)
	private String cancelNote;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cancel_reason_id")
	private CancelReason cancelReason;

	@Column(name = "opb_no", nullable = false, length = 50)
	private String opbNo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="approved_by")
	private User approvedBy;

	@Column(name = "approved_date")
	private Date approvedDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id", nullable = false)
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
	private Store store;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "openingBalance", cascade = CascadeType.ALL)
	private List<OpeningBalanceDetail> openingBalanceDetailList;

	public OpeningBalance() {
		super();
	}

	public OpeningBalance(String opbNo, Status status, Store store) {
		super();
		this.opbNo = opbNo;
		this.status = status;
		this.store = store;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getOpbNo() {
		return opbNo;
	}

	public void setOpbNo(String opbNo) {
		this.opbNo = opbNo;
	}

	/*
	 * public String getRemark() { return remark; }
	 * 
	 * public void setRemark(String remark) { this.remark = remark; }
	 */
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

	public List<OpeningBalanceDetail> getOpeningBalanceDetailList() {
		return openingBalanceDetailList;
	}

	public void setOpeningBalanceDetailList(List<OpeningBalanceDetail> openingBalanceDetailList) {
		this.openingBalanceDetailList = openingBalanceDetailList;
	}

	public User getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(User approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public OpeningBalanceDetail addOpeningBalanceDetail(OpeningBalanceDetail openingBalanceDetail) {
		if (this.openingBalanceDetailList == null)
			this.openingBalanceDetailList = new ArrayList<OpeningBalanceDetail>();

		this.openingBalanceDetailList.add(openingBalanceDetail);
		openingBalanceDetail.setOpeningBalance(this);

		return openingBalanceDetail;
	}

	public OpeningBalanceDetail removeOpeningBalanceDetail(OpeningBalanceDetail openingBalanceDetail) {
		this.openingBalanceDetailList.remove(openingBalanceDetail);
		openingBalanceDetail.setOpeningBalance(null);

		return openingBalanceDetail;
	}

	public OpeningBalanceDetail addOpeningBalanceDetailList(OpeningBalanceDetail openingBalanceDetail) {
		if (getOpeningBalanceDetailList() == null)
			this.openingBalanceDetailList = new ArrayList<OpeningBalanceDetail>();

		getOpeningBalanceDetailList().add(openingBalanceDetail);
		openingBalanceDetail.setOpeningBalance(this);

		return openingBalanceDetail;
	}

}