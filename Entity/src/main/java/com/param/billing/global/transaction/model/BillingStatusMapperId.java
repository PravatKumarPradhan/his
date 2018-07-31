package com.param.billing.global.transaction.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BillingStatusMapperId implements Serializable{
	private static final long serialVersionUID = 3575646305183668472L;
	
	@Column(name = "bill_id")
	private int  billId;
	 
	@Column(name = "bill_status_id")
	private int billStatusId;

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public int getBillStatusId() {
		return billStatusId;
	}

	public void setBillStatusId(int billStatusId) {
		this.billStatusId = billStatusId;
	}
}
