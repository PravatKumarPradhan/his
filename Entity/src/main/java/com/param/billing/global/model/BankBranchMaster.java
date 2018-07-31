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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries({ 
		@NamedQuery(name = "GET_BANK_BRANCH_MASTER_BY_NAME",
				query = "SELECT bankMaster.branchId as branchId, "
					+ " bankMaster.branchCode as branchCode "
				+ " FROM BankBranchMaster bankMaster"
				+ " WHERE  LOWER(bankMaster.branchDesc)=:branchDesc OR bankMaster.branchDesc=:branchDesc  "
				+ " AND bankMaster.orgnisationId=:orgId"),
		@NamedQuery(name = "GET_BANK_BRANCH_MASTER_BY_NAME_NOT_ID", 
		query = "SELECT bankMaster.branchId as branchId,"
				+ " bankMaster.branchCode as branchCode "
				+ " FROM BankBranchMaster bankMaster " 
				+ " WHERE  bankMaster.branchId !=:branchId "
				+ "  AND  LOWER(bankMaster.branchDesc)=:branchDesc OR bankMaster.branchDesc=:branchDesc  "),
		@NamedQuery(name = "GET_BANK_BRANCH_MASTER_BY_ID", 
		query = "SELECT bankMaster.branchId as branchId,"
				+ " bankMaster.branchCode as branchCode,"
				+ " bankMaster.orgnisationId as orgnisationId,"
				+ " bankMaster.status as status ,"
				+ " bankMaster.branchDesc AS branchDesc "				
				+ " FROM BankBranchMaster bankMaster"
				+ " WHERE bankMaster.branchId=:branchId "
				+ " AND bankMaster.orgnisationId=:orgId "),
		@NamedQuery(name = "GET_BANK_BRANCH_MASTER_LIST",
		query = "SELECT bankMaster.branchCode as branchCode, "
				+ " bankMaster.orgnisationId as orgnisationId,"
				+ " bankMaster.status as status ,"
				+ " bankMaster.branchId AS branchId, " 
				+ " bankMaster.branchDesc AS branchDesc "			
				+ " FROM BankBranchMaster bankMaster " 
				+ " WHERE bankMaster.orgnisationId=:orgId")
		})
@Entity
@Table(name = "m_bank_branch_master", schema = "billing")
@SequenceGenerator(name = "bank_branch_master_seq", sequenceName = "billing.bank_branch_master_seq", allocationSize = 1)
public class BankBranchMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_branch_master_seq")
	@Column(name = "branch_id")
	private Integer branchId;

	@Column(name = "branch_code")
	private String branchCode;

	@Column(name = "branch_desc")
	private String branchDesc;

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

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchDesc() {
		return branchDesc;
	}

	public void setBranchDesc(String branchDesc) {
		this.branchDesc = branchDesc;
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

}
