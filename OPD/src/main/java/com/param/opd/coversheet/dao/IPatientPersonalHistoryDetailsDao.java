package com.param.opd.coversheet.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.opd.coversheet.dto.PatientPersonalHistoryDetailsDto;
import com.param.opd.coversheet.model.PatientPersonalHistoryDetails;

public interface IPatientPersonalHistoryDetailsDao extends IGenericDao<PatientPersonalHistoryDetails, Integer>{

	public Response savePatientPersonalHistoryDetails(PatientPersonalHistoryDetailsDto patientPersonalHistoryDetailsDto)throws ApplicationException;

	public Response getListOfOldPatientPersonalHistoryDetails(PatientPersonalHistoryDetailsDto patientPersonalHistoryDetailsDto)throws ApplicationException;
	
	public Response getListOfCurrentPatientPersonalHistoryDetails(PatientPersonalHistoryDetailsDto patientPersonalHistoryDetailsDto)throws ApplicationException;
	
	public Response updatePatientPersonalHistoryDetails(PatientPersonalHistoryDetailsDto patientPersonalHistoryDetailsDto)throws ApplicationException;
	
	public Response updateSaveNurseReviewedFlag(PatientPersonalHistoryDetails patientPersonalHistoryDetails)throws ApplicationException;
	
	public Response updateStatusEnterInError(PatientPersonalHistoryDetailsDto patientPersonalHistoryDetailsDto)throws ApplicationException;
}
