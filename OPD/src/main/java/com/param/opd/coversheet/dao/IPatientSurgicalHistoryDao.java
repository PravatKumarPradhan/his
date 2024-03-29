package com.param.opd.coversheet.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.opd.coversheet.dto.PatientSurgicalHistoryDto;
import com.param.opd.coversheet.model.PatientFamilyHistory;
import com.param.opd.coversheet.model.PatientSurgicalHistory;

public interface IPatientSurgicalHistoryDao extends IGenericDao<PatientSurgicalHistory, Integer>{

	public Response savePatientSurgicalHistory(PatientSurgicalHistoryDto patientSurgicalHistoryDto)throws ApplicationException;

	public Response getOldListOfPatientSurgicalHistory(PatientSurgicalHistoryDto patientSurgicalHistoryDto)throws ApplicationException;
	
	public Response getCurrentListOfPatientSurgicalHistory(PatientSurgicalHistoryDto patientSurgicalHistoryDto)throws ApplicationException;
	
	public Response updatePatientSurgicalHistory(PatientSurgicalHistoryDto patientSurgicalHistoryDto)throws ApplicationException;
	
	public Response updateSaveNurseReviewedFlag(PatientSurgicalHistory patientSurgicalHistory)throws ApplicationException;
	
	public Response updateStatusEnterInError(PatientSurgicalHistoryDto patientSurgicalHistoryDto)throws ApplicationException;
}
