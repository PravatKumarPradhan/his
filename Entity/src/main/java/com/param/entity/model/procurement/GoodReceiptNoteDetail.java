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
import com.param.entity.model.inventory.Batch;
import com.param.entity.model.master.Item;
import com.param.entity.model.master.Manufacturer;
import com.param.entity.model.master.RejectReason;
import com.param.entity.model.master.Status;
import com.param.entity.model.master.Tax;
import com.param.entity.model.master.UomType;
import com.param.entity.model.master.UomUnit;
import com.param.entity.model.master.User;

@Entity(name = "GoodReceiptNoteDetail")
@Table(name = "t_good_receipt_note_detail", schema = "procurement")
public class GoodReceiptNoteDetail extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "batch_no")
	private String batchNo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approved_by")
	private User approvedBy;

	@Column(name = "approved_date")
	private Date approvedDate;

	@Column(name = "grn_approved_quantity")
	private Integer grnApprovedQuantity;

	@Column(name = "bonus_approved_quantity")
	private Integer bonusApprovedQuantity;

	@Column(name = "grn_received_quantity")
	private Integer grnReceivedQuantity;

	@Column(name = "bonus_received_quantity")
	private Integer bonusReceivedQuantity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bonus_uom_type_id")
	private UomType bonusUomType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bonus_uom_unit_id")
	private UomUnit bonusUomUnit;

	private Double cop;

	private Double discount;

	@Temporal(TemporalType.DATE)
	@Column(name = "expiry_date")
	private Date expiryDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "grn_uom_type_id")
	private UomType grnUomType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "grn_uom_unit_id")
	private UomUnit grnUomUnit;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id", nullable = false)
	private Item item;

	@Column(name = "lot_number")
	private String lotNumber;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manufacturer_id")
	private Manufacturer manufacturer;

	private Double mrp;

	@Column(name = "net_amount")
	private Double netAmount;

	private String remark;

	private String specification;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private Status status;

	@Column(name = "tax_amount")
	private Double taxAmount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tax_id", nullable = false)
	private Tax tax;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "batch_id")
	private Batch batch;

	@Column(name = "tax_percentage")
	private Double taxPercentage;

	@Column(name = "total_amount")
	private Double totalAmount;

	@Column(name = "total_discount")
	private Double totalDiscount;

	@Column(name = "total_other_amount")
	private Double totalOtherAmount;

	@Column(name = "rejected_quantity")
	private Integer rejectedQuantity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reject_reason_id")
	private RejectReason rejectReason;

	@Column(name = "reject_note")
	private String rejectNote;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "grn_id")
	private GoodReceiptNote goodReceiptNote;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "po_detail_id")
	private PurchaseOrderDetail purchaseOrderDetail;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "goodReceiptNoteDetail", cascade = CascadeType.ALL)
	private List<GrnBonusUomMapper> grnBonusUomMappersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "goodReceiptNoteDetail", cascade = CascadeType.ALL)
	private List<GrnUomMapper> grnUomMappersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "goodReceiptNoteDetail", cascade = CascadeType.ALL)
	private List<GrnOtherChargesMapper> grnOtherChargesMapperList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "goodReceiptNoteDetail", cascade = CascadeType.ALL)
	private List<GRNStagedQuantity> grnStagedQuantityList;

	public GoodReceiptNoteDetail() {
		super();
	}

	public GoodReceiptNoteDetail(Integer id) {
		super();
		this.id = id;
	}

	public GoodReceiptNoteDetail(String batchNo, Integer grnReceivedQuantity, Integer bonusReceivedQuantity,
			UomType bonusUomType, UomUnit bonusUomUnit, Double cop, Double discount, Date expiryDate,
			UomType grnUomType, UomUnit grnUomUnit, Item item, String lotNumber, Manufacturer manufacturer, Double mrp,
			Double netAmount, String remark, String specification, Status status, Double taxAmount, Tax tax,
			Double taxPercentage, Double totalAmount, Double totalDiscount, Double totalOtherAmount) {
		super();
		this.batchNo = batchNo;
		this.grnReceivedQuantity = grnReceivedQuantity;
		this.bonusReceivedQuantity = bonusReceivedQuantity;
		this.bonusUomType = bonusUomType;
		this.bonusUomUnit = bonusUomUnit;
		this.cop = cop;
		this.discount = discount;
		this.expiryDate = expiryDate;
		this.grnUomType = grnUomType;
		this.grnUomUnit = grnUomUnit;
		this.item = item;
		this.lotNumber = lotNumber;
		this.manufacturer = manufacturer;
		this.mrp = mrp;
		this.netAmount = netAmount;
		this.remark = remark;
		this.specification = specification;
		this.status = status;
		this.taxAmount = taxAmount;
		this.tax = tax;
		this.taxPercentage = taxPercentage;
		this.totalAmount = totalAmount;
		this.totalDiscount = totalDiscount;
		this.totalOtherAmount = totalOtherAmount;
	}

	public void UpdateGoodReceiptNoteDetail(String batchNo, Integer grnReceivedQuantity, Integer bonusReceivedQuantity,
			UomType bonusUomType, UomUnit bonusUomUnit, Double cop, Double discount, Date expiryDate,
			UomType grnUomType, UomUnit grnUomUnit, Double mrp, Double netAmount, String remark, String specification,
			Double taxAmount, Tax tax, Double taxPercentage, Double totalAmount, Double totalDiscount,
			Double totalOtherAmount) {
		this.batchNo = batchNo;
		this.grnReceivedQuantity = grnReceivedQuantity;
		this.bonusReceivedQuantity = bonusReceivedQuantity;
		this.bonusUomType = bonusUomType;
		this.bonusUomUnit = bonusUomUnit;
		this.cop = cop;
		this.discount = discount;
		this.expiryDate = expiryDate;
		this.grnUomType = grnUomType;
		this.grnUomUnit = grnUomUnit;
		this.mrp = mrp;
		this.netAmount = netAmount;
		this.remark = remark;
		this.specification = specification;
		this.taxAmount = taxAmount;
		this.tax = tax;
		this.taxPercentage = taxPercentage;
		this.totalAmount = totalAmount;
		this.totalDiscount = totalDiscount;
		this.totalOtherAmount = totalOtherAmount;
	}

	public void approveGoodReceiptNoteDetail(User approvedBy, Date approvedDate, Integer grnApprovedQuantity,
			Integer bonusApprovedQuantity, Integer rejectedQuantity, RejectReason rejectReason, String rejectNote,
			Status status) {
		this.approvedBy = approvedBy;
		this.approvedDate = approvedDate;
		this.grnApprovedQuantity = grnApprovedQuantity;
		this.bonusApprovedQuantity = bonusApprovedQuantity;
		this.rejectedQuantity = rejectedQuantity;
		this.rejectReason = rejectReason;
		this.rejectNote = rejectNote;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
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

	public Integer getGrnReceivedQuantity() {
		return grnReceivedQuantity;
	}

	public void setGrnReceivedQuantity(Integer grnReceivedQuantity) {
		this.grnReceivedQuantity = grnReceivedQuantity;
	}

	public Integer getBonusReceivedQuantity() {
		return bonusReceivedQuantity;
	}

	public void setBonusReceivedQuantity(Integer bonusReceivedQuantity) {
		this.bonusReceivedQuantity = bonusReceivedQuantity;
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

	public Double getCop() {
		return cop;
	}

	public void setCop(Double cop) {
		this.cop = cop;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public UomType getGrnUomType() {
		return grnUomType;
	}

	public void setGrnUomType(UomType grnUomType) {
		this.grnUomType = grnUomType;
	}

	public UomUnit getGrnUomUnit() {
		return grnUomUnit;
	}

	public void setGrnUomUnit(UomUnit grnUomUnit) {
		this.grnUomUnit = grnUomUnit;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getLotNumber() {
		return lotNumber;
	}

	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Double getMrp() {
		return mrp;
	}

	public void setMrp(Double mrp) {
		this.mrp = mrp;
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

	public Double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
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

	public Double getTotalOtherAmount() {
		return totalOtherAmount;
	}

	public void setTotalOtherAmount(Double totalOtherAmount) {
		this.totalOtherAmount = totalOtherAmount;
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

	public GoodReceiptNote getGoodReceiptNote() {
		return goodReceiptNote;
	}

	public void setGoodReceiptNote(GoodReceiptNote goodReceiptNote) {
		this.goodReceiptNote = goodReceiptNote;
	}

	public List<GrnBonusUomMapper> getGrnBonusUomMappersList() {
		return grnBonusUomMappersList;
	}

	public void setGrnBonusUomMappersList(List<GrnBonusUomMapper> grnBonusUomMappersList) {
		this.grnBonusUomMappersList = grnBonusUomMappersList;
	}

	public List<GrnUomMapper> getGrnUomMappersList() {
		return grnUomMappersList;
	}

	public void setGrnUomMappersList(List<GrnUomMapper> grnUomMappersList) {
		this.grnUomMappersList = grnUomMappersList;
	}

	public List<GrnOtherChargesMapper> getGrnOtherChargesMapperList() {
		return grnOtherChargesMapperList;
	}

	public void setGrnOtherChargesMapperList(List<GrnOtherChargesMapper> grnOtherChargesMapperList) {
		this.grnOtherChargesMapperList = grnOtherChargesMapperList;
	}

	public List<GRNStagedQuantity> getGrnStagedQuantityList() {
		return grnStagedQuantityList;
	}

	public void setGrnStagedQuantityList(List<GRNStagedQuantity> grnStagedQuantityList) {
		this.grnStagedQuantityList = grnStagedQuantityList;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public Integer getGrnApprovedQuantity() {
		return grnApprovedQuantity;
	}

	public void setGrnApprovedQuantity(Integer grnApprovedQuantity) {
		this.grnApprovedQuantity = grnApprovedQuantity;
	}

	public Integer getBonusApprovedQuantity() {
		return bonusApprovedQuantity;
	}

	public void setBonusApprovedQuantity(Integer bonusApprovedQuantity) {
		this.bonusApprovedQuantity = bonusApprovedQuantity;
	}

	public PurchaseOrderDetail getPurchaseOrderDetail() {
		return purchaseOrderDetail;
	}

	public void setPurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail) {
		this.purchaseOrderDetail = purchaseOrderDetail;
	}

	public GrnUomMapper addGRNUomMapperList(GrnUomMapper grnUomMapper) {

		if (getGrnUomMappersList() == null)
			this.grnUomMappersList = new ArrayList<GrnUomMapper>();
		getGrnUomMappersList().add(grnUomMapper);
		grnUomMapper.setGoodReceiptNoteDetail(this);

		return grnUomMapper;
	}

	public GrnBonusUomMapper addGrnBonusUomMapperList(GrnBonusUomMapper grnBonusUomMapper) {
		if (getGrnBonusUomMappersList() == null)
			this.grnBonusUomMappersList = new ArrayList<GrnBonusUomMapper>();

		getGrnBonusUomMappersList().add(grnBonusUomMapper);
		grnBonusUomMapper.setGoodReceiptNoteDetail(this);

		return grnBonusUomMapper;
	}

	public GrnOtherChargesMapper addGRNOtherChargesMapperList(GrnOtherChargesMapper grnOtherChargesMapper) {
		if (getGrnOtherChargesMapperList() == null)
			this.grnOtherChargesMapperList = new ArrayList<GrnOtherChargesMapper>();

		getGrnOtherChargesMapperList().add(grnOtherChargesMapper);
		grnOtherChargesMapper.setGoodReceiptNoteDetail(this);

		return grnOtherChargesMapper;
	}

	public GRNStagedQuantity addGRNStagedQuantityList(GRNStagedQuantity grnStagedQuantity) {
		if (getGrnStagedQuantityList() == null)
			this.grnStagedQuantityList = new ArrayList<GRNStagedQuantity>();

		getGrnStagedQuantityList().add(grnStagedQuantity);
		grnStagedQuantity.setGoodReceiptNoteDetail(this);

		return grnStagedQuantity;
	}

}