package com.param.global.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries({
		@NamedQuery(name = "SURGERY_MASTER_AUTO_FILL_SEARCH", query = " SELECT 	surgMst.surgery_id as surgery_id, surgMst.surgeryName as surgeryName, surgMst.surgeryCode as surgeryCode, surgMst.status as status, "
				+ "			surgMst.unitId as unitId, surgMst.organizationId as organizationId "
				+ " FROM 	SurgeryMaster surgMst " + " WHERE 	LOWER(surgMst.surgeryName) LIKE :surgeryName "
				+ " AND		surgMst.unitId=:unitId " + " AND		surgMst.organizationId=:organizationId "
				+ " AND 	surgMst.status = 'A' "),
		@NamedQuery(name = "GET_LIST_OF_SURGERY_MASTER", 
				query = " SELECT surgMst.surgery_id AS surgery_id, "
				+ " surgMst.surgeryName AS surgeryName, "
				+ " surgMst.surgeryCode AS surgeryCode,"
				+ " surgMst.status AS status,"
				+ " surgMst.surgeryCodeCpt AS surgeryCodeCpt, "
				+ "	specialityMaster.specialityName AS specialityName, "
				+ " surgeryGradeMaster.surgeryGrade AS surgeryGrade "
				+ " FROM 	SurgeryMaster surgMst "
				+ " INNER JOIN surgMst.specialityMaster AS specialityMaster "
				+ " INNER JOIN surgMst.surgeryGradeMaster AS surgeryGradeMaster " 
				+ " WHERE 	surgMst.unitId=:unitId " 
				+ " AND		surgMst.organizationId=:organizationId "),
				@NamedQuery(name = "GET_SURGERY_MASTER_BY_NAME", 
				query = " SELECT surgMst.surgery_id AS surgery_id, "
				+ " surgMst.surgeryName AS surgeryName "				
				+ " FROM 	SurgeryMaster surgMst "
				+ " WHERE 	surgMst.unitId=:unitId " 
				+ " AND		surgMst.organizationId=:orgId "
				+ " AND	    LOWER(surgMst.surgeryName)=:surgeryName OR surgMst.surgeryName=:surgeryName"),

				@NamedQuery(name="GET_SURGERY_MASTER_LIST_BY_ID",
				query="SELECT surgMaster.surgery_id AS surgery_id ,"
				+ " surgMaster.surgeryName AS surgeryName,"
				+ " surgMaster.surgeryCode AS surgeryCode,"
				+ " surgMaster.surgeryCodeCpt AS surgeryCodeCpt ,"
				+ " surgMaster.status AS status,"
				+ " specialityMaster.specialityId AS specialityId ,"
				+ " specialityMaster.specialityName AS specialityName,"
				+ " surgeryGradeMaster.surgeryGradeId AS surgeryGradeId,"
				+ " surgeryGradeMaster.surgeryGrade AS surgeryGrade "
				+ " FROM SurgeryMaster surgMaster "
				+ " INNER JOIN surgMaster.specialityMaster AS specialityMaster "
				+ " INNER JOIN surgMaster.surgeryGradeMaster AS surgeryGradeMaster "
				+ " WHERE surgMaster.unitId=:unitId " 
				+ " AND	 surgMaster.organizationId=:orgId "
				+ " AND	 surgMaster.surgery_id=:surgeryId ")
		
		})

@Entity
@Table(name = "m_surgery_master", schema = "public")
@SequenceGenerator(name = "surgery_master_seq", sequenceName = "public.surgery_master_seq")
public class SurgeryMaster {

	@Id
	@Column(name = "surgery_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "surgery_master_seq")
	private int surgery_id;

	@Column(name = "organization_id")
	private Integer organizationId;

	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "surgery_name")
	private String surgeryName;

	@Column(name = "surgery_code")
	private String surgeryCode;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "crerated_date")
	private Date createdDate;

	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "status")
	private char status;

	@Column(name = "speciality_id")
	private Integer specialityId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "speciality_id", insertable = false, updatable = false)
	private SpecialityMaster specialityMaster;

	@Column(name = "surgery_code_cpt")
	private String surgeryCodeCpt;

	@Column(name = "surgery_grade_id")
	private Integer surgeryGradeId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "surgery_grade_id", insertable = false, updatable = false)
	private SurgeryGradeMaster surgeryGradeMaster;

	public int getSurgery_id() {
		return surgery_id;
	}

	public void setSurgery_id(int surgery_id) {
		this.surgery_id = surgery_id;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public String getSurgeryName() {
		return surgeryName;
	}

	public void setSurgeryName(String surgeryName) {
		this.surgeryName = surgeryName;
	}

	public String getSurgeryCode() {
		return surgeryCode;
	}

	public void setSurgeryCode(String surgeryCode) {
		this.surgeryCode = surgeryCode;
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

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = (status == '\u0000') ? 'A' : status;
	}

	

	public String getSurgeryCodeCpt() {
		return surgeryCodeCpt;
	}

	public void setSurgeryCodeCpt(String surgeryCodeCpt) {
		this.surgeryCodeCpt = surgeryCodeCpt;
	}
	public SpecialityMaster getSpecialityMaster() {
		return specialityMaster;
	}

	public void setSpecialityMaster(SpecialityMaster specialityMaster) {
		this.specialityMaster = specialityMaster;
	}

	public SurgeryGradeMaster getSurgeryGradeMaster() {
		return surgeryGradeMaster;
	}

	public void setSurgeryGradeMaster(SurgeryGradeMaster surgeryGradeMaster) {
		this.surgeryGradeMaster = surgeryGradeMaster;
	}

	public Integer getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(Integer specialityId) {
		this.specialityId = specialityId;
	}

	public Integer getSurgeryGradeId() {
		return surgeryGradeId;
	}

	public void setSurgeryGradeId(Integer surgeryGradeId) {
		this.surgeryGradeId = surgeryGradeId;
	}
	

}
