package com.param.adt.templet.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
	@NamedQuery(name = "GET_PATIENT_TEMPLET_LIST_BY_ID", query = "SELECT patientTemp.patientTempletId as patientTempletId,"
			+ "patientTemp.templetId as templetId,"
			+ "patientTemp.admissionId as admissionId,"
			+ "patientTemp.typeId as typeId "	
			+ "FROM PatientTemplet patientTemp " 		
			+ "WHERE patientTemp.status='A' " 
			+ "AND patientTemp.organizationId=:orgId "
			+ "AND patientTemp.unitId=:unitId " 
			+ "AND patientTemp.typeId=:typeId " 
			+ "AND patientTemp.admissionId=:admissionId  "
			+ "AND patientTemp.templetId=:templeId")
})

public class PatientTemplet {

	@Id
	@Column(name = "patient_templet_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_templet_seq")
	private Integer patientTempletId;

	@Column(name = "admission_id")
	private Integer admissionId;

	@Column(name = "templet_id")
	private Integer templetId;

	@Column(name = "type_id")
	private Integer typeId;

	@Column(name = "templet_text")
	private String templetText;

	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "organization_id")
	private Integer organizationId;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "status")
	private Character status;

	public Integer getPatientTempletId() {
		return patientTempletId;
	}

	public void setPatientTempletId(Integer patientTempletId) {
		this.patientTempletId = patientTempletId;
	}

	public Integer getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(Integer admissionId) {
		this.admissionId = admissionId;
	}

	public Integer getTempletId() {
		return templetId;
	}

	public void setTempletId(Integer templetId) {
		this.templetId = templetId;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTempletText() {
		return templetText;
	}

	public void setTempletText(String templetText) {
		this.templetText = templetText;
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

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

}
