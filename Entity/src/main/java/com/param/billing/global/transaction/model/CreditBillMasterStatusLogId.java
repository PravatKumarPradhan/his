package com.param.billing.global.transaction.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class CreditBillMasterStatusLogId implements Serializable {
	@Column(name = "credit_bill_id")
	private Integer creditBillId;
	  
	@Column(name = "bill_status_id")
	private Integer billStatusId;

	public Integer getCreditBillId() {
		return creditBillId;
	}

	public void setCreditBillId(Integer creditBillId) {
		this.creditBillId = creditBillId;
	}

	public Integer getBillStatusId() {
		return billStatusId;
	}

	public void setBillStatusId(Integer billStatusId) {
		this.billStatusId = billStatusId;
	}
	
}
