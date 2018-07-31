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

import com.param.global.model.SpecialityMaster;
import com.param.global.model.SubSpecialityMaster;

@Entity
@Table(name = "t_contract_cap_details" , schema = "service")
@SequenceGenerator(name = "t_contract_cap_details_seq" , sequenceName = "service.t_contract_cap_details_seq" , allocationSize = 1)
public class TContractCapDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "t_contract_cap_details_seq")
	@Column(name = "contract_cap_details_id")
	private int contractCapDetailsId;
	
	@Column(name = "contract_id")
	private Integer contractId;
	
	@Column(name = "department_id")
	private Integer departmentId;
	
	@Column(name = "sub_department_id")
	private Integer subDepartmentId;
	
	@Column(name = "department_cap_amount")
	private double departmentCapAmount;
	
	@Column(name = "sub_department_cap_amount")
	private double subDepartmentCapAmount;
	
	@Column(name = "status")
	private char status;
	
	@Column(name = "is_service_item")
	private Integer isServiceItem;
	
	@Column(name = "product_category_id")
	private Integer productCategoryId;
	
	@Column(name = "product_category_cap_amount")
	private double productCategoryCapAmount;
	
	@Column(name = "org_id")
	private Integer orgId;
	
	@Column(name = "unit_id")
	private Integer unitId;
	
	@Column(name = "created_by")
	private Integer createdBy;
	
	@Column(name = "updated_by")
	private Integer updatedBy;
	
	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name  = "contract_id" , insertable = false , updatable = false , nullable = false)
	private MCompanyContractMaster mCompanyContractMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name  = "department_id" , insertable = false , updatable = false , nullable = false)
	private SpecialityMaster specialityMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name  = "sub_department_id" , insertable = false , updatable = false , nullable = false)
	private SubSpecialityMaster subSpecialityMaster;

	public int getContractCapDetailsId() {
		return contractCapDetailsId;
	}

	public void setContractCapDetailsId(int contractCapDetailsId) {
		this.contractCapDetailsId = contractCapDetailsId;
	}

	public Integer getContractId() {
		return contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
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

	public double getDepartmentCapAmount() {
		return departmentCapAmount;
	}

	public void setDepartmentCapAmount(double departmentCapAmount) {
		this.departmentCapAmount = departmentCapAmount;
	}

	public double getSubDepartmentCapAmount() {
		return subDepartmentCapAmount;
	}

	public void setSubDepartmentCapAmount(double subDepartmentCapAmount) {
		this.subDepartmentCapAmount = subDepartmentCapAmount;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
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

	public double getProductCategoryCapAmount() {
		return productCategoryCapAmount;
	}

	public void setProductCategoryCapAmount(double productCategoryCapAmount) {
		this.productCategoryCapAmount = productCategoryCapAmount;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public MCompanyContractMaster getmCompanyContractMaster() {
		return mCompanyContractMaster;
	}

	public void setmCompanyContractMaster(MCompanyContractMaster mCompanyContractMaster) {
		this.mCompanyContractMaster = mCompanyContractMaster;
	}

	public SpecialityMaster getSpecialityMaster() {
		return specialityMaster;
	}

	public void setSpecialityMaster(SpecialityMaster specialityMaster) {
		this.specialityMaster = specialityMaster;
	}

	public SubSpecialityMaster getSubSpecialityMaster() {
		return subSpecialityMaster;
	}

	public void setSubSpecialityMaster(SubSpecialityMaster subSpecialityMaster) {
		this.subSpecialityMaster = subSpecialityMaster;
	}
}
