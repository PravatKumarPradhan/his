package com.param.entity.model.adt;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name="AdmissionDetail")
@Table(name="t_admission_details", schema="adt")
public class AdmissionDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="admission_details_id")
	private Integer admissionDetailsId;

	@Column(name="admission_type_id")
	private Integer admissionTypeId;

	@Column(name="attendent_meal_pref_id")
	private Integer attendentMealPrefId;

	@Column(name="bed_category_id")
	private Integer bedCategoryId;

	@Column(name="billing_bed_category_id")
	private Integer billingBedCategoryId;

	@Column(name="cancel_by")
	private Integer cancelBy;

	@Column(name="cancel_date")
	private Timestamp cancelDate;

	@Column(name="created_by")
	private Integer createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;

	private Timestamp doa;

	@Column(name="er_bed_type_allocation")
	private Integer erBedTypeAllocation;

	@Column(name="extend_reason_id")
	private Integer extendReasonId;

	@Column(name="floor_id")
	private Integer floorId;

	@Column(name="future_discharge_date")
	private Timestamp futureDischargeDate;

	@Column(name="is_cancel")
	private String isCancel;

	@Column(name="is_close")
	private String isClose;

	@Column(name="is_critical")
	private String isCritical;

	@Column(name="is_discharge")
	private String isDischarge;

	@Column(name="is_high_risk")
	private String isHighRisk;

	@Column(name="is_medico_legal")
	private String isMedicoLegal;

	@Column(name="is_short_leave")
	private String isShortLeave;

	@Column(name="is_transfer_of_care")
	private String isTransferOfCare;

	@Column(name="meal_pref_id")
	private Integer mealPrefId;

	@Column(name="organization_id")
	private Integer organizationId;

	private Timestamp pdd;

	private String remark;

	@Column(name="room_id")
	private Integer roomId;

	private String status;

	@Column(name="triage_category_id")
	private Integer triageCategoryId;

	@Column(name="unit_id")
	private Integer unitId;

	@Column(name="updated_by")
	private Integer updatedBy;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="admission_id")
	private Admission admission;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bed_id")
	private BedMaster bedMaster;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ward_id")
	private WardMasters wardMaster;

	public AdmissionDetail() {
	}

	public Integer getAdmissionDetailsId() {
		return this.admissionDetailsId;
	}

	public void setAdmissionDetailsId(Integer admissionDetailsId) {
		this.admissionDetailsId = admissionDetailsId;
	}

	public Integer getAdmissionTypeId() {
		return this.admissionTypeId;
	}

	public void setAdmissionTypeId(Integer admissionTypeId) {
		this.admissionTypeId = admissionTypeId;
	}

	public Integer getAttendentMealPrefId() {
		return this.attendentMealPrefId;
	}

	public void setAttendentMealPrefId(Integer attendentMealPrefId) {
		this.attendentMealPrefId = attendentMealPrefId;
	}

	public Integer getBedCategoryId() {
		return this.bedCategoryId;
	}

	public void setBedCategoryId(Integer bedCategoryId) {
		this.bedCategoryId = bedCategoryId;
	}

	public Integer getBillingBedCategoryId() {
		return this.billingBedCategoryId;
	}

	public void setBillingBedCategoryId(Integer billingBedCategoryId) {
		this.billingBedCategoryId = billingBedCategoryId;
	}

	public Integer getCancelBy() {
		return this.cancelBy;
	}

	public void setCancelBy(Integer cancelBy) {
		this.cancelBy = cancelBy;
	}

	public Timestamp getCancelDate() {
		return this.cancelDate;
	}

	public void setCancelDate(Timestamp cancelDate) {
		this.cancelDate = cancelDate;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getDoa() {
		return this.doa;
	}

	public void setDoa(Timestamp doa) {
		this.doa = doa;
	}

	public Integer getErBedTypeAllocation() {
		return this.erBedTypeAllocation;
	}

	public void setErBedTypeAllocation(Integer erBedTypeAllocation) {
		this.erBedTypeAllocation = erBedTypeAllocation;
	}

	public Integer getExtendReasonId() {
		return this.extendReasonId;
	}

	public void setExtendReasonId(Integer extendReasonId) {
		this.extendReasonId = extendReasonId;
	}

	public Integer getFloorId() {
		return this.floorId;
	}

	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
	}

	public Timestamp getFutureDischargeDate() {
		return this.futureDischargeDate;
	}

	public void setFutureDischargeDate(Timestamp futureDischargeDate) {
		this.futureDischargeDate = futureDischargeDate;
	}

	public String getIsCancel() {
		return this.isCancel;
	}

	public void setIsCancel(String isCancel) {
		this.isCancel = isCancel;
	}

	public String getIsClose() {
		return this.isClose;
	}

	public void setIsClose(String isClose) {
		this.isClose = isClose;
	}

	public String getIsCritical() {
		return this.isCritical;
	}

	public void setIsCritical(String isCritical) {
		this.isCritical = isCritical;
	}

	public String getIsDischarge() {
		return this.isDischarge;
	}

	public void setIsDischarge(String isDischarge) {
		this.isDischarge = isDischarge;
	}

	public String getIsHighRisk() {
		return this.isHighRisk;
	}

	public void setIsHighRisk(String isHighRisk) {
		this.isHighRisk = isHighRisk;
	}

	public String getIsMedicoLegal() {
		return this.isMedicoLegal;
	}

	public void setIsMedicoLegal(String isMedicoLegal) {
		this.isMedicoLegal = isMedicoLegal;
	}

	public String getIsShortLeave() {
		return this.isShortLeave;
	}

	public void setIsShortLeave(String isShortLeave) {
		this.isShortLeave = isShortLeave;
	}

	public String getIsTransferOfCare() {
		return this.isTransferOfCare;
	}

	public void setIsTransferOfCare(String isTransferOfCare) {
		this.isTransferOfCare = isTransferOfCare;
	}

	public Integer getMealPrefId() {
		return this.mealPrefId;
	}

	public void setMealPrefId(Integer mealPrefId) {
		this.mealPrefId = mealPrefId;
	}

	public Integer getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Timestamp getPdd() {
		return this.pdd;
	}

	public void setPdd(Timestamp pdd) {
		this.pdd = pdd;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getRoomId() {
		return this.roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTriageCategoryId() {
		return this.triageCategoryId;
	}

	public void setTriageCategoryId(Integer triageCategoryId) {
		this.triageCategoryId = triageCategoryId;
	}

	public Integer getUnitId() {
		return this.unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Admission getAdmission() {
		return this.admission;
	}

	public void setAdmission(Admission admission) {
		this.admission = admission;
	}

	public BedMaster getBedMaster() {
		return this.bedMaster;
	}

	public void setBedMaster(BedMaster bedMaster) {
		this.bedMaster = bedMaster;
	}

	public WardMasters getWardMaster() {
		return this.wardMaster;
	}

	public void setWardMaster(WardMasters wardMaster) {
		this.wardMaster = wardMaster;
	}

}