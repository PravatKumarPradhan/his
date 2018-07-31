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
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.billing.global.transaction.model.BillingGroupSpecialityMapper;
@NamedQueries({
	@NamedQuery(name="GET_BILLING_GROUP_MASTER_BY_UNIT",
			  query ="SELECT billgroupMst.billingGroupId as billingGroupId, billgroupMst.billingGroup as billingGroup, billgroupMst.billingCode as billingCode, "
			  		+ " billgroupMst.billingGroupDesc as billingGroupDesc,billgroupMst.status as status "
			  		+ " FROM BillingGroupMaster billgroupMst"
			  		+ " WHERE billgroupMst.orgnisationId =:orgId AND billgroupMst.unitId =:unitId"
			  		+ " ORDER BY billgroupMst.billingGroupId DESC"),
	
	@NamedQuery(name="CHANGE_BILLING_GROUP_MASTER_STATUS",
			  query ="UPDATE BillingGroupMaster SET status=:status"
			  		+ " WHERE billingGroupId =:billingGroupId AND unitId =:unitId AND orgnisationId =:orgId"),
	
	@NamedQuery(name="GET_BILLING_GROUP_MASTER_COUNT",
			  query ="SELECT COUNT(*) FROM BillingGroupMaster billGroupMst"
			  		+ " WHERE billGroupMst.orgnisationId =:orgId AND billGroupMst.unitId =:unitId")
})

@NamedNativeQueries({
	@NamedNativeQuery(name="GET_BILLING_GROUP_MASTER_BY_ID",
					 query="select bill.billing_group_id as \"billingGroupId\", bill.billing_group as \"billingGroup\", bill.billing_code as \"billingCode\", bill.status as \"status\" , "  
					 		+" bill.billing_group_desc as \"billingGroupDesc\", specialityMst.speciality_id as \"specialityId\",specialityMst.speciality_name as \"specialityName\", "
					 		+" bill.unit_id as \"unitId\", bill.orgnisation_id as \"orgnisationId\""  
					 		+" from billing.m_billing_group_master bill   " 
					 		+" left join billing.t_billing_group_speciality_mapper mapper " 
					 		+"    on mapper.billing_group_master_id=bill.billing_group_id "  
					 		+" left join doctor.m_speciality_master specialityMst " 
					 		+"	  on mapper.speciality_master_id=specialityMst.speciality_id " 
					 		+" where bill.billing_group_id=:billingGroupId and bill.unit_id=:unitId and bill.orgnisation_id=:orgId")
})
@Entity
@Table(name="m_billing_group_master", schema="billing")
@SequenceGenerator(name="billing_group_master_seq",sequenceName="billing.billing_group_master_seq", allocationSize=1)
public class BillingGroupMaster {
	@Id
	@Column(name = "billing_group_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "billing_group_master_seq")
	private int billingGroupId;
	
	@Column(name = "billing_group")
	private String billingGroup;
	
	@Column(name = "billing_code")
	private String billingCode;
	
	@Column(name = "billing_group_desc")
	private String billingGroupDesc;
	
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
	
	@Column(name = "unit_id")
	private Integer unitId;
	
	@Column(name = "orgnisation_id")
	private Integer orgnisationId;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "billingGroupMaster")
	private List<BillingGroupSpecialityMapper> listBillingGroupSpecialityMapper;

	public int getBillingGroupId() {
		return billingGroupId;
	}

	public void setBillingGroupId(int billingGroupId) {
		this.billingGroupId = billingGroupId;
	}

	public String getBillingGroup() {
		return billingGroup;
	}

	public void setBillingGroup(String billingGroup) {
		this.billingGroup = billingGroup;
	}

	public String getBillingCode() {
		return billingCode;
	}

	public void setBillingCode(String billingCode) {
		this.billingCode = billingCode;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = (status == '\u0000') ? 'I' : status;
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

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getOrgnisationId() {
		return orgnisationId;
	}

	public void setOrgnisationId(Integer orgnisationId) {
		this.orgnisationId = orgnisationId;
	}

	public List<BillingGroupSpecialityMapper> getListBillingGroupSpecialityMapper() {
		return listBillingGroupSpecialityMapper;
	}

	public void setListBillingGroupSpecialityMapper(List<BillingGroupSpecialityMapper> listBillingGroupSpecialityMapper) {
		this.listBillingGroupSpecialityMapper = listBillingGroupSpecialityMapper;
	}

	public String getBillingGroupDesc() {
		return billingGroupDesc;
	}

	public void setBillingGroupDesc(String billingGroupDesc) {
		this.billingGroupDesc = billingGroupDesc;
	}

}
