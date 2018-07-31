package com.param.entity.model.master;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.param.entity.model.base.BaseEntity;

@Entity(name="ConsumableBarcodeConfig")
@Table(name="m_consumable_barcode_config",schema="public")
public class ConsumableBarcodeConfig extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;
	
	@Column(nullable = false, length = 10)
	private Boolean itemName;

	@Column(nullable = false, length = 10)
	private Boolean itemCode;

	@Column(nullable = false, length = 10)
	private Boolean batchNo;
	
	@Column(nullable = false, length = 10)
	private Boolean expiryDate;
	
	@Column(nullable = false, length = 10)
	private Boolean lotNo;
	
	@Column(nullable = false, length = 10)
	private Boolean mrp;
	
	@Column(nullable = false, length = 10)
	private Boolean salePrice;
	
	@Column(nullable = false, length = 10)
	private Boolean grnNo;
	
	@Column(nullable = false, length = 10)
	private Boolean vendorName;
	
	@Column(nullable = false, length = 10)
	private Boolean vendorCode;
	
	@Column(nullable = false, length = 10)
	private Boolean hospitalName;
	
	
	public ConsumableBarcodeConfig() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Boolean getItemName() {
		return itemName;
	}

	public void setItemName(Boolean itemName) {
		this.itemName = itemName;
	}

	public Boolean getItemCode() {
		return itemCode;
	}

	public void setItemCode(Boolean itemCode) {
		this.itemCode = itemCode;
	}

	public Boolean getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(Boolean batchNo) {
		this.batchNo = batchNo;
	}

	public Boolean getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Boolean expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Boolean getLotNo() {
		return lotNo;
	}

	public void setLotNo(Boolean lotNo) {
		this.lotNo = lotNo;
	}

	public Boolean getMrp() {
		return mrp;
	}

	public void setMrp(Boolean mrp) {
		this.mrp = mrp;
	}

	public Boolean getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Boolean salePrice) {
		this.salePrice = salePrice;
	}

	public Boolean getGrnNo() {
		return grnNo;
	}

	public void setGrnNo(Boolean grnNo) {
		this.grnNo = grnNo;
	}

	public Boolean getVendorName() {
		return vendorName;
	}

	public void setVendorName(Boolean vendorName) {
		this.vendorName = vendorName;
	}

	public Boolean getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(Boolean vendorCode) {
		this.vendorCode = vendorCode;
	}

	public Boolean getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(Boolean hospitalName) {
		this.hospitalName = hospitalName;
	}

	public void setAddedDate(Timestamp addedDate) {
		this.addedDate = addedDate;
	}

	
	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	
	public ConsumableBarcodeConfig(Boolean itemName, Boolean itemCode, Boolean batchNo, Boolean expiryDate,
			Boolean lotNo, Boolean mrp, Boolean salePrice, Boolean grnNo, Boolean vendorName, Boolean vendorCode,
			Boolean hospitalName) {
		super();
		this.itemName = itemName;
		this.itemCode = itemCode;
		this.batchNo = batchNo;
		this.expiryDate = expiryDate;
		this.lotNo = lotNo;
		this.mrp = mrp;
		this.salePrice = salePrice;
		this.grnNo = grnNo;
		this.vendorName = vendorName;
		this.vendorCode = vendorCode;
		this.hospitalName = hospitalName;
	}

	public void updateConsumableBarcodeConfig(Boolean itemName, Boolean itemCode, Boolean batchNo, Boolean expiryDate,
			Boolean lotNo, Boolean mrp, Boolean salePrice, Boolean grnNo, Boolean vendorName, Boolean vendorCode,
			Boolean hospitalName) {
		this.itemName = itemName;
		this.itemCode = itemCode;
		this.batchNo = batchNo;
		this.expiryDate = expiryDate;
		this.lotNo = lotNo;
		this.mrp = mrp;
		this.salePrice = salePrice;
		this.grnNo = grnNo;
		this.vendorName = vendorName;
		this.vendorCode = vendorCode;
		this.hospitalName = hospitalName;
	}
	
	public ConsumableBarcodeConfig(Integer id) {
		super();
		this.id = id;
	}



}
