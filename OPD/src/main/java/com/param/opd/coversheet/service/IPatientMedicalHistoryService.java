package com.param.opd.coversheet.service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.opd.coversheet.dto.PatientFamilyHistoryDto;
import com.param.opd.coversheet.dto.PatientMedicalHistoryDto;
import com.param.opd.coversheet.model.PatientFamilyHistory;
import com.param.opd.coversheet.model.PatientMedicalHistory;

@SuppressWarnings("rawtypes")
public interface IPatientMedicalHistoryService {

	public Response savePatientMedicalHistory(PatientMedicalHistoryDto patientMedicalHistoryDto)throws ApplicationException;

	public Response getOldPatientMedicalHistory(PatientMedicalHistoryDto patientMedicalHistoryDto)throws ApplicationException;
	
	public Response getCurrentPatientMedicalHistory(PatientMedicalHistoryDto patientMedicalHistoryDto)throws ApplicationException;
	
	public Response updatePatientMedicalHistory(PatientMedicalHistoryDto patientMedicalHistoryDto)throws ApplicationException;
	
	public Response updateSaveNurseReviewedFlag(PatientMedicalHistory patientMedicalHistory)throws ApplicationException;
	
	public Response updateStatusEnterInError(PatientMedicalHistoryDto patientMedicalHistoryDto)throws ApplicationException;

}
