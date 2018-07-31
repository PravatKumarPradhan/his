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
import com.param.entity.model.master.Store;
import com.param.entity.model.master.User;
import com.param.entity.model.master.Vendor;

/**
 * The persistent class for the t_quotation_fill database table.
 * 
 */
@Entity
@Table(name = "t_quotation_fill", schema = "procurement")
public class QuotationFill extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "asset_type_id")
	private AssetType assetType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
	private Store store;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approved_status_id")
	private Status approvalStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approved_by")
	private User approvedBy;

	@Column(name = "approval_date")
	private Date approvedDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "qr_id")
	private QuotationRequest quotationRequest;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vq_id")
	private QuotationVendor quotationVendor;

	@Column(name = "cancel_note")
	private String cancelNote;

	@Column(name = "reference_no")
	private String refNumber;

	@Column(name = "reference_date")
	private Date refDate;

	private String remark;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cancel_reason_id")
	private CancelReason cancelReason;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vendor_id")
	private Vendor vendor;

	// bi-directional many-to-one association to TQuotationFillDetail
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "quotationFill", cascade = CascadeType.ALL)
	private List<QuotationFillDetail> quotationFillDetailList;
	
	public QuotationFill() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuotationFill(Integer id) {
		super();
		this.id = id ;
	}

	public QuotationFill(AssetType assetType, Status status, Store store, Status approvalStatus,
			QuotationRequest quotationRequest, QuotationVendor quotationVendor, String remark, String refNumber,
			Date refDate, Vendor vendor) {
		super();
		this.assetType = assetType;
		this.status = status;
		this.store = store;
		this.approvalStatus = approvalStatus;
		this.quotationRequest = quotationRequest;
		this.quotationVendor = quotationVendor;
		this.remark = remark;
		this.refNumber = refNumber;
		this.refDate = refDate;
		this.vendor = vendor;
	}


	public void updateQuotationFill(String remark) {
		this.remark = remark;
	}
	
	public AssetType getAssetType() {
		return assetType;
	}

	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}

	public String getRefNumber() {
		return refNumber;
	}

	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}

	public Date getRefDate() {
		return refDate;
	}

	public void setRefDate(Date refDate) {
		this.refDate = refDate;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public QuotationRequest getQuotationRequest() {
		return quotationRequest;
	}

	public void setQuotationRequest(QuotationRequest quotationRequest) {
		this.quotationRequest = quotationRequest;
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

	public QuotationVendor getQuotationVendor() {
		return quotationVendor;
	}

	public void setQuotationVendor(QuotationVendor quotationVendor) {
		this.quotationVendor = quotationVendor;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<QuotationFillDetail> getQuotationFillDetailList() {
		return quotationFillDetailList;
	}

	public void setQuotationFillDetailList(List<QuotationFillDetail> quotationFillDetailList) {
		this.quotationFillDetailList = quotationFillDetailList;
	}

	public QuotationFillDetail addQuotationFillDetailList(QuotationFillDetail quotationFillDetail) {
		if (getQuotationFillDetailList() == null)
			this.quotationFillDetailList = new ArrayList<QuotationFillDetail>();

		getQuotationFillDetailList().add(quotationFillDetail);
		quotationFillDetail.setQuotationFill(this);

		return quotationFillDetail;
	}

	public QuotationFillDetail removeQuotationFillDetail(QuotationFillDetail quotationFillDetail) {
		getQuotationFillDetailList().remove(quotationFillDetail);
		quotationFillDetail.setQuotationFill(this);
		return quotationFillDetail;
	}

}