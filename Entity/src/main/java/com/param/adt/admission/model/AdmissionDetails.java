package com.param.adt.admission.model;


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

import com.param.adt.master.global.model.AdmissionTypeMaster;
import com.param.adt.master.global.model.BedMaster;
import com.param.adt.master.global.model.OrganizationMaster;
import com.param.adt.master.global.model.ReasonMaster;
import com.param.adt.master.global.model.UnitMaster;
import com.param.adt.master.unit.model.BillingBedCategoryMaster;
import com.param.adt.master.unit.model.FloorMaster;
import com.param.adt.master.unit.model.MealPreferenceMaster;
import com.param.adt.master.unit.model.RoomMaster;
import com.param.adt.master.unit.model.WardMaster;
import com.param.global.model.BedCategoryMaster;
@NamedQueries({
	@NamedQuery(name="GET_ADMISSION_DETAILS_BY_ADMISSION_ID",query="SELECT adm.admissionDetailsId as admissionDetailsId, "
			+ "adm.admissionId as admissionId, "
			+ "adm.unitId as unitId, "
			+ "adm.organizationId as organizationId, "
			+ "adm.admissionTypeId as admissionTypeId, "
			+ "adm.floorId as floorId, "
			+ "adm.wardId as wardId, "
			+ "adm.roomId as roomId, "
			+ "adm.bedId as bedId, "
			+ "adm.isHighRisk as isHighRisk, "
			+ "adm.isCritical as isCritical, "
			+ "to_char(adm.doa,'dd-MM-yyyy HH:mm:ss') as doa, "
			+ "adm.mealPrefId as mealPrefId, "
			+ "adm.attendentMealPrefId as attendentMealPrefId, "
			+ "adm.bedCategoryId as bedCategoryId, "
			+ "adm.billingBedCategoryId as billingBedCategoryId, "
			+ "adm.triageCategoryId as triageCategoryId, "
			+ "adm.erBedTypeAllocation as erBedTypeAllocation "
			+ "FROM AdmissionDetails adm "
			+ "WHERE adm.admissionId=:admissionId "
			+ "AND adm.status='A' "
			+ "AND adm.unitId=:unitId "
			+ "AND adm.organizationId=:organizationId ")
})
@Entity
@Table(name="t_admission_details",schema="adt")
@SequenceGenerator(name="admission_details_seq", sequenceName="adt.admission_details_seq",allocationSize=1)
public class AdmissionDetails 
{

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="admission_details_seq")
	@Column(name="admission_details_id")
	private int admissionDetailsId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="admission_id")
	private Integer admissionId;
	
	@Column(name="admission_type_id")
	private Integer admissionTypeId;
	
	@Column(name="floor_id")
	private Integer floorId;
	
	@Column(name="ward_id")
	private Integer wardId;
	
	@Column(name="room_id")
	private Integer roomId;
	
	@Column(name="bed_id")
	private Integer bedId;
	
	@Column(name="cancel_by")
	private Integer cancelBy;
	
	@Column(name="cancel_date")
	private Date cancelDate;
	
	@Column(name="remark")
	private String remark;
	
	@Column(name="doa")
	private Date doa;
	
	@Column(name="pdd")
	private Date pdd;
	
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "created_by")
	private Integer createdBy;
	
	@Column(name="updated_by")
	private Integer updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="meal_pref_id")
	private Integer mealPrefId;
	
	@Column(name="attendent_meal_pref_id")
	private Integer attendentMealPrefId;
	
	@Column(name="bed_category_id")
	private Integer bedCategoryId;
	
	@Column(name="billing_bed_category_id")
	private Integer billingBedCategoryId;
	
	@Column(name="triage_category_id")
	private Integer triageCategoryId;
	
	@Column(name="er_bed_type_allocation")
	private Integer erBedTypeAllocation;
	
	@Column(name="future_discharge_date")
	private Date futureDischargeDate;
	
	@Column(name="extend_reason_id")
	private Integer extendReasonId;
	
	@Column(name="is_medico_legal")
	private char isMedicoLegal;
	
	@Column(name="is_transfer_of_care")
	private char isTransferOfCare;
	
	@Column(name="is_short_leave")
	private char isShortLeave;
	
	@Column(name="is_discharge")
	private char isDischarge;
	
	@Column(name="is_cancel")
	private char isCancel;
	
	@Column(name="is_high_risk")
	private char isHighRisk;
	
	@Column(name="is_critical")
	private char isCritical;
	
	@Column(name="status")
	private char status;
	
	@Column(name="is_close")
	private char isClose;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "extend_reason_id", insertable = false, nullable = false, updatable = false)
	private ReasonMaster reasonMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, nullable = false, updatable = false)
	private OrganizationMaster organizationMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id", insertable = false, nullable = false, updatable = false)
	private UnitMaster unitMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admission_id", insertable = false, nullable = false, updatable = false)
	private Admission admission;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admission_type_id", insertable = false, nullable = false, updatable = false)
	private AdmissionTypeMaster admissionTypeMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ward_id", insertable = false, nullable = false, updatable = false)
	private WardMaster wardMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id", insertable = false, nullable = false, updatable = false)
	private RoomMaster roomMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bed_id", insertable = false, nullable = false, updatable = false)
	private BedMaster bedMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "floor_id", insertable = false, nullable = false, updatable = false)
	private FloorMaster floorMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "meal_pref_id", insertable = false, nullable = false, updatable = false)
	private MealPreferenceMaster mealPreferenceMaster1;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "attendent_meal_pref_id", insertable = false, nullable = false, updatable = false)
	private MealPreferenceMaster attendentMealPreferenceMaster1;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bed_category_id", insertable = false, nullable = false, updatable = false)
	private BedCategoryMaster bedCategoryMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "billing_bed_category_id", insertable = false, nullable = false, updatable = false)
	private BillingBedCategoryMaster billingBedCategoryMaster;

	public int getAdmissionDetailsId() {
		return admissionDetailsId;
	}

	public void setAdmissionDetailsId(int admissionDetailsId) {
		this.admissionDetailsId = admissionDetailsId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(Integer admissionId) {
		this.admissionId = admissionId;
	}

	public Integer getAdmissionTypeId() {
		return admissionTypeId;
	}

	public void setAdmissionTypeId(Integer admissionTypeId) {
		this.admissionTypeId = admissionTypeId;
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

	public Integer getBedId() {
		return bedId;
	}

	public void setBedId(Integer bedId) {
		this.bedId = bedId;
	}

	public Integer getCancelBy() {
		return cancelBy;
	}

	public void setCancelBy(Integer cancelBy) {
		this.cancelBy = cancelBy;
	}

	public Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getDoa() {
		return doa;
	}

	public void setDoa(Date doa) {
		this.doa = doa;
	}

	public Date getPdd() {
		return pdd;
	}

	public void setPdd(Date pdd) {
		this.pdd = pdd;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
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

	public Integer getMealPrefId() {
		return mealPrefId;
	}

	public void setMealPrefId(Integer mealPrefId) {
		this.mealPrefId = mealPrefId;
	}

	public Integer getAttendentMealPrefId() {
		return attendentMealPrefId;
	}

	public void setAttendentMealPrefId(Integer attendentMealPrefId) {
		this.attendentMealPrefId = attendentMealPrefId;
	}

	public Integer getBedCategoryId() {
		return bedCategoryId;
	}

	public void setBedCategoryId(Integer bedCategoryId) {
		this.bedCategoryId = bedCategoryId;
	}

	public Integer getBillingBedCategoryId() {
		return billingBedCategoryId;
	}

	public void setBillingBedCategoryId(Integer billingBedCategoryId) {
		this.billingBedCategoryId = billingBedCategoryId;
	}

	public Integer getTriageCategoryId() {
		return triageCategoryId;
	}

	public void setTriageCategoryId(Integer triageCategoryId) {
		this.triageCategoryId = triageCategoryId;
	}

	public Integer getErBedTypeAllocation() {
		return erBedTypeAllocation;
	}

	public void setErBedTypeAllocation(Integer erBedTypeAllocation) {
		this.erBedTypeAllocation = erBedTypeAllocation;
	}

	public Date getFutureDischargeDate() {
		return futureDischargeDate;
	}

	public void setFutureDischargeDate(Date futureDischargeDate) {
		this.futureDischargeDate = futureDischargeDate;
	}

	public Integer getExtendReasonId() {
		return extendReasonId;
	}

	public void setExtendReasonId(Integer extendReasonId) {
		this.extendReasonId = extendReasonId;
	}

	public char getIsMedicoLegal() {
		return isMedicoLegal;
	}

	public void setIsMedicoLegal(char isMedicoLegal) {
		this.isMedicoLegal = (isMedicoLegal == '\u0000') ? 'N' : isMedicoLegal;
	}



	public char getIsTransferOfCare() {
		return isTransferOfCare;
	}

	public void setIsTransferOfCare(char isTransferOfCare) {
		
		this.isTransferOfCare = (isTransferOfCare == '\u0000') ? 'N' : isTransferOfCare;
		System.out.println("==>"+this.isTransferOfCare);
	}

	public char getIsShortLeave() {
		return isShortLeave;
	}

	public void setIsShortLeave(char isShortLeave) {
		this.isShortLeave = (isShortLeave == '\u0000') ? 'N' : isShortLeave;
	}

	public char getIsDischarge() {
		return isDischarge;
	}

	public void setIsDischarge(char isDischarge) {
		this.isDischarge = (isDischarge == '\u0000') ? 'N' : isDischarge;
	}

	public char getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(char isCancel) {
		this.isCancel = (isCancel == '\u0000') ? 'N' : isCancel;
	}

	public char getIsHighRisk() {
		return isHighRisk;
	}

	public void setIsHighRisk(char isHighRisk) {
		this.isHighRisk = (isHighRisk == '\u0000') ? 'N' : isHighRisk;
	}

	public char getIsCritical() {
		return isCritical;
	}

	public void setIsCritical(char isCritical) {
		this.isCritical = (isCritical == '\u0000') ? 'N' : isCritical;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = (status == '\u0000') ? 'A' : status;
	}

	public char getIsClose() {
		return isClose;
	}

	public void setIsClose(char isClose) {
		this.isClose = (isClose == '\u0000') ? 'N' : isClose;

	}
	
	

	
}
