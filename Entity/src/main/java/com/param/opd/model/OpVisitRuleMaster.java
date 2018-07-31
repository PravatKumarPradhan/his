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
@Table(name="m_op_visit_rule_master", schema="opd")
@SequenceGenerator(name = "op_visit_rule_master_seq", sequenceName = "opd.op_visit_rule_master_seq", allocationSize = 1)
public class OpVisitRuleMaster {
	@Id
	@Column(name = "op_visit_rule_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "op_visit_rule_master_seq")
	private int opVisitRuleId;
	
	@Column(name = "unit_id")
	private Integer unitId;
		
	@Column(name = "organization_id")
	private Integer organizationId;
	
	@Column(name = "speciality_id")
	private Integer specialityId;
	
	@Column(name = "followup_visit_days")
	private Integer followupVisitDays;
	
	@Column(name = "followup_visit_count")
	private Integer followupVisitCount;
	
	@Column(name = "secondary_visit_days")
	private Integer secondaryVisitDays;
	
	@Column(name = "secondary_visit_count")
	private Integer secondaryVisitCount;
	
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

	public int getOpVisitRuleId() {
		return opVisitRuleId;
	}

	public void setOpVisitRuleId(int opVisitRuleId) {
		this.opVisitRuleId = opVisitRuleId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(Integer specialityId) {
		this.specialityId = specialityId;
	}

	public Integer getFollowupVisitDays() {
		return followupVisitDays;
	}

	public void setFollowupVisitDays(Integer followupVisitDays) {
		this.followupVisitDays = followupVisitDays;
	}

	public Integer getFollowupVisitCount() {
		return followupVisitCount;
	}

	public void setFollowupVisitCount(Integer followupVisitCount) {
		this.followupVisitCount = followupVisitCount;
	}

	public Integer getSecondaryVisitDays() {
		return secondaryVisitDays;
	}

	public void setSecondaryVisitDays(Integer secondaryVisitDays) {
		this.secondaryVisitDays = secondaryVisitDays;
	}

	public Integer getSecondaryVisitCount() {
		return secondaryVisitCount;
	}

	public void setSecondaryVisitCount(Integer secondaryVisitCount) {
		this.secondaryVisitCount = secondaryVisitCount;
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

	
}
