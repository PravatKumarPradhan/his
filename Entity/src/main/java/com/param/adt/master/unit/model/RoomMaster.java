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
import com.param.global.model.BedCategoryMaster;

@NamedQueries({	
	
	@NamedQuery(name="GET_ROOM_MASTER_BY_NAME",query="SELECT room.roomId as roomId, "
			+ "room.roomDesc as roomDesc "
			+ "FROM RoomMaster room "
			+ "WHERE LOWER(room.roomDesc)=:roomName "
			+ "AND room.organizationId=:orgId"),
	@NamedQuery(name="GET_ROOM_MASTER_BY_NAME_NOT_BY_ID",query="SELECT room.roomId as roomId, "
			+ "room.roomDesc as roomDesc "
			+ "FROM RoomMaster room "
			+ "WHERE LOWER(room.roomDesc)=:roomName "
			+ "AND room.roomId=:roomId"),
	@NamedQuery(name="GET_ROOM_MASTER_LIST",query="SELECT room.roomId as roomId, "
			+ "room.roomDesc as roomDesc,room.roomCode AS roomCode,"		
			+ "room.isIsolation AS isIsolation,  "
			+ "wardMstr.wardName AS wardName, "
			+ "billingBedMstr.billingBedCategoryId AS billingBedCategoryId,"
			+ "billingBedMstr.billingBedDesc AS billingBedDesc,"
			+ "bedCatMstr.bedCategoryId AS bedCategoryId,"
			+ "bedCatMstr.bedCategoryDesc AS bedCategoryDesc  "
			+ "FROM RoomMaster room "
			+ "INNER JOIN room.WardMaster wardMstr "
			+ "INNER JOIN room.billingBedCategoryMaster billingBedMstr "
			+ "INNER JOIN room.bedCategoryMaster bedCatMstr "
			+ "WHERE room.organizationId=:orgId "
		    + "AND room.unitId=:unitId "),
	@NamedQuery(name="GET_ROOM_MASTER_BY_ID",query="SELECT room.roomId as roomId, "
			+ "room.roomDesc as roomDesc,room.roomCode AS roomCode,"
			+ "wardMstr.wardName AS wardName, "
			+ "room.wardId AS wardId,"
			+ "room.isIsolation AS isIsolation,"
			+ "billingBedMstr.billingBedCategoryId AS billingBedCategoryId,"
			+ "billingBedMstr.billingBedDesc AS billingBedDesc, "
			+ "bedCatMstr.bedCategoryId AS bedCategoryId,"
			+ "bedCatMstr.bedCategoryDesc AS bedCategoryDesc  "
			+ "FROM RoomMaster room "
			+ "INNER JOIN room.WardMaster wardMstr "
			+ "INNER JOIN room.billingBedCategoryMaster billingBedMstr "
			+ "INNER JOIN room.bedCategoryMaster bedCatMstr "
			+ "WHERE room.organizationId=:orgId "
			+ "AND room.unitId=:unitId AND room.roomId=:roomId")
})
@Entity
@Table(name = "m_room_master", schema = "adt")
@SequenceGenerator(name="room_master_seq", sequenceName="adt.room_master_seq", allocationSize=1)
public class RoomMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_master_seq")
	@Column(name = "room_id")
	private int roomId;
	
	@Column(name = "ward_id")
	private Integer wardId;
	
	@Column(name = "bed_category_id")
	private Integer bedCategoryId;
	
	@Column(name = "bed_type_id")
	private Integer bedTypeId;
	
	@Column(name = "room_code")
	private String roomCode;
	
	@Column(name = "room_desc")
	private String roomDesc;
	
	@Column(name = "no_of_max_beds")
	private int noOfMaxBeds;
	
	@Column(name = "is_isolation")
	private char isIsolation;
	
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
	
	@Column(name="billing_bed_category_id")
	private Integer billingBedCategoryId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private OrganizationMaster organizationMaster;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ward_id",insertable=false,updatable=false)
	private WardMaster WardMaster;
	

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "billing_bed_category_id", insertable = false, updatable = false)
	private BillingBedCategoryMaster billingBedCategoryMaster;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id", insertable = false, updatable = false)
	private UnitMaster unitMaster;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roomMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AdmissionDetails> admissionDetailsList;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "roomMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Transfer> transferslist;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "roomMaster2", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Transfer> transferslist2;
	
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

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public UnitMaster getUnitMaster() {
		return unitMaster;
	}

	public void setUnitMaster(UnitMaster unitMaster) {
		this.unitMaster = unitMaster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bed_category_id" , insertable = false , updatable = false , nullable = false)
	private BedCategoryMaster bedCategoryMaster;
	
/*	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bed_type_id" , insertable = false , updatable = false , nullable = false)
	private BedTypeMaster bedTypeMaster;*/
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ward_id" , insertable = false , updatable = false , nullable = false)
	private WardMaster wardMaster;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roomMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BedMaster> listBedMaster;

	
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

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	

	public Integer getWardId() {
		return wardId;
	}

	public void setWardId(Integer wardId) {
		this.wardId = wardId;
	}

	public Integer getBedCategoryId() {
		return bedCategoryId;
	}

	public void setBedCategoryId(Integer bedCategoryId) {
		this.bedCategoryId = bedCategoryId;
	}

	public Integer getBedTypeId() {
		return bedTypeId;
	}

	public void setBedTypeId(Integer bedTypeId) {
		this.bedTypeId = bedTypeId;
	}

	public WardMaster getWardMaster() {
		return wardMaster;
	}

	public void setWardMaster(WardMaster wardMaster) {
		this.wardMaster = wardMaster;
	}

	public List<BedMaster> getListBedMaster() {
		return listBedMaster;
	}

	public void setListBedMaster(List<BedMaster> listBedMaster) {
		this.listBedMaster = listBedMaster;
	}

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public String getRoomDesc() {
		return roomDesc;
	}

	public void setRoomDesc(String roomDesc) {
		this.roomDesc = roomDesc;
	}

	public int getNoOfMaxBeds() {
		return noOfMaxBeds;
	}

	public void setNoOfMaxBeds(int noOfMaxBeds) {
		this.noOfMaxBeds = noOfMaxBeds;
	}

	public char getIsIsolation() {
		return isIsolation;
	}

	public void setIsIsolation(char isIsolation) {
		this.isIsolation = isIsolation;
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

	public Integer getBillingBedCategoryId() {
		return billingBedCategoryId;
	}

	public void setBillingBedCategoryId(Integer billingBedCategoryId) {
		this.billingBedCategoryId = billingBedCategoryId;
	}
	
	
}
