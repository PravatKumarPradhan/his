package com.param.opd.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name="m_ip_visit_rule_master", schema="opd")
@SequenceGenerator(name = "ip_visit_rule_master_seq", sequenceName = "opd.ip_visit_rule_master_seq", allocationSize = 1)
public class IpVisitRuleMaster {
	@Id
	@Column(name = "ip_visit_rule_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ip_visit_rule_master_seq")
	private int ipVisitRuleId;

	@Column(name = "no_of_visit_allow_in_day")
	private int noOfVisitAllowInDay;

	@Column(name = "bed_category_id")
	private int bedCategoryId;

	@Column(name = "is_icu_applicable")
	private char isIcuApplicable;

	@Column(name = "speciality_id")
	private Integer specialityId;
	
	@Column(name = "unit_id")
	private Integer unitId;	
	
	@Column(name = "orgnisation_id")
	private Integer orgnisationId;

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

	public int getIpVisitRuleId() {
		return ipVisitRuleId;
	}

	public void setIpVisitRuleId(int ipVisitRuleId) {
		this.ipVisitRuleId = ipVisitRuleId;
	}

	public int getNoOfVisitAllowInDay() {
		return noOfVisitAllowInDay;
	}

	public void setNoOfVisitAllowInDay(int noOfVisitAllowInDay) {
		this.noOfVisitAllowInDay = noOfVisitAllowInDay;
	}

	public int getBedCategoryId() {
		return bedCategoryId;
	}

	public void setBedCategoryId(int bedCategoryId) {
		this.bedCategoryId = bedCategoryId;
	}

	public char getIsIcuApplicable() {
		return isIcuApplicable;
	}

	public void setIsIcuApplicable(char isIcuApplicable) {
		this.isIcuApplicable = isIcuApplicable;
	}

	public Integer getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(Integer specialityId) {
		this.specialityId = specialityId;
	}

	public Integer getOrgnisationId() {
		return orgnisationId;
	}

	public void setOrgnisationId(Integer orgnisationId) {
		this.orgnisationId = orgnisationId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
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
	
}
