package com.param.opd.encounter.dto;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.dto.KinDetailsDto;

public class EncounterMasterDto {
	public Integer getPayeeId() {
		return payeeId;
	}

	public void setPayeeId(Integer payeeId) {
		this.payeeId = payeeId;
	}


	private int encounterId;

	private Integer patientId;

	private Integer visitTypeId;

	private Integer doctorId;
	
	private Integer sourceId;

	private Long encounterDate;

	private String encounterDateString;
	
	private String encounterDateString2;

	private LocalTime encounterTime;

	private Character isReferal;

	private Integer deptId;

	private String remark;

	private Integer kinDetailsId;

	private Integer paymentEntitlementTypeId;

	private String encounterNumber;

	private Long createdDate;

	private Integer createdBy;

	private Long updatedDate;

	private Integer updatedBy;

	private Character status;

	private Integer unitId;

	private Integer organizationId;

	private Date encounterDateTime;

	private List<KinDetailsDto> listKinDetailsDto;

	private Integer appointmentId;

	private Character isScheduledAppointment;

	private String doctorName;

	private Integer specialityId;

	private String specialityName;

	private Integer modalityId;

	private Integer appointmentTypeId;

	private String currentDate; // format should be yyyy-mm-dd

	private Integer systemGeneratedVisitTypeId;

	private Integer userDefinedVisitTypeId;

	private Integer reasonIdForChangingVisitType;

	private String remarkForChangingVisitType;
	
	private String patientName;
	
	private String genderDec;
	
	private String age;
	
	private String uhidNumber;
	
	private String barCode;
	
	private Integer encounterTypeId;
	
	private Integer clinicId;
	
	private Integer referralTypeId;
	
	private Integer referralId;
	
	private String referralRemark;
	
	private String referralDesc;
	
	private String companyDesc;
	
	private String paymentEntitlementDesc;
	
	private String patientCategory;
	
	private Integer defaultSelfTariffId;
	
	private Integer serviceId;
	
	private Integer payeeId;
	
	
	
	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}


	public Integer getDefaultSelfTariffId() {
		return defaultSelfTariffId;
	}


	public void setDefaultSelfTariffId(Integer defaultSelfTariffId) {
		this.defaultSelfTariffId = defaultSelfTariffId;
	}


	public String getPatientCategory() {
		return patientCategory;
	}


	public void setPatientCategory(String patientCategory) {
		this.patientCategory = patientCategory;
	}


	public String getReferralDesc() {
		return referralDesc;
	}


	public void setReferralDesc(String referralDesc) {
		this.referralDesc = referralDesc;
	}


	public String getCompanyDesc() {
		return companyDesc;
	}


	public void setCompanyDesc(String companyDesc) {
		this.companyDesc = companyDesc;
	}


	public String getPaymentEntitlementDesc() {
		return paymentEntitlementDesc;
	}


	public void setPaymentEntitlementDesc(String paymentEntitlementDesc) {
		this.paymentEntitlementDesc = paymentEntitlementDesc;
	}


	public Integer getClinicId() {
		return clinicId;
	}


	public void setClinicId(Integer clinicId) {
		this.clinicId = clinicId;
	}


	public Integer getReferralTypeId() {
		return referralTypeId;
	}


	public void setReferralTypeId(Integer referralTypeId) {
		this.referralTypeId = referralTypeId;
	}


	public Integer getReferralId() {
		return referralId;
	}


	public void setReferralId(Integer referralId) {
		this.referralId = referralId;
	}


	public String getReferralRemark() {
		return referralRemark;
	}


	public void setReferralRemark(String referralRemark) {
		this.referralRemark = referralRemark;
	}


	public String getUhidNumber() {
		return uhidNumber;
	}


	public String getBarCode() {
		return barCode;
	}


	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}


	public void setUhidNumber(String uhidNumber) {
		this.uhidNumber = uhidNumber;
	}


	

	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public String getGenderDec() {
		return genderDec;
	}


	public void setGenderDec(String genderDec) {
		this.genderDec = genderDec;
	}


	public String getPatientName() {
		return patientName;
	}


	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Integer getSystemGeneratedVisitTypeId() {
		return systemGeneratedVisitTypeId;
	}


	public void setSystemGeneratedVisitTypeId(Integer systemGeneratedVisitTypeId) {
		this.systemGeneratedVisitTypeId = systemGeneratedVisitTypeId;
	}

	public Integer getUserDefinedVisitTypeId() {
		return userDefinedVisitTypeId;
	}

	public void setUserDefinedVisitTypeId(Integer userDefinedVisitTypeId) {
		this.userDefinedVisitTypeId = userDefinedVisitTypeId;
	}

	public Integer getReasonIdForChangingVisitType() {
		return reasonIdForChangingVisitType;
	}

	public void setReasonIdForChangingVisitType(Integer reasonIdForChangingVisitType) {
		this.reasonIdForChangingVisitType = reasonIdForChangingVisitType;
	}

	public String getRemarkForChangingVisitType() {
		return remarkForChangingVisitType;
	}

	public void setRemarkForChangingVisitType(String remarkForChangingVisitType) {
		this.remarkForChangingVisitType = remarkForChangingVisitType;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public String getEncounterDateString() {
		return encounterDateString;
	}

	public void setEncounterDateString(String encounterDateString) {
		this.encounterDateString = encounterDateString;
	}

	public Integer getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(Integer specialityId) {
		this.specialityId = specialityId;
	}

	public Integer getAppointmentTypeId() {
		return appointmentTypeId;
	}

	public void setAppointmentTypeId(Integer appointmentTypeId) {
		this.appointmentTypeId = appointmentTypeId;
	}

	public Integer getModalityId() {
		return modalityId;
	}

	public void setModalityId(Integer modalityId) {
		this.modalityId = modalityId;
	}

	public int getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(int encounterId) {
		this.encounterId = encounterId;
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

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public Long getEncounterDate() {
		return encounterDate;
	}

	public void setEncounterDate(Long encounterDate) {
		this.encounterDate = encounterDate;
	}

	public LocalTime getEncounterTime() {
		return encounterTime;
	}

	public void setEncounterTime(String encounterTime) {
		// this.encounterTime = encounterTime;
		if (!encounterTime.equals("")) {
			this.encounterTime = GlobalCommonDateUtils.getLocalTime(encounterTime);
			System.out.println("timeslot=" + encounterTime);
		} else {
			this.encounterTime = GlobalCommonDateUtils.getLocalTime("00:00");
		}
	}

	public Character getIsReferal() {
		return isReferal;
	}

	public void setIsReferal(Character isReferal) {
		this.isReferal = isReferal;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getKinDetailsId() {
		return kinDetailsId;
	}

	public void setKinDetailsId(Integer kinDetailsId) {
		this.kinDetailsId = kinDetailsId;
	}

	public Integer getPaymentEntitlementTypeId() {
		return paymentEntitlementTypeId;
	}

	public void setPaymentEntitlementTypeId(Integer paymentEntitlementTypeId) {
		this.paymentEntitlementTypeId = paymentEntitlementTypeId;
	}

	public String getEncounterNumber() {
		return encounterNumber;
	}

	public void setEncounterNumber(String encounterNumber) {
		this.encounterNumber = encounterNumber;
	}

	public Long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Long updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
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

	public List<KinDetailsDto> getListKinDetailsDto() {
		return listKinDetailsDto;
	}

	public void setListKinDetailsDto(List<KinDetailsDto> listKinDetailsDto) {
		this.listKinDetailsDto = listKinDetailsDto;
	}

	public Date getEncounterDateTime() {
		return encounterDateTime;
	}

	public void setEncounterDateTime(Date encounterDateTime) {
		this.encounterDateTime = encounterDateTime;
	}

	public Integer getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Character getIsScheduledAppointment() {
		return isScheduledAppointment;
	}

	public void setIsScheduledAppointment(Character isScheduledAppointment) {
		this.isScheduledAppointment = isScheduledAppointment;
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


	public Integer getEncounterTypeId() {
		return encounterTypeId;
	}


	public void setEncounterTypeId(Integer encounterTypeId) {
		this.encounterTypeId = encounterTypeId;
	}

}
