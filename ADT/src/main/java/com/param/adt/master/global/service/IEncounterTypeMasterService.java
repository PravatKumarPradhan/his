package com.param.adt.master.global.service;


import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.EncounterTypeMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IEncounterTypeMasterService 
{

	@Transactional
	Response addEncounterTypeMaster(EncounterTypeMasterDto encounterTypeMasterDto) throws ApplicationException;

	@Transactional
	Response getEncounterTypeMasterList(EncounterTypeMasterDto encounterTypeMasterDto) throws ApplicationException;

	@Transactional
	Response updateEncounterTypeMaster(EncounterTypeMasterDto encounterTypeMasterDto) throws ApplicationException;

	@Transactional
	Response getEncounterTypeById(EncounterTypeMasterDto encounterTypeMasterDto) throws ApplicationException;

	@Transactional
	Response updateStatusForEncounterType(EncounterTypeMasterDto encounterTypeMasterDto) throws ApplicationException;

	@Transactional
	Response getActiveEncounterTypeList() throws ApplicationException;

	@Transactional 
	Response getEncounterCount(EncounterTypeMasterDto encounterTypeMasterDto) throws ApplicationException;

}
