package com.param.adt.discharge.dto;

import java.time.LocalTime;
import java.util.List;

import com.param.adt.common.ADTCommonDateUtils;

public class DischargeDto 
{
	private int dischargeId;
	
	private Integer unitId;
	
	private Integer organizationId;
	
	private Integer admissionId;
	
	private Integer doctorId;
	
	private Integer dischargeTypeId;
	
	private Integer reasonId;
	
	private Integer destinationId;
	
	private String dischargeDate;
	
	private String dischargeTime;
	
	private char isDeath;
	
	private String remarkDischarge;

	private Integer cancelReasonId;
	
	private String noteCancel;
	
	private char status;
	
	private Integer dischargeStatusId;
	
	private String dischargeDesc;
	
	private String createdDate;

	private int createdBy;
	
	private int updatedBy;
	
	private String updatedDate;
	
	private Integer bedId;
	
	private Integer bedStatusId;

	private String pdd;
	
	private Integer visitTypeId;
	
	private Integer erBedTypeAllocation;
	
	private char isCancel;
	
	private Integer shortLeaveReasonId;
 
	private Integer patientId;
	
	private Integer tPatientId;
	
	private LocalTime fromTime;
	
	private LocalTime toTime;
	
	private String stringFromTime;
	
	private String stringToTime;
	
	private String fromDate;
	
	private String toDate;
	
	private String note;
	
	private Integer rejectionReasonId;

	private String rejectionNote;
	
	private Integer authorizedBy;
	
	private Integer housekeepingId;

	private Integer nursingStationId;

	private Integer floorId;

	private Integer wardId;

	private Integer roomId;

	private Integer bedCategoryId;

	private Integer priorityId;

	private Integer assignedPersonId;

	private Integer housekeepingLogId;
	
	private Integer shortLeaveStatusId;
	
	private List<Integer> shortLeaveStatusIdList;
	
	private char isIcu;
	
	private String admissionNumber;
	
	private Integer specialityId;
	
	private String specialityName;
	
	private String uhidNumber;
	
	private Integer genderId;
	
	private String genderCode;
	
	private String dob;
	
	private String doctorName;
	
	private String floorName;
	
	private String bedNumber;
	
	private String patientName;
	
	private Integer shortLeaveId;
	
	private String shortLeaveReasonName;
	
	private String reasonDesc;
	
	private String shortLeaveStatusName;
	
	private String doctorsNote;
	
	private String billingsNote;
	
	private char isHandoverMedication;
	
	private String doa;
	
	public String getDoa() {
		return doa;
	}

	public void setDoa(String doa) {
		this.doa = doa;
	}

	public char getIsHandoverMedication() {
		return isHandoverMedication;
	}

	public void setIsHandoverMedication(char isHandoverMedication) {
		this.isHandoverMedication = isHandoverMedication;
	}

	public String getDoctorsNote() {
		return doctorsNote;
	}

	public void setDoctorsNote(String doctorsNote) {
		this.doctorsNote = doctorsNote;
	}

	public String getBillingsNote() {
		return billingsNote;
	}

	public void setBillingsNote(String billingsNote) {
		this.billingsNote = billingsNote;
	}

	public String getShortLeaveStatusName() {
		return shortLeaveStatusName;
	}

	public void setShortLeaveStatusName(String shortLeaveStatusName) {
		this.shortLeaveStatusName = shortLeaveStatusName;
	}

	public Integer getShortLeaveId() {
		return shortLeaveId;
	}

	public String getShortLeaveReasonName() {
		return shortLeaveReasonName;
	}

	public void setShortLeaveReasonName(String shortLeaveReasonName) {
		this.shortLeaveReasonName = shortLeaveReasonName;
	}

	public String getReasonDesc() {
		return reasonDesc;
	}

	public void setReasonDesc(String reasonDesc) {
		this.reasonDesc = reasonDesc;
	}

	public void setShortLeaveId(Integer shortLeaveId) {
		this.shortLeaveId = shortLeaveId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getStringFromTime() {
		return stringFromTime;
	}

	public void setStringFromTime(String stringFromTime) {
		this.stringFromTime = stringFromTime;
	}

	public String getStringToTime() {
		return stringToTime;
	}

	public void setStringToTime(String stringToTime) {
		this.stringToTime = stringToTime;
	}

	public String getAdmissionNumber() {
		return admissionNumber;
	}

	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}

	public Integer getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(Integer specialityId) {
		this.specialityId = specialityId;
	}

	public String getSpecialityName() {
		return specialityName;
	}

	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}

	public String getUhidNumber() {
		return uhidNumber;
	}

	public void setUhidNumber(String uhidNumber) {
		this.uhidNumber = uhidNumber;
	}

	public Integer getGenderId() {
		return genderId;
	}

	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}

	public String getGenderCode() {
		return genderCode;
	}

	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public String getBedNumber() {
		return bedNumber;
	}

	public void setBedNumber(String bedNumber) {
		this.bedNumber = bedNumber;
	}

	public List<Integer> getShortLeaveStatusIdList() {
		return shortLeaveStatusIdList;
	}

	public void setShortLeaveStatusIdList(List<Integer> shortLeaveStatusIdList) {
		this.shortLeaveStatusIdList = shortLeaveStatusIdList;
	}

	public Integer getShortLeaveStatusId() {
		return shortLeaveStatusId;
	}

	public void setShortLeaveStatusId(Integer shortLeaveStatusId) {
		this.shortLeaveStatusId = shortLeaveStatusId;
	}

	public char getIsIcu() {
		return isIcu;
	}

	public void setIsIcu(char isIcu) {
		this.isIcu = isIcu;
	}

	public int getDischargeId() {
		return dischargeId;
	}

	public void setDischargeId(int dischargeId) {
		this.dischargeId = dischargeId;
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

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getDischargeTypeId() {
		return dischargeTypeId;
	}

	public void setDischargeTypeId(Integer dischargeTypeId) {
		this.dischargeTypeId = dischargeTypeId;
	}

	public Integer getReasonId() {
		return reasonId;
	}

	public void setReasonId(Integer reasonId) {
		this.reasonId = reasonId;
	}

	public Integer getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(Integer destinationId) {
		this.destinationId = destinationId;
	}

	public String getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public String getDischargeTime() {
		return dischargeTime;
	}

	public void setDischargeTime(String dischargeTime) {
		this.dischargeTime = dischargeTime;
	}

	public char getIsDeath() {
		return isDeath;
	}

	public void setIsDeath(char isDeath) {
		this.isDeath = isDeath;
	}

	public String getRemarkDischarge() {
		return remarkDischarge;
	}

	public void setRemarkDischarge(String remarkDischarge) {
		this.remarkDischarge = remarkDischarge;
	}

	public Integer getCancelReasonId() {
		return cancelReasonId;
	}

	public void setCancelReasonId(Integer cancelReasonId) {
		this.cancelReasonId = cancelReasonId;
	}

	public String getNoteCancel() {
		return noteCancel;
	}

	public void setNoteCancel(String noteCancel) {
		this.noteCancel = noteCancel;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public Integer getDischargeStatusId() {
		return dischargeStatusId;
	}

	public void setDischargeStatusId(Integer dischargeStatusId) {
		this.dischargeStatusId = dischargeStatusId;
	}

	public String getDischargeDesc() {
		return dischargeDesc;
	}

	public void setDischargeDesc(String dischargeDesc) {
		this.dischargeDesc = dischargeDesc;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getBedId() {
		return bedId;
	}

	public void setBedId(Integer bedId) {
		this.bedId = bedId;
	}

	public Integer getBedStatusId() {
		return bedStatusId;
	}

	public void setBedStatusId(Integer bedStatusId) {
		this.bedStatusId = bedStatusId;
	}

	public String getPdd() {
		return pdd;
	}

	public void setPdd(String pdd) {
		this.pdd = pdd;
	}

	public Integer getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public Integer getErBedTypeAllocation() {
		return erBedTypeAllocation;
	}

	public void setErBedTypeAllocation(Integer erBedTypeAllocation) {
		this.erBedTypeAllocation = erBedTypeAllocation;
	}

	public char getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(char isCancel) {
		this.isCancel = isCancel;
	}

	
	public Integer getShortLeaveReasonId() {
		return shortLeaveReasonId;
	}

	public void setShortLeaveReasonId(Integer shortLeaveReasonId) {
		this.shortLeaveReasonId = shortLeaveReasonId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer gettPatientId() {
		return tPatientId;
	}

	public void settPatientId(Integer tPatientId) {
		this.tPatientId = tPatientId;
	}

	public LocalTime getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		if(!fromTime.equals("")){
			   this.fromTime = ADTCommonDateUtils.getLocalTime(fromTime);
			  }else{
			   this.fromTime = ADTCommonDateUtils.getLocalTime("00:00");
			  }
	}

	public LocalTime getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		if(!toTime.equals("")){
			   this.toTime = ADTCommonDateUtils.getLocalTime(toTime);
			  }else{
			   this.toTime = ADTCommonDateUtils.getLocalTime("00:00");
			  }
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getRejectionReasonId() {
		return rejectionReasonId;
	}

	public void setRejectionReasonId(Integer rejectionReasonId) {
		this.rejectionReasonId = rejectionReasonId;
	}

	public String getRejectionNote() {
		return rejectionNote;
	}

	public void setRejectionNote(String rejectionNote) {
		this.rejectionNote = rejectionNote;
	}

	public Integer getAuthorizedBy() {
		return authorizedBy;
	}

	public void setAuthorizedBy(Integer authorizedBy) {
		this.authorizedBy = authorizedBy;
	}

	public Integer getHousekeepingId() {
		return housekeepingId;
	}

	public void setHousekeepingId(Integer housekeepingId) {
		this.housekeepingId = housekeepingId;
	}

	public Integer getNursingStationId() {
		return nursingStationId;
	}

	public void setNursingStationId(Integer nursingStationId) {
		this.nursingStationId = nursingStationId;
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

	public Integer getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(Integer priorityId) {
		this.priorityId = priorityId;
	}

	public Integer getAssignedPersonId() {
		return assignedPersonId;
	}

	public void setAssignedPersonId(Integer assignedPersonId) {
		this.assignedPersonId = assignedPersonId;
	}

	public Integer getHousekeepingLogId() {
		return housekeepingLogId;
	}

	public void setHousekeepingLogId(Integer housekeepingLogId) {
		this.housekeepingLogId = housekeepingLogId;
	}
	
	
}
