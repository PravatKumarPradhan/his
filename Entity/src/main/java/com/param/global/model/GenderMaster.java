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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.service.global.model.MPackageMaster;

@NamedQueries({
	
	
	@NamedQuery(name="GET_GENDER_LIST",query="SELECT genderMaster.id as id, "
			+ "genderMaster.code as code, "
			+ "genderMaster.desc as desc, "
			+ "genderMaster.status as status "
			+ "FROM GenderMaster as genderMaster "
			+ "WHERE genderMaster.organizationId=:orgId "
			+ "ORDER BY genderMaster.id DESC "),
	@NamedQuery(name = "GET_GENDER_BY_NAME", query = "SELECT genderMaster.id as id, "
			+ "genderMaster.code as code "
			+ "FROM GenderMaster as genderMaster WHERE LOWER(genderMaster.desc)=:desc OR genderMaster.desc=:desc"),
	@NamedQuery(name = "GET_GENDER_BY_ID", query = "SELECT genderMaster.id as id, "
			+ "genderMaster.desc as desc, "
			+ "genderMaster.code as code, "
			+ "genderMaster.status as status "
			+ "FROM GenderMaster as genderMaster WHERE genderMaster.id=:id"),
	@NamedQuery(name = "GET_GENDER_BY_NAME_NOT_ID", query = "SELECT genderMaster.id as id, "
			+ "genderMaster.code as code, "
			+ "genderMaster.status as status "
			+ "FROM GenderMaster as genderMaster WHERE (LOWER(genderMaster.desc)=:desc OR genderMaster.desc=:desc) AND genderMaster.id!=:id"),
	@NamedQuery(name = "GET_ACTIVE_GENDER_LIST", query = "SELECT genderMaster.id as id, "
			+ "genderMaster.desc as desc "
			+ "FROM GenderMaster as genderMaster WHERE genderMaster.status='A'")

})

@Entity
@Table(name = "m_gender_master", schema = "public")
@SequenceGenerator(name="gender_master_seq", sequenceName="public.gender_master_seq", allocationSize=1)
public class GenderMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gender_master_seq")
	@Column(name = "gender_id")
	private int id;
	
	@Column(name = "gender_code")
	private String code;
	
	@Column(name = "gender_name")
	private String desc;
	
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
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "genderMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MPackageMaster> listMPackageMaster;
	
/*	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private OrganizationMaster organizationMaster;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "genderMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AdmissionNote> admissionNotesList;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "genderMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<WardMaster> wardMastersList;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "genderMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UnknownPatientRegistration> unknownPatientRegistrationsList;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "genderMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<WardMaster> listWardMaster;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "genderMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PrefixMaster> prefixMastersList;
	*/
	
	public Integer getOrganizationId() {
		return organizationId;
	}

	public List<MPackageMaster> getListMPackageMaster() {
		return listMPackageMaster;
	}

	public void setListMPackageMaster(List<MPackageMaster> listMPackageMaster) {
		this.listMPackageMaster = listMPackageMaster;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
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


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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
