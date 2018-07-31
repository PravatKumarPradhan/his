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

@Entity(name = "com.param.entity.model.inventory.return")
@Table(name = "t_return", schema = "inventory")
public class Return extends BaseEntity implements Serializable {
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
	protected User approvedBy;

	@Column(name = "approved_date")
	private Date approvedDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "carrier_id")
	private User carrier;

	@Column(name = "is_consignment", nullable = false)
	private Boolean isConsignment = false;

	private String remark;

	@Column(name = "return_no")
	private String returnNo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id", nullable = false)
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "from_store_id", nullable = false)
	private Store fromStore;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "to_store_id", nullable = false)
	private Store toStore;

	@Column(name = "near_by_exp")
	private Boolean nearByExpiry;

	@Column(name = "cancel_note", length = 100)
	private String cancelNote;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cancel_reason_id")
	private CancelReason cancelReason;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mreturn", cascade = CascadeType.ALL)
	private List<ReturnDetail> returnDetail;

	public Return() {
	}

	public Return(Integer id) {
		super();
		this.id = id;
	}

	public Return(Status approvalStatus, User carrier, String returnNo, Boolean isConsignment, String remark,
			Status status, Store fromStore, Store toStore, Boolean nearByExpiry) {
		super();
		this.approvalStatus = approvalStatus;
		this.carrier = carrier;
		this.returnNo = returnNo;
		this.isConsignment = isConsignment;
		this.remark = remark;
		this.status = status;
		this.fromStore = fromStore;
		this.toStore = toStore;
		this.nearByExpiry = nearByExpiry;
	}

	public Return(Status approvalStatus, User carrier, Boolean isConsignment, String remark, String returnNo,
			Status status, Store fromStore, Store toStore, Boolean nearByExpiry) {
		super();
		this.approvalStatus = approvalStatus;
		this.carrier = carrier;
		this.isConsignment = isConsignment;
		this.remark = remark;
		this.returnNo = returnNo;
		this.status = status;
		this.fromStore = fromStore;
		this.toStore = toStore;
		this.nearByExpiry = nearByExpiry;
	}

	public void updateMaterialreturn(User carrier, String remark){
		this.carrier = carrier;
		this.remark = remark;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsConsignment() {
		return this.isConsignment;
	}

	public void setIsConsignment(Boolean isConsignment) {
		this.isConsignment = isConsignment;
	}

	public Boolean getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getReturnNo() {
		return this.returnNo;
	}

	public void setReturnNo(String returnNo) {
		this.returnNo = returnNo;
	}

	public Status getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(Status approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	
	public User getCarrier() {
		return carrier;
	}

	public void setCarrier(User carrier) {
		this.carrier = carrier;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Store getFromStore() {
		return fromStore;
	}

	public void setFromStore(Store fromStore) {
		this.fromStore = fromStore;
	}

	public Store getToStore() {
		return toStore;
	}

	public void setToStore(Store toStore) {
		this.toStore = toStore;
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

	public Boolean getNearByExpiry() {
		return nearByExpiry;
	}

	public void setNearByExpiry(Boolean nearByExpiry) {
		this.nearByExpiry = nearByExpiry;
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

	public List<ReturnDetail> getReturnDetail() {
		return returnDetail;
	}

	public void setReturnDetail(List<ReturnDetail> returnDetail) {
		this.returnDetail = returnDetail;
	}

	public List<ReturnDetail> getReturnDetails() {
		return this.returnDetail;
	}

	public void setReturnDetails(List<ReturnDetail> ReturnDetails) {
		this.returnDetail = ReturnDetails;
	}
	
	public ReturnDetail addReturnDetail(ReturnDetail returnDetail) {
		if (getReturnDetails() == null)
			this.returnDetail = new ArrayList<ReturnDetail>();
		
		getReturnDetails().add(returnDetail);
		returnDetail.setMreturn(this);

		return returnDetail;
	}

	public ReturnDetail removeReturnDetail(ReturnDetail ReturnDetail) {
		getReturnDetails().remove(ReturnDetail);
		ReturnDetail.setMreturn(null);

		return ReturnDetail;
	}

}