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

		@NamedQuery(name = "GET_SURGERY_GRADE_LIST", query = "SELECT surgeryGradeMaster.surgeryGradeId as surgeryGradeId, "
				+ "surgeryGradeMaster.surgeryGrade as surgeryGrade, "
				+ "surgeryGradeMaster.surgeryGradeCode as surgeryGradeCode, " + "surgeryGradeMaster.status as status "
				+ "FROM SurgeryGradeMaster as surgeryGradeMaster "
				+ "WHERE surgeryGradeMaster.orgnisationId=:orgId "
				+ "ORDER BY surgeryGradeMaster.surgeryGradeId DESC"),

		@NamedQuery(name = "GET_SURGERY_GRADE_LIST_BY_ID", query = "SELECT surgeryGradeMaster.surgeryGradeId as surgeryGradeId, "
				+ "surgeryGradeMaster.surgeryGrade as surgeryGrade, "
				+ "surgeryGradeMaster.surgeryGradeCode as surgeryGradeCode, " + "surgeryGradeMaster.status as status "
				+ "FROM SurgeryGradeMaster as surgeryGradeMaster WHERE surgeryGradeMaster.surgeryGradeId=:surgeryGradeId"),

		@NamedQuery(name = "GET_SURGERY_GRADE_LIST_BY_NAME", query = "SELECT surgeryGradeMaster.surgeryGradeId as surgeryGradeId, "
				+ "surgeryGradeMaster.surgeryGrade as surgeryGrade, "
				+ "surgeryGradeMaster.surgeryGradeCode as surgeryGradeCode, " + "surgeryGradeMaster.status as status "
				+ "FROM SurgeryGradeMaster as surgeryGradeMaster WHERE LOWER(surgeryGradeMaster.surgeryGrade)=:surgeryGrade OR surgeryGradeMaster.surgeryGrade=:surgeryGrade"),

		@NamedQuery(name = "GET_SURGERY_GRADE_LIST_BY_NAME_NOT_ID", query = "SELECT surgeryGradeMaster.surgeryGradeId as surgeryGradeId, "
				+ "surgeryGradeMaster.surgeryGrade as surgeryGrade, "
				+ "surgeryGradeMaster.surgeryGradeCode as surgeryGradeCode, " + "surgeryGradeMaster.status as status "
				+ "FROM SurgeryGradeMaster as surgeryGradeMaster WHERE (LOWER(surgeryGradeMaster.surgeryGrade)=:surgeryGrade OR surgeryGradeMaster.surgeryGrade=:surgeryGrade) AND surgeryGradeMaster.surgeryGradeId !=:surgeryGradeId"),

		@NamedQuery(name = "GET_ACTIVE_SURGERY_GRADE_LIST", query = "SELECT surgeryGradeMaster.surgeryGradeId as surgeryGradeId, "
				+ "surgeryGradeMaster.surgeryGrade as surgeryGrade, "
				+ "surgeryGradeMaster.surgeryGradeCode as surgeryGradeCode, " + "surgeryGradeMaster.status as status "
				+ "FROM SurgeryGradeMaster as surgeryGradeMaster WHERE surgeryGradeMaster.status='A'")

})


@Entity
@Table(name="m_surgery_grade_master", schema="public")
@SequenceGenerator(name = "surgery_grade_master_seq", sequenceName = "public.surgery_grade_master_seq", allocationSize = 1)
public class SurgeryGradeMaster {

	@Id
	@Column(name = "surgery_grade_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "surgery_grade_master_seq")
	private int surgeryGradeId;
	  
	@Column(name = "surgery_grade_code")
	private String surgeryGradeCode;
	  
	@Column(name = "surgery_grade")
	private String surgeryGrade;
	
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

	public int getSurgeryGradeId() {
		return surgeryGradeId;
	}

	public void setSurgeryGradeId(int surgeryGradeId) {
		this.surgeryGradeId = surgeryGradeId;
	}

	public String getSurgeryGradeCode() {
		return surgeryGradeCode;
	}

	public void setSurgeryGradeCode(String surgeryGradeCode) {
		this.surgeryGradeCode = surgeryGradeCode;
	}

	public String getSurgeryGrade() {
		return surgeryGrade;
	}

	public void setSurgeryGrade(String surgeryGrade) {
		this.surgeryGrade = surgeryGrade;
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
