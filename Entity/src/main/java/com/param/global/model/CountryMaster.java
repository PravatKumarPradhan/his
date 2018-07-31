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


@NamedQueries({

		@NamedQuery(name = "GET_COUNTRY_LIST", query = "SELECT countryMaster.countryId as countryId, "
				+ "countryMaster.countryCode as countryCode, " + "countryMaster.countryName as countryName, "
				+ "countryMaster.status as status, " + "countryMaster.countryInitial as countryInitial, "
				+ "countryMaster.countryCallingCode as countryCallingCode " + "FROM CountryMaster as countryMaster "
						+ "WHERE countryMaster.organizationId=:orgId "
						+ "ORDER BY countryMaster.countryId DESC"),

		@NamedQuery(name = "GET_COUNTRY_LIST_BY_NAME", query = "SELECT countryMaster.countryId as countryId, "
				+ "countryMaster.countryCode as countryCode, " + "countryMaster.countryName as countryName, "
				+ "countryMaster.status as status, " + "countryMaster.countryInitial as countryInitial, "
				+ "countryMaster.countryCallingCode as countryCallingCode "
				+ "FROM CountryMaster as countryMaster WHERE LOWER(countryMaster.countryName)=:countryName OR countryMaster.countryName=:countryName"),

		@NamedQuery(name = "GET_COUNTRY_LIST_BY_ID", query = "SELECT countryMaster.countryId as countryId, "
				+ "countryMaster.countryCode as countryCode, " + "countryMaster.countryName as countryName, "
				+ "countryMaster.status as status, " + "countryMaster.countryInitial as countryInitial, "
				+ "countryMaster.countryCallingCode as countryCallingCode "
				+ "FROM CountryMaster as countryMaster WHERE countryMaster.countryId=:countryId"),

		@NamedQuery(name = "GET_COUNTRY_LIST_BY_NAME_NOT_ID", query = "SELECT countryMaster.countryId as countryId, "
				+ "countryMaster.countryCode as countryCode, " + "countryMaster.countryName as countryName, "
				+ "countryMaster.status as status, " + "countryMaster.countryInitial as countryInitial, "
				+ "countryMaster.countryCallingCode as countryCallingCode "
				+ "FROM CountryMaster as countryMaster WHERE (LOWER(countryMaster.countryName)=:countryName OR countryMaster.countryName=:countryName) AND countryMaster.countryId!=:countryId"),

		@NamedQuery(name = "GET_ACTIVE_COUNTRY_LIST", query = "SELECT countryMaster.countryId as countryId, "
				+ "countryMaster.countryName as countryName, "
				+ "countryMaster.countryCode as countryCode "
				+ "FROM CountryMaster as countryMaster WHERE countryMaster.status='A'"),
				
		@NamedQuery(name = "GET_COUNTRY_MASTER_LIST", 
					query = "SELECT countryMaster.countryId as countryId, "
					+ "countryMaster.countryCode as countryCode, " + "countryMaster.countryName as countryName, "
					+ "countryMaster.status as status, " + "countryMaster.countryInitial as countryInitial, "
					+ "countryMaster.countryCallingCode as countryCallingCode "
					+ "FROM CountryMaster as countryMaster "
					+ "ORDER BY countryMaster.countryId DESC")

})

@Entity
@Table(name = "m_country_master", schema = "public")
@SequenceGenerator(name = "country_master_seq", sequenceName = "public.country_master_seq", allocationSize = 1)
public class CountryMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_master_seq")
	@Column(name = "country_id")
	private int countryId;

	@Column(name = "country_code")
	private String countryCode;

	@Column(name = "country_name")
	private String countryName;

	@Column(name = "country_initial")
	private String countryInitial;

	@Column(name = "country_calling_code")
	private String countryCallingCode;

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

	@Column(name="organization_id")
	private Integer organizationId;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "countryMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<StateMaster> listStateMaster;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "countryMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DistrictMaster> listDistrictMaster;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "countryMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AreaMaster> listAreaMaster;

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
	

	public List<DistrictMaster> getListDistrictMaster() {
		return listDistrictMaster;
	}

	public void setListDistrictMaster(List<DistrictMaster> listDistrictMaster) {
		this.listDistrictMaster = listDistrictMaster;
	}

	public List<AreaMaster> getListAreaMaster() {
		return listAreaMaster;
	}

	public void setListAreaMaster(List<AreaMaster> listAreaMaster) {
		this.listAreaMaster = listAreaMaster;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryInitial() {
		return countryInitial;
	}

	public void setCountryInitial(String countryInitial) {
		this.countryInitial = countryInitial;
	}

	public String getCountryCallingCode() {
		return countryCallingCode;
	}

	public void setCountryCallingCode(String countryCallingCode) {
		this.countryCallingCode = countryCallingCode;
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

	public List<StateMaster> getListStateMaster() {
		return listStateMaster;
	}

	public void setListStateMaster(List<StateMaster> listStateMaster) {
		this.listStateMaster = listStateMaster;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

}
