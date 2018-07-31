package com.param.adt.admission.dto;

import java.util.List;

public class AdmissionNoteDto 
{
	private int admissionNoteId;
	
	private Integer unitId;
	
	private Integer organizationId;
	
	private Integer requestBy;
	
	private Integer requestToDoctorId;
	
	private Integer doctorSpecialityId;
	
	private String specialityName;
	
	private Integer reasonId;
	
	private String reasonDesc;
	
	private Integer patientId;
	
	private String UHIDNumber;

	private String dFirstName;
	
	private String dMiddleName;
	
	private String dLastName;
	
	private String pFirstName;
	
	private String pMiddleName;
	
	private String pLastName;
	
	private String dob;
	
	private Integer preVisitId;
	
	private Integer visitTypeId;
	
	private String visitTypeName;
	
	private char isFlexiableDate;
	
	private char admissionStatus;
	
	private char isCancel;

	private int canceledBy;
	
	private String canceledDate;
	
	private Integer admissionId;
	
	private char status;
	
	private int createdBy;
	
	private int updatedBy;
	
	private String createdDate;
	
	private String updatedDate;
	
	private Integer bedCategoryId;
	
	private Integer oldBedCategoryId;
	
	private Integer paymentEntitlementId;
	
	private Integer patientCategoryId;
	
	private String doa;
	
	private String oldDoa;
	
	private String pdd;
	
	private char activeStatus;
	
	private Integer prefixId;
	
	private String prefixDesc;
	
	private Integer id;
	
	private String code;
	
	private String bedCategoryDesc;
	
	private String patientCategory;
	
	private String paymentEntitlementDesc;
	
	private Integer offset;
	
	private Integer noOfRecordsPerPage;
	
	private List<AdmissionPatientDocumentsDto> documentArray;
	
	private String note;
	
	private Integer tPatientId;
	
	private Integer bedCategoryWaitingListId;
	
	private Integer waitListNumber;
	
	
	
	
	public Integer getOldBedCategoryId() {
		return oldBedCategoryId;
	}

	public void setOldBedCategoryId(Integer oldBedCategoryId) {
		this.oldBedCategoryId = oldBedCategoryId;
	}

	public String getOldDoa() {
		return oldDoa;
	}

	public void setOldDoa(String oldDoa) {
		this.oldDoa = oldDoa;
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

	public Integer gettPatientId() {
		return tPatientId;
	}

	public void settPatientId(Integer tPatientId) {
		this.tPatientId = tPatientId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public List<AdmissionPatientDocumentsDto> getDocumentArray() {
		return documentArray;
	}

	public void setDocumentArray(List<AdmissionPatientDocumentsDto> documentArray) {
		this.documentArray = documentArray;
	}

	public String getSpecialityName() {
		return specialityName;
	}

	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}

	public String getBedCategoryDesc() {
		return bedCategoryDesc;
	}

	public void setBedCategoryDesc(String bedCategoryDesc) {
		this.bedCategoryDesc = bedCategoryDesc;
	}

	public String getPatientCategory() {
		return patientCategory;
	}

	public void setPatientCategory(String patientCategory) {
		this.patientCategory = patientCategory;
	}

	public String getPaymentEntitlementDesc() {
		return paymentEntitlementDesc;
	}

	public void setPaymentEntitlementDesc(String paymentEntitlementDesc) {
		this.paymentEntitlementDesc = paymentEntitlementDesc;
	}

	public String getPrefixDesc() {
		return prefixDesc;
	}

	public void setPrefixDesc(String prefixDesc) {
		this.prefixDesc = prefixDesc;
	}

	public String getGenderCode() {
		return code;
	}

	public void setGenderCode(String code) {
		this.code = code;
	}

	public String getReasonDesc() {
		return reasonDesc;
	}

	public void setReasonDesc(String reasonDesc) {
		this.reasonDesc = reasonDesc;
	}

	public String getUHIDNumber() {
		return UHIDNumber;
	}

	public void setUHIDNumber(String uHIDNumber) {
		UHIDNumber = uHIDNumber;
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

	public String getVisitTypeName() {
		return visitTypeName;
	}

	public void setVisitTypeName(String visitTypeName) {
		this.visitTypeName = visitTypeName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPrefixId() {
		return prefixId;
	}

	public void setPrefixId(Integer prefixId) {
		this.prefixId = prefixId;
	}

	public Integer getBedCategoryId() {
		return bedCategoryId;
	}

	public void setBedCategoryId(Integer bedCategoryId) {
		this.bedCategoryId = bedCategoryId;
	}

	public Integer getPaymentEntitlementId() {
		return paymentEntitlementId;
	}

	public void setPaymentEntitlementId(Integer paymentEntitlementId) {
		this.paymentEntitlementId = paymentEntitlementId;
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

	public char getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(char activeStatus) {
		this.activeStatus = activeStatus;
	}

	public int getAdmissionNoteId() {
		return admissionNoteId;
	}

	public void setAdmissionNoteId(int admissionNoteId) {
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

	public Integer getRequestBy() {
		return requestBy;
	}

	public void setRequestBy(Integer requestBy) {
		this.requestBy = requestBy;
	}

	public Integer getRequestToDoctorId() {
		return requestToDoctorId;
	}

	public void setRequestToDoctorId(Integer requestToDoctorId) {
		this.requestToDoctorId = requestToDoctorId;
	}

	public Integer getDoctorSpecialtyId() {
		return doctorSpecialityId;
	}

	public void setDoctorSpecialtyId(Integer doctorSpecialtyId) {
		this.doctorSpecialityId = doctorSpecialtyId;
	}

	public Integer getReasonId() {
		return reasonId;
	}

	public void setReasonId(Integer reasonId) {
		this.reasonId = reasonId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getPreVisitId() {
		return preVisitId;
	}

	public void setPreVisitId(Integer preVisitId) {
		this.preVisitId = preVisitId;
	}

	public Integer getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public char getIsFlexiableDate() {
		return isFlexiableDate;
	}

	public void setIsFlexiableDate(char isFlexiableDate) {
		this.isFlexiableDate = isFlexiableDate;
	}

	public char getAdmissionStatus() {
		return admissionStatus;
	}

	public void setAdmissionStatus(char admissionStatus) {
		this.admissionStatus = admissionStatus;
	}

	public char getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(char isCancel) {
		this.isCancel = isCancel;
	}

	
	

	public Integer getDoctorSpecialityId() {
		return doctorSpecialityId;
	}

	public void setDoctorSpecialityId(Integer doctorSpecialityId) {
		this.doctorSpecialityId = doctorSpecialityId;
	}

	public int getCanceledBy() {
		return canceledBy;
	}

	public void setCanceledBy(int canceledBy) {
		this.canceledBy = canceledBy;
	}

	public Integer getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(Integer admissionId) {
		this.admissionId = admissionId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
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

	public String getCanceledDate() {
		return canceledDate;
	}

	public void setCanceledDate(String canceledDate) {
		this.canceledDate = canceledDate;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getPatientCategoryId() {
		return patientCategoryId;
	}

	public void setPatientCategoryId(Integer patientCategoryId) {
		this.patientCategoryId = patientCategoryId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	
	
}
