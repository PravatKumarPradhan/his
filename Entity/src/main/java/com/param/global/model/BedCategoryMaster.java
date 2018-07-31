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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.adt.master.unit.model.BillingBedCategoryMaster;
import com.param.entity.model.master.UnitBedCategoryMapper;
import com.param.service.global.model.TPackageBedCategoryDetail;

 
 @NamedNativeQueries({
	 
	 @NamedNativeQuery(name="GET_BED_CATEGORY_LIST",query="SELECT bc.bed_category_id as \"bedCategoryId\", "
	 		+ "bc.hierarchy_id as \"hierarchyId\", "
	 		+ "bc.occupancy_unit_id as \"occupancyUnitId\", "
	 		+ "bc.bed_category_code as \"bedCategoryCode\", "
	 		+ "bc.bed_category_desc as \"bedCategoryDesc\", "
	 		+ "bc.is_bed_retention as \"isBedRetention\", "
	 		+ "bc.status as \"status\", "
	 		+ "ou.occupancy_unit_desc as \"occupancyUnitDesc\", "
	 		+ "h.hierarchy_desc as \"hierarchyDesc\" "
	 		+ "FROM adt.m_bed_category_master as bc "
	 		+ "INNER JOIN adt.m_hierarchy_master as h on bc.hierarchy_id=h.hierarchy_id "
	 		+ "INNER JOIN public.m_occupancy_unit_master as ou on bc.occupancy_unit_id=ou.occupancy_unit_id "
	 		+ "WHERE bc.organization_id=:orgId "
	 		+ "AND bc.status='A' "
	 		+ "ORDER BY bc.bed_category_id DESC"),
	 
	 @NamedNativeQuery(name="GET_BED_CATEGORY_LIST_BY_ORG_UNIT",query="SELECT bc.bed_category_id as \"bedCategoryId\", "
	 		+ "bc.bed_category_desc as \"bedCategoryDesc\", "
	 		+ "bc.hierarchy_id as \"hierarchyId\" "
	 		+ "FROM adt.m_bed_category_master as bc "
	 		+ "INNER JOIN public.t_unit_bed_category_mapper as ubcm on bc.bed_category_id=ubcm.bed_category_id "
	 		+ "WHERE bc.status='A' "
	 		+ "AND ubcm.unit_id=:unitId "
	 		+ "AND bc.organization_id=:organizationId "
	 		+ "AND ubcm.status='A' "
 			+ "AND bc.status='A'")
 })
 
 @NamedQueries({
 	
 	
 	@NamedQuery(name="GET_BED_CATEGORY_LIST_BY_ID",query="SELECT bc.bedCategoryId as bedCategoryId, "
 			+ "bc.hierarchyId as hierarchyId, "
 			+ "bc.occupancyUnitId as occupancyUnitId, "
 			+ "bc.bedCategoryCode as bedCategoryCode, "
 			+ "bc.bedCategoryDesc as bedCategoryDesc, "
 			+ "bc.isBedRetention as isBedRetention, "
 			+ "bc.status as status "
 			+ "FROM BedCategoryMaster bc "
 			+ "WHERE bc.bedCategoryId=:bedCategoryId"),
 	
 	@NamedQuery(name="GET_BED_CATEGORY_LIST_BY_NAME",query="SELECT bc.bedCategoryId as bedCategoryId, "
 			+ "bc.bedCategoryDesc as bedCategoryDesc "
 			+ "FROM BedCategoryMaster bc "
 			+ "WHERE bc.bedCategoryDesc=:bedCategoryDesc OR LOWER(bc.bedCategoryDesc)=:bedCategoryDesc"),
 	
	@NamedQuery(name="GET_BED_CATEGORY_LIST_BY_NAME_NOT_ID",query="SELECT bc.bedCategoryId as bedCategoryId, "
 			+ "bc.bedCategoryDesc as bedCategoryDesc "
 			+ "FROM BedCategoryMaster bc "
 			+ "WHERE (bc.bedCategoryDesc=:bedCategoryDesc OR LOWER(bc.bedCategoryDesc)=:bedCategoryDesc) "
 			+ "AND bc.bedCategoryId!=:bedCategoryId"),
 	
 	@NamedQuery(name="GET_ACTIVE_BED_CATEGORY_LIST",query="SELECT bc.bedCategoryId as bedCategoryId, "
 			+ "bc.bedCategoryDesc as bedCategoryDesc "
 			+ "FROM BedCategoryMaster bc "
 			+ "WHERE bc.status='A' "),
 	
 	/*@NamedQuery(name="GET_BED_CATEGORY_LIST_BY_ORG_UNIT",query="SELECT bc.bedCategoryId as bedCategoryId, "
 			+ "bc.bedCategoryDesc as bedCategoryDesc, "
 			+ "bc.hierarchyId as hierarchyId "
 			+ "FROM BedCategoryMaster bc "
 			//+ "INNER JOIN bc.unitBedCategoryMappersList ubcm "
 			+ "WHERE bc.status='A' "
 			//+ "AND ubcm.unitId=:unitId "
 			+ "AND bc.organizationId=:organizationId "
 			//+ "AND ubcm.status='A' "
 			+ "AND bc.status='A'")*/
 	
 })
 
 
 @Entity
 @Inheritance(strategy=InheritanceType.JOINED)
 @Table(name = "m_bed_category_master", schema = "adt")
 @SequenceGenerator(name="bed_category_master_seq", sequenceName="adt.bed_category_master_seq", allocationSize=1)
 public class BedCategoryMaster {
 
 	@Id
 	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bed_category_master_seq")
 	@Column(name = "bed_category_id")
 	private int bedCategoryId;
 	
 	@Column(name = "organization_id")
 	private Integer organizationId;
 	
 	@Column(name = "hierarchy_id")
 	private Integer hierarchyId;
 	
 	@Column(name = "occupancy_unit_id")
 	private Integer occupancyUnitId;
 	
 	@Column(name = "bed_category_code")
 	private String bedCategoryCode;
 	
 	@Column(name = "bed_category_desc")
 	private String bedCategoryDesc;
 	
 	@Column(name = "is_bed_retention")
 	private char isBedRetention;
 	
 	@Column(name="billing_bed_category_id")
 	private Integer billingBedCategoryId;
 	
 	@Column(name = "status")
 	private Character status;
 	
 	@Column(name = "created_by")
 	private int createdBy;
 	
 	@Column(name = "created_date")
 	private Date createdDate;
 	
 	@Column(name="updated_by")
 	private int updatedBy;
 	
 	@Column(name="updated_date")
 	private Date updatedDate;
 	
 	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "billing_bed_category_id", insertable = false, updatable = false)
	private BillingBedCategoryMaster billingBedCategoryMaster;
 	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bedCategoryMaster", cascade = CascadeType.ALL, orphanRemoval = true)
 	private List<TPackageBedCategoryDetail> listTPackageBedCategoryDetail;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bedCategoryMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UnitBedCategoryMapper> listUnitBedCategoryMapper;
 
 	public List<UnitBedCategoryMapper> getListUnitBedCategoryMapper() {
		return listUnitBedCategoryMapper;
	}

	public void setListUnitBedCategoryMapper(List<UnitBedCategoryMapper> listUnitBedCategoryMapper) {
		this.listUnitBedCategoryMapper = listUnitBedCategoryMapper;
	}

	public List<TPackageBedCategoryDetail> getListTPackageBedCategoryDetail() {
		return listTPackageBedCategoryDetail;
	}

	public void setListTPackageBedCategoryDetail(List<TPackageBedCategoryDetail> listTPackageBedCategoryDetail) {
		this.listTPackageBedCategoryDetail = listTPackageBedCategoryDetail;
	}

	public BillingBedCategoryMaster getBillingBedCategoryMaster() {
		return billingBedCategoryMaster;
	}

	public void setBillingBedCategoryMaster(BillingBedCategoryMaster billingBedCategoryMaster) {
		this.billingBedCategoryMaster = billingBedCategoryMaster;
	}

	public Integer getBillingBedCategoryId() {
		return billingBedCategoryId;
	}

	public void setBillingBedCategoryId(Integer billingBedCategoryId) {
		this.billingBedCategoryId = billingBedCategoryId;
	}

	public int getBedCategoryId() {
 		return bedCategoryId;
 	}
 
 	public void setBedCategoryId(int bedCategoryId) {
 		this.bedCategoryId = bedCategoryId;
 	}
 
 	public Integer getOrganizationId() {
 		return organizationId;
 	}
 
 	public void setOrganizationId(Integer organizationId) {
 		this.organizationId = organizationId;
 	}
 
 	public Integer getHierarchyId() {
 		return hierarchyId;
 	}
 
 	public void setHierarchyId(Integer hierarchyId) {
 		this.hierarchyId = hierarchyId;
 	}
 
 	public Integer getOccupancyUnitId() {
 		return occupancyUnitId;
 	}
 
 	public void setOccupancyUnitId(Integer occupancyUnitId) {
 		this.occupancyUnitId = occupancyUnitId;
 	}
 
 	public String getBedCategoryCode() {
 		return bedCategoryCode;
 	}
 
 	public void setBedCategoryCode(String bedCategoryCode) {
 		this.bedCategoryCode = bedCategoryCode;
 	}
 
 	public String getBedCategoryDesc() {
 		return bedCategoryDesc;
 	}
 
 	public void setBedCategoryDesc(String bedCategoryDesc) {
 		this.bedCategoryDesc = bedCategoryDesc;
 	}
 
 	public char getIsBedRetention() {
 		return isBedRetention;
 	}
 
 	public void setIsBedRetention(char isBedRetention) {
 		this.isBedRetention = (isBedRetention == '\u0000') ? 'N' : isBedRetention;
 	}
 	
 	
 
 	public Character getStatus() {
 		return status;
 	}
 
 	public void setStatus(Character status) {
 		System.out.println("=========>"+ status);
 		this.status = (status == '\u0000') ? 'N' : status;
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
 	
 	
 	
 	
 
 	
 	
 }