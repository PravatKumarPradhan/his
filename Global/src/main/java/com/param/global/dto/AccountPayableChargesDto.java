package com.param.global.dto;

import java.util.Date;



public class AccountPayableChargesDto {
	
	private Integer accPayableChargeId;
	private String accPayable;
	private Integer serviceId;
	private double accChargesApplicable;
	private Character status;
	private Integer createdBy;
	private Date createdDate;
	private Integer updatedBy;
	private Date updatedDate;
	private Integer orgId;
	private Integer orgUnitId;
	private Integer deptId;
	private Integer subDeptId;
	private Integer orderId;
	private Integer priority;

	public Integer getAccPayableChargeId() {
		return accPayableChargeId;
	}

	public void setAccPayableChargeId(Integer accPayableChargeId) {
		this.accPayableChargeId = accPayableChargeId;
	}

	public String getAccPayable() {
		return accPayable;
	}

	public void setAccPayable(String accPayable) {
		this.accPayable = accPayable;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public double getAccChargesApplicable() {
		return accChargesApplicable;
	}

	public void setAccChargesApplicable(double accChargesApplicable) {
		this.accChargesApplicable = accChargesApplicable;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
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

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public Integer getSubDeptId() {
		return subDeptId;
	}

	public void setSubDeptId(Integer subDeptId) {
		this.subDeptId = subDeptId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	
	
}
