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
	
	@NamedQuery(name="GET_ALLERGY_MASTER_LIST_BY_ALLERGY_TYPE_ID",
			query="     SELECT 	alrgyMst.allergyId as allergyId, alrgyMst.allergyName as allergyName, "
					+ "			alrgyMst.allergyDesc as allergyDesc, alrgyMst.allergyTypeId as allergyTypeId,"
					+ "			alrgyMst.status as status "
					+ " FROM 	AllergyMaster alrgyMst"
					+ " WHERE 	alrgyMst.status = 'A' "
					+ " AND 	alrgyMst.unitId=:unitId "
					+ " AND 	alrgyMst.organizationId=:organizationId "
					+ " AND 	alrgyMst.allergyTypeId=:allergyTypeId"
			)
})


@Entity
@Table(name="m_allergy_master",schema="emr")
@SequenceGenerator(name = "allergy_master_seq", sequenceName = "emr.allergy_master_seq", allocationSize = 1)
public class AllergyMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "allergy_master_seq")
	@Column(name = "allergy_id")
	private Integer allergyId;

	@Column(name = "allergy_name")
	private String allergyName;

	@Column(name = "allergy_desc")
	private String allergyDesc;
	
	@Column(name="allergy_type_id")
	private Integer allergyTypeId;
	
	
	@Column(name="status")
	private char status;
	
	@Column(name="created_by")
	private Integer createdBy;
	
	
	@Column(name="updated_by")
	private Integer updatedBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name = "marathi_allergy_name")
	private String marathiAllergyName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "allergy_type_id", insertable = false, nullable = false, updatable = false)
	private AllergyTypeMaster allergyTypeMaster;

	
	
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

	public Integer getAllergyTypeId() {
		return allergyTypeId;
	}

	public void setAllergyTypeId(Integer allergyTypeId) {
		this.allergyTypeId = allergyTypeId;
	}

	public AllergyTypeMaster getAllergyTypeMaster() {
		return allergyTypeMaster;
	}

	public void setAllergyTypeMaster(AllergyTypeMaster allergyTypeMaster) {
		this.allergyTypeMaster = allergyTypeMaster;
	}

	public Integer getAllergyId() {
		return allergyId;
	}

	public void setAllergyId(Integer allergyId) {
		this.allergyId = allergyId;
	}

	public String getAllergyName() {
		return allergyName;
	}

	public void setAllergyName(String allergyName) {
		this.allergyName = allergyName;
	}

	public String getAllergyDesc() {
		return allergyDesc;
	}

	public void setAllergyDesc(String allergyDesc) {
		this.allergyDesc = allergyDesc;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getMarathiAllergyName() {
		return marathiAllergyName;
	}

	public void setMarathiAllergyName(String marathiAllergyName) {
		this.marathiAllergyName = marathiAllergyName;
	}
	
	
	
}
