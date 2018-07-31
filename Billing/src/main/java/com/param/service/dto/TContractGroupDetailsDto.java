package com.param.service.dto;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.global.model.SpecialityMaster;
import com.param.global.model.SubSpecialityMaster;

public class TContractGroupDetailsDto {

	private Integer contractGroupDetailId;
	
	private Integer contractId;
	
	private Integer departmentId;
	
	private Integer subDepartmentId;
	
	private double variancePercentage;
	
	private double varianceUpDown;
	
	private Integer roundOffAmount;
	
	private Integer orgId;
	
	private Integer unitId;
	
	private Integer createdBy;
	
	private Integer updatedBy;
	
	private Date updatedDate;
	
	private Date createdDate;

	public Integer getContractGroupDetailId() {
		return contractGroupDetailId;
	}

	public void setContractGroupDetailId(Integer contractGroupDetailId) {
		this.contractGroupDetailId = contractGroupDetailId;
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

	public double getVariancePercentage() {
		return variancePercentage;
	}

	public void setVariancePercentage(double variancePercentage) {
		this.variancePercentage = variancePercentage;
	}

	public double getVarianceUpDown() {
		return varianceUpDown;
	}

	public void setVarianceUpDown(double varianceUpDown) {
		this.varianceUpDown = varianceUpDown;
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

	public Integer getRoundOffAmount() {
		return roundOffAmount;
	}

	public void setRoundOffAmount(Integer roundOffAmount) {
		this.roundOffAmount = roundOffAmount;
	}
	
	
}
