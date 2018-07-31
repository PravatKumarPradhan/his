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
import com.param.entity.model.master.AssetType;
import com.param.entity.model.master.CancelReason;
import com.param.entity.model.master.RequestType;
import com.param.entity.model.master.Status;
import com.param.entity.model.master.Store;
import com.param.entity.model.master.Vendor;

@Entity(name = "ReturnableGatepass")
@Table(name = "t_returnable_gatepass", schema = "inventory")
public class ReturnableGatepass extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approval_status_id", nullable = false)
	private Status approvalStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "asset_type_id", nullable = false)
	private AssetType assetType;

	@Column(name = "net_amount", nullable = false)
	private Double netAmount = 0.0;

	@Column(length = 500)
	private String remark;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "request_type_id", nullable = false)
	private RequestType requestType;

	@Column(name = "ret_gp_no", length = 100)
	private String retGpNo;
	
	@Column(name = "refrence_no", length = 100)
	private String referenceNo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id", nullable = false)
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id", nullable = false)
	private Store store;

	@Column(name = "total_amt", nullable = false)
	private Double totalAmt = 0.0;

	@Column(name = "total_tax_amount", nullable = false)
	private Double totalTaxAmount = 0.0;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vendor_id", nullable = false)
	private Vendor vendor;
	
	@Column(name = "cancel_note", length = 100)
	private String cancelNote;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cancel_reason_id")
	private CancelReason cancelReason;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "returnableGatepass", cascade = CascadeType.ALL)
	private List<ReturnableGatepassDetail> returnableGatepassDetails;

	public ReturnableGatepass() {
		super();
	}

	public ReturnableGatepass(String retGpNo, Store store, Vendor vendor, AssetType assetType, RequestType requestType,
			String referenceNo, Double totalAmt, Double totalTaxAmount, Double netAmount, Status status, Status approvalStatus,
			String remark) {
		super();
		this.approvalStatus = approvalStatus;
		this.assetType = assetType;
		this.netAmount = netAmount;
		this.remark = remark;
		this.requestType = requestType;
		this.retGpNo = retGpNo;
		this.referenceNo = referenceNo;
		this.status = status;
		this.store = store;
		this.totalAmt = totalAmt;
		this.totalTaxAmount = totalTaxAmount;
		this.vendor = vendor;
	}

	public void updateReturnableGatepass(Double totalAmt, Double totalTaxAmount, Double netAmount, String referenceNo, String remark ) {
		this.netAmount = netAmount;
		this.remark = remark;
		this.totalAmt = totalAmt;
		this.referenceNo = referenceNo;
		this.totalTaxAmount = totalTaxAmount;
		this.updatedBy = this.getUser();
		this.updatedDate = new Date();
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

	public Double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
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

	public String getRetGpNo() {
		return retGpNo;
	}

	public void setRetGpNo(String retGpNo) {
		this.retGpNo = retGpNo;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
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

	public Double getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(Double totalAmt) {
		this.totalAmt = totalAmt;
	}

	public Double getTotalTaxAmount() {
		return totalTaxAmount;
	}

	public void setTotalTaxAmount(Double totalTaxAmount) {
		this.totalTaxAmount = totalTaxAmount;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
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

	public List<ReturnableGatepassDetail> getReturnableGatepassDetails() {
		return returnableGatepassDetails;
	}

	public void setReturnableGatepassDetails(List<ReturnableGatepassDetail> returnableGatepassDetails) {
		this.returnableGatepassDetails = returnableGatepassDetails;
	}

	public ReturnableGatepassDetail addReturnableGatepassDetail(ReturnableGatepassDetail returnableGatepassDetail) {
		if (this.returnableGatepassDetails == null) {
			this.returnableGatepassDetails = new ArrayList<ReturnableGatepassDetail>();
		}

		this.returnableGatepassDetails.add(returnableGatepassDetail);
		returnableGatepassDetail.setReturnableGatepass(this);

		return returnableGatepassDetail;
	}

	public ReturnableGatepassDetail removeReturnableGatepassDetail(ReturnableGatepassDetail returnableGatepassDetail) {
		this.returnableGatepassDetails.remove(returnableGatepassDetail);
		returnableGatepassDetail.setReturnableGatepass(null);

		return returnableGatepassDetail;
	}

}