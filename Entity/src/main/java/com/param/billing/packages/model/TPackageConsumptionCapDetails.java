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
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.global.model.SpecialityMaster;
import com.param.global.model.SubSpecialityMaster;

@NamedNativeQueries({
	@NamedNativeQuery(
			name="GET_PACKAGE_CONSUMPTION_CAP_DETAILS",
			query="select pccd.package_consumption_cap_details_id as \"packageConsumptionCapDetailsId\", pccd.package_consumption_master_id as \"packageConsumptionMasterId\", pccd.department_id as \"departmentId\","
					+ " pccd.sub_department_id as \"subDepartmentId\", pccd.department_cap_amount as \"departmentCapAmount\", pccd.department_balance_cap_amount as \"departmentBalanceCapAmount\","
					+ " pccd.sub_department_cap_amount as \"subDepartmentCapAmount\", pccd.sub_department_balance_cap_amount as \"subDepartmentBalanceCapAmount\", pccd.is_service_item as \"isServiceItem\","
					+ " pccd.product_category_id as \"productCategoryId\" "
					+ " from billing.t_package_consumption_cap_details pccd"
					+ " where package_consumption_master_id =:packageConsumptionMasterId")
})

@Entity
@Table(name = "t_package_consumption_cap_details" , schema = "billing")
@SequenceGenerator(name = "package_consumption_cap_details_seq" , sequenceName = "billing.package_consumption_cap_details_seq" , allocationSize = 1)
public class TPackageConsumptionCapDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "package_consumption_cap_details_seq")
	@Column(name = "package_consumption_cap_details_id")
	private int packageConsumptionCapDetailsId;
	
	@Column(name = "package_consumption_master_id")
	private Integer packageConsumptionMasterId;
	
	@Column(name = "department_id")
	private Integer departmentId;
	
	@Column(name = "sub_department_id")
	private Integer subDepartmentId;
	
	@Column(name = "is_service_item")
	private Integer isServiceItem;
	
	@Column(name = "product_category_id")
	private Integer productCategoryId;
	
	@Column(name = "department_cap_amount")
	private Double departmentCapAmount;
	
	@Column(name = "sub_department_cap_amount")
	private Double subDepartmentCapAmount;
	
	@Column(name = "department_balance_cap_amount")
	private Double departmentBalanceCapAmount;
	
	@Column(name = "sub_department_balance_cap_amount")
	private Double subDepartmentBalanceCapAmount;
	
	@Column(name = "status")
	private char status;
	
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
	@JoinColumn(name = "department_id" , insertable = false , updatable = false , nullable = false)
	private SpecialityMaster specialityMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "package_consumption_master_id" , insertable = false , updatable = false , nullable = false)
	private TPackageConsumptionMaster tPackageConsumptionMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sub_department_id" , insertable = false , updatable = false , nullable = false)
	private SubSpecialityMaster subSpecialityMaster;

	public int getPackageConsumptionCapDetailsId() {
		return packageConsumptionCapDetailsId;
	}

	public void setPackageConsumptionCapDetailsId(int packageConsumptionCapDetailsId) {
		this.packageConsumptionCapDetailsId = packageConsumptionCapDetailsId;
	}

	public Integer getPackageConsumptionMasterId() {
		return packageConsumptionMasterId;
	}

	public void setPackageConsumptionMasterId(Integer packageConsumptionMasterId) {
		this.packageConsumptionMasterId = packageConsumptionMasterId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getSubDepartmentId() {
		return subDepartmentId;
	}

	public void setSubDepartmentId(Integer subDepartmentId) {
		this.subDepartmentId = subDepartmentId;
	}

	public Integer getIsServiceItem() {
		return isServiceItem;
	}

	public void setIsServiceItem(Integer isServiceItem) {
		this.isServiceItem = isServiceItem;
	}

	public Integer getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(Integer productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public Double getDepartmentCapAmount() {
		return departmentCapAmount;
	}

	public void setDepartmentCapAmount(Double departmentCapAmount) {
		this.departmentCapAmount = departmentCapAmount;
	}

	public Double getSubDepartmentCapAmount() {
		return subDepartmentCapAmount;
	}

	public void setSubDepartmentCapAmount(Double subDepartmentCapAmount) {
		this.subDepartmentCapAmount = subDepartmentCapAmount;
	}

	public Double getDepartmentBalanceCapAmount() {
		return departmentBalanceCapAmount;
	}

	public void setDepartmentBalanceCapAmount(Double departmentBalanceCapAmount) {
		this.departmentBalanceCapAmount = departmentBalanceCapAmount;
	}

	public Double getSubDepartmentBalanceCapAmount() {
		return subDepartmentBalanceCapAmount;
	}

	public void setSubDepartmentBalanceCapAmount(Double subDepartmentBalanceCapAmount) {
		this.subDepartmentBalanceCapAmount = subDepartmentBalanceCapAmount;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = (status != '\u0000' ? status : 'I');
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

	public SpecialityMaster getSpecialityMaster() {
		return specialityMaster;
	}

	public void setSpecialityMaster(SpecialityMaster specialityMaster) {
		this.specialityMaster = specialityMaster;
	}

	public TPackageConsumptionMaster gettPackageConsumptionMaster() {
		return tPackageConsumptionMaster;
	}

	public void settPackageConsumptionMaster(TPackageConsumptionMaster tPackageConsumptionMaster) {
		this.tPackageConsumptionMaster = tPackageConsumptionMaster;
	}

	public SubSpecialityMaster getSubSpecialityMaster() {
		return subSpecialityMaster;
	}

	public void setSubSpecialityMaster(SubSpecialityMaster subSpecialityMaster) {
		this.subSpecialityMaster = subSpecialityMaster;
	}
}
