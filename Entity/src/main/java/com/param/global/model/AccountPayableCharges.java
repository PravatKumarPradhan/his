package com.param.global.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.global.common.LocalTimeConverter;


@Entity
@Table(name = "m_account_payable_charges", schema = "public")
@SequenceGenerator(name = "m_seq_account_payable_charges", sequenceName = "public.m_seq_account_payable_charges", allocationSize = 1)
public class AccountPayableCharges {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_account_payable_charges")
	@Column(name = "acc_payable_charge_id")
	private int accPayableChargeId;

	@Column(name = "acc_payable")
	private String accPayable;

	@Column(name = "service_id")
	private Integer serviceId;
	
	@Column(name = "acc_charges_applicable")
	private double accChargesApplicable;
	
	@Column(name = "status")
	private char status;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "created_date")
	@Convert(converter = LocalTimeConverter.class)
	private Date createdDate;

	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "updated_date")
	@Convert(converter = LocalTimeConverter.class)
	private Date updatedDate;
	
	@Column(name="org_id")
	private Integer orgId;
	
	@Column(name="org_unit_id")
	private Integer orgUnitId;
	
	@Column(name="dept_id")
	private Integer deptId;
	
	@Column(name="sub_dept_id")
	private Integer subDeptId;
	
	@Column(name="order_id")
	private Integer orderId;
	
	@Column(name="priority")
	private Integer priority;

	public int getAccPayableChargeId() {
		return accPayableChargeId;
	}

	public void setAccPayableChargeId(int accPayableChargeId) {
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
