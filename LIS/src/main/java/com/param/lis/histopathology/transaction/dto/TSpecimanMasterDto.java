package com.param.lis.histopathology.transaction.dto;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TSpecimanMasterDto {

	private Integer tSpecimanId;

	private Integer orgId;

	private Integer orgUnitId;

	private String specimanExaminination;
	
	private String captureNote;

	private Integer labSampleDtlsId;

	private String histopathlogyNumber;

	private String barcodeNo;

	private Integer createdBy;

	private Long createdDate;

	private Integer updatedBy;

	private Long updatedDate;

	private Character isDeleted;

	private Integer specimanTypeId;

	private Integer specimanId;

	private String specimanName;

	private String uhid;
	
	private String genderName;
	
	private String patientDetails;
	
	private String doctorDetails;
	
	private Date dob;
	
	private Integer age;
	
	private String visitType;
	private Integer visitTypeId;
	private String testDesc;
	private String specimanType;
	private String frozenSection;
	private String notes;
	
	private List<TSubSpecimanMasterDto> listTSubSpecimanMaster;

	public Integer gettSpecimanId() {
		return tSpecimanId;
	}

	public void settSpecimanId(Integer tSpecimanId) {
		this.tSpecimanId = tSpecimanId;
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

	public String getSpecimanExaminination() {
		return specimanExaminination;
	}

	public void setSpecimanExaminination(String specimanExaminination) {
		this.specimanExaminination = specimanExaminination;
	}

	public Integer getLabSampleDtlsId() {
		return labSampleDtlsId;
	}

	public void setLabSampleDtlsId(Integer labSampleDtlsId) {
		this.labSampleDtlsId = labSampleDtlsId;
	}

	public String getHistopathlogyNumber() {
		return histopathlogyNumber;
	}

	public void setHistopathlogyNumber(String histopathlogyNumber) {
		this.histopathlogyNumber = histopathlogyNumber;
	}

	public String getBarcodeNo() {
		return barcodeNo;
	}

	public void setBarcodeNo(String barcodeNo) {
		this.barcodeNo = barcodeNo;
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

	public void setIsDeleted(Character isDeleted) {
		this.isDeleted = isDeleted;
	}

	public List<TSubSpecimanMasterDto> getListTSubSpecimanMaster() {
		return listTSubSpecimanMaster;
	}

	public void setListTSubSpecimanMaster(List<TSubSpecimanMasterDto> listTSubSpecimanMaster) {
		this.listTSubSpecimanMaster = listTSubSpecimanMaster;
	}

	public Integer getSpecimanTypeId() {
		return specimanTypeId;
	}

	public void setSpecimanTypeId(Integer specimanTypeId) {
		this.specimanTypeId = specimanTypeId;
	}

	public Integer getSpecimanId() {
		return specimanId;
	}

	public void setSpecimanId(Integer specimanId) {
		this.specimanId = specimanId;
	}

	public String getSpecimanName() {
		return specimanName;
	}

	public void setSpecimanName(String specimanName) {
		this.specimanName = specimanName;
	}

	public String getUhid() {
		return uhid;
	}

	public void setUhid(String uhid) {
		this.uhid = uhid;
	}

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
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

	public Date getDob() 
	{
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Integer getAge() 
	{
		Calendar dateOfBirth= Calendar.getInstance();
		dateOfBirth.setTime(dob==null?new Date():dob);
		Calendar today = Calendar.getInstance();
		int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
		if (today.get(Calendar.DAY_OF_YEAR) < dateOfBirth.get(Calendar.DAY_OF_YEAR))
		age--;
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getVisitType() {
		return visitType;
	}

	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}

	public String getTestDesc() {
		return testDesc;
	}

	public void setTestDesc(String testDesc) {
		this.testDesc = testDesc;
	}

	public String getSpecimanType() {
		return specimanType;
	}

	public void setSpecimanType(String specimanType) {
		this.specimanType = specimanType;
	}

	public String getFrozenSection() {
		return frozenSection;
	}

	public void setFrozenSection(String frozenSection) {
		this.frozenSection = frozenSection;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Integer getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public String getCaptureNote() {
		return captureNote;
	}

	public void setCaptureNote(String captureNote) {
		this.captureNote = captureNote;
	}
	
	
}
