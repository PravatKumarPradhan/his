package com.param.billing.global.transaction.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class CreditBillRecieptStatusLogId implements Serializable{
	@Column(name = "credit_bill_reciept_id")
	private Integer creditBillRecieptId;
	  
	@Column(name = "bill_status_id")
	private Integer billStatusId;

	public Integer getCreditBillRecieptId() {
		return creditBillRecieptId;
	}

	public void setCreditBillRecieptId(Integer creditBillRecieptId) {
		this.creditBillRecieptId = creditBillRecieptId;
	}

	public Integer getBillStatusId() {
		return billStatusId;
	}

	public void setBillStatusId(Integer billStatusId) {
		this.billStatusId = billStatusId;
	}
	
}
