package com.param.adt.master.global.dto;

import java.util.Date;



public class GeneralLedgerMasterDto {
	
	private int generalLedgerId;
	
	
	private String generalLedgerCode;
	
	
	private String generalLedgerName;
	
	
	private char status;
	
	
	private int createdBy;
	
	
	private Date createdDate;
	
	private int updatedBy;

	private String updatedDate;
	
private Integer organizationId;
	
	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}


	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}


	public String getUpdatedDate() {
		return updatedDate;
	}


	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}


	public int getGeneralLedgerId() {
		return generalLedgerId;
	}


	public void setGeneralLedgerId(int generalLedgerId) {
		this.generalLedgerId = generalLedgerId;
	}


	public String getGeneralLedgerCode() {
		return generalLedgerCode;
	}


	public void setGeneralLedgerCode(String generalLedgerCode) {
		this.generalLedgerCode = generalLedgerCode;
	}


	public String getGeneralLedgerName() {
		return generalLedgerName;
	}


	public void setGeneralLedgerName(String generalLedgerName) {
		this.generalLedgerName = generalLedgerName;
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
	
	
}
