package com.param.opd.coversheet.service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.opd.coversheet.dto.AllergyPatientMapperDto;
import com.param.opd.coversheet.dto.PatientHabitDetailsDto;
import com.param.opd.coversheet.model.AllergyPatientMapper;
import com.param.opd.coversheet.model.PatientHabitDetails;

public interface IAllergyPatientMapperService {

	public Response saveAllergyPatientMapper(AllergyPatientMapperDto allergyPatientMapperDto)throws ApplicationException;

	public Response getListOfAllergyPatientMapper(AllergyPatientMapperDto allergyPatientMapperDto)throws ApplicationException;
	
	public Response cancelAllergyPatientMapper(AllergyPatientMapperDto allergyPatientMapperDto)throws ApplicationException;
	
	public Response getOldAllergyPatientMapper(AllergyPatientMapperDto allergyPatientMapperDto)throws ApplicationException;
	
	public Response getCurrentAllergyPatientMapper(AllergyPatientMapperDto allergyPatientMapperDto)throws ApplicationException;
	
	public Response updateAllergyPatientMapper(AllergyPatientMapperDto allergyPatientMapperDto)throws ApplicationException;
	
	public Response updateSaveNurseReviewedFlag(AllergyPatientMapper allergyPatientMapper)throws ApplicationException;
	
	public Response updateStatusEnterInError(AllergyPatientMapperDto allergyPatientMapperDto)throws ApplicationException;
	
}
