package com.param.adt.common;

public interface IImageDetailsConstant {

	String FILE_SEPERATOR	   = "/";
	String PARENT_IMAGE_PATH   = "C:/ClinivantageImages";
	
	/***** Patient profile Pic Folder & File Info...
	 * */	
	String PROFILE_PICTURE_PATH  = "/PatientProfilePicture";
	String DOCTOR_DOCUMENTS_PATH = "/DoctorDocuments";
	String DOCTOR_PROFILE_PICTURE_PATH = "/DoctorProfilePicture";
	String DOCTOR_SIGNATURE_PICTURE_PATH = "/DoctorSignature";
	
	String PATIENT_PROFILE_PICTURE_PATH  = PARENT_IMAGE_PATH + PROFILE_PICTURE_PATH;
	String DOCTOR_DOCUMENTS_FULL_PATH = PARENT_IMAGE_PATH + DOCTOR_DOCUMENTS_PATH;
	String DOCTOR_PROFILE_PICTURE_FULL_PATH = PARENT_IMAGE_PATH + DOCTOR_PROFILE_PICTURE_PATH;
	String DOCTOR_SIGNATURE_PICTURE_FULL_PATH = PARENT_IMAGE_PATH + DOCTOR_SIGNATURE_PICTURE_PATH;
	
	String FILE_NAME_SERPERATOR = "_";
	
	String DOT = ".";
	
	/*--extensions--*/
	String JPG = "jpg";
	
	String PNG = "png";
	
	//---- Appointment----//
	String APPOINTMENT_DOCUMENT_PATH = "/AppointmentDocuments";
	String APPOINTMENT_DOCUMENT_FULL_PATH = PARENT_IMAGE_PATH + APPOINTMENT_DOCUMENT_PATH;
	
	//---Appointment Policy ---//
	String POLICY_DOCUMENT_PATH = "/PolicyDocuments";
	String POLICY_DOCUMENT_FULL_PATH = PARENT_IMAGE_PATH + POLICY_DOCUMENT_PATH;
	
}
