package com.param.global.dto.global;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.param.global.dto.items.UomResponse;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurchaseOrderDetailResponse {
	
	private Integer id;

	private Integer itemId;
	
	private String itemCode;
	
	private String itemName;

	private Integer poQuantity;
	
	private Integer bonusQuantity;
	
	private Double cop;

	private Double mrp;
	
	private Integer taxId;

	private String taxName;

	private Double taxPercent;
	
	private Integer genericId;

	private String genericName;

	private Integer manufacturerId;

	private String manufacturerName;
	
	private List<UomResponse> uom;
	
	private List<PurchaseOrderStaged> poStagedList;

	public PurchaseOrderDetailResponse() {
		super();
	}

	public PurchaseOrderDetailResponse(Integer id, Integer itemId, String itemCode, String itemName, Integer poQuantity,
			Integer bonusQuantity,
			Double cop,
			Double mrp) {
		super();
		this.id = id;
		this.itemId = itemId;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.poQuantity = poQuantity;
		this.bonusQuantity = bonusQuantity;
		this.cop = cop;
		this.mrp = mrp;
		
	}
	
	public PurchaseOrderDetailResponse(Integer id, Integer itemId, String itemCode, String itemName, Integer poQuantity,
			Integer bonusQuantity, Double cop, Double mrp, Integer taxId, String taxName, Double taxPercent,
			Integer genericId, String genericName, Integer manufacturerId, String manufacturerName,
			List<UomResponse> uom) {
		super();
		this.id = id;
		this.itemId = itemId;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.poQuantity = poQuantity;
		this.bonusQuantity = bonusQuantity;
		this.cop = cop;
		this.mrp = mrp;
		this.taxId = taxId;
		this.taxName = taxName;
		this.taxPercent = taxPercent;
		this.genericId = genericId;
		this.genericName = genericName;
		this.manufacturerId = manufacturerId;
		this.manufacturerName = manufacturerName;
		this.uom = uom;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
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

	public Double getTaxPercent() {
		return taxPercent;
	}

	public void setTaxPercent(Double taxPercent) {
		this.taxPercent = taxPercent;
	}

	public List<UomResponse> getUom() {
		return uom;
	}

	public void setUom(List<UomResponse> uom) {
		this.uom = uom;
	}

	public Integer getGenericId() {
		return genericId;
	}

	public void setGenericId(Integer genericId) {
		this.genericId = genericId;
	}

	public String getGenericName() {
		return genericName;
	}

	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}

	public Integer getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(Integer manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public List<PurchaseOrderStaged> getPoStagedList() {
		return poStagedList;
	}

	public void setPoStagedList(List<PurchaseOrderStaged> poStagedList) {
		this.poStagedList = poStagedList;
	}

		
}
