package com.param.entity.model.procurement;


import java.math.BigInteger;
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

import com.param.entity.model.adt.Priority;
import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.master.AssetType;
import com.param.entity.model.master.CancelReason;
import com.param.entity.model.master.RequestType;
import com.param.entity.model.master.Screen;
import com.param.entity.model.master.Status;
import com.param.entity.model.master.Store;
import com.param.entity.model.master.User;

@Entity(name = "PurchaseRequest")
@Table(name = "t_purchase_request", schema = "procurement")
public class PurchaseRequest extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approval_status_id")
	private Status approvalStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approved_by")
	private User approvedBy;

	@Column(name = "approved_date")
	private Date approvedDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "asset_type_id", nullable = false)
	private AssetType assetType;

	@Column(name = "cancel_note", length = 100)
	private String cancelNote;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cancel_reason_id")
	private CancelReason cancelReason;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "priority_id", nullable = false)
	private Priority priority;

	@Column(name = "purchase_no", nullable = false, length = 100)
	private String purchaseNo;

	@Column(length = 100)
	private String remark;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "request_type_id", nullable = false)
	private RequestType requestType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "screen_id", nullable = false)
	private Screen screen;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id", nullable = false)
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id", nullable = false)
	private Store store;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="qr_id")
	private QuotationRequest quotationRequest;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "purchaseRequest", cascade = CascadeType.ALL)
	private List<PurchaseRequestDetail> purchaseRequestDetailList;

	public PurchaseRequest() {
		super();
	}

	public PurchaseRequest(Status approvalStatus,AssetType assetType,
			Priority priority, String purchaseNo, String remark,
			RequestType requestType, Screen screen, Status status, Store store) {
		super();
		
		this.approvalStatus = approvalStatus;
		this.assetType = assetType;
		this.priority = priority;
		this.purchaseNo = purchaseNo;
		this.remark = remark;
		this.requestType = requestType;
		this.screen = screen;
		this.status = status;
		this.store = store;
	}

	public void updatePurchaseRequest(Priority priority, String remark) {
		this.priority = priority;
		this.remark = remark;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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


	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public AssetType getAssetType() {
		return assetType;
	}

	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
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

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public String getPurchaseNo() {
		return purchaseNo;
	}

	public void setPurchaseNo(String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public RequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
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

	public List<PurchaseRequestDetail> getPurchaseRequestDetailList() {
		return purchaseRequestDetailList;
	}

	public void setPurchaseRequestDetailList(List<PurchaseRequestDetail> purchaseRequestDetailList) {
		this.purchaseRequestDetailList = purchaseRequestDetailList;
	}
	
	public QuotationRequest getQuotationRequest() {
		return quotationRequest;
	}

	public void setQuotationRequest(QuotationRequest quotationRequest) {
		this.quotationRequest = quotationRequest;
	}

	public PurchaseRequestDetail addPurchaseRequestDetailList(PurchaseRequestDetail purchaseRequestDetail) {
		if (getPurchaseRequestDetailList() == null)
			this.purchaseRequestDetailList = new ArrayList<PurchaseRequestDetail>();
		
		getPurchaseRequestDetailList().add(purchaseRequestDetail);
		purchaseRequestDetail.setPurchaseRequest(this);
		return purchaseRequestDetail;
	}

	public PurchaseRequestDetail removePurchaseRequestDetailList(PurchaseRequestDetail purchaseRequestDetail) {
		getPurchaseRequestDetailList().remove(purchaseRequestDetail);
		purchaseRequestDetail.setPurchaseRequest(null);
		return purchaseRequestDetail;
	}

}