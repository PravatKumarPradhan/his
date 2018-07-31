package com.param.global.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries({

		@NamedQuery(name = "GET_DOCTOR_CATEGORY_MASTER_BY_NAME", 
				query = "SELECT doctorCategoryMaster.doctorCategoryId AS doctorCategoryId "
				+ " FROM DoctorCategoryMaster doctorCategoryMaster "
				+ " WHERE LOWER(doctorCategoryMaster.doctorCategoryDescription)=:doctorDesc OR doctorCategoryMaster.doctorCategoryDescription=:doctorDesc "
				+ " AND doctorCategoryMaster.organizationId=:orgId "),

		@NamedQuery(name = "GET_DOCTOR_CATEGORY_MASTER_BY_ID", query = "SELECT doctorCategoryMaster.doctorCategoryId AS doctorCategoryId, "
				+ "doctorCategoryMaster.doctorCategoryDescription AS doctorCategoryDescription,"
				+ "doctorCategoryMaster.status AS status,"
				+ "doctorCategoryMaster.doctorCategoryCode AS doctorCategoryCode  "
				+ " FROM DoctorCategoryMaster doctorCategoryMaster "
				+ " WHERE doctorCategoryMaster.doctorCategoryId=:doctorId"
				+ " AND doctorCategoryMaster.organizationId=:orgId"),

		@NamedQuery(name = "GET_DOCTOR_CATEGORY_MASTER_LIST", query = "SELECT doctorCategoryMaster.doctorCategoryId AS doctorCategoryId, "
				+ "doctorCategoryMaster.doctorCategoryDescription AS doctorCategoryDescription,"
				+ "doctorCategoryMaster.status AS status,"
				+ "doctorCategoryMaster.doctorCategoryCode AS doctorCategoryCode "

				+ " FROM DoctorCategoryMaster doctorCategoryMaster "
				+ " WHERE doctorCategoryMaster.organizationId=:orgId"),

		@NamedQuery(name = "GET_DOCTOR_CATEGORY_MASTER_BY_NAME_NOT_ID", query = "SELECT doctorCategoryMaster.doctorCategoryId AS doctorCategoryId  "
				+ "FROM DoctorCategoryMaster doctorCategoryMaster "
				+ "WHERE (LOWER(doctorCategoryMaster.doctorCategoryDescription)=:doctorDesc OR doctorCategoryMaster.doctorCategoryDescription=:doctorDesc) "
				+ "AND doctorCategoryMaster.doctorCategoryId !=:doctorId")

})

@Entity
@Table(name = "m_doctor_category", schema = "public")
@SequenceGenerator(name = "doctor_category_seq", sequenceName = "public.doctor_category_seq", allocationSize = 1)
public class DoctorCategoryMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_category_seq")
	@Column(name = "doctor_category_id")
	private int doctorCategoryId;

	@Column(name = "organization_id")
	private Integer organizationId;

	@Column(name = "doctor_category_code")
	private String doctorCategoryCode;

	@Column(name = "doctor_category_description")
	private String doctorCategoryDescription;

	@Column(name = "status")
	private Character status;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;

	public int getDoctorCategoryId() {
		return doctorCategoryId;
	}

	public void setDoctorCategoryId(int doctorCategoryId) {
		this.doctorCategoryId = doctorCategoryId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getDoctorCategoryCode() {
		return doctorCategoryCode;
	}

	public void setDoctorCategoryCode(String doctorCategoryCode) {
		this.doctorCategoryCode = doctorCategoryCode;
	}

	public String getDoctorCategoryDescription() {
		return doctorCategoryDescription;
	}

	public void setDoctorCategoryDescription(String doctorCategoryDescription) {
		this.doctorCategoryDescription = doctorCategoryDescription;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
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

}
