package com.param.billing.global.transaction.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class BillingGroupSpecialityMapperId implements Serializable{
	@Column(name="billing_group_master_id")
	private Integer billingGroupMasterId;
	
	@Column(name="speciality_master_id")
	private Integer specialityMasterId;

	public Integer getBillingGroupMasterId() {
		return billingGroupMasterId;
	}

	public void setBillingGroupMasterId(Integer billingGroupMasterId) {
		this.billingGroupMasterId = billingGroupMasterId;
	}

	public Integer getSpecialityMasterId() {
		return specialityMasterId;
	}

	public void setSpecialityMasterId(Integer specialityMasterId) {
		this.specialityMasterId = specialityMasterId;
	}
	
	
}
