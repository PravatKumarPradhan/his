package com.param.entity.org.model;

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
import com.param.global.model.ModalityMaster;
import com.param.global.model.SubSpecialityMaster;
@NamedQueries({

	@NamedQuery(name = "GET_GENERAL_LEDGER_MASTER_BY_NAME", 
			query = "SELECT ledgerMaster.generalLedgerId AS generalLedgerId "
			+ " FROM GeneralLedgerMaster ledgerMaster "
			+ " WHERE LOWER(ledgerMaster.generalLedgerName)=:ledgerName OR ledgerMaster.generalLedgerName=:ledgerName "
			+ " AND ledgerMaster.organizationId=:orgId "),
	
	@NamedQuery(name = "GET_GENERAL_LEDGER_MASTER_BY_ID", 
			query = "SELECT ledgerMaster.generalLedgerId AS generalLedgerId, "
			+ "ledgerMaster.generalLedgerName AS generalLedgerName," 
			+ "ledgerMaster.generalLedgerCode AS generalLedgerCode," 
			+ "ledgerMaster.status AS status "			
			+ " FROM GeneralLedgerMaster ledgerMaster "
			+ " WHERE ledgerMaster.generalLedgerId=:lederId"
			+ " AND ledgerMaster.organizationId=:orgId") ,
	
	@NamedQuery(name = "GET_GENERAL_LEDGER_MASTER_LIST", 
		query = "SELECT ledgerMaster.generalLedgerId AS generalLedgerId, "
		+ "ledgerMaster.generalLedgerName AS generalLedgerName," 
		+ "ledgerMaster.status AS status,"
		+ "ledgerMaster.generalLedgerCode AS generalLedgerCode "
		+ " FROM GeneralLedgerMaster ledgerMaster "
		+ " WHERE ledgerMaster.organizationId=:orgId"),
	
	@NamedQuery(name = "GET_GENERAL_LEDGER_MASTER_ACTIVE_LIST", 
	query = "SELECT ledgerMaster.generalLedgerId AS generalLedgerId, "
	+ "ledgerMaster.generalLedgerName AS generalLedgerName," 
	+ "ledgerMaster.status AS status,"
	+ "ledgerMaster.generalLedgerCode AS generalLedgerCode "
	+ " FROM GeneralLedgerMaster ledgerMaster "
	+ " WHERE ledgerMaster.organizationId=:orgId "
	+ " AND ledgerMaster.status='A'"),
	
	@NamedQuery(name = "GET_LEDGER_BY_NAME_NOT_ID",
		query = "SELECT ledgerMaster.generalLedgerId AS generalLedgerId  "
		+ "FROM GeneralLedgerMaster ledgerMaster "
		+ "WHERE (LOWER(ledgerMaster.generalLedgerName)=:ledgerName OR ledgerMaster.generalLedgerName=:ledgerName) "
		+ "AND ledgerMaster.generalLedgerId !=:ledgerId")

	})

@Entity
@Table(name = "m_general_ledger_master", schema = "public")
@SequenceGenerator(name="general_ledger_master_seq", sequenceName="public.general_ledger_master_seq", allocationSize=1)
public class GeneralLedgerMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "general_ledger_master_seq")
	@Column(name = "general_ledger_id")
	private Integer generalLedgerId;
	
	@Column(name = "general_ledger_code")
	private String generalLedgerCode;
	
	@Column(name = "general_ledger_name")
	private String generalLedgerName;
	
	@Column(name = "status")
	private Character status;
	
	@Column(name = "created_by")
	private Integer createdBy;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name="updated_by")
	private Integer updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="organisation_id")
	private Integer organizationId;
	
	@Column(name="unit_id")
	private Integer unitId;
	

//	@OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "generalLedgerMaster")
//	private List<GeneralLedgerMaster> listGeneralLedgerMaster;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "generalLedgerMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SubSpecialityMaster> subSpecialityMastersList;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organisation_id", insertable = false, updatable = false)
	private OrganizationMaster organizationMaster;

	public Integer getGeneralLedgerId() {
		return generalLedgerId;
	}

	public void setGeneralLedgerId(Integer generalLedgerId) {
		this.generalLedgerId = generalLedgerId;
	}

	public String getGeneralLedgerCode() {
		return generalLedgerCode;
	}

	public void setGeneralLedgerCode(String generalLedgerCode) {
		this.generalLedgerCode = generalLedgerCode;
	}

	public String getGeneralLedgerName() {
		return generalLedgerName;
	}

	public void setGeneralLedgerName(String generalLedgerName) {
		this.generalLedgerName = generalLedgerName;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
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

	public OrganizationMaster getOrganizationMaster() {
		return organizationMaster;
	}

	public void setOrganizationMaster(OrganizationMaster organizationMaster) {
		this.organizationMaster = organizationMaster;
	}
	

	
}
