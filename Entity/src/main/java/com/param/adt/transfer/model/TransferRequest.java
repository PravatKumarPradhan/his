package com.param.adt.transfer.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.adt.admission.model.Admission;
import com.param.adt.master.global.model.OrganizationMaster;
import com.param.adt.master.global.model.ReasonMaster;
import com.param.adt.master.global.model.UnitMaster;
import com.param.adt.master.unit.model.WardMaster;
import com.param.global.model.BedCategoryMaster;

@Entity
@Table(name="t_transfer_request",schema="adt")
@SequenceGenerator(name="transfer_request_seq",sequenceName="adt.transfer_request_seq",allocationSize=1)
public class TransferRequest 
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="transfer_request_seq")
	@Column(name="transfer_request_id")
	private int transferRequestId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="admission_id")
	private Integer admissionId;
	
	@Column(name="from_ward_id")
	private Integer fromWardId;
	
	@Column(name="to_ward_id")
	private Integer toWardId;
	
	@Column(name="from_bed_category_id")
	private Integer fromBedCategoryId;
	
	@Column(name="to_bed_category_id")
	private Integer toBedCategoryId;
	
	@Column(name="doctor_reason_id")
	private Integer doctorReasonId;
	
	@Column(name="adt_reason_id")
	private Integer adtReasonId;
	
	@Column(name="reason_id")
	private Integer reasonId;
	
	@Column(name="note")
	private String note;
	
	@Column(name="doctor_note")
	private String doctorNote;
	
	@Column(name="adt_note")
	private String adtNote;
	
	@Column(name="doctor_reject_reason")
	private String doctorRejectReason;
	
	@Column(name="adt_reject_reason")
	private String adtRejectReason;
	
	@Column(name="authorized_by")
	private Integer authorizedBy;
	
	@Column(name="transfer_type_id")
	private Integer transferTypeId;
	
	@Column(name="treating_doctor_id")
	private Integer treatingDoctorId;
	
	@Column(name="intensivist_request_id")
	private Integer intensivistRequestId;
	
	@Column(name="created_by")
	private int createdBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="status")
	private char status;
	
	@Column(name="transfer_status_id")
	private Integer transferStatusId;
	
	@Column(name="final_note")
	private String finalNote;
	
	@Column(name="final_reason_id")
	private Integer finalReasonId;
	
	@Column(name="workstation_reason_id")
	private Integer workstationReasonId;
	
	@Column(name="workstation_note")
	private String workstationNote;
	
	@Column(name="icu_type_id")
	private Integer icuTypeId;

	
	
	public Integer getIcuTypeId() {
		return icuTypeId;
	}

	public void setIcuTypeId(Integer icuTypeId) {
		this.icuTypeId = icuTypeId;
	}

	public Integer getWorkstationReasonId() {
		return workstationReasonId;
	}

	public void setWorkstationReasonId(Integer workstationReasonId) {
		this.workstationReasonId = workstationReasonId;
	}

	public String getWorkstationNote() {
		return workstationNote;
	}

	public void setWorkstationNote(String workstationNote) {
		this.workstationNote = workstationNote;
	}

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
	@JoinColumn(name = "from_bed_category_id", insertable = false, nullable = false, updatable = false)
	private BedCategoryMaster bedCategoryMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "from_ward_id", insertable = false, nullable = false, updatable = false)
	private WardMaster wardMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "to_bed_category_id", insertable = false, nullable = false, updatable = false)
	private BedCategoryMaster bedCategoryMaster2;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "to_ward_id", insertable = false, nullable = false, updatable = false)
	private WardMaster wardMaster2;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctor_reason_id", insertable = false, nullable = false, updatable = false)
	private ReasonMaster reasonMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "adt_reason_id", insertable = false, nullable = false, updatable = false)
	private ReasonMaster reasonMaster2;
	
	
	
	public Integer getFinalReasonId() {
		return finalReasonId;
	}

	public void setFinalReasonId(Integer finalReasonId) {
		this.finalReasonId = finalReasonId;
	}

	public String getFinalNote() {
		return finalNote;
	}

	public void setFinalNote(String finalNote) {
		this.finalNote = finalNote;
	}

	public Integer getReasonId() {
		return reasonId;
	}

	public void setReasonId(Integer reasonId) {
		this.reasonId = reasonId;
	}

	public int getTransferRequestId() {
		return transferRequestId;
	}

	public void setTransferRequestId(int transferRequestId) {
		this.transferRequestId = transferRequestId;
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

	public Integer getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(Integer admissionId) {
		this.admissionId = admissionId;
	}

	public Integer getFromWardId() {
		return fromWardId;
	}

	public void setFromWardId(Integer fromWardId) {
		this.fromWardId = fromWardId;
	}

	public Integer getToWardId() {
		return toWardId;
	}

	public void setToWardId(Integer toWardId) {
		this.toWardId = toWardId;
	}

	public Integer getFromBedCategoryId() {
		return fromBedCategoryId;
	}

	public void setFromBedCategoryId(Integer fromBedCategoryId) {
		this.fromBedCategoryId = fromBedCategoryId;
	}

	public Integer getToBedCategoryId() {
		return toBedCategoryId;
	}

	public void setToBedCategoryId(Integer toBedCategoryId) {
		this.toBedCategoryId = toBedCategoryId;
	}


	public Integer getDoctorReasonId() {
		return doctorReasonId;
	}

	public void setDoctorReasonId(Integer doctorReasonId) {
		this.doctorReasonId = doctorReasonId;
	}

	public Integer getAdtReasonId() {
		return adtReasonId;
	}

	public void setAdtReasonId(Integer adtReasonId) {
		this.adtReasonId = adtReasonId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getAuthorizedBy() {
		return authorizedBy;
	}

	public void setAuthorizedBy(Integer authorizedBy) {
		this.authorizedBy = authorizedBy;
	}

	
	public Integer getTransferTypeId() {
		return transferTypeId;
	}

	public void setTransferTypeId(Integer transferTypeId) {
		this.transferTypeId = transferTypeId;
	}

	public Integer getTreatingDoctorId() {
		return treatingDoctorId;
	}

	public void setTreatingDoctorId(Integer treatingDoctorId) {
		this.treatingDoctorId = treatingDoctorId;
	}

	public Integer getIntensivistRequestId() {
		return intensivistRequestId;
	}

	public void setIntensivistRequestId(Integer intensivistRequestId) {
		this.intensivistRequestId = intensivistRequestId;
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

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = (status == '\u0000') ? 'A' : status;
	}

	public Integer getTransferStatusId() {
		return transferStatusId;
	}

	public void setTransferStatusId(Integer transferStatusId) {
		this.transferStatusId = transferStatusId;
	}

	public String getDoctorNote() {
		return doctorNote;
	}

	public void setDoctorNote(String doctorNote) {
		this.doctorNote = doctorNote;
	}

	public String getAdtNote() {
		return adtNote;
	}

	public void setAdtNote(String adtNote) {
		this.adtNote = adtNote;
	}

	public String getDoctorRejectReason() {
		return doctorRejectReason;
	}

	public void setDoctorRejectReason(String doctorRejectReason) {
		this.doctorRejectReason = doctorRejectReason;
	}

	public String getAdtRejectReason() {
		return adtRejectReason;
	}

	public void setAdtRejectReason(String adtRejectReason) {
		this.adtRejectReason = adtRejectReason;
	}

	
	
	
	

	
}
