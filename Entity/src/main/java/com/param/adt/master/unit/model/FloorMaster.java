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

import com.param.adt.master.global.model.BedMaster;
import com.param.adt.master.global.model.OrganizationMaster;
import com.param.adt.master.global.model.UnitMaster;
import com.param.global.model.ClinicMaster;

@NamedQueries({

		@NamedQuery(name = "GET_FLOOR_LIST", query = "SELECT floorMaster.floorId as floorId, "
				+ "floorMaster.floorCode as floorCode, " 
				+ "floorMaster.floorName as floorName, "
				+ "floorMaster.status as status " 
				+ "FROM FloorMaster floorMaster "
				+ "WHERE floorMaster.organizationId=:orgId " 
				+ "AND floorMaster.unitId=:unitId "
				+ "ORDER BY floorMaster.floorId DESC"),

		@NamedQuery(name = "GET_FLOOR_LIST_BY_NAME", query = "SELECT floorMaster.floorId as floorId, "
				+ "floorMaster.floorName as floorName "
				+ "FROM FloorMaster floorMaster WHERE LOWER(floorMaster.floorName)=:floorName OR floorMaster.floorName=:floorName"),

		@NamedQuery(name = "GET_FLOOR_LIST_BY_ID", query = "SELECT floorMaster.floorId as floorId, "
				+ "floorMaster.floorCode as floorCode, " + "floorMaster.floorName as floorName, "
				+ "floorMaster.status as status " + "FROM FloorMaster floorMaster WHERE floorMaster.floorId=:floorId"),

		@NamedQuery(name = "GET_FLOOR_LIST_BY_NAME_NOT_ID", query = "SELECT floorMaster.floorId as floorId, "
				+ "floorMaster.floorName as floorName "
				+ "FROM FloorMaster floorMaster WHERE (LOWER(floorMaster.floorName)=:floorName OR floorMaster.floorName=:floorName) AND floorMaster.floorId!=:floorId"),

		@NamedQuery(name = "GET_ACTIVE_FLOOR_LIST", query = "SELECT floor.floorId as floorId, "
				+ "floor.floorName as floorName " + "FROM FloorMaster floor " + "WHERE floor.unitId=:unitId "
				+ "AND floor.organizationId=:organizationId " + "AND floor.status='A'") })

@Entity
@Table(name = "m_floor_master", schema = "adt")
@SequenceGenerator(name = "floor_master_seq", sequenceName = "adt.floor_master_seq", allocationSize = 1)
public class FloorMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "floor_master_seq")
	@Column(name = "floor_id")
	private int floorId;

	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "floor_code")
	private String floorCode;

	@Column(name = "floor_name")
	private String floorName;

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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private OrganizationMaster organizationMaster;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "floorMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<WardMaster> listWardMaster;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "floorMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BedMaster> listBedMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id", insertable = false, updatable = false)
	private UnitMaster unitMaster;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "floorMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<WardMaster> wardMastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "floorMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ClinicMaster> listClinicMaster;
	
	
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

	public int getFloorId() {
		return floorId;
	}

	public void setFloorId(int floorId) {
		this.floorId = floorId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public List<WardMaster> getListWardMaster() {
		return listWardMaster;
	}

	public void setListWardMaster(List<WardMaster> listWardMaster) {
		this.listWardMaster = listWardMaster;
	}

	public List<BedMaster> getListBedMaster() {
		return listBedMaster;
	}

	public void setListBedMaster(List<BedMaster> listBedMaster) {
		this.listBedMaster = listBedMaster;
	}

	public UnitMaster getUnitMaster() {
		return unitMaster;
	}

	public void setUnitMaster(UnitMaster unitMaster) {
		this.unitMaster = unitMaster;
	}

	public String getFloorCode() {
		return floorCode;
	}

	public void setFloorCode(String floorCode) {
		this.floorCode = floorCode;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
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

	public List<ClinicMaster> getListClinicMaster() {
		return listClinicMaster;
	}

	public void setListClinicMaster(List<ClinicMaster> listClinicMaster) {
		this.listClinicMaster = listClinicMaster;
	}
	
	

}
