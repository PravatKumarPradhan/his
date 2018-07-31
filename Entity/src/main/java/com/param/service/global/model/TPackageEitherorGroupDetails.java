package com.param.service.global.model;

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
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedNativeQueries({
	@NamedNativeQuery(name = "GET_EO_DETAILS_BY_PACKAGE_ID",
					 query = "select eo.package_group_details_id as \"packageGroupDetailsId\", eo.package_id as \"packageId\", eo.group_id as \"groupId\",  "
							 + " eo.package_group_name as \"packageGroupName\", eo.max_price as \"maxPrice\", eo.min_price as \"minPrice\", eo.avg_price as \"avgPrice\",  "
							 + " eo.group_price as \"groupPrice\", eo.org_id as \"orgId\", eo.org_unit_id as \"orgUnitId\", eo.number_service_usable as \"numberServiceUsable\" "
							 + " from service.t_package_eitheror_group_details eo "
							 + " where eo.package_id = :packageId ")
})
@Entity
@Table(name="t_package_eitheror_group_details",schema="service")
@SequenceGenerator(name="t_seq_package_eitheror_group_details",sequenceName="service.t_seq_package_eitheror_group_details",allocationSize=1)
public class TPackageEitherorGroupDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="t_seq_package_eitheror_group_details")
	@Column(name="package_group_details_id")
	private Integer packageGroupDetailsId;
	
	@Column(name="package_id")
	private Integer packageId;
	
	@Column(name="group_id")
	private Integer groupId;
	
	@Column(name = "package_group_name")
	private String packageGroupName;
	
	@Column(name="max_price")
	private Double maxPrice;
	
	@Column(name="min_price")
	private Double minPrice;
	
	@Column(name="avg_price")
	private Double avgPrice;
	
	@Column(name="group_price")
	private Double groupPrice;
	
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
	
	@Column(name = "number_service_usable")
	private Integer numberServiceUsable;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceMaster" , fetch = FetchType.LAZY)
	private List<TPackageServicesDetails> listTPackageServicesDetails;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "package_id" , insertable = false , updatable = false , nullable = false)
	private MPackageMaster mPackageMaster;
	
	public Integer getNumberServiceUsable() {
		return numberServiceUsable;
	}

	public void setNumberServiceUsable(Integer numberServiceUsable) {
		this.numberServiceUsable = numberServiceUsable;
	}

	public String getPackageGroupName() {
		return packageGroupName;
	}

	public void setPackageGroupName(String packageGroupName) {
		this.packageGroupName = packageGroupName;
	}

	public MPackageMaster getmPackageMaster() {
		return mPackageMaster;
	}

	public void setmPackageMaster(MPackageMaster mPackageMaster) {
		this.mPackageMaster = mPackageMaster;
	}

	public List<TPackageServicesDetails> getListTPackageServicesDetails() {
		return listTPackageServicesDetails;
	}

	public void setListTPackageServicesDetails(List<TPackageServicesDetails> listTPackageServicesDetails) {
		this.listTPackageServicesDetails = listTPackageServicesDetails;
	}

	public Integer getPackageGroupDetailsId() {
		return packageGroupDetailsId;
	}

	public void setPackageGroupDetailsId(Integer packageGroupDetailsId) {
		this.packageGroupDetailsId = packageGroupDetailsId;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public Double getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(Double avgPrice) {
		this.avgPrice = avgPrice;
	}

	public Double getGroupPrice() {
		return groupPrice;
	}

	public void setGroupPrice(Double groupPrice) {
		this.groupPrice = groupPrice;
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


}
