package com.param.entity.model.procurement;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.master.FixedAsset;
import com.param.entity.model.master.Item;
import com.param.entity.model.master.Manufacturer;
import com.param.entity.model.master.RejectReason;
import com.param.entity.model.master.Status;
import com.param.entity.model.master.User;
import com.param.entity.model.master.Vendor;

@Entity(name = "ItemRequestDetail")
@Table(name = "t_item_request_detail", schema = "procurement")
public class ItemRequestDetail extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_request_id")
	private ItemRequest itemRequest;

	@Column(name = "item_name", nullable = false)
	private String itemName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manufacturer_id")
	private Manufacturer manufacturer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vendor_id")
	private Vendor vendor;

	@Column(name = "expected_price")
	private Double expectedPrice;

	@Column(name = "reason_item_creation")
	private String itemCreationReason;

	@Column(name = "temp_manufacturer")
	private String manufactureTemp;

	@Column(name = "temp_vendor")
	private String vendorTemp;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private Status status;

	@Column(name = "reject_note", length = 100)
	private String rejectNote;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reject_reason_id")
	private RejectReason rejectReason;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "asset_id")
	private FixedAsset fixedAsset;

	public ItemRequestDetail() {
		super();
	}

	public ItemRequestDetail(Status status) {
		super();
		this.status = status;
	}

	public ItemRequestDetail(String itemName, Double expectedPrice, String itemCreationReason, String manufactureTemp,
			String vendorTemp, Status status) {
		super();
		this.itemName = itemName;
		this.expectedPrice = expectedPrice;
		this.itemCreationReason = itemCreationReason;
		this.manufactureTemp = manufactureTemp;
		this.vendorTemp = vendorTemp;
		this.status = status;
	}

	public ItemRequestDetail(String itemName, Manufacturer manufacturer, Vendor vendor, Double expectedPrice,
			String itemCreationReason, Status status) {
		super();
		this.itemName = itemName;
		this.manufacturer = manufacturer;
		this.vendor = vendor;
		this.expectedPrice = expectedPrice;
		this.itemCreationReason = itemCreationReason;
		this.status = status;
	}

	public void updateItemRequestDetail(Status status) {
		this.status = status;
	}

	public void updateItemRequestDetail(String itemName, Manufacturer manufacturer, Vendor vendor, Double expectedPrice,
			String itemCreationReason, String manufactureTemp, String vendorTemp) {
		this.itemName = itemName;
		this.manufacturer = manufacturer;
		this.vendor = vendor;
		this.expectedPrice = expectedPrice;
		this.itemCreationReason = itemCreationReason;
		this.manufactureTemp = manufactureTemp;
		this.vendorTemp = vendorTemp;

	}

	public void approveItemRequestDetail(String rejectNote, RejectReason rejectReason, Status status, Date updatedDate,
			User updatedUser) {
		this.rejectNote = rejectNote;
		this.rejectReason = rejectReason;
		this.status = status;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedUser;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ItemRequest getItemRequest() {
		return itemRequest;
	}

	public void setItemRequest(ItemRequest itemRequest) {
		this.itemRequest = itemRequest;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public Double getExpectedPrice() {
		return expectedPrice;
	}

	public void setExpectedPrice(Double expectedPrice) {
		this.expectedPrice = expectedPrice;
	}

	public String getItemCreationReason() {
		return itemCreationReason;
	}

	public void setItemCreationReason(String itemCreationReason) {
		this.itemCreationReason = itemCreationReason;
	}

	public String getManufactureTemp() {
		return manufactureTemp;
	}

	public void setManufactureTemp(String manufactureTemp) {
		this.manufactureTemp = manufactureTemp;
	}

	public String getVendorTemp() {
		return vendorTemp;
	}

	public void setVendorTemp(String vendorTemp) {
		this.vendorTemp = vendorTemp;
	}

	public String getRejectNote() {
		return rejectNote;
	}

	public void setRejectNote(String rejectNote) {
		this.rejectNote = rejectNote;
	}

	public RejectReason getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(RejectReason rejectReason) {
		this.rejectReason = rejectReason;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public FixedAsset getFixedAsset() {
		return fixedAsset;
	}

	public void setFixedAsset(FixedAsset fixedAsset) {
		this.fixedAsset = fixedAsset;
	}

}
