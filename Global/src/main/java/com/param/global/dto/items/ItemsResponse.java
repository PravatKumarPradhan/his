package com.param.global.dto.items;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ItemsResponse extends ItemListResponse {

	protected List<UomResponse> uom;

	protected Long batchId;

	protected String batchCode;

	protected Integer uomTypeId;

	protected String uomType;

	protected Integer uomUnitId;

	protected String uomUnit;

	protected Date expiry;

	protected String lotNo;

	protected Long availableQuantity;

	protected String grnNo;

	protected Date grnDate;

	protected Double cop;

	protected Integer taxId;

	protected String taxName;

	protected Double taxPercentage;

	protected Integer vendorId;

	protected String vendor;

	protected String genericName;

	protected String batchNo;

	public ItemsResponse() {
		super();
	}

	public ItemsResponse(Integer itemId, String itemCode, String itemName, Long batchId, String batchCode,
			Integer uomTypeId, String uomType, Integer uomUnitId, String uomUnit, Date expiry, Long availableQuantity) {
		super(itemId, itemCode, itemName);
		this.batchId = batchId;
		this.uomTypeId = uomTypeId;
		this.uomType = uomType;
		this.uomUnitId = uomUnitId;
		this.uomUnit = uomUnit;
		this.expiry = expiry;
		this.availableQuantity = availableQuantity;
	}
	
	public ItemsResponse(Integer itemId, String itemCode, String itemName, Long batchId,
			Integer uomTypeId, String uomType, Integer uomUnitId, String uomUnit, Date expiry, Long availableQuantity) {
		super(itemId, itemCode, itemName);
		this.batchId = batchId;
		this.uomTypeId = uomTypeId;
		this.uomType = uomType;
		this.uomUnitId = uomUnitId;
		this.uomUnit = uomUnit;
		this.expiry = expiry;
		this.availableQuantity = availableQuantity;
	}

	public ItemsResponse(Integer itemId, String itemCode, String itemName, String batchNo, Date expiry, String lotNo,
			Long availableQuantity, String grnNo, Date grnDate) {
		super(itemId, itemCode, itemName);
		this.batchNo = batchNo;
		this.expiry = expiry;
		this.lotNo = lotNo;
		this.availableQuantity = availableQuantity;
		this.grnNo = grnNo;
		this.grnDate = grnDate;
	}

	public void setUom(Integer uomTypeId, String uomType, Integer uomUnitId, String uomUnit) {
		this.uomTypeId = uomTypeId;
		this.uomType = uomType;
		this.uomUnitId = uomUnitId;
		this.uomUnit = uomUnit;
	}

	public List<UomResponse> getUom() {
		return uom;
	}

	public void setUom(List<UomResponse> uom) {
		this.uom = uom;
	}

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public Date getExpiry() {
		return expiry;
	}

	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}

	public String getLotNo() {
		return lotNo;
	}

	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}

	public Long getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(Long availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public String getGrnNo() {
		return grnNo;
	}

	public void setGrnNo(String grnNo) {
		this.grnNo = grnNo;
	}

	public Date getGrnDate() {
		return grnDate;
	}

	public void setGrnDate(Date grnDate) {
		this.grnDate = grnDate;
	}

	public Long getBatchId() {
		return batchId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

	public Integer getUomTypeId() {
		return uomTypeId;
	}

	public void setUomTypeId(Integer uomTypeId) {
		this.uomTypeId = uomTypeId;
	}

	public String getUomType() {
		return uomType;
	}

	public void setUomType(String uomType) {
		this.uomType = uomType;
	}

	public Integer getUomUnitId() {
		return uomUnitId;
	}

	public void setUomUnitId(Integer uomUnitId) {
		this.uomUnitId = uomUnitId;
	}

	public String getUomUnit() {
		return uomUnit;
	}

	public void setUomUnit(String uomUnit) {
		this.uomUnit = uomUnit;
	}

	public Double getCop() {
		return cop;
	}

	public void setCop(Double cop) {
		this.cop = cop;
	}

	public Integer getTaxId() {
		return taxId;
	}

	public void setTaxId(Integer taxId) {
		this.taxId = taxId;
	}

	public String getTaxName() {
		return taxName;
	}

	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}

	public Double getTaxPercentage() {
		return taxPercentage;
	}

	public void setTaxPercentage(Double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	public Integer getVendorId() {
		return vendorId;
	}

	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getGenericName() {
		return genericName;
	}

	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
}
