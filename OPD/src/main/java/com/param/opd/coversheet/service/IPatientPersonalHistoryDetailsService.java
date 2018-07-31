package com.param.opd.coversheet.service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.opd.coversheet.dto.PatientPersonalHistoryDetailsDto;
import com.param.opd.coversheet.model.ComplaintAppointmentMapper;
import com.param.opd.coversheet.model.PatientPersonalHistoryDetails;

@SuppressWarnings("rawtypes")
public interface IPatientPersonalHistoryDetailsService {


	public Response savePatientPersonalHistoryDetails(PatientPersonalHistoryDetailsDto patientPersonalHistoryDetailsDto)throws ApplicationException;

	public Response getListOfOldPatientPersonalHistoryDetails(PatientPersonalHistoryDetailsDto patientPersonalHistoryDetailsDto)throws ApplicationException;
	
	public Response getListOfCurrentPatientPersonalHistoryDetails(PatientPersonalHistoryDetailsDto patientPersonalHistoryDetailsDto)throws ApplicationException;
	
	public Response editPatientPersonalHistoryDetails(PatientPersonalHistoryDetailsDto patientPersonalHistoryDetailsDto)throws ApplicationException;
	
	public Response updatePatientPersonalHistoryDetails(PatientPersonalHistoryDetailsDto patientPersonalHistoryDetailsDto)throws ApplicationException;
	
	public Response updateSaveNurseReviewedFlag(PatientPersonalHistoryDetails patientPersonalHistoryDetails)throws ApplicationException;
	
	public Response updateStatusEnterInError(PatientPersonalHistoryDetailsDto patientPersonalHistoryDetailsDto)throws ApplicationException;
}
