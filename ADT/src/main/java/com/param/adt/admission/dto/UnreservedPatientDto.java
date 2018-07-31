package com.param.adt.admission.dto;

public class UnreservedPatientDto 
{
private int UnreservedtId;
	
	private Integer organizationId;
	
	private Integer unitId;
	
	private Integer admissionNoteId;
	
	private Integer patientId;
	
	private String doa;
	
	private String newDoa;
	
	private Integer bedCategoryId;
	
	private Integer reserveBedId;
	
	private Integer reasonForUnreserve;
	
	private Integer reasonForCancelation;
	
	private Integer reasonForAdmission;
	
	private char isCancelReservation;
	
	private char isFlexible;
	
	private char isRescheduleReservation;
	
	private char status;
	
	private int createdBy;
	
	private int updatedBy;
	
	private String createdDate;

	private String updatedDate;
	
	private Integer paymentEntitlementId;

	private Integer patientCategoryId;
	
	private char activeStatus;
	
	public char getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(char activeStatus) {
		this.activeStatus = (activeStatus == '\u0000') ? 'N' : activeStatus;
	}

	/*admissionNoteId:52 bedCategoryId:"1"createdBy:1 doa:"18-10-2017 00:00:00"isFlexiableDate:"N"newDoa:"14-10-2017 00:00:00"organizationId:1 patientCategoryId:"1"paymentEntitlementId:"1"reasonForAdmission:"1"reasonForUnreserve:"2"unitId:1 updatedBy:1*/
	/*createdBy:1 isCancelReservation:"Y"organizationId:1 reasonForCancelation:"1"unitId:1 updatedBy:1*/
	public Integer getPaymentEntitlementId() {
		return paymentEntitlementId;
	}

	public void setPaymentEntitlementId(Integer paymentEntitlementId) {
		this.paymentEntitlementId = paymentEntitlementId;
	}

	public Integer getPatientCategoryId() {
		return patientCategoryId;
	}

	public void setPatientCategoryId(Integer patientCategoryId) {
		this.patientCategoryId = patientCategoryId;
	}

	public int getUnreservedtId() {
		return UnreservedtId;
	}

	public void setUnreservedtId(int unreservedtId) {
		UnreservedtId = unreservedtId;
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

	public Integer getAdmissionNoteId() {
		return admissionNoteId;
	}

	public void setAdmissionNoteId(Integer admissionNoteId) {
		this.admissionNoteId = admissionNoteId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getDoa() {
		return doa;
	}

	public void setDoa(String doa) {
		this.doa = doa;
	}

	public String getNewDoa() {
		return newDoa;
	}

	public void setNewDoa(String newDoa) {
		this.newDoa = newDoa;
	}

	public Integer getBedCategoryId() {
		return bedCategoryId;
	}

	public void setBedCategoryId(Integer bedCategoryId) {
		this.bedCategoryId = bedCategoryId;
	}

	public Integer getReserveBedId() {
		return reserveBedId;
	}

	public void setReserveBedId(Integer reserveBedId) {
		this.reserveBedId = reserveBedId;
	}

	public Integer getReasonForUnreserve() {
		return reasonForUnreserve;
	}

	public void setReasonForUnreserve(Integer reasonForUnreserve) {
		this.reasonForUnreserve = reasonForUnreserve;
	}

	public Integer getReasonForCancelation() {
		return reasonForCancelation;
	}

	public void setReasonForCancelation(Integer reasonForCancelation) {
		this.reasonForCancelation = reasonForCancelation;
	}

	public Integer getReasonForAdmission() {
		return reasonForAdmission;
	}

	public void setReasonForAdmission(Integer reasonForAdmission) {
		this.reasonForAdmission = reasonForAdmission;
	}

	public char getIsCancelReservation() {
		return isCancelReservation;
	}

	public void setIsCancelReservation(char isCancelReservation) {
		this.isCancelReservation = (isCancelReservation == '\u0000') ? 'N' : isCancelReservation;
	}

	public char getIsFlexible() {
		return isFlexible;
	}

	public void setIsFlexible(char isFlexible) {
		this.isFlexible = (isFlexible == '\u0000') ? 'N' : isFlexible;
	}

	public char getIsRescheduleReservation() {
		return isRescheduleReservation;
	}

	public void setIsRescheduleReservation(char isRescheduleReservation) {
		this.isRescheduleReservation = (isRescheduleReservation == '\u0000') ? 'N' : isRescheduleReservation;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = (status == '\u0000') ? 'N' : status;
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

	
	
	
}
