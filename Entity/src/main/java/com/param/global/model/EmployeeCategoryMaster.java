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

		@NamedQuery(name = "GET_EMPLOYEE_CATEGORY_MASTER_BY_NAME", query = "SELECT employeeCategoryMaster.employeeCategoryId AS employeeCategoryId "
				+ " FROM EmployeeCategoryMaster employeeCategoryMaster "
				+ " WHERE LOWER(employeeCategoryMaster.employeeCategoryDescription)=:empName OR employeeCategoryMaster.employeeCategoryDescription=:empName "
				+ " AND employeeCategoryMaster.organizationId=:orgId "),

		@NamedQuery(name = "GET_EMPLOYEE_CATEGORY_MASTER_BY_ID", query = "SELECT employeeCategoryMaster.employeeCategoryId AS employeeCategoryId, "
				+ "employeeCategoryMaster.employeeCategoryDescription AS employeeCategoryDescription,"
				+ "employeeCategoryMaster.status AS status,"
				+ "employeeCategoryMaster.employeeCategoryCode AS employeeCategoryCode  "
				+ " FROM EmployeeCategoryMaster employeeCategoryMaster "
				+ " WHERE employeeCategoryMaster.employeeCategoryId=:employeeId"
				+ " AND employeeCategoryMaster.organizationId=:orgId"),

		@NamedQuery(name = "GET_EMPLOYEE_CATEGORY_MASTER_LIST", query = "SELECT employeeCategoryMaster.employeeCategoryId AS employeeCategoryId, "
				+ "employeeCategoryMaster.employeeCategoryDescription AS employeeCategoryDescription,"
				+ "employeeCategoryMaster.status AS status,"
				+ "employeeCategoryMaster.employeeCategoryCode AS employeeCategoryCode "

				+ " FROM EmployeeCategoryMaster employeeCategoryMaster "
				+ " WHERE employeeCategoryMaster.organizationId=:orgId"),

		@NamedQuery(name = "GET_EMPLOYEE_CATEGORY_MASTER_BY_NAME_NOT_ID", query = "SELECT employeeCategoryMaster.employeeCategoryId AS employeeCategoryId  "
				+ "FROM EmployeeCategoryMaster employeeCategoryMaster "
				+ "WHERE (LOWER(employeeCategoryMaster.employeeCategoryDescription)=:empName OR employeeCategoryMaster.employeeCategoryDescription=:empName) "
				+ "AND employeeCategoryMaster.employeeCategoryId !=:employeeId")

})

@Entity
@Table(name = "m_employee_category", schema = "public")
@SequenceGenerator(name = "employee_category_seq", sequenceName = "public.employee_category_seq", allocationSize = 1)
public class EmployeeCategoryMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_category_seq")
	@Column(name = "employee_category_id")
	private int employeeCategoryId;

	@Column(name = "organization_id")
	private Integer organizationId;

	@Column(name = "employee_category_code")
	private String employeeCategoryCode;

	@Column(name = "employee_category_description")
	private String employeeCategoryDescription;

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

	public int getEmployeeCategoryId() {
		return employeeCategoryId;
	}

	public void setEmployeeCategoryId(int employeeCategoryId) {
		this.employeeCategoryId = employeeCategoryId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getEmployeeCategoryCode() {
		return employeeCategoryCode;
	}

	public void setEmployeeCategoryCode(String employeeCategoryCode) {
		this.employeeCategoryCode = employeeCategoryCode;
	}

	public String getEmployeeCategoryDescription() {
		return employeeCategoryDescription;
	}

	public void setEmployeeCategoryDescription(String employeeCategoryDescription) {
		this.employeeCategoryDescription = employeeCategoryDescription;
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
