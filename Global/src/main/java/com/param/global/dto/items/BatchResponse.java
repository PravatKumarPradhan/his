package com.param.global.dto.items;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class BatchResponse {

	protected Integer id;

	protected Long batchId;

	protected String batchNo;

	protected String batchDetails;

	protected Date expiry;

	protected Double sellingPrice;

	private Integer availableStockQuantity;

	protected List<UomResponse> uom;

	private Boolean isSelected;

	protected Integer uomTypeId;

	protected Integer uomUnitId;

	protected Integer issueQuantity;

	protected Integer pickerQuantity;

	protected String uomType;

	protected String uomUnit;

	public BatchResponse() {
		super();
	}

	public BatchResponse(Long batchId, String batchNo, Date expiry, List<UomResponse> uom) {
		super();
		this.batchId = batchId;
		this.batchNo = batchNo;
		this.expiry = expiry;
		this.uom = uom;
	}

	public BatchResponse(Long batchId, String batchNo, Date expiry, Integer availableStockQuantity,
			List<UomResponse> uom) {
		this(batchId, batchNo, expiry, uom);
		this.availableStockQuantity = availableStockQuantity;
	}

	public BatchResponse(Long batchId, String batchNo, String batchDetails, Date expiry, List<UomResponse> uom) {
		this(batchId, batchNo, expiry, uom);
		this.batchDetails = batchDetails;
	}

	public BatchResponse(Long batchId, String batchNo, Date expiry, Double sellingPrice, List<UomResponse> uom) {
		this(batchId, batchNo, expiry, uom);
		this.sellingPrice = sellingPrice;
	}

	public BatchResponse(Long batchId, String batchNo, Date expiry, List<UomResponse> uom, Double sellingPrice,
			boolean isSelected) {
		this(batchId, batchNo, expiry, sellingPrice, uom);
		this.isSelected = isSelected;
	}
	
	public BatchResponse(Long batchId, String batchNo, Date expiry, List<UomResponse> uom, Double sellingPrice) {
		this(batchId, batchNo, expiry, sellingPrice, uom);
	}

	public BatchResponse(Long batchId, String batchNo, Date expiry, Double sellingPrice, Integer availableStockQuantity,
			List<UomResponse> uom) {
		this(batchId, batchNo, expiry, availableStockQuantity, uom);
		this.sellingPrice = sellingPrice;
	}

	public Long getBatchId() {
		return batchId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getBatchDetails() {
		Format formatter = new SimpleDateFormat("MMM-yyyy");
		return this.batchNo + " | " + formatter.format(this.expiry);
	}

	public Date getExpiry() {
		return expiry;
	}

	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}

	public Double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public List<UomResponse> getUom() {
		return uom;
	}

	public void setUom(List<UomResponse> uom) {
		this.uom = uom;
	}

	public void setBatchDetails(String batchDetails) {
		this.batchDetails = batchDetails;
	}

	public void addUom(UomResponse uom) {
		if (this.uom == null)
			this.uom = new ArrayList<UomResponse>();

		this.uom.add(uom);
	}

	public Integer getAvailableStockQuantity() {
		return availableStockQuantity;
	}

	public void setAvailableStockQuantity(Integer availableStockQuantity) {
		this.availableStockQuantity = availableStockQuantity;
	}

	public Boolean isSelected() {
		return isSelected;
	}

	public void setSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUomTypeId() {
		return uomTypeId;
	}

	public void setUomTypeId(Integer uomTypeId) {
		this.uomTypeId = uomTypeId;
	}

	public Integer getUomUnitId() {
		return uomUnitId;
	}

	public void setUomUnitId(Integer uomUnitId) {
		this.uomUnitId = uomUnitId;
	}

	public Integer getIssueQuantity() {
		return issueQuantity;
	}

	public void setIssueQuantity(Integer issueQuantity) {
		this.issueQuantity = issueQuantity;
	}

	public Integer getPickerQuantity() {
		return pickerQuantity;
	}

	public void setPickerQuantity(Integer pickerQuantity) {
		this.pickerQuantity = pickerQuantity;
	}

	public Boolean getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}

	public String getUomType() {
		return uomType;
	}

	public void setUomType(String uomType) {
		this.uomType = uomType;
	}

	public String getUomUnit() {
		return uomUnit;
	}

	public void setUomUnit(String uomUnit) {
		this.uomUnit = uomUnit;
	}
}
