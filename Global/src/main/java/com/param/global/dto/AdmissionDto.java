package com.param.global.dto;

import java.math.BigInteger;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class AdmissionDto {
	
	private Integer orderedBy;

	private Date timeOfServiceOrder;

	private Integer cancelReasonId;
	
	private Integer isCancelCheckBox;

	private Integer isRecheduleCheckBox;

	private Integer admissionId;

	private Integer admissionNoteId;

	private Integer unitId;

	private Integer organizationId;

	private Integer patientId;

	private Integer visitTypeId;

	private Integer againstVisitId;

	private Integer doctorId;

	private Integer specialityId;

	private String specialityName;

	private String genderCode;

	private Integer kinDetailsId;

	private String prefixCode;

	private String relationDesc;

	private String identificationName;

	private String kinName;

	private Integer relationId;

	private String mobileNo;

	private String phoneNo;

	private Integer identificationId;

	private String identificationNo;

	private String expiry;

	private char isGuarantor;

	private String address;

	private Integer countryId;

	private Integer stateId;

	private Integer districtId;

	private Integer cityId;

	private Integer areaId;

	private Integer visitorPassTypeId;

	private String visitorPassTypeDesc;

	private Integer offset;

	private Integer noOfRecordsPerPage;

	private Integer triageCategoryId;

	private Integer erBedTypeAllocation;

	private Double age;

	private char ageFormat;

	private char status;

	private String createdDate;

	private Integer createdBy;

	private Integer updatedBy;

	private String updatedDate;

	private Integer wardId;

	private Integer floorId;

	private Integer roomId;

	private Integer bedId;

	private String doa;

	private String pdd;

	private Integer mealPrefId;

	private Integer attendentMealPrefId;

	private List<KinDetailsDto> kinArray;

	private Integer bedCategoryId;

	private Integer billingBedCategoryId;

	private Integer paymentEntitlementId;

	private String admissionNumber;

	private Integer admissionDetailsId;

	private Integer admissionTypeId;

	private char isShortLeave;

	private char isDischarge;

	private char isCancel;

	private Integer cancelBy;

	private Date cancelDate;

	private char isClose;

	private String remark;

	private char isHighRisk;

	private char isCritical;

	private Integer patientCategoryId;

	private char activeStatus;

	private Integer bedStatusId;

	private String paymentEntitlementDesc;

	private String patientCategory;

	private String bedNumber;

	private String UHIDNumber;

	private Integer requestToDoctorId;

	private Integer doctorSpecialityId;

	private Integer reasonId;

	private String reasonDesc;

	private String dFirstName;

	private String dMiddleName;

	private String dLastName;

	private String pFirstName;

	private String pMiddleName;

	private String pLastName;

	private String dob;

	private Integer preVisitId;

	private String visitTypeName;

	private char isFlexiableDate;

	private char admissionStatus;

	private Integer canceledBy;

	private String canceledDate;

	private Integer prefixId;

	private String prefixDesc;

	private Integer genderId;

	private String code;

	private String bedCategoryDesc;

	private Integer toBedCategoryId;

	private String toBedCategoryDesc;

	private Integer unknownPatientId;

	private String accompaniedBy;

	private Integer priorityId;

	private char isMedicoLegal;

	private String patientName;

	private String tUhid;

	private String genderName;

	private Integer erBedType;

	private Integer pPatientId;

	private Integer tPatientId;

	private Integer id;

	private String ukgenderName;
	
	private String note;
	
	private Integer transferId;
	
	private Integer hierarchyId;

	private Integer transferStatusId;
	
	private Integer toHierarchyId;
	
	private String wardName;
	
	private Integer nursingStationId;
	
	private String nursingStationDesc;
	
	private String futureDischargeDate;
	
	private Integer dischargeId;
	
	private Integer dischargeStatusId;
	
	private String dischargeDesc;
	
	private Integer patientSevicesOrderId;
	
	private Integer serviceId;
	
	private Integer orderStatusId;
	
	private String orderDesc;
	
	private Integer subSpecialityId;
	
	private String subSpecialityName;
	
	private String serviceName;
	
	private String floorName;
	
	private Integer dischargeTypeId;
	
	private String dischargeTypeName;
	
	private String transferStatusDesc;
	
	private Integer toBedId;
	
	private Integer toRoomId;
	
	private Integer toWardId;
	
	private String toBedNumber;
	
	private String toWardname;
	
	private Integer doctorReasonId;
	
	private Integer adtReasonId;
	
	private String doctorNote;
	
	private String adtNote;
	
	private String doctorRejectReason;
	
	private String adtRejectReason;
	
	private String doctorReasonDesc;
	
	private String adtReasonDesc;
	
	private Integer transferRequestId;
	
	private char patientType;
	
	private String doctorName;
	
	private Integer treatingDoctorId;
	
	private Integer toFloorId;
	
	private Integer fromBedId;
	
	private String uhidNumber;

	private Integer medicoLegalId;
	
	private String priorityDesc;
	
	private String encounterDate;
	
	private String noOfHours;
	
	private String remarkDischarge;
	
	private String serviceStandardName;
	
	private LocalTime fromTime;
	
	private String fromTime2;
	
	private String transferTime;
	
	private Integer scheduleId;
	
	private Integer transferTypeId;
	
	private Integer patientSurgeryOrderId;
	
	private Integer surgeryId;
	
	private String surgeryName;
	
	private Integer fromWardId;
	
	private Integer fromBedCategoryId;
	
	private Integer authorizedBy;
	
	private Integer intensivistRequestId;
	
	private String finalNote;
	
	private Integer finalReasonId;
	
	private Integer workstationReasonId;
	
	private String workstationNote;
	
	private String scheduleDate;
	
	private String modalityDesc;
	
	private Integer modalityId;
	
	private String surgeryTime;
	
	private String surgeryDate;
	
	private BigInteger surgeryCount;
	
	private Integer extendReasonId;
	
	private char isTransferOfCare;
	
	private char isIcu;
	
	private Integer waitListNumber;
	
	private Integer bedCategoryWaitingListId;
	
	private Integer type;
	
	private String oldDoa;
	
	private Integer oldBedCategoryId;
	
	private Integer dPatientId;
	
	
	
	public Integer getdPatientId() {
		return dPatientId;
	}

	public void setdPatientId(Integer dPatientId) {
		this.dPatientId = dPatientId;
	}

	public String getOldDoa() {
		return oldDoa;
	}

	public void setOldDoa(String oldDoa) {
		this.oldDoa = oldDoa;
	}

	public Integer getOldBedCategoryId() {
		return oldBedCategoryId;
	}

	public void setOldBedCategoryId(Integer oldBedCategoryId) {
		this.oldBedCategoryId = oldBedCategoryId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getBedCategoryWaitingListId() {
		return bedCategoryWaitingListId;
	}

	public void setBedCategoryWaitingListId(Integer bedCategoryWaitingListId) {
		this.bedCategoryWaitingListId = bedCategoryWaitingListId;
	}

	public Integer getWaitListNumber() {
		return waitListNumber;
	}

	public void setWaitListNumber(Integer waitListNumber) {
		this.waitListNumber = waitListNumber;
	}

	public char getIsIcu() {
		return isIcu;
	}

	public void setIsIcu(char isIcu) {
		this.isIcu = isIcu;
	}

	public char getIsTransferOfCare() {
		return isTransferOfCare;
	}

	public void setIsTransferOfCare(char isTransferOfCare) {
		this.isTransferOfCare = (isTransferOfCare == '\u0000') ? 'N' : isTransferOfCare;
	}

	public Integer getExtendReasonId() {
		return extendReasonId;
	}

	public void setExtendReasonId(Integer extendReasonId) {
		this.extendReasonId = extendReasonId;
	}

	public BigInteger getSurgeryCount() {
		return surgeryCount;
	}

	public void setSurgeryCount(BigInteger surgeryCount) {
		this.surgeryCount = surgeryCount;
	}

	public String getSurgeryTime() {
		return surgeryTime;
	}

	public void setSurgeryTime(String surgeryTime) {
		this.surgeryTime = surgeryTime;
	}

	public String getSurgeryDate() {
		return surgeryDate;
	}

	public void setSurgeryDate(String surgeryDate) {
		this.surgeryDate = surgeryDate;
	}

	public Integer getModalityId() {
		return modalityId;
	}

	public void setModalityId(Integer modalityId) {
		this.modalityId = modalityId;
	}

	public String getModalityDesc() {
		return modalityDesc;
	}

	public void setModalityDesc(String modalityDesc) {
		this.modalityDesc = modalityDesc;
	}

	public String getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public Integer getFromWardId() {
		return fromWardId;
	}

	public void setFromWardId(Integer fromWardId) {
		this.fromWardId = fromWardId;
	}

	public Integer getFromBedCategoryId() {
		return fromBedCategoryId;
	}

	public void setFromBedCategoryId(Integer fromBedCategoryId) {
		this.fromBedCategoryId = fromBedCategoryId;
	}

	public Integer getAuthorizedBy() {
		return authorizedBy;
	}

	public void setAuthorizedBy(Integer authorizedBy) {
		this.authorizedBy = authorizedBy;
	}

	public Integer getIntensivistRequestId() {
		return intensivistRequestId;
	}

	public void setIntensivistRequestId(Integer intensivistRequestId) {
		this.intensivistRequestId = intensivistRequestId;
	}

	public String getFinalNote() {
		return finalNote;
	}

	public void setFinalNote(String finalNote) {
		this.finalNote = finalNote;
	}

	public Integer getFinalReasonId() {
		return finalReasonId;
	}

	public void setFinalReasonId(Integer finalReasonId) {
		this.finalReasonId = finalReasonId;
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

	public void setFromTime(LocalTime fromTime) {
		this.fromTime = fromTime;
	}

	public Integer getPatientSurgeryOrderId() {
		return patientSurgeryOrderId;
	}

	public void setPatientSurgeryOrderId(Integer patientSurgeryOrderId) {
		this.patientSurgeryOrderId = patientSurgeryOrderId;
	}

	public Integer getSurgeryId() {
		return surgeryId;
	}

	public void setSurgeryId(Integer surgeryId) {
		this.surgeryId = surgeryId;
	}

	public String getSurgeryName() {
		return surgeryName;
	}

	public void setSurgeryName(String surgeryName) {
		this.surgeryName = surgeryName;
	}

	public Integer getTransferTypeId() {
		return transferTypeId;
	}

	public void setTransferTypeId(Integer transferTypeId) {
		this.transferTypeId = transferTypeId;
	}

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getTransferTime() {
		return transferTime;
	}

	public void setTransferTime(String transferTime) {
		this.transferTime = transferTime;
	}

	public String getFromTime2() {
		return fromTime2;
	}

	public void setFromTime2(String fromTime2) {
		this.fromTime2 = fromTime2;
	}

	public LocalTime getFromTime() {
		return fromTime;
	}

	public String getServiceStandardName() {
		return serviceStandardName;
	}

	public void setServiceStandardName(String serviceStandardName) {
		this.serviceStandardName = serviceStandardName;
	}

	public Integer getOrderedBy() {
		return orderedBy;
	}

	public void setOrderedBy(Integer orderedBy) {
		this.orderedBy = orderedBy;
	}

	public Date getTimeOfServiceOrder() {
		return timeOfServiceOrder;
	}

	public void setTimeOfServiceOrder(Date timeOfServiceOrder) {
		this.timeOfServiceOrder = timeOfServiceOrder;
	}

	public Integer getCancelReasonId() {
		return cancelReasonId;
	}

	public void setCancelReasonId(Integer cancelReasonId) {
		this.cancelReasonId = cancelReasonId;
	}

	public String getRemarkDischarge() {
		return remarkDischarge;
	}

	public void setRemarkDischarge(String remarkDischarge) {
		this.remarkDischarge = remarkDischarge;
	}

	public String getNoOfHours() {
		return noOfHours;
	}

	public void setNoOfHours(String noOfHours) {
		this.noOfHours = noOfHours;
	}

	public String getEncounterDate() {
		return encounterDate;
	}

	public void setEncounterDate(String encounterDate) {
		this.encounterDate = encounterDate;
	}

	public String getPriorityDesc() {
		return priorityDesc;
	}

	public void setPriorityDesc(String priorityDesc) {
		this.priorityDesc = priorityDesc;
	}

	public Integer getMedicoLegalId() {
		return medicoLegalId;
	}

	public void setMedicoLegalId(Integer medicoLegalId) {
		this.medicoLegalId = medicoLegalId;
	}

	public String getUhidNumber() {
		return uhidNumber;
	}

	public void setUhidNumber(String uhidNumber) {
		this.uhidNumber = uhidNumber;
	}

	public Integer getIsCancelCheckBox() {
		return isCancelCheckBox;
	}

	public void setIsCancelCheckBox(Integer isCancelCheckBox) {
		this.isCancelCheckBox = (isCancelCheckBox == '\u0000') ? 'N' : isCancelCheckBox;
	}

	public Integer getIsRecheduleCheckBox() {
		return isRecheduleCheckBox;
	}

	public void setIsRecheduleCheckBox(Integer isRecheduleCheckBox) {
		this.isRecheduleCheckBox = (isRecheduleCheckBox == '\u0000') ? 'N' : isRecheduleCheckBox;
	}

	public Integer getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(Integer admissionId) {
		this.admissionId = admissionId;
	}

	public Integer getAdmissionNoteId() {
		return admissionNoteId;
	}

	public void setAdmissionNoteId(Integer admissionNoteId) {
		this.admissionNoteId = admissionNoteId;
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

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public Integer getAgainstVisitId() {
		return againstVisitId;
	}

	public void setAgainstVisitId(Integer againstVisitId) {
		this.againstVisitId = againstVisitId;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
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

	public String getGenderCode() {
		return genderCode;
	}

	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}

	public Integer getKinDetailsId() {
		return kinDetailsId;
	}

	public void setKinDetailsId(Integer kinDetailsId) {
		this.kinDetailsId = kinDetailsId;
	}

	public String getPrefixCode() {
		return prefixCode;
	}

	public void setPrefixCode(String prefixCode) {
		this.prefixCode = prefixCode;
	}

	public String getRelationDesc() {
		return relationDesc;
	}

	public void setRelationDesc(String relationDesc) {
		this.relationDesc = relationDesc;
	}

	public String getIdentificationName() {
		return identificationName;
	}

	public void setIdentificationName(String identificationName) {
		this.identificationName = identificationName;
	}

	public String getKinName() {
		return kinName;
	}

	public void setKinName(String kinName) {
		this.kinName = kinName;
	}

	public Integer getRelationId() {
		return relationId;
	}

	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Integer getIdentificationId() {
		return identificationId;
	}

	public void setIdentificationId(Integer identificationId) {
		this.identificationId = identificationId;
	}

	public String getIdentificationNo() {
		return identificationNo;
	}

	public void setIdentificationNo(String identificationNo) {
		this.identificationNo = identificationNo;
	}

	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

	public char getIsGuarantor() {
		return isGuarantor;
	}

	public void setIsGuarantor(char isGuarantor) {
		this.isGuarantor = (isGuarantor == '\u0000') ? 'N' : isGuarantor;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getVisitorPassTypeId() {
		return visitorPassTypeId;
	}

	public void setVisitorPassTypeId(Integer visitorPassTypeId) {
		this.visitorPassTypeId = visitorPassTypeId;
	}

	public String getVisitorPassTypeDesc() {
		return visitorPassTypeDesc;
	}

	public void setVisitorPassTypeDesc(String visitorPassTypeDesc) {
		this.visitorPassTypeDesc = visitorPassTypeDesc;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getNoOfRecordsPerPage() {
		return noOfRecordsPerPage;
	}

	public void setNoOfRecordsPerPage(Integer noOfRecordsPerPage) {
		this.noOfRecordsPerPage = noOfRecordsPerPage;
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

	public Double getAge() {
		return age;
	}

	public void setAge(Double age) {
		this.age = age;
	}

	public char getAgeFormat() {
		return ageFormat;
	}

	public void setAgeFormat(char ageFormat) {
		this.ageFormat = (ageFormat == '\u0000') ? 'Y' : ageFormat;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = (status == '\u0000') ? 'A' : status;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
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

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getWardId() {
		return wardId;
	}

	public void setWardId(Integer wardId) {
		this.wardId = wardId;
	}

	public Integer getFloorId() {
		return floorId;
	}

	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
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

	public String getDoa() {
		return doa;
	}

	public void setDoa(String doa) {
		this.doa = doa;
	}

	public String getPdd() {
		return pdd;
	}

	public void setPdd(String pdd) {
		this.pdd = pdd;
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

	public List<KinDetailsDto> getKinArray() {
		return kinArray;
	}

	public void setKinArray(List<KinDetailsDto> kinArray) {
		this.kinArray = kinArray;
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

	public Integer getPaymentEntitlementId() {
		return paymentEntitlementId;
	}

	public void setPaymentEntitlementId(Integer paymentEntitlementId) {
		this.paymentEntitlementId = paymentEntitlementId;
	}

	public String getAdmissionNumber() {
		return admissionNumber;
	}

	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}

	public Integer getAdmissionDetailsId() {
		return admissionDetailsId;
	}

	public void setAdmissionDetailsId(Integer admissionDetailsId) {
		this.admissionDetailsId = admissionDetailsId;
	}

	public Integer getAdmissionTypeId() {
		return admissionTypeId;
	}

	public void setAdmissionTypeId(Integer admissionTypeId) {
		this.admissionTypeId = admissionTypeId;
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

	public char getIsClose() {
		return isClose;
	}

	public void setIsClose(char isClose) {
		this.isClose = isClose;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Integer getPatientCategoryId() {
		return patientCategoryId;
	}

	public void setPatientCategoryId(Integer patientCategoryId) {
		this.patientCategoryId = patientCategoryId;
	}

	public char getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(char activeStatus) {
		this.activeStatus = (activeStatus == '\u0000') ? 'I' : activeStatus;
	}

	public Integer getBedStatusId() {
		return bedStatusId;
	}

	public void setBedStatusId(Integer bedStatusId) {
		this.bedStatusId = bedStatusId;
	}

	public String getPaymentEntitlementDesc() {
		return paymentEntitlementDesc;
	}

	public void setPaymentEntitlementDesc(String paymentEntitlementDesc) {
		this.paymentEntitlementDesc = paymentEntitlementDesc;
	}

	public String getPatientCategory() {
		return patientCategory;
	}

	public void setPatientCategory(String patientCategory) {
		this.patientCategory = patientCategory;
	}

	public String getBedNumber() {
		return bedNumber;
	}

	public void setBedNumber(String bedNumber) {
		this.bedNumber = bedNumber;
	}

	public String getUHIDNumber() {
		return UHIDNumber;
	}

	public void setUHIDNumber(String uHIDNumber) {
		UHIDNumber = uHIDNumber;
	}

	public Integer getRequestToDoctorId() {
		return requestToDoctorId;
	}

	public void setRequestToDoctorId(Integer requestToDoctorId) {
		this.requestToDoctorId = requestToDoctorId;
	}

	public Integer getDoctorSpecialityId() {
		return doctorSpecialityId;
	}

	public void setDoctorSpecialityId(Integer doctorSpecialityId) {
		this.doctorSpecialityId = doctorSpecialityId;
	}

	public Integer getReasonId() {
		return reasonId;
	}

	public void setReasonId(Integer reasonId) {
		this.reasonId = reasonId;
	}

	public String getReasonDesc() {
		return reasonDesc;
	}

	public void setReasonDesc(String reasonDesc) {
		this.reasonDesc = reasonDesc;
	}

	public String getdFirstName() {
		return dFirstName;
	}

	public void setdFirstName(String dFirstName) {
		this.dFirstName = dFirstName;
	}

	public String getdMiddleName() {
		return dMiddleName;
	}

	public void setdMiddleName(String dMiddleName) {
		this.dMiddleName = dMiddleName;
	}

	public String getdLastName() {
		return dLastName;
	}

	public void setdLastName(String dLastName) {
		this.dLastName = dLastName;
	}

	public String getpFirstName() {
		return pFirstName;
	}

	public void setpFirstName(String pFirstName) {
		this.pFirstName = pFirstName;
	}

	public String getpMiddleName() {
		return pMiddleName;
	}

	public void setpMiddleName(String pMiddleName) {
		this.pMiddleName = pMiddleName;
	}

	public String getpLastName() {
		return pLastName;
	}

	public void setpLastName(String pLastName) {
		this.pLastName = pLastName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public Integer getPreVisitId() {
		return preVisitId;
	}

	public void setPreVisitId(Integer preVisitId) {
		this.preVisitId = preVisitId;
	}

	public String getVisitTypeName() {
		return visitTypeName;
	}

	public void setVisitTypeName(String visitTypeName) {
		this.visitTypeName = visitTypeName;
	}

	public char getIsFlexiableDate() {
		return isFlexiableDate;
	}

	public void setIsFlexiableDate(char isFlexiableDate) {
		this.isFlexiableDate = (isFlexiableDate == '\u0000') ? 'N' : isFlexiableDate;
	}

	public char getAdmissionStatus() {
		return admissionStatus;
	}

	public void setAdmissionStatus(char admissionStatus) {
		this.admissionStatus = (admissionStatus == '\u0000') ? 'I' : admissionStatus;
	}

	public Integer getCanceledBy() {
		return canceledBy;
	}

	public void setCanceledBy(Integer canceledBy) {
		this.canceledBy = canceledBy;
	}

	public String getCanceledDate() {
		return canceledDate;
	}

	public void setCanceledDate(String canceledDate) {
		this.canceledDate = canceledDate;
	}

	public Integer getPrefixId() {
		return prefixId;
	}

	public void setPrefixId(Integer prefixId) {
		this.prefixId = prefixId;
	}

	public String getPrefixDesc() {
		return prefixDesc;
	}

	public void setPrefixDesc(String prefixDesc) {
		this.prefixDesc = prefixDesc;
	}

	public Integer getGenderId() {
		return genderId;
	}

	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBedCategoryDesc() {
		return bedCategoryDesc;
	}

	public void setBedCategoryDesc(String bedCategoryDesc) {
		this.bedCategoryDesc = bedCategoryDesc;
	}

	public Integer getToBedCategoryId() {
		return toBedCategoryId;
	}

	public void setToBedCategoryId(Integer toBedCategoryId) {
		this.toBedCategoryId = toBedCategoryId;
	}

	public String getToBedCategoryDesc() {
		return toBedCategoryDesc;
	}

	public void setToBedCategoryDesc(String toBedCategoryDesc) {
		this.toBedCategoryDesc = toBedCategoryDesc;
	}

	public Integer getUnknownPatientId() {
		return unknownPatientId;
	}

	public void setUnknownPatientId(Integer unknownPatientId) {
		this.unknownPatientId = unknownPatientId;
	}

	public String getAccompaniedBy() {
		return accompaniedBy;
	}

	public void setAccompaniedBy(String accompaniedBy) {
		this.accompaniedBy = accompaniedBy;
	}

	public Integer getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(Integer priorityId) {
		this.priorityId = priorityId;
	}

	public char getIsMedicoLegal() {
		return isMedicoLegal;
	}

	public void setIsMedicoLegal(char isMedicoLegal) {
		this.isMedicoLegal = (isMedicoLegal == '\u0000') ? 'N' : isMedicoLegal;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String gettUhid() {
		return tUhid;
	}

	public void settUhid(String tUhid) {
		this.tUhid = tUhid;
	}

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	public Integer getErBedType() {
		return erBedType;
	}

	public void setErBedType(Integer erBedType) {
		this.erBedType = erBedType;
	}

	public Integer getpPatientId() {
		return pPatientId;
	}

	public void setpPatientId(Integer pPatientId) {
		this.pPatientId = pPatientId;
	}

	public Integer gettPatientId() {
		return tPatientId;
	}

	public void settPatientId(Integer tPatientId) {
		this.tPatientId = tPatientId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUkgenderName() {
		return ukgenderName;
	}

	public void setUkgenderName(String ukgenderName) {
		this.ukgenderName = ukgenderName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getTransferId() {
		return transferId;
	}

	public void setTransferId(Integer transferId) {
		this.transferId = transferId;
	}

	public Integer getHierarchyId() {
		return hierarchyId;
	}

	public void setHierarchyId(Integer hierarchyId) {
		this.hierarchyId = hierarchyId;
	}

	public Integer getTransferStatusId() {
		return transferStatusId;
	}

	public void setTransferStatusId(Integer transferStatusId) {
		this.transferStatusId = transferStatusId;
	}

	public Integer getToHierarchyId() {
		return toHierarchyId;
	}

	public void setToHierarchyId(Integer toHierarchyId) {
		this.toHierarchyId = toHierarchyId;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
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

	public String getFutureDischargeDate() {
		return futureDischargeDate;
	}

	public void setFutureDischargeDate(String futureDischargeDate) {
		this.futureDischargeDate = futureDischargeDate;
	}

	public Integer getDischargeId() {
		return dischargeId;
	}

	public void setDischargeId(Integer dischargeId) {
		this.dischargeId = dischargeId;
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

	public Integer getPatientSevicesOrderId() {
		return patientSevicesOrderId;
	}

	public void setPatientSevicesOrderId(Integer patientSevicesOrderId) {
		this.patientSevicesOrderId = patientSevicesOrderId;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getOrderStatusId() {
		return orderStatusId;
	}

	public void setOrderStatusId(Integer orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public Integer getSubSpecialityId() {
		return subSpecialityId;
	}

	public void setSubSpecialityId(Integer subSpecialityId) {
		this.subSpecialityId = subSpecialityId;
	}

	public String getSubSpecialityName() {
		return subSpecialityName;
	}

	public void setSubSpecialityName(String subSpecialityName) {
		this.subSpecialityName = subSpecialityName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public Integer getDischargeTypeId() {
		return dischargeTypeId;
	}

	public void setDischargeTypeId(Integer dischargeTypeId) {
		this.dischargeTypeId = dischargeTypeId;
	}

	public String getDischargeTypeName() {
		return dischargeTypeName;
	}

	public void setDischargeTypeName(String dischargeTypeName) {
		this.dischargeTypeName = dischargeTypeName;
	}

	public String getTransferStatusDesc() {
		return transferStatusDesc;
	}

	public void setTransferStatusDesc(String transferStatusDesc) {
		this.transferStatusDesc = transferStatusDesc;
	}

	public Integer getToBedId() {
		return toBedId;
	}

	public void setToBedId(Integer toBedId) {
		this.toBedId = toBedId;
	}

	public Integer getToRoomId() {
		return toRoomId;
	}

	public void setToRoomId(Integer toRoomId) {
		this.toRoomId = toRoomId;
	}

	public Integer getToWardId() {
		return toWardId;
	}

	public void setToWardId(Integer toWardId) {
		this.toWardId = toWardId;
	}

	public String getToBedNumber() {
		return toBedNumber;
	}

	public void setToBedNumber(String toBedNumber) {
		this.toBedNumber = toBedNumber;
	}

	public String getToWardname() {
		return toWardname;
	}

	public void setToWardname(String toWardname) {
		this.toWardname = toWardname;
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

	public String getDoctorReasonDesc() {
		return doctorReasonDesc;
	}

	public void setDoctorReasonDesc(String doctorReasonDesc) {
		this.doctorReasonDesc = doctorReasonDesc;
	}

	public String getAdtReasonDesc() {
		return adtReasonDesc;
	}

	public void setAdtReasonDesc(String adtReasonDesc) {
		this.adtReasonDesc = adtReasonDesc;
	}

	public Integer getTransferRequestId() {
		return transferRequestId;
	}

	public void setTransferRequestId(Integer transferRequestId) {
		this.transferRequestId = transferRequestId;
	}

	public char getPatientType() {
		return patientType;
	}

	public void setPatientType(char patientType) {
		this.patientType = (patientType == '\u0000') ? 'N' : patientType;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public Integer getTreatingDoctorId() {
		return treatingDoctorId;
	}

	public void setTreatingDoctorId(Integer treatingDoctorId) {
		this.treatingDoctorId = treatingDoctorId;
	}

	public Integer getToFloorId() {
		return toFloorId;
	}

	public void setToFloorId(Integer toFloorId) {
		this.toFloorId = toFloorId;
	}

	public Integer getFromBedId() {
		return fromBedId;
	}

	public void setFromBedId(Integer fromBedId) {
		this.fromBedId = fromBedId;
	}
	
	
	
	/*
	 * "UHIDNumber" : "UHID1001", 
	 * "accompaniedBy" : "Acc by Dr", 
	 * "admissionTypeId" : 4,
	 * "age" : "24" ,
	 * "ageFormat" : "Y", 
	 * "createdBy" : 1 ,
	 * "createdDate" : "01-12-2017 12:39:23", 
	 * "genderId" : 1 ,
	 * "isMedicoLegal" : "Y", 
	 * "organizationId" : 1 ,
	 * "patientName" : "Leo Andres Messi", 
	 * "priorityId" : 1 ,
	 * "status" : "A" ,
	 * "unitId" : 1 ,
	 * "updatedBy" : 1 ,
	 * "updatedDate" : "01-12-2017 12:39:23", 
	 * "visitTypeId" : 4
	 */
	
	

}
