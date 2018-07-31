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

	@NamedQuery(name = "GET_RACE_LIST", query = "SELECT raceMaster.raceId as raceId, "
			+ "raceMaster.raceName as raceName, "
			+ "raceMaster.raceCode as raceCode, " + "raceMaster.status as status "
			+ "FROM RaceMaster as raceMaster "
			+ "WHERE raceMaster.organizationId=:orgId "
			+ "ORDER BY raceMaster.raceId DESC"),

	@NamedQuery(name = "GET_RACE_LIST_BY_ID", query = "SELECT raceMaster.raceId as raceId, "
			+ "raceMaster.raceName as raceName, "
			+ "raceMaster.raceCode as raceCode, " 
			+ "raceMaster.status as status "
			+ "FROM RaceMaster as raceMaster WHERE raceMaster.raceId=:raceId"),

	@NamedQuery(name = "GET_RACE_LIST_BY_NAME", query = "SELECT raceMaster.raceId as raceId, "
			+ "raceMaster.raceName as raceName, "
			+ "raceMaster.raceCode as raceCode, " + "raceMaster.status as status "
			+ "FROM RaceMaster as raceMaster WHERE LOWER(raceMaster.raceName)=:raceName OR raceMaster.raceName=:raceName"),

	@NamedQuery(name = "GET_RACE_LIST_BY_NAME_NOT_BY_ID", query = "SELECT raceMaster.raceId as raceId, "
			+ "raceMaster.raceName as raceName, "
			+ "raceMaster.raceCode as raceCode, " + "raceMaster.status as status "
			+ "FROM RaceMaster as raceMaster WHERE (LOWER(raceMaster.raceName)=:raceName OR raceMaster.raceName=:raceName) AND raceMaster.raceId !=:raceId"),

	@NamedQuery(name = "GET_ACTIVE_RACE_LIST", query = "SELECT raceMaster.raceId as raceId, "
			+ "raceMaster.raceName as raceName, "
			+ "raceMaster.raceCode as raceCode, " + "raceMaster.status as status "
			+ "FROM RaceMaster as raceMaster WHERE raceMaster.status='A'")

})

@Entity
@Table(name = "m_race_master", schema = "public")
@SequenceGenerator(name="race_master_seq", sequenceName="public.race_master_seq", allocationSize=1)
public class RaceMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "race_master_seq")
	@Column(name = "race_id")
	private int raceId;
	
	@Column(name = "race_code")
	private String raceCode;
	
	@Column(name = "race_name")
	private String raceName;
	
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

	public int getRaceId() {
		return raceId;
	}

	public void setRaceId(int raceId) {
		this.raceId = raceId;
	}

	public String getRaceCode() {
		return raceCode;
	}

	public void setRaceCode(String raceCode) {
		this.raceCode = raceCode;
	}

	public String getRaceName() {
		return raceName;
	}

	public void setRaceName(String raceName) {
		this.raceName = raceName;
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
