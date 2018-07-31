package com.param.entity.model.procurement;


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
import com.param.entity.model.inventory.StoreIndentDetail;
import com.param.entity.model.master.Item;
import com.param.entity.model.master.RejectReason;
import com.param.entity.model.master.Status;
import com.param.entity.model.master.UomType;
import com.param.entity.model.master.UomUnit;
import com.param.entity.model.master.User;

@Entity(name = "PurchaseRequestDetail")
@Table(name = "t_purchase_request_detail", schema = "procurement")
public class PurchaseRequestDetail extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@Column(name = "amount_cop", nullable = false)
	private double amountCop;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approved_by")
	private User approvedBy;

	@Column(name = "approved_date")
	private Date approvedDate;

	@Column(name = "approved_quantity")
	private Integer approvedQuantity;

	@Column(nullable = false)
	private double cop;

	@Column(name = "indent_quantity")
	private Integer indentQuantity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id", nullable = false)
	private Item item;

	@Column(nullable = false)
	private Integer quantity;
	
	@Column(name = "reject_note", length = 100)
	private String rejectNote;

	@Column(name = "reject_qty")
	private Integer rejectQty;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reject_reason_id")
	private RejectReason rejectReason;

	@Column(length = 500)
	private String specification;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id", nullable = false)
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uom_type_id")
	private UomType uomType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uom_unit_id")
	private UomUnit uomUnit;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "purchase_request_id", nullable = false)
	private PurchaseRequest purchaseRequest;	
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "po_detail_id")
	private PurchaseOrderDetail purchaseOrderDetail;
	
	//bi-directional many-to-one association to TQuotationRequestDetail
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="qr_detail_id")
	private QuotationRequestDetail quotationRequestDetail;

	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "purchaseRequestDetail", cascade = CascadeType.ALL)
	private List<StoreIndentDetail> storeIndentDetailList;
	

	public PurchaseRequestDetail() {
	}
	
	public PurchaseRequestDetail(Integer quantity, Item item, Integer indentQuantity, Status status,double cop,double amountCop,
			UomType uomType, UomUnit uomUnit,String specification) {
		super();
		this.quantity = quantity;
		this.item = item;
		this.indentQuantity = indentQuantity;
		this.status = status;
		this.cop = cop;
		this.amountCop = amountCop;
		this.uomType = uomType;
		this.uomUnit = uomUnit;
		this.specification = specification;
	}
	
	
	public void updatePurchaseRequestDetail(Item item, Integer quantity, double cop,double amountCop,UomType uomType, UomUnit uomUnit,String specification) {
		this.item = item;
		this.uomType = uomType;
		this.uomUnit = uomUnit;
		this.quantity = quantity;
		this.cop = cop;
		this.amountCop = amountCop;
		this.specification = specification;
	}
	
	public void approveLocalPurchaseRequestDetail(Integer approvedQuantity, User approvedBy, Date approvedDate, String rejectNote,
			Integer rejectQty,RejectReason rejectReason, Status status, Date updatedDate,
			User updatedBy) {
		this.approvedQuantity = approvedQuantity;
		this.approvedBy = approvedBy;
		this.approvedDate = approvedDate;
		this.rejectNote = rejectNote;
		this.rejectQty = rejectQty;
		this.rejectReason = rejectReason;
		this.status = status;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
	}

	public PurchaseRequestDetail(Item item, Integer quantity, String specification, Status status, UomType uomType, UomUnit uomUnit) {
		this.item = item;
		this.quantity = quantity;
		this.specification = specification;
		this.status = status;
		this.uomType = uomType;
		this.uomUnit = uomUnit;
	}

	public PurchaseRequestDetail(Item item, Integer approvedQuantity, Integer rejectQty, RejectReason rejectReason, Status status) {
		this.item = item;
		this.approvedQuantity = approvedQuantity;
		this.rejectQty = rejectQty;
		this.rejectReason = rejectReason;
		this.status = status;
	}

	public void updatePurchaseRequestDetail(Item item, Integer quantity, String specification, Status status, UomType uomType, UomUnit uomUnit) {
		this.item = item;
		this.quantity = quantity;
		this.specification = specification;
		this.status = status;
		this.uomType = uomType;
		this.uomUnit = uomUnit;
	}

	public void updatePurchaseRequestDetail(Item item, Integer approvedQuantity, Integer rejectQty, RejectReason rejectReason, Status status) {
		this.item = item;
		this.approvedQuantity = approvedQuantity;
		this.rejectQty = rejectQty;
		this.rejectReason = rejectReason;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getAmountCop() {
		return amountCop;
	}

	public void setAmountCop(double amountCop) {
		this.amountCop = amountCop;
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

	public Integer getApprovedQuantity() {
		return approvedQuantity;
	}

	public void setApprovedQuantity(Integer approvedQuantity) {
		this.approvedQuantity = approvedQuantity;
	}

	public double getCop() {
		return cop;
	}

	public void setCop(double cop) {
		this.cop = cop;
	}

	public Integer getIndentQuantity() {
		return indentQuantity;
	}

	public void setIndentQuantity(Integer indentQuantity) {
		this.indentQuantity = indentQuantity;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getRejectQty() {
		return rejectQty;
	}

	public void setRejectQty(Integer rejectQty) {
		this.rejectQty = rejectQty;
	}

	public RejectReason getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(RejectReason rejectReason) {
		this.rejectReason = rejectReason;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public UomType getUomType() {
		return uomType;
	}

	public void setUomType(UomType uomType) {
		this.uomType = uomType;
	}

	public UomUnit getUomUnit() {
		return uomUnit;
	}

	public void setUomUnit(UomUnit uomUnit) {
		this.uomUnit = uomUnit;
	}

	public PurchaseRequest getPurchaseRequest() {
		return purchaseRequest;
	}

	public void setPurchaseRequest(PurchaseRequest purchaseRequest) {
		this.purchaseRequest = purchaseRequest;
	}

	public String getRejectNote() {
		return rejectNote;
	}

	public void setRejectNote(String rejectNote) {
		this.rejectNote = rejectNote;
	}
	
	public PurchaseOrderDetail getPurchaseOrderDetail() {
		return purchaseOrderDetail;
	}

	public void setPurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail) {
		this.purchaseOrderDetail = purchaseOrderDetail;
	}

	public List<StoreIndentDetail> getStoreIndentDetailList() {
		return storeIndentDetailList;
	}

	public void setStoreIndentDetailList(List<StoreIndentDetail> storeIndentDetailList) {
		this.storeIndentDetailList = storeIndentDetailList;
	}
	
	public QuotationRequestDetail getQuotationRequestDetail() {
		return quotationRequestDetail;
	}

	public void setQuotationRequestDetail(QuotationRequestDetail quotationRequestDetail) {
		this.quotationRequestDetail = quotationRequestDetail;
	}

	public StoreIndentDetail addStoreIndentDetailList(StoreIndentDetail storeIndentDetail) {
		if (getStoreIndentDetailList() == null)
			this.storeIndentDetailList = new ArrayList<StoreIndentDetail>();

		getStoreIndentDetailList().add(storeIndentDetail);
		storeIndentDetail.setPurchaseRequestDetail(this);

		return storeIndentDetail;
	}

	public StoreIndentDetail removeStoreIndentDetailList(StoreIndentDetail storeIndentDetail) {
		getStoreIndentDetailList().remove(storeIndentDetail);
		storeIndentDetail.setPurchaseRequestDetail(null);

		return storeIndentDetail;
	}

}