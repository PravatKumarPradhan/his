package com.param.opd.coversheet.dto;

import java.util.Date;
import java.util.List;


public class PatientMedicalHistoryDto {

	private Integer patientMedicalHistoryId;
	private Integer appointmentId;
	private Integer diagnosisId;
	private Integer patientId;
	private Integer since;
	private String duration;
	private String remark;
	private Integer unitId;
	private Integer organizationId;
	private char status;
	private String created_date;
	private Integer created_by;
	private Integer updated_by;
	private String updated_date;
	private char isNoSignificantStatus;
	private String code;
	private String diagnosisName;
	
	private Integer roleId;
	private Integer encounterId;
	private char isEnterInError;
	private char isReviewedFlag;
	
	private List<ListOfPatientMedicalHistoryDto> listOfPatientMedicalHistoryDto;
	
	
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getEncounterId() {
		return encounterId;
	}
	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}
	public char getIsEnterInError() {
		return isEnterInError;
	}
	public void setIsEnterInError(char isEnterInError) {
		this.isEnterInError = isEnterInError;
	}
	public char getIsReviewedFlag() {
		return isReviewedFlag;
	}
	public void setIsReviewedFlag(char isReviewedFlag) {
		this.isReviewedFlag = isReviewedFlag;
	}
	public String getDiagnosisName() {
		return diagnosisName;
	}
	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	public char getIsNoSignificantStatus() {
		return isNoSignificantStatus;
	}
	public void setIsNoSignificantStatus(char isNoSignificantStatus) {
		this.isNoSignificantStatus = isNoSignificantStatus;
	}
	public List<ListOfPatientMedicalHistoryDto> getListOfPatientMedicalHistoryDto() {
		return listOfPatientMedicalHistoryDto;
	}
	public void setListOfPatientMedicalHistoryDto(
			List<ListOfPatientMedicalHistoryDto> listOfPatientMedicalHistoryDto) {
		this.listOfPatientMedicalHistoryDto = listOfPatientMedicalHistoryDto;
	}
	
	
	
	public Integer getPatientMedicalHistoryId() {
		return patientMedicalHistoryId;
	}
	public void setPatientMedicalHistoryId(Integer patientMedicalHistoryId) {
		this.patientMedicalHistoryId = patientMedicalHistoryId;
	}
	public Integer getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}
	public Integer getDiagnosisId() {
		return diagnosisId;
	}
	public void setDiagnosisId(Integer diagnosisId) {
		this.diagnosisId = diagnosisId;
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public Integer getSince() {
		return since;
	}
	public void setSince(Integer since) {
		this.since = since;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public Integer getCreated_by() {
		return created_by;
	}
	public void setCreated_by(Integer created_by) {
		this.created_by = created_by;
	}
	
	public Integer getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(Integer updated_by) {
		this.updated_by = updated_by;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public String getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(String updated_date) {
		this.updated_date = updated_date;
	}
	
	
}
