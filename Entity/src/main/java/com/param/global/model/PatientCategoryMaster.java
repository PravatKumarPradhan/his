package com.param.global.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.service.global.model.MPackageMaster;

@NamedQueries({

		@NamedQuery(name = "GET_ACTIVE_PATIENT_CATEGORY_LIST", query = "SELECT pc.patientCategoryId as patientCategoryId, "
				+ "pc.patientCategory as patientCategory " + "FROM PatientCategoryMaster as pc "
				+ "WHERE pc.status='A'"),
		@NamedQuery(name = "GET_PATIENT_CATEGORY_MASTER_BY_ID", query = "SELECT pc.patientCategoryId as patientCategoryId, "
				+ "pc.patientCategory as patientCategory, " + "pc.isForeginerCategory as isForeginerCategory, "
						+ " pc.status as status,  "
				+ "pc.patientCategoryCode as patientCategoryCode "
				+ "FROM PatientCategoryMaster as pc "
				+ "WHERE pc.patientCategoryId=:patientCategoryId " + "AND pc.organizationId=:orgId "),
		@NamedQuery(name = "GET_PATIENT_CATEGORY_MASTER_LIST", query = "SELECT pc.patientCategoryId as patientCategoryId, "
				+ "pc.patientCategory as patientCategory, " 
				+ "pc.status as status, " 
				+ "pc.isForeginerCategory as isForeginerCategory, "
				+ "pc.patientCategoryCode as patientCategoryCode " + "FROM PatientCategoryMaster as pc  "
				+ " WHERE pc.organizationId=:orgId "),
		@NamedQuery(name = "GET_PATIENT_CATEGORY_MASTER_BY_NAME", query = "SELECT pc.patientCategoryId as patientCategoryId, "
				+ "pc.patientCategory as patientCategory, " + "pc.isForeginerCategory as isForeginerCategory, "
				+ "pc.patientCategoryCode as patientCategoryCode " + "FROM PatientCategoryMaster as pc  "
				+ " WHERE LOWER(pc.patientCategory)=:category OR pc.patientCategory=:category " + "AND pc.organizationId=:orgId"),
		@NamedQuery(name = "GET_PATIENT_CATEGORY_MASTER_BY_NAME_NOT_ID",
		query = "SELECT pc.patientCategoryId as patientCategoryId, "
				+ "pc.patientCategory as patientCategory, " 
				+ "pc.isForeginerCategory as isForeginerCategory, "
				+ "pc.patientCategoryCode as patientCategoryCode "
				+ "FROM PatientCategoryMaster as pc  "
				+ "WHERE LOWER(pc.patientCategory)=:category OR pc.patientCategory=:category "
				+ "AND pc.patientCategoryId !=:patientCategoryId"),
	
	@NamedQuery(name="GET_PATIENT_CATEGORY_LIST_BY_ORG", 
			query="SELECT pc.patientCategoryId as id, "
			+ "pc.patientCategory as name "
			+ "FROM PatientCategoryMaster as pc "
			+ "WHERE pc.status='A' "
			+ "AND	pc.organizationId=:orgId ")
})
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "m_patient_category_master", schema = "public")
@SequenceGenerator(name = "patient_category_master_seq", sequenceName = "public.patient_category_master_seq", allocationSize = 1)
public class PatientCategoryMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_category_master_seq")
	@Column(name = "patient_category_id")
	private int patientCategoryId;

	@Column(name = "patient_category")
	private String patientCategory;

	@Column(name = "is_foreginer_category")
	private char isForeginerCategory;

	@Column(name = "status")
	private char status;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "organization_id")
	private Integer organizationId;

	@Column(name = "patient_category_code")
	private String patientCategoryCode;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "patientCategoryMaster")
	private List<MPackageMaster> listMPackageMaster;

	public List<MPackageMaster> getListMPackageMaster() {
		return listMPackageMaster;
	}

	public void setListMPackageMaster(List<MPackageMaster> listMPackageMaster) {
		this.listMPackageMaster = listMPackageMaster;
	}

	public int getPatientCategoryId() {
		return patientCategoryId;
	}

	public void setPatientCategoryId(int patientCategoryId) {
		this.patientCategoryId = patientCategoryId;
	}

	public String getPatientCategory() {
		return patientCategory;
	}

	public void setPatientCategory(String patientCategory) {
		this.patientCategory = patientCategory;
	}

	public char getIsForeginerCategory() {
		return isForeginerCategory;
	}

	public void setIsForeginerCategory(char isForeginerCategory) {
		this.isForeginerCategory = isForeginerCategory;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getPatientCategoryCode() {
		return patientCategoryCode;
	}

	public void setPatientCategoryCode(String patientCategoryCode) {
		this.patientCategoryCode = patientCategoryCode;
	}

}
