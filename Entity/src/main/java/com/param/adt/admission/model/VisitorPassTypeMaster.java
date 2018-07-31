package com.param.adt.admission.model;

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

import com.param.adt.master.global.model.OrganizationMaster;
import com.param.adt.master.global.model.UnitMaster;


@NamedQueries({
	@NamedQuery(name="GET_VISITOR_TYPE_LIST",query="SELECT visitor.visitorPassTypeId as visitorPassTypeId, "
			+ "visitor.visitorPassTypeDesc as visitorPassTypeDesc "
			+ "FROM VisitorPassTypeMaster visitor "
			+ "WHERE organizationId=:organizationId "
			+ "AND unitId=:unitId")
})

@Entity
@Table(name="m_visitor_pass_type_master",schema="adt")
@SequenceGenerator(name="visitor_pass_type_seq", sequenceName="adt.visitor_pass_type",allocationSize=1)
public class VisitorPassTypeMaster 
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="visitor_pass_type_seq")
	@Column(name="visitor_pass_type_id")
	private int visitorPassTypeId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="visitor_pass_type_code")
	private String visitorPassTypeCode;
	
	@Column(name="visitor_pass_type_desc")
	private String visitorPassTypeDesc;
	
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private OrganizationMaster organizationMaster;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id", insertable = false, updatable = false)
	private UnitMaster unitMaster;

	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "visitorPassTypeMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<VisitorPatientMapper> visitorPatientMappersList;

	
	public int getVisitorPassTypeId() {
		return visitorPassTypeId;
	}


	public void setVisitorPassTypeId(int visitorPassTypeId) {
		this.visitorPassTypeId = visitorPassTypeId;
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


	public String getVisitorPassTypeCode() {
		return visitorPassTypeCode;
	}


	public void setVisitorPassTypeCode(String visitorPassTypeCode) {
		this.visitorPassTypeCode = visitorPassTypeCode;
	}


	public String getVisitorPassTypeDesc() {
		return visitorPassTypeDesc;
	}


	public void setVisitorPassTypeDesc(String visitorPassTypeDesc) {
		this.visitorPassTypeDesc = visitorPassTypeDesc;
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


	public OrganizationMaster getOrganizationMaster() {
		return organizationMaster;
	}


	public void setOrganizationMaster(OrganizationMaster organizationMaster) {
		this.organizationMaster = organizationMaster;
	}


	public UnitMaster getUnitMaster() {
		return unitMaster;
	}


	public void setUnitMaster(UnitMaster unitMaster) {
		this.unitMaster = unitMaster;
	}
	
	
	
}
