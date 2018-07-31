package com.param.adt.common;

public interface IAWSCredentials {

	public String ACCESS_KEY = "AKIAIPOC443VLH227T7A";
	public String SECRET_KEY = "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";
	
	public String PATIENT_BUCKET = "clini-patient-profiles";
	public String DOCTOR_BUCKET = "clini-doctor-profiles";
	public String EMPLOYEE_BUCKET = "clini-employee-profiles";
	public String ORDER_BUCKET = "order-pdf";
	
	public String VIGORE_PATIENT_BUCKET = "vigore-patient-profiles";
	
	public String APPOINTMENT_DOC_BUCKET = "clini-documents/appointment-docs/";
	public String APPOINTMENT_POLICY_DOC_BUCKET = "clini-documents/appointment-policy-docs/";
	
	public String DOCTOR_REG_DOC_BUCKET = "clini-documents/doctor-reg-docs/";
	public String APPOINTMENT_COVER_SHEET_BUCKET = "clini-documents/appointment-cover-sheets/";
	
	public String PATIENT_IDENTIFICATION_DOC = "clini-documents/patient-identification-docs";
	public String EMPLOYEE_IDENTIFICATION_DOC = "clini-documents/employee-identification-docs";
	public String PATIENT_DOCUMENTS = "clini-documents/patient-docs";
	
	public String COMMON_PATH = "https://s3-us-west-2.amazonaws.com/";
	
	public String COVERSHEET_UK_DICTATION_BUCKET = "clini-documents/coversheet-uk-dictation";
	
	public String ORDER_PATHOLOGY = "order-pdf/pathology";
	public String ORDER_MICROBIOLOGY = "order-pdf/microbiology";
	public String ORDER_RADIOLOGY = "order-pdf/radiology";
	
	public String PATIENT_DOCS_WITH_PASSWORD = "clini-documents/patient-docs-with-password";
	
	public String ACTIVE_DEPOSIT_MASTER_DOC_BUCKET = "clini-documents/active-deposit-master-docs";
}
