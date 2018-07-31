package com.param.global.dto;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import com.param.global.common.GlobalCommonDateUtils;

public class SlotMasterDto {
	
	private Integer slotId;
	
	private Integer previousSlotId;
	
	private Integer organizationId;
	
	private Integer unitId;
	
	private Integer specialityId;
	
	private Integer doctorId;
	
	private String fromDate;
	
	private String toDate;
	
	private String appointmentDate;
	
	private String appointmentDate2;
	
	private Integer dayId;
	
	private String day;
	
	private String fromTimeString;
	
	private String toTimeString;
	
	private String fromTimeString2;
	
	private String toTimeString2;
	
	private LocalTime fromTime;
	
	private LocalTime toTime;
	
	private Integer timePerSlot;
	
	private LocalTime breakFromTime;
	
	private LocalTime breakToTime;
	
	private Integer slotTypeId;
	
	private Integer visitTypeId;
	
	private Integer createdBy;
	
	private String createdDate;
	
	private Integer updatedBy;
	
	private String updatedDate;
	
	private char status;
	
	private Integer offset;
	
	private Integer count;
	
	private Integer noOfRecordsPerPage;
	
	private List<Integer> dayArrayList;
	
	private List<DoctorMasterDto> doctorArrayList;
	
	private List<SlotMasterDto> slotMasterDtosList;
	
	private List<TimeSlotsDto> breakSlotsList;
	
	private String doctorName;
	
	private String specialityName;
	
	private Integer modalityId;
	
	private Integer subSpecialityId;
	
	private String subSpecialityName;
	
	private String modalityTypeDesc;
	
	private String modalityDesc;
	
	private Integer noOfSlots;
	
	private String uhidNumber;
	
	private String remark;
	
	private Integer appointmentId;
	
	private Integer patientId;
	
	private String patientName;
	
	private String mobileNumber;
	
	private String email;
	
	private String address;
	
	private Integer prefixId;
	
	private String prefixCode;
	
	private Integer genderId;
	
	private String genderCode;
	
	private String age;
	
	private Integer appointmentStatusId;
	
	private Integer slotStatusId;
	
	private Integer cancelReasonId;
	
	private char cancelledBy;
	
	private String appointmentStatusDesc;
	
	private String firstName;
	
	private String middleName;
	
	private String lastName;
	
	private String dob;
	
	private Integer rescheduleReasonId;
	
	private char rescheduledBy;
	
	private Integer appointmentTypeId;
	
	private Integer appointmentListType;
	
	private List<Integer> specialityIdList;
	
	private List<Integer> doctorIdList;
	
	private List<Integer> modalityTypeIdList;
	
	private List<Integer> modalityIdList;
	
	private List<Integer> appointmentStatusIdList;
	
	private List<Integer> visitTypeIdList;
	
	private Integer admissionId;
	
	private Integer modalitySpecialityId;
	
	private Integer year;
	
	private Integer month;
	
	private String currentDate; //format should be yyyy-mm-dd 
	
	private int encounterId;
	
	private Date encounterDate;
	
	private String encounterTime;
	
	private String encounterNumber;
	
	private int clinicId;
	
	private Integer statusId;
	
	private Integer nursingStationId;
	
	private Integer userDefinedVisitTypeId;
	
	private String barCode;
	
	private String genderDec;
	
	private Character isConsultationStatus;
	
	private Character isAssignmentCompStatus;
	
	private Integer isBlockedUnblocked;
	
	private String consultationStopTime;
	
	private String referralTypeName;
	
	private String referralName;
	
	private String referralRemark;
	
	private Integer registrationTypeId;
	
	private Integer reasonId;
	
	private String reasonDesc;
	
	private String blockedRemark;
	
	private Integer priorityId;
	
	private Integer allowedOverBookingSlots;
	
	private Integer ehcPackageId;
	
	private List<SlotPatientDetails> slotPatientDetailsList;
	
	public Integer getEhcPackageId() {
		return ehcPackageId;
	}

	public void setEhcPackageId(Integer ehcPackageId) {
		this.ehcPackageId = ehcPackageId;
	}

	public Integer getAllowedOverBookingSlots() {
		return allowedOverBookingSlots;
	}

	public void setAllowedOverBookingSlots(Integer allowedOverBookingSlots) {
		this.allowedOverBookingSlots = allowedOverBookingSlots;
	}

	public Integer getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(Integer priorityId) {
		this.priorityId = priorityId;
	}

	public Integer getIsBlockedUnblocked() {
		return isBlockedUnblocked;
	}

	public void setIsBlockedUnblocked(Integer isBlockedUnblocked) {
		this.isBlockedUnblocked = isBlockedUnblocked;
	}

	public List<SlotPatientDetails> getSlotPatientDetailsList() {
		return slotPatientDetailsList;
	}

	public void setSlotPatientDetailsList(List<SlotPatientDetails> slotPatientDetailsList) {
		this.slotPatientDetailsList = slotPatientDetailsList;
	}

	public String getConsultationStopTime() {
		return consultationStopTime;
	}

	public void setConsultationStopTime(String consultationStopTime) {
		this.consultationStopTime = consultationStopTime;
	}

	public String getReferralTypeName() {
		return referralTypeName;
	}

	public void setReferralTypeName(String referralTypeName) {
		this.referralTypeName = referralTypeName;
	}

	public String getReferralName() {
		return referralName;
	}

	public void setReferralName(String referralName) {
		this.referralName = referralName;
	}

	public String getReferralRemark() {
		return referralRemark;
	}

	public void setReferralRemark(String referralRemark) {
		this.referralRemark = referralRemark;
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

	public String getBlockedRemark() {
		return blockedRemark;
	}

	public void setBlockedRemark(String blockedRemark) {
		this.blockedRemark = blockedRemark;
	}


	public Integer getRegistrationTypeId() {
		return registrationTypeId;
	}

	public void setRegistrationTypeId(Integer registrationTypeId) {
		this.registrationTypeId = registrationTypeId;

	}

	public String getCurrentDate() {
		return currentDate;
	}

	public Character getIsConsultationStatus() {
		return isConsultationStatus;
	}

	public void setIsConsultationStatus(Character isConsultationStatus) {
		this.isConsultationStatus = isConsultationStatus;
	}

	public Character getIsAssignmentCompStatus() {
		return isAssignmentCompStatus;
	}

	public void setIsAssignmentCompStatus(Character isAssignmentCompStatus) {
		this.isAssignmentCompStatus = isAssignmentCompStatus;
	}

	public String getGenderDec() {
		return genderDec;
	}

	public void setGenderDec(String genderDec) {
		this.genderDec = genderDec;
	}

	public Integer getUserDefinedVisitTypeId() {
		return userDefinedVisitTypeId;
	}

	public void setUserDefinedVisitTypeId(Integer userDefinedVisitTypeId) {
		this.userDefinedVisitTypeId = userDefinedVisitTypeId;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public Integer getNursingStationId() {
		return nursingStationId;
	}

	public void setNursingStationId(Integer nursingStationId) {
		this.nursingStationId = nursingStationId;
	}

	public int getClinicId() {
		return clinicId;
	}

	public void setClinicId(int clinicId) {
		this.clinicId = clinicId;
	}

	public Integer getStatusId() {
		return statusId;
		
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public int getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(int encounterId) {
		this.encounterId = encounterId;
	}


	/*public String getEncounterDate() {
		return encounterDate;
	}

	public void setEncounterDate(String encounterDate) {
		this.encounterDate = encounterDate;
	}*/

	

	public String getEncounterTime() {
		return encounterTime;
	}

	public Date getEncounterDate() {
		return encounterDate;
	}

	public void setEncounterDate(Date encounterDate) {
		this.encounterDate = encounterDate;
	}

	public void setEncounterTime(String encounterTime) {
		this.encounterTime = encounterTime;
	}

	public String getEncounterNumber() {
		return encounterNumber;
	}

	public void setEncounterNumber(String encounterNumber) {
		this.encounterNumber = encounterNumber;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public String getSubSpecialityName() {
		return subSpecialityName;
	}

	public void setSubSpecialityName(String subSpecialityName) {
		this.subSpecialityName = subSpecialityName;
	}

	public String getAppointmentDate2() {
		return appointmentDate2;
	}

	public void setAppointmentDate2(String appointmentDate2) {
		this.appointmentDate2 = appointmentDate2;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getModalitySpecialityId() {
		return modalitySpecialityId;
	}

	public void setModalitySpecialityId(Integer modalitySpecialityId) {
		this.modalitySpecialityId = modalitySpecialityId;
	}

	public Integer getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(Integer admissionId) {
		this.admissionId = admissionId;
	}

	public List<Integer> getVisitTypeIdList() {
		return visitTypeIdList;
	}

	public void setVisitTypeIdList(List<Integer> visitTypeIdList) {
		this.visitTypeIdList = visitTypeIdList;
	}

	public List<Integer> getModalityTypeIdList() {
		return modalityTypeIdList;
	}

	public void setModalityTypeIdList(List<Integer> modalityTypeIdList) {
		this.modalityTypeIdList = modalityTypeIdList;
	}

	public List<Integer> getModalityIdList() {
		return modalityIdList;
	}

	public void setModalityIdList(List<Integer> modalityIdList) {
		this.modalityIdList = modalityIdList;
	}

	public List<Integer> getSpecialityIdList() {
		return specialityIdList;
	}

	public void setSpecialityIdList(List<Integer> specialityIdList) {
		this.specialityIdList = specialityIdList;
	}

	public List<Integer> getDoctorIdList() {
		return doctorIdList;
	}

	public void setDoctorIdList(List<Integer> doctorIdList) {
		this.doctorIdList = doctorIdList;
	}

	public List<Integer> getAppointmentStatusIdList() {
		return appointmentStatusIdList;
	}

	public void setAppointmentStatusIdList(List<Integer> appointmentStatusIdList) {
		this.appointmentStatusIdList = appointmentStatusIdList;
	}

	public Integer getAppointmentListType() {
		return appointmentListType;
	}

	public void setAppointmentListType(Integer appointmentListType) {
		this.appointmentListType = appointmentListType;
	}

	public Integer getAppointmentTypeId() {
		return appointmentTypeId;
	}

	public void setAppointmentTypeId(Integer appointmentTypeId) {
		this.appointmentTypeId = appointmentTypeId;
	}

	public Integer getPreviousSlotId() {
		return previousSlotId;
	}

	public void setPreviousSlotId(Integer previousSlotId) {
		this.previousSlotId = previousSlotId;
	}

	public Integer getRescheduleReasonId() {
		return rescheduleReasonId;
	}

	public void setRescheduleReasonId(Integer rescheduleReasonId) {
		this.rescheduleReasonId = rescheduleReasonId;
	}

	public char getRescheduledBy() {
		return rescheduledBy;
	}

	public void setRescheduledBy(char rescheduledBy) {
		this.rescheduledBy = rescheduledBy;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public Integer getPrefixId() {
		return prefixId;
	}

	public void setPrefixId(Integer prefixId) {
		this.prefixId = prefixId;
	}

	public Integer getGenderId() {
		return genderId;
	}

	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAppointmentStatusDesc() {
		return appointmentStatusDesc;
	}

	public void setAppointmentStatusDesc(String appointmentStatusDesc) {
		this.appointmentStatusDesc = appointmentStatusDesc;
	}

	public Integer getAppointmentStatusId() {
		return appointmentStatusId;
	}

	public void setAppointmentStatusId(Integer appointmentStatusId) {
		this.appointmentStatusId = appointmentStatusId;
	}

	public Integer getSlotStatusId() {
		return slotStatusId;
	}

	public void setSlotStatusId(Integer slotStatusId) {
		this.slotStatusId = slotStatusId;
	}

	public Integer getCancelReasonId() {
		return cancelReasonId;
	}

	public void setCancelReasonId(Integer cancelReasonId) {
		this.cancelReasonId = cancelReasonId;
	}

	public char getCancelledBy() {
		return cancelledBy;
	}

	public void setCancelledBy(char cancelledBy) {
		this.cancelledBy = cancelledBy;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGenderCode() {
		return genderCode;
	}

	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}

	public String getPrefixCode() {
		return prefixCode;
	}

	public void setPrefixCode(String prefixCode) {
		this.prefixCode = prefixCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUhidNumber() {
		return uhidNumber;
	}

	public void setUhidNumber(String uhidNumber) {
		this.uhidNumber = uhidNumber;
	}

	public String getFromTimeString2() {
		return fromTimeString2;
	}

	public void setFromTimeString2(String fromTimeString2) {
		this.fromTimeString2 = fromTimeString2;
	}

	public String getToTimeString2() {
		return toTimeString2;
	}

	public void setToTimeString2(String toTimeString2) {
		this.toTimeString2 = toTimeString2;
	}

	public Integer getNoOfSlots() {
		return noOfSlots;
	}

	public void setNoOfSlots(Integer noOfSlots) {
		this.noOfSlots = noOfSlots;
	}

	public String getModalityTypeDesc() {
		return modalityTypeDesc;
	}

	public void setModalityTypeDesc(String modalityTypeDesc) {
		this.modalityTypeDesc = modalityTypeDesc;
	}

	public String getModalityDesc() {
		return modalityDesc;
	}

	public void setModalityDesc(String modalityDesc) {
		this.modalityDesc = modalityDesc;
	}

	public Integer getModalityId() {
		return modalityId;
	}

	public void setModalityId(Integer modalityId) {
		this.modalityId = modalityId;
	}

	

	public Integer getSubSpecialityId() {
		return subSpecialityId;
	}

	public void setSubSpecialityId(Integer subSpecialityId) {
		this.subSpecialityId = subSpecialityId;
	}

	public String getFromTimeString() {
		return fromTimeString;
	}

	public void setFromTimeString(String fromTimeString) {
		this.fromTimeString = fromTimeString;
	}

	public String getToTimeString() {
		return toTimeString;
	}

	public void setToTimeString(String toTimeString) {
		this.toTimeString = toTimeString;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getSpecialityName() {
		return specialityName;
	}

	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Integer getNoOfRecordsPerPage() {
		return noOfRecordsPerPage;
	}

	public void setNoOfRecordsPerPage(Integer noOfRecordsPerPage) {
		this.noOfRecordsPerPage = noOfRecordsPerPage;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<TimeSlotsDto> getBreakSlotsList() {
		return breakSlotsList;
	}

	public void setBreakSlotsList(List<TimeSlotsDto> breakSlotsList) {
		this.breakSlotsList = breakSlotsList;
	}

	public List<Integer> getDayArrayList() {
		return dayArrayList;
	}

	public void setDayArrayList(List<Integer> dayArrayList) {
		this.dayArrayList = dayArrayList;
	}

	public List<DoctorMasterDto> getDoctorArrayList() {
		return doctorArrayList;
	}

	public void setDoctorArrayList(List<DoctorMasterDto> doctorArrayList) {
		this.doctorArrayList = doctorArrayList;
	}

	public List<SlotMasterDto> getSlotMasterDtosList() {
		return slotMasterDtosList;
	}

	public void setSlotMasterDtosList(List<SlotMasterDto> slotMasterDtosList) {
		this.slotMasterDtosList = slotMasterDtosList;
	}

	public Integer getSlotId() {
		return slotId;
	}

	public void setSlotId(Integer slotId) {
		this.slotId = slotId;
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

	public Integer getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(Integer specialityId) {
		this.specialityId = specialityId;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
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

	public Integer getDayId() {
		return dayId;
	}

	public void setDayId(Integer dayId) {
		this.dayId = dayId;
	}

	public LocalTime getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		if(!fromTime.equals("")){
			this.fromTime = GlobalCommonDateUtils.getLocalTime(fromTime);
		}else{
			this.fromTime = GlobalCommonDateUtils.getLocalTime("00:00");
		}
	}

	public LocalTime getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		if(!toTime.equals("")){
			this.toTime = GlobalCommonDateUtils.getLocalTime(toTime);
		}else{
			this.toTime = GlobalCommonDateUtils.getLocalTime("00:00");
		}
	}

	public Integer getTimePerSlot() {
		return timePerSlot;
	}

	public void setTimePerSlot(Integer timePerSlot) {
		this.timePerSlot = timePerSlot;
	}

	public LocalTime getBreakFromTime() {
		return breakFromTime;
	}

	public void setBreakFromTime(String breakFromTime) {
		if(!breakFromTime.equals("")){
			this.breakFromTime = GlobalCommonDateUtils.getLocalTime(breakFromTime);
		}else{
			this.breakFromTime = GlobalCommonDateUtils.getLocalTime("00:00");
		}
	}

	public LocalTime getBreakToTime() {
		return breakToTime;
	}

	public void setBreakToTime(String breakToTime) {
		if(!breakToTime.equals("")){
			this.breakToTime = GlobalCommonDateUtils.getLocalTime(breakToTime);
		}else{
			this.breakToTime = GlobalCommonDateUtils.getLocalTime("00:00");
		}
	}

	public Integer getSlotTypeId() {
		return slotTypeId;
	}

	public void setSlotTypeId(Integer slotTypeId) {
		this.slotTypeId = slotTypeId;
	}

	public Integer getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
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

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}
	
	

}
