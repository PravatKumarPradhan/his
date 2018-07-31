package com.param.entity.model.master;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "SurgeryGrade")
@Table(name = "m_surgery_grade_master", schema = "public")
public class SurgeryGrade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "surgery_grade_id", unique = true, nullable = false)
	private Integer surgeryGradeId;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "created_id")
	private Integer createdId;

	@Column(name = "organisation_id")
	private Integer organisationId;

	@Column(length = 1)
	private String status;

	@Column(name = "surgery_grade", length = 200)
	private String surgeryGrade;

	@Column(name = "surgery_grade_code", length = 200)
	private String surgeryGradeCode;

	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	private Timestamp updatedDate;

	public SurgeryGrade() {
	}

	public Integer getSurgeryGradeId() {
		return this.surgeryGradeId;
	}

	public void setSurgeryGradeId(Integer surgeryGradeId) {
		this.surgeryGradeId = surgeryGradeId;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCreatedId() {
		return this.createdId;
	}

	public void setCreatedId(Integer createdId) {
		this.createdId = createdId;
	}

	public Integer getOrganisationId() {
		return this.organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSurgeryGrade() {
		return this.surgeryGrade;
	}

	public void setSurgeryGrade(String surgeryGrade) {
		this.surgeryGrade = surgeryGrade;
	}

	public String getSurgeryGradeCode() {
		return this.surgeryGradeCode;
	}

	public void setSurgeryGradeCode(String surgeryGradeCode) {
		this.surgeryGradeCode = surgeryGradeCode;
	}

	public Integer getUnitId() {
		return this.unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
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
