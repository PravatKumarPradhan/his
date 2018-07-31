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
import com.param.entity.model.master.Item;
import com.param.entity.model.master.Manufacturer;
import com.param.entity.model.master.RejectReason;
import com.param.entity.model.master.Status;
import com.param.entity.model.master.Tax;
import com.param.entity.model.master.UomType;
import com.param.entity.model.master.UomUnit;
import com.param.entity.model.master.User;

@Entity(name = "PurchaseOrderDetail")
@Table(name = "t_purchase_order_detail", schema = "procurement")
public class PurchaseOrderDetail extends BaseEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "purchase_order_id")
	private PurchaseOrder purchaseOrder;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id", nullable = false)
	private Item item;

	@Column(name = "po_quantity")
	private Integer poQuantity;

	@Column(name = "bonus_quantity")
	private Integer bonusQuantity;

	@Column(name = "cop")
	private Double cop;

	@Column(name = "mrp")
	private Double mrp;

	@Column(name = "discount")
	private Double discount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tax_id", nullable = false)
	private Tax tax;

	@Column(name = "tax_percentage")
	private Double taxPercentage;

	@Column(name = "total_amount")
	private Double totalAmount;
	
	@Column(name = "total_discount")
	private Double totalDiscount;

	@Column(name = "tax_amount")
	private Double taxAmount;

	@Column(name = "total_other_amount")
	private Double totalOtherAmount;

	@Column(name = "net_amount")
	private Double netAmount;	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manufacturer_id")
	private Manufacturer manufacturer;

	@Column(name = "specification")
	private String specification;

	@Column(name = "remark")
	private String remark;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "po_uom_type_id")
	private UomType poUomType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "po_uom_unit_id")
	private UomUnit poUomUnit;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bonus_uom_type_id")
	private UomType bonusUomType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bonus_uom_unit_id")
	private UomUnit bonusUomUnit;

	@Column(name = "po_approved_quantity")
	private Integer poApprovedQuantity;
	
	@Column(name = "bonus_approved_quantity")
	private Integer bonusApprovedQuantity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approved_by")
	private User approvedBy;
	
	@Column(name="approved_date")
	private Date approvedDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private Status status;
	
	@Column(name = "rejected_quantity")
	private Integer rejectedQuantity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reject_reason_id")
	private RejectReason rejectReason;
	
	@Column(name = "reject_note")
	private String rejectNote;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "purchaseOrderDetail", cascade = CascadeType.ALL)
	private List<PurchaseOrderUomMapper> purchaseOrderUomMapperList;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "purchaseOrderDetail", cascade = CascadeType.ALL)
	private List<BonusUomMapper> bonusUomMapperList;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "purchaseOrderDetail", cascade = CascadeType.ALL)
	private List<PurchaseOrderOtherChargesMapper> poOtherChargesList;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "purchaseOrderDetail", cascade = CascadeType.ALL)
	private List<PurchaseOrderStaged> purchaseOrderStagedList;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "purchaseOrderDetail", cascade = CascadeType.ALL)
	private List<PurchaseRequestDetail> purchaseRequestDetailList;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "purchaseOrderDetail", cascade = CascadeType.ALL)
	private List<GoodReceiptNoteDetail> goodReceiptNoteDetailList;

	public PurchaseOrderDetail() {
		super();
		
	}
	
	public PurchaseOrderDetail(Integer id) {
		this.id = id;
	}
	
	

	public PurchaseOrderDetail(Item item, Integer poQuantity, Integer bonusQuantity,
			Double cop, Double mrp, Double discount, Tax tax, Double taxPercentage, Double totalAmount,
			Double totalDiscount, Double taxAmount, Double totalOtherAmount, Double netAmount,
			Manufacturer manufacturer, String specification, String remark, UomType poUomType, UomUnit poUomUnit,
			UomType bonusUomType, UomUnit bonusUomUnit, Status status) {
		super();
		this.item = item;
		this.poQuantity = poQuantity;
		this.bonusQuantity = bonusQuantity;
		this.cop = cop;
		this.mrp = mrp;
		this.discount = discount;
		this.tax = tax;
		this.taxPercentage = taxPercentage;
		this.totalAmount = totalAmount;
		this.totalDiscount = totalDiscount;
		this.taxAmount = taxAmount;
		this.totalOtherAmount = totalOtherAmount;
		this.netAmount = netAmount;
		this.manufacturer = manufacturer;
		this.specification = specification;
		this.remark = remark;
		this.poUomType = poUomType;
		this.poUomUnit = poUomUnit;
		this.bonusUomType = bonusUomType;
		this.bonusUomUnit = bonusUomUnit;
		this.status = status;
	}
	
	 
	public void updatePurchaseOrderDetail(Item item, Integer poQuantity, Integer bonusQuantity,
			Double cop, Double mrp, Double discount, Double taxPercentage, Double totalAmount,
			Double totalDiscount, Double taxAmount, Double totalOtherAmount, Double netAmount,
			Manufacturer manufacturer, String specification, String remark, UomType poUomType, UomUnit poUomUnit,
			UomType bonusUomType, UomUnit bonusUomUnit) {
		
		this.item = item;
		this.poQuantity = poQuantity;
		this.bonusQuantity = bonusQuantity;
		this.cop = cop;
		this.mrp = mrp;
		this.discount = discount;
		this.taxPercentage = taxPercentage;
		this.totalAmount = totalAmount;
		this.totalDiscount = totalDiscount;
		this.taxAmount = taxAmount;
		this.totalOtherAmount = totalOtherAmount;
		this.netAmount = netAmount;
		this.manufacturer = manufacturer;
		this.specification = specification;
		this.remark = remark;
		this.poUomType = poUomType;
		this.poUomUnit = poUomUnit;
		this.bonusUomType = bonusUomType;
		this.bonusUomUnit = bonusUomUnit;
		
	}
	
	public void approvePurchaseOrderDetail( Integer poApprovedQuantity,
			Integer bonusApprovedQuantity, Integer rejectedQuantity, RejectReason rejectReason, String rejectNote,Status status,
			User approvedBy, Date approvedDate) {		
		this.poApprovedQuantity = poApprovedQuantity;
		this.bonusApprovedQuantity = bonusApprovedQuantity;		
		this.rejectedQuantity = rejectedQuantity;
		this.rejectReason = rejectReason;
		this.rejectNote = rejectNote;
		this.status = status;
		this.approvedBy = approvedBy;
		this.approvedDate = approvedDate;
		
		
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Integer getPoQuantity() {
		return poQuantity;
	}

	public void setPoQuantity(Integer poQuantity) {
		this.poQuantity = poQuantity;
	}

	public Integer getBonusQuantity() {
		return bonusQuantity;
	}

	public void setBonusQuantity(Integer bonusQuantity) {
		this.bonusQuantity = bonusQuantity;
	}

	public Double getCop() {
		return cop;
	}

	public void setCop(Double cop) {
		this.cop = cop;
	}

	public Double getMrp() {
		return mrp;
	}

	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Tax getTax() {
		return tax;
	}

	public void setTax(Tax tax) {
		this.tax = tax;
	}

	public Double getTaxPercentage() {
		return taxPercentage;
	}

	public void setTaxPercentage(Double taxPercentage) {
		this.taxPercentage = taxPercentage;
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

	public Double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public Double getTotalOtherAmount() {
		return totalOtherAmount;
	}

	public void setTotalOtherAmount(Double totalOtherAmount) {
		this.totalOtherAmount = totalOtherAmount;
	}

	public Double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public UomType getPoUomType() {
		return poUomType;
	}

	public void setPoUomType(UomType poUomType) {
		this.poUomType = poUomType;
	}

	public UomUnit getPoUomUnit() {
		return poUomUnit;
	}

	public void setPoUomUnit(UomUnit poUomUnit) {
		this.poUomUnit = poUomUnit;
	}

	public UomType getBonusUomType() {
		return bonusUomType;
	}

	public void setBonusUomType(UomType bonusUomType) {
		this.bonusUomType = bonusUomType;
	}

	public UomUnit getBonusUomUnit() {
		return bonusUomUnit;
	}

	public void setBonusUomUnit(UomUnit bonusUomUnit) {
		this.bonusUomUnit = bonusUomUnit;
	}

	public Integer getPoApprovedQuantity() {
		return poApprovedQuantity;
	}

	public void setPoApprovedQuantity(Integer poApprovedQuantity) {
		this.poApprovedQuantity = poApprovedQuantity;
	}

	public Integer getBonusApprovedQuantity() {
		return bonusApprovedQuantity;
	}

	public void setBonusApprovedQuantity(Integer bonusApprovedQuantity) {
		this.bonusApprovedQuantity = bonusApprovedQuantity;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getRejectedQuantity() {
		return rejectedQuantity;
	}

	public void setRejectedQuantity(Integer rejectedQuantity) {
		this.rejectedQuantity = rejectedQuantity;
	}

	public RejectReason getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(RejectReason rejectReason) {
		this.rejectReason = rejectReason;
	}
	public String getRejectNote() {
		return rejectNote;
	}
	public void setRejectNote(String rejectNote) {
		this.rejectNote = rejectNote;
	}
	public List<PurchaseOrderUomMapper> getPurchaseOrderUomMapperList() {
		return purchaseOrderUomMapperList;
	}
	public void setPurchaseOrderUomMapperList(List<PurchaseOrderUomMapper> purchaseOrderUomMapperList) {
		this.purchaseOrderUomMapperList = purchaseOrderUomMapperList;
	}
	public List<BonusUomMapper> getBonusUomMapperList() {
		return bonusUomMapperList;
	}
	public void setBonusUomMapperList(List<BonusUomMapper> bonusUomMapperList) {
		this.bonusUomMapperList = bonusUomMapperList;
	}
	public List<PurchaseOrderOtherChargesMapper> getPoOtherChargesList() {
		return poOtherChargesList;
	}
	public void setPoOtherChargesList(List<PurchaseOrderOtherChargesMapper> poOtherChargesList) {
		this.poOtherChargesList = poOtherChargesList;
	}
	
	public List<PurchaseRequestDetail> getPurchaseRequestDetailList() {
		return purchaseRequestDetailList;
	}

	public void setPurchaseRequestDetailList(List<PurchaseRequestDetail> purchaseRequestDetailList) {
		this.purchaseRequestDetailList = purchaseRequestDetailList;
	}
	
	public List<PurchaseOrderStaged> getPurchaseOrderStagedList() {
		return purchaseOrderStagedList;
	}
	public void setPurchaseOrderStagedList(List<PurchaseOrderStaged> purchaseOrderStagedList) {
		this.purchaseOrderStagedList = purchaseOrderStagedList;
	}

	public List<GoodReceiptNoteDetail> getGoodReceiptNoteDetailList() {
		return goodReceiptNoteDetailList;
	}
	public void setGoodReceiptNoteDetailList(List<GoodReceiptNoteDetail> goodReceiptNoteDetailList) {
		this.goodReceiptNoteDetailList = goodReceiptNoteDetailList;
	}

	public PurchaseOrderStaged addPurchaseOrderStaged(PurchaseOrderStaged purchaseOrderStaged) {
		if (getPurchaseOrderStagedList() == null)
			this.purchaseOrderStagedList = new ArrayList<PurchaseOrderStaged>();

		getPurchaseOrderStagedList().add(purchaseOrderStaged);
		purchaseOrderStaged.setPurchaseOrderDetail(this);
		return purchaseOrderStaged;
	}
	

	public PurchaseRequestDetail addPurchaseRequestDetailList(PurchaseRequestDetail purchaseRequestDetail) {
		if (getPurchaseRequestDetailList() == null)
			this.purchaseRequestDetailList = new ArrayList<PurchaseRequestDetail>();

		getPurchaseRequestDetailList().add(purchaseRequestDetail);
		purchaseRequestDetail.setPurchaseOrderDetail(this);

		return purchaseRequestDetail;
	}
	public PurchaseOrderUomMapper addPurchaseOrderUomMapperList(PurchaseOrderUomMapper purchaseOrderUomMapper) {
		if (getPurchaseOrderUomMapperList() == null)
			this.purchaseOrderUomMapperList = new ArrayList<PurchaseOrderUomMapper>();		
		getPurchaseOrderUomMapperList().add(purchaseOrderUomMapper);
		purchaseOrderUomMapper.setPurchaseOrderDetail(this);

		return purchaseOrderUomMapper;
	}
	
	public BonusUomMapper addBonusUomMapperList(BonusUomMapper bonusUomMapper) {
		if (getBonusUomMapperList() == null)
			this.bonusUomMapperList = new ArrayList<BonusUomMapper>();
		
		getBonusUomMapperList().add(bonusUomMapper);
		bonusUomMapper.setPurchaseOrderDetail(this);

		return bonusUomMapper;
	}
	
	public PurchaseOrderOtherChargesMapper addPoOtherChargesMapperList(PurchaseOrderOtherChargesMapper poOtherChargesMapper) {
		if (getPoOtherChargesList() == null)
			this.poOtherChargesList = new ArrayList<PurchaseOrderOtherChargesMapper>();
		
		getPoOtherChargesList().add(poOtherChargesMapper);
		poOtherChargesMapper.setPurchaseOrderDetail(this);

		return poOtherChargesMapper;
	}
	
	
}
