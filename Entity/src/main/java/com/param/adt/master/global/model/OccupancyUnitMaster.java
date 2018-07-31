package com.param.adt.master.global.model;

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
	
	@NamedQuery(name="GET_OCCUPANCY_UNIT_LIST", query="SELECT occ.occupancyUnitId as occupancyUnitId, "
			+ "occ.occupancyUnitCode as occupancyUnitCode, "
			+ "occ.occupancyUnitDesc as occupancyUnitDesc, "
			+ "occ.numberOfHours as numberOfHours, "
			+ "occ.status as status "
			+ "FROM OccupancyUnitMaster occ "
			+ "WHERE occ.organizationId=:orgId "
			+ "ORDER BY occ.occupancyUnitId DESC"),
	
	@NamedQuery(name="GET_OCCUPANCY_UNIT_LIST_BY_ID", query="SELECT occ.occupancyUnitId as occupancyUnitId, "
			+ "occ.occupancyUnitCode as occupancyUnitCode, "
			+ "occ.occupancyUnitDesc as occupancyUnitDesc, "
			+ "occ.numberOfHours as numberOfHours, "
			+ "occ.status as status "
			+ "FROM OccupancyUnitMaster occ "
			+ "WHERE  occ.occupancyUnitId=:occId"),
	
	@NamedQuery(name="GET_OCCUPANCY_UNIT_LIST_BY_NAME", query="SELECT occ.occupancyUnitId as occupancyUnitId, "
			+ "occ.occupancyUnitDesc as occupancyUnitDesc "
			+ "FROM OccupancyUnitMaster occ "
			+ "WHERE (occ.occupancyUnitDesc)=:occDesc OR occ.occupancyUnitDesc=:occDesc"),
	
	@NamedQuery(name="GET_OCCUPANCY_UNIT_LIST_BY_NAME_NOT_ID", query="SELECT occ.occupancyUnitId as occupancyUnitId, "
			+ "occ.occupancyUnitDesc as occupancyUnitDesc "
			+ "FROM OccupancyUnitMaster occ "
			+ "WHERE ((occ.occupancyUnitDesc)=:occDesc OR occ.occupancyUnitDesc=:occDesc) AND occ.occupancyUnitId!=:occId"),
	
	@NamedQuery(name="GET_ACTIVE_OCCUPANCY_UNIT_LIST", query="SELECT occ.occupancyUnitId as occupancyUnitId, "
			+ "occ.occupancyUnitDesc as occupancyUnitDesc "
			+ "FROM OccupancyUnitMaster occ "
			+ "WHERE occ.status='A'")
	
	
})


@Entity
@Table(name = "m_occupancy_unit_master", schema = "public")
@SequenceGenerator(name="occupancy_unit_master_seq", sequenceName="public.occupancy_unit_master_seq", allocationSize=1)
public class OccupancyUnitMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "occupancy_unit_master_seq")
	@Column(name = "occupancy_unit_id")
	private int occupancyUnitId;
	
	@Column(name = "occupancy_unit_code")
	private String occupancyUnitCode;
	
	@Column(name = "occupancy_unit_desc")
	private String occupancyUnitDesc;
	
	@Column(name = "number_of_hours")
	private String numberOfHours;
		
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
	
	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "occupancyUnitMaster", cascade = CascadeType.ALL, orphanRemoval = true) 
	private List<BedCategoryMaster> listBedCategoryMaster;*/
	
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

	public int getOccupancyUnitId() {
		return occupancyUnitId;
	}

	public void setOccupancyUnitId(int occupancyUnitId) {
		this.occupancyUnitId = occupancyUnitId;
	}

	public String getOccupancyUnitCode() {
		return occupancyUnitCode;
	}

	public void setOccupancyUnitCode(String occupancyUnitCode) {
		this.occupancyUnitCode = occupancyUnitCode;
	}

	public String getOccupancyUnitDesc() {
		return occupancyUnitDesc;
	}

	public void setOccupancyUnitDesc(String occupancyUnitDesc) {
		this.occupancyUnitDesc = occupancyUnitDesc;
	}

	

	public String getNumberOfHours() {
		return numberOfHours;
	}

	public void setNumberOfHours(String numberOfHours) {
		this.numberOfHours = numberOfHours;
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
