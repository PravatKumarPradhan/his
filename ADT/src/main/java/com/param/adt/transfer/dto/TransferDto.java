package com.param.adt.transfer.dto;

import java.util.List;

public class TransferDto {
	private int transferId;

	private Integer organizationId;

	private Integer unitId;

	private Integer admissionId;

	private Integer fromBedCategoryId;

	private Integer fromBillingBedCategoryId;

	private Integer fromWardId;

	private Integer fromRoomId;

	private Integer fromBedId;

	private String fromDate;

	private String fromTime;

	private Integer toBillingBedCategoryId;

	private Integer toWardId;

	private Integer toRoomId;

	private Integer toBedId;

	private String toDate;

	private String toTime;

	private char isPrimary;

	private Integer transferRequestId;

	private Integer toBedCategoryId;

	private Integer reasonId;

	private String note;

	private Integer authorizedBy;

	private String rejectReason;

	private Integer transferTypeId;

	private Integer treatingDoctorId;

	private Integer intensivistRequestId;

	private int createdBy;

	private String createdDate;

	private int updatedBy;

	private String updatedDate;

	private char status;

	private Integer transferStatusId;

	private Integer admissionDetailsId;
	
	private String UHIDNumber;
	
	private String patientName;
	
	private Integer genderId;
	
	private String genderCode;
	
	private String dob;
	
	private Integer wardId;
	
	private String wardName;
	
	private String floorName;
	
	private Integer floorId;
	
	private String bedNumber;
	
	private Integer doctorId;
	
	private String doctorName;
	
	private String reasonDesc;
	
	private Integer patientId;
	
	private Integer tPatientId;
	
	private String fromBedCategoryDesc;
	
	private String toBedCategoryDesc;
	
	private Integer doctorReasonId;
	
	private Integer adtReasonId;
	
	private String doctorNote;
	
	private String adtNote;
	
	private String doctorRejectReason;
	
	private String adtRejectReason;
	
	private String fromWardName;
	
	private String fromBedNumber;
	
	private Integer fromFloorId;
	
	private String toWardName;
	
	private String toBedNumber;
	
	private Integer toFloorId;
	
	private String finalNote;
	
	private Integer finalReasonId;
	
	private Integer workstationReasonId;
	
	private String workstationNote;
	
	private List<Integer> wardList;
	
	private List<Integer> transferTypeIdList;
	
	private Integer icuTypeId;
	
	private String icuType;
	
	private Integer transferOfCareId;
	
	private Integer requestBy;
	
	private Integer requestTo;
	
	private Integer specialityId;
	
	private Integer rejectReasonId;
	
	private String specialityName;
	
	private Integer bedId;
	
	private String transferStatusDesc;

	private Integer nursingStationId;
	
	private String nursingStationDesc;
	
	private String remark;
	
	private Integer toSpecialityId;
	
	private Integer bySpecialityId;
	
	private String toSpecialityName;
	
	private String bySpecialityName;
	
	private String rejectReasonDesc;
	
	
	/*
	 * admissionId : 96 authorizedBy : 1 organizationId : 1 remark : "Accepted"
	 * requestTo : 1 toSpecialityId : 1 transferOfCareId : 1 transferStatusId :
	 * 11 transferTypeId : 4 unitId : 1 updatedBy : 1 updatedDate :
	 * "23-12-2017 11:27:34"
	 */
	
	
	public Integer getToSpecialityId() {
		return toSpecialityId;
	}

	public String getRejectReasonDesc() {
		return rejectReasonDesc;
	}

	public void setRejectReasonDesc(String rejectReasonDesc) {
		this.rejectReasonDesc = rejectReasonDesc;
	}

	public void setToSpecialityId(Integer toSpecialityId) {
		this.toSpecialityId = toSpecialityId;
	}

	public Integer getBySpecialityId() {
		return bySpecialityId;
	}

	public void setBySpecialityId(Integer bySpecialityId) {
		this.bySpecialityId = bySpecialityId;
	}

	public String getToSpecialityName() {
		return toSpecialityName;
	}

	public void setToSpecialityName(String toSpecialityName) {
		this.toSpecialityName = toSpecialityName;
	}

	public String getBySpecialityName() {
		return bySpecialityName;
	}

	public void setBySpecialityName(String bySpecialityName) {
		this.bySpecialityName = bySpecialityName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getNursingStationId() {
		return nursingStationId;
	}

	public void setNursingStationId(Integer nursingStationId) {
		this.nursingStationId = nursingStationId;
	}

	public String getNursingStationDesc() {
		return nursingStationDesc;
	}

	public void setNursingStationDesc(String nursingStationDesc) {
		this.nursingStationDesc = nursingStationDesc;
	}

	public String getTransferStatusDesc() {
		return transferStatusDesc;
	}

	public void setTransferStatusDesc(String transferStatusDesc) {
		this.transferStatusDesc = transferStatusDesc;
	}

	public String getSpecialityName() {
		return specialityName;
	}

	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}

	public Integer getBedId() {
		return bedId;
	}

	public void setBedId(Integer bedId) {
		this.bedId = bedId;
	}

	public Integer getTransferOfCareId() {
		return transferOfCareId;
	}

	public void setTransferOfCareId(Integer transferOfCareId) {
		this.transferOfCareId = transferOfCareId;
	}

	public Integer getRequestBy() {
		return requestBy;
	}

	public void setRequestBy(Integer requestBy) {
		this.requestBy = requestBy;
	}

	public Integer getRequestTo() {
		return requestTo;
	}

	public void setRequestTo(Integer requestTo) {
		this.requestTo = requestTo;
	}

	public Integer getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(Integer specialityId) {
		this.specialityId = specialityId;
	}

	public Integer getRejectReasonId() {
		return rejectReasonId;
	}

	public void setRejectReasonId(Integer rejectReasonId) {
		this.rejectReasonId = rejectReasonId;
	}

	public List<Integer> getWardList() {
		return wardList;
	}

	public String getIcuType() {
		return icuType;
	}

	public void setIcuType(String icuType) {
		this.icuType = icuType;
	}

	public List<Integer> getTransferTypeIdList() {
		return transferTypeIdList;
	}

	public void setTransferTypeIdList(List<Integer> transferTypeIdList) {
		this.transferTypeIdList = transferTypeIdList;
	}

	public Integer getIcuTypeId() {
		return icuTypeId;
	}

	public void setIcuTypeId(Integer icuTypeId) {
		this.icuTypeId = icuTypeId;
	}

	public void setWardList(List<Integer> wardList) {
		this.wardList = wardList;
	}

	public Integer getTransferTypeId() {
		return transferTypeId;
	}

	public void setTransferTypeId(Integer transferTypeId) {
		this.transferTypeId = transferTypeId;
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

	public int getTransferId() {
		return transferId;
	}

	public void setTransferId(int transferId) {
		this.transferId = transferId;
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

	public Integer getFromBedCategoryId() {
		return fromBedCategoryId;
	}

	public void setFromBedCategoryId(Integer fromBedCategoryId) {
		this.fromBedCategoryId = fromBedCategoryId;
	}

	public Integer getFromBillingBedCategoryId() {
		return fromBillingBedCategoryId;
	}

	public void setFromBillingBedCategoryId(Integer fromBillingBedCategoryId) {
		this.fromBillingBedCategoryId = fromBillingBedCategoryId;
	}

	public Integer getFromWardId() {
		return fromWardId;
	}

	public void setFromWardId(Integer fromWardId) {
		this.fromWardId = fromWardId;
	}

	public Integer getFromRoomId() {
		return fromRoomId;
	}

	public void setFromRoomId(Integer fromRoomId) {
		this.fromRoomId = fromRoomId;
	}

	public Integer getFromBedId() {
		return fromBedId;
	}

	public void setFromBedId(Integer fromBedId) {
		this.fromBedId = fromBedId;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public Integer getToBillingBedCategoryId() {
		return toBillingBedCategoryId;
	}

	public void setToBillingBedCategoryId(Integer toBillingBedCategoryId) {
		this.toBillingBedCategoryId = toBillingBedCategoryId;
	}

	public Integer getToWardId() {
		return toWardId;
	}

	public void setToWardId(Integer toWardId) {
		this.toWardId = toWardId;
	}

	public Integer getToRoomId() {
		return toRoomId;
	}

	public void setToRoomId(Integer toRoomId) {
		this.toRoomId = toRoomId;
	}

	public Integer getToBedId() {
		return toBedId;
	}

	public void setToBedId(Integer toBedId) {
		this.toBedId = toBedId;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public char getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(char isPrimary) {
		this.isPrimary = isPrimary;
	}

	public Integer getTransferRequestId() {
		return transferRequestId;
	}

	public void setTransferRequestId(Integer transferRequestId) {
		this.transferRequestId = transferRequestId;
	}

	public Integer getToBedCategoryId() {
		return toBedCategoryId;
	}

	public void setToBedCategoryId(Integer toBedCategoryId) {
		this.toBedCategoryId = toBedCategoryId;
	}

	public Integer getReasonId() {
		return reasonId;
	}

	public void setReasonId(Integer reasonId) {
		this.reasonId = reasonId;
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

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
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

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
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

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public Integer getTransferStatusId() {
		return transferStatusId;
	}

	public void setTransferStatusId(Integer transferStatusId) {
		this.transferStatusId = transferStatusId;
	}

	public Integer getAdmissionDetailsId() {
		return admissionDetailsId;
	}

	public void setAdmissionDetailsId(Integer admissionDetailsId) {
		this.admissionDetailsId = admissionDetailsId;
	}

	public String getUHIDNumber() {
		return UHIDNumber;
	}

	public void setUHIDNumber(String uHIDNumber) {
		UHIDNumber = uHIDNumber;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
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

	public Integer getWardId() {
		return wardId;
	}

	public void setWardId(Integer wardId) {
		this.wardId = wardId;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public Integer getFloorId() {
		return floorId;
	}

	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
	}

	public String getBedNumber() {
		return bedNumber;
	}

	public void setBedNumber(String bedNumber) {
		this.bedNumber = bedNumber;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getReasonDesc() {
		return reasonDesc;
	}

	public void setReasonDesc(String reasonDesc) {
		this.reasonDesc = reasonDesc;
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

	public String getFromBedCategoryDesc() {
		return fromBedCategoryDesc;
	}

	public void setFromBedCategoryDesc(String fromBedCategoryDesc) {
		this.fromBedCategoryDesc = fromBedCategoryDesc;
	}

	public String getToBedCategoryDesc() {
		return toBedCategoryDesc;
	}

	public void setToBedCategoryDesc(String toBedCategoryDesc) {
		this.toBedCategoryDesc = toBedCategoryDesc;
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

	public String getFromWardName() {
		return fromWardName;
	}

	public void setFromWardName(String fromWardName) {
		this.fromWardName = fromWardName;
	}

	public String getFromBedNumber() {
		return fromBedNumber;
	}

	public void setFromBedNumber(String fromBedNumber) {
		this.fromBedNumber = fromBedNumber;
	}

	public Integer getFromFloorId() {
		return fromFloorId;
	}

	public void setFromFloorId(Integer fromFloorId) {
		this.fromFloorId = fromFloorId;
	}

	public String getToWardName() {
		return toWardName;
	}

	public void setToWardName(String toWardName) {
		this.toWardName = toWardName;
	}

	public String getToBedNumber() {
		return toBedNumber;
	}

	public void setToBedNumber(String toBedNumber) {
		this.toBedNumber = toBedNumber;
	}

	public Integer getToFloorId() {
		return toFloorId;
	}

	public void setToFloorId(Integer toFloorId) {
		this.toFloorId = toFloorId;
	}
	
	

}
