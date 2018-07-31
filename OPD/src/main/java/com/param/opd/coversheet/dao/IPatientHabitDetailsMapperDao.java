package com.param.opd.coversheet.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.opd.coversheet.dto.PatientHabitDetailsDto;
import com.param.opd.coversheet.model.PatientHabitDetails;

public interface IPatientHabitDetailsMapperDao extends IGenericDao<PatientHabitDetails, Integer>{

	public Response savePatientHabitDetailsMapper(PatientHabitDetailsDto patientHabitDetailsDto)throws ApplicationException;

	public Response getListOfPatientHabitDetailsMapper(PatientHabitDetailsDto patientHabitDetailsDto)throws ApplicationException;
	
	public Response updatePatientHabitDetailsMapper(PatientHabitDetailsDto patientHabitDetailsDto)throws ApplicationException;
	
	
	public Response getOldPatientHabitDetails(PatientHabitDetailsDto patientHabitDetailsDto)throws ApplicationException;
	
	public Response getCurrentPatientHabitDetails(PatientHabitDetailsDto patientHabitDetailsDto)throws ApplicationException;
	
	public Response updatePatientHabitDetails(PatientHabitDetailsDto patientHabitDetailsDto)throws ApplicationException;
	
	public Response updateSaveNurseReviewedFlag(PatientHabitDetails patientHabitDetails)throws ApplicationException;
	
	public Response updateStatusEnterInError(PatientHabitDetailsDto patientHabitDetailsDto)throws ApplicationException;
}
