package com.param.opd.coversheet.dao;

import com.param.global.common.Response;
import com.param.opd.coversheet.dto.PatientFamilyHistoryDto;
import com.param.opd.coversheet.model.PatientFamilyHistory;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

public interface IPatientFamilyHistoryDao extends IGenericDao<PatientFamilyHistory, Integer>{

	public Response savePatientFamilyHistory(PatientFamilyHistoryDto patientFamilyHistoryDto)throws ApplicationException;
	
	public Response getOldPatientFamilyHistory(PatientFamilyHistoryDto patientFamilyHistoryDto)throws ApplicationException;
	
	public Response getCurrentPatientFamilyHistory(PatientFamilyHistoryDto patientFamilyHistoryDto)throws ApplicationException;
	
	public Response updatePatientFamilyHistory(PatientFamilyHistoryDto patientFamilyHistoryDto)throws ApplicationException;
	
	public Response updateSaveNurseReviewedFlag(PatientFamilyHistory patientFamilyHistory)throws ApplicationException;
	
	public Response updateStatusEnterInError(PatientFamilyHistoryDto patientFamilyHistoryDto)throws ApplicationException;

}
