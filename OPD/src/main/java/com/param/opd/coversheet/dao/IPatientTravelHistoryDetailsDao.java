package com.param.opd.coversheet.dao;

import com.param.global.common.Response;
import com.param.opd.coversheet.dto.PatientTravelHistoryDetailsDto;
import com.param.opd.coversheet.model.PatientTravelHistoryDetails;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

public interface IPatientTravelHistoryDetailsDao extends IGenericDao<PatientTravelHistoryDetails, Integer>{

	Response getListOfPatientTravelHistoryDetails(PatientTravelHistoryDetailsDto patientTravelHistoryDetailsDto)throws ApplicationException;

	Response savePatientTravelHistoryDetails(PatientTravelHistoryDetailsDto patientTravelHistoryDetailsDto)throws ApplicationException;

	
	/*ADDED BY JYOTI
	DATE - 15-05-2018*/
	public Response getOldPatientTravelHistory(PatientTravelHistoryDetailsDto patientTravelHistoryDetailsDto)throws ApplicationException;
	
	public Response getCurrentPatientTravelHistory(PatientTravelHistoryDetailsDto patientTravelHistoryDetailsDto)throws ApplicationException;
	
	public Response updatePatientTravelHistory(PatientTravelHistoryDetailsDto patientTravelHistoryDetailsDto)throws ApplicationException;
	
	public Response updateSaveNurseReviewedFlag(PatientTravelHistoryDetails patientTravelHistoryDetails)throws ApplicationException;
	
	public Response updateStatusEnterInError(PatientTravelHistoryDetailsDto patientTravelHistoryDetailsDto)throws ApplicationException;
}
