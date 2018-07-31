package com.param.lis.microbiology.transaction.dto;

import java.time.LocalTime;
import java.util.List;

import com.param.lis.global.common.CommonDateUtils;

public class MicrobioResultEntryMasterDto {

	private Integer microbioResultEntryId;
	
	private Integer sensitivityResultId;

	private Integer orgId;

	private Integer orgUnitId;

	private Integer patientId;

	private Integer doctorId;

	private Integer labSampleDtlsId;

	private String microscopicExamination;

	private Integer createdBy;

	private Long createdDate;

	private Integer updatedBy;

	private Long updatedDate;

	private Character isDeleted;
	private Character checkTest;

	public Character getCheckTest() {
		return checkTest;
	}

	public void setCheckTest(Character checkTest) {
		this.checkTest = checkTest;
	}

	private Long incubationStartDate;

	private LocalTime incubationStartTime;

	private Character isIncubationComplete;

	private Character isMacro_exaComplete;

	private Character isMicro_exaComplete;

	/** Dummy Fields */

	private Integer incuObservationFlag;

	private String labSampleNo;

	private String visitType;

	private String uhid;

	private String patientDetails;

	private String doctorDetails;

	private String testDesc;

	private Character isAlliquoteReq;

	private String wardCode;

	private String bedNumber;

	private String priorityName;

	private String colorCode;

	private String sampleDesc;

	private String sampleBarcode;

	private Integer sampleStatusId;

	private Character sampleRecollectFlag;

	private Integer sampleId;
	
	private String incuStartDate;

	private String incuStartTime;

	private List<MicrobioResultDetailsMasterDto> listMicrobioResultDetailsMaster;
	
	private List<MicroscopicExaminationMasterDto> listMicroscopicExaminationMaster;

	public Integer getMicrobioResultEntryId() {
		return microbioResultEntryId;
	}

	public void setMicrobioResultEntryId(Integer microbioResultEntryId) {
		this.microbioResultEntryId = microbioResultEntryId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getOrgUnitId() {
		return orgUnitId;
	}

	public void setOrgUnitId(Integer orgUnitId) {
		this.orgUnitId = orgUnitId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public Integer getLabSampleDtlsId() {
		return labSampleDtlsId;
	}

	public void setLabSampleDtlsId(Integer labSampleDtlsId) {
		this.labSampleDtlsId = labSampleDtlsId;
	}

	public String getMicroscopicExamination() {
		return microscopicExamination;
	}

	public void setMicroscopicExamination(String microscopicExamination) {
		this.microscopicExamination = microscopicExamination;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Long updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Character getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Character isDeleted) 
	{
		this.isDeleted = (isDeleted == '\u0000') ? 'N' : isDeleted;
	}

	public LocalTime getIncubationStartTime()
	{
		return incubationStartTime;
	}

	public void setIncubationStartTime(String incubationStartTime) {

		if (incubationStartTime!=null&&!incubationStartTime.equals("")) {
			this.incubationStartTime = CommonDateUtils.getLocalTime(incubationStartTime);
			System.out.println("timeslot=" + incubationStartTime);
		} else {
			this.incubationStartTime = CommonDateUtils.getLocalTime("00:00");
		}
	}

	public List<MicrobioResultDetailsMasterDto> getListMicrobioResultDetailsMaster() {
		return listMicrobioResultDetailsMaster;
	}

	public void setListMicrobioResultDetailsMaster(
			List<MicrobioResultDetailsMasterDto> listMicrobioResultDetailsMaster) {
		this.listMicrobioResultDetailsMaster = listMicrobioResultDetailsMaster;
	}

	public String getLabSampleNo() {
		return labSampleNo;
	}

	public void setLabSampleNo(String labSampleNo) {
		this.labSampleNo = labSampleNo;
	}

	public String getVisitType() {
		return visitType;
	}

	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}

	public String getUhid() {
		return uhid;
	}

	public void setUhid(String uhid) {
		this.uhid = uhid;
	}

	public String getPatientDetails() {
		return patientDetails;
	}

	public void setPatientDetails(String patientDetails) {
		this.patientDetails = patientDetails;
	}

	public String getDoctorDetails() {
		return doctorDetails;
	}

	public void setDoctorDetails(String doctorDetails) {
		this.doctorDetails = doctorDetails;
	}

	public String getTestDesc() {
		return testDesc;
	}

	public void setTestDesc(String testDesc) {
		this.testDesc = testDesc;
	}

	public Character getIsAlliquoteReq() {
		return isAlliquoteReq;
	}

	public void setIsAlliquoteReq(Character isAlliquoteReq) {
		this.isAlliquoteReq = isAlliquoteReq;
	}

	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}

	public String getBedNumber() {
		return bedNumber;
	}

	public void setBedNumber(String bedNumber) {
		this.bedNumber = bedNumber;
	}

	public String getPriorityName() {
		return priorityName;
	}

	public void setPriorityName(String priorityName) {
		this.priorityName = priorityName;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public String getSampleBarcode() {
		return sampleBarcode;
	}

	public void setSampleBarcode(String sampleBarcode) {
		this.sampleBarcode = sampleBarcode;
	}

	public Integer getSampleStatusId() {
		return sampleStatusId;
	}

	public void setSampleStatusId(Integer sampleStatusId) {
		this.sampleStatusId = sampleStatusId;
	}

	public Character getSampleRecollectFlag() {
		return sampleRecollectFlag;
	}

	public void setSampleRecollectFlag(Character sampleRecollectFlag) {
		this.sampleRecollectFlag = sampleRecollectFlag;
	}

	public String getSampleDesc() {
		return sampleDesc;
	}

	public void setSampleDesc(String sampleDesc) {
		this.sampleDesc = sampleDesc;
	}

	public Integer getSampleId() {
		return sampleId;
	}

	public void setSampleId(Integer sampleId) {
		this.sampleId = sampleId;
	}

	public Character getIsIncubationComplete() {
		return isIncubationComplete;
	}

	public void setIsIncubationComplete(Character isIncubationComplete) {
		this.isIncubationComplete = isIncubationComplete;
		/*this.isIncubationComplete = (isIncubationComplete == '\u0000') ? 'N' : isIncubationComplete;*/
	}

	public Character getIsMacro_exaComplete() {
		return isMacro_exaComplete;
	}

	public void setIsMacro_exaComplete(Character isMacro_exaComplete) {
		/*this.isMacro_exaComplete = (isMacro_exaComplete == '\u0000') ? 'N' : isMacro_exaComplete;*/
		this.isMacro_exaComplete = isMacro_exaComplete;
	}

	public Character getIsMicro_exaComplete() {
		return isMicro_exaComplete;
	}

	public void setIsMicro_exaComplete(Character isMicro_exaComplete) {
		/*this.isMicro_exaComplete = (isMicro_exaComplete == '\u0000') ? 'N' : isMicro_exaComplete;*/
		this.isMicro_exaComplete = isMicro_exaComplete;
	}

	public Long getIncubationStartDate() {
		return incubationStartDate;
	}

	public void setIncubationStartDate(Long incubationStartDate) {
		this.incubationStartDate = incubationStartDate;
	}

	public Integer getIncuObservationFlag() {
		return incuObservationFlag;
	}

	public void setIncuObservationFlag(Integer incuObservationFlag) {
		this.incuObservationFlag = incuObservationFlag;
	}

	public List<MicroscopicExaminationMasterDto> getListMicroscopicExaminationMaster() {
		return listMicroscopicExaminationMaster;
	}

	public void setListMicroscopicExaminationMaster(
			List<MicroscopicExaminationMasterDto> listMicroscopicExaminationMaster) {
		this.listMicroscopicExaminationMaster = listMicroscopicExaminationMaster;
	}

	public Integer getSensitivityResultId() {
		return sensitivityResultId;
	}

	public void setSensitivityResultId(Integer sensitivityResultId) {
		this.sensitivityResultId = sensitivityResultId;
	}

	public String getIncuStartDate() {
		return incuStartDate;
	}

	public void setIncuStartDate(String incuStartDate) {
		this.incuStartDate = incuStartDate;
	}

	public String getIncuStartTime() {
		return incuStartTime;
	}

	public void setIncuStartTime(String incuStartTime) {
		this.incuStartTime = incuStartTime;
	}

	

}
