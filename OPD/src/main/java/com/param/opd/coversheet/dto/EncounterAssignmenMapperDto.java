package com.param.opd.coversheet.dto;

import java.util.Date;

import javax.persistence.Column;

public class EncounterAssignmenMapperDto {

	private Integer encounterDetailsMapperId ;
	private char isAssignmentCompStatus;
	private String assignmentStartTime;
	private Integer assignmentStartBy;
	private Integer assignmentStopBy;
	private Integer assignmentStartRoleId;
	private Integer assignmentStopRoleId;
	private String assignmentStopTime;
	private char isConsultationStatus;
	private String consultationStartTime;
	private Integer consultationStartBy;
	private Integer consultationStopBy;
	private Integer consultationStartRoleId;
	private Integer consultationStopRoleId;
	private String consultationStopTime;
	private Integer unitId;
	private Integer organizationId;
	private Integer encounterId;
	private Integer patientId;
	
	private String VisitType;
	private String doctorName;
	private String specialityName;
	private String encounterDate;
	
	
	
	public String getEncounterDate() {
		return encounterDate;
	}
	public void setEncounterDate(String encounterDate) {
		this.encounterDate = encounterDate;
	}
	public String getVisitType() {
		return VisitType;
	}
	public void setVisitType(String visitType) {
		VisitType = visitType;
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
	public Integer getEncounterDetailsMapperId() {
		return encounterDetailsMapperId;
	}
	public void setEncounterDetailsMapperId(Integer encounterDetailsMapperId) {
		this.encounterDetailsMapperId = encounterDetailsMapperId;
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	
	public char getIsAssignmentCompStatus() {
		return isAssignmentCompStatus;
	}
	public void setIsAssignmentCompStatus(char isAssignmentCompStatus) {
		this.isAssignmentCompStatus = isAssignmentCompStatus == '\u0000'? 'N' : isAssignmentCompStatus;
	}
	public String getAssignmentStartTime() {
		return assignmentStartTime;
	}
	public void setAssignmentStartTime(String assignmentStartTime) {
		this.assignmentStartTime = assignmentStartTime;
	}
	public Integer getAssignmentStartBy() {
		return assignmentStartBy;
	}
	public void setAssignmentStartBy(Integer assignmentStartBy) {
		this.assignmentStartBy = assignmentStartBy;
	}
	public Integer getAssignmentStopBy() {
		return assignmentStopBy;
	}
	public void setAssignmentStopBy(Integer assignmentStopBy) {
		this.assignmentStopBy = assignmentStopBy;
	}
	public Integer getAssignmentStartRoleId() {
		return assignmentStartRoleId;
	}
	public void setAssignmentStartRoleId(Integer assignmentStartRoleId) {
		this.assignmentStartRoleId = assignmentStartRoleId;
	}
	public Integer getAssignmentStopRoleId() {
		return assignmentStopRoleId;
	}
	public void setAssignmentStopRoleId(Integer assignmentStopRoleId) {
		this.assignmentStopRoleId = assignmentStopRoleId;
	}
	
	public String getAssignmentStopTime() {
		return assignmentStopTime;
	}
	public void setAssignmentStopTime(String assignmentStopTime) {
		this.assignmentStopTime = assignmentStopTime;
	}
	public char getIsConsultationStatus() {
		return isConsultationStatus;
	}
	public void setIsConsultationStatus(char isConsultationStatus) {
		this.isConsultationStatus = isConsultationStatus == '\u0000'? 'N' : isConsultationStatus;
	}
	public String getConsultationStartTime() {
		return consultationStartTime;
	}
	public void setConsultationStartTime(String consultationStartTime) {
		this.consultationStartTime = consultationStartTime;
	}
	public Integer getConsultationStartBy() {
		return consultationStartBy;
	}
	public void setConsultationStartBy(Integer consultationStartBy) {
		this.consultationStartBy = consultationStartBy;
	}
	public Integer getConsultationStopBy() {
		return consultationStopBy;
	}
	public void setConsultationStopBy(Integer consultationStopBy) {
		this.consultationStopBy = consultationStopBy;
	}
	public Integer getConsultationStartRoleId() {
		return consultationStartRoleId;
	}
	public void setConsultationStartRoleId(Integer consultationStartRoleId) {
		this.consultationStartRoleId = consultationStartRoleId;
	}
	public Integer getConsultationStopRoleId() {
		return consultationStopRoleId;
	}
	public void setConsultationStopRoleId(Integer consultationStopRoleId) {
		this.consultationStopRoleId = consultationStopRoleId;
	}
	public String getConsultationStopTime() {
		return consultationStopTime;
	}
	public void setConsultationStopTime(String consultationStopTime) {
		this.consultationStopTime = consultationStopTime;
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
	public Integer getEncounterId() {
		return encounterId;
	}
	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}
	
	
}
