package com.param.billing.global.model;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@NamedQueries({ @NamedQuery(name = "GET_ACTIVE_BANK_MASTER", query = "SELECT bankMaster.bankId as bankId, "
		+ "bankMaster.bankName as bankName, bankMaster.unitId as unitId,"
		+ " bankMaster.orgnisationId as orgnisationId, bankMaster.status as status" + " FROM BankMaster bankMaster"
		+ " WHERE bankMaster.status='A' AND bankMaster.unitId=:unitId AND bankMaster.orgnisationId=:orgId"),
		@NamedQuery(name = "GET_BANK_MASTER_BY_NAME", query = "SELECT bankMaster.bankId as bankId, bankMaster.bankName as bankName, bankMaster.unitId as unitId,"
				+ " bankMaster.orgnisationId as orgnisationId, bankMaster.status as status"
				+ " FROM BankMaster bankMaster"
				+ " WHERE  LOWER(bankMaster.bankName)=:bankName OR bankMaster.bankName=:bankName  "
				+ " AND bankMaster.orgnisationId=:orgId"),
		@NamedQuery(name = "GET_BANKMASTER_BY_NAME_NOT_ID", query = "SELECT bankMaster.bankId as bankId, bankMaster.bankName as bankName, bankMaster.unitId as unitId,"
				+ " bankMaster.orgnisationId as orgnisationId, bankMaster.status as status"
				+ " FROM BankMaster bankMaster " + " WHERE  bankMaster.bankId !=:bankId"
				+ "   AND  LOWER(bankMaster.bankName)=:bankName OR bankMaster.bankName=:bankName  "),
		@NamedQuery(name = "GET_BANK_MASTER_LIST_BY_ID", query = "SELECT bankMaster.bankId as bankId, bankMaster.bankName as bankName, bankMaster.unitId as unitId,"
				+ " bankMaster.orgnisationId as orgnisationId, bankMaster.status as status ,"
				+ " bankMaster.branchId AS branchId, " + " bankMaster.bankCode AS bankCode, "
				+ " bankMaster.bankAddress AS bankAddress, " + " bankMaster.bankDsc AS bankDsc "
				+ " FROM BankMaster bankMaster"
				+ " WHERE bankMaster.bankId=:bankId AND bankMaster.orgnisationId=:orgId"),
		@NamedQuery(name = "GET_BANK_MASTER_LIST", query = "SELECT bankMaster.bankId as bankId, bankMaster.bankName as bankName, bankMaster.unitId as unitId,"
				+ " bankMaster.orgnisationId as orgnisationId, bankMaster.status as status ,"
				+ " bankMaster.branchId AS branchId, " + " bankMaster.bankCode AS bankCode, "
				+ " bankMaster.bankAddress AS bankAddress, " + " bankMaster.bankDsc AS bankDsc "
				+ " FROM BankMaster bankMaster" + " WHERE bankMaster.orgnisationId=:orgId") })
@Entity
@Table(name = "m_bank_master", schema = "billing")
@SequenceGenerator(name = "bank_master_seq", sequenceName = "billing.bank_master_seq", allocationSize = 1)
public class BankMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_master_seq")
	@Column(name = "bank_id")
	private int bankId;

	@Column(name = "bank_name")
	private String bankName;

	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "orgnisation_id")
	private Integer orgnisationId;

	@Column(name = "status")
	private char status;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "branch_id")
	private Integer branchId;

	@Column(name = "bank_code")
	private String bankCode;

	@Column(name = "bank_address")
	private String bankAddress;

	@Column(name = "bank_desc")
	private String bankDsc;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branch_id", insertable = false, updatable = false)
	BankBranchMaster branchMaster;

	/*
	 * @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =
	 * "bankMaster") private List<DebitNoteDetails> listDebitNoteDetails;
	 */

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "bankMaster")
	private List<PaymentDepositDetails> listPaymentDepositDetails;

	public List<PaymentDepositDetails> getListPaymentDepositDetails() {
		return listPaymentDepositDetails;
	}

	public void setListPaymentDepositDetails(List<PaymentDepositDetails> listPaymentDepositDetails) {
		this.listPaymentDepositDetails = listPaymentDepositDetails;
	}

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getOrgnisationId() {
		return orgnisationId;
	}

	public void setOrgnisationId(Integer orgnisationId) {
		this.orgnisationId = orgnisationId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
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

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public String getBankDsc() {
		return bankDsc;
	}

	public void setBankDsc(String bankDsc) {
		this.bankDsc = bankDsc;
	}

}
