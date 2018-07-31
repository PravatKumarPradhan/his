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

import com.param.adt.admission.model.AdmissionDetails;
import com.param.adt.master.global.model.BedMaster;
import com.param.adt.master.global.model.OrganizationMaster;
import com.param.adt.master.global.model.UnitMaster;
import com.param.adt.transfer.model.Transfer;
import com.param.service.global.model.MCompanyContractMaster;
import com.param.service.global.model.MPackageMaster;

@NamedQueries({
	
	@NamedQuery(name="GET_BILLING_BED_LIST_BY_BED_CAT" ,
			query="SELECT bbc.billingBedCategoryId as billingBedCategoryId, "
			+ "bbc.billingBedDesc as billingBedDesc "
			+ "FROM BedCategoryMaster bc "
			+ "INNER JOIN bc.billingBedCategoryMaster bbc "
			+ "WHERE bc.bedCategoryId=:bedCategoryId "
			+ "AND bbc.status='A' "
			+ "AND bc.status='A' "
			+ "AND bc.organizationId=:organizationId"),
	
	@NamedQuery(name="GET_ACTIVE_BILLING_BED_CATEGORY_LIST",query="SELECT bbc.billingBedCategoryId as billingBedCategoryId, "
			+ "bbc.billingBedDesc as billingBedDesc "
			+ "FROM BillingBedCategoryMaster bbc "
			+ "WHERE bbc.status='A' "
			+ "AND bbc.unitId=:unitId "
			+ "AND bbc.organizationId=:orgId"),
	@NamedQuery(name="GET_BILLING_BED_CATEGORY_LIST",
	query="SELECT bbc.billingBedCategoryId as billingBedCategoryId, "
			+ "bbc.billingBedDesc as billingBedDesc ,"
			+ "bbc.hierarchyId as hierarchyId "
			+ "FROM BillingBedCategoryMaster bbc "
			+ "WHERE bbc.unitId=:unitId "
			+ "AND bbc.organizationId=:orgId"),	
	@NamedQuery(name="GET_BILLING_BED_CATEGORY_BY_ID",
	query="SELECT bbc.billingBedCategoryId as billingBedCategoryId, "
			+ "bbc.billingBedDesc as billingBedDesc ,"
			+ "bbc.hierarchyId as hierarchyId "
			+ "FROM BillingBedCategoryMaster bbc "
			+ "WHERE bbc.unitId=:unitId "
			+ "AND bbc.organizationId=:orgId "
			+ "AND bbc.billingBedCategoryId=:billingBedId"),
	@NamedQuery(name="GET_BILLING_BED_CATEGORY_BY_NAME",
	query="SELECT bbc.billingBedCategoryId as billingBedCategoryId, "
			+ "bbc.billingBedDesc as billingBedDesc ,"
			+ "bbc.hierarchyId as hierarchyId "
			+ "FROM BillingBedCategoryMaster bbc "
			+ "WHERE bbc.organizationId=:orgId "
			+ "AND LOWER(bbc.billingBedDesc)=:desc"),
	@NamedQuery(name="GET_BILLING_BED_CATEGORY_BY_NAME_NOT_BY_ID",
	query="SELECT bbc.billingBedCategoryId as billingBedCategoryId, "
			+ "bbc.billingBedDesc as billingBedDesc ,"
			+ "bbc.hierarchyId as hierarchyId "
			+ "FROM BillingBedCategoryMaster bbc "
			+ "WHERE bbc.billingBedCategoryId=:billingBedId "
			+ "AND LOWER(bbc.billingBedDesc)=:desc")
})

@Entity
@Table(name="m_billing_bed_category_master",schema="adt")
@SequenceGenerator(name="billing_bed_category_master_seq",sequenceName="adt.billing_bed_category_master_seq",allocationSize=1)
public class BillingBedCategoryMaster 
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="billing_bed_category_master_seq")
	@Column(name="billing_bed_category_id")
	private Integer billingBedCategoryId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="billing_bed_code")
	private String billingBedCode;
	
	@Column(name="billing_bed_desc")
	private String billingBedDesc;
	
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
	
	@Column(name="hierarchy_id")
	private int hierarchyId;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private OrganizationMaster organizationMaster;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "unit_id", insertable = false, updatable = false)
	private UnitMaster unitMaster;
	
/*	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "bed_category_id", insertable = false, updatable = false)
	private BedCategoryMaster bedCategoryMaster;*/

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "billingBedCategoryMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BedMaster> listBedMaster;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "billingBedCategoryMaster", cascade = CascadeType.ALL, orphanRemoval = true) 
	private List<AdmissionDetails> admissionDetailsList;
		
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "billingBedCategoryMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Transfer> transferslist;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "billingBedCategoryMaster2", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Transfer> transferslist2;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "billingBedCategoryMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MPackageMaster> listMPackageMaster;
	
	@OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "billingBedCategoryMaster")
	private List<MCompanyContractMaster> listMCompanyContractMaster;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "billingBedCategoryMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RoomMaster> roomMasters;

	public List<MCompanyContractMaster> getListMCompanyContractMaster() {
		return listMCompanyContractMaster;
	}

	public void setListMCompanyContractMaster(List<MCompanyContractMaster> listMCompanyContractMaster) {
		this.listMCompanyContractMaster = listMCompanyContractMaster;
	}
	
	public List<MPackageMaster> getListMPackageMaster() {
		return listMPackageMaster;
	}

	public void setListMPackageMaster(List<MPackageMaster> listMPackageMaster) {
		this.listMPackageMaster = listMPackageMaster;
	}

	public List<BedMaster> getListBedMaster() {
		return listBedMaster;
	}

	public void setListBedMaster(List<BedMaster> listBedMaster) {
		this.listBedMaster = listBedMaster;
	}

	public Integer getBillingBedCategoryId() {
		return billingBedCategoryId;
	}

	public void setBillingBedCategoryId(Integer billingBedCategoryId) {
		this.billingBedCategoryId = billingBedCategoryId;
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

	public String getBillingBedCode() {
		return billingBedCode;
	}

	public void setBillingBedCode(String billingBedCode) {
		this.billingBedCode = billingBedCode;
	}

	public String getBillingBedDesc() {
		return billingBedDesc;
	}

	public void setBillingBedDesc(String billingBedDesc) {
		this.billingBedDesc = billingBedDesc;
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

	public Integer getHierarchyId() {
		return hierarchyId;
	}

	public void setHierarchyId(Integer hierarchyId) {
		this.hierarchyId = hierarchyId;
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

	public List<AdmissionDetails> getAdmissionDetailsList() {
		return admissionDetailsList;
	}

	public void setAdmissionDetailsList(List<AdmissionDetails> admissionDetailsList) {
		this.admissionDetailsList = admissionDetailsList;
	}

	public List<Transfer> getTransferslist() {
		return transferslist;
	}

	public void setTransferslist(List<Transfer> transferslist) {
		this.transferslist = transferslist;
	}

	public List<Transfer> getTransferslist2() {
		return transferslist2;
	}

	public void setTransferslist2(List<Transfer> transferslist2) {
		this.transferslist2 = transferslist2;
	}

	public List<RoomMaster> getRoomMasters() {
		return roomMasters;
	}

	public void setRoomMasters(List<RoomMaster> roomMasters) {
		this.roomMasters = roomMasters;
	}

	

	
}
