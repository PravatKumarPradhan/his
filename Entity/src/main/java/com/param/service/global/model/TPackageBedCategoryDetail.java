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

import com.param.global.model.BedCategoryMaster;

@Entity
@Table(name="t_package_bed_category_detail",schema="service")
@SequenceGenerator(name="t_seq_package_bed_category_detail",sequenceName="service.t_seq_package_bed_category_detail",allocationSize=1)
public class TPackageBedCategoryDetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="t_seq_package_bed_category_detail")
	@Column(name="package_bed_category_detail_id")
	private Integer packageBedCategoryDetailId;
	
	@Column(name="package_id")
	private Integer packageId;
	
	@Column(name="bed_category_id")
	private Integer bedCategoryId;
	

	@Column(name="applicable_days")
	private Integer applicableDays;
	
	@Column(name="total_amount")
	private Double totalAmount;
	
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
	@JoinColumn(name = "package_id" , insertable = false , updatable = false , nullable = false)
	private MPackageMaster mPackageMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bed_category_id" , insertable = false , updatable = false , nullable = false)
	private BedCategoryMaster bedCategoryMaster;

	public BedCategoryMaster getBedCategoryMaster() {
		return bedCategoryMaster;
	}

	public void setBedCategoryMaster(BedCategoryMaster bedCategoryMaster) {
		this.bedCategoryMaster = bedCategoryMaster;
	}

	public MPackageMaster getmPackageMaster() {
		return mPackageMaster;
	}

	public void setmPackageMaster(MPackageMaster mPackageMaster) {
		this.mPackageMaster = mPackageMaster;
	}

	public Integer getPackageBedCategoryDetailId() {
		return packageBedCategoryDetailId;
	}

	public void setPackageBedCategoryDetailId(Integer packageBedCategoryDetailId) {
		this.packageBedCategoryDetailId = packageBedCategoryDetailId;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public Integer getBedCategoryId() {
		return bedCategoryId;
	}

	public void setBedCategoryId(Integer bedCategoryId) {
		this.bedCategoryId = bedCategoryId;
	}

	public Integer getApplicableDays() {
		return applicableDays;
	}

	public void setApplicableDays(Integer applicableDays) {
		this.applicableDays = applicableDays;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
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
