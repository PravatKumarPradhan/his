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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.master.Item;
import com.param.entity.model.master.Tax;
import com.param.entity.model.master.UomUnit;
import com.param.entity.model.master.Vendor;
import com.param.entity.model.procurement.GoodReceiptNoteDetail;
import com.param.entity.model.procurement.ShelfItem;

@Entity(name = "Batch")
@Table(name = "t_batch", schema = "inventory")
public class Batch extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(name = "actual_lending_price")
	private Double actualLendingPrice;

	@Column(length = 100)
	private String barcode;

	@Column(name = "batch_no", length = 100)
	private String batchNo;

	private Double cop;

	@Column(name = "current_quantity")
	private Integer currentQuantity;

	@Temporal(TemporalType.DATE)
	@Column(name = "expiry_date")
	private Date expiryDate;

	@Column(name = "is_opb")
	private Boolean isOpb;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;

	@Column(name = "lending_price")
	private Double lendingPrice;

	@Column(name = "lot_no", length = 100)
	private String lotNo;

	private Double markup;

	private Integer quantity;

	private Double rate;

	@Column(name = "selling_price")
	private Double sellingPrice;

	@Column(name = "tax_amount")
	private Double taxAmount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tax_id")
	private Tax tax;

	@Column(name = "tax_percentage")
	private Double taxPercentage;

	@Column(name = "barcode_print_count")
	private Integer barcodePrintCount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uom_unit_id")
	private UomUnit uomUnit;

	@Column(name = "is_quarantine", nullable = false)
	private Boolean isQuarantine = false;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vendor_id")
	private Vendor vendor;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "batch", cascade = CascadeType.ALL)
	private List<StoreStock> storeStockList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "batch", cascade = CascadeType.ALL)
	private List<BatchUomMapper> batchUomList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "batch", cascade = CascadeType.ALL)
	private List<StockTransaction> stockTransactionList;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "batch")
	private GoodReceiptNoteDetail goodReceiptNoteDetail;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "batch", cascade = CascadeType.ALL)
	private List<ShelfItem> shelfItemList;

	public Batch() {
		super();
	}

	public Batch(Long id) {
		this.id = id;
	}

	public Batch(Long id, Integer currentQuantity) {
		this(id);
		this.currentQuantity = currentQuantity;
	}

	public Batch(Double actualLendingPrice, String batchNo, Double cop, Integer currentQuantity, Date expiryDate,
			Item item, Double lendingPrice, String lotNo, Double markup, Integer quantity, Double rate,
			Double sellingPrice, Double taxAmount, Tax tax, Double taxPercentage, UomUnit uomUnit, Boolean isOpb) {
		this.actualLendingPrice = actualLendingPrice;
		this.batchNo = batchNo;
		this.cop = cop;
		this.currentQuantity = currentQuantity;
		this.expiryDate = expiryDate;
		this.item = item;
		this.lendingPrice = lendingPrice;
		this.lotNo = lotNo;
		this.markup = markup;
		this.quantity = quantity;
		this.rate = rate;
		this.sellingPrice = sellingPrice;
		this.taxAmount = taxAmount;
		this.tax = tax;
		this.taxPercentage = taxPercentage;
		this.uomUnit = uomUnit;
		this.isOpb = isOpb;
	}

	public Batch(Double actualLendingPrice, String batchNo, Double cop, Integer currentQuantity, Date expiryDate,
			Item item, Double lendingPrice, String lotNo, Double markup, Integer quantity, Double rate,
			Double sellingPrice, Double taxAmount, Tax tax, Double taxPercentage, UomUnit uomUnit, Boolean isOpb,Vendor vendor) {
		this(actualLendingPrice,  batchNo,  cop,  currentQuantity,  expiryDate,
				 item, lendingPrice, lotNo,  markup,  quantity,  rate,
				 sellingPrice,  taxAmount, tax, taxPercentage, uomUnit, isOpb);
		this.vendor = vendor;
	}
	
	
	
	public void updateBatchChange(String batchNo, Date expiryDate, Double rate) {
		this.batchNo = batchNo;
		this.expiryDate = expiryDate;
		this.sellingPrice = rate;
		this.rate = rate;
		this.updatedDate = new Date();
		this.updatedBy = this.getUser();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getActualLendingPrice() {
		return actualLendingPrice;
	}

	public void setActualLendingPrice(Double actualLendingPrice) {
		this.actualLendingPrice = actualLendingPrice;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Double getCop() {
		return cop;
	}

	public void setCop(Double cop) {
		this.cop = cop;
	}

	public Integer getCurrentQuantity() {
		return currentQuantity;
	}

	public void setCurrentQuantity(Integer currentQuantity) {
		this.currentQuantity = currentQuantity;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Boolean getIsOpb() {
		return isOpb;
	}

	public void setIsOpb(Boolean isOpb) {
		this.isOpb = isOpb;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Double getLendingPrice() {
		return lendingPrice;
	}

	public void setLendingPrice(Double lendingPrice) {
		this.lendingPrice = lendingPrice;
	}

	public String getLotNo() {
		return lotNo;
	}

	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}

	public Double getMarkup() {
		return markup;
	}

	public void setMarkup(Double markup) {
		this.markup = markup;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
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

	public UomUnit getUomUnit() {
		return uomUnit;
	}

	public void setUomUnit(UomUnit uomUnit) {
		this.uomUnit = uomUnit;
	}

	public List<StoreStock> getStoreStockList() {
		return storeStockList;
	}

	public void setStoreStockList(List<StoreStock> storeStockList) {
		this.storeStockList = storeStockList;
	}

	public List<BatchUomMapper> getBatchUomList() {
		return batchUomList;
	}

	public void setBatchUomList(List<BatchUomMapper> batchUomList) {
		this.batchUomList = batchUomList;
	}

	public Integer getBarcodePrintCount() {
		return barcodePrintCount;
	}

	public void setBarcodePrintCount(Integer barcodePrintCount) {
		this.barcodePrintCount = barcodePrintCount;
	}

	public Boolean isQuarantine() {
		return isQuarantine;
	}

	public void setQuarantine(Boolean isQuarantine) {
		this.isQuarantine = isQuarantine;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public BatchUomMapper addBatchUom(BatchUomMapper batchUomMapper) {
		if (this.batchUomList == null)
			this.batchUomList = new ArrayList<BatchUomMapper>();

		this.batchUomList.add(batchUomMapper);
		batchUomMapper.setBatch(this);

		return batchUomMapper;
	}

	public StoreStock addStoreStock(StoreStock storeStock) {
		if (this.storeStockList == null)
			this.storeStockList = new ArrayList<StoreStock>();

		this.storeStockList.add(storeStock);
		storeStock.setBatch(this);

		return storeStock;
	}

	public List<StockTransaction> getStockTransactionList() {
		return stockTransactionList;
	}

	public void setStockTransactionList(List<StockTransaction> stockTransactionList) {
		this.stockTransactionList = stockTransactionList;
	}

	public GoodReceiptNoteDetail getGoodReceiptNoteDetail() {
		return goodReceiptNoteDetail;
	}

	public void setGoodReceiptNoteDetail(GoodReceiptNoteDetail goodReceiptNoteDetail) {
		this.goodReceiptNoteDetail = goodReceiptNoteDetail;
	}

	public List<ShelfItem> getShelfItemList() {
		return shelfItemList;
	}

	public void setShelfItemList(List<ShelfItem> shelfItemList) {
		this.shelfItemList = shelfItemList;
	}
	
	public StockTransaction addStockTransaction(StockTransaction stockTransaction) {
		if (this.stockTransactionList == null)
			this.stockTransactionList = new ArrayList<StockTransaction>();

		this.stockTransactionList.add(stockTransaction);
		stockTransaction.setBatch(this);

		return stockTransaction;
	}

	public ShelfItem addShelfItem(ShelfItem shelfItem) {
		if (this.shelfItemList == null)
			this.shelfItemList = new ArrayList<ShelfItem>();

		this.shelfItemList.add(shelfItem);
		shelfItem.setBatch(this);

		return shelfItem;
	}
}