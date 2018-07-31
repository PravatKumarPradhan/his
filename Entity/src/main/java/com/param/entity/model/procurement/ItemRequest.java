package com.param.entity.model.procurement;

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
import com.param.entity.model.master.AssetType;
import com.param.entity.model.master.CancelReason;
import com.param.entity.model.master.Status;
import com.param.entity.model.master.User;

@Entity(name = "ItemRequest")
@Table(name="t_item_request",schema="procurement")
public class ItemRequest extends BaseEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "approval_status_id")
	private Status approvalStatus;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "approved_by")
	private User approvedBy;

	@Column(name="approved_date")
	private Date approvedDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "asset_type_id")
	private AssetType assetType;
	
	@Column(name="cancel_note")
	private String cancelNote;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "cancel_reason_id")
	private CancelReason cancelReason;
	
	@Column(name="request_no")
	private String requestNumber;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private Status status;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "itemRequest", cascade = CascadeType.ALL)
	private List<ItemRequestDetail> itemRequestDetailList;

	public ItemRequest() {
		super();
	}

	public ItemRequest(Status approvalStatus, AssetType assetType, String requestNumber, Status status) {
		super();
		this.approvalStatus = approvalStatus;
		this.assetType = assetType;
		this.requestNumber = requestNumber;
		this.status = status;
	}
	
	public void UpdateItemRequest(Status status) {
		
		this.status = status;
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

	public AssetType getAssetType() {
		return assetType;
	}

	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}

	public String getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	public List<ItemRequestDetail> getItemRequestDetailList() {
		return itemRequestDetailList;
	}

	public void setItemRequestDetailList(List<ItemRequestDetail> itemRequestDetailList) {
		this.itemRequestDetailList = itemRequestDetailList;
	}
	
	public ItemRequestDetail addItemRequestDetailList(ItemRequestDetail itemRequestDetail) {
		if (getItemRequestDetailList() == null)
			this.itemRequestDetailList = new ArrayList<ItemRequestDetail>();
		
		getItemRequestDetailList().add(itemRequestDetail);
		itemRequestDetail.setItemRequest(this);
		return itemRequestDetail;
	}

	public ItemRequestDetail removeItemRequestDetailList(ItemRequestDetail itemRequestDetail) {
		getItemRequestDetailList().remove(itemRequestDetail);
		itemRequestDetail.setItemRequest(null);
		return itemRequestDetail;
	}
	
	
}
