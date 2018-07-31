package com.param.opd.coversheet.service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.opd.coversheet.dto.PatientFamilyHistoryDto;
import com.param.opd.coversheet.dto.PatientHabitDetailsDto;
import com.param.opd.coversheet.model.PatientFamilyHistory;
import com.param.opd.coversheet.model.PatientHabitDetails;

public interface IPatientHabitDetailsMapperService {

	public Response savePatientHabitDetailsMapper(PatientHabitDetailsDto patientHabitDetailsDto)throws ApplicationException;

	public Response getListOfPatientHabitDetailsMapper(PatientHabitDetailsDto patientHabitDetailsDto)throws ApplicationException;
	
	public Response getOldPatientHabitDetails(PatientHabitDetailsDto patientHabitDetailsDto)throws ApplicationException;
	
	public Response getCurrentPatientHabitDetails(PatientHabitDetailsDto patientHabitDetailsDto)throws ApplicationException;
	
	public Response updatePatientHabitDetails(PatientHabitDetailsDto patientHabitDetailsDto)throws ApplicationException;
	
	public Response updateSaveNurseReviewedFlag(PatientHabitDetails patientHabitDetails)throws ApplicationException;
	
	public Response updateStatusEnterInError(PatientHabitDetailsDto patientHabitDetailsDto)throws ApplicationException;
}
