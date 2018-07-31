package com.param.billing.global.transaction.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@SuppressWarnings("serial")
@Embeddable
public class CreditBillMasterRecieptMapperId implements Serializable{
	@Column(name = "credit_bill_id")
	private Integer creditBillId;
	  
	@Column(name = "credit_bill_reciept_id")
	private Integer creditBillRecieptId;

	public Integer getCreditBillId() {
		return creditBillId;
	}

	public void setCreditBillId(Integer creditBillId) {
		this.creditBillId = creditBillId;
	}

	public Integer getCreditBillRecieptId() {
		return creditBillRecieptId;
	}

	public void setCreditBillRecieptId(Integer creditBillRecieptId) {
		this.creditBillRecieptId = creditBillRecieptId;
	}
	
}
