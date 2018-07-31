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
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.adt.admission.model.AdmissionDetails;
import com.param.adt.discharge.model.Discharge;
import com.param.adt.housekeeping.model.Housekeeping;
import com.param.adt.master.unit.model.BillingBedCategoryMaster;
import com.param.adt.master.unit.model.FloorMaster;
import com.param.adt.master.unit.model.RoomMaster;
import com.param.adt.master.unit.model.WardMaster;
import com.param.adt.transfer.model.Transfer;
import com.param.global.model.BedCategoryMaster;

@NamedQueries({ 
	@NamedQuery(name = "GET_ACTIVE_BED_LIST", query = "SELECT bed.bedId as bedId, "
		+ "bed.bedNumber as bedNumber, " 
		+ "bed.floorId as floorId, " 
		+ "bed.roomId as roomId, "
		+ "flr.floorName as floorName, "
		+ "bed.wardId as wardId, " 
		+ "ward.wardName as wardName, "
		+ "bed.bedCategoryId as bedCategoryId, "
		+ "bed.billingBedCategoryId as billingBedCategoryId, "
		+ "bbc.billingBedDesc as billingBedDesc, " 
		+ "bc.bedCategoryDesc as bedCategoryDesc " 
		+ "FROM BedMaster bed "
		+ "INNER JOIN bed.listBedLogStatus bedlog " 
		+ "INNER JOIN bed.floorMaster flr "
		+ "INNER JOIN bed.wardMaster ward " 
		+ "INNER JOIN bed.bedCategoryMaster bc "
		+ "INNER JOIN bed.billingBedCategoryMaster bbc " 
		+ "WHERE bed.unitId=:unitId "
		+ "AND bed.organizationId=:organizationId " 
		+ "AND bedlog.status='A' " 
		+ "AND bed.status='A' "
		+ "AND bed.bedStatusId=3 "
		+ "AND bedlog.bedStatusId=3 "//BedStatusId 3---> Ready For Use
		+ "AND ward.isEr ='N' "
		+ "AND ward.isIcu='N' "  
		+ "AND bed.bedCategoryId=:bedCategoryId"),
	
	@NamedQuery(name = "GET_ICU_BED_LIST_BY_ICU_TYPE_ID", query = "SELECT bed.bedId as bedId, "
			+ "bed.bedNumber as bedNumber, " 
			+ "bed.floorId as floorId, " 
			+ "bed.roomId as roomId, "
			+ "flr.floorName as floorName, "
			+ "bed.wardId as wardId, " 
			+ "ward.wardName as wardName, "
			+ "bed.bedCategoryId as bedCategoryId, "
			+ "bed.billingBedCategoryId as billingBedCategoryId, "
			+ "bbc.billingBedDesc as billingBedDesc, " 
			+ "bc.bedCategoryDesc as bedCategoryDesc " 
			+ "FROM BedMaster bed "
			+ "INNER JOIN bed.listBedLogStatus bedlog " 
			+ "INNER JOIN bed.floorMaster flr "
			+ "INNER JOIN bed.wardMaster ward " 
			+ "INNER JOIN bed.bedCategoryMaster bc "
			+ "INNER JOIN bed.billingBedCategoryMaster bbc " 
			+ "WHERE bed.unitId=:unitId "
			+ "AND bed.organizationId=:organizationId " 
			+ "AND bedlog.status='A' " 
			+ "AND bed.status='A' "
			+ "AND bed.bedStatusId=3 "
			+ "AND bedlog.bedStatusId=3 "//BedStatusId 3---> Ready For Use
			+ "AND ward.isEr ='N' "
			+ "AND ward.isIcu='Y' "  
			+ "AND ward.icuTypeId=:icuTypeId "
			),
	
	@NamedQuery(name = "GET_ACTIVE_ER_BED_LIST", query = "SELECT bed.bedId as bedId, "
			+ "bed.bedNumber as bedNumber, " 
			+ "bed.floorId as floorId, " 
			+ "bed.roomId as roomId, "
			+ "flr.floorName as floorName, "
			+ "bed.wardId as wardId, " 
			+ "ward.wardName as wardName, "
			+ "bed.bedCategoryId as bedCategoryId, "
			+ "bed.billingBedCategoryId as billingBedCategoryId, " 
			+ "bc.bedCategoryDesc as bedCategoryDesc " 
			+ "FROM BedMaster bed "
			+ "INNER JOIN bed.listBedLogStatus bedlog " 
			+ "INNER JOIN bed.floorMaster flr "
			+ "INNER JOIN bed.wardMaster ward " 
			+ "INNER JOIN bed.bedCategoryMaster bc " 
			+ "WHERE bed.unitId=:unitId "
			+ "AND bed.organizationId=:organizationId " 
			+ "AND bedlog.status='A' " 
			+ "AND bed.status='A' "
			+ "AND bed.bedStatusId=3 "
			+ "AND bedlog.bedStatusId=3 " //BedStatusId 3---> Ready For Use
			+ "AND bed.bedCategoryId=5 "),//5 is the id for Bed category
//=====>	
	/*@NamedQuery(name = "GET_AVAILABLE_BED_LIST_BY_MUL_PARAM", query = "SELECT bed.bedId as bedId, "
			+ "bed.bedNumber as bedNumber, " 
			+ "bed.floorId as floorId, " 
			+ "bed.roomId as roomId, "
			+ "floor.floorName as floorName, "
			+ "bed.wardId as wardId, " 
			+ "ward.wardName as wardName, "
			+ "bed.bedCategoryId as bedCategoryId, "
			+ "bed.billingBedCategoryId as billingBedCategoryId " 
			+ "bc.bedCategoryDesc as bedCategoryDesc "
			+ "FROM BedMaster bed "),
*/	
	@NamedQuery(name="GET_BED_BY_WARD_ID",query="SELECT bed.bedId as bedId, "
			+ "bed.bedNumber as bedNumber "
			+ "FROM BedMaster bed "
			+ "INNER JOIN bed.listBedLogStatus bedlog "
			+ "WHERE bed.unitId=:unitId "
			+ "AND bed.organizationId=:organizationId "
			+ "AND bed.wardId=:wardId "
			+ "AND bed.status='A' "
			+ "AND bedlog.status='A' "
			+ "AND bedlog.bedStatusId=5 "
			+ "AND bed.bedStatusId=5 "//BedStatusId 5---> Booked
			),
	
	@NamedQuery(name="GET_BED_LIST_BY_WARD_ID",query="SELECT bed.bedId as bedId, "
			+ "bed.bedNumber as bedNumber, " 
			+ "bed.floorId as floorId, " 
			+ "bed.roomId as roomId, "
			+ "bed.wardId as wardId, " 
			+ "bed.bedCategoryId as bedCategoryId, "
			+ "bed.billingBedCategoryId as billingBedCategoryId "
			+ "FROM BedMaster bed "
			+ "INNER JOIN bed.listBedLogStatus bedlog "
			+ "WHERE bed.unitId=:unitId "
			+ "AND bed.organizationId=:organizationId "
			+ "AND bed.wardId=:wardId "
			+ "AND bed.status='A' "
			+ "AND bedlog.status='A' "
			+ "AND bedlog.bedStatusId=3 "
			+ "AND bed.bedStatusId=3 "
			+ "AND bed.bedCategoryId!=5 "//BedStatusId 3---> Ready to use beds
			),
	
	/*@NamedQuery(name="GET_BEDS_FOR_HOUSEKEEPING_LIST",query="SELECT bm.bedId as bedId, "
			+ "bm.floorId as floorId, "
			+ "floor.floorName as floorName, "
			+ "nur.nursingStationId as nursingStationId, "
			+ "nur.nursingStationDesc as nursingStationDesc, "
			+ "bm.bedCategoryId as bedCategoryId, "
			+ "bc.bedCategoryDesc as bedCategoryDesc, "
			+ "bm.bedNumber as bedNumber, "
			+ "hskp.priorityId as priorityId, "
			+ "priority.priorityDesc as priorityDesc, "
			+ "hskp.housekeepingId as housekeepingId, "
			+ "hskp.housekeepingStatusId as housekeepingStatusId, "
			+ "hskp.assignedPersonId as assignedPersonId, "
			+ "um.userName as userName, "
			+ "hsm.housekeepingStatusDesc as housekeepingStatusDesc, "
			+ "hskplg.note as note, "
			+ "hskplg.rejectionReasonId as rejectionReasonId, "
			+ "reason.reasonDesc as reasonDesc "
			+ "FROM BedMaster bm "
			+ "INNER JOIN bm.listBedLogStatus bedLog "
			+ "INNER JOIN bm.floorMaster floor "
			+ "INNER JOIN bm.wardMaster ward "
			+ "INNER JOIN bm.bedCategoryMaster bc "
			+ "INNER JOIN bm.housekeepingsList hskp "
			+ "INNER JOIN ward.nursingStationWardMappersList nurward "
			+ "INNER JOIN nurward.nursingStationMaster nur "
			+ "INNER JOIN hskp.housekeepingLogsList hskplg "
			+ "LEFT JOIN hskplg.reasonMaster reason "
			+ "INNER JOIN hskp.housekeepingStatusMaster hsm "
			+ "INNER JOIN hskp.priorityMaster priority "
			+ "LEFT JOIN hskp.userMaster um "
			+ "WHERE bm.bedStatusId IN (2,6) "
			+ "AND bedLog.bedStatusId IN (2,6) "
			+ "AND hskp.housekeepingStatusId IN (1,2,3,5) "
			+ "AND hskplg.housekeepingStatusId IN (1,2,3,5) "
			+ "AND bm.status='A' "
			+ "AND bedLog.status='A' "
			+ "AND bm.organizationId=:organizationId "
			+ "AND bm.unitId=:unitId "
			+ "AND hskp.status='A' "
			+ "AND hskplg.status='A' "),
	
	@NamedQuery(name="LIST_OF_VACANT_BEDS_FOR_NURSING",query="SELECT bm.bedId as bedId, "
			+ "bm.floorId as floorId, "
			+ "floor.floorName as floorName, "
			+ "nur.nursingStationId as nursingStationId, "
			+ "nur.nursingStationDesc as nursingStationDesc, "
			+ "bm.bedCategoryId as bedCategoryId, "
			+ "bc.bedCategoryDesc as bedCategoryDesc, "
			+ "bm.bedNumber as bedNumber, "
			+ "hskp.priorityId as priorityId, "
			+ "priority.priorityDesc as priorityDesc, "
			+ "hskp.housekeepingId as housekeepingId, "
			+ "hskp.housekeepingStatusId as housekeepingStatusId, "
			+ "hskp.assignedPersonId as assignedPersonId, "
			+ "um.userName as userName, "
			+ "hsm.housekeepingStatusDesc as housekeepingStatusDesc "
			+ "FROM BedMaster bm "
			+ "INNER JOIN bm.listBedLogStatus bedLog "
			+ "INNER JOIN bm.floorMaster floor "
			+ "INNER JOIN bm.wardMaster ward "
			+ "INNER JOIN bm.bedCategoryMaster bc "
			+ "INNER JOIN bm.housekeepingsList hskp "
			+ "INNER JOIN ward.nursingStationWardMappersList nurward "
			+ "INNER JOIN nurward.nursingStationMaster nur "
			+ "INNER JOIN hskp.housekeepingLogsList hskplg "
			+ "INNER JOIN hskp.housekeepingStatusMaster hsm "
			+ "INNER JOIN hskp.priorityMaster priority "
			+ "INNER JOIN hskp.userMaster um "
			+ "WHERE bm.bedStatusId = 6 "
			+ "AND bedLog.bedStatusId= 6 "
			+ "AND bm.status='A' "
			+ "AND bedLog.status='A' "
			+ "AND bm.organizationId=:organizationId "
			+ "AND bm.unitId=:unitId "
			+ "AND hskp.status='A' "
			+ "AND hskplg.status='A' ")*/
		
})

@NamedNativeQueries({
	
	@NamedNativeQuery(name="GET_BED_AVAILIBILITY",query="select count(*) as \"countBooked\", "
			+ "(select count(*) from adt.m_bed_master bm where bm.bed_category_id = :bedCat) as \"countTotal\", "
			+ "(select count(*) from adt.t_admission_patient_mapping  apm where apm.active_status = 'R' and apm.bed_category_id =:bedCat) as \"countReserved\" "
			+ "from adt.t_admission_details "
			+ "where status='A' "
			+ "and doa < :date "
			+ "and pdd > :date "
			+ "and organization_id=:orgId "
			+ "and unit_id=:unitId ")
})

@Entity
@Table(name = "m_bed_master", schema = "adt")
@SequenceGenerator(name = "bed_master_seq", sequenceName = "adt.bed_master_seq", allocationSize = 1)
public class BedMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bed_master_seq")
	@Column(name = "bed_id")
	private int bedId;

	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "floor_id")
	private Integer floorId;

	@Column(name = "ward_id")
	private Integer wardId;

	@Column(name = "room_id")
	private Integer roomId;

	@Column(name = "bed_category_id")
	private Integer bedCategoryId;

	@Column(name = "bed_status_id")
	private Integer bedStatusId;

	@Column(name = "billing_bed_category_id")
	private Integer billingBedCategoryId;

	@Column(name = "bed_code")
	private String bedCode;

	@Column(name = "bed_number")
	private String bedNumber;

	@Column(name = "is_virtual")
	private char isVirtual;

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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bedMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AdmissionDetails> admissionDetailsList;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id", insertable = false, updatable = false)
	private UnitMaster unitMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "floor_id", insertable = false, updatable = false)
	private FloorMaster floorMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ward_id", insertable = false, updatable = false)
	private WardMaster wardMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id", insertable = false, updatable = false)
	private RoomMaster roomMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bed_category_id", insertable = false, updatable = false)
	private BedCategoryMaster bedCategoryMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bed_status_id", insertable = false, updatable = false)
	private BedStatusMaster bedStatusMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "billing_bed_category_id", insertable = false, updatable = false)
	private BillingBedCategoryMaster billingBedCategoryMaster;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bedMaster", cascade = CascadeType.ALL)
	private List<BedLogStatus> listBedLogStatus;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "bedMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Transfer> transferslist;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "bedMaster2", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Transfer> transferslist2;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "bedMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Discharge> dischargesList;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "bedMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Housekeeping> housekeepingsList;
	
	/*@OneToMany(fetch = FetchType.LAZY,mappedBy = "bedMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<HousekeepingLog> housekeepingLogsList;*/

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

	public WardMaster getWardMaster() {
		return wardMaster;
	}

	public void setWardMaster(WardMaster wardMaster) {
		this.wardMaster = wardMaster;
	}

	public RoomMaster getRoomMaster() {
		return roomMaster;
	}

	public void setRoomMaster(RoomMaster roomMaster) {
		this.roomMaster = roomMaster;
	}


	public BedStatusMaster getBedStatusMaster() {
		return bedStatusMaster;
	}

	public void setBedStatusMaster(BedStatusMaster bedStatusMaster) {
		this.bedStatusMaster = bedStatusMaster;
	}

	

	public Integer getBillingBedCategoryId() {
		return billingBedCategoryId;
	}

	public void setBillingBedCategoryId(Integer billingBedCategoryId) {
		this.billingBedCategoryId = billingBedCategoryId;
	}

	public List<AdmissionDetails> getAdmissionDetailsList() {
		return admissionDetailsList;
	}

	public void setAdmissionDetailsList(List<AdmissionDetails> admissionDetailsList) {
		this.admissionDetailsList = admissionDetailsList;
	}

	public BillingBedCategoryMaster getBillingBedCategoryMaster() {
		return billingBedCategoryMaster;
	}

	public void setBillingBedCategoryMaster(BillingBedCategoryMaster billingBedCategoryMaster) {
		this.billingBedCategoryMaster = billingBedCategoryMaster;
	}

	public List<BedLogStatus> getListBedLogStatus() {
		return listBedLogStatus;
	}

	public void setListBedLogStatus(List<BedLogStatus> listBedLogStatus) {
		this.listBedLogStatus = listBedLogStatus;
	}

	public int getBedId() {
		return bedId;
	}

	public void setBedId(int bedId) {
		this.bedId = bedId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getFloorId() {
		return floorId;
	}

	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
	}

	public Integer getWardId() {
		return wardId;
	}

	public void setWardId(Integer wardId) {
		this.wardId = wardId;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Integer getBedCategoryId() {
		return bedCategoryId;
	}

	public void setBedCategoryId(Integer bedCategoryId) {
		this.bedCategoryId = bedCategoryId;
	}

	public Integer getBedStatusId() {
		return bedStatusId;
	}

	public void setBedStatusId(Integer bedStatusId) {
		this.bedStatusId = bedStatusId;
	}

	public String getBedCode() {
		return bedCode;
	}

	public void setBedCode(String bedCode) {
		this.bedCode = bedCode;
	}

	public String getBedNumber() {
		return bedNumber;
	}

	public void setBedNumber(String bedNumber) {
		this.bedNumber = bedNumber;
	}

	public char getIsVirtual() {
		return isVirtual;
	}

	public void setIsVirtual(char isVirtual) {
		this.isVirtual = isVirtual;
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
