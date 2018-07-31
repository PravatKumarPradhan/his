package com.param.adt.master.global.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.adt.admission.model.AdmissionDetails;


@NamedQueries({
	
	@NamedQuery(name="GET_ADMISSION_TYPE_LIST", query="SELECT am.admissionTypeId as admissionTypeId, "
			+ "am.admissionTypeDesc as admissionTypeDesc, "
			+ "am.admissionTypeCode as admissionTypeCode, "
			+ "am.status as status "
			+ "FROM AdmissionTypeMaster am "
			+ "WHERE am.organizationId =:orgId "
			+ "ORDER BY am.admissionTypeId DESC "),
	
	@NamedQuery(name="GET_ADMISSION_TYPE_LIST_BY_ID", query="SELECT am.admissionTypeId as admissionTypeId, "
			+ "am.admissionTypeDesc as admissionTypeDesc, "
			+ "am.admissionTypeCode as admissionTypeCode, "
			+ "am.status as status "
			+ "FROM AdmissionTypeMaster am "
			+ "WHERE am.admissionTypeId=:admissionTypeId "),
	
	@NamedQuery(name="GET_ADMISSION_TYPE_LIST_BY_NAME", query="SELECT am.admissionTypeId as admissionTypeId, "
			+ "am.admissionTypeDesc as admissionTypeDesc "
			+ "FROM AdmissionTypeMaster am "
			+ "WHERE am.admissionTypeDesc=:admissionTypeDesc OR LOWER(am.admissionTypeDesc)=:admissionTypeDesc"),
	
	@NamedQuery(name="GET_ADMISSION_TYPE_LIST_BY_NAME_NOT_ID", query="SELECT am.admissionTypeId as admissionTypeId, "
			+ "am.admissionTypeDesc as admissionTypeDesc "
			+ "FROM AdmissionTypeMaster am "
			+ "WHERE (am.admissionTypeDesc=:admissionTypeDesc OR LOWER(am.admissionTypeDesc)=:admissionTypeDesc) "
			+ "AND am.admissionTypeId!=:admissionTypeId"),
	
	@NamedQuery(name="GET_ACTIVE_ADMISSION_TYPE_LIST", query="SELECT am.admissionTypeId as admissionTypeId, "
			+ "am.admissionTypeDesc as admissionTypeDesc "
			+ "FROM AdmissionTypeMaster am "
			+ "WHERE am.status='A'")
	
	
	
})

@Entity
@Table(name = "m_admission_type_master", schema = "adt")
@SequenceGenerator(name="admission_type_master_seq", sequenceName="adt.admission_type_master_seq", allocationSize=1)
public class AdmissionTypeMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admission_type_master_seq")
	@Column(name = "admission_type_id")
	private int admissionTypeId;
	
	@Column(name = "admission_type_code")
	private String admissionTypeCode;
	
	@Column(name = "admission_type_desc")
	private String admissionTypeDesc;
		
	@Column(name = "status")
	private char status;
	
	@Column(name = "created_by")
	private int createdBy;
	
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private OrganizationMaster organizationMaster;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AdmissionDetails> admissionDetailsList;
	
	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public OrganizationMaster getOrganizationMaster() {
		return organizationMaster;
	}

	public void setOrganizationMaster(OrganizationMaster organizationMaster) {
		this.organizationMaster = organizationMaster;
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

	public int getAdmissionTypeId() {
		return admissionTypeId;
	}

	public void setAdmissionTypeId(int admissionTypeId) {
		this.admissionTypeId = admissionTypeId;
	}

	public String getAdmissionTypeCode() {
		return admissionTypeCode;
	}

	public void setAdmissionTypeCode(String admissionTypeCode) {
		this.admissionTypeCode = admissionTypeCode;
	}

	public String getAdmissionTypeDesc() {
		return admissionTypeDesc;
	}

	public void setAdmissionTypeDesc(String admissionTypeDesc) {
		this.admissionTypeDesc = admissionTypeDesc;
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
	
	
	
}
