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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.master.AssetType;
import com.param.entity.model.master.CancelReason;
import com.param.entity.model.master.DiscountType;
import com.param.entity.model.master.RequestType;
import com.param.entity.model.master.Status;
import com.param.entity.model.master.Store;
import com.param.entity.model.master.User;
import com.param.entity.model.master.Vendor;

@Entity(name = "GoodReceiptNote")
@Table(name = "t_good_receipt_note", schema = "procurement")
public class GoodReceiptNote extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approve_status_id")
	private Status approvalStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approved_by")
	private User approvedBy;

	@Column(name = "approved_date")
	private Date approvedDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "asset_type_id")
	private AssetType assetType;

	@Column(name = "cancel_note")
	private String cancelNote;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cancel_reason_id")
	private CancelReason cancelReason;

	@Temporal(TemporalType.DATE)
	@Column(name = "dc_date")
	private Date dcDate;

	@Column(name = "dc_number")
	private String dcNumber;

	private Double discount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "discount_type_id")
	private DiscountType discountType;

	@Column(name = "grn_number")
	private String grnNumber;

	@Temporal(TemporalType.DATE)
	@Column(name = "invoice_date")
	private Date invoiceDate;

	@Column(name = "invoice_number")
	private String invoiceNumber;

	@Temporal(TemporalType.DATE)
	@Column(name = "inward_date")
	private Date inwardDate;

	@Column(name = "inward_number")
	private String inwardNumber;

	@Column(name = "is_consignment")
	private Boolean isConsignment;

	@Column(name = "net_amount")
	private Double netAmount;

	private String remark;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "request_id")
	private RequestType requestType;

	@Column(name = "round_off")
	private Double roundOff;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
	private Store store;

	@Column(name = "total_amount")
	private Double totalAmount;

	@Column(name = "total_discount")
	private Double totalDiscount;

	@Column(name = "total_other_amount")
	private Double totalOtherAmount;

	@Column(name = "total_tax_ammount")
	private Double totalTaxAmmount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vendor_id")
	private Vendor vendor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "po_id")
	private PurchaseOrder purchaseOrder;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "goodReceiptNote", cascade = CascadeType.ALL)
	private List<GoodReceiptNoteDetail> goodReceiptNoteDetailsList;

	public GoodReceiptNote() {
		super();
	}

	public GoodReceiptNote(Integer id) {
		super();
		this.id = id;
	}

	public GoodReceiptNote(Status approvalStatus, AssetType assetType, Date dcDate, String dcNumber, String grnNumber,
			Date invoiceDate, String invoiceNumber, Date inwardDate, String inwardNumber, Boolean isConsignment,
			Double netAmount, String remark, RequestType requestType, Double roundOff, Status status, Store store,
			Double totalAmount, Double totalDiscount, Double totalOtherAmount, Double totalTaxAmmount, Vendor vendor) {
		super();
		this.approvalStatus = approvalStatus;
		this.assetType = assetType;
		this.dcDate = dcDate;
		this.dcNumber = dcNumber;
		this.grnNumber = grnNumber;
		this.invoiceDate = invoiceDate;
		this.invoiceNumber = invoiceNumber;
		this.inwardDate = inwardDate;
		this.inwardNumber = inwardNumber;
		this.isConsignment = isConsignment;
		this.netAmount = netAmount;
		this.remark = remark;
		this.requestType = requestType;
		this.roundOff = roundOff;
		this.status = status;
		this.store = store;
		this.totalAmount = totalAmount;
		this.totalDiscount = totalDiscount;
		this.totalOtherAmount = totalOtherAmount;
		this.totalTaxAmmount = totalTaxAmmount;
		this.vendor = vendor;
	}

	public void updateGoodReceiptNote(Double discount, DiscountType discountType, Boolean isConsignment,
			Double netAmount, String remark, Double roundOff, Double totalAmount, Double totalDiscount,
			Double totalOtherAmount, Double totalTaxAmmount, Vendor vendor) {
		this.discount = discount;
		this.discountType = discountType;
		this.isConsignment = isConsignment;
		this.netAmount = netAmount;
		this.remark = remark;
		this.roundOff = roundOff;
		this.totalAmount = totalAmount;
		this.totalDiscount = totalDiscount;
		this.totalOtherAmount = totalOtherAmount;
		this.totalTaxAmmount = totalTaxAmmount;
		this.vendor = vendor;
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

	public Date getDcDate() {
		return dcDate;
	}

	public void setDcDate(Date dcDate) {
		this.dcDate = dcDate;
	}

	public String getDcNumber() {
		return dcNumber;
	}

	public void setDcNumber(String dcNumber) {
		this.dcNumber = dcNumber;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public DiscountType getDiscountType() {
		return discountType;
	}

	public void setDiscountType(DiscountType discountType) {
		this.discountType = discountType;
	}

	public String getGrnNumber() {
		return grnNumber;
	}

	public void setGrnNumber(String grnNumber) {
		this.grnNumber = grnNumber;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Date getInwardDate() {
		return inwardDate;
	}

	public void setInwardDate(Date inwardDate) {
		this.inwardDate = inwardDate;
	}

	public String getInwardNumber() {
		return inwardNumber;
	}

	public void setInwardNumber(String inwardNumber) {
		this.inwardNumber = inwardNumber;
	}

	public Boolean getIsConsignment() {
		return isConsignment;
	}

	public void setIsConsignment(Boolean isConsignment) {
		this.isConsignment = isConsignment;
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

	public Double getRoundOff() {
		return roundOff;
	}

	public void setRoundOff(Double roundOff) {
		this.roundOff = roundOff;
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

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(Double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public Double getTotalOtherAmount() {
		return totalOtherAmount;
	}

	public void setTotalOtherAmount(Double totalOtherAmount) {
		this.totalOtherAmount = totalOtherAmount;
	}

	public Double getTotalTaxAmmount() {
		return totalTaxAmmount;
	}

	public void setTotalTaxAmmount(Double totalTaxAmmount) {
		this.totalTaxAmmount = totalTaxAmmount;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public List<GoodReceiptNoteDetail> getGoodReceiptNoteDetailsList() {
		return goodReceiptNoteDetailsList;
	}

	public void setGoodReceiptNoteDetailsList(List<GoodReceiptNoteDetail> goodReceiptNoteDetailsList) {
		this.goodReceiptNoteDetailsList = goodReceiptNoteDetailsList;
	}

	public GoodReceiptNoteDetail addGoodReceiptNoteDetailList(GoodReceiptNoteDetail goodReceiptNoteDetail) {
		if (getGoodReceiptNoteDetailsList() == null)
			this.goodReceiptNoteDetailsList = new ArrayList<GoodReceiptNoteDetail>();

		getGoodReceiptNoteDetailsList().add(goodReceiptNoteDetail);
		goodReceiptNoteDetail.setGoodReceiptNote(this);

		return goodReceiptNoteDetail;
	}

	public GoodReceiptNoteDetail removeGoodReceiptNoteDetail(GoodReceiptNoteDetail goodReceiptNoteDetail) {
		getGoodReceiptNoteDetailsList().remove(goodReceiptNoteDetail);
		goodReceiptNoteDetail.setGoodReceiptNote(this);

		return goodReceiptNoteDetail;
	}

}