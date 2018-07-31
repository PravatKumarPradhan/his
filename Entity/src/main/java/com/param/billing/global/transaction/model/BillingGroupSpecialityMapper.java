package com.param.billing.global.transaction.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.param.billing.global.model.BillingGroupMaster;
import com.param.global.model.SpecialityMaster;
@NamedQueries({
		@NamedQuery(name="DELETE_BILLING_GROUP_SPECIALITY_MAPPER", 
			      query ="DELETE FROM BillingGroupSpecialityMapper mapper"
			  		   + " WHERE mapper.billingGroupSpecialityMapperId.billingGroupMasterId =:billingGroupId")
})
@Entity
@Table(name="t_billing_group_speciality_mapper", schema="billing")
public class BillingGroupSpecialityMapper {
	@EmbeddedId
	private BillingGroupSpecialityMapperId billingGroupSpecialityMapperId;
	
	@Column(name="status")
	private char status;
	
	@Column(name="created_by")
	private Integer createdBy;
	
	@Column(name="crated_date")
	private Date cratedDate;
	
	@Column(name="updated_by")
	private Integer updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="orgnization_id")
	private Integer orgnizationId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "billing_group_master_id", insertable = false, updatable = false)
	private BillingGroupMaster billingGroupMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "speciality_master_id", insertable = false, updatable = false)
	private SpecialityMaster specialityMaster;
	
	public BillingGroupSpecialityMapperId getBillingGroupSpecialityMapperId() {
		return billingGroupSpecialityMapperId;
	}

	public void setBillingGroupSpecialityMapperId(BillingGroupSpecialityMapperId billingGroupSpecialityMapperId) {
		this.billingGroupSpecialityMapperId = billingGroupSpecialityMapperId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = (status == '\u0000') ? 'I' : status;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCratedDate() {
		return cratedDate;
	}

	public void setCratedDate(Date cratedDate) {
		this.cratedDate = cratedDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getOrgnizationId() {
		return orgnizationId;
	}

	public void setOrgnizationId(Integer orgnizationId) {
		this.orgnizationId = orgnizationId;
	}

	public BillingGroupMaster getBillingGroupMaster() {
		return billingGroupMaster;
	}

	public void setBillingGroupMaster(BillingGroupMaster billingGroupMaster) {
		this.billingGroupMaster = billingGroupMaster;
	}

	public SpecialityMaster getSpecialityMaster() {
		return specialityMaster;
	}

	public void setSpecialityMaster(SpecialityMaster specialityMaster) {
		this.specialityMaster = specialityMaster;
	}
	
}
