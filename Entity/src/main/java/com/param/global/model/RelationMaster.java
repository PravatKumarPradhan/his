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
	
	@NamedQuery(name="GET_RELATION_LIST", query="SELECT relation.id as id, "
			+ "relation.code as code, "
			+ "relation.desc as desc, "
			+ "relation.status as status "
			+ "FROM RelationMaster as relation "
			+ "WHERE relation.organizationId=:orgId "
			+ "ORDER BY relation.id DESC"),
	
	@NamedQuery(name="GET_RELATION_LIST_BY_NAME", query="SELECT relation.id as id, "
			+ "relation.code as code, "
			+ "relation.desc as desc, "
			+ "relation.status as status "
			+ "FROM RelationMaster as relation WHERE LOWER(relation.desc)=:desc OR relation.desc =:desc"),
	
	@NamedQuery(name="GET_RELATION_LIST_BY_ID", query="SELECT relation.id as id, "
			+ "relation.code as code, "
			+ "relation.desc as desc, "
			+ "relation.status as status "
			+ "FROM RelationMaster as relation WHERE relation.id=:id"),
	
	/*@NamedQuery(name="GET_RELATION_LIST_BY_NAME_NOT_ID", query="SELECT relation.id as id, "
			+ "relation.code as code, "
			+ "relation.desc as desc, "
			+ "relation.status as status "
			+ "FROM RelationMaster as relation WHERE (LOWER(relation.desc)=:desc OR relation.desc =:desc) AND relation.id!=:id)"),*/
	
	@NamedQuery(name="GET_ACTIVE_RELATION_LIST", query="SELECT relation.id as id, "
			+ "relation.code as code, "
			+ "relation.desc as desc, "
			+ "relation.status as status "
			+ "FROM RelationMaster as relation WHERE relation.status='A'")
	
})

@Entity
@Table(name = "m_relation_master", schema = "public")
@SequenceGenerator(name="relation_master_seq", sequenceName="public.relation_master_seq", allocationSize=1)
public class RelationMaster {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "relation_master_seq")
	@Column(name = "relation_id")
	private int id;
	
	@Column(name = "relation_code")
	private String code;
	
	@Column(name = "relation_name")
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
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private OrganizationMaster organizationMaster;*/
	

	public Integer getOrganizationId() {
		return organizationId;
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
