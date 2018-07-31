package com.param.billing.global.transaction.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="m_surgery_rate_by_speciality", schema="billing")
@SequenceGenerator(name = "surgery_rate_by_speciality_seq", sequenceName = "billing.surgery_rate_by_speciality_seq", allocationSize = 1)
public class SurgeryRateBySpeciality {
	@Id
	@Column(name = "surgery_rate_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "surgery_rate_by_speciality_seq")
	private int surgeryRateId;
	  
	@Column(name = "surgery_grade_id")
	private Integer surgeryGradeId;
	  
	@Column(name = "speciality_id")
	private Integer specialityId;
	  
	@Column(name = "service_id")
	private Integer serviceId;
	  
	@Column(name = "rate_per_unit")
	private double ratePerUnit;
	
	@Column(name = "rate_per_unit_after_ofc_hour")
	private double ratePerUnitAfterOfcHour;
	  
	@Column(name = "surgery_risk_id")
	private Integer surgeryRiskId;
	
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

	public int getSurgeryRateId() {
		return surgeryRateId;
	}

	public void setSurgeryRateId(int surgeryRateId) {
		this.surgeryRateId = surgeryRateId;
	}

	public Integer getSurgeryGradeId() {
		return surgeryGradeId;
	}

	public void setSurgeryGradeId(Integer surgeryGradeId) {
		this.surgeryGradeId = surgeryGradeId;
	}

	public Integer getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(Integer specialityId) {
		this.specialityId = specialityId;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public double getRatePerUnit() {
		return ratePerUnit;
	}

	public void setRatePerUnit(double ratePerUnit) {
		this.ratePerUnit = ratePerUnit;
	}

	public double getRatePerUnitAfterOfcHour() {
		return ratePerUnitAfterOfcHour;
	}

	public void setRatePerUnitAfterOfcHour(double ratePerUnitAfterOfcHour) {
		this.ratePerUnitAfterOfcHour = ratePerUnitAfterOfcHour;
	}

	public Integer getSurgeryRiskId() {
		return surgeryRiskId;
	}

	public void setSurgeryRiskId(Integer surgeryRiskId) {
		this.surgeryRiskId = surgeryRiskId;
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
