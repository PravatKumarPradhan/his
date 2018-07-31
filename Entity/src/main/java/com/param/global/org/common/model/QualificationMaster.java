package com.param.global.org.common.model;

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
	
	@NamedQuery(name = "GET_QUALIFICATION_MASTER_BY_ID",
	query = "SELECT qm.qualificationId as qualificationId, "
			+ "qm.qualificationDesc as qualificationDesc, "
			+ "qm.qualificationCode as qualificationCode, "
			+ "qm.status as status  "
			+ "FROM QualificationMaster as qm "
			+ "WHERE qm.qualificationId=:qualificationId " 
			+ "AND qm.organizationId=:orgId "),
	@NamedQuery(name = "GET_QUALIFICATION_MASTER_LIST", 
	query = "SELECT qm.qualificationId as qualificationId, "
			+ "qm.qualificationDesc as qualificationDesc, " 
			+ "qm.status as status, " 
			+ "qm.qualificationCode as qualificationCode "
			+ "FROM QualificationMaster as qm  "
			+ " WHERE qm.organizationId=:orgId "),
	@NamedQuery(name = "GET_QUALIFICATION_MASTER_BY_NAME", 
	query = "SELECT qm.qualificationId as qualificationId, "
			+ "qm.qualificationDesc as qualificationDesc, " 
			+ "qm.qualificationCode as qualificationCode, "
			+ "qm.status as status "
			+ "FROM QualificationMaster as qm  "
			+ " WHERE LOWER(qm.qualificationDesc)=:qualificationDesc OR qm.qualificationDesc=:qualificationDesc " 
			+ "AND qm.organizationId=:orgId"),
	@NamedQuery(name = "GET_QUALIFICATION_MASTER_BY_NAME_NOT_BY_ID",
	query = "SELECT qm.qualificationId as qualificationId, "
			+ "qm.qualificationDesc as qualificationDesc, " 
			+ "qm.qualificationCode as qualificationCode "
			+ "FROM QualificationMaster as qm  "
			+ "WHERE LOWER(qm.qualificationDesc)=:qualificationDesc OR qm.qualificationDesc=:qualificationDesc "
			+ "AND qm.qualificationId !=:qualificationId")
})
@Entity
@Table(name="m_qualification_master")
@SequenceGenerator(name="qualification_master_seq",sequenceName="public.qualification_master_seq",allocationSize=1)
public class QualificationMaster {
	@Id
	@Column(name="qualification_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "qualification_master_seq")
	
	private Integer qualificationId;
	
	@Column(name = "qualification_desc")
	private String qualificationDesc;
	
	@Column(name = "qualification_code")
	private String qualificationCode;
	
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

	public Integer getQualificationId() {
		return qualificationId;
	}

	public void setQualificationId(Integer qualificationId) {
		this.qualificationId = qualificationId;
	}

	public String getQualificationDesc() {
		return qualificationDesc;
	}

	public void setQualificationDesc(String qualificationDesc) {
		this.qualificationDesc = qualificationDesc;
	}

	public String getQualificationCode() {
		return qualificationCode;
	}

	public void setQualificationCode(String qualificationCode) {
		this.qualificationCode = qualificationCode;
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

}
