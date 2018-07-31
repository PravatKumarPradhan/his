package com.param.opd.coversheet.service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.opd.coversheet.dto.EncounterAssignmenMapperDto;
import com.param.opd.coversheet.dto.GeneralExaminationAppoMapperDto;

public interface IEncounterAssignmenMapperService {

	public Response saveEncounterAssignment(EncounterAssignmenMapperDto encounterAssignmenMapperDto)throws ApplicationException;
	
	public Response getOldListEncounterAssignment(EncounterAssignmenMapperDto encounterAssignmenMapperDto)throws ApplicationException;
}
