package com.param.billing.packages.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.service.global.model.TPackageEitherorGroupDetails;

@Entity
@Table(name = "t_package_consumption_eo_details" , schema = "billing")
@SequenceGenerator(name = "package_consumption_eo_details_seq" , sequenceName = "billing.package_consumption_eo_details_seq" , allocationSize = 1)
public class TPackageConsumptionEoDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "package_consumption_eo_details_seq")
	@Column(name = "pacakge_consumtion_eo_detail_id")
	private int pacakgeConsumtionEoDetailId;
	
	@Column(name = "package_consumption_master_id")
	private Integer packageConsumptionMasterId;
	
	@Column(name = "is_service_item_bed")
	private Integer isServiceItemBed;
	
	@Column(name = "pacakge_eighteror_group_id")
	private Integer pacakgeEighterorGroupId;
	
	@Column(name = "product_cat_id")
	private Integer productCatId;
	
	@Column(name = "bed_cat_id")
	private Integer bedCatId;
	
	@Column(name = "total_quantity")
	private Integer totalQuantity;
	
	@Column(name = "balance_quantity")
	private Integer balanceQuantity;
	
	@Column(name = "group_price")
	private Double groupPrice;
	
	@Column(name = "organisation_id")
	private Integer organisationId;
	
	@Column(name = "unit_id")
	private Integer unitId;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "created_by")
	private Integer createdBy;
	
	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name = "updated_by")
	private Integer updatedBy;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "package_consumption_master_id" , insertable = false , updatable = false , nullable = false)
	private TPackageConsumptionMaster tPackageConsumptionMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pacakge_eighteror_group_id" , insertable = false , updatable = false , nullable = false)
	private TPackageEitherorGroupDetails tPackageEitherorGroupDetails;

	public int getPacakgeConsumtionEoDetailId() {
		return pacakgeConsumtionEoDetailId;
	}

	public void setPacakgeConsumtionEoDetailId(int pacakgeConsumtionEoDetailId) {
		this.pacakgeConsumtionEoDetailId = pacakgeConsumtionEoDetailId;
	}

	public Integer getPackageConsumptionMasterId() {
		return packageConsumptionMasterId;
	}

	public void setPackageConsumptionMasterId(Integer packageConsumptionMasterId) {
		this.packageConsumptionMasterId = packageConsumptionMasterId;
	}

	public Integer getIsServiceItemBed() {
		return isServiceItemBed;
	}

	public void setIsServiceItemBed(Integer isServiceItemBed) {
		this.isServiceItemBed = isServiceItemBed;
	}

	public Integer getPacakgeEighterorGroupId() {
		return pacakgeEighterorGroupId;
	}

	public void setPacakgeEighterorGroupId(Integer pacakgeEighterorGroupId) {
		this.pacakgeEighterorGroupId = pacakgeEighterorGroupId;
	}

	public Integer getProductCatId() {
		return productCatId;
	}

	public void setProductCatId(Integer productCatId) {
		this.productCatId = productCatId;
	}

	public Integer getBedCatId() {
		return bedCatId;
	}

	public void setBedCatId(Integer bedCatId) {
		this.bedCatId = bedCatId;
	}

	public Integer getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Integer totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public Integer getBalanceQuantity() {
		return balanceQuantity;
	}

	public void setBalanceQuantity(Integer balanceQuantity) {
		this.balanceQuantity = balanceQuantity;
	}

	public Double getGroupPrice() {
		return groupPrice;
	}

	public void setGroupPrice(Double groupPrice) {
		this.groupPrice = groupPrice;
	}

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public TPackageConsumptionMaster gettPackageConsumptionMaster() {
		return tPackageConsumptionMaster;
	}

	public void settPackageConsumptionMaster(TPackageConsumptionMaster tPackageConsumptionMaster) {
		this.tPackageConsumptionMaster = tPackageConsumptionMaster;
	}

	public TPackageEitherorGroupDetails gettPackageEitherorGroupDetails() {
		return tPackageEitherorGroupDetails;
	}

	public void settPackageEitherorGroupDetails(TPackageEitherorGroupDetails tPackageEitherorGroupDetails) {
		this.tPackageEitherorGroupDetails = tPackageEitherorGroupDetails;
	}
}
