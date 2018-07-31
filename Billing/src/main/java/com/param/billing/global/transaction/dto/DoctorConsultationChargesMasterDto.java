package com.param.billing.global.transaction.dto;

public class DoctorConsultationChargesMasterDto {
	private int doctorConsultationChargesMasterId;
	
	private Integer doctorId;
	
	private Integer visitTypeId;
	
	private double charges;
	
	private Integer updatedBy;
	
	private Long updatedDate;
	
	private Integer createdBy;
	
	private Long createdDate;

	private Character status;
	
	private Integer unitId;
	
	private Integer organizationId;
	
	private Integer classId;

	public int getDoctorConsultationChargesMasterId() {
		return doctorConsultationChargesMasterId;
	}

	public void setDoctorConsultationChargesMasterId(int doctorConsultationChargesMasterId) {
		this.doctorConsultationChargesMasterId = doctorConsultationChargesMasterId;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public double getCharges() {
		return charges;
	}

	public void setCharges(double charges) {
		this.charges = charges;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Long updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
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

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	
}
