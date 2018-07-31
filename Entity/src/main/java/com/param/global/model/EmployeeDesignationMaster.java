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
	
	@NamedQuery(name = "GET_DESIGNATION_MASTER_BY_ID",
	query = "SELECT dm.employeeDesignationId as employeeDesignationId, "
			+ "dm.employeeDesignationDescription as employeeDesignationDescription, "
			+ "dm.employeeDesignationCode as employeeDesignationCode, "
			+ "dm.status as status  "
			+ "FROM EmployeeDesignationMaster as dm "
			+ "WHERE dm.employeeDesignationId=:designationId " 
			+ "AND dm.organizationId=:orgId "),
	@NamedQuery(name = "GET_DESIGNATION_MASTER_LIST", 
	query = "SELECT dm.employeeDesignationId as employeeDesignationId, "
			+ "dm.employeeDesignationDescription as employeeDesignationDescription, " 
			+ "dm.status as status, " 
			+ "dm.employeeDesignationCode as employeeDesignationCode "
			+ "FROM EmployeeDesignationMaster as dm  "
			+ " WHERE dm.organizationId=:orgId "),
	@NamedQuery(name = "GET_DESIGNATION_MASTER_BY_NAME", 
	query = "SELECT dm.employeeDesignationId as employeeDesignationId, "
			+ "dm.employeeDesignationDescription as employeeDesignationDescription, " 
			+ "dm.employeeDesignationCode as employeeDesignationCode, "
			+ "dm.status as status "
			+ "FROM EmployeeDesignationMaster as dm  "
			+ " WHERE LOWER(dm.employeeDesignationDescription)=:designationDesc OR dm.employeeDesignationDescription=:designationDesc " 
			+ "AND dm.organizationId=:orgId"),
	@NamedQuery(name = "GET_DESIGNATION_MASTER_BY_NAME_NOT_BY_ID",
	query = "SELECT dm.employeeDesignationId as employeeDesignationId, "
			+ "dm.employeeDesignationDescription as employeeDesignationDescription, " 
			+ "dm.employeeDesignationCode as employeeDesignationCode "
			+ "FROM EmployeeDesignationMaster as dm  "
			+ "WHERE LOWER(dm.employeeDesignationDescription)=:designationDesc OR dm.employeeDesignationDescription=:designationDesc "
			+ "AND dm.employeeDesignationId !=:designationId")
})
@Entity
@Table(name = "m_employee_designation", schema = "public")
@SequenceGenerator(name = "employee_designation_seq", sequenceName = "public.employee_designation_seq", allocationSize = 1)
public class EmployeeDesignationMaster {

	 @Id
	 @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="employee_designation_seq")
	 @Column(name="employee_designation_id")
	 private int employeeDesignationId;
	 
	 @Column(name="organization_id")
	 private Integer organizationId;
	 
	 @Column(name="employee_designation_code")
	 private String employeeDesignationCode;

	 @Column(name="employee_designation_description")
	 private String employeeDesignationDescription;
	  
	 @Column(name="status")
	 private Character status;
	 
	 @Column(name = "created_date")
	 private Date createdDate;

	 @Column(name = "created_by")
	 private Integer createdBy;
	 
	 @Column(name="updated_by")
	 private Integer updatedBy;
	 
	 @Column(name="updated_date")
	 private Date updatedDate;

	public int getEmployeeDesignationId() {
		return employeeDesignationId;
	}

	public void setEmployeeDesignationId(int employeeDesignationId) {
		this.employeeDesignationId = employeeDesignationId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getEmployeeDesignationCode() {
		return employeeDesignationCode;
	}

	public void setEmployeeDesignationCode(String employeeDesignationCode) {
		this.employeeDesignationCode = employeeDesignationCode;
	}

	public String getEmployeeDesignationDescription() {
		return employeeDesignationDescription;
	}

	public void setEmployeeDesignationDescription(String employeeDesignationDescription) {
		this.employeeDesignationDescription = employeeDesignationDescription;
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
