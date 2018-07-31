package com.param.entity.model.doctor;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name="Speciality")
@Table(name="m_speciality_master", schema="doctor")
public class Speciality implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="speciality_id", unique=true, nullable=false)
	private Integer specialityId;

	@Column(name="created_by")
	private Integer createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;

	@Column(name="general_ledger_id")
	private Integer generalLedgerId;

	@Column(name="is_surgical_code", length=1)
	private String isSurgicalCode;

	@Column(name="organization_id")
	private Integer organizationId;

	@Column(name="speciality_code", length=50)
	private String specialityCode;

	@Column(name="speciality_name", length=100)
	private String specialityName;

	@Column(name="specialty_standard_code", length=2147483647)
	private String specialtyStandardCode;

	@Column(name="specialty_standard_used", length=2147483647)
	private String specialtyStandardUsed;

	@Column(length=1)
	private String status;

	@Column(name="updated_by")
	private Integer updatedBy;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	public Speciality() {
	}

	public Integer getSpecialityId() {
		return this.specialityId;
	}

	public void setSpecialityId(Integer specialityId) {
		this.specialityId = specialityId;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getGeneralLedgerId() {
		return this.generalLedgerId;
	}

	public void setGeneralLedgerId(Integer generalLedgerId) {
		this.generalLedgerId = generalLedgerId;
	}

	public String getIsSurgicalCode() {
		return this.isSurgicalCode;
	}

	public void setIsSurgicalCode(String isSurgicalCode) {
		this.isSurgicalCode = isSurgicalCode;
	}

	public Integer getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getSpecialityCode() {
		return this.specialityCode;
	}

	public void setSpecialityCode(String specialityCode) {
		this.specialityCode = specialityCode;
	}

	public String getSpecialityName() {
		return this.specialityName;
	}

	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}

	public String getSpecialtyStandardCode() {
		return this.specialtyStandardCode;
	}

	public void setSpecialtyStandardCode(String specialtyStandardCode) {
		this.specialtyStandardCode = specialtyStandardCode;
	}

	public String getSpecialtyStandardUsed() {
		return this.specialtyStandardUsed;
	}

	public void setSpecialtyStandardUsed(String specialtyStandardUsed) {
		this.specialtyStandardUsed = specialtyStandardUsed;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

}