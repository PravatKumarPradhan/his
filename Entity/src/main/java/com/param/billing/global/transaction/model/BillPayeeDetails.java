package com.param.billing.global.transaction.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.global.common.DateConverter;

@NamedNativeQueries({
	@NamedNativeQuery(name = "GET_PAYEE_DETAILS_BY_BILL_ID",
					 query = "select billpy.bill_payee_details_id as  \"billPayeeDetailsId\", billpy.payee_id as \"payeeId\", coalesce(comp.company_code,'-') as \"companyCode\", "
							 + " comp.company_desc as \"companyDesc\", billpy.bill_id as \"billId\",billpy.bill_amount as \"billAmount\", billpy.due_amount as \"dueAmount\", "
							 + " coalesce((billpy.bill_amount - billpy.due_amount),0) as \"paidAmount\" "
							 + " from billing.t_bill_payee_details billpy "
							 + " inner join public.t_company_master comp  "
							 + " on comp.company_id = billpy.payee_id  "
							 + " where billpy.bill_id = :billId "),
	
	@NamedNativeQuery(name = "UPDATE_DUE_AMOUNT_BY_BILL_AND_PAYEE",
					 query = "update billing.t_bill_payee_details "
					 		+" set due_amount =:dueAmount "
					 		+" where bill_id=:billId and payee_id=:payeeId ")
})
@Entity
@Table(name="t_bill_payee_details",schema="billing")
@SequenceGenerator(name = "bill_payee_details_seq", sequenceName = "billing.bill_payee_details_seq", allocationSize = 1)
public class BillPayeeDetails {

	@Id
	@Column(name="bill_payee_details_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bill_payee_details_seq")
	private int billPayeeDetailsId;
	
	@Column(name="payee_id")
	private Integer payeeId;
	
	@Column(name="bill_id")
	private Integer billId;
	
	@Column(name="bill_amount")
	private Double billAmount;
	
	@Column(name="due_amount")
	private Double dueAmount;
	
	@Column(name="created_by")
	private Integer createdBy;
	
	@Column(name="updated_by")
	private Integer updatedBy;
	
	@Column(name="created_date")
	@Convert(converter=DateConverter.class)
	private Long createdDate;
	
	@Column(name="updated_date")
	@Convert(converter=DateConverter.class)
	private Long updatedDate;
	
	@Column(name="status")
	private Character status;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="org_id")
	private Integer orgId;
	
	@Column(name = "cr_amount")
	private Double crAmount;
	
	@Column(name = "settlement_date")
	@Convert(converter=DateConverter.class)
	private Long settlementDate;
	
	public Double getCrAmount() {
		return crAmount;
	}

	public void setCrAmount(Double crAmount) {
		this.crAmount = crAmount;
	}

	public Long getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(Long settlementDate) {
		this.settlementDate = settlementDate;
	}

	public int getBillPayeeDetailsId() {
		return billPayeeDetailsId;
	}

	public void setBillPayeeDetailsId(int billPayeeDetailsId) {
		this.billPayeeDetailsId = billPayeeDetailsId;
	}

	public Integer getPayeeId() {
		return payeeId;
	}

	public void setPayeeId(Integer payeeId) {
		this.payeeId = payeeId;
	}

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public Double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(Double billAmount) {
		this.billAmount = billAmount;
	}

	public Double getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(Double dueAmount) {
		this.dueAmount = dueAmount;
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

	public Long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}

	public Long getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Long updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
}
