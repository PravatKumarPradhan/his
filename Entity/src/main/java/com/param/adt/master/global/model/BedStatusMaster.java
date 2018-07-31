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

import com.param.adt.master.unit.model.MortuaryBedLogStatus;
@NamedQueries({
	
	@NamedQuery(name="GET_BED_STATUS_LIST", query="SELECT bs.bedStatusId as bedStatusId, "
			+ "bs.bedStatusDesc as bedStatusDesc "
			+ "FROM BedStatusMaster bs "
			+ "WHERE bs.status='A'")
})


@Entity
@Table(name = "m_bed_status_master", schema = "adt")
@SequenceGenerator(name="bed_status_master_seq", sequenceName="adt.bed_status_master_seq", allocationSize=1)
public class BedStatusMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bed_status_master_seq")
	@Column(name = "bed_status_id")
	private int bedStatusId;
	
	@Column(name = "bed_status_code")
	private String bedStatusCode;
	
	@Column(name = "bed_status_desc")
	private String bedStatusDesc;
	
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bedStatusMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BedMaster> listBedMaster;
	

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bedStatusMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BedLogStatus> listBedLogStatus;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bedStatusMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MortuaryBedLogStatus> listMortuaryBedLogStatusId;
	
	

	

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

	public int getBedStatusId() {
		return bedStatusId;
	}

	public void setBedStatusId(int bedStatusId) {
		this.bedStatusId = bedStatusId;
	}

	public List<BedMaster> getListBedMaster() {
		return listBedMaster;
	}

	public void setListBedMaster(List<BedMaster> listBedMaster) {
		this.listBedMaster = listBedMaster;
	}

	public List<BedLogStatus> getListBedLogStatus() {
		return listBedLogStatus;
	}

	public void setListBedLogStatus(List<BedLogStatus> listBedLogStatus) {
		this.listBedLogStatus = listBedLogStatus;
	}

	public String getBedStatusCode() {
		return bedStatusCode;
	}

	public void setBedStatusCode(String bedStatusCode) {
		this.bedStatusCode = bedStatusCode;
	}

	public String getBedStatusDesc() {
		return bedStatusDesc;
	}

	public void setBedStatusDesc(String bedStatusDesc) {
		this.bedStatusDesc = bedStatusDesc;
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
