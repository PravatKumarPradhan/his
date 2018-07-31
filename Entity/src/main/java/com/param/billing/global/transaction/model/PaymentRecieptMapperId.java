package com.param.billing.global.transaction.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@SuppressWarnings("serial")
@Embeddable
public class PaymentRecieptMapperId implements Serializable{
	@Column(name = "payment_reciept_id")
	private Integer paymentRecieptId;
	  
	@Column(name = "payment_details_id")
	private Integer paymentDetailsId;

	public Integer getPaymentRecieptId() {
		return paymentRecieptId;
	}

	public void setPaymentRecieptId(Integer paymentRecieptId) {
		this.paymentRecieptId = paymentRecieptId;
	}

	public Integer getPaymentDetailsId() {
		return paymentDetailsId;
	}

	public void setPaymentDetailsId(Integer paymentDetailsId) {
		this.paymentDetailsId = paymentDetailsId;
	}
	
}
