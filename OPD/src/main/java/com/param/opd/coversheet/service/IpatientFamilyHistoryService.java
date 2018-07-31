package com.param.opd.coversheet.service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.opd.coversheet.dto.PatientFamilyHistoryDto;
import com.param.opd.coversheet.dto.PatientPersonalHistoryDetailsDto;
import com.param.opd.coversheet.model.PatientFamilyHistory;
import com.param.opd.coversheet.model.PatientPersonalHistoryDetails;

public interface IpatientFamilyHistoryService {

	public Response savePatientFamilyHistory(PatientFamilyHistoryDto patientFamilyHistoryDto)throws ApplicationException;

	public Response getOldPatientFamilyHistory(PatientFamilyHistoryDto patientFamilyHistoryDto)throws ApplicationException;
	
	public Response getCurrentPatientFamilyHistory(PatientFamilyHistoryDto patientFamilyHistoryDto)throws ApplicationException;
	
	public Response updatePatientFamilyHistory(PatientFamilyHistoryDto patientFamilyHistoryDto)throws ApplicationException;
	
	public Response updateSaveNurseReviewedFlag(PatientFamilyHistory patientFamilyHistory)throws ApplicationException;
	
	public Response updateStatusEnterInError(PatientFamilyHistoryDto patientFamilyHistoryDto)throws ApplicationException;
}
