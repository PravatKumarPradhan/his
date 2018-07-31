package com.param.opd.coversheet.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.opd.coversheet.dto.AllergyPatientMapperDto;
import com.param.opd.coversheet.model.AllergyPatientMapper;


public interface IAllergyPatientMapperDao extends IGenericDao<AllergyPatientMapper, Integer>{

	public Response saveAllergyPatientMapper(AllergyPatientMapperDto allergyPatientMapperDto)throws ApplicationException;

	public Response getListOfAllergyPatientMapper(AllergyPatientMapperDto allergyPatientMapperDto)throws ApplicationException;
	
	public Response cancelAllergyPatientMapper(AllergyPatientMapperDto allergyPatientMapperDto)throws ApplicationException;

	public Response getOldAllergyPatientMapper(AllergyPatientMapperDto allergyPatientMapperDto)throws ApplicationException;
	
	public Response getCurrentAllergyPatientMapper(AllergyPatientMapperDto allergyPatientMapperDto)throws ApplicationException;
	
	public Response updateAllergyPatientMapper(AllergyPatientMapperDto allergyPatientMapperDto)throws ApplicationException;
	
	public Response updateSaveNurseReviewedFlag(AllergyPatientMapper allergyPatientMapper)throws ApplicationException;
	
	public Response updateStatusEnterInError(AllergyPatientMapperDto allergyPatientMapperDto)throws ApplicationException;
}
