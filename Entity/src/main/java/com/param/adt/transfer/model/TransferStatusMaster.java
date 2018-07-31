package com.param.adt.transfer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="m_transfer_status_master",schema="adt")
public class TransferStatusMaster 
{
	@Id
	@Column(name="transfer_status_id")
	private Integer transferStatusId;
	
	@Column(name="transfer_status_code")
	private String transferStatusCode;
	
	@Column(name="transfer_status_desc")
	private String transferStatusDesc;

	public Integer getTransferStatusId() {
		return transferStatusId;
	}

	public void setTransferStatusId(Integer transferStatusId) {
		this.transferStatusId = transferStatusId;
	}

	public String getTransferStatusCode() {
		return transferStatusCode;
	}

	public void setTransferStatusCode(String transferStatusCode) {
		this.transferStatusCode = transferStatusCode;
	}

	public String getTransferStatusDesc() {
		return transferStatusDesc;
	}

	public void setTransferStatusDesc(String transferStatusDesc) {
		this.transferStatusDesc = transferStatusDesc;
	}
	
	
}
