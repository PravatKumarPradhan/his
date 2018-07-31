package com.param.opd.coversheet.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.opd.coversheet.dto.EncounterAssignmenMapperDto;
import com.param.opd.coversheet.model.EncounterDetails;

public interface IEncounterAssignmenMapperDao extends IGenericDao<EncounterDetails, Integer> {

	public Response saveEncounterAssignment(EncounterAssignmenMapperDto encounterAssignmenMapperDto)throws ApplicationException;
	
	public Response getOldListEncounterAssignment(EncounterAssignmenMapperDto encounterAssignmenMapperDto)throws ApplicationException;
}
