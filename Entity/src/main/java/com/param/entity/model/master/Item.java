package com.param.entity.model.master;

import java.io.Serializable;
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
import com.param.entity.model.inventory.Batch;
import com.param.entity.model.inventory.StoreIndentDetail;
import com.param.entity.model.procurement.ItemRequestDetail;

@Entity(name = "Item")
@Table(name = "m_item", schema = "public")

public class Item extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@Column(nullable = false, length = 10)
	private String code;

	@Column(name = "hsn_code", length = 50)
	private String hsnCode;

	@Column(name = "is_batch_required", nullable = false)
	private Boolean isBatchRequired = false;

	@Column(name = "is_combination", nullable = false)
	private Boolean isCombination = false;

	@Column(name = "is_high_risk_product", nullable = false)
	private Boolean isHighRiskProduct = false;

	@Column(name = "is_infusion", nullable = false)
	private Boolean isInfusion = false;

	@Column(name = "is_kit", nullable = false)
	private Boolean isKit = false;

	@Column(name = "is_narcotic", nullable = false)
	private Boolean isNarcotic = false;

	@Column(name = "is_otc", nullable = false)
	private Boolean isOtc = false;

	@Column(name = "is_psychotropic", nullable = false)
	private Boolean isPsychotropic = false;

	@Column(name = "is_reusable", nullable = false)
	private Boolean isReusable = false;

	@Column(name = "is_vaccin", nullable = false)
	private Boolean isVaccin = false;
	
	@Column(name = "is_consignment", nullable = false)
	private Boolean isConsignment = false;
	
	@Column(name = "is_consumable", nullable = false)
	private Boolean isConsumable = false;
	
	@Column(name = "is_hospital_formulary", nullable = false)
	private Boolean isHospitalFormulary = false;
	
	@Column(nullable = false, length = 500)
	private String name;

	@Column(name = "product_code", length = 50)
	private String productCode;

	@Column(name = "storage_unit_id")
    private Integer storageUnitId;

	@Column(name="strength")
	private Double strength;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "formulation_type_id")
	private FormulationType formulationType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "generic_id")
	private Generic generic;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manufacturer_id", nullable = false)
	private Manufacturer manufacturer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_category_id", nullable = false)
	private ProductCategory productCategory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "strength_unit_id")
	private StrengthUnit strengthUnit;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pharmacological_classification_id")
	private PharmacologicalClassification pharmacologicalClassification;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_tax_id")
	private Tax purchaseTax;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "s_tax_id")
	private Tax salesTax;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	private List<UnitOfMeasurement> unitOfMeasurementList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	private List<Batch> batchList;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	private List<StoreItemMapper> storeItemList;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	private List<StoreIndentDetail> storeIndentDetailList;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item" , cascade  = CascadeType.ALL)
	private List<ItemRequestDetail> itemRequestDetailList;
	
	public Item() {
	
	}
	
	public ProductCategory getProductCategory() {
		return productCategory;
	}


	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}



	public Item(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getHsnCode() {
		return this.hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	public Boolean getIsBatchRequired() {
		return this.isBatchRequired;
	}

	public void setIsBatchRequired(Boolean isBatchRequired) {
		this.isBatchRequired = isBatchRequired;
	}

	public Boolean getIsCombination() {
		return this.isCombination;
	}

	public void setIsCombination(Boolean isCombination) {
		this.isCombination = isCombination;
	}

	public Boolean getIsHighRiskProduct() {
		return this.isHighRiskProduct;
	}

	public void setIsHighRiskProduct(Boolean isHighRiskProduct) {
		this.isHighRiskProduct = isHighRiskProduct;
	}

	public Boolean getIsInfusion() {
		return this.isInfusion;
	}

	public void setIsInfusion(Boolean isInfusion) {
		this.isInfusion = isInfusion;
	}

	public Boolean getIsKit() {
		return this.isKit;
	}

	public void setIsKit(Boolean isKit) {
		this.isKit = isKit;
	}

	public Boolean getIsNarcotic() {
		return this.isNarcotic;
	}

	public void setIsNarcotic(Boolean isNarcotic) {
		this.isNarcotic = isNarcotic;
	}

	public Boolean getIsOtc() {
		return this.isOtc;
	}

	public void setIsOtc(Boolean isOtc) {
		this.isOtc = isOtc;
	}

	public Boolean getIsPsychotropic() {
		return this.isPsychotropic;
	}

	public void setIsPsychotropic(Boolean isPsychotropic) {
		this.isPsychotropic = isPsychotropic;
	}

	public Boolean getIsReusable() {
		return this.isReusable;
	}

	public void setIsReusable(Boolean isReusable) {
		this.isReusable = isReusable;
	}

	public Boolean getIsVaccin() {
		return this.isVaccin;
	}

	public void setIsVaccin(Boolean isVaccin) {
		this.isVaccin = isVaccin;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Integer getStorageUnitId() {
		return this.storageUnitId;
	}

	public void setStorageUnitId(Integer storageUnitId) {
		this.storageUnitId = storageUnitId;
	}

	public Double getStrength() {
		return this.strength;
	}

	public void setStrength(Double strength) {
		this.strength = strength;
	}

	public FormulationType getFormulationType() {
		return this.formulationType;
	}

	public void setFormulationType(FormulationType formulationType) {
		this.formulationType = formulationType;
	}

	public Generic getGeneric() {
		return this.generic;
	}

	public void setGeneric(Generic generic) {
		this.generic = generic;
	}

	public Manufacturer getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public StrengthUnit getStrengthUnit() {
		return strengthUnit;
	}

	public void setStrengthUnit(StrengthUnit strengthUnit) {
		this.strengthUnit = strengthUnit;
	}

	public PharmacologicalClassification getPharmacologicalClassification() {
		return this.pharmacologicalClassification;
	}

	public void setPharmacologicalClassification(PharmacologicalClassification pharmacologicalClassification) {
		this.pharmacologicalClassification = pharmacologicalClassification;
	}

	public List<Batch> getBatchList() {
		return batchList;
	}

	public void setBatchList(List<Batch> batchList) {
		this.batchList = batchList;
	}

	public Tax getPurchaseTax() {
		return purchaseTax;
	}

	public List<UnitOfMeasurement> getUnitOfMeasurementList() {
		return unitOfMeasurementList;
	}

	public void setUnitOfMeasurementList(List<UnitOfMeasurement> unitOfMeasurementList) {
		this.unitOfMeasurementList = unitOfMeasurementList;
	}

	public void setPurchaseTax(Tax purchaseTax) {
		this.purchaseTax = purchaseTax;
	}

	public Tax getSalesTax() {
		return salesTax;
	}

	public void setSalesTax(Tax salesTax) {
		this.salesTax = salesTax;
	}

	public Boolean getIsConsignment() {
		return isConsignment;
	}

	public void setIsConsignment(Boolean isConsignment) {
		this.isConsignment = isConsignment;
	}

	public Boolean getIsConsumable() {
		return isConsumable;
	}

	public void setIsConsumable(Boolean isConsumable) {
		this.isConsumable = isConsumable;
	}

	public List<StoreItemMapper> getStoreItemList() {
		return storeItemList;
	}

	public void setStoreItemList(List<StoreItemMapper> storeItemList) {
		this.storeItemList = storeItemList;
	}

	public Boolean getIsHospitalFormulary() {
		return isHospitalFormulary;
	}

	public void setIsHospitalFormulary(Boolean isHospitalFormulary) {
		this.isHospitalFormulary = isHospitalFormulary;
	}

	public List<StoreIndentDetail> getStoreIndentDetailList() {
		return storeIndentDetailList;
	}

	public void setStoreIndentDetailList(List<StoreIndentDetail> storeIndentDetailList) {
		this.storeIndentDetailList = storeIndentDetailList;
	}

	public List<ItemRequestDetail> getItemRequestDetailList() {
		return itemRequestDetailList;
	}

	public void setItemRequestDetailList(List<ItemRequestDetail> itemRequestDetailList) {
		this.itemRequestDetailList = itemRequestDetailList;
	}

	public void updateItemRestriction(Integer itemId) {
		this.id = itemId;
		
	}

}
