package com.param.service.global.model;

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

import com.param.global.model.ServiceMaster;

@Entity
@Table(name="t_package_inc_exc_details",schema="service")
@SequenceGenerator(name="t_seq_package_inc_exc_details",sequenceName="service.t_seq_package_inc_exc_details",allocationSize=1)
public class TPackageIncExcDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="t_seq_package_inc_exc_details")
	@Column(name="package_inc_exc_id")
	private Integer packageIncExcId;
	
	@Column(name="package_id")
	private Integer packageId;
	
	@Column(name="service_id")
	private Integer serviceId;
	

	@Column(name="is_inclusive_service")
	private Integer isInclusiveService;
	
	@Column(name="is_service_item")
	private Integer isServiceItem;
	
	@Column(name="item_id")
	private Integer itemId;
	
	@Column(name="package_category_consumable_id")
	private Integer packageCategoryConsumableId;
	
	@Column(name="org_id")
	private Integer orgId;
	
	@Column(name="org_unit_id")
	private Integer orgUnitId;
	
	@Column(name="created_by")
	private Integer createdBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="updated_by")
	private Integer updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="status")
	private char status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "package_category_consumable_id" , insertable = false , updatable = false , nullable = false)
	private TPackageCategoryWiseConsumableDetails tPackageCategoryWiseConsumableDetails;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "package_id" , insertable = false , updatable = false , nullable = false)
	private MPackageMaster mPackageMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_id" , insertable = false , updatable = false , nullable = false)
	private ServiceMaster serviceMaster;

	public ServiceMaster getServiceMaster() {
		return serviceMaster;
	}

	public void setServiceMaster(ServiceMaster serviceMaster) {
		this.serviceMaster = serviceMaster;
	}

	public MPackageMaster getmPackageMaster() {
		return mPackageMaster;
	}

	public void setmPackageMaster(MPackageMaster mPackageMaster) {
		this.mPackageMaster = mPackageMaster;
	}

	public TPackageCategoryWiseConsumableDetails gettPackageCategoryWiseConsumableDetails() {
		return tPackageCategoryWiseConsumableDetails;
	}

	public void settPackageCategoryWiseConsumableDetails(
			TPackageCategoryWiseConsumableDetails tPackageCategoryWiseConsumableDetails) {
		this.tPackageCategoryWiseConsumableDetails = tPackageCategoryWiseConsumableDetails;
	}

	public Integer getPackageIncExcId() {
		return packageIncExcId;
	}

	public void setPackageIncExcId(Integer packageIncExcId) {
		this.packageIncExcId = packageIncExcId;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getIsInclusiveService() {
		return isInclusiveService;
	}

	public void setIsInclusiveService(Integer isInclusiveService) {
		this.isInclusiveService = isInclusiveService;
	}

	public Integer getIsServiceItem() {
		return isServiceItem;
	}

	public void setIsServiceItem(Integer isServiceItem) {
		this.isServiceItem = isServiceItem;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getPackageCategoryConsumableId() {
		return packageCategoryConsumableId;
	}

	public void setPackageCategoryConsumableId(Integer packageCategoryConsumableId) {
		this.packageCategoryConsumableId = packageCategoryConsumableId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getOrgUnitId() {
		return orgUnitId;
	}

	public void setOrgUnitId(Integer orgUnitId) {
		this.orgUnitId = orgUnitId;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}
	
	
	
	

}
