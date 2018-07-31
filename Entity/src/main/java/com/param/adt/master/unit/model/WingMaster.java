package com.param.adt.master.unit.model;

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
import com.param.global.model.ClinicMaster;

@NamedQueries({
	
	@NamedQuery(name="GET_WING_MASTER_LIST",
				query="SELECT 		wing.wingId as wingId , wing.wingCode as wingCode , wing.wingDesc as wingDesc ,"
						+ "			wing.organizationId as organizationId , wing.unitId as unitId , wing.status as status ,"
						+ "			wing.createdBy as createdBy , to_char(wing.createdDate,'DD-MM-YYYY') as createdDate,"
						+ "			wing.updatedBy as updatedBy , to_char(wing.updatedDate,'DD-MM-YYYY') as updatedDate "
						+ "FROM		WingMaster wing "
						+ "WHERE	wing.organizationId = :organizationId "
						+ "AND 		wing.unitId = :unitId ")
})

@Entity
@Table(name="m_wing_master",schema="adt")
@SequenceGenerator(name="wing_master_seq",sequenceName="adt.wing_master_seq", allocationSize=1)
public class WingMaster 
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="wing_master_seq")
	@Column(name="wing_id")
	private int wingId;
	
	@Column(name = "unit_id")
	private Integer unitId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name = "wing_code")
	private String wingCode;
	
	@Column(name = "wing_desc")
	private String wingDesc;
	
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
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "unit_id", insertable = false, updatable = false)
	private UnitMaster unitMaster;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "floorMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ClinicMaster> listClinicMaster;
	
	
	public int getWingId() {
		return wingId;
	}

	public void setWingId(int wingId) {
		this.wingId = wingId;
	}

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

	public String getWingCode() {
		return wingCode;
	}

	public void setWingCode(String wingCode) {
		this.wingCode = wingCode;
	}

	public String getWingDesc() {
		return wingDesc;
	}

	public void setWingDesc(String wingDesc) {
		this.wingDesc = wingDesc;
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

	public List<ClinicMaster> getListClinicMaster() {
		return listClinicMaster;
	}

	public void setListClinicMaster(List<ClinicMaster> listClinicMaster) {
		this.listClinicMaster = listClinicMaster;
	}
	
	
	
}
