package com.param.opd.coversheet.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.opd.coversheet.dto.ImmunizationHistoryMapperDto;
import com.param.opd.coversheet.dto.PatientHabitDetailsDto;
import com.param.opd.coversheet.model.ImmunizationHistoryMapper;

public interface IImmunizationHistoryMapperDao extends IGenericDao<ImmunizationHistoryMapper, Integer>{


	public Response updateImmunizationHistoryForOldData(ImmunizationHistoryMapperDto immunizationHistoryMapperDto)throws ApplicationException;
	
	public Response saveImmunizationHistoryMapper(ImmunizationHistoryMapperDto immunizationHistoryMapperDto )throws ApplicationException;

	public Response getListOfImmunizationHistoryMapper(ImmunizationHistoryMapperDto immunizationHistoryMapperDto)throws ApplicationException;
	
	public Response getOldImmunizationHistoryMapper(ImmunizationHistoryMapperDto immunizationHistoryMapperDto)throws ApplicationException;
	
	public Response getCurrentImmunizationHistoryMapper(ImmunizationHistoryMapperDto immunizationHistoryMapperDto)throws ApplicationException;
	
	public Response updateImmunizationHistoryMapper(ImmunizationHistoryMapperDto immunizationHistoryMapperDto)throws ApplicationException;
	
	public Response updateSaveNurseReviewedFlag(ImmunizationHistoryMapper immunizationHistoryMapper)throws ApplicationException;
}
