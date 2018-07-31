package com.param.opd.coversheet.service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.opd.coversheet.dto.ImmunizationHistoryMapperDto;
import com.param.opd.coversheet.dto.PatientFamilyHistoryDto;
import com.param.opd.coversheet.model.ImmunizationHistoryMapper;
import com.param.opd.coversheet.model.PatientFamilyHistory;

public interface IImmunizationHistoryMapperService {

	public Response saveImmunizationHistoryMapper(ImmunizationHistoryMapperDto immunizationHistoryMapperDto )throws ApplicationException;

	public Response getListOfImmunizationHistoryMapper(ImmunizationHistoryMapperDto immunizationHistoryMapperDto)throws ApplicationException;
	
	
	public Response getOldImmunizationHistoryMapper(ImmunizationHistoryMapperDto immunizationHistoryMapperDto)throws ApplicationException;
	
	public Response getCurrentImmunizationHistoryMapper(ImmunizationHistoryMapperDto immunizationHistoryMapperDto)throws ApplicationException;
	
	public Response updateImmunizationHistoryMapper(ImmunizationHistoryMapperDto immunizationHistoryMapperDto)throws ApplicationException;
	
	public Response updateSaveNurseReviewedFlag(ImmunizationHistoryMapper immunizationHistoryMapper)throws ApplicationException;
}
