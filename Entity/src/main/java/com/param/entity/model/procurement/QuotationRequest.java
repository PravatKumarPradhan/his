package com.param.entity.model.procurement;

import java.io.Serializable;
import javax.persistence.*;

import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.master.AssetType;
import com.param.entity.model.master.CancelReason;
import com.param.entity.model.master.RequestType;
import com.param.entity.model.master.Status;
import com.param.entity.model.master.Store;
import com.param.entity.model.master.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the t_quotation_request database table.
 * 
 */
@Entity
@Table(name="t_quotation_request",schema="procurement")
public class QuotationRequest extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private Status status;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "store_id")
	private Store store;

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
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "request_type_id")
	private RequestType requestType;

	@Column(name="qr_no")
	private String qrNo;

	private String remark;
	
	@Column(name="cancel_note")
	private String cancelNote;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "cancel_reason_id")
	private CancelReason cancelReason;

	@Column(name="send_technical_approval")
	private Boolean sendTechnicalApproval;

	@Column(name="send_vendor_selection")
	private Boolean sendVendorSelection;
	
	@Column(name="request_for_technical_approval")
	private Boolean requestForTechnicalApproval;

	//bi-directional many-to-one association to TQuotationRequestDetail
	@OneToMany(fetch = FetchType.LAZY, mappedBy="quotationRequest", cascade = CascadeType.ALL)
	private List<QuotationRequestDetail> quotationRequestDetailsList;
	
	//bi-directional many-to-one association to TPurchaseRequest
	@OneToMany(fetch = FetchType.LAZY, mappedBy="quotationRequest", cascade = CascadeType.ALL)
	private List<PurchaseRequest> purchaseRequestList;

	
	public QuotationRequest() {
		super();
	}
	
	public QuotationRequest(Integer id) {
		this.id = id;
	}
	
	public QuotationRequest( Status status, Store store, Status approvalStatus, 
			AssetType assetType, RequestType requestType, String qrNo, String remark,
			Boolean sendTechnicalApproval, Boolean sendVendorSelection) {
		super();
		this.status = status;
		this.store = store;
		this.approvalStatus = approvalStatus;
		this.assetType = assetType;
		this.requestType = requestType;
		this.qrNo = qrNo;
		this.remark = remark;
		this.sendTechnicalApproval = sendTechnicalApproval;
		this.sendVendorSelection = sendVendorSelection;
	}

	public void updateQuotationRequest(String remark) {
		this.remark = remark;
		
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQrNo() {
		return this.qrNo;
	}

	public void setQrNo(String qrNo) {
		this.qrNo = qrNo;
	}

	public String getRemark() {
		return this.remark;
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

	public RequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}

	public Boolean getSendTechnicalApproval() {
		return this.sendTechnicalApproval;
	}

	public void setSendTechnicalApproval(Boolean sendTechnicalApproval) {
		this.sendTechnicalApproval = sendTechnicalApproval;
	}

	public Boolean getSendVendorSelection() {
		return this.sendVendorSelection;
	}

	public void setSendVendorSelection(Boolean sendVendorSelection) {
		this.sendVendorSelection = sendVendorSelection;
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

	public List<QuotationRequestDetail> getQuotationRequestDetailsList() {
		return quotationRequestDetailsList;
	}

	public void setQuotationRequestDetailsList(List<QuotationRequestDetail> quotationRequestDetailsList) {
		this.quotationRequestDetailsList = quotationRequestDetailsList;
	}

	public List<PurchaseRequest> getPurchaseRequestList() {
		return purchaseRequestList;
	}

	public void setPurchaseRequestList(List<PurchaseRequest> purchaseRequestList) {
		this.purchaseRequestList = purchaseRequestList;
	}
	
	public Boolean getRequestForTechnicalApproval() {
		return requestForTechnicalApproval;
	}

	public void setRequestForTechnicalApproval(Boolean requestForTechnicalApproval) {
		this.requestForTechnicalApproval = requestForTechnicalApproval;
	}

	public QuotationRequestDetail addQuotationRequestDetailList(QuotationRequestDetail quotationRequestDetail) {
		if (getQuotationRequestDetailsList() == null)
			this.quotationRequestDetailsList = new ArrayList<QuotationRequestDetail>();
		
		getQuotationRequestDetailsList().add(quotationRequestDetail);
		quotationRequestDetail.setQuotationRequest(this);
		
		return quotationRequestDetail;
	}

	public QuotationRequestDetail removeTQuotationRequestDetail(QuotationRequestDetail quotationRequestDetail) {
		getQuotationRequestDetailsList().remove(quotationRequestDetail);
		quotationRequestDetail.setQuotationRequest(this);
		return quotationRequestDetail;
	}

}