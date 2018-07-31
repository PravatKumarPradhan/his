package com.param.opd.coversheet.dao;

import com.param.global.common.Response;
import com.param.opd.coversheet.dto.PatientMedicalHistoryDto;
import com.param.opd.coversheet.model.PatientMedicalHistory;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IPatientMedicalHistoryDao extends IGenericDao<PatientMedicalHistory, Integer>{

	public Response savePatientMedicalHistory(PatientMedicalHistoryDto patientMedicalHistoryDto)throws ApplicationException;

	public Response getOldPatientMedicalHistory(PatientMedicalHistoryDto patientMedicalHistoryDto)throws ApplicationException;
	
	public Response getCurrentPatientMedicalHistory(PatientMedicalHistoryDto patientMedicalHistoryDto)throws ApplicationException;
	
	
	public Response updatePatientMedicalHistory(PatientMedicalHistoryDto patientMedicalHistoryDto)throws ApplicationException;
	
	public Response updateSaveNurseReviewedFlag(PatientMedicalHistory patientMedicalHistory)throws ApplicationException;
	
	public Response updateStatusEnterInError(PatientMedicalHistoryDto patientMedicalHistoryDto)throws ApplicationException;
}
