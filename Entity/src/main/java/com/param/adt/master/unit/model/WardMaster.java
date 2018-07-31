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

import com.param.adt.master.global.model.ICUTypeMaster;
import com.param.adt.master.global.model.OrganizationMaster;
import com.param.adt.master.global.model.UnitMaster;
import com.param.adt.transfer.model.Transfer;
import com.param.adt.transfer.model.TransferRequest;
import com.param.global.model.GenderMaster;

@NamedQueries({
	
	@NamedQuery(name="GET_WARD_LIST_BY_FLOOR_ID" , query="SELECT ward.wardId as wardId, "
			+ "ward.wardName as wardName "
			+ "FROM WardMaster ward "
			+ "WHERE ward.organizationId=:organizationId "
			+ "AND ward.unitId=:unitId "
			+ "AND ward.floorId=:floorId "
			+ "AND ward.status='A' "
			+ "AND ward.genderId=:genderId"),
	@NamedQuery(name="GET_WARD_BY_FLOOR_ID",query="SELECT ward.wardId as wardId, "
			+ "ward.wardName as wardName "
			+ "FROM WardMaster ward "
			+ "WHERE ward.organizationId=:organizationId "
			+ "AND ward.unitId=:unitId "
			+ "AND ward.floorId=:floorId "
			+ "AND ward.status='A' "),
	@NamedQuery(name="GET_WARD_MASTER_BY_NAME",query="SELECT ward.wardId as wardId, "
			+ "ward.wardName as wardName "
			+ "FROM WardMaster ward "
			+ "WHERE LOWER(ward.wardName)=:wardName "
			+ "AND ward.organizationId=:orgId"),
	@NamedQuery(name="GET_WARD_MASTER_BY_NAME_NOT_BY_ID",query="SELECT ward.wardId as wardId, "
			+ "ward.wardName as wardName "
			+ "FROM WardMaster ward "
			+ "WHERE LOWER(ward.wardName)=:wardName "
			+ "AND ward.wardId=:wardId"),
	@NamedQuery(name="GET_WARD_MASTER_LIST",query="SELECT ward.wardId as wardId, "
			+ "ward.wardName as wardName,ward.wardCode AS wardCode,"
			+ "ward.isEr AS isEr,ward.isIcu As isIcu,ward.isDayCare AS isDayCare, "
			+ "flrMstr.floorName AS floorName,"
			+ "genMstr.desc AS desc "
			+ "FROM WardMaster ward "
			+ "INNER JOIN ward.floorMaster flrMstr "
			+ "INNER JOIN ward.genderMaster genMstr "			
			+ "WHERE ward.organizationId=:orgId "
			+ "AND ward.unitId=:unitId "),
	@NamedQuery(name="GET_WARD_MASTER_BY_ID",query="SELECT ward.wardId as wardId, "
			+ "ward.wardName as wardName,ward.wardCode AS wardCode,"
			+ "ward.isEr AS isEr,ward.isIcu As isIcu,ward.isDayCare AS isDayCare, "
			+ "flrMstr.floorName AS floorName,"
			+ "genMstr.desc AS desc "
			+ "FROM WardMaster ward "
			+ "INNER JOIN ward.floorMaster flrMstr "
			+ "INNER JOIN ward.genderMaster genMstr "			
			+ "WHERE ward.organizationId=:orgId "
			+ "AND ward.unitId=:unitId AND ward.wardId=:wardId")
})

@Entity
@Table(name="m_ward_master",schema="adt")
@SequenceGenerator(name="ward_master_seq",sequenceName="adt.ward_master_seq",allocationSize=1)
public class WardMaster 
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="ward_master_seq")
	@Column(name="ward_id")
	private int wardId;
	
	@Column(name = "organization_id")
	private Integer organizationId;
	
	@Column(name = "unit_id")
	private Integer unitId;
	
	@Column(name="ward_code")
	private String wardCode;
	
	@Column(name="floor_id")
	private Integer floorId;
	
	@Column(name="ward_name")
	private String wardName;
	
	@Column(name="is_er")
	private char isEr;
	
	@Column(name="no_of_rooms")
	private int noOfRooms;
	
	@Column(name="gender_id")
	private Integer genderId;
	
	@Column(name="is_icu")
	private char isIcu;
	
	@Column(name="is_day_care")
	private char isDayCare;
	
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
	
	@Column(name="icu_type_id")
	private Integer icuTypeId;
	
	@Column(name="store_id")
	private Integer storeId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "icu_type_id", insertable = false, updatable = false)
	private ICUTypeMaster icuTypeMaster;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private OrganizationMaster organizationMaster;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "unit_id", insertable = false, updatable = false)
	private UnitMaster unitMaster;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "floor_id", insertable = false, updatable = false)
	private FloorMaster floorMaster;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "gender_id", insertable = false, updatable = false)
	private GenderMaster genderMaster;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "wardMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<NursingStationWardMapper> nursingStationWardMappersList;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "wardMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Transfer> transferslist;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "wardMaster2", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Transfer> transferslist2;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "wardMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TransferRequest> transferRequestslist;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "wardMaster2", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TransferRequest> transferRequestslist2;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "WardMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RoomMaster> roomMastersList;

/*
	public WardMaster(Integer id) {
		super();
		this.wardId = id;
	}
*/
	public int getWardId() {
		return wardId;
	}

	public void setWardId(int wardId) {
		this.wardId = wardId;
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

	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}

	public Integer getFloorId() {
		return floorId;
	}

	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
	}

	
	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public char getIsEr() {
		return isEr;
	}

	public void setIsEr(char isEr) {
		this.isEr = isEr;
	}

	public int getNoOfRooms() {
		return noOfRooms;
	}

	public void setNoOfRooms(int noOfRooms) {
		this.noOfRooms = noOfRooms;
	}

	public Integer getGenderId() {
		return genderId;
	}

	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}

	public char getIsIcu() {
		return isIcu;
	}

	public void setIsIcu(char isIcu) {
		this.isIcu = isIcu;
	}

	public char getIsDayCare() {
		return isDayCare;
	}

	public void setIsDayCare(char isDayCare) {
		this.isDayCare = isDayCare;
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

	public FloorMaster getFloorMaster() {
		return floorMaster;
	}

	public void setFloorMaster(FloorMaster floorMaster) {
		this.floorMaster = floorMaster;
	}

	public GenderMaster getGenderMaster() {
		return genderMaster;
	}

	public void setGenderMaster(GenderMaster genderMaster) {
		this.genderMaster = genderMaster;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	
	
}
