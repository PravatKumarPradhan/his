package com.param.billing.global.transaction.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@NamedNativeQueries({
	@NamedNativeQuery(name="GET_DOCTOR_CHARGES_BY_DOCTOR_VISIT_TYPE_CLASS", 
			query="SELECT charges.doctor_consultation_charges_master_id as\"doctorConsultationChargesMasterId\","
					+ " charges.doctor_id as \"doctorId\", coalesce(charges.charges,0) as \"charges\" "
					+ " FROM doctor.t_doctor_consultation_charges_master charges "
					+ " WHERE charges.doctor_id=:doctorId AND charges.visit_type_id=:visitTypeId"
					+ " AND charges.class_id=:classId ")
})
@Entity
@Table(name="t_doctor_consultation_charges_master", schema="doctor")
public class DoctorConsultationChargesMaster {
	@Id
	@Column(name="doctor_consultation_charges_master_id")
	private int doctorConsultationChargesMasterId;
	
	@Column(name="doctor_id")
	private Integer doctorId;
	
	@Column(name="visit_type_id")
	private Integer visitTypeId;
	
	@Column(name="charges")
	private double charges;
	
	@Column(name="updated_by")
	private Integer updatedBy;
	
	@Column(name="updated_date")
	private Long updatedDate;
	
	@Column(name="created_by")
	private Integer createdBy;
	
	@Column(name="created_date")
	private Long createdDate;

	@Column(name="status")
	private Character status;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="class_id")
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
