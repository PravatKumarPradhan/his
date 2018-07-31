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
	
	@NamedQuery(name="GET_PREFIX_LIST",query="SELECT prefix.prefixId as prefixId, "
			+ "prefix.prefixCode as prefixCode, "
			+ "prefix.prefixDesc as prefixDesc, "
			+ "prefix.status as status, "
			+ "prefix.id as id, "
			+ "gen.desc as desc "
			+ "FROM PrefixMaster prefix "
			+ "INNER JOIN prefix.genderMaster as gen "
			+ "WHERE prefix.organizationId=:orgId "
			+ "ORDER BY prefix.prefixId DESC"),
	
	@NamedQuery(name="GET_PREFIX_LIST_BY_ID",query="SELECT prefix.prefixId as prefixId, "
			+ "prefix.prefixCode as prefixCode, "
			+ "prefix.prefixDesc as prefixDesc, "
			+ "prefix.status as status, "
			+ "prefix.id as id "
			+ "FROM PrefixMaster as prefix WHERE prefix.prefixId=:prefixId"),
	
	@NamedQuery(name="GET_PREFIX_LIST_BY_NAME",query="SELECT prefix.prefixId as prefixId, "
			+ "prefix.prefixDesc as prefixDesc "
			+ "FROM PrefixMaster as prefix "
			+ "WHERE LOWER(prefix.prefixDesc)=:prefixName OR prefix.prefixDesc=:prefixName"),
	
	@NamedQuery(name="GET_PREFIX_LIST_BY_NAME_NOT_ID",query="SELECT prefix.prefixId as prefixId, "
			+ "prefix.prefixDesc as prefixDesc "
			+ "FROM PrefixMaster as prefix "
			+ "WHERE (LOWER(prefix.prefixDesc)=:prefixName OR prefix.prefixDesc=:prefixName) "
			+ "AND prefix.prefixId!=:prefixId"),
	
	@NamedQuery(name="GET_ACTIVE_PREFIX_LIST",query="SELECT prefix.prefixId as prefixId, "
			+ "prefix.prefixDesc as prefixDesc "
			+ "FROM PrefixMaster as prefix "
			+ "WHERE prefix.status='A'")
	
})


@Entity
@Table(name = "m_prefix_master", schema = "public")
@SequenceGenerator(name="prefix_master_seq", sequenceName="public.prefix_master_seq", allocationSize=1)
public class PrefixMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prefix_master_seq")
	@Column(name = "prefix_id")
	private int prefixId;
	
	@Column(name = "prefix_code")
	private String prefixCode;
	
	@Column(name = "prefix_desc")
	private String prefixDesc;
	
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
	
	@Column(name="gender_id")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gender_id", insertable = false, updatable = false)
	private GenderMaster genderMaster;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "prefixMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PatientMaster> listPatientMaster;*/
	
	
	
	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GenderMaster getGenderMaster() {
		return genderMaster;
	}

	public void setGenderMaster(GenderMaster genderMaster) {
		this.genderMaster = genderMaster;
	}

	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "prefixMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TempPatientMaster> listTempPatientMaster;
	*/

	
	public String getPrefixCode() {
		return prefixCode;
	}

	public void setPrefixCode(String prefixCode) {
		this.prefixCode = prefixCode;
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

	public int getPrefixId() {
		return prefixId;
	}

	public void setPrefixId(int prefixId) {
		this.prefixId = prefixId;
	}

	public String getPrefixDesc() {
		return prefixDesc;
	}

	public void setPrefixDesc(String prefixDesc) {
		this.prefixDesc = prefixDesc;
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

	/*public List<PatientMaster> getListPatientMaster() {
		return listPatientMaster;
	}

	public void setListPatientMaster(List<PatientMaster> listPatientMaster) {
		this.listPatientMaster = listPatientMaster;
	}*/

	
}
